package com.rongcheng.erp.service.carrierInfo;

import com.rongcheng.erp.dao.ZB_CarrierInfoDAO;
import com.rongcheng.erp.entity.CarrierInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * 单据模板表 业务实现层
 * @author 赵滨
 *
 */
@Service("carrierInfoService")
public class ZB_CarrierInfoServiceImpl implements ZB_CarrierInfoService{

    @Resource
    private ZB_CarrierInfoDAO carrierInfoDAO;
    /**
     * 查询全部快递信息
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    public List<CarrierInfo> listCarrierInfoAll() {
        //查找默认快递公司记录
        List<CarrierInfo> list = carrierInfoDAO.listCarrierInfoAll(new BigInteger("0"));
        return list;
    }

}
