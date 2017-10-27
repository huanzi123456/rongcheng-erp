//全局变量 最大页数
var max_page=1;
//全局变量 当前页数
var now_page=1;
$(function(){
	loadUserInfo(1);
	//监听事件
	$("#userInfo_table").on("click",".accredit_amend",modifyAccredit);
	$("#userInfo_table").on("click",".accredit_amend",loadModifyUserInfo);//加载单个修改数据
	$("#userInfo_table").on("click",".account_del",delAccount);
	$("#userInfo_table").on("click",".account_status1",QAccount);
	$("#userInfo_table").on("click",".account_status0",TAccount);
	$(".accredit_add_amend").click(addfun);
});

function modifyAccredit(){
	var $s1=$(".add_amend_btn").attr("id",2);
	$s1.click(modifyAccount);
	$(".shade_accredit").css("display","block");
	//获取tr对象
	var $tr = $(this).parent().parent();
	//获取id
	var id = $tr.data("id");
	addCookie("id", id, 1);
}
function addfun(){
	var $s=$(".add_amend_btn").attr("id",1);
	$s.click(addAccount);
}
function addAccount(){
	var account_name=$("#account_name").val();
	var account_accountNum=$("#account_accountNum").val();
	var account_password=$("#account_password").val();
	var account_telephone=$("#account_telephone").val();
	console.log(account_name,account_accountNum,account_password,account_telephone);
	var ok=true;
	if(account_name==""){
		$("#account_name_div").html("用户名为空");
		ok=false;
	}
	if(account_accountNum==""){
		$("#account_accountNum_div").html("账号为空");
		ok=false;
	}
	if(account_password==""){
		$("#account_password_div").html("密码为空");
		ok=false;
	}
	if(account_telephone==""){
		$("#account_telephone_div").html("电话为空");
		ok=false;
	}
	
	if(ok){
		$.ajax({
			url :"add_account.do",
			type : "post",
			data : {
				"name":account_name,
				"accountNum":account_accountNum,
				"password":account_password,
				"telephone":account_telephone},
			dataType : "json",
			success : function(result) {
				if (result.state == 0) {
					alert("添加成功!");
				    	window.location.href = path+"/account_add_amend.do";	
				}
			},
			error : function() {
				alert("添加失败!");
			}
		});
	}
}
function delAccount(){
	var $tr = $(this).parent().parent();
	var userInfoId = $tr.data("id");
	$.ajax({
		url:"del_account.do",
		type:"post",
		dataType:"json",
		data:{"id":userInfoId},
		 success:function(result){
			if(result.state==0){
				alert("删除成功！"); 	
			}
		},
		error:function(){
			alert("删除失败！"); 
		}
	});
}
function QAccount(){//启用
	var $tr = $(this).parent().parent();
	var userInfoId = $tr.data("id");
	$.ajax({
		url:"modify_accountStatus.do",
		type:"post",
		dataType:"json",
		data:{"id":userInfoId,
			"accountStatus":1},
		 success:function(result){
			if(result.state==0){
				alert("启用成功！"); 	
			}
		},
		error:function(){
			alert("启用失败！"); 
		}
	});
}
function TAccount(){//停用
	var $tr = $(this).parent().parent();
	var userInfoId = $tr.data("id");
	
	$.ajax({
		url:"modify_accountStatus.do",
		type:"post",
		dataType:"json",
		data:{"id":userInfoId,
			"accountStatus":0},
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

function loadUserInfo(page) {
	if(page <= 0 || page > max_page){return;}
	now_page=page;

	$.ajax({
		url : "/loadList_account.do",
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
	var UserInfoTable = $("#userInfo_table");
	UserInfoTable.empty();
	var sa="";
	sa+='<tr>';
	sa+='<th width="50">序号</th>';
	sa+='<th>用户名</th>';
	sa+='<th>创建时间</th>';
	sa+='<th>账号类型</th>';
	sa+='<th>启用/停用</th>';
	sa+='<th width="350">操作</th>';
	sa+='<tr>';
	$("#userInfo_table").append(sa);
	
	for(var i=0;i<list.length;i++){
		var UserInfo = list[i];
		var id = UserInfo.id;
		var name = UserInfo.name;
		var gmtCreate = UserInfo.gmtCreate;
		var type = UserInfo.type;
		var accountStatus = UserInfo.accountStatus;
		var str="";
		str+='<tr>';
		str+='<td>';
		str+=id;
		str+='</td>';
		str+='<td>';
		str+=name;
		str+='</td>';
		str+='<td>';
		str+=gmtCreate;
		str+='</td>';
		str+='<td>';
		str+=type;
		str+='</td>';
		str+='<td>';
		str+=accountStatus;
		str+='</td>';
		str+='<td>';
		str+='<button type="button" class="button border-blue accredit_amend"><span class="icon-edit"></span> 修改</button>';
		str+='<button type="submit" class="button border-green account_status1"><span class="icon-check"></span> 启用</button>';
		str+='<button type="submit" class="button border-black account_status0"><span class="icon-minus"></span> 停用</button>';
		str+='<button type="submit" class="button border-red account_del"><span class="icon-trash-o"></span> 删除</button>';
		str+='</td>';
		str+='</tr>';
		//转jquery对象
		var $tr=$(str);
		//把id加到 这条数据上
		$tr.data("id",id);
//		console.log($tr.data("id"));
		//最后加到table上
		$("#userInfo_table").append($tr);
	}
	/////页码////////////
	var trs="";
	trs += '<tr id = "page_tr">';
	trs += '<td colspan="10"><div class="pagelist"><a class="current" onclick="loadUserInfo(1)" href="javascript:;">首页</a><a class="bookpage" onclick="loadUserInfo('+(now_page-1)+')" href="javascript:;">上一页</a>';
	if(max_page>5){
		if(now_page<=3){
			for(var i=1;i<4;i++){
				if(i==now_page){
					trs += '<span class="bookpage" onclick="loadUserInfo('+i+')">'+i+'</span>';
				}else{
					trs += '<a class="bookpage" onclick="loadUserInfo('+i+')" href="javascript:;">'+i+'</a>';
				}
			}
			trs += '<a class="bookpage" onclick="loadUserInfo(4)" href="javascript:;">4</a><a class = "bookpage" onclick="loadUserInfo(5)" href="javascript:;">5</a>...';
		}else if(now_page>=4 && now_page<=max_page-3){
			trs += '...<a class="bookpage" onclick="loadUserInfo('+(now_page-2)+')" href="javascript:;">'+(now_page-2)+'</a><a class="bookpage" onclick="loadUserInfo('+(now_page-1)+')" href="javascript:;">'+(now_page-1)+'</a>';
			trs += '<span class="bookpage">'+now_page+'</span>';
			trs += '<a class = "bookpage" onclick="loadUserInfo('+(now_page+1)+')" href="javascript:;">'+(now_page+1)+'</a><a class = "bookpage" onclick="loadUserInfo('+(now_page+2)+')" href="javascript:;">'+(now_page+2)+'</a>...';
		}else if(now_page>max_page-3){
			trs += '...<a class="bookpage" onclick="loadUserInfo('+(max_page-4)+')" href="javascript:;">'+(max_page-4)+'</a><a class="bookpage" onclick="loadUserInfo('+(max_page-3)+')" href="javascript:;">'+(max_page-3)+'</a>';
			for(var i=max_page-2;i<=max_page;i++){
				if(i==now_page){
					trs += '<span class="bookpage" onclick="loadUserInfo('+i+')">'+i+'</span>';
				}else{
					trs += '<a class="bookpage" onclick="loadUserInfo('+i+')" href="javascript:;">'+i+'</a>';
				}
			}
		}
	}else{
		var i = 1;
		while (i <= max_page) {
			if(i==now_page){
				trs += '<span class="bookpage" onclick="loadUserInfo('+i+')">'+i+'</span>';
			}else{
				trs += '<a class = "bookpage" onclick="loadUserInfo('+i+')" href="javascript:;">'+i+'</a>';
			}
			i++;
		}
	}
	trs += '<a class="bookpage" onclick="loadUserInfo('+(now_page+1)+')">下一页</a><a class="bookpage" onclick="loadUserInfo('+max_page+')">尾页</a></div></td>';
	trs += '</tr>';
	$("#userInfo_table").append(trs);
	$(".bookpage:contains('"+now_page+"')").addClass("current");
	//////////////////////////
	}
	
	function loadModifyUserInfo(){
		//获取cookie中的userInfoId
		var userInfoId = getCookie("id");
		//判断cookie是否有效
		if(userInfoId==null){
//			window.location.href="accredit.do";
		}
		//发送ajax
		$.ajax({
			url : "load_modify_account.do",
			type : "post",
			data : {"id":userInfoId},
			dataType : "json",
			success:function(result){
				if (result.state == 0) {
				var UserInfo = result.data;
				console.log(UserInfo);
		//获取各项数值
		var userInfoName = UserInfo.name;
		var userInfoAccountNum = UserInfo.accountNum;
		var userInfoPassword = UserInfo.password;
		var userInfoTelephone = UserInfo.telephone;
//		console.log(userInfoName,userInfoAccountNum,userInfoPassword,userInfoTelephone);
		//获取各个内容框，绑定他们哟
		var account_name=$("#account_name").val(userInfoName);
		var account_accountNum=$("#account_accountNum").val(userInfoAccountNum);
		var account_password=$("#account_password").val(userInfoPassword);
		var account_telephone=$("#account_telephone").val(userInfoTelephone);
				}
			},
			error:function(){
				alert("修改栏目获取信息失败！")
			}
		});
	}
	function modifyAccount(){//单个修改
			var userInfoId = getCookie("id");
//			alert(userInfoId);
			var account_name=$("#account_name").val();
			var account_accountNum=$("#account_accountNum").val();
			var account_password=$("#account_password").val();
			var account_telephone=$("#account_telephone").val();
			
			$.ajax({
				url:"modify_account.do",
				type:"post",
				dataType:"json",
				data:{"id":userInfoId,
					"name":account_name,
					"accountNum":account_accountNum,
					"password":account_password,
					"telephone":account_telephone},
				 success:function(result){
					if(result.state==0){
						alert("修改成功！"); 	
						window.location.href = path+"/account_add_amend.do";
					}
				},
				error:function(){
					alert("修改失败！"); 
				}
			});
}
		
