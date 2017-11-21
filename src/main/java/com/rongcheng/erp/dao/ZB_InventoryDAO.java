package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.dto.PlatformErpLinkShopWarehouseInfo;
import com.rongcheng.erp.entity.*;


/**
 * 库存信息表 DAO层
 *
 * @author 赵滨
 */
public interface ZB_InventoryDAO {

    /**
     * 查询库存状态
     *
     * <p>根据关键字
     *
     * @param start 开始位置
     * @param row 查询几条
     * @param ownerId 主账号ID
     * @param keywords 关键字
     * @param warehouseInfoId 仓库ID
     * @return list 结果集合
     * @author 赵滨
     */
    List<Map<String, Object>> listItemCommonStockByKeywords(int start, int row, BigInteger ownerId, String keywords,
                                                            BigInteger warehouseInfoId);

    /**
     * 查询库存状态 条数
     *
     * <p>根据关键字
     *
     * @param ownerId 主账号ID
     * @param keywords 关键字
     * @param warehouseInfoId 仓库ID
     * @return list 结果集合
     * @author 赵滨
     */
    int countItemCommonStockByKeywords(BigInteger ownerId, String keywords, BigInteger warehouseInfoId);

    /**
     * 查询库存状态
     *
     * <p>低于警戒线
     *
     * @param start 开始位置
     * @param row 查询几条
     * @param ownerId 主账号ID
     * @param warehouseInfoId 仓库ID
     * @return list 结果集合
     * @author 赵滨
     */
    List<Map<String, Object>> listItemCommonStockByAlertStock(
            int start, int row, BigInteger ownerId, BigInteger warehouseInfoId);

    /**
     * 查询库存状态 条数
     *
     * <p>低于警戒线
     *
     * @param ownerId 主账号ID
     * @param warehouseInfoId 仓库ID
     * @return list 结果集合
     * @author 赵滨
     */
    int countItemCommonStockByAlertStock(BigInteger ownerId, BigInteger warehouseInfoId);

    /**
     * 查询 库存所在的仓库 列表
     *
     * <p>仓库的名称和ID
     *
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<Map<String, Object>> listStockOfWarehouse(BigInteger ownerId);

    /**
     * 查询 库存信息表
     *
     * <p>根据商品ID 关联查询
     *
     * @param itemCommonId 商品ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<LocationItemStock> listLocationItemStockByItemCommonId(BigInteger itemCommonId, BigInteger ownerId);

    /**
     * 查询 库存信息表
     *
     * <p>根据商品ID 库位ID 关联查询
     *
     * @param itemCommonId 商品ID
     * @param ownerId 主账号ID
     * @param locationId 库位ID
     * @return
     * @author 赵滨
     */
    LocationItemStock getLocationItemStockByItemlocationId(BigInteger itemCommonId,
                                                           BigInteger ownerId, BigInteger locationId);

    /**
     * 保存 库存信息表
     *
     * <p>传入库存信息表对象，MyBatis使用对象保存栏目表。
     *
     * @param locationItemStock 库存信息表
     * @return
     * @author 赵滨
     */
    int saveLocationItemStock(LocationItemStock locationItemStock);

    /**
     * 修改 库存信息表
     *
     * <p>传入库存信息表对象修改栏目内容
     *
     * @param locationItemStock 库存信息表
     * @return
     * @author 赵滨
     */
    int modifyLocationItemStock(LocationItemStock locationItemStock);

    /**
     * 移除 库存信息表
     *
     * <p>根据库位ID 关联查询
     *
     * @param itemCommonId 商品ID
     * @param ownerId 主账号ID
     * @param locationId 库位ID
     * @return
     * @author 赵滨
     */
    int removeLocationItemStockByItemCommonId(BigInteger itemCommonId, BigInteger ownerId, BigInteger locationId);

    /**
     * 移除 库存信息表
     *
     * <p>根据库位ID 关联查询
     *
     * @param ownerId 主账号ID
     * @param locationId 库位ID
     * @return
     * @author 赵滨
     */
    int removeLocationItemStockByLocationId(BigInteger ownerId, BigInteger locationId);

    /**
     * 查询 库存相关统计
     * @param ownerId 主账号ID
     * @param warehouseInfoId 仓库ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> getItemCommonStockByWarehouseInfoId(BigInteger ownerId, BigInteger warehouseInfoId);

    /**
     * 查询 商品库存库位
     *
     * <p>根据关键字
     *
     * @param start 开始位置
     * @param row 查询几条
     * @param ownerId 主账号ID
     * @param keywords 关键字
     * @param warehouseInfoId 仓库ID
     * @return list 结果集合
     * @author 赵滨
     */
    List<Map<String, Object>> listItemCommonStocklocationByKeywords(
            int start, int row, BigInteger ownerId, String keywords, BigInteger warehouseInfoId);

