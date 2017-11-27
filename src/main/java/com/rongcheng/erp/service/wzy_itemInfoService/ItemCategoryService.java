package com.rongcheng.erp.service.wzy_itemInfoService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.dto.WzyItemInfo;
import com.rongcheng.erp.entity.ItemCategoryLink;
import com.rongcheng.erp.exception.OrderOutNumberException;

public interface ItemCategoryService {

    //查找用户的商品分类
    Map<String,Object> findAllCategoryInfo(BigInteger ownerId);
    
    //查询商品的分类
    List<ItemCategoryLink> findAllItemCategoryLink(BigInteger itemId);
    
    //商品分类的创建
    int saveItemCategoryLink(WzyItemInfo info) throws OrderOutNumberException;
    
    //删除商品分类（删除商品时）
    int removeAllItemCategoryLink(String id) throws OrderOutNumberException;
    
    //删除商品分类(修改商品时)
    int deleteItemCategoryLink(WzyItemInfo info) throws OrderOutNumberException;
    
    //删除用户的商品分类
    int deleteUserCategory(BigInteger id, BigInteger ownerId) throws OrderOutNumberException;
    
    //修改用户的商品分类
    int updateUserCategory(BigInteger id, String name, BigInteger ownerId) throws OrderOutNumberException;

    //添加用户的商品分类
    int saveUserCategory(BigInteger id, String name, BigInteger ownerId) throws OrderOutNumberException;

    //批量更改商品分类
    int updateMoreCategory(String id, String insertCategory, BigInteger ownerId) throws OrderOutNumberException;

}
