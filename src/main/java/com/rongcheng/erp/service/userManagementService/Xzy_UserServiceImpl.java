package com.rongcheng.erp.service.userManagementService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rongcheng.erp.dao.Xzy_UserDao;
import com.rongcheng.erp.dto.XzyFindInfo;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.entity.UserRoleAuthority;
import com.rongcheng.erp.utils.UserUtil;
/**
 * 用户管理页面的service实现类
 * @author 薛宗艳
 *
 */
@Service
public class Xzy_UserServiceImpl implements Xzy_UserService{
	@Resource 
	private Xzy_UserDao dao;  
	/*
	 * 1.分页查询
	 */
//	@Value("#{jdbc[pageSize]}")
//	String pageSize;//每页的记录数
	@Value("#{config['rows']}")
	private String pageSize;//每页的记录数
	public XzyJsonResult userPaging(Integer page,BigInteger currentUserId,String currentUserAccountNum,BigInteger currentUserOwnerId,Integer currentUserType) {
		XzyJsonResult result = new XzyJsonResult();
		//将当前登录的账户的信息返回
		UserInfo info = new UserInfo();
		info.setId(currentUserId);
		info.setAccountNum(currentUserAccountNum);
		info.setOwnerId(currentUserOwnerId);
		info.setUserType(currentUserType);
		//1.1统计用户表中同一个owner_id下的用户数量
		Integer count = dao.countUserIdByOwnerId(currentUserOwnerId);
		page=(page-1)*(new Integer (pageSize));
		Integer max_page;//总页数
		if(count<=new Integer(pageSize)){
			max_page=1;		
		}if(count%(new Integer(pageSize))==0){
			max_page=count/(new Integer(pageSize));
		}else{
			max_page = count/(new Integer(pageSize))+1;			
		}
		//1.2分页查询
		XzyFindInfo p = new XzyFindInfo();
		p.setOwnerId(currentUserOwnerId);
    	p.setPage(page);
    	p.setPageSize(new Integer (pageSize));
    	List<XzyFindInfo> list = dao.userPaging(p);
    	
    	List<XzyFindInfo> us = new ArrayList<XzyFindInfo>();    	
    	for(XzyFindInfo lists : list){
    		XzyFindInfo user = new XzyFindInfo();
    		String strRoleId = lists.getStrRoleId();//角色id的字符串
    		String roleName = lists.getStrRoleName();//角色名的字符串
    		String strsRoleId = null;
    		String strRoleName;
    		String strMouldId = null;
    		String strMouldName=null;
    		if(strRoleId != null){   			
    			//去除角色名的重复,并重新拼成字符串
    			String[] roleNames = roleName.split(",");
    			List<String> roleNamed = new ArrayList<String>(Arrays.asList(roleNames));//将数组转换成集合
    			List<String> roleNamese = UserUtil.removeDuplicate(roleNamed);//去除重复
    			strRoleName=StringUtils.join(roleNamese,",");
    			//去除角色id的重复
    			String[] dual = strRoleId.split(",");
    			List<String> duals = new ArrayList<String>(Arrays.asList(dual));//将数组转换成集合
    			List<String> roleIds = UserUtil.removeDuplicate(duals);//去除重复
    			strsRoleId=StringUtils.join(roleIds,",");
    			String sts = "";
    			List<String> auIds = new ArrayList<String>();
    			for(String roleId : roleIds){
    				//5.3 查询同一用户下的不同角色所拥有的权限id(同一用户下的角色和权限关联) 	
    				UserRoleAuthority use = new UserRoleAuthority();
    				use.setRoleId(new BigInteger(roleId+""));
    				use.setOwnerId(currentUserOwnerId);
    				use.setUserId(lists.getId());
    				List<String> auId = dao.seleAuthId(use);
    				String st=StringUtils.join(auId,"-");
    				sts = roleId+"-"+st;       		
            		auIds.add(sts);
            		user.setAuId(auIds);
    			}
    			//去除角色下的权限id的重复
        		String mouldId = lists.getStrMouldId();//权限id的字符串 
        	    String[] auid = mouldId.split(",");
        		List<String> auids = new ArrayList<String>(Arrays.asList(auid));//将数组转换成集合
        		List<String> aid = UserUtil.removeDuplicate(auids);//去除重复    			
        		strMouldId=StringUtils.join(aid,",");
        		//去除角色下的权限名的重复
        		String MouldName = lists.getStrMouldName();
        		String[] auname = MouldName.split(",");
        		List<String> aunames = new ArrayList<String>(Arrays.asList(auname));//将数组转换成集合
        		List<String> aname = UserUtil.removeDuplicate(aunames);//去除重复 
        		strMouldName=StringUtils.join(aname,",");
    		}else{
    			if(lists.getUserType().equals(1)){
    				strRoleName="全部角色";
    			}else{
    				strRoleName="未选择角色";
    			}
    		}
    		
    		//查询补充权限
    		BigInteger userId = lists.getId();//用户的id                                
    		UserRoleAuthority users = new UserRoleAuthority();
    		users.setOwnerId(currentUserOwnerId);
    		users.setUserId(userId);
    		List<XzyFindInfo> strs = dao.selectReplenishAuId(users);
    		
    		String replenishAuid=null;//补充权限id
    		String replenishAuName=null;//补充权限名
    		for(XzyFindInfo li : strs){
    			replenishAuid = li.getReplenishMoudleId();
    			replenishAuName = li.getReplenishMoudleName();
    		}
    		/*
    		 * 当该用户的角色id为空,并且补充权限也为空时
    		 *  (1)主账户:拥有全部的角色和权限
    		 *  (2)普通账户:未设置任何角色和权限
    		 */
            if(strRoleId == null && replenishAuid == null){
            	if(lists.getUserType().equals(1)){
            		replenishAuName="全部权限";
    			}else{
    				replenishAuName="未选择权限";
    			}           	
    		}
    		user.setId(lists.getId());                          //用户的 id
			user.setUserType(lists.getUserType());              //用户的类型
			user.setAccountNum(lists.getAccountNum());          //用户的账号
			user.setName(lists.getName());                      //用户名
			user.setPassword(lists.getPassword());              //用户的密码
			user.setTelephone(lists.getTelephone());            //用户的电话
			user.setAccountStatus(lists.getAccountStatus());    //用户的状态
			user.setStrRoleName(strRoleName);                   //该用户所拥有的角色名字符串
			user.setStrRoleId(strsRoleId);                      //该用户所拥有的角色id字符串
			user.setStrMouldName(strMouldName);                 //该用户所拥有的角色下的权限名字符串
			user.setStrMouldId(strMouldId);                     //该用户所拥有的角色下的权限id字符串
			//user.setAuId(auIds);在87行                                                                 //该用户的"每个角色id+该角色下的权限id"的字符串
			user.setGmtCreate(lists.getGmtCreate());            //该用户的创建时间
			user.setReplenishMoudleId(replenishAuid);           //该用户的补充权限id字符串
			user.setReplenishMoudleName(replenishAuName);        //该用户的补充权限名字符串
			us.add(user);			
    	}
    	result.setMaxPage(max_page);
    	result.setPageSize(new Integer(pageSize));   	
    	result.setMsg("查询用户成功");
    	result.setData(us);
    	result.setdatum(info);
    	result.setStatus(0); 	
		return result;
	}
	/*
	 * 2.查询当前用户的ownerId下的所有的: 角色
	 */
	public XzyJsonResult findRole(BigInteger ownerId) {
		XzyJsonResult result = new XzyJsonResult();
		List<XzyFindInfo> list = dao.findRoleAndAuth(ownerId);
		result.setStatus(0);
		result.setMsg("查询角色成功");
		result.setData(list);
		return result;
	}
	/*
	 * 3.查询当前账户的ownerId下的所有的: 权限
	 */
	public XzyJsonResult findAuth(BigInteger ownerId) {
		XzyJsonResult result = new XzyJsonResult();
		List<XzyFindInfo> list = dao.findAuth(ownerId);
		result.setStatus(0);
		result.setMsg("查询权限成功");
		result.setData(list);
		return result;
	}
	/*
	 * 4.新建用户
	 */	
	@Transactional
	public XzyJsonResult addUser(String accountNum,String password,String name,String telephone,
			String[] list,BigInteger[] authIds,BigInteger ownerId,BigInteger operatorId,Integer userType) {
		XzyJsonResult result = new XzyJsonResult();
		/*
		 * 只有主账户才可以新建用户
		 */
		if(userType.equals(1)){
			//4.1 查询新建的用户的账号,避免重复 
			String li = dao.findByAccountNum(accountNum);
			if(li != null){
				 result.setMsg("账号被占用");
				 result.setStatus(1);
			}else{					
				//4.2 向用户表中插入数据
				UserInfo user = new UserInfo();
				user.setUserType(2);//暂时
				user.setAccountNum(accountNum);
				String pwd = UserUtil.md5(password);
				user.setPassword(pwd);
				user.setName(name);
				user.setTelephone(telephone);
				user.setAccountStatus(1);//暂时
				user.setOwnerId(ownerId);
				user.setOperatorId(operatorId);
				Timestamp d = new Timestamp(System.currentTimeMillis()); 
				user.setGmtCreate(d);
				dao.saveUserInfo(user);
				BigInteger userId = new BigInteger(user.getId()+"");//获得用户的自增id
				//4.3向关联表中插入数据
				//4.3.1 角色不为空时
				if(list != null){
					for(int i=0;i<list.length;i++){					
						String str = list[i];//"角色id+权限id"的字符串
						
						String[] strs = str.split("-");
						
						BigInteger roleId = new BigInteger(strs[0]+"");
						
						for(int j=1;j<strs.length;j++){
							UserRoleAuthority ura = new UserRoleAuthority();
							ura.setUserId(userId);
							ura.setRoleId(roleId);
							ura.setMouldId(new BigInteger(strs[j]+""));
							ura.setOwnerId(ownerId);
							ura.setOperatorId(operatorId);
							ura.setGmtCreate(d);
							dao.saveUserRoleAuthInfo(ura);	
						}
					}
				}
				//4.3.2 角色为空时
				if(authIds != null){
					for(int i=0;i<authIds.length;i++){
						UserRoleAuthority ur = new UserRoleAuthority();
						ur.setUserId(userId);
						ur.setMouldId(authIds[i]);
						ur.setOwnerId(ownerId);
						ur.setOperatorId(operatorId);
						ur.setGmtCreate(d);
						dao.saveUserRoleAuthInfo(ur);
					}
				}				
				result.setStatus(0);
				result.setMsg("创建账户成功");
			}
		}else{
			result.setMsg("您没有此权限");
			result.setStatus(2);
		}		
		return result;
	}
	
