//0.全局变量，打印组件
var LODOP = window.parent.LODOP;

//0.全局变量 快递单号
var trackNum;
 
$(function() {
	
	//1.加载校验
	loadCheckpoint("../");
	
	//2.监听鼠标悬浮事件，切换背景
    $("#checkbox_form").on("mouseover mouseout", ".two_row", mouseBack);
    
    //3.监听订单选择单击事件
    $("#checkbox_form").on("click", ".two_row", clickBack);
    
    //4.全选单击事件
    $("#allSelect").click(allSelect);
    
    //5.监听订单选择框单击事件
    $("#checkbox_form").on("click", "input[name='id']", clickInput);
    
    //6.全选框单击事件
    $("#checkall").click(checkAll);
    
    //7.监听鼠标悬浮选择框，改变手型
    $(".admin-panel").on("mouseover", "input[type='checkbox']", mouseCursor);
    
    //8.监听鼠标悬浮事件，切换背景
    $(".express_content").on("mouseover mouseout", ".touch_backGround", mouseBackground);
    
	//9.监听"批量打印面单"按钮单击事件
	$(".table-ul").on("click", ".pl_express_btn", batchPrintExpressTemplate);
	
	//10.监听模版选择单击事件
	$("#createPrintTemplate").on("click", ".touch_backGround", clickBackGround);

	//11.监听模版选择框单击事件
	$("#createPrintTemplate").on("click", "input[name='checkPrintTemplate']", clickBackGroundInput);
	
	//12.取消输入快递单号，改变勾选
	$("#checkbox_form").on("click", ".jxb-trackingNum", carrierClick);
	
	//13.监听添加或修改订单的快递单号 焦点获取事件
	$("#checkbox_form").on("focus", ".jxb-trackingNum", addOrModifyTrackingFocus);
	
	//14.监听添加或修改订单的快递单号 回车确认事件
    $("#checkbox_form").on("keydown", ".jxb-trackingNum", addOrModifyTrackingEnter);
	
    //15.监听添加或修改订单的快递单号 光标移除事件
    $("#checkbox_form").on("blur", ".jxb-trackingNum", addOrModifyTrackingBlur);
    
    //16.打印 点击事件（弹出打印界面内）
    $("#printBtn").click(print);
    
    //17.取消点击下拉，改变勾选
    $("#checkbox_form").on("click", ".laozi", laoziClick);
    
    //18.监听"批量打印发货单"按钮单击事件
	$(".table-ul").on("click", ".pl_shipments_btn", batchPrintInvoiceTemplate);
	
	//19.取消更换快递公司，改变勾选
	$("#checkbox_form").on("click", ".xialaBtn", xialaBtnClick);
	
	//20.取消 点击事件（弹出打印界面内）
	$("#cancelBtn").click(cancel);
	
	//21.悬浮选择快递改变样式
	$("#checkbox_form").on("mouseover mouseout", ".xialaBtn", mouseXiaLaBtn);
	
	//22.单击修改快递公司,关闭弹框
	$("#checkbox_form").on("click", ".xialaBtn li", clickLiCloseUl);
	
	
});

/**
 * 22.单击修改快递公司,关闭弹框
 * @returns
 * @author 赵滨
 */
function clickLiCloseUl() {
	//隐藏
	$(this).parent().css("display", "none");
}

/**
 * 21.悬浮选择快递改变样式
 * @returns
 * @author 赵滨
 */
function mouseXiaLaBtn() {
	//鼠标悬浮
	if(event.type == "mouseover"){
		$(this).find("ul").css("display", "block");
	//鼠标离开
	}else if(event.type == "mouseout"){
		$(this).find("ul").css("display", "none");
	}
}

/**
 * 20.取消 点击事件（弹出打印界面内）
 * @returns
 * @author 赵滨
 */
function cancel() {
	//清空标题
	$("#batch_template_title").html("");
}

/**
 * 19.取消更换快递公司，改变勾选
 * @returns
 * @author 赵滨
 */
function xialaBtnClick() {
	//获取是否被选中
	var check = $(this).parent().parent().find("input[name='id']").prop("checked");
	//如果选中
	if (check == true) {
		$(this).parent().parent().find("input[name='id']").prop("checked", false);
	//如果没有选中
	} else if (check == false) {
		$(this).parent().parent().find("input[name='id']").prop("checked", true);
	}
}

