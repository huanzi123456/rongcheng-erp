package com.rongcheng.erp.controller;

import com.rongcheng.erp.entity.CarrierInfo;
import com.rongcheng.erp.service.carrierInfo.ZB_CarrierInfoService;
import com.rongcheng.erp.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/carrierInfo/loadCarrierInfo.do")
    public JsonResult loadCarrierInfo() {
        //获取快递信息
        List<CarrierInfo> list = carrierInfoService.listCarrierInfoAll();
        return new JsonResult(list);
    }
}
