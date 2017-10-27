//0.全局变量，打印组件
var LODOP = window.parent.LODOP;

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

    //12.点击左侧'打印'按钮,打印快递单
    $("#PrintCarrier").click(StartPrintCarrier);

    //13.点击右侧'打印'按钮,打印发货单
    $("#Print").click(StartPrint);

    //14.点击左侧'预览'按钮，预览快递单
    $("#PreviewCarrier").click(StartPreviewCarrier);

    //15.点击右侧'预览'按钮，预览发货单
    $("#Preview").click(StartPreview);

    //16.点击左侧'编辑'按钮，编辑快递单
    $("#RedactCarrier").click(StartRedactCarrier);

    //17.点击右侧'编辑'按钮，编辑发货单
    $("#Redact").click(StartRedact);

});

/**
 * 17.点击右侧'编辑'按钮，编辑发货单
 * @author 赵滨
 */
function StartRedact() {

    //获取快递模版
    var printTemplate = $("#PrintTemplate").find("option:selected").data("printTemplate");

    //绑定cookie
    addCookie("printTemplateId",printTemplate.id);
    addCookie("preset", false);

    //跳转 快递单修改页面
    // window.open("/amendInvoiceTemplate.do");
    window.location.href = "/addAmendInvoiceTemplate.do";

}

/**
 * 16.点击左侧'编辑'按钮，编辑快递单
 * @author 赵滨
 */
function StartRedactCarrier() {

    //获取快递模版
    var printTemplateCarrier = $("#PrintTemplateCarrier").find("option:selected").data("printTemplateCarrier");

    //绑定cookie
    addCookie("printTemplateId",printTemplateCarrier.id);
    addCookie("preset", false);

    //跳转 快递单修改页面
    window.location.href = "/addAmendExpressTemplate.do";
}

/**
 * 15.点击右侧'预览'按钮，预览发货单
 * @author 赵滨
 */
function StartPreview() {
    //获取快递模版
    var printTemplate = $("#PrintTemplate").find("option:selected").data("printTemplate");

    //如果没有模版
    if (printTemplate == undefined) {
        showMessage("没有面单模版,请您先预设面单模版");
        return;
    }

    //获取打印信息,然后预览
    StartPrintLodop(printTemplate.id, $("#Printer").find("option:selected").html(), false);
}

/**
 * 14.点击左侧'预览'按钮，预览快递单
 * @author 赵滨
 */
function StartPreviewCarrier() {

    //获取快递模版
    var printTemplateCarrier = $("#PrintTemplateCarrier").find("option:selected").data("printTemplateCarrier");

    //如果没有模版
    if (printTemplateCarrier == undefined) {
        showMessage("没有面单模版,请您先预设面单模版");
        return;
    }

    //如果是 菜鸟面单
    if (printTemplateCarrier.templateType == 9) {
        showMessage("、菜鸟面单的一系列操作，待完成");

        //否则是 普通快递单
    } else if (printTemplateCarrier.templateType == 7 ||
        printTemplateCarrier.templateType == 8) {
        //获取打印信息,然后打印
        StartPrintCarrierLodop(printTemplateCarrier.id, $("#PrinterCarrier").find("option:selected").html(), false);
    }
}

/**
 * 13.点击右侧'打印'按钮,打印发货单
 * @author 赵滨
 */
function StartPrint() {
    //获取快递模版
    var printTemplate = $("#PrintTemplate").find("option:selected").data("printTemplate");

    //如果没有模版
    if (printTemplate == undefined) {
        showMessage("没有面单模版,请您先预设面单模版");
        return;
    }

    //获取打印信息,然后打印
    StartPrintLodop(printTemplate.id, $("#Printer").find("option:selected").html(), true);

}

/**
 * 13.1.获取打印信息,然后打印
 * @constructor
 * @author 赵滨
 */
