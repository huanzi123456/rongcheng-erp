package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class CategoryInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6521232365997174681L;
    private BigInteger id;
    private String categoryName;
    private BigInteger parentId;
    private String note;
    private BigInteger ownerId;
    private BigInteger operatorId;
    private Boolean authorization;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    
    public CategoryInfo() {
        super();
    }
    public CategoryInfo(BigInteger id, String categoryName, BigInteger parentId, String note, BigInteger ownerId,
            BigInteger operatorId, Boolean authorization, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorization = authorization;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
    public BigInteger getId() {
        return id;
    }
    public void setId(BigInteger id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public BigInteger getParentId() {
        return parentId;
    }
    public void setParentId(BigInteger parentId) {
        this.parentId = parentId;
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
    public Boolean getAuthorization() {
        return authorization;
    }
    public void setAuthorization(Boolean authorization) {
        this.authorization = authorization;
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
        return "CategoryInfo [id=" + id + ", categoryName=" + categoryName + ", parentId=" + parentId + ", note=" + note
                + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorization=" + authorization
                + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorization == null) ? 0 : authorization.hashCode());
        result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
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
        CategoryInfo other = (CategoryInfo) obj;
        if (authorization == null) {
            if (other.authorization != null)
                return false;
        } else if (!authorization.equals(other.authorization))
            return false;
        if (categoryName == null) {
            if (other.categoryName != null)
                return false;
        } else if (!categoryName.equals(other.categoryName))
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
        if (parentId == null) {
            if (other.parentId != null)
                return false;
        } else if (!parentId.equals(other.parentId))
            return false;
        return true;
    }

}
