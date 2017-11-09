package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.ItemEspInfo;
import com.rongcheng.erp.entity.vo.ItemInfo;

public interface Wzy_ItemInfoDAO {

	//查询用户的所有商品
	List<ItemInfo> findUserAllItemInfo(@Param("ownerId")BigInteger ownerId, 
	                                   @Param("startPage")Integer startPage,
	                                   @Param("row")Integer row);
	
	//查询用户的商品数量
	Integer findUserItemCount(BigInteger ownerId);
	
	//模糊查询用户的商品数量
	Integer findUserItemCountByLike(@Param("ownerId")BigInteger ownerId,
	                                @Param("keyWord")String keyWord);
	
	//模糊查询
    List<ItemInfo> findItemInfoByLike(@Param("ownerId")BigInteger ownerId,
                                      @Param("keyWord")String keyWord,
                                      @Param("startPage")Integer startPage, 
                                      @Param("row")Integer row);

    //id查询
    List<ItemInfo> findItemInfoById(BigInteger id);
    
    //查询最大id
    BigInteger findItemMaxId();
    
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
