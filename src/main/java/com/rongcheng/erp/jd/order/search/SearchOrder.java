package com.rongcheng.erp.jd.order.search;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.OrderResult;
import com.jd.open.api.sdk.request.order.OrderSearchRequest;
import com.jd.open.api.sdk.response.order.OrderSearchResponse;

/**
 * 
 * 根据条件检索【已付款】订单信息
 * 
 * @author jxb
 *
 */
@Service("searchOrder")
public class SearchOrder {
	@Value("#{config['appKey']}")
	private String appKey;
	@Value("#{config['appSecret']}")
	private String appSecret;
	/**
	 * 根据条件(都非空)检索订单信息
	 * 
	 * @param accessToken
	 * @param orderStateArr
	 * @param page
	 * @param pageSize
	 *            默认100
	 * @return
	 * @throws JdException
	 */
	public OrderResult searchOrderbyOrderState(String accessToken, String orderState, String page, Date lastCreateTime, String... pageSize) throws JdException {
		String serverUrl = "https://api.jd.com/routerjson";
		JdClient client = new DefaultJdClient(serverUrl, accessToken, appKey, appSecret);
		OrderSearchRequest request = new OrderSearchRequest();
		request.setPage(page);
		if (pageSize.length == 0) {
			request.setPageSize("100");
		} else {
			request.setPageSize(pageSize[0]);
		}
		request.setOptionalFields("order_id,order_source,customs,customs_model,vender_id,pay_type,order_total_price,order_seller_price,order_payment,freight_price,seller_discount,order_state,order_state_remark,delivery_type,invoice_info,order_remark,order_start_time,order_end_time,modified,consignee_info,item_info_list,coupon_detail_list,vender_remark,balance_used,payment_confirm_time,waybill,logistics_id,vat_invoice_info,parent_order_id,pin,return_order,order_type,store_order");
		request.setOrderState(orderState);
		request.setDateType("1");
		request.setSortType("1");
		//request.setStartDate(new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(lastCreateTime));
		OrderSearchResponse response = client.execute(request);
		OrderResult orderResult = response.getOrderInfoResult();
		return orderResult;
	}
}
