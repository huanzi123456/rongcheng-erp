package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rongcheng.erp.dto.XzyOrderInfo;
import com.rongcheng.erp.service.readOrderInfo.Xzy_ReadOrderInfoService;
/**
 * 
 * @author 薛宗艳
 * 建立与物流软件的链接
 */
@Controller
@RequestMapping("urlConnection")
public class Xzy_ReadOrderInfoController {
    @Resource
    Xzy_ReadOrderInfoService service;
    
    @RequestMapping("order.do")
    @ResponseBody
    List<XzyOrderInfo> readOrderInfo(HttpServletRequest request){	
    	String orderId = request.getParameter("orderId");
    	List<XzyOrderInfo> oi = service.urlService(new BigInteger(orderId));
    	return oi;
    }
}