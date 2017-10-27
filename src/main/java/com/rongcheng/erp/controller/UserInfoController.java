package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.apache.catalina.Pipeline;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.UserInfo.UserInfoService;
import com.rongcheng.erp.utils.JsonResult;

@Controller
public class UserInfoController {
	private int rows=3;
	@Resource
	private UserInfoService service;
	
	@RequestMapping("/account_add_amend.do")
	public String account(){
		return "account_add_amend";
	}
	@RequestMapping("/account.do")
	public String account1(){
		return "account";
	}
	
	@RequestMapping("/add_account.do")
	@ResponseBody
	public JsonResult addUserInfo(String name,String accountNum,String password,String telephone){
	int n=service.addUserInfo(name, accountNum, password, telephone);
	return new JsonResult(n);
	}
	@RequestMapping("/modify_account.do")
	@ResponseBody
	public JsonResult modifyUserInfo(BigInteger id,String name,String accountNum,String password,String telephone){
	int n=service.modifyUserInfo(id, name, accountNum, password, telephone);
	return new JsonResult(n);
	}
	@RequestMapping("/del_account.do")
	@ResponseBody
	public JsonResult delUserInfo(BigInteger id){
	int n=service.delUserInfo(id);
	return new JsonResult(n);
	}
	//根据页码检索商品信息
	@RequestMapping("/loadList_account.do")
	@ResponseBody
	public JsonResult loadAccount(String page) {
		return loadAccounts(page);
	}
	
	//根据页码检索
	public JsonResult loadAccounts(String page) {
		int max_page = (int) Math.ceil(new Double(service.findUserInfoCount())/rows);
		if(max_page == 0){
			return new JsonResult();
		}
		List<UserInfo> list=service.findUserInfoByPage(new Integer(page)*rows-rows,rows);
		JsonResult jr = new JsonResult(list);
		jr.setMessage(max_page+"");
		return jr;
	}
	@RequestMapping("/load_modify_account.do")
	@ResponseBody
	public JsonResult findByUserInfoId(BigInteger id){
		UserInfo ui=service.findByUserInfoId(id);
		return new JsonResult(ui);
	}
	@RequestMapping("/modify_accountStatus.do")
	@ResponseBody
	public JsonResult modifyUserInfoStatus(BigInteger id,Integer accountStatus){
	int n=service.modifyUserInfoStatus(id, accountStatus);
	return new JsonResult(n);
	}
	@RequestMapping("/regist_account.do")
	@ResponseBody
	public JsonResult regist(String accountNum, String password, String email, String telephone){
		//生成六位验证码
		String charValue = "";
		 for (int i = 0; i < 6; i++) {
		 int a =(int)Math.random()*10;
		 charValue += String.valueOf(a);
		 }
		/*//将生成的六位验证码和传进来的手机号码存入缓存，时间90S
		 try{
		 Pipeline pipeline = cache.pipelined();
		 pipeline.set("CACHE" + to, charValue);
		 pipeline.expire("CACHE", 90);
		 pipeline.sync();
		 }
		 finally
		 {
		 if (cache != null)
		 {
		 cache.close();
		 }
		 }*/
		
		
		/*//如果赎金来的验证码和缓存中的验证码一致，则验证成功
		 if(verifyCode ==cacheVerifyCode ){
		  return true;
		  }else
		  return false;
		  }*/
		if(true){//用户输入验证码和生成的码比较为T
			service.regist(accountNum, password, email, telephone);
		}
	return new JsonResult();
	}
}
