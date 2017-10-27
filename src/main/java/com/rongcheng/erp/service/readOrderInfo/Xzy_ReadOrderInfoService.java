package com.rongcheng.erp.service.readOrderInfo;

import java.math.BigInteger;
import java.util.List;
import com.rongcheng.erp.dto.XzyOrderInfo;
/**
 * 
 * @author 薛宗艳
 *
 */
public interface Xzy_ReadOrderInfoService {
	/**
	 * 
	 * @return 订单信息
	 */
    List<XzyOrderInfo> urlService(BigInteger orderId);
}
