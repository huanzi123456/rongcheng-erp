var currentOwnerId;             //当前用户的ownerid
var now_page;                   //线上商品对应关系主页中分页查询的   当前页
var list;
var obj = new Array();
$(function(){
	//线上商品对应关系的分页查询
	commonPage(1);
	//"换" 弹出框
	$("#xzy_limit").on("click","#xzy_input_change",change_input);
	//在"换" 弹出框中:点击"选择已有"和"新建"按钮切换时
	$("#pagesOne").click(function(){//选择已有
		$(".xzy_onBlur").val("");
		popoverPages(1);
	});
	$("#pagesTwo").click(function(){//新建
		//系统分类
		setBoxInfo1();
	});
	//"换" 弹出框的 "选择已有" 页面的关键字查询
	$(".xzy_onBlur").blur(function(){popoverPages(1);});
	//线上商品对应关系页面的复选框
	$("#xzy_limit").on("click","#xzy_check",checkboxButton)
	//线上商品对应关系页面的"批量维护对应关系"按钮
	$("#xzy_maintainRelation").click(maintaimRelation);
	//线上商品对应关系页面的"批量维护对应关系"弹出框页面的系统商品弹出框的分页查询
	$(".commodity_relation_middle table").on("click",".search_commodity",secondPopbox);
	//线上商品对应关系页面的"批量维护对应关系"弹出框页面的系统商品弹出框页面的关键字查询,保存按钮
	$(".xzy_SecondOnBlur").blur(function(){secondPages(1);});
	//线上商品对应关系页面的"批量维护对应关系"弹出框页面的保存按钮
	$(".commodity_relation_bc").click(modify_commonInfo);
	//线上商品对应关系页面的"批量维护对应关系"弹出框页面的取消按钮
	$(".commodity_relation_middle table").on("click",".border-red",cancelButton);	
	//线上商品对应关系页面的店铺下拉选的加载店铺信息
	addOption();	
	//线上商品对应关系页面的查询按钮
	$(".icon-search").click(function(){commonPage(1);});
});
/**
 * 获取线上商品对应关系页面的店铺信息
 * @returns
 */
function getShopInfo(){
	var platformShopId = $("#xzy_select1").find("option:selected").val();
	return platformShopId;
}
/**
 * 获取线上商品对应关系页面的商品状态
 * @returns
 */
function getCommonState(){
	var commonState = $("#xzy_select0").find("option:selected").text();
	return commonState;
}
/**
 * 线上商品对应关系页面的店铺下拉选的加载
 * @returns
 */
function addOption(){
	$.ajax({
		url:"/onLineCommodity/addShopInfos.do",
		type:"post",
		data:{},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var datas = result.data;
				var options;
				for(var i=0;i<datas.length;i++){
					var one_option = '<option value="'+datas[i].platformShopId+'" id="xzy_onclick">'+datas[i].name+'</option>'
					options += one_option;
				}
				var select = ' <option value="" id="xzy_onclick">全部店铺</option>'+options;
				var $select = $(select);
				$("#xzy_select1").append($select);
			}
		},
		error:function(){
			alert("店铺加载失败...")
		}
	});
}
/**
 * 线上商品对应关系页面的"批量维护对应关系"弹出框页面的取消按钮
 * @returns
 */
function cancelButton(){	
	var tr_index = $(this).parent().parent().index();
	var $find_table = $(".commodity_relation_middle").find("table");
	$find_table.find("tr:eq("+tr_index+")").remove();
	var table_tr_length = $find_table.find("tr").length-1;
	var this_num = $(this).parent().parent().find("td:first").text();
	for(var i=this_num;i<=table_tr_length;i++){
		$find_table.find("tr:eq("+i+")").find("td:first").text(i);
	}
}
//线上商品对应关系页面的"批量维护对应关系"弹出框页面的保存按钮
function modify_commonInfo(){
	if(obj == ""){return;}
	$.ajax({
		url:"/onLineCommodity/modifyInfos.do",
		type:"post",
		cache : false,
	     traditional: true,
		data:{"currentOwnerId":currentOwnerId,"obj":obj},
		dataType:"json",		
		success:function(result){
			if(result.status == 0){	
				commonPage(now_page);
				obj.splice(0,obj.length);
			}
		},
		error:function(){
			alert("页面飞到瓜哇国去了....");
		}
	});	
}
/**
 * 线上商品对应关系页面的"批量维护对应关系"弹出框页面的系统商品弹出框
 */
