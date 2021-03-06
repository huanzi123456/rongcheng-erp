package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 系统商品对应关系关联表 商店表
 * @author 赵滨
 */
public class PlatformErpLinkShopWarehouseInfo implements Serializable{

    //序列号
    private static final long serialVersionUID = 2352142341928271337L;

    //属性
    private BigInteger id;      //1.记录编号
    private BigInteger itemId;  //2.erp商品ID
    private BigInteger platformId;  //3.来源（平台）编码
    private BigInteger shopId;  //4.商品所属店铺ID
    private BigInteger platformItemSku; //5.平台（来源）商品编码
    private BigInteger warehouseId;    //6.erp商品所属仓库编码
    private BigInteger stocklocationId;    //7.erp商品所属库位编码
    private Integer autoSynchron;  //8.库存自动同步状况（开关键）
    private Integer autoOnsale;    //9.自动上架状况（开关键）
    private Integer synchronException; //10.例外情况（库存低于警戒值不同步）
    private Integer availableStock;    //11.库存可用量
    private Integer allocationRatio;   //12.库存分配比例
    private Integer remnantStock;  //13.库存零头
    private String reserved1;   //14.自定义内容
    private String note;    //15.备注
    private BigInteger ownerId; //16.用户主账户ID
    private BigInteger operatorId;  //17.操作人
    private Byte authorized;  //18.该记录是否被授权
    private Timestamp gmtCreate;    //19.记录创建时间
    private Timestamp gmtModified;  //20.记录修改时间
    private String shopName;    //21.商店名称
    private String warehouseName;   //22.仓库名称

    /**
     * 无参构造
     */
    public PlatformErpLinkShopWarehouseInfo() {
    }

    /**
     * 有参构造
     */
    public PlatformErpLinkShopWarehouseInfo(BigInteger id, BigInteger itemId, BigInteger platformId, BigInteger shopId,
                                            BigInteger platformItemSku, BigInteger warehouseId, BigInteger stocklocationId,
                                            Integer autoSynchron, Integer autoOnsale, Integer synchronException,
                                            Integer availableStock, Integer allocationRatio, Integer remnantStock,
                                            String reserved1, String note, BigInteger ownerId, BigInteger operatorId,
                                            Byte authorized, Timestamp gmtCreate, Timestamp gmtModified,
                                            String shopName, String warehouseName) {
        this.id = id;
        this.itemId = itemId;
        this.platformId = platformId;
        this.shopId = shopId;
        this.platformItemSku = platformItemSku;
        this.warehouseId = warehouseId;
        this.stocklocationId = stocklocationId;
        this.autoSynchron = autoSynchron;
        this.autoOnsale = autoOnsale;
        this.synchronException = synchronException;
        this.availableStock = availableStock;
        this.allocationRatio = allocationRatio;
        this.remnantStock = remnantStock;
        this.reserved1 = reserved1;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorized = authorized;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.shopName = shopName;
        this.warehouseName = warehouseName;
    }

    //get和set
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public BigInteger getPlatformId() {
        return platformId;
    }

    public void setPlatformId(BigInteger platformId) {
        this.platformId = platformId;
    }

