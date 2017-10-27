//0.全局变量，打印通信
var webSocket = window.parent.webSocket;

//0.全局变量 快递单号
var trackNum;

$(function() {
	
	//1.点击'单据套打'
	$("#billPrinting").click(billPrinting);
	
	//2.加载打印机
	loadPrinter();
	
	//3.快递模版 改变事件
	$("#PrintTemplateCarrier").change(changePrintTemplateCarrier);
	
	//4.单据类型 改变事件
	$("#PrintType").change(changePrintType);
	
	//5.快递模版 改变事件
	$("#PrintTemplate").change(changePrintTemplate);
	
	//6.点击×关闭弹出框
	$(".del_express_btn").click(delExpressBtn);
	
	//7.监听 添加或修改订单的快递单号 回车确认事件
    $("#checkbox_form").on("keydown", ".jxb-trackingNum", addOrModifyTrackingEnter);
    
    //8.监听 添加或修改订单的快递单号 光标移除事件
    $("#checkbox_form").on("blur", ".jxb-trackingNum", addOrModifyTrackingBlur);
    
    //9.监听 添加或修改订单的快递单号 回车确认事件
    $("#orderPrint_table").on("keydown", "input[type='text']", addOrModifyInputTextEnter);
    
    //10.监听 添加或修改订单的快递单号 光标移除事件
    $("#orderPrint_table").on("blur", "input[type='text']", addOrModifyInputTextBlur);
    
    //11.监听添加或修改订单的快递单号 焦点获取事件
	$("#checkbox_form").on("focus", ".jxb-trackingNum", addOrModifyTrackingFocus);
	$("#orderPrint_table").on("focus", "input[type='text']", addOrModifyTrackingFocus);
	
	
	//100.text
	$("#PrintCarrier").click(function(){
		
//		doPrint();
		
		 var LODOP=getLodop();
		 console.log(LODOP);
		
	})
	
	//100.text
	$("#Print").click(function(){
		
//		doPrinterConfig();
		
	})
	
	
});

function doPrinterConfig() {
    var request  = {
        requestID : "12345678901234567890",
        version : "1.0",
        cmd : "printerConfig"
    };

    webSocket.send(JSON.stringify(request));
}

/***
 * 打印
 */
function doPrint()
{
     //printTaskId = $("#printTaskId").val();
//     waybillTemplateURL = $("#waybillTemplateURL").val();
//	 var waybillTemplateURL = "http://cloudprint.cainiao.com/cloudprint/template/getStandardTemplate.json?template_id=801";
	 var waybillTemplateURL = "http://localhost:8080/CaiNiao/B";
//	 var waybillTemplateURL = "http://cloudprint.cainiao.com/template/standard/101/528";
	 console.log(waybillTemplateURL);
//     customAreaURL = $("#customAreaURL").val();
//	 var customAreaURL = "http://cloudprint.cainiao.com/cloudprint/customArea/queryCustomAreaList4Top.json?custom_area_id=642230";
	 var customAreaURL = "http://localhost:8080/CaiNiao/C";
	 console.log(customAreaURL);
//     waybillNO = $("#waybillNO").val();
     var waybillNO = 9890000076011;
     var printTaskId = parseInt(1000*Math.random());

     request  = {
         cmd : "print",
         requestID : "12345678901234567890",
         version : "1.0",
         task : {
             taskID : ''+printTaskId,
             preview : true,
             printer : "FX DocuPrint M225 dw",
             previewType :"image",
             documents : [
                {
                   "documentID":waybillNO,
                   contents : [
                       //电子面单部分
                       /*{
                           templateURL : waybillTemplateURL,
                           signature : "ALIBABACAINIAOWANGLUO",
                           "data": {
                             "recipient": {
                               "address": {
                                 "city": "北京市",
                                 "detail": "花家地社区卫生服务站三层楼我也不知道是哪儿了",
                                 "district": "朝阳区",
                                 "province": "北京",
                                 "town": "望京街道"
                               },
                               "mobile": "1326443654",
                               "name": "张三",
                               "phone": "057123222"
                             },
                             "routingInfo": {
                               "consolidation": {
                                 "name": "杭州",
                                 "code": "hangzhou"
                               },
                               "origin": {
                                 "code": "POSTB"
                               },
                               "sortation": {
                                 "name": "杭州"
                               },
                               "routeCode": "380D-56-04"
                             },
                             "sender": {
                               "address": {
                                 "city": "北京市",
                                 "detail": "花家地社区卫生服务站二层楼我也不知道是哪儿了",
                                 "district": "朝阳区",
                                 "province": "北京",
                                 "town": "望京街道"
                               },
                               "mobile": "1326443654",
                               "name": "张三",
                               "phone": "057123222"
                             },
                             "shippingOption": {
                               "code": "COD",
                               "services": {
                                 "SVC-COD": {
                                   "value": "200"
                                 }
                               },
                               "title": "代收货款"
                             },
                             "waybillCode": "9890000160004"
                           }
                               //电子面单数据
                       },*/
                       //自定义区部分
                       {
                           templateURL : customAreaURL,
                           data : {
                                "item_name":"我是你要的商品芭比娃娃。。。"
                           }
                      　}
                   ]
                }
             ]
         }
     };
     alert(JSON.stringify(request));
     
     //发送请求
		webSocket.send(JSON.stringify(request));
		
		//响应请求
		webSocket.onmessage = function (event) {
			
			//获取返回参数 转为json对象
		    var response = $.parseJSON(event.data);
		    
		    console.log(response);
 		console.log(response.status);
 		console.log(response.requestID);
 		console.log(response.taskID);
 		console.log(response.previewURL);
 		console.log(response.previewImage);
 		
		};
}