    /**
     * 查询 商品库存库位 条数
     *
     * <p>根据关键字
     *
     * @param ownerId 主账号ID
     * @param keywords 关键字
     * @param warehouseInfoId 仓库ID
     * @return list 结果集合
     * @author 赵滨
     */
    int countItemCommonStocklocationByKeywords(BigInteger ownerId, String keywords, BigInteger warehouseInfoId);

    /**
     * 查询 仓库列表
     *
     * <p>根据 关键字
     *
     * @param ownerId 主账号ID
     * @param warehouseStatus 该仓库是否被启用
     * @param keywords 关键字
     * @param defaultAll 是否默认全部选择
     * @return
     * @author 赵滨
     */
    List<WarehouseInfo> listWarehouseInfoByKeywords(BigInteger ownerId, Integer warehouseStatus, String keywords,
                                                    Boolean defaultAll, int start, int row);

    /**
     * 查询 仓库列表 条数
     *
     * <p>根据 关键字
     *
     * @param ownerId 主账号ID
     * @param warehouseStatus 该仓库是否被启用
     * @param keywords 关键字
     * @param defaultAll 是否默认全部选择
     * @return
     * @author 赵滨
     */
    int countWarehouseInfoByKeywords(BigInteger ownerId, Integer warehouseStatus, String keywords,
                                     Boolean defaultAll);

    /**
     * 保存 仓库列表
     *
     * <p>传入对象，MyBatis使用对象保存。
     *
     * @param warehouseInfo 仓库列表
     * @return
     * @author 赵滨
     */
    int saveWarehouseInfo(WarehouseInfo warehouseInfo);

    /**
     * 修改 仓库列表
     *
     * <p>传入对象修改内容
     *
     * @param warehouseInfo 仓库列表
     * @return
     * @author 赵滨
     */
    int modifyWarehouseInfo(WarehouseInfo warehouseInfo);

    /**
     * 删除仓库
     * @param warehouseId 仓库ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int removeWarehouseInfoById(BigInteger warehouseId, BigInteger ownerId);

    /**
     * 获取 仓库列表
     *
     * <p>根据ID
     *
     * @param id
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    WarehouseInfo getWarehouseInfoById(BigInteger id, BigInteger ownerId);

    /**
     * 获取 库位列表
     *
     * <p>根据 库位编码
     *
     * @param userStocklocationCode 库位编码
     * @param ownerId 主账号ID
     * @param start 开始
     * @param row 行数
     * @return
     * @author 赵滨
     */
    List<StocklocationInfo> listStocklocationInfoByCode(String userStocklocationCode, BigInteger ownerId,
                                                        int start, int row);

    /**
     * 获取 库位列表 条数
     *
     * <p>根据 库位编码
     *
     * @param userStocklocationCode库位编码
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int countStocklocationInfoByCode(String userStocklocationCode, BigInteger ownerId);

    /**
     * 获取 库位列表 单条
     *
     * <p>根据 ID
     *
     * @param id ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    StocklocationInfo getStocklocationInfoById(BigInteger id, BigInteger ownerId);

    /**
     * 保存 仓库库位
     * @param stocklocationInfo 库位
     * @return
     * @author 赵滨
     */
    int saveStocklocationInfoByCode(StocklocationInfo stocklocationInfo);

    /**
     * 修改 仓库库位
     * @param stocklocationInfo 库位
     * @return
     * @author 赵滨
     */
    int modifyStocklocationInfoByCode(StocklocationInfo stocklocationInfo);

    /**
     * 删除 仓库库位
     * @param id 栏目ID
     * @param ownerId 主账号ID
     * @return row 删除的行数
     * @author 赵滨
     */
    int removeStocklocationInfoById(BigInteger id, BigInteger ownerId);

    /**
     * 获取商品内容 根据 库位ID
     * @param locationId 库位ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<ItemCommonInfo> listItemByLocationId(BigInteger locationId, BigInteger ownerId);

    /**
     * 获取商品内容 根据 关键字
     * @param keywords 关键字
     * @param ownerId 主账号ID
     * @param start 开始
     * @param row 行数
     * @return
     * @author 赵滨
     */
    List<ItemCommonInfo> listItemByKeywords(String keywords, BigInteger ownerId, int start, int row);

    /**
     * 获取商品 条数 根据 关键字
     * @param keywords 关键字
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int countItemByKeywords(String keywords, BigInteger ownerId);

    /**
     * 获取商品 内容  根据 商品ID数组
     * @param itemIds 商品ID数组
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    List<ItemCommonInfo> listItemByItemIds(Map<String, Object> params);

    /**
     * 查询库存同步
     *
     * <p>根据关键字
     *
     * @param start 开始位置
     * @param row 查询几条
     * @param ownerId 主账号ID
     * @param keywords 关键字
     * @param autoSynchron 自动同步
     * @param defaultAll 是否默认全部选择
     * @return list 结果集合
     * @author 赵滨
     */
    List<Map<String, Object>> listItemCommonSyncByKeywords(int start, int row, BigInteger ownerId, String keywords,
                                                           Integer autoSynchron, Boolean defaultAll);

