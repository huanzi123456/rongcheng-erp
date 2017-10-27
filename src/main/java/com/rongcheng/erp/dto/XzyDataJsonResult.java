package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;
/**
 * 作用:数据统计页面返回前端的数据
 * @author 薛宗艳
 *
 */
public class XzyDataJsonResult implements Serializable{
	private static final long serialVersionUID = -8154154268572987508L;
	private Integer status;
	private Object msg;
	private Object data;
	private Map<String,Object> map;//存储数据统计页面三个表中所查询的数据
	private List<ShopInfo> list;//存储店铺信息表中的店铺id及名字
	private List<PlatformInfo> list1;//存储平台来源信息表中的平台id及平台名称
	public XzyDataJsonResult() {
		super();
	}
	public XzyDataJsonResult(Integer status, Object msg, Object data, Map<String, Object> map, List<ShopInfo> list,
			List<PlatformInfo> list1) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.map = map;
		this.list = list;
		this.list1 = list1;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List<ShopInfo> getList() {
		return list;
	}
	public void setList(List<ShopInfo> list) {
		this.list = list;
	}
	public List<PlatformInfo> getList1() {
		return list1;
	}
	public void setList1(List<PlatformInfo> list1) {
		this.list1 = list1;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((list1 == null) ? 0 : list1.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
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
		XzyDataJsonResult other = (XzyDataJsonResult) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (list1 == null) {
			if (other.list1 != null)
				return false;
		} else if (!list1.equals(other.list1))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
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
		return "JsonResult [status=" + status + ", msg=" + msg + ", data=" + data + ", map=" + map + ", list=" + list
				+ ", list1=" + list1 + "]";
	}
	
}
