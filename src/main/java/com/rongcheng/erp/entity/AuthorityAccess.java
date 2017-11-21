package com.rongcheng.erp.entity;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 
 * @author Wzy
 * 该实体类用于存储京东的token相关数据
 */
public class AuthorityAccess {

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
	
}
