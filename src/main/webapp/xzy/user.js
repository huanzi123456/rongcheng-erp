var currentUserId;        //当前用户的id
var currentUserType;      //当前用户的类型(1,2,3)
var currentUserOwnerId;   //当前用户的ownerId
var currentUserAccountNum;//当前用户的账号
var now_page;             //当前页
var num;
$(function(){ 
	//单击阴影部分时弹出框页面隐藏
	$("#shadess").click(function(){
    	$(".shade_accredit").css("display","none");   	
    }) 
    /*
     * 1.分页查询用户
     */
	userPaging(1);
	/*
	 * 2.新建用户  注:只有主账户可以新建用户
	 */	
	if(currentUserType == 1){
		$("#new_button").removeAttr("disabled");
	    $("#new_button").click(newUser);	    
	}else{
		$("#parent_role").hide();
		$("#new_button").attr("disabled",true);
	}   
	/*
	 * 3.修改用户  
	 * 注:主账户可以修改其他账户的所有信息;主账户拥有全部的权限,不能修改自己的角色和权限信息
	 *   其他账户只能修改自己的用户名,密码,电话,其他信息不能修改
	 */
    $("#tabless").on("click","#rebuild_button",modifyUser);
    /*
     * 其他按钮:启用,停用,删除
     *   主账户不能启用,停用,删除自己,可以启用,停用,删除其他用户
     *   其他账户不能使用启用,停用,删除按钮
     */
    $("#tabless").on("click","#other_botton",otherButton);
});
/**
 * 1.页面查询
 * @param page:当前页
 * @returns
 */
