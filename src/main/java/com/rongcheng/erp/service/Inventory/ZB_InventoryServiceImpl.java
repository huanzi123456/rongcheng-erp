package com.rongcheng.erp.service.Inventory;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;

import com.rongcheng.erp.dto.PlatformErpLinkShopWarehouseInfo;
import com.rongcheng.erp.entity.*;
import com.rongcheng.erp.exception.NameException;
import com.rongcheng.erp.exception.UploadStockException;
import com.rongcheng.erp.jd.upload.item.JingDongStockWriteUpdateSkuStock;
import com.rongcheng.erp.utils.UUIDTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.ZB_InventoryDAO;


@Service()
public class ZB_InventoryServiceImpl implements ZB_InventoryService {

    /**
     * 栏目通道管理表 DAO层 对象
     */
    @Resource
    ZB_InventoryDAO inventoryDAO;

    @Value("#{config['serverUrl']}")
    private String serverUrl;
    @Value("#{config['jdAppKeys']}")
    private String appKey;
    @Value("#{config['jdAppSecrets']}")
    private String appSecret;
//    @Value("#{config['jdAccessTokens']}")
//    private String accessToken;
    
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
    public Map<String, Object> loadInventoryState(Integer nowPage, Integer rows, String keywords, 
            Boolean isAlertStock, BigInteger ownerId, BigInteger warehouseInfoId) {
        //起始行数
        int start = (nowPage - 1) * rows;
        
        //创建返回集合map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //创建库存状态
        List<Map<String, Object>> listItemCommonStock = new ArrayList<Map<String,Object>>();
        
        //创建最大页数
        int maxPage;
        
        //如果是低于警戒线
        if (isAlertStock == true) {
            //查询库存状态 低于警戒线
            listItemCommonStock = 
                    inventoryDAO.listItemCommonStockByAlertStock(start, rows, ownerId, warehouseInfoId);
            
            //查询库存状态 条数 低于警戒线
            maxPage = inventoryDAO.countItemCommonStockByAlertStock(ownerId, warehouseInfoId);
            
        } else {
            //查询库存状态 根据关键字
            listItemCommonStock = 
                    inventoryDAO.listItemCommonStockByKeywords(start, rows, ownerId, keywords, warehouseInfoId);
          
            //查询库存状态 条数 根据关键字
            maxPage = inventoryDAO.countItemCommonStockByKeywords(ownerId, keywords, warehouseInfoId);
        }
        
        //加入库存状态
        map.put("listItemCommonStock", listItemCommonStock);
        
        //加入条数
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));
        
        //获取总量查询信息
        Map<String, Object> itemCommonStock =
                inventoryDAO.getItemCommonStockByWarehouseInfoId(ownerId, warehouseInfoId);

        //加入总量
        map.put("itemCommonStock", itemCommonStock);
        //加入行数
        map.put("rows", rows);

        return map;
    }
    
    /**
     * 查询 库存所在的仓库 列表
     * 
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public List<Map<String, Object>> listStockOfWarehouse(BigInteger ownerId) {
        
        return inventoryDAO.listStockOfWarehouse(ownerId);
    }

    /**
     * 修改 警戒库存
     * 
     * @param alertStockNum 警戒库存
     * @param locationItemStockId  库位库存ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int updateInventoryState(Integer alertStockNum, BigInteger[] locationItemStockId, BigInteger ownerId) {
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        //更新的行数
        int rows = 0;
        
        //遍历ID数组
        for (int i = 0; i < locationItemStockId.length; i++) {
            LocationItemStock locationItemStock = new LocationItemStock();
            locationItemStock.setId(locationItemStockId[i]);    //设置ID
            locationItemStock.setOwnerId(ownerId);              //设置主账号ID
            locationItemStock.setAlertStock(alertStockNum);     //设置警戒量
            locationItemStock.setGmtModified(time);             //设置修改时间
            //修改
            rows += inventoryDAO.modifyLocationItemStock(locationItemStock);
        }
        return rows;
    }
    
    /**
     * 加载 库位库存 页面
     * @param nowPage 当前页数
     * @param keywords 搜索关键字
     * @param rows 显示的行数
     * @param ownerId 主账号ID
     * @param warehouseInfoId 仓库ID
     * @return
     * @author 赵滨
     */
    public Map<String, Object> loadStorageLocation(Integer nowPage, Integer rows, String keywords, 
            BigInteger ownerId, BigInteger warehouseInfoId) {
        //起始行数
        int start = (nowPage - 1) * rows;
        //创建返回集合map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //查询 库存库位 根据关键字
        List<Map<String, Object>> listItemCommonStocklocation = 
                inventoryDAO.listItemCommonStocklocationByKeywords(start, rows, ownerId, keywords, warehouseInfoId);
        map.put("listItemCommonStocklocation", listItemCommonStocklocation);
        
        //查询 库存库位 条数 根据关键字
        int maxPage = inventoryDAO.countItemCommonStocklocationByKeywords(ownerId, keywords, warehouseInfoId);
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));

        //获取总量查询信息
        Map<String, Object> itemCommonStock =
                inventoryDAO.getItemCommonStockByWarehouseInfoId(ownerId, warehouseInfoId);
        map.put("itemCommonStock", itemCommonStock);

        map.put("rows", rows);

        return map;
    }
    
    /**
     * 修改 库位库存
     * 
     * @param alertStockNum 库存
     * @param locationItemStockId  库位库存ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int updateStorageLocation(Integer alertStockNum, BigInteger[] locationItemStockId, BigInteger ownerId) {
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        //更新的行数
        int rows = 0;

        //遍历ID数组
        for (int i = 0; i < locationItemStockId.length; i++) {
            LocationItemStock locationItemStock = new LocationItemStock();
            locationItemStock.setId(locationItemStockId[i]);    //设置ID
            locationItemStock.setOwnerId(ownerId);              //设置主账号ID
            locationItemStock.setStockQuantity(alertStockNum);  //设置库存
            locationItemStock.setGmtModified(time);             //设置修改时间
            //修改
            rows += inventoryDAO.modifyLocationItemStock(locationItemStock);
        }
        return rows;
    }
    
    /**
     * 修改 库存清零
     *
     * @param locationItemStockId  库位库存ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int updateStocksEmpty(BigInteger[] locationItemStockId, BigInteger ownerId) {
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        //更新的行数
        int rows = 0;

        //遍历ID数组
        for (int i = 0; i < locationItemStockId.length; i++) {
            LocationItemStock locationItemStock = new LocationItemStock();
            locationItemStock.setId(locationItemStockId[i]);    //设置ID
            locationItemStock.setOwnerId(ownerId);              //设置主账号ID
            locationItemStock.setStockQuantity(0);  //设置库存
            locationItemStock.setGmtModified(time);             //设置修改时间
            //修改
            rows += inventoryDAO.modifyLocationItemStock(locationItemStock);
        }
        return rows;
    }
    
    /**
     * 加载 仓库列表 页面
     * @param nowPage 当前页数
     * @param keywords 搜索关键字
     * @param warehouseStatus 该仓库是否被启用
     * @return
     * @author 赵滨
     */
    public Map<String, Object> loadInventoryList(Integer nowPage, Integer rows, String keywords, 
            Integer warehouseStatus, BigInteger ownerId) {
        
        //起始行数
        int start = (nowPage - 1) * rows;
        
        //是否默认全部选择
        Boolean defaultAll = false;
        
        //如果是否启用为空，说明是全部
        if (warehouseStatus == null) {
            
            //全部选择 为 是
            defaultAll = true;
            
        }
        
        //创建返回参数map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //获取仓库列表
        List<WarehouseInfo> listWarehouseInfo = 
                inventoryDAO.listWarehouseInfoByKeywords(ownerId, warehouseStatus, keywords, defaultAll, start, rows);
        
        //加入参数
        map.put("listWarehouseInfo", listWarehouseInfo);
        
        //获取仓库列表 条数
        int maxPage = inventoryDAO.countWarehouseInfoByKeywords(ownerId, warehouseStatus, keywords, defaultAll);
        
        //加入参数
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));
        
        //加入参数
        map.put("rows", rows);
        
        return map;
    }
    
    /**
     * 修改 仓库列表 状态
     * @param warehouseId 仓库ID
     * @param status 状态
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int updateUseDisable(BigInteger warehouseId, Integer status, BigInteger ownerId) {
        
        //获取仓库
        WarehouseInfo warehouseInfo = inventoryDAO.getWarehouseInfoById(warehouseId, ownerId);
        
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        //修改状态
        warehouseInfo.setWarehouseStatus(status);
        
        //修改修改时间
        warehouseInfo.setGmtModified(time);
        
        int row = inventoryDAO.modifyWarehouseInfo(warehouseInfo);
        
        return row;
    }
    
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
    public int inventoryListSaveAddUpdate(BigInteger warehouseId, String userWarehouseCode, String warehouseName, 
            String consignorName, String consignorTel, BigInteger regionId, String userAddress,
            BigInteger ownerId) {
        
      //定义返回行数
      int row = 0;
      
      //创建当前时间
      Timestamp time = new Timestamp(System.currentTimeMillis());
      
      //如果不存在ID 说明是 新增
      if (warehouseId == null) {
          
          //创建仓库对象
          WarehouseInfo warehouseInfo = new WarehouseInfo();
          
          //设置ID
          warehouseInfo.setId(null);
          
          //设置仓库用户自定义编码
          warehouseInfo.setUserWarehouseCode(userWarehouseCode);
          
          //设置仓库名称
          warehouseInfo.setWarehouseName(warehouseName);
          
          //设置发货人姓名
          warehouseInfo.setConsignorName(consignorName);
          
          //设置发货人电话
          warehouseInfo.setConsignorTel(consignorTel);
          
          //设置发货人手机
          warehouseInfo.setConsignorMobile(null);
          
          //设置发货地区id
          warehouseInfo.setRegionId(regionId);
          
          //设置仓库自定义发货地址
          warehouseInfo.setUserAddress(userAddress);
          
          //设置该仓库是否被启用
          warehouseInfo.setWarehouseStatus(1);
          
          //设置云仓订单对接码
          warehouseInfo.setConnectionCode(null);
          
          //设置备注
          warehouseInfo.setNote(null);
          
          //设置用户主账户ID
          warehouseInfo.setOwnerId(ownerId);
          
          //设置操作人
          warehouseInfo.setOperatorId(null);
          
          //设置该记录是否被授权
          warehouseInfo.setAuthorized(null);
          
          //设置记录创建时间
          warehouseInfo.setGmtCreate(time);
          
          //设置记录修改时间
          warehouseInfo.setGmtModified(null);

          //保存
          row = inventoryDAO.saveWarehouseInfo(warehouseInfo);
          
      //如果存在ID 说明说 修改
      } else if (warehouseId != null) {
          
          //获取仓库对象
          WarehouseInfo warehouseInfo = inventoryDAO.getWarehouseInfoById(warehouseId, ownerId);
          
          //如果对象不存在
          if (warehouseInfo == null) {
              
              return 0;
              
          }
          
          //设置ID
          //warehouseInfo.setId(warehouseInfo.id);
          
          //设置仓库用户自定义编码
          warehouseInfo.setUserWarehouseCode(userWarehouseCode);
          
          //设置仓库名称
          warehouseInfo.setWarehouseName(warehouseName);
          
          //设置发货人姓名
          warehouseInfo.setConsignorName(consignorName);
          
          //设置发货人电话
          warehouseInfo.setConsignorTel(consignorTel);
          
          //设置发货人手机
          //warehouseInfo.setConsignorMobile(warehouseInfo.getConsignorMobile());
          
          //设置发货地区id
          warehouseInfo.setRegionId(regionId);
          
          //设置仓库自定义发货地址
          warehouseInfo.setUserAddress(userAddress);
          
          //设置该仓库是否被启用
          //warehouseInfo.setWarehouseStatus(warehouseInfo.getWarehouseStatus());
          
          //设置云仓订单对接码
          //warehouseInfo.setConnectionCode(warehouseInfo.getConnectionCode());
          
          //设置备注
          //warehouseInfo.setNote(warehouseInfo.getNote());
          
          //设置用户主账户ID
          //warehouseInfo.setOwnerId(warehouseInfo.getOwnerId());
          
          //设置操作人
          //warehouseInfo.setOperatorId(warehouseInfo.getOperatorId());
          
          //设置该记录是否被授权
          //warehouseInfo.setAuthorized(warehouseInfo.getAuthorized());
          
          //设置记录创建时间
          //warehouseInfo.setGmtCreate(warehouseInfo.getGmtCreate());
          
          //设置记录修改时间
          warehouseInfo.setGmtModified(time);

          //修改
          row = inventoryDAO.modifyWarehouseInfo(warehouseInfo);
          
      }
      
      return row;
    }

    /**
     * 删除仓库
     * @param warehouseId 仓库ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int deleteInventoryList(BigInteger warehouseId, BigInteger ownerId) {
        return inventoryDAO.removeWarehouseInfoById(warehouseId, ownerId);
    }
    
    /**
     * 加载 库位管理列表 页面
     * @param nowPage 当前页码
     * @param keywords 关键字
     * @param ownerId 主账号ID
     * @param rows 显示的行数
     * @return
     * @author 赵滨
     */
    public Map<String, Object> loadLocation(Integer nowPage, String keywords, BigInteger ownerId, Integer rows) {
        
        //起始行数
        int start = (nowPage - 1) * rows;
        
        //创建返回集合map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //创建库位列表
        List<StocklocationInfo> listStocklocationInfo = 
                inventoryDAO.listStocklocationInfoByCode(keywords, ownerId, start, rows);
        
        //创建最大页数
        int maxPage = inventoryDAO.countStocklocationInfoByCode(keywords, ownerId);
        
        
        //加入库位列表
        map.put("listStocklocationInfo", listStocklocationInfo);
        
        //加入条数
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));
        
        //加入参数
        map.put("rows", rows);
        
        return map;
    }
    
    /**
     * 获取商品内容 根据 库位ID
     * @param locationId 库位ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public List<ItemCommonInfo> listItemByLocationId(BigInteger locationId, BigInteger ownerId) {
        //返回
        return inventoryDAO.listItemByLocationId(locationId, ownerId);
    }
    
    /**
     * 获取商品 内容  根据 关键字
     * @param nowPage 当前页码
     * @param keywords 关键字
     * @param ownerId 主账号ID
     * @param rows 显示的行数
     * @return
     * @author 赵滨
     */
    public Map<String, Object> listItemByKeywords(Integer nowPage, String keywords, BigInteger ownerId, int rows) {
        
        //创建返回集合map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //起始行数
        int start = (nowPage - 1) * rows;
        
        //获取商品集合
        List<ItemCommonInfo> listItemCommonInfo = inventoryDAO.listItemByKeywords(keywords, ownerId, start, rows);
        
        //加入 商品集合
        map.put("listItemCommonInfo", listItemCommonInfo);
        
        //获取最大条数
        int maxPage = inventoryDAO.countItemByKeywords(keywords, ownerId);
        
        //加入 条数
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));
        
        //返回
        return map;
        
    }
    
    /**
     * 获取商品 内容  根据 商品ID数组
     * @param itemIds 商品ID数组
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public List<ItemCommonInfo> listItemByItemIds(BigInteger[] itemIds, BigInteger ownerId) {
        
        //参数数组
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("itemIds", itemIds);
        params.put("ownerId", ownerId);
        
        return inventoryDAO.listItemByItemIds(params);
        
    }
    
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
    public int locationSaveAddUpdate(BigInteger locationId, String userStocklocationCode, String name, 
            BigInteger[] itemIds, BigInteger[] itemDelIds, BigInteger ownerId) {
        
        //定义返回行数
        int row = 0;
        
        //创建当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        //如果不存在ID 说明是 新增
        if (locationId == null) {
            
            //创建 erp专用-仓库信息表
            StocklocationInfo stocklocationInfo = new StocklocationInfo();
            
            //设置ID
            stocklocationInfo.setId(null);
            
            //设置仓库编码
            stocklocationInfo.setWarehouseId(new BigInteger("0"));
            
            //设置用户自定义库位编码
            stocklocationInfo.setUserStocklocationCode(userStocklocationCode);
            
            //设置库位名称
            stocklocationInfo.setName(name);
            
            //设置该库位是否被启用
            stocklocationInfo.setStocklocationStatus(null);
            
            //设置云仓订单对接码
            stocklocationInfo.setConnectionCode(null);
            
            //设置配送区域
            stocklocationInfo.setDistributionRegion(null);
            
            //设置备注
            stocklocationInfo.setNote(null);
            
            //设置用户主账户ID
            stocklocationInfo.setOwnerId(ownerId);
            
            //设置操作人
            stocklocationInfo.setOperatorId(null);
            
            //设置该记录是否被授权
            stocklocationInfo.setAuthorized(null);
            
            //设置记录创建时间
            stocklocationInfo.setGmtCreate(time);
            
            //设置记录修改时间
            stocklocationInfo.setGmtModified(null);
            
            //保存
            row += inventoryDAO.saveStocklocationInfoByCode(stocklocationInfo);
            
            //如果需要添加
            if (itemIds != null) {
                
                //遍历商品ID数组
                for (int i = 0; i < itemIds.length; i++) {
                    
                    //创建 库存信息表
                    LocationItemStock locationItemStock = new LocationItemStock();
                    
                    //设置记录编号
                    locationItemStock.setId(null);
                    
                    //设置仓库编码
                    locationItemStock.setWarehouseId(null);
                    
                    //设置库位编码
                    locationItemStock.setStocklocationId(stocklocationInfo.getId());
                    
                    //设置商品编号
                    locationItemStock.setItemId(itemIds[i]);
                    
                    //设置库存量
                    locationItemStock.setStockQuantity(0);
                    
                    //设置警戒量库存
                    locationItemStock.setAlertStock(0);
                    
                    //设置该库存商品是否参与发货
                    locationItemStock.setSendoutStatus(null);
                    
                    //设置自定义内容1
                    locationItemStock.setReserved1(null);

                    //设置备注
                    locationItemStock.setNote(null);

                    //设置用户主账户ID
                    locationItemStock.setOwnerId(ownerId);
                    
                    //设置操作人
                    locationItemStock.setOperatorId(null);
                    
                    //设置该记录是否被授权
                    locationItemStock.setAuthorization(null);
                    
                    //设置记录创建时间
                    locationItemStock.setGmtCreate(time);
                    
                    //设置记录修改时间
                    locationItemStock.setGmtModified(null);
                    
                    //保存
                    inventoryDAO.saveLocationItemStock(locationItemStock);
                    
                }
                
            }
            
        //否则 是 修改
        } else {
            
            //查询 erp专用-仓库信息表
            StocklocationInfo stocklocationInfo = inventoryDAO.getStocklocationInfoById(locationId, ownerId);
            
            //设置仓库编码
            //stocklocationInfo.setWarehouseId(null);
            
            //设置用户自定义库位编码
            stocklocationInfo.setUserStocklocationCode(userStocklocationCode);
            
            //设置库位名称
            stocklocationInfo.setName(name);
            
            //设置记录修改时间
            stocklocationInfo.setGmtModified(time);
            
            //保存
            row += inventoryDAO.modifyStocklocationInfoByCode(stocklocationInfo);
            
            //如果需要删除
            if (itemDelIds != null) {
                
                //遍历 商品ID数组 需要删除的
                for (int i = 0; i < itemDelIds.length; i++) {
                    
                    //删除
                    inventoryDAO.removeLocationItemStockByItemCommonId(itemDelIds[i], 
                            ownerId, stocklocationInfo.getId());
                }
            }
            
            //如果需要添加
            if (itemIds != null) {
                
                //遍历 商品ID数组
                for (int i = 0; i < itemIds.length; i++) {
                    
                    //获取 库存信息表
                    LocationItemStock locationItemStock = inventoryDAO.getLocationItemStockByItemlocationId(itemIds[i],
                            ownerId, stocklocationInfo.getId());
                    
                    //如果 找不到 对象
                    if (locationItemStock == null) {
                        
                        //创建对象
                        locationItemStock = new LocationItemStock();
                        
                        //设置记录编号
                        locationItemStock.setId(null);
                        
                        //设置仓库编码
                        locationItemStock.setWarehouseId(null);
                        
                        //设置库位编码
                        locationItemStock.setStocklocationId(stocklocationInfo.getId());
                        
                        //设置商品编号
                        locationItemStock.setItemId(itemIds[i]);
                        
                        //设置库存量
                        locationItemStock.setStockQuantity(0);
                        
                        //设置警戒量库存
                        locationItemStock.setAlertStock(0);
                        
                        //设置该库存商品是否参与发货
                        locationItemStock.setSendoutStatus(null);
                        
                        //设置自定义内容1
                        locationItemStock.setReserved1(null);

                        //设置备注
                        locationItemStock.setNote(null);

                        //设置用户主账户ID
                        locationItemStock.setOwnerId(ownerId);
                        
                        //设置操作人
                        locationItemStock.setOperatorId(null);
                        
                        //设置该记录是否被授权
                        locationItemStock.setAuthorization(null);
                        
                        //设置记录创建时间
                        locationItemStock.setGmtCreate(time);
                        
                        //设置记录修改时间
                        locationItemStock.setGmtModified(null);
                        
                        //保存
                        inventoryDAO.saveLocationItemStock(locationItemStock);
                    }
                }
            }
        }
        
        return row;
    }
    
    /**
     * 删除库位
     * @param locationId 库位ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int removeLocationById(BigInteger locationId, BigInteger ownerId) {
        
        //删除
        inventoryDAO.removeLocationItemStockByLocationId(ownerId, locationId);
        
        return inventoryDAO.removeStocklocationInfoById(locationId, ownerId);
    }

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
    public Map<String, Object> loadInventorySync(Integer nowPage, Integer rows, String keywords, Integer autoSynchron,
                                          BigInteger ownerId) {
        //起始行数
        int start = (nowPage - 1) * rows;
        //是否默认全部选择
        Boolean defaultAll = false;
        //如果是否启用为空，说明是全部
        if (autoSynchron == null) {
            //全部选择 为 是
            defaultAll = true;
        }

        //创建返回参数map
        Map<String, Object> map = new HashMap<String, Object>();
        //获取 自动同步
        List<Map<String, Object>> listItemCommonSync =
                inventoryDAO.listItemCommonSyncByKeywords(start, rows, ownerId, keywords, autoSynchron, defaultAll);
        //加入参数
        map.put("listItemCommonSync", listItemCommonSync);

        //获取仓库列表 条数
        int maxPage = inventoryDAO.countItemCommonSyncByKeywords(ownerId, keywords, autoSynchron, defaultAll);
        //加入参数
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));

        //加入参数
        map.put("rows", rows);

        return map;
    }

    /**
     * 加载 库存同步配置 根据商品ID数组
     * @param itemIds 商品ID数组
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public List<Map<String, Object>> listInventorySyncByItemBind(BigInteger[] itemIds, BigInteger ownerId) {
        //创建返回结果
        List<Map<String, Object>> list = new ArrayList<>();

        //遍历每一条商品，获取它的所有店铺和自动上传设置
        for (int i = 0; i < itemIds.length; i++) {
            BigInteger itemId = itemIds[i];

            Map<String, Object> map = new HashMap<>();
            //根据itemId去查询线上商品对应关系表中的信息，要求是店铺名称不能重复，可以出现多个店铺名称
            //获取 系统商品对应关系关联表 同步信息
            List<PlatformErpLinkShopWarehouseInfo> platformErpLinkShopWarehouseInfoList =
                    inventoryDAO.listPlatformErpLinkShopWarehouseInfoByGroup(itemId, ownerId, true);
            map.put("platformErpLinkShopWarehouseInfoList", platformErpLinkShopWarehouseInfoList);

            //根据itemId去查询库存关联表中的仓库和库位
            //查询库位商品库存关联表 获取仓库和库位
            List<Map<String, Object>> itemWarehouseStocklocationList =
                    inventoryDAO.listItemWarehouseStocklocation(itemId, ownerId);
            map.put("itemWarehouseStocklocationList", itemWarehouseStocklocationList);

            list.add(map);
        }

        return list;
    }

    /**
     * 设置 库存同步配置
     * @param configuations 配置信息，json字符串
     * @param itemIds 商品ID数组
     * @param ownerId   主账号ID
     * @return 修改的条数
     * @author 赵滨
     */
    public int updateInventorySyncConfiguations(String configuations, BigInteger[] itemIds, BigInteger ownerId)
            throws UploadStockException {
        int row = 0;

        //遍历每一条商品，获取它的所有店铺和自动上传设置  eg:101,102
        for (int i = 0; i < itemIds.length; i++) {
            BigInteger itemId = itemIds[i];

            //根据itemId去查询线上商品对应关系表中的信息，要求是店铺名称不能重复，可以出现多个店铺名称
            //获取 系统商品对应关系关联表 同步信息
            List<PlatformErpLinkShopWarehouseInfo> platformErpLinkShopWarehouseInfoList =
                    inventoryDAO.listPlatformErpLinkShopWarehouseInfoByGroup(itemId, ownerId, false);

            //根据itemId去查询库存关联表中的仓库和库位
            //查询库位商品库存关联表 获取仓库和库位
            List<Map<String, Object>> itemWarehouseStocklocationList =
                    inventoryDAO.listItemWarehouseStocklocation(itemId, ownerId);

            //遍历 系统商品对应关系关联表 同步信息，获取每一条 去与前台数据匹配，然后进行修改  eq:101的关联集合
            for (int j = 0; j < platformErpLinkShopWarehouseInfoList.size(); j++) {
                PlatformErpLinkShopWarehouseInfo platformErpLinkShopWarehouseInfo =
                        platformErpLinkShopWarehouseInfoList.get(j);
                //检查配置，如果匹配到这条信息就修改
                row += checkInventorySyncConfiguations(
                        configuations, platformErpLinkShopWarehouseInfo, itemWarehouseStocklocationList);

            }
        }
        return row;
    }

    /**
     * 检查配置，如果匹配到这条信息就修改  eq:101的关联集合 中的 单条
     * @param configuations 配置JSON字符串
     * @param platformErpLinkShopWarehouseInfo 库位商品库存关联单条对象
     * @param itemWarehouseStocklocationList 单条对象存放的仓库库位集合
     * @return
     */
    private int checkInventorySyncConfiguations(
            String configuations, PlatformErpLinkShopWarehouseInfo platformErpLinkShopWarehouseInfo,
            List<Map<String, Object>> itemWarehouseStocklocationList) throws UploadStockException {
        //获取店铺ID
        BigInteger shopId = platformErpLinkShopWarehouseInfo.getShopId();

        // 把字符串转换为JSONArray对象
        JSONArray jsonArray = JSONArray.fromObject(configuations);
        // 遍历 jsonarray 数组，把每一个对象转成 json 对象
        for(int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            //如果店铺相同，继续检查
            if (shopId.toString().equals(jsonObject.get("shopId").toString())) {
                String warehouseId = jsonObject.get("warehouseId").toString();
                String stocklocationId = jsonObject.get("stocklocationId").toString();

                //选中 全部仓库 全部库位
                if ("0".equals(warehouseId) && "0".equals(stocklocationId)) {
                    //修改
                    return updateInventorySyncConfiguation(
                            jsonObject, platformErpLinkShopWarehouseInfo);
                }

                //选中 其他仓库 全部库位
                if (!"0".equals(warehouseId) && "0".equals(stocklocationId)) {
                    //检查是否存在相同仓库库位
                    Boolean ExistRepetition =
                            ExistRepetitionWarehouseStocklocation(
                                    itemWarehouseStocklocationList, warehouseId, "");
                    if (ExistRepetition) {
                        //修改
                        return updateInventorySyncConfiguation(
                                jsonObject, platformErpLinkShopWarehouseInfo);
                    }
                }

                //选中 其他仓库 其他库位
                if (!"0".equals(warehouseId) && !"0".equals(stocklocationId)) {
                    //检查是否存在相同仓库库位
                    Boolean ExistRepetition =
                            ExistRepetitionWarehouseStocklocation(
                                    itemWarehouseStocklocationList, warehouseId, stocklocationId);
                    if (ExistRepetition) {
                        //修改
                        return updateInventorySyncConfiguation(
                                jsonObject, platformErpLinkShopWarehouseInfo);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 是否存在相同 仓库 库位
     * @param itemWarehouseStocklocationList 需要查找的集合
     * @param warehouseId 被检查的仓库
     * @param stocklocationId 被检查的库位
     * @return
     * @author 赵滨
     */
    private Boolean ExistRepetitionWarehouseStocklocation(
            List<Map<String, Object>> itemWarehouseStocklocationList, String warehouseId, String stocklocationId) {
        //检查仓库和库位是否存在集合中，可以同时存在，也可以只检查仓库
        //遍历集合
        for (int i = 0; i < itemWarehouseStocklocationList.size(); i++) {
            Map<String, Object> itemWarehouseStocklocation = itemWarehouseStocklocationList.get(i);
            //仓库相同
            if (itemWarehouseStocklocation.get("warehouseId").toString().equals(warehouseId)) {
                //库位相同 或者 不检查库位
                if (itemWarehouseStocklocation.get("stocklocationId").toString().equals(stocklocationId) ||
                        "".toString().equals(stocklocationId))
                    return true;
            }
        }
        return false;
    }

    /**
     * 修改 系统商品对应关系关联表 调用DAO层方法
     *
     * @param jsonObject                       参数JSON对象
     * @param platformErpLinkShopWarehouseInfo 库位商品库存关联单条对象
     * @return
     * @author 赵滨
     */
    private int updateInventorySyncConfiguation(
            JSONObject jsonObject, PlatformErpLinkShopWarehouseInfo platformErpLinkShopWarehouseInfo)
            throws UploadStockException {

        //获取 同步（开关键）
        Integer synchron = new Integer(jsonObject.get("synchron").toString());
        //获取 自动同步（开关键）
        Integer autoSynchron = new Integer(jsonObject.get("autoSynchron").toString());
        //定义 设置自动同步
        Integer setAutoSynchron = 0;

        //同步和自动同步都打开的时候，才开启自动同步
        if (synchron == 1 && autoSynchron == 1)
            setAutoSynchron = 1;

        //创建 系统商品对应关系关联表 然后设置信息
        PlatformErpLink platformErpLink = new PlatformErpLink();
        //复制基本内容
        platformErpLink.setId(platformErpLinkShopWarehouseInfo.getId());
        platformErpLink.setOwnerId(platformErpLinkShopWarehouseInfo.getOwnerId());
        platformErpLink.setPlatformItemSku(platformErpLinkShopWarehouseInfo.getPlatformItemSku());
        platformErpLink.setItemId(platformErpLinkShopWarehouseInfo.getItemId());
        platformErpLink.setPlatformId(platformErpLinkShopWarehouseInfo.getPlatformId());
        platformErpLink.setShopId(platformErpLinkShopWarehouseInfo.getShopId());

        //修改的内容
        platformErpLink.setAutoSynchron(setAutoSynchron);
        platformErpLink.setAutoOnsale(new Integer(jsonObject.get("autoOnsale").toString()));
        platformErpLink.setSynchronException(new Integer(jsonObject.get("synchronException").toString()));
        platformErpLink.setAvailableStock(new Integer(jsonObject.get("availableStock").toString()));
        platformErpLink.setAllocationRatio(new Integer(jsonObject.get("allocationRatio").toString()));
        platformErpLink.setRemnantStock(new Integer(jsonObject.get("remnantStock").toString()));
        platformErpLink.setWarehouseId(new BigInteger(jsonObject.get("warehouseId").toString()));
        platformErpLink.setStocklocationId(new BigInteger(jsonObject.get("stocklocationId").toString()));
        platformErpLink.setGmtModified(new Timestamp(System.currentTimeMillis()));

        //开启同步，需要调接口同步
        if (synchron == 1) {
            //同步库存,调用接口
            Boolean isSynchronizeSuccessful = synchronizeStock(platformErpLink);
            if (!isSynchronizeSuccessful) {
                throw new UploadStockException("上传库存至平台产生错误，请稍后重试");
            }
        }

        //修改
        return inventoryDAO.updatePlatformErpLink(platformErpLink);
    }

    private Boolean synchronizeStock(PlatformErpLink platformErpLink) {
        //获取 平台（来源）商品编码  eq：商品编码（京东）
        BigInteger platformItemSku = platformErpLink.getPlatformItemSku();
        //获取商品ID
        BigInteger itemId = platformErpLink.getItemId();
        //获取店铺ID
        BigInteger shopId = platformErpLink.getShopId();
        //获取仓库ID
        BigInteger warehouseId = platformErpLink.getWarehouseId();
        //获取库位ID
        BigInteger stocklocationId = platformErpLink.getStocklocationId();
        //获取主账号ID
        BigInteger ownerId = platformErpLink.getOwnerId();
        //获取库存可用量
        Integer availableStock = platformErpLink.getAvailableStock();
        //获取库存分配比例
        Integer allocationRatio = platformErpLink.getAllocationRatio();
        //获取库存零头
        Integer remnantStock = platformErpLink.getRemnantStock();
        //获取访问令牌
        String accessToken = inventoryDAO.getAuthorityAccessByShopId(shopId, ownerId).getAccessToken();

        //获取库存总量
        Integer sumStockQuantity = inventoryDAO.getSumStockQuantityByItemIdWarehouseIdStocklocationId(
                itemId, warehouseId, stocklocationId, ownerId);
        //按照规则计算
        if (availableStock == 1) {
            sumStockQuantity -= inventoryDAO.getSumOrderStockQuantityByItemId(itemId, true, ownerId);
        } else if (availableStock == 2) {
            sumStockQuantity -= inventoryDAO.getSumOrderStockQuantityByItemId(itemId, false, ownerId);
        }
        //获取库存数量
        Integer stockNum = sumStockQuantity * allocationRatio / 100 + remnantStock;

        //根据平台ID判断调用谁的接口
        if (platformErpLink.getPlatformId() != null) {
            if ("3".equals(platformErpLink.getPlatformId().toString())) {
                //调用京东接口，上传库存
                return JingDongStockWriteUpdateSkuStock.updateSkuStock(
                        serverUrl, accessToken, appKey, appSecret,
                        platformItemSku.longValue(), stockNum.longValue());
            }
        }
        return false;
    }

    /**
     * 加载 仓库和库位 页面
     *
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    public Map<String, Object> loadWarehouseAndStocklocation(BigInteger ownerId) {
        //返回结果集
        Map<String, Object> map = new HashMap<>();

        //仓库集合
        map.put("listWarehouseInfo", inventoryDAO.listWarehouseInfo(ownerId));
        //库位集合
        map.put("listStocklocationInfo", inventoryDAO.listStocklocationInfo(ownerId));

        //返回
        return map;
    }

    /**
     * 加载 云仓商品配对 页面
     *
     * @param nowPage         当前页数
     * @param keywords        搜索关键字
     * @param warehouseId 仓库ID
     * @param stocklocationId 库位ID
     * @param rows 显示的行数
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    public Map<String, Object> loadCommodityMatching(
            Integer nowPage, Integer rows,String keywords, BigInteger warehouseId, BigInteger stocklocationId,
            BigInteger ownerId) {
        //返回结果集
        Map<String, Object> map = new HashMap<>();

        //起始行数
        int start = (nowPage - 1) * rows;

        //查询配对信息
        List<Map<String, Object>> listLocationItemStockAndItem = inventoryDAO.listLocationItemStockAndItemByKeywords(
                ownerId, keywords, warehouseId, stocklocationId, start, rows);
        map.put("listLocationItemStockAndItem", listLocationItemStockAndItem);

        //查询条数
        int maxPage = inventoryDAO.countLocationItemStockAndItemByKeywords(
                ownerId, keywords, warehouseId, stocklocationId);
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));

        //返回
        return map;
    }

    /**
     * 删除 商品 的云仓关联关系
     *
     * @param locationItemStockId 关联关系ID
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    public int deleteCommodityMatching(BigInteger locationItemStockId, BigInteger ownerId) {
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());

        //创建关联关系对象
        LocationItemStock locationItemStock = new LocationItemStock();
        //设置ID
        locationItemStock.setId(locationItemStockId);
        //设置主账号ID
        locationItemStock.setOwnerId(ownerId);
        //设置修改时间
        locationItemStock.setGmtModified(time);

        //把关联ID设置为null
        return inventoryDAO.updateLocationItemStockOfBindIdSetNull(locationItemStock);
    }

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
    public Map<String, Object> loadWarehouseAndStocklocationOfAlert(
            Integer nowPageTop, String keyWordsTop, BigInteger warehouseIdTop, BigInteger stocklocationIdTop,
            Integer nowPageBottom, String keyWordsBottom, BigInteger warehouseIdBottom,
            BigInteger stocklocationIdBottom, Integer rows, BigInteger ownerId) {
        //返回结果集
        Map<String, Object> map = new HashMap<>();

        //如果加载顶部
        if (nowPageTop != null) {
            //起始行数
            int startTop = (nowPageTop - 1) * rows;

            //查询 云仓商品关联 信息
            List<Map<String, Object>> listLocationItemStockAndItemTop =
                    inventoryDAO.listLocationItemStockAndItemByKeywordsOfAlert(
                            ownerId, keyWordsTop, warehouseIdTop, stocklocationIdTop, startTop, rows);
            map.put("listLocationItemStockAndItemTop", listLocationItemStockAndItemTop);

            //查询条数
            int maxPage = inventoryDAO.countLocationItemStockAndItemByKeywordsOfAlert(
                    ownerId, keyWordsTop, warehouseIdTop, stocklocationIdTop);
            map.put("maxPageTop", Math.ceil((double)maxPage/(double)rows));
        }

        //如果加载底部
        if (nowPageBottom != null) {
            //起始行数
            int startBottom = (nowPageBottom - 1) * rows;

            //查询 云仓商品关联 信息
            List<Map<String, Object>> listLocationItemStockAndItemBottom =
                    inventoryDAO.listLocationItemStockAndItemByKeywordsOfAlert(
                            ownerId, keyWordsBottom, warehouseIdBottom, stocklocationIdBottom, startBottom, rows);
            map.put("listLocationItemStockAndItemBottom", listLocationItemStockAndItemBottom);

            //查询条数
            int maxPage = inventoryDAO.countLocationItemStockAndItemByKeywordsOfAlert(
                    ownerId, keyWordsBottom, warehouseIdBottom, stocklocationIdBottom);
            map.put("maxPageBottom", Math.ceil((double)maxPage/(double)rows));
        }

        //返回
        return map;
    }

    /**
     * 提交 云仓商品关联关系
     * @param topId 顶部关系ID
     * @param bindMap 关系集合
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int bindCommit(BigInteger topId, String bindMap, BigInteger ownerId) {
        //定义基本参数
        int row = 0;
        Timestamp time = new Timestamp(System.currentTimeMillis());

        //查询顶部关系ID，查看该条关系是否存在关联UUID，存在直接使用，不存在先创建
        LocationItemStock locationItemStockTop = inventoryDAO.getLocationItemStockByLocationItemStockId(topId, ownerId);
        //获取关联ID
        String bindId = locationItemStockTop.getBindId();
        if (bindId == null) {
            //生成新的UUID
            bindId = UUIDTool.createUUIDString();

            //设置到关联关系中，并保存
            LocationItemStock locationItemStock = new LocationItemStock();
            locationItemStock.setId(locationItemStockTop.getId());
            locationItemStock.setOwnerId(ownerId);
            locationItemStock.setBindId(bindId);
            locationItemStock.setGmtModified(time);
            inventoryDAO.updateLocationItemStock(locationItemStock);
        }

        try {
            //转换json对象
            JSONObject jsonObject = JSONObject.fromObject(bindMap);
            //迭代器遍历
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next().toString();
                String value = jsonObject.getString(key);

                //创建底部关联对象，并设置相关信息
                LocationItemStock locationItemStockBottom = new LocationItemStock();
                locationItemStockBottom.setId(new BigInteger(key));
                locationItemStockBottom.setOwnerId(ownerId);
                locationItemStockBottom.setGmtModified(time);
                //判断value的值，来确定是新增关联，还是取消关联
                if ("true".equals(value)) {
                    locationItemStockBottom.setBindId(bindId);
                    //建立关联
                    row += inventoryDAO.updateLocationItemStock(locationItemStockBottom);
                } else if ("false".equals(value)) {
                    //取消关联
                    row += inventoryDAO.updateLocationItemStockOfBindIdSetNull(locationItemStockBottom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
