var now_page1 = 1;//0.全局变量，当前页面
var max_page1 = 1;//0.全局变量，最大页面
var key_words1 = "";//0.全局变量，关键字

var now_page2 = 1;//0.全局变量，商品当前页面
var max_page2 = 1;//0.全局变量，商品最大页面
var key_words2 = "";//0.全局变量，商品关键字

var item_id = [];//0.全局变量，商品ID
var item_del_id = [];//0.全局变量，商品ID 需删除
var del_item_id = [];//0.全局变量，商品ID 删除

$(function() {
	//1.加载页面
	loadLocation(now_page1, key_words1);
	
	//2.点击'查询'关键字
	$("#location_query").click(locationQuery);
	
	//3.监听页码点击事件
	$("#location_table").on("click", ".pagelist a", clickPage);
	
	//4.'关键字'回车，等于点击查询
	$("input[name='keywords']").keydown(keywordsKeydown);
	
	//5.监听点击'全选'
	$("#location_table").on("click", "input[name='allId']" ,allId);
	
	//6.监听点击'单选'
	$("#location_table").on("click", "input[name='id[]']" ,soleId);
	
	//7.点击'新建'
	$(".add_btn").click(addButton);
	
	//8.监听点击'修改'
	$("#location_table").on("click", ".amend_btn", amendButton)
	
	//9.关闭弹出框
	$(".new_location_box").on("click", ".location_delbtn, .kw_qx", closeButton);
	
	//10.点击弹出框内的'保存'
	$("#location_save").click(locationSave);
	
	//11.点击'添加商品'，获取商品列表
	$(".add_shopping").click(addShopping);
	
	//12.'关键字'光标移除
	$("input[name='item_keywords']").blur(itemKeywordsBlur);
	
	//13.'关键字'回车
	$("input[name='item_keywords']").keydown(itemKeywordsKeydown);
	
	//14.监听页码点击事件
	$(".content_page").on("click", ".pagelist a", clickContentPage);
	
	//15.监听点击'全选'
	$("#listItem_table").on("click", "input[name='itemAll']" ,itemAll);
	
	//16.监听点击'单选'
	$("#listItem_table").on("click", "input[name='item_id[]']" ,itemSole);
	
	//17.点击'添加商品'框内的'提交'
	$(".button_tj").click(buttonCommit);
	
	//18.点击'库位'弹出框内的'删除'
	$("#item_table").on("click", ".item_delete", itemDelete);
	
	//19.监听点击页面的'删除'
	$("#location_table").on("click", ".location_delete", locationDelete);
})

/**
 * 19.监听点击页面的'删除'
 * @returns
 * @author 赵滨
 */
function locationDelete() {
	
	if(confirm('确定删除吗?')){
		
		//获取库位ID
		var locationId = $(this).parent().parent().data("listStocklocationInfo").id;
		
		//发送请求，提交商品列表
		$.ajax({
			url : "/location/removeLocationById.do",
			type : "post",
//			traditional : true,
			data : {
				"locationId" : locationId, //int
			},
			dataType : "json",
			success : function(result) {
				
				//接受 参数
				var row = result.data
				
				//删除行数大于0
				if (row > 0) {
					showMessage("删除成功!");
					
					//获取页面中的显示条数
					var rows = $("#location_table").find(".location_delete");
					
					//如果只有一条	并且  当前页码不是首页
					if (rows.length == 1 && now_page1 > 1) {
						
						//返回上一页
						now_page1--;
					}
					
					//加载页面
					loadLocation(now_page1, key_words1);
				}
			},
			error : function() {
				showMessage("删除库位失败");
			}
		});
		
	}
	
}

/**
 * 18.点击'库位'弹出框内的'删除'
 * @returns
 * @author 赵滨
 */
