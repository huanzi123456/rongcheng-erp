/**
 * 数据统计页面的js
 */
var currentOwnerId;
var day="";
var time1="";
var time2="";
var pay="";
var time3="";
var time4="";
$(function(){
	//数据统计页面的添加店铺信息和平台信息 
	addInfos();
	//订单创建时间day
	$("#xzy_select .createDay").click(getDay);
	//订单创建时间time1(第一个日历选框)
	$("#xzy_select").find("input:first").click(getTime1); 
	//订单创建时间time2(第二个日历选框)
	$("#xzy_select").find("input:last").click(getTime2);
	//订单付款时间pay
	$("#xzy_selects .payday").click(getPay);
	//订单付款时间time3(第一个日历选框)
	$("#xzy_selects").find("input:first").click(getTime3);
	//订单付款时间time4(第二个日历选框)
	$("#xzy_selects").find("input:last").click(getTime4);
	//关联查询
	$("#find").click(selectButton);
});