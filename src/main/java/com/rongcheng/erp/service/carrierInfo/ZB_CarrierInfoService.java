package com.rongcheng.erp.service.carrierInfo;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.CarrierInfo;
/**
 * 快递信息表 业务层
 * @author 赵滨
 *
 */
public interface ZB_CarrierInfoService {

    /**
     * 查询全部快递信息
     * @param authorized 是否授权
     * @param ownerId   主账户ID
     * @param operatorId 操作人ID
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    List<CarrierInfo> listCarrierInfoAll(Boolean authorized, BigInteger ownerId, BigInteger operatorId);
}
