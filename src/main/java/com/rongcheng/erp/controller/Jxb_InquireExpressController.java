package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.PrintTemplate;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.order.Jxb_OrderService;
import com.rongcheng.erp.service.printTemplate.PrintTemplateService;
import com.rongcheng.erp.utils.JsonResult;
import com.rongcheng.erp.utils.KdniaoTrackQueryAPI;

import net.sf.json.JSONObject;

/**
 * 
 * @author jiml
 *
 */
@Controller
public class Jxb_InquireExpressController {
	@Resource
	private Jxb_OrderService jxb_OrderService;
	public static String[] exp = { "", // 填充0下标
			"SF", "ZTO", "STO", "YTO", "YD", "EMS", "BTWL", "ZJS", "", // 京东快递，无
			"HHTT", "DBL", "DBL", // 德邦物流，无
			"YZPY", "", // EMS经济快递，无
			"BTWL", "LB", "SURE", "QFKD", "GTO", "FAST", "UC", "", // 中国邮政，无
			"ANE", ""// 佳龙快运，无
	};

	/**
	 * 查询运单详情
	 * 
	 * @param request
	 * @return 正常返回物流信息 json
	 * @throws Exception
	 */
	@RequestMapping("/inquireExpress.do")
	@ResponseBody
	public Object inquireExpress(int carrierId, String expNo) throws Exception {
		String json = KdniaoTrackQueryAPI.getOrderTracesByJson(exp[carrierId], expNo);
		return JSONObject.fromObject(json);
	}

	/**
	 * 刷新运单状态
	 * 
	 * @param request
	 * @return 正常返回物流信息 json，失败返回-1
	 * @throws Exception
	 */
	@RequestMapping("/refreshExpress.do")
	@ResponseBody
	public Object refreshExpress(BigInteger[] orderId, int carrierId, String expNo, int status, HttpSession session) {
		BigInteger ownerId = ((UserInfo)(session.getAttribute("user"))).getOwnerId();
		try {
			Long start = System.currentTimeMillis();
			String json = KdniaoTrackQueryAPI.getOrderTracesByJson(exp[carrierId], expNo);
			System.out.println("查快递用时：" + (System.currentTimeMillis() - start));
			JSONObject jsonObject = JSONObject.fromObject(json);
			// 物流状态：2-在途中,3-签收,4-问题件
			int state = new Integer(jsonObject.get("State").toString());
			OrderInfo o = new OrderInfo();
			o.setOwnerId(ownerId);
			if ((status == 7 || status == 8) && state > 2) {
				// 更新数据库订单状态码
				if (state == 3) {
					o.setOrderStatus(9);
					jxb_OrderService.updateByIdSelective(orderId, o);
				} else if (status == 7 && state == 4) {
					o.setOrderStatus(8);
					jxb_OrderService.updateByIdSelective(orderId, o);
				}
			}
			return JSONObject.fromObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	@Resource
	private PrintTemplateService lsxPrintTemplateService;
	@RequestMapping("/findtemplateId.do")
	@ResponseBody
	public JsonResult findtemplateType(BigInteger carrierId){
		List<PrintTemplate> pt=lsxPrintTemplateService.findtemplateType(carrierId);
	return new JsonResult(pt);
	}
}
