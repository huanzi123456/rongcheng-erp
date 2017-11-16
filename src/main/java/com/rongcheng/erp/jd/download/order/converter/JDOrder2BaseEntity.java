package com.rongcheng.erp.jd.download.order.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import com.rongcheng.erp.jd.download.order.converter.incrId.BuyerInfoAutoIncrId;
import com.rongcheng.erp.jd.download.order.converter.incrId.OrderAutoIncrId;
import org.springframework.stereotype.Service;

import com.jd.open.api.sdk.domain.order.ItemInfo;
import com.jd.open.api.sdk.domain.order.OrderResult;
import com.jd.open.api.sdk.domain.order.OrderSearchInfo;
import com.jd.open.api.sdk.domain.order.UserInfo;
import com.rongcheng.erp.converter.CustomTimestampConverter;
import com.rongcheng.erp.dao.Jxb_ItemCommonDAO;
import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.OrderItemLink;

@Service("jDOrder2BaseEntity")
public class JDOrder2BaseEntity {
	@Resource
	private Jxb_ItemCommonDAO dao;
	public Map<String, List<?>> parse(OrderResult orderResult,BigInteger ownerId) {
		CustomTimestampConverter customTimestampConverter = new CustomTimestampConverter();
		List<OrderSearchInfo> orderSearchInfoList = orderResult.getOrderInfoList();
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		List<OrderItemLink> orderItemLinkList = new ArrayList<OrderItemLink>();
		List<BuyerInfo> buyerInfoList = new ArrayList<BuyerInfo>();
		for (OrderSearchInfo orderSearchInfo : orderSearchInfoList) {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			BigInteger shopId = new BigInteger(orderSearchInfo.getVenderId());
			// 新建本地买家信息
			BuyerInfo buyer = new BuyerInfo();
			BigInteger buyerId = BuyerInfoAutoIncrId.getNextId();
			buyer.setOwnerId(ownerId);
			buyer.setId(buyerId);
			buyer.setShopId(shopId);
			buyer.setBuyerAccount(orderSearchInfo.getPin());
			UserInfo jdBuyer = orderSearchInfo.getConsigneeInfo();
			buyer.setConsigneeName(jdBuyer.getFullname());
			buyer.setConsigneeMobile(jdBuyer.getMobile());
			buyer.setConsigneeTel(jdBuyer.getTelephone());
			//buyer.setRegionId(jdBuyer.getCounty());
			buyer.setUserAddress(jdBuyer.getFullAddress());
			buyerInfoList.add(buyer);
			
			// 新建本地订单
			OrderInfo orderInfo = new OrderInfo();
			BigInteger id = OrderAutoIncrId.getNextId();
			String erpOrderNum = "jd" + orderSearchInfo.getOrderId();
			orderInfo.setId(id);
			orderInfo.setOwnerId(ownerId);
			orderInfo.setGmtCreate(ts);
			orderInfo.setErpOrderNum(erpOrderNum);
			orderInfo.setPlatformId(new BigInteger("3"));
			orderInfo.setShopId(shopId);
			orderInfo.setPlatformBuyerId(buyerId);
			orderInfo.setPlatformOrderId(new BigInteger(orderSearchInfo.getOrderId()));
			orderInfo.setShippingFee(new BigDecimal(orderSearchInfo.getFreightPrice()));
			orderInfo.setBuyerWord(orderSearchInfo.getOrderRemark());
			orderInfo.setSellerWord(orderSearchInfo.getVenderRemark());
			if ("货到付款".equals(orderSearchInfo.getPayType())) {
				orderInfo.setOrderCod(true);
			} else {
				String payTime = orderSearchInfo.getPaymentConfirmTime();
				if ("0001-01-01 00:00:00".equals(payTime)) {
					orderInfo.setOrderStatus(1);
				} else {
					orderInfo.setOrderStatus(2);
					try {
						orderInfo.setOrderPay(customTimestampConverter.parse(payTime, Locale.CHINA));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				orderInfo.setOrderCreate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderSearchInfo.getOrderStartTime()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			orderInfoList.add(orderInfo);

			// 新建本地关联
			OrderItemLink orderItemLink = new OrderItemLink();

			// 京东平台订单商品信息
			List<ItemInfo> items = orderSearchInfo.getItemInfoList();
			for (ItemInfo itemInfo : items) {
				Map<String,Object> map = new HashMap<String, Object>();
				ItemCommonInfo ici = new ItemCommonInfo();
				ici.setOwnerId(ownerId);
				ici.setPlatformId(new BigInteger("3"));
				ici.setPlatformItemSku(new BigInteger(itemInfo.getWareId()+""));
				map.put("record", ici);
				map.put("start", 0);
				map.put("rows", 2);
				List<ItemCommonInfo> icis = dao.listAllBasicInfoByLimtSelective(map);
				if(icis!=null&&icis.size()==1){
					orderItemLink.setOwnerId(ownerId);
					orderItemLink.setGmtCreate(ts);
					orderItemLink.seterpOrderId(id);
					orderItemLink.setOrderItemId(icis.get(0).getId());
					orderItemLink.setQuantity(new Integer(itemInfo.getItemTotal()));
					orderItemLinkList.add(orderItemLink);
				}
			}
		}

		Map<String, List<?>> resultMap = new HashMap<String, List<?>>();
		resultMap.put("orderInfoList", orderInfoList);
		resultMap.put("orderItemLinkList", orderItemLinkList);
		resultMap.put("buyerInfoList", buyerInfoList);
		return resultMap;
	}
}