/**
 * 18.监听"批量打印发货单"按钮单击事件
 * @returns
 * @author 赵滨
 */
function batchPrintInvoiceTemplate() {

	//第一部分 判断是否选择订单
	//获取订单集合
	var OrderInfoList = $("#checkbox_form input[name='id']:checked");
	//定义订单id数组
	var OrderInfoId = [];
	//遍历
	for (var i = 0; i < OrderInfoList.length; i++) {
		//加入数组中
		OrderInfoId.push(OrderInfoList.eq(i).val());
	}
	//如果没有选中订单
	if (OrderInfoId.length == 0) {
		//清空页面
		$("#createPrintTemplate").children().remove();
		//添加到页面中
		$("#createPrintTemplate").append('<h2 style="text-align:center;cursor:default;">请选择要打印的订单</h2>');
		//取消打印按钮
		$("#printBtn").css('display','none'); 
		return;
	}
	
	//第二部分 获取模版
	//获取快递公司id
	var carrierId = 0;
	//获取基础参数
	var authorized = getCookie("authorized");
	var ownerId = getCookie("ownerId");
	var operatorId = getCookie("operatorId");
	/*console.log(carrierId, authorized, ownerId, operatorId);*/
	 //发送请求获取模版
	$.ajax({
		url : path+"/printTemplate/loadInvoiceTemplate.do",
		type : "post",
		data : {
			"carrierId" : carrierId,
			"authorized" : authorized,
			"ownerId" : ownerId,
			"operatorId" : operatorId
		},
		dataType : "json",
		success : function(result) {
			//获取模版集合
			var printTemplateList = result.data;
			//创建模版
			createInvoiceTemplate(printTemplateList);
		},
		error : function() {
			alert("面单模版加载失败!");
		}
	});
}

/**
 * 18.1.创建模版
 * @param printTemplateList 模版集合
 * @returns
 * @author 赵滨
 */
function createInvoiceTemplate(printTemplateList) {
	
	//清空页面
	$("#createPrintTemplate").children().remove();
	
	//定义发货单数组
	var InvoiceTemplateList = [];
	
	//遍历集合
	for (var i = 0; i < printTemplateList.length; i++) {
		//如果是发货单
		if (printTemplateList[i].templateType ==1 || printTemplateList[i].templateType == 2) {
			//加入数组中
			InvoiceTemplateList.push(printTemplateList[i]);
		}
	}
	
	//如果没有预设模版
	if (InvoiceTemplateList.length == 0) {
		//添加到页面中
		$("#createPrintTemplate").append('<h2 style="text-align:center;cursor:default;">您还没有设置发货单的打印模版</h2>');
		//取消打印按钮
		$("#printBtn").css('display','none'); 
	} else {
		
		//填写标题
		$("#batch_template_title").html("批量打印发货单");
		
		//显示打印按钮
		$("#printBtn").css('display','inline-block');
		//第一部分
		var li = "";
		li += '<li style="cursor:default;">';
		li += '<span style="padding-left: 50px;">名称</span><span>类型</span>';
		li += '</li>';
		//添加到页面中
		$("#createPrintTemplate").append(li);
		
		//第二部分
		li = "";
		//遍历
		for (var i = 0; i < InvoiceTemplateList.length; i++) {
			li = "";
			li += '<li class="touch_backGround">';
			li += '<span><input style="cursor:pointer;" type="radio" name="checkPrintTemplate">';
			li += InvoiceTemplateList[i].templateName;
		    li += '</span><span>';
		    
		    if (InvoiceTemplateList[i].templateType == 1) {
		    	li += '普通发货单';
		    } else if (InvoiceTemplateList[i].templateType == 2) {
		    	li += '热敏发货单';
		    }
			
		    li += '</span>';
		    li += '</li>';
		    
		    //转换对象
		    $li = $(li);
		    //绑定id
		    $li.data("printTemplateId", InvoiceTemplateList[i].id);
		    $li.data("printTemplate", InvoiceTemplateList[i]);
		    /*console.log($li.data("printTemplateId"));*/
		    //添加到页面中
			$("#createPrintTemplate").append($li);
		}
	}
	
}

