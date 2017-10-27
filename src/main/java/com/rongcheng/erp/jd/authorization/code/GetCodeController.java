package com.rongcheng.erp.jd.authorization.code;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jimlp.util.ResponseUtils;

/**
 * 
 * @author jxb
 *
 */

@Controller
@RequestMapping("/jd")
public class GetCodeController {
	@Value("#{config['appKey']}")
	private String appKey;
	@Value("#{config['appSecret']}")
	private String appSecret;
	@RequestMapping("/code.do")
	public String get(String code, String state, HttpServletRequest request, HttpSession session) throws MalformedURLException, UnsupportedEncodingException, IOException {
		System.out.println(appKey+appSecret);
		String redirectUri = "http://dy.rongcheng-tech.com/jd/code.do";
		String url = "https://oauth.jd.com/oauth/token?grant_type=authorization_code&client_id=" + appKey + "&client_secret=" + appSecret + "&scope=read&redirect_uri=" + redirectUri + "&code=" + code + "&state=1234";
		String accessToken = ResponseUtils.getResponseData(url, "GBK");
		request.setAttribute("accessToken", accessToken);
		return "jd/code";
	}
}
