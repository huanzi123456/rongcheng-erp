package com.rongcheng.erp.service.order;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.Jxb_BuyerDAO;
import com.rongcheng.erp.dao.Jxb_OrderDAO;
import com.rongcheng.erp.dao.Jxb_PlatformDAO;
import com.rongcheng.erp.dao.Jxb_ShopDAO;
import com.rongcheng.erp.dto.OrderItemInfo_S;
import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;

@Service("jxb_OrderService")
public class Jxb_OrderServiceImpl implements Jxb_OrderService {

	@Resource
	private Jxb_OrderDAO dao;
	@Resource
	private Jxb_PlatformDAO platformdDAO;
	@Resource
	private Jxb_ShopDAO shopDAO;
	@Resource
	private Jxb_BuyerDAO buyerDAO;

	public int updateByIdSelective(BigInteger[] ids, OrderInfo record) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("record", record);
		map.put("ids", ids);
		return dao.updateByIdSelective(map);
	}

	public Object[] listOrderSelective(OrderItemInfo_S orderItemInfo_S, Integer page, Integer rows) {
		Integer max_page = 1;
		Integer count = dao.countSelective(orderItemInfo_S);
		if (orderItemInfo_S.getId() == null) {
			int start = 0;
			if (page == null) {
				rows = count;
			} else {
				max_page = (int) Math.ceil(new Double(count) / rows);
				if (max_page == 0) {
					return null;
				}
				if (page > max_page) {
					page = max_page;
				}
				if (page < 1) {
					page = 1;
				}
				start = page * rows - rows;
			}
			orderItemInfo_S.setStart(start);
			orderItemInfo_S.setRows(rows);
		}
		List<OrderItemInfo_S> itemInfo_SList = dao.listOrderItemInfo_SByLimtSelective(orderItemInfo_S);
		for (OrderItemInfo_S oi_S : itemInfo_SList) {
			PlatformInfo platformInfo = platformdDAO.getById(oi_S.getPlatformId());
			ShopInfo shopInfo = shopDAO.getById(oi_S.getShopId());
			BuyerInfo buyerInfo = buyerDAO.getById(oi_S.getPlatformBuyerId());
			oi_S.setPlatformInfo(platformInfo);
			oi_S.setShopInfo(shopInfo);
			oi_S.setBuyerInfo(buyerInfo);
		}
		return new Object[] { max_page, itemInfo_SList };
	}
}