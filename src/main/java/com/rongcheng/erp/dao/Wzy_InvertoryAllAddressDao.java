package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongcheng.erp.dto.WzyAddressCarrierAllocation;
import com.rongcheng.erp.entity.WarehouseRegionShop;

public interface Wzy_InvertoryAllAddressDao {
  
    //查询全国城市编码(省)
    List<WzyAddressCarrierAllocation> findAddressCarrierAllocation();
    
    //查询全国城市编码(市)
    List<WzyAddressCarrierAllocation> findAddressCarrierAllocationSecond();
    
    //更新仓库的全国编码
    int updateCoverArea();
    
    //删除仓库关系(因全国编码改动)
    int deleteCoverArea(WarehouseRegionShop shop);
    
    //插入仓库关系(因全国编码改动)
    int saveCoverArea(WarehouseRegionShop shop);
    
    //查询仓库的覆盖范围
    List<Integer> findWarehouseCover(
            @Param("warehouseId")BigInteger warehouseId, 
            @Param("stocklocationId")BigInteger stocklocationId, 
            @Param("ownerId")BigInteger ownerId);
}
