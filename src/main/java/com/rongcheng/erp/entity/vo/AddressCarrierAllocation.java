package com.rongcheng.erp.entity.vo;

import java.math.BigInteger;

public class AddressCarrierAllocation {

    private BigInteger regionId;
    private BigInteger regionCode;
    private String name;
    private BigInteger parentId;
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
}