/**
 * 11.监听添加或修改订单的快递单号 焦点获取事件
 * @returns
 * @author 赵滨
 */
function addOrModifyTrackingFocus() {
	//保存至全局变量
	trackNum = $(this).val();
}

/**
 * 10.监听 添加或修改订单的快递单号 光标移除事件
 * @returns
 * @author 赵滨
 */
function addOrModifyInputTextBlur() {
	//获取快递单号
	var trackingNum = $(this).val();
	
	//如果没有修改单号
	if (trackingNum == trackNum) {
		return;
	}
	
	//获取订单id
	var orderInfoId = $(this).parent().parent().data("orderInfo").id;
	
	//判断发送条件
	var ok = true;
	//如果没有输入内容
	if (trackingNum == "") {
		trackingNum = null;
		
	//如果输入不是数字
	} else if (isNaN(trackingNum)) {
		ok = false;
		alert("请输入正整数");
		
	//如果输入不是整数
	} else if (!/^\d+$/.test(trackingNum)) {
		ok = false;
		alert("请输入正整数");
		
	//如果快递单号过大
	} else if (trackingNum.toString().length > 20) {
		ok = false;
		alert("快递单号过长");
	}
	
	//如果条件通过
	if (ok) {
		//发送请求
		$.ajax({
			url : "/erp/addOrModifyTracking.do",
			type : "post",
			data : {
				"orderInfoId" : orderInfoId,
				"trackingNum" : trackingNum
			},
			dataType : "json",
			success : function(result) {
				//获取订单
				var orderInfo = result.data;
				
				//获取该条订单，所对应的网页订单,把修改后的订单输入到快递单号框内
				$("#checkbox_form").find("input[value='"+orderInfo.id+"']")
					.parent().parent().find(".jxb-trackingNum").val(orderInfo.trackingNum);
				
			},
			error : function() {
				alert("快递单号保存失败!");
			}
		});
	}
}

/**
 * 9.监听 添加或修改订单的快递单号 回车确认事件
 * @returns
 * @author 赵滨
 */
function addOrModifyInputTextEnter() {
	//如果是回车键
    if (event.keyCode == 13) {
    	//触发光标移除事件
    	$(this).blur();
    }
}

/**
 * 8.监听 添加或修改订单的快递单号 光标移除事件
 * @returns
 * @author 赵滨
 */
