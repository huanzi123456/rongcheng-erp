//0.全局变量，当前页面
var now_page = 1;

//0.全局变量，最大页面
var max_page = 1;

//0.全局变量，关键字
var key_words = "";

//0.全局变量，该仓库是否被启用
var warehouse_status = null;

//0.全局变量，商品ID
var item_common_id = [];

$(function() {
	
	//1.加载页面
	loadInventoryList(now_page, key_words, warehouse_status);
	
	//2.点击'查询'关键字
	$("#inventoryList_query").click(inventoryListQuery);
	
	//3.监听页码点击事件
	$("#inventoryList_table").on("click", ".pagelist a", clickPage);
	
	//4.'关键字'回车，等于点击查询
	$("input[name='keywords']").keydown(keywordsKeydown);
	
	//5.点击'仓库'，选择仓库内容
	$("#inventoryList_warehouse").change(clickWarehouse);
	
	//6.监听点击'全选'
	$("#inventoryList_table").on("click", "input[name='allId']" ,allId);
	
	//7.监听点击'单选'
	$("#inventoryList_table").on("click", "input[name='id[]']" ,soleId);
	
	//8.监听点击'启用'、'停用'
	$("#inventoryList_table").on("click", ".inventoryList_useDisable", useDisable);
	
	//9.点击'新建'
	$(".add_button").click(addButton);
	
	//10.监听点击'修改'
	$("#inventoryList_table").on("click", ".amend_button", amendButton)
	
	//11.关闭弹出框
	$(".inventory_alert_box").on("click", ".new_inventory_delbtn, .inventory_del", closeButton);
	
	//12.点击弹出框内的'保存'
	$("#inventoryList_save").click(inventoryListSave);
	
})

/**
 * 12.点击弹出框内的'保存'
 * @returns
 * @author 赵滨
 */
function inventoryListSave() {
	
	//获取仓库ID
	var warehouseId = $("#text_warehouseId").val();
	
	//获取仓库编码
	var userWarehouseCode = $("#text_userWarehouseCode").val();
	
	//获取仓库名称
	var warehouseName = $("#text_warehouseName").val();
	
	//获取联系人
	var consignorName = $("#text_consignorName").val();
	
	//获取联系人电话
	var consignorTel = $("#text_consignorTel").val();
	
	//获取发货地区id
	var regionId = null;
	
	//如果省份不为空
	if ($("#city-picker3").parent().find("span[data-count='province']").attr("data-code") != null) {
		
		//赋值
		regionId = $("#city-picker3").parent().find("span[data-count='province']").attr("data-code");
		
	}
	
	//如果城市不为空
	if ($("#city-picker3").parent().find("span[data-count='city']").attr("data-code") != null) {
		
		//赋值
		regionId = $("#city-picker3").parent().find("span[data-count='city']").attr("data-code");
		
	}
	
	//如果区域不为空
	if ($("#city-picker3").parent().find("span[data-count='district']").attr("data-code") != null) {
		
		//赋值
		regionId = $("#city-picker3").parent().find("span[data-count='district']").attr("data-code");
		
	}
	
	//填写自定义发货地址
	var userAddress = $("#text_userAddress").val();
	
	//仓库编码不能为空
	if (userWarehouseCode == "") {
		
		showMessage("仓库编码不能为空");
		return;
		
	}

    //如果地址id为空则 改成默认
    if (regionId == null) {
	    regionId = 0;
    }
	
	//发送请求，保存仓库
	$.ajax({
		url : "/inventoryList/saveAddUpdate.do",
		type : "post",
		//traditional : true,
		data : {
			"warehouseId" : warehouseId, 
			"userWarehouseCode" : userWarehouseCode,
			"warehouseName" : warehouseName,
			"consignorName" : consignorName,
			"consignorTel" : consignorTel,
			"regionId" : regionId,
			"userAddress" : userAddress
		},
		dataType : "json",
		success : function(result) {
			//接受行数
			var row = result.data
			
			//如果大于1
			if (row > 0) {
				showMessage("保存仓库成功");
				
				//加载页面
				loadInventoryList(now_page, key_words, warehouse_status);
				
				//弹出框 关闭
				$(".inventory_alert_box").css("display","none");
				
			} else if (row == 0) {
				
				showMessage("保存的仓库数量为0");
				
				//弹出框 关闭
				$(".inventory_alert_box").css("display","none");
				
			}
			
		},
		error : function() {
			showMessage("保存仓库失败");
		}
	});
}

/**
 * 11.关闭弹出框
 * @returns
 * @author 赵滨
 */
function closeButton() {
	
	//弹出框 关闭
	$(".inventory_alert_box").css("display","none");
	
}

/**
 * 10.监听点击'修改'
 * @returns
 * @author 赵滨
 */
