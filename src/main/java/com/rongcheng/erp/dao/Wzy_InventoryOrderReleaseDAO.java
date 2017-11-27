package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongcheng.erp.dto.WzyWarehouseRegionShop;

public interface Wzy_InventoryOrderReleaseDAO {
    
    //查询所有的仓库
    List<WzyWarehouseRegionShop> findWarehouseRegionShopByOwnerId(
            @Param("ownerId")BigInteger ownerId, 
            @Param("startPage")Integer startPage, 
            @Param("row")Integer row);
    
    //查询仓库量
    Integer findWarehouseRegionShopCount(BigInteger ownerId);
    
}
