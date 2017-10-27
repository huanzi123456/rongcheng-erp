package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;

import com.rongcheng.erp.entity.PrintTemplate;

public interface Jxb_PrintTemplateDAO {
	List<PrintTemplate> listAll(BigInteger ownerId);
}
