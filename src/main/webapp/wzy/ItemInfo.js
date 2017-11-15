/**
 * wzy
 */
//设置当前页面
var now_page = 1;

//设置最大页面
var max_page = 1;

//设置关键字
var key_word = "";

$(function(){
	//1.打开网页时立即查询
	doGetItemInfo(now_page, key_word);
	
	//2.点击新建进行商品添加
	$(".management_xj").click(function(){
		setBoxInfo();
		$("#title").html("新建商品");
		$(".commodity_management_box").css("display","block");
	});

	//3.点击复制并新建对商品进行复制并创建
	$(".management_fzxj").click(function(){
		doCopyAndSave();
	});

	//4.点击修改对当前的商品进行的修改
	$("#tbody").on("click",".management_amend",doUpDateItemInfo)

	//5.点击删除对当前商品进行删除
	$("#tbody").on("click",".border-red",doRemoveItemInfo)

	//6.点击批量删除对选中的商品进行删除
	$(".border-remove").click(function(){
		doRemoveMoreItem();
	});

	//7.点击查询进行模糊查询
	$(".icon-search").click(function(){
		doFindItemInfoByLike();
	})

	//8.用来进行增加与修改的保存
	$(".commodity_management_box").on("click",".management_bc",doSaveOrUpdateItemInfo);

	//9.用来取消新建或保存
	$(".commodity_management_box").on("click",".management_qx, .management_delbtn",doCancelItemInfo)
	
	//10.监听页码点击
	$("#tfoot").on("click", ".pagelist a", clickPage);
	
	//11.监听全选
	$("input[name='options']").click(function(){
		clickOptions();
	})
})


//加载查询
function doGetItemInfo(now_page, key_word){
	//移除id
	$("#tbody").removeData("id")
	//发送请求，获取页面内容
	$.ajax({
		url : "/findUserByKeyWord.do",
		type : "post",
		//traditional : true,
		data : {
			"nowPage" : now_page, // int
			"keyWord" : key_word  // String
		},
		dataType : "json",
		success : function(result) {
			//创建页面显示的信息
			doSetTbodyInfo(result.data.list);
			doSetTfootInfo(result.data.maxPage);
			key_word = $(".input").val("");			
		},
		error : function() {
			showMessage("加载商品列表失败");
		}
	});
}

//tbody页面
function doSetTbodyInfo(list){
	
	//获取tbody并清空
	var tbody = $("#tbody");
	tbody.empty();
	
	//遍历信息并填写
	for (var i=0;i<list.length;i++){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		var firstTd='<td><input type="checkbox" name="checkedItem" value="[id]"></td>';
		firstTd=firstTd.replace("[id]",list[i].id);
		var tds="<td><p>"+list[i].erpItemNum+"</p></td>" +
				"<td><p>"+list[i].name+"</p></td>"+
				"<td><p>"+list[i].styleCode+"</p></td>"+
				"<td><p>"+list[i].barCode+"</p></td>"+
				"<td><p>"+list[i].color+" * "+list[i].size+"</p></td>"+
				"<td><p>"+list[i].normalPrice+"</p></td>"+
				"<td><p>"+list[i].brand+"</p></td>"+
				"<td>"+
				'<a href="javascript:;" class="button border-main management_amend"> 修改</a>'+
				'<a href="javascript:;" class="button border-red"> 删除</a>'+
				"</td>";
		tr.append(firstTd);	
		tr.append(tds);
		tbody.append(tr);
	}
};

