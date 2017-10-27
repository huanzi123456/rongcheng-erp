$(function() {
	// 加载平台店铺
	loadPlatformShop();
	// 加载快递列表
	loadCarrierToASDiv();
	//地区下拉（无街道）
	$('#provinceDiv').citys({
	    required:false,
	    nodata:'disabled'
	});
	//日期选择插件
	$( "#time3" ).datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear:true});
	$( "#time4" ).datepicker({dateFormat: 'yy-mm-dd 23:59:59.999', changeMonth: true, changeYear:true});
	$( "#payTime1" ).datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear:true});
	$( "#payTime2" ).datepicker({dateFormat: 'yy-mm-dd 23:59:59.999', changeMonth: true, changeYear:true});
	$(".jxb_ASCheck").change(function() {
		if($.trim($(this).val()).length>0){
			$(this).removeAttr("form");
		}else{
			$(this).attr("form","");
		}
	});
	$("input[name='erp'").click(function() {
		if(!$(this).val()){
			$(this).attr("disabled","disabled");
		}else{
			$("input[name='erp'").removeAttr("disabled");
		}
	});
	$(".jxb_dblclick_clear").dblclick(function() {
		$(this).val("");
		$(this).attr("form","");
	});
});
// 加载快递列表
function loadCarrierToASDiv() {
	var divs = '';
	for (var i = 0; i < carrier.length; i++) {
		divs += '<div class="xinxi">';
		divs += '<input type="checkbox" name="carrierIdArray" value="'+(i+1)+'"><span style="margin-right:5px;">'+carrier[i]+'</span>';
		divs += '</div>';
	}
	$("#carrier_div").append(divs);
}
//订单高级查询
function orderAdvancedSearch(page) {
	now_page=0;
	if(page<= 0||page> max_page||page==now_page){return;}
	now_page=page;
	var data = new FormData($("#advancedForm")[0]);
	data.append("page",page);
	$.ajax({
		url : "/audit/testorderAdvancedSearch.do",
		type : "post",
		data : data,
		contentType: false,
        processData: false,
		dataType : "json",
		success : function(result) {
			max_page = result.message;
			reload(result.data,true);
		},
		error : function() {
			showMessage("获取订单列表失败，请稍后重试。");
		}
	});
}
//加载平台店铺
var platformShop;
function loadPlatformShop() {
	$.ajax({
		url : "/audit/loadPlatformShop.do",
		dataType : "json",
		success : function(result) {
			platformShop = result;
			writePlatformSelect();
		},
		error : function() {
			showMessage("加载平台店铺失败，请稍后重试。");
		}
	});
}
function writePlatformSelect(){
	var options='<option value="">全部</option>';
	for ( var i in platformShop) {
		options+='<option value="'+platformShop[i].id+'">'+platformShop[i].name+'</option>';
	}
	$("#platformSelect").append(options);
	$("#platformSelect").change(function() {
		writeShopSelect($(this).val());
	});
}
function writeShopSelect(platformId){
	$("#shopSelect").empty();
	var options='<option value="">全部</option>';
	for ( var i in platformShop) {
		if(platformShop[i].id == platformId){
			for ( var j in platformShop[i].shopList) {
				var shopId = platformShop[i].shopList[j].id;
				var shopName = platformShop[i].shopList[j].name;
				options+='<option value="'+shopId+'">'+shopName+'</option>';
			}
			$("#shopSelect").append(options);
			return;
		}
	}
}