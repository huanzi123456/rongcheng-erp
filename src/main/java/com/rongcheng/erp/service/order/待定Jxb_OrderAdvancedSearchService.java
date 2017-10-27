package com.rongcheng.erp.service.order;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dto.ItemCommonWarehouse_S;
import com.rongcheng.erp.dto.OrderItemInfo_S;
import com.rongcheng.erp.entity.BuyerInfo;

@Service("jxb_OrderAdvancedSearchService")
public class 待定Jxb_OrderAdvancedSearchService {

	@Resource
	Jxb_OrderServiceImpl jxb_OrderService;

	@SuppressWarnings("unchecked")
	public List<OrderItemInfo_S> listOrderDetailSelective(OrderItemInfo_S orderItemInfo_S) {
		Object[] originalResult = jxb_OrderService.listOrderSelective(orderItemInfo_S, null, null);
		List<OrderItemInfo_S> originalList = (List<OrderItemInfo_S>) originalResult[1];
		List<OrderItemInfo_S> resultList = new ArrayList<OrderItemInfo_S>();
		String keyword = orderItemInfo_S.getKeyword();
		if (keyword == null || keyword.trim().length() == 0) {
			return originalList;
		}
		for (OrderItemInfo_S orderDetail : originalList) {
			BuyerInfo buyer = orderDetail.getBuyerInfo();
			if (buyer != null) {
				StringBuilder search = new StringBuilder();
				search.append(buyer.getBuyerName());
				search.append(buyer.getConsigneeName());
				search.append(buyer.getNickname());
				search.append(buyer.getConsigneeTel());
				if (search != null && search.toString().indexOf(keyword) != -1) {
					resultList.add(orderDetail);
					continue;
				}
			}
			List<ItemCommonWarehouse_S> itemList = orderDetail.getItemCommonWarehouse_S();
			if (itemList.size() > 0) {
				for (ItemCommonWarehouse_S itemCommonWarehouse_S : itemList) {
					StringBuilder search = new StringBuilder();
					search.append(itemCommonWarehouse_S.getName());
					search.append(itemCommonWarehouse_S.getErpItemNum());
					if (search != null && search.toString().indexOf(keyword) != -1) {
						resultList.add(orderDetail);
						break;
					}
				}
			}
		}
		return resultList;
	}

}
