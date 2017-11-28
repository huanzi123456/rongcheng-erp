/*
 * "换"弹出框的新建商品页面 
 */
/**
 * 保存按钮
 * @returns
 */
function saveCommonInfo(platformErpLinkId){	
	var param = getInfo();
//	console.log("1商品编码:"+param.erpItemNum);
//    console.log("2商品类目:"+param.category);
//    console.log("3商品品牌:"+param.brand);
//    console.log("4商品名称:"+param.name);
//    console.log("5商品简称:"+param.shortName);
//    console.log("6商品规格:"+param.spec);
//    console.log("7.商品颜色:"+param.color);
//    console.log("8.商品尺码:"+param.size);
//    console.log("9.商品价格:"+param.normalPrice);
//    console.log("10.商品成本价:"+param.costPrice);
//    console.log("11.商品条码:"+param.barCode);
//    console.log("12.商品包装:"+param.packageCondition);
//    console.log("13长:"+param.length);
//    console.log("14宽:"+param.width);
//    console.log("15高:"+param.height);
//    console.log("16.商品重量:"+param.weight);
//    console.log("17.商品单位	:"+param.unit);
//    console.log("18.商品批次:"+param.batchCode);
//    console.log("19.保质期:"+param.expireDate);
//    //console.log("20.季节:"+param.commonSeason);
//    console.log("21.款号	:"+param.styleCode);
//    //console.log("22.系统分类:"+param.commonClassification);
//    console.log("23.自定义1:"+param.reserved1);
//    console.log("24.自定义2:"+param.reserved2);
//    console.log("25.自定义3:"+param.reserved3);
//    console.log("26.备注:"+param.note);
//    console.log("27.预售:"+param.presell);
//    console.log("28.代售:"+param.commissionSell);
//    console.log("29.赠品:"+param.gift)		
	//线上商品关联表的id
	param.platformErpLinkId = platformErpLinkId;
	//对页面信息进行判断	
	if(!param.commonCode || !param.commonName){
		showMessage("商品编号或名称不能为空");
	}else{
		$.post(url,param,function(result){
			if(!result.state){
				
			}
		})
	}
}
/**
 * 清空页面信息
 * @returns
 */
function emptyCommonInfo(){
	var commonNum = $(".management_content").find("input").length;
	for(var i=0;i<commonNum;i++){
		$(".management_content").find("input:eq("+i+")").val("");			
	}
	//$(".commonClassification").find("option:first").prop("selected",true);//系统分类
	$(".comment").val("");//备注	
	$(".presell").prop("checked",false);//预售
	$(".presells").prop("checked",false);//代售
	$(".gift").prop("checked",false);//赠品
}
/**
 * 获取页面的信息
 * @returns
 */
function getInfo(){
	//清空并处理数组
	//categoryOne = [];
	//doGetBoxCategory();
	var presell,commissionSell,gift;
	var $presell = $(".management_content").find("input[name='presell']");
	if($presell.prop("checked")){
		presell = $presell.next().text();//27.预售
	}
	var $commissionSell = $(".management_content").find("input[name='commissionSell']");
	if($commissionSell.prop("checked")){
		commissionSell = $commissionSell.next().text();//28.代售
	}
	var $gift = $(".management_content").find("input[name='gift']");
	if($gift.prop("checked")){
		gift = $gift.next().text();//29.赠品
	}
	var param = {erpItemNum:$(".commonCode").val(),//1.商品编码
			     category:$(".commonCategory").val(),//2.商品类目
			     brand:$(".commonBrand").val(),//3.商品品牌
			     name:$(".commonName").val(),//4.商品名称
			     shortName:$(".commonShortened").val(),//5.商品简称
			     spec:$(".commonSpecification").val(),//6.商品规格
			     color:$(".commonColor").val(),//7.商品颜色
			     size:$(".commonSize").val(),//8.商品尺码
			     normalPrice:$(".commonNormalPrice").val(),//9.商品价格
			     costPrice:$(".commonCostPrice").val(),//10.商品成本价
			     barCode:$(".commonCostPrice").val(),//11.商品条码
			     packageCondition:$(".commonPack").val(),//12.商品包装	
			     length:$(".commonLang").val(),//13.长
			     width:$(".commonWidth").val(),//14.宽
			     height:$(".commonHigh").val(),//15.高	
			     weight:$(".commonWeight").val(),//16.商品重量
			     unit:$(".commonUnit").val(),//17.商品单位	
			     batchCode:$(".commonBatch").val(),//18.商品批次	
			     expireDate:$(".commonBzq").val(),//19.保质期	
			     //commonSeason:$(".commonSeason").val(),//20.季节?
			     styleCode:$(".commonStyleNum").val(),//21.款号
			     //insertCategory:insertCategory,//22.系统分类
				 //deleteCategory:deleteCategory,
				 reserved1:$(".userDefined1").val(),//23.自定义1
				 reserved2:$(".userDefined2").val(),//24.自定义2
				 reserved3:$(".userDefined3").val(),//25.自定义3
				 note:$(".comment").val(),//26.备注	
				 presell:presell,//27.预售
				 commissionSell:commissionSell,//28.代售
	             gift:gift,//29.赠品
	}
	return param;
}