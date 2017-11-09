package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongcheng.erp.dto.ItemCommonAndEspInfo;
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
	 * 查询platform_erp_link表中的京东数据的最大创建时间
	 * @param plat
	 * @return
	 */
	Date seleLastCreatTime(PlatformErpLink plat);
	/**
	 * 根据platform_erp_link表中的platform_item_sku和platform_id查询item_common_info表中的id
	 * @param item
	 * @return   
	 */
    BigInteger seleItemCommonInfoId(ItemCommonInfo item);
    /**
     * 根据platform_erp_link表中的platform_shop_id和platform_id查询shop_info表中的id
     * @param shop
     * @return
     */
    BigInteger seleIdFromShopInfo(ShopInfo shop);
    /**
     * 将京东接口的数据保存到表platform_erp_link
     * @param link
     */
    void savePlatformErpLink(PlatformErpLink link);
    /**
     * 统计platform_erp_link表中的记录数
     * @param ownerId
     * @return
     */
    int totalCount(@Param("ownerId")BigInteger ownerId);
    /**
     * 分页查询
     * @param ownerId
     * @return
     */
    List<PlatformErpLink> seleCommonInfo(@Param("ownerId")BigInteger ownerId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
    /**
     * 统计系统商品信息表1（高频使用表）中的记录数
     * @param ownerId
     * @return
     */
    int getTotalCount(@Param("ownerId")BigInteger ownerId);
    /**
     * "换"操作中"选择已有"弹出框页面的分页查询
     * @param ownerId
     * @param page
     * @param pageSize
     * @return
     */
    List<ItemCommonAndEspInfo> seleEspInfo(@Param("ownerId")BigInteger ownerId,@Param("page")Integer page,@Param("pageSize")Integer pageSize);
}
