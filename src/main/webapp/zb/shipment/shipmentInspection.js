//0.全局变量，上次单号
var oldNum = null;
//0.全局遍历，是否允许发货
var isDeliverGoods = false;

$(function() {
	//1.单号框内光标移除事件  根据单号进行验货
	$("#oddNumbers").blur(oddNumbers)
	
	//2.单号框内回车事件  根据单号进行验货
	$("#oddNumbers").keydown(enter);

	//3.点击“发货” 修改订单状态
	$("#deliverGoods").click(deliverGoods);
})

/**
 * 3.点击“发货” 修改订单状态
 * @returns
 * @author 赵滨
 */
function deliverGoods() {
	//如果不允许发货
	if (isDeliverGoods == false) {
		if ($("#promptInformation").html() == "提示信息：已发货") {
			showMessage("该订单已经发货，不可以重复发货");
			return;
		}
		showMessage("请选择正确的订单发货");
		return;
	}
	
	//发送请求
	$.ajax({
		url : "/shipment/shipmentInspectionDeliverGoods.do",
		type : "post",
		data : {
			"oddNumbers" : oldNum
		},
		dataType : "json",
		success : function(result) {
			showMessage("发货成功");
			$("#promptInformation").html("提示信息：已发货");
			//不允许发货
			isDeliverGoods = false;
		},
		error : function() {
			showMessage("发生错误");
		}
	});
}

/**
 * 2.单号框内回车事件
 * @param e
 * @returns
 * @author 赵滨
 */
function enter(e) {
	//如果是回车按键
	if(e.keyCode==13){
		//光标移除
		$("#oddNumbers").blur();
	}
}

/**
 * 1.根据单号进行验货
 * @returns
 * @author 赵滨
 */
function oddNumbers() {
	//获取扫描运单号或系统单号
	var num = $("#oddNumbers").val();

	//如果没有输入内容
	if (num.trim() == "") {
		//清空页面
		clearDeliverGoods();
		return;
	}
	//赋值
	oldNum = num;

	//发送请求
	$.ajax({
		url : "/shipment/shipmentInspectionOddNumbers.do",
		type : "post",
		data : {
			"oddNumbers" : num
		},
		dataType : "json",
		success : function(result) {
			if(result.state==0){
				//获取集合map
				var map = result.data;
				
				//1.获取订单信息
				var orderInfo = map.orderInfo;
				//如果为空
                if (orderInfo == null) {
                    showMessage("没有找到订单");
                    //清空页面
                    clearDeliverGoods();
                    return;
                }

				//2.获取订单下的商品
				var listMapItem = map.listMapItem;
				
				//3.获取订单验货信息(订单信息和买家信息)
				var mapOrder = map.mapOrder;
				
				//如果订单 是 正常
				if (	orderInfo.orderStatus == 4 || 
						orderInfo.orderStatus == 5 || 
						orderInfo.orderStatus == 6	) {
					//允许发货
					isDeliverGoods = true;
					//创建发货页面
					createDeliverGoods(orderInfo, listMapItem, mapOrder);
					return;
				} else if (orderInfo.orderStatus == 1) {
				    showMessage("该订单未付款，无法发货")
                } else if (orderInfo.orderStatus == 2) {
                    showMessage("该订单未审核，无法发货")
                } else if (orderInfo.orderStatus == 3) {
                    showMessage("该订单未打印，无法发货")
                } else if (orderInfo.orderStatus == 7) {
                    showMessage("该订单已发货")
                } else if (orderInfo.orderStatus == 8) {
                    showMessage("该订单物流运输异常")
                } else if (orderInfo.orderStatus == 9) {
                    showMessage("该订单已签收")
                } else if (orderInfo.orderStatus == 10) {
                    showMessage("该订单已完成")
                }
			}else if(result.state==5){
                console.log(result.message);
                showMessage(result.message);
			}
            //清空页面
            clearDeliverGoods();
		},
		error : function() {
			showMessage("订单扫描错误");
		}
	});
}

/**
 * 1.1.创建订单信息
 * @param orderInfo
 * @returns
 * @author 赵滨
 */
