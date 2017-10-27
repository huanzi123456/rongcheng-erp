package com.rongcheng.erp.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.dao.Jxb_AddressCarrierAllocationDAO;
import com.rongcheng.erp.dao.Jxb_PrintTemplateDAO;
import com.rongcheng.erp.entity.AddressCarrierAllocation;
import com.rongcheng.erp.entity.UserInfo;

/**
 * 快递分区
 * 
 * @author JIML
 *
 */
@Controller
public class Jxb_AddressCarrierAllocationController {
	@Resource
	private Jxb_AddressCarrierAllocationDAO jxb_AddressCarrierAllocationDAO;
	@Resource
	private Jxb_PrintTemplateDAO jxb_PrintTemplateDAO;

	@RequestMapping("/expressPartition.do")
	public String tojSort() {
		return "settings/expressPartition";
	}

	@RequestMapping("/loadExpressPartition.do")
	@ResponseBody
	public Object load(HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		return processExpressPartition(jxb_AddressCarrierAllocationDAO.list(ownerId));
	}

	@RequestMapping("/loadAllTemplate.do")
	@ResponseBody
	public Object loadAllTemplate(HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		return jxb_PrintTemplateDAO.listAll(ownerId);
	}

	// 取消特例分区（只会是市或区县级）
	@RequestMapping("/cancelSpecial.do")
	@ResponseBody
	public Object cancelSpecial(int regionCode, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		BigInteger operatorId = ((UserInfo) (session.getAttribute("user"))).getOperatorId();
		int parentRegionCode = 0;
		int regionCodeEnd = 0;
		if (regionCode % 10000 == 0) {// 省级及以上
			return 0;
		} else if (regionCode % 100 == 0) {// 市级
			parentRegionCode = regionCode / 10000 * 10000;
			regionCodeEnd = regionCode / 100 * 100 + 99;
		} else {// 区县级
			parentRegionCode = regionCode / 100 * 100;
			regionCodeEnd = regionCode;
		}
		System.out.println(regionCode + "==" + parentRegionCode);
		return jxb_AddressCarrierAllocationDAO.cancelSpecialByRegionCode(ownerId, regionCode, regionCodeEnd, parentRegionCode, operatorId);
	}

	// 修改快递和模板
	@RequestMapping("modifyCarrierAndReserved1.do")
	@ResponseBody
	public Object modifyCarrierAndReserved1(Integer[] regionCode, Integer carrier, String reserved1, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		BigInteger operatorId = ((UserInfo) (session.getAttribute("user"))).getOperatorId();
		if (reserved1.trim().length() == 0) {
			reserved1 = null;
		}
		int c = 0;
		for (int p : regionCode) {
			int regionCodeEnd = 0;
			if (p % 10000 == 0) {
				regionCodeEnd = p / 10000 * 10000 + 9999;
			} else if (p % 100 == 0) {
				regionCodeEnd = p / 100 * 100 + 99;
			} else {
				regionCodeEnd = p;
			}
			c += jxb_AddressCarrierAllocationDAO.modifyCarrierAndReserved1(ownerId, carrier, reserved1, p, regionCodeEnd, operatorId);
		}
		return c;
	}

	// 修改模板
	@RequestMapping("modifyReserved1.do")
	@ResponseBody
	public Object modifyReserved1(int[] province, String reserved1, HttpSession session) {
		BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
		BigInteger operatorId = ((UserInfo) (session.getAttribute("user"))).getOperatorId();
		if (reserved1.trim().length() == 0) {
			return 0;
		}
		int c = 0;
		for (int p : province) {
			int regionCodeEnd = 0;
			if (p % 10000 == 0) {
				regionCodeEnd = p / 10000 * 10000 + 9999;
			} else if (p % 100 == 0) {
				regionCodeEnd = p / 100 * 100 + 99;
			} else {
				regionCodeEnd = p;
			}
			c += jxb_AddressCarrierAllocationDAO.modifyCarrierAndReserved1(ownerId, null, reserved1, p, regionCodeEnd, operatorId);
		}
		return c;
	}

	// 加工快递分区信息
	public HashMap<String, List<AddressCarrierAllocation>> processExpressPartition(List<AddressCarrierAllocation> expressPartition) {
		List<AddressCarrierAllocation> processedExpressPartition = new ArrayList<AddressCarrierAllocation>();// 包含省及例外情况
		List<AddressCarrierAllocation> distrinctExpressPartition = new ArrayList<AddressCarrierAllocation>();// 例外情况
		List<AddressCarrierAllocation> provinceExpressPartition = new ArrayList<AddressCarrierAllocation>();// 所有省
		List<AddressCarrierAllocation> cityExpressPartition = new ArrayList<AddressCarrierAllocation>();// 所有市
		List<AddressCarrierAllocation> areaExpressPartition = new ArrayList<AddressCarrierAllocation>();// 所有区县
		// 删除数组第一个元素（删除中国的快递分区信息）
		expressPartition.remove(0);
		// 剥离出省、市、区县信息
		for (int i = 0; i < expressPartition.size(); i++) {
			int regionCode = expressPartition.get(i).getRegionCode().intValue();
			if (regionCode % 10000 == 0) {
				provinceExpressPartition.add(expressPartition.get(i));
			} else if (regionCode % 100 == 0) {
				cityExpressPartition.add(expressPartition.get(i));
			} else {
				areaExpressPartition.add(expressPartition.get(i));
			}
		}
		// 生成最终快递分区信息
		for (int i = 0; i < provinceExpressPartition.size(); i++) {
			int provinceRegionCode = provinceExpressPartition.get(i).getRegionCode().intValue();
			int provinceCarrierId = provinceExpressPartition.get(i).getCarrierId().intValue();
			processedExpressPartition.add(provinceExpressPartition.get(i));
			for (int j = 0; j < cityExpressPartition.size(); j++) {
				int cityRegionCode = cityExpressPartition.get(j).getRegionCode().intValue();
				int cityCarrierId = cityExpressPartition.get(j).getCarrierId().intValue();
				boolean chil = cityRegionCode / 10000 * 10000 == provinceRegionCode;
				// 当前市属于当前省
				if (chil) {
					if (cityCarrierId != provinceCarrierId) {
						processedExpressPartition.add(cityExpressPartition.get(j));
						distrinctExpressPartition.add(cityExpressPartition.get(j));
					}
					for (int k = 0; k < areaExpressPartition.size(); k++) {
						int areaRegionCode = areaExpressPartition.get(k).getRegionCode().intValue();
						int areaCarrierId = areaExpressPartition.get(k).getCarrierId().intValue();
						chil = areaRegionCode / 100 * 100 == cityRegionCode;
						if (chil && cityCarrierId != areaCarrierId) {
							processedExpressPartition.add(areaExpressPartition.get(k));
							distrinctExpressPartition.add(areaExpressPartition.get(k));
						}
					}
				}
			}
		}
		HashMap<String, List<AddressCarrierAllocation>> map = new HashMap<String, List<AddressCarrierAllocation>>();
		map.put("provinceExpressPartition", provinceExpressPartition);
		map.put("distrinctExpressPartition", distrinctExpressPartition);
		return map;
	}
}
