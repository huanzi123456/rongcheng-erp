var now_page = 1;//0.全局变量，当前页面
var max_page = 1;//0.全局变量，最大页面
var key_words = "";//0.全局变量，关键字
var is_alert_stock = false;//0.全局变量，警戒线
var warehouse_info_id = "";//0.全局变量，仓库
var location_item_stock_id = [];//0.全局变量，ID

$(function() {
	//1.加载仓库
	loadWarehouseInfo();
	
	//2.加载页面
	loadInventoryState(now_page, key_words, is_alert_stock, warehouse_info_id);
	
	//3.点击'查询'关键字
	$("#inventoryState_query").click(inventoryStateQuery);
	
	//4.点击'低于警戒线库存商品'
	$("#inventoryState_cordon").click(inventoryStateCordon);
	
	//5.监听页码点击事件
	$("#all_price").on("click", ".pagelist a", clickPage);
	
	//6.点击'仓库'，选择仓库内容
	$("#inventoryState_warehouse").change(clickWarehouse);
	
	//7.'关键字'回车，等于点击查询
	$("input[name='keywords']").keydown(keywordsKeydown);
	
	//8.点击'警戒库存'，显示弹出框
	$("#inventoryState_table").on("click", ".alert_btn", alertStock);
	
	//9.点击'批量设置警戒库存'，显示弹出
    $("#alertStocks").click(alertStocks);
	
	//10.'警戒库存',点击'确定'
	$("#alertStock_confirm").click(alertStockConfirm);
	
	//11.监听点击'全选'
	$("#inventoryState_table").on("click", "input[name='allId']" ,allId);
	
	//12.监听点击'单选'
	$("#inventoryState_table").on("click", "input[name='id[]']" ,soleId);
})

/**
 * 12.监听点击'单选'
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
 * 11.点击'全选'
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
 * 10.'警戒库存',点击'确定'
 * @returns
 * @author 赵滨
 */
function alertStockConfirm() {
	//获取警戒库存
	var alertStockNum = $("#alertStock_num").val().trim();
	
	//如果不是数字
	if (!isPInt(alertStockNum)) {
		showMessage("请输入正整数");
		return;
	}
	
	//如果没有填写内容
	if (alertStockNum == "" || alertStockNum == null) {
		showMessage("请输入正整数");
		return;
	}
	
	//关闭弹出框
	$(".inventory_state_box").css("display", "none");

	//发送请求，修改警戒库存
	$.ajax({
		url : "/inventoryState/updateInventoryState.do",
		type : "post",
		traditional : true,
		data : {
			"alertStockNum" : alertStockNum, //int
			"locationItemStockId" : location_item_stock_id, //bigint[]
		},
		dataType : "json",
		success : function(result) {
			//接受rows
			var rows = result.data
			//如果是修改完成
			if (rows > 0) {
				//加载页面
				loadInventoryState(now_page, key_words, is_alert_stock, warehouse_info_id);
                showMessage("修改警戒库存成功");
			} else {
                showMessage("0条警戒库存被修改");
            }
			
		},
		error : function() {
			showMessage("修改警戒库存失败");
		}
	});
	
}

/**
 * 10.1.正整数
 * @param str
 * @returns
 * @author 赵滨
 */
function isPInt(str) {
    var g = /^[0-9]*$/;
    return g.test(str);
}

/**
 * 9.点击'批量设置警戒库存'，显示弹出
 * @returns
 * @author 赵滨
 */
function alertStocks() {
	
	//获取商品ID
	var temp = [];
	
	//遍历所有选择框
    $("input[name='id[]']").each(function() {
    	 
    	//如果选中
        if ($(this).prop("checked") == true) {
        	
        	//加入数组
        	temp.push(parseInt($(this).val()));
        	
        }  
    });
    
    //如果没有选中
    if (temp.length == 0) {
    	
    	showMessage("请选择商品");
    	return;
    	
    }
    
    //显示 警戒库 弹出框
	$(".inventory_state_box").css("display", "block");
    
    //赋值ID
    location_item_stock_id = temp;
    
    //输入库存框内
	$("#alertStock_num").val("");
	
}

/**
 * 8.点击'警戒库存'，显示弹出框
 * @returns
 * @author 赵滨
 */
