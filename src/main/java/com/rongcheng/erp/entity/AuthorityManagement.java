package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
/**
 * 权限管理表
 * @author 薛宗艳
 *
 */
public class AuthorityManagement implements Serializable{
	private static final long serialVersionUID = -7030675463262184770L;
	private BigInteger id;
    private BigInteger moduleId;
    private String moduleName;
    private BigInteger parentModule;
    private Integer userSequence;
    private Integer isShow;
    private String reserved1;
    private String note;
    private BigInteger ownerId;
    private BigInteger operatorId;
    private Integer isAuthorization;
    private Integer isApply;
    private Date gmtCreate;
    private Date gmtModified;
	public AuthorityManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthorityManagement(BigInteger id, BigInteger moduleId, String moduleName, BigInteger parentModule,
			Integer userSequence, Integer isShow, String reserved1, String note, BigInteger ownerId,
			BigInteger operatorId, Integer isAuthorization, Integer isApply, Date gmtCreate, Date gmtModified) {
		super();
		this.id = id;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.parentModule = parentModule;
		this.userSequence = userSequence;
		this.isShow = isShow;
		this.reserved1 = reserved1;
		this.note = note;
		this.ownerId = ownerId;
		this.operatorId = operatorId;
		this.isAuthorization = isAuthorization;
		this.isApply = isApply;
		this.gmtCreate = gmtCreate;
		this.gmtModified = gmtModified;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getModuleId() {
		return moduleId;
	}
	public void setModuleId(BigInteger moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public BigInteger getParentModule() {
		return parentModule;
	}
	public void setParentModule(BigInteger parentModule) {
		this.parentModule = parentModule;
	}
	public Integer getUserSequence() {
		return userSequence;
	}
	public void setUserSequence(Integer userSequence) {
		this.userSequence = userSequence;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
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
	public Integer getIsAuthorization() {
		return isAuthorization;
	}
	public void setIsAuthorization(Integer isAuthorization) {
		this.isAuthorization = isAuthorization;
	}
	public Integer getIsApply() {
		return isApply;
	}
	public void setIsApply(Integer isApply) {
		this.isApply = isApply;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
		result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isApply == null) ? 0 : isApply.hashCode());
		result = prime * result + ((isAuthorization == null) ? 0 : isAuthorization.hashCode());
		result = prime * result + ((isShow == null) ? 0 : isShow.hashCode());
		result = prime * result + ((moduleId == null) ? 0 : moduleId.hashCode());
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((parentModule == null) ? 0 : parentModule.hashCode());
		result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
		result = prime * result + ((userSequence == null) ? 0 : userSequence.hashCode());
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
		AuthorityManagement other = (AuthorityManagement) obj;
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
		if (isApply == null) {
			if (other.isApply != null)
				return false;
		} else if (!isApply.equals(other.isApply))
			return false;
		if (isAuthorization == null) {
			if (other.isAuthorization != null)
				return false;
		} else if (!isAuthorization.equals(other.isAuthorization))
			return false;
		if (isShow == null) {
			if (other.isShow != null)
				return false;
		} else if (!isShow.equals(other.isShow))
			return false;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
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
		if (parentModule == null) {
			if (other.parentModule != null)
				return false;
		} else if (!parentModule.equals(other.parentModule))
			return false;
		if (reserved1 == null) {
			if (other.reserved1 != null)
				return false;
		} else if (!reserved1.equals(other.reserved1))
			return false;
		if (userSequence == null) {
			if (other.userSequence != null)
				return false;
		} else if (!userSequence.equals(other.userSequence))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AuthorityManagement [id=" + id + ", moduleId=" + moduleId + ", moduleName=" + moduleName
				+ ", parentModule=" + parentModule + ", userSequence=" + userSequence + ", isShow=" + isShow
				+ ", reserved1=" + reserved1 + ", note=" + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId
				+ ", isAuthorization=" + isAuthorization + ", isApply=" + isApply + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + "]";
	}
}
