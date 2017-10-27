package com.rongcheng.erp.service.userManagementService;

import java.math.BigInteger;
import com.rongcheng.erp.dto.XzyJsonResult;
/**
 * 用户管理页面的service接口
 * @author 薛宗艳
 *
 */
public interface Xzy_UserService {
	/**
	 * 1.分页查询
	 * @param page                    :当前页
	 * @param currentUserId           :当前登录的用户的id
	 * @param currentUserAccountNum   :当前登录的用户的账号
	 * @param currentUserOwnerId      :当前登录的用户的主账户id
	 * @param currentUserType         :当前登录的用户的账号类型
	 * @return
	 */
	XzyJsonResult userPaging(Integer page,BigInteger currentUserId,String currentUserAccountNum,BigInteger currentUserOwnerId,Integer currentUserType);
	/**
	 * 2.查询当前用户的ownerId下的所有的: 角色
	 * @param ownerId :用户主账户id
	 * @return
	 */
	XzyJsonResult findRole(BigInteger ownerId);
	/**
	 * 3.查询当前账户的ownerId下的所有的: 权限
	 * @param ownerId :用户主账户id
	 * @return
	 */
	XzyJsonResult findAuth(BigInteger ownerId);
	/**
	 * 4.新建用户
	 * @param accountNum   :账号
	 * @param password     :密码
	 * @param name         :用户名
	 * @param telephone    :电话
	 * @param roleIds      :角色id的数组
	 * @param authIds      :权限id的数组
	 * @param ownerId      :用户主账户id
	 * @param operatorId   :当前登录的用户的id
	 * @param userType     :当前登录的用户的类型
	 * @return
	 */
	XzyJsonResult addUser(String accountNum,String password,String name,String telephone,String[] list,
			BigInteger[] authIds,BigInteger ownerId,BigInteger operatorId,Integer userType);
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
	XzyJsonResult modifyUser(BigInteger id,String accountNum,String name,String oldPassword,
    		String newPassword,String telephone,BigInteger[] prevAuthIds,BigInteger[] authIds,
    		String[] prevList,String[] list,BigInteger ownerId,
    		BigInteger operatorId,Integer currUserType);
	/**
	 * 6.其他按钮(启用,停用,删除)
	 * @param id           :被修改的账户的id
	 * @param ownerId      :用户主账户id
	 * @param operatorId   :当前登录的用户的id
	 * @param buttonName   :按钮名
	 * @return
	 */
	XzyJsonResult otherButton(BigInteger id,BigInteger ownerId,
    		BigInteger operatorId,String buttonName,Integer currentUserType);
}