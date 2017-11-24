package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongcheng.erp.entity.CategoryInfo;
import com.rongcheng.erp.entity.ItemCategoryLink;

public interface Wzy_ItemCategoryDao {

    //查询用户的分类信息
    List<CategoryInfo> findAllCategoryByOwnerId(BigInteger ownerId);
    
    //查询商品的分类
    List<ItemCategoryLink> findAllCategoryByItemId(BigInteger itemId);
    
    //添加商品的分类
    int saveItemCategory(ItemCategoryLink icl);
    
    //删除商品的分类（更改商品时）
    int deleteItemCategory(ItemCategoryLink icl);
    
    //删除商品的分类(删除商品时)
    int deleteAllItemCategory(BigInteger Id);
    
    //删除用户的商品分类
    int deleteUserCategory(@Param("id")BigInteger id, 
                           @Param("ownerId")BigInteger ownerId);
    
    //查询该分类是不是基层分类
    int findCountParendIdById(@Param("parentId")BigInteger id, 
                              @Param("ownerId")BigInteger ownerId);
    
    //添加新的商品分类
    int saveUserCategory(CategoryInfo info);
    
    //修改用户的商品分类
    int updateUserCategory(CategoryInfo info);
}
