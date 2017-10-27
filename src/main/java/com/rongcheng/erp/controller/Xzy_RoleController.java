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
import com.rongcheng.erp.service.roleManagementService.Xzy_RoleService;
/**
 * 角色管理页面的controller层
 * @author 薛宗艳
 *
 */
@Controller
@RequestMapping("/role")
public class Xzy_RoleController {
	@Resource
    private Xzy_RoleService service;
	/**
	 * 1.分页查询
	 * @param page :当前页
	 * @param request
	 * @return
	 * @author 薛宗艳
	 */
	@RequestMapping("/findPaging.do")
    @ResponseBody
    public XzyJsonResult rolePaging(Integer page,HttpServletRequest request){
    	HttpSession session = request.getSession();
    	UserInfo user=(UserInfo)session.getAttribute("user");  
    	BigInteger id = user.getId();
    	String accountNum = user.getAccountNum();
    	BigInteger ownerId = user.getOwnerId();
    	Integer userType = user.getUserType();
    	XzyJsonResult result = service.rolePaging(page,id,accountNum,ownerId,userType);
    	return result;
    }
	/**
	 * 2.单击新建或修改按钮时,查询当前账户的ownerId下的所有的: 权限
	 * @param ownerId :用户主账户id
	 * @return
	 * @author 薛宗艳
	 */
	@RequestMapping("/findAuth.do")
    @ResponseBody
    public XzyJsonResult rolePaging(BigInteger ownerId){
    	XzyJsonResult result = service.findAllAuthInfo(ownerId);
    	return result;
    }
	/**
	 * 3.新建角色
	 * @param roleName      :角色名
	 * @param roleProfile   :角色描述
	 * @param authIds       :权限id
	 * @param ownerId       :用户主账户id
	 * @param operatorId    :当前登录的账户的id
	 * @return
	 * @author 薛宗艳
	 */
	@RequestMapping("/createRole.do")
    @ResponseBody
    public XzyJsonResult addRole(String roleName,String roleProfile,String[] authIds,
    		BigInteger ownerId,BigInteger operatorId,Integer currentUserType){
    	XzyJsonResult result = service.addRole(roleName, roleProfile, authIds, ownerId, operatorId,currentUserType);
    	return result;
    }
	/**
	 * 4.修改角色
	 * @param roleId       :被修改的角色id
	 * @param roleName     :被修改的角色名
	 * @param roleProfile  :被修改的角色描述
	 * @param authIds      :角色修改后拥有的权限id
	 * @param ownerId      :用户主账户id
	 * @param operatorId   :当前登录的用户的id
	 * @param prevAuId     :角色修改前拥有的权限id字符串
	 * @return
	 * @author 薛宗艳
	 */
	@RequestMapping("/modifyRole.do")
    @ResponseBody
    public XzyJsonResult modifyRole(BigInteger roleId,String roleName,String roleProfile,BigInteger[] authIds,
    		BigInteger ownerId,BigInteger operatorId,String prevAuId,Integer currentUserType){
    	XzyJsonResult result = service.modifyRole(roleId,roleName,roleProfile,authIds,ownerId,
    			operatorId,prevAuId,currentUserType);    			
    	return result;
    }
	/**
	 * 5.删除角色
	 * @param roleId  :被删除的角色id
	 * @param ownerId :用户主账户id
	 * @return
	 * @author 薛宗艳
	 */
	@RequestMapping("/delRole.do")
    @ResponseBody
    public XzyJsonResult deleteRole(BigInteger roleId,BigInteger ownerId,Integer currentUserType){
    	XzyJsonResult result = service.delRole(roleId,ownerId,currentUserType);
    	return result;
    }
}