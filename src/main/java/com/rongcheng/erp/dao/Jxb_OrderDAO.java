package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.dto.OrderItemInfo_S;
import com.rongcheng.erp.entity.OrderInfo;

public interface Jxb_OrderDAO {
	/**
     * 条件查询记录数，所有参数都可为null。
     * @author jxb
     *
     * @return
     */
    int countSelective(OrderItemInfo_S orderItemInfo_S);
	

    /**
     * 
     * @author jxb
     *
     * @param record
     * @return
     */
    int insertSelective(OrderInfo record);
    int insertListSelective(List<OrderInfo> record);
    
    /**
     * 根据id List集合，条件选择修改项。
     * @author jxb
     *
     * @param map 	<"record",OrderInfo> 设置需要修改的内容，其他置null。
     * 				<"ids",List<BigInteger>>id List集合
     * @return
     */
    int updateByIdSelective(Map<String,Object> map);
    
    /**
     * 分页查找订单及其商品基本信息，所有参数都可为null。
	 * 
	 * @author jxb
	 *
	 * @param map		参数封装<"record",OrderInfo>订单对象，设置需要的查询条件，其他置null。
	 *							<"keyword",String>
	 *							<"start",int>
	 *							<"rows",int>
	 * @return
     */
    List<OrderItemInfo_S> listOrderItemInfo_SByLimtSelective(OrderItemInfo_S orderItemInfo_S);
    
    BigInteger getMaxId();
    
    Date getLastCreateTime(BigInteger ownerId,BigInteger platformId);
}