/**
 * 17.取消点击下拉，改变勾选
 * @returns
 * @author 赵滨
 */
function laoziClick() {
	//获取是否被选中
	var check = $(this).parent().parent().parent().find("input[name='id']").prop("checked");
	//如果选中
	if (check == true) {
		$(this).parent().parent().parent().find("input[name='id']").prop("checked", false);
	//如果没有选中
	} else if (check == false) {
		$(this).parent().parent().parent().find("input[name='id']").prop("checked", true);
	}
}

/**
 * 16.打印 点击事件
 * @returns
 * @author 赵滨
 */
function print() {

	//获取模版id
	var printTemplateId = $("#createPrintTemplate input[name='checkPrintTemplate']:checked").parent().parent().data("printTemplateId")
	
	//如果没有选中模版
	if (printTemplateId == undefined) {
		alert("请选择面单模版");
		return;
	}
	
	//定义快递单号
	var trackingNumArray = [];
	//定义订单id
	var orderInfoArray = [];
	//获取勾选框
	var inputClick = $("#checkbox_form input[name='id']");
	//遍历
	for (var i = 0; i < inputClick.length; i++) {
		//如果勾选
		if (inputClick.eq(i).prop("checked") == true) {
			//添加到订单id中
			orderInfoArray.push(inputClick.eq(i).val());
			//如果有快递单号
			if (inputClick.eq(i).parent().parent().find("input[type='text']").val() != "") {
				//添加到快递单号中
				trackingNumArray.push(inputClick.eq(i).parent().parent().find("input[type='text']").val());
			}
		}
	}
	/*console.log(trackingNumArray.length);
	console.log(orderInfoArray.length);*/
	
	//获取模版类型说明
	var templateTypeHtml = $("#createPrintTemplate input[name='checkPrintTemplate']:checked").parent().next().html();
	//如果模版类型不是菜鸟
	if (templateTypeHtml == "普通快递面单" || templateTypeHtml == "热敏电子面单") {
		//如果有订单没有填写快递单号
		if (trackingNumArray.length != orderInfoArray.length) {
			alert("打印普通快递面单和热敏电子面单，请在订单处填写快递单号");
			return;
		}
	}
	
	//获取基础参数
	var authorized = getCookie("authorized");
	var ownerId = getCookie("ownerId");
	var operatorId = getCookie("operatorId");
	
	var ok = true;
	if (ok) {
		
		//关闭打印窗口
		$(".express_box").css("display", "none");
		//加载读取图片
		LoadingWait(true);
		
		//发送打印请求
		$.ajax({
			url : path+"/printTemplate/printTemplate.do",
			type : "post",
			traditional : true,
			data : {
				"orderInfoId" : orderInfoArray,
				"printTemplateId" : printTemplateId,
				"authorized" : authorized,
				"ownerId" : ownerId,
				"operatorId" : operatorId
			},
			dataType : "json",
			/*success : function(result) {
				//获取订单集合
				var orderInfoList = result.data;
				console.log(orderInfoList);
				//遍历
				for (var i = 0; i < orderInfoList.length; i++) {
					//获取订单id
					var orderInfoId = orderInfoList[i].id;
					//获取勾选框
					var inputClick = $("#checkbox_form input[name='id']");
					//遍历
					 for (var j = 0; j < inputClick.length; j++) {
					        //如果勾选是本条内容
					        if (inputClick.eq(j).val() == orderInfoId) {
					            
					            //获取打印快递单打印时间
					            var expressSheetPrint = orderInfoList[i].expressSheetPrint;
					            //获取发货单打印时间
					            var saleBillPrint = orderInfoList[i].saleBillPrint;
					            //如果打印两种面单
					            if (expressSheetPrint != null && saleBillPrint != null) {
					                //提示
					                inputClick.eq(j).parent().parent().find(".is_printTemplate").html("已打印面单、发货单");
					                
					            //如果打印面单
					            } else if (expressSheetPrint != null) {
					                //提示
					                inputClick.eq(j).parent().parent().find(".is_printTemplate").html("已打印面单");
					                
					            //如果打印发货单
					            }else if (saleBillPrint != null) {
					                //提示
					                inputClick.eq(j).parent().parent().find(".is_printTemplate").html("已打印发货单");
					            }
					            
					        }
					    }
				}
				//关闭读取图片
				LoadingWait(false);
			},*/
			success : function(result) {
				//获取集合
				var returnMap = result.data;
				
				//打印
				returnMapPrint(returnMap);
				
				//关闭读取图片
				LoadingWait(false);
			},
			error : function() {
				alert("打印失败");
				//关闭读取图片
				LoadingWait(false);
			}
		});
	}
}

