package com.rongcheng.erp.service.ItemCommonInfo;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.ItemCommonInfo;

public interface ItemCommonInfoService {
/*	public ItemCommonInfo findById(Integer id);*/
	public List<ItemCommonInfo> findItemCommonInfoAll();//������
	public int modifyItemCommonInfo(BigInteger id,String shortName);
	public void modifyItemCommonInfos(BigInteger[] id,String[] sort);
	public List<ItemCommonInfo> findItemCommonInfoByPage(int start,int rows);//��ҳ����ItemCommonInfo
	public String findItemCommonInfoCount();//����ItemCommonInfo��¼��������
	}
