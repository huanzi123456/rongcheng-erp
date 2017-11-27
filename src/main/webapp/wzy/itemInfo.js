/**
 * wzy
 */
//设置当前页面
var now_page = 1;

//设置最大页面
var max_page = 1;

//设置关键字
var key_word = "";

//设置分类
var category_word = "";

//用于递归系统分类的元素
var radio2 = "";
var radio3 = "";

//储存商品的分类（数据库）
var categoryOne = new Array(); 

//储存商品的分类（更该后）
var categoryTwo = new Array();

//传回的分类删除
var deleteCategory = "";

//传回的分类添加
var insertCategory = "";
$(function(){
	
	//1.1打开网页时立即查询全部商品
	doGetItemInfo(now_page, key_word, category_word);

	//1.2加载弹出框中的下拉选
	setCategoryInfo();
	
	//1.3加载更改分类中的界面
	setMoreCategoryBox();
	
	//2.点击新建进行商品添加
	$(".management_xj").click(function(){
		//布置分类下拉选
		$("#title").html("新建商品");
		$('.select_simulate_box').css("display","none");
		$(".commodity_management_box").css("display","block");
		setBoxInfo1();
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

	//9.用来取消新建或修改
	$(".commodity_management_box").on("click",".management_qx, .management_delbtn",doCancelItemInfo)
	
	//10.监听页码点击
	$("#tfoot").on("click", ".pagelist a", clickPage);
	
	//11.监听全选
	$("input[name='options']").click(function(){
		clickOptions();
	})
	
	//12.监听新建以及修改中的下拉
	$('.select_simulate_xb').click(function(){
		if ($('.select_simulate_box').css('display')=='none') {
			$('.select_simulate_box').css("display","block");
		}else{
			$('.select_simulate_box').css("display","none");
		}
	});
	
	//13.监听商品分类的按钮
	$(".class_add").click(function(){
		$(".class_add_box").css("display","block");
		doGetCategoryTreeByAddCategory()
	});
	$(".class_amend").click(function(){
		$(".class_add_box").css("display","block");
		doGetCategoryName();
	});
	$(".class_add_box").on("click",".class_add__qx, .class_add_delbtn",function(){
		$("#class_box_category").removeData("button");
		$("#class_box_category").removeData("id");
		$("#class_box_category").val("");
		$(".class_add_box").css("display","none");
	})
	$(".class_add__bc").click(function(){
		doSaveUserCategory();
	});
	$(".red_color").click(function(){
		doDeleteUserCategory();
	})
	
	//14.监听分类选择
	$(".shopping_box").on("click", ".left_fl a", clickCategory);
	
	//15.商品box中的多项选
	$(".select_simulate").on("click",clickCheckBox);
	
	//16.更改分类的按钮点击
	$(".classify_fl").click(function(){
		saveMoreCategory();
	})
	$(".amend_classify_box").on("click", ".classify_delbtn, .classify_qx",function(){
		$(".amend_classify_box").css("display","none");
	})
	$(".amend_classify_box").on("click", ".classify_bc",doSaveMoreCategory);
	$(".classify_box").on("click",clickCheckBox2);
})

//加载查询
function doGetItemInfo(now_page, key_word, category_word){
	//移除id
	$("#tbody").removeData("id")
	//发送请求，获取页面内容
	$.ajax({
		url : "/findUserByKeyWord.do",
		type : "post",
		//traditional : true,
		data : {
			"nowPage" : now_page, // int
			"categotyId" : category_word,
			"keyWord" : key_word  // String
		},
		dataType : "json",
		success : function(result) {
			//创建页面显示的信息
			doSetTbodyInfo(result.data);
			doSetTfootInfo(result.data.maxPage);
			$(".input").val("");
			key_word = $(".input").val("");			
		},
		error : function() {
			showMessage("加载商品列表失败");
		}
	});
}

//tbody页面
function doSetTbodyInfo(data){
	//获取tbody并清空
	var list = data.list;
	var startPage = data.startPage;
	var tbody = $("#tbody");
	tbody.empty();
	//遍历信息并填写
	for (var i=0;i<list.length;i++){
		var num = startPage+1+i;
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		var firstTd="<td>" +
						"<span>"+num+"</span>"+
				 		"<br><input type='checkbox' name='checkedItem' value='[id]'>" +
						"<div class='img_box'>"+
  							"<img src='"+list[i].image1+"' alt=''>"+
  							"<div class='img_show_box'>"+
  								"<img src='"+list[i].image1+"' alt=''>"+
  								"</div>"+
  						"</div>"+
					"</td>";
		firstTd=firstTd.replace("[id]",list[i].id);
		
		var code = list[i].erpItemNum?list[i].erpItemNum:" ";
		var barcode = list[i].barCode?list[i].barCode:" ";
		var color = list[i].color?list[i].color:" ";
		var size = list[i].size?list[i].size:" ";
		var normalPrice = list[i].normalPrice?list[i].normalPrice:" ";
		var brand = list[i].brand?list[i].brand:" ";
		
		var tds="<td>"+list[i].erpItemNum+"</td>" +
				"<td><p>"+list[i].name+"</p></td>"+
				"<td>"+code+"</td>"+
				"<td>"+barcode+"</td>"+
				"<td><p>"+color+" * "+size+"<p></td>"+
				"<td>"+normalPrice+"</p></td>"+
				"<td>"+brand+"</td>"+
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
	doGetItemInfo(now_page,key_word,category_word)
}

//点击商品的新建修改保存
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
			if(!result.state){
				$(".commodity_management_box").css("display","none");
				$(".commodity_management_box").removeData("id");
				now_page = 1;
				doGetItemInfo(now_page, key_word, category_word);
			}
		})
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
			setBoxInfo2(result.data)
			//移除id使模块变为新建
			$('.select_simulate_box').css("display","none");
			$(".commodity_management_box").removeData("id")
			$(".commodity_management_box").css("display","block");
		});
	}
}