function userPaging(page){
	now_page = page;
	$("#tabless").find("tr").remove();
	$.ajax({
		url:"/user/userPaging.do",
		async:false,
		type:"post",
		data:{"page":page},
		dataType:"json",			
		success:function(result){
			if(result.status==0){
				var pageSize = result.pageSize;            //每页的记录数
				var maxPage = result.maxPage;              //总的页数
				var datum = result.datum;
			    currentUserId = datum.id;                  //当前用户的id
				currentUserType = datum.userType;          //当前用户的类型(1,2,3)				
				currentUserOwnerId = datum.ownerId;        //当前用户的ownerId
				currentUserAccountNum = datum.accountNum;  //当前用户的账号
				var datas = result.data;				
				var table_tr;
				num = datas.length;
				for(var i=0;i<datas.length;i++){
					var id = datas[i].id;                                            //被修改的用户的id
					var user_name = datas[i].name;                                   //被修改的用户的用户名
					var account_num = datas[i].accountNum;                           //被修改的用户的账户
					var password = datas[i].password;                                //被修改的用户的密码
					var telephone = datas[i].telephone;                              //被修改的用户的电话		
					var gmt_create = (new Date(datas[i].gmtCreate)).toLocaleString();//被修改的用户的创建时间					    
					var user_type = datas[i].userType;                               //被修改的用户的用户类型
					    if(user_type==1){
					    	user_type="主账户";
					    }else if(user_type==2){
					    	user_type="管理员账户";
					    }else if(user_type==3){
					    	user_type="普通用户";
					    }
					var account_status = datas[i].accountStatus; //被修改的用户的账户状态:0账户停用或关闭；1账户启用或正常
					    if(account_status==0){
					    	account_status="停用";
					    }else if(account_status==1){
					    	account_status="启用";
					    }
					var role_id = datas[i].strRoleId;            //被修改的用户的角色id字符串
					var role_name = datas[i].strRoleName;        //被修改的用户的角色名字符串
					var mould_id = datas[i].strMouldId;          //被修改的用户所拥有的角色下的权限id字符串
					var module_name = datas[i].strMouldName;     //被修改的用户所拥有的角色下的权限名字符串
					var reAuId = datas[i].replenishMoudleId;     //被修改的用户所拥有的补充权限id字符串
					var reAuName = datas[i].replenishMoudleName; //被修改的用户所拥有的补充权限名字符串					                       
					var moduleName;                              //显示的该用户所拥有的全部权限名
					var names = new Array();
					var strMod;
					if(module_name!=null){
						strMod = module_name.split(",");
					}
					if(module_name!=null){
						for(var j=0;j<strMod.length;j++){
							names.push(strMod[j]);
						}
					}					
					var strAu=null;
					if(reAuName!=null){
						strAu = reAuName.split(",");
					}
					if(reAuName!=null){
						for(var j=0;j<strAu.length;j++){
							names.push(strAu[j]);
						}
					}										
					var result=[];
					for(var j=0; j<names.length; j++){
					    if(result.indexOf(names[j])==-1){
					        result.push(names[j])
					    }
					}
					var pin = datas[i].auId;//同一用户下的不同角色所拥有的("角色id+权限id")字符串					
					var strings="";
					if(pin != null){
						for(var j=0;j<pin.length;j++){
							if(strings == ""){
								strings = pin[j];
							}else{
								strings += "-"+pin[j]
							}						    	
						}
					}	
					var s_tds = '<td val='+id+'>'+((page-1)*pageSize+(i+1))+'</td>'+
		                        '<td val='+password+'>'+user_name+'</td>'+  
		                        '<td val='+account_num+'>'+gmt_create+'</td>'+
		                        '<td val='+telephone+'>'+user_type+'</td>'+
		                        '<td>'+account_status+'</td>'+
		                        '<td val='+pin+'>'+
					              '<span class="js_span">'+
						            '<span val='+role_id+'>'+role_name+'</span>'+
						            '<div class="js_box">'+
							          '<span val='+reAuId+'>'+result+'</span>'+
						            '</div>'+
					              '</span>'+
				                '</td>'	           	                      
				    var s_td;    										
				    if(currentUserType == 1){   //主账户,按钮均可用	
				    	
				    	if(user_type=="主账户"){  //主账户不能停用,启用,删除自己
				    		s_td = '<td>'+
			                         '<button type="submit" class="button border-blue accredit_add_amend" id="rebuild_button"><span class="icon-edit"></span> 修改</button>'+
			                         '<button type="submit" class="button border-green button_disabled" disabled="true"><span class="icon-check"></span> 启用</button>'+
		  	                         '<button type="submit" class="button border-black button_disabled" disabled="true"><span class="icon-minus"></span> 停用</button>'+
		  	                         '<button type="submit" class="button border-red button_disabled" disabled="true"><span class="icon-trash-o"></span> 删除</button>'+
		                           '</td>'
				    	}else{
				    		s_td = '<td>'+
			                       '<button type="submit" class="button border-blue accredit_add_amend" id="rebuild_button"><span class="icon-edit"></span> 修改</button>'+
			                         '<button type="submit" class="button border-green" id="other_botton"><span class="icon-check"></span> 启用</button>'+
		  	                         '<button type="submit" class="button border-black" id="other_botton"><span class="icon-minus"></span> 停用</button>'+
		  	                         '<button type="submit" class="button border-red" id="other_botton"><span class="icon-trash-o"></span> 删除</button>'+
		                           '</td>'
				    	}
				    }else if(currentUserAccountNum == account_num){//当前账户只有自己的修改按钮可用 
				    	s_td = '<td>'+
				                 '<button type="submit" class="button border-blue accredit_add_amend" id="rebuild_button"><span class="icon-edit"></span> 修改</button>'+
				                 '<button type="submit" class="button border-green button_disabled" disabled="true"><span class="icon-check"></span> 启用</button>'+
			  	                 '<button type="submit" class="button border-black button_disabled" disabled="true"><span class="icon-minus"></span> 停用</button>'+
			  	                 '<button type="submit" class="button border-red button_disabled" disabled="true"><span class="icon-trash-o"></span> 删除</button>'+
			                   '</td>'
				    }else{//既不是主账户,也不是当前账户,则所有按钮均不可用
				    	s_td = '<td>'+
				                 '<button type="submit" class="button border-blue accredit_add_amend button_disabled" disabled="true"><span class="icon-edit"></span> 修改</button>'+
				                 '<button type="submit" class="button border-green button_disabled" disabled="true"><span class="icon-check"></span> 启用</button>'+
			  	                 '<button type="submit" class="button border-black button_disabled" disabled="true"><span class="icon-minus"></span> 停用</button>'+
			  	                 '<button type="submit" class="button border-red button_disabled" disabled="true"><span class="icon-trash-o"></span> 删除</button>'+
			                   '</td>'
				    }
				    table_tr += '<tr class="clear_tr">'+
				                  s_tds+
				                  s_td+
				                '</tr>'	
				}				
				var s_tr = '<tr>'+
	                         '<th width="50">序号</th>'+
	                         '<th>用户名</th>'+      
	                         '<th>创建时间</th>'+
	                         '<th>账号类型</th>'+
	                         '<th>启用/停用</th>'+
	                         '<th>拥有角色、权限</th>'+
	                         '<th width="350">操作</th>'+     
                           '</tr>'+
                           table_tr                            
		        if(maxPage>1){
		        	s_tr = s_tr+
		        	       '<tr>'+ 
                             '<td colspan="8"><div class="pagelist" id="pageClick"> </div></td>'+ 
                           '</tr>'			
		     	}             
         		var $tr = $(s_tr);
         		$("#tabless").append($tr);
         	    // 店铺信息设置添加修改弹出框
	        	var accredit_height=$(".accredit-height");
				var shade_accredit=$(".shade_accredit")
				var accredit_height_height=accredit_height.height();
				var shade_gao=shade_accredit.height()/4*5;
				if (shade_gao>accredit_height_height) {
					shade_accredit.css("height","800px");
				}else{
					shade_accredit.css("height",accredit_height_height+"px");
				}
				var accredit_add_amend=$(".accredit_add_amend");
				accredit_add_amend.click(function(){   					
					shade_accredit.css("display","block");   						
				})
				var accreait_beij=$(".accreait_beij");
				accreait_beij.click(function(){
					shade_accredit.css("display","none");
				})
				//动态添加页码
				var data = new Object();
				data.size = pageSize;
				pagination(maxPage,page,"pageClick","userPaging",data);
				
			}
		},
		//赵滨	打开button按钮
		complete : function() {
            $(".button_disabled").prop("disabled", true);
            if(currentUserType != 1){
            	$("#new_button").prop("disabled", true);
            }           
        }             
	});
}
//新建用户
function newUser(){
	$("#oldpwd").hide();                     //单击新建按钮时将旧密码输入框隐藏
	$("#account_num").removeAttr("disabled");//删除账号输入框的disabled属性
	var authIds = new Array();               //保存选中的补充权限id
	var list = new Array();                  //保存选中的每个 "角色id+权限id" 的字符串数组
    //1.单击新建按钮时清空信息
	//(1).单击新建按钮时清空输入框的信息
	$("#username").val("");             //用户名
	$("#account_num").val("");          //账号
    $("#password").val("");             //新密码
    $("#surePassword").val("");         //确认新密码
	$("#telephone").val("");            //电话
	//(2).清空输入框的提示信息
	$("#username_span").html("");       //用户名
	$("#account_num_span").html("");    //账号
	$("#password_span").html("");       //新密码
    $("#surepassword_span").html("");   //确认新密码
    $("#telephone_span").html("");      //电话
    //$("#allMoudle_span").html("");      //权限
    //2.动态添加信息
	//2.1动态添加角色
	$("#appendRole").find("span").remove();
    findRole();
	//2.2动态添加权限
    $("#addAuth").find("div").remove();
	addAuth(); 	    
	//3.获取信息	    	
	//3.1 角色复选框的单击事件:获取选中的"角色id+该角色拥有的权限id"字符串存在list中
	clickRole(list);
    //3.2 获取补充权限
	clickAuth(authIds);
    $("#submits").unbind("click").click(function(){
    	//3.3清空提示信息
    	$("#username_span").html("");                //用户名
    	$("#account_num_span").html("");             //账号
  	    $("#password_span").html("");                //密码
  	    $("#surepassword_span").html("");            //确认密码
  	    $("#telephone_span").html("");               //电话
  	    //$("#allMoudle_span").html("");             //权限
  	    //3.1 获取输入框的信息
  	    var name = $("#username").val();             //用户名
  	    var accountNum = $("#account_num").val();    //账户
  	    var password = $("#password").val();         //密码
  	    var surepassword = $("#surePassword").val(); //确认密码
  	    var telephone = $("#telephone").val();       //电话
    	//3.2格式判断
    	var ok=true;
    	//var han = /^[\u4e00-\u9fa5|a-z|A-Z|0-9]+$/;
    	if(name == ""){
		    $("#username_span").html("请输入用户名");
			ok=false;
		}	    	
		if(accountNum == ""){
			$("#account_num_span").html("请输入账号");
			ok=false;
		}
		if(password == ""){
			$("#password_span").html("请输入密码");
			ok=false;
		}
		if(surepassword == ""){
			$("#surepassword_span").html("请确认密码");
		}
		if(password != "" && surepassword != "" && password != surepassword){
			$("#surepassword_span").html("密码不一致");
			ok=false;
		}
		var han = /^[0-9]+$/;
        if(!han.test(telephone)){
            $("#telephone_span").html("请输入正确的电话号码");
            ok=false;
        }
		if(telephone == ""){
		    $("#telephone_span").html("请输入电话号码");
			ok=false;
		}
//		if(authIds==""){
//		    $("#allMoudle_span").html("请选择权限");
//			ok=false;
//		}				
		//3.4发送ajax
		if(ok){
			$.ajax({
	    		url:"/user/addUser.do",
	    		type:"post",
	    		data:{"accountNum":accountNum,"password":password,"name":name,"telephone":telephone,"list":list,"authIds":authIds,"ownerId":currentUserOwnerId,"operatorId":currentUserId,"userType":currentUserType},
	    		dataType:"json",
	    		traditional:true,
	    		success:function(result){
	    			if(result.status==0){//成功        								    					
	    				$(".shade_accredit").css("display","none");
	    				userPaging(now_page);
	    			}else if(result.status==1){	    				
	    				$("#account_num_span").html(result.msg);//用户名被占用
	    			}else if(result.status==2){//不是主账户,无权新建角色
	    				alert( result.msg);
	    			}
	    		},
	    		error : function() {
    				alert("新建用户失败!!!");
    			},
    			//赵滨	打开button按钮
    			complete : function() {
    	            $(".button_disabled").prop("disabled", true);
    	            if(currentUserType != 1){
    	            	$("#new_button").prop("disabled", true);
    	            } 
    	        }	
	    	});
		}
    });    		
}
/**
 * 修改角色
 * @returns
 */
