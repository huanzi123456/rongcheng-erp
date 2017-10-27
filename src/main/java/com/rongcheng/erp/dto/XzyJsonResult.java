package com.rongcheng.erp.dto;

import java.io.Serializable;
/**
 * 返回ajax的数据
 * @author 薛宗艳
 *
 */
public class XzyJsonResult implements Serializable{
	private static final long serialVersionUID = -8540645510100828170L;
	private Integer status;      //状态 
	private String msg;          //信息1
	private String message;      //信息2
	private Object data;         //数据1
	private Object datum;        //数据2
	private Object dataes;//不用的时候去掉
    private Integer pageSize;    //每页记录数(默认5条)
    private Integer maxPage;     //总的页数
	public XzyJsonResult() {
		super();
	}
	public XzyJsonResult(Integer status, String msg, String message, Object data, Object datum, Object dataes,
			Integer pageSize, Integer maxPage) {
		super();
		this.status = status;
		this.msg = msg;
		this.message = message;
		this.data = data;
		this.datum = datum;
		this.dataes = dataes;
		this.pageSize = pageSize;
		this.maxPage = maxPage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getdatum() {
		return datum;
	}
	public void setdatum(Object datum) {
		this.datum = datum;
	}
	public Object getDataes() {
		return dataes;
	}
	public void setDataes(Object dataes) {
		this.dataes = dataes;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((dataes == null) ? 0 : dataes.hashCode());
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + ((maxPage == null) ? 0 : maxPage.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());		
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		XzyJsonResult other = (XzyJsonResult) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataes == null) {
			if (other.dataes != null)
				return false;
		} else if (!dataes.equals(other.dataes))
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (maxPage == null) {
			if (other.maxPage != null)
				return false;
		} else if (!maxPage.equals(other.maxPage))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "XzyJsonResult [status=" + status + ", msg=" + msg + ", message=" + message + ", data=" + data
				+ ", datum=" + datum + ", dataes=" + dataes + ", pageSize=" + pageSize + ", maxPage="
				+ maxPage + "]";
	}	
}