	/*
	 * 5.修改用户
	 */
	@Transactional
	public XzyJsonResult modifyUser(BigInteger id,String accountNum,String name,String oldPassword,
    		String newPassword,String telephone,BigInteger[] prevAuthIds,BigInteger[] authIds,
    		String[] prevList,String[] list,BigInteger ownerId,
    		BigInteger operatorId,Integer currUserType) {		
		XzyJsonResult result = new XzyJsonResult();
		boolean ok = true;
		//5.1 在用户表查询该用户的密码
		UserInfo ui = new UserInfo();
		ui.setId(id);
		ui.setOwnerId(ownerId);
		String pwd = dao.selectPwdById(ui);//数据库中加密的密码
		String pwds = UserUtil.md5(oldPassword);//页面中的旧密码		
		if(!(pwd.equals(pwds))){
			result.setMessage("密码错误");
			result.setStatus(1);
			ok=false;
		}
		//5.2 在用户表查询账号
		UserInfo up = new UserInfo();
		up.setAccountNum(accountNum);
		up.setId(id);
		String accountNums = dao.seleAccountNumById(up);		
		if(accountNums != null){
			result.setMsg("账号被占用");
			result.setStatus(1);
			ok=false;
		}
		if(ok){			
			//5.3 修改用户表中的信息
			String password = "";
        	if(newPassword != ""){
        		//如果修改密码,则password=新密码
        		password = newPassword;
        	}else{
        		//如果不修改密码,则password=旧密码
        		password = oldPassword;
        	}
        	password = UserUtil.md5(password);
        	UserInfo userInfo = new UserInfo();
        	userInfo.setName(name);
        	userInfo.setAccountNum(accountNum);
        	userInfo.setPassword(password);
        	userInfo.setTelephone(telephone);
        	userInfo.setOperatorId(operatorId);
        	Timestamp gmtModified = new Timestamp(System.currentTimeMillis()); 
        	userInfo.setGmtModified(gmtModified);
        	userInfo.setId(id);
        	userInfo.setOwnerId(ownerId);
        	dao.updateUserInfo(userInfo);
        	//5.4修改关联表的数据
        	if(currUserType.equals(1)){
        		//5.4.1 角色id不为空时
            	if(list != null){//修改后的角色list不为空
            		if(prevList != null){//修改前的角色prevList不为空
            			/*
                		 * 修改前后的角色均不为空时:
                		 *  (1)找出修改前后均包含的角色
                		 *  (2)找出修改后新增的角色
                		 *  (3)找出修改后未被包含的角色
                		 */
                		//(1)找出修改前后均包含的角色,将其删除(即不做任何处理)
                		List<String> roleId = new ArrayList<String>(Arrays.asList(list));
                    	List<String> prevRoleId = new ArrayList<String>(Arrays.asList(prevList));
                    	Iterator<String> prevRoleIds = prevRoleId.iterator();
                    	while(prevRoleIds.hasNext()){
                    		String prevId = prevRoleIds.next();
                    		Iterator<String> roleIds = roleId.iterator();
                    		while(roleIds.hasNext()){
                    			String roleid = roleIds.next();
                    			if(prevId.equals(roleid)){
                    				prevRoleIds.remove();
                    				roleIds.remove();
                    			}
                    		}
                    	} 
                    	//(2)找出修改后新增的角色(插入)          roleId:修改后新增加的角色
                    	for(String RoleIds : roleId){
                    		String[] ids = RoleIds.split("-");
                    		String roId = ids[0];
                    		for(int i=1;i<ids.length;i++){
                        		String mouldId = ids[i];
                        		UserRoleAuthority ura = new UserRoleAuthority();
                				ura.setUserId(id);
                				ura.setRoleId(new BigInteger(roId));
                				ura.setMouldId(new BigInteger(mouldId));
                				ura.setOwnerId(ownerId);
                				ura.setOperatorId(operatorId);
                				ura.setGmtCreate(gmtModified);
                				dao.insertURAs(ura);
                        	}
                    	}            	
                    	//(3)找出修改后未被包含的角色(删除)       prevRoleId:修改后未被包含的角色  
                    	for(String RoleIds : prevRoleId){
                    		String[] ids = RoleIds.split("-");
                    		String roId = ids[0];
                    		for(int i=0;i<ids.length;i++){
                    			String mouldId = ids[i];
                    			UserRoleAuthority ura = new UserRoleAuthority();
                				ura.setUserId(id);
                				ura.setRoleId(new BigInteger(roId));
                				ura.setMouldId(new BigInteger(mouldId));
                				ura.setOwnerId(ownerId);
                				dao.deleteInfo(ura);
                    		}
                    	}
            		}else{
            			/*
            			 * 修改前的角色prevList为空时,修改后的角色list不为空:
            			 *   将修改后的角色全部作为新增的角色插入
            			 */
            			List<String> roleId = new ArrayList<String>(Arrays.asList(list));
            			for(String RoleIds : roleId){
                    		String[] ids = RoleIds.split("-");
                    		String roId = ids[0];
                    		for(int i=1;i<ids.length;i++){
                        		String mouldId = ids[i];
                        		UserRoleAuthority ura = new UserRoleAuthority();
                				ura.setUserId(id);
                				ura.setRoleId(new BigInteger(roId));
                				ura.setMouldId(new BigInteger(mouldId));
                				ura.setOwnerId(ownerId);
                				ura.setOperatorId(operatorId);
                				ura.setGmtCreate(gmtModified);
                				dao.insertURAs(ura);
                        	}
                    	}
            		}
            	}else{      		
            		if(prevList != null){
            			/*
                		 * 修改前的角色prevList不为空,修改后的角色list为空时:
                		 *   将该用户的角色全部删除
                		 */
            			List<String> prevRoleId = new ArrayList<String>(Arrays.asList(prevList));
            			for(String RoleIds : prevRoleId){
                    		String[] ids = RoleIds.split("-");
                    		String roId = ids[0];
                    		for(int i=0;i<ids.length;i++){
                    			String mouldId = ids[i];
                    			UserRoleAuthority ura = new UserRoleAuthority();
                				ura.setUserId(id);
                				ura.setRoleId(new BigInteger(roId));
                				ura.setMouldId(new BigInteger(mouldId));
                				ura.setOwnerId(ownerId);
                				dao.deleteInfo(ura);
                    		}
                    	}
            		}
            	}
            	/**
            	  * @param prevAuthIds      :修改前的补充权限id数组
                  * @param authIds          :修改后的补充权限id数组
                  */
            	//5.4.2角色id为空时,即补充权限的修改
            	if(authIds != null){//修改后的补充权限authIds不为空
            		if(prevAuthIds != null){//修改前的补充权限prevAuthIds不为空
            			/*
                		 * 修改前后的补充权限不为空时:
                		 *   (1)找出修改前后均包含的补充权限
                		 *   (2)找出修改后新增的补充权限
                		 *   (3)找出修改后未被包含的补充权限
                		 */
            			//(1)找出修改前后均包含的补充权限,将其删除(即不做任何处理)
            			List<BigInteger> mouldId = new ArrayList<BigInteger>(Arrays.asList(authIds));
                    	List<BigInteger> prevMouldId = new ArrayList<BigInteger>(Arrays.asList(prevAuthIds));
                    	Iterator<BigInteger> prevMouldIds = prevMouldId.iterator();
                    	while(prevMouldIds.hasNext()){
                    		BigInteger prevId = prevMouldIds.next();
                    		Iterator<BigInteger> moudleIds = mouldId.iterator();
                    		while(moudleIds.hasNext()){
                    			BigInteger mouldid = moudleIds.next();
                    			if(prevId.equals(mouldid)){
                    				prevMouldIds.remove();
                    				moudleIds.remove();
                    			}
                    		}
                    	}
            			//(2)找出修改后新增的补充权限authIds
                    	for(BigInteger moId : mouldId){
            				UserRoleAuthority ura = new UserRoleAuthority();
            				ura.setUserId(id);
            				ura.setMouldId(moId);
            				ura.setOwnerId(ownerId);
            				ura.setOperatorId(operatorId);
            				ura.setGmtCreate(gmtModified);
            				dao.insertURAs(ura);
            			}
            			//(3)找出修改后未被包含的补充权限删除 prevAuthIds
                    	for(BigInteger moId : prevMouldId){
        					UserRoleAuthority ura = new UserRoleAuthority();
            				ura.setUserId(id);
            				ura.setMouldId(moId);
            				ura.setOwnerId(ownerId);
            				dao.deleteInfo(ura);
        				}
            		}else{//修改前的补充权限prevAuthIds为空
            			/*
            			 * 修改前的补充权限prevAuthIds为空
            			 *   将修改后的补充权限authIds全部作为新增的补充权限插入
            			 */
            			List<BigInteger> mouldId = new ArrayList<BigInteger>(Arrays.asList(authIds));
            			for(BigInteger moId : mouldId){
            				UserRoleAuthority ura = new UserRoleAuthority();
            				ura.setUserId(id);
            				ura.setMouldId(moId);
            				ura.setOwnerId(ownerId);
            				ura.setOperatorId(operatorId);
            				ura.setGmtCreate(gmtModified);
            				dao.insertURAs(ura);
            			}
            		}        		
            	}else{//修改后的补充权限authIds为空
            		if(prevAuthIds != null){//修改前的补充权限prevAuthIds不为空
            			/*
                		 * 修改前的补充权限不为空,修改后的补充权限为空时:
                		 *   将该用户的补充权限删除
                		 */
            			List<BigInteger> prevMouldId = new ArrayList<BigInteger>(Arrays.asList(prevAuthIds));
            			for(BigInteger moId : prevMouldId){
        					UserRoleAuthority ura = new UserRoleAuthority();
            				ura.setUserId(id);
            				ura.setMouldId(moId);
            				ura.setOwnerId(ownerId);
            				dao.deleteInfo(ura);
        				}
            		}       		
            	}
            	result.setMsg("账户信息修改成功");
    			result.setStatus(0);
        	}else{
        		result.setMsg("您没有此权限");
        		result.setStatus(2);
        	}			
		}
		return result;
	}
	/*
	 * 6.其他按钮(启用,停用,删除)
	 */
	@Transactional
	public XzyJsonResult otherButton(BigInteger id, BigInteger ownerId, BigInteger operatorId, String buttonName,Integer currentUserType) {
		XzyJsonResult result = new XzyJsonResult();
		String name=buttonName.trim();
		//只有主账户可以进行除了自己以外的停用,启用,删除操作
		if(currentUserType.equals(1)){
			if("启用".equals(name)){
				//1.启用按钮
				dao.starts(operatorId, id, ownerId);
				result.setStatus(0);
				result.setData(name);
			}else if("停用".equals(name)){
				//2.停用按钮
		    	dao.stops(operatorId, id, ownerId);
		    	result.setStatus(0);
		    	result.setData(name);
			}else if("删除".equals(name)){
				//3.删除按钮
				  //(1)删除用户表的记录
		    	dao.deleUser(id,ownerId);
		    	  //(2)删除关联表的信息
		    	dao.deleURA(id,ownerId);
		    	result.setStatus(0);
		    	result.setData(name);
			}
		}else{
			result.setStatus(1);
	    	result.setMsg("您没有此权限");
		}		
		return result;
	}
}