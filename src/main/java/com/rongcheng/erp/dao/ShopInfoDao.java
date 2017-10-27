package com.rongcheng.erp.dao;


import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.ShopInfo;

public interface ShopInfoDao {
	public ShopInfo findByShopId(BigInteger id);
//	public ShopInfo findByShopType(String type);
	public int modifyShopInfo(ShopInfo si);
	public List<ShopInfo> findAll();
	public int addShopInfo(ShopInfo si);
	public List<ShopInfo> findShopInfoByPage(int start,int rows);
	public String findShopInfoCount();		
}
