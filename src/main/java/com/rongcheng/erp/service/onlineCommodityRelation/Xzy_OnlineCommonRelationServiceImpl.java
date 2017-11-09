package com.rongcheng.erp.service.onlineCommodityRelation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.rongcheng.erp.dao.Xzy_OnlineCommodityRelationDao;
import com.rongcheng.erp.dto.ItemCommonAndEspInfo;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.PlatformErpLink;
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
     * 分页查询
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
		List<PlatformErpLink> link = dao.seleCommonInfo(ownerId, page, new Integer(pageSize));
		result.setMaxPage(max_page);
		result.setPageSize(new Integer(pageSize));
		result.setData(link);
		result.setStatus(0);
		return result;
	}
	
	/**
	 * "换"操作中"选择已有"弹出框页面的分页查询
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
}
