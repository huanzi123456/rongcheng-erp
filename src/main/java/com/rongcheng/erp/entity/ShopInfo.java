package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 店铺信息表
 * @author 赵滨
 *
 */
public class ShopInfo implements Serializable{
    
    //版本号
    private static final long serialVersionUID = 5156738051939533263L;

    //1.店铺ID  非空  非负  长度20
    private BigInteger id;
    
    //2.店铺名称  非空  长度18
    private String name;
    
    //3.卖家平台账号  长度30
    private String sellerAccount;
    
    //4.来源平台ID  非负  长度20
    private BigInteger platformId;
    
    //5.自定义店铺编码  长度10  用于上传至平台的卖家编码处
    private String userShopCode;
    
    //6.店铺类型  长度9  与平台表店铺类型关联
    private String type;
    
    //7.店铺发货地区id   非负  长度20
    private BigInteger regionId;
    
    //8.店铺自定义发货地址  长度60
    private String userAddress;
    
    //9.邮编  非负  长度6
    private Integer zipCode;
    
    //10.联系人姓名  长度10
    private String contactName;
    
    //11.联系人电话  长度30
    private String contactTel;
    
    //12.联系人手机  长度30
    private String contactMobile;
    
    //13.店铺链接  长度128;
    private String shopUrl;
    
    //14.店铺logo  长度255
    private String logo;
    
    //15.授权到期日  datetime
    private Timestamp authorityDueDate;
    
    //16.自定义内容1  长度30
    private String reserved1;
    
    //17.备注 长度100
    private String note;
    
    //18.店铺秘钥 长度20
    private String shopSecretKey;
    
    //19.店铺ID条码图片  长度255
    private String barCodeImage;
    
    //20.店铺二维码图片  长度255
    private String qrCodeImage;
    
    //21.用户主账户ID  非空  非负  长度20  数据表所有者
    private BigInteger ownerId;
    
    //22.操作人  非空  非负  长度20
    private BigInteger operatorId;
    
    //23.是否授权  非空 非负  长度1
    private Boolean authorized;
    
    //24.记录创建时间  非空  datetime
    private Timestamp gmtCreate;
    
    //25.记录修改时间  datetime
    private Timestamp gmtModified;

    //26.是否启用
    private Boolean shopStatus;
    //无参数的构造器
    public ShopInfo() {
        super();
    }

    //有参数的构造器
    public ShopInfo(BigInteger id, String name, String sellerAccount, BigInteger platformId, String userShopCode,
			String type, BigInteger regionId, String userAddress, Integer zipCode, String contactName,
			String contactTel, String contactMobile, String shopUrl, String logo, Timestamp authorityDueDate,
			String reserved1, String note, String shopSecretKey, String barCodeImage, String qrCodeImage,
			BigInteger ownerId, BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified,
			Boolean shopStatus) {
		super();
		this.id = id;
		this.name = name;
		this.sellerAccount = sellerAccount;
		this.platformId = platformId;
		this.userShopCode = userShopCode;
		this.type = type;
		this.regionId = regionId;
		this.userAddress = userAddress;
		this.zipCode = zipCode;
		this.contactName = contactName;
		this.contactTel = contactTel;
		this.contactMobile = contactMobile;
		this.shopUrl = shopUrl;
		this.logo = logo;
		this.authorityDueDate = authorityDueDate;
		this.reserved1 = reserved1;
		this.note = note;
		this.shopSecretKey = shopSecretKey;
		this.barCodeImage = barCodeImage;
		this.qrCodeImage = qrCodeImage;
		this.ownerId = ownerId;
		this.operatorId = operatorId;
		this.authorized = authorized;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.shopStatus = shopStatus;
	}

    //创建的setter和getter方法
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

	public String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}

	public BigInteger getPlatformId() {
		return platformId;
	}

	public void setPlatformId(BigInteger platformId) {
		this.platformId = platformId;
	}

	public String getUserShopCode() {
		return userShopCode;
	}

	public void setUserShopCode(String userShopCode) {
		this.userShopCode = userShopCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Integer getzipCode() {
		return zipCode;
	}

	public void setzipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Timestamp getAuthorityDueDate() {
		return authorityDueDate;
	}

	public void setAuthorityDueDate(Timestamp authorityDueDate) {
		this.authorityDueDate = authorityDueDate;
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

	public String getShopSecretKey() {
		return shopSecretKey;
	}

	public void setShopSecretKey(String shopSecretKey) {
		this.shopSecretKey = shopSecretKey;
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

	public Boolean getShopStatus() {
		return shopStatus;
	}

	public void setShopStatus(Boolean shopStatus) {
		this.shopStatus = shopStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 //重写toString
	@Override
	public String toString() {
		return "ShopInfo [id=" + id + ", name=" + name + ", sellerAccount=" + sellerAccount + ", platformId="
				+ platformId + ", userShopCode=" + userShopCode + ", type=" + type + ", regionId=" + regionId
				+ ", userAddress=" + userAddress + ", zipCode=" + zipCode + ", contactName=" + contactName
				+ ", contactTel=" + contactTel + ", contactMobile=" + contactMobile + ", shopUrl=" + shopUrl + ", logo="
				+ logo + ", authorityDueDate=" + authorityDueDate + ", reserved1=" + reserved1 + ", note=" + note
				+ ", shopSecretKey=" + shopSecretKey + ", barCodeImage=" + barCodeImage + ", qrCodeImage=" + qrCodeImage
				+ ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized=" + authorized + ", gmtCreate="
				+ gmtCreate + ", gmtModified=" + gmtModified + ", shopStatus=" + shopStatus + "]";
	}

	//重写equals重写hashCode
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
		ShopInfo other = (ShopInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