//点击商品的删除
function doRemoveItemInfo(){
	
	//获取删除的商品id
	var param={
			id:$(this).parent().parent().data("id")
	}
	var url = "/doRemoveItemInfo.do";
	if(confirm("确定要删除吗")){
		$.post(url,param,function(result){
		showMessage(result.message);
		doGetItemInfo(now_page, key_word, category_word);
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
			var ajaxPost = $.post(url,param,function(result){
				doGetItemInfo(now_page, key_word, category_word);
			});
			
			ajaxPost.done(function(){
				showMessage("删除成功!")
			})
			ajaxPost.fail(function(){
				showMessage("删除失败!")
			})
		}
	}
}

//获取商品的信息用于商品的新建或更新
function doGetItemParam(){
	
	//依据id判断新建还是更新
	if($(".commodity_management_box").data("id")){
		
		doGetBoxCategory();
		
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
					insertCategory:insertCategory,
					deleteCategory:deleteCategory
		}
	}else{
		
		//清空并处理数组
		categoryOne = [];
		doGetBoxCategory();
		
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
				insertCategory:insertCategory,
				deleteCategory:deleteCategory
		}
	}
	return param;
}

//点击商品的修改
function doUpDateItemInfo(){
	//更改模块标题
	$("#title").html("商品修改");
	
	//获取商品id
	var id=$(this).parent().parent().data("id");
	
	//利用id判断俄日更新并打开模块
	$('.select_simulate_box').css("display","none");
	$(".commodity_management_box").data("id",id);
	$(".commodity_management_box").css("display","block");
	var param = {
			id:id
	}
	var url = "/findItemInfoById.do";
	var ajax = $.post(url,param,function(result){
		setBoxInfo1();
		setBoxInfo2(result.data);
	});
}

//点击商品box取消
function doCancelItemInfo(){
	//移除模块id
	$(".commodity_management_box").removeData("id")
	$(".commodity_management_box").css("display","none");
}

//填写模块用于复制 修改
function setBoxInfo2(result){
	var data="";
	var list="";
	if(result){
		data=result.list[0];
		lists=result.list1;
		console.log(lists)
	}
	//布置
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
	categoryOne=[];
	if(!lists){
		for(var i = 0;i<lists.length;i++){
			if(lists[i].categoryId){
				$("input[name='category1'][value='"+lists[i].categoryId+"']").prop("checked", true);
				$("input[name='categoryNull']").prop('checked', false)
			}
			categoryOne.push(lists[i].categoryId);	
		}
	}else{
		categoryOne.push("k");
	}
}

//仅用于新建
function setBoxInfo1(){
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
	$("input[name='categoryNull']").prop("checked",true)
	$("input[name='category1']").each(function(){
		$(this).prop('checked', false)
	})
}

//模糊查询
function doFindItemInfoByLike(){
	
	//初始化页码
	now_page = 1;

	//设置关键字
	key_word = $(".input").val().trim();
	//查询
	doGetItemInfo(now_page, key_word, category_word)
}

//点击商品的全选
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

