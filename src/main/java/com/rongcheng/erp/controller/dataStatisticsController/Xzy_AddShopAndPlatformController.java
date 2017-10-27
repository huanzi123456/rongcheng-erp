package com.rongcheng.erp.controller.dataStatisticsController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rongcheng.erp.dto.XzyDataJsonResult;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.DataStatisticsService.Xzy_AddShopAndPlatformService;
/**
 * 数据统计页面添加店铺和平台的下拉选
 * @author 薛宗艳
 *
 */
@Controller
@RequestMapping("/find")
public class Xzy_AddShopAndPlatformController {
    @Resource
    Xzy_AddShopAndPlatformService service;
    @RequestMapping("/shopAndPlatform.do")
    @ResponseBody
    public XzyDataJsonResult addInfo(String dual,HttpServletRequest request){
    	HttpSession session = request.getSession();
    	UserInfo user=(UserInfo)session.getAttribute("user");    	
    	String accountNum = user.getAccountNum();
    	XzyDataJsonResult result = service.addShopAndPlatFormat(accountNum);
    	return result;
    }
}