function modifyUser(){ 
	var prevAuthIds = new Array();       //保存修改前的补充权限id  
	var authIds = new Array();           //保存修改后的补充权限id       
    var prevList = new Array();          //保存修改前 "角色id+权限id" 的字符串数组
    var list = new Array();	             //保存修改后 "角色id+权限id" 的字符串数组    
    //1.清空输入框的信息
    //1.1 清空提示信息
	$("#username_span").html("");        //用户名
	$("#account_num_span").html("");     //账户
    $("#oldpassword_span").html("");     //旧密码
    $("#password_span").html("");        //新密码
    $("#surepassword_span").html("");	 //确认新密码    	    
    $("#telephone_span").html("");       //电话  
    //$("#allMoudle_span").html("");     //权限
    //1.2 清空输入框的信息
    $("#username").val("");              //用户名
    $("#account_num").val("");           //账户
    $("#oldPassword").val("");           //旧密码
    $("#password").val("");              //新密码
    $("#surePassword").val("");          //确认新密码
    $("#telephone").val("");             //电话  
  	//2.获取信息  被修改的账户的信息(修改前)
  	var $this = $(this).parent().parent();
  	var id = $this.find("td:first").attr("val");                            //获取被修改的用户的id
  	var account_num = $this.find("td:eq(2)").attr("val");                   //获取被修改的用户的账号
  	var name = $this.find("td:first").next().text();                        //获取被修改的用户的用户名			
  	var tel = $this.find("td:eq(3)").attr("val");                           //获取被修改的用户的电话
  	var userType = $this.find("td:eq(3)").text();                           //被修改的用户的类型
  	var auid = $this.find("td:eq(5)").attr("val");                          //同一用户下的全部"角色id+权限id"字符串			
  	var mouldIds = $this.find("td:eq(5)").find("span:eq(2)").attr("val");   //用户修改前拥有的补充权限id字符串
  	//3.添加信息
  	//3.1 在输入框中添加:用户名,电话,账号
  	$("#username").val(name);           //用户名
  	$("#telephone").val(tel);           //电话  	
  	$("#account_num").val(account_num); //账号
  	if(userType == "主账户"){
        $("#build_role").hide();        //角色隐藏
        $("#form_group").hide();        //权限隐藏
  	}else{
  		$("#build_role").show();        //角色显示
        $("#form_group").show();        //权限显示
  		//3.2动态添加角色
  		$("#appendRole").find("span").remove();
  	    findRole();
  		//3.3动态添加权限
  	    $("#addAuth").find("div").remove();
  		addAuth();
  	}
  	//当前登录的账户不可以修改自己的账号信息,主账户可以修改除自己以外的账户的信息
    //4.功能拦截
    //4.1单击修改按钮时将旧密码输入框显示
	$("#oldpwd").show();
	//4.2 主账号:弹出框中的内容均可修改
	if(currentUserType == 1){	
		if(currentUserAccountNum ==  account_num){
  			$("#account_num").attr("disabled",true);   
  		}else{
  			$("#account_num").removeAttr("disabled");             //账号		
  	    	$("#appendRole").find("input").removeAttr("disabled");//角色   	
  	    	$("#form_group").find("input").removeAttr("disabled");//权限
  		}		
	}else if(currentUserType != 1 && currentUserAccountNum == account_num){
		//非主账号:只能修改自己的用户名,密码,电话		
		$("#account_num").attr("disabled",true);              //账号		
        $("#appendRole").find("input").attr("disabled",true); //角色        
        $("#form_group").find("input").attr("disabled",true); //权限
	}
  	//将修改前用户拥有的"角色+权限"字符串添加到list和prevList数组中
  	if(auid != "null"){
  		var auids = auid.split(",");
  		for(var i=0;i<auids.length;i++){
  			var str = "";
  			var aid = auids[i].split("-");
  			for(var j=0;j<aid.length;j++){
  				if(str == ""){
  					str = aid[j];
  				}else{
  					str += "-" + aid[j];
  				}
  			} 	
  			list.push(str);
  			prevList.push(str);
  		}
  	}
  	/*
  	 * 5.将用户修改前拥有的角色设置选中;将该角色下的权限设置选中,并将该权限添加disabled属性
  	 */  
  	for(var i=0;i<list.length;i++){
  		var ID = list[i].split("-");
  		//将用户修改前拥有的角色设置选中
  		var roleId = ID[0];
  		for(var j=0;j<$("#appendRole span").length;j++){         //动态生成的角色的长度
  			var $span = $("#appendRole").find("span:eq("+j+")");
  			var roleid = $span.find("input").attr("val");        //获取动态生成的角色id
  			if(roleId == roleid){  			    
  				$span.find(":first").prop("checked",true);       //将角色状态设置为选中
  			}
  		}
  		//将该角色下的权限设置选中,并将该权限添加disabled属性
  		for(var j=1;j<ID.length;j++){
  			for(var k=0;k<$("#addAuth").find("input").length;k++){//权限div中的input的个数
  				$obj = $("#addAuth").find("input:eq("+k+")");
  				if(ID[j] == $obj.attr("val")){                    //获取每个input的id					
  					$obj.prop("checked",true);                    //将该权限添加选中状态			
  					$obj.attr("disabled",true);                   //将该权限设置不可修改
  					$obj.parent().parent().prev().find("input").prop("checked",true);//将该权限的父模块设置选中
  				}
  			}
  		}
  	}	 	    
	//将用户修改前拥有的补充权限添加到authIds,prevAuthIds中
  	if(mouldIds != "null"){
  		var replenId = mouldIds.split(",");
  		for(var i=0;i<replenId.length;i++){
  			authIds.push(replenId[i]);
  			prevAuthIds.push(replenId[i]);
  		}
  	}  	
  	//将用户修改前拥有的补充权限添设置选中
  	for(var i=0;i<authIds.length;i++){
  		 var leng = $("#addAuth").find("input").length;                            //权限div中的input的个数
  		 for(var j=0;j<leng;j++){
  			 var $obj = $("#addAuth").find("input:eq("+j+")");
  			var inputId = $obj.attr("val");                                        //获取每个input的id
  			if(inputId == authIds[i]){
  				$obj.prop("checked",true);                                         //将该权限添加选中状态  
  				$obj.parent().parent().prev().find("input").prop("checked",true);  //将该权限的父模块设置选中
        	}
  		 }
  	}	
	//6.复选框的单击事件
    //6.1 角色复选框的单击事件(选中某个角色时,该角色相应的权限选中;某个角色去除选中时,该角色的相应权限去除选中)  
	clickRole(list); 	
    //6.2 权限复选框的单击事件
	clickAuth(authIds);
    $("#submits").unbind("click").click(function(){
    	//7.1清空提示信息
		$("#username_span").html("");                   //用户名
  	    $("#account_num_span").html("");                //账户
	    $("#oldpassword_span").html("");                //旧密码
	    $("#password_span").html("");                   //新密码
	    $("#surepassword_span").html("");               //确认新密码	    	    
	    $("#telephone_span").html("");                  //电话  
	    //$("#allMoudle_span").html("");                //权限
    	//7.2 获取修改后的信息
    	var reName = $("#username").val();              //用户名
  	    var re_account_num = $("#account_num").val();   //账户
  	    var re_password = $("#oldPassword").val();      //旧密码
  	    var reNewPassword = $("#password").val();       //新密码
  	    var reSurePassword = $("#surePassword").val();  //确认新密码
  	    var reTelephone = $("#telephone").val();        //电话  
  	    //6.3格式判断
	    var ok=true;
  	    if(reName == ""){
				$("#username_span").html("请输入用户名");
				ok=false;
		    }
  	    if(currentUserType==1){//主账户
  	    	if(re_account_num == ""){
  				$("#account_num_span").html("请输入账号");
  				ok=false;
  		    }
  	    }else{
  	    	re_account_num = '';
  	    } 
		if(re_password == ""){
			$("#oldpassword_span").html("请输入密码");
			ok=false;
		}
		if(reNewPassword != "" && reSurePassword != "" && reNewPassword != reSurePassword){
		    $("#surepassword_span").html("两次输入的密码不一致");
		    ok=false;
		}
		if(reNewPassword != "" && reSurePassword == ""){
		    $("#surepassword_span").html("请确认密码");
		    ok=false;
		}
		if(reNewPassword == "" && reSurePassword != ""){
		    $("#password_span").html("请输入新密码");
		    ok=false;
		}
		var han = /^[0-9]+$/;
        if(!han.test(reTelephone)){
            $("#telephone_span").html("请输入正确的电话号码");
            ok=false;
        }
		if(reTelephone == ""){
		    $("#telephone_span").html("请输入电话号码");
			ok=false;
		}
//		if(arrs.length==0){
//			$("#allMoudle_span").html("请选择权限");
//			ok=false;
//		}
		if(ok){
		    $.ajax({
		    	url:"/user/rebuildUser.do",
    			type:"post",
    			data:{"id":id,"accountNum":re_account_num,"name":reName,"oldPassword":re_password,"newPassword":reNewPassword,"telephone":reTelephone,"prevAuthIds":prevAuthIds,"authIds":authIds,"prevList":prevList,"list":list,"ownerId":currentUserOwnerId,"operatorId":currentUserId,"currUserType":currentUserType},
    			dataType:"json",
    			traditional:true,
    			success:function(result){
                    if(result.status==0){ 
    				    $(".shade_accredit").css("display","none");
                    	userPaging(now_page);    					
    				}else if(result.status==1){
    					var message = result.message;
    					var msg = result.msg;
    					if(message != null){    						
        					$("#oldpassword_span").html(message);  //密码错误
    					}
    					if(msg != null){    						
    						$("#account_num_span").html(msg);      //账号被占用
    					}    					
    				}else if(result.status==2){//不是主账户无权修改角色
    					$("#role_span_info").text(result.msg);	
    					$("#allMoudle_span").html(result.msg);
    				}
    			},
    			error:function() {
    				alert("修改用户失败!!!");
    			},
    			//赵滨	打开button按钮
    			complete : function() {
    	            $(".button_disabled").prop("disabled", true);
    	            if(currentUserType != 1){
    	            	$("#new_button").prop("disabled", true);
    	            } 
    	        }
		    });
		}
	});
}  
/**
 * 其他按钮:(停用,启用,删除)
 * @returns
 */
