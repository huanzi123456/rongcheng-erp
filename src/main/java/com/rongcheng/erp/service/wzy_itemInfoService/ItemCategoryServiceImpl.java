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
    public ItemCategoryLink getItemCategoryLink(WzyItemInfo info, BigInteger ownerId) {
        icl = new ItemCategoryLink();
        icl.setAnthorization(info.getAuthorized());
        icl.setItemId(info.getItemId());
        icl.setNote(info.getNote());
        icl.setOperatorId(info.getOperatorId());
        icl.setOwnerId(ownerId);
        icl.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        icl.setGmtModified(new Timestamp(System.currentTimeMillis()));
        return icl;
    }
    
    //为商品添加分类
    public int saveItemCategoryLink(WzyItemInfo info, BigInteger ownerId) throws OrderOutNumberException{
        if(info == null || ownerId == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        BigInteger id = itemDao.findItemCommonInfoId(info);
        icl = getItemCategoryLink(info, ownerId);
        icl.setItemId(id);
        int success = 0;
        if(info.getInsertCategory().equals("k")){
            success = dao.saveItemCategory(icl);
        }else if(info.getInsertCategory() != ""){
            String[] insert = info.getInsertCategory().split(",");
            for(int i = 0;i<insert.length;i++) {
                icl.setCategoryId(new BigInteger(insert[i]));
            success = dao.saveItemCategory(icl);
            }
        }else {
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
    public int deleteItemCategoryLink(WzyItemInfo info, BigInteger ownerId) throws OrderOutNumberException{
        if(info == null || ownerId == null) {
            throw new OrderOutNumberException("数据获取失败");
        }
        BigInteger id = itemDao.findItemCommonInfoId(info);
        icl = getItemCategoryLink(info, ownerId);
        icl.setItemId(id);
        System.out.println(icl);
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
    
}
