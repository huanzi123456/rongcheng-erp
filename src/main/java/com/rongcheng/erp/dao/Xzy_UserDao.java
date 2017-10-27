package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.rongcheng.erp.dto.XzyFindInfo;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.entity.UserRoleAuthority;
/**
 * 用户管理页面的dao层
 * @author 薛宗艳
 *
 */
public interface Xzy_UserDao {
	/*
	 * 1.分页查询
	 */
	//1.1 统计用户表中同一个owner_id下的用户数量
	Integer countUserIdByOwnerId(BigInteger ownerId);
	//1.2 分页查询
	List<XzyFindInfo> userPaging(XzyFindInfo up);
	//1.3 查询同一用户下的不同角色所拥有的权限id
	List<String> seleAuthId(UserRoleAuthority us);
	//1.4 根据权限id查询权限名
	List<XzyFindInfo> selectReplenishAuId(UserRoleAuthority auth);			
	/*
	 * 2.查询当前用户的ownerId下的所有的: 角色
	 */
	List<XzyFindInfo> findRoleAndAuth(BigInteger ownerId);	
	/*
	 * 3.查询当前账户的ownerId下的所有的: 权限
	 */
	List<XzyFindInfo> findAuth(BigInteger ownerId);		
	/*
	 * 4.新建用户
	 */
	//4.1 查询新建的用户的账号,避免重复 
	String findByAccountNum(String accountNum);
	//4.2 向用户表中插入数据
	void saveUserInfo(UserInfo user);
	//4.3 向关联表中插入数据
	void saveUserRoleAuthInfo(UserRoleAuthority ura);
	/*
	 * 5.修改用户
	 */
	//5.1 在用户表查询该用户的密码
	String selectPwdById(UserInfo ui);
	//5.2 在用户表查询账号
	String seleAccountNumById(UserInfo u);
	//5.3 修改用户表中的信息
	void updateUserInfo(UserInfo ui);
	//5.4 修改关联表的数据
	void insertURAs(UserRoleAuthority ura);
	//5.5 删除关联表中修改用户后未被包含的权限记录
	void deleteInfo(UserRoleAuthority ur);
	/*
	 * 6.其他按钮 
	 */
	//6.1 启用按钮
	void starts(@Param("operatorId")BigInteger operatorId,@Param("id")BigInteger id,@Param("ownerId")BigInteger ownerId);
	//6.2 停用按钮
	void stops(@Param("operatorId")BigInteger operatorId,@Param("id")BigInteger id,@Param("ownerId")BigInteger ownerId);
	//6.3 删除按钮
	  //(1)删除用户表的记录
	void deleUser(@Param("id")BigInteger id,@Param("ownerId")BigInteger ownerId);
	  //(2)删除关联表的信息
	void deleURA(@Param("id")BigInteger id,@Param("ownerId")BigInteger ownerId);	
}