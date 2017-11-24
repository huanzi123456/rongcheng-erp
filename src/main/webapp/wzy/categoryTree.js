/**
 * 
 */
//初始化分类模块
var radio="";

$(function(){
	//分类查询（分类保持不变）
	doFindAllCategory();
	
})

function doFindAllCategory(){
	
	//清空原始数据
	$(".left_fl").empty();
	radio = "";
	
	$.ajax({
		url : "/findAllCategory.do",
		type : "post",
		//traditional : true,
		dataType : "json",
		success : function(result) {
			//创建页面显示的信息
			doSetCategoryBox(0, result.data.list);
			var div = getBaseCategory();
			var li = $("<li></li>");
			li.append(radio);
			$(".left_fl").append(div);
			$(".left_fl").append(li);
			
		},
		error : function() {
			showMessage("系统分类列表失败");
		}
	});
}

//网页递归遍历2
function doSetCategoryBox(id, list){
    //根据菜单主键id生成菜单列表html
    //id：菜单主键id
    //arry：菜单数组信息
    var childArry = GetParentArry(id, list);
       if (childArry.length > 0) {
    	   radio += '<ol>';
        for (var i in childArry) {
        	radio += '<li>' +
        	'<a href="javascript:;" value="'+childArry[i].id+'">'+childArry[i].categoryName+'</a>';
        	doSetCategoryBox(childArry[i].id, list);
          	radio += '</li>';
        }
        radio += '</ol>';
      }
}

//网页递归遍历1
function GetParentArry(id, list) {
    var newArry = new Array();
    for (var i in list) {
      if (list[i].parentId == id)
        newArry.push(list[i]);
    }
    return newArry;
  }

//获取常规分类
function getBaseCategory(){
	var div = '<li>'+
    		  		'<a href="javascript:;" value="">全部商品</a>'+
    		  '</li>'+
    		  '<li>'+
    		  		'<a href="javascript:;" value="0">未分类商品</a>'+
    		  '</li>';
	return div;
}


