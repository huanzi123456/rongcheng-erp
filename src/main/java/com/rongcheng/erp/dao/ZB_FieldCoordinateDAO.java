package com.rongcheng.erp.dao;

import com.rongcheng.erp.entity.FieldCoordinate;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
     * @param ownerId 主账号id
     * @return List<FieldCoordinate> 字段坐标集合
     * @author 赵滨
     */
    List<FieldCoordinate> listFieldCoordinateByTemplateId(BigInteger templateId, BigInteger ownerId);
    
    /**
     * 查找字段坐标 根据id
     * @param id    坐标id
     * @param ownerId 主账号id
     * @return 字段坐标集
     * @author 赵滨
     */
    FieldCoordinate getFieldCoordinateById(BigInteger id, BigInteger ownerId);
    
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
     * @param ownerId 主账号id
     * @return row 删除行数
     * @author 赵滨
     */
    int removeFieldCoordinateByTemplateId(BigInteger templateId, BigInteger ownerId);
    
    /**
     * 移除字段坐标 根据id
     * @param params id[] 是否授权 主账号id
     * @return row 删除行数
     * @author 赵滨
     */
    int removeFieldCoordinateById(Map<String, Object> params);

    /**
     * 初始化字段坐标
     * @param id 模板ID
     * @param ownerId 主账号ID
     * @param timestamp 创建时间
     * @return
     * @author 赵滨
     */
    int initializationFieldCoordinate(BigInteger id, BigInteger ownerId, Timestamp timestamp);

    /**
     * 复制并新建字段坐标
     * @param id 模板ID
     * @param ownerId 主账号ID
     * @param timestamp 创建时间
     * @param printTemplateId 原模板ID
     * @return
     * @author 赵滨
     */
    int copyAddFieldCoordinate(BigInteger id, BigInteger ownerId, Timestamp timestamp, BigInteger printTemplateId);
}
