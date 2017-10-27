package com.rongcheng.erp.service.printTemplate;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.PrintTemplate;


/**
 * 单据模板表 业务层
 * @author 赵滨
 *
 */
public interface ZB_PrintTemplateService {
    
    /**
     * 根据页数关联查询单据模板
     * @param page 加载的页数
     * @param rows 显示的条数
     * @param authorized 是否授权
     * @param ownerId 主账号ID
     * @param operatorId 操作人ID
     * @param templateType 模版类型
     * @return Map<String, Object> 单据模版和快递公司关联的的集合
     * @author 赵滨
     */
    Map<String, Object> listPrintTemplateByPage(int page, int rows, Boolean authorized, BigInteger ownerId,
                                                BigInteger operatorId, Integer[] templateType);
    
    /**
     * 查询单据模板最大条数
     * @param rows 显示的条数
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @param templateType 模版类型
     * @return row 条数
     * @author 赵滨
     */
    int countPrintTemplate(int rows, Boolean authorized, BigInteger ownerId, BigInteger operatorId,
                           Integer[] templateType);
    
    /**
     * 查询单据模板图片 根据 模版类型（无重复）
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @param templateType 模版类型[]
     * @return  List<PrintTemplate> 单据模版的集合
     * @param preset 是否使用预设模版
     * @author 赵滨
     */
    List<PrintTemplate> listPrintTemplateByType(Boolean authorized, BigInteger ownerId,
                                                BigInteger operatorId, Integer[] templateType, Boolean preset);
    
    /**
     * 移除单据模板
     * @param id
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @return row 删除的行数
     * @author 赵滨
     */
    int removePrintTemplateById(BigInteger id, Boolean authorized, BigInteger ownerId, BigInteger operatorId);
    
    /**
     * 复制并新建单据模板 
     * @param printTemplateId 单据模板id
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @return
     * @author 赵滨
     */
    PrintTemplate copyAddExpressTemplate(BigInteger printTemplateId, Boolean authorized, BigInteger ownerId,
                                         BigInteger operatorId);

    /**
     * 保存 或 修改 单据模板
     * @param map 模版和坐标的集合
     * @param preset 是否预设模版
     * @return state 状态值
     * @author 赵滨
     */
    int saveUpdatePrintTemplate(Map map, Boolean preset);

    /**
     * 保存单据模板
     * @param map 模版和坐标的集合
     * @return state 状态值
     * @author 赵滨
     */
    int savePrintTemplate(Map map);
    
    /**
     * 根据id关联查询单据模板
     * @param authorized 是否授权
     * @param ownerId    主账号id
     * @param operatorId 操作人id
     * @param id    单据模版id
     * @param preset 是否使用预设模版
     * @return  Map<String, Object> 一个单据模版 和 不定个数的字段坐标
     * @author 赵滨
     */
    Map<String, Object> listPrintTemplateById(Boolean authorized, BigInteger ownerId, BigInteger operatorId,
                                              BigInteger id, Boolean preset);
    
    /**
     * 更新单据模板
     * @param map 模版和坐标的集合
     * @return state 状态值
     * @author 赵滨
     */
    int updatePrintTemplate(Map map);
    
    /**
     * 查询模版根据快递公司id
     * @param carrierId 快递公司id
     * @param authorized 是否授权
     * @param ownerId   主账号id
     * @param operatorId 操作人id
     * @return List<PrintTemplate> 模版的集合
     * @author 赵滨
     */
     List<PrintTemplate> listPrintTemplateByCarrierId(BigInteger carrierId, Boolean authorized, BigInteger ownerId,
                                                      BigInteger operatorId);
    
     /**
      * 添加或修改订单的快递单号
      * @param orderInfoId 订单id
      * @param trackingNum 快递单号
      * @param authorized 是否授权
      * @param ownerId   主账号id
      * @param operatorId 操作人id
      * @return OrderInfo 订单
      * @author 赵滨
      */
     OrderInfo addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, Boolean authorized,
                                   BigInteger ownerId, BigInteger operatorId);
     
    /**
     * 打印面单功能
     * @param authorized 是否授权
     * @param ownerId    主账号id
     * @param operatorId 操作人id
     * @param orderInfoId   订单ID[]
     * @param printTemplateId 模版id
     * @param projectPath 项目根目录
     * @return List<OrderInfo> 订单集合
     * @author 赵滨
     */
     List<OrderInfo> printTemplate(Boolean authorized, BigInteger ownerId, BigInteger operatorId,
                                   BigInteger[] orderInfoId, BigInteger printTemplateId, String projectPath);
    
     /**
      * 打印面单功能
      * @param authorized 是否授权
      * @param ownerId    主账号id
      * @param operatorId 操作人id
      * @param orderInfoId   订单ID[]
      * @param printTemplateId 模版id
      * @return Map<String, Object> 返回参数集合
      * @author 赵滨
      */
     Map<String, Object> listPrintAll(Boolean authorized, BigInteger ownerId, BigInteger operatorId,
                                      BigInteger[] orderInfoId, BigInteger printTemplateId);
}
