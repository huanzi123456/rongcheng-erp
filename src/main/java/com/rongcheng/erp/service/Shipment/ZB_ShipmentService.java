package com.rongcheng.erp.service.Shipment;

import java.math.BigInteger;
import java.util.Map;

/**
 * 验货发货 业务层
 * @author 赵滨
 */
public interface ZB_ShipmentService {
    /**
     * 根据单号返回验货发货信息
     * @param oddNumbers 单号
     * @param ownerId 主账号ID
     * @return map 验货发货信息
     * @author 赵滨
     */
    Map<String, Object> shipmentInspectionOddNumbers(String oddNumbers, BigInteger ownerId);
    
    /**
     * 根据订单ID进行发货
     * @param oddNumbers 单号
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int shipmentInspectionDeliverGoods(String oddNumbers, BigInteger ownerId);
}
