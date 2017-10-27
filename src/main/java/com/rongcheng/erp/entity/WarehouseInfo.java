package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * erp专用-仓库信息表 实体类
 * 
 * @author 赵滨
 */
public class WarehouseInfo implements Serializable{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1726676685017390363L;
    
    
    //1.ID
    private BigInteger id;
    
    //2.仓库用户自定义编码
    private String userWarehouseCode;
    
    //3.仓库名称
    private String warehouseName;
    
    //4.发货人姓名
    private String consignorName;
    
    //5.发货人电话
    private String consignorTel;
    
    //6.发货人手机
    private String consignorMobile;
    
    //7.发货地区id
    private BigInteger regionId;
    
    //8.仓库自定义发货地址
    private String userAddress;
    
    //9.该仓库是否被启用
    private Integer warehouseStatus;
    
    //10.云仓订单对接码
    private BigInteger connectionCode;
    
    //11.备注
    private String note;
    
    //12.用户主账户ID
    private BigInteger ownerId;
    
    //13.操作人
    private BigInteger operatorId;
    
    //14.该记录是否被授权
    private Boolean authorized;
    
    //15.记录创建时间
    private Timestamp gmtCreate;
    
    //16.记录修改时间
    private Timestamp gmtModified;

    /**
     * 无参构造
     */
    public WarehouseInfo() {
        super();
    }

    /**
     * 有参构造
     */
    public WarehouseInfo(BigInteger id, String userWarehouseCode, String warehouseName, String consignorName,
            String consignorTel, String consignorMobile, BigInteger regionId, String userAddress,
            Integer warehouseStatus, BigInteger connectionCode, String note, BigInteger ownerId, BigInteger operatorId,
            Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.userWarehouseCode = userWarehouseCode;
        this.warehouseName = warehouseName;
        this.consignorName = consignorName;
        this.consignorTel = consignorTel;
        this.consignorMobile = consignorMobile;
        this.regionId = regionId;
        this.userAddress = userAddress;
        this.warehouseStatus = warehouseStatus;
        this.connectionCode = connectionCode;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorized = authorized;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    //get和set方法
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUserWarehouseCode() {
        return userWarehouseCode;
    }

    public void setUserWarehouseCode(String userWarehouseCode) {
        this.userWarehouseCode = userWarehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public Integer getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(Integer warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }

    public BigInteger getConnectionCode() {
        return connectionCode;
    }

    public void setConnectionCode(BigInteger connectionCode) {
        this.connectionCode = connectionCode;
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

    /**
     * 重写hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorized == null) ? 0 : authorized.hashCode());
        result = prime * result + ((connectionCode == null) ? 0 : connectionCode.hashCode());
        result = prime * result + ((consignorMobile == null) ? 0 : consignorMobile.hashCode());
        result = prime * result + ((consignorName == null) ? 0 : consignorName.hashCode());
        result = prime * result + ((consignorTel == null) ? 0 : consignorTel.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
        result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
        result = prime * result + ((userWarehouseCode == null) ? 0 : userWarehouseCode.hashCode());
        result = prime * result + ((warehouseName == null) ? 0 : warehouseName.hashCode());
        result = prime * result + ((warehouseStatus == null) ? 0 : warehouseStatus.hashCode());
        return result;
    }

    /**
     * 重写equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WarehouseInfo other = (WarehouseInfo) obj;
        if (authorized == null) {
            if (other.authorized != null)
                return false;
        } else if (!authorized.equals(other.authorized))
            return false;
        if (connectionCode == null) {
            if (other.connectionCode != null)
                return false;
        } else if (!connectionCode.equals(other.connectionCode))
            return false;
        if (consignorMobile == null) {
            if (other.consignorMobile != null)
                return false;
        } else if (!consignorMobile.equals(other.consignorMobile))
            return false;
        if (consignorName == null) {
            if (other.consignorName != null)
                return false;
        } else if (!consignorName.equals(other.consignorName))
            return false;
        if (consignorTel == null) {
            if (other.consignorTel != null)
                return false;
        } else if (!consignorTel.equals(other.consignorTel))
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
        if (regionId == null) {
            if (other.regionId != null)
                return false;
        } else if (!regionId.equals(other.regionId))
            return false;
        if (userAddress == null) {
            if (other.userAddress != null)
                return false;
        } else if (!userAddress.equals(other.userAddress))
            return false;
        if (userWarehouseCode == null) {
            if (other.userWarehouseCode != null)
                return false;
        } else if (!userWarehouseCode.equals(other.userWarehouseCode))
            return false;
        if (warehouseName == null) {
            if (other.warehouseName != null)
                return false;
        } else if (!warehouseName.equals(other.warehouseName))
            return false;
        if (warehouseStatus == null) {
            if (other.warehouseStatus != null)
                return false;
        } else if (!warehouseStatus.equals(other.warehouseStatus))
            return false;
        return true;
    }

    /**
     * 重写toString
     */
    @Override
    public String toString() {
        return "WarehouseInfo [id=" + id + ", userWarehouseCode=" + userWarehouseCode + ", warehouseName="
                + warehouseName + ", consignorName=" + consignorName + ", consignorTel=" + consignorTel
                + ", consignorMobile=" + consignorMobile + ", regionId=" + regionId + ", userAddress=" + userAddress
                + ", warehouseStatus=" + warehouseStatus + ", connectionCode=" + connectionCode + ", note=" + note
                + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized=" + authorized + ", gmtCreate="
                + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
    
}
