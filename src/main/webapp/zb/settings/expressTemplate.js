var max_page=1;//0.全局变量 最大页数
var now_page=1;//0.全局变量 当前页数
var checkId=null;//0.全局遍历 选中的id

$(function() {
	//1.空
    //2.空
	//3.加载快递面单页面
	loadExpressTemplate(parseInt(now_page), null, 1, null);
	
	//4.监听页码点击事件
	$("#expressTemplate_table").on("click", ".pagelist a", pageClick);
	
	//5.添加模版单击事件 加载快递公司
	$("#expressTemplate_addExpressTemplate").click(loadCarrierInfo);
	
	//6.监听快递公司点击事件  更改选择框
	$("#expressTemplate_carrierInfo").on("click", "li", clickCarrierInfo);
	
	//7.添加模版单击事件 加载面单图片
	$("#expressTemplate_addExpressTemplate").click(loadPrintTemplateImage);
	
	//8.监听面单图片点击事件  更改选择框
	$("#expressTemplate_printTemplateImage").on("click", "li", clickPrintTemplateImage);
	
	//9.监听“下一步”单击事件
	$("#expressTemplate_next").on("click", "a", nextStep);
	
	//10.监听修改单击事件
	$("#expressTemplate_table").on("click", ".expressTemplate_update", expressTemplateUpdate);
	
	//11.监听删除单击事件
	$("#expressTemplate_table").on("click", ".expressTemplate_remove", expressTemplateRemove);
	
	//12.复制并新建 单击事件
	$("#expressTemplate_copyAdd").click(copyAdd);
	
	//13.点击整条模版，选中单选框
	$("#expressTemplate_table").on("click", ".tr_expressTempLate", clickTempLate);
	
	//14.鼠标悬浮，改变背景颜色
	$("#expressTemplate_table").on("mouseover mouseout", ".tr_expressTempLate", mouseTempLateTr);
});

/**
 * 14.鼠标悬浮，改变背景颜色
 * @returns
 * @author 赵滨
 */
function mouseTempLateTr() {
	
	//鼠标悬浮
	if(event.type == "mouseover"){
		$(this).prop("style","background-color: #FCFCFC;cursor:pointer;");
	//鼠标离开 	
	}else if(event.type == "mouseout"){
		$(this).prop("style","background-color: #FFF;");
	}
}

/**
 * 13.点击整条模版，选中单选框
 * @returns
 * @author 赵滨
 */
function clickTempLate() {
	
	//如果选中的不是 删除和修改
	if ($(event.target).prop("tagName") != "A") {
		//选中单选框
		$(this).find("input[name='checkExpressTempLate']").prop("checked", true);
		//绑定id
		checkId = $(this).data("printTemplateId");
	}
}

/**
 * 12.复制并新建 单击事件
 * @returns
 * @author 赵滨
 */
function copyAdd() {

	//移除click	用来防止无限点击
	$('#expressTemplate_copyAdd').unbind("click");
	
	//定义两个筛选条件 用来开启点击事件
	var returnAjax = false;
	var timeOut1000 = false;
	//定时器 一秒
	setTimeout(function(){
		//完成定时
		timeOut1000 = true;
		//如果条件都满足
		if (returnAjax == true) {
			//添加点击事件
			$("#expressTemplate_copyAdd").click(copyAdd);
		}
	},1000)
	
	//获取订单id
	var printTemplateId = $("input[name='checkExpressTempLate']:checked").parent().parent().data("printTemplateId");
	//如果没有选中
	if (printTemplateId == null) {
		showMessage("请选择一个模版用来复制");
	}else {
		//获取参数
		var authorized = getCookie("authorized");
		var ownerId = getCookie("ownerId");
		var operatorId = getCookie("operatorId");
		
		//发送请求，复制
		$.ajax({
			url : path+"/expressTemplate/copyAddExpressTemplate.do",
			type : "post",
			data : {
				"printTemplateId" : printTemplateId,
				"authorized" : authorized,
				"ownerId" : ownerId,
				"operatorId" : operatorId
			},
			dataType : "json",
			success : function(result) {
				//获取对象
				var printTemplate = result.data;
				/*console.log(printTemplate);*/
				//如果复制名称过长
				if (printTemplate == null) {
					//提示
					showMessage("复制的模版名称过长");
				} else {
                    //加载页面
                    loadExpressTemplate(parseInt(now_page), null, 1, null);
				}
				//完成返回
				returnAjax = true;
				//如果条件都满足
				if (timeOut1000 == true) {
					//添加点击事件
					$("#expressTemplate_copyAdd").click(copyAdd);
				}
			},
			error : function() {
				showMessage("复制失败!");
			}
		});
	}
}

