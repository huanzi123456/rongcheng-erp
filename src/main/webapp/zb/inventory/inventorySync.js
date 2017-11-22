var now_page = 1;   //0.全局变量，当前页面
var max_page = 1;   //0.全局变量，最大页面
var key_words = ""; //0.全局变量，关键字
var auto_synchron = "";//0.全局变量，自动同步
var item_ids = [];//商品ID

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

    return;
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
    var configuations = new Array();
    //获取自动同步
    var autoSynchron =  $("#inventorySync_startSync").prop("checked");
    //先获取同步配置列表的内容
    var syncTableTrs = $("#inventorySync_syncTable").find("tr").not(":first");
    //是否选中单选内容
    var notCheckedSingle = true;

    //遍历每条列表
    for (var i = 0; i < syncTableTrs.length; i++) {
        //获取每条列表
        var syncTableTr = syncTableTrs.eq(i);
        //获取同步选择框
        var synchron = syncTableTr.find("input[name='autoSynchron']").prop("checked");
        //如果选中单选，修改
        if (synchron) {
            notCheckedSingle = false;
        }
        //查看规则填写的内容是否符合规范
        if (!/^\d+$/g.test(Number(syncTableTr.find("input[name='allocationRatio']").val())) ||
            !/^\d+$/g.test(Number(syncTableTr.find("input[name='remnantStock']").val())) ||
            syncTableTr.find("input[name='allocationRatio']").val().length > 5 ||
            syncTableTr.find("input[name='remnantStock']").val().length > 5) {
            showMessage("请在规则中填写正整数(0-9999)");
            return;
        }
        configuations.push(newConfiguation(syncTableTr, autoSynchron));
    }
    //开启自动同步，但是没有选择店铺
    if (autoSynchron && notCheckedSingle) {
        showMessage("请选择开启自动同步的店铺")
        return;
    }

    //发送请求，获取同步配置
    $.ajax({
        url: "/inventorySync/updateInventorySyncConfiguations.do",
        type: "post",
        traditional : true,
        data: {
            //转成json字符串
            "configuations": JSON.stringify(configuations),  //biginteger
            "itemIds": item_ids,  //biginteger
        },
        dataType: "json",
        success: function (result) {
            //接收参数
            var row = result.data
            var state = result.state;
            if (state == 0) {
                if (row > 0) {
                    showMessage("库存同步配置成功");
                    //隐藏 警戒库 弹出框
                    $(".inventory_sync_box").css("display", "none");
                    //加载页面
                    loadInventorySync(now_page, key_words, auto_synchron);
                } else {
                    showMessage("库存同步配置为零");
                }
            } else if (state == 1) {
                //获取异常信息
                var message = result.message;
                showMessage(message);
            }
        },
        error: function () {
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
function newConfiguation(syncTableTr, autoSynchron) {
    //创建返回对象map
    var map = {};

    //加入 ID
    // map["id"] = Number(configurationId);
    //加入 同步（开关键）
    map["synchron"] = Number(syncTableTr.find("input[name='autoSynchron']").prop("checked"));
    //加入 库存自动同步状况（开关键）
    map["autoSynchron"] = Number(autoSynchron);
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
    //加入 店铺ID
    map["shopId"] = Number(syncTableTr.data("platformErpLinkShopWarehouseInfo").shopId);

    //加入 仓库ID
    map["warehouseId"] = syncTableTr.find(".warehouse_select").val();
    //获取仓库下拉框
    var warehouseOption = syncTableTr.find(".warehouse_select option").not(":first");
    map["warehouseIds"] = [];
    for (var i = 0; i < warehouseOption.length; i++) {
        map["warehouseIds"].push(warehouseOption.eq(i).val());
    }

    //加入 库位ID
    map["stocklocationId"] = syncTableTr.find(".stocklocation_select").val();
    //获取库位下拉框
    var stocklocationOption = syncTableTr.find(".stocklocation_select option").not(":first");
    map["stocklocationIds"] = [];
    for (var i = 0; i < stocklocationOption.length; i++) {
        map["stocklocationIds"].push(stocklocationOption.eq(i).val());
    }

    return map;
}


/**
 * 7.点击'批量更改库存同步设置'
 * @author 赵滨
 */
function inventorySyncBatchSync() {
    //清空 同步配置
    $("#inventorySync_syncTable").children().remove();

    //商品ID
    var itemIds = [];
    var find = $("#inventorySync_table").find("input[name='id[]']:checked");

    //关闭开关
    $("#inventorySync_startSync").prop("checked", false);
    for (var i = 0; i < find.length; i++) {
        itemIds.push(find.eq(i).val());
        //如果自动同步开关为开，就在弹出框内选中
        var  on_off= find.eq(i).parent().parent().find("td:last").prev().html();
        if (on_off == "开") {
            $("#inventorySync_startSync").prop("checked", true);
        }
    }

    //提示
    if (itemIds.length == 0) {
        //隐藏 警戒库 弹出框
        $(".inventory_sync_box").css("display", "none");
        showMessage("请选择需要库存同步的商品")
        return;
    }
    //赋值给全局变量
    item_ids = itemIds;

    //发送请求，获取同步配置
    $.ajax({
        url : "/inventorySync/listInventorySyncByItemBind.do",
        type : "post",
        traditional : true,
        data : {
            "itemIds" : itemIds //BigInteger
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
    insertTitle($("#inventorySync_syncTable"));

    //遍历每条商品记录
    for (var listIndex = 0; listIndex < list.length; listIndex++) {
        var map = list[listIndex];

        //获取 系统商品对应关系关联表 同步信息
        var platformErpLinkShopWarehouseInfoList = map.platformErpLinkShopWarehouseInfoList;
        //获取 查询库位商品库存关联表 获取仓库和库位
        var itemWarehouseStocklocationList = map.itemWarehouseStocklocationList;

        //如果没有同步配置
        if (platformErpLinkShopWarehouseInfoList.length == 0) {
            showMessage("该条商品不能同步设置，请先关联线上商品对应关系");
            //隐藏 警戒库 弹出框
            $(".inventory_sync_box").css("display", "none");
            return;
        }
        //如果没有仓库库位
        if (itemWarehouseStocklocationList.length == 0) {
            showMessage("该条商品不能同步设置，请先设置商品的仓库库位");
            //隐藏 警戒库 弹出框
            $(".inventory_sync_box").css("display", "none");
            return;
        }

        //插入内容
        for (var i = 0; i < platformErpLinkShopWarehouseInfoList.length; i++) {
            //这条商品信息 的 单条关联信息
            var platformErpLinkShopWarehouseInfo = platformErpLinkShopWarehouseInfoList[i];

            //是否存在相同店铺
            var existRepetitionShopTr = existRepetitionShop(
                $("#inventorySync_syncTable").find("tr").not(":first"), platformErpLinkShopWarehouseInfo.shopId);

            if (existRepetitionShopTr == -1) {
                //插入同步配置
                insertSyncConfiguration(
                    $("#inventorySync_syncTable"), platformErpLinkShopWarehouseInfo, itemWarehouseStocklocationList);
            } else{
                //获取这个tr的data
                var itemWarehouseStocklocationListOld = existRepetitionShopTr.data("itemWarehouseStocklocationList");
                var platformErpLinkShopWarehouseInfoOld = existRepetitionShopTr.data("platformErpLinkShopWarehouseInfo");
                //把旧的数组全部追加到新的数组中
                Array.prototype.push.apply(itemWarehouseStocklocationList, itemWarehouseStocklocationListOld);
                //调出选中的同步
                if (platformErpLinkShopWarehouseInfoOld.autoSynchron == 1) {
                    //重新赋值
                    platformErpLinkShopWarehouseInfo = platformErpLinkShopWarehouseInfoOld;
                }
                //删除旧的tr
                existRepetitionShopTr.remove();
                //插入同步配置
                insertSyncConfiguration(
                    $("#inventorySync_syncTable"), platformErpLinkShopWarehouseInfoOld, itemWarehouseStocklocationList);
            }
            //把仓库库位内容插入进去
            insertSelectOption(
                $("#inventorySync_syncTable").find(".warehouse_select:last"),
                $("#inventorySync_syncTable").find(".stocklocation_select:last"),
                itemWarehouseStocklocationList, platformErpLinkShopWarehouseInfo
            );

            //如果是最后一条记录，那么查看'全选'是否需要选中
            if (i == platformErpLinkShopWarehouseInfoList.length - 1) {
                checkedOne($("#inventorySync_syncTable"), "allAutoSynchron",
                    "autoSynchron", platformErpLinkShopWarehouseInfo.autoSynchron);
            }
        }
    }

    //监听切换'仓库'，切换仓库下面的库位
    $("#inventorySync_syncTable").on("change", ".warehouse_select", warehouseSelectOnChange);

    //监听切换'库位'，查看仓库是否改变
    $("#inventorySync_syncTable").on("change", ".stocklocation_select", function () {
        if ($(this).parent().parent().find(".warehouse_select").val() == 0) {
            showMessage("如果指定库位，请先选择仓库")
            $(this).find("option:first").prop("selected", true);
        }
    });

    //显示 警戒库 弹出框
    $(".inventory_sync_box").css("display", "block");

    //插入标题
    function insertTitle(appendObject) {
        var tr = '<tr> ' +
            '<th width="8%" style="padding-top: 10px;"> ' +
            '<input type="checkbox" name="allAutoSynchron">全选 ' +
            '</th> ' +
            '<th width="12%" style="padding-top: 10px;">店铺</th> ' +
            '<th width="11%" style="padding-top: 10px;">仓库</th> ' +
            '<th width="11%" style="padding-top: 10px;">库位</th> ' +
            '<th width="38%" style="padding-top: 10px;">规则</th> ' +
            '<th width="10%">有库存时自动上架</th> ' +
            '<th width="10%">库存低于警戒值不同步</th> ' +
            '</tr>';
        appendObject.append(tr);
    }

    //插入下拉框
    function insertSelectOption(
        warehouseSelect, stocklocationSelect, itemWarehouseStocklocationList, platformErpLinkShopWarehouseInfo) {
        for (var i = 0; i < itemWarehouseStocklocationList.length; i++) {
            var itemWarehouseStocklocation = itemWarehouseStocklocationList[i];

            //查看仓库是否存在重复，如果重复则不插入
            var warehouseExistRepetition = existRepetitionWarehouseStocklocation(
                warehouseSelect.children("option"), itemWarehouseStocklocation.warehouseId);
            if (warehouseExistRepetition == -1) {
                var selected = '';
                //如果下拉框中的ID与这条记录的ID匹配
                if (itemWarehouseStocklocation.warehouseId == platformErpLinkShopWarehouseInfo.warehouseId) {
                    selected = ' selected=true ';
                }
                //插入到仓库最后一个下拉框内
                warehouseSelect.append(
                    '<option ' + selected +     //是否选中
                    'value="' + itemWarehouseStocklocation.warehouseId + '">' + //仓库ID
                    itemWarehouseStocklocation.warehouseName    //仓库名称
                    + '</option>'
                );
            }

            //定义仓库ID，用于判断选中哪些库位
            var warehouseId = warehouseSelect.val();
            //首先，选择全部仓库，可以显示；其次选中的仓库，和原始数据匹配成功，可以显示
            if (warehouseId == 0 || warehouseId == itemWarehouseStocklocation.warehouseId) {
                //查看库位是否存在重复，如果重复则不插入
                var stocklocationExistRepetition = existRepetitionWarehouseStocklocation(
                    stocklocationSelect.children("option"), itemWarehouseStocklocation.stocklocationId);
                if (stocklocationExistRepetition == -1) {
                    var selected = '';
                    //如果下拉框中的ID与这条记录的ID匹配
                    if (itemWarehouseStocklocation.stocklocationId == platformErpLinkShopWarehouseInfo.stocklocationId) {
                        selected = ' selected=true ';
                    }
                    //插入到库位最后一个下拉框内
                    stocklocationSelect.append(
                        '<option ' + selected +     //是否选中
                        'value="' + itemWarehouseStocklocation.stocklocationId + '">' + //库位ID
                        itemWarehouseStocklocation.stocklocationName    //库位名称
                        + '</option>'
                    );
                }
            }
        }
    }
    //查看仓库库位是否存在重复,如果出现重复返回1，如果没有出现重复返回-1
    function existRepetitionWarehouseStocklocation(optionObjectList, id) {
        for (var i = 0; i < optionObjectList.length; i++) {
            var optionObject = optionObjectList.eq(i);
            if (optionObject.val() == id) {
                return 1;
            }
        }
        return -1;
    }
    //是否存在相同店铺
    function existRepetitionShop(objectList, id) {
        for (var i = 0; i < objectList.length; i++) {
            var shopId = objectList.eq(i).data("platformErpLinkShopWarehouseInfo").shopId;
            if (shopId == id) {
                return objectList.eq(i);
            }
        }
        return -1;
    }
    //插入同步配置
    function insertSyncConfiguration(appendObject, platformErpLinkShopWarehouseInfo, itemWarehouseStocklocationList) {
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
            '    </select>' +
            '  </td>' +

            '  <td style="padding-top: 2px;">' +
            '    <select class="input stocklocation_select" ' +
            '               style="width:100px; line-height:17px; display:inline-block">' +
            '      <option value="0">全部库位</option>'+     //库位ID，库位名称
            '    </select>' +
            '  </td>' +

            '  <td style="padding-top: 2px;">' +
            '    <select class="input availableStock" ' +
            '               style="width:215px; line-height:17px; display:inline-block">' +
            '      <option value="0"'+(platformErpLinkShopWarehouseInfo.availableStock == 0?"selected":"")+
            '               >总量</option>' +
            '      <option value="1"'+(platformErpLinkShopWarehouseInfo.availableStock == 1?"selected":"")+
            '               >可用量(订单量买家付款后扣减)</option>' +
            '      <option value="2"'+(platformErpLinkShopWarehouseInfo.availableStock == 2?"selected":"")+
            '               >可用量(订单量审核后扣减)</option>' +
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
        var $tr = $(tr);
        $tr.data("itemWarehouseStocklocationList", itemWarehouseStocklocationList);
        $tr.data("platformErpLinkShopWarehouseInfo", platformErpLinkShopWarehouseInfo);
        //插入内容
        appendObject.append($tr);
    }
    //监听切换'仓库'，切换仓库下面的库位
    function warehouseSelectOnChange() {
        //仓库库位原始数据集合
        var itemWarehouseStocklocationList = $(this).parent().parent().data("itemWarehouseStocklocationList");
        //库位下拉框
        var stocklocationSelect = $(this).parent().parent().find(".stocklocation_select");
        //当前选中的仓库ID
        var warehouseId = $(this).find("option:selected").val();

        //清空库位下拉框
        stocklocationSelect.children().remove();
        //插入全部库位
        stocklocationSelect.append('<option value="0">全部库位</option>');

        for (var i = 0; i < itemWarehouseStocklocationList.length; i++) {
            var itemWarehouseStocklocation = itemWarehouseStocklocationList[i];
            //首先，选择全部仓库，可以显示；其次选中的仓库，和原始数据匹配成功，可以显示
            if (warehouseId == 0 || warehouseId == itemWarehouseStocklocation.warehouseId) {
                //查看库位是否存在重复，如果重复则不插入
                var stocklocationExistRepetition =
                    existRepetitionWarehouseStocklocation(stocklocationSelect.children("option"), itemWarehouseStocklocation.stocklocationId);
                if (stocklocationExistRepetition == -1) {
                    //插入到库位最后一个下拉框内
                    stocklocationSelect.append(
                        '<option value="' + itemWarehouseStocklocation.stocklocationId + '">' + //库位ID
                        itemWarehouseStocklocation.stocklocationName    //库位名称
                        + '</option>'
                    );
                }
            }
        }
    }
}

/**
 * 6.点击'同步配置'，显示弹出
 * @author 赵滨
 */
function alertStock() {
    //清空 同步配置
    $("#inventorySync_syncTable").children().remove();

    //商品ID
    var itemIds = [];
    itemIds.push($(this).parent().parent().find("input[name='id[]']").val());
    //获取自动同步开关
    var autoSynchrons = $(this).parent().prev().html();
    //操作 开启自动同步 开关
    if (autoSynchrons == "开") {
        $("#inventorySync_startSync").prop("checked", true);
    } else if (autoSynchrons == "关") {
        $("#inventorySync_startSync").prop("checked", false);
    }
    //赋值给全局变量
    item_ids = itemIds;

    //发送请求，获取同步配置
    $.ajax({
        url : "/inventorySync/listInventorySyncByItemBind.do",
        type : "post",
        traditional : true,
        data : {
            "itemIds" : itemIds //BigInteger
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
    //行数
    var rows = map.rows;
    //清空页面
    $("#inventorySync_table").children().remove();

    //第一部分
    var tr = '';
    tr += '<tr>';
    tr += '<th width="80"><input type="checkbox" name="allId" value="1" class="check_coding" />序号</th>';
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
        tr += (i + 1) + ((parseInt(now_page) - 1) * parseInt(rows));	//序号
        // tr += listItemCommonSync[i].id;     //ID
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].erpItemNum;	//编码
        tr += '</td>';
        tr += '<td>';
        tr += '<p title="';
        tr += listItemCommonSync[i].name;	//名称
        tr += '">';
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
        tr += listItemCommonSync[i].sumQuantity;	//总库存量
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].useQuantity;	//总订单占用量
        tr += '</td>';
        tr += '<td>';
        tr += listItemCommonSync[i].sumQuantity - listItemCommonSync[i].useQuantity;	//总可用量
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

    if (listItemCommonSync.length > 0) {
        //创建页码
        createPageList();
    }

    function createPageList() {
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
        tr += '<a href="javascript:void(0)">下一页</a><a href="javascript:void(0)">尾页</a>';
        tr += '<input name="goto" style="border-radius: 3px;' +
            'border: 1px solid #dfdfdf;padding: 5px 5px;width: 3.5em;font: inherit;text-align: center;">';
        tr += '<a href="javascript:void(0)">跳转</a></div>';
        tr += '</td></tr>';

        //加入页面
        $("#inventorySync_table").append(tr);
    }

}