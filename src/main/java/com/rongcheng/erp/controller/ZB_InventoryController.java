package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.exception.UploadStockException;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.service.Inventory.ZB_InventoryService;
import com.rongcheng.erp.utils.JsonResult;

/**
 * 库位设置 控制层
 *
 * @author 赵滨
 */
@Controller
public class ZB_InventoryController {

    @Resource
    private ZB_InventoryService inventoryService;

    //分页相关（每页多少条）
    @Value("#{config['rows']}")
    private int rows;

    @ExceptionHandler(UploadStockException.class)
    @ResponseBody
    public JsonResult uploadStockException(UploadStockException e){
        e.printStackTrace();
        return new JsonResult(JsonResult.ERROR, e);
    }

    /**
     * 跳转 库存状态 页面
     *
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/inventoryState.do")
    public String inventoryState() {
        return "inventory/inventoryState";
    }

    /**
     * 加载 库存状态 页面
     *
     * @param nowPage         当前页数
     * @param keywords        搜索关键字
     * @param isAlertStock    是否低于警戒线
     * @param warehouseInfoId 仓库ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryState/loadInventoryState.do")
    public JsonResult loadInventoryState(
            Integer nowPage, String keywords, Boolean isAlertStock, BigInteger warehouseInfoId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map =
                inventoryService.loadInventoryState(nowPage, rows, keywords, isAlertStock, ownerId, warehouseInfoId);
        //返回
        return new JsonResult(map);
    }

    /**
     * 加载 仓库 栏目
     *
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryState/loadWarehouseInfo.do")
    public JsonResult loadWarehouseInfo(HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.listStockOfWarehouse(ownerId));
    }

    /**
     * 修改 警戒库存
     *
     * @param session  HttpSession
     * @param alertStockNum 警戒库存
     * @param locationItemStockId  库位库存ID
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryState/updateInventoryState.do")
    public JsonResult updateInventoryState(
            Integer alertStockNum, BigInteger[] locationItemStockId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.updateInventoryState(alertStockNum, locationItemStockId, ownerId));
    }

    /**
     * 跳转 库位库存 页面
     *
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/storageLocation.do")
    public String storageLocation() {
        return "inventory/storageLocation";
    }

    /**
     * 加载 库位库存 页面
     *
     * @param nowPage         当前页数
     * @param keywords        搜索关键字
     * @param warehouseInfoId 仓库ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/storageLocation/loadStorageLocation.do")
    public JsonResult loadInventoryState(
            Integer nowPage, String keywords, BigInteger warehouseInfoId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map =
                inventoryService.loadStorageLocation(nowPage, rows, keywords, ownerId, warehouseInfoId);
        //返回
        return new JsonResult(map);
    }

    /**
     * 修改 库位库存
     *
     * @param alertStockNum 库存
     * @param locationItemStockId  库位库存ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/storageLocation/updateStorageLocation.do")
    public JsonResult updateStorageLocation(
            Integer alertStockNum, BigInteger[] locationItemStockId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.updateStorageLocation(alertStockNum, locationItemStockId, ownerId));
    }

    /**
     * 修改 库存清零
     *
     * @param locationItemStockId  库位库存ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/storageLocation/updateStocksEmpty.do")
    public JsonResult updateStocksEmpty(BigInteger[] locationItemStockId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.updateStocksEmpty(locationItemStockId, ownerId));
    }

    /**
     * 跳转 仓库列表 页面
     *
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/inventoryList.do")
    public String inventoryList() {
        return "inventory/inventoryList";
    }

    /**
     * 加载 仓库列表 页面
     *
     * @param nowPage         当前页数
     * @param keywords        搜索关键字
     * @param warehouseStatus 该仓库是否被启用
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/loadInventoryList.do")
    public JsonResult loadInventoryList(
            Integer nowPage, String keywords, Integer warehouseStatus, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map =
                inventoryService.loadInventoryList(nowPage, rows, keywords, warehouseStatus, ownerId);
        //返回
        return new JsonResult(map);
    }

    /**
     * 修改 仓库列表 状态
     *
     * @param warehouseId 仓库ID
     * @param status      状态
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/updateUseDisable.do")
    public JsonResult updateUseDisable(BigInteger warehouseId, Integer status, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.updateUseDisable(warehouseId, status, ownerId));
    }

    /**
     * 保存新增修改仓库
     *
     * @param warehouseId       仓库ID
     * @param userWarehouseCode 仓库编码
     * @param warehouseName     仓库名称
     * @param consignorName     联系人
     * @param consignorTel      联系人电话
     * @param regionId          地区ID
     * @param userAddress       地址详细
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/saveAddUpdate.do")
    public JsonResult inventoryListSaveAddUpdate(
            BigInteger warehouseId, String userWarehouseCode, String warehouseName, String consignorName,
            String consignorTel, BigInteger regionId, String userAddress, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.inventoryListSaveAddUpdate(warehouseId, userWarehouseCode, warehouseName,
                consignorName, consignorTel, regionId, userAddress, ownerId)
        );
    }

    /**
     * 删除仓库
     * @param warehouseId 仓库ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/deleteInventoryList.do")
    public JsonResult deleteInventoryList(BigInteger warehouseId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.deleteInventoryList(warehouseId, ownerId));
    }


    /**
     * 跳转 库位管理 页面
     *
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/location.do")
    public String location() {
        return "inventory/location";
    }

    /**
     * 加载 库位管理列表 页面
     *
     * @param nowPage  当前页码
     * @param keywords 关键字
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/location/loadLocation.do")
    public JsonResult loadLocation(Integer nowPage, String keywords, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map = inventoryService.loadLocation(nowPage, keywords, ownerId, rows);

        //返回
        return new JsonResult(map);
    }

    /**
     * 获取商品内容 根据 库位ID
     *
     * @param locationId 库位ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/location/listItemByLocationId.do")
    public JsonResult getItemByLocationId(BigInteger locationId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //返回
        return new JsonResult(inventoryService.listItemByLocationId(locationId, ownerId));
    }

    /**
     * 获取商品 内容  根据 关键字
     *
     * @param nowPage  当前页码
     * @param keywords 关键字
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/location/listItemByKeywords.do")
    public JsonResult listItemByKeywords(Integer nowPage, String keywords, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        int row = 7;
        //返回
        return new JsonResult(inventoryService.listItemByKeywords(nowPage, keywords, ownerId, row));
    }

    /**
     * 获取商品 内容  根据 商品ID数组
     *
     * @param itemIds 商品ID数组
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/location/listItemByItemIds.do")
    public JsonResult listItemByItemIds(BigInteger[] itemIds, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //返回
        return new JsonResult(inventoryService.listItemByItemIds(itemIds, ownerId));
    }

    /**
     * 保存新增修改库位
     *
     * @param locationId            库位ID
     * @param userStocklocationCode 用户自定义库位编码
     * @param name                  库位名称
     * @param itemIds               商品ID数组
     * @param itemDelIds            商品ID数组 需要删除的
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/location/saveAddUpdate.do")
    public JsonResult locationSaveAddUpdate(
            BigInteger locationId, String userStocklocationCode, String name,
            BigInteger[] itemIds, BigInteger[] itemDelIds, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        return new JsonResult(inventoryService.locationSaveAddUpdate(locationId, userStocklocationCode,
                name, itemIds, itemDelIds, ownerId));
    }

    /**
     * 删除库位
     *
     * @param locationId 库位ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/location/removeLocationById.do")
    public JsonResult removeLocationById(BigInteger locationId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //返回
        return new JsonResult(inventoryService.removeLocationById(locationId, ownerId));
    }

    /**
     * 跳转 库存同步 页面
     *
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/inventorySync.do")
    public String inventorySync() {
        return "inventory/inventorySync";
    }

    /**
     * 加载 库位管理列表 页面
     *
     * @param nowPage      当前页码
     * @param keywords     关键字
     * @param autoSynchron 自动同步
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventorySync/loadInventorySync.do")
    public JsonResult loadInventorySync(Integer nowPage, String keywords, Integer autoSynchron, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map =
                inventoryService.loadInventorySync(nowPage, rows, keywords, autoSynchron, ownerId);
        //返回
        return new JsonResult(map);
    }

    /**
     * 加载 库存同步配置 根据商品ID数组
     * @param itemIds 商品ID数组
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventorySync/listInventorySyncByItemBind.do")
    public JsonResult listInventorySyncByItemBind(BigInteger[] itemIds, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        List<Map<String, Object>> list = inventoryService.listInventorySyncByItemBind(itemIds, ownerId);
        //返回
        return new JsonResult(list);
    }

    /**
     * 设置 库存同步配置
     * @param request
     * @param itemIds 商品ID数组
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventorySync/updateInventorySyncConfiguations.do")
    public JsonResult updateInventorySyncConfiguations(
            HttpServletRequest request, BigInteger[] itemIds, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //获取配置
        String configuations = request.getParameter("configuations");
        //设置 库存同步配置
        int row = inventoryService.updateInventorySyncConfiguations(configuations, itemIds, ownerId);
        //返回
        return new JsonResult(row);
    }

    /**
     * 跳转 云仓商品配对 页面
     *
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/commodityMatching.do")
    public String commodityMatching() {
        return "inventory/commodityMatching";
    }

    /**
     * 加载 仓库和库位 页面
     *
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/loadWarehouseAndStocklocation.do")
    public JsonResult loadWarehouseAndStocklocation(HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map = inventoryService.loadWarehouseAndStocklocation(ownerId);
        //返回
        return new JsonResult(map);
    }

    /**
     * 加载 云仓商品配对 页面
     *
     * @param nowPage         当前页数
     * @param keywords        搜索关键字
     * @param warehouseId 仓库ID
     * @param stocklocationId 库位ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/loadCommodityMatching.do")
    public JsonResult loadCommodityMatching(
            Integer nowPage, String keywords, BigInteger warehouseId,
            BigInteger stocklocationId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map =
                inventoryService.loadCommodityMatching(nowPage, rows, keywords, warehouseId, stocklocationId, ownerId);
        //返回
        return new JsonResult(map);
    }

    /**
     * 删除 商品 的云仓关联关系
     *
     * @param locationItemStockId 关联关系ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/deleteCommodityMatching.do")
    public JsonResult deleteCommodityMatching(BigInteger locationItemStockId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //删除，返回
        return new JsonResult(inventoryService.deleteCommodityMatching(locationItemStockId, ownerId));
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
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/loadWarehouseAndStocklocationOfAlert.do")
    public JsonResult loadWarehouseAndStocklocationOfAlert(
            Integer nowPageTop, String keyWordsTop, BigInteger warehouseIdTop, BigInteger stocklocationIdTop,
            Integer nowPageBottom, String keyWordsBottom, BigInteger warehouseIdBottom,
            BigInteger stocklocationIdBottom, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //加载
        Map<String, Object> map = inventoryService.loadWarehouseAndStocklocationOfAlert(
                nowPageTop, keyWordsTop, warehouseIdTop, stocklocationIdTop,
                nowPageBottom, keyWordsBottom, warehouseIdBottom, stocklocationIdBottom, rows, ownerId);
        //返回
        return new JsonResult(map);
    }

    /**
     * 提交 云仓商品关联关系
     * @param request
     * @param topId 顶部关系ID
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/inventoryList/bindCommit.do")
    public JsonResult bindCommit(HttpServletRequest request, BigInteger topId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //获取配置
        String bindMap = request.getParameter("bindMap");
        //提交 云仓商品关联关系
        int row = inventoryService.bindCommit(topId, bindMap, ownerId);
        //返回
        return new JsonResult(row);
    }
}
