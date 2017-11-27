package com.rongcheng.erp.service.printTemplate;

import com.rongcheng.erp.entity.FieldCoordinate;
import com.rongcheng.erp.entity.OrderInfo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 打印模块 业务层
 * @author 赵滨
 */
public interface ZB_PrintService {
    
    /**
     * 获取订单信息
     * @param orderInfoIds 订单ID数组
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> listOrderInfoPrintByIds(BigInteger[] orderInfoIds, BigInteger ownerId);
    
    /**
     * 添加或修改订单的快递单号
     * @param orderInfoId 订单id
     * @param trackingNum 快递单号
     * @param ownerId   主账号id
     * @return OrderInfo 订单
     * @author 赵滨
     */
    OrderInfo addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, BigInteger ownerId);

    /**
     * 查找字段坐标 根据模版id
     * @param templateId 单据模版id
     * @param ownerId 主账号id
     * @return List<FieldCoordinate> 字段坐标集合
     * @author 赵滨
     */
    List<FieldCoordinate> listFieldCoordinateByTemplateId(BigInteger templateId, BigInteger ownerId);

    /**
     * 打印完成
     * @param orderInfoIds 订单ID数组
     * @param template 模版
     * @return
     * @author 赵滨
     */
    int printComplete(BigInteger[] orderInfoIds, String template, BigInteger ownerId);
}