/**
 * 11.监听删除单击事件
 * @returns
 * @author 赵滨
 */
function expressTemplateRemove() {
	
	if (confirm("您确定要删除吗?")) { 
		//获取参数
		var authorized = getCookie("authorized");
		var ownerId = getCookie("ownerId");
		var operatorId = getCookie("operatorId");
		
		//获取顶级tr
		var $tr = $(this).parent().parent().parent();
		//获取模版id
		var printTemplateId = $tr.data("printTemplateId");
		
		//发送请求，删除内容
		$.ajax({
			url : path+"/expressTemplate/removeExpressTemplate.do",
			type : "post",
			data : {
				"printTemplateId" : printTemplateId,
				"authorized" : authorized,
				"ownerId" : ownerId,
				"operatorId" : operatorId
			},
			dataType : "json",
			success : function(result) {
				if (result.state == 0) {
					var row = result.data;
					//如果删除的行数，不是0行
					if (row !=0 ) {
						showMessage("删除成功!");
                        //获取页面中的显示条数
                        var rows = $("#expressTemplate_table").find("tr");
                        //如果只有一条	并且  当前页码不是首页
                        if (rows.length == 3 && now_page > 1) {
                            //返回上一页
                            now_page--;
                        }
                        //加载页面
                        loadExpressTemplate(parseInt(now_page), null, 1, null);
					} else {
						showMessage("删除中失败!");
					}
				}
			},
			error : function() {
				showMessage("删除失败!");
			}
		});
	}
}

/**
 * 10.监听修改单击事件
 * @returns
 * @author 赵滨
 */
function expressTemplateUpdate() {
	
	//获取顶级tr
	var $tr = $(this).parent().parent().parent();
	//获取模版id
	var printTemplateId = $tr.data("printTemplateId");
	//绑定cookie
	addCookie("printTemplateId",printTemplateId);
    addCookie("preset", false);
}

/**
 * 9.“下一步”单击事件
 * @returns
 * @author 赵滨
 */
function nextStep() {
	
	//获取快递公司id
	for (var i = 0; i < $("#expressTemplate_carrierInfo li").length; i++) {
		if ($("#expressTemplate_carrierInfo li").eq(i).attr("class") == "clicking_font_li") {
			var carrierInfoId = $("#expressTemplate_carrierInfo li").eq(i).data("carrierInfoId");
		}
	}
	//获取面单图片 和 是否电子面单
	for (var i = 0; i < $("#expressTemplate_printTemplateImage li").length; i++) {
		if ($("#expressTemplate_printTemplateImage img").eq(i).attr("class") == "clicking_img_li") {
			var printTemplateId = $("#expressTemplate_printTemplateImage img").eq(i).parent().data("printTemplateId");
		}
	}
	//判断条件ok
	var ok = true;
	//判断是否选中内容
	if (carrierInfoId == undefined) {
		showMessage("请选择快递公司");
		ok = false;
	}else if (printTemplateId == undefined) {
		showMessage("请选择面单图片");
		ok = false;
	}
	//如果检查正常
	if (ok) {
		//绑定cookie
		addCookie("printTemplateId",printTemplateId);
        addCookie("preset", true);
		//跳转页面
		window.location.href="/addAmendExpressTemplate.do";
	}
}