function itemDelete() {
	
	//获取该条ID
	var itemId = $(this).parent().parent().data("listItemCommonInfo").id;
	
	//遍历需要删除的id
	for (var i = 0; i < item_del_id.length; i++) {
		
		if (item_del_id[i] == itemId) {
			
			//追加删除数组
			del_item_id.push(itemId);
		}
	}
	
	//删除该条
	$(this).parent().parent().remove();
	
}

/**
 * 17.点击'添加商品'框内的'提交'
 * @returns
 * @author 赵滨
 */
function buttonCommit() {
	
	//如果没有选中商品
	if (item_id.length == 0) {
		
		//创建商品列
		createListItemCommonInfo();
		
		return;
	}
	
	//发送请求，提交商品列表
	$.ajax({
		url : "/location/listItemByItemIds.do",
		type : "post",
		traditional : true,
		data : {
			"itemIds" : item_id, //int
		},
		dataType : "json",
		success : function(result) {
			//接受 集合map
			var listItemCommonInfo = result.data
			
			//创建商品列
			createListItemCommonInfo(listItemCommonInfo);
			
		},
		error : function() {
			showMessage("获取商品列表失败");
		}
	});
	
}

/**
 * 16.监听点击'单选'
 * @returns
 * @author 赵滨
 */
function itemSole() {
	
	//获取当前选框
	var nowChecked = $(this).prop("checked");
	
	//获取当前选框的ID
	var nowCheckedId = $(this).val();
	
	//如果是选中
	if (nowChecked == true) {
		
		//遍历ID数组
		for (var i = 0; i < item_id.length; i++) {
			//如果相同
			if(item_id[i] == nowCheckedId) {
				//删除
				item_id.splice(i, 1);
				break;
		    }
		}
		
		//加入ID数组
		item_id.push(parseInt(nowCheckedId));
		
	} else {
		
		//遍历ID数组
		for (var i = 0; i < item_id.length; i++) {
			//如果相同
			if(item_id[i] == nowCheckedId) {
				//删除
				item_id.splice(i, 1);
				break;
		    }
		}
	}
	
	//获取全选框
	var allChecked = $("input[name='itemAll']").prop("checked");
	
	//获取所有选择框
	var checkeds = $("input[name='item_id[]']");
	
	//如果没选中 并且 全选选中
	if (nowChecked == false && allChecked == true) {
		
		//取消全选
		$("input[name='itemAll']").prop("checked", "");
		
	//否则
	} else {
		
		//遍历所有选择框
		for (var i = 0; i < checkeds.length; i++) {
			
			//如果出现没有选中的
			if (checkeds.eq(i).prop("checked") == false) {
				//跳出
				return;
			}
			
		}
		
		//结尾处，对于没有产生跳出的内容追加全选框
		$("input[name='itemAll']").prop("checked", "true");
		
	}
}

/**
 * 15.点击'全选'
 * @returns
 * @author 赵滨
 */
function itemAll() {
	
	//如果选中
	if ($(this).prop("checked") == true) {
		
		//遍历选择框
	    $("input[name='item_id[]']").each(function() {
	    	 
	    	//选中
	    	$(this).prop("checked", "true");
	    	
	    	//遍历ID数组
			for (var i = 0; i < item_id.length; i++) {
				//如果相同
				if(item_id[i] == $(this).val()) {
					//删除
					item_id.splice(i, 1);
					break;
			    }
			}
			
			//加入ID数组
			item_id.push(parseInt($(this).val()));
	    });
	
	//否则
	} else {
		
		//遍历选择框
	    $("input[name='item_id[]']").each(function() {
	    	
	    	//不选中
	    	$(this).prop("checked", "");
			
			//遍历ID数组
			for (var i = 0; i < item_id.length; i++) {
				//如果相同
				if(item_id[i] == $(this).val()) {
					//删除
					item_id.splice(i, 1);
					break;
			    }
			}
		
	    });
	}

}

/**
 * 14.监听页码点击事件
 * @returns
 * @author 赵滨
 */
