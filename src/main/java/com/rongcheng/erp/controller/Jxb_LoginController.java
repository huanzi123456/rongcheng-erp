package com.rongcheng.erp.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.UserInfo.Jxb_UserService;
import com.rongcheng.erp.utils.ImgCodeUtil;
import com.rongcheng.erp.utils.JsonResult;

@Controller
public class Jxb_LoginController {

	@Resource
	private Jxb_UserService jxb_UserService;

	@RequestMapping("/admin.do")
	public String toLogin(HttpSession session) {
		session.removeAttribute("user");
		return "login/login";
	}

	@RequestMapping("/sign.do")
	public String toSign() {
		return "login/sign";
	}

	@RequestMapping("/index.do")
	public String toIndex() {
		return "login/index";
	}

	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(String accountNum, String password, String yzm, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
			String captcha = (String) session.getAttribute("captcha");
			UserInfo user = jxb_UserService.checkLogin(accountNum, password, yzm, captcha);
			// 登录成功，绑订数据到session。
			session.setAttribute("user", user);
			return new JsonResult(0);
		} catch (RuntimeException e) {
			return new JsonResult(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult("系统异常");
		}
	}

	@RequestMapping("/createImg.do")
	protected void createImg(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		// 生成验证码及图片
		Object[] objs = ImgCodeUtil.createImage();
		// 将验证码存入session
		String captcha = (String) objs[0];
		session.setAttribute("captcha", captcha);
		// 将图片输出给浏览器
		res.setContentType("image/jpeg");
		// res.setHeader("Content-Type","image/jpeg");
		BufferedImage img = (BufferedImage) objs[1];
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			ImageIO.write(img, "jpeg", os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
