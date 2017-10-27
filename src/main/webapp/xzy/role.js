var currentUserId;        //当前用户的id
var currentUserType;      //当前用户的类型(1,2,3)
var currentUserOwnerId;   //当前用户的ownerId
var currentUserAccountNum;//当前用户的账号
var now_page;             //当前页
var num;                  //每页查询出来的记录数
$(function(){
	//单击阴影部分时弹出框页面隐藏
	$("#shades").click(function(){		
		$(".shade_accredit").css("display","none");	 
	})	
	//1.分页查询
	getPage(1);
	//2.新建角色
	$("#newRole_button").click(addNewRole);
	//3.修改角色 
	$("#table_tr").on("click","#rebuildRole",modifyRole);
	//4.删除角色
	$("#table_tr").on("click","#deleteRole",deleteRole);			
});
/**
 * 1.分页查询
 * @param page :当前页
 * @returns
 */
function getPage(page){
	now_page = page;
    $("#table_tr").find("tr").remove();
	$.ajax({
    	url:"/role/findPaging.do",
		type:"post",
		data:{"page":page},
		dataType:"json",			
		success:function(result){
			if(result.status==0){
				var pageSize = result.pageSize;            //每页的记录数
				var maxPage = result.maxPage;              //总的页数
				//当前登录的账号的信息
				var datum = result.datum;
				currentUserId = datum.id;                  //当前登录的用户的id
				currentUserType = datum.userType;          //当前登录的用户的类型(1,2,3)
				currentUserOwnerId = datum.ownerId;        //当前登录的用户的ownerId
				currentUserAccountNum = datum.accountNum;  //当前登录的用户的账号
				//每条记录的信息
				var datas = result.data;
				var s_tr;
				num = datas.length;
				for(var i=0;i<datas.length;i++){
					var roleId = datas[i].roleId;                                    //角色id
					var roleName = datas[i].roleName;                                //角色名
					var gmtCreate = (new Date(datas[i].gmtCreate)).toLocaleString(); //角色创建时间
					var roleProfile = datas[i].roleProfile;                          //角色描述
					var moduleName = datas[i].strMouldName;                          //该角色拥有的权限名的字符串
					var moduleId = datas[i].strMouldId;                              //该角色拥有的权限id的字符串				
					s_tr +='<tr>'+
			            '<td val='+roleId+'>'+((page-1)*pageSize+(i+1))+'</td>'+
			            '<td>'+roleName+'</td>'+  
			            '<td>'+gmtCreate+'</td>'+
			            '<td>'+roleProfile+'</td>'+
			            '<td val='+moduleId+'>'+moduleName+'</td>'+
			            '<td>'+                         
				          '<button type="submit" class="button border-blue accredit_add_amend" id="rebuildRole"><span class="icon-edit"></span> 修改</button>'+
				          '<button type="submit" class="button border-red" id="deleteRole"><span class="icon-trash-o"></span> 删除</button>'+
			            '</td>'+
		              '</tr>'		               
				}				 
				var table_tr =  '<tr id="prepend">'+
                                  '<th width="50">序号</th>'+
                                  '<th width="100">角色名</th>'+       
                                  '<th>创建时间</th>'+
                                  '<th width="100">描述</th>'+
                                  '<th>拥有权限</th>'+
                                  '<th width="200">操作</th>'+     
                                '</tr>'+
				                s_tr
				if(maxPage>1){
					table_tr = table_tr+
						       '<tr>'+ 
					             '<td colspan="8"><div class="pagelist" id="pageClick"> </div></td>'+ 
					           '</tr>'			
				} 	              
				var $tr = $(table_tr); 				
		        $("#table_tr").append($tr);
		        //添加修改弹出框
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
				//动态添加页数
				var data = new Object();
				data.size = pageSize;
				pagination(maxPage,page,"pageClick","getPage",data);
			}else if(result.status==1){
				alert("您没有此权限");
			}
		}
    });
}
/**
 * 2.新建角色
 * @returns
 */
