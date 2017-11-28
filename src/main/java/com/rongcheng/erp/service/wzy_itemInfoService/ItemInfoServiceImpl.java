package com.rongcheng.erp.service.wzy_itemInfoService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.Wzy_ItemInfoDAO;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.ItemEspInfo;
import com.rongcheng.erp.dto.WzyItemInfo;
import com.rongcheng.erp.exception.OrderOutNumberException;

@Service("itemInfoService")
public class ItemInfoServiceImpl implements ItemInfoService {
    @Resource
    private Wzy_ItemInfoDAO dao;
    @Resource
    private ItemCategoryService service;
    
    private ItemEspInfo iei;
    private ItemCommonInfo ici;
    private List<WzyItemInfo> list;
    private Integer maxPage;
    private Integer maxData;
    
    //分页查询
    @Override
    public Map<String,Object> findUserByKeyWord(
            BigInteger ownerId, Integer row, Integer nowPage, String keyWord, BigInteger categoryId) throws OrderOutNumberException{

        //账号判断
        if(ownerId == null) {
            throw new OrderOutNumberException("您的账号已超时");
        }
        //分页处理
        Integer startPage = (nowPage-1)*row;
        //关键字判断
        if(keyWord=="") {
            //执行全部查询查询
            list = dao.findUserAllItemInfo(ownerId, startPage, row, categoryId);
            //查询全部信息的最大数据并得到最大页数
            maxData = dao.findUserItemCount(ownerId, categoryId);
            maxPage = maxData/row;
            if(maxData%row !=0) {
                maxPage++;
            }
        }else {
            //模糊查询
            list = dao.findItemInfoByLike(ownerId, keyWord, startPage, categoryId, row);
            //模糊查询的最大数据并得到最大页数
            maxData = dao.findUserItemCountByLike(ownerId, keyWord, categoryId);
            maxPage = maxData/row;
            if(maxData%row !=0) {
                maxPage++;
            }
        }
        
        //建立map
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startPage", startPage);
        map.put("list", list);
        map.put("maxPage", maxPage);
        return map;
    }
    
    //添加商品表
    @Override
    public BigInteger saveItemInfo(WzyItemInfo Item) throws OrderOutNumberException{
        if(Item == null) {
            throw new OrderOutNumberException("未得到数据");
        }
        int num = dao.findItemInfoByErpItemNum(Item);
        if(num >0) {
            return new BigInteger("10");
        }
        ici = getItemCommonInfo(Item);
        dao.saveItemCommonInfo(ici);
        iei = getItemEspInfo(Item);
        iei.setItemId(ici.getId());
        Item.setItemId(ici.getId());
        int success =  dao.saveItemEspInfo(iei);
        service.saveItemCategoryLink(Item);
        if(success <=0) {
            throw new OrderOutNumberException("插入失败，可能是您输入的某个数据过长");
        }
        return ici.getId();
    }
    
    //将数据转换成两组
    public ItemEspInfo getItemEspInfo(WzyItemInfo info) {
        iei = new ItemEspInfo();
        iei.setWarehouseId(info.getWarehouseId());
        iei.setName(info.getName());
        iei.setLength(info.getLength());
        iei.setWeight(info.getWeight());
        iei.setWidth(info.getWidth());
        iei.setHeight(info.getHeight());
        iei.setUnit(info.getUnit());
        iei.setBatchCode(info.getBatchCode());
        iei.setExpireDate(info.getExpireDate());
        iei.setStyleCode(info.getStyleCode());
        iei.setNote(info.getNote());
        iei.setImage2(info.getImage2());
        iei.setImage3(info.getImage3());
        iei.setImage4(info.getImage4());
        iei.setImage5(info.getImage5());
        iei.setImage6(info.getImage6());
        iei.setAuthorized(info.getAuthorized());
        iei.setOperatorId(info.getOperatorId());
        iei.setReserved2(info.getReserved2());
        iei.setReserved1(info.getReserved3());
        iei.setSpec(info.getSpec());
        iei.setPlatformId(info.getPlatformId());
        iei.setPlatformItemSku(info.getPlatformItemSku());
        iei.setOwnerId(info.getOwnerId());
        iei.setItemId(info.getId());
        iei.setGmtModified(new Timestamp(System.currentTimeMillis()));
        iei.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        return iei;
    }
    public ItemCommonInfo getItemCommonInfo(WzyItemInfo info) {
        ici= new ItemCommonInfo();
        ici.setId(info.getId());
        ici.setErpItemNum(info.getErpItemNum());
        ici.setCategory(info.getCategory());
        ici.setBrand(info.getBrand());
        ici.setName(info.getName());
        ici.setShortName(info.getShortName());
        ici.setSpec(info.getColor()+" * "+info.getSize());
        ici.setColor(info.getColor());
        ici.setSize(info.getSize());
        ici.setNormalPrice(info.getNormalPrice());
        ici.setCostPrice(info.getCostPrice());
        ici.setBarCode(info.getBarCode());
        ici.setPackageCondition(info.getPackageCondition());
        ici.setNote(info.getNote());
        ici.setGift(info.getGift());
        ici.setPresell(info.getPresell());
        ici.setCommissionSell(info.getCommissionSell());
        ici.setOwnerId(info.getOwnerId());
        ici.setImage1(info.getImage1());
        ici.setShopId(info.getShopId());
        ici.setReserved1(info.getReserved1());
        ici.setPlatformId(info.getPlatformId());
        ici.setPlatformItemSku(info.getPlatformItemSku());
        ici.setPromotionPrice(info.getPromotionPrice());
        ici.setBarCodeImage(info.getBarCodeImage());
        ici.setOperatorId(info.getOperatorId());
        ici.setQrCodeImage(info.getQrCodeImage());
        ici.setBarCodeImage(info.getBarCodeImage());
        ici.setSeries(info.getSeries());
        ici.setItemSecretKey(info.getItemSecretKey());
        ici.setUserItemCode(info.getUserItemCode());
        ici.setUserShopCode(info.getUserShopCode());
        ici.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        ici.setGmtModified(new Timestamp(System.currentTimeMillis()));
        return ici;
    }
    
    //商品更新
    @Override
    public int updateItemInfo(WzyItemInfo info) throws OrderOutNumberException{
        if(info == null) {
            throw new OrderOutNumberException("未找到修改的数据");
        }
        try {
            ici=getItemCommonInfo(info);
            iei=getItemEspInfo(info);
            dao.updateItemCommonInfo(ici);
            dao.updateItemEspInfo(iei);
            return 1;
        }catch(OrderOutNumberException e) {
            throw new OrderOutNumberException("更新失败，可能是您输入的某个数据过长");
        }
    }
    
    //商品删除
    @Override
    public int removeItemInfo(String id) throws OrderOutNumberException{
        if(id==null) {
            throw new OrderOutNumberException("数据获取异常");
        }
        String[] ids = id.split(",");
        int index = 0;
        try {
            for(int i = 0; i < ids.length ; i++) {
                BigInteger itemId = new BigInteger(ids[i]);
                dao.removeItemCommonInfo(itemId);
                dao.removeItemEspInfo(itemId);
                index++;
            }
        }catch(OrderOutNumberException e) {
            throw new OrderOutNumberException("删除未全部完成");
        }
        return index;
    }

    //商品id查询
    @Override
    public List<WzyItemInfo> findItemInfoById(BigInteger id) throws OrderOutNumberException{
        if(id == null) {
            throw new OrderOutNumberException("未找到修改的数据");
        }
        return dao.findItemInfoById(id);
    }

}
