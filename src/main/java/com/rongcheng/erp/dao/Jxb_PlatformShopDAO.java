package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.dto.PlatformShop;

public interface Jxb_PlatformShopDAO {
	List<PlatformShop> listAll(BigInteger ownerId);
}