function otherButton(){
	var $Account = $(this).parent().parent();
	var id = $Account.find("td:first").attr("val");//获取被修改的用户的id	
	var buttonName = $(this).text();//按钮名
	var ok=false;
	//只有主账户才可以进行除了自己外的删除,启用,停用操作
	if(currentUserType==1){
		if(buttonName.trim() == "删除"){
			//删除按钮:提示用户是否删除
			if(del() == true){
				ok=true;
			}else{
				ok=false;
			}
		}else{
			ok=true;
		}
		if(ok){
			$.ajax({
			    url:"/user/otherButton.do",
				type:"post",
				data:{"id":id,"ownerId":currentUserOwnerId,"operatorId":currentUserId,"buttonName":buttonName,"currentUserType":currentUserType},
				dataType:"json", 
				success:function(result){
					if(result.status==0){	
						if("启用"==result.data){	
							$Account.find("td:eq(4)").text("启用");
						}else if("停用"==result.data){
							$Account.find("td:eq(4)").text("停用");
						}else if("删除"==result.data){
							if(now_page>1){
								if(num-1<1){
									now_page = now_page-1;
								}
							}					
		                	userPaging(now_page);
						}			    
					}else if(result.status==1){
						alert(result.msg);
					}	    
				},
				//赵滨	打开button按钮
				complete : function() {
		            $(".button_disabled").prop("disabled", true);
		            if(currentUserType != 1){//只有主账户才可以进行除了自己外的删除,启用,停用操作
		            	$("#new_button").prop("disabled", true);
		            } 
		        }
			});
		}
	}else{
		alert("您没有此权限");
	}		
}
//用户的提示信息
function del(){
	var msg = "您真的确定要删除吗？\n\n请确认！";
	if (confirm(msg)==true){
	    return pan=true;	    
	}else{
		return pan=false;		
	}
} 
/**
 * 动态添加角色
 * @returns
 */
