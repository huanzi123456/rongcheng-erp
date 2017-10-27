package com.rongcheng.erp.dto;

import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.WarehouseInfo;

/**
 * 商品仓库信息详情(订单表和商品表的信息合成)
 * 
 * @author jxb
 *
 */
public class ItemCommonWarehouse_S extends ItemCommonInfo {

	public ItemCommonWarehouse_S() {
		super();
	}

	// 同一商品采购数量 长度5
	private Integer quantity;
	// 仓库信息///////////////////
	private WarehouseInfo warehouseInfo;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public WarehouseInfo getWarehouseInfo() {
		return warehouseInfo;
	}

	public void setWarehouseInfo(WarehouseInfo warehouseInfo) {
		this.warehouseInfo = warehouseInfo;
	}

	@Override
	public String toString() {
		String ss = super.toString();
		return "ItemCommonWarehouse_S ["+ss.substring(ss.indexOf("[")+1,ss.lastIndexOf("]"))+", quantity=" + quantity + ", warehouseInfo=" + warehouseInfo + "]";
	}
}