/**
 * 16.1.加载读取图片
 * @param b true 加载，false 关闭
 * @returns
 * @author 贾晓斌
 */
function LoadingWait(b) {
	if(b){
		if($("#loading").length==0){
			var w = window.innerWidth;
			var h = window.innerHeight;;
			var imgTag = document.createElement('img'); 
			imgTag.setAttribute("src", "/images/loading.gif");
			imgTag.setAttribute("id", "loading");
			imgTag.setAttribute("style", "position: fixed;top: "+(h/2-19)+"px;left: "+(w/2-110)+"px; z-index: 9999999");
			$("body:first").append(imgTag);
		}else{
			document.getElementById("loading").style.display="";
		}
	}else{
		document.getElementById("loading").style.display="none";
	}
}

/**
 * 16.2.打印
 * @returns
 * @author 赵滨
 */
function returnMapPrint(returnMap) {
	
	//1.获取基本参数 和 加载打印框
	
	//获取订单集合
	var orderInfoList = returnMap.orderInfoList;
	
	//获取买家集合
	var buyerInfoList = returnMap.buyerInfoList;
	
	//获取字段坐标集合
	var fieldCoordinateList = returnMap.fieldCoordinateList;
	
	//获取订单商品集合
	var listItemCommonInfoList = returnMap.listItemCommonInfoList;
	
	//获取打印模版
	var printTemplate = returnMap.printTemplate;
	
	//获取店铺集合
	var shopInfoList = returnMap.shopInfoList;
	
	//获取模版宽
	var printTemplateWidth = printTemplate.templateWidth;
	
	//获取模版高
	var printTemplateHeight = printTemplate.templateHeight;
	
	//获取x和y的DPI
	var deviceXDPI = null;
	var deviceYDPI = null;
    if (window.screen.deviceXDPI) {
    	deviceXDPI = window.screen.deviceXDPI;
    	deviceYDPI = window.screen.deviceYDPI;
    }
    else {
        var tmpNode = document.createElement("DIV");
        tmpNode.style.cssText = 
        	"width:1in;height:1in;position:absolute;left:0px;top:0px;z-index:99;visibility:hidden";
        document.body.appendChild(tmpNode);
        deviceXDPI = parseInt(tmpNode.offsetWidth);
        deviceYDPI = parseInt(tmpNode.offsetHeight);
        tmpNode.parentNode.removeChild(tmpNode);    
    }
	
    //毫米转换像素
    printTemplateWidth = Math.round(printTemplateWidth/25.4*deviceXDPI);
    printTemplateHeight = Math.round(printTemplateHeight/25.4*deviceXDPI);
    
	//给打印div加宽
	$("#print_area").css("width", printTemplateWidth);
	
	//给打印div加高
	$("#print_area").css("height", printTemplateHeight);
	
	//给打印div加图片
	$("#print_image").css("background-image", "url('"+printTemplate.templateImage+"')");
	
	//获取类型
	var templateType = $("#createPrintTemplate").find("input[name='checkPrintTemplate']:checked")
						.parent().parent().data("printTemplate").templateType;
	
	//如果不是普通快递面单
	if (templateType != 7) {
		// 创建对象
		var img = new Image();
		
		// 改变图片的src
		img.src = printTemplate.templateImage;
		
		//给打印div加宽
		$("#print_image").css("width", img.width);
		
		//给打印div加高
		$("#print_image").css("height", img.height);
		
		//给打印div加图片
		$("#print_image").css("background-image", "url('"+printTemplate.templateImage+"')");
	}
	
	//2.打印每一条订单信息
	
	//遍历订单,因为可能是同时打印多条订单
	for (var i = 0; i < orderInfoList.length; i++) {
        
        //遍历字段坐标
        for (var j = 0; j < fieldCoordinateList.length; j++) {
            //获取对象名称
            var fieldCoordinateName = fieldCoordinateList[j].fieldName;
           
            //定义要打印的内容
            var printContext = null;
            
            //判断取值
            //设置寄件人
            if ("sender" == fieldCoordinateName) {
            	//赋值
            	printContext = shopInfoList[i].contactName;
                
            //设置店铺名称
            } else if ("shop_name" == fieldCoordinateName) {
            	//赋值
            	printContext = shopInfoList[i].name;
                
            //设置订单编号
            } else if ("order_number" == fieldCoordinateName) {
            	//赋值
            	printContext = orderInfoList[i].erpOrderNum;
                
            //设置商品名称
            } else if ("merchandise_news" == fieldCoordinateName) {
                //获取每条订单的商品集合 
                var tempList = listItemCommonInfoList[i];
                var shopName = "";
                for (var k = 0; k < tempList.length; k++) {
                    shopName += tempList[k].name;
                    if (k != tempList.length-1) {
                        shopName += "和";
                    }
                }
                //赋值
            	printContext = shopName;
            	
            //否则 跳出当次循环
            } else {
            	break;
            }
        
            //获取打印点坐标
            xCoordinate = fieldCoordinateList[j].xCoordinate;
            yCoordinate = fieldCoordinateList[j].yCoordinate;
            
            //毫米转换像素
            xCoordinate = Math.round(xCoordinate/25.4*deviceXDPI);
            yCoordinate = Math.round(yCoordinate/25.4*deviceXDPI);
            
            //创建拖动块
			var div = '';
			div += '<div style="width: 100px; height: 30px; position: absolute; left:';
			div += xCoordinate;
			div += 'px; top:';
			div += yCoordinate;
			div += 'px;">';
			div += printContext;
			div += '</div>';
			//添加到页面中显示
			$("#print_area").append(div);
        
        }
    }
	
	//打印这些信息
//	$("#print_area").printArea();

	$("#print_area").print({
	    globalStyles:false,//是否包含父文档的样式，默认为true
	    mediaPrint:false,//是否包含media='erp'的链接标签。会被globalStyles选项覆盖，默认为false
	    stylesheet:null,//外部样式表的URL地址，默认为null
	    noPrintSelector:".no-erp",//不想打印的元素的jQuery选择器，默认为".no-erp"
	    iframe:true,//是否使用一个iframe来替代打印表单的弹出窗口，true为在本页面进行打印，false就是说新开一个页面打印，默认为true
	    append:null,//将内容添加到打印内容的后面
	    prepend:null,//将内容添加到打印内容的前面，可以用来作为要打印内容
	    manuallyCopyFormValues:true,
	    deferred:$.Deferred()//回调函数
	    
	}); 
}

