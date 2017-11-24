<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta name="renderer" content="webkit">
		<title>修改订单</title>
		<link rel="stylesheet" href="/css/pintuer.css">
		<link rel="stylesheet" href="/css/admin.css">
		<script src="/js/jquery.js"></script>
		<script src="/js/pintuer.js"></script>
		<script src="/util/jiml-utils.js"></script>
        <script src="/util/region/js/jquery.citys.js"></script>
        <script src="/jxb/js/order-add-update.js"></script>
        <script src="/jxb/js/order-update.js"></script>
        <script src="/util/pattern.js"></script>
        <script src="../js/big_box.js"></script>
        <script type="text/javascript">
        	// 地区下拉初始值
        	var regionCode = ${order[0].buyerInfo.regionId==null?0:order[0].buyerInfo.regionId};
        </script>
	</head>
	<style>
	  .div_big_box:after{
	  	content: "";
	  	display: block;
	  	height: 0;
	  	clear: both;
	  }
	</style>
	<body style="position:relative;">
		<div class="div_big_box">
			<div class="content_box">
				<form id="form1">
				<input name="orderId" value="${order[0].id }" style="display: none;"/>
				<div class="panel admin-panel" style="margin-bottom: 20px;">
					<div class="panel-head">
						<strong class="icon-reorder">修改订单</strong></div>
					<div class="body-content" style="padding-bottom:0;">
						<div class="form-x">
							<div class="form-group">
					            <div class="label">
					              <label>买家会员账号：</label>
					            </div>
					            <div class="field">
					              <!-- 标记订单买家信息是否已存在 -->
					              <input value="${order[0].buyerInfo.id }" name="buyerId" style="display: none;"/>
					              <input class="input w50" value="${order[0].buyerInfo.buyerAccount }" name="account" id="account" ${order[0].buyerInfo.buyerAccount==null||order[0].buyerInfo.buyerAccount==''?'':'disabled="disabled"' }maxlength="30"/>
					              <div class="tips"></div>
					            </div>
					        </div>
							<div class="form-group">
								<div class="label">
									<label>收件人：</label></div>
								<div class="field">
									<input class="input w50" value="${order[0].buyerInfo.consigneeName }" name="name" id="name" maxlength="10"/>
									<div class="tips"></div>
								</div>
							</div>
							<div class="form-group">
								<div class="label">
									<label>联系电话/手机：</label></div>
								<div class="field">
									<input class="input w50" value="${order[0].buyerInfo.consigneeMobile }" name="tel" id="tel" type="tel" maxlength="30"/>
									<div class="tips"></div>
								</div>
							</div>
							<div class="form-group">
								<div class="label">
									<label>地址：</label></div>
								<div class="field" id ="addrDiv">
									<div id="addrSelect"></div>
									<select id="province" name="province"class="input" style="width:200px; line-height:17px;display:inline-block;"></select>  
									<select id="city" name="city" class="input" style="width:200px; line-height:17px;display:inline-block;"></select>  
									<select id="area" name="area"class="input" style="width:200px; line-height:17px;display:inline-block;"></select> 
									<!-- <select id="street" name="street"class="input" style="width:200px; line-height:17px;display:inline-block;"></select>  -->
									<input class="input" value="${order[0].buyerInfo.userAddress }" name="addr" id="addr" maxlength="60" style="display:inline-block;width:300px;"placeholder="详细地址"/>
									<div class="tips"></div>
								</div>
							</div>
							<div class="form-group">
								<div class="label">
									<label>备注：</label></div>
								<div class="field">
									<input class="input" value="${order[0].sellerWord }" name="sellerWord" id="sellerWord"/>
									<div class="tips"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel admin-panel">
					<div class="panel-head">
						<strong class="icon-reorder">添加商品</strong></div>
					<div class="padding border-bottom">
						<ul class="search">
							<li>
								<a class="button border-main alert_page" href="javascript:;" onclick="openDiv()">
									<span class="icon-edit"></span>添加商品</a>
							</li>
						</ul>
					</div>
					<table class="table table-hover text-center" >
						<thead>
							<tr>
								<th width="120">商品编码</th>
								<th>商品名称</th>
								<th>商品规格</th>
								<th>商品价格</th>
								<th>数量</th>
								<th>编辑</th></tr></thead>
						<tbody id="have_item_table">
							<!-- 欲购商品列表加载区 -->
							<c:forEach var="item" items="${order[0].itemCommonWarehouse_S }" varStatus="index">
						        <tr class="have_tr" id="item${item.id }">
						          <td style="display:none;"><input name="itemIds" value="${item.id }"/></td>
						          <td>${item.erpItemNum }</td>
						          <td>${item.name }</td>
						          <td>${item.color }*${item.size }</td>  
						          <td>￥${item.normalPrice }</td>
						          <td><input type="text" name="quantity" value="${item.quantity }" style="width:50px; text-align:center; border:1px solid #ddd; padding:7px 0;" /></td>
						          <td><div class="button-group"><a class="button border-red" href="javascript:void(0)" onclick="del(this)"><span class="icon-trash-o"></span> 删除</a> </div></td>
						        </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</form>
				<div class="panel admin-panel" style="border:none;">
					<div class="body-content">
						<div class="form-x">
							<div class="form-group ">
								<div class="label">
									<label></label>
								</div>
								<div class="field">
									<button class="button bg-main icon-check-square-o" onclick="submitOrder()">提交</button></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="alert_data-box" >
			<div class="alert_data" style="height: 100%;">
				<div class="panel admin-panel">
					<div class="panel-head">
					<strong class="icon-reorder">添加商品</strong></div>
					<div class="padding border-bottom">
						<form id="form2">
					          <ul class="search" style="padding-left:10px;">
					            <li>品牌：</li>
					            <li>
					              <select name="brand" class="input" style="width:200px; line-height:17px;" onchange="loadItemByPage(1)">
					                <option value="">请选择分类</option>
					                <c:forEach items="${brandList}" var="brand">
					                	<option value="${brand }">${brand }</option>
					                </c:forEach>
					              </select>
					            </li>
					            <li>类目：</li>
					            <li>
					              <select name="category" class="input" style="width:200px; line-height:17px;" onchange="loadItemByPage(1)">
					                <option value="">请选择分类</option>
					                <c:forEach items="${categoryList}" var="category">
					                	<option value="${category }">${category }</option>
					                </c:forEach>
					              </select>
					            </li>
					            <li>搜索框：</li>
					            <li>
					              <input type="text" placeholder="请输入编码/名称/规格搜索" name="keyword" class="input" style="width:250px; line-height:17px;display:inline-block" />
					              <a href="javascript:void(0)" class="button border-main icon-search" onclick="loadItemByPage(1)" > 搜索</a></li>
					          </ul>
						</form>
					</div>
					<table class="table table-hover text-center" id="item_table">
						<!-- 所有商品列表加载区 -->
					</table>
				</div>
				<div class="panel admin-panel" style="border:none;">
					<div class="body-content">
						<div class="form-x">
							<div class="form-group ">
								<div class="label">
									<label></label>
								</div>
								<div class="field">
									<button class="button bg-main icon-check-square-o" onclick="submitItem()">提交</button></div>
							</div>
						</div>
					</div>
				</div>
				<div class="delete_btn" style="top: 4px;" onclick="closeDiv()">×</div>
			</div>
		</div>
	</body>
	<script>
	  var boxHeight=document.querySelector('.div_big_box').offsetHeight;
	  var alert_data_box=document.querySelector(".alert_data-box");
	  blackHeight(alert_data_box,boxHeight)
	</script>
</html>