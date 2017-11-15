package com.rongcheng.erp.service.onlineCommodityRelation;

import java.math.BigInteger;
import com.rongcheng.erp.dto.XzyJsonResult;
/**
 * 线上商品对应关系页面
 * @author 薛宗艳
 *
 */
public interface Xzy_OnlineCommonRelationService {
	/**
	 * 线上商品对应关系页面的分页查询
	 * @param ownerId
	 * @param page
	 * @return
	 */
	XzyJsonResult commonPage(BigInteger ownerId,Integer page);
	/**
	 * "换"弹出框中"选择已有"页面的分页查询
	 * @param ownerId
	 * @param page
	 * @return
	 */
	XzyJsonResult commonPages(BigInteger ownerId,Integer page);
	/**
	 * "换"弹出框中"选择已有"页面的模糊查询
	 * @param str
	 * @param ownerId
	 * @return
	 */
	XzyJsonResult likeSele(String str,BigInteger ownerId,Integer page);
	/**
	 * "换"弹出框中"选择已有"页面的保存按钮
	 * @param currentOwnerId
	 * @param common_id
	 * @param platformErpLinkId
	 * @return
	 */
	XzyJsonResult modifyLinkInfo(BigInteger currentOwnerId,BigInteger common_id,BigInteger platformErpLinkId);
}