function secondPopbox(){
	//弹出框页面
	$(".xzy_SecondOnBlur").val("");
	secondPages(1);
	//保存按钮
	var $this = $(this);
    $("#xzy_keeps").unbind("click").click(function(){
	   save_Secondinput($this);
    });
}
/**
 * 获取弹出框中被选中的线下商品信息
 * @returns
 */
function save_Secondinput($this){	
	var input_length = $("#tableLimits tr").find("input[name='NumOne']").length;
	var common_id;
	var common_name;
	var common_size;
	for(var i=0;i<input_length;i++){
		var bool = $("#tableLimits").find("input[name='NumOne']:eq("+i+")").prop("checked");
		if(bool){
			var input_i = $("#tableLimits").find("input[name='NumOne']:eq("+i+")").parent();			
			common_id = input_i.next().text();//系统商品信息表1的id
			common_name = input_i.next().next().text();//商品名称
			common_size = input_i.next().next().next().text();//商品规格
		}			
	}
	if(common_id != undefined){
		var linkid = $this.parent().parent().parent().find("td:first").attr("name");//线上商品关联表的id
		$this.parent().find("input").attr("value",common_id);//系统商品信息表的id(系统商品编码)
		$this.parent().parent().next().text(common_name);//系统商品名称
		$this.parent().parent().next().next().text(common_size);//系统商品规格
		var arrs = linkid+"-"+common_id;
		obj.push(arrs);
	}
}
/**
 * 线上商品对应关系页面的"批量维护对应关系"弹出框页面的系统商品弹出框的分页查询
 * @param page
 * @returns
 */
function secondPages(page){
	$("#tableLimits").find("tr").remove();
	var inputs = $(".xzy_SecondOnBlur").val();
	$.ajax({
		url:"/onLineCommodity/commonPages.do",
		type:"post",
		data:{"inputs":inputs,"page":page,"currentOwnerId":currentOwnerId},
		dataType:"json",				
		success:function(result){
			if(result.status == 0){
				var pageSize = result.pageSize;                        //每页的记录数
				var maxPage = result.maxPage;                          //总的页数
				var datas = result.data;
				var five_tr;
				for(var i=0;i<datas.length;i++){
					var list = datas[i].itemCommonInfo;
					var color = list[0].color;
					var size = list[0].size;
					var commonSpecification;                            //商品规格
					if(color == null){
						commonSpecification = size;
					}else{
						commonSpecification = size+color;
					}
					//$("#tableLimits").find("input[name='NumOne']:eq("+i+")").parent();
		    		var one_tr = '<tr>'+
		              '<td><input type="radio" name="NumOne"></td>'+
		              '<td><span class="span">'+list[0].id+'</span></td>'+//商品ID
		              '<td><span class="span">'+list[0].name+'</span></td>'+//商品名称
		              '<td><span class="span">'+commonSpecification+'</span></td>'+//商品规格
		              '<td>'+list[0].normalPrice+'</td>'+//商品原价
		              '<td>'+datas[i].unit+'</td>'+//计量单位
		            '</tr>';
		    		five_tr += one_tr;
				}
				var table_tr = '<tr>'+
	              '<th width="5">&nbsp;</th>'+
	              '<th width="19">商品编码</th>'+
	              '<th width="19">商品名称</th>'+
	              '<th width="19">商品规格</th>'+
	              '<th width="19">商品价格</th>'+
	              '<th width="19">单位</th>'+
	            '</tr>'+		            
	            five_tr+
	            '<tr><td colspan="10"><div class="pagelist" id="secondOnclick"></div></td></tr>';
				var $table_tr = $(table_tr);
				$("#tableLimits").append($table_tr);
				//动态添加页码
				pagination(maxPage,page,"secondOnclick","secondPages",{num:3});
				//添加 "换" 弹出框(点击保存按钮时,弹出框取消)
				common_box();
			}
		},
		error:function(){"哎呀..页面不见了!"}
	});
}
/**
 * 线上商品对应关系页面的"批量维护对应关系"按钮
 * @returns
 */
