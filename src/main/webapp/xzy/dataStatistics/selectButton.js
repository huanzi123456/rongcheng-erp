//1.获取店铺id	
function getShopId(){
	var shopId = $("#dynamicShop").find("option:selected").val();
	return shopId;
}
//2.获得平台id	  
function getPlatformId(){
	var platformId = $("#dynamicPlatform").find("option:selected").val();
	return platformId;
}
//3.获取商品编码
function getCode(){
	return $("#shopEncode").val(); 
}
//4.获得订单原始状态	  
function getOrderState(){
	var orderState = $("#flinput").find("option:selected").val();
	return orderState;
}
//5.订单创建时间day
function getDay(){
	day = $(this).val();
	$("#xzy_select").find("input").val("");
	$("#xzy_selects").find("input").val("");
	pay="";
	$(".payday").removeClass("selected");
	$(".createDay").removeClass("selected");
	$(this).addClass("selected");
}
//6.订单创建时间time1(第一个日历选框)
function getTime1(){
	$("#xzy_selects").find("input").val("");
	day="";pay="";
	$(".payday").removeClass("selected");
	$(".createDay").removeClass("selected");
}
//7.订单创建时间time2(第二个日历选框)
function getTime2(){
	$("#xzy_selects").find("input").val("");
	day="";pay="";
	$(".payday").removeClass("selected");
	$(".createDay").removeClass("selected");
}
//8.订单付款时间pay
function getPay(){
	pay = $(this).val();
	$("#xzy_select").find("input").val("");
	$("#xzy_selects").find("input").val("");
	day="";
	$(".createDay").removeClass("selected");
	$(".payday").removeClass("selected");
	$(this).addClass("selected");
}
//9.订单付款时间time3(第一个日历选框)
function getTime3(){
	$("#xzy_select").find("input").val("");
	day="";pay="";
	$(".payday").removeClass("selected");
	$(".createDay").removeClass("selected");
}
//10.订单付款时间time4(第二个日历选框)
function getTime4(){
	$("#xzy_select").find("input").val("");
	day="";pay="";
	$(".payday").removeClass("selected");
	$(".createDay").removeClass("selected");
}
//关联查询
function selectButton(){
	var shopId = getShopId();                            //店铺id
	var platformId = getPlatformId();                    //平台id	
	var code = getCode();                                //商品编码
	var orderStatus = getOrderState();                    //获得订单原始状态	
	time1 = $("#xzy_select").find("input:first").val();  //创建时间行的第一个日历选框
	time2 = $("#xzy_select").find("input:last").val();   //创建时间行的第二个日历选框
	time3 = $("#xzy_selects").find("input:first").val(); //付款时间行的第一个日历选框
	time4 = $("#xzy_selects").find("input:last").val();  //付款时间行的第二个日历选框
	$.ajax({
		url:"/find/relationQuery.do",
		type:"post",
		data:{"day":day,"pay":pay,"shopId":shopId,"platformId":platformId,"time1":time1,"time2":time2,
			"time3":time3,"time4":time4,"orderStatus":orderStatus,"code":code,"ownerId":currentOwnerId},		  
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var map = result.data; 
				//订单统计报表
				var totalOrder = map.tatalOrders;//订单总数 				    
				var totalDeparture = map.outOrders;//已发货的订单数
				var waitOrders = map.waitOrders;//待发货的订单数
				var orderClosed = map.seleOrderClosed;//已关闭的订单数
				var volumeOfTrade = map.volumeOfTrade;//成交额
				    volumeOfTrade = volumeOfTrade.toFixed(2);
				var unitPrice = map.clientPrice;//客单价
				    unitPrice = unitPrice.toFixed(2);
				//商品统计报表
				var totalCount = map.totalCount;//商品总数 				     				    
				var outGood = map.outGood;//已发货的商品数 				    
				var waitGood = map.waitGood;//待发货的商品数
				var totalPrice = map.prices;//商品总价值
				    totalPrice = totalPrice.toFixed(2);  				      				    
				var avgPrice = map.avgPrice;//商品平均价
				    avgPrice = avgPrice.toFixed(2); 				    
				var costPrice = map.costPrice;//商品总成本
				    costPrice = costPrice.toFixed(2);
				//销售前20
				var strs = map.strs;
				//将返回的数据写入统计报表中
				$("#totalOrder").html(totalOrder);//订单总数
			    $("#totalDeparture").html(totalDeparture);//已发货的订单数
			    $("#surplusDeparture").html(waitOrders);//待发货的订单数
			    $("#closeOrder").html(orderClosed);//已关闭的订单数
			    $("#totalPrice").html(volumeOfTrade);//成交额
			    $("#unitPrice").html(unitPrice);//客单价
			    
			    $("#s1").html(totalCount);//商品总数
			    $("#s2").html(outGood);//已发货的商品数 
			    $("#s3").html(waitGood);//待发货的商品数
			    $("#s4").html(totalPrice);//商品总价值
			    $("#s5").html(avgPrice);//商品平均价
			    $("#s6").html(costPrice);//商品总成本
			    
			    var sell = strs.split(",");//销售前20    
			    for(var i=0;i<sell.length;i++){
			    	if(i<10){
			    		$("#sells").find("tr:eq(1)").find("td:eq("+i+")").html(sell[i]);
			    	}else{
			    		$("#sells").find("tr:eq(3)").find("td:eq("+(i-10)+")").html(sell[i]);
			    	}				    	
			    }
			}
		},
		error:function(){}
	});
}