function amendButton() {
	
	//弹出框
	$(".inventory_alert_box").css("display","block");
	
	//更改标题
	$(".new_inventory_title").find("span:first").html("修改仓库");
	
	//获取仓库对象
	var warehouseInfo = $(this).parent().parent().data("warehouseInfo");
	
	//填写仓库ID
	$("#text_warehouseId").val(warehouseInfo.id);
	
	//填写仓库编码
	$("#text_userWarehouseCode").val(warehouseInfo.userWarehouseCode);
	
	//填写仓库名称
	$("#text_warehouseName").val(warehouseInfo.warehouseName);
	
	//填写联系人
	$("#text_consignorName").val(warehouseInfo.consignorName);
	
	//填写联系人电话
	$("#text_consignorTel").val(warehouseInfo.consignorTel);
	
	//调取citys.js 获取全国地址表
	var citys = getRegionNameByCode(warehouseInfo.regionId);
	
	//清空地区内容
	$("#city-picker3-parent").children().remove();
	
	//重新添加选择框
	$("#city-picker3-parent").append('<input id="city-picker3" class="form-control" readonly type="text" value="" data-toggle="city-picker">');
	
	//加载 腾宁 的方法		<script src="/linkage/js/city-picker.js"></script>
	$('[data-toggle="city-picker"]').citypicker();
		
	//填写省
	$("#city-picker3").parent().find("div[data-count='province'] a[title='"+citys.province+"']").click();
	
	//如果城市为空
	if (citys.city == "" && citys.area != "") {
		//填写市
		$("#city-picker3").parent().find("div[data-count='city'] a[title='"+citys.province+"']").click();

	} else {
		//填写市
		$("#city-picker3").parent().find("div[data-count='city'] a[title='"+citys.city+"']").click();
	}
	
	//填写区
	$("#city-picker3").parent().find("div[data-count='district'] a[title='"+citys.area+"']").click();
		
	//填写自定义发货地址
	$("#text_userAddress").val(warehouseInfo.userAddress);
	
}

/**
 * 9.点击'新建'
 * @returns
 * @author 赵滨
 */
function addButton() {
	
	//弹出框
	$(".inventory_alert_box").css("display","block");
	
	//更改标题
	$(".new_inventory_title").find("span:first").html("新增仓库");
	
	//填写仓库ID
	$("#text_warehouseId").val("");
	
	//填写仓库编码
	$("#text_userWarehouseCode").val("");
	
	//填写仓库名称
	$("#text_warehouseName").val("");
	
	//填写联系人
	$("#text_consignorName").val("");
	
	//填写联系人电话
	$("#text_consignorTel").val("");
	
	//清空地区内容
	$("#city-picker3-parent").children().remove();
	
	//重新添加选择框
	$("#city-picker3-parent").append('<input id="city-picker3" class="form-control" readonly type="text" value="" data-toggle="city-picker">');
	
	//加载 腾宁 的方法		<script src="/linkage/js/city-picker.js"></script>
	$('[data-toggle="city-picker"]').citypicker();
	
	//填写自定义发货地址
	$("#text_userAddress").val("");
	
}

/**
 * 9.1.切换基础和SP
 * @author 腾宁
 */
$(function() {
	/*******/
	var content_btn=$(".content_btn");
	var content_text=$(".content_text");
	var num=1;
	content_text.eq(0).css("zIndex",1);
	$(".content_btn").click(function(){
		num++;
		$(".content_text").eq($(this).index()).css("zIndex",num)
	})
})

/**
 * 8.监听点击'启用'、'停用'
 * @returns
 * @author 赵滨
 */
function useDisable() {
	
	//获取操作内容
	var useDisable = $(this).html();
	
	if (confirm("您确认"+useDisable+"该仓库吗？")) {
		
		//获取仓库ID
		var warehouseId = $(this).parent().parent().find("input[name='id[]']").val();
		
		var status = null;
		
		//如果是启用
		if (useDisable == "启用") {
			
			status = 1;
			
		//如果是停用
		} else if (useDisable == "停用") {
			
			status = 0;
			
		}
		
		//发送请求，启用停用
		$.ajax({
			url : "/inventoryList/updateUseDisable.do",
			type : "post",
			//traditional : true,
			data : {
				"warehouseId" : warehouseId, // bigint
				"status" : status 	//integer
			},
			dataType : "json",
			success : function(result) {
				//接受行数
				var row = result.data
				
				//如果大于1
				if (row > 0) {
					showMessage(useDisable+"该仓库成功");
					
					//加载页面
					loadInventoryList(now_page, key_words, warehouse_status);
				}
				
			},
			error : function() {
				showMessage(useDisable+"该仓库失败");
			}
		});
	}
	
}

/**
 * 7.监听点击'单选'
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
 * 6.点击'全选'
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
 * 5.点击'仓库'，选择仓库内容
 * @returns
 * @author 赵滨
 */
function clickWarehouse() {
	
	//获取 并 赋值
	warehouse_status = $("#inventoryList_warehouse").find("option:selected").val();
	
	//页码初始化
	now_page = 1;
	
	//关键字清空
	key_words = "";
	
	//关键字清空
	$("input[name='keywords']").val("");
	
	//加载页面
	loadInventoryList(now_page, key_words, warehouse_status);
	
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
		$("#inventoryList_query").click();
		
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
	
	//加载页面
	loadInventoryList(now_page, key_words, warehouse_status);
	
}

/**
 * 2.点击'查询'关键字
 * @returns
 * @author 赵滨
 */
