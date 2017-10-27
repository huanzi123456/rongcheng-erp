package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.ItemCommonInfo;

public interface ItemCommonInfoDao {
	public ItemCommonInfo findById(BigInteger id);
	public List<ItemCommonInfo> findItemCommonInfoAll();//��ѯ�б�
	public int modifyItemCommonInfo(ItemCommonInfo ici);
	
	public List<ItemCommonInfo> findItemCommonInfoByPage(int start,int rows);//��ҳ����ItemCommonInfo
	public String findItemCommonInfoCount();//����ItemCommonInfo��¼��������
	
}
