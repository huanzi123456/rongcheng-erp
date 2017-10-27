var now_page=0;
var max_page=1;
function loadCarrierToSelectiveUl() {
	var lis = '';
	for (var i = 0; i < carrier.length; i++) {
		lis += '<li class="jxb_button">';
		lis += '<input name="carrierIdArray" value="'+(i+1)+'" type="text" style="display: none;" disabled="disabled"/>'+carrier[i]+'</li> ';
	}
	$("#jxb_carrier_ul").append(lis);
}

$(function(){
	loadCarrierToSelectiveUl();
	loadOrderSelective(1);
	//日期选择插件
	$( "#time1" ).datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear:true});
	$( "#time2" ).datepicker({dateFormat: 'yy-mm-dd 23:59:59.999', changeMonth: true, changeYear:true});
	//全选
	$("#checkall").click(function(){ 
		if($("#checkall").get(0).checked) {
			$("input[name='id']").each(function(){
				this.checked = true;
			});
		}else{
			$("input[name='id']").each(function(){
				this.checked = false;
			});
		}
	});
	$(".jxb_button").click(function(){
		if($(this).attr("class")=="jxb_button"){
			$(this).attr("class","jxb_button selected");
	    	$(this).children().removeAttr("disabled");
		}else{
			$(this).attr("class","jxb_button");
			$(this).children().attr("disabled","disabled");
		}
	});
	$(".jxb_time_button").click(function(){
		$(".jxb_time_field").find(".jxb_time").attr("disabled","disabled");//禁用其下全部input
		if($(this).attr("class")=="jxb_time_button"){//当前标签未选中
			$(".jxb_time_field").find(".selected").attr("class","jxb_time_button");//清空所有选中
			$(this).attr("class","jxb_time_button selected");//设置当前标签为选中
	    	$(this).children("input").removeAttr("disabled");//设置其下input可用
	    	$(this).children("#time1").focus();//点击时间框触发弹出插件
			var value = $(this).children($(".jxb_timeTemp")).text();
			var d = new Date();
			d.setDate(d.getDate() - value);
			$(this).children("input[name='orderCreate']").val(d.format("yyyy-MM-dd"));
		}else{//选中
			$(".jxb_time_field").find(".selected").attr("class","jxb_time_button");//清空所有选中
			//$("#time_default").removeAttr("disabled");//设置默认input可用
		}
	});
	document.getElementById("time1").onclick=function(e){
		e.stopPropagation();
	}
	document.getElementById("time2").onclick=function(e){
		e.stopPropagation();
	}
});

//根据从服务器加载订单列表
function loadOrderSelective(page) {
	if($("#timesli").attr("class")=="jxb_time_button selected"&&($("#time1").val()==""||$("#time2").val()=="")){showMessage("请填写查询时间段，或取消时间段查询。");return;}
	now_page=0;
	if(page<= 0||page> max_page||page==now_page){return;}
	now_page=page;
	var data = new FormData($("#selectiveForm")[0]);
	data.append("page",page);
	$.ajax({
		url : "/audit/listOrderBasicInfoSelective.do",
		type : "post",
		data : data,
		contentType: false,
        processData: false,
		dataType : "json",
		success : function(result) {
			max_page = result.message;
			reload(result.data);
		},
		error : function() {
			showMessage("获取订单列表失败，请稍后重试。");
		}
	});
}

//订单状态修改（1：关闭订单，2：审批订单）
function updateOrder(state) {
    if (!mustChecked()){return;}
    var data = new FormData($("#checkbox_form")[0]);
	if(state==2){
	    data.append("refundClose",state);
	}else if(state==3){
		data.append("orderStatus",state);
	}
	$.ajax({
		url : "updateOrderSelective.do",
		type : "post",
		data : data,
		contentType: false,  
        processData: false,
		dataType : "json",
		success : function(result) {
			showMessage(result.data+"条记录被修改了。");
			loadOrderSelective(now_page)
		},
		error : function() {
			showMessage("记录修改失败。");
		}
	});
}
//修改快递
function updateCarrier(id,carrierId) {
	if($.isEmptyObject($("#orderStatus").val()) || $("#orderStatus").val() > 3){
		return;
	}
	$(".xialaBtn>ul").css("display","none");
	$.ajax({
		url : "/audit/updateOrderSelective.do",
		type : "post",
		data : {"id":id,"carrierId":carrierId},
		dataType : "json",
		success : function(result) {
			$("#carrierName"+id).html(carrier[carrierId-1]);
			$("#carrierId"+id).val(carrierId);
		},
		error : function() {
			showMessage("快递信息修改失败。");
		},
		complete : function() {
			$(".xialaBtn>ul").removeAttr("style");
		}
	});
}

//判断有没有至少选择一个
function mustChecked() {
	var b = false;
	$("input[name='id']").each(function(){
	    if (this.checked) {
	      b = true;
	    }
	});
	return b;
}