package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;
/**
 * 
 * @author 薛宗艳
 *
 */
public class XzyOrderInfo implements Serializable{
	private static final long serialVersionUID = 6282985594826025000L;
	//1.订单ID  非空  非负  长度20
    private BigInteger id;
	//3.平台（来源）订单ID  非空  非负  长度20
    private BigInteger platformOrderId;
    //8.快递id  非负  长度20
    private BigInteger carrierId;
    //10.快递单号  长度10
    private BigInteger trackingNum;
	public XzyOrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XzyOrderInfo(BigInteger id, BigInteger platformOrderId, BigInteger carrierId, BigInteger trackingNum) {
		super();
		this.id = id;
		this.platformOrderId = platformOrderId;
		this.carrierId = carrierId;
		this.trackingNum = trackingNum;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getPlatformOrderId() {
		return platformOrderId;
	}
	public void setPlatformOrderId(BigInteger platformOrderId) {
		this.platformOrderId = platformOrderId;
	}
	public BigInteger getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(BigInteger carrierId) {
		this.carrierId = carrierId;
	}
	public BigInteger getTrackingNum() {
		return trackingNum;
	}
	public void setTrackingNum(BigInteger trackingNum) {
		this.trackingNum = trackingNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((platformOrderId == null) ? 0 : platformOrderId.hashCode());
		result = prime * result + ((trackingNum == null) ? 0 : trackingNum.hashCode());
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
		XzyOrderInfo other = (XzyOrderInfo) obj;
		if (carrierId == null) {
			if (other.carrierId != null)
				return false;
		} else if (!carrierId.equals(other.carrierId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (platformOrderId == null) {
			if (other.platformOrderId != null)
				return false;
		} else if (!platformOrderId.equals(other.platformOrderId))
			return false;
		if (trackingNum == null) {
			if (other.trackingNum != null)
				return false;
		} else if (!trackingNum.equals(other.trackingNum))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "XzyOrderInfo [id=" + id + ", platformOrderId=" + platformOrderId + ", carrierId=" + carrierId
				+ ", trackingNum=" + trackingNum + "]";
	}	
}