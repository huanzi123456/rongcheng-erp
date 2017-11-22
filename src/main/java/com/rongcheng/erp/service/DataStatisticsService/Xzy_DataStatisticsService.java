package com.rongcheng.erp.service.DataStatisticsService;
import java.math.BigInteger;
import com.rongcheng.erp.dto.XzyJsonResult;
/**
 * 数据统计页面的Service层
 * @author 薛宗艳
 *
 */
public interface Xzy_DataStatisticsService {
	//数据统计页面的添加店铺信息和平台信息
	XzyJsonResult addShopAndPlatFormat(BigInteger ownerId);
	//关联查询
	XzyJsonResult selectOtherInfos(Integer day,Integer pay,BigInteger shopId,BigInteger platformId,String time1,
    		String time2,String time3,String time4,Integer orderStatus,String code,BigInteger ownerId);
}
