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
	 * @param page:当前页
	 * @return
	 */
	XzyJsonResult commonPage(BigInteger ownerId,Integer page);
	/**
	 * "换"弹出框中"选择已有"页面的分页查询
	 * @param ownerId
	 * @param page:当前页
	 * @return
	 */
	XzyJsonResult commonPages(BigInteger ownerId,Integer page);
	/**
	 * "换"弹出框中"选择已有"页面的关键字查询
	 * @param str     :关键字
	 * @param ownerId
	 * @param page    :当前页
	 * @return
	 */
	XzyJsonResult likeSele(String str,BigInteger ownerId,Integer page);
	/**
	 * "换"弹出框中"选择已有"页面的保存按钮
	 * @param currentOwnerId     :ownerId
	 * @param common_id          :系统商品信息表1的id
	 * @param platformErpLinkId  :店铺商品-erp系统商品对应关系关联表的id
	 * @return
	 */
	XzyJsonResult modifyLinkInfo(BigInteger currentOwnerId,BigInteger common_id,BigInteger platformErpLinkId);
	/**
	 * 批量维护对应关系弹出框页面的保存按钮
	 * @param currentOwnerId :ownerID
	 * @param obj            :店铺商品-erp系统商品对应关系关联表的(id+itemId)字符串
	 * @return
	 */
	XzyJsonResult modifyLinkInfos(BigInteger currentOwnerId,String obj);
}
