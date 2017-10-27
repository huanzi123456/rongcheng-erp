var now_page = 1;   //0.全局变量，当前页面
var max_page = 1;   //0.全局变量，最大页面
var key_words = ""; //0.全局变量，关键字
var auto_synchron = "";//0.全局变量，自动同步
$(function () {
    //1.加载页面
    loadInventorySync(now_page, key_words, auto_synchron);

    //2.点击'查询'关键字
    $("#inventorySync_query").click(inventorySyncQuery);

    //3.点击'自动同步'，选择开关
    $("#inventorySync_autoSynchron").change(clickAutoSynchron);

    //4.'关键字'回车，等于点击查询
    $("input[name='keywords']").keydown(keywordsKeydown);

    //5.监听页码点击事件
    $("#inventorySync_table").on("click", ".pagelist a", clickPage);

    //6.点击'同步配置'
    $("#inventorySync_table").on("click", ".alert_btn", alertStock);

    //7.点击'批量更改库存同步设置'
    $("#inventorySync_batchSync").click(inventorySyncBatchSync);

    //8.'同步配置'中点击'确定'
    $("#inventorySync_enterSync").click(inventorySyncEnterSync);

    //9.加载单选全选控制
    loadOptionsControl();
});

/**
 * 9.加载单选全选控制
 * @author 赵滨
 */
function loadOptionsControl() {
    //监听点击'全选'  库存同步
    $("#inventorySync_table").on("click", "input[name='allId']" ,function () {
        checkedAll($("#inventorySync_table"), "allId", "id[]");
    });

    //监听点击'单选'  库存同步
    $("#inventorySync_table").on("click", "input[name='id[]']" ,function () {
        checkedOne($("#inventorySync_table"), "allId", "id[]", $(this).prop("checked"));
    });

    //监听点击'全选'  库存同步配置
    $("#inventorySync_syncTable").on("click", "input[name='allAutoSynchron']" ,function () {
        checkedAll($("#inventorySync_syncTable"), "allAutoSynchron", "autoSynchron");
    });

    //监听点击'单选'  库存同步配置
    $("#inventorySync_syncTable").on("click", "input[name='autoSynchron']" , function () {
        checkedOne($("#inventorySync_syncTable"), "allAutoSynchron", "autoSynchron", $(this).prop("checked"));
    });

    //监听'开启自动同步' 自动同步
    $("#inventorySync_startSync").click(function () {
        //如果勾选
        if ($("#inventorySync_startSync").prop("checked") == true) {
            //遍历同步配置中的勾选框
            $("input[name='autoSynchron']").each(function (index) {
                //如果出现勾选，说明可以开启，跳出
                if ($(this).prop("checked") == true) {
                    return false;
                //如果到最后一个还没有出现勾选，提示用户勾选
                } else if (index == $("input[name='autoSynchron']").length - 1) {
                    $("#inventorySync_startSync").prop("checked", "");
                    showMessage("请先选择同步配置的内容")
                }
            });
        //如果取消勾选
        } else {
            //遍历同步配置中的勾选框
            $("input[name='autoSynchron']").each(function (index) {
                $(this).prop("checked", "");
            });
            $("input[name='allAutoSynchron']").prop("checked", "");
        }
    });

    //监听点击'单选'  自动同步
    $("#inventorySync_syncTable").on("click", "input[name='autoSynchron']" , function () {
        //如果勾选
        if ($(this).prop("checked") == true) {
            $("#inventorySync_startSync").prop("checked", true);
        //取消勾选
        } else {
            //遍历同步配置中的勾选框
            $("input[name='autoSynchron']").each(function (index) {
                //如果出现勾选，说明正常，跳出
                if ($(this).prop("checked") == true) {
                    return false;
                //如果到最后一个还没有出现勾选，提示用户勾选
                } else if (index == $("input[name='autoSynchron']").length - 1) {
                    $("#inventorySync_startSync").prop("checked", "");
                }
            });
        }
    });

    //监听点击'全选'  自动同步
    $("#inventorySync_syncTable").on("click", "input[name='allAutoSynchron']" ,function () {
        //如果勾选
        if ($(this).prop("checked") == true) {
            $("#inventorySync_startSync").prop("checked", true);
        //取消勾选
        } else {
            $("#inventorySync_startSync").prop("checked", "");
        }
    });
}

