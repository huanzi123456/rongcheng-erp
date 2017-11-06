package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 系统商品对应关系关联表
 * @author 赵滨
 */
public class PlatformErpLink implements Serializable{

    //序列号
    private static final long serialVersionUID = -7280301959854021797L;

    //属性
    private BigInteger id;      //1.记录编号
    private BigInteger itemId;  //2.erp商品ID
    private BigInteger platformId;  //3.来源（平台）编码
    private BigInteger shopId;  //4.商品所属店铺ID
    private String platformShopId;  //5.平台店铺id
    private String platformShopName;  //6.店铺名称
    private BigInteger platformItemSku; //7.平台（来源）商品编码
    private BigInteger platformItemName;    //8.平台商品名称
    private String platformItemColor;   //9.平台商品颜色
    private String platformItemSize;    //10.平台商品尺码
    private String platform_item_state; //后加字段(薛宗艳):平台商品状态 长度10
    private String platformUserCode;    //11.平台商品卖家自定义编码
    private String platformUserImg; //12.平台商品主图
    private BigInteger warehouseId;    //13.erp商品所属仓库编码
    private BigInteger stocklocationId;    //14.erp商品所属库位编码
    private Integer autoSynchron;  //15.库存自动同步状况（开关键）
    private Integer autoOnsale;    //16.自动上架状况（开关键）
    private Integer synchronException; //17.例外情况（库存低于警戒值不同步）
    private Integer availableStock;    //18.库存可用量
    private Integer allocationRatio;   //19.库存分配比例
    private Integer remnantStock;  //20.库存零头
    private String reserved1;   //21.自定义内容
    private String note;    //22.备注
    private BigInteger ownerId; //23.用户主账户ID
    private BigInteger operatorId;  //24.操作人
    private Boolean authorized;  //25.该记录是否被授权
    private Timestamp gmtCreate;    //26.记录创建时间
    private Timestamp gmtModified;  //27.记录修改时间

    /**
     * 无参构造
     */
    public PlatformErpLink() {

    }
    /**
     * 有参构造
     */
    
	public PlatformErpLink(BigInteger id, BigInteger itemId, BigInteger platformId, BigInteger shopId, String platformShopId, String platformShopName, BigInteger platformItemSku, BigInteger platformItemName, String platformItemColor, String platformItemSize, String platform_item_state, String platformUserCode, String platformUserImg, BigInteger warehouseId, BigInteger stocklocationId, Integer autoSynchron, Integer autoOnsale, Integer synchronException, Integer availableStock, Integer allocationRatio, Integer remnantStock, String reserved1, String note, BigInteger ownerId, BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.platformId = platformId;
		this.shopId = shopId;
		this.platformShopId = platformShopId;
		this.platformShopName = platformShopName;
		this.platformItemSku = platformItemSku;
		this.platformItemName = platformItemName;
		this.platformItemColor = platformItemColor;
		this.platformItemSize = platformItemSize;
		this.platform_item_state = platform_item_state;
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
	public BigInteger getPlatformItemName() {
		return platformItemName;
	}
	public void setPlatformItemName(BigInteger platformItemName) {
		this.platformItemName = platformItemName;
	}
	public String getPlatformItemColor() {
		return platformItemColor;
	}
	public void setPlatformItemColor(String platformItemColor) {
		this.platformItemColor = platformItemColor;
	}
	public String getPlatformItemSize() {
		return platformItemSize;
	}
	public void setPlatformItemSize(String platformItemSize) {
		this.platformItemSize = platformItemSize;
	}
	public String getPlatform_item_state() {
		return platform_item_state;
	}
	public void setPlatform_item_state(String platform_item_state) {
		this.platform_item_state = platform_item_state;
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
    //重写equals   
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
		result = prime * result + ((platformItemColor == null) ? 0 : platformItemColor.hashCode());
		result = prime * result + ((platformItemName == null) ? 0 : platformItemName.hashCode());
		result = prime * result + ((platformItemSize == null) ? 0 : platformItemSize.hashCode());
		result = prime * result + ((platformItemSku == null) ? 0 : platformItemSku.hashCode());
		result = prime * result + ((platformShopId == null) ? 0 : platformShopId.hashCode());
		result = prime * result + ((platformShopName == null) ? 0 : platformShopName.hashCode());
		result = prime * result + ((platformUserCode == null) ? 0 : platformUserCode.hashCode());
		result = prime * result + ((platformUserImg == null) ? 0 : platformUserImg.hashCode());
		result = prime * result + ((platform_item_state == null) ? 0 : platform_item_state.hashCode());
		result = prime * result + ((remnantStock == null) ? 0 : remnantStock.hashCode());
		result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
		result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
		result = prime * result + ((stocklocationId == null) ? 0 : stocklocationId.hashCode());
		result = prime * result + ((synchronException == null) ? 0 : synchronException.hashCode());
		result = prime * result + ((warehouseId == null) ? 0 : warehouseId.hashCode());
		return result;
	}
	//重写hashCode
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
		if (platformItemColor == null) {
			if (other.platformItemColor != null)
				return false;
		} else if (!platformItemColor.equals(other.platformItemColor))
			return false;
		if (platformItemName == null) {
			if (other.platformItemName != null)
				return false;
		} else if (!platformItemName.equals(other.platformItemName))
			return false;
		if (platformItemSize == null) {
			if (other.platformItemSize != null)
				return false;
		} else if (!platformItemSize.equals(other.platformItemSize))
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
		if (platform_item_state == null) {
			if (other.platform_item_state != null)
				return false;
		} else if (!platform_item_state.equals(other.platform_item_state))
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
    //重写toString
	@Override
	public String toString() {
		return "PlatformErpLink [id=" + id + ", itemId=" + itemId + ", platformId=" + platformId + ", shopId=" + shopId + ", platformShopId=" + platformShopId + ", platformShopName=" + platformShopName + ", platformItemSku=" + platformItemSku + ", platformItemName=" + platformItemName + ", platformItemColor=" + platformItemColor + ", platformItemSize=" + platformItemSize + ", platform_item_state=" + platform_item_state + ", platformUserCode=" + platformUserCode + ", platformUserImg=" + platformUserImg + ", warehouseId=" + warehouseId + ", stocklocationId=" + stocklocationId + ", autoSynchron=" + autoSynchron + ", autoOnsale=" + autoOnsale + ", synchronException=" + synchronException + ", availableStock="
				+ availableStock + ", allocationRatio=" + allocationRatio + ", remnantStock=" + remnantStock + ", reserved1=" + reserved1 + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized=" + authorized + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}