/**
 * 8.监听面单图片点击事件  更改选择框
 * @returns
 * @author 赵滨
 */
function clickPrintTemplateImage() {

	//获取第0个的class名字
	var $clickClass = $(this).eq(0).attr("class");
	//获取li集合
	var $clickLiList = $("#expressTemplate_printTemplateImage li");
	var $clickImgList = $("#expressTemplate_printTemplateImage img");
	//判断
	if ($clickClass != "picture_head") {
		//清空class，除了第0个
		$clickImgList.not(".picture_head").attr("class", "clicking_li_img");
		//给选中的li加上class
		$(this).children("img").attr("class","clicking_img_li");
	}
	//获取选中内容
	/*for (var i = 0; i < $clickLiList.length; i++) {
		if ($clickImgList.eq(i).attr("class") == "clicking_img_li") {
			console.log($clickImgList.eq(i).attr("src"));
		}
	}*/

}

/**
 * 7.添加模版单击事件 加载面单图片
 * @returns
 * @author 赵滨
 */
function loadPrintTemplateImage() {
	//清空图片内容
	$("#expressTemplate_printTemplateImage li").remove();
	//获取面单类型数组
	var templateType = [7, 8, 9];
	//获取参数
	var authorized = getCookie("authorized");
	var ownerId = getCookie("ownerId");
	var operatorId = getCookie("operatorId");
	//定义是否获取预设模版
	var preset = true;
	//发送请求，获取图片
	$.ajax({
		url : path+"/expressTemplate/loadPrintTemplateImage.do",
		type : "post",
		traditional : true,
		data : {
			"authorized" : authorized,
			"ownerId" : ownerId,
			"operatorId" : operatorId,
			"templateType" : templateType,
			"preset" : preset
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				//获取面单模版图片集合
				var printTemplateImageList = result.data;
				//创建面单模版图片
				createPrintTemplateImage(printTemplateImageList);
			}
		},
		error : function() {
			showMessage("图片加载失败!");
		}
		
	});
}

/**
 * 7.1.创建面单模版图片
 * @param printTemplateImageList 面单模版图片集合
 * @returns
 * @author 赵滨
 */
function createPrintTemplateImage(printTemplateImageList) {
	
	//监听快递公司点击事件
	$("#expressTemplate_carrierInfo").on("click", "li", function(){
		
		//清空页面内容
		$("#expressTemplate_printTemplateImage li").remove();
		
		//获取快递公司id
		var carrierInfoId =  $(this).data("carrierInfoId");
		/*console.log(carrierInfoId);*/
		
		//创建table用于加入tr内容
		var $printTemplateImage = $("#expressTemplate_printTemplateImage");
		
		//创建tr拼接块
		var li = "";
		
		//第一部分 上部
		li = "";
		li += '<li class="picture_head">面单图片</li>';
		
		//将li对象添加到expressTemplate_printTemplateImage身后
		$printTemplateImage.append(li);
		
		//遍历预设模版	第二部分 下部
		for (var i = 0; i < printTemplateImageList.length; i++) {
			
			//根据快递公司id判断是否添加这条内容
			if (carrierInfoId == printTemplateImageList[i].carrierId) {
				li = "";
				li += '<li><img src="';
				li += printTemplateImageList[i].templateImage;
				li += '" alt="" class="clicking_li_img"><br/>';
				li += "<b>["+printTemplateImageList[i].carrierName+"]</b> ";
				li += printTemplateImageList[i].templateName;
				li += '</li>';
				//转换对象
				var $li = $(li);
				//绑定id
				$li.data("printTemplateId",printTemplateImageList[i].id);
				//将li对象添加到expressTemplate_carrierInfo身后
				$printTemplateImage.append($li);
			}
		}
	});

}

/**
 * 6.监听快递公司点击事件  更改选择框
 * @returns
 * @author 赵滨
 */