/**
 * 9.1.监听点击'单选'
 * @param area  选择区域
 * @param all   全选名称
 * @param one   单选名称
 * @param nowChecked   当前选项
 * @author 赵滨
 */
function checkedOne(area, all, one, nowChecked) {
    //获取全选框
    var allChecked = area.find("input[name='"+all+"']").prop("checked");
    //获取所有选择框
    var checkeds = area.find("input[name='"+one+"']");

    //如果没选中 并且 全选选中
    if (nowChecked == false && allChecked == true) {
        //取消全选
        area.find("input[name='"+all+"']").prop("checked", "");

    //否则
    } else {
        //遍历所有选择框
        for (var i = 0; i < checkeds.length; i++) {
            //如果出现没有选中的
            if (checkeds.eq(i).prop("checked") == false) {
                //跳出
                return;
            }
        }
        //结尾处，对于没有产生跳出的内容追加全选框
        area.find("input[name='"+all+"']").prop("checked", "true");
    }
}

/**
 * 9.2.点击'全选'
 * @param area  选择区域
 * @param all   全选名称
 * @param one   单选名称
 * @author 赵滨
 */
function checkedAll(area, all, one) {
    //如果选中
    if (area.find("input[name='"+all+"']").prop("checked") == true) {
        //遍历选择框
        area.find("input[name='"+one+"']").each(function() {
            //选中
            $(this).prop("checked", "true");
        });

    //否则
    } else {
        //遍历选择框
        $("input[name='"+one+"']").each(function() {
            //不选中
            $(this).prop("checked", "");
        });
    }
}

/**
 * 8.'同步配置'中点击'确定'
 * @author 赵滨
 */
function inventorySyncEnterSync() {
    /*
    如果开启自动同步，则根据页面的相关配置进行设置；
    如果关闭自动同步，则关闭所有同步，其他的配置正常；
    同时，一家店铺可能存在多个仓库，需要把所有的仓库拆解成配置表中的数据；
    如果用户选择一家店铺中的一个仓库，则其他仓库的配置不需要修改；
    如果用户选择一家店铺中的所有仓库，则所有仓库配置全部按照当前设置修改。
    */

    //创建 配置信息对象 数组
    // var configuations = [];
    var configuations = new Array();

    //先获取同步配置列表的内容
    var syncTableTrs = $("#inventorySync_syncTable").find("tr").not(":first");
    //遍历每条列表
    for (var i = 0; i < syncTableTrs.length; i++) {
        //获取每条列表
        var syncTableTr = syncTableTrs.eq(i);
        //获取仓库下拉框
        var warehouseSelect = syncTableTrs.eq(i).find(".warehouse_select");

        //查看规则填写的内容是否符合规范
        if(!/^\d+$/g.test(Number(syncTableTr.find("input[name='allocationRatio']").val())) ||
            !/^\d+$/g.test(Number(syncTableTr.find("input[name='remnantStock']").val())) ||
            syncTableTr.find("input[name='allocationRatio']").val().length > 5 ||
            syncTableTr.find("input[name='remnantStock']").val().length > 5) {
            showMessage("请在规则中填写正整数(0-9999)");
            return;
        }

        //如果选择的是全部仓库，对全部的仓库进行配置修改
        if (warehouseSelect.find("option:selected").val() == 0) {
            //遍历仓库下拉框
            for (var j = 0; j < warehouseSelect.find("option").not(":first").length; j++) {
                var configurationId = warehouseSelect.find("option").not(":first").eq(j).val();
                //创建配置信息
                configuations.push(newConfiguation(configurationId, syncTableTr));
            }

        //如果选择单条仓库，只修改当前这条
        } else if (warehouseSelect.find("option:selected").val() > 0) {
            var configurationId = warehouseSelect.find("option:selected").val();
            //创建配置信息
            configuations.push(newConfiguation(configurationId, syncTableTr));
        }
    }
    //发送请求，获取同步配置
    $.ajax({
        url : "/inventorySync/updateInventorySyncConfiguations.do",
        type : "post",
        data : {
            //转成json字符串
            "configuations": JSON.stringify(configuations)  //biginteger
        },
        dataType : "json",
        success : function(result) {
            //接收参数
            var row = result.data
            if (row > 0) {
                showMessage("库存同步配置成功");
                //隐藏 警戒库 弹出框
                $(".inventory_sync_box").css("display", "none");
                //加载页面
                loadInventorySync(now_page, key_words, auto_synchron);
            } else {
                showMessage("库存同步配置为零");
            }
        },
        error : function() {
            showMessage("库存同步配置失败");
        }
    });
}

