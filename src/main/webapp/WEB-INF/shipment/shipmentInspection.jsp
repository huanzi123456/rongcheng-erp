<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script src="/js/jquery.js"></script>
    <script src="/js/pintuer.js"></script> 
    <link rel="stylesheet" href="/css/shipment_inspection.css">
    <script src="/util/jiml-utils.js"></script>
    <script src="/util/region/js/jquery.citys.js"></script>
    <script src="/zb/shipment/shipmentInspection.js"></script>
    
</head>
<body>
  <div class="panel admin-panel">
    <div class="body-content" style="padding-bottom:0;">
      <div class="form-x">  
        <div class="form-group" style="width: 50%;float: left;">
          <div class="label" style="width: 40%">
            <label>扫描运单号或系统单号：</label>
          </div>
          <div class="field" style="width: 60%">
            <input type="text" placeholder='请输入单号获取订单' class="input" style="width: 80%" id="oddNumbers">
          </div>
        </div>
        <div class="form-group" style="width: 50%;float: left;">
          <div class="field" style="line-height: 42px;">
          已校验总量：
            <span>0</span>
          </div>
        </div> 
        <div class="form-group" style="width: 50%;float: left;">
          <div class="label" style="width: 40%">
            <label>商品条码：</label>
          </div>
          <div class="field" style="width: 60%">
            <input type="text" class="input" style="width: 80%">
          </div>
        </div>  
        <div class="form-group" style="width: 50%;float: left;">
          <p><input type="checkbox">货品校验完成后，自动发货</p>
          <p><input type="checkbox">扫描订单后，自动发货</p>
        </div>
      </div>
    </div>
  </div>
  <div class="panel admin-panel shimpent_list">
    <table class="table table-hover text-center table_left" id="itemTable">
        <!-- 加载内容 -->
    </table>
    <div class="right_submit">
      <div class="leave_word">
        
      </div>
      <div class="remark">
        
      </div>
      <div class="button_box" id="deliverGoods">发货</div>
      <span id="promptInformation">
      
      </span>
    </div>
  </div>
</body>
</html>
