package com.rongcheng.erp.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ItemEspInfo {
private BigInteger id;
private String name;
private BigInteger platformId;
private BigInteger platformItemSku;
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
private BigInteger warehouseId;
private String reserved1;
private String reserved2;
private String note;
private Byte isAuthorized;
private BigInteger itemId;
private BigInteger ownerId;
private Integer shopId;
private Timestamp gmtCreate;
private Timestamp gmtModified;
private BigInteger operatorId;
public ItemEspInfo() {
	
}
public ItemEspInfo(BigInteger id, String name, BigInteger platformId, BigInteger platformItemSku, String spec,
		String image2, String image3, String image4, String image5, String image6, String unit, BigDecimal length,
		BigDecimal width, BigDecimal height, BigDecimal weight, String batchCode, Timestamp expireDate,
		String styleCode, BigInteger warehouseId, String reserved1, String reserved2, String note, Byte isAuthorized,
		BigInteger itemId, BigInteger ownerId, Integer shopId, Timestamp gmtCreate, Timestamp gmtModified,
		BigInteger operatorId) {
	super();
	this.id = id;
	this.name = name;
	this.platformId = platformId;
	this.platformItemSku = platformItemSku;
	this.spec = spec;
	this.image2 = image2;
	this.image3 = image3;
	this.image4 = image4;
	this.image5 = image5;
	this.image6 = image6;
	this.unit = unit;
	this.length = length;
	this.width = width;
	this.height = height;
	this.weight = weight;
	this.batchCode = batchCode;
	this.expireDate = expireDate;
	this.styleCode = styleCode;
	this.warehouseId = warehouseId;
	this.reserved1 = reserved1;
	this.reserved2 = reserved2;
	this.note = note;
	this.isAuthorized = isAuthorized;
	this.itemId = itemId;
	this.ownerId = ownerId;
	this.shopId = shopId;
	this.gmtCreate = gmtCreate;
	this.gmtModified = gmtModified;
	this.operatorId = operatorId;
}

public BigInteger getId() {
	return id;
}
public void setId(BigInteger id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public BigInteger getWarehouseId() {
	return warehouseId;
}
public void setWarehouseId(BigInteger warehouseId) {
	this.warehouseId = warehouseId;
}
public String getReserved1() {
	return reserved1;
}
public void setReserved1(String reserved1) {
	this.reserved1 = reserved1;
}
public String getReserved2() {
	return reserved2;
}
public void setReserved2(String reserved2) {
	this.reserved2 = reserved2;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
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
public BigInteger getOwnerId() {
	return ownerId;
}
public void setOwnerId(BigInteger ownerId) {
	this.ownerId = ownerId;
}
public Integer getShopId() {
	return shopId;
}
public void setShopId(Integer shopId) {
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

@Override
public String toString() {
	return "ItemEspInfo [id=" + id + ", name=" + name + ", platformId=" + platformId + ", platformItemSku="
			+ platformItemSku + ", spec=" + spec + ", image2=" + image2 + ", image3=" + image3 + ", image4=" + image4
			+ ", image5=" + image5 + ", image6=" + image6 + ", unit=" + unit + ", length=" + length + ", width=" + width
			+ ", height=" + height + ", weight=" + weight + ", batchCode=" + batchCode + ", expireDate=" + expireDate
			+ ", styleCode=" + styleCode + ", warehouseId=" + warehouseId + ", reserved1=" + reserved1 + ", reserved2="
			+ reserved2 + ", note=" + note + ", isAuthorized=" + isAuthorized + ", itemId=" + itemId + ", ownerId="
			+ ownerId + ", shopId=" + shopId + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified
			+ ", operatorId=" + operatorId + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	ItemEspInfo other = (ItemEspInfo) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}




}
