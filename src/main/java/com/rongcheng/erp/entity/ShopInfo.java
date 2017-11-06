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
    
    //后加字段(薛宗艳 ):平台店铺id 长度20
    private String platform_shop_id;
    
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
    public ShopInfo(BigInteger id, String name, String sellerAccount, BigInteger platformId, String platform_shop_id, String userShopCode, String type, BigInteger regionId, String userAddress, Integer zipCode, String contactName, String contactTel, String contactMobile, String shopUrl, String logo, Timestamp authorityDueDate, String reserved1, String note, String shopSecretKey, String barCodeImage, String qrCodeImage, BigInteger ownerId, BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified, Boolean shopStatus) {
		super();
		this.id = id;
		this.name = name;
		this.sellerAccount = sellerAccount;
		this.platformId = platformId;
		this.platform_shop_id = platform_shop_id;
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

	public String getPlatform_shop_id() {
		return platform_shop_id;
	}

	public void setPlatform_shop_id(String platform_shop_id) {
		this.platform_shop_id = platform_shop_id;
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

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
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
		return "ShopInfo [id=" + id + ", name=" + name + ", sellerAccount=" + sellerAccount + ", platformId=" + platformId + ", platform_shop_id=" + platform_shop_id + ", userShopCode=" + userShopCode + ", type=" + type + ", regionId=" + regionId + ", userAddress=" + userAddress + ", zipCode=" + zipCode + ", contactName=" + contactName + ", contactTel=" + contactTel + ", contactMobile=" + contactMobile + ", shopUrl=" + shopUrl + ", logo=" + logo + ", authorityDueDate=" + authorityDueDate + ", reserved1=" + reserved1 + ", note=" + note + ", shopSecretKey=" + shopSecretKey + ", barCodeImage=" + barCodeImage + ", qrCodeImage=" + qrCodeImage + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized="
				+ authorized + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", shopStatus=" + shopStatus + "]";
	}
	//重写equals重写hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityDueDate == null) ? 0 : authorityDueDate.hashCode());
		result = prime * result + ((authorized == null) ? 0 : authorized.hashCode());
		result = prime * result + ((barCodeImage == null) ? 0 : barCodeImage.hashCode());
		result = prime * result + ((contactMobile == null) ? 0 : contactMobile.hashCode());
		result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result + ((contactTel == null) ? 0 : contactTel.hashCode());
		result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
		result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
		result = prime * result + ((platform_shop_id == null) ? 0 : platform_shop_id.hashCode());
		result = prime * result + ((qrCodeImage == null) ? 0 : qrCodeImage.hashCode());
		result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
		result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
		result = prime * result + ((sellerAccount == null) ? 0 : sellerAccount.hashCode());
		result = prime * result + ((shopSecretKey == null) ? 0 : shopSecretKey.hashCode());
		result = prime * result + ((shopStatus == null) ? 0 : shopStatus.hashCode());
		result = prime * result + ((shopUrl == null) ? 0 : shopUrl.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
		result = prime * result + ((userShopCode == null) ? 0 : userShopCode.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		if (authorityDueDate == null) {
			if (other.authorityDueDate != null)
				return false;
		} else if (!authorityDueDate.equals(other.authorityDueDate))
			return false;
		if (authorized == null) {
			if (other.authorized != null)
				return false;
		} else if (!authorized.equals(other.authorized))
			return false;
		if (barCodeImage == null) {
			if (other.barCodeImage != null)
				return false;
		} else if (!barCodeImage.equals(other.barCodeImage))
			return false;
		if (contactMobile == null) {
			if (other.contactMobile != null)
				return false;
		} else if (!contactMobile.equals(other.contactMobile))
			return false;
		if (contactName == null) {
			if (other.contactName != null)
				return false;
		} else if (!contactName.equals(other.contactName))
			return false;
		if (contactTel == null) {
			if (other.contactTel != null)
				return false;
		} else if (!contactTel.equals(other.contactTel))
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
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		if (platformId == null) {
			if (other.platformId != null)
				return false;
		} else if (!platformId.equals(other.platformId))
			return false;
		if (platform_shop_id == null) {
			if (other.platform_shop_id != null)
				return false;
		} else if (!platform_shop_id.equals(other.platform_shop_id))
			return false;
		if (qrCodeImage == null) {
			if (other.qrCodeImage != null)
				return false;
		} else if (!qrCodeImage.equals(other.qrCodeImage))
			return false;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		if (reserved1 == null) {
			if (other.reserved1 != null)
				return false;
		} else if (!reserved1.equals(other.reserved1))
			return false;
		if (sellerAccount == null) {
			if (other.sellerAccount != null)
				return false;
		} else if (!sellerAccount.equals(other.sellerAccount))
			return false;
		if (shopSecretKey == null) {
			if (other.shopSecretKey != null)
				return false;
		} else if (!shopSecretKey.equals(other.shopSecretKey))
			return false;
		if (shopStatus == null) {
			if (other.shopStatus != null)
				return false;
		} else if (!shopStatus.equals(other.shopStatus))
			return false;
		if (shopUrl == null) {
			if (other.shopUrl != null)
				return false;
		} else if (!shopUrl.equals(other.shopUrl))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userAddress == null) {
			if (other.userAddress != null)
				return false;
		} else if (!userAddress.equals(other.userAddress))
			return false;
		if (userShopCode == null) {
			if (other.userShopCode != null)
				return false;
		} else if (!userShopCode.equals(other.userShopCode))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}	
}