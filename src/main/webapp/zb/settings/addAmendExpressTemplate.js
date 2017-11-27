var LODOP = window.parent.LODOP;//0.全局变量，打印组件
var fieldCoordinateIdDel = [];//0.全局变量 保存删除的id数组
$(function() {
	//1.加载页面
	loadAmendExpressTemplate();
	
	//2.移动按钮点击事件
	$("#addAmendExpressTemplate_move").click(moveAddAmendExpressTemplate);
	
	//3.提交修改模版点击事件
	$("#addAmendExpressTemplate_commit").click(commitAddAmendExpressTemplate);
	
	//4.加载打印机
	loadPrinter();

    //5.返回按钮 点击事件
    $("#addAmendExpressTemplate_goback").click(gobackAddAmendExpressTemplate);
});

/**
 * 5.返回按钮 点击事件
 * @author 赵滨
 */
function gobackAddAmendExpressTemplate() {
    window.history.go(-1);  //返回上一页
    // window.history.back();  //返回上一页
}

/**
 * 4.加载打印机
 * @returns
 * @author 赵滨
 */
function loadPrinter() {
	//如果没有获取到打印组件
	if (LODOP == null || LODOP == undefined) {
		showMessage("打印控件没有正确安装或启动，请点击上方执行安装。");
		return;
	}
	
	//获取打印机数目
	var PRINTER_COUNT = LODOP.GET_PRINTER_COUNT();
	//如果没有打印机
	if (PRINTER_COUNT == 0) {
		return;
	}
	
    //清空打印机选项
    $("#addAmendExpressTemplate_printChoice").children().remove();
    
    //拼接块
    var op = '<option value="-1">默认打印机</option>';
    
    //加入选项中
    $("#addAmendExpressTemplate_printChoice").append(op);
    	
	//遍历数组
	for (var i = 0; i < PRINTER_COUNT; i++) {
		op = '';
		op += '<option value="'+i+'">';
		op += LODOP.GET_PRINTER_NAME(i);	//打印机名称
		op += '</option>';
		
		//加入选项中
	    $("#addAmendExpressTemplate_printChoice").append(op);
	}
}

/**
 * 3.提交修改模版点击事件
 * @returns
 * @author 赵滨
 */
function commitAddAmendExpressTemplate() {
	//局部变量，用于删除数组
	var delFieldCoordinateId = [];
	//复制数组,避免影响全局变量
	delFieldCoordinateId = fieldCoordinateIdDel.concat();  
	
	//判断条件ok
	var ok = true;
	
	//创建发送的参数对象
	var data = {};
	
	//获取基础参数
	var printTemplateId = getCookie("printTemplateId");

    //加入到data中
	data["printTemplateId"] = printTemplateId;

    //获取页面参数 （模版）
	var templateName = $("input[name='templateName']").val();
	var templateWidth = $("input[name='templateWidth']").val();
	var templateHeight = $("input[name='templateHeight']").val();
	var printChoice = $("#addAmendExpressTemplate_printChoice").find("option:selected").text();
	//获取模版的宽度（像素数）
	var widthTemplate = $("#addAmendExpressTemplate_background").width();
	//获取模版的高度（像素数）
	var heightTemplate = $("#addAmendExpressTemplate_background").height();

    //检测模版宽高是否合格
    if (templateWidth == "" || templateHeight == "" ||
        isNaN(templateWidth) || isNaN(templateHeight) ||
        !/^\d+$/.test(templateWidth) || !/^\d+$/.test(templateHeight) ||
        templateWidth.toString().length > 3 || templateHeight.toString().length > 3) {
        showMessage("请在纸张大小中填入正整数(0-999)");
        return;
    }

	//加入到data中
	data["templateName"] = templateName;
	data["templateWidth"] = templateWidth;
	data["templateHeight"] = templateHeight;
    data["printChoice"] = printChoice;
	if (templateName == "") {
		showMessage("请输入模版名称");
		ok = false;
	}
	
	//获取框内参数 （坐标）
	var childrenDiv = $("#addAmendExpressTemplate_background").children("div");
	//遍历集合
	for (var i = 0; i < childrenDiv.length; i++) {
		//获取距离顶部坐标
		var topCoordinate = childrenDiv.eq(i).css("top");
		//像素转换成毫米
		// topCoordinate = Math.round(parseInt(topCoordinate)/deviceXDPI*25.4);
		//获取距离顶部坐标
		var leftCoordinate = childrenDiv.eq(i).css("left");
		//像素转换成毫米
		// leftCoordinate = Math.round(parseInt(leftCoordinate)/deviceXDPI*25.4);
		//获取英文名称
		var fieldName = childrenDiv.eq(i).prop("id");
        //获取x长度
        var xLength = childrenDiv.eq(i).find("textarea").css("width");
        //获取y长度
        var yLength = childrenDiv.eq(i).find("textarea").css("height");
		//获取坐标id
		var fieldCoordinateId = childrenDiv.eq(i).data("fieldCoordinateId");
		/*console.log(fieldName, topCoordinate, leftCoordinate, fieldCoordinateId);*/
		//如果是添加内容
		if (typeof(fieldCoordinateId) == "undefined") {
			//加入到data中
			data["fieldCoordinate"+i] = fieldName+","+leftCoordinate+","+topCoordinate+","+xLength+","+yLength;
		} else {
			//加入到data中
			data["fieldCoordinate"+i] =
				fieldName+","+leftCoordinate+","+topCoordinate+","+xLength+","+yLength+","+fieldCoordinateId;
			//遍历需要删除id数组
			$.each(delFieldCoordinateId,function(index,item) {
				//如果数组中的id与框内相同
	            if (item==fieldCoordinateId) {
	            	//移除不需要删除的id
	            	delFieldCoordinateId.splice(index,1);
	    	    }
			});
		}
	}
	//删除的框发送的数据
	var fieldCoordinateIdData = null;
	//遍历删除id数组
	for (var i = 0; i < delFieldCoordinateId.length; i++) {
		//转换赋值
		fieldCoordinateIdData += delFieldCoordinateId[i];
		if (i < delFieldCoordinateId.length-1) {
			fieldCoordinateIdData += ",";
		}
	}
	//加入data中，用于删除框
	data["fieldCoordinateIdData"] = fieldCoordinateIdData;

    var preset = getCookie("preset");    //是否预设模版

    if (ok) {
		//发送请求 添加模版
		$.ajax({
			url : "/addAmendExpressTemplate/commitAddAmendExpressTemplate.do",
			type : "post",
			data : {
			    "preset" : preset,
                "data" : data
			},
			dataType : "json",
			success : function(result) {
				if (result.data > 0) {
					showMessage("模版提交成功");
					// window.location.href = "../expressTemplate.do"
                    window.history.go(-1);  //返回上一页
                    // window.history.back();  //返回上一页
				} else if (result.data == 0) {
					showMessage("请填写模版内容");
				}
			},
			error : function() {
				showMessage("模版提交失败!");
			}
			
		});
	}
}