function maintaimRelation(){
	obj.splice(0,obj.length);
	$(".commodity_relation_middle").find("table").find("tr").remove();
	var five_tr;
	for(var i=0;i<list.length;i++){
		var arr = list[i].split("-");		
		var one_tr = 
		'<tr>'+
		  '<td name='+arr[0]+'>'+(i+1)+'<div class="dianpu"><img src="'+arr[1]+'" alt=""></div></td>'+//线上商品关联表的id  序号     线上商品图片
          '<td><p>'+arr[2]+'</p><span>'+arr[3]+'</span></td>'+//平台店铺名称   平台店铺id
          '<td>'+
            '<span class="span50">&nbsp;'+arr[4]+'</span>'+//平台(来源)商品编码
            '<span class="span50">'+arr[5]+'</span>'+//线上商品规格
            '<p>'+arr[6]+'</p>'+//平台商品名称
          '</td>'+
          '<td style="border-left: 1px dashed #999;">'+
            '<div class="spbm">'+
              '<input type="text" value="'+arr[7]+'">'+//系统商品信息表的id(系统商品编码)
              '<div class="fdjPng search_commodity"></div>'+
            '</div>'+
          '</td>'+
          '<td><p>'+arr[9]+'</p></td>'+//系统商品名称
          '<td><p>'+arr[8]+'</p></td>'+//系统商品规格
          '<td><a href="javascript:;" class="button border-red"> 取消</a></td>'+
        '</tr>';
		five_tr += one_tr; 
	}
	var table_tr = 
	'<tr>'+
      '<th width="50">序号</th>'+
      '<th>线上店铺</th>'+
      '<th width="240"><span class="span50">线上商品编码/名称</span><span class="span50">线上商品规格</span>'+
      '</th><th width="125">对应系统商品编码</th>'+
      '<th>对应系统商品名称</th>'+
      '<th>对应系统商品规格</th>'+
      '<th width="64">取消</th>'+
    '</tr>'+
    five_tr;
	var $table_tr = $(table_tr);
	$(".commodity_relation_middle").find("table").append($table_tr);
	common_box();	
}
/**
 * 线上商品对应关系页面的复选框
 * @returns
 */
