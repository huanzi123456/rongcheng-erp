package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.BuyerInfo;

public interface Jxb_BuyerDAO {
	/**
	 * 
	 * @author jxb
	 *
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(BigInteger id);

    /**
     * 
     * @author jxb
     *
     * @param id
     * @return
     */
    BuyerInfo getById(BigInteger id);

    /**
     * 
     * @author jxb
     *
     * @param record
     * @return
     */
    int insertSelective(BuyerInfo record);

    /**
     * 
     * @author jxb
     *
     * @param record
     * @return
     */
    int updateByIdSelective(BuyerInfo record);
    /**
     * 获取当前最大id值
     * 
     * @return
     */
    BigInteger getMaxId();
    
    int insertListSelective(List<BuyerInfo> record);
}