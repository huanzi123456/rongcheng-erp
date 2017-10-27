package com.rongcheng.erp.service.itemCommon;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.Jxb_ItemCommonDAO;
import com.rongcheng.erp.entity.ItemCommonInfo;
@Service("jxb_ItemCommonService")
public class Jxb_ItemCommonServiceImpl implements Jxb_ItemCommonService {

	@Resource
	private Jxb_ItemCommonDAO dao;

	public int countSelective(Map<String,Object> map){
		return dao.countSelective(map);
	}
	
	public List<String> listCategory(BigInteger ownerId) {
		return dao.listCategory(ownerId);
	}

	public List<String> listBrand(BigInteger ownerId) {
		return dao.listBrand(ownerId);
	}

	/**
	* 分页查找商品基本信息，所有参数都可为null。
	*/
	public Object[] listAllBasicInfoByLimtSelective(int page,int rows, String keyword,ItemCommonInfo itemCommonInfo) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("record", itemCommonInfo);
		map.put("keyword", keyword);
		int max_page = (int) Math.ceil(new Double(dao.countSelective(map))/rows);
		if(max_page == 0){
			return null;
		}
		if(page>max_page){
			page = max_page;
		}
		if(page<1){
			page = 1;
		}
		int start = page*rows-rows;
		map.put("start", start);
		map.put("rows", rows);
		return new Object[]{max_page,dao.listAllBasicInfoByLimtSelective(map)};
	}

}
