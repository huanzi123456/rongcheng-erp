package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.dto.WzyItemInfo;
import com.rongcheng.erp.entity.ItemCategoryLink;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.exception.OrderOutNumberException;
import com.rongcheng.erp.service.wzy_itemInfoService.ItemCategoryService;
import com.rongcheng.erp.service.wzy_itemInfoService.ItemInfoService;
import com.rongcheng.erp.utils.JsonResult;

@Controller
public class Wzy_ItemInfoController {

    private Integer row = 6;
    @Resource
    private ItemInfoService service;
    @Resource 
    private ItemCategoryService category;
    
    //1.跳转到商品管理页面
    @RequestMapping("/ItemInfoAdmin.do")
    public String toItemInfoHtml() {
        return "settings/commodity_management";
    }
    
    //2.查询该用户的所有商品
    @RequestMapping("/findUserByKeyWord.do")
    @ResponseBody
    public JsonResult findUserAllItemInfo(Integer nowPage, String keyWord, BigInteger categotyId, HttpSession session) {
        if(nowPage == null) {
            throw new RuntimeException("数据接收失败");
        }
        //获取用户id
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        //查询分页
        Map<String,Object> map= service.findUserByKeyWord(ownerId, row, nowPage, keyWord, categotyId);
        return new JsonResult(map);
    }
    
    //3.创建商品
    @RequestMapping("/saveItemInfo.do")
    @ResponseBody
    public JsonResult saveItemInfo(WzyItemInfo info, HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        info.setOwnerId(ownerId);
        BigInteger success1 = service.saveItemInfo(info);
        Integer success = Integer.parseInt(success1.toString());
        if(success == 10) {
            return new JsonResult(1,null,"商品编号不能重复");
        }
        return new JsonResult(0,null,"插入成功");
    }
    
    //4.删除商品
    @RequestMapping("/doRemoveItemInfo.do")
    @ResponseBody
    public JsonResult removeItemInfo(String id) {
        if(id==null) {
            throw new RuntimeException("未获取数据");
        }
        service.removeItemInfo(id);
        category.removeAllItemCategoryLink(id);
        return new JsonResult(0,null,"删除成功");
    }
    
    //5.商品更新
    @RequestMapping("/updateItemInfo.do")
    @ResponseBody
    public JsonResult updateItemInfo(WzyItemInfo id, HttpSession session) {
        if(id==null) {
            throw new RuntimeException("未获取数据");
        }
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        id.setOwnerId(ownerId);
        id.setItemId(id.getId());
        int success = service.updateItemInfo(id);
        int success1 = category.saveItemCategoryLink(id);
        int success2 = category.deleteItemCategoryLink(id);
        if(success <= 0 && success1 <= 0 && success2 <= 0) {
            return new JsonResult(0,null,"更新失败");
        }
        return new JsonResult(0,null,"更新成功");
    }

    //6.id查询
    @RequestMapping("/findItemInfoById.do")
    @ResponseBody
    public JsonResult findItemInfoById(BigInteger id) {
        if(id==null) {
            throw new RuntimeException("未获取数据");
        }
        List<ItemCategoryLink> list1 = category.findAllItemCategoryLink(id);
        List<WzyItemInfo> list = service.findItemInfoById(id);
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("list", list);
        map.put("list1", list1);
        return new JsonResult(map);
    }

    //7.更改分类（批量的更改商品的分类）
    @RequestMapping("/updateMoreCategory.do")
    @ResponseBody
    public JsonResult updateMoreCategory(String id, String insertCategory, HttpSession session) {
        if(id==null) {
            throw new RuntimeException("未获取数据");
        }
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        int success = category.updateMoreCategory(id, insertCategory, ownerId);
        if(success <= 0) {
            return new JsonResult(0,null,"更新失败");
        }
        return new JsonResult(0,null,"更新成功");
    }
    
    //异常处理
    @ExceptionHandler(OrderOutNumberException.class)
    @ResponseBody
    public JsonResult orderOutNumberException(OrderOutNumberException e){
        e.printStackTrace();
        return new JsonResult(5,e);
    }
}
