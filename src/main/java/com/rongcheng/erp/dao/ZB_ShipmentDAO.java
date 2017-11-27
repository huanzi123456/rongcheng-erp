package com.rongcheng.erp.dao;

import com.rongcheng.erp.entity.OrderInfo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 验货发货 数据访问层
 * @author 赵滨
 */
public interface ZB_ShipmentDAO {
    /**
     * 获取订单下的商品
     * 
     * <p>根据订单erp订单编号 或者 快递单号
     * 
     * @param num 订单erp订单编号 或者 快递单号
     * @param ownerId 主账号ID
     * @return list 订单下的商品
     * @author 赵滨
     */
    List<Map<String, Object>> listShipmentItemByOrderNumTrackingNum(String num, BigInteger ownerId);

    /**
     * 获取订单验货信息(订单信息和买家信息)
     * 
     * <p>根据订单erp订单编号 或者 快递单号
     * 
     * @param num 订单erp订单编号 或者 快递单号
     * @param ownerId 主账号ID
     * @return map 订单验货信息
     * @author 赵滨
     */
    Map<String, Object> getShipmentByOrderNumTrackingNum(String num, BigInteger ownerId);
    
    /**
     * 获取订单信息
     * 
     * <p>根据订单erp订单编号 或者 快递单号
     * 
     * @param num 订单erp订单编号 或者 快递单号
     * @param ownerId 主账号ID
     * @param start 开始处
     * @return OrderInfo 订单信息
     * @author 赵滨
     */
    OrderInfo getOrderInfoByOrderNumTrackingNum(String num, BigInteger ownerId, int start);
}