    public BigInteger getShopId() {
        return shopId;
    }

    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }

    public BigInteger getPlatformItemSku() {
        return platformItemSku;
    }

    public void setPlatformItemSku(BigInteger platformItemSku) {
        this.platformItemSku = platformItemSku;
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

    public Integer getAutoSynchron() {
        return autoSynchron;
    }

    public void setAutoSynchron(Integer autoSynchron) {
        this.autoSynchron = autoSynchron;
    }

    public Integer getAutoOnsale() {
        return autoOnsale;
    }

    public void setAutoOnsale(Integer autoOnsale) {
        this.autoOnsale = autoOnsale;
    }

    public Integer getSynchronException() {
        return synchronException;
    }

    public void setSynchronException(Integer synchronException) {
        this.synchronException = synchronException;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public Integer getAllocationRatio() {
        return allocationRatio;
    }

    public void setAllocationRatio(Integer allocationRatio) {
        this.allocationRatio = allocationRatio;
    }

    public Integer getRemnantStock() {
        return remnantStock;
    }

    public void setRemnantStock(Integer remnantStock) {
        this.remnantStock = remnantStock;
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

    public Byte getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Byte authorized) {
        this.authorized = authorized;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }


    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    /**
     * 重写equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformErpLinkShopWarehouseInfo that = (PlatformErpLinkShopWarehouseInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (itemId != null ? !itemId.equals(that.itemId) : that.itemId != null) return false;
        if (platformId != null ? !platformId.equals(that.platformId) : that.platformId != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (platformItemSku != null ? !platformItemSku.equals(that.platformItemSku) : that.platformItemSku != null)
            return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;
        if (stocklocationId != null ? !stocklocationId.equals(that.stocklocationId) : that.stocklocationId != null)
            return false;
        if (autoSynchron != null ? !autoSynchron.equals(that.autoSynchron) : that.autoSynchron != null) return false;
        if (autoOnsale != null ? !autoOnsale.equals(that.autoOnsale) : that.autoOnsale != null) return false;
        if (synchronException != null ? !synchronException.equals(that.synchronException) : that.synchronException != null)
            return false;
        if (availableStock != null ? !availableStock.equals(that.availableStock) : that.availableStock != null)
            return false;
        if (allocationRatio != null ? !allocationRatio.equals(that.allocationRatio) : that.allocationRatio != null)
            return false;
        if (remnantStock != null ? !remnantStock.equals(that.remnantStock) : that.remnantStock != null) return false;
        if (reserved1 != null ? !reserved1.equals(that.reserved1) : that.reserved1 != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (authorized != null ? !authorized.equals(that.authorized) : that.authorized != null) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
        if (shopName != null ? !shopName.equals(that.shopName) : that.shopName != null) return false;
        return warehouseName != null ? warehouseName.equals(that.warehouseName) : that.warehouseName == null;
    }

    /**
     * 重写hashCode
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (platformItemSku != null ? platformItemSku.hashCode() : 0);
        result = 31 * result + (warehouseId != null ? warehouseId.hashCode() : 0);
        result = 31 * result + (stocklocationId != null ? stocklocationId.hashCode() : 0);
        result = 31 * result + (autoSynchron != null ? autoSynchron.hashCode() : 0);
        result = 31 * result + (autoOnsale != null ? autoOnsale.hashCode() : 0);
        result = 31 * result + (synchronException != null ? synchronException.hashCode() : 0);
        result = 31 * result + (availableStock != null ? availableStock.hashCode() : 0);
        result = 31 * result + (allocationRatio != null ? allocationRatio.hashCode() : 0);
        result = 31 * result + (remnantStock != null ? remnantStock.hashCode() : 0);
        result = 31 * result + (reserved1 != null ? reserved1.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (authorized != null ? authorized.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        result = 31 * result + (shopName != null ? shopName.hashCode() : 0);
        result = 31 * result + (warehouseName != null ? warehouseName.hashCode() : 0);
        return result;
    }

    /**
     * 重写toString
     */
    @Override
    public String toString() {
        return "PlatformErpLinkShopWarehouseInfo{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", platformId=" + platformId +
                ", shopId=" + shopId +
                ", platformItemSku=" + platformItemSku +
                ", warehouseId=" + warehouseId +
                ", stocklocationId=" + stocklocationId +
                ", autoSynchron=" + autoSynchron +
                ", autoOnsale=" + autoOnsale +
                ", synchronException=" + synchronException +
                ", availableStock=" + availableStock +
                ", allocationRatio=" + allocationRatio +
                ", remnantStock=" + remnantStock +
                ", reserved1='" + reserved1 + '\'' +
                ", note='" + note + '\'' +
                ", ownerId=" + ownerId +
                ", operatorId=" + operatorId +
                ", authorized=" + authorized +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", shopName='" + shopName + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                '}';
    }
}
