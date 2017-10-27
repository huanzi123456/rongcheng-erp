//全局变量 最大页数
var max_page=1;
//全局变量 当前页数
var now_page=1;
$(function(){
	loadItemCommonInfo(1);
	
});
function saveItemCommonInfos(){
	var id1 = new Array();
	 $("input[name='id1']:checked").each(function(){
		 id1.push($(this).val());
	 });
//	 alert(ids);
	 console.log(id1);
		$.ajax({
			url:"settings/modify_storeMessages.do",
			type:"post",
			dataType:"json",
			data:$('#fm1').serialize(),
			 success:function(result){
				if(result.state==0){//成功
					alert("保存成功！"); 	
				}
			},
			error:function(){
				alert("保存失败！"); 
			}
		});
}


function saveItemCommonInfo(i,id){
	 var sm_shortName=$("#sm_shortName"+i).val();
	
			$.ajax({
				url:"settings/modify_storeMessage.do",
				type:"post",
				dataType:"json",
				data:{
					"id":id,
					"shortName":sm_shortName
					},
				 success:function(result){
					if(result.state==0){//成功
						alert("保存成功！"); 	
					}
				},
				error:function(){
					alert("保存失败！"); 
				}
			});
}


function loadItemCommonInfo(page) {
if(page <= 0 || page > max_page){return;}
now_page=page;

$.ajax({
	url : "load_modify_storeMessage.do",
	type : "post",
	data : {
		"page" : page
	},
	dataType : "json",
	success : function(result) {
		max_page = result.message;
		var list=result.data;
		reload(list);
	},
	error : function() {
		alert("加载失败!!!");
//		reload(list);
	}
});
}
function reload(list) {
var storeMessageTable = $("#storeMessage_table");
storeMessageTable.empty();
var sa="";
sa+='<tr>';
sa+='<th width="120">商品编码</th>';
sa+='<th>商品名称</th>';
sa+='<th>商品规格</th>';
sa+='<th>商品品牌</th>';
sa+='<th width="300">短标题</th>';
sa+='<tr>';
$("#storeMessage_table").append(sa);
for(var i=0;i<list.length;i++){
	var ItemCommonInfo = list[i];
	var id = ItemCommonInfo.id;
	
	var erpItemNum = ItemCommonInfo.erpItemNum;
	var name = ItemCommonInfo.name;
	var color = ItemCommonInfo.color;
	var size = ItemCommonInfo.size;
	var brand = ItemCommonInfo.brand;
	var shortName = ItemCommonInfo.shortName;
	
	var str="";
	str+='<tr>';
	str+='<td><input type="checkbox" name="id1" value="'+id+'" class="check_coding"/>';
	str+=erpItemNum;
	str+='</td>'; 
	str+='<td>';
	str+=name;
	str+='</td>'; 
	str+='<td>';
	str+=color+size;
	str+='</td>'; 
	str+='<td>';
	str+=brand;
	str+='</td>';
	str+='<td>';
	str+='<input type="text" id="sm_shortName'+i+'" name="sort" value="'+shortName+'" style="width:150px; text-align:center; border:1px solid #ddd; padding:7px 0;" class="input_coding"/><button type="button" class="button"  onclick="saveItemCommonInfo('+i+','+id+')"><span class="icon-edit"></span> 保存</button>';
	str+='</td>';
	str+='</tr>';
	var $tr=$(str);
	$("#storeMessage_table").append($tr);
	
	
}

/////页码////////////
var trs="";
trs += '<tr id = "page_tr">';
trs += '<td colspan="8"><div class="pagelist"><a class="current" onclick="loadItemCommonInfo(1)" href="javascript:;">首页</a><a class="bookpage" onclick="loadItemCommonInfo('+(now_page-1)+')" href="javascript:;">上一页</a>';
if(max_page>5){
	if(now_page<=3){
		for(var i=1;i<4;i++){
			if(i==now_page){
				trs += '<span class="bookpage" onclick="loadItemCommonInfo('+i+')">'+i+'</span>';
			}else{
				trs += '<a class="bookpage" onclick="loadItemCommonInfo('+i+')" href="javascript:;">'+i+'</a>';
			}
		}
		trs += '<a class="bookpage" onclick="loadItemCommonInfo(4)" href="javascript:;">4</a><a class = "bookpage" onclick="loadItemCommonInfo(5)" href="javascript:;">5</a>...';
	}else if(now_page>=4 && now_page<=max_page-3){
		trs += '...<a class="bookpage" onclick="loadItemCommonInfo('+(now_page-2)+')" href="javascript:;">'+(now_page-2)+'</a><a class="bookpage" onclick="loadItemCommonInfo('+(now_page-1)+')" href="javascript:;">'+(now_page-1)+'</a>';
		trs += '<span class="bookpage">'+now_page+'</span>';
		trs += '<a class = "bookpage" onclick="loadItemCommonInfo('+(now_page+1)+')" href="javascript:;">'+(now_page+1)+'</a><a class = "bookpage" onclick="loadItemCommonInfo('+(now_page+2)+')" href="javascript:;">'+(now_page+2)+'</a>...';
	}else if(now_page>max_page-3){
		trs += '...<a class="bookpage" onclick="loadItemCommonInfo('+(max_page-4)+')" href="javascript:;">'+(max_page-4)+'</a><a class="bookpage" onclick="loadItemCommonInfo('+(max_page-3)+')" href="javascript:;">'+(max_page-3)+'</a>';
		for(var i=max_page-2;i<=max_page;i++){
			if(i==now_page){
				trs += '<span class="bookpage" onclick="loadItemCommonInfo('+i+')">'+i+'</span>';
			}else{
				trs += '<a class="bookpage" onclick="loadItemCommonInfo('+i+')" href="javascript:;">'+i+'</a>';
			}
		}
	}
}else{
	var i = 1;
	while (i <= max_page) {
		if(i==now_page){
			trs += '<span class="bookpage" onclick="loadItemCommonInfo('+i+')">'+i+'</span>';
		}else{
			trs += '<a class = "bookpage" onclick="loadItemCommonInfo('+i+')" href="javascript:;">'+i+'</a>';
		}
		i++;
	}
}
trs += '<a class="bookpage" onclick="loadItemCommonInfo('+(now_page+1)+')">下一页</a><a class="bookpage" onclick="loadItemCommonInfo('+max_page+')">尾页</a></div></td>';
trs += '</tr>';
$("#storeMessage_table").append(trs);
//////////////////////////
$(".bookpage:contains('"+now_page+"')").addClass("current");
}




