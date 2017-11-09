package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.entity.vo.ItemInfo;
import com.rongcheng.erp.service.wzy_itemInfoService.ItemInfoService;
import com.rongcheng.erp.utils.JsonResult;

@Controller
public class Wzy_ItemInfoController {

    private Integer row = 5;
    @Resource
    private ItemInfoService service;

    
    //1.跳转到商品管理页面
    @RequestMapping("/ItemInfoAdmin.do")
    public String toItemInfoHtml() {
        return "settings/commodity_management";
    }
    
    //2.查询该用户的所有商品
    @RequestMapping("/findUserByKeyWord.do")
    @ResponseBody
    public JsonResult findUserAllItemInfo(Integer nowPage, String keyWord, HttpSession session) {
        if(nowPage == null) {
            throw new RuntimeException("数据接收失败");
        }
        //获取用户id
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        //创建Map
        //Map<String,Object> map = ;
        //查询分页
        Map<String,Object> map= service.findUserByKeyWord(ownerId, row, nowPage, keyWord);
        return new JsonResult(map);
    }
    
    //3.创建商品
    @RequestMapping("/saveItemInfo.do")
    @ResponseBody
    public JsonResult saveItemInfo(ItemInfo info, HttpSession session) {
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        info.setOwnerId(ownerId);
        try {
        int success1 = service.saveItemCommonInfo(info);
        int success2 = service.saveItemEspInfo(info);
        if(success1 <=0 ||success2<=0) {
            return new JsonResult(1,null,"插入失败");
        }
        }catch(Exception e) {
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
        return new JsonResult(0,null,"删除成功");
    }
    
    //5.商品更新
    @RequestMapping("/updateItemInfo.do")
    @ResponseBody
    public JsonResult updateItemInfo(ItemInfo id, HttpSession session) {
        if(id==null) {
            throw new RuntimeException("未获取数据");
        }
        UserInfo user = (UserInfo)session.getAttribute("user");
        BigInteger ownerId = user.getOwnerId();
        int success = service.updateItemInfo(id,ownerId);
        if(success == 0) {
            return new JsonResult(0,null,"更新");
        }
        return new JsonResult(0,null,"更新成功");
    }

    //6.id查询
    //6.id查询
    @RequestMapping("/findItemInfoById.do")
    @ResponseBody
    public JsonResult findItemInfoById(BigInteger id) {
        if(id==null) {
            throw new RuntimeException("未获取数据");
        }
        List<ItemInfo> list = service.findItemInfoById(id);
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("list", list);
        return new JsonResult(map);
    }
}
