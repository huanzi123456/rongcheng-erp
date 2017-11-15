package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.PlatformErpLink;
/**
 * 表PlatformErpLink 和  ItemCommonInfo表的关联查询 
 * @author 薛宗艳
 *
 */
public class PlatformErpLinkItemCommonInfo implements Serializable{
	private static final long serialVersionUID = -3715667209882781443L;
    private BigInteger platformErpLinkId;       //1.记录编号
    private String platformShopId;              //5.平台店铺id
    private String platformShopName;            //6.店铺名称
    private BigInteger platformItemSku;         //7.平台(来源)商品编码
    private String platformItemName;            //9.平台商品名称
    private String platformItemAttrvaluealias1; //11.平台商品属性别名1
    private String platformItemAttrvaluealias2; //14.平台商品属性别名2
    private String platformUserImg;             //18.平台商品主图
	private List<ItemCommonInfo> itemCommonInfo;
	public PlatformErpLinkItemCommonInfo() {
		super();
	}
	public PlatformErpLinkItemCommonInfo(BigInteger platformErpLinkId, String platformShopId, String platformShopName, BigInteger platformItemSku, String platformItemName, String platformItemAttrvaluealias1, String platformItemAttrvaluealias2, String platformUserImg, List<ItemCommonInfo> itemCommonInfo) {
		super();
		this.platformErpLinkId = platformErpLinkId;
		this.platformShopId = platformShopId;
		this.platformShopName = platformShopName;
		this.platformItemSku = platformItemSku;
		this.platformItemName = platformItemName;
		this.platformItemAttrvaluealias1 = platformItemAttrvaluealias1;
		this.platformItemAttrvaluealias2 = platformItemAttrvaluealias2;
		this.platformUserImg = platformUserImg;
		this.itemCommonInfo = itemCommonInfo;
	}
	public BigInteger getPlatformErpLinkId() {
		return platformErpLinkId;
	}
	public void setPlatformErpLinkId(BigInteger platformErpLinkId) {
		this.platformErpLinkId = platformErpLinkId;
	}
	public String getPlatformShopId() {
		return platformShopId;
	}
	public void setPlatformShopId(String platformShopId) {
		this.platformShopId = platformShopId;
	}
	public String getPlatformShopName() {
		return platformShopName;
	}
	public void setPlatformShopName(String platformShopName) {
		this.platformShopName = platformShopName;
	}
	public BigInteger getPlatformItemSku() {
		return platformItemSku;
	}
	public void setPlatformItemSku(BigInteger platformItemSku) {
		this.platformItemSku = platformItemSku;
	}
	public String getPlatformItemName() {
		return platformItemName;
	}
	public void setPlatformItemName(String platformItemName) {
		this.platformItemName = platformItemName;
	}
	public String getPlatformItemAttrvaluealias1() {
		return platformItemAttrvaluealias1;
	}
	public void setPlatformItemAttrvaluealias1(String platformItemAttrvaluealias1) {
		this.platformItemAttrvaluealias1 = platformItemAttrvaluealias1;
	}
	public String getPlatformItemAttrvaluealias2() {
		return platformItemAttrvaluealias2;
	}
	public void setPlatformItemAttrvaluealias2(String platformItemAttrvaluealias2) {
		this.platformItemAttrvaluealias2 = platformItemAttrvaluealias2;
	}
	public String getPlatformUserImg() {
		return platformUserImg;
	}
	public void setPlatformUserImg(String platformUserImg) {
		this.platformUserImg = platformUserImg;
	}
	public List<ItemCommonInfo> getItemCommonInfo() {
		return itemCommonInfo;
	}
	public void setItemCommonInfo(List<ItemCommonInfo> itemCommonInfo) {
		this.itemCommonInfo = itemCommonInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemCommonInfo == null) ? 0 : itemCommonInfo.hashCode());
		result = prime * result + ((platformErpLinkId == null) ? 0 : platformErpLinkId.hashCode());
		result = prime * result + ((platformItemAttrvaluealias1 == null) ? 0 : platformItemAttrvaluealias1.hashCode());
		result = prime * result + ((platformItemAttrvaluealias2 == null) ? 0 : platformItemAttrvaluealias2.hashCode());
		result = prime * result + ((platformItemName == null) ? 0 : platformItemName.hashCode());
		result = prime * result + ((platformItemSku == null) ? 0 : platformItemSku.hashCode());
		result = prime * result + ((platformShopId == null) ? 0 : platformShopId.hashCode());
		result = prime * result + ((platformShopName == null) ? 0 : platformShopName.hashCode());
		result = prime * result + ((platformUserImg == null) ? 0 : platformUserImg.hashCode());
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
		PlatformErpLinkItemCommonInfo other = (PlatformErpLinkItemCommonInfo) obj;
		if (itemCommonInfo == null) {
			if (other.itemCommonInfo != null)
				return false;
		} else if (!itemCommonInfo.equals(other.itemCommonInfo))
			return false;
		if (platformErpLinkId == null) {
			if (other.platformErpLinkId != null)
				return false;
		} else if (!platformErpLinkId.equals(other.platformErpLinkId))
			return false;
		if (platformItemAttrvaluealias1 == null) {
			if (other.platformItemAttrvaluealias1 != null)
				return false;
		} else if (!platformItemAttrvaluealias1.equals(other.platformItemAttrvaluealias1))
			return false;
		if (platformItemAttrvaluealias2 == null) {
			if (other.platformItemAttrvaluealias2 != null)
				return false;
		} else if (!platformItemAttrvaluealias2.equals(other.platformItemAttrvaluealias2))
			return false;
		if (platformItemName == null) {
			if (other.platformItemName != null)
				return false;
		} else if (!platformItemName.equals(other.platformItemName))
			return false;
		if (platformItemSku == null) {
			if (other.platformItemSku != null)
				return false;
		} else if (!platformItemSku.equals(other.platformItemSku))
			return false;
		if (platformShopId == null) {
			if (other.platformShopId != null)
				return false;
		} else if (!platformShopId.equals(other.platformShopId))
			return false;
		if (platformShopName == null) {
			if (other.platformShopName != null)
				return false;
		} else if (!platformShopName.equals(other.platformShopName))
			return false;
		if (platformUserImg == null) {
			if (other.platformUserImg != null)
				return false;
		} else if (!platformUserImg.equals(other.platformUserImg))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlatformErpLinkItemCommonInfo [platformErpLinkId=" + platformErpLinkId + ", platformShopId=" + platformShopId + ", platformShopName=" + platformShopName + ", platformItemSku=" + platformItemSku + ", platformItemName=" + platformItemName + ", platformItemAttrvaluealias1=" + platformItemAttrvaluealias1 + ", platformItemAttrvaluealias2=" + platformItemAttrvaluealias2 + ", platformUserImg=" + platformUserImg + ", itemCommonInfo=" + itemCommonInfo + "]";
	}
}