function addOrModifyTrackingBlur() {
	//获取快递单号
	var trackingNum = $(this).val();
	
	//如果没有修改单号
	if (trackingNum == trackNum) {
		return;
	}
	
	//获取订单id
	var orderInfoId = $(this).parent().parent().find("input[name='id']").val();
	
	//判断发送条件
	var ok = true;
	//如果没有输入内容
	if (trackingNum == "") {
		trackingNum = null;
		
	//如果输入不是数字
	} else if (isNaN(trackingNum)) {
		ok = false;
		alert("请输入正整数");
		
	//如果输入不是整数
	} else if (!/^\d+$/.test(trackingNum)) {
		ok = false;
		alert("请输入正整数");
		
	//如果快递单号过大
	} else if (trackingNum.toString().length > 20) {
		ok = false;
		alert("快递单号过长");
	}
	
	//如果条件通过
	if (ok) {
		//发送请求
		$.ajax({
			url : "/erp/addOrModifyTracking.do",
			type : "post",
			data : {
				"orderInfoId" : orderInfoId,
				"trackingNum" : trackingNum
			},
			dataType : "json",
			success : function(result) {
				//获取订单
				var orderInfo = result.data;
				/*console.log(orderInfo.trackingNum);*/
			},
			error : function() {
				alert("快递单号保存失败!");
			}
		});
	}
}

/**
 * 7.监听 添加或修改订单的快递单号 回车确认事件
 * @returns
 * @author 赵滨
 */
function addOrModifyTrackingEnter() {
	//如果是回车键
    if (event.keyCode == 13) {
    	//触发光标移除事件
    	$(this).blur();
    }
}

/**
 * 6.点击×关闭弹出框
 * @returns
 * @author 赵滨
 */
function delExpressBtn() {
	//关闭
	$(".express_box").css("display", "none");
}

/**
 * 5.快递模版 改变事件
 * @returns
 * @author 赵滨
 */
function changePrintTemplate() {
	//获取当前选中的打印机
    var printChoice = "";
    
    //如果不为空
    if ($("#PrintTemplate").find("option:checked").data("printTemplate") != undefined) {
    	//赋值
    	printChoice = $("#PrintTemplate").find("option:checked").data("printTemplate").printChoice;
    }
    
    //加载打印机
	var printChoiceList = $("#Printer option");
	for (var i = 0; i < printChoiceList.length; i++) {
		if (printChoiceList.eq(i).html() == printChoice) {
			printChoiceList.eq(i).prop("selected",true);
		}
	}
}

/**
 * 4.单据类型 改变事件
 * @returns
 * @author 赵滨
 */
function changePrintType() {
	//获取单据类型 绑定数据
	var listPrintTemplate = $("#PrintType").find("option:checked").data("listPrintTemplate");
	
	//清空快递模版
	$("#PrintTemplate").children().remove();
	
	//遍历 单据类型 绑定数据
	for (var i = 0; i < listPrintTemplate.length; i++) {
		//拼接
		var op = '<option value="'+listPrintTemplate[i].id+'">'+	//模版ID
			listPrintTemplate[i].templateName+'</option>';	//模版名称
		
		//转换对象
		var $op = $(op);
		
		//绑定数据
		$op.data("printTemplate", listPrintTemplate[i]);
		
		//加入 快递模版
		$("#PrintTemplate").append($op);
	}
	
	//5.快递模版 改变事件
	changePrintTemplate();
}

/**
 * 3.快递模版 改变事件
 * @returns
 * @author 赵滨
 */
function changePrintTemplateCarrier() {
	//获取当前选中的打印机
    var printChoice = "";
    
    //如果不为空
    if ($("#PrintTemplateCarrier").find("option:checked").data("printTemplateCarrier") != undefined) {
    	//赋值
    	printChoice = $("#PrintTemplateCarrier").find("option:checked").data("printTemplateCarrier").printChoice;
    }
    
    //加载打印机
	var printChoiceList = $("#PrinterCarrier option");
	for (var i = 0; i < printChoiceList.length; i++) {
		if (printChoiceList.eq(i).html() == printChoice) {
			printChoiceList.eq(i).prop("selected",true);
		}
	}
}

/**
 * 2.加载打印机
 * @returns
 * @author 赵滨
 */
