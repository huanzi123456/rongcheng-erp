package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;



/**
 * 库存信息表 实体类
 * 
 * @author 赵滨
 */
public class LocationItemStock implements Serializable{
    
    /**
     * 序列号
     */
    private static final long serialVersionUID = -4424846681265678810L;
    
    /**
     * 构建参数
     */
    //1.记录编号
    private BigInteger id;
    
    //2.仓库编码
    private BigInteger warehouseId;
    
    //3.库位编码
    private BigInteger stocklocationId;
    
    //4.商品编号
    private BigInteger itemId;
    
    //5.库存量
    private Integer stockQuantity;
    
    //6.警戒量库存
    private Integer alertStock;
    
    //7.该库存商品是否参与发货
    private Integer sendoutStatus;
    
    //8.自定义内容1
    private String reserved1;
    
    //9.备注
    private String note;
    
    //10.用户主账户ID
    private BigInteger ownerId;
    
    //11.操作人
    private BigInteger operatorId;
    
    //12.该记录是否被授权
    private Boolean authorization;
    
    //13.记录创建时间
    private Timestamp gmtCreate;
    
    //14.记录修改时间
    private Timestamp gmtModified;
    
    /**
     * 无参构造
     */
    public LocationItemStock() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 有参构造
     */
    public LocationItemStock(BigInteger id, BigInteger warehouseId, BigInteger stocklocationId, BigInteger itemId,
            Integer stockQuantity, Integer alertStock, Integer sendoutStatus, String reserved1, String note,
            BigInteger ownerId, BigInteger operatorId, Boolean authorization, Timestamp gmtCreate,
            Timestamp gmtModified) {
        super();
        this.id = id;
        this.warehouseId = warehouseId;
        this.stocklocationId = stocklocationId;
        this.itemId = itemId;
        this.stockQuantity = stockQuantity;
        this.alertStock = alertStock;
        this.sendoutStatus = sendoutStatus;
        this.reserved1 = reserved1;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorization = authorization;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    //get set
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

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getAlertStock() {
        return alertStock;
    }

    public void setAlertStock(Integer alertStock) {
        this.alertStock = alertStock;
    }

    public Integer getSendoutStatus() {
        return sendoutStatus;
    }

    public void setSendoutStatus(Integer sendoutStatus) {
        this.sendoutStatus = sendoutStatus;
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
    
    /**
     * 重写哈希code
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alertStock == null) ? 0 : alertStock.hashCode());
        result = prime * result + ((authorization == null) ? 0 : authorization.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        result = prime * result + ((sendoutStatus == null) ? 0 : sendoutStatus.hashCode());
        result = prime * result + ((stockQuantity == null) ? 0 : stockQuantity.hashCode());
        result = prime * result + ((stocklocationId == null) ? 0 : stocklocationId.hashCode());
        result = prime * result + ((warehouseId == null) ? 0 : warehouseId.hashCode());
        return result;
    }
    
    /**
     * 重写equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LocationItemStock other = (LocationItemStock) obj;
        if (alertStock == null) {
            if (other.alertStock != null)
                return false;
        } else if (!alertStock.equals(other.alertStock))
            return false;
        if (authorization == null) {
            if (other.authorization != null)
                return false;
        } else if (!authorization.equals(other.authorization))
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
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
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
        if (sendoutStatus == null) {
            if (other.sendoutStatus != null)
                return false;
        } else if (!sendoutStatus.equals(other.sendoutStatus))
            return false;
        if (stockQuantity == null) {
            if (other.stockQuantity != null)
                return false;
        } else if (!stockQuantity.equals(other.stockQuantity))
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
    
    /**
     * 重写toString
     */
    @Override
    public String toString() {
        return "LocationItemStock [id=" + id + ", warehouseId=" + warehouseId + ", stocklocationId=" + stocklocationId
                + ", itemId=" + itemId + ", stockQuantity=" + stockQuantity + ", alertStock=" + alertStock
                + ", sendoutStatus=" + sendoutStatus + ", reserved1=" + reserved1 + ", note=" + note + ", ownerId="
                + ownerId + ", operatorId=" + operatorId + ", authorization=" + authorization + ", gmtCreate="
                + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }

}
