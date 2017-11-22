package com.rongcheng.erp.service.DataStatisticsService;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rongcheng.erp.dao.Xzy_DataStatisticsDao;
import com.rongcheng.erp.dto.XzyDataSelectParam;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;
/**
 * 数据统计页面的Service层实现类
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_DataStatisticsServiceImpl implements Xzy_DataStatisticsService{
	@Resource
	Xzy_DataStatisticsDao dao;
	/**
	 * 数据统计页面的添加店铺信息和平台信息 
	 */
	@Override
	public XzyJsonResult addShopAndPlatFormat(BigInteger ownerId) {
		XzyJsonResult result = new XzyJsonResult();
		//1.查询店铺名称及店铺id
	    List<ShopInfo> shop = dao.selectShopInfo(ownerId);	    	    
	    //2.查询平台名称及平台id
	    List<PlatformInfo> platform = dao.selectPlatformInfo(ownerId);
	    result.setData(shop);
	    result.setDataes(platform);;
	    result.setMsg(ownerId.toString());
	    result.setStatus(0);
		return result;
	}
	/**
	 * 订单统计报表~商品统计报表~销售前20
	 */
	@Override
	public XzyJsonResult selectOtherInfos(Integer day, Integer pay, BigInteger shopId, BigInteger platformId,
			String time1, String time2, String time3, String time4, Integer orderStatus, String code,
			BigInteger ownerId) {
		XzyJsonResult result = new XzyJsonResult();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("day",day);//
		map.put("time1",time1);//
		map.put("time2",time2);//
		map.put("pay",pay);//
		map.put("time3",time3);
		map.put("time4",time4);
		map.put("shopId",shopId);
		map.put("platformId",platformId);
		map.put("orderState",orderStatus);
		map.put("code",code);//
		map.put("ownerId",ownerId);
		//1.订单统计报表
		Integer tatalOrders = dao.selectTotalOrder(map);//订单总数		
		Integer outOrders = dao.seleOutGoods(map);//已发货的订单数
		Integer waitOrders = dao.seleWaitGoods(map);//待发货的订单数
		Integer orderClosed = dao.seleOrderClosed(map);//已关闭的订单数
		Double volumeOfTrade =0.00;//成交额(暂时等于商品总价值)
		Double clientPrice=0.00;//客单价		
		Integer clients = dao.seleClients(map);//统计客户数  (客单价=成交额/客户数)null
		//2.商品统计报表
		Integer outGoods = dao.seleGoodsNum(map);//(1)已发货的订单的商品总个数
		Integer waitGoods = dao.seleWaitGoodsNum(map);//(2)未发货的订单的商品总个数
		Integer totalCount=0;//商品总数
		Double prices=0.00;//商品总价值	    
		Double costPrice=0.00;//商品总成本
		Double avgPrice=0.00;//商品平均价=(商品总价值/商品总数)
		List<XzyDataSelectParam> list = dao.selePrice(map);//商品总数,商品总价值,商品总成本			
		for(int i=0;i<list.size();i++){
			if(list.get(i)!=null){
				for(XzyDataSelectParam list1 : list){
					prices += list1.getPrice();			
					totalCount += list1.getQuantity();				
					costPrice += list1.getCostPrice();
				}
				avgPrice=(prices/totalCount);
			}else{
				prices=0.00;
				totalCount=0;
				costPrice=0.00;
				avgPrice=0.00;
			}
		}
		//3.销售前20
		List<XzyDataSelectParam> lists = dao.SalesTopTwenty(map);
		String strs="";
		if(lists.size()==0){
		    strs=",,,,,,,,,,,,,,,,,,,";
		}else{
		    for(XzyDataSelectParam list2 :lists){
			    String str = list2.getName()+"("+list2.getQuantity()+")";
			    strs += str + ",";
			}
			strs = strs.substring(0, strs.lastIndexOf(","));
		}	
		Map<String,Object> maps = new HashMap<String,Object>();
		//1.订单统计报表
		maps.put("tatalOrders",tatalOrders);//订单总数
		maps.put("outOrders",outOrders);//已发货的订单数
		maps.put("waitOrders",waitOrders);//待发货的订单数		
		maps.put("seleOrderClosed",orderClosed);//已关闭的订单数
		  volumeOfTrade=prices;
		maps.put("volumeOfTrade",volumeOfTrade);//成交额(暂时等于商品总价值)
		  if(volumeOfTrade==0 || clients==null){
			  clientPrice=0.00;
		  }else{
			  clientPrice=(volumeOfTrade/clients);
		  }				
		maps.put("clientPrice", clientPrice);//客单价=成交额/客户数
		//2.商品统计报表
		maps.put("outGood",outGoods);//已发货的商品数
		maps.put("waitGood", waitGoods);//待发货的商品数	
	    maps.put("prices",prices);//商品总价值    
	    maps.put("totalCount",totalCount);//商品总数
	    maps.put("costPrice",costPrice);//商品总成本	 
	    maps.put("avgPrice",avgPrice);//商品平均价=(商品总价值/商品总数)	    
	    //3.销售前20
		maps.put("strs",strs);
		result.setData(maps);
		result.setStatus(0);
		return result;
	}
}