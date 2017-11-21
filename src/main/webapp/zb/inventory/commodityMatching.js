var now_page = 1;   //0.全局变量，当前页面
var max_page = 1;   //0.全局变量，最大页面
var key_words = ""; //0.全局变量，关键字
var warehouse_id = "";  //0.全局变量，仓库ID
var stocklocation_id = "";  //0.全局变量，库位ID

var now_page_top = 1;   //0.全局变量，当前页面，顶部
var max_page_top = 1;   //0.全局变量，最大页面，顶部
var key_words_top = ""; //0.全局变量，关键字，顶部
var warehouse_id_top = "";  //0.全局变量，仓库ID，顶部
var stocklocation_id_top = "";  //0.全局变量，库位ID，顶部

var now_page_bottom = 1;   //0.全局变量，当前页面，底部
var max_page_bottom = 1;   //0.全局变量，最大页面，底部
var key_words_bottom = ""; //0.全局变量，关键字，底部
var warehouse_id_bottom = "";  //0.全局变量，仓库ID，底部
var stocklocation_id_bottom = "";  //0.全局变量，库位ID，底部

var stocklocation_info_list = [];   //0.全局变量，库位列表
var load_ajax = null;   //全局变量，加载ajax对象
var bind_map = {};    //全局变量，关联对象集合

$(function() {
    //1.加载仓库和库位
    loadWarehouseAndStocklocation();

    //2.加载页面
    loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);

    //3.加载监听页码点击事件
    loadClickPage();

    //4.加载点击'查询'关键字
    loadClickKeywordsQuery();

    //5.加载'关键字'回车，等于点击查询
    loadKeywordsKeydown();

    //6.加载点击'仓库'，选择仓库内容
    loadClickWarehouse();

    //7.加载点击'库位'，选择库位内容
    loadClickStocklocation();

    //8.监听'删除'，取消当前商品的关联信息
    $("#commodityMatchingTableParent").on("click", ".delete_bind", deleteBind);

    //9.点击'云仓商品关联'，加载弹出框
    $("#alertMatching").click(alertMatching);

    //10.监听剑姬 顶部 '单选'
    $("#tableTopOfAlert").on("click", "input[name='id_top']" ,clickIdTop);

    //11.监听点击  底部 '全选'
    $("#tableBottomOfAlert").on("click", "input[name='id_bottom_all']" ,clickIdBottomAll);

    //12.监听点击 底部 '单选'
    $("#tableBottomOfAlert").on("click", "input[name='id_bottom']" ,clickIdBottom);

    //13.点击'提交'
    $("body").on("click", "#bind_commit", bindCommit);
})

/**
 * 13.点击'提交'
 * @author 赵滨
 */
function bindCommit() {
    //获取顶部 选择框 ID
    var topId = $("#tableTopOfAlert").find("input[name='id_top']:checked").val();
    if (topId == null) {
        showMessage("请在上方商品列表中，选择主关联商品")
        return;
    }
    if (JSON.stringify(bind_map) == "{}") {
        showMessage("当前关联关系没有改变，无需修改")
        return;
    }

    //发送请求，提交云仓商品关联关系
    $.ajax({
        url : "/inventoryList/bindCommit.do",
        type : "post",
        //traditional : true,
        data : {
            "topId" : topId, // bigint
            "bindMap" : JSON.stringify(bind_map) // json
        },
        dataType : "json",
        success : function(result) {
            //接受
            var row = result.data
            if (row > 0) {
                showMessage("提交云仓商品关联成功");
                //关闭弹窗
                $(".matching_alert_box").css("display","none");
                //加载页面
                loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);
            } else {
                showMessage("提交云仓商品关联失败");
            }
        },
        error : function() {
            showMessage("提交云仓商品关联失败");
        }
    });
}

/**
 * 12.监听点击 底部 '单选'
 * @returns
 * @author 赵滨
 */
function clickIdBottom() {
    //获取当前选框
    var nowChecked = $(this).prop("checked");
    //获取底部ID
    var bottomId = $(this).val();
    //根据单选框 操作 关联对象集合
    if (nowChecked == false) {
        //如果集合中已经存在，那么删除该key-value
        if (bind_map[bottomId] != null) {
            delete bind_map[bottomId];
        } else {
            bind_map[bottomId] = false;
        }
    } else {
        //如果集合中已经存在，那么删除该key-value
        if (bind_map[bottomId] != null) {
            delete bind_map[bottomId];
        } else {
            //获取顶部 选择框 关联ID
            var topBindId = $("#tableTopOfAlert").find("input[name='id_top']:checked")
                .parent().parent().data("locationItemStockBindId");
            //获取底部 选择框 关联ID
            var bottomBindId = $(this).parent().parent().data("locationItemStockBindId");
            //如果不相同，并且不为空，那么该底部选择框是别的关联信息
            if (bottomBindId != topBindId && bottomBindId != 0 && topBindId != null) {
                if (!confirm("这条商品已经和其他商品存在关联，您是否确认取消之前的关联，和当前商品新建关联？")) {
                    $(this).prop("checked", false);
                    return;
                }
            }
            bind_map[bottomId] = true
        }
    }

    //获取全选框
    var allChecked = $("input[name='id_bottom_all']").prop("checked");
    //获取所有选择框
    var checkeds = $("input[name='id_bottom']");
    //如果没选中 并且 全选选中
    if (nowChecked == false && allChecked == true) {
        //取消全选
        $("input[name='id_bottom_all']").prop("checked", "");

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
        $("input[name='id_bottom_all']").prop("checked", "true");
    }
}

