<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="/css/pintuer.css">
    <link rel="stylesheet" href="/css/admin.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/pintuer.js"></script>  
    <link rel="stylesheet" href="/css/inventory_state.css">
    <link rel="stylesheet" href="/css/alert_box.css">
	<link href="/linkage/css/city-picker.css" rel="stylesheet" type="text/css"/>
	
    <script src="/util/region/js/jquery.citys.js"></script>
	<script src="/util/cookie_util.js"></script>
    <script src="/util/jiml-utils.js"></script>
    <script src="/zb/inventory/inventoryList.js"></script>
	
</head>
<body style="position: relative;min-height: 600px;min-width: 1020px;">
  	<div class="panel admin-panel accredit-height">
	    <!-- <div class="panel-head"><strong class="icon-reorder"> 库存同步</strong></div> -->
	    <div class="padding border-bottom">
		    <ul class="search">
		        <li>自动上传：
		          <select class="input" style="width:150px; line-height:17px; display:inline-block" id="inventoryList_warehouse">
		            <option value="">全部</option>
		            <option value="1">启用</option>
		            <option value="0">停用</option>
		          </select>
		        </li>
		        <li>关键字：
		          <input type="text" placeholder="仓库编码/仓库名称" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
		          <a href="javascript:;" class="button border-main icon-search" id="inventoryList_query"> 搜索</a>
		        </li>
	     	</ul>
	    </div>
	    <ul class="search search_bottom">
	        <li>
	        	<a href="javascript:;" class="button border-main add_button">新建</a>
	        </li>
     	</ul>
	    <table class="table table-hover text-center list_table" id="inventoryList_table">
			<!-- 加载内容 -->
			
		</table>
 	</div>
 	<div class="inventory_alert_box">
 		<div class="new_inventory_alert">
 			<div class="new_inventory_title">
 				<span></span>
 				<div class="new_inventory_delbtn">×</div>
 			</div>
 			<div class="new_inventory_content">
				<div class="content_btn_box">
					<div class="content_btn">基本信息</div>
					<div class="content_btn">SP盒子配置</div>
				</div>
				<div class="content_box">
					<div class="content_text">
						<div class="content_row">
						<input type="hidden" id="text_warehouseId">
							<div class="content_col">
								<span>仓库编码：</span>
								<input type="text" id="text_userWarehouseCode">
							</div>
							<div class="content_col">
								<span>仓库名称：</span>
								<input type="text" id="text_warehouseName">
							</div>
						</div>
						<div class="content_row">
							<div class="content_col">
								<span>联系人：</span>
								<input type="text" id="text_consignorName">
							</div>
							<div class="content_col">
								<span>联系人电话：</span>
								<input type="text" id="text_consignorTel">
							</div>
						</div>
						<div class="content_dz">
							<span class="span">地址：</span>
							<select name="" id="" style="float: left;">
								<option value="1">中国</option>
								<option value="2">海外</option>
							</select>
							<div id="city-picker3-parent">
								<input id="city-picker3" class="form-control" readonly type="text" value="" data-toggle="city-picker">
							</div>
						</div>
						<div class="content_row">
							<span class="span">&nbsp;</span>
							<input type="text" class="xx_dz" placeholder="详细地址（粘贴或回车自动解析）" id="text_userAddress">
						</div>
					</div>
					<div class="content_text">
						<div class="box_open">
							<input type="checkbox">
							<span>
								开启【SP盒子对接配置】
							</span>
						</div>
						<div class="box_open">
							<span>输入配对编码</span>
							<input type="text">
						</div>
						<div class="box_open">
							<input type="checkbox">
							<span>
								同时开启【自动审核】（订单每分钟自动审核下发到SP盒子）
							</span>
						</div>
						<div class="box_open">
							<span>
								商品信息下发方式：
							</span>
							<input type="radio" name="shopping">原始线上商品名
							<input type="radio" name="shopping">系统商品简称
							<input type="radio" name="shopping">隐藏商品信息
						</div>
						<div class="box_open">
							<span>
								配置相应的SP接码后，分配到此仓库的所有订单将流转到SP盒子，且由SP盒子打单
							</span>
						</div>
					</div>
				</div>
				<div class="content_tj">
					<a href="javascript:;" class="button border-main a_btn_sync" id="inventoryList_save"> 保存</a>
					<a href="javascript:;" class="button border-main a_btn_sync inventory_del"> 取消</a>
				</div>
 			</div>
 		</div>
 	</div>
<script src="/linkage/js/city-picker.data.js"></script>
<script src="/linkage/js/city-picker.js"></script>
<script src="/linkage/js/main.js"></script>

<%--<script src="/js/inventory_list.js"></script>--%>
</body>
</html>