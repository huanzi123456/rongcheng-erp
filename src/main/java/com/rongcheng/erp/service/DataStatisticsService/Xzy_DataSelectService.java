package com.rongcheng.erp.service.DataStatisticsService;

import java.math.BigInteger;

import com.rongcheng.erp.dto.XzyDataJsonResult;
/**
 * 数据统计页面
 * @author 薛宗艳
 *
 */
public interface Xzy_DataSelectService {
	XzyDataJsonResult dataSelect(Integer day,Integer pay,BigInteger shopId,BigInteger platformId,String time1,
    		String time2,String time3,String time4,Integer orderStatus,String code,BigInteger owner_id);
}
