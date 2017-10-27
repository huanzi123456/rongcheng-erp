package com.rongcheng.erp.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
/**
 * 存储查询出来的信息
 * 权限表,角色表,用户表,关联表
 * @author 薛宗艳
 *
 */
public class XzyFindInfo implements Serializable{
	private static final long serialVersionUID = 1544954201889883082L;
	private BigInteger roleId;                        //角色id
	private String roleName;                          //角色名
	private String roleProfile;                       //角色描述
	private BigInteger mouldId;                       //权限ID
	
	private String parentModuleId;                    //父模块id
	private String parentModuleName;                  //父模块名
	private String childModuleId;                     //子模块id
	private String childModuleName;                   //子模块名
	
	private BigInteger id;                            //用户id
	private BigInteger ownerId;                       //用户主账户id
	private Integer userType;                         //用户类型
	private String name;                              //用户名
	private String accountNum;                        //用户账号
	private String password;                          //密码
	private String telephone;                         //电话
	private Integer accountStatus;                    //账户状态:0账户停用或关闭；1账户启用或正常
	private Date gmtCreate;                           //创建时间
	private String strRoleId;                         //角色id的字符串
	private String strRoleName;                       //角色名的字符串
	private String strMouldId;                        //权限id的字符串
	private String strMouldName;                      //权限名的字符串
	private List<String> auId;                        //"角色id+该角色拥有的权限id"的字符串集合
	private String replenishMoudleId;                  //补充权限id的字符串
	private String replenishMoudleName;                //补充权限名的字符串
	private Integer page;                             //当前页 
    private Integer pageSize;                         //每页记录数(默认5条)
	
