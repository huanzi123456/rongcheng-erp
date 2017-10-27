<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>待打印订单</title>  
    <link rel="stylesheet" href="/css/pintuer.css">
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/jquery-ui.min.css">
    <link rel="stylesheet" href="/jxb/css/order.css">
    <link rel="stylesheet" href="/css/print.css">
    <link rel="stylesheet" href="/css/font/iconfont.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/pintuer.js"></script>
    <script src="/util/jiml-utils.js"></script>
    <script src="/util/carrier-utils.js"></script>
    <script src="/util/region/js/jquery.citys.js"></script>
    <script src="/jxb/js/order.js"></script>
    <script src="/jxb/js/order-print.js"></script>
    <script src="/jxb/js/order_AdvancedSearch.js"></script>
    <script type="text/javascript">
      var isOtherOrder = false;
    </script>
    <script src="../js/big_box.js"></script>
<!-- 赵滨 -->
<script src="/util/templateJson.js"></script>
<script src="/util/cookie_util.js"></script>
<script src="/zb/settings/basevalue.js"></script>
<script src="/zb/settings/toPrint.js"></script>
<script src="/zb/settings/jquery.PrintArea.js"></script>
<script src="/zb/settings/jQuery.print.js"></script>
<style type="text/css">
    .cursor_default :HOVER{
      cursor:default;
    }
    .cursor_pointer :HOVER {
      cursor:pointer;
    }
    .div_big_box{
      overflow: hidden;
    }
</style>

<!-- <script language="javascript" src="/zb/settings/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
       <embed id="LODOP_EM" type="application/x-erp-lodop" width=0 height=0></embed>
</object> -->

