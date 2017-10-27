package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;
/**
 * 作用:数据统计页面中mapper的参数和返回值
 * @author 薛宗艳
 *
 */
public class XzyDataSelectParam implements Serializable{
	private static final long serialVersionUID = 6066863581052527729L;
	    private Integer day;//订单创建时间天数(1,3,7,30)
	    private Integer pay;//订单付款时间天数(1,3,7,30)
	    private String time1;
	    private String time2;
	    private String time3;
	    private String time4;
	    private BigInteger shopId;//店铺id
	    private BigInteger platformId;//平台id
	    private Integer orderStatus;//订单原始状态
	    private String code;//商品编码对应order_item_id   
	    private BigInteger owner_id;//用户主账户id	
	    
	    
	    private Integer quantity;//商品总数数量   
	    private Double price;//商品总价值=同一商品采购数量*商品原价(normal_price)
	    private Double costPrice;//商品总成本=同一商品采购数量*商品成本价(cost_price)
	    private String name;//商品名
		public XzyDataSelectParam() {
			super();
			// TODO Auto-generated constructor stub
		}
		public XzyDataSelectParam(Integer day, Integer pay, String time1, String time2, String time3, String time4,
				BigInteger shopId, BigInteger platformId, Integer orderStatus, String code, BigInteger owner_id,
				Integer quantity, Double price, Double costPrice, String name) {
			super();
			this.day = day;
			this.pay = pay;
			this.time1 = time1;
			this.time2 = time2;
			this.time3 = time3;
			this.time4 = time4;
			this.shopId = shopId;
			this.platformId = platformId;
			this.orderStatus = orderStatus;
			this.code = code;
			this.owner_id = owner_id;
			this.quantity = quantity;
			this.price = price;
			this.costPrice = costPrice;
			this.name = name;
		}
		public Integer getDay() {
			return day;
		}
		public void setDay(Integer day) {
			this.day = day;
		}
		public Integer getPay() {
			return pay;
		}
		public void setPay(Integer pay) {
			this.pay = pay;
		}
		public String getTime1() {
			return time1;
		}
		public void setTime1(String time1) {
			this.time1 = time1;
		}
		public String getTime2() {
			return time2;
		}
		public void setTime2(String time2) {
			this.time2 = time2;
		}
		public String getTime3() {
			return time3;
		}
		public void setTime3(String time3) {
			this.time3 = time3;
		}
		public String getTime4() {
			return time4;
		}
		public void setTime4(String time4) {
			this.time4 = time4;
		}
		public BigInteger getShopId() {
			return shopId;
		}
		public void setShopId(BigInteger shopId) {
			this.shopId = shopId;
		}
		public BigInteger getPlatformId() {
			return platformId;
		}
		public void setPlatformId(BigInteger platformId) {
			this.platformId = platformId;
		}
		public Integer getOrderStatus() {
			return orderStatus;
		}
		public void setOrderStatus(Integer orderStatus) {
			this.orderStatus = orderStatus;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public BigInteger getOwner_id() {
			return owner_id;
		}
		public void setOwner_id(BigInteger owner_id) {
			this.owner_id = owner_id;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Double getCostPrice() {
			return costPrice;
		}
		public void setCostPrice(Double costPrice) {
			this.costPrice = costPrice;
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
			result = prime * result + ((code == null) ? 0 : code.hashCode());
			result = prime * result + ((costPrice == null) ? 0 : costPrice.hashCode());
			result = prime * result + ((day == null) ? 0 : day.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
			result = prime * result + ((owner_id == null) ? 0 : owner_id.hashCode());
			result = prime * result + ((pay == null) ? 0 : pay.hashCode());
			result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
			result = prime * result + ((price == null) ? 0 : price.hashCode());
			result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
			result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
			result = prime * result + ((time1 == null) ? 0 : time1.hashCode());
			result = prime * result + ((time2 == null) ? 0 : time2.hashCode());
			result = prime * result + ((time3 == null) ? 0 : time3.hashCode());
			result = prime * result + ((time4 == null) ? 0 : time4.hashCode());
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
			XzyDataSelectParam other = (XzyDataSelectParam) obj;
			if (code == null) {
				if (other.code != null)
					return false;
			} else if (!code.equals(other.code))
				return false;
			if (costPrice == null) {
				if (other.costPrice != null)
					return false;
			} else if (!costPrice.equals(other.costPrice))
				return false;
			if (day == null) {
				if (other.day != null)
					return false;
			} else if (!day.equals(other.day))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (orderStatus == null) {
				if (other.orderStatus != null)
					return false;
			} else if (!orderStatus.equals(other.orderStatus))
				return false;
			if (owner_id == null) {
				if (other.owner_id != null)
					return false;
			} else if (!owner_id.equals(other.owner_id))
				return false;
			if (pay == null) {
				if (other.pay != null)
					return false;
			} else if (!pay.equals(other.pay))
				return false;
			if (platformId == null) {
				if (other.platformId != null)
					return false;
			} else if (!platformId.equals(other.platformId))
				return false;
			if (price == null) {
				if (other.price != null)
					return false;
			} else if (!price.equals(other.price))
				return false;
			if (quantity == null) {
				if (other.quantity != null)
					return false;
			} else if (!quantity.equals(other.quantity))
				return false;
			if (shopId == null) {
				if (other.shopId != null)
					return false;
			} else if (!shopId.equals(other.shopId))
				return false;
			if (time1 == null) {
				if (other.time1 != null)
					return false;
			} else if (!time1.equals(other.time1))
				return false;
			if (time2 == null) {
				if (other.time2 != null)
					return false;
			} else if (!time2.equals(other.time2))
				return false;
			if (time3 == null) {
				if (other.time3 != null)
					return false;
			} else if (!time3.equals(other.time3))
				return false;
			if (time4 == null) {
				if (other.time4 != null)
					return false;
			} else if (!time4.equals(other.time4))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "DataSelectParam [day=" + day + ", pay=" + pay + ", time1=" + time1 + ", time2=" + time2 + ", time3="
					+ time3 + ", time4=" + time4 + ", shopId=" + shopId + ", platformId=" + platformId
					+ ", orderStatus=" + orderStatus + ", code=" + code + ", owner_id=" + owner_id + ", quantity="
					+ quantity + ", price=" + price + ", costPrice=" + costPrice + ", name=" + name + "]";
		}			
}
