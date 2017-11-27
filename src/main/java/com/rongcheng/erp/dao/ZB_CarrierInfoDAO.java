package com.rongcheng.erp.dao;

import com.rongcheng.erp.entity.CarrierInfo;

import java.math.BigInteger;
import java.util.List;

/**
 * 快递信息表 数据访问层
 * @author 赵滨
 *
 */
public interface ZB_CarrierInfoDAO {
    /**
     * 根据ID查询快递信息
     * @param id ID
     * @param ownerId   主账户ID
     * @return CarrierInfo 快递信息
     * @author 赵滨
     */
    CarrierInfo getCarrierInfoById(BigInteger id, BigInteger ownerId);
    
    /**
     * 查询全部快递信息
     * @param ownerId   主账户ID
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    List<CarrierInfo> listCarrierInfoAll(BigInteger ownerId);
}
