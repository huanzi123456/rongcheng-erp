package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rongcheng.erp.entity.ShopInfo;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.ShopInfo.ShopInfoService;
import com.rongcheng.erp.utils.JsonResult;

@Controller
public class ShopInfoController {
	private int rows=20;
	@Resource
	private ShopInfoService service;
	
	@RequestMapping("/accredit.do")
	public String toShopInfo(){
		return "accredit";
	}
	
	@RequestMapping("/add_storeInformation.do")
	@ResponseBody
	public JsonResult addShopInfo(HttpSession session,String name,String sellerAccount,String contactName,String contactTel,String userAddress){
		BigInteger ownerId=((UserInfo)(session.getAttribute("user"))).getOwnerId();
		Timestamp tamp = new Timestamp(System.currentTimeMillis());
	int n=service.addShopInfo(ownerId,tamp,name, sellerAccount, contactName, contactTel, userAddress);
	return new JsonResult(n);
	}
	
	//根据页码检索商品信息
	@RequestMapping("/loadList_shopInfo.do")
	@ResponseBody
	public JsonResult loadShopInfo(String page) {
		return loadShopInfos(page);
	}
	
	//根据页码检索
	public JsonResult loadShopInfos(String page) {
		int max_page = (int) Math.ceil(new Double(service.findShopInfoCount())/rows);
		if(max_page == 0){
			return new JsonResult();
		}
		List<ShopInfo> ShopInfo=service.findShopInfoByPage(new Integer(page)*rows-rows,rows);
		JsonResult jr = new JsonResult(ShopInfo);
		jr.setMessage(max_page+"");
		return jr;
	}
	/*@RequestMapping("/storeInformation.do")
	public String toShopInfo(HttpServletRequest request) {
		List<ShopInfo> list=service.findAll();
		request.setAttribute("shopInfo", list);
		return "storeInformation";
		}*/
	
	@RequestMapping("/load_modify_shopInfo.do")
	@ResponseBody
	public JsonResult findByShopId(BigInteger id){
	ShopInfo si=service.findByShopId(id);
		return new JsonResult(si);
	}
	@RequestMapping("/modify_shopInfo.do")
	@ResponseBody
	public JsonResult modifyShopInfo(BigInteger id,String name,String sellerAccount,String contactName,String contactTel,String userAddress){
	int n=service.modifyShopInfo(id, name, sellerAccount, contactName, contactTel, userAddress);
	return new JsonResult(n);
	}
	@RequestMapping("/modify_shopInfoStatus.do")
	@ResponseBody
	public JsonResult modifyUserInfoStatus(BigInteger id,Boolean shopStatus){
	int n=service.modifyShopInfoStatus(id, shopStatus);
	return new JsonResult(n);
	}
}
