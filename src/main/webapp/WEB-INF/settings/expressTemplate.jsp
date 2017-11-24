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
<script src="/zb/settings/expressTemplate.js"></script>
<script src="../js/big_box.js"></script>
</head>
<style>
  .div_big_box:after{
      content: "";
      display: block;
      height: 0;
      clear: both;
    }
</style>
<body>
  <div class="div_big_box">
    <form method="post" action="" id="listform">
      <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 快递面单</strong></div>
        <div class="padding border-bottom">
          <ul class="search" style="padding-left:10px;">
            <li> <a class="button border-main icon-plus-square-o shade_btn" id="expressTemplate_addExpressTemplate"> 添加模板</a> </li>
            <li> <a class="button border-main icon-plus-square-o " id="expressTemplate_copyAdd" style="cursor: pointer;"> 复制并新建</a> </li>
            <!-- add_expressTemplate.html -->
          </ul>
        </div>
        <table class="table text-center" id="expressTemplate_table">
          <!-- 加载内容 -->
        </table>
      </div>
    </form>
  </div>
  <div class="shade">
    <div class="shade_box"></div>
    <div class="celect_menu">
      <ul class="logistics_company" id="expressTemplate_carrierInfo">
        <!-- 加载快递内容 -->
      </ul>
      <ul class="picture_menu" id="expressTemplate_printTemplateImage">
        <!-- 加载模版图片 -->
      </ul>
      <ul class="search search_btn" id="expressTemplate_next">
        <li> <a class="button border-main icon-plus-square-o shade_btn" > 下一步</a> </li>
      </ul>
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
  var shade=document.querySelector(".shade");
  blackHeight(shade,boxHeight)
</script>
<!-- 点击遮罩 -->


</body>
</html>