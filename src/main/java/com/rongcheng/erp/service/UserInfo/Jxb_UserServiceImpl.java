package com.rongcheng.erp.service.UserInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.Jxb_UserInfoDao;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.utils.UserUtil;

@Service("jxb_UserService")
public class Jxb_UserServiceImpl implements Jxb_UserService {
	@Resource
	private Jxb_UserInfoDao jxb_UserInfoDao;

	public UserInfo checkLogin(String accountNum, String password, String inputCaptcha, String captcha) throws RuntimeException {
		if (captcha == null || !captcha.equalsIgnoreCase(inputCaptcha)) {
			throw new RuntimeException("验证码错误");
		}
		UserInfo user = new UserInfo();
		user.setAccountNum(accountNum);
		user.setPassword(UserUtil.md5(password));
		try {
			user = jxb_UserInfoDao.getUserSelective(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (user == null) {
			// 账号或密码错误
			throw new RuntimeException("账号或密码错误");
		}

		Integer accountStatus = user.getAccountStatus();
		if (accountStatus == 1) {
			// 登录验证通过
			return user;
		}

		if (accountStatus == null || accountStatus == 0) {
			// 账号冻结
			throw new RuntimeException("账号已被冻结");
		}
		// 账号异常
		throw new RuntimeException("账号异常");

	}

}
