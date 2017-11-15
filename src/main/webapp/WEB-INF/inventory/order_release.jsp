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
    <link rel="stylesheet" href="/css/inventory_state.css">
    <link rel="stylesheet" href="/css/alert_box.css">
</head>
<body style="position: relative; min-height: 600px;">
    <div class="panel admin-panel accredit-height">
        <div class="padding border-bottom">
            <ul class="search">
                <li>
                    订单审核中的订单将以配置的规则自动选择对应仓库，当一个订单同时满足多个仓库规则时，优先匹配库存充足的仓库。
                </li>
            </ul>
            <ul class="search">
                <li>
                    <input type="checkbox"> 没有满足规则的订单默认分配到：
                    <select class="input" style="width:200px; line-height:17px; display:inline-block">
                        <option value="0">默认仓库</option>
                        <option value="1">仓库1</option>
                        <option value="2">仓库2</option>
                    </select>
                </li>
            </ul>
        </div>
        <ul class="search search_bottom">
            <li>
              <a href="javascript:;" class="button border-main"> 刷新仓库规则列表</a>
            </li>
        </ul>
        <table class="table table-hover text-center list_table">
	        <thead> 
	            <tr>
	                <th width="60">序号</th> 
	                <th width="100">仓库名称</th>
	                <th width="100">库位</th>
	                <th width="25%">覆盖区域</th>
	                <th width="25%">发货商品</th>
	                <th width="25%">发货店铺</th>      
	            </tr>
	        </thead>    
	        <tbody id="list_body">  
	            <tr>
	                <td>1</td>
	                <td>
	                    <p>
	                        默认仓库
	                    </p>
	                </td>
	                <td>
                        <p>
                                                                        默认库位
                        </p>
                    </td>
	                <td>
	                    <p style="width: 80%;float: left;">
	                        【全部地区】
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="dq_alert">设置</a>
	                </td>
	                <td>
	                    <p style="width: 80%;float: left;">
	                        （不设置）
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="sp_alert">设置</a>
	                </td>  
	                <td>
	                    <p style="width: 80%;float: left;">
	                        【全部店铺】
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="dp_alert">设置</a>
	                </td>
	            </tr>
	            <tr>
	                <td>2</td>
	                <td>
	                    <p>
	                        仓库1
	                    </p>
	                </td>  
	                <td>
	                    <p style="width: 80%;float: left;">
	                        地区1；地区2；地区3；地区4；地区5；地区6；地区1；地区2；地区3；地区4；地区5；地区6；
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="dq_alert">设置</a>
	                </td>
	                <td>
	                    <p style="width: 80%;float: left;">
	                        商品1；商品2；商品3；商品4；商品5；商品6；商品7；商品8；商品9；商品10；
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="sp_alert">设置</a>
	                </td>  
	                <td>
	                    <p style="width: 80%;float: left;">
	                        店铺1；店铺2；店铺3；店铺4；店铺5；店铺6；店铺7；店铺8；店铺9；店铺10；
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="dp_alert">设置</a>
	                </td>
	            </tr>
	            <tr>
	                <td>3</td>
	                <td>
	                    <p>
	                        仓库2
	                    </p>
	                </td>  
	                <td>
	                    <p style="width: 80%;float: left;">
	                        地区1；地区2；地区3；地区4；地区5；地区6；地区1；地区2；地区3；地区4；地区5；地区6；
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="dq_alert">设置</a>
	                </td>
	                <td>
	                    <p style="width: 80%;float: left;">
	                        商品1；商品2；商品3；商品4；商品5；商品6；商品7；商品8；商品9；商品10；
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="sp_alert">设置</a>
	                </td>  
	                <td>
	                    <p style="width: 80%;float: left;">
	                        店铺1；店铺2；店铺3；店铺4；店铺5；店铺6；店铺7；店铺8；店铺9；店铺10；
	                    </p>
	                    <a href="javascript:;" style="line-height: 30px;color: blue;" class="dp_alert">设置</a>
	                </td>
	            </tr>
	        </tbody>
	        <tfoot id="list_foot">
	        </tfoot>
        </table>
    </div>
    <div class="regional_rules_box">
        <div class="new_regional_rules">
            <div class="regional_rules_title">
                <span>仓库规则--覆盖区域规则设置</span>
                <div class="regional_rules_delbtn">×</div>
            </div>
            <div class="regional_rules_content">
                <ol id="ol">
                </ol>
                <div style="text-align:center;">
                    <a href="javascript:;" class="button border-main regional_rules_bc"> 保存</a>
                    <a href="javascript:;" class="button border-main regional_rules_qx"> 取消</a>
                </div>
            </div>
        </div>
    </div>
    <div class="select_commodity_box">
        <div class="new_select_commodity">
            <div class="select_commodity_title">
                <span>仓库规则--选择商品</span>
                <div class="select_commodity_delbtn">×</div>
            </div>
            <div class="select_commodity_content">
                <div class="panel admin-panel accredit-height">
                    <div class="padding border-bottom">
                        <ul class="search">
                            <li>
                                <input type="radio" name='commodity'>
                                包含商品
                            </li>
                            <li>
                                <input type="radio" name='commodity'>
                                排除商品
                            </li>
                            <li>
                                <input type="radio" name='commodity'>
                                全部商品
                            </li>
                        </ul>
                        <ul class="search">
                            <li>是否选中：
                              <select class="input" style="width:100px; line-height:17px; display:inline-block">
                                <option value="0">全部</option>
                                <option value="1">选中</option>
                                <option value="2">没选中</option>
                              </select>
                            </li>
                            <li>关键字：
                              <input type="text" placeholder="商品编码/名称/规格" name="keywords" class="input" style="width:200px; line-height:17px;display:inline-block" />
                              <a href="javascript:;" class="button border-main icon-search"> 查询</a>
                            </li>
                        </ul>
                    </div>
                    <div class="shopping_box">
                        <ul class="left_shopping">
                            <li>
                                <a href="javascript:;">全部商品</a>
                            </li>
                            <li>
                                <a href="javascript:;">未分类商品</a>
                            </li>
                            <li>
                                <a href="javascript:;">护肤</a>
                            </li>
                            <li>
                                <a href="javascript:;">养眼</a>
                            </li>
                            <li>
                                <a href="javascript:;">美容</a>
                            </li>
                        </ul>
                        <div class="right_list_shopping">
                            <table class="table table-hover text-center list_table">
	                            <thead>
	                                <tr>
	                                    <th width="80"><input type="checkbox"/>选择</th>
	                                    <th>序号</th>       
	                                    <th>商品编码</th>
	                                    <th>商品名称</th>
	                                    <th>商品规格</th>
	                                </tr>      
                                </thead>
                                <tbody id="itemTbody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="all_price" style="height: 100px;">
                        <div style="height: 50px;line-height: normal;border-top: 1px solid #ddd;" class="page">
                            <!-- <div class="pagelist"> 
                                <a href="">上一页</a> 
                                <span class="current">1</span>
                                <a href="">2</a>
                                <a href="">3</a>
                                <a href="">下一页</a>
                                <a href="">尾页</a> 
                            </div> -->
                        </div>
                        <div style="text-align: center;border-top: 1px solid #ddd;padding-top:8px;">
                            <a href="javascript:;" class="button border-main select_commodity_bc"> 保存</a>
                            <a href="javascript:;" class="button border-main select_commodity_qx"> 取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="goods_matching_box">
        <div class="new_goods_matching">
            <div class="goods_matching_title">
                <span>仓库规则--发货商品匹配规则设置</span>
                <div class="goods_matching_delbtn">×</div>
            </div>
            <div class="goods_matching_content">
                <div class="inventory_top">
                    <span>发货店铺：</span>
                    <div class="select_goods_box">
                        <div class="select_goods_btn">
                            选择店铺
                        </div>
                        <div class="select_goods_content">
                            <ul>
                                <li><input type="checkbox">店铺名称</li>
                                <li><input type="checkbox">店铺1店铺1店铺1店铺1店铺1店铺1店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                                <li><input type="checkbox">店铺1</li>
                            </ul>
                            <div style="text-align: center;padding-top:5px;">
                                <a href="javascript:;" class="button border-main select_goods_bc">保存</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="inventory_top" style="height: 240px;overflow: auto;">
                    <span style="vertical-align: top;">已选择：</span>
                    <p style="width:370px; line-height:30px; display:inline-block">
                        <span>
                            <i>1.</i>
                             ( 微店 )时代广场timesquare; 
                        </span>
                        <span>
                            <i>2.</i>
                             ( 微店 )时代广场timesquare; 
                        </span>
                        <span>
                            <i>3.</i>
                             ( 微店 )时代广场timesquare; 
                        </span>
                        <span>
                            <i>4.</i>
                             ( 微店 )时代广场timesquare; 
                        </span>
                    </p>
                </div>
                <div class="inventory_top">
                    <a href="javascript:;" class="button border-main goods_matching_bc"> 保存</a>
                    <a href="javascript:;" class="button border-main goods_matching_qx"> 取消</a>
                </div>
            </div>
        </div>
    </div>
    <script src="/js/jquery.js"></script>
    <script src="/js/pintuer.js"></script>
    <script src="/util/jiml-utils.js"></script>
    <script src="/wzy/inventroyOrderRelease.js"></script>
</body>
</html>