function checkboxButton(){
	list = new Array();
	var value = $(this).attr("value");//value=0:全选;value=1:单选
	var num = $("#xzy_limit").find("input[name='id[]']").length;
	if(value == 0){//全选
		var ok = $(this).prop("checked");	
		if(ok){
			for(var i=0;i<num;i++){
				$("#xzy_limit").find("input[name='id[]']:eq("+i+")").prop("checked",true);
				var $input = $("#xzy_limit").find("input[name='id[]']:eq("+i+")").parent();
				var strInfo = get_info($input);
				list.push(strInfo);
			}
			$(this).parent().find("p").text(num);
		}else{
			for(var i=0;i<num;i++){
				$("#xzy_limit").find("input[name='id[]']:eq("+i+")").prop("checked",false);				
			}
			list.splice(0,list.length);
			$(this).parent().find("p").text(0);
		}
	}else if(value == 1){//单选
		var $input = $(this).parent();
		var strInfo = get_info($input);
		var ok = $(this).prop("checked");//选中状态
		if(ok){
			var nums = $(this).parent().parent().parent().find("tr:first").find("th:first").find("p").text();
			var num1=parseInt(nums);   
            var num2=parseInt(1);   
            var num3=num1+num2;  
			$(this).parent().parent().parent().find("tr:first").find("th:first").find("p").text(num3);
			list.push(strInfo);
			//判断全选下的每一个input,如果全部被选中,则将全选选中
			var boolean = true;			
			for(var i=0;i<num;i++){
				var sure = $("#xzy_limit").find("input[name='id[]']:eq("+i+")").prop("checked");
				var j=0;
				if(sure == false){
					boolean = false;
				}
			}
			if(boolean){
				$("#xzy_limit").find("input[value='0']").prop("checked",true);
			}
		}else{
			for(var i=0;i<list.length;i++){
				if(strInfo == list[i]){
					list.splice(i,1);
				}
			}
			$("#xzy_limit").find("input[value='0']").prop("checked",false);
			var nums = $(this).parent().parent().parent().find("tr:first").find("th:first").find("p").text();			
			var num1=parseInt(nums);   
            var num2=parseInt(1);   
            var num3=num1-num2;  
			$(this).parent().parent().parent().find("tr:first").find("th:first").find("p").text(num3);
		}
	}
}
/**
 * 获取"线上商品对应关系的分页查询"的商品及店铺信息
 * @param $input
 * @returns
 */
function get_info($input){
	//线上商品信息
	var linkId= $input.attr("val");//线上商品关联表的id
	var onlineImg =  $input.next().find("img:first").attr("src")//线上商品图片
	var platformShopName = $input.parent().find("td:eq(2)").find("p").text();//平台店铺名称
	var platformShopId = $input.parent().find("td:eq(2)").find("span").text();//平台店铺id
	var platformItemSku = $input.parent().find("td:eq(3)").find("div[class='wh']").text()//平台(来源)商品编码
	var onlineSpecification = $input.parent().find("td:eq(3)").find("span[class='span50']").text()//线上商品规格
	var platformItemName = $input.parent().find("td:eq(3)").find("p").text()//平台商品名称
	//系统商品信息
	var systemId = $input.parent().find("td:eq(5)").find("span:first").text();//系统商品信息表的id
	var systemGe = $input.parent().find("td:eq(5)").find("span:eq(1)").text();//系统商品规格
	var systemName = $input.parent().find("td:eq(5)").find("p").text();//系统商品名称
	var strInfo = linkId+"-"+onlineImg+"-"+platformShopName+"-"+platformShopId+"-"+
	platformItemSku+"-"+onlineSpecification+"-"+platformItemName+"-"+
	systemId+"-"+systemGe+"-"+systemName;
	return strInfo;
}
/**
 * "换" 操作按钮
 */
function change_input(){	
	//线上商品关联表的id
	var platformErpLinkId = $(this).parent().parent().find("td:first").attr("val");	
	var span1 = $(".single_updating_list_btn_box").find("span:first").attr("class");//选择已有
	var span2 = $(".single_updating_list_btn_box").find("span:last").attr("class");//新建
	if(span1 == "active"){//选择已有页面				
		//分页查询
		popoverPages(1);		
	}else if(span2 == "active"){//新建页面	
		//系统分类		
		setBoxInfo1();
	}
	//清空关键字的输入框内容
	$(".xzy_onBlur").val("");
	//2."换" 弹出框的 "选择已有" 页面的保存按钮
	$("#xzy_save").unbind("click").click(function(){
		save_input(platformErpLinkId);
	});
	//"换" 弹出框的 "新建" 页面的保存按钮
	$("#xzy_saves").unbind("click").click(function(){
		saveCommonInfo(platformErpLinkId);
		//弹出框隐藏
		$(".single_updating_box").css("display","none");
	});
}
/**
 * "换" 弹出框的 "选择已有" 页面的保存按钮
 * @param platformErpLinkId:线上商品关联表的id
 * @returns
 */
