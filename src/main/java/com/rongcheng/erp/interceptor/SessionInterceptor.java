package com.rongcheng.erp.interceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rongcheng.erp.dao.Jxb_UserInfoDao;
import com.rongcheng.erp.entity.UserInfo;

/**
 * 用于session验证的拦截器
 *
 */
public class SessionInterceptor implements HandlerInterceptor {
	@Resource
	private Jxb_UserInfoDao jxb_UserInfoDao;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		HttpSession session = req.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		if (user == null) {
			return redirect(res);
		}
		String accountNum = user.getAccountNum();
		String password = user.getPassword();
		user = new UserInfo();
		user.setAccountNum(accountNum);
		user.setPassword(password);
		user.setAccountStatus(1);
		user = jxb_UserInfoDao.getUserSelective(user);
		if (user == null) {
			return redirect(res);
		}
		// 已经登录过，则继续向下执行。
		session.setAttribute("user", user);
		return true;
	}

	private boolean redirect(HttpServletResponse res) throws IOException {
		res.setStatus(302);
		res.setContentType("text/html; charset=utf-8");
		res.getWriter().println("<script>window.open('/admin.do','_top');</script>");
		return false;
	}

}