function StartPrintLodop(printTemplateId, PrinterName, isPrint) {
    //定义订单id
    var orderInfoArray = [];

    //获取 订单集合
    var listOrderInfo = $("#orderPrint_table tr");

    //遍历
    for (var i = 0; i < listOrderInfo.length; i++) {
        //如果 存在
        if (listOrderInfo.eq(i).data("orderInfo") != null) {
            //添加到订单id中
            orderInfoArray.push(listOrderInfo.eq(i).data("orderInfo").id);

            //获取 快递单号
            var trackingNum = listOrderInfo.eq(1).find("input[type='text']").val();

            //如果没有输入内容
            if (trackingNum == "") {
                showMessage("请在运单号内输入正整数");
                return;

                //如果输入不是数字
            } else if (isNaN(trackingNum)) {
                showMessage("请在运单号内输入正整数");
                return;

                //如果输入不是整数
            } else if (!/^\d+$/.test(trackingNum)) {
                showMessage("请在运单号内输入正整数");
                return;

                //如果快递单号过大
            } else if (trackingNum.toString().length > 20) {
                showMessage("请在运单号内输入正整数");
                return;
            }
        }
    }

    //发送打印请求
    $.ajax({
        url : "/printTemplate/printTemplate.do",
        type : "post",
        traditional : true,
        data : {
            "orderInfoId" : orderInfoArray,
            "printTemplateId" : printTemplateId,
            "authorized" : 1,
            "ownerId" : 1,
            "operatorId" : 1
        },
        dataType : "json",

        success : function(result) {
            //获取集合
            var returnMap = result.data;

            //打印
            returnMapPrint(returnMap, PrinterName, isPrint);

        },
        error : function() {
            showMessage("打印失败");
        }
    });
}

/**
 * 13.2.打印
 * @returns
 * @author 赵滨
 */