function alertStock() {
	
	//显示 警戒库 弹出框
	$(".inventory_state_box").css("display", "block");
	
	//获取商品ID
	var temp = [];
	
	//赋值商品ID
	temp.push(parseInt($(this).parent().parent().find("input[name='id[]']").val()));
	
	//赋值ID
    location_item_stock_id = temp;
	
	//获取 警戒量
	var itemCommonAlertStock = $(this).parent().parent().data("itemCommonAlertStock");
	
	//输入库存框内
	$("#alertStock_num").val(itemCommonAlertStock);
	
}

/**
 * 7.'关键字'回车，等于点击查询
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
		$("#inventoryState_query").click();
		
	 }
}

/**
 * 6.点击'仓库'，选择仓库内容
 * @returns
 * @author 赵滨
 */
function clickWarehouse() {

	//获取 并 赋值
	warehouse_info_id = $("#inventoryState_warehouse").find("option:selected").val();
	
	//页码初始化
	now_page = 1;
	
	//关键字清空
	key_words = "";
	
	//关键字清空
	$("input[name='keywords']").val("");
	
	//警戒线关闭
	is_alert_stock = false;
	
	//加载页面
	loadInventoryState(now_page, key_words, is_alert_stock, warehouse_info_id);
	
}

/**
 * 5.监听页码点击事件
 * @returns
 * @author 赵滨
 */
function clickPage() {
    var aHtml = $(this).html();
    //判断当前页的值
    if (aHtml == "首页") {
        if (now_page == 1) {
            return;
        }
        now_page = 1;
    } else if (aHtml == "上一页") {
        if (now_page > 1) {
            now_page--;
        } else {
            return;
        }
    } else if (aHtml == "下一页") {
        if (now_page < max_page) {
            now_page++;
        } else {
            return;
        }
    } else if (aHtml == "尾页") {
        if (now_page == parseInt(max_page)) {
            return;
        }
        now_page = parseInt(max_page);
    } else if (aHtml == "跳转") {
        var goto = $("input[name='goto']").val();
        if (!/^[0-9]*$/.test(goto)) {
            showMessage("请输入正整数");
            return;
        }
        if (goto == "" || goto == null) {
            return;
        }
        if (goto == 0) {
            return;
        }
        if (goto > max_page) {
            showMessage("最大页数为" + max_page + "页，跳转页数不能超过最大页数");
            return;
        } else if (now_page == goto) {
            return;
        }
        now_page = parseInt(goto);
    } else {
        now_page = parseInt(aHtml);
    }
	
	//加载页面
	loadInventoryState(now_page, key_words, is_alert_stock, warehouse_info_id);
}

/**
 * 4.点击'低于警戒线库存商品'
 * @returns
 * @author 赵滨
 */
function inventoryStateCordon() {
	
	//关键字清空
	$("input[name='keywords']").val("");
	
	//关键字清空
	key_words = "";
	
	//选择警戒线
	is_alert_stock = true;
	
	//页码初始化
	now_page = 1;
	
	//加载页面
	loadInventoryState(now_page, key_words, is_alert_stock, warehouse_info_id);
}

/**
 * 3.点击'查询'关键字
 * @returns
 * @author 赵滨
 */
function inventoryStateQuery() {
	
	//获取关键字
	key_words = $("input[name='keywords']").val().trim();
	
	//不选择警戒线
	is_alert_stock = false;
	
	//页码初始化
	now_page = 1;
	
	//加载页面
	loadInventoryState(now_page, key_words, is_alert_stock, warehouse_info_id);
}

/**
 * 2.加载页面
 * @param nowPage 当前页面
 * @param keywords 搜索关键字
 * @returns
 * @author 赵滨
 */
function loadInventoryState(nowPage, keywords, isAlertStock, warehouseInfoId) {
	
	//发送请求，获取页面内容
	$.ajax({
		url : "/inventoryState/loadInventoryState.do",
		type : "post",
		//traditional : true,
		data : {
			"isAlertStock" : isAlertStock, //boolean
			"nowPage" : nowPage, // int
			"keywords" : keywords, //String
			"warehouseInfoId" : warehouseInfoId	//bigint
		},
		dataType : "json",
		success : function(result) {
			//接受栏目集合
			var map = result.data
			
			//创建页面
			createInventoryState(map);
			
		},
		error : function() {
			showMessage("加载库存状态失败");
		}
	});
}

/**
 * 2.1.创建页面
 * @param map
 * @returns
 * @author 赵滨
 */
