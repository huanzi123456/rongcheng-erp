package com.rongcheng.erp.service.UserInfo;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.UserInfo;

public interface UserInfoService {
	public int modifyUserInfo(BigInteger id,String name,String accountNum,String password,String telephone);
	public int addUserInfo(String name,String accountNum,String password,String telephone);
	public int delUserInfo(BigInteger id);
	public List<UserInfo> findUserInfoByPage(int start,int rows);
	public String findUserInfoCount();	
	public UserInfo findByUserInfoId(BigInteger id);
	public int modifyUserInfoStatus(BigInteger id,Integer accountStatus);
	public void regist(String accountNum,String password,String email,String telephone);
	public UserInfo checkLogin(String accountNum, String password, String yzm, String imgCode) throws RuntimeException;
}
