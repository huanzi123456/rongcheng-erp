package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.*;

/**
 * 打印模块 数据访问层
 * @author 赵滨
 *
 */
public interface ZB_PrintDAO {
    
    /**
     * 根据ID查询订单
     * @param params 是否授权, 主账户ID, 订单ID[]
     * @return List<OrderInfo> 订单的集合
     * @author 赵滨
     */
    List<OrderInfo> listOrderInfoByIds(Map<String, Object> params);
    
    /**
     * 根据ID查询买家
     * @param params 是否授权, 主账户ID, 买家ID[]
     * @return List<BuyerInfo> 买家的集合
     * @author 赵滨
     */
    List<BuyerInfo> listBuyerInfoByIds(Map<String, Object> params);
    
    /**
     * 根据ID查询店铺
     * @param params 是否授权, 主账户ID, 店铺ID[]
     * @return List<ShopInfo> 店铺的集合
     * @author 赵滨
     */
    List<ShopInfo> listShopInfoById(Map<String, Object> params);
    
    /**
     * 根据ID查询模版
     * @param params 是否授权, 主账户ID, 模版ID[]
     * @return List<PrintTemplate> 模版的集合
     * @author 赵滨
     * 废除
     */
    /*List<PrintTemplate> listPrintTemplateById(Map<String, Object> params);*/
    
    /**
     * 根据模版ID查询坐标
     * @param templateId 模版ID
     * @param authorized 是否授权
     * @param ownerId   主账户ID
     * @return List<FieldCoordinate> 坐标的集合
     * @author 赵滨
     */
    List<FieldCoordinate> listFieldCoordinateByTemplateId(BigInteger templateId, Boolean authorized, BigInteger ownerId);
    
    /**
     * 根据订单ID查询商品
     * @param orderInfoId 订单ID
     * @param authorized  是否授权
     * @param ownerId   主账户ID
     * @return List<ItemCommonInfoOrderItemLink> 商品的集合
     * @return 赵滨
     */
    List<ItemCommonInfoOrderItemLink> listItemCommonInfoByOrderInfoId(BigInteger orderInfoId, Boolean authorized, BigInteger ownerId);
    
    /**
     * 查询模版根据快递公司id
     * @param carrierId 快递公司id
     * @param authorized 是否授权
     * @param ownerId   主账号id
     * @return List<PrintTemplate> 模版的集合
     * @author 赵滨
     */
    List<PrintTemplate> listPrintTemplateByCarrierId(BigInteger carrierId, Boolean authorized, BigInteger ownerId);
    
    /**
     * 根据ID查询单条订单
     * @param orderInfoId 订单id
     * @param authorized 是否授权
     * @param ownerId 主账号id
     * @return OrderInfo 订单
     * @author 赵滨
     */
    OrderInfo getOrderInfoById(BigInteger orderInfoId, Boolean authorized, BigInteger ownerId);
    
    /**
     * 更新订单
     * @param orderInfo 订单
     * @return row 行数
     * @author 赵滨
     */
    int updateOrderInfo(OrderInfo orderInfo);
    
    
    
    
    
    //查找快递地区
    //AddressCarrierAllocation getAddressCarrierAllocationById(BigInteger id);
    
    
    
    
}