function clickCarrierInfo() {
		//获取第0个的class名字
		var $clickClass = $(this).eq(0).attr("class")
		//获取li集合
		var $clickLiList = $("#expressTemplate_carrierInfo li");
		//判断
		if ($clickClass != "company_head") {
			//清空class，除了第0个
			$clickLiList.not(".company_head").attr("class", "clicking_li_font");
			//给选中的li加上class
			$(this).attr("class","clicking_font_li");
		}
		//获取选中内容
		/*for (var i = 0; i < $clickLiList.length; i++) {
			if ($clickLiList.eq(i).attr("class") == "clicking_font_li") {
				console.log($clickLiList.eq(i).data("carrierInfoId"));
			}
		}*/
}

/**
 * 5.添加模版单击事件 加载快递公司
 * @returns
 * @author 赵滨
 */
function loadCarrierInfo() {
	
	//获取参数
	var authorized = getCookie("authorized");
	var ownerId = getCookie("ownerId");
	var operatorId = getCookie("operatorId");
	//发送请求，加载快递公司名称
	$.ajax({
		url : path+"/carrierInfo/loadCarrierInfo.do",
		type : "post",
		data : {
			"authorized" : authorized,
			"ownerId" : ownerId,
			"operatorId" : operatorId
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				//获取快递信息集合
				var carrierInfoList = result.data;
				/*console.log(carrierInfoList);*/
				//创建快递公司名称
				createCarrierInfo(carrierInfoList);
			}
		},
		error : function() {
			showMessage("快递公司加载失败!");
		}
		
	});
}

/**
 * 5.1.创建快递公司名称
 * @param carrierInfoList 快递信息集合
 * @returns
 * @author 赵滨
 */
function createCarrierInfo(carrierInfoList) {
	//清空页面内容
	$("#expressTemplate_carrierInfo li").remove();
	
	//创建table用于加入tr内容
	var $carrierInfo = $("#expressTemplate_carrierInfo");
	
	//创建tr拼接块
	var li = "";
	
	//第一部分 上部
	li = "";
	li += '<li class="company_head">快递公司</li>';
	
	//将li对象添加到expressTemplate_carrierInfo身后
	$carrierInfo.append(li);
	
	//第二部分 下部
	for (var i = 0; i < carrierInfoList.length; i++) {
		li = "";
		li += '<li class="clicking_li_font">';
		li += carrierInfoList[i].carrierName;
		li += '</li>';
		//转换对象
		var $li = $(li);
		//将id绑定到li中
		$li.data("carrierInfoId",carrierInfoList[i].id);
		//将li对象添加到expressTemplate_carrierInfo身后
		$carrierInfo.append($li);
	}
}

/**
 * 4.监听页码点击事件
 * @returns
 * @author 赵滨
 */
function pageClick() {
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
	loadExpressTemplate(parseInt(now_page), getCookie("authorized"), getCookie("ownerId"), getCookie("operatorId"));
}

/**
 * 3.加载快递面单页面
 * @param page	需要加载第几页数
 * @param authorized	是否授权
 * @param ownerId	主账户ID
 * @param operatorId 操作人ID
 * @returns
 * @author 赵滨
 */
function loadExpressTemplate(page, authorized, ownerId, operatorId) {
	var templateType = [7, 8, 9];
	
	//发送请求
	$.ajax({
		url : path+"/expressTemplate/loadExpressTemplate.do",
		type : "post",
		traditional : true,
		data : {
			"page" : page,
			"authorized" : authorized,
			"ownerId" : ownerId,
			"operatorId" : operatorId,
			"templateType" : templateType
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				//获取PrintTemplate集合
				var map = result.data;
                //创建PrintTemplate的tr，追加到页面中
                createPrintTemplate(map);
				//获取单选框
				var input = $("input[name='checkExpressTempLate']");
				//遍历单选框
				for (var i = 0; i < input.length; i++) {
					//该框id相同
					if (input.eq(i).parent().parent().data("printTemplateId") == checkId) {
						//选中
						input.eq(i).prop("checked", true);
					}
				}
			}
		},
		error : function() {
			showMessage("模版加载失败!");
		}
	});
}

