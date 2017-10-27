$(function(){
	//地区下拉（无街道）
	$('#addrDiv').citys({
		code:regionCode,
	    required:false,
	    nodata:'disabled',
	    onChange:function(data){
	        var text = data['direct']?'(直辖市)':'';
	        $('#place').text('当前选中地区：'+data['province']+text+' '+data['city']+' '+data['area']);
	    }
	});
	//输入框内容改变事件
	$("input").change(inputChange);
});
var now_page=0;
var max_page=1;
var itemOnePageTemp=null;//每页商品列表缓存
var choiceTemp = new Array();//每次打开弹出商品列表，选择的商品Id缓存，取消提交时需要从欲购列表移除的
var delTemp = new Array();//每次打开弹出商品列表，取消选择的商品Id缓存，提交时需要从欲购列表移除的

//根据从服务器加载商品列表
function loadItemByPage(page) {
	now_page=0;
	if(page<= 0||page> max_page||page==now_page){return;}
	now_page=page;
	$(".page_clear").remove();
	var inputTag = document.createElement('input'); 
    inputTag.setAttribute("type", "hidden");
    inputTag.setAttribute("class", "page_clear");
    inputTag.setAttribute("name", "page"); 
    inputTag.setAttribute("value", page); 
	$("#form2").append(inputTag);
	var data = $("#form2").serialize();
	$.ajax({
		url : "listItemBasicInfoSelective.do",
		type : "post",
		data : data,
		dataType : "json",
		success : function(result) {
			if(result.message!=null){
				max_page = result.message;
			}
			reload(result.data);
		},
		error : function() {
			showMessage("获取商品信息失败，请稍后重试。");
		}
	});
}
//弹出框内加载商品列表
function reload(data) {
	itemOnePageTemp=data;
	var table = $("#item_table");
	table.empty();
	var trs = '<tr><th width="120">商品编码</th><th>商品名称</th><th>商品品牌</th><th>商品类目</th><th>商品规格</th><th>商品价格</th></tr>';
	if(data != null){
		for (var i = 0; i < data.length; i++) {
			var obj = data[i];
			trs += '<tr>';
			if($("#item"+obj.id).length==1){
				trs += '<td><input type="checkbox" name="id[]" value="'+obj.id+'"checked="checked" onclick="choice(this,'+i+')"/>'+obj.erpItemNum+'</td>';
			}else{
				trs += '<td><input type="checkbox" name="id[]" value="'+obj.id+'" onclick="choice(this,'+i+')"/>'+obj.erpItemNum+'</td>';
			}
			trs += '<td>'+obj.name+'</td>';
			trs += '<td>'+obj.brand+'</td>';
			trs += '<td>'+obj.category+'</td>';
			trs += '<td>'+obj.color+'*'+obj.size+'</td>';
			trs += '<td>￥'+obj.normalPrice+'</td>';
			trs += '</tr>';
		}
		trs += '<tr><td colspan="8" id = "page_td"></td></tr>';
		table.append(trs);
	}else{
		table.append(trs);
		return;
	}
/////分页页码加载
	pagination(max_page,now_page,"page_td","loadItemByPage");
}
//商品列表弹出框内 添减欲购商品
function choice(obj,index) {
	if(obj.checked){
		for(var i=0;i<delTemp.length;i++){
			if(delTemp[i]==itemOnePageTemp[index].id){
				delTemp.splice(i,1);//提交时需要从欲购列表移除的(取消移除)
			}
		}
		if($("#item"+itemOnePageTemp[index].id).length==0){
			choiceTemp.push(itemOnePageTemp[index].id);//取消提交时需要从欲购列表移除的
			var table = $("#have_item_table");
			var tr = '';
			tr += '<tr class="have_tr" id="item'+itemOnePageTemp[index].id+'">';
				tr += '<td style="display:none;"><input name="id" value="'+itemOnePageTemp[index].id+'"/></td>';
				tr += '<td>'+itemOnePageTemp[index].erpItemNum+'</td>';
				tr += '<td>'+itemOnePageTemp[index].shortName+'</td>';
				tr += '<td>'+itemOnePageTemp[index].color+'*'+itemOnePageTemp[index].size+'</td>';
				tr += '<td>￥'+itemOnePageTemp[index].normalPrice+'</td>';
				tr += '<td>';
					tr += '<input name="quantity" value="1" style="width:50px; text-align:center; border:1px solid #ddd; padding:7px 0;" /></td>';
				tr += '<td>';
					tr += '<div class="button-group">';
						tr += '<a class="button border-red" href="javascript:void(0)" onclick="del(this)">';
							tr += '<span class="icon-trash-o"></span>删除</a>';
					tr += '</div>';
				tr += '</td>';
			tr += '</tr>';
			table.append(tr);
		}
	}else{
		for(var i=0;i<delTemp.length;i++){
			if(delTemp[i]==itemOnePageTemp[index].id){
				return;//已存在不能重复添加
			}
		}
		delTemp.push(itemOnePageTemp[index].id);//提交时需要从欲购列表移除的
	}
}
//提交欲购商品到欲购列表（只需删除取消的商品）
function submitItem() {
	$(".alert_data-box").css("display","none");
	for(var x=0;x<delTemp.length;x++){//删除取消的商品
		$("#item"+delTemp[x]).remove();
	}
}
//删除已添加的单件商品
function del(obj) {
	$(obj).parent().parent().parent().remove();
}
//弹出商品列表
function openDiv() {
	choiceTemp = [];//每次打开弹出商品列表，清空商品Id缓存。
	delTemp = [];
	loadItemByPage(1);
}
//关闭商品列表框（取消选取商品操作）
function closeDiv() {
	for(var x=0;x<choiceTemp.length;x++){
		$("#item"+choiceTemp[x]).remove();
	}
}
//提交检查检查
function inputChange(){
	$(this).next(".tips").html("");
	var id_Name = "tel";
	if($(this).attr("name")==id_Name){
		if(!patt_tel.test($(this).val())){
			$(this).removeAttr("id");
			$(this).focus();
			$(this).next(".tips").html('<span class="err_span">格式错误，每个号码为6-16位数字，如果有多个号码，中间用英文半角逗号“,”隔开。</span>');
			return;
		}else{
			$(this).attr("id",id_Name);
		}
	}
};

function subCheck() {
	if($.trim($("#account").val())==""||$.trim($("#name").val())==""||$.trim($("#tel").val())==""||$("#area").val()==""||$("#addr").val().trim()==""||$("#have_item_table").children().length<=0){
		showMessage("请填写必要的信息再提交。")
		return false;
	}
	if($("#tel").val().trim()){
		
	}
	return true;
}
