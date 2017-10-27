package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.dao.Jxb_AddressCarrierAllocationDAO;
import com.rongcheng.erp.dao.Jxb_BuyerDAO;
import com.rongcheng.erp.dao.Jxb_OrderDAO;
import com.rongcheng.erp.dao.Jxb_OrderItemLinkDAO;
import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.OrderItemLink;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.itemCommon.Jxb_ItemCommonService;
import com.rongcheng.erp.utils.JsonResult;

/**
 * 创建订单
 * 
 * @author jiml
 *
 */
@Controller
@RequestMapping("/audit")
public class Jxb_AuditAddOrderController {
	// 分页相关（每页多少条）
	@Value("#{config['rows']}")
	private int rows;
	@Resource
	private Jxb_OrderDAO orderDAO;
	@Resource
	private Jxb_OrderItemLinkDAO orderItemLinkDAO;
	@Resource
	private Jxb_ItemCommonService jxb_ItemCommonService;
	@Resource
	private Jxb_BuyerDAO buyerDAO;
	@Resource
	private Jxb_AddressCarrierAllocationDAO jxb_AddressCarrierAllocationDAO;

	/**
	 * 到手动创建订单页面
	 * 
	 * @author jxb
	 * @param session
	 *
	 * @return
	 */
	@RequestMapping("/orderAdd.do")
	public String toOrderAdd(HttpServletRequest request, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		request.setAttribute("brandList", jxb_ItemCommonService.listBrand(ownerId));
		request.setAttribute("categoryList", jxb_ItemCommonService.listCategory(ownerId));
		return "audit/order-add";
	}

	/**
	 * 手动创建订单 创建收货人->创建订单->创建订单商品关联
	 * (返回json数据状态码：0为正常状态，1=数据丢失或为空，2=保存买家信息失败，3=保存订单失败，4=保存订单商品关联出现部分失败或全部失败。)
	 * 
	 * @author jxb
	 *
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/manuallyCreateOrder.do")
	public Object manuallyCreateOrder(HttpServletRequest request, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		BigInteger operatorId = ((UserInfo) (session.getAttribute("user"))).getOperatorId();
		// 商品信息
		String[] idArray = request.getParameterValues("id");
		String[] quantityArray = request.getParameterValues("quantity");
		// 商品数据丢失或为空
		if (idArray == null || quantityArray == null || idArray.length != quantityArray.length) {
			return new JsonResult(1, null, "");
		}
		///////////////////////////////////////////////////////////////

		// 创建买家信息（||收货人信息），并插入数据库
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		BigInteger regionCode = new BigInteger(request.getParameter("area"));
		// 收货人数据丢失或不全
		if (name == null || tel == null || addr == null || name.trim().equals("") || tel.trim().equals("") || addr.trim().equals("")) {
			return new JsonResult(1, null, "");
		}
		String account = request.getParameter("account");
		BuyerInfo buyerInfo = new BuyerInfo();
		buyerInfo.setBuyerAccount(account);
		buyerInfo.setConsigneeName(name);
		buyerInfo.setConsigneeMobile(tel);
		buyerInfo.setConsigneeTel(tel);
		buyerInfo.setRegionId(regionCode);// 收货人地区行政区划代码
		buyerInfo.setUserAddress(addr);
		buyerInfo.setGmtCreate(new Timestamp(System.currentTimeMillis()));
		buyerInfo.setOwnerId(ownerId);
		buyerInfo.setOperatorId(operatorId);
		buyerInfo.setAuthorized(false);// 是否授权
		try {
			buyerDAO.insertSelective(buyerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(2, null, "");
		}
		BigInteger buyerId = buyerInfo.getId();

		//////////////////////////////////////////////////////////////////

		// 创建线下订单对象，并插入数据库
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		BigInteger bi_0 = new BigInteger("0");
		// 根据地区查预设快递
		BigInteger carrierId = jxb_AddressCarrierAllocationDAO.getCarrierIdByRegionCode(regionCode, ownerId);

		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setErpOrderNum("E" + Calendar.SECOND);
		orderInfo.setPlatformBuyerId(buyerId);// 买家id
		orderInfo.setPlatformOrderId(bi_0);// 平台（来源）订单ID
		orderInfo.setPlatformId(bi_0);// 来源（平台）编码，线下订单为0
		orderInfo.setShopId(bi_0);// 店铺ID，线下为0
		orderInfo.setOrderStatus(2);// 线下订单默认为已付款状态
		orderInfo.setOnlineOrder(false);// 是否线上订单
		orderInfo.setSellerWord(request.getParameter("sellerWord"));
		orderInfo.setCarrierId(carrierId);
		orderInfo.setOrderCreate(nowTime);
		orderInfo.setOrderPay(nowTime);
		orderInfo.setOrderCreate(nowTime);
		orderInfo.setGmtCreate(nowTime);
		orderInfo.setOwnerId(ownerId);
		orderInfo.setOperatorId(operatorId);
		try {
			orderDAO.insertSelective(orderInfo);
		} catch (Exception e) {
			e.printStackTrace();
			buyerDAO.deleteByPrimaryKey(buyerId);// 删除垃圾数据
			return new JsonResult(3, null, "");
		}
		///////////////////////////////////////////////////////////////////

		// 创建订单商品关联对象集合，并插入数据库
		for (int i = 0; i < idArray.length; i++) {
			OrderItemLink oil = new OrderItemLink();
			oil.seterpOrderId(orderInfo.getId());// 订单id
			oil.setOrderItemId(new BigInteger(idArray[i]));// 商品id
			oil.setQuantity(new Integer(quantityArray[i]));
			oil.setGmtCreate(new Timestamp(System.currentTimeMillis()));
			oil.setOwnerId(ownerId);
			oil.setOperatorId(operatorId);
			oil.setAuthorized(true);// 是否授权
			try {
				orderItemLinkDAO.insertSelective(oil);
			} catch (Exception e) {
				e.printStackTrace();
				// buyerDAO.deleteByPrimaryKey(bi);//删除垃圾数据
				// orderDAO.deleteByPrimaryKey(bi);//删除垃圾数据
				// orderItemLinkDAO.deleteAllByOrderId(bi,ownerId);//删除垃圾数据
				return new JsonResult(4, null, "");
			}
			// oilList.add(oil);
		}
		return new JsonResult();
	}

	/**
	 * 分页条件查询商品基本信息，包含页码，关键字，以及所有商品属性。所有参数均可为null。
	 * 
	 * @author jxb
	 * @param session
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/listItemBasicInfoSelective.do")
	public Object listItemBasicInfoByKeyword(HttpServletRequest request, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		String page = request.getParameter("page");
		String brand = request.getParameter("brand");
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");
		if (brand != null && brand.trim().equals("")) {
			brand = null;
		}
		if (category != null && category.trim().equals("")) {
			category = null;
		}
		if (keyword != null && keyword.trim().equals("")) {
			keyword = null;
		}
		try {
			new Integer(page);
		} catch (Exception e) {
			page = "1";
		}
		ItemCommonInfo itemCommonInfo = new ItemCommonInfo();
		itemCommonInfo.setOwnerId(ownerId);
		itemCommonInfo.setBrand(brand);
		itemCommonInfo.setCategory(category);
		Object[] o = jxb_ItemCommonService.listAllBasicInfoByLimtSelective(new Integer(page), rows, keyword, itemCommonInfo);
		JsonResult jr = new JsonResult();
		if (o == null) {
			return jr;
		}
		jr.setData(o[1]);// 绑定数据
		jr.setMessage(o[0] + "");// 设置最大页
		return jr;
	}

}
