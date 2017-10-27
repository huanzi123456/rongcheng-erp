
//获取订单集合 orderInfoList[i]

//获取买家集合 buyerInfoList[i]

//获取字段坐标集合 fieldCoordinateList[j]

//获取订单商品集合 listItemCommonInfoList[i]

//获取打印模版 printTemplate[i]

//获取店铺集合 shopInfoList[i]

//获取发件人地区 consignorRegion

//获取收件人地区 consigneeRegion

//定义模版的列表，用json的格式表述，在读取是便于优化
var templateJson = {

    express : [
        {
            ChineseName : "发件人姓名",
            EnglishName : "consignor_name",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactName)?'':shopInfoList[i].contactName)"
        },
        {
            ChineseName : "发件人_省",
            EnglishName : "consignor_province",
            ParameterName : "($.isEmptyObject(consignorRegion.province)?'':consignorRegion.province)"
        },
        {
            ChineseName : "发件人_市",
            EnglishName : "consignor_city",
            ParameterName : "($.isEmptyObject(consignorRegion.city)?'':consignorRegion.city)"
        },
        {
            ChineseName : "发件人_区",
            EnglishName : "consignor_district",
            ParameterName : "($.isEmptyObject(consignorRegion.area)?'':consignorRegion.area)"
        },
        {
            ChineseName : "发件人_完整地址",
            EnglishName : "consignor_fulladdress",
            ParameterName : "($.isEmptyObject(consignorRegion.province)?'':consignorRegion.province)+" +
                            "($.isEmptyObject(consignorRegion.city)?'':consignorRegion.city)+" +
                            "($.isEmptyObject(consignorRegion.area)?'':consignorRegion.area)+" +
                            "($.isEmptyObject(shopInfoList[i].userAddress)?'':shopInfoList[i].userAddress)"
        },
        {
            ChineseName : "发件人_电话",
            EnglishName : "consignor_tel",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactMobile)?'':shopInfoList[i].contactMobile)"
        },
        {
            ChineseName : "发件人_手机",
            EnglishName : "consignor_mobile",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactTel)?'':shopInfoList[i].contactTel)"
        },
        {
            ChineseName : "发件人_手机/电话",
            EnglishName : "consignor_bothphone",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactMobile)?'':shopInfoList[i].contactMobile)+'/'+" +
                            "($.isEmptyObject(shopInfoList[i].contactTel)?'':shopInfoList[i].contactTel)"
        },
        {
            ChineseName : "卖家店铺名称",
            EnglishName : "seller_shopname",
            ParameterName : "($.isEmptyObject(shopInfoList[i].name)?'':shopInfoList[i].name)"
        },
        {
            ChineseName : "卖家_ID",
            EnglishName : "seller_id",
            ParameterName : "($.isEmptyObject(shopInfoList[i].sellerAccount)?'':shopInfoList[i].sellerAccount)"
        },
        {
            ChineseName : "物流单号",
            EnglishName : "tracking_num",
            ParameterName : "($.isEmptyObject(orderInfoList[i].trackingNum)?'':orderInfoList[i].trackingNum)"
        },
        {
            ChineseName : "收件人姓名",
            EnglishName : "consignee_name",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].buyerName)?'':buyerInfoList[i].buyerName)"
        },
        {
            ChineseName : "收件人_ID",
            EnglishName : "consignee_id",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].buyerAccount)?'':buyerInfoList[i].buyerAccount)"
        },
        {
            ChineseName : "收件人_省",
            EnglishName : "consignee_province",
            ParameterName : "($.isEmptyObject(consigneeRegion.province)?'':consigneeRegion.province)"
        },
        {
            ChineseName : "收件人_市",
            EnglishName : "consignee_city",
            ParameterName : "($.isEmptyObject(consigneeRegion.city)?'':consigneeRegion.city)"
        },
        {
            ChineseName : "收件人_区",
            EnglishName : "consignee_district",
            ParameterName : "($.isEmptyObject(consigneeRegion.area)?'':consigneeRegion.area)"
        },
        {
            ChineseName : "收件人_完整地址",
            EnglishName : "consignee_fulladdress",
            ParameterName : "($.isEmptyObject(consigneeRegion.province)?'':consigneeRegion.province)+" +
                            "($.isEmptyObject(consigneeRegion.city)?'':consigneeRegion.city)+" +
                            "($.isEmptyObject(consigneeRegion.area)?'':consigneeRegion.area)+" +
                            "($.isEmptyObject(buyerInfoList[i].userAddress)?'':buyerInfoList[i].userAddress)"
        },
        {
            ChineseName : "收件人_电话",
            EnglishName : "consignee_tel",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].consigneeTel)?'':buyerInfoList[i].consigneeTel)"
        },
        {
            ChineseName : "收件人_手机",
            EnglishName : "consignee_mobile",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].consigneeMobile)?'':buyerInfoList[i].consigneeMobile)"
        },
        {
            ChineseName : "收件人_手机/电话",
            EnglishName : "consignee_bothphone",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].consigneeMobile)?'':buyerInfoList[i].consigneeMobile)+'/'+" +
                            "($.isEmptyObject(buyerInfoList[i].consigneeTel)?'':buyerInfoList[i].consigneeTel)"
        },
        {
            ChineseName : "平台订单编号",
            EnglishName : "platform_orderid",
            ParameterName : "($.isEmptyObject(orderInfoList[i].platformId)?'':orderInfoList[i].platformId)"
        }
    ],

    invoice : [
        {
            ChineseName : "发件人姓名",
            EnglishName : "consignor_name",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactName)?'':shopInfoList[i].contactName)"
        },
        {
            ChineseName : "发件人_省",
            EnglishName : "consignor_province",
            ParameterName : "($.isEmptyObject(consignorRegion.province)?'':consignorRegion.province)"
        },
        {
            ChineseName : "发件人_市",
            EnglishName : "consignor_city",
            ParameterName : "($.isEmptyObject(consignorRegion.city)?'':consignorRegion.city)"
        },
        {
            ChineseName : "发件人_区",
            EnglishName : "consignor_district",
            ParameterName : "($.isEmptyObject(consignorRegion.area)?'':consignorRegion.area)"
        },
        {
            ChineseName : "发件人_完整地址",
            EnglishName : "consignor_fulladdress",
            ParameterName : "($.isEmptyObject(consignorRegion.province)?'':consignorRegion.province)+" +
            "($.isEmptyObject(consignorRegion.city)?'':consignorRegion.city)+" +
            "($.isEmptyObject(consignorRegion.area)?'':consignorRegion.area)+" +
            "($.isEmptyObject(shopInfoList[i].userAddress)?'':shopInfoList[i].userAddress)"
        },
        {
            ChineseName : "发件人_电话",
            EnglishName : "consignor_tel",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactMobile)?'':shopInfoList[i].contactMobile)"
        },
        {
            ChineseName : "发件人_手机",
            EnglishName : "consignor_mobile",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactTel)?'':shopInfoList[i].contactTel)"
        },
        {
            ChineseName : "发件人_手机/电话",
            EnglishName : "consignor_bothphone",
            ParameterName : "($.isEmptyObject(shopInfoList[i].contactMobile)?'':shopInfoList[i].contactMobile)+'/'+" +
            "($.isEmptyObject(shopInfoList[i].contactTel)?'':shopInfoList[i].contactTel)"
        },
        {
            ChineseName : "卖家店铺名称",
            EnglishName : "seller_shopname",
            ParameterName : "($.isEmptyObject(shopInfoList[i].name)?'':shopInfoList[i].name)"
        },
        {
            ChineseName : "卖家_ID",
            EnglishName : "seller_id",
            ParameterName : "($.isEmptyObject(shopInfoList[i].sellerAccount)?'':shopInfoList[i].sellerAccount)"
        },
        {
            ChineseName : "物流单号",
            EnglishName : "tracking_num",
            ParameterName : "($.isEmptyObject(orderInfoList[i].trackingNum)?'':orderInfoList[i].trackingNum)"
        },
        {
            ChineseName : "收件人姓名",
            EnglishName : "consignee_name",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].buyerName)?'':buyerInfoList[i].buyerName)"
        },
        {
            ChineseName : "收件人_ID",
            EnglishName : "consignee_id",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].buyerAccount)?'':buyerInfoList[i].buyerAccount)"
        },
        {
            ChineseName : "收件人_省",
            EnglishName : "consignee_province",
            ParameterName : "($.isEmptyObject(consigneeRegion.province)?'':consigneeRegion.province)"
        },
        {
            ChineseName : "收件人_市",
            EnglishName : "consignee_city",
            ParameterName : "($.isEmptyObject(consigneeRegion.city)?'':consigneeRegion.city)"
        },
        {
            ChineseName : "收件人_区",
            EnglishName : "consignee_district",
            ParameterName : "($.isEmptyObject(consigneeRegion.area)?'':consigneeRegion.area)"
        },
        {
            ChineseName : "收件人_完整地址",
            EnglishName : "consignee_fulladdress",
            ParameterName : "($.isEmptyObject(consigneeRegion.province)?'':consigneeRegion.province)+" +
            "($.isEmptyObject(consigneeRegion.city)?'':consigneeRegion.city)+" +
            "($.isEmptyObject(consigneeRegion.area)?'':consigneeRegion.area)+" +
            "($.isEmptyObject(buyerInfoList[i].userAddress)?'':buyerInfoList[i].userAddress)"
        },
        {
            ChineseName : "收件人_电话",
            EnglishName : "consignee_tel",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].consigneeTel)?'':buyerInfoList[i].consigneeTel)"
        },
        {
            ChineseName : "收件人_手机",
            EnglishName : "consignee_mobile",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].consigneeMobile)?'':buyerInfoList[i].consigneeMobile)"
        },
        {
            ChineseName : "收件人_手机/电话",
            EnglishName : "consignee_bothphone",
            ParameterName : "($.isEmptyObject(buyerInfoList[i].consigneeMobile)?'':buyerInfoList[i].consigneeMobile)+'/'+" +
            "($.isEmptyObject(buyerInfoList[i].consigneeTel)?'':buyerInfoList[i].consigneeTel)"
        },
        {
            ChineseName : "平台订单编号",
            EnglishName : "platform_orderid",
            ParameterName : "($.isEmptyObject(orderInfoList[i].platformId)?'':orderInfoList[i].platformId)"
        }
    ]
}

// var json = eval(testJson.options)
// for (var i = 0; i < json.length; i++) {
//     console.log(json[i].text + " " + json[i].value)
// }












