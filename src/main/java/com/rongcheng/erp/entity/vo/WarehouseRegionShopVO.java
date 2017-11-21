package com.rongcheng.erp.entity.vo;

import java.math.BigInteger;

public class WarehouseRegionShopVO {

    private BigInteger id;
    private String userWarehouseCode;
    private String warehouseName;
    private String stocklocationName;
    private BigInteger stocklocationId;
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public String getUserWarehouseCode() {
        return userWarehouseCode;
    }
    public void setUserWarehouseCode(String userWarehouseCode) {
        this.userWarehouseCode = userWarehouseCode;
    }
    public String getWarehouseName() {
        return warehouseName;
    }
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    public String getStocklocationName() {
        return stocklocationName;
    }
    public void setStocklocationName(String stocklocationName) {
        this.stocklocationName = stocklocationName;
    }
    public BigInteger getStocklocationId() {
        return stocklocationId;
    }
    public void setStocklocationId(BigInteger stocklocationId) {
        this.stocklocationId = stocklocationId;
    }
    @Override
    public String toString() {
        return "WarehouseRegionShopVO [id=" + id + ", userWarehouseCode=" + userWarehouseCode + ", warehouseName="
                + warehouseName + ", stocklocationName=" + stocklocationName + ", stocklocationId=" + stocklocationId
                + "]";
    }
    
}
