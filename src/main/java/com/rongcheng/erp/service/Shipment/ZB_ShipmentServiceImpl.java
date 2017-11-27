package com.rongcheng.erp.service.Shipment;

import com.rongcheng.erp.dao.ZB_PrintDAO;
import com.rongcheng.erp.dao.ZB_ShipmentDAO;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.exception.OrderOutNumberException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 验货发货 业务层Impl
 * @author 赵滨
 *
 */
@Service("shipmentService")
public class ZB_ShipmentServiceImpl implements ZB_ShipmentService {

    @Resource
    private ZB_ShipmentDAO shipmentDAO;
    
    @Resource
    private ZB_PrintDAO printDAO;
    
    /**
     * 根据单号返回验货发货信息
     * @param oddNumbers 单号
     * @param ownerId 主账号ID
     * @return map 验货发货信息
     * @author 赵滨
     */
    public Map<String, Object> shipmentInspectionOddNumbers(String oddNumbers, BigInteger ownerId)
            throws OrderOutNumberException{
        //0.创建验货发货集合map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //1.获取订单信息
        OrderInfo orderInfo = shipmentDAO.getOrderInfoByOrderNumTrackingNum(oddNumbers, ownerId, 1);
        
        //如果存在多条
        if (orderInfo != null) {
            throw new OrderOutNumberException("该单号有误，存在多条订单");
        }
        
        //如果正常，查单条内容
        orderInfo = shipmentDAO.getOrderInfoByOrderNumTrackingNum(oddNumbers, ownerId, 0);
        map.put("orderInfo", orderInfo);
        
        //2.获取订单下的商品
        List<Map<String, Object>> listMapItem = shipmentDAO.listShipmentItemByOrderNumTrackingNum(oddNumbers, ownerId);
        map.put("listMapItem", listMapItem);
        
        //3.获取订单验货信息(订单信息和买家信息)
        Map<String, Object> mapOrder =  shipmentDAO.getShipmentByOrderNumTrackingNum(oddNumbers, ownerId);
        map.put("mapOrder", mapOrder);

        return map;
    }

    /**
     * 根据订单ID进行发货
     * @param oddNumbers 单号
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int shipmentInspectionDeliverGoods(String oddNumbers, BigInteger ownerId) {
        //根据单号查找订单
        OrderInfo orderInfo = shipmentDAO.getOrderInfoByOrderNumTrackingNum(oddNumbers, ownerId, 0);
        
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        //设置发货时间
        orderInfo.setOrderDeparture(time);
        
        //设置订单状态类型      7已发货
        orderInfo.setOrderStatus(7);
           
        //修改订单
        int row = printDAO.updateOrderInfo(orderInfo);
        
        return row;
    }
}
