var now_page = 1;   //0.全局变量，当前页面
var max_page = 1;   //0.全局变量，最大页面
var key_words = ""; //0.全局变量，关键字
var warehouse_id = "";  //0.全局变量，仓库ID
var stocklocation_id = "";  //0.全局变量，库位ID

$(function() {
    //1.加载仓库和库位
    loadWarehouseAndStocklocation();

    //2.加载页面
    loadCommodityMatching(now_page, key_words, warehouse_id, stocklocation_id);
})

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
                    tb += '<table class="table table-hover text-center list_table widthSize">\n' +
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
                }
                tb += '</table>';
                $("#commodityMatchingTableParent").append(tb);
            }
            tb = '';
            tb += '<tr>';
            tb += '<td><span>'+locationItemStockAndItem.erpItemNum+'</span></td>';  //商品编码
            tb += '<td>';
            tb += '<p>';
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
            tb += '<a href="javascript:;" class="button border-red">删除</a>';
            tb += '</td>';
            tb += '</tr>';
            //插入页面
            $("#commodityMatchingTableParent").children("table:last").append(
                $(tb).data("locationItemStockId", locationItemStockAndItem.id));
            //关联码替换
            lastBindId = bindId;
        }

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
    tr += '<a href="javascript:void(0)">下一页</a><a href="javascript:void(0)">尾页</a></div>';
    tr += '</div>';
    //加入页面
    $("#commodityMatchingTableParent").append(tr);
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
    //清空页面仓库
    $("#selectWarehouseInfo").children().remove();
    //插入全部仓库
    $("#selectWarehouseInfo").append('<option value="">全部仓库</option>');
    //遍历仓库集合，插入仓库
    for (var i = 0; i < listWarehouseInfo.length; i++) {
        var warehouseInfo = listWarehouseInfo[i];
        var op = '<option value="'+warehouseInfo.id+'">'+warehouseInfo.warehouseName+'</option>';
        $("#selectWarehouseInfo").append($(op).data("warehouseInfo", warehouseInfo));
    }

    //获取库位集合
    var listStocklocationInfo = map.listStocklocationInfo;
    //清空页面库位
    $("#selectStocklocationInfo").children().remove();
    //插入全部库位
    $("#selectStocklocationInfo").append('<option value="">全部库位</option>');
    //遍历库位集合，插入库位
    for (var i = 0; i < listStocklocationInfo.length; i++) {
        var stocklocationInfo = listStocklocationInfo[i];
        var op = '<option value="'+stocklocationInfo.id+'">'+stocklocationInfo.name+'</option>';
        $("#selectStocklocationInfo").append($(op).data("stocklocationInfo", stocklocationInfo));
    }
}