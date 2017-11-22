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
    
    <script src="/util/cookie_util.js"></script>
	<script src="/util/jiml-utils.js"></script>
    <script src="/zb/inventory/inventoryState.js"></script>
    
</head>
<body style="position: relative; min-height: 600px;">
  	<div class="panel admin-panel accredit-height">
	    <!-- <div class="panel-head"><strong class="icon-reorder"> 商品信息设置</strong></div> -->
	    <div class="padding border-bottom">
		    <ul class="search">
		        <li>仓库：
		          <select class="input" style="width:150px; line-height:17px; display:inline-block" id="inventoryState_warehouse">
		            <!-- 加载仓库 -->
		          </select>
		        </li>
		        <li>关键字：
		          <input type="text" placeholder="商品名称/编码/条码" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
		          <a href="javascript:;" class="button border-main icon-search" id="inventoryState_query"> 查询</a>
		        </li>
		        <li>
		        	<a href="javascript:;" class="button border-main icon-search" id="inventoryState_cordon"> 低于警戒线库存商品</a>
		        </li>
		        <li>
		        	<p><input type="checkbox">显示0库存商品</p>
		        	<p><input type="checkbox">显示组合商品库存</p>
		        </li>
	     	</ul>
	    </div>
		<div class="shopping_box">
	    	<div class="left_shopping">
	    		<ul class="left_fl">
		    		<li>
		    			<a href="javascript:;">全部商品</a>
		    		</li>
		    		<li>
		    			<a href="javascript:;">未分类商品</a>
		    		</li>
		    		<li>
		    			<a href="javascript:;">护肤</a>
		    			<ol>
		    				<li>
		    					<a href="javascript:;">2级分类</a>
		    					<ol>
		    						<li>
		    							<a href="javascript:;">3级分类</a>
		    						</li>
		    						<li>
		    							<a href="javascript:;">3级分类</a>
		    						</li>
		    					</ol>
		    				</li>
		    				<li>
		    					<a href="javascript:;">2级分类</a>
		    					<ol>
		    						<li>
		    							<a href="javascript:;">3级分类</a>
		    							<ol>
				    						<li>
				    							<a href="javascript:;">4级分类</a>
				    						</li>
				    						<li>
				    							<a href="javascript:;">4级分类</a>
				    							<ol>
						    						<li>
						    							<a href="javascript:;">5级分类</a>
						    						</li>
						    						<li>
						    							<a href="javascript:;">5级分类</a>
						    							<ol>
								    						<li>
								    							<a href="javascript:;">6级分类分类分类分类分类</a>
								    						</li>
								    						<li>
								    							<a href="javascript:;">6级分类</a>
								    						</li>
								    					</ol>
						    						</li>
						    					</ol>
				    						</li>
				    					</ol>
		    						</li>
		    						<li>
		    							<a href="javascript:;">3级分类</a>
		    							<ol>
				    						<li>
				    							<a href="javascript:;">4级分类</a>
				    						</li>
				    						<li>
				    							<a href="javascript:;">4级分类</a>
				    						</li>
				    					</ol>
		    						</li>
		    					</ol>
		    				</li>
		    			</ol>
		    		</li>
		    		<li>
		    			<a href="javascript:;">养眼</a>
		    		</li>
		    		<li>
		    			<a href="javascript:;">美容</a>
		    		</li>
	    		</ul>
	    	</div>
		    <div class="right_list_shopping">
		    	<ul class="search search_bottom">
			        <li>
			          <a href="javascript:;" class="button border-main" id="alertStocks"> 批量设置警戒库存</a>
			        </li>
			        <li style="float:right;">
			        	<a href="javascript:;" class="button border-main"> 导出</a>
			        </li>
		     	</ul>
				<table class="table table-hover text-center list_table" id="inventoryState_table">
					   <!-- 加载内容 -->
				</table>
		    </div>
	    </div>
	    <ul class="all_price" id="all_price">
	    	<!-- 加载统计和页码 -->
	    </ul>
 	</div>
 	<div class="inventory_state_box">
 		<div class="new_inventory_state">
 			<div class="inventory_state_title">
 				<span>设置警戒库</span>
 				<div class="inventory_state_delbtn">×</div>
 			</div>
 			<div class="inventory_content">
 				<div class="inventory_top">
 					<span>警戒库存：</span>
 					<input type="text" value="" id="alertStock_num">
 				</div>
 				<div class="inventory_top">
 					<a href="javascript:;" class="button border-main a_btn_sync" id="alertStock_confirm"> 确定</a>
 					<a href="javascript:;" class="button border-main a_btn_sync invascript_qx"> 取消</a>
 				</div>
 			</div>
 		</div>
 	</div>
 	<script src="/js/inventory_state.js"></script>
</body>
</html>