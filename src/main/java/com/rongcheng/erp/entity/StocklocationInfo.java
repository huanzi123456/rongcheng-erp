package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * erp专用-仓库信息表 实体类
 * 
 * @author 赵滨
 */
public class StocklocationInfo implements Serializable{

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8609233885029537941L;

    //1.ID
    private BigInteger id;
    
    //2.仓库编码
    private BigInteger warehouseId;
    
    //3.用户自定义库位编码
    private String userStocklocationCode;
    
    //4.库位名称
    private String name;
    
    //5.该库位是否被启用
    private Integer stocklocationStatus;
    
    //6.云仓订单对接码
    private BigInteger connectionCode;
    
    //7.配送区域
    private BigInteger distributionRegion;
    
    //8.备注
    private String note;
    
    //9.用户主账户ID
    private BigInteger ownerId;
    
    //10.操作人
    private BigInteger operatorId;
    
    //11.该记录是否被授权
    private Boolean authorized;
    
    //12.记录创建时间
    private Timestamp gmtCreate;
    
    //13.记录修改时间
    private Timestamp gmtModified;

    /**
     * 无参构造
     */
    public StocklocationInfo() {
        super();
    }
    
    /**
     * 有参构造
     */
    public StocklocationInfo(BigInteger id, BigInteger warehouseId, String userStocklocationCode, String name,
            Integer stocklocationStatus, BigInteger connectionCode, BigInteger distributionRegion, String note,
            BigInteger ownerId, BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.warehouseId = warehouseId;
        this.userStocklocationCode = userStocklocationCode;
        this.name = name;
        this.stocklocationStatus = stocklocationStatus;
        this.connectionCode = connectionCode;
        this.distributionRegion = distributionRegion;
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

    public BigInteger getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(BigInteger warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getUserStocklocationCode() {
        return userStocklocationCode;
    }

    public void setUserStocklocationCode(String userStocklocationCode) {
        this.userStocklocationCode = userStocklocationCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStocklocationStatus() {
        return stocklocationStatus;
    }

    public void setStocklocationStatus(Integer stocklocationStatus) {
        this.stocklocationStatus = stocklocationStatus;
    }

    public BigInteger getConnectionCode() {
        return connectionCode;
    }

    public void setConnectionCode(BigInteger connectionCode) {
        this.connectionCode = connectionCode;
    }

    public BigInteger getDistributionRegion() {
        return distributionRegion;
    }

    public void setDistributionRegion(BigInteger distributionRegion) {
        this.distributionRegion = distributionRegion;
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
    
    /**
     * 重写hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorized == null) ? 0 : authorized.hashCode());
        result = prime * result + ((connectionCode == null) ? 0 : connectionCode.hashCode());
        result = prime * result + ((distributionRegion == null) ? 0 : distributionRegion.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((stocklocationStatus == null) ? 0 : stocklocationStatus.hashCode());
        result = prime * result + ((userStocklocationCode == null) ? 0 : userStocklocationCode.hashCode());
        result = prime * result + ((warehouseId == null) ? 0 : warehouseId.hashCode());
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
        StocklocationInfo other = (StocklocationInfo) obj;
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
        if (distributionRegion == null) {
            if (other.distributionRegion != null)
                return false;
        } else if (!distributionRegion.equals(other.distributionRegion))
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
        if (stocklocationStatus == null) {
            if (other.stocklocationStatus != null)
                return false;
        } else if (!stocklocationStatus.equals(other.stocklocationStatus))
            return false;
        if (userStocklocationCode == null) {
            if (other.userStocklocationCode != null)
                return false;
        } else if (!userStocklocationCode.equals(other.userStocklocationCode))
            return false;
        if (warehouseId == null) {
            if (other.warehouseId != null)
                return false;
        } else if (!warehouseId.equals(other.warehouseId))
            return false;
        return true;
    }
    
    /**
     * 重写toString
     */
    @Override
    public String toString() {
        return "StocklocationInfo [id=" + id + ", warehouseId=" + warehouseId + ", userStocklocationCode="
                + userStocklocationCode + ", name=" + name + ", stocklocationStatus=" + stocklocationStatus
                + ", connectionCode=" + connectionCode + ", distributionRegion=" + distributionRegion + ", note=" + note
                + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized=" + authorized + ", gmtCreate="
                + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }

}
