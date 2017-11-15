package com.rongcheng.erp.controller;

import java.math.BigInteger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jd.open.api.sdk.JdException;
import com.rongcheng.erp.dto.XzyJsonResult;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.onlineCommodityRelation.Xzy_OnlineCommonRelationService;
import com.rongcheng.erp.service.onlineCommodityRelation.Xzy_SaveDataFromJDService;
/**
 * 线上商品对应关系页面
 * @author 薛宗艳
 *
 */
@Controller
@RequestMapping("/onLineCommodity")
public class Xzy_OnLineCommodityRelationController {
	@Resource
	Xzy_SaveDataFromJDService service;//京东的商品店铺数据
	
	@Resource
	Xzy_OnlineCommonRelationService services;
	
	@RequestMapping("/commodityRelation.do")
    public String toCommodityRelation(HttpSession session) throws JdException{
		//service.save(session);//京东的商品及店铺信息导入
    	return "settings/commodityRelation";
    }
	/**
	 * 1.线上商品对应关系页面的分页查询
	 * @param page:当前页
	 * @return
	 */
	@RequestMapping("/commonPage.do")
	@ResponseBody
	public XzyJsonResult commonPage(Integer page,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		BigInteger ownerId = user.getOwnerId();
		XzyJsonResult result = services.commonPage(ownerId, page);
		return result;
	}
	/**
	 * 2."换"操作中"选择已有"弹出框页面的分页查询
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("/commonPages.do")
	@ResponseBody
	public XzyJsonResult commonPages(String inputs,Integer page,BigInteger currentOwnerId){
		XzyJsonResult result = null;
		if(inputs == ""){//弹出框分页
			result = services.commonPages(currentOwnerId, page);
		}else{//关键词分页
			result = services.likeSele(inputs, currentOwnerId,page);
		}
		return result;
	}
	/**
	 * 3."换"操作中"选择已有"弹出框页面的关键字查询
	 * @param inputs
	 * @param currentOwnerId
	 * @return
	 */
//	@RequestMapping("/changeRelation.do")
//	@ResponseBody
//	public XzyJsonResult changeRelation(String inputs,BigInteger currentOwnerId,Integer page){	
//		XzyJsonResult result = services.likeSele(inputs, currentOwnerId,page);
//		return result;
//	}
}
