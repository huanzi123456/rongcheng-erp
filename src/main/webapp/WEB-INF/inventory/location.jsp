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
    <script src="/zb/inventory/location.js"></script>
    
</head>
<body style="position: relative;min-height: 600px;min-width: 1020px;">
  	<div class="panel admin-panel accredit-height">
	    <!-- <div class="panel-head"><strong class="icon-reorder"> 库存同步</strong></div> -->
	    <div class="padding border-bottom">
		    <ul class="search">
		        <li>关键字：
		          <input type="text" placeholder="库位编码/名称/条码" name="keywords" class="input" style="width:400px; line-height:17px;display:inline-block" />
		          <a href="javascript:;" class="button border-main icon-search" id="location_query"> 查询</a>
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
			          	<a href="javascript:;" class="button border-main add_btn">新建</a>
			        </li>
			        <li>
			        	<a href="javascript:;" class="button border-main">下载模板</a>
			        </li>
			        <li>
			        	<a href="javascript:;" class="button border-main">导入</a>
			        </li>
			        <li style="float: right;">
			        	<a href="javascript:;" class="button border-main">导出</a>
			        </li>
		     	</ul>
		     	<table class="table table-hover text-center list_table" id="location_table">
					<!-- 加载内容 -->
				</table>
	    	</div>
     	</div>
 	</div>
 	<div class="new_location_box">
 		<div class="new_location">
 			<div class="location_title">
 				<span></span>
 				<div class="location_delbtn">×</div>
 			</div>
 			<div class="location_content">
 			    <input type="hidden" id="hidden_locationId">
 				<div class="kw_bm">
 					<span>库位编码：</span>
 					<input type="text" id="text_userStocklocationCode">
 				</div>
 				<div class="kw_bz">
 					<span>库位名称：</span>
 					<textarea name="" id="text_name" cols="30" rows="10"></textarea>
 				</div>
 				<div class="kw_sp">
 					<span>存放物品：</span>
 					<div class="kw_table">
 						<table id="item_table">
	 						<!-- 加载商品 -->
	 					</table>
 					</div>
 				</div>
 				<div class="kw_btn">
 					<a href="javascript:;" class="button border-main add_shopping">添加商品</a>
 					<a href="javascript:;" class="button border-main save_kw" id="location_save">保存</a>
 					<a href="javascript:;" class="button border-main kw_qx">取消</a>
 				</div>
 			</div>
 		</div>
 	</div>
 	<div class="shopping_add_box">
 		<div class="new_shopping">
 			<div class="shopping_title">
 				<span>添加商品</span>
 				<div class="shopping_delbtn">×</div>
 			</div>
 			<div class="shopping_content">
 				<div class="content_head">
 					<span>关键字：</span>
 					<input type="text" placeholder="商品编码/名称" name="item_keywords">
 				</div>
 				<div class="content_main">
 					<table id="listItem_table">
 						<!-- 加载商品 -->
 					</table>
 				</div>
 				<div class="content_page">
 				   <!-- 加载页码 -->
 				</div>
 				<div class="content_tj">
 					<a href="javascript:;" class="button border-main a_btn_sync button_tj"> 提交</a>
 				</div>
 			</div>
 		</div>
 	</div>
</body>
<!--  <script src="/js/location.js"></script> -->
</html>