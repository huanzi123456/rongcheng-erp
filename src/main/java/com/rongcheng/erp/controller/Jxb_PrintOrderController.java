package com.rongcheng.erp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author jiml
 *
 */
@Controller
public class Jxb_PrintOrderController {
	// 到待打印页面
	@RequestMapping("/toPrint.do")
	public String toPrint() {
		return "audit/order-print";
	}

	// 到其他订单查询页面
	@RequestMapping("/toOtherOrderPage.do")
	public String toOtherOrderPage(int state, HttpServletRequest request) {
		// 1已打印订单，2未付款订单，3退款中订单，4线下订单，5已发货订单，6货到付款订单，7已关闭的订单，8锁定的订单
		switch (state) {
		case 1:
			request.setAttribute("expressSheetPrint", "1970-01-01");
			request.setAttribute("saleBillPrint", "1970-01-01");
			break;
		case 2:
			request.setAttribute("orderStatus", 1);
			break;
		case 3:
			request.setAttribute("refundClose", 1);
			break;
		case 4:
			request.setAttribute("onlineOrder", 0);
			break;
		case 5:// 订单状态包含7,8,9(目前仅允许填入7,8,9，且orderStatus必须为null)
			request.setAttribute("orderStatusArray", 789);
			break;
		case 6:
			request.setAttribute("orderCod", 1);
			break;
		case 7:
			request.setAttribute("refundClose", 2);
			break;
		case 8:
			request.setAttribute("orderHold", 1);
			break;
		default:
			break;
		}
		return "audit/order-other";
	}
}
