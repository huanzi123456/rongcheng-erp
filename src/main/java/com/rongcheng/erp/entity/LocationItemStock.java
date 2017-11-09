package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;



/**
 * 库位商品库存关联表 实体类
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
    private BigInteger id;      //1.记录编号
    private BigInteger warehouseId;     //2.仓库编码
    private BigInteger stocklocationId;     //3.库位编码
    private BigInteger itemId;      //4.商品编号
    private Integer stockQuantity;      //5.库存量
    private Integer alertStock;     //6.警戒量库存
    private Integer sendoutStatus;      //7.该库存商品是否参与发货
    private String bindId;          //8.表内商品关联码
    private String reserved1;       //8.自定义内容1
    private String note;        //9.备注
    private BigInteger ownerId;     //10.用户主账户ID
    private BigInteger operatorId;      //11.操作人
    private Boolean authorization;      //12.该记录是否被授权
    private Timestamp gmtCreate;        //13.记录创建时间
    private Timestamp gmtModified;      //14.记录修改时间

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
    public LocationItemStock(
            BigInteger id, BigInteger warehouseId, BigInteger stocklocationId, BigInteger itemId,
            Integer stockQuantity, Integer alertStock, Integer sendoutStatus, String bindId, String reserved1,
            String note, BigInteger ownerId, BigInteger operatorId, Boolean authorization, Timestamp gmtCreate,
            Timestamp gmtModified) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.stocklocationId = stocklocationId;
        this.itemId = itemId;
        this.stockQuantity = stockQuantity;
        this.alertStock = alertStock;
        this.sendoutStatus = sendoutStatus;
        this.bindId = bindId;
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

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    /**
     * 重写equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationItemStock that = (LocationItemStock) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;
        if (stocklocationId != null ? !stocklocationId.equals(that.stocklocationId) : that.stocklocationId != null)
            return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (stockQuantity != null ? !stockQuantity.equals(that.stockQuantity) : that.stockQuantity != null)
            return false;
        if (alertStock != null ? !alertStock.equals(that.alertStock) : that.alertStock != null) return false;
        if (sendoutStatus != null ? !sendoutStatus.equals(that.sendoutStatus) : that.sendoutStatus != null)
            return false;
        if (bindId != null ? !bindId.equals(that.bindId) : that.bindId != null) return false;
        if (reserved1 != null ? !reserved1.equals(that.reserved1) : that.reserved1 != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (authorization != null ? !authorization.equals(that.authorization) : that.authorization != null)
            return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        return gmtModified != null ? gmtModified.equals(that.gmtModified) : that.gmtModified == null;
    }

    /**
     * 重写哈希code
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (warehouseId != null ? warehouseId.hashCode() : 0);
        result = 31 * result + (stocklocationId != null ? stocklocationId.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (stockQuantity != null ? stockQuantity.hashCode() : 0);
        result = 31 * result + (alertStock != null ? alertStock.hashCode() : 0);
        result = 31 * result + (sendoutStatus != null ? sendoutStatus.hashCode() : 0);
        result = 31 * result + (bindId != null ? bindId.hashCode() : 0);
        result = 31 * result + (reserved1 != null ? reserved1.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (authorization != null ? authorization.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        return result;
    }

    /**
     * 重写toString
     */
    @Override
    public String toString() {
        return "LocationItemStock{" +
                "id=" + id +
                ", warehouseId=" + warehouseId +
                ", stocklocationId=" + stocklocationId +
                ", itemId=" + itemId +
                ", stockQuantity=" + stockQuantity +
                ", alertStock=" + alertStock +
                ", sendoutStatus=" + sendoutStatus +
                ", bindId='" + bindId + '\'' +
                ", reserved1='" + reserved1 + '\'' +
                ", note='" + note + '\'' +
                ", ownerId=" + ownerId +
                ", operatorId=" + operatorId +
                ", authorization=" + authorization +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