<!-- 赵滨 -->
</head>
<body style="position: relative;">
  <div class="div_big_box">
    <div class="panel admin-panel" style="margin-bottom: 20px;">
      <div class="panel-head inquire_btn_box">
        <strong class="strong_btn">订单查询</strong>
        <strong class="strong_btn">高级查询</strong>
      </div>
      <div class="inquire_content_box">
        <form id="selectiveForm">
          <!-- 订单状态:3已审核，待打印 -->
          <input id="orderStatus" name="orderStatus" value="3" class="jxb_none">
          <jsp:include page="public/search.jsp"></jsp:include>
        </form>
        <form id="advancedForm">
          <!-- 订单状态:3已审核，待打印 -->
          <input id="orderStatus" name="orderStatus" value="3" class="jxb_none">
          <jsp:include page="public/search-advanced.jsp"></jsp:include>
        </form>
      </div>
    </div>
    <div class="panel admin-panel">
      <div class="panel-head"><strong class="icon-reorder"> 待处理订单</strong></div>
      <form id="checkbox_form">
        <input id="style" name="style" value="" style="display: none;"/>
          <div class="table table-hover text-center">
            <ul class="table-ul cursor_default" id="reload">
              <li>系统单号</li>     
              <li>来源</li>  
              <li>买家信息</li>
              <li>收件信息</li> 
              <li>快递</li>  
              <li>审核关注点</li>
              <li>留言备注</li>
              <li>快递单号</li>
              <li>付款时间</li>
              <li>操作</li>
            </ul>
          <!-- reload -->
            <ul class="table-ul">
              <li id="allSelect"><input type="checkbox" id="checkall" />
                全选 </li>
              <li style="width: 90%;cursor:default;">
                <!--  <a class="button border-main pl_express_btn" href="javascript:;"><span class="icon-erp"></span> 批量打印面单</a>  -->
                <a class="button border-main pl_shipments_btn" href="javascript:;" id="billPrinting"><span class="icon-print"></span> 单据套打</a>
                <a class="button border-main" href="javascript:;"><span class="icon-random"></span> 拆单打印</a>
                <a class="button border-main" href="javascript:;"><span class="icon-thumbs-up"></span> 快速发货</a>
              </li>
            </ul>
            <ul class="table-ul" style="line-height: normal;">
              <li style="width: 100%;padding-top: 9px;">
                <div id="pagelist" style="height: 50px;">
                </div>
              </li>
            </ul>
          </div>
      </form>
    </div>
  </div>
  <!-- 隐藏的打印区域 -->
  <div id="print_area" style="display: none;">
    <%--<div id="print_area" style="display: block;">--%>
   <div id="print_area_div_top" style="position: absolute;height: 180px;width: 100%;"></div>
   <div id="print_area_div" style="position: absolute;">
       <style>
           table{border:none;height:18px}
           #print_table th{border: 1px solid #000;height:18px;}
           #print_table td{border: 1px solid #000;height:18px;}
           td{text-align:center;}
       </style>
       <table id="print_table" border=0 cellSpacing=0 cellPadding=0  width="200" height="200" bordercolor="#000000" style="border-collapse:collapse;">
           <thead></thead>
           <tbody></tbody>
           <tfoot></tfoot>
       </table>
       <div id="print_area_div_bottom" style="position: absolute;height: 180px;width: 100%;"></div>
   </div>
  </div>
  <!-- 批量打印面单弹出 -->
  <div class="express_box">
    <div class="express_content">
      <div class="del_express_btn">×</div>
      <h3>打印电子面单</h3>
      <div class="express_top">
        <div class="express_top_left">
          <h4>快递单:</h4>
          <div class="express_top_content">
            <div>
              <span>快递模板：</span>
              <select name="" id="PrintTemplateCarrier">
                <!-- 加载模版 -->
              </select>
              <a href="javascript:;" id="RedactCarrier">编辑</a>
            </div>
            <div>
              <span>打印机：</span>
              <select name="" id="PrinterCarrier">
                <!-- 加载打印机 -->
              </select>
              <a href="javascript:;" id="PreviewCarrier">预览</a>
            </div>
            <div>
              <span>更换取号账户：</span>
              <select name="" id="">
                <option value="1">默认</option>
              </select>
            </div>
            <div style="padding-left: 50px;">
              菜鸟电子面单数量:<i>南开鞍山西道(40)</i>
            </div>
          </div>
        </div>
        <div class="express_top_left">
          <h4>快递单:</h4>
          <div class="express_top_content">
            <div>
              <span>快递模板：</span>
              <select name="" id="PrintTemplate">
                <!-- 加载模版 -->
              </select>
              <a href="javascript:;" id="Redact">编辑</a>
            </div>
            <div>
              <span>单据类型：</span>
              <select name="" id="PrintType">
                <!-- 加载类型 -->
              </select>
              <a href="javascript:;" id="Preview">预览</a>
            </div>
            <div>
              <span>打印机</span>
              <select name="" id="Printer">
                <!-- 加载打印机 -->
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="express_middle">
        <table id="orderPrint_table" style="border:none;">
          <!-- 加载订单 -->
        </table>
      </div>
      <div class="express_tj">
        <div class="express_bottom_left">
          <button class="button border-main" id="PrintCarrier" href="javascript:;" style="margin-right: 50px;">
            <span class="icon-print"></span> 打印</button>
        </div>
        <div class="express_bottom_right">
          <button class="button border-main" id="Print" href="javascript:;" style="margin-right: 50px;">
            <span class="icon-print"></span> 打印</button>
        </div>
      </div>
    </div>
  </div>
  <!-- 单据套打提示框 -->
  <div class="set_to_play_box">
    <div class="set_to_play">
      <h3>提示</h3>
      <div class="set_to_play_content">
        <span>您选择的订单中：</span>
        <h4>有<i>0</i>单<i>已打印快递单</i></h4>
        <span>是否继续打印？</span>
      </div>
      <div class="set_to_play_btn">
        <button type="button" class="button border-main set_to_play_yes">是</button>
        <button type="button" class="button border-red set_to_play_no">否</button>
      </div>
    </div>
  </div>
</body>
<script src="/js/print.js"></script>
<script>
  var boxHeight=document.querySelector('.div_big_box').offsetHeight;
  var express_box=document.querySelector(".express_box");
  blackHeight(express_box,boxHeight)
  var set_to_play_box=document.querySelector(".set_to_play_box");
  blackHeight(set_to_play_box,boxHeight)
</script>
</html>