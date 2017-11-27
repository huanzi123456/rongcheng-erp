var LODOP = window.parent.LODOP;//0.全局变量，打印组件
var fieldCoordinateIdDel = [];//0.全局变量 保存删除的id数组
$(function() {
	//1.加载页面
	loadAddAmendInvoiceTemplate();
	
	//2.移动按钮点击事件
	$("#addAmendInvoiceTemplate_move").click(moveAddAmendInvoiceTemplate);
	
	//3.提交修改模版点击事件
	$("#addAmendInvoiceTemplate_commit").click(commitAddAmendInvoiceTemplate);
	
	//4.加载打印机
	loadPrinter();

    //5.发货清单 勾选事件 完成四个选项无法取消勾选
    $(".shipping_list ul").eq(0).find("input").click(checkShippingList);

    //6.返回按钮 点击事件
    $("#addAmendInvoiceTemplate_goback").click(gobackAddAmendInvoiceTemplate);
});

/**
 * 6.返回按钮 点击事件
 * @author 赵滨
 */
function gobackAddAmendInvoiceTemplate() {
    window.history.go(-1);  //返回上一页
    // window.history.back();  //返回上一页
}

/**
 * 5.发货清单 勾选事件 完成四个选项无法取消勾选
 * @author 赵滨
 */
function checkShippingList() {
    //获取当前的类名
    var className = $(this).prop("class");

    //如果是四个必须选中的内容
    if (className == "productName" || className == "CODING" ||
        className == "storageLocation" || className == "productNumber") {
        //选中
        $(this).click();
    }
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
    $("#addAmendInvoiceTemplate_printChoice").children().remove();
    
    //拼接块
    var op = '<option value="-1">默认打印机</option>';
    //加入选项中
    $("#addAmendInvoiceTemplate_printChoice").append(op);
	//遍历数组
	for (var i = 0; i < PRINTER_COUNT; i++) {
		op = '';
		op += '<option value="'+i+'">';
		op += LODOP.GET_PRINTER_NAME(i);	//打印机名称
		op += '</option>';
		
		//加入选项中
	    $("#addAmendInvoiceTemplate_printChoice").append(op);
	}
}

/**
 * 3.提交修改模版点击事件
 * @returns
 * @author 赵滨
 */