function clickContentPage() {

	var aHtml = $(this).html();
	//判断当前页的值
	if(aHtml=="首页"){
		now_page2 = 1;
	}else if(aHtml=="上一页"){
		if(now_page2>1){
			now_page2--;
		}
	}else if(aHtml=="下一页"){
		if(now_page2<max_page2){
			now_page2++;
		}
	}else if(aHtml=="尾页"){
		now_page2 = parseInt(max_page2);
	}else{
		now_page2 = parseInt(aHtml);
	}
	
	//加载页面
	listItemByKeywords(now_page2, key_words2);
	
}

/**
 * 13.'关键字'回车
 * @param e
 * @returns
 * @author 赵滨
 */
function itemKeywordsKeydown(e) {
	
	//获取event对象
	var ev = document.all ? window.event : e;
	
	//如果是回车按键
	if(ev.keyCode==13) {
	
		//光标移除
		$("input[name='item_keywords']").blur();
	 }
}

/**
 * 12.'关键字'光标移除
 * @returns
 * @author 赵滨
 */
function itemKeywordsBlur() {
	
	//获取关键字
	key_words2 = $("input[name='item_keywords']").val();
	
	//重置页码
	now_page2 = 1;
	
	//获取商品
	listItemByKeywords(now_page2, key_words2);
}

/**
 * 11.点击'添加商品'，获取商品列表
 * @returns
 * @author 赵滨
 */
function addShopping() {
	//清空输入框
	$("input[name='item_keywords']").val("");
	//获取关键字
	key_words2 = $("input[name='item_keywords']").val();
	//重置页码
	now_page2 = 1;
	//重置商品ID
	item_id = [];
	//获取 商品 块
	var itemTr = $("#item_table").find("tr");
	//遍历商品
	for (var i = 0; i < itemTr.length; i++) {
		//如果 对象不为空
		if (itemTr.eq(i).data("listItemCommonInfo") != null) {
			//加入
			item_id.push(itemTr.eq(i).data("listItemCommonInfo").id);
		}
	}
	
	//获取商品
	listItemByKeywords(now_page2, key_words2);
}

/**
 * 11.1.获取商品
 * @param nowPage
 * @param keywords
 * @returns
 * @author 赵滨
 */
function listItemByKeywords(nowPage, keywords) {
	
	//发送请求，获取商品列表
	$.ajax({
		url : "/location/listItemByKeywords.do",
		type : "post",
		//traditional : true,
		data : {
			"nowPage" : nowPage, //int
			"keywords" : keywords //String
		},
		dataType : "json",
		success : function(result) {
			//接受 集合map
			var map = result.data
			
			//创建 商品集合
			createListItemByKeywords(map);
			
		},
		error : function() {
			showMessage("获取商品列表失败");
		}
	});
	
}

/**
 * 11.2.创建 商品集合
 * @param map
 * @returns
 * @author 赵滨
 */