//商品box布置下拉选分类（关于新建修改）
function setCategoryInfo(){
	
	//清空下拉选
	$(".select_simulate_box").empty();
	
	//清空全局变量
	radio2 = "";
	
	$.ajax({
		url : "/findAllCategory.do",
		type : "post",
		//traditional : true,
		dataType : "json",
		success : function(result) {
			//递归分类
			doSetCategoryBoxInSimulate(0, result.data.list);
			var div ='<li>'+
						'<a href="javascript:;"><input type="checkbox" name="categoryNull" checked="true" value="0">未分类商品</a>'+
					 '</li>';
			var li = $("<li></li>");
			
			li.append(radio2);
			$(".select_simulate_box").append(div);
			$(".select_simulate_box").append(li);
			
			setBoxInfo1();
			//监听多项选
			$("input[name='category1']").change(function(){
				clickCheckBox();
			});
		}
	})
}

//商品box递归遍历1
function doSetCategoryBoxInSimulate(id, list){
    //根据菜单主键id生成菜单列表html
    //id：菜单主键id
    //arry：菜单数组信息
    var childArry = GetParentArry2(id, list);
       if (childArry.length > 0) {
    	   radio2 += '<ol style="padding-left:20px;">';
        for (var i in childArry) {
        	radio2 += '<li>' +
        	'<a href="javascript:;"><input type="checkbox" name="category1" pId="'+childArry[i].parentId+'" value="'+childArry[i].id+'">'+childArry[i].categoryName+'</a>';
        	doSetCategoryBoxInSimulate(childArry[i].id, list);
          	radio2 += '</li>';
        }
        radio2 += '</ol>';
      }
}

//商品box递归遍历2
function GetParentArry2(id, list) {
    var newArry = new Array();
    for (var i in list) {
      if (list[i].parentId == id)
        newArry.push(list[i]);
    }
    return newArry;
}

//监听分类点击
function clickCategory(){
	$(".left_fl a").css({"color":"black"});
	$(".left_fl a").data("num" , 0);
	$(this).css({"color":"blue"});
	$(this).data("num" ,1);
	category_word = $(this).attr("value");
	now_page = 1;
	key_word = "";
	doGetItemInfo(now_page, key_word, category_word);
}

//商品box多选框的交互
function clickCheckBox(){

	$("input[name='category1']").change(function(){
		
		//获取当前的多选对象
		var obj = $(this);
		
		//父类取消子类取消
		doChildrenCheckBox(obj)
		
		//子类选中父类选中
		var index = doFatherCheckBox(obj);
		
		//判断第一级的勾选（id=0）
		if(index){
				$("input[name='categoryNull']").prop('checked', false);
			}else {
				$("input[name='categoryNull']").prop('checked', true);
			}
	})
	
	
	$("input[name='categoryNull']").change(function(){
		var obj = $(this);
		if(obj.is(":checked")){
			$("input[name='category1']").each(function(){
				$(this).prop('checked', false);
			})
		}else{
			obj.prop('checked', true);
		}
	})
}

//商品box子类选中父类选中
function doFatherCheckBox(obj){
	
	if(obj.attr("pid")){
		var father = obj.parent().parent().parent().parent().children();
		var fatherObj = father[0].children[0];
		var index = 0;
		var box = $("input[name='category1']");
		for(var i = 0;i<box.length;i++){
			if(box[i].checked){
				index++;
				fatherObj.checked=true;
				doFatherCheckBox($(fatherObj))
			}
		}
	}
	return index;
}

//商品box父类取消子类取消
function doChildrenCheckBox(obj){
	var child = obj.parent().parent().children().children();
	var childObj=null;
	if(!obj.is(':checked')){
		for(var i = 1;i<child.length;i++){
			if(child[i].children[0]){
				childObj = child[i].children[0].children[0];
			}
			if(childObj.checked){
				childObj.checked=false;
				doChildrenCheckBox($(childObj));
			}
		}
	}
}

//获取商品box中的分类信息
function doGetBoxCategory(){
	
	categoryTwo = [];
	
	if($("input[name='categoryNull']").is(":checked")){
		categoryTwo.push("k");
	}else{
		$("input[name='category1']").each(function(){
			  if($(this).prop("checked")){
				  categoryTwo.push($(this).val())
			  }
		  });
	}
	
	//处理集合
	comparisonList();
}

//处理集合
function comparisonList(){
	
	//清空集合
	insertCategory = "";
	deleteCategory = "";
	
	//集合处理方法
	Array.prototype.minus = function (arr) {
        var result = new Array();
        var obj = {};
        for (var i = 0; i < arr.length; i++) {
            obj[arr[i]] = 1;
        }
        for (var j = 0; j < this.length; j++) {
            if (!obj[this[j]])
            {
                obj[this[j]] = 1;
                result.push(this[j]);
            }
        }
        var ids ="";
        for(var i  = 0;i <result.length;i++){
        	if(ids==""){
				  ids+= result[i];
			  }else{
				  ids+=","+result[i];
			  }
        }
        return ids
    };
    
    deleteCategory = categoryOne.minus(categoryTwo)
    insertCategory = categoryTwo.minus(categoryOne);
}