/**
 * 3.1.创建快递面单内容
 * @param map 快递面单和快递信息的集合
 * @returns
 * @author 赵滨
 */
function createPrintTemplate(map) {
    //最大页数赋值
    max_page = map.maxPage;
    //获取printTemplate集合
    var printTemplateList = map.printTemplateList;
    //获取carrierInfo集合
    var carrierInfoList = map.carrierInfoList;
    //清空页面内容
    $("#expressTemplate_table tr").remove();

    //创建table用于加入tr内容
    var $table = $("#expressTemplate_table");
    //创建tr拼接块
    var tr = "";

    //第一部分 顶部
    tr = "";
    tr += '<tr>';
    tr += '<th width="10%">ID</th>';
    tr += '<th>图片</th>';
    tr += '<th>名称</th>';
    tr += '<th>物流公司</th>';
    tr += '<th>模板类型</th>';
    tr += '<th width="310">操作</th>';
    tr += '</tr>';
    //将tr对象添加到expressTemplate_table身后
    $table.append(tr);
    //第二部分 中部
    for (var i = 0; i < printTemplateList.length; i++) {
        tr = '';
        tr += '<tr style="cursor: pointer;" class="tr_expressTempLate">';
        tr += '<td><input type="radio" name="checkExpressTempLate" style="cursor: pointer;"/>';
        tr += printTemplateList[i].id;
        tr += '</td>';
        tr += '<td width="10%"><img src="';
        tr += printTemplateList[i].templateImage;
        tr += '" alt="" width="70" height="50" /></td>';
        tr += '<td>';
        tr += printTemplateList[i].templateName;
        tr += '</td>';
        tr += '<td>';
        tr += carrierInfoList[i].carrierName;
        tr += '</td>';
        tr += '<td>';
        //如果是普通快递面单
        if (printTemplateList[i].templateType == 7) {
            tr += '普通快递面单';
            //如果是热敏电子面单
        } else if (printTemplateList[i].templateType == 8) {
            tr += '热敏电子面单';
            //如果是菜鸟电子面单
        } else if (printTemplateList[i].templateType == 9) {
            tr += '菜鸟电子面单';
        }
        tr += '</td>';
        tr += '<td><div class="button-group"> ';
        tr += '<a class="button border-main  expressTemplate_update" href="/addAmendExpressTemplate.do">';
        tr += '<span class="icon-edit"></span> 修改</a> ';
        tr += '<a class="button border-red expressTemplate_remove" >';
        /*href="javascript:void(0)"*/
        tr += '<span class="icon-trash-o"></span> 删除</a> </div></td>';
        tr += '</tr>';
        //转换对象，用于存储
        var $tr = $(tr);
        //将printTemplateId绑定到tr对象中
        $tr.data("printTemplateId", printTemplateList[i].id);
        //将tr对象添加到expressTemplate_table身后
        $table.append($tr);
    }

    if (printTemplateList.length > 0) {
        //创建页码
        createPageList();
    }

    function createPageList() {
        //第三部分 底部
        tr = '';
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
                    if (i == now_page) {
                        tr += '<span class="current">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    } else {
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
                    if (i == now_page) {
                        tr += '<span class="current">';
                        tr += i;
                        tr += '</span>';

                        //否则页码为白色背景
                    } else {
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
                if (i == now_page) {
                    tr += '<span class="current">';
                    tr += i;
                    tr += '</span>';

                    //否则页码为白色背景
                } else {
                    tr += '<a>';
                    tr += i;
                    tr += '</a>';
                }
                i++;
            }
        }

        //尾页结束部分
        tr += '<a>下一页</a><a>尾页</a>';
        tr += '<input name="goto" style="border-radius: 3px;' +
            'border: 1px solid #dfdfdf;padding: 5px 5px;width: 3.5em;font: inherit;text-align: center;">';
        tr += '<a href="javascript:void(0)">跳转</a></div>';
        tr += '</td></tr>';
        //将tr对象添加到expressTemplate_table身后
        $table.append(tr);
    }
}