function createListItemByKeywords(map) {
	//最大页码
	max_page2 = map.maxPage;
	//清空页面
	$("#listItem_table").children().remove();
	
	//定义拼接块
	var tr = '';
	//第一部分
	tr += '<tr>';
	tr += '<th width="40">';
	tr += '<input type="checkbox" name="itemAll" value="" class="check_coding" />';
	tr += '</th>';
	tr += '<th width="100">';
	tr += '商品编码';
	tr += '</th>';
	tr += '<th width="200">';
	tr += '商品名称';
	tr += '</th>';
	tr += '<th width="200">';
	tr += '商品规格';
	tr += '</th>';
	tr += '<th width="80">';
	tr += '商品价格';
	tr += '</th>';
	tr += '<th width="80">';
	tr += '单位';
	tr += '</th>';
	tr += '</tr>';
	//追加
	$("#listItem_table").append(tr);
	
	//第二部分
	tr = '';
	//获取商品集合
	var listItemCommonInfo = map.listItemCommonInfo;
	//遍历商品集合
	for (var i = 0; i < listItemCommonInfo.length; i++) {
		tr = '';
		tr += '<tr>';
		tr += '<td>';
		tr += '<input type="checkbox" name="item_id[]" value="';
		tr += listItemCommonInfo[i].id;		//商品ID
		tr += '" class="check_coding" ';
		//遍历商品ID数组,选中存在的商品
		for (var j = 0; j < item_id.length; j++) {
			//如果数组中 有 这条商品
			if (item_id[j] == listItemCommonInfo[i].id) {
				tr += 'checked';
			}
		}
		tr += ' />';
		tr += '</td>';
		tr += '<td>';
		tr += listItemCommonInfo[i].erpItemNum;	//商品编码
		tr += '</td>';
		tr += '<td>';
		tr += '<p>';
		tr += listItemCommonInfo[i].name;		//商品名称
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		tr += '<p>';
		tr += listItemCommonInfo[i].color+" "+listItemCommonInfo[i].size;	//商品规格
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		//如果为空
		if (listItemCommonInfo[i].normalPrice == null) {
			tr += '';

		} else {
			tr += listItemCommonInfo[i].normalPrice;	//商品价格
		}
		tr += '</td>';
		tr += '<td>';
		tr += '';		//单位
		tr += '</td>';
		tr += '</tr>';
		//转换对象
		$tr = $(tr);
		//绑定对象
		$tr.data("listItemCommonInfo", listItemCommonInfo[i]);
		//插入 商品栏目 标题
		$("#listItem_table").append($tr);
	}

    if (listItemCommonInfo.length > 0) {
	    //创建页码
        createPageList();
    }

	//获取所有选择框
	var checkeds = $("input[name='item_id[]']");
	//遍历所有选择框
	for (var i = 0; i < checkeds.length; i++) {
		//如果出现没有选中的
		if (checkeds.eq(i).prop("checked") == false) {
			//跳出
			return;
		}
	}
	//结尾处，对于没有产生跳出的内容追加全选框
	$("input[name='itemAll']").prop("checked", "true");

    function createPageList() {
        //第三部分
        $(".content_page").children().remove();
        tr = '';
        //开始部分
        tr += '<div class="pagelist"><a href="javascript:void(0)">首页</a><a href="javascript:void(0)">上一页</a> ';
        //中间部分
        if (max_page2 > 5) {
            //如果是页码前两个
            if (now_page2 <= 3) {
                //循环前三页码
                for (var i = 1; i < 4; i++) {

                    //如果选中当前页码，则变成蓝色背景
                    if (i == now_page2) {
                        tr += '<span class="current" style="cursor:default">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    } else {
                        tr += '<a href="javascript:void(0)">';
                        tr += i;
                        tr += '</a>';
                    }
                }
                //写出最后两个
                tr += '<a href="javascript:void(0)">';
                tr += 4;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += 5;
                tr += '</a>……';

                //如果是页码最中间
            } else if (now_page2 >= 4 && now_page2 <= max_page2 - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += now_page2 - 2;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += now_page2 - 1;
                tr += '</a>';

                //页码中间选中的
                tr += '<span class="current" style="cursor:default">';
                tr += now_page2;
                tr += '</span>';

                //页码后两个
                tr += '<a href="javascript:void(0)">';
                tr += now_page2 + 1;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += now_page2 + 2;
                tr += '</a>……';
                //如果是页码后两个
            } else if (now_page2 > max_page2 - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += max_page2 - 4;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += max_page2 - 3;
                tr += '</a>';

                //循环后三页
                for (var i = max_page2 - 2; i <= max_page2; i++) {
                    //如果选中当前页码，则变成蓝色背景
                    if (i == now_page2) {
                        tr += '<span class="current" style="cursor:default">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    } else {
                        tr += '<a href="javascript:void(0)">';
                        tr += i;
                        tr += '</a>';
                    }
                }
            }

            //否则页数小于5页
        } else {
            var i = 1;
            //循环页码
            while (i <= max_page2) {
                //如果选中当前页码，则变成蓝色背景
                if (i == now_page2) {
                    tr += '<span class="current" href="javascript:void(0)" style="cursor:default">';
                    tr += i;
                    tr += '</span>';

                    //否则页码为白色背景
                } else {
                    tr += '<a href="javascript:void(0)">';
                    tr += i;
                    tr += '</a>';
                }
                i++;
            }
        }

        //结束部分
        tr += '<a href="javascript:void(0)">下一页</a><a href="javascript:void(0)">尾页</a></div>';

        //加入页面
        $(".content_page").append(tr);
    }
}

