package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ItemCommonInfoOrderItemLink implements Serializable{

    //版本号
    private static final long serialVersionUID = 8496213216497072874L;

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
	private Integer quantity;	//赵滨添加

	public ItemCommonInfoOrderItemLink() {

	}

    public ItemCommonInfoOrderItemLink(BigInteger id, String erpItemNum, String name, String shortName,
               BigInteger platformId, BigInteger platformItemSku, BigInteger warehouseId,
               String userShopCode, String userItemCode, String image1, String category,
               String brand, String series, String color, String size, BigDecimal normalPrice,
               BigDecimal costPrice, BigDecimal promotionPrice, String barCode,
               String packageCondition, String userClassification, Boolean presell,
               Boolean commissionSell, Boolean gift, String barCodeImage, String qrCodeImage,
               String reserved1, String note, Boolean authorized, String itemSecretKey,
               BigInteger ownerId, BigInteger shopId, Timestamp gmtCreate, Timestamp gmtModified,
                                       BigInteger operatorId, String spec, Integer quantity) {
        this.id = id;
        this.erpItemNum = erpItemNum;
        this.name = name;
        this.shortName = shortName;
        this.platformId = platformId;
        this.platformItemSku = platformItemSku;
        this.warehouseId = warehouseId;
        this.userShopCode = userShopCode;
        this.userItemCode = userItemCode;
        this.image1 = image1;
        this.category = category;
        this.brand = brand;
        this.series = series;
        this.color = color;
        this.size = size;
        this.normalPrice = normalPrice;
        this.costPrice = costPrice;
        this.promotionPrice = promotionPrice;
        this.barCode = barCode;
        this.packageCondition = packageCondition;
        this.userClassification = userClassification;
        this.presell = presell;
        this.commissionSell = commissionSell;
        this.gift = gift;
        this.barCodeImage = barCodeImage;
        this.qrCodeImage = qrCodeImage;
        this.reserved1 = reserved1;
        this.note = note;
        this.authorized = authorized;
        this.itemSecretKey = itemSecretKey;
        this.ownerId = ownerId;
        this.shopId = shopId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.operatorId = operatorId;
        this.spec = spec;
        this.quantity = quantity;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemCommonInfoOrderItemLink that = (ItemCommonInfoOrderItemLink) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (erpItemNum != null ? !erpItemNum.equals(that.erpItemNum) : that.erpItemNum != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (platformId != null ? !platformId.equals(that.platformId) : that.platformId != null) return false;
        if (platformItemSku != null ? !platformItemSku.equals(that.platformItemSku) : that.platformItemSku != null)
            return false;
        if (warehouseId != null ? !warehouseId.equals(that.warehouseId) : that.warehouseId != null) return false;
        if (userShopCode != null ? !userShopCode.equals(that.userShopCode) : that.userShopCode != null) return false;
        if (userItemCode != null ? !userItemCode.equals(that.userItemCode) : that.userItemCode != null) return false;
        if (image1 != null ? !image1.equals(that.image1) : that.image1 != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (series != null ? !series.equals(that.series) : that.series != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (normalPrice != null ? !normalPrice.equals(that.normalPrice) : that.normalPrice != null) return false;
        if (costPrice != null ? !costPrice.equals(that.costPrice) : that.costPrice != null) return false;
        if (promotionPrice != null ? !promotionPrice.equals(that.promotionPrice) : that.promotionPrice != null)
            return false;
        if (barCode != null ? !barCode.equals(that.barCode) : that.barCode != null) return false;
        if (packageCondition != null ? !packageCondition.equals(that.packageCondition) : that.packageCondition != null)
            return false;
        if (userClassification != null ? !userClassification.equals(that.userClassification) : that.userClassification != null)
            return false;
        if (presell != null ? !presell.equals(that.presell) : that.presell != null) return false;
        if (commissionSell != null ? !commissionSell.equals(that.commissionSell) : that.commissionSell != null)
            return false;
        if (gift != null ? !gift.equals(that.gift) : that.gift != null) return false;
        if (barCodeImage != null ? !barCodeImage.equals(that.barCodeImage) : that.barCodeImage != null) return false;
        if (qrCodeImage != null ? !qrCodeImage.equals(that.qrCodeImage) : that.qrCodeImage != null) return false;
        if (reserved1 != null ? !reserved1.equals(that.reserved1) : that.reserved1 != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (authorized != null ? !authorized.equals(that.authorized) : that.authorized != null) return false;
        if (itemSecretKey != null ? !itemSecretKey.equals(that.itemSecretKey) : that.itemSecretKey != null)
            return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (shopId != null ? !shopId.equals(that.shopId) : that.shopId != null) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (spec != null ? !spec.equals(that.spec) : that.spec != null) return false;
        return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (erpItemNum != null ? erpItemNum.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (platformId != null ? platformId.hashCode() : 0);
        result = 31 * result + (platformItemSku != null ? platformItemSku.hashCode() : 0);
        result = 31 * result + (warehouseId != null ? warehouseId.hashCode() : 0);
        result = 31 * result + (userShopCode != null ? userShopCode.hashCode() : 0);
        result = 31 * result + (userItemCode != null ? userItemCode.hashCode() : 0);
        result = 31 * result + (image1 != null ? image1.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (normalPrice != null ? normalPrice.hashCode() : 0);
        result = 31 * result + (costPrice != null ? costPrice.hashCode() : 0);
        result = 31 * result + (promotionPrice != null ? promotionPrice.hashCode() : 0);
        result = 31 * result + (barCode != null ? barCode.hashCode() : 0);
        result = 31 * result + (packageCondition != null ? packageCondition.hashCode() : 0);
        result = 31 * result + (userClassification != null ? userClassification.hashCode() : 0);
        result = 31 * result + (presell != null ? presell.hashCode() : 0);
        result = 31 * result + (commissionSell != null ? commissionSell.hashCode() : 0);
        result = 31 * result + (gift != null ? gift.hashCode() : 0);
        result = 31 * result + (barCodeImage != null ? barCodeImage.hashCode() : 0);
        result = 31 * result + (qrCodeImage != null ? qrCodeImage.hashCode() : 0);
        result = 31 * result + (reserved1 != null ? reserved1.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (authorized != null ? authorized.hashCode() : 0);
        result = 31 * result + (itemSecretKey != null ? itemSecretKey.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (shopId != null ? shopId.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (spec != null ? spec.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemCommonInfoOrderItemLink{" +
                "id=" + id +
                ", erpItemNum='" + erpItemNum + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", platformId=" + platformId +
                ", platformItemSku=" + platformItemSku +
                ", warehouseId=" + warehouseId +
                ", userShopCode='" + userShopCode + '\'' +
                ", userItemCode='" + userItemCode + '\'' +
                ", image1='" + image1 + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", series='" + series + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", normalPrice=" + normalPrice +
                ", costPrice=" + costPrice +
                ", promotionPrice=" + promotionPrice +
                ", barCode='" + barCode + '\'' +
                ", packageCondition='" + packageCondition + '\'' +
                ", userClassification='" + userClassification + '\'' +
                ", presell=" + presell +
                ", commissionSell=" + commissionSell +
                ", gift=" + gift +
                ", barCodeImage='" + barCodeImage + '\'' +
                ", qrCodeImage='" + qrCodeImage + '\'' +
                ", reserved1='" + reserved1 + '\'' +
                ", note='" + note + '\'' +
                ", authorized=" + authorized +
                ", itemSecretKey='" + itemSecretKey + '\'' +
                ", ownerId=" + ownerId +
                ", shopId=" + shopId +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", operatorId=" + operatorId +
                ", spec='" + spec + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