/**
 * 2.移动按钮点击事件
 * @returns
 * @author 赵滨
 */
function moveAddAmendExpressTemplate() {
	//获取框内数值集合
	var childrenDiv = $("#addAmendExpressTemplate_background").children("div");
	//获取左移数值（毫米）
	var xCoordinate = $("input[name='xCoordinate']").val();
	//获取上移数值（毫米）
	var yCoordinate = $("input[name='yCoordinate']").val();

	//判断条件ok
	var ok = true;
	//判断左移
	if (xCoordinate == "") {
		xCoordinate = 0;
	} else if (isNaN(xCoordinate)) {
		showMessage("请输入数字");
		ok = false;
	}
	//判断上移
	if (yCoordinate == "") {
		yCoordinate = 0;
	} else if (isNaN(yCoordinate)) {
		showMessage("请输入数字");
		ok = false;
	}
	if (ok) {

		//获取模版的宽度（像素数）
		var widthTemplate = $("#addAmendExpressTemplate_background").width();
		//获取模版的高度（像素数）
		var heightTemplate = $("#addAmendExpressTemplate_background").height();

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

		//遍历集合
		for (var i = 0; i < childrenDiv.length; i++) {
			//获取当前数值的宽
			var childrenDivWidth = childrenDiv.eq(i).width();
			//获取当前数值的高
			var childrenDivHeight = childrenDiv.eq(i).height();

			//如果输入的移动量不为0
			if (xCoordinate != 0) {
				//获取距离左部坐标（像素）
				var leftCoordinate = childrenDiv.eq(i).css("left");
				//移动后的坐标 （xCoordinate转换成像素）
				var newLeftCoordinate = parseInt(leftCoordinate)-(Math.round(xCoordinate/25.4*deviceXDPI));
				//如果超过左边界
				if (newLeftCoordinate <= 2) {
					//重新赋值
					childrenDiv.eq(i).css("left", 2);

				//如果不超过左边界，并且没有超过右边界
				} else if (newLeftCoordinate > 2 && newLeftCoordinate < (widthTemplate - childrenDivWidth - 2)) {
					//重新赋值
					childrenDiv.eq(i).css("left", newLeftCoordinate);

				//如果超过右边界
				} else if (newLeftCoordinate >= (widthTemplate - childrenDivWidth - 2)) {
					//重新赋值
					childrenDiv.eq(i).css("left", (widthTemplate - childrenDivWidth - 2));
				}

			}
			//如果输入的移动量不为0
			if (yCoordinate != 0) {
				//获取距离顶部坐标
				var topCoordinate = childrenDiv.eq(i).css("top");
				//移动后的坐标 （topCoordinate转换成像素）
				var newTopCoordinate = parseInt(topCoordinate)-(Math.round(yCoordinate/25.4*deviceYDPI));
				//如果超过上边界
				if (newTopCoordinate <= 2) {
					//重新赋值
					childrenDiv.eq(i).css("top", 0);

				//如果不超过上边界，并且没有超过下边界
				} else if (newTopCoordinate > 2 && newTopCoordinate < (heightTemplate - childrenDivHeight - 2)) {
					//重新赋值
					childrenDiv.eq(i).css("top", newTopCoordinate);

				//如果超过下边界
				} else if (newTopCoordinate >= (heightTemplate - childrenDivHeight - 2)) {
					//重新赋值
					childrenDiv.eq(i).css("top", (heightTemplate - childrenDivHeight - 2));
				}
			}
		}
	}
}

