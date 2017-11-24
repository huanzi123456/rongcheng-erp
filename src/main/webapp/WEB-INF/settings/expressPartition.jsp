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
<script src="/util/region/js/jquery.citys.js"></script>
<script src="/util/carrier-utils.js"></script>
<script src="/util/jiml-utils.js"></script>
<script src="/jxb/js/expressPartition.js"></script>
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
    <div id="listform">
      <div class="panel admin-panel kdfq">
        <div class="panel-head">
          <strong class="icon-reorder"> 快递分区设置</strong>
        </div>
        <ol class="table table-hover text-center" id="addrOl">
          <li id="afterLi">
            <div>地区</div>
            <div>默认物流</div>
            <div>例外</div>
            <div>快递模板选择</div>
          </li>
          <li>
            <div>
              <input type="checkbox" id="checkall" /> 全选
            </div>
            <div colspan="7" style="text-align: left; padding-left: 20px;">
              <div class="button border-main xialaBtn" style="margin-left: 60px;">
                <span class="icon-plus"></span> 批量选择快递
                <ul id="carrierBox">
                  <%-- <li>顺丰</li> --%>
                </ul>
              </div>
            </div>
          </li>
          <li><p class="pagelist" id="pagelist"></p></li>
        </ol>
      </div>
    </div>
  </div>
  <div class="shade_wai">
    <div class="shade_hei"></div>
    <div class="shade_content">
      <h2>选择地区/快递</h2>
      <div class="shade_list" id="shade_list">
        <span>市：</span><select id="city" name="city" class="input" style="width: 180px; line-height: 17px; display: inline-block;"></select> 
        <span>区：</span><select id="area" name="area" class="input" style="width: 180px; line-height: 17px; display: inline-block;"></select>
       </div>
      <div class="shade_list">
        <span>快递：</span> <select id="carrier" class="input" style="width: 385px; line-height: 17px; display: inline-block;">
          <%-- <option>请选择</option>
          <option value="1">顺丰</option> --%>
        </select>
      </div>
      <div class="shade_list">
        <span>选择模板：</span> <select id="muban" class="input" style="width: 352px; line-height: 17px; display: inline-block;">
          <%-- <option value="qingxuanze">请选择</option>
          <option value="beijingshi">张三的电子模板</option> --%>
        </select>
      </div>
      <div class="panel admin-panel" style="border: none;">
        <div class="body-content">
          <div class="form-x">
            <div class="form-group form-padding">
              <div class="label">
                <label></label>
              </div>
              <div class="field">
                <button class="button bg-main icon-check-square-o" id="tjlw">提交</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 快递分区-快递模板选择 -->
  <div class="muban_box">
    <div class="muban_content">
      <h1>普通面单打印</h1>
      <ul class="express_list">
        <li id="mb_first_li"><span style="padding-left: 50px;">名称</span><span>类型</span></li>
        <%-- <li><span><input type="radio" name="one" checked="checked"> 张三设置的模板</span><span>热敏的</span></li> --%>
      </ul>
      <div class="express_tj">
        <button class="button border-main" id="subMuBan" style="margin-right: 50px;">
          <span class="icon-print"></span> 提交
        </button>
        <button class="button border-main del_express_btn">
          <span class="icon-trash-o"></span> 取消
        </button>
      </div>
    </div>
  </div>
</body>
<script>
  var boxHeight=document.querySelector('.div_big_box').offsetHeight;
  var shade_wai=document.querySelector(".shade_wai");
  blackHeight(shade_wai,boxHeight)
  var muban_box=document.querySelector(".muban_box");
  blackHeight(muban_box,boxHeight)
</script>
</html>