package com.rongcheng.erp.service.ItemCommonInfo;

import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.ItemCommonInfoDao;
import com.rongcheng.erp.entity.ItemCommonInfo;

@Service("itemCommonInfoService")
public class ItemCommonInfoServiceImpl implements ItemCommonInfoService{
	@Resource
	private ItemCommonInfoDao dao;
	public List<ItemCommonInfo> findItemCommonInfoAll() {
		List<ItemCommonInfo> list=dao.findItemCommonInfoAll();
		return list;
	}
	public int modifyItemCommonInfo(BigInteger id, String shortName) {
		ItemCommonInfo ici=dao.findById(id);
		ici.setShortName(shortName);
		int n=dao.modifyItemCommonInfo(ici);
		return n;
	}
	public List<ItemCommonInfo> findItemCommonInfoByPage(int start, int rows) {
		List<ItemCommonInfo> list=dao.findItemCommonInfoByPage(start, rows);
		return list;
	}
	public String findItemCommonInfoCount() {
		String p =dao.findItemCommonInfoCount();
		return p;
	}
	public void modifyItemCommonInfos(BigInteger[] id, String[] shortName) {
		
		for(int i=0;i<id.length;i++){
			ItemCommonInfo ici=new ItemCommonInfo();
			ici.setShortName(shortName[0]);
			ici.setId(id[i]);
			dao.modifyItemCommonInfo(ici);
		}
	}
}