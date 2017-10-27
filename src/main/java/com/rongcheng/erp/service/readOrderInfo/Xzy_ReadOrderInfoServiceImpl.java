package com.rongcheng.erp.service.readOrderInfo;

import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rongcheng.erp.dao.Xzy_ReadOrderInfoDao;
import com.rongcheng.erp.dto.XzyOrderInfo;
/**
 * 
 * @author 薛宗艳
 * 建立与物流软件的链接
 */
@Service
public class Xzy_ReadOrderInfoServiceImpl implements Xzy_ReadOrderInfoService{
    @Resource
    Xzy_ReadOrderInfoDao dao;
	public List<XzyOrderInfo> urlService(BigInteger orderId) {
		List<XzyOrderInfo> oi = dao.selectOrderInfo(orderId);		 
		return oi;
	}

}
