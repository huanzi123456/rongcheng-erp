package com.rongcheng.erp.dao;

import com.rongcheng.erp.entity.PrintTemplate;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 单据模板表 数据访问层
 * @author 赵滨
 *
 */
public interface ZB_PrintTemplateDAO {
    /**
     * 根据页数查询单据模板
     * @param params 第几条开始, 查找多少条, 是否授权, 主账户ID, 模版类型[]
     * @return List<PrintTemplate>  单据模版的集合
     * @author 赵滨
     */
    List<PrintTemplate> listPrintTemplateByPage(Map<String, Object> params);
    
    /**
     * 保存单据模板
     * @param printTemplate 单据模板
     * @return row 存储的行数
     * @author 赵滨
     */
    int savePrintTemplate(PrintTemplate printTemplate);
    
    /**
     * 移除单据模板
     * @param id
     * @param ownerId   主账号ID
     * @return row 删除的行数
     * @author 赵滨
     */
    int removePrintTemplateById(BigInteger id, BigInteger ownerId);
    
    /**
     * 更新单据模板
     * @param printTemplate
     * @return row 条数
     * @author 赵滨
     */
    int updatePrintTemplate(PrintTemplate printTemplate);
    
    /**
     * 查询单据模板最大条数
     * @param params 是否授权, 主账号ID, 模版类型[]
     * @return row  条数
     * @author 赵滨
     */
    int countPrintTemplate(Map<String, Object> params);
    
    /**
     * 查询单据模板图片 （无重复）
     * @param params 是否授权, 主账号ID, 模版类型[]
     * @return  List<PrintTemplate> 单据模版的集合
     * @author 赵滨
     */
    List<PrintTemplate> listPrintTemplateByType(Map<String, Object> params);
    
    /**
     * 查询单据模板 根据id
     * @param id    单据模板id
     * @param ownerId   主账号ID
     * @return PrintTemplate 单据模板
     * @author 赵滨
     */
    PrintTemplate getPrintTemplateById(BigInteger id, BigInteger ownerId);

    /**
     * 查询单据模版 根据模版名称
     * @param templateName 模版名称
     * @param ownerId 主账号id
     * @return List<PrintTemplate> 模版集合
     * @author 赵滨
     */
    List<PrintTemplate> listPrintTemplateByLikeTemplateName(String templateName, BigInteger ownerId);

    /**
     * 初始模板条数
     * @return
     * @author 赵滨
     */
    int getInitialTemplateIdCount();

    /**
     * 获取最大模板ID
     * @return
     * @author 赵滨
     */
    BigInteger getMaxTemplateId();

    /**
     * 初始化模板
     * @param id 模板ID
     * @param ownerId 主账号ID
     * @param timestamp 创建时间
     * @return
     * @author 赵滨
     */
    int initializationPrintTemplate(BigInteger id, BigInteger ownerId, Timestamp timestamp);

    /**
     * 复制并新建模板
     * @param id 模板ID
     * @param ownerId 主账号ID
     * @param timestamp 创建时间
     * @return
     * @author 赵滨
     */
    int copyAddPrintTemplate(BigInteger id, BigInteger ownerId, Timestamp timestamp);
}