function save_input(platformErpLinkId){	
	var input_length = $("#xzy_limits tr").find("input[name='NumOne']").length;
	var common_id;
	for(var i=0;i<input_length;i++){
		var bool = $("#xzy_limits").find("input[name='NumOne']:eq("+i+")").prop("checked");
		if(bool){
			var input_i = $("#xzy_limits").find("input[name='NumOne']:eq("+i+")").parent();
			//系统商品信息表1的id
			common_id = input_i.next().text();
		}			
	}
	//单选框的状态为选中时,才会修改信息
	if(common_id != undefined){
		$.ajax({
			url:"/onLineCommodity/modifyInfo.do",
			type:"post",
			data:{"currentOwnerId":currentOwnerId,"common_id":common_id,"platformErpLinkId":platformErpLinkId},
			dataType:"json",
			success:function(result){
				if(result.status == 0){					
					commonPage(now_page);
				}
			},
			error:function(){
				alert("页面飞到瓜哇国去了....");
			}
		});
	}
}
/**
 * "换" 操作中 "选择已有" 弹出框页面的分页查询
 * "换" 操作中 "选择已有" 弹出框页面的关键字查询
 * @param page
 * @returns 
 */
function popoverPages(page){
	$("#xzy_limits").find("tr").remove();
	var inputs = $(".xzy_onBlur").val();
	$.ajax({
		url:"/onLineCommodity/commonPages.do",
		type:"post",
		data:{"inputs":inputs,"page":page,"currentOwnerId":currentOwnerId},
		dataType:"json",				
		success:function(result){
			if(result.status == 0){
				var pageSize = result.pageSize;                        //每页的记录数
				var maxPage = result.maxPage;                          //总的页数
				var datas = result.data;
				var five_tr;
				for(var i=0;i<datas.length;i++){
					var list = datas[i].itemCommonInfo;
					var color = list[0].color;
					var size = list[0].size;
					var commonSpecification;                            //商品规格
					if(color == null){
						commonSpecification = size;
					}else{
						commonSpecification = size+color;
					}
		    		var one_tr = '<tr>'+
		              '<td><input type="radio" name="NumOne"></td>'+
		              '<td><span class="span">'+list[0].id+'</span></td>'+//商品ID
		              '<td><span class="span">'+list[0].name+'</span></td>'+//商品名称
		              '<td><span class="span">'+commonSpecification+'</span></td>'+//商品规格
		              '<td>'+list[0].normalPrice+'</td>'+//商品原价
		              '<td>'+datas[i].unit+'</td>'+//计量单位
		            '</tr>';
		    		five_tr += one_tr;
				}
				var table_tr = '<tr>'+
	              '<th width="5">&nbsp;</th>'+
	              '<th width="19">商品编码</th>'+
	              '<th width="19">商品名称</th>'+
	              '<th width="19">商品规格</th>'+
	              '<th width="19">商品价格</th>'+
	              '<th width="19">单位</th>'+
	            '</tr>'+		            
	            five_tr+
	            '<tr><td colspan="10"><div class="pagelist" id="novels"></div></td></tr>';
				var $table_tr = $(table_tr);
				$("#xzy_limits").append($table_tr);
				//动态添加页码
				pagination(maxPage,page,"novels","popoverPages",{num:2});
				//添加 "换" 弹出框(点击保存按钮时,弹出框取消)
				pop_up_box();
			}
		},
		error:function(){"哎呀..页面不见了!"}
	});
}
/**
 *  "换"弹出框的"新建"页面的保存按钮 
 * @returns
 */
function saveCommonInfo(platformErpLinkId){
	var param = doGetItemParam();
	//线上商品关联表的id	
	param.image2 = platformErpLinkId;
	param.ownerId = currentOwnerId;	
	var url="/onLineCommodity/newButton.do";
	//对页面信息进行判断	
	if(!param.erpItemNum || !param.name){
		showMessage("商品编号或名称不能为空");
	}else{
		console.log("进入到新建商品");
		$.post(url,param,function(result){
			if(result.status == 0){
				console.log("成功");
				commonPage(now_page);
			}
		})
	}
}
/**
 * 线上商品对应关系的分页查询
 * @param page:当前页
 * @returns
 */
