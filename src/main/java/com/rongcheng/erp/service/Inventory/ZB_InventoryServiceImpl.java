package com.rongcheng.erp.service.Inventory;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.rongcheng.erp.dto.PlatformErpLinkShopWarehouseInfo;
import com.rongcheng.erp.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.ZB_InventoryDAO;


@Service()
public class ZB_InventoryServiceImpl implements ZB_InventoryService {

    /**
     * 栏目通道管理表 DAO层 对象
     */
    @Resource
    ZB_InventoryDAO inventoryDAO;
    
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
        
        //是否默认仓库
        Boolean defaultWarehouse = false;
        
        //如果仓库是0，说明是默认仓库
        if ((new BigInteger("0").equals(warehouseInfoId))) {
            defaultWarehouse = true;
        }
        
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
                    inventoryDAO.listItemCommonStockByAlertStock(start, rows, ownerId, warehouseInfoId, 
                            defaultWarehouse);
            
            //查询库存状态 条数 低于警戒线
            maxPage = inventoryDAO.countItemCommonStockByAlertStock(ownerId, warehouseInfoId, 
                    defaultWarehouse);
            
        } else {
            //查询库存状态 根据关键字
            listItemCommonStock = 
                    inventoryDAO.listItemCommonStockByKeywords(start, rows, ownerId, keywords, warehouseInfoId, 
                            defaultWarehouse);
          
            //查询库存状态 条数 根据关键字
            maxPage = inventoryDAO.countItemCommonStockByKeywords(ownerId, keywords, warehouseInfoId,
                    defaultWarehouse);
        }
        
        //加入库存状态
        map.put("listItemCommonStock", listItemCommonStock);
        
        //加入条数
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));
        
        //获取总量查询信息
        Map<String, Object> itemCommonStock = inventoryDAO.getItemCommonStockByWarehouseInfoId(ownerId, 
                warehouseInfoId, defaultWarehouse);
        
        //加入总量
        map.put("itemCommonStock", itemCommonStock);
        
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
     * @param itemCommonId 商品ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int updateInventoryState(Integer alertStockNum, BigInteger[] itemCommonId, BigInteger ownerId) {
        
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        //更新的行数
        int rows = 0;
        
        //遍历ID数组
        for (int i = 0; i < itemCommonId.length; i++) {
            
            //根据 商品ID 查询 库存信息表
            LocationItemStock locationItemStock = 
                    inventoryDAO.getLocationItemStockByItemCommonId(itemCommonId[i], ownerId);
            
            //如果没有该对象
            if (locationItemStock == null) {
                
                //创建
                locationItemStock = new LocationItemStock();
                
                //设置记录编号
                locationItemStock.setId(null);
                
                //设置仓库编码
                locationItemStock.setWarehouseId(null);
                
                //设置库位编码
                locationItemStock.setStocklocationId(null);
                
                //设置商品编号
                locationItemStock.setItemId(itemCommonId[i]);
                
                //设置库存量
                locationItemStock.setStockQuantity(0);
                
                //设置警戒量库存
                locationItemStock.setAlertStock(alertStockNum);
                
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
                
                rows += inventoryDAO.saveLocationItemStock(locationItemStock);
                
            //如果有对象
            } else {
                
                //设置警戒量
                locationItemStock.setAlertStock(alertStockNum);
                
                //设置修改时间
                locationItemStock.setGmtModified(time);
                
                //修改
                rows += inventoryDAO.modifyLocationItemStock(locationItemStock);
                
            }
            
        }
        
        return rows;
    }
    
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
    public Map<String, Object> loadStorageLocation(Integer nowPage, Integer rows, String keywords, 
            BigInteger ownerId, BigInteger warehouseInfoId) {
      
        //是否默认仓库
        Boolean defaultWarehouse = false;
        
        //如果仓库是0，说明是默认仓库
        if ((new BigInteger("0").equals(warehouseInfoId))) {
            defaultWarehouse = true;
        }
        
        //起始行数
        int start = (nowPage - 1) * rows;
        
        //创建返回集合map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //查询 库存库位 根据关键字
        List<Map<String, Object>> listItemCommonStocklocation = 
                inventoryDAO.listItemCommonStocklocationByKeywords(start, rows, ownerId, keywords, warehouseInfoId, 
                        defaultWarehouse);
        
        //加入库存库位
        map.put("listItemCommonStocklocation", listItemCommonStocklocation);
        
        //查询 库存库位 条数 根据关键字
        int maxPage = inventoryDAO.countItemCommonStocklocationByKeywords(ownerId, keywords, warehouseInfoId, 
                defaultWarehouse);
        
        //加入条数
        map.put("maxPage", Math.ceil((double)maxPage/(double)rows));
        
        //获取总量查询信息
        Map<String, Object> itemCommonStock = inventoryDAO.getItemCommonStockByWarehouseInfoId(ownerId, 
                warehouseInfoId, defaultWarehouse);
        
        //加入总量
        map.put("itemCommonStock", itemCommonStock);
        
        return map;
        
    }
    
    /**
     * 修改 库位库存
     * 
     * @param alertStockNum 库存
     * @param itemCommonId 商品ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int updateStorageLocation(Integer alertStockNum, BigInteger[] itemCommonId, BigInteger ownerId) {
        
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        //更新的行数
        int rows = 0;
        
        //遍历ID数组
        for (int i = 0; i < itemCommonId.length; i++) {
            
            //根据 商品ID 查询 库存信息表
            LocationItemStock locationItemStock = 
                    inventoryDAO.getLocationItemStockByItemCommonId(itemCommonId[i], ownerId);
            
            //如果没有该对象
            if (locationItemStock == null) {
                
                //创建
                locationItemStock = new LocationItemStock();
                
                //设置记录编号
                locationItemStock.setId(null);
                
                //设置仓库编码
                locationItemStock.setWarehouseId(null);
                
                //设置库位编码
                locationItemStock.setStocklocationId(null);
                
                //设置商品编号
                locationItemStock.setItemId(itemCommonId[i]);
                
                //设置库存量
                locationItemStock.setStockQuantity(alertStockNum);
                
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
                
                rows += inventoryDAO.saveLocationItemStock(locationItemStock);
                
            //如果有对象
            } else {
                
                //设置库存
                locationItemStock.setStockQuantity(alertStockNum);
                
                //设置修改时间
                locationItemStock.setGmtModified(time);
                
                //修改
                rows += inventoryDAO.modifyLocationItemStock(locationItemStock);
                
            }
        }
        
        return rows;
    }
    
    /**
     * 修改 库存清零
     * 
     * @param itemCommonId 商品ID
     * @param ownerId 主账号ID
     * @return
     * @author 赵滨
     */
    public int updateStocksEmpty(BigInteger[] itemCommonId, BigInteger ownerId) {
        
        //当前时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        
        //更新的行数
        int rows = 0;
        
        //遍历ID数组
        for (int i = 0; i < itemCommonId.length; i++) {
            
            //根据 商品ID 查询 库存信息表
            LocationItemStock locationItemStock = 
                    inventoryDAO.getLocationItemStockByItemCommonId(itemCommonId[i], ownerId);
            
            //如果没有该对象
            if (locationItemStock == null) {
                
                //创建
                locationItemStock = new LocationItemStock();
                
                //设置记录编号
                locationItemStock.setId(null);
                
                //设置仓库编码
                locationItemStock.setWarehouseId(null);
                
                //设置库位编码
                locationItemStock.setStocklocationId(null);
                
                //设置商品编号
                locationItemStock.setItemId(itemCommonId[i]);
                
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
                
                rows += inventoryDAO.saveLocationItemStock(locationItemStock);
                
            //如果有对象
            } else {
                
                //设置库存
                locationItemStock.setStockQuantity(0);
                
                //设置修改时间
                locationItemStock.setGmtModified(time);
                
                //修改
                rows += inventoryDAO.modifyLocationItemStock(locationItemStock);
                
            }
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
    public List<Map<String, Object>> listInventorySyncByItemIds(BigInteger[] itemIds, BigInteger ownerId) {
        //创建返回结果
        List<Map<String, Object>> list = new ArrayList<>();
        //遍历ID数组
        for (int i = 0; i < itemIds.length; i++) {
            BigInteger itemId = itemIds[i];
            Map<String, Object> map = new HashMap<>();
            //获取 系统商品对应关系关联表 同步信息
            List<PlatformErpLinkShopWarehouseInfo> platformErpLinkShopWarehouseInfoList =
                    inventoryDAO.listPlatformErpLinkShopWarehouseInfo(itemId, ownerId);
            map.put("platformErpLinkShopWarehouseInfoList", platformErpLinkShopWarehouseInfoList);

            list.add(map);
        }
        return list;
    }

    /**
     * 设置 库存同步配置
     * @param configuations 配置信息，json字符串
     * @param ownerId   主账号ID
     * @return 修改的条数
     * @author 赵滨
     */
    public int updateInventorySyncConfiguations(String configuations, BigInteger ownerId) {
        int row = 0;
        try {
            // 把字符串转换为JSONArray对象
            JSONArray jsonArray = JSONArray.fromObject(configuations);
            if(jsonArray.size() > 0){
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                for(int i = 0; i < jsonArray.size(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    //创建 系统商品对应关系关联表 然后设置信息
                    PlatformErpLink platformErpLink = new PlatformErpLink();

                    platformErpLink.setId(new BigInteger(jsonObject.get("id").toString()));
                    platformErpLink.setAutoSynchron(new Integer(jsonObject.get("autoSynchron").toString()));
                    platformErpLink.setAutoOnsale(new Integer(jsonObject.get("autoOnsale").toString()));
                    platformErpLink.setSynchronException(new Integer(jsonObject.get("synchronException").toString()));
                    platformErpLink.setAvailableStock(new Integer(jsonObject.get("availableStock").toString()));
                    platformErpLink.setAllocationRatio(new Integer(jsonObject.get("allocationRatio").toString()));
                    platformErpLink.setRemnantStock(new Integer(jsonObject.get("remnantStock").toString()));
                    platformErpLink.setGmtModified(new Timestamp(System.currentTimeMillis()));

                    //修改
                    row += inventoryDAO.updatePlatformErpLink(platformErpLink);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
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
     * @param warehouseId 库位ID
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
}
