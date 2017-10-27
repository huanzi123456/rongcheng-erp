package com.rongcheng.erp.service.roleManagementService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rongcheng.erp.dao.Xzy_RoleDao;
import com.rongcheng.erp.dto.XzyFindInfo;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.RoleManagement;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.entity.UserRoleAuthority;
/**
 * 角色管理页面的service层实现类
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_RoleServiceImpl implements Xzy_RoleService{
	@Resource 
	private Xzy_RoleDao dao;
	/**
	 * 1.分页查询
	 */
//	@Value("#{jdbc[pageSize]}")
//	String pageSize;//每页的记录数	
	@Value("#{config['rows']}")
	private String pageSize;//每页的记录数	
	public XzyJsonResult rolePaging(Integer page,BigInteger id,String accountNum,BigInteger ownerId,Integer userType) {
		XzyJsonResult result = new XzyJsonResult();
		/*
		 * 只有是主账号(即userType==1)时,才可以进行角色管理的操作
		 */
		if(userType.equals(1)){	
			page=(page-1)*(new Integer (pageSize));
			//将当前登录的账户的信息添加到userInfo中
			UserInfo userInfo = new UserInfo();
			userInfo.setId(id);
			userInfo.setAccountNum(accountNum);
			userInfo.setOwnerId(ownerId);
			userInfo.setUserType(userType);
			//1.1 统计总的记录数
			Integer count = dao.findCountByRole(ownerId);
			Integer max_page;//总页数
			if(count<=new Integer(pageSize)){
				max_page=1;
			}if(count%(new Integer(pageSize))==0){
				max_page=count/(new Integer(pageSize));
			}else{
				max_page = count/(new Integer(pageSize))+1;
			}
			//分页查询
			XzyFindInfo fi = new XzyFindInfo();
			fi.setOwnerId(ownerId);
			fi.setPage(page);
			fi.setPageSize(new Integer(pageSize));
			List<XzyFindInfo> roleInfo = dao.findPage(fi);
			
			//String pages = pageSize +","+max_page.toString();
			result.setPageSize(new Integer(pageSize));//每页的记录数
			result.setMaxPage(max_page);//总页数
			//result.setMsg(pages);
			result.setdatum(userInfo);//当前用户的信息
			result.setData(roleInfo);//分页查询的信息
			//result.setDataes(userInfo);//当前用户的信息
			result.setMsg("查询成功");
			result.setStatus(0);
		}else{
			result.setStatus(1);
			result.setMsg("您没有此权限");
		}
		return result;
	}
	/**
	 * 2.查询当前账户的ownerId下的所有的: 权限
	 */
	public XzyJsonResult findAllAuthInfo(BigInteger ownerId) {
		XzyJsonResult result = new XzyJsonResult();
		List<XzyFindInfo> list = dao.findAllAuthByOwnerId(ownerId);
		result.setStatus(0);
		result.setMsg("查询权限成功");
		result.setData(list);
		return result;
	}
	/**
	 * 3.新建角色
	 */
	@Transactional
	public XzyJsonResult addRole(String roleName, String roleProfile, String[] authIds,
			BigInteger ownerId,BigInteger operatorId,Integer currentUserType) {
		XzyJsonResult result = new XzyJsonResult();
		/*
		 * 只有是主账号(即userType==1)时,才可以进行角色管理的操作
		 */
		if(currentUserType.equals(1)){
			//3.1 查询角色名是否重复
			RoleManagement rm = new RoleManagement();
			rm.setRoleName(roleName);
			rm.setOwnerId(ownerId);
			String roleNames = dao.selectRoleNameByName(rm);
			if(roleNames != null){
				result.setMsg("角色名被占用");
				result.setStatus(1);
			}else{
				//3.2 向角色表中插入数据 并返回角色id值
				RoleManagement roleTable = new RoleManagement();
				roleTable.setRoleName(roleName);
				roleTable.setRoleProfile(roleProfile);
				roleTable.setOwnerId(ownerId);
				roleTable.setOperatorId(operatorId);
				Timestamp gmtCreate = new Timestamp(System.currentTimeMillis());
				roleTable.setGmtCreate(gmtCreate);
				dao.addRole(roleTable);
				BigInteger roleId = new BigInteger(roleTable.getId()+"");
				//3.3 向关联表中插入数据
				for(int i=0;i<authIds.length;i++){
					UserRoleAuthority ura = new UserRoleAuthority();				
					ura.setRoleId(roleId);
					BigInteger mouldId = new BigInteger(authIds[i]+"");
					ura.setMouldId(mouldId);
					ura.setOwnerId(ownerId);
					ura.setOperatorId(operatorId);
					ura.setGmtCreate(gmtCreate);
					dao.addRoleAuth(ura);
				}
				result.setMsg("新建角色成功");
				result.setStatus(0);
			}
		}else{
			result.setStatus(2);
			result.setMsg("您没有此权限");
		}
		return result;
	}
	/**
	 * 4.修改角色
	 */
	@Transactional
	public XzyJsonResult modifyRole(BigInteger roleId, String roleName, String roleProfile, BigInteger[] authIds,
			BigInteger ownerId, BigInteger operatorId, String prevAuId,Integer currentUserType) {
		XzyJsonResult result = new XzyJsonResult();
		/*
		 * 只有是主账号(即userType==1)时,才可以进行角色管理的操作
		 */
		if(currentUserType.equals(1)){
			/*
			 * 4.1 查询角色名是否重复 
			 */
			RoleManagement rm = new RoleManagement();
			rm.setRoleName(roleName);
			rm.setOwnerId(ownerId);
			rm.setId(roleId);
			String roleNames = dao.selectRoleNameById(rm);
			if(roleNames != null){
				result.setMsg("角色名被占用");
				result.setStatus(1);
			}else{
				/*
				 * 4.2 更新角色表中的数据
				 */
				RoleManagement role = new RoleManagement();
				role.setRoleName(roleName);
				role.setRoleProfile(roleProfile);
				role.setOwnerId(ownerId);
				role.setOperatorId(operatorId);
				Timestamp gmtModified = new Timestamp(System.currentTimeMillis());
				role.setGmtModified(gmtModified);
				role.setId(roleId);
				dao.updateRoleTable(role);
				/*
				 * 4.3 更新关联表的数据
				 */
				String[] prevRoleId = prevAuId.split(",");
				List<String> prevAuthIdList = new ArrayList<String>(Arrays.asList(prevRoleId));				
				List<BigInteger> authIdList = new ArrayList<BigInteger>(Arrays.asList(authIds));
				/*
				 * prevAuthIdList:该角色修改前拥有的权限id
				 * authIdList    :该角色修改后拥有的权限id
				 * 遍历两个集合,将相同的权限删除,剩余的即:
				 *   (1)authIdList     :修改后新增的权限
				 *   (2)prevAuthIdList :修改后未被包含的权限
				 */
				Iterator<String> itIds = prevAuthIdList.iterator();//修改前的权限id	
				while(itIds.hasNext()){
					BigInteger prevIds = new BigInteger(itIds.next()+"");
					Iterator<BigInteger> itId = authIdList.iterator();//修改后的权限id
					while(itId.hasNext()){
						BigInteger Ids = new BigInteger(itId.next()+"");
						if(prevIds.equals(Ids)){//修改前的等于修改后的
							itIds.remove();
							itId.remove();
						}
					}
				}
				/*
				 * (1)修改后新添加的权限插入  authIdList
				 */
				//userId为空时:
				for(BigInteger id : authIdList){
					UserRoleAuthority ura = new UserRoleAuthority();
					ura.setRoleId(roleId);
					ura.setMouldId(id);
					ura.setOwnerId(ownerId);
					ura.setOperatorId(operatorId);
					ura.setGmtCreate(gmtModified);
					dao.saveUserRoleAuth(ura);
				}
				//userId不为空时:
				//查询在同一个ownerId下的拥有该角色的用户id
				UserRoleAuthority user = new UserRoleAuthority();
				user.setRoleId(roleId);
				user.setOwnerId(ownerId);
				List<BigInteger> userIdList = dao.seleUserId(user);
				//在同一个ownerId下每个拥有该角色的用户将新增的权限插入
				for(BigInteger userId : userIdList){
					for(BigInteger mouldId : authIdList){
						UserRoleAuthority use = new UserRoleAuthority();
						use.setUserId(userId);
						use.setRoleId(roleId);
						use.setMouldId(mouldId);
						use.setOwnerId(ownerId);
						use.setOperatorId(operatorId);
						use.setGmtCreate(gmtModified);
						dao.saveUserRoleAuth(use);
					}					
				}
				/*
				 * (2)修改后未被包含的权限删除  prevAuthIdList
				 */
				for(String ids : prevAuthIdList){
					UserRoleAuthority ura = new UserRoleAuthority();
					ura.setRoleId(roleId);
					ura.setOwnerId(ownerId);
					ura.setMouldId(new BigInteger(ids+""));
					dao.deleteURA(ura);
				}
				
				result.setMsg("角色修改成功");
				result.setStatus(0);
			}
		}else{			
			result.setMessage("您没有此权限");
			result.setStatus(2);
		}			
		return result;
	}
	/**
	 * 5.删除角色
	 */
	@Transactional
	public XzyJsonResult delRole(BigInteger roleId, BigInteger ownerId,Integer currentUserType) {
		XzyJsonResult result = new XzyJsonResult();
		/*
		 * 只有是主账号(即userType==1)时,才可以进行角色管理的操作
		 */
		if(currentUserType.equals(1)){
			//5.1 删除角色表中对应的角色
			RoleManagement rm = new RoleManagement();
			rm.setId(roleId);
			rm.setOwnerId(ownerId);
			dao.deleteRoleById(rm);
					
			//5.2将关联表中的userId为空的对应的角色id删除
			UserRoleAuthority ur = new UserRoleAuthority();
			ur.setRoleId(roleId);
			ur.setOwnerId(ownerId);
			dao.deleteRoleFromURA(ur);
			
			result.setStatus(0);
			result.setMsg("删除角色成功");
		}else{
			result.setMsg("您没有此权限");
			result.setStatus(2);
		}		
		return result;
	}
}