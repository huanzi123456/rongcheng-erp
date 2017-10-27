$(function() {
	// 加载所有模板信息（加载完成之后继续加载所有快递分区信息）
	loadTemplate();
	// 快递下拉选
	expressSelect();
	// 加载批量选择快递框里的快递
	carrierBox();
	// 修改快递
	$("#addrOl").on("click", ".jxb_provinceCarrierLi", modifyCarrier);
	// 批量修改快递
	$("#carrierBox").on("click", "li", modifyCarriers);
	// 添加例外
	$("#addrOl").on("click", ".jxb_addlw", addlw);
	// 添加例外时选择快递后触发的快递改变事件
	$("#carrier").on("change", carrierChange);
	// 提交例外
	$("#tjlw").on("click", sublw);
	// 选择模板
	$("#addrOl").on("click", ".jxb_xzmb", xzmb);
	// 提交模板选择
	$("#subMuBan").on("click", subMuBan);
	// 删除例外
	$("#addrOl").on("click", ".jxb_del", dellw);
	//全选
	$("#checkall").click(function(){
	  if($(this).is(":checked")){
		  $(".jxb_inpt_province").prop('checked',true);
	  }else{
		  $(".jxb_inpt_province").prop('checked',false);
	  }
	})
});
//加载所有模板信息
var templates = new Array();// 所有模板
function loadTemplate() {
	$.ajax({
		url : "/loadAllTemplate.do",
		type : "post",
		dataType : "json",
		success : function(result) {
			templates = result;
			// 加载所有快递分区信息
			loadExpressPartition();
		},
		error : function() {
			queueShowMessage("加载模板信息失败");
		}
	});
}
// 加载所有快递分区信息
var distrinctExpressPartition = new Array();// 例外情况
var provinceExpressPartition = new Array();// 所有省
function loadExpressPartition() {
	$.ajax({
		url : "/loadExpressPartition.do",
		type : "post",
		dataType : "json",
		success : function(result) {
			provinceExpressPartition = result.provinceExpressPartition;
			distrinctExpressPartition = result.distrinctExpressPartition;
			// 加载到页面
			pageExpressPartition(1);
		},
		error : function() {
			queueShowMessage("加载快递分区失败");
		}
	});
}
// 快递下拉选
function expressSelect() {
	var expressSelect = '<option value="">请选择</option>';
	var carrierLength = carrier.length;
	for (var i = 0; i < carrierLength; i++) {
		expressSelect += '<option value="' + (i + 1) + '">' + carrier[i] + '</option>';
	}
	$("#carrier").append(expressSelect);
}
// 批量选择快递框里的快递
function carrierBox() {
	var carrierBox = '';
	var carrierLength = carrier.length;
	for (var i = 0; i < carrierLength; i++) {
		carrierBox += '<li value="'+(i+1)+'">' + carrier[i] + '</li>';
	}
	$("#carrierBox").append(carrierBox);
}
// 修改快递
function modifyCarrier() {
	var li = $(this);
	var carrier = li.val();
	var province = getThisLiProvince(this);
	var count = modifyCarrierAndReserved1(carrier,"",province);
	if(count > 0){
		queueShowMessage("修改成功");
		$(".xialaBtn>ul").removeAttr("style");
		li.parents(".jxb_reloadLi").find(".jxb_carrierName").html(getCarrierName(carrier));
		var templateId;
		t:for ( var i in templates) {
			var template = templates[i];
			if(template.carrierId==carrier){
				templateId = template.id;
				break t;
			}
		}
		li.parents(".jxb_reloadLi").find(".jxb_templateName").html(getTemplateName(templateId));
		for (var i = 0; i < provinceExpressPartition.length; i++) {
			if(provinceExpressPartition[i].regionCode == province){
				provinceExpressPartition[i].carrierId = carrier;
				provinceExpressPartition[i].reserved1 = templateId;
				return;
			}
		}
	}else if(count == -1){
		queueShowMessage("修改失败");
	}else if(count == 0){
		queueShowMessage("没有修改");
	}
}
// 批量修改快递
function modifyCarriers() {
	var li = $(this);
	var carrier = li.val();
	var province = new Array();
	$(".jxb_inpt_province").each(function() {
		if($(this).is(":checked")){
			province.push($(this).val());
		}
	});
	if(province.length==0){
		queueShowMessage("请选择地区");
		return;
	}
	var count = modifyCarrierAndReserved1(carrier,"",province);
	if(count > 0){
		queueShowMessage("修改成功");
		$(".xialaBtn>ul").removeAttr("style");
		var templateId;
		t:for ( var i in templates) {
			var template = templates[i];
			if(template.carrierId==carrier){
				templateId = template.id;
				break t;
			}
		}
		$(".jxb_inpt_province").each(function() {
			if($(this).is(":checked")){
				$(this).parents(".jxb_reloadLi").find(".jxb_carrierName").html(getCarrierName(carrier));
				$(this).parents(".jxb_reloadLi").find(".jxb_templateName").html(getTemplateName(templateId));
				for(var i = 0; i < provinceExpressPartition.length; i++){
					if(provinceExpressPartition[i].regionCode == $(this).val()){
						provinceExpressPartition[i].carrierId = carrier;
						provinceExpressPartition[i].reserved1 = templateId;
					}
				}
			}
		});
	}else if(count == -1){
		queueShowMessage("修改失败");
	}else if(count == 0){
		queueShowMessage("没有修改");
	}
}
// 添加例外
function addlw() {
	$(".shade_wai").css("display","block");
	var province = getThisLiProvince(this);
	addr(province);
}
// 添加例外时选择快递后触发的快递改变事件
function carrierChange() {
	var carrierId = $(this).val();
	var data = loadMuBan(carrierId);
	if(data){
		reloadSelectMuBan(data);
	}
}
//加载模板到模板下拉选
function reloadSelectMuBan(data) {
	var select = $("#muban");
	select.empty();
	var options='<option value="">请选择模板</option>';
	for(var i in data){
		var mb = data[i];
		options+='<option value="'+mb.id+'">'+mb.templateName+'</option>';
	}
	select.append(options);
}
// 提交例外
function sublw() {
	var area = $("#area").val();
	if(!area){
		queueShowMessage("请选择地区");
		return;
	}
	var carrier = $("#carrier").val();
	if(!carrier){
		queueShowMessage("请选择快递");
		return;
	}
	var reserved1 = $("#muban").val();
	if(!reserved1){
		queueShowMessage("请选择模板");
		return;
	}
	var count = modifyCarrierAndReserved1(carrier,reserved1,area);
	if(count > 0){
		queueShowMessage("添加成功");
		$(".shade_wai").css("display","none");
		var province = Math.floor(area/10000)*10000;
		var ol = '<ol class="jxb_removeEmptyOl">';
		ol += '<li>例外情况：</li>';
		ol += '<li>'+getRegionNameByCode(area).parent+'</li>';
		ol += '<li class="jxb_lwli" value="'+area+'">'+getRegionNameByCode(area).present+'</li>';
		ol += '<li>'+getCarrierName(carrier)+'</li>';
		ol += '<li class="jxb_templateName">'+getTemplateName(Number(reserved1))+'</li>';
		ol += '<li><a class="jxb_del" style="color: red; border: 1px solid #f00; border-radius: 3px; padding: 2px 10px;" href="javascript:;"><span class="icon-trash-o"></span> 删除</a></li>';
		ol += '</ol>';
		$("input[value="+province+"]").parents(".jxb_reloadLi").children().eq(3).after(ol);
		distrinctExpressPartition.unshift({regionCode: area, carrierId: carrier, reserved1: reserved1});
	}else if(count == -1){
		queueShowMessage("添加失败");
	}else if(count == 0){
		queueShowMessage("没有添加");
	}
}
// 选择模板
function xzmb() {
	$(".muban_box").css("display","block");
	var carrierName = $(this).parents(".jxb_reloadLi").find(".jxb_carrierName").html();
	var carrierId = getCarrierId(carrierName);
	var regionCode = $(this).parents(".jxb_reloadLi").find(".jxb_inpt_province").val();
	$("#subMuBan").val(regionCode);
	var data = loadMuBan(carrierId);
	if(data){
		reloadmb(data);
	}
}
//根据快递id加载模板
function loadMuBan(carrierId) {
	var data = {"carrierId":carrierId};
	var muBanArray;
	$.ajax({
		url : "/findtemplateId.do",
		data : data,
		type : "post",
		dataType : "json",
		async : false,
		success : function(result) {
			muBanArray = result.data;
		},
		error : function() {
			queueShowMessage("加载模板失败");
			muBanArray = false;
		}
	});
	return muBanArray;
}
// 加载模板到模板列表
function reloadmb(data) {
	$(".jxb_mb_li").remove();
	var lis='';
	for(var i in data){
		var mb = data[i];
		lis+='<li class="jxb_mb_li"><span><input value="'+mb.id+'" name="mbId" type="radio"/>'+mb.templateName+'</span><span>'+mb.templateType+'</span></li>';
	}
	$("#mb_first_li").after(lis);
}
// 提交模板选择
function subMuBan() {
	var province = $("#subMuBan").val();
	// 模板id
	var reserved1 = $("input[name='mbId']:checked").val();
	if(!province){
		queueShowMessage("未锁定地区，请刷新页面重新操作");
		return;
	}
	if(!reserved1){
		queueShowMessage("请选择模板");
		return;
	}
	var count = modifyCarrierAndReserved1("",reserved1,province);
	if(count > 0){
		queueShowMessage("修改成功");
		var templateName = getTemplateName(reserved1);
		$("input[value="+province+"]").parents(".jxb_reloadLi").find(".jxb_templateName").eq(0).html(templateName);
		for(var i = 0; i < provinceExpressPartition.length; i++){
			if(provinceExpressPartition[i].regionCode == province){
				provinceExpressPartition[i].reserved1 = reserved1;
			}
		}
		$(".muban_box").css("display","none");
	}else if(count == -1){
		queueShowMessage("修改失败");
	}else if(count == 0){
		queueShowMessage("没有修改");
	}
}
// 删除例外
function dellw() {
	if (confirm("您确定要删除吗?")) {
		var aTag = $(this);
		var code = aTag.parent().siblings(".jxb_lwli").val();
		var data = {"regionCode":code};
		$.ajax({
			url : "/cancelSpecial.do",
			data : data,
			type : "post",
			dataType : "json",
			success : function(result) {
				if(result>0){
					queueShowMessage("删除成功");
					aTag.parents(".jxb_removeEmptyOl").remove();
					for(var i = 0; i < distrinctExpressPartition.length; i++){
						if(distrinctExpressPartition[i].regionCode == code){
							distrinctExpressPartition.splice(i,1);
							return;
						}
					}
				}else if(result=0){
					queueShowMessage("没有删除");
				}
			},
			error : function() {
				queueShowMessage("删除失败");
			}
		});
    }
}
//根据模板id查询名字
function getTemplateName(id) {
	if(!id || isNaN(id)){
		return "";
	}else{
		for ( var i in templates) {
			if(templates[i].id == id){
				return templates[i].templateName;
			}
		}
		return "";
	}
}
function pageExpressPartition(page) {
	$("#checkall").prop('checked',false);
	var count = provinceExpressPartition.length;
	var size = 6;
	var maxPage = Math.ceil(count / size);
	if(page<1||page>maxPage){
		return;
	}
	$(".jxb_reloadLi").remove();
	var lis = '';
	var start = (page-1)*size;
	for (var i = start; i < ((start + size)>count?count:start + size); i++) {
		var pep = provinceExpressPartition[i];
		lis += '<li class="jxb_reloadLi">';
			lis += '<div>';
				lis += '<input type="checkbox" class="jxb_inpt_province" value="'+pep.regionCode+'" />'+getRegionNameByCode(pep.regionCode).present;
			lis += '</div>';
			lis += '<div>';
    			lis += '<div class="button-group">';
        			lis += '<div class="button border-main xialaBtn">';
            			lis += '<span class="icon-plus"></span><span class="jxb_carrierName">'+getCarrierName(pep.carrierId)+'</span>';
            			lis += '<ul>';
            			for(var c = 0;c<carrier.length;c++){
                			lis += '<li class="jxb_provinceCarrierLi" value="'+(c+1)+'">'+carrier[c]+'</li>';
            			}
            			lis += '</ul>';
        			lis += '</div>';
    			lis += '</div>';
    		lis += '</div>';
			lis += '<div>';
    			lis += '<a class="button border-green shade_button jxb_addlw" href="javascript:void(0)"><span class="icon-plus"></span> 添加</a>';
    		lis += '</div>';
    		lis += '<div>';
    			lis += '<a class="button border-green xz_mb jxb_xzmb" href="javascript:void(0)"><span class="icon-plus"></span> 选择</a> <span class="jxb_templateName">'+getTemplateName(Number(pep.reserved1))+'</span>';
			lis += '</div>';
			for(var j =0;j<distrinctExpressPartition.length;j++){
				var dep = distrinctExpressPartition[j];
				if(pep.regionCode==parseInt(dep.regionCode/10000)*10000){
					lis += '<ol class="jxb_removeEmptyOl">';
        			lis += '<li>例外情况：</li>';
        			lis += '<li>'+getRegionNameByCode(dep.regionCode).parent+'</li>';
        			lis += '<li class="jxb_lwli" value="'+dep.regionCode+'">'+getRegionNameByCode(dep.regionCode).present+'</li>';
        			lis += '<li>'+getCarrierName(dep.carrierId)+'</li>';
        			lis += '<li class="jxb_templateName">'+getTemplateName(Number(dep.reserved1))+'</li>';
        			lis += '<li><a class="jxb_del" style="color: red; border: 1px solid #f00; border-radius: 3px; padding: 2px 10px;" href="javascript:;"><span class="icon-trash-o"></span> 删除</a></li>';
        			lis += '</ol>';
				}
			}
		lis += '</li>';
	}
	$("#afterLi").after(lis);
	$(".jxb_removeEmptyOl").each(function(){
        if(!$(this).html()){
        	$(this).remove();
        }
    });
	pagination(maxPage, page, "pagelist", "pageExpressPartition");
}
//快递及模板提交操作
function modifyCarrierAndReserved1(carrier,reserved1,regionCode) {
	var data = new FormData();
	data.append("carrier",carrier);
	data.append("reserved1",reserved1);
	data.append("regionCode",regionCode);
	var returnData = -1;
	$.ajax({
		url : "/modifyCarrierAndReserved1.do",
		data : data,
		type : "post",
		dataType : "json",
		async : false,
		processData: false,
		contentType: false,
		success : function(result) {
			returnData = result;
		}
	});
	return returnData;
}
//根据当前列表内任意元素，获取当前所属省份 code
function getThisLiProvince(obj) {
	var provinceCode = $(obj).parents(".jxb_reloadLi").find(".jxb_inpt_province").val();
	return provinceCode;
}
//地区下拉（无街道）
function addr(province) {
	$('#shade_list').citys({
		code : province,
		required : false,
		nodata : 'disabled',
		onChange : function(data) {
			var text = data['direct'] ? '(直辖市)' : '';
			$('#place').text('当前选中地区：' + data['province'] + text + ' ' + data['city'] + ' ' + data['area']);
		}
	});
}