//tfoot布置
function doSetTfootInfo(page){
	//第三部分
	max_page =page
	var tfoot = $("#tfoot");
	tfoot.empty();
	var tr = "";
	tr += '<tr>';
	
	//尾页开始部分
	tr += '<td colspan="8"><div class="pagelist"> <a>首页</a><a>上一页</a> ';
	/*console.log(now_page);*/
	//尾页中间部分
	if (max_page > 5) {
		//如果是页码前两个
		if (now_page <= 3) {
			//循环前三页码
			for (var i = 1; i < 4; i++) {
				
				//如果选中当前页码，则变成蓝色背景
				if(i==now_page){
					tr += '<span class="current">';
					tr += i;
					tr += '</span>';
					
				//否则页码为白色背景
				}else{
					tr += '<a>';
					tr += i;
					tr += '</a>';
				}
			}
			//写出最后两个
			tr += '<a>';
			tr += 4;
			tr += '</a>';
			tr += '<a>';
			tr += 5;
			tr += '</a>……';
			
		//如果是页码最中间
		} else if (now_page >= 4 && now_page <= max_page - 3) {
			//页码前两个
			tr += '……<a>';
			tr += now_page - 2;
			tr += '</a>';
			tr += '<a>';
			tr += now_page - 1;
			tr += '</a>';
			
			//页码中间选中的
			tr += '<span class="current">';
			tr += now_page;
			tr += '</span>';
			
			//页码后两个
			tr += '<a>';
			tr += now_page + 1;
			tr += '</a>';
			tr += '<a>';
			tr += now_page + 2;
			tr += '</a>……';
		//如果是页码后两个
		} else if (now_page > max_page - 3) {
			//页码前两个
			tr += '……<a>';
			tr += max_page - 4;
			tr += '</a>';
			tr += '<a>';
			tr += max_page - 3;
			tr += '</a>';
			
			//循环后三页
			for (var i = max_page - 2; i <= max_page; i++) {
				//如果选中当前页码，则变成蓝色背景
				if(i==now_page){
					tr += '<span class="current">';
					tr += i;
					tr += '</span>';
					
				//否则页码为白色背景
				}else{
					tr += '<a>';
					tr += i;
					tr += '</a>';
				}
			}
		}
	
	//否则页数小于5页
	} else {
		var i = 1;
		//循环页码
		while (i <= max_page) {
			//如果选中当前页码，则变成蓝色背景
			if(i==now_page){
				tr += '<span class="current">';
				tr += i;
				tr += '</span>';
				
			//否则页码为白色背景
			}else{
				tr += '<a>';
				tr += i;
				tr += '</a>';
			}
			i++;
		}
	}

	//尾页结束部分
	tr += '<a>下一页</a><a>尾页</a> </div></td>';
	tr += '</tr>';
    tfoot.append(tr);
    
}

//监听页码
function clickPage() {

	var aHtml = $(this).html();
	//判断当前页的值
	if(aHtml=="首页"){
		now_page = 1;
	}else if(aHtml=="上一页"){
		if(now_page>1){
			now_page--;
		}
	}else if(aHtml=="下一页"){
		if(now_page<max_page){
			now_page++;
		}
	}else if(aHtml=="尾页"){
		now_page = parseInt(max_page);
	}else{
		now_page = parseInt(aHtml);
	}
	doGetItemInfo(now_page,key_word)
}

//点击保存
function doSaveOrUpdateItemInfo(){
	
	//获取id判断是更新还是添加
	var id=$(".commodity_management_box").data("id");
	
	//获取传输信息
	var url=id?"/updateItemInfo.do":"/saveItemInfo.do";
	var param = doGetItemParam();
	//对页面信息进行判断
	if(!param.erpItemNum || !param.name){
		showMessage("商品编号或名称不能为空");
	}else{
		$.post(url,param,function(result){
			showMessage(result.message);
		})
		$(".commodity_management_box").css("display","none");
		$(".commodity_management_box").removeData("id");
		now_page = 1;
		doGetItemInfo(now_page, key_word);
	}
}

