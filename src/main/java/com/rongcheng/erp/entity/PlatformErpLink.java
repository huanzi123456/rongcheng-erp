package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 系统商品对应关系关联表
 * @author 薛宗艳
 */
public class PlatformErpLink implements Serializable{

    //序列号
    private static final long serialVersionUID = -7280301959854021797L;
    
    //属性
    private BigInteger id;                      //1.记录编号
    private BigInteger itemId;                  //2.erp商品ID
    private BigInteger platformId;              //3.来源（平台）编码
    private BigInteger shopId;                  //4.商品所属店铺ID
    private String platformShopId;              //5.平台店铺id
    private String platformShopName;            //6.店铺名称
    private BigInteger platformItemSku;         //7.平台(来源)商品编码
    private BigInteger platformItemWareid;      //8.京东商品wareid	
    private String platformItemName;            //9.平台商品名称
    private String platformItemAttrid1;         //10平台商品属性id1	
    private String platformItemAttrvaluealias1; //11.平台商品属性别名1	
    private String platformItemAttrvalue1;      //12.平台商品属性值1   
    private String platformItemAttrid2;         //13.平台商品属性id2	
    private String platformItemAttrvaluealias2; //14.平台商品属性别名2
    private String platformItemAttrvalue2;      //15.平台商品属性值2    	
    private String platformItemState;           //16.平台商品状态  1:上架 2:下架 4:删除 
    private String platformUserCode;            //17.平台商品卖家自定义编码
    private String platformUserImg;             //18.平台商品主图
    private BigInteger warehouseId;             //19.erp商品所属仓库编码
    private BigInteger stocklocationId;         //20.erp商品所属库位编码
    private Integer autoSynchron;               //21.库存自动同步状况（开关键）
    private Integer autoOnsale;                 //22.自动上架状况（开关键）
    private Integer synchronException;          //23.例外情况（库存低于警戒值不同步）
    private Integer availableStock;             //24.库存可用量
    private Integer allocationRatio;            //25.库存分配比例
    private Integer remnantStock;               //26.库存零头
    private String reserved1;                   //27.自定义内容
    private String note;                        //28.备注
    private BigInteger ownerId;                 //29.用户主账户ID
    private BigInteger operatorId;              //30.操作人
    private Boolean authorized;                 //31.该记录是否被授权
    private Timestamp gmtCreate;                //32.记录创建时间
    private Timestamp gmtModified;              //33.记录修改时间
	public PlatformErpLink() {
		super();
	}
	public PlatformErpLink(BigInteger id, BigInteger itemId, BigInteger platformId, BigInteger shopId, String platformShopId, String platformShopName, BigInteger platformItemSku, BigInteger platformItemWareid, String platformItemName, String platformItemAttrid1, String platformItemAttrvaluealias1, String platformItemAttrvalue1, String platformItemAttrid2, String platformItemAttrvaluealias2, String platformItemAttrvalue2, String platformItemState, String platformUserCode, String platformUserImg, BigInteger warehouseId, BigInteger stocklocationId, Integer autoSynchron, Integer autoOnsale, Integer synchronException, Integer availableStock, Integer allocationRatio, Integer remnantStock, String reserved1,
			String note, BigInteger ownerId, BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.platformId = platformId;
		this.shopId = shopId;
		this.platformShopId = platformShopId;
		this.platformShopName = platformShopName;
		this.platformItemSku = platformItemSku;
		this.platformItemWareid = platformItemWareid;
		this.platformItemName = platformItemName;
		this.platformItemAttrid1 = platformItemAttrid1;
		this.platformItemAttrvaluealias1 = platformItemAttrvaluealias1;
		this.platformItemAttrvalue1 = platformItemAttrvalue1;
		this.platformItemAttrid2 = platformItemAttrid2;
		this.platformItemAttrvaluealias2 = platformItemAttrvaluealias2;
		this.platformItemAttrvalue2 = platformItemAttrvalue2;
		this.platformItemState = platformItemState;
		this.platformUserCode = platformUserCode;
		this.platformUserImg = platformUserImg;
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
	}
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
	public BigInteger getPlatformItemWareid() {
		return platformItemWareid;
	}
	public void setPlatformItemWareid(BigInteger platformItemWareid) {
		this.platformItemWareid = platformItemWareid;
	}
	public String getPlatformItemName() {
		return platformItemName;
	}
	public void setPlatformItemName(String platformItemName) {
		this.platformItemName = platformItemName;
	}
	public String getPlatformItemAttrid1() {
		return platformItemAttrid1;
	}
	public void setPlatformItemAttrid1(String platformItemAttrid1) {
		this.platformItemAttrid1 = platformItemAttrid1;
	}
	public String getPlatformItemAttrvaluealias1() {
		return platformItemAttrvaluealias1;
	}
	public void setPlatformItemAttrvaluealias1(String platformItemAttrvaluealias1) {
		this.platformItemAttrvaluealias1 = platformItemAttrvaluealias1;
	}
	public String getPlatformItemAttrvalue1() {
		return platformItemAttrvalue1;
	}
	public void setPlatformItemAttrvalue1(String platformItemAttrvalue1) {
		this.platformItemAttrvalue1 = platformItemAttrvalue1;
	}
	public String getPlatformItemAttrid2() {
		return platformItemAttrid2;
	}
	public void setPlatformItemAttrid2(String platformItemAttrid2) {
		this.platformItemAttrid2 = platformItemAttrid2;
	}
	public String getPlatformItemAttrvaluealias2() {
		return platformItemAttrvaluealias2;
	}
	public void setPlatformItemAttrvaluealias2(String platformItemAttrvaluealias2) {
		this.platformItemAttrvaluealias2 = platformItemAttrvaluealias2;
	}
	public String getPlatformItemAttrvalue2() {
		return platformItemAttrvalue2;
	}
	public void setPlatformItemAttrvalue2(String platformItemAttrvalue2) {
		this.platformItemAttrvalue2 = platformItemAttrvalue2;
	}
	public String getPlatformItemState() {
		return platformItemState;
	}
	public void setPlatformItemState(String platformItemState) {
		this.platformItemState = platformItemState;
	}
	public String getPlatformUserCode() {
		return platformUserCode;
	}
	public void setPlatformUserCode(String platformUserCode) {
		this.platformUserCode = platformUserCode;
	}
	public String getPlatformUserImg() {
		return platformUserImg;
	}
	public void setPlatformUserImg(String platformUserImg) {
		this.platformUserImg = platformUserImg;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allocationRatio == null) ? 0 : allocationRatio.hashCode());
		result = prime * result + ((authorized == null) ? 0 : authorized.hashCode());
		result = prime * result + ((autoOnsale == null) ? 0 : autoOnsale.hashCode());
		result = prime * result + ((autoSynchron == null) ? 0 : autoSynchron.hashCode());
		result = prime * result + ((availableStock == null) ? 0 : availableStock.hashCode());
		result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
		result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
		result = prime * result + ((platformItemAttrid1 == null) ? 0 : platformItemAttrid1.hashCode());
		result = prime * result + ((platformItemAttrid2 == null) ? 0 : platformItemAttrid2.hashCode());
		result = prime * result + ((platformItemAttrvalue1 == null) ? 0 : platformItemAttrvalue1.hashCode());
		result = prime * result + ((platformItemAttrvalue2 == null) ? 0 : platformItemAttrvalue2.hashCode());
		result = prime * result + ((platformItemAttrvaluealias1 == null) ? 0 : platformItemAttrvaluealias1.hashCode());
		result = prime * result + ((platformItemAttrvaluealias2 == null) ? 0 : platformItemAttrvaluealias2.hashCode());
		result = prime * result + ((platformItemName == null) ? 0 : platformItemName.hashCode());
		result = prime * result + ((platformItemSku == null) ? 0 : platformItemSku.hashCode());
		result = prime * result + ((platformItemState == null) ? 0 : platformItemState.hashCode());
		result = prime * result + ((platformItemWareid == null) ? 0 : platformItemWareid.hashCode());
		result = prime * result + ((platformShopId == null) ? 0 : platformShopId.hashCode());
		result = prime * result + ((platformShopName == null) ? 0 : platformShopName.hashCode());
		result = prime * result + ((platformUserCode == null) ? 0 : platformUserCode.hashCode());
		result = prime * result + ((platformUserImg == null) ? 0 : platformUserImg.hashCode());
		result = prime * result + ((remnantStock == null) ? 0 : remnantStock.hashCode());
		result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((stocklocationId == null) ? 0 : stocklocationId.hashCode());
		result = prime * result + ((synchronException == null) ? 0 : synchronException.hashCode());
		result = prime * result + ((warehouseId == null) ? 0 : warehouseId.hashCode());
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
		PlatformErpLink other = (PlatformErpLink) obj;
		if (allocationRatio == null) {
			if (other.allocationRatio != null)
				return false;
		} else if (!allocationRatio.equals(other.allocationRatio))
			return false;
		if (authorized == null) {
			if (other.authorized != null)
				return false;
		} else if (!authorized.equals(other.authorized))
			return false;
		if (autoOnsale == null) {
			if (other.autoOnsale != null)
				return false;
		} else if (!autoOnsale.equals(other.autoOnsale))
			return false;
		if (autoSynchron == null) {
			if (other.autoSynchron != null)
				return false;
		} else if (!autoSynchron.equals(other.autoSynchron))
			return false;
		if (availableStock == null) {
			if (other.availableStock != null)
				return false;
		} else if (!availableStock.equals(other.availableStock))
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
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
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
		if (platformItemAttrid1 == null) {
			if (other.platformItemAttrid1 != null)
				return false;
		} else if (!platformItemAttrid1.equals(other.platformItemAttrid1))
			return false;
		if (platformItemAttrid2 == null) {
			if (other.platformItemAttrid2 != null)
				return false;
		} else if (!platformItemAttrid2.equals(other.platformItemAttrid2))
			return false;
		if (platformItemAttrvalue1 == null) {
			if (other.platformItemAttrvalue1 != null)
				return false;
		} else if (!platformItemAttrvalue1.equals(other.platformItemAttrvalue1))
			return false;
		if (platformItemAttrvalue2 == null) {
			if (other.platformItemAttrvalue2 != null)
				return false;
		} else if (!platformItemAttrvalue2.equals(other.platformItemAttrvalue2))
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
		if (platformItemState == null) {
			if (other.platformItemState != null)
				return false;
		} else if (!platformItemState.equals(other.platformItemState))
			return false;
		if (platformItemWareid == null) {
			if (other.platformItemWareid != null)
				return false;
		} else if (!platformItemWareid.equals(other.platformItemWareid))
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
		if (platformUserCode == null) {
			if (other.platformUserCode != null)
				return false;
		} else if (!platformUserCode.equals(other.platformUserCode))
			return false;
		if (platformUserImg == null) {
			if (other.platformUserImg != null)
				return false;
		} else if (!platformUserImg.equals(other.platformUserImg))
			return false;
		if (remnantStock == null) {
			if (other.remnantStock != null)
				return false;
		} else if (!remnantStock.equals(other.remnantStock))
			return false;
		if (reserved1 == null) {
			if (other.reserved1 != null)
				return false;
		} else if (!reserved1.equals(other.reserved1))
			return false;
		if (shopId == null) {
			if (other.shopId != null)
				return false;
		} else if (!shopId.equals(other.shopId))
			return false;
		if (stocklocationId == null) {
			if (other.stocklocationId != null)
				return false;
		} else if (!stocklocationId.equals(other.stocklocationId))
			return false;
		if (synchronException == null) {
			if (other.synchronException != null)
				return false;
		} else if (!synchronException.equals(other.synchronException))
			return false;
		if (warehouseId == null) {
			if (other.warehouseId != null)
				return false;
		} else if (!warehouseId.equals(other.warehouseId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PlatformErpLink [id=" + id + ", itemId=" + itemId + ", platformId=" + platformId + ", shopId=" + shopId + ", platformShopId=" + platformShopId + ", platformShopName=" + platformShopName + ", platformItemSku=" + platformItemSku + ", platformItemWareid=" + platformItemWareid + ", platformItemName=" + platformItemName + ", platformItemAttrid1=" + platformItemAttrid1 + ", platformItemAttrvaluealias1=" + platformItemAttrvaluealias1 + ", platformItemAttrvalue1=" + platformItemAttrvalue1 + ", platformItemAttrid2=" + platformItemAttrid2 + ", platformItemAttrvaluealias2=" + platformItemAttrvaluealias2 + ", platformItemAttrvalue2=" + platformItemAttrvalue2 + ", platformItemState=" + platformItemState
				+ ", platformUserCode=" + platformUserCode + ", platformUserImg=" + platformUserImg + ", warehouseId=" + warehouseId + ", stocklocationId=" + stocklocationId + ", autoSynchron=" + autoSynchron + ", autoOnsale=" + autoOnsale + ", synchronException=" + synchronException + ", availableStock=" + availableStock + ", allocationRatio=" + allocationRatio + ", remnantStock=" + remnantStock + ", reserved1=" + reserved1 + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized=" + authorized + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}