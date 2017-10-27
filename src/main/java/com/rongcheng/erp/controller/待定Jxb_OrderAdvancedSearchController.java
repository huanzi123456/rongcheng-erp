package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.dto.OrderItemInfo_S;
import com.rongcheng.erp.service.order.待定Jxb_OrderAdvancedSearchService;
import com.rongcheng.erp.utils.JsonResult;

/**
 * List<OrderItemInfo_S>{ 订单信息。。。 List<ItemCommonWarehouse_S>{ 商品信息。。。
 * WarehouseInfo } }
 * 
 * @author jxb
 *
 */

@Controller
@RequestMapping("/audit")
public class 待定Jxb_OrderAdvancedSearchController {
	// 分页相关（每页多少条）
	@Value("#{config['rows']}")
	private int rows;
	BigInteger ownerId = new BigInteger("1");// 主账号id

	@Resource
	private 待定Jxb_OrderAdvancedSearchService jxb_OrderAdvancedSearchService;

	/**
	 * 订单审核页面条件查询订单基本信息。所有参数均可为null。
	 * 
	 * @author jxb
	 *
	 * @return
	 * @throws InterruptedException
	 */
	@ResponseBody
	@RequestMapping("/orderAdvancedSearch.do")
	public Object orderAdvancedSearch(OrderItemInfo_S orderItemInfo_S, HttpServletRequest request) throws InterruptedException {
		String page = request.getParameter("page");
		try {
			new Integer(page);
		} catch (Exception e) {
			page = "1";
		}
		orderItemInfo_S.setOwnerId(ownerId);
		List<OrderItemInfo_S> list = jxb_OrderAdvancedSearchService.listOrderDetailSelective(orderItemInfo_S);
		if (list == null || list.size() == 0) {
			return new JsonResult(0, null, "1");
		}
		int count = list.size();
		int max_page = (int) Math.ceil(new Double(count) / rows);
		int start = new Integer(page) * rows - rows;
		try {
			list = list.subList(start, start + rows);
		} catch (Exception e) {
			list = list.subList(start, count);
		}
		return new JsonResult(0, list, max_page + "");
	}
}