function addNewRole(){	
	var authIds = new Array();                          //存放选中状态下的权限id的数组
	var authName = new Array();                         //存放选中状态下的权限名的数组
	//1.单击新建按钮时清空信息
	//1.1清空输入框的信息
	$("#rolename").val("");                             //清空角色名
	$("#roleprofile").val("");                          //清空角色描述
	//1.2清空提示信息
	$("#rolename_span").html("");                       //清空角色名提示			
	$("#roleprofile_span").html("");                    //清空角色描述提示
	$("#allMoudle_span").html("");                      //清空权限提示
	//2.动态添加权限
	$("#addAuthes").find("div").remove();
	addRoleAuth();
	//3.权限复选框的单击事件
	clickAuthInput(authIds,authName);		
	//4.提交按钮
	$("#submit").unbind("click").click(function(){	
		//4.3清空提示信息
    	$("#rolename_span").html("");                   //清空角色名提示			
		$("#roleprofile_span").html("");                //清空角色描述提示
		$("#allMoudle_span").html("");                  //清空权限提示
		//4.1 获取输入框的信息
		var roleName = $("#rolename").val();            //获取角色名			
		var roleProfile = $("#roleprofile").val();      //获取角色描述
		//4.2格式判断
		var ok=true;
		//var han = /^[\u4e00-\u9fa5|a-z|A-Z|0-9]+$/;
        //if(!han.test(role_name)){
        	//$("#rolename_span").html("角色名只能是中文,英文,数字");
        	//ok=false;
        //}
		if(roleName == ""){
			$("#rolename_span").html("请输入角色名");
			ok=false;
		}
		if(roleProfile == ""){
			$("#roleprofile_span").html("请输入角色描述");
			ok=false;
		}
		if(authIds.length==0){
			$("#allMoudle_span").html("请选择权限");
			ok=false;
		}
		if(ok){
			$.ajax({
    			url:"/role/createRole.do",
    			type:"post",
    			data:{"roleName":roleName,"roleProfile":roleProfile,"authIds":authIds,"ownerId":currentUserOwnerId,"operatorId":currentUserId,"currentUserType":currentUserType},
    			dataType:"json",
    			traditional: true,
    			success:function(result){
    				if(result.status==0){       	   					
    				    $(".shade_accredit").css("display","none");//父模块中的子模块的长度    						
    					getPage(now_page);
    				}else if(result.status==1){  					
    					$("#rolename_span").html(result.msg);//角色名被占用
    				}else if(result.status==2){
    					alert("您没有此权限");
    				}
    			},
    			error:function() {
    				alert("新建角色失败!!!");
    			}
    		});
		}
	});
}
/**
 * 修改角色
 */