//修改时获取系统分类名称
function doGetCategoryName(){
	var index = 0;
	
	//修改标题
	$(".class_add_title span").html("修改系统分类");
	
	//遍历获取求该的分类名称
	$(".left_fl a").each(function(){
		if($(this).data("num")){
			$("#class_box_category").val($(this).html());
			$("#class_box_category").data("button",0);
			$("#class_box_category").data("id",$(this).attr("value"));
			index++;
		}
	})
	
	if(!$("#class_box_category").data("id") || $("#class_box_category").data("id")=="0"){
		showMessage("全部商品和未分类商品不能更改");
		$("#class_box_category").removeData("id");
		$(".class_add_box").css("display","none");
	}
	
	if(!index){
		showMessage("请您选择一个商品分类进行修复");
		$(".class_add_box").css("display","none");
	}
}

//添加时获取用户的商品分类树
function doGetCategoryTreeByAddCategory(){

	var index = 0;
	
	//修改标题
	$(".class_add_title span").html("增加系统分类");
	
	//遍历获取求该的分类名称
	$(".left_fl a").each(function(){
		if($(this).data("num")){
			$("#class_box_category").data("button",1);
			$("#class_box_category").data("id",$(this).attr("value"));
			index++;
		}
	})
	
	//判断是否是全部商品以及未分类商品
	if(!$("#class_box_category").data("id") || $("#class_box_category").data("id")=="0"){
		$("#class_box_category").data("id" , 0);
	}
	
	if(!index){
		showMessage("请在下方选择您要在那个分类下进行分类添加");
		$(".class_add_box").css("display","none");
	}
} 

//保存分类的添加或修改
function doSaveUserCategory(){
	
	//判断是添加还是更改
	var button = $("#class_box_category").data("button");
	
	var url = button?"/saveUserCategory.do":"/updateUserCategory.do";
	
	$.ajax({
		url : url,
		type : "post",
		//traditional : true,
		data : {
			"id" : $("#class_box_category").data("id"), // int
			"name" : $("#class_box_category").val(),
		},
		dataType : "json",
		success : function(result) {
			//创建页面显示的信息
			setCategoryInfo();
			doFindAllCategory();
			$(".class_add_box").css("display","none");
			showMessage(result.message);
		},
		error : function() {
			showMessage("操作失败");
		}
	});
	
}

//删除系统分类
function doDeleteUserCategory(){
	
	//遍历系统分类，获取删除的分类id
	var index = 0;
	$(".left_fl a").each(function(){
		if($(this).data("num")){
			$("#class_box_category").data("button",1);
			$("#class_box_category").data("id",$(this).attr("value"));
			index++;
		}
	})
	
	//判断是否执行
	if(!index){
		//判断是否选择了分类
		showMessage("您未选择要删除的对象，请您选则下方的分类进行删除")
	}else{
		//判断是否是全部商品以及未分类商品
		if(!$("#class_box_category").data("id") || $("#class_box_category").data("id")=="0"){
			showMessage("全部商品和未分类商品不能删除");
		}else{
			//再次确认是否执行
			if(confirm("确定要删除吗")){
				$.ajax({
					url : "deleteUserCategory.do",
					type : "post",
					//traditional : true,
					data : {
						"id" : $("#class_box_category").data("id"), // int
					},
					dataType : "json",
					success : function(result) {
						//创建页面显示的信息
						setCategoryInfo();
						doFindAllCategory()
						$(".class_add_box").css("display","none");
						showMessage(result.message);
					},
					error : function() {
						showMessage("操作失败");
					}
				})
			}
		}
	}
}

//布置更改分类中的界面
function setMoreCategoryBox(){
	
	//清空下拉选
	$(".classify_box").empty();
	
	//清空全局变量
	radio3 = "";
	
	$.ajax({
		url : "/findAllCategory.do",
		type : "post",
		//traditional : true,
		dataType : "json",
		success : function(result) {
			//递归分类
			doSetCategoryBoxInClassify(0, result.data.list);
			var div2 ='<li>'+
						'<a href="javascript:;"><input type="checkbox" name="categoryNull2" checked="true" value="0">未分类商品</a>'+
					 '</li>';
			var li2 = $("<li></li>");
			li2.empty();
			li2.append(radio3);
			$(".classify_box").append(div2);
			$(".classify_box").append(li2);
			
			//监听多项选
			$("input[name='category2']").change(function(){
				clickCheckBox2();
			});
		}
	})
}

