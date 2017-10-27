package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class BuyerInfo implements Serializable{
	private static final long serialVersionUID = -6284192828601055407L;
	private BigInteger id;
	private BigInteger platformId;
	private String buyerAccount;
	private String nickname;
	private String buyerName;
	private String consigneeName;
	private String consigneeTel;
	private String consigneeMobile;
	private BigInteger regionId;
	private String userAddress;
	private Integer zipCode;
	private String reserved1;
	private String note;
	private BigInteger ownerId;
	private BigInteger shopId;
	private BigInteger operatorId;
	private Boolean authorized;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;
	
	public BuyerInfo() {
	}
	public BuyerInfo(BigInteger id, BigInteger platformId, String buyerAccount, String nickname, String buyerName,
			String consigneeName, String consigneeTel, String consigneeMobile, BigInteger regionId, String userAddress,
			Integer zipCode, String reserved1, String note, BigInteger ownerId, BigInteger shopId,
			BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
		super();
		this.id = id;
		this.platformId = platformId;
		this.buyerAccount = buyerAccount;
		this.nickname = nickname;
		this.buyerName = buyerName;
		this.consigneeName = consigneeName;
		this.consigneeTel = consigneeTel;
		this.consigneeMobile = consigneeMobile;
		this.regionId = regionId;
		this.userAddress = userAddress;
		this.zipCode = zipCode;
		this.reserved1 = reserved1;
		this.note = note;
		this.ownerId = ownerId;
		this.shopId = shopId;
		this.operatorId = operatorId;
		this.authorized = authorized;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getPlatformId() {
		return platformId;
	}

	public void setPlatformId(BigInteger platformId) {
		this.platformId = platformId;
	}

	public String getBuyerAccount() {
		return buyerAccount;
	}

	public void setBuyerAccount(String buyerAccount) {
		this.buyerAccount = buyerAccount;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
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

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
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

	public BigInteger getShopId() {
		return shopId;
	}

	public void setShopId(BigInteger shopId) {
		this.shopId = shopId;
	}

	public BigInteger getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(BigInteger operatorId) {
		this.operatorId = operatorId;
	}

	public Boolean getAuthorized() {
		return authorized;
	}

	public void setAuthorized(Boolean authorized) {
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
		BuyerInfo other = (BuyerInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BuyerInfo [id=" + id + ", platformId=" + platformId + ", buyerAccount=" + buyerAccount + ", nickname="
				+ nickname + ", buyerName=" + buyerName + ", consigneeName=" + consigneeName + ", consigneeTel="
				+ consigneeTel + ", consigneeMobile=" + consigneeMobile + ", regionId=" + regionId + ", userAddress="
				+ userAddress + ", zipCode=" + zipCode + ", reserved1=" + reserved1 + ", note=" + note + ", ownerId="
				+ ownerId + ", shopId=" + shopId + ", operatorId=" + operatorId + ", authorized=" + authorized
				+ ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}

}