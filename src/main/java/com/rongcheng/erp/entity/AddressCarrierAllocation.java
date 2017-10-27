package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

public class AddressCarrierAllocation implements Serializable {
	private static final long serialVersionUID = 7241188594049756267L;
	private BigInteger regionId;
	private BigInteger regionCode;
	private String regionName;
	private BigInteger parentId;
	private Byte regionLevel;
	private Integer regionOrder;
	private String regionNameEn;
	private String regionShort;
	private BigInteger carrierId;
	private BigDecimal headFee;
	private BigDecimal repeatFee;
	private String reserved1;
	private String note;
	private BigInteger ownerId;
	private BigInteger operatorId;
	private Boolean authorized;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;

	public AddressCarrierAllocation() {
		// TODO Auto-generated constructor stub
	}

    public AddressCarrierAllocation(BigInteger regionId, BigInteger regionCode, String regionName, BigInteger parentId,
            Byte regionLevel, Integer regionOrder, String regionNameEn, String regionShort, BigInteger carrierId,
            BigDecimal headFee, BigDecimal repeatFee, String reserved1, String note, BigInteger ownerId,
            BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.regionId = regionId;
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.parentId = parentId;
        this.regionLevel = regionLevel;
        this.regionOrder = regionOrder;
        this.regionNameEn = regionNameEn;
        this.regionShort = regionShort;
        this.carrierId = carrierId;
        this.headFee = headFee;
        this.repeatFee = repeatFee;
        this.reserved1 = reserved1;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorized = authorized;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public BigInteger getRegionId() {
        return regionId;
    }

    public void setRegionId(BigInteger regionId) {
        this.regionId = regionId;
    }

    public BigInteger getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(BigInteger regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public BigInteger getParentId() {
        return parentId;
    }

    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }

    public Byte getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Byte regionLevel) {
        this.regionLevel = regionLevel;
    }

    public Integer getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(Integer regionOrder) {
        this.regionOrder = regionOrder;
    }

    public String getRegionNameEn() {
        return regionNameEn;
    }

    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn;
    }

    public String getRegionShort() {
        return regionShort;
    }

    public void setRegionShort(String regionShort) {
        this.regionShort = regionShort;
    }

    public BigInteger getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(BigInteger carrierId) {
        this.carrierId = carrierId;
    }

    public BigDecimal getHeadFee() {
        return headFee;
    }

    public void setHeadFee(BigDecimal headFee) {
        this.headFee = headFee;
    }

    public BigDecimal getRepeatFee() {
        return repeatFee;
    }

    public void setRepeatFee(BigDecimal repeatFee) {
        this.repeatFee = repeatFee;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorized == null) ? 0 : authorized.hashCode());
        result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((headFee == null) ? 0 : headFee.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
        result = prime * result + ((regionCode == null) ? 0 : regionCode.hashCode());
        result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
        result = prime * result + ((regionLevel == null) ? 0 : regionLevel.hashCode());
        result = prime * result + ((regionName == null) ? 0 : regionName.hashCode());
        result = prime * result + ((regionNameEn == null) ? 0 : regionNameEn.hashCode());
        result = prime * result + ((regionOrder == null) ? 0 : regionOrder.hashCode());
        result = prime * result + ((regionShort == null) ? 0 : regionShort.hashCode());
        result = prime * result + ((repeatFee == null) ? 0 : repeatFee.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
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
        AddressCarrierAllocation other = (AddressCarrierAllocation) obj;
        if (authorized == null) {
            if (other.authorized != null)
                return false;
        } else if (!authorized.equals(other.authorized))
            return false;
        if (carrierId == null) {
            if (other.carrierId != null)
                return false;
        } else if (!carrierId.equals(other.carrierId))
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
        if (headFee == null) {
            if (other.headFee != null)
                return false;
        } else if (!headFee.equals(other.headFee))
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
        if (parentId == null) {
            if (other.parentId != null)
                return false;
        } else if (!parentId.equals(other.parentId))
            return false;
        if (regionCode == null) {
            if (other.regionCode != null)
                return false;
        } else if (!regionCode.equals(other.regionCode))
            return false;
        if (regionId == null) {
            if (other.regionId != null)
                return false;
        } else if (!regionId.equals(other.regionId))
            return false;
        if (regionLevel == null) {
            if (other.regionLevel != null)
                return false;
        } else if (!regionLevel.equals(other.regionLevel))
            return false;
        if (regionName == null) {
            if (other.regionName != null)
                return false;
        } else if (!regionName.equals(other.regionName))
            return false;
        if (regionNameEn == null) {
            if (other.regionNameEn != null)
                return false;
        } else if (!regionNameEn.equals(other.regionNameEn))
            return false;
        if (regionOrder == null) {
            if (other.regionOrder != null)
                return false;
        } else if (!regionOrder.equals(other.regionOrder))
            return false;
        if (regionShort == null) {
            if (other.regionShort != null)
                return false;
        } else if (!regionShort.equals(other.regionShort))
            return false;
        if (repeatFee == null) {
            if (other.repeatFee != null)
                return false;
        } else if (!repeatFee.equals(other.repeatFee))
            return false;
        if (reserved1 == null) {
            if (other.reserved1 != null)
                return false;
        } else if (!reserved1.equals(other.reserved1))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AddressCarrierAllocation [regionId=" + regionId + ", regionCode=" + regionCode + ", regionName="
                + regionName + ", parentId=" + parentId + ", regionLevel=" + regionLevel + ", regionOrder="
                + regionOrder + ", regionNameEn=" + regionNameEn + ", regionShort=" + regionShort + ", carrierId="
                + carrierId + ", headFee=" + headFee + ", repeatFee=" + repeatFee + ", reserved1=" + reserved1
                + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized="
                + authorized + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }

}
