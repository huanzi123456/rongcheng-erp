package com.rongcheng.erp.service.ShopInfo;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import com.rongcheng.erp.entity.ShopInfo;

public interface ShopInfoService {
//	public ShopInfo findByShopType(String type);
	public ShopInfo findByShopId(BigInteger id);
	public int modifyShopInfo(BigInteger id,String name,String sellerAccount,String contactName,String contactTel,String userAddress);
	public List<ShopInfo> findAll();
	//添加店铺
	public int addShopInfo(BigInteger ownerId,Timestamp gmtCreate,String name,String sellerAccount,String contactName,String contactTel,String userAddress);
	public List<ShopInfo> findShopInfoByPage(int start,int rows);
	public String findShopInfoCount();	
	public int modifyShopInfoStatus(BigInteger id,Boolean shopStatus);
}