/**
 * 15.监听添加或修改订单的快递单号 光标移除事件
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
	//获取基础参数
	var authorized = getCookie("authorized");
	var ownerId = getCookie("ownerId");
	var operatorId = getCookie("operatorId");
	//获取订单id
	var orderInfoId = $(this).parent().parent().find("input[name='id']").val();
	/*console.log(authorized, ownerId, operatorId, orderInfoId, trackingNum);*/
	
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
	} else if (trackingNum > 999999999) {
		ok = false;
		alert("快递单号不能超过 999 999 999");
	}
	
	//如果条件通过
	if (ok) {
		//发送请求
		$.ajax({
			url : path+"/printTemplate/addOrModifyTracking.do",
			type : "post",
			data : {
				"orderInfoId" : orderInfoId,
				"trackingNum" : trackingNum,
				"authorized" : authorized,
				"ownerId" : ownerId,
				"operatorId" : operatorId
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
 * 14.监听添加或修改订单的快递单号 回车确认事件
 * @returns
 * @author 赵滨
 */
function addOrModifyTrackingEnter() {

	/*console.log(event.which);
	console.log(event.keyCode);*/
	//如果是回车键
    if (event.keyCode == 13) {
    	//触发光标移除事件
    	$(this).blur();
    }
}

/**
 * 13.监听添加或修改订单的快递单号 焦点获取事件
 * @returns
 * @author 赵滨
 */
function addOrModifyTrackingFocus() {
	//保存至全局变量
	trackNum = $(this).val();
}

/**
 * 12.取消输入快递单号，改变勾选
 * @returns
 * @author 赵滨
 */
function carrierClick() {
	//获取是否被选中
	var check = $(this).parent().parent().find("input[name='id']").prop("checked");
	//如果选中
	if (check == true) {
		$(this).parent().parent().find("input[name='id']").prop("checked", false);
	//如果没有选中
	} else if (check == false) {
		$(this).parent().parent().find("input[name='id']").prop("checked", true);
	}
}

/**
 * 11.监听模版选择框单击事件
 * @returns
 * @author 赵滨
 */
function clickBackGroundInput() {
	//获取是否被选中
	var check = $(this).prop("checked");
	//如果选中
	if (check == true) {
		$(this).prop("checked", false);
	//如果没有选中
	} else if (check == false) {
		$(this).prop("checked", true);
	}
}

/**
 * 10.监听模版选择单击事件
 * @returns
 * @author 赵滨
 */
function clickBackGround() {
	//获取是否被选中
	var check = $(this).find("input[name='checkPrintTemplate']").prop("checked");
	//如果选中
	if (check == true) {
		$(this).find("input[name='checkPrintTemplate']").prop("checked", false);
	//如果没有选中
	} else if (check == false) {
		$(this).find("input[name='checkPrintTemplate']").prop("checked", true);
	}
}

/**
 * 9.监听"批量打印面单"按钮单击事件
 * @returns
 * @author 赵滨
 */
function batchPrintExpressTemplate(){
	//第一部分 判断是否选择订单
	//获取订单集合
	var OrderInfoList = $("#checkbox_form input[name='id']:checked");
	//定义订单id数组
	var OrderInfoId = [];
	//遍历
	for (var i = 0; i < OrderInfoList.length; i++) {
		//加入数组中
		OrderInfoId.push(OrderInfoList.eq(i).val());
	}
	//如果没有选中订单
	if (OrderInfoId.length == 0) {
		//清空页面
		$("#createPrintTemplate").children().remove();
		//添加到页面中
		$("#createPrintTemplate").append('<h2 style="text-align:center;cursor:default;">请选择要打印的订单</h2>');
		//取消打印按钮
		$("#printBtn").css('display','none'); 
		return;
	}
	
	//第二部分 判断是否同一快递公司
	//获取快递公司集合
	var CarrierInfoList = OrderInfoList.parent().parent().find("input[name='carrierId']");
	
	//定义快递公司id数组
	var CarrierInfoId = [];
	//遍历快递公司集合 
	for (var i = 0; i < CarrierInfoList.length; i++) {
		//添加到数组中
		CarrierInfoId.push(CarrierInfoList.eq(i).val());
	}
	//排序，用于判断
	CarrierInfoId.sort(CarrierInfoId);
	//如果收尾不相等，说明存在不同快递公司
	if (CarrierInfoId[0] != CarrierInfoId[CarrierInfoId.length-1]) {
		//清空页面
		$("#createPrintTemplate").children().remove();
		//添加到页面中
		$("#createPrintTemplate").append('<h2 style="text-align:center;cursor:default;">请选择快递公司相同的订单</h2>');
		//取消打印按钮
		$("#printBtn").css('display','none'); 
		return;
	}
	
	//第三部分 获取该快递公司的模版
	//获取快递公司id
	var carrierId = CarrierInfoId[0];
	
	//获取基础参数
	var authorized = getCookie("authorized");
	var ownerId = getCookie("ownerId");
	var operatorId = getCookie("operatorId");
	/*console.log(carrierId, authorized, ownerId, operatorId);*/
	 //发送请求获取模版
	$.ajax({
		url : path+"/printTemplate/loadPrintTemplate.do",
		type : "post",
		data : {
			"carrierId" : carrierId,
			"authorized" : authorized,
			"ownerId" : ownerId,
			"operatorId" : operatorId
		},
		dataType : "json",
		success : function(result) {
			//获取模版集合
			var printTemplateList = result.data;
			//创建模版
			createPrintTemplate(printTemplateList);
		},
		error : function() {
			alert("面单模版加载失败!");
		}
	});
}
/**
 * 9.1.创建模版
 * @param printTemplateList 模版集合
 * @returns
 * @author 赵滨
 */
function createPrintTemplate(printTemplateList) {
	
	//清空页面
	$("#createPrintTemplate").children().remove();
	
	//如果没有预设模版
	if (printTemplateList.length == 0) {
		//添加到页面中
		$("#createPrintTemplate").append('<h2 style="text-align:center;cursor:default;">您还没有设置该快递公司的打印模版</h2>');
		//取消打印按钮
		$("#printBtn").css('display','none'); 
	} else {
		
		//填写标题
		$("#batch_template_title").html("批量打印面单");
		
		//显示打印按钮
		$("#printBtn").css('display','inline-block');
		//第一部分
		var li = "";
		li += '<li style="cursor:default;">';
		li += '<span style="padding-left: 50px;">名称</span><span>类型</span>';
		li += '</li>';
		//添加到页面中
		$("#createPrintTemplate").append(li);
		
		//第二部分
		li = "";
		//遍历
		for (var i = 0; i < printTemplateList.length; i++) {
			li = "";
			li += '<li class="touch_backGround">';
			li += '<span><input style="cursor:pointer;" type="radio" name="checkPrintTemplate">';
			li += printTemplateList[i].templateName;
		    li += '</span><span>';
		    
		    if (printTemplateList[i].templateType == 7) {
		    	li += '普通快递面单';
		    } else if (printTemplateList[i].templateType == 8) {
		    	li += '热敏电子面单';
		    } else if (printTemplateList[i].templateType == 9) {
		    	li += '菜鸟电子面单';
		    }
			
		    li += '</span>';
		    li += '</li>';
		    
		    //转换对象
		    $li = $(li);
		    //绑定id
		    $li.data("printTemplateId", printTemplateList[i].id);
		    $li.data("printTemplate", printTemplateList[i]);
		    //添加到页面中
			$("#createPrintTemplate").append($li);
		}
	}
}

/**
 * 8.监听鼠标悬浮事件，切换背景
 * @returns
 * @author 赵滨
 */
function mouseBackground() {
	//鼠标悬浮
	if(event.type == "mouseover"){
		$(this).prop("style","background-color: #FCFCFC;cursor:pointer;");
	//鼠标离开
	}else if(event.type == "mouseout"){
		$(this).prop("style","background-color: #FFF;");
	}
}

/**
 * 7.监听鼠标悬浮选择框，改变手型
 * @returns
 * @author 赵滨
 */
function mouseCursor() {
	$(this).css("cursor","pointer");
}

/**
 * 6.全选框单击事件
 * @returns
 * @author 赵滨
 */
function checkAll() {
	//获取是否被选中
	var check = $(this).prop("checked");
	//如果选中
	if (check == true) {
		$("#checkall").click();
	//如果没有选中
	} else if (check == false) {
		$("#checkall").click();
	}
}

/**
 * 5.监听订单选择框单击事件
 * @returns
 * @author 赵滨
 */
function clickInput(){
	//获取是否被选中
	var check = $(this).prop("checked");
	//如果选中
	if (check == true) {
		$(this).prop("checked", false);
	//如果没有选中
	} else if (check == false) {
		$(this).prop("checked", true);
	}
}

/**
 * 4.全选单击事件
 * @returns
 * @author 赵滨
 */
function allSelect() {
	//获取是否被选中
	var checkAll = $("#checkall").prop("checked");
	//如果选中
	if (checkAll == true) {
		$("#checkall").click();
	//如果没有选中
	} else if (checkAll == false) {
		$("#checkall").click();
	}
}

/**
 * 3.监听订单选择单击事件
 * @returns
 * @author 赵滨
 */
function clickBack() {
	//获取是否被选中
	var check = $(this).find("input[name='id']").prop("checked");
	//如果选中
	if (check == true) {
		$(this).find("input[name='id']").prop("checked",false);
	//如果没有选中
	} else if (check == false) {
		$(this).find("input[name='id']").prop("checked",true);		
	}
}

/**
 * 2.鼠标悬浮事件，切换背景
 * @returns
 * @author 赵滨
 */
function mouseBack() {
	//鼠标悬浮
	if(event.type == "mouseover"){
		$(this).prop("style","background-color: #FCFCFC;");
	//鼠标离开
	}else if(event.type == "mouseout"){
		$(this).prop("style","background-color: #FFF;");
	}
}