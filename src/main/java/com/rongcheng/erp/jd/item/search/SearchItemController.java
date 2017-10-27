package com.rongcheng.erp.jd.item.search;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.ware.WareReadService.Page;
import com.rongcheng.erp.dao.Jxb_ItemCommonDAO;
import com.rongcheng.erp.dao.Jxb_ItemEspDAO;
import com.rongcheng.erp.entity.ItemCommonInfo;
import com.rongcheng.erp.entity.ItemEspInfo;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.jd.item.converter.JDItem2BaseEntity;

/**
 * 
 * @author jxb
 *
 */

@Controller
@RequestMapping("/jd")
public class SearchItemController {
	@Value("#{config['accessToken']}")
	private String accessToken;
	@Resource
	Jxb_ItemCommonDAO itemCommonDAO;
	@Resource
	Jxb_ItemEspDAO itemEspDAO;
	@Resource
	SearchItem searchItem;

	@RequestMapping("/item.do")
	public String order(HttpServletRequest req, HttpSession session) throws JdException {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		int page = 1;
		int pageSize = 100;
		Date lastCreateTime = itemCommonDAO.getLastCreateTime(ownerId, new BigInteger("3"));
		System.out.println(lastCreateTime);
		Page itemResult = searchItem.search(accessToken, page, lastCreateTime, pageSize);
		req.setAttribute("page", itemResult);
		Long count = itemResult.getTotalItem();
		while (count > 0) {
			saveItemList(itemResult, ownerId);
			count -= pageSize;
			if (count > 0) {
				page++;
				itemResult = searchItem.search(accessToken, page, lastCreateTime, pageSize);
			}
		}
		return "jd/item";
	}

	@SuppressWarnings("unchecked")
	public int saveItemList(Page itemResult, BigInteger ownerId) {
		Map<String, List<?>> map = JDItem2BaseEntity.parse(itemResult, ownerId);
		List<ItemCommonInfo> itemCommonInfoList = (List<ItemCommonInfo>) map.get("itemCommonInfoList");
		if (itemCommonInfoList == null || itemCommonInfoList.size() < 1) {
			return -1;
		}
		int n = itemCommonDAO.insertListSelective(itemCommonInfoList);
		List<ItemEspInfo> itemEspInfoList = (List<ItemEspInfo>) map.get("itemEspInfoList");
		if (itemEspInfoList == null || itemEspInfoList.size() < 1) {
			return n;
		}
		int m = itemEspDAO.insertListSelective(itemEspInfoList);
		return n - m;
	}
}
