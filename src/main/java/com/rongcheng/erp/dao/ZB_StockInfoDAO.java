package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


/**
 * 库存信息表 DAO层
 * 
 * @author 赵滨
 */
public interface ZB_StockInfoDAO {

    /**
     * 查询库存状态
     * 
     * <p>根据关键字
     * 
     * @param start 开始位置
     * @param row 查询几条
     * @param ownerId 主账号ID
     * @param keywords 关键字
     * @return list 结果集合
     * @author 赵滨
     */
    List<Map<String, Object>> listItemCommonStockByKeywords(int start, int row, BigInteger ownerId, String keywords);

    /**
     * 查询库存状态
     * 
     * <p>低于警戒线
     * 
     * @param start 开始位置
     * @param row 查询几条
     * @param ownerId 主账号ID
     * @return list 结果集合
     * @author 赵滨
     */
    List<Map<String, Object>> listItemCommonStockByAlertStock(int start, int row, BigInteger ownerId);
}
