package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 快递信息表
 * @author 赵滨
 *
 */
public class CarrierInfo implements Serializable{
    
    //版本号
    private static final long serialVersionUID = -2527982986932723584L;

    //1.快递公司ID 非空 非负 长度20
    private BigInteger id;
    
    //2.快递公司名字 非空 长度9
    private String carrierName;

    //3.快递公司logo  长度255
    private String carrierLogo;
    
    //4.末端集散点名称 长度20  暂作合作快递名字
    private String terminalName;
    
    //5.末端分拣编码  长度20  暂作合作快递分拣编码或合作快递编码
    private String terminalCode;
    
    //6.自定义内容1  长度30
    private String reserved1;
    
    //7.备注  长度100
    private String note;
    
    //8.用户主账户ID  非空 非负  长度20  数据表所有者
    private BigInteger ownerId;
    
    //9.操作人  非负  长度20
    private BigInteger operatorId;
    
    //10.是否授权  非负  长度1
    private Boolean authorized;
    
    //11.记录创建时间  非空  datetime
    private Timestamp gmtCreate;
    
    //12.记录修改时间  datetime
    private Timestamp gmtModified;

    //无参数的构造器
    public CarrierInfo() {
        super();
    }
 
    //有参数的构造器
    public CarrierInfo(BigInteger id, String carrierName, String carrierLogo, String terminalName, String terminalCode,
            String reserved1, String note, BigInteger ownerId, BigInteger operatorId, Boolean authorized,
            Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.carrierName = carrierName;
        this.carrierLogo = carrierLogo;
        this.terminalName = terminalName;
        this.terminalCode = terminalCode;
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

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getCarrierLogo() {
        return carrierLogo;
    }

    public void setCarrierLogo(String carrierLogo) {
        this.carrierLogo = carrierLogo;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
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
        result = prime * result + ((carrierLogo == null) ? 0 : carrierLogo.hashCode());
        result = prime * result + ((carrierName == null) ? 0 : carrierName.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        result = prime * result + ((terminalCode == null) ? 0 : terminalCode.hashCode());
        result = prime * result + ((terminalName == null) ? 0 : terminalName.hashCode());
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
        CarrierInfo other = (CarrierInfo) obj;
        if (authorized == null) {
            if (other.authorized != null)
                return false;
        } else if (!authorized.equals(other.authorized))
            return false;
        if (carrierLogo == null) {
            if (other.carrierLogo != null)
                return false;
        } else if (!carrierLogo.equals(other.carrierLogo))
            return false;
        if (carrierName == null) {
            if (other.carrierName != null)
                return false;
        } else if (!carrierName.equals(other.carrierName))
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
        if (reserved1 == null) {
            if (other.reserved1 != null)
                return false;
        } else if (!reserved1.equals(other.reserved1))
            return false;
        if (terminalCode == null) {
            if (other.terminalCode != null)
                return false;
        } else if (!terminalCode.equals(other.terminalCode))
            return false;
        if (terminalName == null) {
            if (other.terminalName != null)
                return false;
        } else if (!terminalName.equals(other.terminalName))
            return false;
        return true;
    }
  
    //重写toString
    @Override
    public String toString() {
        return "CarrierInfo [id=" + id + ", carrierName=" + carrierName + ", carrierLogo=" + carrierLogo
                + ", terminalName=" + terminalName + ", terminalCode=" + terminalCode + ", reserved1=" + reserved1
                + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorized="
                + authorized + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
    
}
