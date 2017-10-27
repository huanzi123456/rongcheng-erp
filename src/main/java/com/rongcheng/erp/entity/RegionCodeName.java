package com.rongcheng.erp.entity;

import java.io.Serializable;
/**
 * 地区代码和名字对应表
 * @author 薛宗艳
 *
 */
public class RegionCodeName implements Serializable{
	private static final long serialVersionUID = 4701017336640412350L;	
    private Integer id;           //对应表的id  自增
    private Integer regionCode;   //地区编码
    private String name;          //地区名
	public RegionCodeName() {
		super();
	}
	public RegionCodeName(Integer id, Integer regionCode, String name) {
		super();
		this.id = id;
		this.regionCode = regionCode;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((regionCode == null) ? 0 : regionCode.hashCode());
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
		RegionCodeName other = (RegionCodeName) obj;
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
		if (regionCode == null) {
			if (other.regionCode != null)
				return false;
		} else if (!regionCode.equals(other.regionCode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RegionCodeName [id=" + id + ", regionCode=" + regionCode + ", name=" + name + "]";
	}
}