function createInventoryState(map) {
	//加载最大页数
	max_page = map.maxPage;
	//行数
	var rows = map.rows;
    //清空页面
	$("#inventoryState_table").children().remove();
	//第一部分
	var tr = '';
	tr += '<tr>';
	tr += '<th width="80"><input type="checkbox"  name="allId" value="" class="check_coding" />序号</th>';
	tr += '<th>商品编码</th>';
	tr += '<th width="100">商品名称</th>';
	tr += '<th>商品条码</th>';
	tr += '<th width="100">商品规格</th>';
	tr += '<th width="100">商品备注</th>';
	tr += '<th>总量</th>';
	tr += '<th>订单占用量</th>';
	tr += '<th>可用量</th>';
	tr += '<th>30天销量</th>';
	tr += '<th>警戒量</th>';
	tr += '<th>操作</th>';
	tr += '</tr>';
	
	//追加
	$("#inventoryState_table").append(tr);
	
	//第二部分
	tr = '';
	//获取库存状态
	var listItemCommonStock = map.listItemCommonStock;
	//总量
	var sumQuantity = 0;
	//占用量
	var useQuantity = 0;
	//可用量
	var availableQuantity = 0;
	
	//遍历集合
	for (var i = 0; i < listItemCommonStock.length; i++) {
		tr = '';
		tr += '<tr>';
		tr += '<td><input type="checkbox" name="id[]" value="'+listItemCommonStock[i].id+'" class="check_coding" />';
		tr += (i + 1) + ((parseInt(now_page) - 1) * parseInt(rows));	//序号
		// tr += listItemCommonStock[i].itemId;	//ID
		tr += '</td>';
		tr += '<td>';
		tr += listItemCommonStock[i].erpItemNum;	//编码
		tr += '</td>';
		tr += '<td>';
		tr += '<p title="';
        tr += listItemCommonStock[i].name;	//名称
        tr += '">';
		tr += listItemCommonStock[i].name;	//名称
		tr += '</p>';
		tr += '</td> '; 
		tr += '<td>';
		tr += listItemCommonStock[i].barCode;	//条码
		tr += '</td>';  
		tr += '<td>';
		tr += '<p>';
		tr += listItemCommonStock[i].color+' '+listItemCommonStock[i].size;	//规格
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		tr += '<p>';
		tr += listItemCommonStock[i].packageCondition;	//商品包装（是否破损，包装完整等）
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		tr += listItemCommonStock[i].sumQuantity;	//总量
		
		sumQuantity += listItemCommonStock[i].sumQuantity;
		
		tr += '</td>';
		tr += '<td>';
		tr += listItemCommonStock[i].useQuantity;	//占用量

		useQuantity += listItemCommonStock[i].useQuantity;
		
		tr += '</td>';
		tr += '<td>';
		tr += listItemCommonStock[i].sumQuantity - listItemCommonStock[i].useQuantity;	//可用量

		availableQuantity += listItemCommonStock[i].sumQuantity - listItemCommonStock[i].useQuantity;
		
		tr += '</td>';
		tr += '<td>';
		tr += listItemCommonStock[i].salesQuantity;	//30天销量

		tr += '</td>';
		tr += '<td>';
		tr += listItemCommonStock[i].alertStock;	//警戒量

		tr += '</td>';
		tr += '<td>';
		tr += '<a href="javascript:;" class="button border-main a_btn_sync alert_btn"> 警戒库存</a>';
		tr += '</td>';
		tr += '</tr>';
		
		//转换对象
		$tr = $(tr);
		
		//绑定id
		$tr.data("itemCommonAlertStock", listItemCommonStock[i].alertStock);
		
		//追加
		$("#inventoryState_table").append($tr);
	}
	
	//第三部分
	
	//清空
	$("#all_price").children().remove();
	tr = '';
	tr += '<li>';
	tr += '<span class="span">';
	tr += '当前页面总库存';
	tr += '</span>';
	tr += '<span class="span">';
	tr += sumQuantity;
	tr += '</span>';
	tr += '<span class="span">';
	tr += useQuantity;
	tr += '</span>';
	tr += '<span class="span">';
	tr += availableQuantity;
	tr += '</span>';
	tr += '</li>';
	
	//获取总量
	var itemCommonStock = map.itemCommonStock;
	//如果存在总量
	if (itemCommonStock != null) {
		tr += '<li>';
		tr += '<span class="span">';
		tr += '仓内总库存';
		tr += '</span>';
		tr += '<span class="span">';
		tr += itemCommonStock.sumQuantity;
		tr += '</span>';
		tr += '<span class="span">';
		tr += itemCommonStock.useQuantity;
		tr += '</span>';
		tr += '<span class="span">';
		tr += itemCommonStock.sumQuantity - itemCommonStock.useQuantity;
		tr += '</span>';
		tr += '</li>';

	} else {
		tr += '<li>';
		tr += '<span class="span">';
		tr += '仓内总库存';
		tr += '</span>';
		tr += '<span class="span">';
		tr += 0;
		tr += '</span>';
		tr += '<span class="span">';
		tr += 0;
		tr += '</span>';
		tr += '<span class="span">';
		tr += 0;
		tr += '</span>';
		tr += '</li>';
	}

    if (listItemCommonStock.length > 0) {
	    //创建页码
        createPageList();
    }

	function createPageList() {
        //开始部分
        tr += '<li style="height: 50px;line-height: normal;">';
        tr += '<div class="pagelist"><a href="javascript:void(0)">首页</a><a href="javascript:void(0)">上一页</a> ';
        //中间部分
        if (max_page > 5) {
            //如果是页码前两个
            if (now_page <= 3) {
                //循环前三页码
                for (var i = 1; i < 4; i++) {

                    //如果选中当前页码，则变成蓝色背景
                    if(i==now_page){
                        tr += '<span class="current" style="cursor:default">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    }else{
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
            } else if (now_page >= 4 && now_page <= max_page - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += now_page - 2;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += now_page - 1;
                tr += '</a>';

                //页码中间选中的
                tr += '<span class="current" style="cursor:default">';
                tr += now_page;
                tr += '</span>';

                //页码后两个
                tr += '<a href="javascript:void(0)">';
                tr += now_page + 1;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += now_page + 2;
                tr += '</a>……';
                //如果是页码后两个
            } else if (now_page > max_page - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += max_page - 4;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += max_page - 3;
                tr += '</a>';

                //循环后三页
                for (var i = max_page - 2; i <= max_page; i++) {
                    //如果选中当前页码，则变成蓝色背景
                    if(i==now_page){
                        tr += '<span class="current" style="cursor:default">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    }else{
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
            while (i <= max_page) {
                //如果选中当前页码，则变成蓝色背景
                if(i==now_page){
                    tr += '<span class="current" href="javascript:void(0)" style="cursor:default">';
                    tr += i;
                    tr += '</span>';

                    //否则页码为白色背景
                }else{
                    tr += '<a href="javascript:void(0)">';
                    tr += i;
                    tr += '</a>';
                }
                i++;
            }
        }

        //结束部分
        tr += '<a href="javascript:void(0)">下一页</a><a href="javascript:void(0)">尾页</a>';
        tr += '<input name="goto" style="border-radius: 3px;' +
            'border: 1px solid #dfdfdf;padding: 5px 5px;width: 3.5em;font: inherit;text-align: center;">';
        tr += '<a href="javascript:void(0)">跳转</a></div>';
        tr += '</li>';

        //加入页面
        $("#all_price").append(tr);
    }
}

/**
 * 1.加载仓库
 * @returns
 * @author 赵滨
 */
function loadWarehouseInfo() {
	
	//发送请求，获取页面内容
	$.ajax({
		url : "/inventoryState/loadWarehouseInfo.do",
		type : "post",
		//traditional : true,
		data : {},
		dataType : "json",
		success : function(result) {
			//接受栏目集合
			var list = result.data
			
			//创建仓库
			createWarehouseInfo(list);
			
		},
		error : function() {
			showMessage("加载仓库失败");
		}
	});
	
}

/**
 * 1.1.创建仓库
 * @param list
 * @returns
 * @author 赵滨
 */
function createWarehouseInfo(list) {
	
	//清空
	$("#inventoryState_warehouse").children().remove();
	
	//默认第一个
	var op = '<option value="">全部仓库</option>';

	//遍历
	for (var i = 0; i < list.length; i++) {
		
		op += '<option value="';
		op += list[i].id;
		op += '">';
		op += list[i].warehouseName;
		op += '</option>';
		
	}
	
	//追加
	$("#inventoryState_warehouse").append(op);
	
}