/**
 * 8.1.创建配置信息
 * @param configurationId 配置ID
 * @param syncTableTr 每条配置tr
 * @author 赵滨
 */
function newConfiguation(configurationId, syncTableTr) {
    //创建返回对象map
    var map = {};

    //加入 ID
    map["id"] = Number(configurationId);
    //加入 库存自动同步状况（开关键）
    map["autoSynchron"] = Number(syncTableTr.find("input[name='autoSynchron']").prop("checked"));
    //加入 自动上架状况（开关键）
    map["autoOnsale"] = Number(syncTableTr.find("input[name='autoOnsale']").prop("checked"));
    //加入 例外情况（库存低于警戒值不同步）
    map["synchronException"] = Number(syncTableTr.find("input[name='synchronException']").prop("checked"));
    //加入 库存可用量
    map["availableStock"] = Number(syncTableTr.find(".availableStock option:selected").val());
    //加入 库存分配比例
    map["allocationRatio"] = Number(syncTableTr.find("input[name='allocationRatio']").val());
    //加入 库存零头
    map["remnantStock"] = Number(syncTableTr.find("input[name='remnantStock']").val());

    return map;
}


/**
 * 7.点击'批量更改库存同步设置'
 * @author 赵滨
 */
function inventorySyncBatchSync() {
    //清空 同步配置
    $("#inventorySync_syncTable").children().remove();

    //获取商品id数组
    var itemIds = [];
    var find = $("#inventorySync_table").find("input[name='id[]']:checked");
    //关闭开关
    $("#inventorySync_startSync").prop("checked", false);
    for (var i = 0; i < find.length; i++) {
        //加入数组
        itemIds.push(find.eq(i).val());
        //如果自动同步开关为开，就在弹出框内选中
        var  on_off= find.eq(i).parent().parent().find("td:last").prev().html();
        if (on_off == "开") {
            $("#inventorySync_startSync").prop("checked", true);
        }
    }
    //提示
    if (itemIds.length <= 0) {
        //隐藏 警戒库 弹出框
        $(".inventory_sync_box").css("display", "none");
        showMessage("请选择需要库存同步的商品");
        return;
    }

    //发送请求，获取同步配置
    $.ajax({
        url : "/inventorySync/listInventorySyncByItemIds.do",
        type : "post",
        traditional : true,
        data : {
            "itemIds" : itemIds //biginteger
        },
        dataType : "json",
        success : function(result) {
            //接收集合
            var list = result.data

            //创建库存同步配置页面
            createInventorySyncBatchSync(list);
        },
        error : function() {
            showMessage("加载库存同步配置失败");
        }
    });
}

/**
 * 7.1.创建库存同步配置页面
 * @author 赵滨
 */
