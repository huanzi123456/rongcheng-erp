package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 来源平台信息表
 * @author 赵滨
 *
 */
public class PlatformInfo implements Serializable{
    
    //版本号
    private static final long serialVersionUID = 6339492588832569133L;

    //1.平台ID  非空  非负  长度20
    private BigInteger id;
    
    //2.平台名称  非空  长度10
    private String name;
    
    //3.平台链接  长度255
    private String platformUrl;
    
    //4.平台店铺类型 长度9
    private String platformShopType;
    
    //5.自定义内容1 长度30
    private String reserved1;
    
    //6.备注  长度100
    private String note;
    
    //7.用户主账户ID  非空 非负 长度20 数据表所有者
    private BigInteger ownerId;
    
    //8.操作人  非空 非负 长度20
    private BigInteger operatorId;
    
    //9.是否授权  非空 非负 长度1
    private Boolean authorized;
    
    //10.记录创建时间  非空  datetime
    private Timestamp gmtCreate;
    
    //11.记录修改时间  datetime
    private Timestamp gmtModified;

    //无参数的构造器
    public PlatformInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    //有参数的构造器
    public PlatformInfo(BigInteger id, String name, String platformUrl, String platformShopType, String reserved1,
            String note, BigInteger ownerId, BigInteger operatorId, Boolean authorized, Timestamp gmtCreate,
            Timestamp gmtModified) {
        super();
        this.id = id;
        this.name = name;
        this.platformUrl = platformUrl;
        this.platformShopType = platformShopType;
        this.reserved1 = reserved1;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorized = authorized;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    //创建的setter和getter方法
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatformUrl() {
        return platformUrl;
    }

    public void setPlatformUrl(String platformUrl) {
        this.platformUrl = platformUrl;
    }

    public String getPlatformShopType() {
        return platformShopType;
    }

    public void setPlatformShopType(String platformShopType) {
        this.platformShopType = platformShopType;
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

    //重写hashCode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorized == null) ? 0 : authorized.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((platformShopType == null) ? 0 : platformShopType.hashCode());
        result = prime * result + ((platformUrl == null) ? 0 : platformUrl.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        return result;
    }

    //重写equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlatformInfo other = (PlatformInfo) obj;
        if (authorized == null) {
            if (other.authorized != null)
                return false;
        } else if (!authorized.equals(other.authorized))
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
        if (platformShopType == null) {
            if (other.platformShopType != null)
                return false;
        } else if (!platformShopType.equals(other.platformShopType))
            return false;
        if (platformUrl == null) {
            if (other.platformUrl != null)
                return false;
        } else if (!platformUrl.equals(other.platformUrl))
            return false;
        if (reserved1 == null) {
            if (other.reserved1 != null)
                return false;
        } else if (!reserved1.equals(other.reserved1))
            return false;
        return true;
    }

    //重写toString
    @Override
    public String toString() {
        return "PlatformInfo [id=" + id + ", name=" + name + ", platformUrl=" + platformUrl + ", platformShopType="
                + platformShopType + ", reserved1=" + reserved1 + ", note=" + note + ", ownerId=" + ownerId
                + ", operatorId=" + operatorId + ", authorized=" + authorized + ", gmtCreate=" + gmtCreate
                + ", gmtModified=" + gmtModified + "]";
    }
    
    
}