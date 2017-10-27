package com.rongcheng.erp.dto;

import java.sql.Timestamp;
import java.util.List;

import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;

/**
 * 订单信息详情对象(订单表和商品表的信息合成)
 * 
 * @author jxb
 *
 */
public class OrderItemInfo_S extends OrderInfo {

	private static final long serialVersionUID = 116516516516516L;
	////////////// 高级搜索额外添加 start /////////////
	// 打印状态（判断是否为null进而按打印状态查询，和值无关）。1-已打快递已打发货    2-已打快递未打发货    3-未打快递已打发货    4-未打快递未打发货
	private String print;
	// 搜索关键字
	private String keyword;
	// 订单有无退款和关闭
	private String[] refundCloseArray;
	// 商品预售状态
	private String[] presellArray;
	// 货到付款与否
	private String[] orderCodArray;
	// 订单商品总数
	private String[] quantity;
	// 订单商品种类数量
	private String[] count;
	// 订单总金额
	private String[] price;
	// 订单总重量
	private String[] weight;
	// 订单收货省份
	private String province;
	// 订单付款起始时间
	private Timestamp payTime1;
	// 订单付款结束时间
	private Timestamp payTime2;
	////////////// 高级搜索额外添加 end /////////////
	// 快递
	private String[] carrierIdArray;
	// 订单状态
	private String[] orderStatusArray;
	// 物流记录
	private String traces;
	// 订单创建起始时间
	private Timestamp time1;
	// 订单创建结束时间
	private Timestamp time2;
	// 订单状态
	private String state;
	// 起始行
	private Integer start;
	// 行数
	private Integer rows;
	// 平台信息
	private PlatformInfo platformInfo;
	// 店铺信息
	private ShopInfo shopInfo;
	// 买家信息
	private BuyerInfo buyerInfo;
	// 订单下商品及仓库简单信息
	private List<ItemCommonWarehouse_S> itemCommonWarehouse_S;

	// 无参数的构造器
	public OrderItemInfo_S() {
		super();
	}

	public String[] getRefundCloseArray() {
		return refundCloseArray;
	}

	public void setRefundCloseArray(String[] refundCloseArray) {
		this.refundCloseArray = refundCloseArray;
	}

	public String[] getOrderCodArray() {
		return orderCodArray;
	}

	public void setOrderCodArray(String[] orderCodArray) {
		this.orderCodArray = orderCodArray;
	}

	public String[] getCarrierIdArray() {
		return carrierIdArray;
	}

	public void setCarrierIdArray(String[] carrierIdArray) {
		this.carrierIdArray = carrierIdArray;
	}

	public String[] getPresellArray() {
		return presellArray;
	}

	public void setPresellArray(String[] presellArray) {
		this.presellArray = presellArray;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String[] getQuantity() {
		return quantity;
	}

	public void setQuantity(String[] quantity) {
		this.quantity = quantity;
	}

	public String[] getCount() {
		return count;
	}

	public void setCount(String[] count) {
		this.count = count;
	}

	public String[] getPrice() {
		return price;
	}

	public void setPrice(String[] price) {
		this.price = price;
	}

	public String[] getWeight() {
		return weight;
	}

	public void setWeight(String[] weight) {
		this.weight = weight;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public PlatformInfo getPlatformInfo() {
		return platformInfo;
	}

	public void setPlatformInfo(PlatformInfo platformInfo) {
		this.platformInfo = platformInfo;
	}

	public ShopInfo getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(ShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}

	public BuyerInfo getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(BuyerInfo buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public List<ItemCommonWarehouse_S> getItemCommonWarehouse_S() {
		return itemCommonWarehouse_S;
	}

	public void setItemCommonWarehouse_S(List<ItemCommonWarehouse_S> itemCommonWarehouse_S) {
		this.itemCommonWarehouse_S = itemCommonWarehouse_S;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Timestamp getTime1() {
		return time1;
	}

	public void setTime1(Timestamp time1) {
		this.time1 = time1;
	}

	public Timestamp getTime2() {
		return time2;
	}

	public void setTime2(Timestamp time2) {
		this.time2 = time2;
	}

	public Timestamp getPayTime1() {
		return payTime1;
	}

	public void setPayTime1(Timestamp payTime1) {
		this.payTime1 = payTime1;
	}

	public Timestamp getPayTime2() {
		return payTime2;
	}

	public void setPayTime2(Timestamp payTime2) {
		this.payTime2 = payTime2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTraces() {
		return traces;
	}

	public void setTraces(String traces) {
		this.traces = traces;
	}

	public String[] getOrderStatusArray() {
		return orderStatusArray;
	}

	public void setOrderStatusArray(String[] orderStatusArray) {
		this.orderStatusArray = orderStatusArray;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	/*
	 * @Override public String toString() { String ss = super.toString(); String
	 * s = ss.substring(ss.indexOf("[") + 1, ss.lastIndexOf("]")); return
	 * "OrderItemInfo_S [" + s + ",keyword=" + keyword + ", refundCloseArray=" +
	 * Arrays.toString(refundCloseArray) + ", orderCodArray=" +
	 * Arrays.toString(orderCodArray) + ", carrierIdArray=" +
	 * Arrays.toString(carrierIdArray) + ", platformInfo=" + platformInfo +
	 * ", shopInfo=" + shopInfo + ", buyerInfo=" + buyerInfo +
	 * ", itemCommonWarehouse_S=" + itemCommonWarehouse_S + ", start=" + start +
	 * ", rows=" + rows + ", time1=" + time1 + ", time2=" + time2 + ", state=" +
	 * state + "]"; }
	 */

}