function createInventorySyncBatchSync(list) {
    //插入标题
    var tr = '<tr> ' +
        '<th width="9%" style="padding-top: 10px;"> ' +
        '<input type="checkbox" name="allAutoSynchron">全选 ' +
        '</th> ' +
        '<th width="13%" style="padding-top: 10px;">店铺</th> ' +
        '<th width="13%">同步库存至该店铺的仓库</th> ' +
        '<th width="45%" style="padding-top: 10px;">规则</th> ' +
        '<th width="10%">有库存时自动上架</th> ' +
        '<th width="10%">库存低于警戒值不同步</th> ' +
        '</tr>';
    $("#inventorySync_syncTable").append(tr);

    //遍历每条商品记录
    for (var listIndex = 0; listIndex < list.length; listIndex++) {
        var map = list[listIndex];

        //获取 系统商品对应关系关联表 同步信息
        var platformErpLinkShopWarehouseInfoList = map.platformErpLinkShopWarehouseInfoList;

        //获取同步配置列表，在循环外面获取，可以防止获取新插入的数据
        var syncTableTrs = $("#inventorySync_syncTable").find("tr");

        //如果没有同步配置
        if (platformErpLinkShopWarehouseInfoList.length == 0) {
            showMessage("该条商品不能同步设置，请先关联线上商品对应关系");
            //隐藏 警戒库 弹出框
            $(".inventory_sync_box").css("display", "none");
            return;
        }
        //插入内容
        for (var i = 0; i < platformErpLinkShopWarehouseInfoList.length; i++) {
            //这条商品信息 的 单条关联信息
            var platformErpLinkShopWarehouseInfo = platformErpLinkShopWarehouseInfoList[i];

            //如果是最后一条记录，那么查看'全选'是否需要选中
            if (i == platformErpLinkShopWarehouseInfoList.length - 1) {
                checkedOne($("#inventorySync_syncTable"), "allAutoSynchron",
                    "autoSynchron", platformErpLinkShopWarehouseInfo.autoSynchron);
            }

            //遍历已经显示的配置列表，如果找到相同店铺的列表，合并到仓库下拉框中
            for (var trIndex = 0; trIndex < syncTableTrs.length; trIndex++) {
                var syncTableTr = syncTableTrs.eq(trIndex);

                //如果 找到相同的店铺
                if (syncTableTr.find("p:first").html() == platformErpLinkShopWarehouseInfo.shopName) {
                    //加入仓库下拉框
                    syncTableTr.find(".warehouse_select").append(
                        '<option value="'+platformErpLinkShopWarehouseInfo.id+'">'+    //记录ID
                        platformErpLinkShopWarehouseInfo.warehouseName+'</option>'  //仓库名称
                    );
                    break;
                 //如果是最后一条记录，那么直接插入到页面列表中
                } else if (trIndex == syncTableTrs.length - 1) {
                    var tr = '' +
                        '<tr>' +
                        '  <td style="padding-top: 15px;">' +
                        '    <input type="checkbox" name="autoSynchron"'+
                               (platformErpLinkShopWarehouseInfo.autoSynchron == 1?"checked":"")+'>' +
                        '  </td>' +

                        '  <td>' +
                        '    <p>'+platformErpLinkShopWarehouseInfo.shopName+'</p>' +    //店铺名称
                        '  </td>' +

                        '  <td style="padding-top: 2px;">' +
                        '    <select class="input warehouse_select" ' +
                        '               style="width:100px; line-height:17px; display:inline-block">' +
                        '      <option value="0">全部仓库</option>'+     //仓库ID，仓库名称
                        '      <option value="'+platformErpLinkShopWarehouseInfo.id+'">'+    //仓库ID
                                    platformErpLinkShopWarehouseInfo.warehouseName+'' +     //仓库名称
                        '      </option>'+
                        '    </select>' +
                        '  </td>' +

                        '  <td style="padding-top: 2px;">' +
                        '    <select class="input availableStock" ' +
                        '               style="width:250px; line-height:17px; display:inline-block">' +
                        '      <option value="0"'+(platformErpLinkShopWarehouseInfo.availableStock == 0?"selected":"")+
                        '               >可用量(订单量买家付款后扣减)</option>' +
                        '      <option value="1"'+(platformErpLinkShopWarehouseInfo.availableStock == 1?"selected":"")+
                        '               >可用量(订单量审核后扣减)</option>' +
                        '      <option value="2"'+(platformErpLinkShopWarehouseInfo.availableStock == 2?"selected":"")+
                        '               >总量</option>' +
                        '    </select>' +
                        '    <span>X</span>' +
                        '    <input type="text" class="input" name="allocationRatio" ' +
                        '               value="'+platformErpLinkShopWarehouseInfo.allocationRatio+'">' +//库存分配比例
                        '    <span>%+</span>' +
                        '    <input type="text" class="input" name="remnantStock" ' +
                        '               value="'+platformErpLinkShopWarehouseInfo.remnantStock+'">' +   //库存零头
                        '  </td>' +

                        '  <td style="padding-top: 15px;"><input type="checkbox" name="autoOnsale"'+
                                (platformErpLinkShopWarehouseInfo.autoOnsale == 1?"checked":"")+'>' +  //自动上架状况
                        '  </td>' +

                        '  <td style="padding-top: 15px;"><input type="checkbox" name="synchronException"'+
                                (platformErpLinkShopWarehouseInfo.synchronException == 1?"checked":"")+'>' + //例外情况
                        '  </td>' +
                        '</tr>';
                    //插入内容
                    $("#inventorySync_syncTable").append(tr);
                }
            }
        }
    }
    //显示 警戒库 弹出框
    $(".inventory_sync_box").css("display", "block");
}