function loadPrinter() {
	
	//如果没有成功握手打印组件
	if (webSocket.readyState != 1) {
		
		//检测打印组件相关信息
		window.parent.checkCaiNiaoPrint();
		
		return;
	}
	
	//请求 打印机列表 对象
	var request  = getRequestObject("getPrinters");
	
	//发送请求
	webSocket.send(JSON.stringify(request));
	
	//响应请求
	webSocket.onmessage = function (event) {
		
		//获取返回参数 转为json对象
	    var response = $.parseJSON(event.data);
	    
	    //清空打印机选项
	    $("#PrinterCarrier").children().remove();
	    $("#Printer").children().remove();
	    
	    //拼接块
	    var op = '<option value="">默认打印机</option>';
	    
	    //加入选项中
	    $("#PrinterCarrier").append(op);
	    $("#Printer").append(op);
	    	
    	//如果返回成功
    	if (response.status == "success") {
    		
    		//获取打印机数组
    		var printers = response.printers;
    		
    		//遍历数组
    		for (var i = 0; i < printers.length; i++) {
    			op = '';
    			op += '<option value="">';
    			op += printers[i].name;	//打印机名称
    			op += '</option>';
    			
    			//转换对象
    			var $op = $(op);
    			
    			//绑定数据
    			$op.data("printer", printers[i]);
    			
    			//加入选项中
    		    $("#PrinterCarrier").append($op);
    		    
    		    //转换对象
    			$op = $(op);
    			
    			//绑定数据
    			$op.data("printer", printers[i]);

    			//加入选项中
    		    $("#Printer").append($op);
			}
    	}
	};
}

/**
 * 1.点击'单据套打'
 * @returns
 * @author 赵滨
 */
function billPrinting() {
	
	//隐藏 提示框
	$(".set_to_play_box").css("display","none");
	
	//1.检查是否选中订单
	
	//获取订单集合
	var OrderInfoList = $("#checkbox_form input[name='id']:checked");
	
	//如果订单数量为零
	if (OrderInfoList.length == 0) {
		//提示 弹出框
		alert("请选择您需要打印的订单");
		return;
	}
	
	//2.检查快递公司是否一致
	
	//获取快递公司集合
	var CarrierInfoList = [];
	
	//遍历 订单集合
	for (var i = 0; i < OrderInfoList.length; i++) {
		//加入快递公司集合
		CarrierInfoList.push(OrderInfoList.eq(i).parent().parent().find(".xialaBtn span").eq(1).html());
	}

	//排序，用于判断
	CarrierInfoList.sort(CarrierInfoList);
	
	//如果收尾不相等，说明存在不同快递公司
	if (CarrierInfoList[0] != CarrierInfoList[CarrierInfoList.length-1]) {
		//提示 弹出框
		alert("请选择快递公司相同的订单");
		return;
	}
	
	//3.检查已经打印订单
	
	//获取订单ID
	var OrderInfoIds = [];
	
	//遍历 订单集合
	for (var i = 0; i < OrderInfoList.length; i++) {
		//加入订单ID
		OrderInfoIds.push(parseInt(OrderInfoList.eq(i).val()));
	}
	
	//发送请求
	$.ajax({
		url : "/erp/listOrderInfoPrintByIds.do",
		type : "post",
		traditional : true,
		data : {
			"orderInfoIds" : OrderInfoIds,
		},
		dataType : "json",
		success : function(result) {
			
			//获取集合map
			var map = result.data;
			
			//处理订单信息
			listOrderInfoPrintByIds(map);
			
		},
		error : function() {
			alert("获取订单信息失败!");
		}
	});
	
}

/**
 * 1.1.处理订单信息
 * @param map
 * @returns
 * @author 赵滨
 */
