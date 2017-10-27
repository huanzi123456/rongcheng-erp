package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 单据模板表
 * @author 赵滨
 *
 */
public class PrintTemplate implements Serializable{

    //版本号
    private static final long serialVersionUID = 4649472571104519295L;

    //1.记录编号  非空  非负  长度20
    private BigInteger id;
    
    //2.快递公司ID  非空  非负  长度20  除快递面单模板外的其他模板的此值统一设置。
    private BigInteger carrierId;
    
    //3.快递名称  长度9
    private String carrierName;
    
    //4.模板名称 非空 长度20
    private String templateName;
    
    //5.模板图片  长度128
    private String templateImage;
    
    //6.模板类型  非负  长度1  1.快递面单模板，2.发货单模板；等等
    private Integer templateType;
    
    //7.是否电子面单  非负  长度1  模板类型：分电子面单和普通面单
    private Boolean electronicWaybill;
    
    //8.模板宽  非空  非负  长度3
    private Integer templateWidth;
    
    //9.模板高  非空  非负  长度3
    private Integer templateHeight;
    
    //10.打印机选择 长度128 char
    private String printChoice;
    
    //11.自定义内容1 长度99
    private String reserved1;
    
    //12.备注  长度100
    private String note;
    
    //13.用户主账户ID  非空  非负  长度20  数据表所有者
    private BigInteger ownerId;
    
    //14.操作人  非负  长度20
    private BigInteger operatorId;
    
    //15.是否授权  非负  长度1
    private Boolean authorized;
    
    //16.记录创建时间  非空  datetime
    private Timestamp gmtCreate;
    
    //17.记录修改时间  datetime
    private Timestamp gmtModified;

    //无参数的构造器
    public PrintTemplate() {
        super();
    }

    //有参数的构造器
    public PrintTemplate(BigInteger id, BigInteger carrierId, String carrierName, String templateName,
            String templateImage, Integer templateType, Boolean electronicWaybill, Integer templateWidth,
            Integer templateHeight, String printChoice, String reserved1, String note, BigInteger ownerId,
            BigInteger operatorId, Boolean authorized, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.templateName = templateName;
        this.templateImage = templateImage;
        this.templateType = templateType;
        this.electronicWaybill = electronicWaybill;
        this.templateWidth = templateWidth;
        this.templateHeight = templateHeight;
        this.printChoice = printChoice;
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

    public BigInteger getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(BigInteger carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateImage() {
        return templateImage;
    }

    public void setTemplateImage(String templateImage) {
        this.templateImage = templateImage;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public Boolean getElectronicWaybill() {
        return electronicWaybill;
    }

    public void setElectronicWaybill(Boolean electronicWaybill) {
        this.electronicWaybill = electronicWaybill;
    }

    public Integer getTemplateWidth() {
        return templateWidth;
    }

    public void setTemplateWidth(Integer templateWidth) {
        this.templateWidth = templateWidth;
    }

    public Integer getTemplateHeight() {
        return templateHeight;
    }

    public void setTemplateHeight(Integer templateHeight) {
        this.templateHeight = templateHeight;
    }

    public String getPrintChoice() {
        return printChoice;
    }

    public void setPrintChoice(String printChoice) {
        this.printChoice = printChoice;
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
        result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
        result = prime * result + ((carrierName == null) ? 0 : carrierName.hashCode());
        result = prime * result + ((electronicWaybill == null) ? 0 : electronicWaybill.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((printChoice == null) ? 0 : printChoice.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        result = prime * result + ((templateHeight == null) ? 0 : templateHeight.hashCode());
        result = prime * result + ((templateImage == null) ? 0 : templateImage.hashCode());
        result = prime * result + ((templateName == null) ? 0 : templateName.hashCode());
        result = prime * result + ((templateType == null) ? 0 : templateType.hashCode());
        result = prime * result + ((templateWidth == null) ? 0 : templateWidth.hashCode());
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
        PrintTemplate other = (PrintTemplate) obj;
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
        if (carrierName == null) {
            if (other.carrierName != null)
                return false;
        } else if (!carrierName.equals(other.carrierName))
            return false;
        if (electronicWaybill == null) {
            if (other.electronicWaybill != null)
                return false;
        } else if (!electronicWaybill.equals(other.electronicWaybill))
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
        if (printChoice == null) {
            if (other.printChoice != null)
                return false;
        } else if (!printChoice.equals(other.printChoice))
            return false;
        if (reserved1 == null) {
            if (other.reserved1 != null)
                return false;
        } else if (!reserved1.equals(other.reserved1))
            return false;
        if (templateHeight == null) {
            if (other.templateHeight != null)
                return false;
        } else if (!templateHeight.equals(other.templateHeight))
            return false;
        if (templateImage == null) {
            if (other.templateImage != null)
                return false;
        } else if (!templateImage.equals(other.templateImage))
            return false;
        if (templateName == null) {
            if (other.templateName != null)
                return false;
        } else if (!templateName.equals(other.templateName))
            return false;
        if (templateType == null) {
            if (other.templateType != null)
                return false;
        } else if (!templateType.equals(other.templateType))
            return false;
        if (templateWidth == null) {
            if (other.templateWidth != null)
                return false;
        } else if (!templateWidth.equals(other.templateWidth))
            return false;
        return true;
    }

    //重写toString
    @Override
    public String toString() {
        return "PrintTemplate [id=" + id + ", carrierId=" + carrierId + ", carrierName=" + carrierName
                + ", templateName=" + templateName + ", templateImage=" + templateImage + ", templateType="
                + templateType + ", electronicWaybill=" + electronicWaybill + ", templateWidth=" + templateWidth
                + ", templateHeight=" + templateHeight + ", printChoice=" + printChoice + ", reserved1=" + reserved1 + 
                ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized=" +
                authorized + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
}
