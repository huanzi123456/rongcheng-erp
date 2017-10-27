package com.rongcheng.erp.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.dao.Jxb_BuyerDAO;
import com.rongcheng.erp.dao.Jxb_OrderDAO;
import com.rongcheng.erp.dao.Jxb_OrderItemLinkDAO;
import com.rongcheng.erp.dao.Jxb_PlatformDAO;
import com.rongcheng.erp.dao.Jxb_ShopDAO;
import com.rongcheng.erp.dto.OrderItemInfo_S;
import com.rongcheng.erp.entity.BuyerInfo;
import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.OrderItemLink;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.service.itemCommon.Jxb_ItemCommonService;
import com.rongcheng.erp.service.order.Jxb_OrderService;
import com.rongcheng.erp.utils.ImgCodeUtil;
import com.rongcheng.erp.utils.JsonResult;

/**
 * 
 * @author jxb
 *
 */

@Controller
@RequestMapping("/audit")
public class Jxb_AuditEditOrderController {
	// 分页相关（每页多少条）
	@Value("#{config['rows']}")
	private int rows;

	@Resource
	private Jxb_PlatformDAO platformdDAO;
	@Resource
	private Jxb_ShopDAO shopDAO;
	@Resource
	private Jxb_OrderDAO dao;
	@Resource
	private Jxb_OrderService jxb_OrderService;

	@Resource
	private Jxb_OrderItemLinkDAO orderItemLinkDAO;
	@Resource
	private Jxb_ItemCommonService jxb_ItemCommonService;
	@Resource
	private Jxb_BuyerDAO buyerDAO;

	@RequestMapping("/orderEdit.do")
	public String toOrderEdit() {
		return "audit/order-edit";
	}

	/**
	 * 订单审核页面条件查询订单基本信息。所有参数均可为null。
	 * 
	 * @author jxb
	 * @param session
	 *
	 * @return
	 * @throws InterruptedException
	 */
	@ResponseBody
	@RequestMapping("/listOrderBasicInfoSelective.do")
	public Object listOrderBasicInfoSelective(OrderItemInfo_S orderItemInfo_S, HttpServletRequest request, HttpSession session) throws InterruptedException {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		String page = request.getParameter("page");
		try {
			new Integer(page);
		} catch (Exception e) {
			page = "1";
		}

		orderItemInfo_S.setOwnerId(ownerId);
		Object[] o = jxb_OrderService.listOrderSelective(orderItemInfo_S, new Integer(page), rows);
		if (o == null) {
			return new JsonResult(0, null, "1");
		}
		return new JsonResult(0, o[1], o[0] + "");
	}

