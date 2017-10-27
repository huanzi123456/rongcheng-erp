package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.dao.Jxb_BuyerDAO;
import com.rongcheng.erp.dao.Jxb_PlatformDAO;
import com.rongcheng.erp.dao.Jxb_PlatformShopDAO;
import com.rongcheng.erp.dao.Jxb_ShopDAO;
import com.rongcheng.erp.dao.TestAS_Jxb_OrderDAO;
import com.rongcheng.erp.dto.OrderItemInfo_S;
import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.utils.JsonResult;

/**
 * 
 * @author jxb
 *
 */

@Controller
@RequestMapping("/audit")
public class TestAS {
	// 分页相关（每页多少条）
	@Value("#{config['rows']}")
	private int rows;
	@Resource
	private Jxb_PlatformDAO platformdDAO;
	@Resource
	private Jxb_ShopDAO shopDAO;
	@Resource
	private Jxb_BuyerDAO buyerDAO;
	@Resource
	private TestAS_Jxb_OrderDAO testAS_Jxb_OrderDAO;
	@Resource
	private Jxb_PlatformShopDAO jxb_PlatformShopDAO;

	@ResponseBody
	@RequestMapping("/testorderAdvancedSearch.do")
	public Object orderAdvancedSearch(int page, OrderItemInfo_S orderItemInfo_S, HttpServletRequest request, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		// 打印状态。1-已打快递已打发货 2-已打快递未打发货 3-未打快递已打发货 4-未打快递未打发货
		String print = request.getParameter("erp");
		if (print != null) {
			int intPrint = 0;
			try {
				intPrint = new Integer(print);
			} catch (Exception e) {
			}
			switch (intPrint) {
			case 1:
				orderItemInfo_S.setSaleBillPrint(new Timestamp(1));
				orderItemInfo_S.setExpressSheetPrint(new Timestamp(1));
				break;
			case 2:
				orderItemInfo_S.setExpressSheetPrint(new Timestamp(1));
				break;
			case 3:
				orderItemInfo_S.setSaleBillPrint(new Timestamp(1));
				break;
			}
		}
		orderItemInfo_S.setOwnerId(ownerId);
		int count = 0;
		Object ct = session.getAttribute("orderAdvancedSearchCount");
		if (ct != null && page > 1) {
			count = (Integer) ct;
		} else {
			count = testAS_Jxb_OrderDAO.count(orderItemInfo_S);
			session.setAttribute("orderAdvancedSearchCount", count);
		}
		int max_page = (int) Math.ceil(new Double(count) / rows);
		if (max_page == 0) {
			return new JsonResult(0, null, "1");
		}
		if (page > max_page) {
			page = max_page;
		}
		if (page < 1) {
			page = 1;
		}
		int start = page * rows - rows;
		orderItemInfo_S.setStart(start);
		orderItemInfo_S.setRows(rows);
		List<OrderItemInfo_S> list = testAS_Jxb_OrderDAO.list(orderItemInfo_S);
		for (OrderItemInfo_S oi_S : list) {
			PlatformInfo platformInfo = platformdDAO.getById(oi_S.getPlatformId());
			ShopInfo shopInfo = shopDAO.getById(oi_S.getShopId());
			BuyerInfo buyerInfo = buyerDAO.getById(oi_S.getPlatformBuyerId());
			oi_S.setPlatformInfo(platformInfo);
			oi_S.setShopInfo(shopInfo);
			oi_S.setBuyerInfo(buyerInfo);
		}
		return new JsonResult(0, list, max_page + "");
	}

	@ResponseBody
	@RequestMapping("/loadPlatformShop.do")
	public Object loadPlatformShop(HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		return jxb_PlatformShopDAO.listAll(ownerId);
	}
}
