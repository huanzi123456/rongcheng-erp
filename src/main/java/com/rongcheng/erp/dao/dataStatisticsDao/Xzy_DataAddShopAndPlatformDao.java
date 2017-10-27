package com.rongcheng.erp.dao.dataStatisticsDao;

import java.math.BigInteger;
import java.util.List;
import com.rongcheng.erp.entity.PlatformInfo;
import com.rongcheng.erp.entity.ShopInfo;
import com.rongcheng.erp.entity.UserInfo;
/**
 * 数据统计页面的添加店铺信息和平台信息
 * @param name:当前用户的用户名
 * @param owner_id:当前用户的owner_id
 * @author 薛宗艳
 *
 */
public interface Xzy_DataAddShopAndPlatformDao {
	//1.查询当前账号的id和owner_id
	List<UserInfo> selectUserId(String accountNum);
	//2.查询店铺名称及店铺id
	List<ShopInfo> findShopNameAndShopId(BigInteger owner_id);	
	//3.查询平台名称及平台id
	List<PlatformInfo> findPlatformNameAndPlatformId(BigInteger owner_id);	
}