function modifyRole(){
	var authIds = new Array();                              //存放选中状态下的权限id的数组
	var authName = new Array();                             //存放选中状态下的权限名的数组
	//1.清空信息
	//1.1清空输入框的信息
	$("#rolename").val("");                                 //清空角色名        
    $("#roleprofile").val("");                              //清空角色描述
	//1.2清空提示信息
    $("#rolename_span").html("");                           //清空角色名提示			
	$("#roleprofile_span").html("");                        //清空角色描述提示	
	$("#allMoudle_span").html("");                          //清空权限提示 
	//2.获取信息
	var $this = $(this).parent().parent();
	var roleId =$this.find("td:first").attr("val");         //角色id;	
	var roleName = $this.find("td:first").next().text();    //角色名
    var roleProfile = $this.find("td:eq(3)").text();        //角色描述
    var strAuthority = $this.find("td:eq(4)").attr("val");  //该角色修改前的权限id字符串
    var strAuthName = $this.find("td:eq(4)").text();        //该角色修改前的权限名字符串
    //3.单击修改按钮时添加信息
    //3.1 添加输入框的信息
    $("#rolename").val(roleName);                           //添加角色名
    $("#roleprofile").val(roleProfile);                     //添加角色描述
    //3.2 动态添加权限
    $("#addAuthes").find("div").remove();
	addRoleAuth();
	//3.3 将该角色修改前拥有的权限设置选中;并将该权限的id添加到authIds中;弹出框中的已选择XX个权限,添加authIds的个数
	var inputLength = $("#addAuthes").find("input").length; //权限div的input的个数
	var arrAuthName = strAuthName.split(",");
	for(var i=0;i<arrAuthName.length;i++){
		authName.push(arrAuthName[i]);
	}
	var arrs = strAuthority.split(",");
	for(var i=0;i<arrs.length;i++){
		for(var j=0;j<inputLength;j++){
			var roleIds = $("#addAuthes").find("input:eq("+j+")").attr("val");//获取权限id			
			if(roleIds == arrs[i]){
				$("#addAuthes").find("input:eq("+j+")").prop("checked",true);				
			}
		}
		authIds.push(arrs[i]);
		for(var a=0;a<=authIds.length;a++){
	  	    $("#addAuthes").find("div:eq(0)").find("div:eq(1)").find("span").text(a);
	  	}
	}
	//4.权限复选框的单击事件
	clickAuthInput(authIds,authName);
	//5.提交按钮
    $("#submit").unbind("click").click(function(){
    	//5.3清空提示信息
		$("#rolename_span").html("");                //清空角色名提示			
		$("#roleprofile_span").html("");             //清空角色描述提示	
		$("#allMoudle_span").html("");               //清空权限提示
    	//5.1 获取输入框的信息
		var role_name = $("#rolename").val();        //获取修改后的角色名	        
	    var role_profile = $("#roleprofile").val();  //获取修改后的角色描述	
	    //5.2 格式判断
	    var ok=true; 
//	    var han = /^[\u4e00-\u9fa5|a-z|A-Z|0-9]+$/;
//      if(!han.test(role_name)){
//          $("#rolename_span").html("角色名只能是中文,英文,数字");
//        	    ok=false;
//      }
	    if(role_name == ""){
			$("#rolename_span").html("角色名不能为空");
			ok=false;
		}
		if(role_profile == ""){
			$("#roleprofile_span").html("角色描述不能为空");
			ok=false;
		}
		if(authIds.length==0){
			$("#allMoudle_span").html("请选择权限");
			ok=false;
		}		
		if(ok){
	    	$.ajax({
				url:"/role/modifyRole.do",
				type:"post",
				data:{"roleId":roleId,"roleName":role_name,"roleProfile":role_profile,"authIds":authIds,"ownerId":currentUserOwnerId,"operatorId":currentUserId,"prevAuId":strAuthority,"currentUserType":currentUserType},
				dataType:"json",
				traditional: true,
				success:function(result){
					if(result.status==0){							
    				    $(".shade_accredit").css("display","none");//单击提交按钮时,页面隐藏
    				    //修改角色信息
    				    $this.find("td:first").next().text(role_name);//角色名
    				    $this.find("td:eq(3)").text(role_profile);//角色描述   				    
    				    $this.find("td:eq(4)").attr("val",authIds);//该角色拥有的权限id字符串
    				    $this.find("td:eq(4)").text(authName.join(","));//该角色拥有的权限id字符串
					}else if(result.status==1){
						//角色名被占用
    					$("#rolename_span").html(result.msg);
					}else if(result.status==2){
						alert(result.message);
					}						
				},
				error : function() {
    				alert("修改角色失败!!!");
    			}
			});
	    }
	});
}
/**
 * 删除角色
 */
