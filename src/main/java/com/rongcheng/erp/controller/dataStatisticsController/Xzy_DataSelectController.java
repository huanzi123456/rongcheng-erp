package com.rongcheng.erp.controller.dataStatisticsController;

import java.math.BigInteger;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.dto.XzyDataJsonResult;
import com.rongcheng.erp.service.DataStatisticsService.Xzy_DataSelectService;
/**
 * 数据统计页面:数据查询
 * @author 薛宗艳
 */
@Controller
@RequestMapping("/data")
public class Xzy_DataSelectController {	
    @Resource 
    Xzy_DataSelectService service;
    @RequestMapping("/orderInfo.do")
    @ResponseBody
    /**
     * 
     * @param day 订单创建时间(1,3,7,30)
     * @param pay 订单付款时间(1,3,7,30)
     * @param shopId 店铺id
     * @param platformId 平台id
     * @param time1 订单创建时间的第一个日历选框
     * @param time2 订单创建时间的第二个日历选框
     * @param time3 订单付款时间的第一个日历选框
     * @param time4 订单付款时间的第二个日历选框
     * @param orderStatus 订单原始状态(全部-1,已取消9,已发货7,待发货5 临时)
     * @param code 商品编码 对应item_common_info中的erp_item_num字段
     * @param owner_id 用户主账户id
     * @return 订单统计报表,商品统计报表,销售前20三个表的内容
     */
    public XzyDataJsonResult dataSelect(Integer day,Integer pay,BigInteger shopId,BigInteger platformId,
    		String time1,String time2,String time3,String time4,Integer orderStatus,
    		String code,BigInteger owner_id){
    	XzyDataJsonResult result = null;
    	if(day==null && pay==null && shopId==null && platformId==null 
    			&& time1=="" && time2=="" && time3=="" && time4=="" 
    			&& orderStatus==null && code==""){
    		return result;
    	}else{
    		result = service.dataSelect(day, pay, shopId, platformId, time1, time2, time3, time4,
        			orderStatus, code, owner_id);
        	return result;
    	}
    }
}
