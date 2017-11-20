package com.rongcheng.erp.service.onlineCommodityRelation;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.rongcheng.erp.dao.Xzy_OnlineCommodityRelationDao;
import com.rongcheng.erp.dto.ItemCommonAndEspInfo;
import com.rongcheng.erp.dto.PlatformErpLinkItemCommonInfo;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.ShopInfo;
/**
 * 线上商品对应关系页面
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_OnlineCommonRelationServiceImpl implements Xzy_OnlineCommonRelationService{
    @Resource
    Xzy_OnlineCommodityRelationDao dao;
    
    @Value("#{config['rows']}")
    private String pageSize;
    
    /**
     * 线上商品对应关系页面的分页查询
     */
	@Override
	public XzyJsonResult commonPage(BigInteger ownerId, Integer page) {		
		XzyJsonResult result = new XzyJsonResult();
		//统计 店铺商品-erp系统商品对应关系关联表中的记录数
		Integer count = dao.totalCount(ownerId);
		page=(page-1)*(new Integer (pageSize));
		Integer max_page;//总页数
		if(count<=new Integer(pageSize)){
			max_page=1;		
		}if(count%(new Integer(pageSize))==0){
			max_page=count/(new Integer(pageSize));
		}else{
			max_page = count/(new Integer(pageSize))+1;			
		}	
		//分页查询
		List<PlatformErpLinkItemCommonInfo> link = dao.seleCommonInfo(ownerId, page, new Integer(pageSize));
		result.setMaxPage(max_page);
		result.setPageSize(new Integer(pageSize));
		result.setData(link);
		result.setMsg(ownerId.toString());
		result.setStatus(0);
		return result;
	}	
	/**
	 * "换"弹出框中"选择已有"页面的分页查询
	 */
	@Override
	public XzyJsonResult commonPages(BigInteger ownerId, Integer page) {
		XzyJsonResult result = new XzyJsonResult();
		//统计 店铺商品-erp系统商品对应关系关联表中的记录数
		Integer count = dao.getTotalCount(ownerId);
		page=(page-1)*(new Integer (pageSize));
		Integer max_page;//总页数
		if(count<=new Integer(pageSize)){
			max_page=1;		
		}if(count%(new Integer(pageSize))==0){
			max_page=count/(new Integer(pageSize));
		}else{
			max_page = count/(new Integer(pageSize))+1;			
		}	
		//分页查询
		List<ItemCommonAndEspInfo> link = dao.seleEspInfo(ownerId, page, new Integer(pageSize));		
		result.setMaxPage(max_page);
		result.setPageSize(new Integer(pageSize));
		result.setData(link);
		result.setStatus(0);
		return result;
	} 
	
	/**
	 * "换"弹出框中"选择已有"页面的模糊查询
	 */
	@Override
	public XzyJsonResult likeSele(String str, BigInteger ownerId,Integer page) {
		XzyJsonResult result = new XzyJsonResult();
		//统计满足条件的记录数
		ItemCommonInfo item = new ItemCommonInfo();
		item.setOwnerId(ownerId);
		item.setName(str);
    	try {
    		item.setId(new BigInteger(str));
		} catch (Exception e) {
			
		}   	
    	item.setColor(str);
    	item.setSize(str);
		Integer count = dao.seleCountId(item);
		page=(page-1)*(new Integer (pageSize));
		Integer max_page;//总页数
		if(count<=new Integer(pageSize)){
			max_page=1;		
		}if(count%(new Integer(pageSize))==0){
			max_page=count/(new Integer(pageSize));
		}else{
			max_page = count/(new Integer(pageSize))+1;			
		}
		//分页查询	
		Map<String,Object> map = new HashMap<String,Object>();
    	map.put("ownerId", new BigInteger(ownerId+""));
    	map.put("name",str);
    	BigInteger id = null;
    	try {
    		id = new BigInteger(str);
		} catch (Exception e) {
			
		}
    	map.put("id",id);
    	map.put("size", str);
    	map.put("color", str);
    	map.put("page", page);
    	map.put("pageSize",new Integer(pageSize));
    	List<ItemCommonAndEspInfo> link = dao.likeEspInfo(map);
    	result.setMaxPage(max_page);
    	result.setPageSize(new Integer(pageSize));
    	result.setData(link);
		result.setStatus(0);
		return result;
	}
    /**
     * "换"弹出框中"选择已有"页面的保存按钮
     */
	@Override
	public XzyJsonResult modifyLinkInfo(BigInteger currentOwnerId, BigInteger common_id, BigInteger platformErpLinkId) {		
		XzyJsonResult result = new XzyJsonResult();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ownerId", currentOwnerId);
		map.put("itemId", common_id);
		map.put("id", platformErpLinkId);
		dao.updatePlatformErpLink(map);
		result.setStatus(0);
		return result;
	}
	/**
	 * 批量维护对应关系弹出框页面的保存按钮
	 */
	@Override
	public XzyJsonResult modifyLinkInfos(BigInteger currentOwnerId,String obj) {
		XzyJsonResult result = new XzyJsonResult();
		String[] strs = obj.split(",");
		for(int i=0;i<strs.length;i++){
			String[] arr = strs[i].split("-");			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ownerId", currentOwnerId);
			map.put("itemId", new BigInteger(arr[1]));
			map.put("id", new BigInteger(arr[0]));
			dao.updatePlatformErpLink(map);
		}
		result.setStatus(0);
		return result;
	}
	/**
	 * 线上商品对应关系页面的店铺下拉选的加载店铺
	 */
	@Override
	public XzyJsonResult addShopInfos(BigInteger currentOwnerId) {
		XzyJsonResult result = new XzyJsonResult();
		List<ShopInfo> list = dao.selectShopInfo(currentOwnerId);
		result.setData(list);
		result.setStatus(0);
		return result;
	}
	/**
	 * 线上商品对应关系页面的查询按钮
	 */
	@Override
	public XzyJsonResult selectButtons(String commonState, String platformShopId, String onlineInfo, String systemInof, BigInteger currentOwnerId,Integer page) {
		XzyJsonResult result = new XzyJsonResult();
		BigInteger onlineCommonSku = null ;
		BigInteger systemCommonId = null ; 
		try {
			onlineCommonSku = new BigInteger(onlineInfo);
			systemCommonId = new BigInteger(systemInof);
		} catch (Exception e) {
			
		}		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ownerId", currentOwnerId);          //ownerId      BigInteger
		map.put("platformItemState", commonState);   //状态                         Stirng 
		map.put("platformShopId", platformShopId);   //店铺                         String	
		map.put("platformItemSku", onlineCommonSku); //线上商品编号          BigInteger
		map.put("platformItemName", onlineInfo);     //线上商品名              Stirng 
		map.put("id", systemCommonId);               //系统商品编号          BigInteger
		map.put("name", systemInof);                 //系统商品名              Stirng 
		//1.统计满足条件的记录数
		Integer count = dao.selectButtonCount(map);		
		page=(page-1)*(new Integer (pageSize));
		Integer max_page;//总页数
		if(count<=new Integer(pageSize)){
			max_page=1;		
		}if(count%(new Integer(pageSize))==0){
			max_page=count/(new Integer(pageSize));
		}else{
			max_page = count/(new Integer(pageSize))+1;			
		}
		//2.分页查询
		map.put("page", page);                      
		map.put("pageSize", new Integer(pageSize));
		List<PlatformErpLinkItemCommonInfo> list = dao.selectCommonInfo(map);
		result.setMaxPage(max_page);
    	result.setPageSize(new Integer(pageSize));
    	result.setData(list);
		result.setStatus(0);
		return result;
	}	
}