$(function(){	
	var shopId;//店铺id
	var platformId;//平台id
	var day;//订单创建时间   	     
	var pay;//订单付款时间
	var orderStatus;//原始订单状态
	//页面加载时发送请求:查询店铺信息,平台信息
	$.ajax({
		url:"/find/shopAndPlatform.do",	    		                                      
		type:"post",
		data:{"dual":1},
		dateType:"json",
		success:function(result){
			if(result.status == 0){
				var id = result.msg;//当前用户的id
				var owner_id = result.data;//owner_id				  
				var shop = result.list;//获取店铺信息(id,name)
				var platform = result.list1;//获取返回的平台信息(id,name)
				$("#searchInfo").data("id",id);
				$("#searchInfo").data("owner_id",owner_id);
				//动态增加店铺下拉选
				for(var i=0;i<shop.length;i++){
					var shops='<option value='+shop[i].id+'>'+shop[i].name+'</option>';
					var $shop = $(shops);
					
					$("#dynamicShop").append($shop);
				}	
				//获取店铺id				
				  $("#dynamicShop").click(function(){
					  shopId = $(this).val();
				  });
				//动态增加平台信息的下拉选
				for(var i=0;i<platform.length;i++){
					var shops='<option value='+platform[i].id+'>'+platform[i].name+'</option>';
					var $shop = $(shops);
					$("#dynamicPlatform").append($shop);
				}
				 //获得平台id	    				  
				$("#dynamicPlatform").click(function(){
					 platformId = document.getElementById("dynamicPlatform").value;		  
				});
			}
		}
    });	
	 //获取创建时间的天数(1,3,7,30)并清空付款时间的天数和日历中的日期
	  $("#today1").click(function(){
  	  day = 1;
  	  pay=null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    });
    $("#today3").click(function(){
  	  day = 3;
  	  pay = null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    });
    $("#today7").click(function(){
  	  day = 7;
  	  pay = null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    });
    $("#today30").click(function(){
  	  day=30;
  	  pay =null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    }); 
    //获取订单付款时间的天数(1,3,7,30)并清空创建时间的天数和日历中的日期
    $("#payday1").click(function(){
  	  pay = 1;
  	  day=null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    });
    $("#payday3").click(function(){
  	  pay =3;
  	  day=null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    });
    $("#payday7").click(function(){
  	  pay = 7;
  	  day=null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    });
    $("#payday30").click(function(){
  	  pay = 30;
  	  day=null;
  	  $("#date1").val("");
  	  $("#date2").val("");
  	  $("#date3").val("");
  	  $("#date4").val("");
    }); 
    //日期选择  选date1时将date3清空
    $("#date1").click(function(){
  	  $("#date3").val("");
    })
    //日期选择  选date2时将date4清空
    $("#date2").click(function(){
  	  $("#date4").val("");
    })
    //日期选择  选date3时将date1清空
    $("#date3").click(function(){
  	  $("#date1").val("");
    })
    //日期选择  选date4时将date2清空
    $("#date4").click(function(){
  	  $("#date2").val("");
    })
    //原始订单状态flinput
    $("#flinput").click(function(){
    	orderStatus = $(this).find("option:selected").val();
    });
	$("#find").click(function(){
		var id = $("#searchInfo").data("id");//当前用户的id
		var owner_id = $("#searchInfo").data("owner_id");//用户的主账户id
		var time1 = $("#date1").val();//创建时间行的第一个日历选框
		var time2 = $("#date2").val();//创建时间行的第二个日历选框
		var time3 = $("#date3").val();//付款时间行的第一个日历选框
		var time4 = $("#date4").val();//付款时间行的第二个日历选框
		var code = $("#shopEncode").val();//商品编码		
		//console.log("day1:"+day+","+"pay1:"+pay+","+"shopid1:"+shopId+","+"platformid1:"+platformId+","+
				 // "time1:"+time1+","+"time2:"+time2+","+"time3:"+time3+","+"time4:"+time4+","+
				  //"id:"+id+","+"owner_id::"+owner_id+","+"orderStatus:"+orderStatus+","+"code:"+code);
		$.ajax({
			url:"/data/orderInfo.do",
			type:"post",
			data:{"day":day,"pay":pay,"shopId":shopId,"platformId":platformId,"time1":time1,"time2":time2,
				"time3":time3,"time4":time4,"orderStatus":orderStatus,"code":code,"owner_id":owner_id},		  
			dataType:"json",
			success:function(result){
			    //发送成功
				if(result.status == 0){    					  
					var map = result.map; 
					//订单统计报表
  				    var totalOrder = map.totalOrder;//订单总数 				    
  				    var totalDeparture = map.outGoods;//已发货的订单数
  				    var surplusDeparture = map.waitGoods;//待发货的订单数
  				    var closeOrder = map.closed;//已关闭的订单数
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
  				    $("#surplusDeparture").html(surplusDeparture);//待发货的订单数
  				    $("#closeOrder").html(closeOrder);//已关闭的订单数
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
  				    //查询结束时清空部分数据
  				    shopId=null;platformId=null;day=null;pay=null;orderStatus=null;
			     }
			}
		});		
	})
});