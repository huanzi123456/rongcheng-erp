package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ItemCategoryLink implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3283048002956969626L;
    /**
     * 
     */
    private BigInteger id;
    private BigInteger categoryId;
    private BigInteger itemId;
    private String note;
    private BigInteger ownerId;
    private BigInteger operatorId;
    private Boolean anthorization;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    
    public ItemCategoryLink() {
        super();
    }

    public ItemCategoryLink(BigInteger id, BigInteger categoryId, BigInteger itemId, String note, BigInteger ownerId,
            BigInteger operatorId, Boolean anthorization, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.categoryId = categoryId;
        this.itemId = itemId;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.anthorization = anthorization;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(BigInteger categoryId) {
        this.categoryId = categoryId;
    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
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

    public Boolean getAnthorization() {
        return anthorization;
    }

    public void setAnthorization(Boolean anthorization) {
        this.anthorization = anthorization;
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
    public String toString() {
        return "ItemCategoryLink [id=" + id + ", categoryId=" + categoryId + ", itemId=" + itemId + ", note=" + note
                + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", anthorization=" + anthorization
                + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
    
}
