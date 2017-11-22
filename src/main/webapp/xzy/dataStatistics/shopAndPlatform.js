/**
 * 数据统计页面的添加店铺信息和平台信息 
 * @returns
 */
function addInfos(){
	$.ajax({
		url:"/find/shopAndPlatformInfo.do",	    		                                      
		type:"post",
		data:{},
		dateType:"json",
		success:function(result){
			if(result.status == 0){
				currentOwnerId = result.msg; //owner_id				  
				var shop = result.data;//获取店铺信息(id,name)
				var platform = result.dataes;//获取返回的平台信息(id,name)
				//动态增加店铺下拉选
				var all_option = '<option value="-1">全部</option>';
				for(var i=0;i<shop.length;i++){
					all_option += '<option value='+shop[i].id+'>'+shop[i].name+'</option>';					
				}
				var $shop = $(all_option);					
				$("#dynamicShop").append($shop);
				//动态增加平台信息的下拉选
				var all_options = '<option value="-1">全部</option>';
				for(var i=0;i<platform.length;i++){
					all_options += '<option value='+platform[i].id+'>'+platform[i].name+'</option>';					
				}
				var $shops = $(all_options);
				$("#dynamicPlatform").append($shops);
			}
		},
		error:function(){}
	});
}