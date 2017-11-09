package com.rongcheng.erp.entity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ItemInfo implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1351605751844949868L;
    private BigInteger id;
	private String erpItemNum;
	private String name;
	private String shortName;
	private BigInteger platformId;
	private BigInteger platformItemSku;
	private BigInteger warehouseId;
	private String userShopCode;
	private String userItemCode;
	private String image1;
	private String category;
	private String brand;
	private String series;
	private String color;
	private String size;
	private BigDecimal normalPrice;
	private BigDecimal costPrice;
	private BigDecimal promotionPrice;
	private String barCode;
	private String packageCondition;
	private String userClassification;
	private Boolean presell;
	private Boolean commissionSell;
	private Boolean gift;
	private String barCodeImage;
	private String qrCodeImage;
	private String reserved1;
	private String note;
	private Boolean authorized;
	private String itemSecretKey;
	private BigInteger ownerId;
	private BigInteger shopId;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;
	private BigInteger operatorId;
	private String spec;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private String image6;
	private String unit;
	private BigDecimal length;
	private BigDecimal width;
	private BigDecimal height;
	private BigDecimal weight;
	private String batchCode;
	private Timestamp expireDate;
	private String styleCode;
	private String reserved2;
	private String reserved3;
	private Byte isAuthorized;
	private BigInteger itemId;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getErpItemNum() {
		return erpItemNum;
	}
	public void setErpItemNum(String erpItemNum) {
		this.erpItemNum = erpItemNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public BigInteger getPlatformId() {
		return platformId;
	}
	public void setPlatformId(BigInteger platformId) {
		this.platformId = platformId;
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
	public String getUserShopCode() {
		return userShopCode;
	}
	public void setUserShopCode(String userShopCode) {
		this.userShopCode = userShopCode;
	}
	public String getUserItemCode() {
		return userItemCode;
	}
	public void setUserItemCode(String userItemCode) {
		this.userItemCode = userItemCode;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public BigDecimal getNormalPrice() {
		return normalPrice;
	}
	public void setNormalPrice(BigDecimal normalPrice) {
		this.normalPrice = normalPrice;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getPackageCondition() {
		return packageCondition;
	}
	public void setPackageCondition(String packageCondition) {
		this.packageCondition = packageCondition;
	}
	public String getUserClassification() {
		return userClassification;
	}
	public void setUserClassification(String userClassification) {
		this.userClassification = userClassification;
	}
	public Boolean getPresell() {
		return presell;
	}
	public void setPresell(Boolean presell) {
		this.presell = presell;
	}
	public Boolean getCommissionSell() {
		return commissionSell;
	}
	public void setCommissionSell(Boolean commissionSell) {
		this.commissionSell = commissionSell;
	}
	public Boolean getGift() {
		return gift;
	}
	public void setGift(Boolean gift) {
		this.gift = gift;
	}
	public String getBarCodeImage() {
		return barCodeImage;
	}
	public void setBarCodeImage(String barCodeImage) {
		this.barCodeImage = barCodeImage;
	}
	public String getQrCodeImage() {
		return qrCodeImage;
	}
	public void setQrCodeImage(String qrCodeImage) {
		this.qrCodeImage = qrCodeImage;
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
	public Boolean getAuthorized() {
		return authorized;
	}
	public void setAuthorized(Boolean authorized) {
		this.authorized = authorized;
	}
	public String getItemSecretKey() {
		return itemSecretKey;
	}
	public void setItemSecretKey(String itemSecretKey) {
		this.itemSecretKey = itemSecretKey;
	}
	public BigInteger getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(BigInteger ownerId) {
		this.ownerId = ownerId;
	}
	public BigInteger getShopId() {
		return shopId;
	}
	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
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
	public BigInteger getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(BigInteger operatorId) {
		this.operatorId = operatorId;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	public String getImage6() {
		return image6;
	}
	public void setImage6(String image6) {
		this.image6 = image6;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigDecimal getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public BigDecimal getWidth() {
		return width;
	}
	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getBatchCode() {
		return batchCode;
	}
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	public Timestamp getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}
	public String getStyleCode() {
		return styleCode;
	}
	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public Byte getIsAuthorized() {
		return isAuthorized;
	}
	public void setIsAuthorized(Byte isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	public BigInteger getItemId() {
		return itemId;
	}
	public void setItemId(BigInteger itemId) {
		this.itemId = itemId;
	}
	
	public String getReserved3() {
        return reserved3;
    }
    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }
    @Override
    public String toString() {
        return "ItemInfo [id=" + id + ", erpItemNum=" + erpItemNum + ", name=" + name + ", shortName=" + shortName
                + ", platformId=" + platformId + ", platformItemSku=" + platformItemSku + ", warehouseId=" + warehouseId
                + ", userShopCode=" + userShopCode + ", userItemCode=" + userItemCode + ", image1=" + image1
                + ", category=" + category + ", brand=" + brand + ", series=" + series + ", color=" + color + ", size="
                + size + ", normalPrice=" + normalPrice + ", costPrice=" + costPrice + ", promotionPrice="
                + promotionPrice + ", barCode=" + barCode + ", packageCondition=" + packageCondition
                + ", userClassification=" + userClassification + ", presell=" + presell + ", commissionSell="
                + commissionSell + ", gift=" + gift + ", barCodeImage=" + barCodeImage + ", qrCodeImage=" + qrCodeImage
                + ", reserved1=" + reserved1 + ", note=" + note + ", authorized=" + authorized + ", itemSecretKey="
                + itemSecretKey + ", ownerId=" + ownerId + ", shopId=" + shopId + ", gmtCreate=" + gmtCreate
                + ", gmtModified=" + gmtModified + ", operatorId=" + operatorId + ", spec=" + spec + ", image2="
                + image2 + ", image3=" + image3 + ", image4=" + image4 + ", image5=" + image5 + ", image6=" + image6
                + ", unit=" + unit + ", length=" + length + ", width=" + width + ", height=" + height + ", weight="
                + weight + ", batchCode=" + batchCode + ", expireDate=" + expireDate + ", styleCode=" + styleCode
                + ", reserved2=" + reserved2 + ", reserved3=" + reserved3 + ", isAuthorized=" + isAuthorized
                + ", itemId=" + itemId + "]";
    }
    
	
}
