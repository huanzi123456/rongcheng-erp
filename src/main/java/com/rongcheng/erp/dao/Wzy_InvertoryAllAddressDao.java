package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongcheng.erp.entity.vo.AddressCarrierAllocation;

public interface Wzy_InvertoryAllAddressDao {
  
    //查询全国城市编码(省)
    List<AddressCarrierAllocation> findAddressCarrierAllocation();
    
    //查询全国城市编码(市)
    List<AddressCarrierAllocation> findAddressCarrierAllocationSecond();
    
    //更新仓库的全国编码
    int updateCoverArea();
    
    //删除仓库关系(因全国编码改动)
    int deleteCoverArea(@Param("warehouseId")Integer warehouseId, 
                        @Param("stocklocationId")Integer stocklocationId, 
                        @Param("ownerId")BigInteger ownerId,
                        @Param("coverRegionId")Integer coverRegionId);
    
    //插入仓库关系(因全国编码改动)
    int saveCoverArea(@Param("warehouseId")Integer warehouseId, 
                      @Param("stocklocationId")Integer stocklocationId, 
                      @Param("ownerId")BigInteger ownerId,
                      @Param("coverRegionId")Integer coverRegionId);
    
    //查询仓库的覆盖范围
    List<Integer> findWarehouseCover(
            @Param("warehouseId")Integer warehouseId, 
            @Param("stocklocationId")Integer stocklocationId, 
            @Param("ownerId")BigInteger ownerId);
}
