package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 字段坐标表
 * @author 赵滨
 *
 */
public class FieldCoordinate implements Serializable{

    //版本号
    private static final long serialVersionUID = 5296016906713213161L;

    //1.记录编号ID  非空  非负  长度20
    private BigInteger id;
    
    //2.模板ID    非负  长度20
    private BigInteger templateId;
    
    //3.字段英文命名 非空 长度20
    private String fieldName;
    
    //4.坐标类型  非负  长度1
    private Integer coordinateType;
    
    //5.字段x坐标  长度3
    private Integer xCoordinate;
    
    //6.字段y坐标  长度3
    private Integer yCoordinate;

    //7.字段x长度  长度3
    private Integer xLength;

    //8.字段y长度  长度3
    private Integer yLength;

    //9.自定义内容 长度10
    private String reserved1;
    
    //10.备注  长度100
    private String note;
    
    //11.用户主账户ID  非空  非负  长度20  数据表所有者
    private BigInteger ownerId;
    
    //12.操作人  非负  长度20
    private BigInteger operatorId;
    
    //13.是否授权  非负  长度1
    private Boolean authorized;
    
    //14.记录创建时间  非空  datetime
    private Timestamp gmtCreate;
    
    //15.记录修改时间  datetime
    private Timestamp gmtModified;

    //无参数的构造器
    public FieldCoordinate() {
        super();
        // TODO Auto-generated constructor stub
    }

    //有参数的构造器
    public FieldCoordinate(BigInteger id, BigInteger templateId, String fieldName, Integer coordinateType,
                           Integer xCoordinate, Integer yCoordinate, Integer xLength, Integer yLength,
                           String reserved1, String note, BigInteger ownerId, BigInteger operatorId,
                           Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
        this.id = id;
        this.templateId = templateId;
        this.fieldName = fieldName;
        this.coordinateType = coordinateType;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.xLength = xLength;
        this.yLength = yLength;
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

    public BigInteger getTemplateId() {
        return templateId;
    }

    public void setTemplateId(BigInteger templateId) {
        this.templateId = templateId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(Integer coordinateType) {
        this.coordinateType = coordinateType;
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
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

    public Integer getxLength() {
        return xLength;
    }

    public void setxLength(Integer xLength) {
        this.xLength = xLength;
    }

    public Integer getyLength() {
        return yLength;
    }

    public void setyLength(Integer yLength) {
        this.yLength = yLength;
    }

    //重写equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldCoordinate that = (FieldCoordinate) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (templateId != null ? !templateId.equals(that.templateId) : that.templateId != null) return false;
        if (fieldName != null ? !fieldName.equals(that.fieldName) : that.fieldName != null) return false;
        if (coordinateType != null ? !coordinateType.equals(that.coordinateType) : that.coordinateType != null)
            return false;
        if (xCoordinate != null ? !xCoordinate.equals(that.xCoordinate) : that.xCoordinate != null) return false;
        if (yCoordinate != null ? !yCoordinate.equals(that.yCoordinate) : that.yCoordinate != null) return false;
        if (xLength != null ? !xLength.equals(that.xLength) : that.xLength != null) return false;
        if (yLength != null ? !yLength.equals(that.yLength) : that.yLength != null) return false;
        if (reserved1 != null ? !reserved1.equals(that.reserved1) : that.reserved1 != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (authorized != null ? !authorized.equals(that.authorized) : that.authorized != null) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        return gmtModified != null ? gmtModified.equals(that.gmtModified) : that.gmtModified == null;
    }

    //重写hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (templateId != null ? templateId.hashCode() : 0);
        result = 31 * result + (fieldName != null ? fieldName.hashCode() : 0);
        result = 31 * result + (coordinateType != null ? coordinateType.hashCode() : 0);
        result = 31 * result + (xCoordinate != null ? xCoordinate.hashCode() : 0);
        result = 31 * result + (yCoordinate != null ? yCoordinate.hashCode() : 0);
        result = 31 * result + (xLength != null ? xLength.hashCode() : 0);
        result = 31 * result + (yLength != null ? yLength.hashCode() : 0);
        result = 31 * result + (reserved1 != null ? reserved1.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (authorized != null ? authorized.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        return result;
    }

    //重写toString
    @Override
    public String toString() {
        return "FieldCoordinate{" +
                "id=" + id +
                ", templateId=" + templateId +
                ", fieldName='" + fieldName + '\'' +
                ", coordinateType=" + coordinateType +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", xLength=" + xLength +
                ", yLength=" + yLength +
                ", reserved1='" + reserved1 + '\'' +
                ", note='" + note + '\'' +
                ", ownerId=" + ownerId +
                ", operatorId=" + operatorId +
                ", authorized=" + authorized +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
