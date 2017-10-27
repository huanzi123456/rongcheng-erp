package com.rongcheng.erp.service.order;

import java.math.BigInteger;

import com.rongcheng.erp.dto.OrderItemInfo_S;
import com.rongcheng.erp.entity.OrderInfo;

public interface Jxb_OrderService {
    
    /**
     * 根据id List集合，条件选择修改项。
     * 
     * @author jxb
     *
	 * @param record	OrderInfo 设置需要修改的内容，其他置null。
	 * @param ids		String[] id 数组
	 * 
	 * @return
	 */
    int updateByIdSelective(BigInteger[] ids, OrderInfo record);
    
    /**
     * 分页查找订单及其商品基本信息，所有参数都可为null。
	 * 
	 * @author jxb
	 *
	 * @param map		参数封装<"record",OrderInfo>订单对象，设置需要的查询条件，其他置null。
	 *							//<"keyword",String>
	 *							<"start",int>
	 *							<"rows",int>
	 * @return			[int 总页数,List<OrderItemInfo_S>]
     */
    Object[] listOrderSelective(OrderItemInfo_S orderItemInfo_S,Integer start,Integer rows);
}