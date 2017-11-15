/**
 * Wzy
 * 用于发配订单
 */
//加载list

//当前页数
var now_page = 1;

//关键字
var key_word = "";

//最大页数
var max_page = 1;

//全国地址选取项
var selectArea = new Array();

//已经设定好的全国地址
var coverArea = new Array();

//即将添加的集合
var insertArea = "";

//即将删除的集合
var deleteArea = "";
insertArea
$(function(){
	
	//加载页面
	loadOrderRelease(now_page, max_page)
	
	//监听商品设置
	$(".list_table").on("click",".sp_alert",doSetUpItemInfo);
	
	//监听分页(商品模块)
	$(".page").on("click", ".pagelist a", clickPage);
	
	//监听分页(仓库管理模块)
	$("#list_foot").on("click", ".pagelist a", clickPage);
	
	//监听全国地址设置
	$("#list_body").on("click",".dq_alert",setCoverArea);
	
	//监听全国地址的保存
	$(".regional_rules_box").on("click",".regional_rules_bc", doUpdateCoverArea);
	
	//监听全国地址的多选点击
	$(".regional_rules_box").on("click", ClickMonitor);
	
	//监听全国地质取消
	$(".regional_rules_box").on("click",".regional_rules_qx, .regional_rules_delbtn", doCoverAreaCancel);
})

//监听全国地质的过选点击
function ClickMonitor(){
	
	//全选
	$(".regional_rules_check").click(function(){
		$('input[type="checkbox"]').prop('checked',true);
	});
	
	//全不选
	$(".regional_rules_notCheck").click(function(){
		$('input[type="checkbox"]').prop('checked',false);
	});
	
	$("input[name='city']").change(function(){
		var obj = $(this);
		var subOptions = obj.parent().next().children();
		if(obj.is(':checked')){
            for(i=0;i<subOptions.length;i++){
            	subOptions[i].children[0].checked=true;
            }
        }else{
            for(i=0;i<subOptions.length;i++){
            	subOptions[i].children[0].checked=false;
            }
        }
	})
	
	$("input[name='citys']").change(function(){
		var obj = $(this);
		console.log(obj.val())
		var subOptions = obj.parent().parent().children();
		var parentOption = obj.parent().parent().prev().children();
		for(i=0;i<subOptions.length;i++){
			var subOption = subOptions[i].children[0];
        	if(subOption.checked){
        		parentOption[0].checked = true
        		return;
        	}else{
        		parentOption[0].checked = false;
        	}
        }
	})
	
}

//监听全国地址取消
function doCoverAreaCancel(){
	$(".select_commodity_box").removeData("warehouseId");
	$(".select_commodity_box").removeData("stocklocationId");
	$(".regional_rules_box").css("display","none");
}

//处理集合
function comparisonList(){
	
	//清空集合
	insertArea = "";
	deleteArea = "";
	
	//
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
    
    deleteArea = coverArea.minus(selectArea)
    insertArea = selectArea.minus(coverArea);
	
}

//监听全国地址保存
function doUpdateCoverArea(){
	
	//获取仓库库位id
	var stocklocationId = $(".regional_rules_box").data("stocklocationId");
	var warehouseId = $(".regional_rules_box").data("warehouseId");
	//获取选区的城市
	var ids="";
	$("#ol input[type='checkbox']").each(function(){
		  if($(this).is(':checked')){
			  var code = $(this).val();
			  selectArea.push(code)
		  }
	  });
    
	//前后结果对比
	comparisonList();
	
	//清空集合
	selectArea =[];
    
	//ajax
    var url = "updateCoverArea.do";
    var param = {
    		warehouseId:warehouseId,
    		stocklocationId:stocklocationId,
    		deleteList:deleteArea,
    		insertList:insertArea,
    }
    console.log(param)
    $.post(url,param,function(result){
    	
    	$(".select_commodity_box").removeData("warehouseId");
    	$(".select_commodity_box").removeData("stocklocationId");
    	$(".regional_rules_box").css("display","none");
    })
    
}

