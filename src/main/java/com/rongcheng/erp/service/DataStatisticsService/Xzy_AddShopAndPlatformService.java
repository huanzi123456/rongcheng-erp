package com.rongcheng.erp.service.DataStatisticsService;

import com.rongcheng.erp.dto.XzyDataJsonResult;
/**
 * 数据统计页面的添加店铺信息和平台信息
 * @param accountNum:当前登录的用户的账号
 * @author 薛宗艳
 *
 */
public interface Xzy_AddShopAndPlatformService {
	XzyDataJsonResult addShopAndPlatFormat(String accountNum);
}
