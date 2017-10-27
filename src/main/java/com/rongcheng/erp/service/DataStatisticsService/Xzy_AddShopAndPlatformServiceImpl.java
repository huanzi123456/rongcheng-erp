package com.rongcheng.erp.service.DataStatisticsService;

import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rongcheng.erp.dao.dataStatisticsDao.Xzy_DataAddShopAndPlatformDao;
import com.rongcheng.erp.dto.XzyDataJsonResult;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;
import com.rongcheng.erp.entity.UserInfo;
/**
 * 数据统计页面的添加店铺信息和平台信息
 * @param accountNum:当前登录的用户的账号
 * @param owner_id:当前用户的owner_id
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_AddShopAndPlatformServiceImpl implements Xzy_AddShopAndPlatformService{
    @Resource
    Xzy_DataAddShopAndPlatformDao dao;
    XzyDataJsonResult result = new XzyDataJsonResult();
	public XzyDataJsonResult addShopAndPlatFormat(String accountNum) {
		//1.查询当前账号的id和owner_id
		List<UserInfo> use = dao.selectUserId(accountNum);
		BigInteger id = null;//当前用户的id
		BigInteger owner_id = null;//用户的主账户id
	    for(UserInfo i:use){
	    	id = i.getId();
	    	owner_id=i.getOwnerId();
	    }
	    //2.查询店铺名称及店铺id
	    List<ShopInfo> shop = dao.findShopNameAndShopId(owner_id);	    	    
	    //3.查询平台名称及平台id
	    List<PlatformInfo> platform = dao.findPlatformNameAndPlatformId(owner_id);
	    result.setList(shop);
	    result.setList1(platform);
	    result.setMsg(id);
	    result.setData(owner_id);
	    result.setStatus(0);
		return result;
	}

}
