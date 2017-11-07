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
		          <a href="javascript:;" class="button border-main alert_matching" id="alertMatching"> 云仓商品关联</a>
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
					          <select class="input" style="width:100px; line-height:17px; display:inline-block"
									  id="selectWarehouseInfo_top">
					            <%--加载仓库--%>
					          </select>
					        </li>
					        <li>库位：
					          <select class="input" style="width:100px; line-height:17px; display:inline-block"
                                      id="selectStocklocationInfo_top">
					            <%--加载库位--%>
					          </select>
					        </li>
					        <li>商品：
					          <input type="text" placeholder="商品名称/编码/条码" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
					          <a href="javascript:;" class="button border-main icon-search"> 查询</a>
					        </li>
				     	</ul>
				    </div>
				    <table class="table table-hover text-center list_table" id="tableTopOfAlert">
						<%--加载顶部内容--%>
                        <%--加载页码--%>
					</table>
			 	</div>
			 	<div class="panel admin-panel accredit-height" style="margin-top: 20px;">
			 		<div class="panel-head"><strong class="icon-reorder"> 请选择需关联的商品</strong></div>
				    <div class="padding border-bottom matching_box">
					    <ul class="search">
					    	<li>查询：</li>
					        <li>仓库：
					          <select class="input" style="width:100px; line-height:17px; display:inline-block"
                                      id="selectWarehouseInfo_bottom">
								  <%--加载仓库--%>
					          </select>
					        </li>
					        <li>库位：
					          <select class="input" style="width:100px; line-height:17px; display:inline-block"
                                      id="selectStocklocationInfo_bottom">
								  <%--加载库位--%>
					          </select>
					        </li>
					        <li>商品：
					          <input type="text" placeholder="商品名称/编码/条码" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
					          <a href="javascript:;" class="button border-main icon-search"> 查询</a>
					        </li>
				     	</ul>
				    </div>
				    <table class="table table-hover text-center list_table" id="tableBottomOfAlert">
                        <%--加载底部内容--%>
                        <%--加载页码--%>
                        <%--加载提交--%>
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