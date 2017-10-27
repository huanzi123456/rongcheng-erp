package com.rongcheng.erp.service.ShopInfo;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.ShopInfoDao;
import com.rongcheng.erp.entity.ShopInfo;
@Service("shopInfoService")
public class ShopInfoServiceImpl implements ShopInfoService{
	@Resource
	private ShopInfoDao dao;
	public int modifyShopInfo(BigInteger id,String name, String sellerAccount, String contactName, String contactTel,
			String userAddress) {
		ShopInfo si=dao.findByShopId(id);
		si.setName(contactName);
		si.setSellerAccount(sellerAccount);
		si.setContactName(contactName);
		si.setContactTel(contactTel);
		si.setUserAddress(userAddress);
		int n=dao.modifyShopInfo(si);
		return n;
	}

	public List<ShopInfo> findAll() {
		List<ShopInfo> list=dao.findAll();
		return list;
	}

	public int addShopInfo(BigInteger ownerId,Timestamp gmtCreate,String name, String sellerAccount, String contactName, String contactTel,
			String userAddress) {
		ShopInfo si = new ShopInfo();
		si.setOwnerId(ownerId);
		si.setGmtCreate(gmtCreate);
		si.setName(name);
		si.setSellerAccount(sellerAccount);
		si.setContactName(contactName);
		si.setContactTel(contactTel);
		si.setUserAddress(userAddress);
		int n=dao.addShopInfo(si);
		return n;
	}

	public List<ShopInfo> findShopInfoByPage(int start, int rows) {
		List<ShopInfo> list1=dao.findShopInfoByPage(start, rows);
		return list1;
	}

	public String findShopInfoCount() {
		String s=dao.findShopInfoCount();
		return s;
	}

	public ShopInfo findByShopId(BigInteger id) {
		ShopInfo si =dao.findByShopId(id);
		return si;
	}

	public int modifyShopInfoStatus(BigInteger id, Boolean shopStatus) {
		ShopInfo si=dao.findByShopId(id);
		si.setShopStatus(shopStatus);
		int n=dao.modifyShopInfo(si);
		return n;
	}
}