//制作全国列表
function dosetCoverArea(data){
	var list = data.list;
	var list1 = data.list1;
	var list2 = data.list2;
	coverArea=[];
	for(var i = 0;i<list2.length;i++){
		coverArea.push(list2[i])
	}
	var ol = $("#ol");
	ol.empty();
	var a = '<li><ul><li>'+
    			'<a href="javascript:;" style=" color: blue;" class="regional_rules_check">全选</a>'+
    			'&nbsp;&nbsp;&nbsp;&nbsp;'+
    			'<a href="javascript:;" style=" color: blue;" class="regional_rules_notCheck">全不选</a>'+
    		'</li></ul></li>';
	ol.append(a);
	for(var i=1;i<list.length;i++){
		var city = list[i].regionId
		var li= $("<li></li>");
		var ul= $("<ul></ul>");
		var li1="<li class='li1'>" +
					"<input type='checkbox' name='city' value='"+list[i].regionCode+"'>"+list[i].name+
				"</li>";
		ul.append(li1)
		li.append(ul);
		var li2=$("<li class='li2'></li>");
		for(var j=0;j<list1.length;j++){
			var county = list1[j].parentId;
			if(city == county){
				var span="<span>" +
							"<input type='checkbox' name='citys' value="+list1[j].regionCode+">"+list1[j].name+
						"</span>";
				li2.append(span)
			}
		}
		ul.append(li2);
		li.append(ul);
		ol.append(li);
	}
	for(var i = 0;i<list2.length;i++){
		$("input:checkbox[value='"+list2[i]+"']").attr('checked',true);
	}
}

//监听仓库覆盖范围设置
function setCoverArea(){
	var warehouseId = $(this).parent().parent().data("warehouseId");
	var stocklocationId = $(this).parent().parent().data("stocklocationId");
	$(".regional_rules_box").data("warehouseId",warehouseId);
	$(".regional_rules_box").data("stocklocationId",stocklocationId);
	$(".regional_rules_box").css("display","block");
	var url = "/getCoverArea.do";
	var param={
			warehouseId:warehouseId,
			stocklocationId:stocklocationId
	}
	$.post(url,param,function(result){
		dosetCoverArea(result.data);
	})
}

//监听商品设置
function doSetUpItemInfo(){
	var ids = $(this).parent().parent().data("id");
	$(".select_commodity_box").data("id",ids);
	$(".select_commodity_box").css("display","block");
}

//监听点击分页
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
	console.log(now_page)
	loadOrderRelease(now_page,key_word)
}

//布置分页(页面)
function doSetTfootInfo(page){
	//第三部分
	max_page =page
	var tfoot = $("#list_foot");
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

//布置页面
function doSetTbodyInfo(list){
	//获取tbody并清空
	var listTbody = $("#list_body");
	listTbody.empty();
	//遍历信息并填写
	for (var i=0;i<list.length;i++){
		var tr=$("<tr></tr>");
		tr.data("warehouseId",list[i].warehouseId);
		tr.data("stocklocationId",list[i].stocklocationId)
		var tds="<td>"+(i+1)+"</td>" +
				"<td><p>"+list[i].warehouseName+"</p></td>" +
				"<td><p>"+list[i].stocklocationId+"</p></td>"+
				"<td>" +
				' <a href="javascript:;" style="line-height: 30px;color: blue;" class="dq_alert">设置</a>'+		
				"</td>"+
				"<td>" +
				' <a href="javascript:;" style="line-height: 30px;color: blue;" class="sp_alert">设置</a>'+		
				"</td>"+
				"<td>" +
				' <a href="javascript:;" style="line-height: 30px;color: blue;" class="dp_alert">设置</a>'+		
				"</td>";
		tr.append(tds);
		listTbody.append(tr);
	}
}

//加载页面
function loadOrderRelease(now_page, max_page){
	//查询商品
	//发送请求，获取页面内容
	$.ajax({
		url : "/loadOrderRelease.do",
		type : "post",
		//traditional : true,
		data : {
			"nowPage" : now_page, // int
		},
		dataType : "json",
		success : function(result) {
			//创建页面显示的信息
			doSetTbodyInfo(result.data.list);
			doSetTfootInfo(result.data.page);
		},
		error : function() {
			showMessage("加载商品列表失败");
		}
	});
}

