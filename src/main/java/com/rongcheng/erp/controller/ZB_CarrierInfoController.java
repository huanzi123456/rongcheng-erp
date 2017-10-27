package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.CarrierInfo;
import com.rongcheng.erp.service.carrierInfo.ZB_CarrierInfoService;
import com.rongcheng.erp.utils.JsonResult;

/**
 * 快递信息表 控制层
 * @author 赵滨
 *
 */
@Controller
public class ZB_CarrierInfoController {

    @Resource
    private ZB_CarrierInfoService carrierInfoService;

    /**
     * 加载快递信息表的内容
     * @param authorized 是否授权
     * @param ownerId   主账户ID
     * @param operatorId 操作人ID
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/carrierInfo/loadCarrierInfo.do")
    public JsonResult loadCarrierInfo(Boolean authorized, BigInteger ownerId, BigInteger operatorId) {
    	//获取快递信息
        List<CarrierInfo> list = carrierInfoService.listCarrierInfoAll(authorized, ownerId, operatorId);
        return new JsonResult(list);
    }
}
