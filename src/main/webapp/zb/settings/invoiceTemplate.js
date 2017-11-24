var max_page=1;//0.全局变量 最大页数
var now_page=1;//0.全局变量 当前页数
var checkId=null;//0.全局遍历 选中的id

$(function() {
	//1.加载快递面单页面
	loadInvoiceTemplate(1);
	
	//2.监听页码点击事件
	$("#invoiceTemplate_table").on("click", ".pagelist a", pageClick);
	
	//3.添加模版单击事件 加载面单图片
    $("#invoiceTemplate_templateType").on("click", "li", loadPrintTemplateImage);
	
	//4.监听“下一步”单击事件
	$("#invoiceTemplate_next").on("click", "a", nextStep);
	
	//5.监听修改单击事件
	$("#invoiceTemplate_table").on("click", ".invoiceTemplate_update", invoiceTemplateUpdate);
	
	//6.监听删除单击事件
	$("#invoiceTemplate_table").on("click", ".invoiceTemplate_remove", invoiceTemplateRemove);
	
	//7.复制并新建 单击事件
	$("#invoiceTemplate_copyAdd").click(copyAdd);
	
	//8.点击整条模版，选中单选框
	$("#invoiceTemplate_table").on("click", ".tr_invoiceTempLate", clickTempLate);
	
	//9.鼠标悬浮，改变背景颜色
	$("#invoiceTemplate_table").on("mouseover mouseout", ".tr_invoiceTempLate", mouseTempLateTr);
});

/**
 * 9.鼠标悬浮，改变背景颜色
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
 * 8.点击整条模版，选中单选框
 * @returns
 * @author 赵滨
 */
function clickTempLate() {
	//如果选中的不是 删除和修改
	if ($(event.target).prop("tagName") != "A") {
		//选中单选框
		$(this).find("input[name='checkInvoiceTempLate']").prop("checked", true);
		//绑定id
		checkId = $(this).data("printTemplateId");
	}
}

/**
 * 7.复制并新建 单击事件
 * @returns
 * @author 赵滨
 */
function copyAdd() {
	//获取订单id
	var printTemplateId = $("input[name='checkInvoiceTempLate']:checked").parent().parent().data("printTemplateId");
	//如果没有选中
	if (printTemplateId == null) {
		showMessage("请选择一个模版用来复制");
	}else {
		//发送请求，复制
		$.ajax({
			url : "/invoiceTemplate/copyAddInvoiceTemplate.do",
			type : "post",
			data : {
				"printTemplateId" : printTemplateId
			},
			dataType : "json",
			success : function(result) {
                if (result.state == 1) {
                    showMessage(result.message);
                    return;
                } else {
                    //获取对象
                    var printTemplate = result.data;
                    //加载页面
                    loadInvoiceTemplate(parseInt(now_page));
                    showMessage("复制成功")
                }
			},
			error : function() {
				showMessage("复制失败");
			}
		});
	}
}

/**
 * 6.监听删除单击事件
 * @returns
 * @author 赵滨
 */