function returnMapPrint(returnMap, PrinterName, isPrint) {

    //1 获取基本参数 和 加载打印框

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

    //2 打印每一条订单信息

    //遍历订单,因为可能是同时打印多条订单
    for (var i = 0; i < orderInfoList.length; i++) {
        //清空
        $("#print_table thead").children().remove();
        $("#print_table tbody").children().remove();
        $("#print_table tfoot").children().remove();
        $("#print_area_div_top").children().remove();
        $("#print_area_div_bottom").children().remove();

        //获取每条订单的商品数量
        var itemQuantity = listItemCommonInfoList[i].length;

        //设定 序号
        var serialNumber = false;
        //设定 商品名称
        var productName = false;
        //设定 数量
        var productNumber = false;
        //设定 单价
        var unitPrice = false;
        //设定 优惠
        var discounts = false;
        //设定 实付金额
        var amountActuallyPaid = false;
        //设定 规格
        var specification = false;
        //设定 金额
        var money = false;
        //设定 商家编码
        var CODING = false;
        //设定 库位
        var storageLocation = false;
        //设定 总计
        var aggregate = false;
        //设定 商品图片
        var productPicture = false;
        //设定 打印线上商品信息
        var printOnlineItem = false;
        //设定 打印组合商品信息
        var printGroupItem = false;

        //遍历字段坐标
        for (var j = 0; j < fieldCoordinateList.length; j++) {
            //获取对象名称
            var fieldCoordinateName = fieldCoordinateList[j].fieldName;

            //获取打印点坐标
            xCoordinate = fieldCoordinateList[j].xCoordinate;
            yCoordinate = fieldCoordinateList[j].yCoordinate;

            //获取打印宽高
            xLength = fieldCoordinateList[j].xLength;
            yLength = fieldCoordinateList[j].yLength;


            //如果是 发货清单
            if (xCoordinate == 0 && yCoordinate == 0 && xLength == 0 && yLength == 0) {
                if ("serialNumber" == fieldCoordinateName) {
                    serialNumber = true;
                } else if ("productName" == fieldCoordinateName) {
                    productName = true;
                } else if ("productNumber" == fieldCoordinateName) {
                    productNumber = true;
                } else if ("unitPrice" == fieldCoordinateName) {
                    unitPrice = true;
                } else if ("discounts" == fieldCoordinateName) {
                    discounts = true;
                } else if ("amountActuallyPaid" == fieldCoordinateName) {
                    amountActuallyPaid = true;
                } else if ("specification" == fieldCoordinateName) {
                    specification = true;
                } else if ("money" == fieldCoordinateName) {
                    money = true;
                } else if ("CODING" == fieldCoordinateName) {
                    CODING = true;
                } else if ("storageLocation" == fieldCoordinateName) {
                    storageLocation = true;
                } else if ("aggregate" == fieldCoordinateName) {
                    aggregate = true;
                } else if ("productPicture" == fieldCoordinateName) {
                    productPicture = true;
                } else if ("printOnlineItem" == fieldCoordinateName) {
                    printOnlineItem = true;
                } else if ("printGroupItem" == fieldCoordinateName) {
                    printGroupItem = true;
                }
            //如果是表格宽度
            } else if (xCoordinate == 0 && yCoordinate == 1 && xLength == 0 && yLength == 1) {
                $("#print_table").width(fieldCoordinateName);
            }
        }

        //定义金额总计
        var priceSum = 0;

        //定义数量总计
        var quantitySum = 0;

        //定义实付金额总计

        //定义优惠总计

        //遍历订单商品表
        $.each(listItemCommonInfoList[0], function (j, item) {
            //创建拼接块
            var tr = '';

            //如果是第一次生成 创建标题
            if (j == 0) {
                tr = '<tr>';

                if (serialNumber) {
                    tr += '<th>序号</th>';
                }
                if (productPicture) {
                    tr += '<th>商品图片</th>';
                }
                if (productName) {
                    tr += '<th>商品名称</th>';
                }
                if (productNumber) {
                    tr += '<th>数量</th>';
                }
                if (specification) {
                    tr += '<th>规格</th>';
                }
                if (CODING) {
                    tr += '<th>商品编码</th>';
                }
                if (unitPrice) {
                    tr += '<th>单价</th>';
                }
                if (discounts) {
                    tr += '<th>优惠</th>';
                }
                if (amountActuallyPaid) {
                    tr += '<th>实付金额</th>';
                }
                if (money) {
                    tr += '<th>金额</th>';
                }
                if (storageLocation) {
                    tr += '<th>库位</th>';
                }

                tr += '</tr>';

                //追加
                $("#print_table thead").append(tr);
            }

            tr = '<tr>';

            if (serialNumber) {
                tr += '<td><font size="5">'+(j+1)+'</font></td>';  //序号
            }
            if (productPicture) {
                tr += '<td><img src="'+(item.image1 == null?"":item.image1)+'" /></td>';  //商品图片
            }
            if (productName) {
                tr += '<td><font size="5">'+item.name+'</font></td>';  //商品名称
            }
            if (productNumber) {
                tr += '<td><font size="5">'+item.quantity+'</font></td>';  //数量
                quantitySum += item.quantity;
            }
            if (specification) {
                tr += '<td><font size="5">'+item.color+" "+item.size+'</font></td>';  //规格
            }
            if (CODING) {
                tr += '<td><font size="5">'+item.erpItemNum+'</font></td>';  //商品编码
            }
            if (unitPrice) {
                tr += '<td><font size="5">'+item.normalPrice+'</font></td>';  //单价
            }
            if (discounts) {
                tr += '<td><font size="5">'+0+'</font></td>';  //优惠
            }
            if (amountActuallyPaid) {
                tr += '<td><font size="5">0</font></td>';  //实付金额
            }
            if (money) {
                tr += '<td><font size="5">'+(item.normalPrice * item.quantity)+'</font></td>';  //金额
                priceSum += (item.normalPrice * item.quantity);
            }
            if (storageLocation) {
                tr += '<td><font size="5">库位...</font></td>';  //库位
            }

            tr += '</tr>';

            //追加
            $("#print_table tbody").append(tr);
        });

        //如果存在总价
        if (aggregate) {
            tr = '<tr>';

            if (serialNumber) {
                tr += '<td><font size="5">总计</font></td>';  //序号
            }
            if (productPicture) {
                tr += '<td></td>';
            }
            if (productName) {
                tr += '<td></td>';
            }
            if (productNumber) {
                tr += '<td><font size="5">'+quantitySum+'</font></td>';  //数量
            }
            if (specification) {
                tr += '<td></td>';
            }
            if (CODING) {
                tr += '<td></td>';
            }
            if (unitPrice) {
                tr += '<td></td>';
            }
            if (discounts) {
                tr += '<td><font size="5">0</font></td>';  //优惠
            }
            if (amountActuallyPaid) {
                tr += '<td><font size="5">0</font></td>';  //实付金额
            }
            if (money) {
                tr += '<td><font size="5">'+priceSum+'</font></td>';  //金额
            }
            if (storageLocation) {
                tr += '<td></td>';
            }

            tr += '</tr>';

            //追加
            $("#print_table tbody").append(tr);
        }

        //获取发件人地区
        var consignorRegion = getRegionNameByCode(shopInfoList[i].regionId);

        //获取收件人地区
        var consigneeRegion = getRegionNameByCode(buyerInfoList[i].regionId);

        //遍历字段坐标
        for (var j = 0; j < fieldCoordinateList.length; j++) {
            // console.log("第几条坐标:"+j);
            //获取对象名称
            var fieldCoordinateName = fieldCoordinateList[j].fieldName;

            //定义要打印的内容
            var printContext = null;

            //获取列表的json对象
            var json = eval(templateJson.express);

            //遍历列表
            for (var k = 0; k < json.length; k++) {
                //如果 名称 成功匹配
                if (json[k].EnglishName == fieldCoordinateName) {
                    //赋值
                    printContext = json[k].ChineseName+":"+eval(json[k].ParameterName);
                }
            }

            //获取打印点坐标
            xCoordinate = fieldCoordinateList[j].xCoordinate;
            yCoordinate = fieldCoordinateList[j].yCoordinate;

            //获取打印宽高
            xLength = fieldCoordinateList[j].xLength;
            yLength = fieldCoordinateList[j].yLength;

            //如果是 打印上部内容
            if (xCoordinate > 1 && yCoordinate > 1 && xLength > 1 && yLength > 1) {
                //拼接块
                var div = '<div style="width: '+xLength+'px; height: '+yLength+'px; position: absolute; ' +
                    'left: '+Math.abs(xCoordinate)+'px; top: '+Math.abs(yCoordinate)+'px;overflow: hidden;" >'+
                    printContext+'</div>';
                $("#print_area_div_top").append(div);

            //如果是 打印下部内容
            } else if (xCoordinate < -1 && yCoordinate < -1 && xLength > 1 && yLength > 1) {
                //拼接块
                var div = '<div style="width: '+xLength+'px; height: '+yLength+'px; position: absolute; ' +
                    'left: '+Math.abs(xCoordinate)+'px; top: '+Math.abs(yCoordinate)+'px;overflow: hidden;" >'+
                    printContext+'</div>';
                $("#print_area_div_bottom").append(div);
            }
        }

        //3设置打印相关信息

        //按序号或名称指定打印机，选定后禁止手工重选；
        LODOP.SET_PRINTER_INDEX(PrinterName);

        //打印方向及纸张类型,纸张宽,固定纸张时该参数是纸张高,纸张类型名
        LODOP.SET_PRINT_PAGESIZE(3, "", "", "");
        // LODOP.SET_PRINT_PAGESIZE(3,printTemplateWidth,printTemplateHeight,"");

        //设置基本打印风格：
        // LODOP.SET_PRINT_STYLE("FontSize",14);

        //函数控制缩放：按整宽且不变形，即按整宽的同时，高度与之同比
        LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","Auto-Width");

        //打印 上部
        LODOP.ADD_PRINT_HTM(0,0,"100%","100%",document.getElementById("print_area_div_top").innerHTML);

        //打印 表单和下部
        var TopHeight = $("#print_area_div_top").height();
        LODOP.ADD_PRINT_HTM(TopHeight+5,0,"100%","100%",document.getElementById("print_area_div").innerHTML);

        //如果是 打印
        if (isPrint) {

            //监听 打印机返回 事件 返回逻辑真假结果（1或true代表真，false或0代表假）
            LODOP.On_Return = function(TaskID,Value){
                //如果打印成功
                if (Value > 0) {
                    var op = $("#PrintType").find("option:selected").html();
                    if (op == "发货单") {
                        //打印完成 发货单
                        printComplete("invoice", 1);

                    } else if (op == "出库单") {
                        //打印完成 出库单
                        printComplete("invoice", 2);

                    } else if (op == "入库单") {
                        //打印完成 入库单
                        printComplete("invoice", 3);
                    }
                } else {
                    showMessage("打印失败");
                }
            };
            LODOP.PRINT();     //直接打印

        //否则是 预览
        } else {
            //设置 各种样式的 打印预览窗口： 2-适宽,  1-显示(下方)工具栏, 1-是 直接打印
            LODOP.SET_PREVIEW_WINDOW(1, 1, 1, "", "", ".开始打印");

            //打印后自动关闭预览窗口
            LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);

            //监听 打印机返回 事件  返回打印次数，大于0表示打印成功的次数；
            LODOP.On_Return = function(TaskID,Value){
                //如果打印成功
                if (Value > 0) {
                    var op = $("#PrintType").find("option:selected").html();
                    if (op == "发货单") {
                        //打印完成 发货单
                        printComplete("invoice", 1);

                    } else if (op == "出库单") {
                        //打印完成 出库单
                        printComplete("invoice", 2);

                    } else if (op == "入库单") {
                        //打印完成 入库单
                        printComplete("invoice", 3);
                    }
                } else {}
            };

            LODOP.PREVIEW();    //打印预览
            return;
        }
    }

}

