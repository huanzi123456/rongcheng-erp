<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <script src="/js/cookie_util.js"></script> 
    <script type="text/javascript" src="/util/region/js/jquery.citys.js"></script>
    <script src="/lsx/accredit.js"></script> 
	<script src="../js/big_box.js"></script>
</head>
<style>
  .div_big_box{
    overflow: hidden;
  }
</style>
<body style="position: relative;">
	<div class="div_big_box">
	  	<div class="panel admin-panel accredit-height">
		    <div class="panel-head"><strong class="icon-reorder"> 商品信息设置</strong></div>
		    <div class="padding border-bottom">
		      <ul class="search">
		        <li>
		          <button type="button"  class="button border-green" id="checkall"><span class="icon-check"></span> 全选</button>
		          <button type="submit" class="button border-main accredit_add_amend"><span class="icon-plus"></span> 添加店铺</button>
		          <button type="submit" class="button"><span class="icon-repeat"></span> 更新订单寄件地址</button>
		        </li>
		      </ul>
		    </div>
		    <table class="table table-hover text-center" id="shopInfo_table">
			</table>
	 	</div>
 	</div>
  <!-- 添加弹出框 -->
	<div class="shade_accredit">
		<div class="accreait_beij"></div>
		<div class="accreait_content">
		  <div class="panel admin-panel" style="margin-bottom: 20px;">
		    <div class="panel-head"><strong class="icon-reorder">选择店铺类型</strong></div>
		    <div class="body-content" style="padding-bottom:0;">
		      <div class="form-x">   
		        <div class="form-group">
		          <div class="label">
		            <label>选择店铺类型：</label>
		          </div>
		          <div class="field">
		            <select name="cid" class="input w50">
		              <option value="">请选择分类</option>
		              <option value="">tmall</option>
		              <option value="">tb</option>
		              <option value="">1688</option>
		              <option value="">SOP</option>
		              <option value="">FBb</option>
		            </select>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		  <div class="panel admin-panel" style="margin-bottom: 20px;">
		    <div class="panel-head"><strong class="icon-reorder">设置店铺发货类型</strong></div>
		    <div class="body-content" style="padding-bottom:0;">
		      <div class="form-x">  
		        <div class="form-group">
		          <div class="label">
		            <label>店铺名称：</label>
		          </div>
		          <div class="field">
		            <input type="text" class="input" value="" id="storeInfo_name" name="title"/>
		            <div class="tips" id="storeInfo_name_ms" style="color: red"></div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="label">
		            <label>平台会员名：</label>
		          </div>
		          <div class="field">
		            <input type="text" class="input" value="" id="storeInfo_sellerAccount" name="title"/>
		            <div class="tips" id="storeInfo_sellerAccount_ms" style="color: red"></div>
		          </div>
		        </div>  
		        <div class="form-group">
		          <div class="label">
		            <label>店铺发货人：</label>
		          </div>
		          <div class="field">
		            <input type="text" class="input" value=""  id="storeInfo_contactName" name="title"/>
		            <div class="tips" id="storeInfo_contactName_ms" style="color: red"></div>
		          </div>
		        </div>  
		        <div class="form-group">
		          <div class="label">
		            <label>店铺发货联系电话：</label>
		          </div>
		          <div class="field">
		            <input type="text" class="input" value=""   id="storeInfo_contactTel"  name="title"/>
		            <div class="tips" id="storeInfo_contactTel_ms" style="color: red"></div>
		          </div>
		        </div>  
		        <div class="form-group" >
		          <div class="label">
		            <label>店铺发货地址：</label>
		          </div>
		          <!--/////////////////////////////////////////////////  -->
		          <div id="addrDiv">
		        <select id="province" name="province"class="input" style="width:200px; line-height:17px;display:inline-block;"></select>  
				<select id="city" name="city" class="input" style="width:200px; line-height:17px;display:inline-block;"></select>  
				<select id="area" name="area"class="input" style="width:200px; line-height:17px;display:inline-block;"></select> 
				<!-- <select id="street" name="street"class="input" style="width:200px; line-height:17px;display:inline-block;"><lect>  -->  
		  		 <span id="storeInfo_userAddress_ms" style="color: red"></span> 
		  		</div>
		        </div>  
		      </div>
		    </div>
		  </div>
		  <div class="panel admin-panel" style="margin-bottom: 20px;">
		    <div class="panel-head"><strong class="icon-reorder">授权信息</strong></div>
		    <div class="body-content" style="padding-bottom:0;">
		      <div class="form-x">  
		        <div class="form-group">
		       		<div class="label">
			            <label></label>
			        </div>
		            <div class="field">
			            <button class="button border-main"> 点击授权</button>
			        </div>
		            <div class="tips"></div>
		        </div>
		        <div class="form-group">
		        	<div class="label">
			            <label></label>
			        </div>
		            <div class="field">
			            <span style="color:#00AAEE;margin-right:50px;"><a href="javascript:;" style="color:#00AAEE;">购买链接</a>（授权前，请先订购）</span>
			            <a href="javascript:;" style="color:#f00;">点击查看返现说明</a>
			        </div>
		            <div class="tips"></div>
		        </div>
		      </div>
		    </div>
		  </div>
		  <div class="panel admin-panel" style="border:none;">
		    <div class="body-content">
		      <div class="form-x">  
		        <div class="form-group ">
		          <div class="label">
		            <label></label>
		          </div>
		          <div class="field">
		            <button class="button bg-main icon-check-square-o accredit_add_amend_x"> 提交</button>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
<script>
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
	});
	var boxHeight=document.querySelector('.div_big_box').offsetHeight;
	var shade_accredit=document.querySelector(".shade_accredit");
	blackHeight(shade_accredit,boxHeight)
</script>
</body></html>