function findRole(){	
	$.ajax({
  	    url:"/user/findRole.do",
  	    async:false,
		type:"post",
		data:{"ownerId":currentUserOwnerId},
		dataType:"json", 
		success:function(result){
		    if(result.status==0){	
		    	var datas = result.data;
		    	if(datas != ""){
		    		for(var i=0;i<datas.length;i++){
			    		var roleId=datas[i].roleId;                 //角色id
			    		var roleName=datas[i].roleName;             //角色名
			    		var childModuleId =datas[i].childModuleId;  //权限id的字符串			    		
			    		var add_div ='<span style="margin-right: 10px;display:inline-block;"><input type="checkbox" val='+roleId+' class="NO'+roleId+'"/>'+roleName+'</span>'
			    		var $add_div = $(add_div);  
			    		$add_div.data("roleId",roleId);
			    		$add_div.data("roleName",roleName);
			    		$add_div.data("childModuleId",childModuleId);    			    		    
			            $("#appendRole").append($add_div);			            
			    	}
		    	}else{
		    		$("#role_span_info").text("您还没有创建角色!");	
		    	}
		    }	    
		},
		error:function() {
			alert("加载角色失败!!!");
		},
		//赵滨	打开button按钮
		complete : function() {
            $(".button_disabled").prop("disabled", true);
            if(currentUserType != 1){
            	$("#new_button").prop("disabled", true);
            } 
        }
    });
}
/**
 * 动态添加权限
 * @returns
 */
