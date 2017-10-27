package com.rongcheng.erp.service.printTemplate;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.rongcheng.erp.dao.ZB_FieldCoordinateDAO;
import com.rongcheng.erp.entity.FieldCoordinate;
import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.ZB_PrintDAO;
import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.PrintTemplate;

/**
 * 打印模块 业务层 实现类
 * @author 赵滨
 */
@Service
public class ZB_PrintServiceImpl implements ZB_PrintService {

    @Resource
    private ZB_PrintDAO printDAO;

    @Resource
    private ZB_FieldCoordinateDAO fieldCoordinateDAO;

    /**
     * 获取订单信息
     * @param orderInfoIds 订单ID数组
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public Map<String, Object> listOrderInfoPrintByIds(BigInteger[] orderInfoIds, BigInteger ownerId) {
        //创建查找参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderInfoIds", orderInfoIds);
        params.put("ownerId", ownerId);
        
        //创建返回参数
        Map<String, Object> map = new HashMap<String, Object>();
        
        //查找订单
        List<OrderInfo> listOrderInfo = printDAO.listOrderInfoByIds(params);
        map.put("listOrderInfo", listOrderInfo);
        
        //获取快递公司
        BigInteger carrierId = listOrderInfo.get(0).getCarrierId();
        
        //查找快递模版
        List<PrintTemplate> listPrintTemplateCarrier  = 
                printDAO.listPrintTemplateByCarrierId(carrierId, null, ownerId);
        map.put("listPrintTemplateCarrier", listPrintTemplateCarrier);
        
        //查找发货模版
        List<PrintTemplate> listPrintTemplate  = 
                printDAO.listPrintTemplateByCarrierId(new BigInteger("0"), null, ownerId);
        map.put("listPrintTemplate", listPrintTemplate);
        
        //创建买家ID
        BigInteger[] buyerInfoIds = new BigInteger[listOrderInfo.size()];
        
        //遍历订单
        for (int i = 0; i < listOrderInfo.size(); i++) {
            //获取买家ID
            buyerInfoIds[i] = listOrderInfo.get(i).getPlatformBuyerId();
        }
        
        //重新定义查找参数
        params = new HashMap<String, Object>();
        params.put("buyerInfoIds", buyerInfoIds);
        params.put("ownerId", ownerId);
        
        //查找买家信息
        List<BuyerInfo> listBuyerInfo = printDAO.listBuyerInfoByIds(params);
        map.put("listBuyerInfo", listBuyerInfo);
        
        //返回
        return map;
    }

    /**
     * 添加或修改订单的快递单号
     * @param orderInfoId 订单id
     * @param trackingNum 快递单号
     * @param ownerId   主账号id
     * @return OrderInfo 订单
     * @author 赵滨
     */
    public OrderInfo addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, BigInteger ownerId) {
        
        //查询订单
        OrderInfo orderInfo = printDAO.getOrderInfoById(orderInfoId, null, ownerId);
        //如果存在
        if (orderInfo != null) {
            //更新快递单号
            orderInfo.setTrackingNum(trackingNum);
            
            //更新修改时间
            orderInfo.setGmtModified(new Timestamp(System.currentTimeMillis()));
            
            //保存
            int row = printDAO.updateOrderInfo(orderInfo);
        }
        return orderInfo;
    }

    /**
     * 查找字段坐标 根据模版id
     * @param templateId 单据模版id
     * @param ownerId 主账号id
     * @return List<FieldCoordinate> 字段坐标集合
     * @author 赵滨
     */
    public List<FieldCoordinate> listFieldCoordinateByTemplateId(BigInteger templateId, BigInteger ownerId) {
        List<FieldCoordinate> fieldCoordinates =
                fieldCoordinateDAO.listFieldCoordinateByTemplateId(templateId, null, ownerId);
        System.out.println(fieldCoordinates);
        return fieldCoordinates;
    }

    /**
     * 打印完成
     * @param orderInfoIds 订单ID数组
     * @param template 模版
     * @return
     * @author 赵滨
     */
    public int printComplete(BigInteger[] orderInfoIds, String template, BigInteger ownerId) {

        //创建时间
        Timestamp time = new Timestamp(System.currentTimeMillis());

        //创建查找参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderInfoIds", orderInfoIds);
        params.put("ownerId", ownerId);

        //查找订单
        List<OrderInfo> listOrderInfo = printDAO.listOrderInfoByIds(params);

        //返回更新条数
        int row = 0;

        //遍历订单ID
        for (int i = 0; i < listOrderInfo.size(); i++) {
            //获取订单
            OrderInfo orderInfo = listOrderInfo.get(i);

            //如果是电子面单
            if ("express".equals(template)) {
                //设置电子面单打印时间
                orderInfo.setExpressSheetPrint(time);
                //设置订单状态类型
                if (orderInfo.getSaleBillPrint() == null) {
                    orderInfo.setOrderStatus(4);
                }
                //设置修改时间
                orderInfo.setGmtModified(time);

            //如果是发货单
            } else if ("invoice".equals(template)) {
                //设置电子面单打印时间
                orderInfo.setSaleBillPrint(time);
                //设置订单状态类型
                orderInfo.setOrderStatus(5);
                //设置修改时间
                orderInfo.setGmtModified(time);
            }
            //更新订单
            row += printDAO.updateOrderInfo(orderInfo);
        }
        return row;
    }
}
