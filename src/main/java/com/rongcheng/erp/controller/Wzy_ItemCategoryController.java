package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.exception.OrderOutNumberException;
import com.rongcheng.erp.service.wzy_itemInfoService.ItemCategoryService;
import com.rongcheng.erp.utils.JsonResult;

@Controller
public class Wzy_ItemCategoryController {
    
    @Resource
    private ItemCategoryService service;
    
    //查询用户拥有的分类
    @RequestMapping("/findAllCategory.do")
    @ResponseBody
    public JsonResult findAllCategoryInfo(HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        Map<String,Object> map = service.findAllCategoryInfo(ownerId);
        return new JsonResult(map);
    }
    
    //添加用户的分类
    @RequestMapping("/saveUserCategory.do")
    @ResponseBody
    public JsonResult saveCategoryInfo(BigInteger id, String name, HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        int success = service.saveUserCategory(id, name, ownerId);
        if(success <= 0) {
            new JsonResult(1,null,"添加失败");
        }
        return new JsonResult(0,null,"添加成功");
    }
    
    //修改用户分类
    @RequestMapping("/updateUserCategory.do")
    @ResponseBody
    public JsonResult updateCategoryInfo(BigInteger id, String name, HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        int success = service.updateUserCategory(id, name, ownerId);
        if(success <= 0) {
            return new JsonResult(1,null,"修改失败");
        }
        return new JsonResult(0,null,"修改成功");
    }
    
    //删除用户的基层分类
    @RequestMapping("/deleteUserCategory.do")
    @ResponseBody
    public JsonResult deleteCategoryInfo(BigInteger id, HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        int success = service.deleteUserCategory(id, ownerId);
        if(success == -1) {
            return new JsonResult(1,null,"该分类不是最低层分类，不能删除");
        }else if(success == 0) {
            return new JsonResult(1,null,"删除失败");
        }else {
            return new JsonResult(0,null,"删除成功");
        }
    }
    
    //异常处理
    @ExceptionHandler(OrderOutNumberException.class)
    @ResponseBody
    public JsonResult orderOutNumberException(OrderOutNumberException e){
        e.printStackTrace();
        return new JsonResult(5,e);
    }
}
