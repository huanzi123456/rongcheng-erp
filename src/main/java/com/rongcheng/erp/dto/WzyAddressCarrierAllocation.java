package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class WzyAddressCarrierAllocation implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5508709265996294289L;
    private BigInteger regionId;
    private BigInteger regionCode;
    private String name;
    private BigInteger parentId;
   
    public WzyAddressCarrierAllocation() {
        super();
    }
    public WzyAddressCarrierAllocation(BigInteger regionId, BigInteger regionCode, String name, BigInteger parentId) {
        super();
        this.regionId = regionId;
        this.regionCode = regionCode;
        this.name = name;
        this.parentId = parentId;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigInteger getParentId() {
        return parentId;
    }
    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
    }
    @Override
    public String toString() {
        return "AddressCarrierAllocation [regionId=" + regionId + ", regionCode=" + regionCode + ", name="
                + name + ", parentId=" + parentId + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
        result = prime * result + ((regionCode == null) ? 0 : regionCode.hashCode());
        result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
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
        WzyAddressCarrierAllocation other = (WzyAddressCarrierAllocation) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
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
        return true;
    }

    
}