/**
 * 10.点击弹出框内的'保存'
 * @returns
 * @author 赵滨
 */
function locationSave() {

	//获取库位ID
	var locationId = $("#hidden_locationId").val();
	
	//填写库位编码
	var userStocklocationCode = $("#text_userStocklocationCode").val();
	
	//填写库位名称
	var name = $("#text_name").val();

    //如果没有库位编码
    if (userStocklocationCode.trim() == "") {
        showMessage("请填写库位编码");
        return;
    }

	//如果没有库位名称
	if (name.trim() == "") {
		showMessage("请填写库位名称");
		return;
	}
	
	//重置商品ID
	var itemIds = [];
	
	//获取 商品 块
	var itemTr = $("#item_table").find("tr");
	
	//遍历商品
	for (var i = 0; i < itemTr.length; i++) {
		
		//如果 对象不为空
		if (itemTr.eq(i).data("listItemCommonInfo") != null) {
			//加入
			itemIds.push(itemTr.eq(i).data("listItemCommonInfo").id);
		}
		
	}
	
	//发送请求，保存仓库
	$.ajax({
		url : "/location/saveAddUpdate.do",
		type : "post",
		traditional : true,
		data : {
			"locationId" : locationId, 
			"userStocklocationCode" : userStocklocationCode,
			"name" : name,
			"itemIds" : itemIds,
			"itemDelIds" : del_item_id
		},
		dataType : "json",
		success : function(result) {
			//接受行数
			var row = result.data
			
			//如果大于1
			if (row > 0) {
				showMessage("保存库位成功");
				
				//加载页面
				loadLocation(now_page1, key_words1);
				
				//弹出框 关闭
				$(".new_location_box").css("display","none");
				
			} else if (row == 0) {
				
				showMessage("保存的仓库数量为0");
				
				//弹出框 关闭
				$(".new_location_box").css("display","none");
				
			}
			
		},
		error : function() {
			showMessage("保存库位失败");
		}
	});
}

/**
 * 9.关闭弹出框
 * @returns
 * @author 赵滨
 */
function closeButton() {
	
	//弹出框 关闭
	$(".new_location_box").css("display","none");
	
}

/**
 * 8.监听点击'修改'
 * @returns
 * @author 赵滨
 */
function amendButton() {
	
	//弹出框
	$(".new_location_box").css("display","block");
	
	//更改标题
	$(".new_location_box").find("span:first").html("修改库位");
	
	//获取库存对象
	var stocklocationInfo = $(this).parent().parent().data("listStocklocationInfo");
	
	//填写库位ID
	$("#hidden_locationId").val(stocklocationInfo.id);
	
	//填写库位编码
	$("#text_userStocklocationCode").val(stocklocationInfo.userStocklocationCode);
	
	//填写库位名称
	$("#text_name").val(stocklocationInfo.name);
	
	//发送请求，获取商品内容
	$.ajax({
		url : "/location/listItemByLocationId.do",
		type : "post",
		//traditional : true,
		data : {
			"locationId" : stocklocationInfo.id //bigint
		},
		dataType : "json",
		success : function(result) {
			
			//获取商品集合
			var listItemCommonInfo = result.data
			
			//创建 商品集合
			createListItemCommonInfo(listItemCommonInfo);

			//重置商品ID
			item_del_id = [];
			
			//遍历商品
			for (var i = 0; i < listItemCommonInfo.length; i++) {
				
				//如果 对象不为空
				if (listItemCommonInfo[i] != null) {
					//加入
					item_del_id.push(parseInt(listItemCommonInfo[i].id));
				}
				
			}
		},
		error : function() {
			showMessage("加载商品内容失败");
		}
	});
	
}