function addAuth(){
	$.ajax({
	    url:"/user/findAuth.do",
	    type:"post",
	    async:false,
	    data:{"ownerId":currentUserOwnerId},
	    dataType:"json",
	    success:function(result){
	    	if(result.status==0){
	    		var datas = result.data;
	    		if(datas != ""){
	    			var span = "";
	    			for(var i=0;i<datas.length;i++){
	    				var parentModuleId = datas[i].parentModuleId;      //父模块id
	    				var parentModuleName = datas[i].parentModuleName;  //父模块名
	    				var StringIds = datas[i].childModuleId;            //子模块id
	    				var StringNames = datas[i].childModuleName;        //子模块名
	    				var inputs = '<div style="width: 20%;float: left;">'+
	    					           '<span class="NO_2"><input type="checkbox" val='+parentModuleId+'></input>'+parentModuleName+'</span>'+
	    					         '</div>'
	    				var StringId = StringIds.split(",");
	    				var StringName = StringNames.split(",");
	    				var divs = "";	    				
	    				for(var j=0;j<StringName.length;j++){
	    					var childModuleId = StringId[j];
	    					var childModuleName = StringName[j];
	    					var childInput = '<span class="NO_3" style="margin-right: 10px;display:inline-block;"><input type="checkbox" val='+childModuleId+'></input>'+childModuleName+'</span>'
	    					divs += childInput;	    						
	    				}
	    				var input = '<div>'+
	    					          inputs+ 
	    					          '<div style="width: 80%;float: left;">'+
	    					          divs+
	    					  	      '</div>'+
	    			                '</div>'
	    				span += input;	  	      
	    			}
	    			var spans = '<div>'+
	        		              '<span class="NO_1">查看/补充权限</span>'+
		        	            '</div>'+
		        	            span+
		        	            '<div class="tips"><span id="allMoudle_span" style="color:red;"></span></div>'
	    			var $spans = $(spans);
	    			$("#addAuth").append($spans);
	    		}
	    	}
	    },
	    error:function(){
	    	alert("加载权限失败");
	    },
	  //赵滨	打开button按钮
		complete : function() {
            $(".button_disabled").prop("disabled", true);
            if(currentUserType != 1){
            	$("#new_button").prop("disabled", true);
            } 
        }
	});
}
/**
 * 角色复选框的单击事件
 * @param list:"角色id+权限id"的字符串
 * @returns
 */
