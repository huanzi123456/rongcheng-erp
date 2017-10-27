<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="/css/pintuer.css">
    <link rel="stylesheet" href="/css/admin.css">
    <script src="/js/jquery.js"></script>

    <!-- Lodop控件 -->
    <script language="javascript" src="/Lodop/LodopFuncs.js"></script>
    <object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" style="display:none;" >
      <embed id="LODOP_EM" type="application/x-print-lodop"></embed>
    </object>
    <script language="javascript" src="/Lodop/LodopBase.js"></script>
    
</head>

  <!-- 提示 -->
  <div id="CLodop_Setup" style="display:none;">
    <font color='#FF00FF'>检测打印控件没有安装启动！为了您的正常使用，点击这里：
      <a href='/Lodop/CLodop_Setup_for_Win32NT.exe' target='_self'>执行安装</a>，安装后请刷新页面或重新进入。
    </font>
  </div>

<body style="background-color:#f2f9fd;min-width: 1200px;">
  <div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
      <h1>
        <img src="/images/logo.png" class="" height="50" alt="" />
	融成云ERP系统
      </h1>
    </div>
    <div class="head-l" style="float: right;margin-right: 50px;">
      <a class="button button-little bg-red" href="/admin.do">
        <span class="icon-power-off"></span> 
        退出登录
      </a> 
    </div>
    <div class="head-l" style="float: right;line-height: 35px;color: #fff;font-size:16px;margin-right: 20px;">
      你好：<span>${sessionScope.user.nickname }</span>
    </div>
  </div>
  <div class="mian_content">
    <div class="leftnav">
      <div class="leftnav-title">
        <strong>
          <span class="icon-list"></span>
          菜单列表
        </strong>
      </div>
      <iframe scrolling="auto" rameborder="0" src="/topnav/settings.html" name="left" width="100%" height="90%"></iframe>
    </div>
    <div class="right_content">
      <ul class="bread">
        <li>
          <a href="/topnav/settings.html"  class=" icon-cog" target="left"> 信息设置</a>
        </li>
        <li>
          <a href="/topnav/audit.html" class="icon-list-alt" target="left">订单审核</a>
        </li>
        <li>
          <a href="/topnav/print.html" class="icon-inbox" target="left"> 订单打印</a>
        </li>
        <li>
          <a href="/topnav/shipment.html" class="icon-shopping-cart" target="left"> 验货发货</a>
        </li>
        <li>
          <a href="/topnav/inventory.html"  class=" icon-cog" target="left"> 仓库管理</a>
        </li>
        <li>
          <a href="/topnav/dataStatisticsList.html" class="icon-align-left" target="left"> 数据统计</a>
        </li>
        <li>
          <a href="/topnav/account.jsp" class="icon-file" target="left"> 账号设置</a>
        </li>
      </ul>
      <div class="admin">
        <iframe id="index_admin_iframe" scrolling="auto" rameborder="0" src="/topnav/show.html" name="right" width="100%" height="99%"></iframe>
      </div>
    </div>
  </div>
</body>
<script src="/js/topnav.js"></script>
</html>