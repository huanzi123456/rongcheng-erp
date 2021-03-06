package com.rongcheng.erp.service.wzy_orderReleaseService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.Wzy_InventoryOrderReleaseDAO;
import com.rongcheng.erp.dao.Wzy_InvertoryAllAddressDao;
import com.rongcheng.erp.dto.WzyAddressCarrierAllocation;
import com.rongcheng.erp.dto.WzyWarehouseRegionShop;
import com.rongcheng.erp.entity.WarehouseRegionShop;
import com.rongcheng.erp.exception.OrderOutNumberException;


@Service("orderReleaseService")
public class OrderReleaseServiceImpl implements OrderReleaseService {
    
    @Resource
    private Wzy_InventoryOrderReleaseDAO dao;
    @Resource
    private Wzy_InvertoryAllAddressDao addressDao;
    private Integer maxPage;
    private Integer maxData;
    private Integer startPage;
    
    //查询仓库信息
    @Override
    public Map<String,Object> findAllWarehouseByOwnerId(BigInteger ownerId, Integer nowPage, Integer row) throws RuntimeException{
        if(ownerId == null) {
            throw new RuntimeException("数据获取失败");
        }
        //获取分页信息
        maxData = dao.findWarehouseRegionShopCount(ownerId);
        maxPage = maxData/row;
        if(maxData%row !=0) {
            maxPage++;
        }
        startPage = (nowPage-1)*row;
        //分页获取信息
        List<WzyWarehouseRegionShop> list = dao.findWarehouseRegionShopByOwnerId(ownerId, startPage, row);
        
        //封装进Map中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        map.put("page", maxPage);
        map.put("startPage", startPage);
        return map;
    }

    //查询全国城市编码并查询仓库的覆盖范围
    @Override
    public Map<String,Object> findAddressCarrierAllocation(BigInteger warehouseId, BigInteger stocklocationId, BigInteger ownerId){
        List<WzyAddressCarrierAllocation> list = addressDao.findAddressCarrierAllocation();
        List<WzyAddressCarrierAllocation> list1 = addressDao.findAddressCarrierAllocationSecond();
        List<Integer> list2 = addressDao.findWarehouseCover(warehouseId, stocklocationId, ownerId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        map.put("list1", list1);
        map.put("list2", list2);
        return map;
    }

    //对仓库的覆盖范围进行更新
    @Override
    public int updateWarehouseCoverArea(
            String insertArea, String deleteArea, BigInteger warehouseId, BigInteger stocklocationId, BigInteger ownerId) throws OrderOutNumberException{
        if((insertArea == "" || insertArea == null) && (deleteArea == "" || deleteArea == null)) {
            throw new OrderOutNumberException("您更改的数据没有变化，因此不进行数据更新");
        }
        //创建对象
        WarehouseRegionShop shop = new WarehouseRegionShop();
        shop.setWarehouseId(warehouseId);
        shop.setStocklocationId(stocklocationId);
        shop.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        shop.setOwnerId(ownerId);
        shop.setGmtModified(new Timestamp(System.currentTimeMillis()));
        //删除夺取的覆盖范围
        if(deleteArea != "" && deleteArea != null) {
            String[] delete = deleteArea.split(",");
            for(int i = 0;i<delete.length;i++) {
                shop.setCoverRegionId(new BigInteger(delete[i]));
                addressDao.deleteCoverArea(shop);
            }
        }
        
        //插入新增的覆盖范围
        if(insertArea != "" && insertArea != null) {
            String[] insert = insertArea.split(",");
            for(int i = 0;i<insert.length;i++) {
                shop.setCoverRegionId(new BigInteger(insert[i]));
                addressDao.saveCoverArea(shop);
            }
        }
        return 0;
    }


}
