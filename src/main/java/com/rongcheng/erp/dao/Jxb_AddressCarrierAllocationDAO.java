package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.AddressCarrierAllocation;

public interface Jxb_AddressCarrierAllocationDAO {

	/**
	 * 根据 regionCode 和 ownerId 匹配其快递id
	 * 
	 * @return
	 */
	BigInteger getCarrierIdByRegionCode(BigInteger regionCode, BigInteger ownerId);

	/**
	 * 获取所有记录
	 * 
	 * @return
	 */
	List<AddressCarrierAllocation> list(BigInteger ownerId);

	/**
	 * 更新当前地区及其同快递的子地区
	 * 
	 * @param ownerId
	 * @param operatorId
	 * @param carrier
	 *            快递id
	 * @param reserved1
	 *            快递模板id
	 * @param regionCodeStrat
	 *            要修改的起始地区code（110000或110100）
	 * @param regionCodeEnd
	 *            要修改的终止地区code（119999或110199）
	 * @return
	 */
	Integer modifyCarrierAndReserved1(BigInteger ownerId, Integer carrier, String reserved1, Integer regionCodeStrat, Integer regionCodeEnd, BigInteger operatorId);
	/**
	 * 取消例外情况（修改当前地区和相同快递信息的下级地区的快递信息于上级地区）
	 * 
	 * @param ownerId
	 * @param operatorId
	 * @param regionCodeStrat 要取消的地区
	 * @param regionCodeEnd 要取消的地区的终止范围
	 * @param parentRegionCode 要取消地区的上级地区
	 * @return
	 */
	Integer cancelSpecialByRegionCode(BigInteger ownerId, Integer regionCodeStrat, Integer regionCodeEnd, Integer parentRegionCode, BigInteger operatorId);
}