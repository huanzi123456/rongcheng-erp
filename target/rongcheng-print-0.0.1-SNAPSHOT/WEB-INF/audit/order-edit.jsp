<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>  
    <link rel="stylesheet" href="/css/pintuer.css">
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/jquery-ui.min.css">
    <link rel="stylesheet" href="/jxb/css/order.css">
    <link rel="stylesheet" href="/css/print.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/pintuer.js"></script>
    <script src="/util/jiml-utils.js"></script>
    <script src="/util/carrier-utils.js"></script>
    <script src="/util/region/js/jquery.citys.js"></script>
    <script src="/jxb/js/order.js"></script>
    <script src="/jxb/js/order-edit.js"></script>
    <script src="/jxb/js/order_AdvancedSearch.js"></script>
    <link rel="stylesheet" href="/css/font/iconfont.css">
    <script src="../js/big_box.js"></script>
</head>
<style>
  .div_big_box{
    overflow: hidden;
  }
</style>
<body style="position: relative;">
  <div class="div_big_box">
    <div class="panel admin-panel" style="margin-bottom: 20px;">
      <div class="panel-head inquire_btn_box">
        <strong class="strong_btn">快速查询</strong>
        <strong class="strong_btn">高级查询</strong>
      </div>
      <div class="inquire_content_box">
        <form id="selectiveForm">
          <!-- 订单状态 -->
          <input id="orderStatus" name="orderStatus" value="2" class="jxb_none">
          <jsp:include page="public/search.jsp"></jsp:include>
        </form>
        <form id="advancedForm">
          <jsp:include page="public/search-advanced.jsp"></jsp:include>
        </form>
      </div>
    </div>
    <div class="panel admin-panel">
      <div class="panel-head"><strong class="icon-reorder"> 待审核订单</strong></div>
      <div class="padding border-bottom">
        <ul class="search">
          <li>
            <a class="button border-main jxb_button btn_shezhi" href="javascript:;"><span class="icon-edit"></span>设置</a>
          </li>
        </ul>
      </div>
      <form id="checkbox_form">
          <div class="table table-hover text-center">
            <ul class="table-ul" id="reload">
              <li>系统单号</li>     
              <li>来源</li>  
              <li>买家信息</li>
              <li>收件信息</li> 
              <li>快递</li>  
              <li>审核关注点</li>
              <li>留言备注</li>
              <li>总价</li>
              <li>总数量</li>
              <li>操作</li>
            </ul>
            <!-- reload -->
            <ul class="table-ul">
              <li><input type="checkbox" id="checkall" />
                全选 </li>      
              <li style="width: 90%">
                <a class="button border-main" href="javascript:;"><span class="icon-edit"></span> 自动合并</a> 
                <a class="button border-main" href="javascript:;"><span class="icon-edit"></span> 批量保存</a>
                <a class="button border-main" href="javascript:;" onclick="updateOrder(3)"><span class="icon-edit"></span> 批量审批</a>
                <a class="button border-main" href="javascript:;" onclick="updateOrder(2)"><span class="icon-edit"></span> 订单关闭</a>
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
  <div class="shade_shezhi">
    <div class="sheade_shezhi_zz"></div>
    <div class="sheade_shezhi_content">
      <div>
         <input type="checkbox" name="id[]" value="" />订单自动审核通过
         <a class="button border-main shade_tij" href="javascript:;" style="margin-left:50px;"><span class="icon-edit"></span> 提交</a> 
      </div>
    </div>
  </div>
</body>
<script src="/js/print.js"></script>
<script>
  var boxHeight=document.querySelector('.div_big_box').offsetHeight;
  var shade_shezhi=document.querySelector(".shade_shezhi");
  blackHeight(shade_shezhi,boxHeight)
</script>
</html>