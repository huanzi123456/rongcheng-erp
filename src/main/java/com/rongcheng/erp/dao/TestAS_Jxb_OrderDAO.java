package com.rongcheng.erp.dao;

import java.util.List;

import com.rongcheng.erp.dto.OrderItemInfo_S;

public interface TestAS_Jxb_OrderDAO {
	List<OrderItemInfo_S> list(OrderItemInfo_S orderItemInfo_S);
	int count(OrderItemInfo_S orderItemInfo_S);
}