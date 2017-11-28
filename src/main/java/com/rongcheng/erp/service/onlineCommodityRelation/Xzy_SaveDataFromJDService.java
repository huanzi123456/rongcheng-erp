package com.rongcheng.erp.service.onlineCommodityRelation;

import java.math.BigInteger;
import com.jd.open.api.sdk.JdException;
/**
 * 读取京东的商品和店铺信息并保存到platform_erp_link表中
 * @author 薛宗艳
 *
 */
public interface Xzy_SaveDataFromJDService {
    void save(String jdAccessTokens,BigInteger ownerId)throws JdException;
}