/**
 * 12.点击左侧'打印'按钮,打印快递单
 * @author 赵滨
 */
function StartPrintCarrier() {
    //获取快递模版
    var printTemplateCarrier = $("#PrintTemplateCarrier").find("option:selected").data("printTemplateCarrier");

    //如果没有模版
    if (printTemplateCarrier == undefined) {
        showMessage("没有面单模版,请您先预设面单模版");
        return;
    }

    //如果是 菜鸟面单
    if (printTemplateCarrier.templateType == 9) {
        showMessage("、菜鸟面单的一系列操作，待完成");

    //否则是 普通快递单
    } else if (printTemplateCarrier.templateType == 7 ||
               printTemplateCarrier.templateType == 8) {
        //获取打印信息,然后打印
        StartPrintCarrierLodop(printTemplateCarrier.id, $("#PrinterCarrier").find("option:selected").html(), true);
    }
}

/**
 * 12.1.获取打印信息,然后打印
 * @author 赵滨
 */
function StartPrintCarrierLodop(printTemplateId, PrinterName, isPrint) {
    //定义订单id
    var orderInfoArray = [];

    //获取 订单集合
    var listOrderInfo = $("#orderPrint_table tr");

    //遍历
    for (var i = 0; i < listOrderInfo.length; i++) {
        //如果 存在
        if (listOrderInfo.eq(i).data("orderInfo") != null) {
            //添加到订单id中
            orderInfoArray.push(listOrderInfo.eq(i).data("orderInfo").id);

            //获取 快递单号
            var trackingNum = listOrderInfo.eq(1).find("input[type='text']").val();

            //如果没有输入内容
            if (trackingNum == "") {
                showMessage("请在运单号内输入正整数");
                return;

                //如果输入不是数字
            } else if (isNaN(trackingNum)) {
                showMessage("请在运单号内输入正整数");
                return;

                //如果输入不是整数
            } else if (!/^\d+$/.test(trackingNum)) {
                showMessage("请在运单号内输入正整数");
                return;

                //如果快递单号过大
            } else if (trackingNum.toString().length > 20) {
                showMessage("请在运单号内输入正整数");
                return;
            }
        }
    }

    //发送打印请求
    $.ajax({
        url : "/printTemplate/printTemplate.do",
        type : "post",
        traditional : true,
        data : {
            "orderInfoId" : orderInfoArray,
            "printTemplateId" : printTemplateId,
            "authorized" : 1,
            "ownerId" : 1,
            "operatorId" : 1
        },
        dataType : "json",

        success : function(result) {
            //获取集合
            var returnMap = result.data;

            //打印
            returnMapPrintCarrier(returnMap, PrinterName, isPrint);

        },
        error : function() {
            showMessage("打印失败");
        }
    });
}

