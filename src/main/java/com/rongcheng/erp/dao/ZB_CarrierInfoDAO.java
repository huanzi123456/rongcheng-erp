package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.CarrierInfo;
/**
 * 快递信息表 数据访问层
 * @author 赵滨
 *
 */
public interface ZB_CarrierInfoDAO {
    /**
     * 根据ID查询快递信息
     * @param id ID
     * @param authorized 是否授权
     * @param ownerId   主账户ID
     * @return CarrierInfo 快递信息
     * @author 赵滨
     */
    CarrierInfo getCarrierInfoById(BigInteger id, Boolean authorized, BigInteger ownerId);
    
    /**
     * 查询全部快递信息
     * @param authorized 是否授权
     * @param ownerId   主账户ID
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    List<CarrierInfo> listCarrierInfoAll(Boolean authorized, BigInteger ownerId);
    
    /**
     * 保存快递信息
     * @param carrierInfo 快递信息]
     * @return row 行数
     * @author 赵滨
     */
//    int saveCarrierInfo(CarrierInfo carrierInfo);
}