function clickRole(list){
	$("#appendRole").unbind().on('click',"span input",function(){
		var len = $("#addAuth").find("input").length;               //权限模块拥有的input的个数
	    var roleId =$(this).parent().data("roleId");                //角色id 		        
	    var childModuleId =$(this).parent().data("childModuleId");  //该角色拥有的权限id字符串
	    var strs = roleId+"-"+childModuleId.replace(/,/g,"-");      //"角色id+权限id" 的字符串	  
	    var arrModuleId = childModuleId.split(",");
	    if($(this).is(":checked")){                                 //选中角色时  
	        for(var i=0;i<arrModuleId.length;i++){                  //遍历该角色拥有的权限id
	            var moduleIdOfRole = arrModuleId[i];                //该角色拥有的权限id
	          	for(var j=0;j<len;j++){                             //len 权限模块拥有的input的个数
	          		var $input = $("#addAuth").find("input:eq("+j+")");
	          		var moduleId = $input.attr("val");              //获取每个input的id
	          		if(moduleIdOfRole == moduleId){	          			
	          			$input.prop("checked",true);                //将该角色拥有的权限设置选中
	          			$input.attr("disabled",true);               //将该权限设置不可修改	          			
	          			$input.parent().parent().prev().find(":input").prop("checked",true);//子权限模块选中时,父权限模块也设置选中
	          		}
	          	}
	        }
	        list.push(strs);//将该角色的"角色id+权限id" 的字符串添加到list中	          
	    }else{ 
	    	/*
	    	 * 该角色去除选中时:
	    	 */
	    	//将list中的strs删除	    	
	 		for(var i=0;i<list.length;i++){
	 			if(strs == list[i]){
	 				list.splice(i,1);
	 			}
	 		}
	 		/*
	 		 * list:被选中的"角色id+权限id"字符串数组
	 		 * arrModuleId:取消选中的该角色所拥有的权限id
	 		 * 找出(被选中的角色含有的权限id)和(取消选中的角色含有的权限id)中相同的权限id,
	 		 * 并将其从arrModuleId中删除,即:不做任何处理 
	 		 */
	    	for(var i=0;i<list.length;i++){
	    		var roleAuth = list[i].split("-");
	    		for(var j=1;j<roleAuth.length;j++){
	    			for(var k=0;k<arrModuleId.length;k++){//该角色拥有的权限id数组
	    				if(roleAuth[j]==arrModuleId[k]){
	    					arrModuleId.splice(k,1);
	    					k=k-1;
	    				}
	    			}
	    		}
	    	}
	    	//将剩余的arrModuleId中的权限id,取消选中,并将disabled属性删除
	      	for(var i=0;i<arrModuleId.length;i++){                                    //该角色拥有的权限id数组
	      		var moduleIdOfRole = arrModuleId[i];                                  //该角色拥有的权限id
	      		for(var j=0;j<len;j++){                                               //len 权限模块拥有的input的个数
	          		var moduleId = $("#addAuth").find("input:eq("+j+")").attr("val"); //获取每个input的id
	          		if(moduleIdOfRole == moduleId){	 
	          			var $input = $("#addAuth").find("input:eq("+j+")");
	          			$input.prop("checked",false);                                  //该角色相应的权限自动去除选中
	          			$input.removeAttr("disabled");                                 //删除disabled属性	          			
	 	                var leng = $input.parent().parent().find(":input").length;     //该子模块的父模块拥有的子模块个数
	          			var ok=true;
	 	            	for(var m=0;m<leng;m++){
	 	            	    //该父模块的子模块的选中状态:选中为true,未选中为false
	 	            		var boolean = $input.parent().parent().find("input:eq("+m+")").is(":checked");
	 						//如果有一个子模块选中,则将ok改为false,结束循环
	 						if(boolean){
	 							ok=false;	
	 							break;
	 						}	 							
	 					}
	 					if(ok){//当全部的子模块都去除选中,则父模块去除选中
	 						$input.parent().parent().prev().find("input").prop("checked",false);
	 					}
	          		}  		            			
	          	}
	      	}
	 		
	    }
	});
}
/**
 * 权限复选框的单击事件
 * @param authIds:被选中的权限id
 * @returns
 */
