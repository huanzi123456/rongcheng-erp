package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * erp账号用户信息表
 * 
 * @author 赵滨
 *
 */
public class UserInfo implements Serializable {

	// 版本号
	private static final long serialVersionUID = 3267766318450583865L;

	// 1.子账号用户id 非空 非负 长度20
	private BigInteger id;

	// 2.用户昵称 长度20
	private String nickname;

	// 3.用户姓名 长度10
	private String name;

	// 4.用户电话 长度30
	private String telephone;

	// 5.用户手机 长度30
	private String mobile;

	// 6.用户所在地区id 非负 长度20
	private BigInteger regionId;

	// 7.自定义内容1 长度30
	private String reserved1;

	// 8.备注 长度100
	private String note;

	// 9.用户主账户ID 非空 非负 长度20 数据表所有者
	private BigInteger ownerId;

	// 10.操作人 非空 非负 长度20
	private BigInteger operatorId;

	// 11.是否管理员账号 非空 非负 长度1
	private Integer admin;

	// 12.是否主账号（或所有者） 非空 非负 长度1
	private Integer rootAccount;

	// 13.记录创建时间 非空 datetime
	private Timestamp gmtCreate;

	// 14.记录修改时间 datetime
	private Timestamp gmtModified;
	// 15.用户账户
	private String accountNum;
	// 16.数字证书
	private String digitalCertificate;
	// 17.账户状态
	private Integer accountStatus;
	// 18.账户角色
	private Integer roleId;
	// 19.账户补充权限
	private Integer authorityId;
	// 20.账户密码
	private String password;
	// 20.账户邮箱
	private String accountEmail;
	// 21.用户类型
	private Integer userType;
	// 22.用户自定义地址
	private String userAddress;

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfo(BigInteger id, String nickname, String name, String telephone, String mobile, BigInteger regionId, String reserved1, String note, BigInteger ownerId, BigInteger operatorId, Integer admin, Integer rootAccount, Timestamp gmtCreate, Timestamp gmtModified, String accountNum, String digitalCertificate, Integer accountStatus, Integer roleId, Integer authorityId, String password, String accountEmail, Integer userType, String userAddress) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.name = name;
		this.telephone = telephone;
		this.mobile = mobile;
		this.regionId = regionId;
		this.reserved1 = reserved1;
		this.note = note;
		this.ownerId = ownerId;
		this.operatorId = operatorId;
		this.admin = admin;
		this.rootAccount = rootAccount;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
		this.accountNum = accountNum;
		this.digitalCertificate = digitalCertificate;
		this.accountStatus = accountStatus;
		this.roleId = roleId;
		this.authorityId = authorityId;
		this.password = password;
		this.accountEmail = accountEmail;
		this.userType = userType;
		this.userAddress = userAddress;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigInteger getRegionId() {
		return regionId;
	}

	public void setRegionId(BigInteger regionId) {
		this.regionId = regionId;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigInteger getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(BigInteger ownerId) {
		this.ownerId = ownerId;
	}

	public BigInteger getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(BigInteger operatorId) {
		this.operatorId = operatorId;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public Integer getRootAccount() {
		return rootAccount;
	}

	public void setRootAccount(Integer rootAccount) {
		this.rootAccount = rootAccount;
	}

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Timestamp gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getDigitalCertificate() {
		return digitalCertificate;
	}

	public void setDigitalCertificate(String digitalCertificate) {
		this.digitalCertificate = digitalCertificate;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNum == null) ? 0 : accountNum.hashCode());
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((authorityId == null) ? 0 : authorityId.hashCode());
		result = prime * result + ((digitalCertificate == null) ? 0 : digitalCertificate.hashCode());
		result = prime * result + ((accountEmail == null) ? 0 : accountEmail.hashCode());
		result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
		result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
		result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((rootAccount == null) ? 0 : rootAccount.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
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
		UserInfo other = (UserInfo) obj;
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
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (authorityId == null) {
			if (other.authorityId != null)
				return false;
		} else if (!authorityId.equals(other.authorityId))
			return false;
		if (digitalCertificate == null) {
			if (other.digitalCertificate != null)
				return false;
		} else if (!digitalCertificate.equals(other.digitalCertificate))
			return false;
		if (accountEmail == null) {
			if (other.accountEmail != null)
				return false;
		} else if (!accountEmail.equals(other.accountEmail))
			return false;
		if (gmtCreate == null) {
			if (other.gmtCreate != null)
				return false;
		} else if (!gmtCreate.equals(other.gmtCreate))
			return false;
		if (gmtModified == null) {
			if (other.gmtModified != null)
				return false;
		} else if (!gmtModified.equals(other.gmtModified))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (operatorId == null) {
			if (other.operatorId != null)
				return false;
		} else if (!operatorId.equals(other.operatorId))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		if (reserved1 == null) {
			if (other.reserved1 != null)
				return false;
		} else if (!reserved1.equals(other.reserved1))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (rootAccount == null) {
			if (other.rootAccount != null)
				return false;
		} else if (!rootAccount.equals(other.rootAccount))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (userAddress == null) {
			if (other.userAddress != null)
				return false;
		} else if (!userAddress.equals(other.userAddress))
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
		return "UserInfo [id=" + id + ", nickname=" + nickname + ", name=" + name + ", telephone=" + telephone + ", mobile=" + mobile + ", regionId=" + regionId + ", reserved1=" + reserved1 + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", admin=" + admin + ", rootAccount=" + rootAccount + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", accountNum=" + accountNum + ", digitalCertificate=" + digitalCertificate + ", accountStatus=" + accountStatus + ", roleId=" + roleId + ", authorityId=" + authorityId + ", password=" + password + ", accountEmail=" + accountEmail + ", userType=" + userType + ", userAddress=" + userAddress + "]";
	}

}
