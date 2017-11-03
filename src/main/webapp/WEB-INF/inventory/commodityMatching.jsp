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
    <script src="/js/big_box.js"></script>
    <style>
		.div_big_box:after{
			content: "";
			display: inline-block;
			height: 0;
			clear: both;
		}
    </style>

	<script src="/util/cookie_util.js"></script>
	<script src="/util/jiml-utils.js"></script>
	<script src="/zb/inventory/commodityMatching.js"></script>

</head>
<body style="position: relative;min-height: 600px;">
	<div class="div_big_box">
	 	<div class="panel admin-panel accredit-height" id="commodityMatchingTableParent">
	 		<div class="padding border-bottom matching_box">
			    <ul class="search">
			    	<li>查询：</li>
			        <li>仓库：
			          <select class="input" style="width:100px; line-height:17px; display:inline-block"
                              id="selectWarehouseInfo">
			            <%--加载仓库--%>
			          </select>
			        </li>
			        <li>库位：
			          <select class="input" style="width:100px; line-height:17px; display:inline-block"
                              id="selectStocklocationInfo">
			            <%--加载库位--%>
			          </select>
			        </li>
			        <li>商品：
			          <input type="text" placeholder="商品名称/编码/条码" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
			          <a href="javascript:;" class="button border-main icon-search"> 查询</a>
			        </li>
		     	</ul>
		    </div>
		    <ul class="search search_bottom">
		        <li>
		          <a href="javascript:;" class="button border-main alert_matching"> 云仓商品关联</a>
		        </li>
	     	</ul>
		    <%--加载内容--%>
            <%--加载页码--%>
	 	</div>
 	</div>
 	<div class="matching_alert_box">
 		<div class="new_matching_alert">
 			<div class="matching_title">
 				云仓商品关联
 				<div class="matching_delBtn">×</div>
 			</div>
 			<div class="matching_content">
	 			<div class="panel admin-panel accredit-height">
				    <!-- <div class="panel-head"><strong class="icon-reorder"> 云仓商品配对</strong></div> -->
				    <div class="padding border-bottom">
					    <ul class="search">
					    	<li>查询：</li>
					        <li>仓库：
					          <select class="input" style="width:100px; line-height:17px; display:inline-block">
					            <option value="0">仓库1</option>
					            <option value="1">仓库2</option>
					            <option value="2">仓库3</option>
					          </select>
					        </li>
					        <li>库位：
					          <select class="input" style="width:100px; line-height:17px; display:inline-block">
					            <option value="0">库位1</option>
					            <option value="1">库位2</option>
					            <option value="2">库位3</option>
					          </select>
					        </li>
					        <li>商品：
					          <input type="text" placeholder="商品名称/编码/规格" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
					          <a href="javascript:;" class="button border-main icon-search"> 查询</a>
					        </li>
				     	</ul>
				    </div>
				    <table class="table table-hover text-center list_table">
						<tr>
							<th width="60">编号</th>
							<th width="100">商品名称</th>       
							<th>商品条码</th>
							<th width="100">商品规格</th>
							<th>仓库</th>
							<th>库位</th>
							<th>总量</th>
							<th>订单占用量</th>
							<th>可用量</th>
							<th>操作</th>       
						</tr>      
						<tr>
							<td>1</td>
							<td>T56953655</td>
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>  
							<td>44984487489498</td>  
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>
								<a href="javascript:;" class="button border-main"> 关联至</a>
							</td>
						</tr>
						<tr>
							<td>1</td>
							<td>T56953655</td>
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>  
							<td>44984487489498</td>  
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>
								<a href="javascript:;" class="button border-main"> 关联至</a>
							</td>
						</tr>
						<tr>
							<td>1</td>
							<td>T56953655</td>
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>  
							<td>44984487489498</td>  
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>
								<a href="javascript:;" class="button border-main"> 关联至</a>
							</td>
						</tr>
						<tr>
							<td colspan="10"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
						</tr>
					</table>
			 	</div>
			 	<div class="panel admin-panel accredit-height" style="margin-top: 20px;">
			 		<div class="panel-head"><strong class="icon-reorder"> 请选择需关联的商品</strong></div>
				    <div class="padding border-bottom matching_box">
					    <ul class="search">
					    	<li>查询：</li>
					        <li>仓库：
					          <select class="input" style="width:100px; line-height:17px; display:inline-block">
					            <option value="0">仓库1</option>
					            <option value="1">仓库2</option>
					            <option value="2">仓库3</option>
					          </select>
					        </li>
					        <li>库位：
					          <select class="input" style="width:100px; line-height:17px; display:inline-block">
					            <option value="0">库位1</option>
					            <option value="1">库位2</option>
					            <option value="2">库位3</option>
					          </select>
					        </li>
					        <li>商品：
					          <input type="text" placeholder="商品名称/编码/规格" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
					          <a href="javascript:;" class="button border-main icon-search"> 查询</a>
					        </li>
				     	</ul>
				    </div>
				    <table class="table table-hover text-center list_table">
						<tr>
							<th width="80">
							<input type="checkbox">
							编号</th>
							<th width="100">商品名称</th>       
							<th>商品条码</th>
							<th width="100">商品规格</th>
							<th>仓库</th>
							<th>库位</th>
							<th>总量</th>
							<th>订单占用量</th>
							<th>可用量</th>
						</tr>      
						<tr>
							<td><input type="checkbox">1</td>
							<td>T56953655</td>
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>  
							<td>44984487489498</td>  
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
						</tr>
						<tr>
							<td><input type="checkbox">1</td>
							<td>T56953655</td>
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>  
							<td>44984487489498</td>  
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
						</tr>
						<tr>
							<td><input type="checkbox">1</td>
							<td>T56953655</td>
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>  
							<td>44984487489498</td>  
							<td>
								<p>
									二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款二代亚光眼影盘础款
								</p>
							</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
							<td>1000</td>
						</tr>
						<tr>
							<td colspan="9">
								<div class="pagelist"> 
									<a href="">上一页</a> 
									<span class="current">1</span>
									<a href="">2</a>
									<a href="">3</a>
									<a href="">下一页</a>
									<a href="">尾页</a> 
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="9" >
								<a href="javascript:;" class="button border-main matching_bc"> 提交</a>
							</td>
						</tr>
					</table>
			 	</div>
			 </div>
 		</div>
 	</div>
</body>
<script>
	var boxHeight=document.querySelector('.div_big_box').offsetHeight;
	var matching_alert_box=document.querySelector(".matching_alert_box");
	blackHeight(matching_alert_box,boxHeight);
</script>
<script src="/js/commodity_matching.js"></script>
</html>