/**
 * 8.1.创建 商品集合
 * @param listItemCommonInfo 商品集合
 * @returns
 * @author 赵滨
 */
function createListItemCommonInfo(listItemCommonInfo) {
	
	//清空 存放物品
	$("#item_table").children().remove();
	
	//定义标题
	var tr = '';
	
	tr += '<tr>';
	tr += '<th width="120">商品编码</th>';
	tr += '<th width="260">商品名称</th>';
	tr += '<th width="260">商品规格</th>';
	tr += '<th width="120">操作</th>';
	tr += '</tr>';
	
	//插入 商品栏目 标题
	$("#item_table").append(tr);
	
	//如果为空，跳出
	if (listItemCommonInfo == null) {
		return;
	}
	
	//遍历商品
	for (var i = 0; i < listItemCommonInfo.length; i++) {
		
		tr = '';
		
		tr += '<tr>';
		tr += '<td>';
		tr += listItemCommonInfo[i].erpItemNum;		//商品编码
		tr += '</td>';
		tr += '<td>';
		tr += '<p>';
		tr += listItemCommonInfo[i].name;			//商品名称
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		tr += '<p>';
		tr += listItemCommonInfo[i].color +" "+ listItemCommonInfo[i].size;		//商品规格
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		tr += '<a href="javascript:;" class="button border-main item_delete">删除</a>';
		tr += '</td>';
		tr += '</tr>';
		
		//转换对象
		$tr = $(tr);
		
		//绑定对象
		$tr.data("listItemCommonInfo", listItemCommonInfo[i]);
		
		//插入 商品栏目 标题
		$("#item_table").append($tr);
		
	}
}

/**
 * 7.点击'新建'
 * @returns
 * @author 赵滨
 */
function addButton() {
	
	//弹出框
	$(".new_location_box").css("display","block");
	
	//更改标题
	$(".new_location_box").find("span:first").html("新增库位");
	
	//填写库位ID
	$("#hidden_locationId").val("");
	
	//填写库位编码
	$("#text_userStocklocationCode").val("");
	
	//填写库位名称
	$("#text_name").val("");
	
	//清空 存放物品
	$("#item_table").children().remove();
	
	//定义标题
	var tr = '';
	
	tr += '<tr>';
	tr += '<th width="120">商品编码</th>';
	tr += '<th width="260">商品名称</th>';
	tr += '<th width="260">商品规格</th>';
	tr += '<th width="120">操作</th>';
	tr += '</tr>';
	
	//插入 商品栏目 标题
	$("#item_table").append(tr);
}

/**
 * 7.1.添加商品
 * @author 腾宁
 */
$(function() {
	/*******/
	var shopping_add_box=$(".shopping_add_box"),
	shopping_delbtn=$(".shopping_delbtn"),
	button_tj=$(".button_tj"),
	add_shopping=$(".add_shopping");

	add_shopping.click(function(){
		shopping_add_box.css("display","block");
	});
	
	shopping_delbtn.click(function(){
		shopping_add_box.css("display","none");
	});
	
	button_tj.click(function(){
		shopping_add_box.css("display","none");
	});
})

/**
 * 6.监听点击'单选'
 * @returns
 * @author 赵滨
 */
