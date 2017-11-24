package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class WzyWarehouseRegionShop implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6896451215501151694L;
    private BigInteger id;
    private String userWarehouseCode;
    private String warehouseName;
    private String stocklocationName;
    private BigInteger stocklocationId;

    public WzyWarehouseRegionShop() {
        super();
    }
    public WzyWarehouseRegionShop(BigInteger id, String userWarehouseCode, String warehouseName,
            String stocklocationName, BigInteger stocklocationId) {
        super();
        this.id = id;
        this.userWarehouseCode = userWarehouseCode;
        this.warehouseName = warehouseName;
        this.stocklocationName = stocklocationName;
        this.stocklocationId = stocklocationId;
    }
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((stocklocationId == null) ? 0 : stocklocationId.hashCode());
        result = prime * result + ((stocklocationName == null) ? 0 : stocklocationName.hashCode());
        result = prime * result + ((userWarehouseCode == null) ? 0 : userWarehouseCode.hashCode());
        result = prime * result + ((warehouseName == null) ? 0 : warehouseName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WzyWarehouseRegionShop other = (WzyWarehouseRegionShop) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (stocklocationId == null) {
            if (other.stocklocationId != null)
                return false;
        } else if (!stocklocationId.equals(other.stocklocationId))
            return false;
        if (stocklocationName == null) {
            if (other.stocklocationName != null)
                return false;
        } else if (!stocklocationName.equals(other.stocklocationName))
            return false;
        if (userWarehouseCode == null) {
            if (other.userWarehouseCode != null)
                return false;
        } else if (!userWarehouseCode.equals(other.userWarehouseCode))
            return false;
        if (warehouseName == null) {
            if (other.warehouseName != null)
                return false;
        } else if (!warehouseName.equals(other.warehouseName))
            return false;
        return true;
    }
    
    
}
