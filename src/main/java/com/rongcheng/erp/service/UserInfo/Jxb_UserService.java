package com.rongcheng.erp.service.UserInfo;

import com.rongcheng.erp.entity.UserInfo;

public interface Jxb_UserService {
	public UserInfo checkLogin(String accountNum, String password, String yzm, String imgCode) throws RuntimeException;
}
