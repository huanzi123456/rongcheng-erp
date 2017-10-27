package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;

import com.sun.jmx.snmp.Timestamp;

/**
 * 库存信息表 实体类
 * 
 * @author 赵滨
 */
public class StockInfo implements Serializable{
    
    /**
     * 序列号
     */
    private static final long serialVersionUID = -4424846681265678810L;
    
    /**
     * 构建参数
     */
    //1.记录编号
    private BigInteger rowno;
    
    //2.商品编号
    private BigInteger itemId;
    
    //3.库位编码
    private String stocklocationCode;
    
    //4.库存量
    private Integer stockQuantity;
    
    //5.警戒量库存
    private Integer alertStock;
    
    //6.自定义内容1
    private String reserved1;
    
    //7.备注
    private String note;
    
    //8.用户主账户ID
    private BigInteger ownerId;
    
    //9.操作人
    private BigInteger operatorId;
    
    //10.该记录是否被授权
    private Boolean authorization;
    
    //11.记录创建时间
    private Timestamp gmtCreate;
    
    //12.记录修改时间
    private Timestamp gmtModified;
    
    /**
     * 无参构造
     */
    public StockInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 有参构造
     */
    public StockInfo(BigInteger rowno, BigInteger itemId, String stocklocationCode, Integer stockQuantity,
            Integer alertStock, String reserved1, String note, BigInteger ownerId, BigInteger operatorId,
            Boolean authorization, Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.rowno = rowno;
        this.itemId = itemId;
        this.stocklocationCode = stocklocationCode;
        this.stockQuantity = stockQuantity;
        this.alertStock = alertStock;
        this.reserved1 = reserved1;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorization = authorization;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
    
    /**
     * get set方法
     */
    public BigInteger getRowno() {
        return rowno;
    }

    public void setRowno(BigInteger rowno) {
        this.rowno = rowno;
    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public String getStocklocationCode() {
        return stocklocationCode;
    }

    public void setStocklocationCode(String stocklocationCode) {
        this.stocklocationCode = stocklocationCode;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getAlertStock() {
        return alertStock;
    }

    public void setAlertStock(Integer alertStock) {
        this.alertStock = alertStock;
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

    /**
     * 重写哈希code
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alertStock == null) ? 0 : alertStock.hashCode());
        result = prime * result + ((authorization == null) ? 0 : authorization.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        result = prime * result + ((rowno == null) ? 0 : rowno.hashCode());
        result = prime * result + ((stockQuantity == null) ? 0 : stockQuantity.hashCode());
        result = prime * result + ((stocklocationCode == null) ? 0 : stocklocationCode.hashCode());
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
        StockInfo other = (StockInfo) obj;
        if (alertStock == null) {
            if (other.alertStock != null)
                return false;
        } else if (!alertStock.equals(other.alertStock))
            return false;
        if (authorization == null) {
            if (other.authorization != null)
                return false;
        } else if (!authorization.equals(other.authorization))
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
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
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
        if (rowno == null) {
            if (other.rowno != null)
                return false;
        } else if (!rowno.equals(other.rowno))
            return false;
        if (stockQuantity == null) {
            if (other.stockQuantity != null)
                return false;
        } else if (!stockQuantity.equals(other.stockQuantity))
            return false;
        if (stocklocationCode == null) {
            if (other.stocklocationCode != null)
                return false;
        } else if (!stocklocationCode.equals(other.stocklocationCode))
            return false;
        return true;
    }

    /**
     * 重写toString
     */
    @Override
    public String toString() {
        return "StockInfo [rowno=" + rowno + ", itemId=" + itemId + ", stocklocationCode=" + stocklocationCode
                + ", stockQuantity=" + stockQuantity + ", alertStock=" + alertStock + ", reserved1=" + reserved1
                + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", authorization="
                + authorization + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
    }
    
}
