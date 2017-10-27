package com.rongcheng.erp.service.itemCommon;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.ItemCommonInfo;

public interface Jxb_ItemCommonService {
    
    ///////////////////////
	/**
     * 条件查询记录数，所有参数都可为null。
     * @author jxb
     *
     * @param map	参数封装<"record",ItemCommonInfo>
	 *						<"keyword",String>
     * @return
     */
    int countSelective(Map<String,Object> map);
    
    
	/**
	* 分页查找商品基本信息，所有参数都可为null。
	* 
	* @author jxb
	*
	* @param page			查找的页码
	* @param rows			每页的条数
	* @param keyword		关键字
	* @param itemCommonInfo	将商品的查找条件set进对象相应属性，不作为条件的一律置null。
	* @return				[int 最大页,List<ItemCommonInfo>]
	*/
	Object[] listAllBasicInfoByLimtSelective(int page,int rows, String keyword,ItemCommonInfo itemCommonInfo);
    
    /**
     * 查找类目列表
     * 
     * @author jxb
     *
     * @param ownerId
     * @return
     */
    List<String> listCategory(BigInteger ownerId);
    
    /**
     * 查找品牌列表
     * 
     * @author jxb
     *
     * @param ownerId
     * @return
     */
    List<String> listBrand(BigInteger ownerId);
}