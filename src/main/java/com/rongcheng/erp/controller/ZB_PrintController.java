package com.rongcheng.erp.controller;

import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.printTemplate.ZB_PrintService;
import com.rongcheng.erp.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;


/**
 * 打印模块 控制层
 * @author 赵滨
 */
@Controller
public class ZB_PrintController {
    
    @Resource
    private ZB_PrintService printService;
    
    /**
     * 获取订单信息
     * @param orderInfoIds 订单ID数组
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/print/listOrderInfoPrintByIds.do")
    public JsonResult listOrderInfoPrintByIds(BigInteger[] orderInfoIds, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();

        //返回
        return new JsonResult(printService.listOrderInfoPrintByIds(orderInfoIds, ownerId));
    }
    
    /**
     * 添加或修改订单的快递单号
     * @param orderInfoId 订单id
     * @param trackingNum 快递单号
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/print/addOrModifyTracking.do")
    public JsonResult addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();

        OrderInfo orderInfo =
                printService.addOrModifyTracking(orderInfoId, trackingNum, ownerId);
        return new JsonResult(orderInfo);
    }

    /**
     * 获取字段坐标表
     * @param printTemplateId 模版ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/listFieldCoordinateByTemplateId.do")
    public JsonResult listFieldCoordinateByTemplateId(BigInteger printTemplateId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();

        //返回
        return new JsonResult(printService.listFieldCoordinateByTemplateId(printTemplateId, ownerId));
    }

    /**
     * 打印完成
     * @param orderInfoIds 订单ID数组
     * @param template 模版
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/print/printComplete.do")
    public JsonResult printComplete(BigInteger[] orderInfoIds, String template, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();

        //返回
        return new JsonResult(printService.printComplete(orderInfoIds, template, ownerId));
    }
}
