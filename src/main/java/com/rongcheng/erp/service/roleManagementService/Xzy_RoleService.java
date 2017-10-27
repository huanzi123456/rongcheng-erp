package com.rongcheng.erp.service.roleManagementService;

import java.math.BigInteger;
import com.rongcheng.erp.dto.XzyJsonResult;
/**
 * 角色管理页面的service层接口
 * @author 薛宗艳
 *
 */
public interface Xzy_RoleService {
	/**
	 * 1.分页查询
	 * @param page        :当前页
	 * @param id          :当前登录的账户的id
	 * @param accountNum  :当前登录的账户的账号
	 * @param ownerId     :当前登录的账户的主账户id
	 * @param userType    :当前登录的账户的账号类型
	 * @return
	 * @author 薛宗艳
	 */
	XzyJsonResult rolePaging(Integer page,BigInteger id,String accountNum,BigInteger ownerId,Integer userType);
	
	/**
	 * 2.查询当前账户的ownerId下的所有的: 权限
	 * @param ownerId :用户主账户id
	 * @return
	 * @author 薛宗艳 
	 */
	XzyJsonResult findAllAuthInfo(BigInteger ownerId);
	
	/**
	 * 3.新建角色
	 * @param roleName    :角色名
	 * @param roleProfile :角色名
	 * @param authIds     :权限id的数组
	 * @return
	 * @author 薛宗艳
	 */
	XzyJsonResult addRole(String roleName,String roleProfile,String[] authIds,
			BigInteger ownerId,BigInteger operatorId,Integer currentUserType);
	
	/**
	 * 4.修改角色
	 * @param roleId       :被修改的角色id
	 * @param roleName     :被修改的角色名
	 * @param roleProfile  :被修改的角色描述
	 * @param authIds      :角色修改后拥有的权限id
	 * @param ownerId      :用户主账户id
	 * @param operatorId   :当前登录的用户的id
	 * @param prevAuId     :该角色修改前拥有的权限id
	 * @return
	 * @author 薛宗艳
	 */
	XzyJsonResult modifyRole(BigInteger roleId,String roleName,String roleProfile,BigInteger[] authIds,
			BigInteger ownerId,BigInteger operatorId,String prevAuId,Integer currentUserType);
	
	/**
	 * 5.删除角色
	 * @param roleId  :被删除的角色id
	 * @param ownerId :用户主账户id
	 * @return
	 * @author 薛宗艳
	 */
	XzyJsonResult delRole(BigInteger roleId,BigInteger ownerId,Integer currentUserType);
	
}