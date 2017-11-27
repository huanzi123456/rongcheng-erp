package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.ItemEspInfo;
import com.rongcheng.erp.dto.WzyItemInfo;

public interface Wzy_ItemInfoDAO {

	//查询用户的所有商品
	List<WzyItemInfo> findUserAllItemInfo(@Param("ownerId")BigInteger ownerId, 
	                                   @Param("startPage")Integer startPage,
	                                   @Param("row")Integer row,
	                                   @Param("categoryId")BigInteger categoryId);
	
	//查询用户的商品数量
	Integer findUserItemCount(@Param("ownerId")BigInteger ownerId,
	                          @Param("categoryId")BigInteger categoryId);
	
	//模糊查询用户的商品数量
	Integer findUserItemCountByLike(@Param("ownerId")BigInteger ownerId,
	                                @Param("keyWord")String keyWord,
	                                @Param("categoryId")BigInteger categoryId);
	
	//模糊查询
    List<WzyItemInfo> findItemInfoByLike(@Param("ownerId")BigInteger ownerId,
                                      @Param("keyWord")String keyWord,
                                      @Param("startPage")Integer startPage, 
                                      @Param("categoryId")BigInteger categoryId, 
                                      @Param("row")Integer row);

    //id查询
    List<WzyItemInfo> findItemInfoById(BigInteger id);
    
    //查询当前数据id
    BigInteger findItemCommonInfoId(WzyItemInfo Item);
    
	//增加用户的商品
	int saveItemCommonInfo(ItemCommonInfo Item);
	int saveItemEspInfo(ItemEspInfo Item);
	
	//更新用户的商品信息
	int updateItemCommonInfo(ItemCommonInfo Item);
	int updateItemEspInfo(ItemEspInfo Item);
	
	//删除用户的商品（批量或者单独删除）
	int removeItemCommonInfo(BigInteger id);
	int removeItemEspInfo(BigInteger id);

}