function soleId() {
	
	//获取当前选框
	var nowChecked = $(this).prop("checked");
	
	//获取全选框
	var allChecked = $("input[name='allId']").prop("checked");
	
	//获取所有选择框
	var checkeds = $("input[name='id[]']");
	
	//如果没选中 并且 全选选中
	if (nowChecked == false && allChecked == true) {
		
		//取消全选
		$("input[name='allId']").prop("checked", "");
		
	//否则
	} else {
		
		//遍历所有选择框
		for (var i = 0; i < checkeds.length; i++) {
			
			//如果出现没有选中的
			if (checkeds.eq(i).prop("checked") == false) {
				//跳出
				return;
			}
			
		}
		
		//结尾处，对于没有产生跳出的内容追加全选框
		$("input[name='allId']").prop("checked", "true");
		
	}
}

/**
 * 5.点击'全选'
 * @returns
 * @author 赵滨
 */
function allId() {
	
	//如果选中
	if ($(this).prop("checked") == true) {
		
		//遍历选择框
	    $("input[name='id[]']").each(function() {
	    	 
	    	//选中
	    	$(this).prop("checked", "true");
	    });
	
	//否则
	} else {
		
		//遍历选择框
	    $("input[name='id[]']").each(function() {
	    	
	    	//不选中
	    	$(this).prop("checked", "");
	    });
	    
	}
}

/**
 * 4.'关键字'回车，等于点击查询
 * @returns
 * @author 赵滨
 */
function keywordsKeydown(e) {
	
	//获取event对象
	var ev = document.all ? window.event : e;
	
	//如果是回车按键
	if(ev.keyCode==13) {
	
		//光标移除
		$("input[name='keywords']").blur();
		
		//点击'查询'关键字
		$("#location_query").click();
		
	 }
}

/**
 * 3.监听页码点击事件
 * @returns
 * @author 赵滨
 */
function clickPage() {

	var aHtml = $(this).html();
	//判断当前页的值
	if(aHtml=="首页"){
		now_page1 = 1;
	}else if(aHtml=="上一页"){
		if(now_page1>1){
			now_page1--;
		}
	}else if(aHtml=="下一页"){
		if(now_page1<max_page1){
			now_page1++;
		}
	}else if(aHtml=="尾页"){
		now_page1 = parseInt(max_page1);
	}else{
		now_page1 = parseInt(aHtml);
	}
	
	//加载页面
	loadLocation(now_page1, key_words1);
	
}

/**
 * 2.点击'查询'关键字
 * @returns
 * @author 赵滨
 */
function locationQuery() {
	
	//获取关键字
	key_words1 = $("input[name='keywords']").val().trim();
	
	//页码初始化
	now_page1 = 1;
	
	//加载页面
	loadLocation(now_page1, key_words1);
	
}

/**
 * 1.加载页面
 * @returns
 * @author 赵滨
 */
function loadLocation(nowPage, keywords) {
	
	//发送请求，获取页面内容
	$.ajax({
		url : "/location/loadLocation.do",
		type : "post",
		//traditional : true,
		data : {
			"nowPage" : nowPage, //int
			"keywords" : keywords //String
		},
		dataType : "json",
		success : function(result) {
			//接受栏目集合
			var map = result.data
			
			//创建页面
			createLocation(map);
			
		},
		error : function() {
			showMessage("加载库位列表失败");
		}
	});
	
}

/**
 * 1.1.创建页面
 * @returns
 * @author 赵滨
 */