/**
 * 11.监听点击 底部 '全选'
 * @returns
 * @author 赵滨
 */
function clickIdBottomAll() {
    //首次点击
    var firstClick = true;
    //如果选中
    if ($(this).prop("checked") == true) {
        //遍历选择框
        $("input[name='id_bottom']").each(function() {
            //如果没有选中，那么改成选中
            if ($(this).prop("checked") == false) {
                //获取底部ID
                var bottomId = $(this).val();
                //如果集合中已经存在，那么删除该key-value
                if (bind_map[bottomId] != null) {
                    delete bind_map[bottomId];
                } else {
                    //获取顶部 选择框 关联ID
                    var topBindId = $("#tableTopOfAlert").find("input[name='id_top']:checked")
                        .parent().parent().data("locationItemStockBindId");
                    //获取底部 选择框 关联ID
                    var bottomBindId = $(this).parent().parent().data("locationItemStockBindId");
                    //如果不相同，并且不为空，那么该底部选择框是别的关联信息
                    if (bottomBindId != topBindId && bottomBindId != 0 &&
                        topBindId != null && firstClick == true) {
                        firstClick = false;
                        if (!confirm(
                            "下列商品已经和其他商品存在关联，您是否确认取消之前的关联，和当前商品新建关联？")) {
                            $("input[name='id_bottom_all']").prop("checked", false);
                            return false;
                        }
                    }
                    bind_map[bottomId] = true;
                }
                //选中 每个框
                $(this).prop("checked", true);
            }
        });

    } else {
        //遍历选择框
        $("input[name='id_bottom']").each(function() {
            //如果已经选中，那么取消选中
            if ($(this).prop("checked") == true) {
                //如果是可以使用的，才取消勾选
                if ($(this).prop("disabled") == false) {
                    $(this).prop("checked", false);
                }
                //获取底部ID
                var bottomId = $(this).val();
                //如果集合中已经存在，那么删除该key-value
                if (bind_map[bottomId] != null) {
                    delete bind_map[bottomId];
                } else {
                    bind_map[bottomId] = false;
                }
            }
        });
    }
}

/**
 * 10.监听点击 顶部 '单选'
 * @author 赵滨
 */
function clickIdTop() {
    //关联顶部底部勾选
    checkedBindTopBottom();
}

/**
 * 10.1.初始化底部信息
 * @author 赵滨
 */
function initializeBottom() {
    //取消底部勾选
    $("#tableBottomOfAlert").find("input[name='id_bottom']").prop("checked", false);
    $("#tableBottomOfAlert").find("input[name='id_bottom_all']").prop("checked", false);
    //清空 关联对象集合
    bind_map = {};
    //可以使用input
    $("#tableBottomOfAlert").find("input[name='id_bottom']").prop("disabled", false);
}

/**
 * 10.2.关联顶部底部勾选
 * @author 赵滨
 */
function checkedBindTopBottom() {
    //初始化底部信息
    initializeBottom();

    //获取顶部 选择框 ID
    var topId = $("#tableTopOfAlert").find("input[name='id_top']:checked").val();
    //获取顶部 选择框 关联ID
    var topBindId = $("#tableTopOfAlert").find("input[name='id_top']:checked")
        .parent().parent().data("locationItemStockBindId");

    //遍历底部内容，匹配相同关联ID，然后勾选
    var bottomList = $("#tableBottomOfAlert").find("tr");
    for (var i = 0; i < bottomList.length; i++) {
        var bottomBindId = bottomList.eq(i).data("locationItemStockBindId");
        //相同就选中，不同就取消勾选
        if (bottomBindId == topBindId && topBindId != 0 && bottomBindId != 0) {
            //选中
            bottomList.eq(i).find("input[name='id_bottom']").prop("checked", true);
        } else {
            //不选
            bottomList.eq(i).find("input[name='id_bottom']").prop("checked", false);
        }

        //获取底部 选择框 ID
        var bottomId = bottomList.eq(i).find("input[name='id_bottom']").val();
        //如果是ID相同，说明是同一内容
        if (topId == bottomId) {
            //不可使用
            bottomList.eq(i).find("input[name='id_bottom']").prop("disabled", true);
            bottomList.eq(i).find("input[name='id_bottom']").prop("checked", true);
        }
    }

    //根据页面选择情况，修改全选
    var checkeds = $("input[name='id_bottom']");
    //遍历所有选择框
    for (var i = 0; i < checkeds.length; i++) {
        //如果出现没有选中的
        if (checkeds.eq(i).prop("checked") == false) {
            //跳出
            return;
        }
    }
    //结尾处，对于没有产生跳出的内容追加全选框
    $("input[name='id_bottom_all']").prop("checked", "true");
}


