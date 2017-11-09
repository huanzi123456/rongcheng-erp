package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import com.rongcheng.erp.entity.ItemCommonInfo;
public class ItemCommonAndEspInfo implements Serializable{
	private static final long serialVersionUID = -670045729944494204L;
	private BigInteger id;	
	private String unit;
	private BigInteger ownerId;
    private List<ItemCommonInfo> itemCommonInfo;
	public ItemCommonAndEspInfo() {
		super();
	}
	public ItemCommonAndEspInfo(BigInteger id, String unit, BigInteger ownerId, List<ItemCommonInfo> itemCommonInfo) {
		super();
		this.id = id;
		this.unit = unit;
		this.ownerId = ownerId;
		this.itemCommonInfo = itemCommonInfo;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public BigInteger getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(BigInteger ownerId) {
		this.ownerId = ownerId;
	}
	public List<ItemCommonInfo> getItemCommonInfo() {
		return itemCommonInfo;
	}
	public void setItemCommonInfo(List<ItemCommonInfo> itemCommonInfo) {
		this.itemCommonInfo = itemCommonInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemCommonInfo == null) ? 0 : itemCommonInfo.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		ItemCommonAndEspInfo other = (ItemCommonAndEspInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemCommonInfo == null) {
			if (other.itemCommonInfo != null)
				return false;
		} else if (!itemCommonInfo.equals(other.itemCommonInfo))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ItemCommonAndEspInfo [id=" + id + ", unit=" + unit + ", ownerId=" + ownerId + ", itemCommonInfo=" + itemCommonInfo + "]";
	}
	
}