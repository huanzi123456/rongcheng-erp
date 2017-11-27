package com.rongcheng.erp.service.carrierInfo;

import com.rongcheng.erp.entity.CarrierInfo;

import java.util.List;

/**
 * 快递信息表 业务层
 * @author 赵滨
 *
 */
public interface ZB_CarrierInfoService {

    /**
     * 查询全部快递信息
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    List<CarrierInfo> listCarrierInfoAll();
}