    public XzyFindInfo() {
		super();
	}
	public XzyFindInfo(BigInteger roleId, String roleName, String roleProfile, BigInteger mouldId,
			String parentModuleId, String parentModuleName, String childModuleId, String childModuleName, BigInteger id,
			BigInteger ownerId, Integer userType, String name, String accountNum, String password, String telephone,
			Integer accountStatus, Date gmtCreate, String strRoleId, String strRoleName, String strMouldId,
			String strMouldName, List<String> auId, String replenishMoudleId, String replenishMoudleName, Integer page,
			Integer pageSize) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleProfile = roleProfile;
		this.mouldId = mouldId;
		this.parentModuleId = parentModuleId;
		this.parentModuleName = parentModuleName;
		this.childModuleId = childModuleId;
		this.childModuleName = childModuleName;
		this.id = id;
		this.ownerId = ownerId;
		this.userType = userType;
		this.name = name;
		this.accountNum = accountNum;
		this.password = password;
		this.telephone = telephone;
		this.accountStatus = accountStatus;
		this.gmtCreate = gmtCreate;
		this.strRoleId = strRoleId;
		this.strRoleName = strRoleName;
		this.strMouldId = strMouldId;
		this.strMouldName = strMouldName;
		this.auId = auId;
		this.replenishMoudleId = replenishMoudleId;
		this.replenishMoudleName = replenishMoudleName;
		this.page = page;
		this.pageSize = pageSize;
	}
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleProfile() {
		return roleProfile;
	}
	public void setRoleProfile(String roleProfile) {
		this.roleProfile = roleProfile;
	}
	public BigInteger getMouldId() {
		return mouldId;
	}
	public void setMouldId(BigInteger mouldId) {
		this.mouldId = mouldId;
	}
	public String getParentModuleId() {
		return parentModuleId;
	}
	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}
	public String getParentModuleName() {
		return parentModuleName;
	}
	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}
	public String getChildModuleId() {
		return childModuleId;
	}
	public void setChildModuleId(String childModuleId) {
		this.childModuleId = childModuleId;
	}
	public String getChildModuleName() {
		return childModuleName;
	}
	public void setChildModuleName(String childModuleName) {
		this.childModuleName = childModuleName;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(BigInteger ownerId) {
		this.ownerId = ownerId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getStrRoleId() {
		return strRoleId;
	}
	public void setStrRoleId(String strRoleId) {
		this.strRoleId = strRoleId;
	}
	public String getStrRoleName() {
		return strRoleName;
	}
	public void setStrRoleName(String strRoleName) {
		this.strRoleName = strRoleName;
	}
	public String getStrMouldId() {
		return strMouldId;
	}
	public void setStrMouldId(String strMouldId) {
		this.strMouldId = strMouldId;
	}
	public String getStrMouldName() {
		return strMouldName;
	}
	public void setStrMouldName(String strMouldName) {
		this.strMouldName = strMouldName;
	}
	public List<String> getAuId() {
		return auId;
	}
	public void setAuId(List<String> auId) {
		this.auId = auId;
	}
	public String getReplenishMoudleId() {
		return replenishMoudleId;
	}
	public void setReplenishMoudleId(String replenishMoudleId) {
		this.replenishMoudleId = replenishMoudleId;
	}
	public String getReplenishMoudleName() {
		return replenishMoudleName;
	}
	public void setReplenishMoudleName(String replenishMoudleName) {
		this.replenishMoudleName = replenishMoudleName;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNum == null) ? 0 : accountNum.hashCode());
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((auId == null) ? 0 : auId.hashCode());
		result = prime * result + ((childModuleId == null) ? 0 : childModuleId.hashCode());
		result = prime * result + ((childModuleName == null) ? 0 : childModuleName.hashCode());
		result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mouldId == null) ? 0 : mouldId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((parentModuleId == null) ? 0 : parentModuleId.hashCode());
		result = prime * result + ((parentModuleName == null) ? 0 : parentModuleName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((replenishMoudleId == null) ? 0 : replenishMoudleId.hashCode());
		result = prime * result + ((replenishMoudleName == null) ? 0 : replenishMoudleName.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((roleProfile == null) ? 0 : roleProfile.hashCode());
		result = prime * result + ((strMouldId == null) ? 0 : strMouldId.hashCode());
		result = prime * result + ((strMouldName == null) ? 0 : strMouldName.hashCode());
		result = prime * result + ((strRoleId == null) ? 0 : strRoleId.hashCode());
		result = prime * result + ((strRoleName == null) ? 0 : strRoleName.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XzyFindInfo other = (XzyFindInfo) obj;
		if (accountNum == null) {
			if (other.accountNum != null)
				return false;
		} else if (!accountNum.equals(other.accountNum))
			return false;
		if (accountStatus == null) {
			if (other.accountStatus != null)
				return false;
		} else if (!accountStatus.equals(other.accountStatus))
			return false;
		if (auId == null) {
			if (other.auId != null)
				return false;
		} else if (!auId.equals(other.auId))
			return false;
		if (childModuleId == null) {
			if (other.childModuleId != null)
				return false;
		} else if (!childModuleId.equals(other.childModuleId))
			return false;
		if (childModuleName == null) {
			if (other.childModuleName != null)
				return false;
		} else if (!childModuleName.equals(other.childModuleName))
			return false;
		if (gmtCreate == null) {
			if (other.gmtCreate != null)
				return false;
		} else if (!gmtCreate.equals(other.gmtCreate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mouldId == null) {
			if (other.mouldId != null)
				return false;
		} else if (!mouldId.equals(other.mouldId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (parentModuleId == null) {
			if (other.parentModuleId != null)
				return false;
		} else if (!parentModuleId.equals(other.parentModuleId))
			return false;
		if (parentModuleName == null) {
			if (other.parentModuleName != null)
				return false;
		} else if (!parentModuleName.equals(other.parentModuleName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (replenishMoudleId == null) {
			if (other.replenishMoudleId != null)
				return false;
		} else if (!replenishMoudleId.equals(other.replenishMoudleId))
			return false;
		if (replenishMoudleName == null) {
			if (other.replenishMoudleName != null)
				return false;
		} else if (!replenishMoudleName.equals(other.replenishMoudleName))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (roleProfile == null) {
			if (other.roleProfile != null)
				return false;
		} else if (!roleProfile.equals(other.roleProfile))
			return false;
		if (strMouldId == null) {
			if (other.strMouldId != null)
				return false;
		} else if (!strMouldId.equals(other.strMouldId))
			return false;
		if (strMouldName == null) {
			if (other.strMouldName != null)
				return false;
		} else if (!strMouldName.equals(other.strMouldName))
			return false;
		if (strRoleId == null) {
			if (other.strRoleId != null)
				return false;
		} else if (!strRoleId.equals(other.strRoleId))
			return false;
		if (strRoleName == null) {
			if (other.strRoleName != null)
				return false;
		} else if (!strRoleName.equals(other.strRoleName))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "XzyFindInfo [roleId=" + roleId + ", roleName=" + roleName + ", roleProfile=" + roleProfile
				+ ", mouldId=" + mouldId + ", parentModuleId=" + parentModuleId + ", parentModuleName="
				+ parentModuleName + ", childModuleId=" + childModuleId + ", childModuleName=" + childModuleName
				+ ", id=" + id + ", ownerId=" + ownerId + ", userType=" + userType + ", name=" + name + ", accountNum="
				+ accountNum + ", password=" + password + ", telephone=" + telephone + ", accountStatus="
				+ accountStatus + ", gmtCreate=" + gmtCreate + ", strRoleId=" + strRoleId + ", strRoleName="
				+ strRoleName + ", strMouldId=" + strMouldId + ", strMouldName=" + strMouldName + ", auId=" + auId
				+ ", replenishMoudleId=" + replenishMoudleId + ", replenishMoudleName=" + replenishMoudleName
				+ ", page=" + page + ", pageSize=" + pageSize + "]";
	}	     	
}