function inventoryListQuery() {
	
	//获取关键字
	key_words = $("input[name='keywords']").val().trim();
	
	//页码初始化
	now_page = 1;
	
	//加载页面
	loadInventoryList(now_page, key_words, warehouse_status);
	
}

/**
 * 1.加载页面
 * @returns
 * @author 赵滨
 */
function loadInventoryList(nowPage, keywords, warehouseStatus) {
	
	//发送请求，获取页面内容
	$.ajax({
		url : "/inventoryList/loadInventoryList.do",
		type : "post",
		//traditional : true,
		data : {
			"nowPage" : nowPage, // int
			"keywords" : keywords, //String
			"warehouseStatus" : warehouseStatus	//bigint
		},
		dataType : "json",
		success : function(result) {
			//接受栏目集合
			var map = result.data
			
			//创建页面
			createInventoryList(map);
			
		},
		error : function() {
			showMessage("加载仓库列表失败");
		}
	});
	
}

/**
 * 1.1.创建页面
 * @param map
 * @returns
 * @author 赵滨
 */
function createInventoryList(map) {
	
	//加载最大页数
	max_page = map.maxPage;
	
	//清空页面
	$("#inventoryList_table").children().remove();
	
	//第一部分
	var tr = '';
	
	tr += '<tr>';
	tr += '<th width="80"><input type="checkbox" name="allId" value="1" class="check_coding" />序号</th>';
	tr += '<th>仓库编码</th>';
	tr += '<th width="100">仓库名称</th>';
	tr += '<th>联系人</th>';
	tr += '<th width="200">仓库地址</th>';
	tr += '<th>状态</th>';
	tr += '<th width="180">操作</th>';
	tr += '</tr>';
	
	//追加
	$("#inventoryList_table").append(tr);
	
	//第二部分
	tr = '';
	
	//获取 仓库列表
	var listWarehouseInfo = map.listWarehouseInfo;
	
	//遍历 仓库列表
	for (var i = 0; i < listWarehouseInfo.length; i++) {
		
		tr = '';
		
		tr += '<tr>';
		tr += '<td><input type="checkbox" name="id[]" value="'+listWarehouseInfo[i].id+'" class="check_coding" />';	//ID
		tr += (i + 1) + ((parseInt(now_page) - 1) * parseInt(map.rows));	//序号
		tr += '</td>';
		tr += '<td>';
		tr += listWarehouseInfo[i].userWarehouseCode;	//仓库编码
		tr += '</td>';
		tr += '<td>';
		tr += '<p>';
		tr += listWarehouseInfo[i].warehouseName;	//仓库名称
		tr += '</p>';
		tr += '</td>  ';
		tr += '<td>';
		
		tr += listWarehouseInfo[i].consignorName + " "+ listWarehouseInfo[i].consignorTel;//联系人
		
		tr += '</td>';
		tr += '<td>';
		tr += '<p style="width:200px;">';
				
		//调取citys.js 获取全国地址表
		var citys = getRegionNameByCode(listWarehouseInfo[i].regionId);
		
		//如果没有地址 啦啦啦
		if (typeof(citys.province) == "undefined") {
			
			tr += '<span>'+listWarehouseInfo[i].userAddress+'</span>';//地址
			
		//如果城市为空
		} else if (citys.city == "" && citys.area != "") {
			
			tr += '<span>'+citys.province+" "+citys.province+" "+citys.area+" "+listWarehouseInfo[i].userAddress+'</span>';//地址
			
		//如果城市不为空
		} else{
			
			tr += '<span>'+citys.province+" "+citys.city+" "+citys.area+" "+listWarehouseInfo[i].userAddress+'</span>';//地址

		}
		
		tr += '</p>';
		tr += '</td>';
		tr += '<td>';
		
		//如果启用
		if (listWarehouseInfo[i].warehouseStatus == 1) {
			tr += '启用';
			
	    //如果停用
		} else if (listWarehouseInfo[i].warehouseStatus == 0) {
			tr += '停用';
		}
		
		tr += '</td>';
		tr += '<td>';
		tr += '<a href="javascript:;" class="button border-main a_btn_sync inventoryList_useDisable">';
			
		//如果启用
		if (listWarehouseInfo[i].warehouseStatus == 1) {
			tr += '停用';
			
	    //如果停用
		} else if (listWarehouseInfo[i].warehouseStatus == 0) {
			tr += '启用';
		}
		
		tr += '</a>';
		tr += '<a href="javascript:;" class="button border-main a_btn_sync amend_button"> 修改</a>';
		tr += '</td>';
		tr += '</tr>';
		
		//转换对象
		$tr = $(tr);
		
		//绑定对象
		$tr.data("warehouseInfo", listWarehouseInfo[i]);
		
		//追加
		$("#inventoryList_table").append($tr);
		
	}
	
	//第三部分
	tr = '';
	
	//开始部分
	tr += '<tr><td colspan="10">';
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
    tr += '<a href="javascript:void(0)">下一页</a><a href="javascript:void(0)">尾页</a></div>';
	tr += '</td></tr>';

    //加入页面
    $("#inventoryList_table").append(tr);
    
}