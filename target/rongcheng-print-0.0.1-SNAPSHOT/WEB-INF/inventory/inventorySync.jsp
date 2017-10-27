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
	<script src="/zb/inventory/inventorySync.js"></script>
</head>
<body style="position: relative;min-height: 600px;">
  	<div class="panel admin-panel accredit-height">
	    <!-- <div class="panel-head"><strong class="icon-reorder"> 库存同步</strong></div> -->
	    <div class="padding border-bottom">
		    <ul class="search">
		        <li>自动同步：
		          <select class="input" style="width:150px; line-height:17px; display:inline-block" id="inventorySync_autoSynchron">
		            <option value="">全部</option>
		            <option value="1">开</option>
		            <option value="0">关</option>
		          </select>
		        </li>
		        <li>关键字：
		          <input type="text" placeholder="商品名称/编码/条码" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
		          <a href="javascript:;" class="button border-main icon-search" id="inventorySync_query"> 查询</a>
		        </li>
	     	</ul>
	    </div>
	    <ul class="search search_bottom">
	        <li>
	          <a href="javascript:;" class="button border-main alert_btn"  id="inventorySync_batchSync">
				  批量更改库存同步设置
			  </a>
	        </li>
	        <li>
	        	<a href="javascript:;" class="button border-main"> 点击上传库存</a>
	        </li>
     	</ul>
	    <table class="table table-hover text-center list_table" id="inventorySync_table">
			<!-- 加载内容 -->
			<!-- 加载页码 -->
		</table>
 	</div>
 	<div class="inventory_sync_box">
 		<div class="new_inventory_sync">
 			<div class="inventory_sync_title">
 				<span>库存同步配置</span>
 				<div class="inventory_sync_delbtn">×</div>
 			</div>
 			<div class="inventory_sync_content">
 				<h5>自动同步</h5>
 				<div class="inventory_row">
 					<input type="checkbox" name="inventorySync_startSync" id="inventorySync_startSync">开启自动同步
					<span>(勾选开启后，此商品库存数量发生变化时，库存会自动同步到淘宝、京东等线上平台上)</span>
 				</div>
 				<h5>同步配置</h5>
 				<div class="inventory_table">
 					<table id="inventorySync_syncTable">
 						<%--加载同步配置--%>
 					</table>
 				</div>
 				<div class="inventory_btn">
 					<a href="javascript:;" class="button border-main a_btn_sync" id="inventorySync_enterSync"> 确定</a>
 					<a href="javascript:;" class="button border-main a_btn_sync inventory_qx"> 取消</a>
 				</div>
 			</div>
 		</div>
 	</div>
 	<script src="/js/inventory_sync.js"></script>
</body>
</html>