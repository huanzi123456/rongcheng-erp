package com.rongcheng.erp.dao;

import java.math.BigInteger;

import com.rongcheng.erp.entity.PlatformInfo;

public interface Jxb_PlatformDAO {
	int deleteByPrimaryKey(BigInteger id);

    int insertSelective(PlatformInfo record);

    PlatformInfo getById(BigInteger id);

    int updateByPrimaryKeySelective(PlatformInfo record);

}