/**
 * 6.点击'同步配置'，显示弹出
 * @author 赵滨
 */
function alertStock() {
    //清空 同步配置
    $("#inventorySync_syncTable").children().remove();



    //获取商品id
    var itemIds = [$(this).parent().parent().find("input[name='id[]']").val()];

    //获取自动同步开关
    var autoSynchrons = $(this).parent().prev().html();

    //操作 开启自动同步 开关
    if (autoSynchrons == "开") {
        $("#inventorySync_startSync").prop("checked", true);
    } else if (autoSynchrons == "关") {
        $("#inventorySync_startSync").prop("checked", false);
    }

    //发送请求，获取同步配置
    $.ajax({
        url : "/inventorySync/listInventorySyncByItemIds.do",
        type : "post",
        traditional : true,
        data : {
            "itemIds" : itemIds //biginteger
        },
        dataType : "json",
        success : function(result) {
            //接收集合
            var list = result.data

            //创建库存同步配置页面
            createInventorySyncBatchSync(list);
        },
        error : function() {
            showMessage("加载库存同步配置失败");
        }
    });
}

/**
 * 5.监听页码点击事件
 * @author 赵滨
 */
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

    //加载页面
    loadInventorySync(now_page, key_words, auto_synchron);
}

/**
 * 4.'关键字'回车，等于点击查询
 * @author 赵滨
 */
function keywordsKeydown(e) {
    //获取event对象
    var ev = document.all ? window.event : e;

    //如果是回车按键
    if(ev.keyCode==13) {

        //光标移除
        $("input[name='keywords']").blur();

        //点击'查询'关键字
        $("#inventorySync_query").click();
    }
}

/**
 * 3.点击'自动同步'，选择开关
 * @author 赵滨
 */
function clickAutoSynchron() {
    //获取 并 赋值
    auto_synchron = $("#inventorySync_autoSynchron").find("option:selected").val();

    //页码初始化
    now_page = 1;

    //关键字清空
    key_words = "";

    //关键字清空
    $("input[name='keywords']").val("");

    //加载页面
    loadInventorySync(now_page, key_words, auto_synchron);
}

/**
 * 2.点击'查询'关键字
 * @returns
 * @author 赵滨
 */
function inventorySyncQuery() {
    //获取关键字
    key_words = $("input[name='keywords']").val().trim();

    //页码初始化
    now_page = 1;

    //加载页面
    loadInventorySync(now_page, key_words, auto_synchron);
}

/**
 * 1.加载页面
 * @author 赵滨
 */
function loadInventorySync(nowPage, keywords, autoSynchron) {
    //发送请求，获取页面内容
    $.ajax({
        url : "/inventorySync/loadInventorySync.do",
        type : "post",
        //traditional : true,s
        data : {
            "autoSynchron" : autoSynchron, //integer
            "nowPage" : nowPage, // int
            "keywords" : keywords //String
        },
        dataType : "json",
        success : function(result) {
            //接受栏目集合
            var map = result.data

            //创建页面
            createInventorySync(map);
        },
        error : function() {
            showMessage("加载库存同步失败");
        }
    });
}

/**
 * 1.1.创建页面
 * @param map
 * @author 赵滨
 */
