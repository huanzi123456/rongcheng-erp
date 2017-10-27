package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;
import com.rongcheng.erp.dto.XzyOrderInfo;

/**
 * 
 * @author 薛宗艳
 * 建立与物流软件的链接
 */
public interface Xzy_ReadOrderInfoDao {
	/*
	 * 读取订单信息表中的信息:订单id,订单编号platformOrderId,快递名称carrierId,快递编号trackingNum
	 */
	List<XzyOrderInfo> selectOrderInfo(BigInteger id);
}
