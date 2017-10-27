package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;
import com.rongcheng.erp.entity.PrintTemplate;

public interface PrintTemplateDao {
	public List<PrintTemplate> findtemplateType(BigInteger carrierId);// 查找面单
}