function createInventorySync(map) {

    //加载最大页数
    max_page = map.maxPage;

    //清空页面
    $("#inventorySync_table").children().remove();

    //第一部分
    var tr = '';

    tr += '<tr>';
    tr += '<th width="60"><input type="checkbox" name="allId" value="1" class="check_coding" />ID</th>';
    tr += '<th>商品编码</th>';
    tr += '<th width="100">商品名称</th>';
    tr += '<th>商品条码</th>';
    tr += '<th width="100">商品规格</th>';
    tr += '<th>总库存量</th>';
    tr += '<th>总订单占用量</th>';
    tr += '<th>总可用量</th>';
    tr += '<th>自动同步</th>';
    tr += '<th>操作</th>';
    tr += '</tr> ';

    //追加
    $("#inventorySync_table").append(tr);

    //第二部分
    tr = '';

    //获取 自动同步
    var listItemCommonSync = map.listItemCommonSync;

    //遍历集合
    for (var i = 0; i < listItemCommonSync.length; i++) {

        tr = '';

        tr += '<tr>';
        tr += '<td><input type="checkbox" name="id[]" value="'+listItemCommonSync[i].id+'" class="check_coding" />';
        tr += listItemCommonSync[i].id;     //ID
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].erpItemNum;	//编码
        tr += '</td>';
        tr += '<td>';
        tr += '<p>';
        tr += listItemCommonSync[i].name;	//名称
        tr += '</p>';
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].barCode;	//条码
        tr += '</td>';
        tr += '<td>';
        tr += '<p>';
        tr += listItemCommonSync[i].color+' '+listItemCommonSync[i].size;	//规格
        tr += '</p>';
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].sumQuantity;	//总量
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].useQuantity;	//占用量
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].sumQuantity - listItemCommonSync[i].useQuantity;	//可用量
        tr += '</td>';
        tr += '<td>';
        //自动同步
        if (listItemCommonSync[i].autoSynchron == 1) {
            tr += "开";
        } else if (listItemCommonSync[i].autoSynchron == 0) {
            tr += "关";
        }
        tr += '</td>';
        tr += '<td>';
        tr += '<a href="javascript:;" class="button border-main a_btn_sync alert_btn"> 同步配置</a>';
        tr += '</td>';
        tr += '</tr>';

        //转换对象
        $tr = $(tr);

        //绑定对象
        $tr.data("listItemCommonSync", listItemCommonSync[i]);

        //追加
        $("#inventorySync_table").append($tr);
    }

    //第三部分
    tr = '';
    //开始部分
    tr += '<tr><td colspan="10">';
    tr += '<div class="pagelist"><a href="javascript:void(0)">首页</a><a href="javascript:void(0)">上一页</a> ';
    //中间部分
    if (max_page > 5) {
        //如果是页码前两个
        if (now_page <= 3) {
            //循环前三页码
            for (var i = 1; i < 4; i++) {

                //如果选中当前页码，则变成蓝色背景
                if(i==now_page){
                    tr += '<span class="current" style="cursor:default">';
                    tr += i;
                    tr += '</span>';

                    //否则页码为白色背景
                }else{
                    tr += '<a href="javascript:void(0)">';
                    tr += i;
                    tr += '</a>';
                }
            }
            //写出最后两个
            tr += '<a href="javascript:void(0)">';
            tr += 4;
            tr += '</a>';
            tr += '<a href="javascript:void(0)">';
            tr += 5;
            tr += '</a>……';

            //如果是页码最中间
        } else if (now_page >= 4 && now_page <= max_page - 3) {
            //页码前两个
            tr += '……<a href="javascript:void(0)">';
            tr += now_page - 2;
            tr += '</a>';
            tr += '<a href="javascript:void(0)">';
            tr += now_page - 1;
            tr += '</a>';

            //页码中间选中的
            tr += '<span class="current" style="cursor:default">';
            tr += now_page;
            tr += '</span>';

            //页码后两个
            tr += '<a href="javascript:void(0)">';
            tr += now_page + 1;
            tr += '</a>';
            tr += '<a href="javascript:void(0)">';
            tr += now_page + 2;
            tr += '</a>……';
            //如果是页码后两个
        } else if (now_page > max_page - 3) {
            //页码前两个
            tr += '……<a href="javascript:void(0)">';
            tr += max_page - 4;
            tr += '</a>';
            tr += '<a href="javascript:void(0)">';
            tr += max_page - 3;
            tr += '</a>';

            //循环后三页
            for (var i = max_page - 2; i <= max_page; i++) {
                //如果选中当前页码，则变成蓝色背景
                if(i==now_page){
                    tr += '<span class="current" style="cursor:default">';
                    tr += i;
                    tr += '</span>';

                    //否则页码为白色背景
                }else{
                    tr += '<a href="javascript:void(0)">';
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
                tr += '<span class="current" href="javascript:void(0)" style="cursor:default">';
                tr += i;
                tr += '</span>';

                //否则页码为白色背景
            }else{
                tr += '<a href="javascript:void(0)">';
                tr += i;
                tr += '</a>';
            }
            i++;
        }
    }

    //结束部分
    tr += '<a href="javascript:void(0)">下一页</a><a href="javascript:void(0)">尾页</a></div>';
    tr += '</td></tr>';

    //加入页面
    $("#inventorySync_table").append(tr);
}