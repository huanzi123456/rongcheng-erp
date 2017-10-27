package com.rongcheng.erp.dto;

import java.util.List;

import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;

/**
 * 平台店铺
 * 
 * @author JIML
 *
 */
public class PlatformShop extends PlatformInfo {

	private static final long serialVersionUID = 7822646948995817112L;
	// 店铺信息
	private List<ShopInfo> shopList;

	public PlatformShop() {
		super();
	}

	public List<ShopInfo> getShopList() {
		return shopList;
	}

	public void setShopList(List<ShopInfo> shopList) {
		this.shopList = shopList;
	}

	@Override
	public String toString() {
		String ss = super.toString();
		String s = ss.substring(ss.indexOf("[") + 1, ss.lastIndexOf("]"));
		return "PlatformShop [" + s + ",shopList=" + shopList + "]";
	}

}