/**
 * 12.2.打印
 * @returns
 * @author 赵滨
 */
function returnMapPrintCarrier(returnMap, PrinterName, isPrint) {
    //1 获取基本参数 和 加载打印框

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

    //2 打印每一条订单信息

    //如果是 预览
    if (!isPrint) {
        // 创建对象
        var img = new Image();

        // 改变图片的src
        img.src = printTemplate.templateImage;

        //给图片 加 宽高
        LODOP.ADD_PRINT_IMAGE(0,0,img.width,img.height,
            "<img border='0' src='"+printTemplate.templateImage+"' />");
        //LODOP.ADD_PRINT_IMAGE(200,150,260,150,"C:/test.jpg"); //本地图片

        //设置 最新对象 仅用来预览：
        LODOP.SET_PRINT_STYLEA(0,"PreviewOnly",true);
    }

    //遍历订单,因为可能是同时打印多条订单
    for (var i = 0; i < orderInfoList.length; i++) {

        //获取发件人地区
        var consignorRegion = getRegionNameByCode(shopInfoList[i].regionId);

        //获取收件人地区
        var consigneeRegion = getRegionNameByCode(buyerInfoList[i].regionId);

        //遍历字段坐标
        for (var j = 0; j < fieldCoordinateList.length; j++) {
            //获取对象名称
            var fieldCoordinateName = fieldCoordinateList[j].fieldName;

            //定义要打印的内容
            var printContext = null;

            //获取列表的json对象
            var json = eval(templateJson.express);

            //遍历列表
            for (var k = 0; k < json.length; k++) {
                //如果 名称 成功匹配
                if (json[k].EnglishName == fieldCoordinateName) {
                    //赋值
                    printContext = eval(json[k].ParameterName);
                }
            }

            //获取打印点坐标
            xCoordinate = fieldCoordinateList[j].xCoordinate;
            yCoordinate = fieldCoordinateList[j].yCoordinate;

            //获取打印宽高
            xLength = fieldCoordinateList[j].xLength;
            yLength = fieldCoordinateList[j].yLength;

            //在矩形框内打印内容：
            LODOP.ADD_PRINT_TEXT(yCoordinate,xCoordinate,xLength,yLength,printContext);
        }

        //3 设置打印相关信息

        //按序号或名称指定打印机，选定后禁止手工重选；
        LODOP.SET_PRINTER_INDEX(PrinterName);

        //设置基本打印风格：
        LODOP.SET_PRINT_STYLE("FontSize",14);

        //如果是 打印
        if (isPrint) {

            //监听 打印机返回 事件 返回逻辑真假结果（1或true代表真，false或0代表假）
            LODOP.On_Return = function(TaskID,Value){
                //如果打印成功
                if (Value > 0) {
                    //打印完成 电子面单
                    printComplete("express");
                } else {
                    showMessage("打印失败");
                }
            };
            LODOP.PRINT();     //直接打印

        //否则是 预览
        } else {

            //设置 各种样式的 打印预览窗口： 2-适宽,  1-下方 工具栏, 1-是 直接打印
            LODOP.SET_PREVIEW_WINDOW(1, 1, 1, "", "", ".开始打印");

            //打印后自动关闭预览窗口
            LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);

            //监听 打印机返回 事件  返回打印次数，大于0表示打印成功的次数；
            LODOP.On_Return = function(TaskID,Value){
                //如果打印成功
                if (Value > 0) {
                    //打印完成 电子面单
                    printComplete("express");
                } else {}
            };

            LODOP.PREVIEW();    //打印预览
            return;
        }
    }
}