function deleteRole(){
	var $this = $(this).parent().parent();
	var roleId =$this.find("td:first").attr("val");//角色id;	
    var strAuthId = $this.find("td:eq(4)").attr("val");//该角色修改前的权限id字符串
    var ok=false;
    if(del() == true){
		ok=true;
	}else{
		ok=false;
	}  
    if(ok){
    	$.ajax({
      		url:"/role/delRole.do",
      		type:"post",
      		data:{"roleId":roleId,"ownerId":currentUserOwnerId,"currentUserType":currentUserType},
      		dataType:"json",
      		success:function(result){
      			if(result.status==0){
      				if(now_page>1){
    					if(num-1<1){
    						now_page = now_page-1;
    					}
    				}
      				getPage(now_page);
      			}
      		},
      		error:function(){
      			alert("删除角色失败");
      		}
      	});
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
 * 新建按钮或修改按钮时动态添加权限
 * @returns
 */
function addRoleAuth(){
	$.ajax({
	    url:"/role/findAuth.do",
	    type:"post",
	    async:false,
	    data:{"ownerId":currentUserOwnerId},
	    dataType:"json",
	    success:function(result){
	    	if(result.status==0){
	    		var datas = result.data;
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
        		              '<div style="width: 20%;float: left;">'+
        		                '<span class="NO_1"><input type="checkbox" id="all"/>所有权限</span>'+
        	                  '</div>'+
        	                  '<div style="width: 80%;float: left;">'+
				                '已选择<span>0</span>个权限'+
        	                  '</div>'+
    	                    '</div>'+
	                        span+
	                        '<div class="tips"><span id="allMoudle_span" style="color:red;"></span></div>'
	                var $spans = $(spans);
    			$("#addAuthes").append($spans);
	    	}		    	
	    },
	    error:function(){
	    	alert("加载权限失败");
	    }
	});
}
/**
 * 权限复选框的单击事件
 * @param authIds :权限id的数组
 * @returns
 */
function clickAuthInput(authIds,authName){
	$("#addAuthes").unbind().on("click","input",function(){
		//获取父模块的信息,如果父模块的信息是空,则说明单击的是父模块;如果父模块的信息不是空,则说明单击的是子模块
		var $parent = $(this).parent().parent();
	    var parentModuleInfo = $parent.prev().find("span").text(); //父模块名
	    var moudleId = $(this).attr("val");                        //获取此模块的id
	    var moudleName = $(this).parent().text();                  //获取此模块的权限名	    
	    if(parentModuleInfo != ""){                                //单击子模块
	    	if($(this).is(":checked")){                            //如果此模块被选中	    			
	    		authIds.push(moudleId);                            //将此模块的id添加到数组中
	    		authName.push(moudleName);                         //将此模块的权限名添加到数组中	    		
	    		$parent.prev().find(":input").prop("checked",true);//子权限模块选中时,父权限模块也设置选中
	    		//弹出框中的  已选择XX个权限  选中时添加个数
			    for(var i=0;i<=authIds.length;i++){
		  		    $("#addAuthes").find("div:eq(0)").find("div:eq(1)").find("span").text(i);
		  		}
	    	}else{//如果此模块去除选中
	    		for(var i=0;i<authIds.length;i++){
				    //如果去除选中,将authIds中的该权限名删除
			  	    if(moudleId==authIds[i]){
			  		    authIds.splice(i,1);
			  			    i=i-1;			  			    
			  		}
			  	}
	    		for(var i=0;i<authName.length;i++){
	    			if(moudleName == authName[i]){
	    				authName.splice(i,1);
	    				i=i-1;
	    			}
	    		}
	    		//弹出框中的  已选择XX个权限  去除选中时减少个数
	    		for(var i=0;i<=authIds.length;i++){
	    			$("#addAuthes").find("div:eq(0)").find("div:eq(1)").find("span").text(i);		    		
		  		}
	    		//当该子模块的父模块的全部子模块的选中状态均去除,则将该子模块的父模块的选中状态去除
	    		//该子模块的父模块拥有的子模块个数
				var leng = $parent.find("input").length;
				var ok=true;
	            for(var m=0;m<leng;m++){
	            	//该父模块的子模块的选中状态:选中为true,未选中为false
	            	var boolean = $parent.find("input:eq("+m+")").is(":checked");
					//有一个子模块选中,则将ok改为false,结束循环
					if(boolean){
						ok=false;	
						break;
					}	 							
				}
				if(ok){//当全部的子模块都去除选中,则父模块去除选中
					$parent.prev().find("input").prop("checked",false);
				}
	    	}
	    }else if($(this).parent().text() != "所有权限"){//单击父模块
	    	//父模块中的子模块的长度
			var num = $parent.next().find("span").length;
			if($(this).is(":checked")){//选中父模块
				for(var a=0;a<num;a++){					
					$parent.next().find("input:eq("+a+")").prop("checked",true);//将所有的子模块设置选中					
					var aIds = $parent.next().find("input:eq("+a+")").attr("val");//获取子模块id
					//子模块id添加到authIds中
					authIds.push(aIds);
					//获取子模块的权限名
					var aName = $parent.next().find("input:eq("+a+")").parent().text();
					//子模块id添加到authName中
					authName.push(aName);
				}
				//弹出框中的  已选择XX个权限  选中时添加个数
			    for(var i=0;i<=authIds.length;i++){
		  		    $("#addAuthes").find("div:eq(0)").find("div:eq(1)").find("span").text(i);
		  		}
			}else{//去除父模块的选中状态	
				for(var b=0;b<num;b++){				
					$parent.next().find("input:eq("+b+")").prop("checked",false);//将所有的子模块选中状态取消					
  					var aIds = $parent.next().find("input:eq("+b+")").attr("val");//获取子模块id
  					for(var i=0;i<authIds.length;i++){
  					    //将该父模块的所有子模块删除
						if(aIds==authIds[i]){
							authIds.splice(i,1);
					  		i=i-1;	
					  	}
  					}				    
					var aName = $parent.next().find("input:eq("+b+")").parent().text();//获取子模块的权限名
					for(var i=0;i<authName.length;i++){
						//将该父模块的所有子模块删除
						if(aName==authName[i]){
							authName.splice(i,1);
					  		i=i-1;	
					  	}
					}					
				}
				//弹出框中的  已选择XX个权限  去除选中时减少个数
	    		for(var i=0;i<=authIds.length;i++){
	    			$("#addAuthes").find("div:eq(0)").find("div:eq(1)").find("span").text(i);		    		
		  		}
			}
	    }
	    //全选
	    var $fourParent = $(this).parent().parent().parent().parent();
	    var allModuleInfo = $(this).parent().text();//所有权限
	    if(allModuleInfo == "所有权限"){
	    	if($(this).is(":checked")){//如果此模块被选中		    		
	    		var length = $("#addAuthes").children("div").length;//获取父模块的个数
	    		//遍历父模块,将所有父模块设置选中,获取每个父模块的子模块个数,遍历子模块,将子模块设置选中,并将子模块的id添加到authIds中
	    		for(var i=1;i<length-1;i++){
	    			var $div = $fourParent.children("div:eq("+i+")").find("div:eq(1)");
	    			//将父模块设置选中
	    			$fourParent.children("div:eq("+i+")").find("div:eq(0)").find("input").prop("checked",true);
	    			//获取该父模块下子模块的长度
	    			var childLeng = $div.find("span").length;
	    			for(var j=0;j<childLeng;j++){
	    				//判断该子模块是否已选中
	    				var boolean = $div.find("span:eq("+j+")").find("input").is(":checked");
	    				//如果此子模块已经选中,则结束本次循环,继续下一次;如果此子模块未被选中,则将其设置选中,并将id填入到authIds中
	    				if(boolean){
	    					continue;
	    				}else if(!boolean){
	    					$div.find("span:eq("+j+")").find("input").prop("checked",true);    					
	    					//获取子模块id
	    					var aIds = $div.find("span:eq("+j+")").find("input").attr("val");
	    					authIds.push(aIds);
	    					//获取子模块权限名
	    					var aName = $div.find("span:eq("+j+")").find("input").parent().text();
	    					authName.push(aName);
	    				}	    				
	    			}
	    		}
	    		//弹出框中的  已选择XX个权限  选中时添加个数
	    		for(var i=0;i<=authIds.length;i++){
	    			$("#addAuthes").find("div:eq(0)").find("div:eq(1)").find("span").text(i);		    		
		  		}
	    	}else{//去除选中	    		
	    		$fourParent.find("input").prop("checked",false);//将所有input的选中状态取消	    		
	    		authIds.splice(0,authIds.length);//将authIds清空	    		
	    		authName.splice(0,authName.length);//将authName清空	    		
	    		$("#addAuthes").find("div:eq(0)").find("div:eq(1)").find("span").text("0");	//弹出框中的  已选择XX个权限  添加0
	    	}
	    }
	});
}