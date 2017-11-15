/**
 * 线上商品对应关系页面
 */
var currentOwnerId;             //当前用户的ownerid
var now_page;//线上商品对应关系主页中分页查询的   当前页
var input_change_page;//线上商品对应关系"换"操作中查询   当前页
$(function(){
	/**
     * 1.线上商品对应关系的分页查询
     */
	commonPage(1);
	/**
	 * 2."换"  操作中  "选择已有"  弹出框页面的分页查询
	 */
	$("#xzy_input_change").click(popoverPages(1));
	/**
	 * 3."选择已有" 弹出框页面的关键字查询
	 */
	$(".xzy_onBlur").blur(function(){popoverPages(1);});
	/**
	 * 4."选择已有" 弹出框页面的保存按钮
	 */
	$("#xzy_save").click(function(){
		var input_length = $("#xzy_limits tr").find("input[name='NumOne']").length;
		console.log("leng:"+input_length);
		//var boolean = $("#xzy_limits tr").find("input[name='NumOne']").prop("checked",true);
		//$('input:radio[name="sex"]:checked')
		for(var i=0;i<input_length;i++){
			var bool = $("#xzy_limits").find("input[name='NumOne']:eq("+i+")").prop("checked");
			if(bool){
				var input_i = $("#xzy_limits").find("input[name='NumOne']:eq("+i+")").parent();
				var common_id = input_i.next().text();//商品id
				console.log("id:"+common_id);
			}			
		}
		
	});
});
/**
 * 2."换"  操作中  "选择已有"  弹出框页面的分页查询
 * 3."选择已有" 弹出框页面的关键字查询
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
					var commonSpecification;                             //商品规格
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
			}
		},
		error:function(){"哎呀..页面不见了!"}
	});
}
/**
 * 1.线上商品对应关系的分页查询
 * @param page:当前页
 * @returns
 */
function commonPage(page){
	now_page = page;
	$("#xzy_limit").find("tr").remove();
	$.ajax({
		url:"/onLineCommodity/commonPage.do",
		type:"post",
		data:{"page":page},
		dataType:"json",
		async:false,
		success:function(result){
			if(result.status == 0){
				var pageSize = result.pageSize;                        //每页的记录数
				var maxPage = result.maxPage;                          //总的页数
				var datas = result.data;
				currentOwnerId = result.msg;
				var five_tr ;
				for(var i=0;i<datas.length;i++){
					//线上商品信息
					//var platformErpLinkId = datas[i].id;               //线上商品关联表的id
					//var platformShopId = datas[i].platformShopId;      //平台店铺id
					//var platformShopName = datas[i].platformShopName;  //平台店铺名称
					//var platformItemSku = datas[i].platformItemSku;    //平台(来源)商品编码
					//var platformItemName = datas[i].platformItemName;  //平台商品名称		
					var attr1 = datas[i].platformItemAttrvaluealias1;
					var attr2 = datas[i].platformItemAttrvaluealias2;
					var onlineSpecification;                           //线上商品规格
					if(attr2 == ""){
						onlineSpecification = attr1;
					}else{
						onlineSpecification = attr1+"*"+attr2;
					}
					//var onlineImg = datas[i].platformUserImg;          //线上商品图片
					//系统商品信息
		    		var list = datas[i].itemCommonInfo;	;
		    		//var itemCommonInfoId = list[0].id;                 //系统商品信息表的id
		    		//var name = list[0].name;	                       //系统商品名称	
		    		//var offlineImg = list[0].image1;                   //系统商品图片
		    		var size = list[0].size;
		    		var color = list[0].color;
		    		var offlineSpecification;                          //系统商品规格
					if(color == null){
						offlineSpecification = size;
					}else{
						offlineSpecification = size+"*"+color;
					}
					if(offlineSpecification == null){
						offlineSpecification = "未设置系统商品规格";
					}
				    var one_tr = '<tr>'+
			          '<td val='+datas[i].id+'>'+//线上商品关联表的id
			            '<input type="checkbox" name="id[]" value="1" class="check_coding" /><br>'+
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
			              '<img src="'+datas[i].platformUserImg+'" alt="" title="微店">'+//线上商品图片
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
			              '<img src="'+list[0].image1+'" alt="">'+//系统商品图片
			              '<div class="show_img_box">'+
			                '<img src="'+list[0].image1+'" alt="">'+//系统商品图片
			              '</div>'+
			            '</div>'+
			          '</td>'+
			          '<td >'+
			            '<div>'+
			             '<span class="span50">'+list[0].id+'</span>'+//系统商品信息表的id
			              '<span class="span50">'+offlineSpecification+'</span>'+//系统商品规格
			            '</div>'+
			            '<p>'+
			            list[0].name+//系统商品名称
			            '</p>'+
			          '</td>'+
			          '<td>'+
			            '<a href="javascript:;" class="button border-main single_huan" id="xzy_input_change"> 换</a>'+
			          '</td>'+
			        '</tr>'
			        five_tr +=  one_tr; 
				}
				var table_tr = '<tr>'+
		          '<th width="30"><input type="checkbox" class="xzy_checkAll"/>1</th>'+
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
		},
		error:function(){
			alert("不好了,页面飞走了");
		}
	});
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