function clickAuth(authIds){
	$("#addAuth").unbind().on("click","input",function(){
		var $Parent = $(this).parent().parent();
	    //获取父模块的信息,如果父模块的信息是空,则说明单击的是父模块;如果父模块的信息不是空,则说明单击的是子模块
	    var parentModuleInfo = $Parent.prev().find("span").text();
	    var moudleId = $(this).attr("val");                                     //获取此模块的id
	    if(parentModuleInfo != ""){                                             //单击子模块
	    	if($(this).is(":checked")){                                         //如果此模块被选中	    			
	    		authIds.push(moudleId);                                         //将此模块的id添加到数组中
	    		//子权限模块选中时,父权限模块也设置选中
	    		$Parent.prev().find(":input").prop("checked",true);
	    	}else{//如果此模块去除选中
	    	    //将authIds中的该权限名删除
				for(var i=0;i<authIds.length;i++){
			  	    if(moudleId==authIds[i]){
			  		    authIds.splice(i,1);
			  			i=i-1;			  			    
			  		}
			  	}
				//该子模块的父模块拥有的子模块个数
				var leng = $Parent.find("input").length;
				var ok=true;
	            for(var m=0;m<leng;m++){
	            	//该父模块的子模块的选中状态:选中为true,未选中为false
	            	var boolean = $Parent.find("input:eq("+m+")").is(":checked");
					//有一个子模块选中,则将ok改为false,结束循环
					if(boolean){
						ok=false;	
						break;
					}	 							
				}
				if(ok){//当全部的子模块都去除选中,则父模块去除选中
					$Parent.prev().find("input").prop("checked",false);
				}
	    	}
        }else{//单击父模块	    	
			var num = $Parent.next().find("span").length;//父模块中的子模块的长度
			if($(this).is(":checked")){//选中父模块
				for(var a=0;a<num;a++){					
					$Parent.next().find("input:eq("+a+")").prop("checked",true);//将所有的子模块设置选中								
					var aIds = $Parent.next().find("input:eq("+a+")").attr("val");//获取子模块id				
					authIds.push(aIds);//子模块id添加到authIds中
				}
			}else{//去除父模块的选中状态					
				for(var i=0;i<authIds.length;i++){
					for(var b=0;b<num;b++){
						//如果该子模块是属于角色的,则此子模块不做处理
						var status = $Parent.next().find("input:eq("+b+")").attr("disabled");
						if(status == undefined){							
							$Parent.next().find("input:eq("+b+")").prop("checked",false);//将所有的子模块选中状态取消		  					
		  					var aIds = $Parent.next().find("input:eq("+b+")").attr("val");//获取子模块id
							//将该父模块的所有子模块删除
							if(aIds==authIds[i]){
								authIds.splice(i,1);
						  		i=i-1;	
						  	}
						}						
					}
				}
			}
	    }
    });
}