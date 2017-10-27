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
<link rel="stylesheet" href="/css/template.css">
<script src="/js/jquery.js"></script>
<script src="/js/pintuer.js"></script>
<script src="/util/jiml-utils.js"></script>
<script src="/util/cookie_util.js"></script>
<script src="/zb/settings/basevalue.js"></script>
<script src="/zb/settings/invoiceTemplate.js"></script>
<script src="../js/big_box.js"></script>
</head>
<style>
  .div_big_box{
    overflow: hidden;
  }
</style>
<body>
  <div class="div_big_box">
    <form method="post" action="" id="listform">
      <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 自定义单据</strong></div>
        <div class="padding border-bottom">
          <ul class="search" style="padding-left:10px;">
            <li> <a class="button border-main icon-plus-square-o add_template" id="invoiceTemplate_addInvoiceTemplate"> 添加模板</a> </li>
            <li> <a class="button border-main icon-plus-square-o " id="invoiceTemplate_copyAdd" style="cursor: pointer;"> 复制并新建</a> </li>
          </ul>
        </div>
        <table class="table text-center" id="invoiceTemplate_table">
            <!-- 加载内容 -->
        </table>
      </div>
    </form>
  </div>
<!-- 点击遮罩 -->
  <div class="template_shade">
    <div class="template_btn"></div>
    <div class="template_content_box">
      <div class="template_content_title">选择模板类型</div>
      <div class="template_content">
        <ul class="template_content_list1" id="invoiceTemplate_templateType">
          <li style="width:10%;text-align: right;" class="company_head">模版类型：</li>
          <li class="radio_checked"><input type="radio" name="type" value="发货单">发货单</li>
          <li class="radio_checked"><input type="radio" name="type" value="出库单">出库单</li>
          <li class="radio_checked"><input type="radio" name="type" value="入库单">入库单</li>
        </ul>
        <ul class="template_content_list2" id="invoiceTemplate_printTemplateImage">
          
        </ul>
        <ul class="search search_btn" style="padding-top:20px;" id="invoiceTemplate_next">
          <li> <a class="button border-main icon-plus-square-o shade_btn"> 下一步</a> </li>
        </ul>
      </div>
    </div>
  </div>
<script type="text/javascript">
  //全选
  $("#checkall").click(function(){ 
    $("input[name='id[]']").each(function(){
      if (this.checked) {
        this.checked = false;
      }
      else {
        this.checked = true;
      }
    });
  })
  var boxHeight=document.querySelector('.div_big_box').offsetHeight;
  var template_shade=document.querySelector(".template_shade");
  blackHeight(template_shade,boxHeight)
</script>
</body>
</html>