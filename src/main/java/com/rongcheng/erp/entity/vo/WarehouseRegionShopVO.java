package com.rongcheng.erp.entity.vo;

import java.math.BigInteger;
import java.sql.Timestamp;

public class WarehouseRegionShopVO {

    private BigInteger id;
    private BigInteger warehouseId;
    private BigInteger stocklocationId;
    private BigInteger coverRegionId;
    private BigInteger shopId;
    private String warehouseNmae;
    private String reserved1;
    private String note;
    private BigInteger ownerId;
    private BigInteger operatorId;
    private Boolean authorization;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private String name;
    private String position;
    private String shelfCode;
    private String consignorName;
    private String consignorTel;
    private String consignorMobile;
    private BigInteger regionId;
    private String userAddress;

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
    public String getWarehouseNmae() {
        return warehouseNmae;
    }
    public void setWarehouseNmae(String warehouseNmae) {
        this.warehouseNmae = warehouseNmae;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getShelfCode() {
        return shelfCode;
    }
    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }
    public String getConsignorName() {
        return consignorName;
    }
    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }
    public String getConsignorTel() {
        return consignorTel;
    }
    public void setConsignorTel(String consignorTel) {
        this.consignorTel = consignorTel;
    }
    public String getConsignorMobile() {
        return consignorMobile;
    }
    public void setConsignorMobile(String consignorMobile) {
        this.consignorMobile = consignorMobile;
    }
    public BigInteger getRegionId() {
        return regionId;
    }
    public void setRegionId(BigInteger regionId) {
        this.regionId = regionId;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    @Override
    public String toString() {
        return "WarehouseRegionShop [id=" + id + ", warehouseId=" + warehouseId + ", stocklocationId=" + stocklocationId
                + ", coverRegionId=" + coverRegionId + ", shopId=" + shopId + ", warehouseNmae=" + warehouseNmae
                + ", reserved1=" + reserved1 + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId
                + ", authorization=" + authorization + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified
                + ", name=" + name + ", position=" + position + ", shelfCode=" + shelfCode + ", consignorName="
                + consignorName + ", consignorTel=" + consignorTel + ", consignorMobile=" + consignorMobile
                + ", regionId=" + regionId + ", userAddress=" + userAddress + "]";
    }
    
}