/**
 * 9.点击'云仓商品关联'，加载弹出框
 * @author 赵滨
 */
function alertMatching() {
    //初始化 滚动条
    $(".matching_content").scrollTop(0);
    //初始化 全局变量
    now_page_top = 1;   //0.全局变量，当前页面，顶部
    max_page_top = 1;   //0.全局变量，最大页面，顶部
    key_words_top = ""; //0.全局变量，关键字，顶部
    warehouse_id_top = "";  //0.全局变量，仓库ID，顶部
    stocklocation_id_top = "";  //0.全局变量，库位ID，顶部

    now_page_bottom = 1;   //0.全局变量，当前页面，底部
    max_page_bottom = 1;   //0.全局变量，最大页面，底部
    key_words_bottom = ""; //0.全局变量，关键字，底部
    warehouse_id_bottom = "";  //0.全局变量，仓库ID，底部
    stocklocation_id_bottom = "";  //0.全局变量，库位ID，底部
    //初始化 关键字
    $("#tableTopOfAlert").parent().find("input[name='keywords']").val("");
    $("#tableBottomOfAlert").parent().find("input[name='keywords']").val("");
    //初始化 仓库
    // $("#selectStocklocationInfo_top").children(":first").prop("selected", true);
    // console.log($("#selectStocklocationInfo_top").children(":first"));

    //加载 云仓商品关联 弹出框
    loadWarehouseAndStocklocationOfAlert(
        now_page_top, key_words_top, warehouse_id_top, stocklocation_id_top,
        now_page_bottom, key_words_bottom, warehouse_id_bottom, stocklocation_id_bottom);
}

/**
 *9.1.加载 云仓商品关联 弹出框
 * @author 赵滨
 */
function loadWarehouseAndStocklocationOfAlert(
        nowPageTop, keyWordsTop, warehouseIdTop, stocklocationIdTop,
        nowPageBottom, keyWordsBottom, warehouseIdBottom, stocklocationIdBottom) {
    //发送请求，获取页面内容
    load_ajax = $.ajax({
        url : "/inventoryList/loadWarehouseAndStocklocationOfAlert.do",
        type : "post",
        //traditional : true,
        data : {
            "nowPageTop" : nowPageTop, // int
            "keyWordsTop" : keyWordsTop, //String
            "warehouseIdTop" : warehouseIdTop,	//bigint
            "stocklocationIdTop" : stocklocationIdTop,	//bigint
            "nowPageBottom" : nowPageBottom, // int
            "keyWordsBottom" : keyWordsBottom, //String
            "warehouseIdBottom" : warehouseIdBottom,	//bigint
            "stocklocationIdBottom" : stocklocationIdBottom	//bigint
        },
        dataType : "json",
        success : function(result) {
            //接受栏目集合
            var map = result.data
            //创建页面
            createAlertMatching(map);
        },
        error : function() {
            showMessage("加载云仓商品关联失败");
        }
    });
}

/**
 * 9.2.创建页面
 * @author 赵滨
 */