	/**
	 * 批量修改订单状态（单个订单快递修改，批量关闭，批量审核）
	 * 
	 * @author jxb
	 *
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateOrderSelective.do")
	public Object updateOrderSelective(BigInteger[] id, OrderInfo record, HttpServletRequest request, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		BigInteger operatorId = ((UserInfo) (session.getAttribute("user"))).getOperatorId();
		record.setOwnerId(ownerId);
		record.setOperatorId(operatorId);
		Integer orderStatus = record.getOrderStatus();
		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		if (orderStatus != null && orderStatus == 3) {// 如果进行审核通过，则标记为未申请退款。
			record.setRefundClose(0);
			record.setOrderCheck(nowTime);
		}
		record.setGmtModified(nowTime);
		int n = jxb_OrderService.updateByIdSelective(id, record);
		return new JsonResult(0, n, null);
	}

	// 去修改页面（跳转页多两次大量数据表查询）
	@RequestMapping("/orderEditAment.do")
	public String toOrderEditAment(HttpServletRequest request, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		String idStr = request.getParameter("id");
		try {
			new BigInteger(idStr);
		} catch (Exception e) {
			return "audit/order-update";
		}
		OrderItemInfo_S orderItemInfo_S = new OrderItemInfo_S();
		orderItemInfo_S.setOwnerId(ownerId);
		orderItemInfo_S.setId(new BigInteger(idStr));
		Object[] o = jxb_OrderService.listOrderSelective(orderItemInfo_S, 1, 1);
		request.setAttribute("order", o[1]);
		System.out.println(idStr + o[1]);
		request.setAttribute("brandList", jxb_ItemCommonService.listBrand(ownerId));
		request.setAttribute("categoryList", jxb_ItemCommonService.listCategory(ownerId));
		return "audit/order-update";
	}

	/**
	 * 修改单个订单 (返回json数据状态码：0为正常状态，1=数据丢失或为空，2=数据库存储失败。)
	 * 
	 * @author jxb
	 *
	 * @param request
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateOneOrder.do")
	public Object updateOneOrder(HttpServletRequest request, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		BigInteger operatorId = ((UserInfo) (session.getAttribute("user"))).getOperatorId();
		// 订单id
		String orderIdStr = request.getParameter("orderId");
		try {
			new BigInteger(orderIdStr);
		} catch (Exception e) {
			e.printStackTrace();
			// 订单id非数字
			return new JsonResult(1, null, "订单id非数字");
		}
		BigInteger orderId = new BigInteger(orderIdStr);
		// 商品信息
		String[] itemIdArray = request.getParameterValues("itemIds");
		String[] quantityArray = request.getParameterValues("quantity");
		// 商品数据丢失或为空
		if (itemIdArray == null || quantityArray == null || itemIdArray.length != quantityArray.length) {
			return new JsonResult(1, null, "商品数据丢失或为空");
		}
		///////////////////////////////////////////////////////////////

		// 创建买家信息（||收货人信息），并更新数据库
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		String areaCode = request.getParameter("area");
		// 收货人数据丢失或不全
		if (name == null || tel == null || addr == null || name.trim().equals("") || tel.trim().equals("") || addr.trim().equals("")) {
			return new JsonResult(1, null, "收货人数据丢失或不全");
		}
		String account = request.getParameter("account");
		String sellerWord = request.getParameter("sellerWord");
		BuyerInfo buyerInfo = new BuyerInfo();
		buyerInfo.setOwnerId(ownerId);
		buyerInfo.setOperatorId(operatorId);
		buyerInfo.setGmtModified(new Timestamp(System.currentTimeMillis()));

		buyerInfo.setBuyerAccount(account);
		buyerInfo.setConsigneeName(name);
		buyerInfo.setConsigneeMobile(tel);
		buyerInfo.setConsigneeTel(tel);
		buyerInfo.setRegionId(new BigInteger(areaCode));// 收货人地区行政区划代码
		buyerInfo.setUserAddress(addr);
		try {
			BigInteger buyerId = new BigInteger(request.getParameter("buyerId"));
			buyerInfo.setId(buyerId);
			buyerDAO.updateByIdSelective(buyerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(2, null, "买家及收货信息修改失败");
		}

		//////////////////////////////////////////////////////////////////

		// 创建线下订单对象，并跟更新数据库
		OrderInfo orderInfo = new OrderInfo();
		BigInteger[] orderIdArray = { orderId };
		orderInfo.setOwnerId(ownerId);
		orderInfo.setOperatorId(operatorId);
		orderInfo.setGmtModified(new Timestamp(System.currentTimeMillis()));

		orderInfo.setSellerWord(sellerWord);
		try {
			jxb_OrderService.updateByIdSelective(orderIdArray, orderInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(2, null, "订单基本信息修改失败");
		}
		///////////////////////////////////////////////////////////////////

		// 创建订单商品关联对象集合，并更新数据库
		orderItemLinkDAO.deleteAllByOrderId(orderId, ownerId);
		for (int i = 0; i < itemIdArray.length; i++) {
			OrderItemLink oil = new OrderItemLink();
			oil.setOwnerId(ownerId);
			oil.setOperatorId(operatorId);
			oil.setGmtCreate(new Timestamp(System.currentTimeMillis()));
			oil.setGmtModified(new Timestamp(System.currentTimeMillis()));
			oil.setAuthorized(true);// 是否授权

			oil.seterpOrderId(orderId);// 订单id
			oil.setOrderItemId(new BigInteger(itemIdArray[i]));// 商品id
			oil.setQuantity(new Integer(quantityArray[i]));
			try {
				orderItemLinkDAO.insertSelective(oil);
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult(2, null, "商品列表修改失败");
			}
		}
		return new JsonResult();
	}

	@RequestMapping("/createImg.do")
	protected void createImg(HttpServletRequest req, HttpServletResponse res) throws IOException, ScriptException {
		// 生成验证码及图片
		Object[] objs = ImgCodeUtil.createImage();
		// 将验证码存入session
		String imgCode = (String) objs[0];
		HttpSession session = req.getSession();
		session.setAttribute("imgCode", imgCode);
		// 将图片输出给浏览器
		res.setContentType("image/jpeg");
		BufferedImage img = (BufferedImage) objs[1];
		// 该输出流由tomcat创建，目标是浏览器
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "jpeg", os);
		os.close();
	}
}
