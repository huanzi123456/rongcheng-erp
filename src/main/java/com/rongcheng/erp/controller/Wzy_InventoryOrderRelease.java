package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.exception.OrderOutNumberException;
import com.rongcheng.erp.service.wzy_orderReleaseService.OrderReleaseService;
import com.rongcheng.erp.utils.JsonResult;


@Controller
public class Wzy_InventoryOrderRelease {
    
    @Resource
    private OrderReleaseService service;
    private Integer row = 2;
    
    //1.加载网页
    @RequestMapping("/getInventoryOrderRelease.do")
    public String toOrderRelease() {
        return "inventory/order_release";
    }
    
    //2.进行页面布置
    @RequestMapping("/loadOrderRelease.do")
    @ResponseBody
    public JsonResult loadOrderRelease(Integer nowPage, HttpSession session) {
        if(nowPage==null) {
            throw new RuntimeException("获取信息失败");
        }
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        Map<String,Object> map = service.findAllWarehouseByOwnerId(ownerId, nowPage, row);
        return new JsonResult(map);
    }
    
    //3.覆盖范围
    @RequestMapping("/getCoverArea.do")
    @ResponseBody
    public JsonResult findAddressCarrierAllocation(BigInteger warehouseId, BigInteger stocklocationId, String id, HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        Map<String,Object> map = service.findAddressCarrierAllocation(warehouseId, stocklocationId, ownerId);
        return new JsonResult(map);
    }
    
    //4.对于仓库的覆盖地址进行更新
    @RequestMapping("/updateCoverArea.do")
    @ResponseBody
    public JsonResult updateCoverArea(
            BigInteger stocklocationId, BigInteger warehouseId, String insertList, String deleteList, HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        service.updateWarehouseCoverArea(insertList, deleteList, warehouseId, stocklocationId, ownerId);
        return new JsonResult(0, null, "数据更新成功");
    }
    
    //异常处理
    @ExceptionHandler(OrderOutNumberException.class)
    @ResponseBody
    public JsonResult orderOutNumberException(OrderOutNumberException e){
        e.printStackTrace();
        return new JsonResult(5,e);
    }
}
