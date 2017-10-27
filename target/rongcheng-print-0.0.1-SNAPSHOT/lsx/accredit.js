//全局变量 最大页数
var max_page=1;
//全局变量 当前页数
var now_page=1;
var storeInfo_userAddress;
var storeInfo_userAddress1;
var storeInfo_userAddress2;
var regionCode;
var regionId;
$(function(){
	//bug添加店铺时候出现.页面没刷新，自动添数据
	loadShopInfo(1);
	//监听修改事件
	$("#shopInfo_table").on("click",".accredit_add_amend1",modifyAccredit);
	$("#shopInfo_table").on("click",".accredit_add_amend1",loadModifyShopInfo);//加载单个修改数据
	$(".accredit_add_amend").click(fun);
	//修改确定按钮的单击事件
	$("#shopInfo_table").on("click",".shopInfo_status0",TAccount);
	
	 //地区下拉（无街道）
    $('#addrDiv').citys({
        required:false,
        nodata:'disabled',
        onChange:function(data){
            var text = data['direct']?'(直辖市)':'';
            $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
            storeInfo_userAddress=(data['province']+","+data['city']+","+data['area']); 
//            console.log(storeInfo_userAddress);	
        }
    });
    
});

function fun(){
	var $s=$(".accredit_add_amend_x").attr("id",1);
	$s.click(addStoreInformation);
}
function modifyAccredit(){
	var $s1=$(".accredit_add_amend_x").attr("id",2);
	$s1.click(modifyStoreInformation);
	$(".shade_accredit").css("display","block");
	//获取tr对象
	var $tr = $(this).parent().parent();
	//获取id
	var id = $tr.data("id");
	addCookie("id", id, 1);
}
function addStoreInformation(){
	var storeInfo_name=$("#storeInfo_name").val();
	var storeInfo_sellerAccount=$("#storeInfo_sellerAccount").val();
	var storeInfo_contactName=$("#storeInfo_contactName").val();
	var storeInfo_contactTel=$("#storeInfo_contactTel").val();
	var ok=true;
	if(storeInfo_name==""){
		$("#storeInfo_name_ms").html("店铺名称为空");
		ok=false;
	}
	if(storeInfo_sellerAccount==""){
		$("#storeInfo_sellerAccount_ms").html("用户名为空");
		ok=false;
	}
	if(storeInfo_contactName==""){
		$("#storeInfo_contactName_ms").html("姓名为空");
		ok=false;
	}
	if(storeInfo_contactTel==""){
		$("#storeInfo_contactTel_ms").html("电话为空");
		ok=false;
	}
	if(storeInfo_userAddress==undefined){
		$("#storeInfo_userAddress_ms").html("地址为空");
		ok=false;
	}
	//String name,String sellerAccount,String contactName,String contactTel,String userAddress
	if(ok){
		$.ajax({
			url :"add_storeInformation.do",
			type : "post",
			data : {"name":storeInfo_name,
			"sellerAccount":storeInfo_sellerAccount,
			"contactName":storeInfo_contactName,
			"contactTel":storeInfo_contactTel,
			"userAddress":storeInfo_userAddress},
			dataType : "json",
			success : function(result) {
				if (result.state == 0) {
					alert("添加成功!");
				    	window.location.href = path+"/accredit.do";	
				}
			},
			error : function() {
				alert("添加失败!");
			}
		});
	}
}
function loadShopInfo(page) {
	if(page <= 0 || page > max_page){return;}
	now_page=page;

	$.ajax({
		url : "loadList_shopInfo.do",
		type : "post",
		data : {
			"page" : page,
		},
		dataType : "json",
		success : function(result) {
			max_page = result.message;
			var list=result.data;
			reload(list);
		},
		error : function() {
			alert("加载失败!!!");
		}
	});
	}
	function reload(list) {
	var shopInfoTable = $("#shopInfo_table");
	shopInfoTable.empty();
	var sa="";
	sa+='<tr>';
	sa+='<th width="100">ID</th>';
	sa+='<th>店铺名称</th>';
	sa+='<th>平台会员名</th>';
	sa+='<th>店铺类型</th>';
	sa+='<th>店铺发货人</th>';
	sa+='<th>店铺发货联系电话</th>';
	sa+='<th>店铺发货地址</th>';
	sa+='<th>授权至</th>';
	sa+='<th width="300">操作</th> ';
	sa+='<tr>';
	$("#shopInfo_table").append(sa);
	for(var i=0;i<list.length;i++){
		var ShopInfo = list[i];
		var id = ShopInfo.id;
		var name = ShopInfo.name;
		var sellerAccount = ShopInfo.sellerAccount;
		var type = ShopInfo.type;
		var contactName = ShopInfo.contactName;
		var contactTel = ShopInfo.contactTel;
		var userAddress = ShopInfo.userAddress;
		var authorityDueDate = ShopInfo.authorityDueDate;
		var str="";
		str+='<tr class="tr1">';
		str+='<td><input type="checkbox" name="id[]" value="1" class="check_coding" />';
		str+=id;
		str+='</td>';
		str+='<td>';
		str+=name;
		str+='</td>';
		str+='<td>';
		str+=sellerAccount;
		str+='</td>';
		str+='<td>';
		str+=type;
		str+='</td>';
		str+='<td>';
		str+=contactName;
		str+='</td>';
		str+='<td>';
		str+=contactTel;
		str+='</td>';
		str+='<td>';
		str+=userAddress;
		str+='</td>';
		str+='<td>';
		str+=authorityDueDate;
		str+='</td>';
		str+='<td>';
		str+='<button type="submit" class="button"><span class="icon-edit"></span> 授权</button>';
		str+='<button type="submit" class="button border-main accredit_add_amend1"><span class="icon-plus"></span>修改</button>';
		str+='<button type="submit" class="button border-red shopInfo_status0"><span class="icon-trash-o"></span>停用</button>';
		str+='</td>';
		str+='</tr>';
		//转jquery对象
		var $tr=$(str);
		//把id加到 这条数据上
		$tr.data("id",id);
//		console.log($tr.data("id"));
		//最后加到table上
		$("#shopInfo_table").append($tr);
	}

	/////页码////////////
	var trs="";
	trs += '<tr id = "page_tr">';
	trs += '<td colspan="10"><div class="pagelist"><a class="current" onclick="loadShopInfo(1)" href="javascript:;">首页</a><a class="bookpage" onclick="loadShopInfo('+(now_page-1)+')" href="javascript:;">上一页</a>';
	if(max_page>5){
		if(now_page<=3){
			for(var i=1;i<4;i++){
				if(i==now_page){
					trs += '<span class="bookpage" onclick="loadShopInfo('+i+')">'+i+'</span>';
				}else{
					trs += '<a class="bookpage" onclick="loadShopInfo('+i+')" href="javascript:;">'+i+'</a>';
				}
			}
			trs += '<a class="bookpage" onclick="loadShopInfo(4)" href="javascript:;">4</a><a class = "bookpage" onclick="loadShopInfo(5)" href="javascript:;">5</a>...';
		}else if(now_page>=4 && now_page<=max_page-3){
			trs += '...<a class="bookpage" onclick="loadShopInfo('+(now_page-2)+')" href="javascript:;">'+(now_page-2)+'</a><a class="bookpage" onclick="loadShopInfo('+(now_page-1)+')" href="javascript:;">'+(now_page-1)+'</a>';
			trs += '<span class="bookpage">'+now_page+'</span>';
			trs += '<a class = "bookpage" onclick="loadShopInfo('+(now_page+1)+')" href="javascript:;">'+(now_page+1)+'</a><a class = "bookpage" onclick="loadShopInfo('+(now_page+2)+')" href="javascript:;">'+(now_page+2)+'</a>...';
		}else if(now_page>max_page-3){
			trs += '...<a class="bookpage" onclick="loadShopInfo('+(max_page-4)+')" href="javascript:;">'+(max_page-4)+'</a><a class="bookpage" onclick="loadShopInfo('+(max_page-3)+')" href="javascript:;">'+(max_page-3)+'</a>';
			for(var i=max_page-2;i<=max_page;i++){
				if(i==now_page){
					trs += '<span class="bookpage" onclick="loadShopInfo('+i+')">'+i+'</span>';
				}else{
					trs += '<a class="bookpage" onclick="loadShopInfo('+i+')" href="javascript:;">'+i+'</a>';
				}
			}
		}
	}else{
		var i = 1;
		while (i <= max_page) {
			if(i==now_page){
				trs += '<span class="bookpage" onclick="loadShopInfo('+i+')">'+i+'</span>';
			}else{
				trs += '<a class = "bookpage" onclick="loadShopInfo('+i+')" href="javascript:;">'+i+'</a>';
			}
			i++;
		}
	}
	trs += '<a class="bookpage" onclick="loadShopInfo('+(now_page+1)+')">下一页</a><a class="bookpage" onclick="loadShopInfo('+max_page+')">尾页</a></div></td>';
	trs += '</tr>';
	$("#shopInfo_table").append(trs);
	//////////////////////////
	$(".bookpage:contains('"+now_page+"')").addClass("current");
	}
	
	function modifyStoreInformation(){//单个修改
			var shopInfoId = getCookie("id");
			var storeInfo_name=$("#storeInfo_name").val();
			var storeInfo_sellerAccount=$("#storeInfo_sellerAccount").val();
			var storeInfo_contactName=$("#storeInfo_contactName").val();
			var storeInfo_contactTel=$("#storeInfo_contactTel").val();
			   /*$('#addrDiv').citys({
			        required:false,
			        nodata:'disabled',
			        onChange:function(data){
			            var text = data['direct']?'(直辖市)':'';
			            $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
			            storeInfo_userAddress1=(data['province']+","+data['city']+","+data['area']); 
//			            console.log(storeInfo_userAddress1);	
			        }
			    });*/
				var ok=true;
				if(storeInfo_userAddress==undefined){
					ok=false;
					alert("请重新选择地址");
//						var storeInfo_userAddress=storeInfo_userAddress2;
				}
//				console.log(storeInfo_userAddress);
				if(ok){
					$.ajax({
						url:"modify_shopInfo.do",
						type:"post",
						dataType:"json",
						data:{"id":shopInfoId,
							"name":storeInfo_name,
							"sellerAccount":storeInfo_sellerAccount,
							"contactName":storeInfo_contactName,
							"contactTel":storeInfo_contactTel,
							"userAddress":storeInfo_userAddress},
						 success:function(result){
							if(result.state==0){
								alert("修改成功！"); 	
								window.location.href = path+"/accredit.do";	
							}
						},
						error:function(){
							alert("修改失败！"); 
						}
					});	
				}
			
}
function loadModifyShopInfo(){
			//获取cookie中的shopInfoId
			var shopInfoId = getCookie("id");
			//判断cookie是否有效
			if(shopInfoId==null){
//				window.location.href="accredit.do";
			}
			//发送ajax
			$.ajax({
				url : "load_modify_shopInfo.do",
				type : "post",
				data : {"id":shopInfoId},
				dataType : "json",
				success:function(result){
					if (result.state == 0) {
					var ShopInfo = result.data;
			//获取各项数值
			var name = ShopInfo.name;
			var sellerAccount = ShopInfo.sellerAccount;
			var contactName = ShopInfo.contactName;
			var contactTel = ShopInfo.contactTel;
			var userAddress = ShopInfo.userAddress;
			var arr=userAddress.split(",");
			console.log(arr);
			//获取各个内容框，绑定他们哟
			var storeInfo_name=$("#storeInfo_name").val(name);
			var storeInfo_sellerAccount=$("#storeInfo_sellerAccount").val(sellerAccount);
			var storeInfo_contactName=$("#storeInfo_contactName").val(contactName);
			var storeInfo_contactTel=$("#storeInfo_contactTel").val(contactTel);
//			console.log(arr[1]);
//			console.log(arr[2]);
			//解决了地区重复的问题
			if((arr[1]!=""&&arr[1]!=null)&&(arr[2]!=""&&arr[2]!=null)){//bug 北京市，，什麼區
//				console.log("进来了");
					$.ajax({//通过市找到区的地区编码
						url:"findByRegionName.do",
						type:"post",
						dataType:"json",
						data:{"regionName":arr[1]},
						 success:function(result){
							if(result.state==0){
								var region=result.data;
								var regionId=region.regionId;
								$.ajax({
									url:"findByRegionName1.do",
									type:"post",
									dataType:"json",
									data:{"regionName":arr[2]},
									 success:function(result){
										if(result.state==0){
											var list=result.data;
											for (var i = 0; i < list.length; i++) {
												var region = list[i];
												var regionCode=region.regionCode;
												var parentId=region.parentId;
												if(regionId==parentId){
												$('#addrDiv').citys({
										        code:regionCode,
											    required:false,
											    nodata:'disabled',
											    onChange:function(data){
											        var text = data['direct']?'(直辖市)':'';
											        $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
//											         storeInfo_userAddress2=(data['province']+","+data['city']+","+data['area']); 
//											        console.log(storeInfo_userAddress2);
											    }
											});
										}
										}}
									},
									error:function(){
										alert("加载失败！"); 
									}
								});	//
							}
						},
						error:function(){
							alert("加载失败！"); 
						}
					});	
				
				}
			if((arr[0]!=""&&arr[0]!=null)&&(arr[2]!=""&&arr[2]!=null)&&(arr[1]==""||arr[1]==null)){
				$.ajax({
					url:"findByRegionName.do",
					type:"post",
					dataType:"json",
					data:{"regionName":arr[2]},
					 success:function(result){
						if(result.state==0){
							var region=result.data;
							var regionCode=region.regionCode;
							$('#addrDiv').citys({
						        code:regionCode,
							    required:false,
							    nodata:'disabled',
							    onChange:function(data){
							        var text = data['direct']?'(直辖市)':'';
							        $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
							    }
							});
						}
					},
					error:function(){
						alert("加载失败！"); 
					}
				});	
			}
				if((arr[2]==""||arr[2]==null)&&(arr[1]!=""&&arr[1]!=null)){
					$.ajax({
						url:"findByRegionName.do",
						type:"post",
						dataType:"json",
						data:{"regionName":arr[1]},
						 success:function(result){
							if(result.state==0){
								var region=result.data;
								var regionCode=region.regionCode;
								$('#addrDiv').citys({
							        code:regionCode,
								    required:false,
								    nodata:'disabled',
								    onChange:function(data){
								        var text = data['direct']?'(直辖市)':'';
								        $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
//								         storeInfo_userAddress2=(data['province']+","+data['city']+","+data['area']); 
								    }
								});
							}
						},
						error:function(){
							alert("加载失败！"); 
						}
					});
				}
			/**
			 * 
			 */
			/*if(arr[2]!=""&&arr[2]!=null){//bug 北京市，，什麼區
			$.ajax({
				url:"findByRegionName.do",
				type:"post",
				dataType:"json",
				data:{"regionName":arr[2]},
				 success:function(result){
					if(result.state==0){
						var region=result.data;
						var regionId=region.regionId;
						var regionCode=region.regionCode;
						console.log(regionId);
						$('#addrDiv').citys({
					        code:regionCode,
						    required:false,
						    nodata:'disabled',
						    onChange:function(data){
						        var text = data['direct']?'(直辖市)':'';
						        $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
						         storeInfo_userAddress2=(data['province']+","+data['city']+","+data['area']); 
						        console.log(storeInfo_userAddress2);
						    }
						});
					}
				},
				error:function(){
					alert("加载失败！"); 
				}
			});	
			}*/
			/*if(arr[1]!=""&&arr[1]!=null){//解決地區相同的問題
				$.ajax({
					url:"findByRegionName1.do",
					type:"post",
					dataType:"json",
					async : false, 
					data:{"regionName":arr[2]},
					 success:function(result){
						if(result.state==0){
							var list=result.data;
							for (var i = 0; i < list.length; i++) {
								var region = list[i];
								var regionCode=region.regionCode;
								var parentId=region.parentId;
								if(regionId==parentId){
									console.log(regionId+"---"+parentId);
									$('#addrDiv').citys({
								        code:regionCode,
									    required:false,
									    nodata:'disabled',
									    onChange:function(data){//選中地區
									        var text = data['direct']?'(直辖市)':'';
									        $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
									    }
									});
								}
							}
						}
					},
					error:function(){
						alert("加载失败！"); 
					}
				});	
			}*/
					}
				},
				error:function(){
					alert("修改栏目获取信息失败！")
				}
			});
		}
		function TAccount(){//停用
			var $tr = $(this).parent().parent();
			var shopInfoId = $tr.data("id");
			$.ajax({
				url:"modify_shopInfoStatus.do",
				type:"post",
				dataType:"json",
				data:{"id":shopInfoId,
					"shopStatus":false},
				 success:function(result){
					if(result.state==0){
						alert("停用成功！"); 	
					}
				},
				error:function(){
					alert("停用失败！"); 
				}
			});
		}
		/*var a=userAddress.substring(0,userAddress.indexOf('省')+1);
		var b=userAddress.substring(userAddress.indexOf('省')+1,userAddress.indexOf('市')+1);
		var c=userAddress.substring(userAddress.indexOf('市')+1);*/