    /**
     * 查询库存同步 条数
     *
     * <p>根据关键字
     *
     * @param ownerId 主账号ID
     * @param keywords 关键字
     * @param autoSynchron 自动同步
     * @param defaultAll 是否默认全部选择
     * @return list 结果集合
     * @author 赵滨
     */
    int countItemCommonSyncByKeywords(BigInteger ownerId, String keywords, Integer autoSynchron, Boolean defaultAll);

    /**
     * 查询系统商品对应关系关联表
     * @param itemId 商品ID
     * @param ownerId 主账号ID
     * @param isGroup 按照分组
     * @return
     * @author 赵滨
     */
    List<PlatformErpLinkShopWarehouseInfo> listPlatformErpLinkShopWarehouseInfoByGroup(
            BigInteger itemId, BigInteger ownerId, Boolean isGroup);

    /**
     * 查询库位商品库存关联表 获取仓库和库位
     * @param itemId 商品ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<Map<String, Object>> listItemWarehouseStocklocation(BigInteger itemId, BigInteger ownerId);

    /**
     * 更新系统商品对应关系关联表
     * @param platformErpLink 系统商品对应关系关联表
     * @return row 行数
     * @author 赵滨
     */
    int updatePlatformErpLink(PlatformErpLink platformErpLink);

    /**
     * 获取 用户授权信息表 根据参数
     * @param shopId 店铺ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    AuthorityAccess getAuthorityAccessByShopId(BigInteger shopId, BigInteger ownerId);

    /**
     * 获取 总库存 根据参数
     * @param itemId  商品ID
     * @param warehouseId 仓库ID
     * @param stocklocationId 库位ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int getSumStockQuantityByItemIdWarehouseIdStocklocationId(
            BigInteger itemId, BigInteger warehouseId, BigInteger stocklocationId, BigInteger ownerId);

    /**
     * 获取 订单占用量 根据参数
     * @param itemId 商品ID
     * @param isPay 是否从付款开始匹配
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int getSumOrderStockQuantityByItemId(BigInteger itemId, Boolean isPay, BigInteger ownerId);

    /**
     * 查询 仓库信息
     * @param ownerId 主账号ID
     * @return
     */
    List<WarehouseInfo> listWarehouseInfo(BigInteger ownerId);

    /**
     * 查询 库位信息
     * @param ownerId 主账号ID
     * @return
     */
    List<StocklocationInfo> listStocklocationInfo(BigInteger ownerId);

    /**
     * 查询 云仓商品配对
     * @param ownerId   主账号ID
     * @param keywords  关键字
     * @param warehouseId   仓库ID
     * @param stocklocationId   库位ID
     * @param start 开始
     * @param row   行数
     * @return listMap 云仓商品配对
     * @author 赵滨
     */
    List<Map<String, Object>> listLocationItemStockAndItemByKeywords(
            BigInteger ownerId, String keywords,BigInteger warehouseId, BigInteger stocklocationId, int start,
            int row);

    /**
     * 查询 云仓商品配对 条数
     * @param ownerId   主账号ID
     * @param keywords  关键字
     * @param warehouseId   仓库ID
     * @param stocklocationId   库位ID
     * @return row 条数
     * @author 赵滨
     */
    int countLocationItemStockAndItemByKeywords(
            BigInteger ownerId, String keywords,BigInteger warehouseId, BigInteger stocklocationId);

    /**
     * 把关联ID设置为null
     * @param locationItemStock 关联关系
     * @return row 条数
     * @author 赵滨
     */
    int updateLocationItemStockOfBindIdSetNull(LocationItemStock locationItemStock);

    /**
     * 查询 云仓商品关联 弹出框
     * @param ownerId   主账号ID
     * @param keywords  关键字
     * @param warehouseId   仓库ID
     * @param stocklocationId   库位ID
     * @param start 开始
     * @param row   行数
     * @return listMap 云仓商品配对
     * @author 赵滨
     */
    List<Map<String, Object>> listLocationItemStockAndItemByKeywordsOfAlert(
            BigInteger ownerId, String keywords,BigInteger warehouseId, BigInteger stocklocationId, int start,
            int row);

    /**
     * 查询 云仓商品关联 弹出框 条数
     * @param ownerId   主账号ID
     * @param keywords  关键字
     * @param warehouseId   仓库ID
     * @param stocklocationId   库位ID
     * @return row 条数
     * @author 赵滨
     */
    int countLocationItemStockAndItemByKeywordsOfAlert(
            BigInteger ownerId, String keywords,BigInteger warehouseId, BigInteger stocklocationId);

    /**
     * 查询 云仓商品关联关系
     *
     * <p>根据 ID 查询
     *
     * @param locationItemStockId ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    LocationItemStock getLocationItemStockByLocationItemStockId(BigInteger locationItemStockId, BigInteger ownerId);

    /**
     * 更新 库位商品库存关联表
     * @param locationItemStock 库位商品库存关联
     * @return row 条数
     * @author 赵滨
     */
    int updateLocationItemStock(LocationItemStock locationItemStock);
}
