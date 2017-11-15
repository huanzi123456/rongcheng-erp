package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.rongcheng.erp.dto.ItemCommonAndEspInfo;
import com.rongcheng.erp.dto.PlatformErpLinkItemCommonInfo;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.PlatformErpLink;
import com.rongcheng.erp.entity.ShopInfo;
/**
 * 线上商品对应关系页面
 * @author 薛宗艳
 *
 */
public interface Xzy_OnlineCommodityRelationDao {
	/**
	 * 京东的数据处理
	 */
	//查询platform_erp_link表中的京东数据的最大创建时间
	Date seleLastCreatTime(PlatformErpLink plat);	
	//根据platform_erp_link表中的platform_item_sku和platform_id查询item_common_info表中的id
    BigInteger seleItemCommonInfoId(ItemCommonInfo item);
    //根据platform_erp_link表中的platform_shop_id和platform_id查询shop_info表中的id
    BigInteger seleIdFromShopInfo(ShopInfo shop);
    //将京东接口的数据保存到表platform_erp_link
    void savePlatformErpLink(PlatformErpLink link);
    /**
     * 线上商品对应关系页面的处理
     */
    /*
     * 1.线上商品对应关系页面的分页查询
     * (1)统计platform_erp_link表的记录数
     * (2)分页查询
     */
    int totalCount(@Param("ownerId")BigInteger ownerId);
    List<PlatformErpLinkItemCommonInfo> seleCommonInfo(@Param("ownerId")BigInteger ownerId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
    /*
     * 2."换"操作中"选择已有"弹出框页面的分页查询
     * (1)统计系统商品信息表1（高频使用表）中的记录数
     * (2)分页查询
     */
    int getTotalCount(@Param("ownerId")BigInteger ownerId);
    List<ItemCommonAndEspInfo> seleEspInfo(@Param("ownerId")BigInteger ownerId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
    /*
     * 3."换"操作中"选择已有"弹出框页面的关键字查询
     *  (1)统计满足条件的记录数
     *  (2)将满足条件的记录分页查询  
     */
    Integer seleCountId(ItemCommonInfo item);
    List<ItemCommonAndEspInfo> likeEspInfo(Map<String,Object> map);
}