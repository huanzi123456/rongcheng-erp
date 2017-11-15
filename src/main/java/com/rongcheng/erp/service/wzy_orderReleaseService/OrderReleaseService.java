package com.rongcheng.erp.service.wzy_orderReleaseService;

import java.math.BigInteger;
import java.util.Map;

public interface OrderReleaseService {

    //分页查询以及分页信息获取
    Map<String, Object> findAllWarehouseByOwnerId(BigInteger ownerId, Integer nowPage, Integer row);

    //查询全国城市编码即覆盖范围
    Map<String, Object> findAddressCarrierAllocation(Integer warehouseId, Integer stocklocationId, BigInteger ownerId);
    
    //更新仓库的覆盖范围
    public int updateWarehouseCoverArea(
            String insertArea, String deleteArea, Integer warehouseId, Integer stocklocationId, BigInteger ownerId);
}
