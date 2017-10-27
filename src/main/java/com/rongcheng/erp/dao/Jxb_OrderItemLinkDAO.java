package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.OrderItemLink;

public interface Jxb_OrderItemLinkDAO {
	int deleteByPrimaryKey(BigInteger id);

	/**
	 * 删除所有满足订单id值的记录
	 * 
	 * @author jxb
	 *
	 * @param id
	 * @return
	 */
	int deleteAllByOrderId(BigInteger orderId, BigInteger ownerId);

	/**
	 * 
	 * @author jxb
	 *
	 * @param record
	 * @return
	 */
	int insertSelective(OrderItemLink record);

	int insertListSelective(List<OrderItemLink> record);
}