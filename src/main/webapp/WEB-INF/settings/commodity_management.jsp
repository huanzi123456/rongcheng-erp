<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" href="/css/commodity_management.css">
    <script src="/js/big_box.js"></script>
    <style>
      .div_big_box:after{
        content: "";
        display: inline-block;
        height: 0;
        clear: both;
      }
    </style>
</head>
<body style="position: relative;">
    <div class="div_big_box">
        <div class="panel admin-panel accredit-height">
            <!-- <div class="panel-head"><strong class="icon-reorder"> 库存同步</strong></div> -->
            <div class="padding border-bottom">
                <ul class="search">
                    <li>关键字：
                      <input type="text" placeholder="商品编号/商品名称/商品条码" name="keywords" class="input" style="width:400px; line-height:17px;display:inline-block"/>
                      <a href="javascript:;" class="button border-main icon-search"> 查询</a>
                      <input type="checkbox" name="id[]" style="margin-left: 50px;" />
                      <span>
                                                                             只显示<b>"没有线上商品关联关系"</b>的商品
                      </span>
                    </li>
                </ul>
            </div>
            <div class="shopping_box">
                <ul class="left_shopping">
                    <div>
                        <a href="javascript:;" class="btn_button class_add">新增</a><a href="javascript:;" class="btn_button class_amend">修改</a><a href="javascript:;" class="btn_button red_color">删除</a>
                    </div>
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
                    <ul class="search search_bottom">
                        <li>
                            <a href="javascript:;" class="button border-main management_xj">新建</a>
                        </li>
                        <li>
                            <a href="javascript:;" class="button border-main management_fzxj">复制并新建</a>
                        </li>
                        <li>
                            <a href="javascript:;" class="button border-main classify_fl">更改分类</a>
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
                        <li style="float: right;">
                            <a href="javascript:;" class="button border-main border-remove">批量删除</a>
                        </li>
                    </ul>
                    <table class="table table-hover text-center list_table">
                        <thead>
                            <tr>
                                <th width="80"><input type="checkbox" name="options" /></th>
                                <th>商品编码</th>       
                                <th width="15%">商品名称</th>
                                <th>款号</th>
                                <th>商品条码</th>
                                <th width="15%">商品规格</th>
                                <th>商品价格</th>
                                <th>商品品牌</th>
                                <th>操作</th>       
                            </tr>
                        </thead>
                        <tbody id="tbody">
                        </tbody>
                        <tfoot id="tfoot">
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="commodity_management_box">
        <div class="new_management">
            <div class="management_title">
                <span id="title">新增商品</span>
                <div class="management_delbtn">×</div>
            </div>
            <ul class="management_content">
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品编码:</span>
                            <b>*</b>
                            <input type="text" id="erpItemNum">
                        </li>
                        <li class="bfz33">
                            <span>商品类目:</span>
                            <input type="text" id="category">
                        </li>
                        <li class="bfz33">
                            <span>商品品牌:</span>
                            <input type="text" id="brand">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz66">
                            <span>商品名称:</span>
                            <b>*</b>
                            <input type="text" id="name">
                        </li>
                        <li class="bfz33">
                            <span>商品简称:</span>
                            <input type="text" id=shortName>
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品规格:</span>
                            <input type="text" id="spec">
                        </li>
                        <li class="bfz33">
                            <span>颜色:</span>
                            <input type="text" id="color">
                        </li>
                        <li class="bfz33">
                            <span>尺码:</span>
                            <input type="text" id="size">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品价格:</span>
                            <input type="text" id="normalPrice">
                        </li>
                        <li class="bfz33">
                            <span>成本价:</span>
                            <input type="text" id="costPrice">
                        </li>
                        <li class="bfz33">
                            <span>商品条码:</span>
                            <input type="text" id="barCode">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品包装:</span>
                            <input type="text" id="packageCondition">
                        </li>
                        <li class="bfz33">
                            <span>长*宽*高:</span>
                            <input type="text" class="ckg" id="length">
                            <input type="text" class="ckg" id="width">
                            <input type="text" class="ckg" id="height">
                        </li>
                        <li class="bfz33">
                            <span>商品重量:</span>
                            <input type="text" id="weight">kg
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>商品单位:</span>
                            <input type="text" id="unit">
                        </li>
                        <li class="bfz33">
                            <span>商品批次:</span>
                            <input type="text" id="batchCode">
                        </li>
                        <li class="bfz33">
                            <span>保质期:</span>
                            <input type="text" id="expireDate">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li class="bfz33">
                            <span>季节:</span>
                            <input type="text">
                        </li>
                        <li class="bfz33">
                            <span>款号:</span>
                            <input type="text" id="styleCode">
                        </li>
                        <li class="bfz33">
                            <span>系统分类:</span>
                            <select name="" id="userClassification">
                                <option value="1">未分类商品</option>
                                <option value="2">护肤</option>
                                <option value="3">互联</option>
                            </select>
                        </li>
                    </ol>
                </li>
                <li style="margin: 10px 0">
                    <ol>
                        <li class="bfz33">
                            <span>自定义1:</span>
                            <input type="text" id="reserved1">
                        </li>
                        <li class="bfz33">
                            <span>自定义2:</span>
                            <input type="text" id="reserved2">
                        </li>
                        <li class="bfz33">
                            <span>自定义3:</span>
                            <input type="text" id="reserved3">
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li>
                            <span>备注:</span>
                            <textarea name="note" id="note" cols="30" rows="10"></textarea>
                        </li>
                    </ol>
                </li>
                <li>
                    <ol>
                        <li>
                            <span>其他:</span>
                        </li>
                        <li>
                            <div class="fl_div">
                                <input type="checkbox" name="presell" value="0">
                                <span>预售</span>
                            </div>
                            <div class="fl_div">
                                <input type="checkbox" name="commissionSell" value="0">
                                <span>代售</span>
                            </div>
                            <div class="fl_div">
                                <input type="checkbox" name="gift" value="0">
                                <span>赠品</span>
                            </div>
                        </li>
                    </ol>
                </li>
                <li class="management_tj">
                    <a href="javascript:;" class="button border-main management_bc"> 保存</a>
                    <a href="javascript:;" class="button border-main management_qx"> 取消</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="amend_classify_box">
        <div class="new_classify">
            <div class="classify_title">
                <span>更改分类</span>
                <div class="classify_delbtn">×</div>
            </div>
            <div class="classify_content">
                <ul class="classify_box">
                    <li>分类分类</li>
                    <li>分类分类</li>
                    <li>分类分类</li>
                    <li>分类分类</li>
                    <li>分类分类</li>
                    <li>分类分类</li>
                    <li>分类分类</li>
                </ul>
                <div class="classify_tj">
                    <a href="javascript:;" class="button border-main classify_bc"> 保存</a>
                    <a href="javascript:;" class="button border-main classify_qx"> 取消</a>
                </div>
            </div>
        </div>
    </div>
    <div class="class_add_box">
        <div class="new_class_add">
            <div class="class_add_title">
                <span>系统分类</span>
                <div class="class_add_delbtn">×</div>
            </div>
            <div class="class_add_content">
                <div class="class_add_text">
                    <span>分类名称:</span>
                    <input type="text">
                </div>
                <div class="class_add__tj">
                    <a href="javascript:;" class="button border-main class_add__bc"> 保存</a>
                    <a href="javascript:;" class="button border-main class_add__qx"> 取消</a>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="/util/jiml-utils.js"></script>
<script src="/wzy/itemInfo.js"></script>
<script>
    var boxHeight=document.querySelector('.div_big_box').offsetHeight;
    var commodity_management_box=document.querySelector(".commodity_management_box");
    blackHeight(commodity_management_box,boxHeight);
    var amend_classify_box=document.querySelector(".amend_classify_box");
    blackHeight(amend_classify_box,boxHeight);
    var class_add_box=document.querySelector(".class_add_box");
    blackHeight(class_add_box,boxHeight);
</script>
</html>