package com.rongcheng.erp.dao;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import com.rongcheng.erp.dto.XzyDataSelectParam;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;
/**
 * 数据统计页面的dao层
 * @author 薛宗艳
 *
 */
public interface Xzy_DataStatisticsDao {
	//一.查询所有的店铺  id及name
	List<ShopInfo> selectShopInfo(BigInteger ownerId);
	
	//二.查询所有的平台id及name
	List<PlatformInfo> selectPlatformInfo(BigInteger ownerId);
	
	/*
	 * 三.关联查询
	 */
	//1.订单统计报表
	Integer selectTotalOrder(Map<String,Object> map);            //(1)订单总数	
	Integer seleOutGoods(Map<String,Object> map);                //(2)已发货的订单数
	Integer seleWaitGoods(Map<String,Object> map);               //(3)待发货的订单数
	Integer seleOrderClosed(Map<String,Object> map);             //(4)已关闭的订单数
	Double selectOrderValue(Map<String,Object> map);             //(5)成交额
	Integer seleClients(Map<String,Object> map);                 //(6)统计客户数(客单价=成交额/客户数)
	//2.商品统计报表
	Integer seleGoodsNum(Map<String,Object> map);                //(1)已发货的订单的商品总个数
	Integer seleWaitGoodsNum(Map<String,Object> map);            //(2)未发货的订单的商品总个数
	List<XzyDataSelectParam> selePrice(Map<String,Object> map);  //(3)商品总数,商品总价值,商品总成本
	//3.销售前20
	List<XzyDataSelectParam> SalesTopTwenty(Map<String,Object> map);
}