//点击“复制并新建”
function doCopyAndSave(){
	
	//更改模块标题
	$("#title").html("复制并新建");
	
	//获取购线上拼得id并判断
	var ids="";
	var index = 0;
	$("#tbody input[name='checkedItem']")
	  .each(function(){
		  if($(this).prop("checked")){
			  if(ids==""){
				  ids+=$(this).val();
			  }else{
				  ids+=","+$(this).val();
			  }
			  index++;
		  }
	  });
	console.log(ids)
	if(index == 0){
		showMessage("请至少选择一个");
	}else if(index > 1){
		showMessage("您选择的信息过多，请选择一条信息");
	}else{
		//为模块添加id用于填写模块
		$(".commodity_management_box").data("id",ids)
		var param = {
				id:ids
		}
		var url = "/findItemInfoById.do";
		$.post(url,param,function(result){
			setBoxInfo(result.data.list[0])
			//移除id使模块变为新建
			$(".commodity_management_box").removeData("id")
			$(".commodity_management_box").css("display","block");
		});
	}
}

//点击删除
function doRemoveItemInfo(){
	
	//获取删除的商品id
	var param={
			id:$(this).parent().parent().data("id")
	}
	var url = "/doRemoveItemInfo.do";
	if(confirm("确定要删除吗")){
		$.post(url,param,function(result){
		showMessage(result.message);
		doGetItemInfo(now_page, key_word);
		}
	)}
}

//点击“批量删除”
function doRemoveMoreItem(){
	
	//获取商品的id并判断
	var ids="";
	var index = 0;
	$("#tbody input[name='checkedItem']")
	  .each(function(){
		  if($(this).prop("checked")){
			  if(ids==""){
				  ids+=$(this).val();
			  }else{
				  ids+=","+$(this).val();
			  }
			  index++;
		  }
	  });
	if(index == 0){
		showMessage("请至少选择一个");
	}else{
		if(confirm("确定要删除吗")){
			var url = "/doRemoveItemInfo.do";
			var param = {
					id:ids
			}
			$.post(url,param,function(result){
				showMessage(result.message);
				doGetItemInfo(now_page, key_word);
			});
		}
	}
}

//获取商品的信息用于商品的新建或更新
function doGetItemParam(){
	if($(".commodity_management_box").data("id")){
		var param ={erpItemNum:$("#erpItemNum").val(),
					category:$("#category").val(),
					brand:$("#brand").val(),
					name:$("#name").val(),
					spec:$("#spec").val(),
					shortName:$("#shortName").val(),
					color:$("#color").val(),
					size:$("#size").val(),
					normalPrice:$("#normalPrice").val(),
					costPrice:$("#costPrice").val(),
					barCode:$("#barCode").val(),
					packageCondition:$("#packageCondition").val(),
					length:$("#length").val(),
					width:$("#width").val(),
					height:$("#height").val(),
					weight:$("#weight").val(),
					unit:$("#unit").val(),
					batchCode:$("#batchCode").val(),
					expireDate:$("#expireDate").val(),
					styleCode:$("#styleCode").val(),
					userClassification:$("#userClassification").val(),
					reserved1:$("#reserved1").val(),
					reserved2:$("#reserved2").val(),
					reserved3:$("#reserved3").val(),
					note:$("#note").val(),
					commissionSell:$("input[name='commissionSell']")[0].checked,
					presell:$("input[name='presell']")[0].checked,
					gift:$("input[name='gift']")[0].checked,
					id:$(".commodity_management_box").data("id"),
		}
	}else{
		var param ={erpItemNum:$("#erpItemNum").val(),
				category:$("#category").val(),
				brand:$("#brand").val(),
				name:$("#name").val(),
				spec:$("#spec").val(),
				shortName:$("#shortName").val(),
				color:$("#color").val(),
				size:$("#size").val(),
				normalPrice:$("#normalPrice").val(),
				costPrice:$("#costPrice").val(),
				barCode:$("#barCode").val(),
				packageCondition:$("#packageCondition").val(),
				length:$("#length").val(),
				width:$("#width").val(),
				height:$("#height").val(),
				weight:$("#weight").val(),
				unit:$("#unit").val(),
				batchCode:$("#batchCode").val(),
				expireDate:$("#expireDate").val(),
				styleCode:$("#styleCode").val(),
				userClassification:$("#userClassification").val(),
				reserved1:$("#reserved1").val(),
				reserved2:$("#reserved2").val(),
				reserved3:$("#reserved3").val(),
				note:$("#note").val(),
				commissionSell:$("input[name='commissionSell']")[0].checked,
				presell:$("input[name='presell']")[0].checked,
				gift:$("input[name='gift']")[0].checked
		}
	}
	return param;
}

