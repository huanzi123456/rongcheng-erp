package com.rongcheng.erp.jd.order.search;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.OrderResult;
import com.rongcheng.erp.dao.Jxb_BuyerDAO;
import com.rongcheng.erp.dao.Jxb_OrderDAO;
import com.rongcheng.erp.dao.Jxb_OrderItemLinkDAO;
import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.OrderItemLink;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.jd.order.converter.JDOrder2BaseEntity;

/**
 * 
 * @author jxb
 *
 */

@Controller
@RequestMapping("/jd")
public class SearchOrderController {
	@Value("#{config['accessToken']}")
	private String accessToken;
	@Resource
	private Jxb_BuyerDAO buyerDAO;
	@Resource
	private Jxb_OrderDAO orderDAO;
	@Resource
	private Jxb_OrderItemLinkDAO jxb_OrderItemLinkDAO;
	@Resource
	private JDOrder2BaseEntity jDOrder2BaseEntity;
	@Resource
	SearchOrder searchOrder;

	@RequestMapping("/order.do")
	public String order(HttpServletRequest req, HttpSession session) throws MalformedURLException, UnsupportedEncodingException, IOException, JdException {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		Date lastCreateTime = orderDAO.getLastCreateTime(ownerId, new BigInteger("3"));
		int page = 1;
		int pageSize = 100;

		String orderState = "WAIT_SELLER_STOCK_OUT";
		OrderResult orderResult = searchOrder.searchOrderbyOrderState(accessToken, orderState, page + "", lastCreateTime);
		req.setAttribute("orderResult", orderResult);
		req.setAttribute("orderList", orderResult.getOrderInfoList());
		int count = orderResult.getOrderTotal();
		while (count > 0) {
			saveOrderList(orderResult,ownerId);
			count -= pageSize;
			if (count > 0) {
				page++;
				orderResult = searchOrder.searchOrderbyOrderState(accessToken, orderState, page + "", lastCreateTime);
			}
		}

		page = 1;
		orderState = "WAIT_GOODS_RECEIVE_CONFIRM";
		orderResult = searchOrder.searchOrderbyOrderState(accessToken, orderState, "1", lastCreateTime);
		req.setAttribute("orderResult2", orderResult);
		req.setAttribute("orderList2", orderResult.getOrderInfoList());
		count = orderResult.getOrderTotal();
		while (count > 0) {
			saveOrderList(orderResult,ownerId);
			count -= pageSize;
			if (count > 0) {
				page++;
				orderResult = searchOrder.searchOrderbyOrderState(accessToken, orderState, page + "", lastCreateTime);
			}
		}

		page = 1;
		orderState = "WAIT_SELLER_DELIVERY";
		orderResult = searchOrder.searchOrderbyOrderState(accessToken, orderState, "1", lastCreateTime);
		req.setAttribute("orderResult3", orderResult);
		req.setAttribute("orderList3", orderResult.getOrderInfoList());
		count = orderResult.getOrderTotal();
		while (count > 0) {
			saveOrderList(orderResult,ownerId);
			count -= pageSize;
			if (count > 0) {
				page++;
				orderResult = searchOrder.searchOrderbyOrderState(accessToken, orderState, page + "", lastCreateTime);
			}
		}

		return "jd/order";
	}

	@SuppressWarnings("unchecked")
	public int saveOrderList(OrderResult orderResult, BigInteger ownerId) {
		Map<String, List<?>> map = jDOrder2BaseEntity.parse(orderResult,ownerId);
		List<OrderInfo> orderInfoList = (List<OrderInfo>) map.get("orderInfoList");
		List<OrderItemLink> orderItemLinkList = (List<OrderItemLink>) map.get("orderItemLinkList");
		List<BuyerInfo> buyerInfoList = (List<BuyerInfo>) map.get("buyerInfoList");
		int n = orderDAO.insertListSelective(orderInfoList);
		jxb_OrderItemLinkDAO.insertListSelective(orderItemLinkList);
		buyerDAO.insertListSelective(buyerInfoList);
		return n;
	}
}
