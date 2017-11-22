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
    <script src="/zb/inventory/storageLocation.js"></script>
    
</head>
<body style="position: relative; min-height: 600px;">
  	<div class="panel admin-panel accredit-height">
	    <!-- <div class="panel-head"><strong class="icon-reorder"> 商品信息设置</strong></div> -->
	    <div class="padding border-bottom">
		    <ul class="search">
		        <li>仓库：
		          <select class="input" style="width:150px; line-height:17px; display:inline-block" id="storageLocation_warehouse">
		            <!-- 加载仓库 -->
		          </select>
		        </li>
		        <li>关键字：
		          <input type="text" placeholder="商品名称/编码/条码/库位" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
		          <a href="javascript:;" class="button border-main icon-search" id="storageLocation_query"> 查询</a>
		        </li>
		        <li>
		        	<input type="checkbox">显示0库存商品
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
			          <a href="javascript:;" class="button border-main stock_adjustment_btn" id="alertStocks"> 批量调整库存</a>
			        </li>
			        <li>
			          <a href="javascript:;" class="button border-main"> 批量调整库存库位</a>
			        </li>
			        <li>
			          <a href="javascript:;" class="button border-main" id="stocks_empty"> 库存清零</a>
			        </li>
			        <li style="float:right;">
			        	<a href="javascript:;" class="button border-main"> 导出</a>
			        </li>
		     	</ul>
			    <table class="table table-hover text-center list_table" id="storageLocation_table">
					<!-- 加载内容 --> 
					
				</table>
		    </div>
	    </div>
	    <ul class="all_price" id="all_price">
	       <!-- 加载统计和页码 -->
	    
	    </ul>
 	</div>
 	
 	<div class="stock_adjustment_box">
        <div class="new-stock_adjustment">
            <div class="stock_adjustment_title">
                <span>库存调整</span>
                <div class="stock_adjustment_delbtn">×</div>
            </div>
            <div class="stock_adjustment_content">
                <div>
                    <span>共选择<i></i>条记录</span>
                </div>
                <div>
                    <span>库存总量统一调整成：</span>
                    <input type="text" id="alertStocks_num">
                </div>
                <div>
                    <a href="javascript:;" class="button border-main a_btn_sync" id="alertStocks_confirm"> 确认调整</a>
                </div>
            </div>
        </div>
    </div>
    <div class="adjustment_box">
        <div class="new_adjustment">
            <div class="adjustment_title">
                <span>库存调整</span>
                <div class="adjustment_delbtn">×</div>
            </div>
            <div class="adjustment_content">
                <div>
                    <span>商品编码：</span>
                    <i>T32654695965</i>
                </div>
                <div>
                    <span>库位：</span>
                    <i>LOC-01</i>
                </div>
                <div>
                    <span>订单占用量：</span>
                    <i>0</i>
                </div>
                <div>
                    <span>可用量：</span>
                    <i>6</i>
                </div>
                <div>
                    <span>库存总量：</span>
                    <input type="text" value="" id="alertStock_num">
                </div>
                <div>
                    <a href="javascript:;" class="button border-main a_btn_sync" id="alertStock_confirm"> 确认调整</a>
                </div>
            </div>
        </div>
    </div>
    <div class="storage_location_box">
        <div class="new_storage_location">
            <div class="storage_location_title">
                <span>批量调整库存库位</span>
                <div class="storage_location_delbtn">×</div>
            </div>
            <div class="storage_location_content">
                <div>
                    <span>调整到库位：</span>
                    <input type="text">
                    <a href="javascript:;" class="button border-main a_btn_sync choose_location_btn"> 添加库位</a>
                </div>
                <div>
                    <a href="javascript:;" class="button border-main a_btn_sync"> 确认调整到该库位</a>
                    <a href="javascript:;" class="button border-main a_btn_sync storage_location_qx"> 取消</a>
                </div>
            </div>
        </div>
    </div>
    <div class="choose_location_box">
        <div class="new_choose_location">
            <div class="choose_location_title">
                <span>选择库位</span>
                <div class="choose_location_delbtn">×</div>
            </div>
            <div class="choose_location_content">
                <div class="choose_kw">
                    <span>库位：</span>
                    <input type="text" placeholder="库位编码">
                </div>
                <div class="choose_table">
                    <table>
                        <tr>
                            <th width="10%">&nbsp;</th>
                            <th width="30%">库位编码</th>
                            <th width="30%">库位说明</th>
                            <th width="30%">存放商品</th>
                        </tr>
                        <tr>
                            <td>
                                <input type="radio" name="radio">
                            </td>
                            <td>LOC-01</td>
                            <td>默认库位，系统说明</td>
                            <td>存放商品</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="radio" name="radio">
                            </td>
                            <td>LOC-01</td>
                            <td>默认库位，系统说明</td>
                            <td>存放商品</td>
                        </tr>
                    </table>
                </div>
                <div class="content_page">
                    <div class="pagelist"> 
                        <a href="">上一页</a> 
                        <span class="current">1</span>
                        <a href="">2</a>
                        <a href="">3</a>
                        <a href="">下一页</a>
                        <a href="">尾页</a> 
                    </div>
                </div>
                <div class="content_tj">
                    <a href="javascript:;" class="button border-main a_btn_sync"> 保存</a>
                    <a href="javascript:;" class="button border-main a_btn_sync choose_location_qx"> 取消</a>
                </div>
            </div>
        </div>
    </div>
 	
</body>
    <!-- <script src="/js/storage_location.js"></script> -->
</html>