//点击修改
function doUpDateItemInfo(){
	
	//更改模块标题
	$("#title").html("商品修改");
	
	//获取商品id
	var id=$(this).parent().parent().data("id");
	
	//利用id判断俄日更新并打开模块
	$(".commodity_management_box").data("id",id);
	$(".commodity_management_box").css("display","block");
	var param = {
			id:id
	}
	var url = "/findItemInfoById.do";
	$.post(url,param,function(result){
		setBoxInfo(result.data.list[0]);
	});
}

//点击取消
function doCancelItemInfo(){
	//移除模块id
	$(".commodity_management_box").removeData("id")
	$(".commodity_management_box").css("display","none");
}

//填写模块
function setBoxInfo(data){
	if($(".commodity_management_box").data("id")){
		$("#erpItemNum").attr('readonly',true)
		$("#erpItemNum").val(data.erpItemNum)
		$("#category").val(data.category)
		$("#brand").val(data.brand)
		$("#name").val(data.name)
		$("#shortName").val(data.shortName)
		$("#spec").val(data.spec)
		$("#color").val(data.color)
		$("#size").val(data.size)
		$("#normalPrice").val(data.normalPrice)
		$("#costPrice").val(data.costPrice)
		$("#barCode").val(data.barCode)
		$("#packageCondition").val(data.packageCondition)
		$("#length").val(data.length)
		$("#width").val(data.width)
		$("#height").val(data.height)
		$("#weight").val(data.weight)
		$("#unit").val(data.unit)
		$("#batchCode").val(data.batchCode)
		$("#expireDate").val(data.expireDate)
		$("#styleCode").val(data.styleCode)
		$("#userClassification").val(data.userClassification)
		$("#reserved1").val(data.reserved1)
		$("#reserved2").val(data.reserved2)
		$("#reserved3").val(data.reserved3)
		$("#note").val(data.note)
		if(data.presell){
			$("input[name='presell']")[0].checked==true
		}
		if(data.commissionSell){
			$("input[name='commissionSell']")[0].checked==true
		}
		if(data.gift){
			$("input[name='gift']")[0].checked = true
		}
	}else{
		$("#erpItemNum").attr("readOnly",false)
		$("#erpItemNum").val("")
		$("#category").val("")
		$("#brand").val("")
		$("#name").val("")
		$("#shortName").val("")
		$("#spec").val("")
		$("#color").val("")
		$("#size").val("")
		$("#normalPrice").val("")
		$("#costPrice").val("")
		$("#barCode").val("")
		$("#packageCondition").val("")
		$("#length").val("")
		$("#width").val("")
		$("#height").val("")
		$("#weight").val("")
		$("#unit").val("")
		$("#batchCode").val("")
		$("#expireDate").val("")
		$("#styleCode").val("")
		$("#userClassification").val("")
		$("#reserved1").val("")
		$("#reserved2").val("")
		$("#reserved3").val("")
		$("#note").val("")
		$("input[name='presell']")[0].checked=false
		$("input[name='commissionSell']")[0].checked=false
		$("input[name='gift']")[0].checked=false
	}
}

//模糊查询
function doFindItemInfoByLike(){
	
	//初始化页码
	now_page = 1;

	//设置关键字
	key_word = $(".input").val().trim();
	
	//查询
	doGetItemInfo(now_page, key_word)
}

//点击全选
function clickOptions(){
	var checkeds = $("input[name='checkedItem']")
	if($("input[name='options']")[0].checked){
		for(var i=0;i<checkeds.length;i++){
			checkeds[i].checked = true
		}
	}else{
		for(var i=0;i<checkeds.length;i++){
			checkeds[i].checked = false
		}
	}
}