function createLocation(map) {
	//加载最大页数
	max_page1 = map.maxPage;
	//清空页面
	$("#location_table").children().remove();
	
	//第一部分
	var tr = '';
	tr += '<tr>';
	tr += '<th width="80"><input type="checkbox" name="allId" value="" class="check_coding" />序号</th>';
	tr += '<th>库位编码</th>';
	tr += '<th width="30%">库位名称</th>';
	tr += '<th width="30%">商品列表</th>';
	tr += '<th>操作</th>';
	tr += '</tr>';
	//追加
	$("#location_table").append(tr);
	
	//第二部分
	tr = '';
	//获取库位列表
	var listStocklocationInfo = map.listStocklocationInfo;
	//遍历库位列表
	for (var i = 0; i < listStocklocationInfo.length; i++) {
		tr = '';
		tr += '<tr>';
		tr += '<td><input type="checkbox" name="id[]" value="'+listStocklocationInfo[i].id+'" class="check_coding" />';
		tr += (i + 1) + ((parseInt(now_page1) - 1) * parseInt(map.rows));	//序号
		tr += '</td>';
		tr += '<td>';
		tr += listStocklocationInfo[i].userStocklocationCode;	//库位编码
		tr += '</td>';
		tr += '<td>';
		tr += '<p style="width: 100%;">';
		tr += listStocklocationInfo[i].name;	//库位名称
		tr += '</p>';
		tr += '</td>';  
		tr += '<td>';
		tr += '<p style="width: 100%;">';
		tr += '';	//商品列表
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		tr += '<a href="javascript:;" class="button border-main a_btn_sync amend_btn"> 修改</a>';
		tr += '<a href="javascript:;" class="button border-red a_btn_sync location_delete"> 删除</a>';
		tr += '</td>';
		tr += '</tr>';
		//转换对象
		$tr = $(tr);
		//绑定对象
		$tr.data("listStocklocationInfo", listStocklocationInfo[i]);
		//追加
		$("#location_table").append($tr);
	}

    if (listStocklocationInfo.length > 0) {
        //创建页码
        createPageList();
    }

    function createPageList() {
        //第三部分
        tr = '';
        //开始部分
        tr += '<tr><td colspan="10">';
        tr += '<div class="pagelist"><a href="javascript:void(0)">首页</a><a href="javascript:void(0)">上一页</a> ';
        //中间部分
        if (max_page1 > 5) {
            //如果是页码前两个
            if (now_page1 <= 3) {
                //循环前三页码
                for (var i = 1; i < 4; i++) {

                    //如果选中当前页码，则变成蓝色背景
                    if (i == now_page1) {
                        tr += '<span class="current" style="cursor:default">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    } else {
                        tr += '<a href="javascript:void(0)">';
                        tr += i;
                        tr += '</a>';
                    }
                }
                //写出最后两个
                tr += '<a href="javascript:void(0)">';
                tr += 4;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += 5;
                tr += '</a>……';

                //如果是页码最中间
            } else if (now_page1 >= 4 && now_page1 <= max_page1 - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += now_page1 - 2;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += now_page1 - 1;
                tr += '</a>';

                //页码中间选中的
                tr += '<span class="current" style="cursor:default">';
                tr += now_page1;
                tr += '</span>';

                //页码后两个
                tr += '<a href="javascript:void(0)">';
                tr += now_page1 + 1;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += now_page1 + 2;
                tr += '</a>……';
                //如果是页码后两个
            } else if (now_page1 > max_page1 - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += max_page1 - 4;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += max_page1 - 3;
                tr += '</a>';

                //循环后三页
                for (var i = max_page1 - 2; i <= max_page1; i++) {
                    //如果选中当前页码，则变成蓝色背景
                    if (i == now_page1) {
                        tr += '<span class="current" style="cursor:default">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    } else {
                        tr += '<a href="javascript:void(0)">';
                        tr += i;
                        tr += '</a>';
                    }
                }
            }

            //否则页数小于5页
        } else {
            var i = 1;
            //循环页码
            while (i <= max_page1) {
                //如果选中当前页码，则变成蓝色背景
                if (i == now_page1) {
                    tr += '<span class="current" href="javascript:void(0)" style="cursor:default">';
                    tr += i;
                    tr += '</span>';

                    //否则页码为白色背景
                } else {
                    tr += '<a href="javascript:void(0)">';
                    tr += i;
                    tr += '</a>';
                }
                i++;
            }
        }

        //结束部分
        tr += '<a href="javascript:void(0)">下一页</a><a href="javascript:void(0)">尾页</a></div>';
        tr += '</td></tr>';

        //加入页面
        $("#location_table").append(tr);
    }
}













