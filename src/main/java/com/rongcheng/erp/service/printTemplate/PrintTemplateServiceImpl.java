package com.rongcheng.erp.service.printTemplate;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rongcheng.erp.dao.PrintTemplateDao;
import com.rongcheng.erp.entity.PrintTemplate;

@Service("lsxPrintTemplateService")
public class PrintTemplateServiceImpl implements PrintTemplateService {
	@Resource
	private PrintTemplateDao dao;

	public List<PrintTemplate> findtemplateType(BigInteger carrierId) {
		List<PrintTemplate> pt = dao.findtemplateType(carrierId);
		return pt;
	}
}
