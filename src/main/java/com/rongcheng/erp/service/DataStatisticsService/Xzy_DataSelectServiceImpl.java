package com.rongcheng.erp.service.DataStatisticsService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rongcheng.erp.dao.dataStatisticsDao.Xzy_DataSelectDao;
import com.rongcheng.erp.dto.XzyDataJsonResult;
import com.rongcheng.erp.dto.XzyDataSelectParam;
/**
 * 数据统计页面
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_DataSelectServiceImpl implements Xzy_DataSelectService{
    @Resource
    Xzy_DataSelectDao dao;
    XzyDataJsonResult result = new XzyDataJsonResult();
	public XzyDataJsonResult dataSelect(Integer day, Integer pay, BigInteger shopId, BigInteger platformId, String time1,
			String time2, String time3, String time4, Integer orderStatus, String code, BigInteger owner_id) {
//		System.out.println("day:"+day+","+"pay:"+pay+","+"shopid:"+shopId+","+"platformid:"+platformId+
//    			","+"time1:"+time1+","+"time2:"+time2+","+"time3:"+time3+","+"time4:"+time4+","+
//    			"orderStatus:"+orderStatus+","+"code:"+code+","+"owner_id::"+owner_id);
		XzyDataSelectParam pm = new XzyDataSelectParam();
		pm.setDay(day);
		pm.setPay(pay);
		pm.setShopId(shopId);
		pm.setPlatformId(platformId);
		pm.setTime1(time1);
		pm.setTime2(time2);
		pm.setTime3(time3);
		pm.setTime4(time4);
		pm.setOrderStatus(orderStatus);
		pm.setCode(code);
		pm.setOwner_id(owner_id);
		//1.订单统计报表
		Integer tatalOrders = dao.seleTotalOrderBy(pm);//订单总数
		Integer outOrders = dao.seleOutGoodsBy(pm);//已发货的订单数
		Integer waitOrders = dao.seleWaitGoodsBy(pm);//待发货的订单数
		Integer closeOrders = dao.seleOrderClosed(pm);//已关闭的订单数
		Double volumeOfTrade =0.00;//成交额(暂时等于商品总价值)
		Double clientPrice=0.00;//客单价		
		Integer clients = dao.seleClients(pm);//统计客户数  (客单价=成交额/客户数)null
		//2.商品统计报表
		Integer outGoods = dao.seleGoodsNum(pm);//已发货的商品数
		Integer waitGoods = dao.seleWaitGoodsNum(pm);//待发货的商品数
		Integer totalCount=0;//商品总数
		Double prices=0.00;//商品总价值	    
	    Double costPrice=0.00;//商品总成本
	    Double avgPrice=0.00;//商品平均价
		List<XzyDataSelectParam> list  =  dao.selePrice(pm);		
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
		List<XzyDataSelectParam> lists = dao.seleSells(pm);
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
		Map<String, Object> map = new HashMap<String, Object>();
		//1.订单统计报表
		map.put("totalOrder",tatalOrders);//订单总数
		map.put("outGoods",outOrders);//已发货的订单数
		map.put("waitGoods",waitOrders);//待发货的订单数
		map.put("closed",closeOrders);//已关闭的订单数
		volumeOfTrade=prices;//成交额(暂时等于商品总价值)
		map.put("volumeOfTrade",volumeOfTrade);
		if(volumeOfTrade==0 || clients==null){
			clientPrice=0.00;
		}else{
			clientPrice=(volumeOfTrade/clients);//客单价=成交额/客户数
		}				
		map.put("clientPrice", clientPrice);
		//2.商品统计报表
		map.put("outGood",outGoods);//已发货的商品数
		map.put("waitGood", waitGoods);//待发货的商品数	
	    map.put("prices",prices);//商品总价值    
	    map.put("totalCount",totalCount);//商品总数
	    map.put("costPrice",costPrice);//商品总成本	 
	    map.put("avgPrice",avgPrice);//商品平均价
	    //3.销售前20
	    map.put("strs",strs);//销售前20
		result.setMap(map);
		result.setStatus(0);
		return result;
	}
}
