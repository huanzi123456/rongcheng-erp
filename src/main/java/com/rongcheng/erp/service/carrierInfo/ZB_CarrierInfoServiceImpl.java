package com.rongcheng.erp.service.carrierInfo;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.ZB_CarrierInfoDAO;
import com.rongcheng.erp.entity.CarrierInfo;
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
     * @param authorized 是否授权
     * @param ownerId   主账户ID
     * @param operatorId 操作人ID
     * @return List<CarrierInfo> 快递信息集合
     * @author 赵滨
     */
    public List<CarrierInfo> listCarrierInfoAll(Boolean authorized, BigInteger ownerId, BigInteger operatorId) {
        //判断主账号是否存在
        // To Be Continued
        
        //判断操作人是否存在
        // To Be Continued
        
        //判断是否授权
        // To Be Continued
        
        //查找默认快递公司记录
        List<CarrierInfo> list = carrierInfoDAO.listCarrierInfoAll(authorized, new BigInteger("0"));
        return list;
    }

}
