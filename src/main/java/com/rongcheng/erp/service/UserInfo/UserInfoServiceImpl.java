package com.rongcheng.erp.service.UserInfo;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.UserInfoDao;
import com.rongcheng.erp.entity.UserInfo;
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	@Resource
	private UserInfoDao dao;
	public int modifyUserInfo(BigInteger id, String name, String accountNum, String password, String telephone) {
		UserInfo ui=dao.findByUserInfoId(id);
		ui.setName(name);
		ui.setAccountNum(accountNum);
		ui.setPassword(password);
		ui.setTelephone(telephone);
		int n=dao.modifyUserInfo(ui);
		return n;
	}

	public int addUserInfo(String name, String accountNum, String password, String telephone) {
		UserInfo ui = new UserInfo();
		ui.setName(name);
		ui.setAccountNum(accountNum);
		ui.setPassword(password);
		ui.setTelephone(telephone);
		int n=dao.addUserInfo(ui);
		return n;
	}

	public int delUserInfo(BigInteger id) {
		int n=dao.delUserInfo(id);
		return n;
	}

	public List<UserInfo> findUserInfoByPage(int start, int rows) {
		List<UserInfo> list= dao.findUserInfoByPage(start, rows);
		return list;
	}

	public String findUserInfoCount() {
		String s=dao.findUserInfoCount();
		return s;
	}

	public UserInfo findByUserInfoId(BigInteger id) {
		UserInfo ui =dao.findByUserInfoId(id);
		return ui;
	}

	public int modifyUserInfoStatus(BigInteger id, Integer accountStatus) {
		UserInfo ui=dao.findByUserInfoId(id);
		ui.setAccountStatus(accountStatus);
		int n=dao.modifyUserInfo(ui);
		return n;
	}

	public void regist(String accountNum, String password, String email, String telephone) {
		UserInfo ui = new UserInfo();
		ui.setAccountNum(accountNum);
		ui.setPassword(password);
		ui.setAccountEmail(email);
		ui.setTelephone(telephone);
		dao.addUserInfo(ui);
	}

	public UserInfo checkLogin(String accountNum, String password, String inputImgCode, String imgCode) throws RuntimeException {
		if (inputImgCode == null || inputImgCode == "" || !inputImgCode.equalsIgnoreCase(imgCode)) {
			throw new RuntimeException("imgCodeError");
		}

		UserInfo ui = dao.getUserByAccountNum(accountNum);
		if (ui == null) {
			// 找不到符合条件的记录, 抛出一个应用异常。
			throw new RuntimeException("accountError");
		}
		if (!ui.getPassword().equals(password)) {
			// 用户提交的密码与数据库保存的密码不一致。
			throw new RuntimeException("passWordError");
		}
		/*if (ui.getAccountStatus()==0) {
			// 用户已被冻结
			throw new RuntimeException("userError");
		}*/

		// 登录验证通过
		return ui;
	}

}
