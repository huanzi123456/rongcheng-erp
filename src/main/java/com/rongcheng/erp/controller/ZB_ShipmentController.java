package com.rongcheng.erp.controller;

import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.exception.OrderOutNumberException;
import com.rongcheng.erp.service.Shipment.ZB_ShipmentService;
import com.rongcheng.erp.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Map;

/**
 * 验货发货 控制层
 * 
 * @author 赵滨
 */
@Controller
public class ZB_ShipmentController {
    @Resource
    private ZB_ShipmentService shipmentService;
    
    @ExceptionHandler(OrderOutNumberException.class)
    @ResponseBody
    public JsonResult orderOutNumberException(OrderOutNumberException e){
        e.printStackTrace();
        return new JsonResult(5,e);
    }
    
    /**
     * 跳转 验货发货 页面
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/shipmentInspection.do")
    public String shipmentInspection() {
        return "shipment/shipmentInspection";
    }
    
    /**
     * 根据单号返回验货发货信息
     * @param oddNumbers 单号
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/shipment/shipmentInspectionOddNumbers.do")
    public JsonResult shipmentInspectionOddNumbers(String oddNumbers, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();

        //获取验货发货信息
        Map<String, Object> map = shipmentService.shipmentInspectionOddNumbers(oddNumbers, ownerId);
        
        return new JsonResult(map);
    }
    
    /**
     * 根据订单ID进行发货
     * @param oddNumbers 单号
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/shipment/shipmentInspectionDeliverGoods.do")
    public JsonResult shipmentInspectionDeliverGoods(String oddNumbers, HttpSession session){
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();

        //发货
        int row = shipmentService.shipmentInspectionDeliverGoods(oddNumbers, ownerId);
        
        return new JsonResult(row);
    }
}