/**
 * 12.3 打印完成
 */
function printComplete(template, type) {

    //定义打印完成的订单数组
    var orderInfoIds = [];

    //遍历订单
    $("#orderPrint_table tr").not(":first").each(function (i) {
        //加入数组
        orderInfoIds.push($(this).data("orderInfo").id);
    })

    //发送请求
    $.ajax({
        url : "/erp/printComplete.do",
        type : "post",
        traditional : true,
        data : {
            "orderInfoIds" : orderInfoIds,
            "template" : template
        },
        dataType : "json",
        success : function(result) {
            //获取更新条数
            var row = result.data;

            if (row > 0) {
                showMessage("打印成功");

                //获取每条订单框
                var ul = $("#checkbox_form").find("input[name='id']:checked").parent().parent();

                //遍历订单
                for (var i = 0; i < ul.length; i++) {

                    //清空提示
                    ul.eq(i).children("li").eq(8).find("br").remove();
                    ul.eq(i).children("li").eq(8).find("span").remove();

                    //如果是电子面单
                    if (template == "express") {
                        //插入提示
                        ul.eq(i).children("li").eq(8).append('<br><span style="color: #c625c3;">已打印电子面单</span>');

                        //如果是发货单
                    } else if (template == "invoice") {
                        if (type == 1) {
                            //插入提示
                            ul.eq(i).children("li").eq(8).append('<br><span style="color: #c625c3;">已打印发货单</span>');
                        } else if (type == 2) {
                            //插入提示
                            ul.eq(i).children("li").eq(8).append('<br><span style="color: #c625c3;">已打印出库单</span>');
                        } else if (type == 3) {
                            //插入提示
                            ul.eq(i).children("li").eq(8).append('<br><span style="color: #c625c3;">已打印入库单</span>');
                        }
                    }
                }
            } else {
                showMessage("打印失败");
            }
        },
        error : function() {
            showMessage("打印失败");
        }
    });

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
        showMessage("请输入正整数");

        //如果输入不是整数
    } else if (!/^\d+$/.test(trackingNum)) {
        ok = false;
        showMessage("请输入正整数");

        //如果快递单号过大
    } else if (trackingNum.toString().length > 20) {
        ok = false;
        showMessage("快递单号过长");
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
                showMessage("快递单号保存失败!");
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
        showMessage("请输入正整数");

        //如果输入不是整数
    } else if (!/^\d+$/.test(trackingNum)) {
        ok = false;
        showMessage("请输入正整数");

        //如果快递单号过大
    } else if (trackingNum.toString().length > 20) {
        ok = false;
        showMessage("快递单号过长");
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
                showMessage("快递单号保存失败!");
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
    $("#amendInvoiceTemplate_printChoice").children().remove();

    //拼接块
    var op = '<option value="-1">默认打印机</option>';

    //加入选项中
    $("#PrinterCarrier").append(op);

    //拼接块
    op = '<option value="-1">默认打印机</option>';

    //加入选项中
    $("#Printer").append(op);

    //遍历数组
    for (var i = 0; i < PRINTER_COUNT; i++) {
        op = '';
        op += '<option value="'+i+'">';
        op += LODOP.GET_PRINTER_NAME(i);	//打印机名称
        op += '</option>';

        //转换对象
        var $op = $(op);

        //加入选项中
        $("#PrinterCarrier").append($op);

        //转换对象
        $op = $(op);

        //加入选项中
        $("#Printer").append($op);
    }
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
        showMessage("请选择您需要打印的订单");
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
        showMessage("请选择快递公司相同的订单");
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
            showMessage("获取订单信息失败!");
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

