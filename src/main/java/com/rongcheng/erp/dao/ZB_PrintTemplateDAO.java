package com.rongcheng.erp.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.PrintTemplate;
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
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @return row 删除的行数
     * @author 赵滨
     */
    int removePrintTemplateById(BigInteger id, Boolean authorized, BigInteger ownerId);
    
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
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @return PrintTemplate 单据模板
     * @author 赵滨
     */
    PrintTemplate getPrintTemplateById(BigInteger id, Boolean authorized, BigInteger ownerId);
    
    /**
     * 查询单据模版 根据创建时间
     * @param gmtCreate 创建时间
     * @param authorized 是否授权
     * @param ownerId 主账号ID
     * @return PrintTemplate 单据模板
     * @author 赵滨
     */
    PrintTemplate getPrintTemplateByGmtCreate(Timestamp gmtCreate, Boolean authorized, BigInteger ownerId);
    
    /**
     * 查询单据模版 根据模版名称
     * @param templateName 模版名称
     * @param authorized 是否授权
     * @param ownerId 主账号id
     * @return List<PrintTemplate> 模版集合
     * @author 赵滨
     */
    List<PrintTemplate> listPrintTemplateByLikeTemplateName(String templateName, Boolean authorized, BigInteger ownerId);
}