function invoiceTemplateRemove() {
	if (confirm("您确定要删除吗?")) {
		//获取顶级tr
		var $tr = $(this).parent().parent().parent();
		//获取模版id
		var printTemplateId = $tr.data("printTemplateId");
        $.post().done();
		//发送请求，删除内容
		$.ajax({
			url : "/invoiceTemplate/removeInvoiceTemplate.do",
			type : "post",
			data : {
				"printTemplateId" : printTemplateId
			},
			dataType : "json",
			success : function(result) {
				if (result.state == 0) {
					var row = result.data;
					//如果删除的行数，不是0行
					if (row !=0 ) {
						showMessage("删除成功!");
                        //获取页面中的显示条数
                        var rows = $("#invoiceTemplate_table").find("tr");
                        //如果只有一条	并且  当前页码不是首页
                        if (rows.length == 3 && now_page > 1) {
                            //返回上一页
                            now_page--;
                        }
						//加载页面
                        loadInvoiceTemplate(parseInt(now_page));
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
 * 5.监听修改单击事件
 * @returns
 * @author 赵滨
 */
function invoiceTemplateUpdate() {
	//获取顶级tr
	var $tr = $(this).parent().parent().parent();
	//获取模版id
	var printTemplateId = $tr.data("printTemplateId");
	//绑定cookie
	addCookie("printTemplateId",printTemplateId);
    addCookie("preset", false);
}

/**
 * 4.“下一步”单击事件
 * @returns
 * @author 赵滨
 */
function nextStep() {
	//获取面单图片 和 是否电子面单
	for (var i = 0; i < $("#invoiceTemplate_printTemplateImage img").length; i++) {
		if ($("#invoiceTemplate_printTemplateImage img").eq(i).prev().prop("checked") == true) {
			var printTemplateId = $("#invoiceTemplate_printTemplateImage img").eq(i).data("printTemplateId");
		}
	}
	//判断条件ok
	var ok = true;
	//判断是否选中内容
	if ($("input[name='type']:checked").val()==undefined) {
		showMessage("请选择模版类型");
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
		window.location.href="/addAmendInvoiceTemplate.do";
	}
}

/**
 * 3.添加模版单击事件 加载面单图片
 * @returns
 * @author 赵滨
 */
function loadPrintTemplateImage() {
    //不选择提示内容
    if ($(this).prop("class") == "company_head") {
        return;
    }
	//清空子类架构
	$("#invoiceTemplate_printTemplateImage").children().remove();
    //定义是否获取预设模版
    var preset = true;
    //获取面单类型数组
    var templateType = [];
    //定义面单类型名称
    var templateTypeName = "";

    //选中的内容
    var clickContext = $(this).children().val();
    if (clickContext == "发货单") {
        templateType = [1, 2];
        templateTypeName = "发货单";
    }else if (clickContext == "出库单") {
        templateType = [3, 4];
        templateTypeName = "出库单";
    } else if (clickContext == "入库单") {
        templateType = [5, 6];
        templateTypeName = "入库单";
    }
    //发送请求，获取图片
	$.ajax({
		url : "/invoiceTemplate/loadPrintTemplateImage.do",
		type : "post",
		traditional : true,
		data : {
			"carrierInfoId" : 0,
			"templateType" : templateType,
			"preset" : preset
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				//获取面单模版图片集合
				var printTemplateImageList = result.data;
				//创建面单模版图片
				createPrintTemplateImage(printTemplateImageList, templateTypeName);
			}
		},
		error : function() {
			showMessage("图片加载失败!");
		}
	});
}

/**
 * 3.1.创建面单模版图片
 * @param printTemplateImageList 面单模版图片集合
 * @returns
 * @author 赵滨
 */
function createPrintTemplateImage(printTemplateImageList, templateTypeName) {
    //创建
    var li = '';
    li += '<li style="width:10%;text-align: right;"> 模板图片 :</li>';
    li += '<li class="img_checked">';
    li += '<input type="radio" name="picture"><img src="" alt=""><br>';
    li += '<span></span>';
    li += '</li>';
    li += '<li class="img_checked">';
    li += '<input type="radio" name="picture"><img src="" alt=""><br>';
    li += '<span></span>';
    li += '</li>';
    //添加
    $("#invoiceTemplate_printTemplateImage").append(li);

    //取消之前选中内容
    $('input:radio[name="picture"]').prop("checked",false);
    //遍历预设模版
    for (var i = 0; i < printTemplateImageList.length; i++) {
        //获取id
        var printTemplateId = printTemplateImageList[i].id;
        //如果是普通发货单
        if (printTemplateId == 1 || printTemplateId == 3 || printTemplateId == 5) {
            //替换图片
            $("#invoiceTemplate_printTemplateImage img").
            eq(0).prop("src",printTemplateImageList[i].templateImage);
            //绑定id
            $("#invoiceTemplate_printTemplateImage img").eq(0).data("printTemplateId", printTemplateId);
            //替换说明
            $("#invoiceTemplate_printTemplateImage span").eq(0).html("<b>[普通]</b>"+ templateTypeName +"");
            //如果是热敏发货单
        } else if (printTemplateId == 2 || printTemplateId == 4 || printTemplateId == 6) {
            //替换图片
            $("#invoiceTemplate_printTemplateImage img").
            eq(1).prop("src",printTemplateImageList[i].templateImage);
            //绑定id
            $("#invoiceTemplate_printTemplateImage img").eq(1).data("printTemplateId", printTemplateId);
            //替换说明
            $("#invoiceTemplate_printTemplateImage span").eq(1).html("<b>[热敏]</b>" + templateTypeName +"");
        }
    }

    //选择图片，就选择radio
	$("#invoiceTemplate_printTemplateImage").on("click", "img", function(){
		$(this).prev().prop("checked", true);
	});
}

/**
 * 2.监听页码点击事件
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
	loadInvoiceTemplate(parseInt(now_page));

}

/**
 * 1.加载快递面单页面
 * @param page	需要加载第几页数
 * @returns
 * @author 赵滨
 */
function loadInvoiceTemplate(page) {
	var templateType = [1, 2, 3, 4, 5, 6];
	
	//发送请求
	$.ajax({
		url : "/invoiceTemplate/loadInvoiceTemplate.do",
		type : "post",
		traditional : true,
		async: false,
		data : {
			"page" : page,
			"templateType" : templateType
		},
		dataType : "json",
		success : function(result) {
			if (result.state == 0) {
				//获取PrintTemplate集合
				var map = result.data;
				//创建PrintTemplate的tr，追加到页面中
				createInvoiceTemplate(map);
				//获取单选框
				var input = $("input[name='checkInvoiceTempLate']");
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
 * 1.1.创建快递面单内容
 * @param map 快递面单和快递信息的集合
 * @returns
 * @author 赵滨
 */
function createInvoiceTemplate(map) {
    //获取最大页码
    max_page = map.maxPage;
	//获取printTemplate集合
	var printTemplateList = map.printTemplateList;
	//获取carrierInfo集合
	var carrierInfoList = map.carrierInfoList;
	//清空页面内容
	$("#invoiceTemplate_table tr").remove();
	
	//创建table用于加入tr内容
	var $table = $("#invoiceTemplate_table");
	//创建tr拼接块
	var tr = "";
	//第一部分 顶部
	tr = "";
	tr += '<tr style="hover: pointer;">';
	tr += '<th width="10%">ID</th>';
	tr += '<th>名称</th>';
	tr += '<th>模板类型</th>';
	tr += '<th width="310">操作</th>';
	tr += '</tr>';
	//将tr对象添加到expressTemplate_table身后
	$table.append(tr);

	//第二部分 中部
	for (var i = 0; i < printTemplateList.length; i++) {
		tr = '';
		tr += '<tr style="cursor: pointer;" class="tr_invoiceTempLate">';
		tr += '<td><input type="radio" name="checkInvoiceTempLate" style="cursor: pointer;"/>';
		tr += printTemplateList[i].id;
		tr += '</td>';
		tr += '<td>';
		tr += printTemplateList[i].templateName;
		tr += '</td>';
		tr += '<td>';
		if (printTemplateList[i].templateType == 1) {
			tr += '普通发货单';
		} else if (printTemplateList[i].templateType == 2) {
			tr += '热敏发货单';
		} else if (printTemplateList[i].templateType == 3) {
			tr += '普通出库单';
		} else if (printTemplateList[i].templateType == 4) {
			tr += '热敏出库单';
		} else if (printTemplateList[i].templateType == 5) {
			tr += '普通入库单';
		} else if (printTemplateList[i].templateType == 3) {
			tr += '热敏入库单';
		}
		tr += '</td>';
		tr += '<td><div class="button-group"> ';
		tr += '<a class="button border-main  invoiceTemplate_update" href="/addAmendInvoiceTemplate.do">';
		tr += '<span class="icon-edit"></span> 修改</a> ';
		tr += '<a class="button border-red invoiceTemplate_remove" >';/*href="javascript:void(0)"*/
		tr += '<span class="icon-trash-o"></span> 删除</a> </div></td>';
		tr += '</tr>';
		//转换对象，用于存储
		var $tr=$(tr);
		//将printTemplateId绑定到tr对象中
		$tr.data("printTemplateId",printTemplateList[i].id);
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