/**
 * 1.加载页面
 * @returns
 * @author 赵滨
 */
function loadAmendExpressTemplate() {
    //加载 页面的列表
    //获取列表的json对象
    var json = eval(templateJson.express);

    //清空页面
    $("#addAmendExpressTemplate_list").children().remove();

    //遍历列表
    for (var i = 0; i < json.length; i++) {
        //创建代码li
        var li = '<li class="'+json[i].EnglishName+'" draggable="true">'+json[i].ChineseName+'</li>';
        //追加如ul
        $("#addAmendExpressTemplate_list").append(li);
    }

    //加载 腾宁 的页面
    $("html").append('<script src="/js/expressTemplate.js"></script>');

	//获取参数
	var printTemplateId = getCookie("printTemplateId");
	var preset = getCookie("preset");    //是否预设模版

    //修改标题
    if (preset == true || preset == "true") {
        $("#addAmendExpressTemplate_title").html("添加模版");
    } else {
        $("#addAmendExpressTemplate_title").html("修改模版");
    }
    //根据id，要获取模版信息，和坐标信息
	$.ajax({
		url : "/addAmendExpressTemplate/loadAddAmendExpressTemplate.do",
		type : "post",
		data : {
			"printTemplateId" : printTemplateId,
			"preset" : preset
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				//获取map
				var map = result.data;
				
				//获取模版信息	第一部分
				var PrintTemplate = map.PrintTemplate;
				//加载模版名称
				$("input[name='templateName']").val(PrintTemplate.templateName);
				//加载模版宽度
				$("input[name='templateWidth']").val(PrintTemplate.templateWidth);
				//加载模版高度
				$("input[name='templateHeight']").val(PrintTemplate.templateHeight);
				//加载图片
				$("#addAmendExpressTemplate_background img").prop("src", PrintTemplate.templateImage);
                //加载等待
				$("#addAmendExpressTemplate_background img").load(function(){
					//设置宽度
					$("#addAmendExpressTemplate_background")
					.width($("#addAmendExpressTemplate_background img").width()+"px");
				});
				//加载打印机
				var printChoiceList = $("#addAmendExpressTemplate_printChoice option");
				for (var i = 0; i < printChoiceList.length; i++) {
					if (printChoiceList.eq(i).html() == PrintTemplate.printChoice) {
						printChoiceList.eq(i).prop("selected",true);
					}
					/*console.log();*/
				}
				
				//获取坐标集合  第二部分
				var fieldCoordinateList = map.fieldCoordinateList;
				//如果有坐标
                if (fieldCoordinateList) {
                    for (var i = 0; i < fieldCoordinateList.length; i++) {
                        //获取相关参数
                        var fieldCoordinateId = fieldCoordinateList[i].id;
                        var fieldName = fieldCoordinateList[i].fieldName;
                        var xCoordinate = fieldCoordinateList[i].xCoordinate;
                        var yCoordinate = fieldCoordinateList[i].yCoordinate;
                        var xLength = fieldCoordinateList[i].xLength;
                        var yLength = fieldCoordinateList[i].yLength;
                        //毫米转换像素
                        // xCoordinate = Math.round(xCoordinate/25.4*deviceXDPI);
                        // yCoordinate = Math.round(yCoordinate/25.4*deviceXDPI);

                        //创建拖动块
                        var div = '';
                        div += '<div id="';
                        div += fieldName;
                        div += '" draggable="true" style="width: 100px; height: 30px; position: absolute; left: ';
                        div += xCoordinate;
                        div += 'px; top: ';
                        div += yCoordinate;
                        div += 'px;">';
                        div += '<textarea readonly="" style="width: ';
                        div += xLength;
                        div += 'px; height: ';
                        div += yLength;
                        div += 'px; background: rgba(100, 100, 255, 0.2); cursor: pointer;">';
                        div += '</textarea></div>';
                        div += '</div>';
                        //转换对象
                        $div = $(div);
                        //绑定id
                        $div.data("fieldCoordinateId", fieldCoordinateId);
                        //添加到页面中显示
                        $("#addAmendExpressTemplate_background").append($div);
                        //添加到需要删除数组中
                        fieldCoordinateIdDel.push(fieldCoordinateId);
                    }
                    //加载浮动框移动事件  引用的invoiceTemplate.js
                    oldFloatingFrame();
                }
			}
		},
		error : function() {
			showMessage("模版加载失败!");
		}
	});
}

