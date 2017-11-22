package com.rongcheng.erp.controller;

import java.math.BigInteger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.DataStatisticsService.Xzy_DataStatisticsService;
/**
 * 数据统计页面的Controller层
 * @author 薛宗艳
 *
 */
@Controller
@RequestMapping("/find")
public class Xzy_DataStatisticsController {
	@Resource
	Xzy_DataStatisticsService service;
	
	/**
	 * 数据统计页面的添加店铺信息和平台信息 
	 * @param request
	 * @return
	 */
    @RequestMapping("/shopAndPlatformInfo.do")
    @ResponseBody
    public XzyJsonResult addInfo(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	UserInfo user=(UserInfo)session.getAttribute("user");    	
    	BigInteger ownerId = user.getOwnerId();
    	XzyJsonResult result = service.addShopAndPlatFormat(ownerId);
    	return result;
    }
    /**
     * 关联查询
     * @param day           订单创建时间(1,3,7,30)
     * @param pay           订单付款时间(1,3,7,30)
     * @param shopId        店铺id
     * @param platformId    平台id
     * @param time1         订单创建时间的第一个日历选框
     * @param time2         订单创建时间的第二个日历选框
     * @param time3         订单付款时间的第一个日历选框
     * @param time4         订单付款时间的第二个日历选框
     * @param orderStatus   订单原始状态(全部-1,已取消9,已发货7,待发货5)
     * @param code          商品编码 对应item_common_info中的erp_item_num字段
     * @param owner_id      用户主账户id
     * @return 
     */
    @RequestMapping("/relationQuery.do")
    @ResponseBody
    public XzyJsonResult dataSelect(Integer day,Integer pay,BigInteger shopId,BigInteger platformId,
    		String time1,String time2,String time3,String time4,Integer orderStatus,
    		String code,BigInteger ownerId){  	
    	XzyJsonResult result = service.selectOtherInfos(day, pay, shopId, platformId, time1, time2, time3, time4, orderStatus, code, ownerId);
        return result;
    }  
}