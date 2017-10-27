package com.rongcheng.erp.dao;

import java.math.BigInteger;

import com.rongcheng.erp.entity.ShopInfo;

public interface Jxb_ShopDAO {
    int deleteByPrimaryKey(BigInteger id);

    int insertSelective(ShopInfo record);

    ShopInfo getById(BigInteger id);

    int updateByPrimaryKeySelective(ShopInfo record);

}