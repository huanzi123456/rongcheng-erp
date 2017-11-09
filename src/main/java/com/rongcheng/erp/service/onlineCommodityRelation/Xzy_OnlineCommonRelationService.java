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
	 * 分页查询
	 * @param ownerId
	 * @param page
	 * @return
	 */
	XzyJsonResult commonPage(BigInteger ownerId,Integer page);
	/**
	 * "换"操作中"选择已有"弹出框页面的分页查询
	 * @param ownerId
	 * @param page
	 * @return
	 */
	XzyJsonResult commonPages(BigInteger ownerId,Integer page);
}
