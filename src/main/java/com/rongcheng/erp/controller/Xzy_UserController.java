package com.rongcheng.erp.controller;

import java.math.BigInteger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.userManagementService.Xzy_UserService;
/**
 * 用户管理页面的controller层
 * @author 薛宗艳
 *
 */
@Controller
@RequestMapping("/user")
public class Xzy_UserController {
    @Resource
    private Xzy_UserService service;    
    /**
     * 1.页面查询
     * @param page    :当前页
     * @return result
     */
    @RequestMapping("/userPaging.do")
    @ResponseBody
    public XzyJsonResult userPaging(Integer page,HttpServletRequest request){
    	HttpSession session = request.getSession();
    	UserInfo user=(UserInfo)session.getAttribute("user");  
    	BigInteger currentUserId = user.getId();
    	String currentUserAccountNum = user.getAccountNum();
    	BigInteger currentUserOwnerId = user.getOwnerId();
    	Integer currentUserType = user.getUserType();
    	XzyJsonResult result = service.userPaging(page,currentUserId,currentUserAccountNum,currentUserOwnerId,currentUserType);
    	return result;
    }      
    /**
     * 2.查询当前用户的ownerId下的所有的: 角色
     * @param ownerId:当前用户的ownerId
     * @return result
     */
    @RequestMapping("/findRole.do")
    @ResponseBody
    public XzyJsonResult findRoleInfo(BigInteger ownerId){
    	XzyJsonResult result = service.findRole(ownerId);
    	return result;
    }
    /**
     * 3.查询当前账户的ownerId下的所有的: 权限
     * @param ownerId:当前用户的ownerId
     * @return result
     */
    @RequestMapping("/findAuth.do")
    @ResponseBody
    public XzyJsonResult findAuthInfo(BigInteger ownerId){
    	XzyJsonResult result = service.findAuth(ownerId);
    	return result;
    }
    /**
     * 4.新建用户
     * @param accountNum   :账号
     * @param password     :密码
     * @param name         :用户名
     * @param telephone    :电话
     * @param roleIds      :角色id数组
     * @param authIds      :权限id数组
     * @param ownerId      :用户主账户id
     * @param operatorId   :当前登录的用户的id
     * @param userType     :当前登录的用户的类型
     * @return result
     */
    @RequestMapping("/addUser.do")
    @ResponseBody
    public XzyJsonResult addUser(String accountNum,String password,String name,String telephone,
    		String[] list,BigInteger[] authIds,BigInteger ownerId,
    		BigInteger operatorId,Integer userType){
        XzyJsonResult result = service.addUser(accountNum,password,name,telephone,list,
        		authIds,ownerId,operatorId,userType);   	
    	return result;
    }    
    /**
     * 5.修改用户
     * @param id               :被修改的账户的id
     * @param accountNum       :被修改的账户的账号
     * @param name             :被修改的账户的用户名
     * @param oldPassword      :被修改的账户的旧密码
     * @param newPassword      :被修改的账户的新密码
     * @param telephone        :被修改的账户的电话
     * @param prevAuthIds      :修改前的补充权限id数组
     * @param authIds          :修改后的补充权限id数组
     * @param prevList         :修改前 角色id+权限id数组
     * @param list             :修改后 角色id+权限id数组
     * @param ownerId          :用户主账户id
     * @param operatorId       :当前登录的用户的id
     * @param currUserType     :当前登录的用户的类型
     * @return
     */
    @RequestMapping("/rebuildUser.do")
    @ResponseBody
    public XzyJsonResult rebuildUser(BigInteger id,String accountNum,String name,String oldPassword,
    		String newPassword,String telephone,BigInteger[] prevAuthIds,BigInteger[] authIds,
    		String[] prevList,String[] list,BigInteger ownerId,
    		BigInteger operatorId,Integer currUserType){  	
    	XzyJsonResult result = service.modifyUser(id,accountNum,name,oldPassword,newPassword,telephone,
    			prevAuthIds,authIds,prevList,list,ownerId,operatorId,currUserType);
    	return result;
    }
    /**
	 * 6.其他按钮(启用,停用,删除)
	 * @param id           :被修改的账户的id
	 * @param ownerId      :用户主账户id
	 * @param operatorId   :当前登录的用户的id
	 * @param buttonName   :按钮名
	 * @return
	 */
    @RequestMapping("/otherButton.do")
    @ResponseBody
    public XzyJsonResult otherButton(BigInteger id,BigInteger ownerId,BigInteger operatorId,String buttonName,Integer currentUserType){
    	XzyJsonResult result = service.otherButton(id, ownerId, operatorId, buttonName,currentUserType);
    	return result;
    }
}