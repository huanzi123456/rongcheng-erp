package com.rongcheng.erp.service.Inventory;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.utils.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.ItemCommonInfo;


/**
 * 库位设置 业务层
 * 
 * @author 赵滨
 */
public interface ZB_InventoryService {

    /**
     * 加载 库存状态 页面
     * @param nowPage 当前页数
     * @param keywords 搜索关键字
     * @param rows 显示的行数
     * @param isAlertStock 是否低于警戒线
     * @param ownerId 主账号ID
     * @param warehouseInfoId 仓库ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadInventoryState(Integer nowPage, Integer rows, String keywords,
                                           Boolean isAlertStock, BigInteger ownerId, BigInteger warehouseInfoId);
    
    /**
     * 查询 库存所在的仓库 列表
     * 
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<Map<String, Object>> listStockOfWarehouse(BigInteger ownerId);
    
    /**
     * 修改 警戒库存
     * 
     * @param alertStockNum 警戒库存
     * @param locationItemStockId  库位库存ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int updateInventoryState(Integer alertStockNum, BigInteger[] locationItemStockId, BigInteger ownerId);
    
    /**
     * 加载 库存库位 页面
     * @param nowPage 当前页数
     * @param keywords 搜索关键字
     * @param rows 显示的行数
     * @param ownerId 主账号ID
     * @param warehouseInfoId 仓库ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadStorageLocation(Integer nowPage, Integer rows, String keywords,
                                            BigInteger ownerId, BigInteger warehouseInfoId);
    
    /**
     * 修改 库位库存
     * 
     * @param alertStockNum 库存
     * @param locationItemStockId  库位库存ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int updateStorageLocation(Integer alertStockNum, BigInteger[] locationItemStockId, BigInteger ownerId);
    
    /**
     * 修改 库存清零
     *
     * @param locationItemStockId  库位库存ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int updateStocksEmpty(BigInteger[] locationItemStockId, BigInteger ownerId);
    
    /**
     * 加载 仓库列表 页面
     * @param nowPage 当前页数
     * @param keywords 搜索关键字
     * @param warehouseStatus 该仓库是否被启用
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadInventoryList(Integer nowPage, Integer rows, String keywords,
                                          Integer warehouseStatus, BigInteger ownerId);
    
    /**
     * 修改 仓库列表 状态
     * @param warehouseId 仓库ID
     * @param status 状态
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int updateUseDisable(BigInteger warehouseId, Integer status, BigInteger ownerId);
    
    /**
     * 保存新增修改仓库
     * @param warehouseId 仓库ID
     * @param userWarehouseCode 仓库编码
     * @param warehouseName 仓库名称
     * @param consignorName 联系人
     * @param consignorTel 联系人电话
     * @param regionId 地区ID
     * @param userAddress 地址详细
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int inventoryListSaveAddUpdate(BigInteger warehouseId, String userWarehouseCode, String warehouseName,
                                   String consignorName, String consignorTel, BigInteger regionId, String userAddress,
                                   BigInteger ownerId);
    
    /**
     * 加载 库位管理列表 页面
     * @param nowPage 当前页码
     * @param keywords 关键字
     * @param ownerId 主账号ID
     * @param rows 显示的行数
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadLocation(Integer nowPage, String keywords, BigInteger ownerId, Integer rows);
    
    /**
     * 获取商品内容 根据 库位ID
     * @param locationId 库位ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<ItemCommonInfo> listItemByLocationId(BigInteger locationId, BigInteger ownerId);
    
    /**
     * 获取商品 内容  根据 关键字
     * @param nowPage 当前页码
     * @param keywords 关键字
     * @param ownerId 主账号ID
     * @param rows 显示的行数
     * @return
     * @author 赵滨
     */
    Map<String, Object> listItemByKeywords(Integer nowPage, String keywords, BigInteger ownerId, int rows);
    
    /**
     * 获取商品 内容  根据 商品ID数组
     * @param itemIds 商品ID数组
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<ItemCommonInfo> listItemByItemIds(BigInteger[] itemIds, BigInteger ownerId);
    
    /**
     * 保存新增修改库位
     * @param locationId 库位ID
     * @param userStocklocationCode 用户自定义库位编码
     * @param name 库位名称
     * @param itemIds 商品ID数组
     * @param itemDelIds 商品ID数组 需要删除的
     * @param ownerId 主账号ID
     * @return
     * @author  赵滨
     */
    int locationSaveAddUpdate(BigInteger locationId, String userStocklocationCode, String name,
                              BigInteger[] itemIds, BigInteger[] itemDelIds, BigInteger ownerId);
    
    /**
     * 删除库位
     * @param locationId 库位ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int removeLocationById(BigInteger locationId, BigInteger ownerId);

    /**
     * 加载 自动同步 页面
     * @param nowPage 当前页数
     * @param rows 显示的行数
     * @param keywords 搜索关键字
     * @param autoSynchron 自动同步
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadInventorySync(Integer nowPage, Integer rows, String keywords, Integer autoSynchron,
                                          BigInteger ownerId);

    /**
     * 加载 库存同步配置 根据商品ID数组
     * @param itemIds 商品ID数组
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    List<Map<String, Object>> listInventorySyncByItemBind(BigInteger[] itemIds, BigInteger ownerId);

    /**
     * 设置 库存同步配置
     * @param configuations 配置信息，json字符串
     * @param itemIds 商品ID数组
     * @param ownerId   主账号ID
     * @return 修改的条数
     * @author 赵滨
     */
    int updateInventorySyncConfiguations(String configuations, BigInteger[] itemIds, BigInteger ownerId);

    /**
     * 加载 仓库和库位 页面
     *
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadWarehouseAndStocklocation(BigInteger ownerId);

    /**
     * 加载 云仓商品配对 页面
     *
     * @param nowPage     当前页数
     * @param keywords    搜索关键字
     * @param warehouseId 仓库ID
     * @param stocklocationId 库位ID
     * @param rows 显示的行数
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadCommodityMatching(Integer nowPage, Integer rows,String keywords, BigInteger warehouseId,
                              BigInteger stocklocationId, BigInteger ownerId);

    /**
     * 删除 商品 的云仓关联关系
     *
     * @param locationItemStockId 关联关系ID
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    int deleteCommodityMatching(BigInteger locationItemStockId, BigInteger ownerId);

    /**
     * 加载 云仓商品关联 弹出框
     *
     * @param nowPageTop    当前页面，顶部
     * @param keyWordsTop   关键字，顶部
     * @param warehouseIdTop    仓库ID，顶部
     * @param stocklocationIdTop    库位ID，顶部
     * @param nowPageBottom 当前页面，底部
     * @param keyWordsBottom    关键字，底部
     * @param warehouseIdBottom 仓库ID，底部
     * @param stocklocationIdBottom 库位ID，底部
     * @param rows 显示的行数
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    Map<String, Object> loadWarehouseAndStocklocationOfAlert(
            Integer nowPageTop, String keyWordsTop, BigInteger warehouseIdTop, BigInteger stocklocationIdTop,
            Integer nowPageBottom, String keyWordsBottom, BigInteger warehouseIdBottom,
            BigInteger stocklocationIdBottom, Integer rows, BigInteger ownerId);

    /**
     * 提交 云仓商品关联关系
     * @param topId 顶部关系ID
     * @param bindMap 关系集合
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    int bindCommit(BigInteger topId, String bindMap, BigInteger ownerId);
}
