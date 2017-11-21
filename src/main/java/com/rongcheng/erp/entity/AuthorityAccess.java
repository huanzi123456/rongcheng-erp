package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 
 * @author Wzy
 * 该实体类用于存储京东的token相关数据
 */
public class AuthorityAccess implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 575151203185782187L;
    private BigInteger id;
	private String accountNum;
	private String operationMode;
    private BigInteger shopId;
	private String grantType;
	private String authorizationCode;
	private String authorizationState;
	private String tokenType;
	private String accessToken;
	private String refreshToken;
	private String returnCode;
	private String authorizationLength;
	private String authorizationTime;
	private String userNick;
	private String authorizationScope;
	private String authorizationView;
	private String nativeApplication;
	private String responseType;
	private String appId;
	private String appSecret;
	private String redirectUri;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	private String note;
	private BigInteger ownerId;
	private String operatorId;
	private Timestamp gmtCreate;
	private Timestamp gmtModified;
	
	
	public AuthorityAccess() {
        super();
    }
    public AuthorityAccess(BigInteger id, String accountNum, String operationMode, BigInteger shopId, String grantType,
            String authorizationCode, String authorizationState, String tokenType, String accessToken,
            String refreshToken, String returnCode, String authorizationLength, String authorizationTime,
            String userNick, String authorizationScope, String authorizationView, String nativeApplication,
            String responseType, String appId, String appSecret, String redirectUri, String reserved1, String reserved2,
            String reserved3, String reserved4, String reserved5, String note, BigInteger ownerId, String operatorId,
            Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.accountNum = accountNum;
        this.operationMode = operationMode;
        this.shopId = shopId;
        this.grantType = grantType;
        this.authorizationCode = authorizationCode;
        this.authorizationState = authorizationState;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.returnCode = returnCode;
        this.authorizationLength = authorizationLength;
        this.authorizationTime = authorizationTime;
        this.userNick = userNick;
        this.authorizationScope = authorizationScope;
        this.authorizationView = authorizationView;
        this.nativeApplication = nativeApplication;
        this.responseType = responseType;
        this.appId = appId;
        this.appSecret = appSecret;
        this.redirectUri = redirectUri;
        this.reserved1 = reserved1;
        this.reserved2 = reserved2;
        this.reserved3 = reserved3;
        this.reserved4 = reserved4;
        this.reserved5 = reserved5;
        this.note = note;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
    public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getOperationMode() {
		return operationMode;
	}
	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getAuthorizationState() {
		return authorizationState;
	}
	public void setAuthorizationState(String authorizationState) {
		this.authorizationState = authorizationState;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getAuthorizationLength() {
		return authorizationLength;
	}
	public void setAuthorizationLength(String authorizationLength) {
		this.authorizationLength = authorizationLength;
	}
	public String getAuthorizationTime() {
		return authorizationTime;
	}
	public void setAuthorizationTime(String authorizationTime) {
		this.authorizationTime = authorizationTime;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getAuthorizationScope() {
		return authorizationScope;
	}
	public void setAuthorizationScope(String authorizationScope) {
		this.authorizationScope = authorizationScope;
	}
	public String getAuthorizationView() {
		return authorizationView;
	}
	public void setAuthorizationView(String authorizationView) {
		this.authorizationView = authorizationView;
	}
	public String getNativeApplication() {
		return nativeApplication;
	}
	public void setNativeApplication(String nativeApplication) {
		this.nativeApplication = nativeApplication;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getReserved4() {
		return reserved4;
	}
	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
	public String getReserved5() {
		return reserved5;
	}
	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
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
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
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
	public BigInteger getShopId() {
        return shopId;
    }
    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }
    @Override
    public String toString() {
        return "AuthorityAccess [id=" + id + ", accountNum=" + accountNum + ", operationMode=" + operationMode
                + ", grantType=" + grantType + ", authorizationCode=" + authorizationCode + ", authorizationState="
                + authorizationState + ", tokenType=" + tokenType + ", accessToken=" + accessToken + ", refreshToken="
                + refreshToken + ", returnCode=" + returnCode + ", authorizationLength=" + authorizationLength
                + ", authorizationTime=" + authorizationTime + ", userNick=" + userNick + ", authorizationScope="
                + authorizationScope + ", authorizationView=" + authorizationView + ", nativeApplication="
                + nativeApplication + ", responseType=" + responseType + ", appId=" + appId + ", appSecret=" + appSecret
                + ", redirectUri=" + redirectUri + ", reserved1=" + reserved1 + ", reserved2=" + reserved2
                + ", reserved3=" + reserved3 + ", reserved4=" + reserved4 + ", reserved5=" + reserved5 + ", note="
                + note + ", ownerId=" + ownerId + ", operatorId=" + operatorId + ", gmtCreate=" + gmtCreate
                + ", gmtModified=" + gmtModified + ", shopId=" + shopId + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
        result = prime * result + ((accountNum == null) ? 0 : accountNum.hashCode());
        result = prime * result + ((appId == null) ? 0 : appId.hashCode());
        result = prime * result + ((appSecret == null) ? 0 : appSecret.hashCode());
        result = prime * result + ((authorizationCode == null) ? 0 : authorizationCode.hashCode());
        result = prime * result + ((authorizationLength == null) ? 0 : authorizationLength.hashCode());
        result = prime * result + ((authorizationScope == null) ? 0 : authorizationScope.hashCode());
        result = prime * result + ((authorizationState == null) ? 0 : authorizationState.hashCode());
        result = prime * result + ((authorizationTime == null) ? 0 : authorizationTime.hashCode());
        result = prime * result + ((authorizationView == null) ? 0 : authorizationView.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((grantType == null) ? 0 : grantType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nativeApplication == null) ? 0 : nativeApplication.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((operationMode == null) ? 0 : operationMode.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((redirectUri == null) ? 0 : redirectUri.hashCode());
        result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        result = prime * result + ((reserved2 == null) ? 0 : reserved2.hashCode());
        result = prime * result + ((reserved3 == null) ? 0 : reserved3.hashCode());
        result = prime * result + ((reserved4 == null) ? 0 : reserved4.hashCode());
        result = prime * result + ((reserved5 == null) ? 0 : reserved5.hashCode());
        result = prime * result + ((responseType == null) ? 0 : responseType.hashCode());
        result = prime * result + ((returnCode == null) ? 0 : returnCode.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
        result = prime * result + ((tokenType == null) ? 0 : tokenType.hashCode());
        result = prime * result + ((userNick == null) ? 0 : userNick.hashCode());
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
        AuthorityAccess other = (AuthorityAccess) obj;
        if (accessToken == null) {
            if (other.accessToken != null)
                return false;
        } else if (!accessToken.equals(other.accessToken))
            return false;
        if (accountNum == null) {
            if (other.accountNum != null)
                return false;
        } else if (!accountNum.equals(other.accountNum))
            return false;
        if (appId == null) {
            if (other.appId != null)
                return false;
        } else if (!appId.equals(other.appId))
            return false;
        if (appSecret == null) {
            if (other.appSecret != null)
                return false;
        } else if (!appSecret.equals(other.appSecret))
            return false;
        if (authorizationCode == null) {
            if (other.authorizationCode != null)
                return false;
        } else if (!authorizationCode.equals(other.authorizationCode))
            return false;
        if (authorizationLength == null) {
            if (other.authorizationLength != null)
                return false;
        } else if (!authorizationLength.equals(other.authorizationLength))
            return false;
        if (authorizationScope == null) {
            if (other.authorizationScope != null)
                return false;
        } else if (!authorizationScope.equals(other.authorizationScope))
            return false;
        if (authorizationState == null) {
            if (other.authorizationState != null)
                return false;
        } else if (!authorizationState.equals(other.authorizationState))
            return false;
        if (authorizationTime == null) {
            if (other.authorizationTime != null)
                return false;
        } else if (!authorizationTime.equals(other.authorizationTime))
            return false;
        if (authorizationView == null) {
            if (other.authorizationView != null)
                return false;
        } else if (!authorizationView.equals(other.authorizationView))
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
        if (grantType == null) {
            if (other.grantType != null)
                return false;
        } else if (!grantType.equals(other.grantType))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nativeApplication == null) {
            if (other.nativeApplication != null)
                return false;
        } else if (!nativeApplication.equals(other.nativeApplication))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (operationMode == null) {
            if (other.operationMode != null)
                return false;
        } else if (!operationMode.equals(other.operationMode))
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
        if (redirectUri == null) {
            if (other.redirectUri != null)
                return false;
        } else if (!redirectUri.equals(other.redirectUri))
            return false;
        if (refreshToken == null) {
            if (other.refreshToken != null)
                return false;
        } else if (!refreshToken.equals(other.refreshToken))
            return false;
        if (reserved1 == null) {
            if (other.reserved1 != null)
                return false;
        } else if (!reserved1.equals(other.reserved1))
            return false;
        if (reserved2 == null) {
            if (other.reserved2 != null)
                return false;
        } else if (!reserved2.equals(other.reserved2))
            return false;
        if (reserved3 == null) {
            if (other.reserved3 != null)
                return false;
        } else if (!reserved3.equals(other.reserved3))
            return false;
        if (reserved4 == null) {
            if (other.reserved4 != null)
                return false;
        } else if (!reserved4.equals(other.reserved4))
            return false;
        if (reserved5 == null) {
            if (other.reserved5 != null)
                return false;
        } else if (!reserved5.equals(other.reserved5))
            return false;
        if (responseType == null) {
            if (other.responseType != null)
                return false;
        } else if (!responseType.equals(other.responseType))
            return false;
        if (returnCode == null) {
            if (other.returnCode != null)
                return false;
        } else if (!returnCode.equals(other.returnCode))
            return false;
        if (shopId == null) {
            if (other.shopId != null)
                return false;
        } else if (!shopId.equals(other.shopId))
            return false;
        if (tokenType == null) {
            if (other.tokenType != null)
                return false;
        } else if (!tokenType.equals(other.tokenType))
            return false;
        if (userNick == null) {
            if (other.userNick != null)
                return false;
        } else if (!userNick.equals(other.userNick))
            return false;
        return true;
    }
	
}
