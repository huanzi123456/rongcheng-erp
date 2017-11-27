/*
 * "换"弹出框的新建商品页面 
 */
var obj = new Object();
/**
 * 清空页面信息
 * @returns
 */
function emptyCommonInfo(){
	var commonNum = $(".management_content").find("input").length;
	for(var i=0;i<commonNum;i++){
		$(".management_content").find("input:eq("+i+")").val("");			
	}
	$(".commonClassification").find("option:first").prop("selected",true);//系统分类
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
	obj.commonCode  = $(".commonCode").val();//1.商品编码
	obj.commonCategory = $(".commonCategory").val();//2.商品类目
	obj.commonBrand = $(".commonBrand").val();//3.商品品牌
	obj.commonName = $(".commonName").val();//4.商品名称
	obj.commonShortened = $(".commonShortened").val();//5.商品简称
	obj.commonSpecification = $(".commonSpecification").val();//6.商品规格
	obj.commonColor =$(".commonColor").val();//7.商品颜色
	obj.commonSize = $(".commonSize").val();//8.商品尺码
	obj.commonNormalPrice = $(".commonNormalPrice").val();//9.商品价格
	obj.commonCostPrice = $(".commonCostPrice").val();//10.商品成本价
	obj.commonBarcode  = $(".commonCostPrice").val();//11.商品条码	
	obj.commonPack = $(".commonPack").val();//12.商品包装	
	obj.commonLang = $(".commonLang").val();//13.长
	obj.commonWidth = $(".commonWidth").val();//14.宽
	obj.commonHigh = $(".commonHigh").val();//15.高	
	obj.commonWeight = $(".commonWeight").val();//16.商品重量
	obj.commonUnit = $(".commonUnit").val();//17.商品单位	
	obj.commonBatch = $(".commonBatch").val();//18.商品批次	
	obj.commonBzq = $(".commonBzq").val();//19.保质期	
	obj.commonSeason = $(".commonSeason").val();//20.季节
	obj.commonStyleNum = $(".commonStyleNum").val();//21.款号	
	obj.commonClassification = $(".commonClassification").find("option:selected").text();//22.系统分类	
	obj.userDefined1 = $(".userDefined1").val();//23.自定义1
	obj.userDefined2 = $(".userDefined2").val();//24.自定义2
	obj.userDefined3 = $(".userDefined3").val();//25.自定义3	
	obj.comment = $(".comment").val();//26.备注	
	if($(".presell").prop("checked")){
		obj.presell = $(".presell").next().text();//27.预售
	}
	if($(".presells").prop("checked")){
		obj.presells = $(".presells").next().text();//28.代售
	}
	if($(".gift").prop("checked")){
		obj.gift = $(".gift").next().text();//29.赠品
	}
	return obj;
}
/**
 * 保存按钮
 * @returns
 */
function saveCommonInfo(){
	var objs = getInfo();
//    console.log("1商品编码:"+objs.commonCode);
//    console.log("2商品类目:"+objs.commonCategory);
//    console.log("3商品品牌:"+objs.commonBrand);
//    console.log("4商品名称:"+objs.commonName);
//    console.log("5商品简称:"+objs.commonShortened);
//    console.log("6商品规格:"+objs.commonSpecification);
//    console.log("7.商品颜色:"+objs.commonColor);
//    console.log("8.商品尺码:"+objs.commonSize);
//    console.log("9.商品价格:"+objs.commonNormalPrice);
//    console.log("10.商品成本价:"+objs.commonCostPrice);
//    console.log("11.商品条码:"+objs.commonBarcode);
//    console.log("12.商品包装:"+objs.commonPack);
//    console.log("13长:"+objs.commonLang);
//    console.log("14宽:"+objs.commonWidth);
//    console.log("15高:"+objs.commonHigh);
//    console.log("16.商品重量:"+objs.commonWeight);
//    console.log("17.商品单位	:"+objs.commonUnit);
//    console.log("18.商品批次:"+objs.commonBatch);
//    console.log("19.保质期:"+objs.commonBzq);
//    console.log("20.季节:"+objs.commonSeason);
//    console.log("21.款号	:"+objs.commonStyleNum);
//    console.log("22.系统分类:"+objs.commonClassification);
//    console.log("23.自定义1:"+objs.userDefined1);
//    console.log("24.自定义2:"+objs.userDefined2);
//    console.log("25.自定义3:"+objs.userDefined3);
//    console.log("26.备注:"+objs.comment);
//    console.log("27.预售:"+objs.presell);
//    console.log("28.代售:"+objs.presells);
//    console.log("29.赠品:"+objs.gift);
}