function commitAddAmendInvoiceTemplate() {
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
	var printChoice = $("#addAmendInvoiceTemplate_printChoice").find("option:selected").text();

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

    //定义个数变量
    var iNum = 1;
	//获取框内参数 （坐标） 上部
	var childrenDiv = $(".header_content").children("div");
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
			data["fieldCoordinate"+iNum] = fieldName+","+leftCoordinate+","+topCoordinate+","+xLength+","+yLength;
		} else {
			//加入到data中
			data["fieldCoordinate"+iNum] =
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
        iNum++;
	}

    //获取框内参数 （坐标） 下部
    childrenDiv = $(".footer_content").children("div");
	//遍历集合
    for (var i = 0; i < childrenDiv.length; i++) {
        //获取距离顶部坐标
        var topCoordinate = "-"+childrenDiv.eq(i).css("top");
        //像素转换成毫米
        // topCoordinate = Math.round(parseInt(topCoordinate)/deviceXDPI*25.4);
        //获取距离顶部坐标
        var leftCoordinate = "-"+childrenDiv.eq(i).css("left");
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

        //如果是添加内容
        if (typeof(fieldCoordinateId) == "undefined") {
            //加入到data中
            data["fieldCoordinate"+iNum] = fieldName+","+leftCoordinate+","+topCoordinate+","+xLength+","+yLength;
        } else {
            //加入到data中
            data["fieldCoordinate"+iNum] =
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
        iNum++;
    }

    //获取框内参数 发货清单
    childrenDiv = $(".shipping_list").children("ul").eq(0).find("input[type='checkbox']");
    //遍历集合
    for (var i = 0; i < childrenDiv.length; i++) {
        //如果勾选了内容
        if (childrenDiv.eq(i).prop("checked") == true) {
            //获取英文名称
            var fieldName = childrenDiv.eq(i).prop("class");
            //获取坐标id
            var fieldCoordinateId = childrenDiv.eq(i).val();

            //如果是添加内容
            if (fieldCoordinateId == "on"  || fieldCoordinateId == "") {
                //加入到data中
                data["fieldCoordinate"+iNum] = fieldName+","+0+","+0+","+0+","+0;
            } else {
                //加入到data中
                data["fieldCoordinate"+iNum] = fieldName+","+0+","+0+","+0+","+0+","+fieldCoordinateId;
                //遍历需要删除id数组
                $.each(delFieldCoordinateId,function(index,item) {
                    //如果数组中的id与框内相同
                    if (item==fieldCoordinateId) {
                        //移除不需要删除的id
                        delFieldCoordinateId.splice(index,1);
                    }
                });
            }

            iNum++;
        }
    }

    //获取 商品排序：
    childrenDiv = $(".shipping_list").children("ul").eq(1).find("input[type='radio']:checked");

    //获取英文名称
    var fieldName = childrenDiv.prop("class");

    //获取坐标id
    var fieldCoordinateId = childrenDiv.val();

    //如果是添加内容
    if (fieldCoordinateId == "on" || fieldCoordinateId == "") {
        //加入到data中
        data["fieldCoordinate"+iNum] = fieldName+","+1+","+1+","+1+","+1;
    } else {
        //加入到data中
        data["fieldCoordinate"+iNum] = fieldName+","+1+","+1+","+1+","+1+","+fieldCoordinateId;
        //遍历需要删除id数组
        $.each(delFieldCoordinateId,function(index,item) {
            //如果数组中的id与框内相同
            if (item==fieldCoordinateId) {
                //移除不需要删除的id
                delFieldCoordinateId.splice(index,1);
            }
        });
    }
    iNum++;

    //获取 表格宽度px
    var tableWidth = $("#addAmendInvoiceTemplate_background").width();
    //获取坐标id
    var fieldCoordinateId = $("#addAmendInvoiceTemplate_background").val();
    //加入到data中
    data["fieldCoordinate"+iNum] = tableWidth+","+0+","+1+","+0+","+1+","+fieldCoordinateId;
    /*iNum++;

    //获取 字体样式：
    var fontStyle = $("#fontStyle").find("option:selected").html();
    //加入到data中
    data["fieldCoordinate"+iNum] = fontStyle+","+1+","+1+","+0+","+0;
    iNum++;

    //获取 字体大小：
    var fontSize = $(".font_size_text").children("input").val();
    //加入到data中
    data["fieldCoordinate"+iNum] = fontSize+","+0+","+0+","+1+","+1;*/

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
			url : "/addAmendInvoiceTemplate/commitAddAmendInvoiceTemplate.do",
			type : "post",
			data : {
                "preset" : preset,
                "data" : data
            },
			dataType : "json",
			success : function(result) {
                if (result.data > 0) {
					showMessage("模版提交成功");
					// window.location.href = "../invoiceTemplate.do"
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
function moveAddAmendInvoiceTemplate() {
    //获取框内数值集合
    var childrenDiv = $("#addAmendInvoiceTemplate_printChoice").children("div").children("div");
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
        var widthTemplate = $("#addInvoiceTemplate_background").width();
        //获取模版的高度（像素数）
        var heightTemplate = $("#addInvoiceTemplate_background").height();

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
                    childrenDiv.eq(i).css("top", 2);

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
function loadAddAmendInvoiceTemplate() {
    $(".header_content").width(1000);
    $(".header_content").height(180);
    $(".table_list_box").width(1000);
    $(".table_list_box").height(180);
    $(".footer_content").width(1000);
    $(".footer_content").height(180);
    //加载 页面的列表
    //获取列表的json对象
    var json = eval(templateJson.invoice);

    //清空页面
    $("#addAmendInvoiceTemplate_list").children().remove();

    //遍历列表
    for (var i = 0; i < json.length; i++) {
        //创建代码li
        var li = '<li class="'+json[i].EnglishName+'" draggable="true">'+json[i].ChineseName+'</li>';
        //追加如ul
        $("#addAmendInvoiceTemplate_list").append(li);
    }

    //加载 腾宁 的页面
    $("html").append('<script src="/js/invoiceTemplate.js"></script>');

	//获取参数
	var printTemplateId = getCookie("printTemplateId");
    var preset = getCookie("preset");    //是否预设模版

    //修改标题
    if (preset == true || preset == "true") {
        $("#addAmendInvoiceTemplate_title").html("添加模版");

        //勾选商品名称
        $(".shipping_list ul").eq(0).find(".productName").click();
        //勾选商家编码
        $(".shipping_list ul").eq(0).find(".CODING").click();
        //勾选库位
        $(".shipping_list ul").eq(0).find(".storageLocation").click();
        //勾选数量
        $(".shipping_list ul").eq(0).find(".productNumber").click();

        //选中商品编码
        $(".CODING").prop("checked", true);
    } else {
        $("#addAmendInvoiceTemplate_title").html("修改模版");
    }

    //根据id，要获取模版信息，和坐标信息
	$.ajax({
		url : "/addAmendInvoiceTemplate/loadAddAmendInvoiceTemplate.do",
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
				//加载打印机
				var printChoiceList = $("#addAmendInvoiceTemplate_printChoice option");
				for (var i = 0; i < printChoiceList.length; i++) {
					if (printChoiceList.eq(i).html() == PrintTemplate.printChoice) {
						printChoiceList.eq(i).prop("selected",true);
					}
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

                        //如果是坐标 上部
                        if (xCoordinate > 1 && yCoordinate > 1 && xLength > 1 && yLength > 1) {
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
                            div += 'px; background: rgba(100, 100, 255, 0.2); cursor: pointer; ">';
                            div += '</textarea></div>';
                            div += '</div>';
                            //转换对象
                            $div = $(div);
                            //绑定id
                            $div.data("fieldCoordinateId", fieldCoordinateId);
                            //添加到页面中显示
                            $(".header_content").append($div);
                            //添加到需要删除数组中
                            fieldCoordinateIdDel.push(fieldCoordinateId);

                            //如果是坐标 下部
                        } else if (xCoordinate < -1 && yCoordinate < -1 && xLength > 1 && yLength > 1) {
                            //创建拖动块
                            var div = '';
                            div += '<div id="';
                            div += fieldName;
                            div += '" draggable="true" style="width: 100px; height: 30px; position: absolute; left: ';
                            div += Math.abs(xCoordinate);
                            div += 'px; top: ';
                            div += Math.abs(yCoordinate);
                            div += 'px;">';
                            div += '<textarea readonly="" style="width: ';
                            div += xLength;
                            div += 'px; height: ';
                            div += yLength;
                            div += 'px; background: rgba(100, 100, 255, 0.2); cursor: pointer; ">';
                            div += '</textarea></div>';
                            div += '</div>';
                            //转换对象
                            $div = $(div);
                            //绑定id
                            $div.data("fieldCoordinateId", fieldCoordinateId);
                            //添加到页面中显示
                            $(".footer_content").append($div);
                            //添加到需要删除数组中
                            fieldCoordinateIdDel.push(fieldCoordinateId);

                            //如果是 表格宽度
                        } else if (xCoordinate == 0 && yCoordinate == 1 && xLength == 0 && yLength == 1) {
                            //绑定id
                            $("#addAmendInvoiceTemplate_background").val(fieldCoordinateId);
                            // //添加到需要删除数组中
                            // fieldCoordinateIdDel.push(fieldCoordinateId);

                            //如果是 发货清单
                        } else if (xCoordinate == 0 && yCoordinate == 0 && xLength == 0 && yLength == 0) {
                            //选中
                            // $(".shipping_list").children("ul").eq(0).find("." + fieldName + "").prop("checked", true);
                            $(".shipping_list").children("ul").eq(0).find("." + fieldName + "").click();
                            //绑定id
                            $(".shipping_list").children("ul").eq(0).find("." + fieldName + "").val(fieldCoordinateId);
                            //添加到需要删除数组中
                            fieldCoordinateIdDel.push(fieldCoordinateId);

                            //如果是 商品排序
                        } else if (xCoordinate == 1 && yCoordinate == 1 && xLength == 1 && yLength == 1) {
                            //选中
                            $(".shipping_list").children("ul").eq(1).find("." + fieldName + "").prop("checked", true);
                            //绑定id
                            $(".shipping_list").children("ul").eq(1).find("." + fieldName + "").val(fieldCoordinateId);
                            //添加到需要删除数组中
                            fieldCoordinateIdDel.push(fieldCoordinateId);

                            //如果是 字体样式
                        } /*else if (xCoordinate == 1 && yCoordinate == 1 && xLength == 0 && yLength == 0) {
                        //获取字体样式
                    	var fontStyle = $("#fontStyle").find("option");
                    	//遍历
                        fontStyle.forEach(function (e) {
                            if ($(this).html() == fieldName) {
                            	//选中
                                $(this).prop("selected", true);
                            }
						});

					//如果是 字体大小
                    } else if (xCoordinate == 0 && yCoordinate == 0 && xLength == 1 && yLength == 1) {
                        //赋值
                    	$(".font_size_text").children("input").val(fieldName);
                    }*!/*/
                    }
                    //加载浮动框移动事件  引用的invoiceTemplate.js
                    oldFloatingFrame2();
                }
			}
		},
		error : function() {
			showMessage("模版加载失败!");
		}
	});
}