function listOrderInfoPrintByIds(map) {
	//1 获取订单集合
	var listOrderInfo = map.listOrderInfo;
	
	//获取已打印订单条数
	var printNum = 0;
	
	//遍历订单集合
	for (var i = 0; i < listOrderInfo.length; i++) {
		//如果已经打印快递单
		if (listOrderInfo[i].orderStatus == 4 && listOrderInfo[i].expressSheetPrint != null) {
			//订单自增
			printNum++;
			
		//如果已经打印发货单
		} else if (listOrderInfo[i].orderStatus == 5 && listOrderInfo[i].saleBillPrint != null) {
			//订单自增
			printNum++;
		}
	}
	
	//如果存在已经打印订单
	if (printNum > 0) {
		//更改提示条数
		$(".set_to_play_box").find("h4 i:first").html(printNum);
		
		//显示 提示框
		$(".set_to_play_box").css("display","block");
		
	//如果没打印过
	} else {
		//显示 打印框
		$(".express_box").css("display","block");
	}

	//2 获取发货模版
    var listBuyerInfo  = map.listBuyerInfo;
    
    //清空 订单信息
    $("#orderPrint_table").children().remove();
    
    //拼接
    var tr = '';
    
    //拼接 标题栏
    tr += '<tr>';
    tr += '<th>系统单号</th>';
    tr += '<th>原始单号</th>';
    tr += '<th width="160">运单号</th>';
    tr += '<th width="100">收件人</th>';
    tr += '<th width="200">收件人地址</th>';
    tr += '<th>批次流水号</th>';
    tr += '</tr>';
    
    //追加
    $("#orderPrint_table").append(tr);
    
    //遍历 订单 加入页面
    for (var i = 0; i < listOrderInfo.length; i++) {
    	tr = '';
		
    	tr += '<tr>';
    	tr += '<td>'+listOrderInfo[i].erpOrderNum+'</td>';	//系统单号
    	tr += '<td>'+listOrderInfo[i].platformOrderId+'</td>';	//原始单号
    	tr += '<td><input type="text" value="'+
    		(listOrderInfo[i].trackingNum == null?"":listOrderInfo[i].trackingNum)+'"></td>';	//运单号
    	
    	//如果订单和买家一致
    	if (listOrderInfo[i].platformBuyerId == listBuyerInfo[i].id) {
    		tr += '<td>'+listBuyerInfo[i].consigneeName+'</td>';
        	tr += '<td>';
        	tr += '<p>';
        	
        	//调取citys.js 获取全国地址表
        	var citys = getRegionNameByCode(listBuyerInfo[i].regionId);
        	
        	//如果没有捕获地址
        	if (citys.province == undefined) {
        		tr += listBuyerInfo[i].userAddress;
        		
        	} else {
        		tr += citys.province+citys.city+citys.area+listBuyerInfo[i].userAddress;
        	}
        	
            tr += '</p>';
            tr += '</td>';
            
        //如果不一致
    	} else {
    		//遍历买家信息
    		for (var j = 0; j < listBuyerInfo.length; j++) {
				//如果匹配
    			if (listOrderInfo[i].platformBuyerId == listBuyerInfo[j].id) {
    				tr += '<td>'+listBuyerInfo[j].consigneeName+'</td>';
    	        	tr += '<td>';
    	        	tr += '<p>';
    	        	
    	        	//调取citys.js 获取全国地址表
    	        	var citys = getRegionNameByCode(listBuyerInfo[j].regionId);
    	        	
    	        	//如果没有捕获地址
    	        	if (citys.province == undefined) {
    	        		tr += listBuyerInfo[j].userAddress;
    	        		
    	        	} else {
    	        		tr += citys.province+citys.city+citys.area+listBuyerInfo[j].userAddress;
    	        	}
    	        	
    	            tr += '</p>';
    	            tr += '</td>';
    			}
			}
    	}
    	
    	//批次流水号
        tr += '<td>'+(listOrderInfo[i].lotCode == null?"":listOrderInfo[i].lotCode)+'</td>';
        tr += '</tr>';
        
        //转换对象
		$tr = $(tr);
		
		//绑定数据
		$tr.data("orderInfo", listOrderInfo[i]);
		
    	//追加
    	$("#orderPrint_table").append($tr);
	}

	//3 获取快递模版
    var listPrintTemplateCarrier  = map.listPrintTemplateCarrier;
    
    //清空快递模版
    $("#PrintTemplateCarrier").children().remove();
    
    //拼接
    var op = '';
    
    //遍历模版
    for (var i = 0; i < listPrintTemplateCarrier.length; i++) {
    	op = '';
    	
    	op += '<option value="'+listPrintTemplateCarrier[i].id+'">'+	//模版ID
    			listPrintTemplateCarrier[i].templateName+'</option>';	//模版名称
    	
    	//转换对象
		$op = $(op);
		
		//绑定数据
		$op.data("printTemplateCarrier", listPrintTemplateCarrier[i]);
		
    	//追加
    	$("#PrintTemplateCarrier").append($op);
	}
    
    //获取当前选中的打印机
    var printChoice = "";
    
    //如果不为空
    if ($("#PrintTemplateCarrier").find("option:checked").data("printTemplateCarrier") != undefined) {
    	//赋值
    	printChoice = $("#PrintTemplateCarrier").find("option:checked").data("printTemplateCarrier").printChoice;
    }

    //加载打印机
	var printChoiceList = $("#PrinterCarrier option");
	for (var i = 0; i < printChoiceList.length; i++) {
		if (printChoiceList.eq(i).html() == printChoice) {
			printChoiceList.eq(i).prop("selected",true);
		}
	}
    
    //4 获取发货模版
    var listPrintTemplate  = map.listPrintTemplate;
    //获取发货单
    var invoice = [];
    
    //获取出库单
    var outboundOrder = [];
    
    //获取入库单
    var warehouseReceipt = [];
    
    //遍历发货模版
    for (var i = 0; i < listPrintTemplate.length; i++) {
		//获取模版类型
    	var printTemplateType = listPrintTemplate[i].templateType;
    	
    	//如果是发货单
    	if (printTemplateType == 1 || printTemplateType == 2) {
    		//加入数组
    		invoice.push(listPrintTemplate[i]);
		
		//如果是出库单
    	} else if (printTemplateType == 3 || printTemplateType == 4) {
    		//加入数组
    		outboundOrder.push(listPrintTemplate[i]);
    		
    	//如果是入库单
    	} else if (printTemplateType == 5 || printTemplateType == 6) {
    		//加入数组
    		warehouseReceipt.push(listPrintTemplate[i]);
    	}
	}

    //清空单据类型
    $("#PrintType").children().remove();
    
	op = '<option value="">发货单</option>';
	
	//转换对象
	$op = $(op);
	
	//绑定数据
	$op.data("listPrintTemplate", invoice);
	
	//加入 快递模版
	$("#PrintType").append($op);
	
	op = '<option value="">出库单</option>';
	
	//转换对象
	$op = $(op);
	
	//绑定数据
	$op.data("listPrintTemplate", outboundOrder);
	
	//加入 快递模版
	$("#PrintType").append($op);
	
	op = '<option value="">入库单</option>';
	
	//转换对象
	$op = $(op);
	
	//绑定数据
	$op.data("listPrintTemplate", warehouseReceipt);
	
	//加入 快递模版
	$("#PrintType").append($op);
	
    //清空快递模版
    $("#PrintTemplate").children().remove();
    
    //获取单据类型 选择框内的值
    var printType = $("#PrintType").find("option:checked").html();
    
    //如果是发货单
    if (printType == "发货单") {
    	//遍历 发货单
    	for (var i = 0; i < invoice.length; i++) {
    		//拼接
    		op = '<option value="'+invoice[i].id+'">'+	//模版ID
    			invoice[i].templateName+'</option>';	//模版名称
    		
    		//转换对象
    		$op = $(op);
    		
    		//绑定数据
    		$op.data("printTemplate", invoice[i]);
    		
    		//加入 快递模版
    		$("#PrintTemplate").append($op);
		}
    	
    //如果是出库单
    } else if (printType == "出库单") {
    	//遍历 出库单
    	for (var i = 0; i < outboundOrder.length; i++) {
    		//拼接
    		op = '<option value="'+outboundOrder[i].id+'">'+	//模版ID
    			outboundOrder[i].templateName+'</option>';	//模版名称
    		
    		//转换对象
    		$op = $(op);
    		
    		//绑定数据
    		$op.data("printTemplate", outboundOrder[i]);
    		
    		//加入 快递模版
    		$("#PrintTemplate").append($op);
		}
    	
    //如果是入库单
    } else if (printType == "入库单") {
    	//遍历 入库单
    	for (var i = 0; i < warehouseReceipt.length; i++) {
    		//拼接
    		op = '<option value="'+warehouseReceipt[i].id+'">'+	//模版ID
    			warehouseReceipt[i].templateName+'</option>';	//模版名称
    		
    		//转换对象
    		$op = $(op);
    		
    		//绑定数据
    		$op.data("printTemplate", warehouseReceipt[i]);
    		
    		//加入 快递模版
    		$("#PrintTemplate").append($op);
		}
    }
	
    //获取当前选中的打印机
    var printChoice = "";
    
    //如果不为空
    if ($("#PrintTemplate").find("option:checked").data("printTemplate") != undefined) {
    	//赋值
    	printChoice = $("#PrintTemplate").find("option:checked").data("printTemplate").printChoice;
    }
    
    //加载打印机
	var printChoiceList = $("#Printer option");
	for (var i = 0; i < printChoiceList.length; i++) {
		if (printChoiceList.eq(i).html() == printChoice) {
			printChoiceList.eq(i).prop("selected",true);
		}
	}
}
