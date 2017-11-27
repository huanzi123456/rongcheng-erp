package com.rongcheng.erp.service.wzy_itemInfoService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.Wzy_ItemCategoryDao;
import com.rongcheng.erp.dao.Wzy_ItemInfoDAO;
import com.rongcheng.erp.dto.WzyItemInfo;
import com.rongcheng.erp.entity.CategoryInfo;
import com.rongcheng.erp.entity.ItemCategoryLink;
import com.rongcheng.erp.exception.OrderOutNumberException;

@Service("itemCategoryService")
public class ItemCategoryServiceImpl implements ItemCategoryService{

    @Resource
    private Wzy_ItemCategoryDao dao;
    @Resource
    private Wzy_ItemInfoDAO itemDao;
    
    private ItemCategoryLink icl;
    private CategoryInfo ci;
    
    //查找用户的商品分类
    public Map<String,Object> findAllCategoryInfo(BigInteger ownerId){
        if(ownerId == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        List<CategoryInfo> list = dao.findAllCategoryByOwnerId(ownerId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list", list);
        return map;
    }
    
    //查询商品的分类
    public List<ItemCategoryLink> findAllItemCategoryLink(BigInteger itemId){
        if(itemId == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        List<ItemCategoryLink> list = dao.findAllCategoryByItemId(itemId);
        return list;
    }
    
    //对于商品信息的处理
    public ItemCategoryLink getItemCategoryLink(WzyItemInfo info) {
        icl = new ItemCategoryLink();
        icl.setAnthorization(info.getAuthorized());
        icl.setItemId(info.getItemId());
        icl.setNote(info.getNote());
        icl.setOperatorId(info.getOperatorId());
        icl.setOwnerId(info.getOwnerId());
        icl.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        icl.setGmtModified(new Timestamp(System.currentTimeMillis()));
        return icl;
    }
    
    //为商品添加分类
    public int saveItemCategoryLink(WzyItemInfo info) throws OrderOutNumberException{
        if(info == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        icl = getItemCategoryLink(info);
        int success = 0;
        if(!info.getInsertCategory().equals("k")){
            String[] insert = info.getInsertCategory().split(",");
            for(int i = 0;i<insert.length;i++) {
                icl.setCategoryId(new BigInteger(insert[i]));
            success = dao.saveItemCategory(icl);
            }
        }else{
            return 1;
        }
        return success;
    }
    
    //删除商品分类（删除商品时）
    public int removeAllItemCategoryLink(String id) throws OrderOutNumberException{
        if(id == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        BigInteger itemId = new BigInteger(id); 
        int success = dao.deleteAllItemCategory(itemId);
        return success;
    }
    
    //删除商品分类(修改商品时)
    public int deleteItemCategoryLink(WzyItemInfo info) throws OrderOutNumberException{
        if(info == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        icl = getItemCategoryLink(info);
        icl.setItemId(info.getId());
        int success = 0;
        if(info.getDeleteCategory().equals("k")){
            success = dao.deleteItemCategory(icl);
        }else if(info.getDeleteCategory() != ""){
            String[] delete = info.getDeleteCategory().split(",");
            for(int i = 0;i<delete.length;i++) {
                icl.setCategoryId(new BigInteger(delete[i]));
            success = dao.deleteItemCategory(icl);
            }
        }else {
            return 1;
        }
        return success;
    }

    //添加用户的商品分类
    public int saveUserCategory(BigInteger id, String name, BigInteger ownerId) throws OrderOutNumberException{
        if(id == null || ownerId == null || name == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        ci = new CategoryInfo();
        ci.setCategoryName(name);
        ci.setOwnerId(ownerId);
        ci.setParentId(id);
        ci.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        ci.setGmtModified(new Timestamp(System.currentTimeMillis()));
        int success = dao.saveUserCategory(ci);
        return success;
    }
    
    //修改用户的商品分类
    public int updateUserCategory(BigInteger id, String name, BigInteger ownerId) throws OrderOutNumberException{
        if(id == null || ownerId == null || name == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        ci = new CategoryInfo();
        ci.setCategoryName(name);
        ci.setOwnerId(ownerId);
        ci.setId(id);
        ci.setGmtModified(new Timestamp(System.currentTimeMillis()));
        int success = dao.updateUserCategory(ci);
        return success;
    }
    
    //删除用户的商品分类
    public int deleteUserCategory(BigInteger id, BigInteger ownerId) throws OrderOutNumberException{
        if(id == null || ownerId == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        int index = dao.findCountParendIdById(id, ownerId);
        if(index > 0) {
            return -1;
        }else {
            int success = dao.deleteUserCategory(id, ownerId);
            return success;
        }
    }
    
    //更改分类的点击更新操作
    public int updateMoreCategory(String id, String insertCategory, BigInteger ownerId) throws OrderOutNumberException {
        if(id == null ||insertCategory == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        String[] ids = id.split(",");
        icl=new ItemCategoryLink();
        icl.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        icl.setGmtModified(new Timestamp(System.currentTimeMillis()));
        icl.setOwnerId(ownerId);
        for(int i=0; i<ids.length; i++) {
            icl.setItemId(new BigInteger(ids[i]));
            dao.deleteAllItemCategory(new BigInteger(ids[i]));
            if(!insertCategory.equals("k")){
                String[] insert = insertCategory.split(",");
                for(int j = 0;j<insert.length;j++) {
                    icl.setCategoryId(new BigInteger(insert[j]));
                    dao.saveItemCategory(icl);
                }
            }else{
                return 1;
            }
        }
        return 0;
    }

}
