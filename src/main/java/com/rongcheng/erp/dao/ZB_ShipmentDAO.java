package com.rongcheng.erp.dao;

import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.AddressCarrierAllocation;
import com.rongcheng.erp.entity.OrderInfo;

/**
 * 验货发货 数据访问层
 * 
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
    List<Map<String, Object>> listShipmentItemByOrderNumTrackingNum(String num, Long ownerId);
    
    
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
    Map<String, Object> getShipmentByOrderNumTrackingNum(String num, Long ownerId);
    
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
    OrderInfo getOrderInfoByOrderNumTrackingNum(String num, Long ownerId, int start);
    
    /**
     * 获取全国地址+快递分配信息表
     * 
     * <p>根据地区ID
     * 
     * @param code 地区code
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    AddressCarrierAllocation getAddressCarrierAllocationById(Long code, Long ownerId);
}
