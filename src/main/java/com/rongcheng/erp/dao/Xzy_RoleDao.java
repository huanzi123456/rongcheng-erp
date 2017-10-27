package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;
import com.rongcheng.erp.dto.XzyFindInfo;
import com.rongcheng.erp.entity.RoleManagement;
import com.rongcheng.erp.entity.UserRoleAuthority;
/**
 * 角色管理页面的dao层
 * @author 薛宗艳
 *
 */
public interface Xzy_RoleDao {
	/*
	 * 1.分页查询	
	 */
	//1.1  统计角色表中的记录数(同一个owner_id下的)
	Integer findCountByRole(BigInteger ownerId);
	//1.2  分页查询(查询角色id,角色名,创建时间,角色描述,权限名字符串,权限id字符串)
	List<XzyFindInfo> findPage(XzyFindInfo fi);
	
	/*
	 * 2.单击新建或修改按钮时,查询当前账户的ownerId下的所有的: 权限 
	 */
	List<XzyFindInfo> findAllAuthByOwnerId(BigInteger ownerId);	
	/*
	 * 3.新建角色
	 */
	//3.1 查询角色名是否重复
	String selectRoleNameByName(RoleManagement rm);
	//3.2 向角色表中插入数据 并返回角色id值
	void addRole(RoleManagement rm);
	//3.3 向关联表中插入数据
	void addRoleAuth(UserRoleAuthority ura);	
	/*
	 * 4.修改角色
	 */
	//
	//4.1 查询角色名是否重复
	String selectRoleNameById(RoleManagement rm);
	//4.2 更新角色表中的数据
	void updateRoleTable(RoleManagement rm);
	//4.3 更新关联表中的数据 
	//(1) 修改前后均有的权限ID保留
	//(2) 修改后未被包含的权限删除
	void deleteURA(UserRoleAuthority ura);
	//(3)查询在同一个ownerId下的拥有该角色的用户id
	List<BigInteger> seleUserId(UserRoleAuthority user);
	//(4) 修改后新添加的权限id插入
	void saveUserRoleAuth(UserRoleAuthority ura);	
	/*
	 * 5.删除角色
	 */
	//5.1 删除角色表中对应的角色
	void deleteRoleById(RoleManagement rm);
	//5.2 将关联表中对应的角色id删除
	void deleteRoleFromURA(UserRoleAuthority ura);
}