package com.rongcheng.erp.service.wzy_itemInfoService;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.ItemEspInfo;
import com.rongcheng.erp.dto.WzyItemInfo;

public interface ItemInfoService {
    
    //依据关键字查询用户的商品
    Map<String,Object> findUserByKeyWord(BigInteger ownerId, Integer row, Integer maxPage, String keyWord, BigInteger categoryId);
    
    //增加用户的商品
    BigInteger saveItemInfo(WzyItemInfo Item);
    
    //将数据转换成两组
    ItemEspInfo getItemEspInfo(WzyItemInfo info);
    ItemCommonInfo getItemCommonInfo(WzyItemInfo info);
    
    //更新用户的商品信息
    int updateItemInfo(WzyItemInfo info);
    
    //删除用户的商品（批量或者单独删除）
    int removeItemInfo(String id);
    
    //id查询
    List<WzyItemInfo> findItemInfoById(BigInteger id);

}
