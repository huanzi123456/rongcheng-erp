package com.rongcheng.erp.jd.download.item.converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jd.open.api.sdk.domain.ware.WareReadService.Page;
import com.jd.open.api.sdk.domain.ware.WareReadService.Ware;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.ItemEspInfo;
import com.rongcheng.erp.jd.download.item.converter.incrId.ItemAutoIncrId;

public class JDItem2BaseEntity {
	public static Map<String, List<?>> parse(Page itemResult, BigInteger ownerId) {
		List<Ware> itemList = itemResult.getData();
		List<ItemCommonInfo> itemCommonInfoList = new ArrayList<ItemCommonInfo>();
		List<ItemEspInfo> itemEspInfoList = new ArrayList<ItemEspInfo>();
		for (Ware item : itemList) {
			BigInteger id = ItemAutoIncrId.getNextId();
			System.out.println(id);
			// 新建本地商品1
			ItemCommonInfo itemCommonInfo = new ItemCommonInfo();
			itemCommonInfo.setId(id);
			itemCommonInfo.setOwnerId(ownerId);
			itemCommonInfo.setGmtCreate(new Timestamp(System.currentTimeMillis()));

			itemCommonInfo.setPlatformId(new BigInteger("3"));
			itemCommonInfo.setShopId(new BigInteger(item.getShopId() + ""));/*-----------------------京东 商家对应的shopID----------------------*/

			itemCommonInfo.setErpItemNum("jd" + item.getWareId());
			itemCommonInfo.setPlatformItemSku(new BigInteger(item.getWareId() + ""));
			itemCommonInfo.setName(item.getTitle());
			itemCommonInfo.setImage1("http://img13.360buyimg.com/n1/" + item.getLogo());
			itemCommonInfo.setCategory(item.getCategoryId() + "");/*-----------------------京东 类目id----------------------*/
			itemCommonInfo.setBrand(item.getBrandName());
			itemCommonInfo.setCostPrice(item.getCostPrice());
			itemCommonInfo.setNormalPrice(item.getMarketPrice());/* -------------价格-------- */
			itemCommonInfo.setPromotionPrice(item.getJdPrice());/* -------------价格-------- */
			itemCommonInfo.setBarCode(item.getBarCode());

			itemCommonInfoList.add(itemCommonInfo);

			// 新建本地商品2
			ItemEspInfo itemEspInfo = new ItemEspInfo();
			itemEspInfo.setItemId(id);
			itemEspInfo.setOwnerId(ownerId);
			itemEspInfo.setGmtCreate(new Timestamp(System.currentTimeMillis()));
			itemEspInfo.setPlatformId(new BigInteger("3"));
			itemEspInfo.setPlatformItemSku(new BigInteger(item.getWareId() + ""));
			itemEspInfo.setLength(new BigDecimal(item.getLength()));
			itemEspInfo.setWidth(new BigDecimal(item.getWidth()));
			itemEspInfo.setHeight(new BigDecimal(item.getHeight()));
			itemEspInfo.setWeight(new BigDecimal(item.getWeight()));
			itemEspInfo.setStyleCode(item.getItemNum());
			itemEspInfoList.add(itemEspInfo);
		}
		Map<String, List<?>> resultMap = new HashMap<String, List<?>>();
		resultMap.put("itemCommonInfoList", itemCommonInfoList);
		resultMap.put("itemEspInfoList", itemEspInfoList);
		return resultMap;
	}
}
