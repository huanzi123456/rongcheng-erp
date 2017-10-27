package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.FieldCoordinate;

/**
 * 字段坐标表 数据访问层
 * @author 赵滨
 *
 */
public interface ZB_FieldCoordinateDAO {

    /**
     * 保存字段坐标
     * @param fieldCoordinate 字段坐标
     * @return row 存储的行数
     * @author 赵滨
     */
    int saveFieldCoordinate(FieldCoordinate fieldCoordinate);
    
    /**
     * 查找字段坐标 根据模版id
     * @param templateId 单据模版id
     * @param authorized 是否授权
     * @param ownerId 主账号id
     * @return List<FieldCoordinate> 字段坐标集合
     * @author 赵滨
     */
    List<FieldCoordinate> listFieldCoordinateByTemplateId(BigInteger templateId, Boolean authorized,
                                                          BigInteger ownerId);
    
    /**
     * 查找字段坐标 根据id
     * @param id    坐标id
     * @param authorized 是否授权
     * @param ownerId 主账号id
     * @return 字段坐标集
     * @author 赵滨
     */
    FieldCoordinate getFieldCoordinateById(BigInteger id, Boolean authorized, BigInteger ownerId);
    
    /**
     * 更新字段坐标
     * @param fieldCoordinate 字段坐标
     * @return row 行数
     * @author 赵滨
     */
    int updateFieldCoordinate(FieldCoordinate fieldCoordinate);
    
    /**
     * 移除字段坐标 根据模版id
     * @param templateId 单据模版id
     * @param authorized 是否授权
     * @param ownerId 主账号id
     * @return row 删除行数
     * @author 赵滨
     */
    int removeFieldCoordinateByTemplateId(BigInteger templateId, Boolean authorized, BigInteger ownerId);
    
    /**
     * 移除字段坐标 根据id
     * @param params id[] 是否授权 主账号id
     * @return row 删除行数
     * @author 赵滨
     */
    int removeFieldCoordinateById(Map<String, Object> params);
}
