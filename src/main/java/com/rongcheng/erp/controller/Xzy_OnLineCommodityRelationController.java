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
	 * 线上商品对应关系页面的分页查询
	 * 线上商品对应关系页面的查询按钮
	 * @param commonState      :状态
	 * @param platformShopId   :店铺
	 * @param onlineInfo       :线上商品编号/线上商品名
	 * @param systemInof       :系统商品编号/系统商品名
	 * @param page             :当前页
	 * @param request
	 * @return
	 */
	@RequestMapping("/commonPage.do")
	@ResponseBody
	public XzyJsonResult commonPage(String commonState,String platformShopId,String onlineInfo,String systemInof,Integer page,HttpServletRequest request){
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		BigInteger ownerId = user.getOwnerId();
		XzyJsonResult result;
		if(commonState == "" && platformShopId == "" && onlineInfo == "" && systemInof == ""){
			//无条件的分页查询
			result = services.commonPage(ownerId, page);
		}else{
			//四个条件的分页查询(查询按钮)
			result = services.selectButtons(commonState, platformShopId, onlineInfo, systemInof, ownerId, page);
		}
		return result;
	}
	/**
	 * "换"弹出框中"选择已有"页面的分页查询
	 * 换"弹出框中"选择已有"页面的关键字查询
	 * @param inputs           :关键字
	 * @param page             :当前页
	 * @param currentOwnerId   :ownerId
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
	 * "换"弹出框中"选择已有"页面的保存按钮
	 * @param currentOwnerId     :ownerId
	 * @param common_id          :系统商品信息表1的id
	 * @param platformErpLinkId  :店铺商品-erp系统商品对应关系关联表的id
	 * @return
	 */
	@RequestMapping("/modifyInfo.do")
	@ResponseBody
	public XzyJsonResult modifyInfo(BigInteger currentOwnerId,BigInteger common_id,BigInteger platformErpLinkId){
		XzyJsonResult result = services.modifyLinkInfo(currentOwnerId, common_id, platformErpLinkId);	
		return result;
	}
	/**
	 * 批量维护对应关系弹出框页面的保存按钮
	 * @param currentOwnerId :ownerID
	 * @param obj            :店铺商品-erp系统商品对应关系关联表的(id+itemId)字符串
	 * @return
	 */
	@RequestMapping("/modifyInfos.do")
	@ResponseBody
	public XzyJsonResult modifyInfos(BigInteger currentOwnerId,String obj){
		XzyJsonResult result = services.modifyLinkInfos(currentOwnerId, obj);
		return result;
	}
	/**
	 * 线上商品对应关系页面的店铺下拉选的加载店铺
	 * @param currentOwnerId
	 * @return
	 */
	@RequestMapping("/addShopInfos.do")
	@ResponseBody
	public XzyJsonResult addShopInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo)session.getAttribute("user");
		BigInteger ownerId = user.getOwnerId();
		XzyJsonResult result = services.addShopInfos(ownerId);
		return result;
	}
}