function createAlertMatching(map) {
    //如果存在顶部内容
    if (map.maxPageTop != null) {
        //加载最大页数
        max_page_top = map.maxPageTop;
        //加载框
        var tableOfAlert = "tableTopOfAlert";
        //获取 云仓商品关联 列表
        var listLocationItemStockAndItem = map.listLocationItemStockAndItemTop;
        //创建部分弹出框
        createAlertPart(tableOfAlert, listLocationItemStockAndItem);
    }

    //如果存在底部内容
    if (map.maxPageBottom != null) {
        //加载最大页数
        max_page_bottom = map.maxPageBottom;
        //加载框
        var tableOfAlert = "tableBottomOfAlert";
        //获取 云仓商品关联 列表
        var listLocationItemStockAndItem = map.listLocationItemStockAndItemBottom;
        //创建部分弹出框
        createAlertPart(tableOfAlert, listLocationItemStockAndItem);
    }

    //创建部分弹出框
    function createAlertPart(tableOfAlert, listLocationItemStockAndItem) {
        //清空内容
        $("#" + tableOfAlert + "").children().remove();

        //拼接块
        var tr = '<tr>' +
            '<th width="100">';

        //如果是底部内容，加入选择框
        if (tableOfAlert == "tableBottomOfAlert") {
            tr += '<input type="checkbox" name="id_bottom_all">';
        }

        tr += '商品编码</th>' +
            '<th width="100">商品名称</th>' +
            '<th>商品条码</th>' +
            '<th width="100">商品规格</th>' +
            '<th>仓库</th>' +
            '<th>库位</th>' +
            '<th>总量</th>' +
            '<th>订单占用量</th>' +
            '<th>可用量</th>' +
            '</tr>';
        //插入
        $("#" + tableOfAlert + "").append(tr);

        //遍历 云仓商品关联 列表
        for (var i = 0; i < listLocationItemStockAndItem.length; i++) {
            var locationItemStockAndItem = listLocationItemStockAndItem[i];

            tr = '';
            tr += '<tr>';
            tr += '<td>';
            //如果是底部内容，加入选择框
            if (tableOfAlert == "tableBottomOfAlert") {
                tr += '<input type="checkbox" name="id_bottom" value="' + locationItemStockAndItem.id + '">';//ID
            } else {
                tr += '<input type="radio"  name="id_top" value="' + locationItemStockAndItem.id + '">';    //ID
            }
            tr += locationItemStockAndItem.erpItemNum;  //商品编码
            tr += '</td>';
            tr += '<td>';
            tr += '<p title="';
            tr += locationItemStockAndItem.name;    //商品名称
            tr += '">';
            tr += locationItemStockAndItem.name;    //商品名称
            tr += '</p>';
            tr += '</td>'; //商品名称
            tr += '<td>';
            tr += '<p>';
            tr += locationItemStockAndItem.barCode;  //商品条码
            tr += '</p>';
            tr += '</td>';
            tr += '<td>'+locationItemStockAndItem.color+' '+locationItemStockAndItem.size+'</td>';  //商品规格
            tr += '<td>';
            tr += '<p>';
            tr += locationItemStockAndItem.warehouseName;    //仓库
            tr += '</p>';
            tr += '</td>';
            tr += '<td>';
            tr += '<p>';
            tr += locationItemStockAndItem.stocklocationName;    //库位
            tr += '</p>';
            tr += '</td>';
            tr += '<td><span>'+locationItemStockAndItem.sumQuantity+'</span></td>'; //库存总量
            tr += '<td><span></span></td>';
            tr += '<td><span></span></td>';
            tr += '</tr>';
            //插入页面
            $("#" + tableOfAlert + "").append($(tr).data("locationItemStockBindId", locationItemStockAndItem.bindId));
        }
        //如果存在内容
        if (listLocationItemStockAndItem.length > 0) {
            //创建页码
            createPageList(tableOfAlert);
        }
    }

    //创建页码
    function createPageList(tableOfAlert) {
        //定义页码变量
        var nowPage = null;
        var maxPage = null;
        var goto = null;

        //根据不同的部分，来拼接变量名
        if (tableOfAlert == "tableTopOfAlert") {
            nowPage = window["now_page_top"];
            maxPage = window["max_page_top"];
            goto = "goto_top";

        } else if (tableOfAlert == "tableBottomOfAlert") {
            nowPage = window["now_page_bottom"];
            maxPage = window["max_page_bottom"];
            goto = "goto_bottom";
        }
        var tr = '';
        //开始部分
        tr += '<tr><td colspan="10">';
        tr += '<div class="pagelist"><a href="javascript:void(0)">首页</a><a href="javascript:void(0)">上一页</a> ';
        //中间部分
        if (maxPage > 5) {
            //如果是页码前两个
            if (nowPage <= 3) {
                //循环前三页码
                for (var i = 1; i < 4; i++) {
                    //如果选中当前页码，则变成蓝色背景
                    if(i==nowPage){
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
            } else if (nowPage >= 4 && nowPage <= maxPage - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += nowPage - 2;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += nowPage - 1;
                tr += '</a>';
                //页码中间选中的
                tr += '<span class="current" style="cursor:default">';
                tr += nowPage;
                tr += '</span>';
                //页码后两个
                tr += '<a href="javascript:void(0)">';
                tr += nowPage + 1;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += nowPage + 2;
                tr += '</a>……';
                //如果是页码后两个
            } else if (nowPage > maxPage - 3) {
                //页码前两个
                tr += '……<a href="javascript:void(0)">';
                tr += maxPage - 4;
                tr += '</a>';
                tr += '<a href="javascript:void(0)">';
                tr += maxPage - 3;
                tr += '</a>';
                //循环后三页
                for (var i = maxPage - 2; i <= maxPage; i++) {
                    //如果选中当前页码，则变成蓝色背景
                    if(i==nowPage){
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
            while (i <= maxPage) {
                //如果选中当前页码，则变成蓝色背景
                if(i==nowPage){
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
        tr += '<input name="' + goto + '" style="border-radius: 3px;' +
            'border: 1px solid #dfdfdf;padding: 5px 5px;width: 3.5em;font: inherit;text-align: center;">';
        tr += '<a href="javascript:void(0)">跳转</a></div>';
        tr += '</td></tr>';
        //加入页面
        $("#" + tableOfAlert + "").append(tr);

        //插入提交
        if (tableOfAlert == "tableBottomOfAlert") {
            tr = '<tr>' +
                '<td colspan="9" >' +
                '<a href="javascript:;" class="button border-main matching_bc" id="bind_commit"> 提交</a>' +
                '</td>' +
                '</tr>';
            $("#" + tableOfAlert + "").append(tr);
        }
    }
}

/**
 * 8.监听'删除'，取消当前商品的关联信息
 * @author 赵滨
 */
function deleteBind() {
    if (confirm("您确认删除该条关联吗？")) {
        //获取关联ID
        var locationItemStockId = $(this).parent().parent().data("locationItemStockId");

        //发送请求，获取页面内容
        $.ajax({
            url : "/inventoryList/deleteCommodityMatching.do",
            type : "post",
            //traditional : true,
            data : {
                "locationItemStockId" : locationItemStockId // bigint
            },
            dataType : "json",
            success : function(result) {
                //接收
                var row = result.data
                if (row > 0) {
                    showMessage("删除关联成功");
                    //获取页面列表条数
                    var listLength = $("#commodityMatchingTableParent").children("table").eq(0).find("tr").length;
                    if (listLength <= 2 && now_page > 1) {
                        now_page--;
                    }
                    //加载页面
                    loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);
                } else {
                    showMessage("删除关联失败");
                }
            },
            error : function() {
                showMessage("删除关联失败");
            }
        });
    }
}

/**
 * 7.加载点击'库位'，选择库位内容
 * @returns
 * @author 赵滨
 */
function loadClickStocklocation() {
    //点击主页面'库位'，选择库位内容
    $("#selectStocklocationInfo").change(function () {
        //关键字清空
        $("#commodityMatchingTableParent input[name='keywords']").val("");
        //点击库位
        clickStocklocation("");
        //加载页面
        loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);
    });

    //点击顶部'库位'，选择库位内容
    $("#selectStocklocationInfo_top").change(function () {
        //关键字清空
        $("#tableTopOfAlert").parent().find("input[name='keywords']").val("");
        //点击库位
        clickStocklocation("_top");
        //加载 顶部 内容
        loadWarehouseAndStocklocationOfAlert(
            now_page_top, key_words_top, warehouse_id_top, stocklocation_id_top,
            "", "", "", "");
        //初始化底部信息
        initializeBottom()
    });

    //点击底部'库位'，选择库位内容
    $("#selectStocklocationInfo_bottom").change(function () {
        //关键字清空
        $("#tableBottomOfAlert").parent().find("input[name='keywords']").val("");
        //点击库位
        clickStocklocation("_bottom");
        //加载 底部 内容
        loadWarehouseAndStocklocationOfAlert(
            "", "", "", "",
            now_page_bottom, key_words_bottom, warehouse_id_bottom, stocklocation_id_bottom);
        //等待加载 底部 内容，完成后，再执行方法
        load_ajax.done(function() {
            //关联顶部底部勾选
            checkedBindTopBottom();
        });
    });

    //点击库位
    function clickStocklocation(name) {
        //获取 并 赋值
        window["stocklocation_id" + name] = $("#selectStocklocationInfo" + name + "").find("option:selected").val();
        //页码初始化
        window["now_page" + name] = 1;
        //关键字清空
        window["key_words" + name] = "";
    }
}

/**
 * 6.加载点击'仓库'，选择仓库内容
 * @returns
 * @author 赵滨
 */
function loadClickWarehouse() {
    //点击主页面'仓库'，选择仓库内容
    $("#selectWarehouseInfo").change(function () {
        //关键字清空
        $("#commodityMatchingTableParent input[name='keywords']").val("");
        //点击仓库
        clickWarehouse("");
        //加载页面
        loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);
    });

    //点击顶部'仓库'，选择仓库内容
    $("#selectWarehouseInfo_top").change(function () {
        //关键字清空
        $("#tableTopOfAlert").parent().find("input[name='keywords']").val("");
        //点击仓库
        clickWarehouse("_top");
        //加载 顶部 内容
        loadWarehouseAndStocklocationOfAlert(
            now_page_top, key_words_top, warehouse_id_top, stocklocation_id_top,
            "", "", "", "");
        //初始化底部信息
        initializeBottom()
    });

    //点击底部'仓库'，选择仓库内容
    $("#selectWarehouseInfo_bottom").change(function () {
        //关键字清空
        $("#tableBottomOfAlert").parent().find("input[name='keywords']").val("");
        //点击仓库
        clickWarehouse("_bottom");
        //加载 底部 内容
        loadWarehouseAndStocklocationOfAlert(
            "", "", "", "",
            now_page_bottom, key_words_bottom, warehouse_id_bottom, stocklocation_id_bottom);
        //等待加载 底部 内容，完成后，再执行方法
        load_ajax.done(function() {
            //关联顶部底部勾选
            checkedBindTopBottom();
        });
    });

    function clickWarehouse(name) {
        //获取 并 赋值
        window["warehouse_id" + name] = $("#selectWarehouseInfo"+name+"").find("option:selected").val();
        //页码初始化
        window["now_page" + name] = 1;
        //关键字清空
        window["key_words" + name] = "";
        //库位初始化
        window["stocklocation_id" + name] = "";
        //库位初始化

        //删除已存在库位
        $("#selectStocklocationInfo"+name+"").children().remove();
        //插入全部库位
        $("#selectStocklocationInfo"+name+"").append('<option value="">全部库位</option>');
        //遍历库位集合，插入库位
        for (var i = 0; i < stocklocation_info_list.length; i++) {
            var stocklocationInfo = stocklocation_info_list[i];
            //如果全部仓库，或者是该仓库下的库位，插入
            if (window["warehouse_id" + name] == "" ||
                window["warehouse_id" + name] == stocklocationInfo.warehouseId) {
                var op = '<option value="'+stocklocationInfo.id+'">'+stocklocationInfo.name+'</option>';
                $("#selectStocklocationInfo"+name+"").append($(op).data("stocklocationInfo", stocklocationInfo));
            }
        }
    }
}

/**
 * 5.加载'关键字'回车，等于点击查询
 * @returns
 * @author 赵滨
 */
function loadKeywordsKeydown() {
    //主页面关键字查询
    $("#commodityMatchingTableParent input[name='keywords']").keydown(function (e) {
        //获取event对象
        var ev = document.all ? window.event : e;
        //如果是回车按键
        if(ev.keyCode==13) {
            //光标移除
            $("#commodityMatchingTableParent input[name='keywords']").blur();
            //点击'查询'关键字
            $("#commodityMatchingTableParent .icon-search").click();
        }
    });

    //主页面关键字查询
    $("#tableTopOfAlert").parent().find("input[name='keywords']").keydown(function (e) {
        //获取event对象
        var ev = document.all ? window.event : e;
        //如果是回车按键
        if(ev.keyCode==13) {
            //光标移除
            $("#tableTopOfAlert").parent().find("input[name='keywords']").blur();
            //点击'查询'关键字
            $("#tableTopOfAlert").parent().find(".icon-search").click();
        }
    });

    //主页面关键字查询
    $("#tableBottomOfAlert").parent().find("input[name='keywords']").keydown(function (e) {
        //获取event对象
        var ev = document.all ? window.event : e;
        //如果是回车按键
        if(ev.keyCode==13) {
            //光标移除
            $("#tableBottomOfAlert").parent().find("input[name='keywords']").blur();
            //点击'查询'关键字
            $("#tableBottomOfAlert").parent().find(".icon-search").click();
        }
    });
}

/**
 * 4.加载点击'查询'关键字
 * @returns
 * @author 赵滨
 */
function loadClickKeywordsQuery() {
    //点击主页面关键字查询
    $("#commodityMatchingTableParent .icon-search").click(function () {
        //获取关键字
        key_words = $("#commodityMatchingTableParent input[name='keywords']").val().trim();
        //页码初始化
        now_page = 1;
        //加载页面
        loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);
    });

    //点击顶部关键字查询
    $("#tableTopOfAlert").parent().find(".icon-search").click(function () {
        //获取关键字
        key_words_top = $("#tableTopOfAlert").parent().find("input[name='keywords']").val().trim();
        //页码初始化
        now_page_top = 1;
        //加载 顶部 内容
        loadWarehouseAndStocklocationOfAlert(
            now_page_top, key_words_top, warehouse_id_top, stocklocation_id_top,
            "", "", "", "");
        //初始化底部信息
        initializeBottom()
    });

    //点击底部关键字查询
    $("#tableBottomOfAlert").parent().find(".icon-search").click(function () {
        //获取关键字
        key_words_bottom = $("#tableBottomOfAlert").parent().find("input[name='keywords']").val().trim();
        //页码初始化
        now_page_bottom = 1;
        //加载 底部 内容
        loadWarehouseAndStocklocationOfAlert(
            "", "", "", "",
            now_page_bottom, key_words_bottom, warehouse_id_bottom, stocklocation_id_bottom);
        //等待加载 底部 内容，完成后，再执行方法
        load_ajax.done(function() {
            //关联顶部底部勾选
            checkedBindTopBottom();
        });
    });
}

/**
 * 3.加载监听页码点击事件
 * @returns
 * @author 赵滨
 */
function loadClickPage() {
    //点击主页面页码
    $("#commodityMatchingTableParent").on("click", ".pagelist a", function () {
        //改变页码
        if (changePageList("", $(this).html())) {
            //加载页面
            loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);
        }
    });

    //点击顶部页码
    $("#tableTopOfAlert").on("click", ".pagelist a", function () {
        //改变页码
        if (changePageList("_top", $(this).html())) {
            //加载 顶部 内容
            loadWarehouseAndStocklocationOfAlert(
                now_page_top, key_words_top, warehouse_id_top, stocklocation_id_top,
                "", "", "", "");
            //初始化底部信息
            initializeBottom();
        }
    });

    //点击底部面页码
    $("#tableBottomOfAlert").on("click", ".pagelist a", function () {
        //改变页码
        if (changePageList("_bottom", $(this).html())) {
            //加载 底部 内容
            loadWarehouseAndStocklocationOfAlert(
                "", "", "", "",
                now_page_bottom, key_words_bottom, warehouse_id_bottom, stocklocation_id_bottom);
            //等待加载 底部 内容，完成后，再执行方法
            load_ajax.done(function() {
                //关联顶部底部勾选
                checkedBindTopBottom();
            });
        }
    });

    //改变页码
    function changePageList(name, aHtml) {
        //从全局变量获取当前值
        var nowPage = window["now_page" + name];
        var maxPage = window["max_page" + name];

        //判断当前页的值
        if (aHtml == "首页") {
            if (nowPage == 1) {
                return;
            }
            nowPage = 1;
        } else if (aHtml == "上一页") {
            if (nowPage > 1) {
                nowPage--;
            } else {
                return;
            }
        } else if (aHtml == "下一页") {
            if (nowPage < maxPage) {
                nowPage++;
            } else {
                return;
            }
        } else if (aHtml == "尾页") {
            if (nowPage == parseInt(maxPage)) {
                return;
            }
            nowPage = parseInt(maxPage);
        } else if (aHtml == "跳转") {
            var goto = $("input[name='goto" + name +"']").val();
            if (!/^[0-9]*$/.test(goto)) {
                showMessage("请输入正整数");
                return;
            }
            if (goto == "" || goto == null) {
                return;
            }
            if (goto == 0) {
                goto = 1;
            }
            if (goto > maxPage) {
                showMessage("最大页数为" + maxPage + "页，跳转页数不能超过最大页数");
                return;
            } else if (nowPage == goto) {
                return;
            }
            nowPage = parseInt(goto);
        } else {
            nowPage = parseInt(aHtml);
        }

        //返回给全局变量
        window["now_page" + name] = nowPage;
        window["max_page" + name] = maxPage;
        return true;
    }
}

/**
 * 2.加载页面
 * @returns
 * @author 赵滨
 */
function loadCommodityMatching(nowPage, keywords, warehouseId, stocklocationId) {
    //发送请求，获取页面内容
    $.ajax({
        url : "/inventoryList/loadCommodityMatching.do",
        type : "post",
        //traditional : true,
        data : {
            "nowPage" : nowPage, // int
            "keywords" : keywords, //String
            "warehouseId" : warehouseId,	//bigint
            "stocklocationId" : stocklocationId	//bigint
        },
        dataType : "json",
        success : function(result) {
            //接受栏目集合
            var map = result.data
            //创建页面
            createCommodityMatching(map);
        },
        error : function() {
            showMessage("加载云仓商品配对失败");
        }
    });
}

/**
 * 2.1.创建页面
 * @param map
 * @returns
 * @author 赵滨
 */
function createCommodityMatching(map) {
    //加载最大页数
    max_page = map.maxPage;

    //清空内容
    $("#commodityMatchingTableParent").children("table").remove();
    //清空页码
    $("#commodityMatchingTableParent").find(".pagelist").parent().remove();

    //定义表内商品关联码 用来判断是否需要分块，相同关联码在一个模块内
    var lastBindId = "";

    //获取 云仓商品配对列表
    var listLocationItemStockAndItem = map.listLocationItemStockAndItem;
    if (listLocationItemStockAndItem.length > 0) {
        //遍历 云仓商品配对列表
        for (var i = 0; i < listLocationItemStockAndItem.length; i++) {
            var locationItemStockAndItem = listLocationItemStockAndItem[i];
            var tb = '';
            //获取关联码
            var bindId = locationItemStockAndItem.bindId;
            //如果是新的关联码，创建新模块
            if (lastBindId != bindId) {
                tb += '<table class="table table-hover text-center list_table widthSize">';
                //如果首次加载，先加载标题
                if (i == 0) {
                    //拼接块
                    tb += '       <tr>\n' +
                        '            <th>商品编码</th>\n' +
                        '            <th>商品名称</th>\n' +
                        '            <th>商品条码</th>\n' +
                        '            <th>商品规格</th>\n' +
                        '            <th>仓库</th>\n' +
                        '            <th>库位</th>\n' +
                        '            <th>总量</th>\n' +
                        '            <th>订单占用量</th>\n' +
                        '            <th>可用量</th>\n' +
                        '            <th>编辑</th>\n' +
                        '          </tr>';
                }
                tb += '</table>';
                $("#commodityMatchingTableParent").append(tb);
            }
            tb = '';
            tb += '<tr>';
            tb += '<td><span>'+locationItemStockAndItem.erpItemNum+'</span></td>';  //商品编码
            tb += '<td>';
            tb += '<p title="';
            tb += locationItemStockAndItem.name;    //商品名称
            tb += '">';
            tb += locationItemStockAndItem.name;    //商品名称
            tb += '</p>';
            tb += '</td>';
            tb += '<td><span>'+locationItemStockAndItem.barCode+'</span></td>';    //商品条码
            //商品规格
            tb += '<td><span>'+locationItemStockAndItem.color+' '+locationItemStockAndItem.size+'</span></td>';
            tb += '<td>';
            tb += '<p>';
            tb += locationItemStockAndItem.warehouseName;    //仓库
            tb += '</p>';
            tb += '</td>';
            tb += '<td>';
            tb += '<p>';
            tb += locationItemStockAndItem.stocklocationName;    //库位
            tb += '</p>';
            tb += '</td>';
            tb += '<td><span>'+locationItemStockAndItem.sumQuantity+'</span></td>'; //库存总量
            tb += '<td><span></span></td>'; //订单占用量
            tb += '<td><span></span></td>'; //可用量
            tb += '<td>';
            tb += '<a href="javascript:;" class="button border-red delete_bind">删除</a>';
            tb += '</td>';
            tb += '</tr>';
            //插入页面
            $("#commodityMatchingTableParent").children("table:last").append(
                $(tb).data("locationItemStockId", locationItemStockAndItem.id));
            //关联码替换
            lastBindId = bindId;
        }
        //创建页码
        createPageList();

    } else if (listLocationItemStockAndItem.length == 0) {
        //拼接块
        var tb = '<table class="table table-hover text-center list_table widthSize">\n' +
            '            <tr>\n' +
            '            <th>商品编码</th>\n' +
            '            <th>商品名称</th>\n' +
            '            <th>商品条码</th>\n' +
            '            <th>商品规格</th>\n' +
            '            <th>仓库</th>\n' +
            '            <th>库位</th>\n' +
            '            <th>总量</th>\n' +
            '            <th>订单占用量</th>\n' +
            '            <th>可用量</th>\n' +
            '            <th>编辑</th>\n' +
            '            </tr>\n' +
            '            </table>';
        //插入页面
        $("#commodityMatchingTableParent").append(tb);
    }

    //创建页码
    function createPageList() {
        //第三部分
        var tr = '';
        //开始部分
        tr += '<div>';
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
        tr += '</div>';
        //加入页面
        $("#commodityMatchingTableParent").append(tr);
    }
}

/**
 * 1.加载仓库和库位
 * @author 赵滨
 */
function loadWarehouseAndStocklocation() {
    //发送请求，获取页面内容
    $.ajax({
        url : "/inventoryList/loadWarehouseAndStocklocation.do",
        type : "post",
        //traditional : true,
        data : {},
        dataType : "json",
        success : function(result) {
            //接受栏目集合
            var map = result.data
            //创建仓库和库位
            createWarehouseAndStocklocation(map);
        },
        error : function() {
            showMessage("加载仓库和库位失败");
        }
    });
}

/**
 * 1.1.创建仓库和库位
 * @param map
 * @author 赵滨
 */
function createWarehouseAndStocklocation(map) {
    //获取仓库集合
    var listWarehouseInfo = map.listWarehouseInfo;

    //创建需要插入的仓库
    var selectWarehouseInfoList = [
        "selectWarehouseInfo", "selectWarehouseInfo_top", "selectWarehouseInfo_bottom"
    ];
    for (var i = 0; i < selectWarehouseInfoList.length; i++) {
        var selectWarehouseInfo = selectWarehouseInfoList[i];
        //清空页面仓库
        $("#"+selectWarehouseInfo+"").children().remove();
        //插入全部仓库
        $("#"+selectWarehouseInfo+"").append('<option value="">全部仓库</option>');
        //遍历仓库集合，插入仓库
        for (var j= 0; j < listWarehouseInfo.length; j++) {
            var warehouseInfo = listWarehouseInfo[j];
            var op = '<option value="'+warehouseInfo.id+'">'+warehouseInfo.warehouseName+'</option>';
            $("#"+selectWarehouseInfo+"").append($(op).data("warehouseInfo", warehouseInfo));
        }
    }

    //创建需要插入的库位
    var selectStocklocationInfoList = [
        "selectStocklocationInfo", "selectStocklocationInfo_top", "selectStocklocationInfo_bottom"
    ];
    for (var i = 0; i < selectStocklocationInfoList.length; i++) {
        var selectStocklocationInfo = selectStocklocationInfoList[i];
        //获取库位集合
        var listStocklocationInfo = map.listStocklocationInfo;
        //清空页面库位
        $("#"+selectStocklocationInfo+"").children().remove();
        //插入全部库位
        $("#"+selectStocklocationInfo+"").append('<option value="">全部库位</option>');

        //遍历库位集合，插入库位
        for (var j = 0; j < listStocklocationInfo.length; j++) {
            var stocklocationInfo = listStocklocationInfo[j];
            var op = '<option value="'+stocklocationInfo.id+'">'+stocklocationInfo.name+'</option>';
            $("#"+selectStocklocationInfo+"").append($(op).data("stocklocationInfo", stocklocationInfo));
            //如果是首次加载
            if (i == 0) {
                //保存到全局变量中
                stocklocation_info_list.push(stocklocationInfo);
            }
        }
    }
}