package com.rongcheng.erp.service.printTemplate;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.PrintTemplate;

public interface PrintTemplateService {
	public List<PrintTemplate> findtemplateType(BigInteger carrierId);// 查找面单
}
