var now_page;             //当前页
$(function(){
	/*
     * 1.分页查询用户
     */
	commonPage(1);
	/*
	 * 2."换"操作中"选择已有"弹出框页面的分页查询
	 */
	$(".input_change").click(popoverPages(1));
	/*
	 * 全选
	 */
	$("#xzy_limit").on("click",".xzy_checkAll",function(){
		var ok = $(this).prop("checked");//选中状态
		var num = $("#xzy_limit").find("input[name='id[]']").length;
		if(ok){
			for(var i=0;i<num;i++){
				$("#xzy_limit").find("input[name='id[]']:eq("+i+")").prop("checked",true);
			}
		}else{
			for(var i=0;i<num;i++){
				$("#xzy_limit").find("input[name='id[]']:eq("+i+")").prop("checked",false);
			}
		}
	});
});
/**
 * 2."换"操作中"选择已有"弹出框页面的分页查询
 * @param page
 * @returns
 */
function popoverPages(page){
	$("#xzy_limits").find("tr").remove();
	$.ajax({
		url:"/onLineCommodity/commonPages.do",
		type:"post",
		data:{"page":page},
		dataType:"json",				
		success:function(result){
			if(result.status == 0){
				var pageSize = result.pageSize;                        //每页的记录数
				var maxPage = result.maxPage;                          //总的页数
				var datas = result.data;
				var five_tr;
				for(var i=0;i<datas.length;i++){
					var unit = datas[i].unit;                           //计量单位
					if(unit == null){
						unit = "小米加步枪";
					}
					var list = datas[i].itemCommonInfo;
					var id = list[0].id;                                 //商品ID
					var name = list[0].name;                             //商品名称
					var color = list[0].color;
					var size = list[0].size;
					var commonSpecification;                             //商品规格
					if(color == null){
						commonSpecification = size;
					}else{
						commonSpecification = size+color;
					}
					var normal_price = list[0].normalPrice;              //商品原价
//					console.log("计量单位:"+unit);
//					console.log("商品ID:"+id);
//		    		console.log("商品名称:"+name);
//		    		console.log("商品颜色:"+color);
//		    		console.log("商品尺码:"+size);
//		    		console.log("商品原价:"+normal_price);
		    		var one_tr = '<tr>'+
		              '<td>'+
		                '<input type="radio" name="NumOne">'+
		              '</td>'+
		              '<td>'+
		                '<span class="span">'+id+'</span>'+
		              '</td>'+
		              '<td>'+
		                '<span class="span">'+name+'</span>'+
		              '</td>'+
		              '<td>'+
		                '<span class="span">'+commonSpecification+'</span>'+
		              '</td>'+
		              '<td>'+
		              normal_price+
		              '</td>'+
		              '<td>'+
		              unit+
		              '</td>'+
		            '</tr>';
		    		five_tr += one_tr;
				}
				var head_tr = '<tr>'+
	              '<th width="5">&nbsp;</th>'+
	              '<th width="19">'+
	                '商品编码'+
	              '</th>'+
	              '<th width="19">'+
	                '商品名称'+
	              '</th>'+
	              '<th width="19">'+
	                '商品规格'+
	              '</th>'+
	              '<th width="19">'+
	                '商品价格'+
	              '</th>'+
	              '<th width="19">'+
	                '单位'+
	              '</th>'+
	            '</tr>'+		            
	            five_tr;
				var tableTr;
				if(maxPage == 1){
					tableTr = head_tr;
				}else{
					tableTr = head_tr + '<tr>'+
		              '<td colspan="10"><div class="pagelist" id="novels"></div></td>'+
		              '</tr>';
				}
				var $tableTr = $(tableTr);
				$("#xzy_limits").append($tableTr);
				//动态添加页码
				var data = new Object();
				data.size = pageSize;
				pagination(maxPage,page,"novels","popoverPages",data);
				$("#pageClick").unbind('click');
			}
		},
		error:function(){"哎呀..页面不见了!"}
	});
}
/**
 * 1.页面查询
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
				var five_tr ;
				for(var i=0;i<datas.length;i++){
					var platformErpLinkId = datas[i].id;               //线上商品关联表的id
					var platformShopId = datas[i].platformShopId;      //平台店铺id
					var platformShopName = datas[i].platformShopName;  //店铺名称
					var platformItemSku = datas[i].platformItemSku;    //平台(来源)商品编码
					var platformItemName = datas[i].platformItemName;  //平台商品名称		
					var attr1 = datas[i].platformItemAttrvaluealias1;
					var attr2 = datas[i].platformItemAttrvaluealias2;
					var onlineSpecification;                           //线上商品规格
					if(attr2 == ""){
						onlineSpecification = attr1;
					}else{
						onlineSpecification = attr1+"*"+attr2;
					}
					var onlineImg = datas[i].platformUserImg;          //线上商品图片
		    		var list = datas[i].commonInfo;
		    		var itemCommonInfoId = list[0].id;                 //系统商品信息表的id
		    		var name = list[0].name;	                       //系统商品名称	
		    		if(name==null){
		    			name="未设置系统商品名";
		    		}
		    		var offlineImg = list[0].image1;                   //系统商品图片
		    		if(offlineImg == null){
		    			offlineImg=onlineImg;
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
						offlineSpecification = "未设置系统商品规格";
					}
//					console.log("线上商品关联表id:"+platformErpLinkId);
//					console.log("平台店铺id:"+platformShopId);
//		    		console.log("店铺名称:"+platformShopName);
//		    		console.log("平台(来源)商品编码:"+platformItemSku);
//		    		console.log("平台商品名称:"+platformItemName);
//		    		console.log("线上商品规格:"+onlineSpecification);
//		    		console.log("线上商品图片:"+onlineImg);
//		    		console.log("系统商品信息表的id:"+itemCommonInfoId);
//		    		console.log("系统商品名称:"+name);
//					console.log("系统商品图片:"+offlineImg);
//		    		console.log("系统商品规格:"+offlineSpecification);
				    var one_tr = '<tr>'+
			          '<td>'+
			            '<input type="checkbox" name="id[]" value="1" class="check_coding" /><br>'+
			            ((page-1)*pageSize+(i+1))+
			          '</td>'+
			          '<td>'+
			            '<div class="sjx" title="线上商品和系统商品编码不一致"></div>'+
			            '<div class="img_box">'+
			              '<img src="'+onlineImg+'" alt="">'+
			              '<div class="show_img_box">'+
			                '<img src="'+onlineImg+'" alt="">'+
			              '</div>'+
			            '</div>'+
			            '<div class="dianpu">'+
			              '<img src="'+onlineImg+'" alt="" title="微店">'+
			            '</div>'+
			          '</td>'+
			          '<td>'+
			            '<p>'+
			            platformShopName+
			            '</p>'+
			            '<span>'+platformShopId+'</span>'+
			          '</td>'+
			          '<td>'+
			            '<div>'+
			              '<div class="wh" title="线上商品的商家编码没有编写">'+platformItemSku+'</div>'+
			              '<span class="span50">'+onlineSpecification+'</span>'+
			            '</div>'+
			            '<p>'+
			            platformItemName+
			            '</p>'+
			          '</td>'+  
			          '<td style="border-left: 1px dashed #999;">'+
			            '<div class="img_box">'+
			              '<img src="'+offlineImg+'" alt="">'+
			              '<div class="show_img_box">'+
			                '<img src="../images/400.png" alt="">'+
			              '</div>'+
			            '</div>'+
			          '</td>'+
			          '<td >'+
			            '<div>'+
			             '<span class="span50">'+itemCommonInfoId+'</span>'+
			              '<span class="span50">'+offlineSpecification+'</span>'+
			            '</div>'+
			            '<p>'+
			            name+
			            '</p>'+
			          '</td>'+
			          '<td>'+
			            '<a href="javascript:;" class="button border-main single_huan input_change"> 换</a>'+
			          '</td>'+
			        '</tr>'
			        five_tr +=  one_tr; 
				}
				var body_tr = '<tr>'+
		          '<th width="30"><input type="checkbox" class="xzy_checkAll"/>1</th>'+
		          '<th width="100">&nbsp;</th>'+
		          '<th>线上店铺</th>'+       
		          '<th>'+
		            '<span class="span50">线上商品编码</span>'+
		            '<span class="span50">线上商品规格</span>'+
		          '</th>'+
		          '<th width="50">&nbsp;</th>'+
		          '<th>'+
		            '<span class="span50">对应系统商品编码</span>'+
		            '<span class="span50">对应系统商品规格</span>'+
		          '</th>'+
		          '<th>操作</th>'+       
		        '</tr>'+
		        five_tr;		       
		        /*
		         * 如果总页数等于1,则不加下面的页码
		         */
		        var table_tr;
		        if(maxPage==1){
		        	table_tr = body_tr;
		        }else{
		        	table_tr = body_tr +
		        	 '<tr>'+
			          '<td colspan="10"><div class="pagelist" id="pageClick"></div></td>'+
			        '</tr>';
		        }  
		        var $table = $(table_tr);
         		$("#xzy_limit").append($table);
				/*
				* 线上商品对应关系页面 换按钮弹出框
				* single_updating_box 弹出框
				* single_updating_bc  弹出框的已有框保存按钮
				* single_updating_list  弹出框的选项卡列表
				* single_updating_list_btn_box   弹出框的选项卡按钮盒子
				* single_new_bc  弹出框新建框保存按钮
				* single_new_qx  弹出框新建框取消按钮
				* single_huan  打开弹出框按钮
				* single_updating_delbtn   弹出框右上角叉号
				*/
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
         		//动态添加页码
				var data = new Object();
				data.size = pageSize;
				pagination(maxPage,page,"pageClick","commonPage",data);
			}	
		},
		error:function(){
			alert("不好了,页面飞走了");
		}
	});
}