function createDeliverGoods(orderInfo, listMapItem, mapOrder) {
	//获取买方留言
	var buyerWord = orderInfo.buyerWord;
	//获取卖方备注
	var sellerWord = orderInfo.sellerWord;
	
	//如果买方没有留言
	if (buyerWord == null || buyerWord == "") {
		//赋值
		$(".leave_word").html("【无留言】");
	} else {
		//赋值
		$(".leave_word").html("【买方留言】"+buyerWord);
	}
	
	//如果卖方没有备注
	if (sellerWord == null || sellerWord == "") {
		//赋值
		$(".remark").html("【无备注】");
	} else {
		//赋值
		$(".remark").html("【卖方备注】"+sellerWord);
	}
	
	//如果存在发货时间，那么属性为发货
	if (orderInfo.orderDeparture != null) {
		//提示
		$("#promptInformation").html("提示信息：已发货");
		//不允许发货
		isDeliverGoods = false;
	} else {
		//提示
		$("#promptInformation").html("提示信息：未发货");
	}
	
	//清空商品内容
	$("#itemTable").children().remove();
	
	//创建添加元素
	var tr = '';
	//分支一：头部
	tr += '<tr style="height: 50px">';
	tr += '<th>序号</th>';
	tr += '<th>商品编码</th>';
	tr += '<th width="140">商品名称</th>';
	tr += '<th>商品规格</th>'; 
	tr += '<th>单位</th>';  
	tr += '<th width="140">备注</th>';
	tr += '<th>数量</th>';
	tr += '<th>待验货量</th>';
	tr += '<th>校验量</th>';
	tr += '</tr>';
	//追加
	$("#itemTable").append(tr);
	
	//分支三：中部
	for (var i = 0; i < listMapItem.length; i++) {
		//清空元素
		tr = '';
		tr += '<tr>';
		tr += '<td>'+(i+1)+'</td>';			//序号
		tr += '<td>'+listMapItem[i].erpItemNum+'</td>';		//商品编码
		tr += '<td>';
		tr += '<p style="width:140px;">'+listMapItem[i].name+'</p>';	//商品名称
		tr += '</td>';
		tr += '<td>'+listMapItem[i].color+' '+listMapItem[i].size+'</td>';	//商品规格
		tr += '<td> </td>';		//单位
		tr += '<td>';
		tr += '<p style="width:140px;"> </p>';		//备注
		tr += '</td>';
		tr += '<td>'+listMapItem[i].quantity+'</td>';	//数量
		tr += '<td> </td>';		//待验货量
		tr += '<td> </td>';		//校验量
		tr += '</tr>';
		//追加
		$("#itemTable").append(tr);
	}
	
	//清空元素
	tr = '';
	//如果没有底部信息
	if (mapOrder == null) {
		//分支三：底部
		tr += '<tr>';
		tr += '<td colspan="9">';
		tr += '<div class="information">';//mapOrder
		tr += '<span  style="margin-left:50px">没有找到收货人信息</span>';
		tr += '</div>';
		tr += '</td>';
		tr += '</tr>';
	} else {
		//分支三：底部
		tr += '<tr>';
		tr += '<td colspan="9">';
		tr += '<div class="information">';//mapOrder
		tr += '<span>'+mapOrder.consigneeName+'&nbsp;</span>';
		tr += '<span>'+mapOrder.consigneeTel+'/'+mapOrder.consigneeMobile+'&nbsp;</span>';
		//如果 地址id 为 0
		if (mapOrder.regionId == 0) {
			tr += '<span>'+"全国 "+mapOrder.userAddress+'</span>';//地址
		} else if (mapOrder.regionId > 0) {
			//调取citys.js 获取全国地址表
			var citys = getRegionNameByCode(mapOrder.regionId);
			tr += '<span>'+citys.province+" "+citys.city+" "+citys.area+" "+mapOrder.userAddress+'</span>';//地址
		}
		tr += '</div>';
		tr += '</td>';
		tr += '</tr>';
	}
	//追加
	$("#itemTable").append(tr);
}

/**
 * 1.2.清空发货页面
 * @returns
 * @author 赵滨
 */
function clearDeliverGoods() {
	//清空留言
	$(".leave_word").html("");
	//清空备注
	$(".remark").html("");
	//清空商品框
	$("#itemTable").children().remove();
	//清空提示
	$("#promptInformation").html("");
	//不允许发货
	isDeliverGoods = false;
}






















