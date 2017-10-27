package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.ItemCommonInfo;

public interface Jxb_ItemCommonDAO {   
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
	 * @param map		参数封装<"record",ItemCommonInfo>
	 *							<"keyword",String>
	 *							<"start",int>
	 *							<"rows",int>
	 * @return
	 */
	List<ItemCommonInfo> listAllBasicInfoByLimtSelective(Map<String,Object> map);
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
    
    int insertListSelective(List<ItemCommonInfo> record);
    
    BigInteger getMaxId();
    
    Date getLastCreateTime(BigInteger ownerId,BigInteger platformId);
}