function commonPage(page){
	/////////////////////////////////////////////
	//平台店铺信息
    var platformShopId = getShopInfo();
    //商品状态
    var commonState = getCommonState();
    if(commonState == "在售商品"){
    	commonState = 1;
    }else if(commonState == "下架商品"){
    	commonState = 2;
    }else if(commonState == "全部"){
    	commonState = "";
    }
    if(platformShopId == undefined){
    	platformShopId = "";
    }
    //线上商品编号或名称
	var onlineInfo = $("#xzy_online").val();
	//系统商品编号或名称
	var systemInof = $("#xzy_system").val();
	/////////////////////////////////////////////
	list="";
	now_page = page;
	$("#xzy_limit").find("tr").remove();
	$.ajax({
		url:"/onLineCommodity/commonPage.do",
		type:"post",
		data:{"commonState":commonState,"platformShopId":platformShopId,"onlineInfo":onlineInfo,"systemInof":systemInof,"page":page},
		dataType:"json",		
		success:function(result){
			homePage(result,page);	
		},
		error:function(){
			alert("不好了,页面飞走了");
		}
	});
}
function homePage(result,page){
	if(result.status == 0){
		var pageSize = result.pageSize;                        //每页的记录数
		var maxPage = result.maxPage;                          //总的页数
		var datas = result.data;
		currentOwnerId = result.msg;
		var five_tr ;
		for(var i=0;i<datas.length;i++){
			//线上商品信息	
			var attr1 = datas[i].platformItemAttrvaluealias1;
			var attr2 = datas[i].platformItemAttrvaluealias2;
			var platformId = datas[i].platformId;
			var imgdp;
			if(platformId == 3){//京东
				imgdp = "/images/dp.png";
			}else {
				imgdp = "/images/dp.png";
			}
			var onlineSpecification;                           //线上商品规格
			if(attr2 == ""){
				onlineSpecification = attr1;
			}else{
				onlineSpecification = attr1+"*"+attr2;
			}
			//系统商品信息
    		var list = datas[i].itemCommonInfo;
    		var sysImgs = list[0].image1
    		if(sysImgs == null){
    			sysImgs = "/images/wh.png"; 
    		}
    		var sysId = list[0].id;
    		if(sysId == null){
    			sysId = ""; 
    		}
    		var sysName = list[0].name;
    		if(sysName == null){
    			sysName = "";
    		}
    		var size = list[0].size;
    		var color = list[0].color;
    		var offlineSpecification;                          //系统商品规格
			if(color == null){
				offlineSpecification = size;
			}else{
				offlineSpecification = size+"*"+color;
			}
			if(offlineSpecification == null){
				offlineSpecification = "";
			}
		    var one_tr = '<tr>'+
	          '<td val='+datas[i].platformErpLinkId+'>'+//线上商品关联表的id
	            '<input type="checkbox" name="id[]" value="1" class="check_coding" id="xzy_check"/><br>'+
	            ((page-1)*pageSize+(i+1))+
	          '</td>'+
	          '<td>'+
	            '<div class="sjx" title="线上商品和系统商品编码不一致"></div>'+
	            '<div class="img_box">'+
	              '<img src="'+datas[i].platformUserImg+'" alt="">'+//线上商品图片
	              '<div class="show_img_box">'+
	                '<img src="'+datas[i].platformUserImg+'" alt="">'+//线上商品图片
	              '</div>'+
	            '</div>'+
	            '<div class="dianpu">'+
	              '<img src="'+imgdp+'" alt="" title="微店">'+//线上商品图片
	            '</div>'+
	          '</td>'+
	          '<td>'+
	            '<p>'+
	            datas[i].platformShopName+//平台店铺名称
	            '</p>'+
	            '<span>'+datas[i].platformShopId+'</span>'+//平台店铺id
	          '</td>'+
	          '<td>'+
	            '<div>'+
	              '<div class="wh" title="线上商品的商家编码没有编写">'+datas[i].platformItemSku+'</div>'+//平台(来源)商品编码
	              '<span class="span50">'+onlineSpecification+'</span>'+//线上商品规格
	            '</div>'+
	            '<p>'+
	            datas[i].platformItemName+//平台商品名称
	            '</p>'+
	          '</td>'+  
	          '<td style="border-left: 1px dashed #999;">'+
	            '<div class="img_box">'+
	              '<img src="'+sysImgs+'" alt="">'+//系统商品图片
	              '<div class="show_img_box">'+
	                '<img src="'+sysImgs+'" alt="">'+//系统商品图片
	              '</div>'+
	            '</div>'+
	          '</td>'+
	          '<td >'+
	            '<div>'+
	             '<span class="span50">'+sysId+'</span>'+//系统商品信息表的id
	              '<span class="span50">'+offlineSpecification+'</span>'+//系统商品规格
	            '</div>'+
	            '<p>'+
	            sysName+//系统商品名称
	            '</p>'+
	          '</td>'+
	          '<td>'+
	            '<a href="javascript:;" class="button border-main single_huan" id="xzy_input_change"> 换</a>'+
	          '</td>'+
	        '</tr>'
	        five_tr +=  one_tr; 
		}
		var table_tr = '<tr>'+
          '<th width="30"><input type="checkbox" value="0" id="xzy_check"/><p>0</p></th>'+
          '<th width="100">&nbsp;</th>'+
          '<th>线上店铺</th>'+       
          '<th><span class="span50">线上商品编码</span><span class="span50">线上商品规格</span></th>'+
          '<th width="50">&nbsp;</th>'+
          '<th><span class="span50">对应系统商品编码</span><span class="span50">对应系统商品规格</span></th>'+
          '<th>操作</th>'+       
        '</tr>'+five_tr+
        '<tr><td colspan="10"><div class="pagelist" id="pageClick"></div></td></tr>';
		//追加tr
        var $table = $(table_tr);
 		$("#xzy_limit").append($table);         		
 		//添加 "换" 弹出框
 		pop_up_box();
 		//动态添加页码
		pagination(maxPage,page,"pageClick","commonPage",{num:1});
		
	}
}
/**
 * 添加 "换" 弹出框
 * @returns
 */
