package com.rongcheng.erp.dao.dataStatisticsDao;

import java.util.List;

import com.rongcheng.erp.dto.XzyDataSelectParam;
/**
 * 数据统计页面
 * @author 薛宗艳
 *
 */
public interface Xzy_DataSelectDao {
  //1.订单统计报表
	//(1)订单总数
	Integer seleTotalOrderBy(XzyDataSelectParam pm);
	//(2)已发货的订单数
	Integer seleOutGoodsBy(XzyDataSelectParam pm);
	//(3)待发货的订单数
	Integer seleWaitGoodsBy(XzyDataSelectParam pm);
	//(4)已关闭的订单数
	Integer seleOrderClosed(XzyDataSelectParam pm);
	//(5)成交额(暂时等于商品总价值)
	//(6)统计客户数  (客单价=成交额/客户数)
	Integer seleClients(XzyDataSelectParam pm);
  //2.商品统计报表
	//(1)已发货的订单的商品总个数
	Integer seleGoodsNum(XzyDataSelectParam pm);
	//(2)未发货的订单的商品总个数
	Integer seleWaitGoodsNum(XzyDataSelectParam pm);
	//(3)商品总数,商品总价值,商品总成本
	List<XzyDataSelectParam> selePrice(XzyDataSelectParam pm);
  //3.销售前20	
	List<XzyDataSelectParam> seleSells(XzyDataSelectParam pm);
}
