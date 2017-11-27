package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class WarehouseRegionShop implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 3155319445777235184L;
    private BigInteger id;
    private BigInteger warehouseId;
    private BigInteger stocklocationId;
    private BigInteger coverRegionId;
    private BigInteger shopId;
    private String reserved1;
    private String note;
    private BigInteger ownerId;
    private BigInteger operatorId;
    private Boolean authorization;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    
    public WarehouseRegionShop(BigInteger id, BigInteger warehouseId, BigInteger stocklocationId,
            BigInteger coverRegionId, BigInteger shopId, String reserved1, String note, BigInteger ownerId,
            BigInteger operatorId, Boolean authorization, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.warehouseId = warehouseId;
        this.stocklocationId = stocklocationId;
        this.coverRegionId = coverRegionId;
        this.shopId = shopId;
        this.reserved1 = reserved1;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorization = authorization;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
    
    public WarehouseRegionShop() {
        super();
    }
    
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public BigInteger getWarehouseId() {
        return warehouseId;
    }
    public void setWarehouseId(BigInteger warehouseId) {
        this.warehouseId = warehouseId;
    }
    public BigInteger getStocklocationId() {
        return stocklocationId;
    }
    public void setStocklocationId(BigInteger stocklocationId) {
        this.stocklocationId = stocklocationId;
    }
    public BigInteger getCoverRegionId() {
        return coverRegionId;
    }
    public void setCoverRegionId(BigInteger coverRegionId) {
        this.coverRegionId = coverRegionId;
    }
    public BigInteger getShopId() {
        return shopId;
    }
    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }
    public String getReserved1() {
        return reserved1;
    }
    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public BigInteger getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(BigInteger ownerId) {
        this.ownerId = ownerId;
    }
    public BigInteger getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(BigInteger operatorId) {
        this.operatorId = operatorId;
    }
    public Boolean getAuthorization() {
        return authorization;
    }
    public void setAuthorization(Boolean authorization) {
        this.authorization = authorization;
    }
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }
    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    public Timestamp getGmtModified() {
        return gmtModified;
    }
    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
    @Override
    public String toString() {
        return "WarehouseRegionShop [id=" + id + ", warehouseId=" + warehouseId + ", stocklocationId=" + stocklocationId
                + ", coverRegionId=" + coverRegionId + ", shopId=" + shopId + ", reserved1=" + reserved1 + ", note="
                + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorization=" + authorization
                + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorization == null) ? 0 : authorization.hashCode());
        result = prime * result + ((coverRegionId == null) ? 0 : coverRegionId.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
        result = prime * result + ((stocklocationId == null) ? 0 : stocklocationId.hashCode());
        result = prime * result + ((warehouseId == null) ? 0 : warehouseId.hashCode());
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
        WarehouseRegionShop other = (WarehouseRegionShop) obj;
        if (authorization == null) {
            if (other.authorization != null)
                return false;
        } else if (!authorization.equals(other.authorization))
            return false;
        if (coverRegionId == null) {
            if (other.coverRegionId != null)
                return false;
        } else if (!coverRegionId.equals(other.coverRegionId))
            return false;
        if (gmtCreate == null) {
            if (other.gmtCreate != null)
                return false;
        } else if (!gmtCreate.equals(other.gmtCreate))
            return false;
        if (gmtModified == null) {
            if (other.gmtModified != null)
                return false;
        } else if (!gmtModified.equals(other.gmtModified))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (operatorId == null) {
            if (other.operatorId != null)
                return false;
        } else if (!operatorId.equals(other.operatorId))
            return false;
        if (ownerId == null) {
            if (other.ownerId != null)
                return false;
        } else if (!ownerId.equals(other.ownerId))
            return false;
        if (reserved1 == null) {
            if (other.reserved1 != null)
                return false;
        } else if (!reserved1.equals(other.reserved1))
            return false;
        if (shopId == null) {
            if (other.shopId != null)
                return false;
        } else if (!shopId.equals(other.shopId))
            return false;
        if (stocklocationId == null) {
            if (other.stocklocationId != null)
                return false;
        } else if (!stocklocationId.equals(other.stocklocationId))
            return false;
        if (warehouseId == null) {
            if (other.warehouseId != null)
                return false;
        } else if (!warehouseId.equals(other.warehouseId))
            return false;
        return true;
    }
    
    
}