function pop_up_box(){	
	var single_updating_box=$(".single_updating_box"),
		single_updating_bc=$(".single_updating_bc"),
		single_new_bc=$(".single_new_bc"),
		single_new_qx=$(".single_new_qx"),
		single_huan=$(".single_huan"),
		single_updating_delbtn=$(".single_updating_delbtn"),
		single_updating_list=$(".single_updating_list"),
		single_updating_list_btn=$(".single_updating_list_btn_box span"),
		num=1;
	single_huan.click(function(){
		single_updating_box.css("display","block");
	});
	single_updating_bc.click(function(){
		single_updating_box.css("display","none");
	});
	single_new_bc.click(function(){
		single_updating_box.css("display","none");
	});
	single_new_qx.click(function(){
		single_updating_box.css("display","none");
	});
	single_updating_delbtn.click(function(){
		single_updating_box.css("display","none");
	});
}
/**
 *线上商品对应关系页面 批量处理商品对应关系按钮弹出框里边的系统商品按钮
 * @returns
 */
function common_box(){
	var search_commodity=$(".search_commodity"),
		hs_code_bc=$(".hs_code_bc"),
		hs_code_box=$(".hs_code_box"),
		hs_code_delbtn=$(".hs_code_delbtn");

		search_commodity.click(function(){
			hs_code_box.css("display","block");
		});
		hs_code_bc.click(function(){
			hs_code_box.css("display","none");
		});
		hs_code_delbtn.click(function(){
			hs_code_box.css("display","none");
		})
}