//更改分类递归遍历1
function doSetCategoryBoxInClassify(id, list){
    //根据菜单主键id生成菜单列表html
    //id：菜单主键id
    //arry：菜单数组信息
    var childArry = GetParentArry3(id, list);
       if (childArry.length > 0) {
    	   radio3 += '<ol>';
        for (var i in childArry) {
        	radio3 += '<li>' +
        	'<a href="javascript:;"><input type="checkbox" name="category2" pId="'+childArry[i].parentId+'" value="'+childArry[i].id+'">'+childArry[i].categoryName+'</a>';
        	doSetCategoryBoxInClassify(childArry[i].id, list);
          	radio3 += '</li>';
        }
        radio3 += '</ol>';
      }
}

//更改分类递归遍历2
function GetParentArry3(id, list) {
    var newArry = new Array();
    for (var i in list) {
      if (list[i].parentId == id)
        newArry.push(list[i]);
    }
    return newArry;
}

//更改分类多选框的交互2
function clickCheckBox2(){

	$("input[name='category2']").change(function(){
		
		//获取当前的多选对象
		var obj = $(this);
		
		//父类取消子类取消
		doChildrenCheckBox2(obj)
		
		//子类选中父类选中
		var index = doFatherCheckBox2(obj);
		
		//判断第一级的勾选（id=0）
		if(index){
				$("input[name='categoryNull2']").prop('checked', false);
			}else {
				$("input[name='categoryNull2']").prop('checked', true);
			}
	})
	
	
	$("input[name='categoryNull2']").change(function(){
		var obj = $(this);
		if(obj.is(":checked")){
			$("input[name='category2']").each(function(){
				$(this).prop('checked', false);
			})
		}else{
			obj.prop('checked', true);
		}
	})
}

//更改分类子类选中父类选中2
function doFatherCheckBox2(obj){
	
	if(obj.attr("pid")){
		var father = obj.parent().parent().parent().parent().children();
		var fatherObj = father[0].children[0];
		var index = 0;
		var box = $("input[name='category2']");
		for(var i = 0;i<box.length;i++){
			if(box[i].checked){
				index++;
				fatherObj.checked=true;
				doFatherCheckBox2($(fatherObj))
			}
		}
	}
	return index;
}

//更改分类父类取消子类取消2
function doChildrenCheckBox2(obj){
	var child = obj.parent().parent().children().children();
	var childObj=null;
	if(!obj.is(':checked')){
		for(var i = 1;i<child.length;i++){
			if(child[i].children[0]){
				childObj = child[i].children[0].children[0];
			}
			if(childObj.checked){
				childObj.checked=false;
				doChildrenCheckBox2($(childObj));
			}
		}
	}
}

//获取更该分类的信息
function doGetBoxCategory2(){
	
	categoryTwo = [];
	
	if($("input[name='categoryNull2']").is(":checked")){
		categoryTwo.push("k");
	}else{
		$("input[name='category2']").each(function(){
			  if($(this).prop("checked")){
				  categoryTwo.push($(this).val())
			  }
		  });
	}
	
	//处理集合
	comparisonList()
}

//点击更改分类
function saveMoreCategory(){
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
		$(".amend_classify_box").data("id",ids);
		$(".amend_classify_box").css("display","block");
	}
}

//点击更改分类的保存
function doSaveMoreCategory(){

	//获取需要更改的商品id
	var id= $(".amend_classify_box").data("id");
	
	//获取更改分类中的信息
	getMoreCategoryInfo()
	console.log(id)
	$.ajax({
		url : "/updateMoreCategory.do",
		type : "post",
		//traditional : true,
		data : {
			id:id,
			insertCategory:insertCategory,
		},
		dataType : "json",
		success : function(result) {
			//创建页面显示的信息
			$(".amend_classify_box").css("display","none");
			doGetItemInfo(now_page, key_word, category_word);
			showMessage("更改分类成功");
		},
		error : function() {
			showMessage("更改分类失败");
		}
	});
	
}

//获取更改分类中的数据
function getMoreCategoryInfo(){

	categoryTwo = [];
	
	if($("input[name='categoryNull2']").is(":checked")){
		categoryTwo.push("k");
	}else{
		$("input[name='category2']").each(function(){
			  if($(this).prop("checked")){
				  categoryTwo.push($(this).val())
			  }
		  });
	}
	
	//处理集合
	comparisonList();
}