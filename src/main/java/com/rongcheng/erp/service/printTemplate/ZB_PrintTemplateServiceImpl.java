package com.rongcheng.erp.service.printTemplate;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.rongcheng.erp.entity.*;
import org.springframework.stereotype.Service;

import com.rongcheng.erp.dao.ZB_CarrierInfoDAO;
import com.rongcheng.erp.dao.ZB_FieldCoordinateDAO;
import com.rongcheng.erp.dao.ZB_PrintDAO;
import com.rongcheng.erp.dao.ZB_PrintTemplateDAO;
import com.rongcheng.erp.utils.PrintTool;

/**
 * 单据模板表 业务实现层
 * @author 赵滨
 *
 */
@Service("printTemplateService")
public class ZB_PrintTemplateServiceImpl implements ZB_PrintTemplateService {

    @Resource
    private ZB_PrintTemplateDAO printTemplateDAO;
    
    @Resource
    private ZB_CarrierInfoDAO carrierInfoDAO;
    
    @Resource
    private ZB_FieldCoordinateDAO fieldCoordinateDAO;
    
    @Resource
    private ZB_PrintDAO printDAO;
    
    /**
     * 根据页数查询单据模板
     * @param page 加载的页数
     * @param rows 显示的条数
     * @param authorized 是否授权
     * @param ownerId 主账号ID
     * @param operatorId 操作人ID
     * @param templateType 模版类型
     * @return List<PrintTemplate> 单据模版的集合
     * @author 赵滨
     */
    public Map<String, Object> listPrintTemplateByPage(int page, int rows, Boolean authorized, BigInteger ownerId,
            BigInteger operatorId, Integer[] templateType) {
        //获取start参数
        int start = (page-1)*rows;
        //定义map
        Map<String, Object> map = new HashMap<>();

        //创建参数集合map
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("rows", rows);
        params.put("authorized", authorized);
        params.put("ownerId", ownerId);
        params.put("templateType", templateType);
        
        //查询
        List<PrintTemplate> printTemplateList = 
                printTemplateDAO.listPrintTemplateByPage(params);
        map.put("printTemplateList", printTemplateList);

        //定义CarrierInfo集合
        List<CarrierInfo> carrierInfoList = new ArrayList<>();
        //遍历printTemplateList
        for (PrintTemplate printTemplate : printTemplateList) {
            //获取CarrierId
            BigInteger carrierId = printTemplate.getCarrierId();
            //根据CarrierId查找CarrierInfo
            CarrierInfo carrierInfo = carrierInfoDAO.getCarrierInfoById(carrierId, authorized, new BigInteger("0"));
            //CarrierInfoList中添加carrierInfo
            carrierInfoList.add(carrierInfo);
        }
        map.put("carrierInfoList", carrierInfoList);

        //创建参数集合map
        params = new HashMap<>();
        params.put("authorized", authorized);
        params.put("ownerId", ownerId);
        params.put("templateType", templateType);
        //获取条数
        int row = printTemplateDAO.countPrintTemplate(params);
        map.put("maxPage", Math.ceil((double) row / (double) rows));

        return map;
    }
    
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
    public List<PrintTemplate> listPrintTemplateByType(Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId, Integer[] templateType, Boolean preset) {
        //创建参数集合map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authorized", authorized);
        map.put("templateType", templateType);
        //如果是预设模版
        if (preset == true) {
            map.put("ownerId", new BigInteger("0"));
        } else {
            map.put("ownerId", ownerId);
        }
        //查询
        List<PrintTemplate> list = 
                printTemplateDAO.listPrintTemplateByType(map);
        return list;
    }
    
    /**
     * 移除单据模板
     * @param templateId 模版id
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @return row 删除的行数
     * @author 赵滨
     */
    public int removePrintTemplateById(BigInteger templateId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //判断主账号是否存在
        // To Be Continued
        
        //判断操作人是否存在
        // To Be Continued
        
        //判断是否授权
        // To Be Continued
        
        //查询单据模板是否存在
        PrintTemplate printTemplate = printTemplateDAO.getPrintTemplateById(templateId, authorized, ownerId);
        if (printTemplate != null) {
            //删除
            int row = printTemplateDAO.removePrintTemplateById(templateId, authorized, ownerId);
            fieldCoordinateDAO.removeFieldCoordinateByTemplateId(templateId, authorized, ownerId);
            return row;
        }
        return 0;
    }
    
    /**
     * 复制并新建单据模板 
     * @param printTemplateId 单据模板id
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @return
     * @author 赵滨
     */
    public PrintTemplate copyAddExpressTemplate(BigInteger printTemplateId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //判断主账号是否存在
        // To Be Continued
        
        //判断操作人是否存在
        // To Be Continued
        
        //判断是否授权
        // To Be Continued
        
        //记录创建时间
        Timestamp gmtCreate = new Timestamp(System.currentTimeMillis()); 
        
        //第一部分  复制模版
        
        //查询需要复制的模版
        PrintTemplate oldPrintTemplate = printTemplateDAO.getPrintTemplateById(printTemplateId, authorized, ownerId);
        
        String templateName = oldPrintTemplate.getTemplateName();
        
        //如果该模版本身就是副本  例如：某某某 - 副本(2)
        if (templateName.matches("^.+\\s-\\s副本\\(\\d+\\)$")) {
            //去掉尾部  例如：某某某
            templateName = templateName.replaceAll("\\s-\\s副本\\(\\d+\\)$", "");
        }
        
        //查询相同名称模版 的 副本
        List<PrintTemplate> printTemplateList = 
                printTemplateDAO.listPrintTemplateByLikeTemplateName(templateName, authorized, ownerId);
        
        //如果存在该模版副本
        if (printTemplateList.size() != 0) {
            
            //定义副本数字数组
            Integer[] numArray = new Integer[printTemplateList.size()];
            
            //遍历副本集合
            for (int i = 0; i < printTemplateList.size(); i++) {
                //获取对象
                PrintTemplate printTemplate = printTemplateList.get(i);
                
                //分组
                String[] strArray = printTemplate.getTemplateName().split("^.+\\s-\\s副本\\(");
                
                //获取每个数字
                String strNum = strArray[strArray.length-1].replaceAll("\\)", "");
                
                //装到数字数组中
                numArray[i] = new Integer(strNum);
            }
            
            //排序
            Arrays.sort(numArray);
            //重命名
            templateName = templateName+" - 副本("+(numArray[numArray.length-1]+1)+")";
            
        //如果该模版没有副本
        } else {
            //重命名
            templateName = templateName+" - 副本(1)";
        }
        
        //如果字符超过
        if (templateName.length() > 20) {
            return null;
        }
        
        //定义新建模版
        PrintTemplate newPrintTemplate = new PrintTemplate(null, oldPrintTemplate.getCarrierId(), 
                oldPrintTemplate.getCarrierName(), templateName, 
                oldPrintTemplate.getTemplateImage(), oldPrintTemplate.getTemplateType(), 
                oldPrintTemplate.getElectronicWaybill(), oldPrintTemplate.getTemplateWidth(), 
                oldPrintTemplate.getTemplateHeight(), oldPrintTemplate.getPrintChoice(), 
                oldPrintTemplate.getReserved1(), oldPrintTemplate.getNote(), 
                ownerId, operatorId, authorized, gmtCreate, null);
        
        //保存新建模版
        printTemplateDAO.savePrintTemplate(newPrintTemplate);
        
        //获取新建模版id
        BigInteger newPrintTemplateId = 
                printTemplateDAO.getPrintTemplateByGmtCreate(gmtCreate, authorized, ownerId).getId();
        
        newPrintTemplate.setId(newPrintTemplateId);
        
        //第二部分  复制坐标
        
        //查询需要复制的坐标
        List<FieldCoordinate> fieldCoordinateList = 
                fieldCoordinateDAO.listFieldCoordinateByTemplateId(printTemplateId, authorized, ownerId);
        
        //遍历坐标
        for (int i = 0; i < fieldCoordinateList.size(); i++) {
            
            //创建新建坐标
            FieldCoordinate fieldCoordinate = new FieldCoordinate(null, newPrintTemplateId, 
                    fieldCoordinateList.get(i).getFieldName(), fieldCoordinateList.get(i).getCoordinateType(), 
                    fieldCoordinateList.get(i).getxCoordinate(), fieldCoordinateList.get(i).getyCoordinate(), 
                    fieldCoordinateList.get(i).getxLength(), fieldCoordinateList.get(i).getyLength(),
                    fieldCoordinateList.get(i).getReserved1(), fieldCoordinateList.get(i).getNote(),
                    ownerId, operatorId, authorized, gmtCreate, null);
                    
            //保存新建坐标
            fieldCoordinateDAO.saveFieldCoordinate(fieldCoordinate);
        }
        return newPrintTemplate;
    }

    /**
     * 保存 或 修改 单据模板
     * @param map 模版和坐标的集合
     * @param preset 是否预设模版
     * @return state 状态值
     * @author 赵滨
     */
    public int saveUpdatePrintTemplate(Map map, Boolean preset) {

        //判断 只有不为空才能继续运行  否则直接报错
        if (map.size() != 0 || map != null || !map.isEmpty()) {

            //判断是 保存 还是 修改
            if (preset == true) {
                return savePrintTemplate(map);
            } else {
                return updatePrintTemplate(map);
            }

        }
        return 0;
    }

    /**
     * 保存单据模板
     * @param map 模版和坐标的集合
     * @return state 状态值
     * @author 赵滨
     */
    public int savePrintTemplate(Map map) {
        
        //判断 只有不为空才能继续运行  否则直接报错
        if (map.size() != 0 || map != null || !map.isEmpty()) {
            
            //记录创建时间
            Timestamp gmtCreate = new Timestamp(System.currentTimeMillis()); 
            
            //定义参数
            //模版id
            BigInteger printTemplateId = null;
            
            //是否授权
            Boolean authorized = null;
            
            //主账号
            BigInteger ownerId = null;
            
            //操作人
            BigInteger operatorId = null;
            
            //模板名称
            String templateName = null;
            
            //模板宽
            Integer templateWidth = null;
            
            //模板高 
            Integer templateHeight = null;
            
            //打印机选择
            String printChoice = null;
            
            //遍历    用于第一步 创建map前半部分参数
            for (Object key : map.keySet()) {
                //获取值
                for (String value : (String[]) map.get(key)) {

                    //匹配键
                    if ("data[printTemplateId]".equals(key)) {
                        //模版id
                        printTemplateId = new BigInteger(value);
                        
                    } else if ("data[ownerId]".equals(key)) {
                        //主账号
                        ownerId = new BigInteger(value);
                        
                    } else if ("data[templateName]".equals(key)) {
                        //模板名称
                        templateName = value;
                        
                    } else if ("data[templateWidth]".equals(key)) {
                        //模板宽
                        templateWidth = new Integer(value);

                    } else if ("data[templateHeight]".equals(key)) {
                        //模板高
                        templateHeight = new Integer(value);

                    } else if ("data[printChoice]".equals(key)) {
                        //打印机选择
                        printChoice = value;
                        
                    }
                }
            }
            
            //判断主账号是否存在
            // To Be Continued
            
            //判断操作人是否存在
            // To Be Continued
            
            //判断是否授权
            // To Be Continued
            
            //查找预设模版实体对象    用于第一步 创建map前半部分参数
            PrintTemplate printTemplateMode = 
                    printTemplateDAO.getPrintTemplateById(printTemplateId, authorized, new BigInteger("0"));

            //快递公司ID
            BigInteger carrierId = printTemplateMode.getCarrierId();
            
            //快递公司名称
            String carrierName = printTemplateMode.getCarrierName();
            
            //模板图片
            String templateImage = printTemplateMode.getTemplateImage();
            
            //是否电子面单
            Boolean electronicWaybill = printTemplateMode.getElectronicWaybill();
            
            //模板类型
            Integer templateType = printTemplateMode.getTemplateType();
            
            //创建实体对象    用于第一步 创建map前半部分参数
            PrintTemplate printTemplate = new PrintTemplate(null, carrierId, carrierName, templateName, 
                    templateImage, templateType, electronicWaybill, templateWidth, templateHeight, printChoice, 
                    null, null, ownerId, operatorId, authorized, gmtCreate, null);
            
            //保存模版对象    第一部分结束
            int row = printTemplateDAO.savePrintTemplate(printTemplate);
            
            //获取所创建的模版的id
            BigInteger id = printTemplateDAO.getPrintTemplateByGmtCreate(gmtCreate, authorized, ownerId).getId();
            
            //遍历 用于第二步 创建map后半部分参数
            for (Object key : map.keySet()) {
                //获取键
                /*System.out.println(key);*/
                //获取值
                for (String value : (String[]) map.get(key)) {
                    //匹配键
                    if ("data[fieldCoordinate]".equals(((String)key).replaceAll("(\\d)+", ""))) {
                        //创建实体对象（字段坐标表）
                        FieldCoordinate fieldCoordinate = new FieldCoordinate();
                        
                        //模板ID  
                        fieldCoordinate.setTemplateId(id);
                        
                        //坐标类型
                        fieldCoordinate.setCoordinateType(templateType);
                        
                        //分割参数  例如：sender_phonebox,267px,211px
                        String[] values = value.split("(\\s)*,(\\s)*");
                        
                        //字段英文命名
                        fieldCoordinate.setFieldName(values[0].toString());
                            
                        //字段x坐标
                        fieldCoordinate.setxCoordinate
                                (Integer.valueOf(values[1].replaceAll("[a-z]+", "")));
                        
                        //字段y坐标
                        fieldCoordinate.setyCoordinate
                                (Integer.valueOf(values[2].replaceAll("[a-z]+", "")));

                        //字段x长度
                        fieldCoordinate.setxLength
                                (Integer.valueOf(values[3].replaceAll("[a-z]+", "")));

                        //字段y长度
                        fieldCoordinate.setyLength
                                (Integer.valueOf(values[4].replaceAll("[a-z]+", "")));
                                                    
                        //用户主账户ID
                        fieldCoordinate.setOwnerId(ownerId);
                        
                        //操作人
                        fieldCoordinate.setOperatorId(operatorId);
                        
                        //是否授权
                        fieldCoordinate.setAuthorized(authorized);
                        
                        //记录创建时间
                        fieldCoordinate.setGmtCreate(gmtCreate);

                        //保存字段坐标对象  第二部分结束
                        fieldCoordinateDAO.saveFieldCoordinate(fieldCoordinate);
                        /*System.out.println(fieldCoordinate);*/
                    } 
                }
            }
            return row;
        }

        return 0;
    }
    
    /**
     * 根据id关联查询单据模板
     * @param authorized 是否授权
     * @param ownerId    主账号id
     * @param operatorId 操作人id
     * @param id    单据模版id
     * @return  Map<String, Object> 一个单据模版 和 不定个数的字段坐标
     * @author 赵滨
     */
    public Map<String, Object> listPrintTemplateById(Boolean authorized, BigInteger ownerId, BigInteger operatorId, 
            BigInteger id, Boolean preset) {
        //判断主账号是否存在
        // To Be Continued
        
        //判断操作人是否存在
        // To Be Continued
        
        //判断是否授权
        // To Be Continued
        
        //创建返回值对象map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //如果是预设模版
        if (preset == true) {
            //查询模版
            PrintTemplate PrintTemplate = printTemplateDAO.getPrintTemplateById(id, authorized, new BigInteger("0"));
            map.put("PrintTemplate", PrintTemplate);
            //查询字段坐标
            List<FieldCoordinate> fieldCoordinateList =
                    fieldCoordinateDAO.listFieldCoordinateByTemplateId(id, authorized, ownerId);
            map.put("fieldCoordinateList", fieldCoordinateList);
        } else {
            //查询模版
            PrintTemplate PrintTemplate = printTemplateDAO.getPrintTemplateById(id, authorized, ownerId);
            map.put("PrintTemplate", PrintTemplate);
            //查询字段坐标
            List<FieldCoordinate> fieldCoordinateList = 
                    fieldCoordinateDAO.listFieldCoordinateByTemplateId(id, authorized, ownerId);
            map.put("fieldCoordinateList", fieldCoordinateList);
        }
        return map;
    }
    
    /**
     * 更新单据模板
     * @param map 模版和坐标的集合
     * @return state 状态值
     * @author 赵滨
     */
    public int updatePrintTemplate(Map map) {
        
        //判断 只有不为空才能继续运行  否则直接报错
        if (map.size() != 0 || map != null || !map.isEmpty()) {
            
            //记录修改时间
            Timestamp gmtCreate = new Timestamp(System.currentTimeMillis()); 
            Timestamp gmtModified = gmtCreate;
            
            //定义参数
            //模版id
            BigInteger printTemplateId = null;
            
            //是否授权
            Boolean authorized = null;
            
            //主账号
            BigInteger ownerId = null;
            
            //操作人
            BigInteger operatorId = null;
            
            //模板名称
            String templateName = null;
            
            //模板宽
            Integer templateWidth = null;
            
            //模板高 
            Integer templateHeight = null;
            
            //打印机选择
            String printChoice = null;
            
            //需要删除的id数组
            String fieldCoordinateIdData = null;
            
            //遍历    用于第一步 创建map前半部分参数
            for (Object key : map.keySet()) {
                //获取值
                for (String value : (String[]) map.get(key)) {
                    //匹配键
                    if ("data[printTemplateId]".equals(key)) {
                        //模版id
                        printTemplateId = new BigInteger(value);
                        
                    } else if ("data[authorized]".equals(key)) {
                        //是否授权
                        authorized = new Boolean(value);
                        
                    } else if ("data[ownerId]".equals(key)) {
                        //主账号
                        ownerId = new BigInteger(value);
                        
                    } else if ("data[operatorId]".equals(key)) {
                        //操作人
                        operatorId = new BigInteger(value);
                        
                    }  else if ("data[templateName]".equals(key)) {
                        //模板名称
                        templateName = value;
                        
                    } else if ("data[templateWidth]".equals(key)) {
                        //模板宽
                        templateWidth = new Integer(value);
                        
                    } else if ("data[templateHeight]".equals(key)) {
                        //模板高 
                        templateHeight = new Integer(value);
                        
                    } else if ("data[printChoice]".equals(key)) {
                        //打印机选择
                        printChoice = value;
                        
                    } else if ("data[fieldCoordinateIdData]".equals(key)) {
                        fieldCoordinateIdData = value;
                    }
                }
            }
            //判断主账号是否存在
            // To Be Continued
            
            //判断操作人是否存在
            // To Be Continued
            
            //判断是否授权
            // To Be Continued
            
            //创建实体对象    用于第一步 创建map前半部分参数
            PrintTemplate printTemplate = printTemplateDAO.getPrintTemplateById(printTemplateId, authorized, ownerId);

            int row = 0;
            //如果对象存在
            if (printTemplate != null) {
                //设置
                //操作人
                printTemplate.setOperatorId(operatorId);
                
                //模板名称
                printTemplate.setTemplateName(templateName);
                
                //模板宽
                printTemplate.setTemplateWidth(templateWidth);
                
                //模板高 
                printTemplate.setTemplateHeight(templateHeight);
                
                //打印机选择
                printTemplate.setPrintChoice(printChoice);
                
                //修改时间
                printTemplate.setGmtModified(gmtModified);
                
                //更新对象  第一部分结束
                row = printTemplateDAO.updatePrintTemplate(printTemplate);
                
                //创建删除参数
                Map<String, Object> fieldCoordinateMap = new HashMap<String, Object>();
                fieldCoordinateMap.put("authorized", authorized);
                fieldCoordinateMap.put("ownerId", ownerId);
                //fieldCoordinateIdData转换成数组
                String[] fieldCoordinateIdDel = fieldCoordinateIdData.split("(\\s)*,(\\s)*");
                fieldCoordinateMap.put("fieldCoordinateIdDel", fieldCoordinateIdDel);
                //删除多余字段坐标内容
                fieldCoordinateDAO.removeFieldCoordinateById(fieldCoordinateMap);
            }
            
            
            //获取templateType
            Integer templateType = printTemplate.getTemplateType();
            
            //遍历 用于第二步 创建map后半部分参数
            for (Object key : map.keySet()) {
                //获取值
                for (String value : (String[]) map.get(key)) {
                    //匹配键
                    if ("data[fieldCoordinate]".equals(((String)key).replaceAll("(\\d)+", ""))) {
                        
                        //分割参数  例如：sender_phonebox,267px,211px,2
                        String[] values = value.split("(\\s)*,(\\s)*");
                        
                        //如果不存在id，那么添加内容
                        if (values.length == 5) {
                            
                            //创建实体对象（字段坐标表）
                            FieldCoordinate fieldCoordinate = new FieldCoordinate();
                            
                            //模板ID  
                            fieldCoordinate.setTemplateId(printTemplateId);
                            
                            //坐标类型
                            fieldCoordinate.setCoordinateType(templateType);
                            
                            //字段英文命名
                            fieldCoordinate.setFieldName(values[0].toString());

                            //字段x坐标
                            fieldCoordinate.setxCoordinate
                                    (Integer.valueOf(values[1].replaceAll("[a-z]+", "")));

                            //字段y坐标
                            fieldCoordinate.setyCoordinate
                                    (Integer.valueOf(values[2].replaceAll("[a-z]+", "")));

                            //字段x长度
                            fieldCoordinate.setxLength
                                    (Integer.valueOf(values[3].replaceAll("[a-z]+", "")));

                            //字段y长度
                            fieldCoordinate.setyLength
                                    (Integer.valueOf(values[4].replaceAll("[a-z]+", "")));

                            //用户主账户ID
                            fieldCoordinate.setOwnerId(ownerId);
                            
                            //操作人
                            fieldCoordinate.setOperatorId(operatorId);
                            
                            //是否授权
                            fieldCoordinate.setAuthorized(authorized);
                            
                            //记录创建时间
                            fieldCoordinate.setGmtCreate(gmtCreate);

                            //保存字段坐标对象
                            fieldCoordinateDAO.saveFieldCoordinate(fieldCoordinate);
                            
                        //如果存在id，那么修改内容
                        } else if (values.length == 6) {

                            //获取id
                            BigInteger fieldCoordinateId = new BigInteger(values[5].toString());
                            
                            //获取对象
                            FieldCoordinate fieldCoordinate = 
                                    fieldCoordinateDAO.getFieldCoordinateById(fieldCoordinateId, authorized, ownerId);
                            
                            //字段英文命名
                            fieldCoordinate.setFieldName(values[0].toString());

                            //字段x坐标
                            fieldCoordinate.setxCoordinate
                                    (Integer.valueOf(values[1].replaceAll("[a-z]+", "")));

                            //字段y坐标
                            fieldCoordinate.setyCoordinate
                                    (Integer.valueOf(values[2].replaceAll("[a-z]+", "")));

                            //字段x长度
                            fieldCoordinate.setxLength
                                    (Integer.valueOf(values[3].replaceAll("[a-z]+", "")));

                            //字段y长度
                            fieldCoordinate.setyLength
                                    (Integer.valueOf(values[4].replaceAll("[a-z]+", "")));

                            //操作人
                            fieldCoordinate.setOperatorId(operatorId);
                            
                            //记录修改时间
                            fieldCoordinate.setGmtModified(gmtModified);

                            //更新对象  第二部分结束
                            fieldCoordinateDAO.updateFieldCoordinate(fieldCoordinate);
                        }
                    } 
                }
            }
            return row;
        }

        return 0;
    }
    
    /**
     * 查询模版根据快递公司id
     * @param carrierId 快递公司id
     * @param authorized 是否授权
     * @param ownerId   主账号id
     * @param operatorId 操作人id
     * @return List<PrintTemplate> 模版的集合
     * @author 赵滨
     */
    public List<PrintTemplate> listPrintTemplateByCarrierId(BigInteger carrierId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
         //判断主账号是否存在
         // To Be Continued
         
         //判断操作人是否存在
         // To Be Continued
         
         //判断是否授权
         // To Be Continued
         
         //查询
         List<PrintTemplate> printTemplateList = printDAO.listPrintTemplateByCarrierId(carrierId, authorized, ownerId);
         return printTemplateList;
     }
    
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
    public OrderInfo addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, Boolean authorized, 
             BigInteger ownerId, BigInteger operatorId) {
        //判断主账号是否存在
        // To Be Continued
        
        //判断操作人是否存在
        // To Be Continued
         
        //判断是否授权
        // To Be Continued
        
        //查询订单
        OrderInfo orderInfo = printDAO.getOrderInfoById(orderInfoId, authorized, ownerId);
        //如果存在
        if (orderInfo != null) {
            //更新快递单号
            orderInfo.setTrackingNum(trackingNum);
            //保存
            int row = printDAO.updateOrderInfo(orderInfo);
        }
        return orderInfo;
    }
     
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
    public List<OrderInfo> printTemplate(Boolean authorized, BigInteger ownerId, BigInteger operatorId, 
            BigInteger[] orderInfoId, BigInteger printTemplateId, String projectPath) {
        
        //判断主账号是否存在
        // To Be Continued
        
        //判断操作人是否存在
        // To Be Continued
        
        //判断是否授权
        // To Be Continued
        
        //记录时间
        Timestamp gmtNow = new Timestamp(System.currentTimeMillis()); 
        
        //1.获取订单 
        
        //创建查找参数
        Map<String, Object> mapOrderInfo = new HashMap<String, Object>();
        mapOrderInfo.put("authorized", authorized);
        mapOrderInfo.put("ownerId", ownerId);
        mapOrderInfo.put("orderInfoIds", orderInfoId);
        //查找订单 
        List<OrderInfo> orderInfoList = printDAO.listOrderInfoByIds(mapOrderInfo);
        /*for (int i = 0; i < orderInfoList.size(); i++) {
            System.out.println(orderInfoList.get(i));
        }*/
        
        //2.获取订单表中关联的ID
        
        //2.1.获取买家id
        BigInteger[] buyerInfoId = new BigInteger[orderInfoList.size()];
        
        //2.2.获取店铺id
        BigInteger[] shopId = new BigInteger[orderInfoList.size()];
        
        //2.3.获取快递id
        BigInteger[] carrierId = new BigInteger[orderInfoList.size()];
        
        for (int i = 0; i < orderInfoList.size(); i++) {
            
            //添加到买家id数组中
            buyerInfoId[i] = orderInfoList.get(i).getPlatformBuyerId();
            
            //添加到店铺id数组中
            shopId[i] = orderInfoList.get(i).getShopId();
            
            //添加到快递id数组中
            carrierId[i] = orderInfoList.get(i).getCarrierId();
        }

        //3.获取订单表中关联的表
        
        //3.1.创建买家查找参数
        Map<String, Object> mapBuyerInfo = new HashMap<String, Object>();
        mapBuyerInfo.put("authorized", authorized);
        mapBuyerInfo.put("ownerId", ownerId);
        mapBuyerInfo.put("buyerInfoIds", buyerInfoId);
        //查找买家
        List<BuyerInfo> buyerInfoList = printDAO.listBuyerInfoByIds(mapBuyerInfo);
        /*for (int i = 0; i < buyerInfoList.size(); i++) {
            System.out.println(buyerInfoList.get(i));
        }*/
        
        //3.2.创建店铺查找参数
        Map<String, Object> mapShopInfo = new HashMap<String, Object>();
        mapShopInfo.put("authorized", authorized);
        mapShopInfo.put("ownerId", ownerId);
        mapShopInfo.put("shopId", shopId);
        //查找店铺
        List<ShopInfo> shopInfoList = printDAO.listShopInfoById(mapShopInfo);
        /*for (int i = 0; i < shopInfoList.size(); i++) {
            System.out.println(shopInfoList.get(i));
        }*/
        
        //3.3.查找模版
        PrintTemplate printTemplate = printTemplateDAO.getPrintTemplateById(printTemplateId, authorized, ownerId);
        /*System.out.println(printTemplate);*/
        
        //4.查找商品信息
        
        //创建订单商品集合
        List<List<ItemCommonInfoOrderItemLink>> listItemCommonInfoList =
                new ArrayList<List<ItemCommonInfoOrderItemLink>>();
        for (int i = 0; i < orderInfoList.size(); i++) {
            //获取每条订单关联的商品
            List<ItemCommonInfoOrderItemLink> itemCommonInfoList =
                    printDAO.listItemCommonInfoByOrderInfoId(orderInfoList.get(i).getId(), authorized, ownerId);
            //添加到商品集合中
            listItemCommonInfoList.add(itemCommonInfoList);
        }
        
        //5.使用模版，进行打印等操作
        for (int i = 0; i < orderInfoList.size(); i++) {
            
            //获取字段坐标
            List<FieldCoordinate> fieldCoordinateList = printDAO.listFieldCoordinateByTemplateId(printTemplateId, authorized, ownerId);
            
            //创建打印点集合
            List<String> list = new ArrayList<String>();
            
            //遍历字段坐标
            for (int j = 0; j < fieldCoordinateList.size(); j++) {
                //获取对象名称
                String fieldName = fieldCoordinateList.get(j).getFieldName();
                //判断取值
                //设置寄件人
                if ("sender".equals(fieldName)) {
                    list.add(shopInfoList.get(i).getContactName()+","+fieldCoordinateList.get(j).getxCoordinate()+","+
                            fieldCoordinateList.get(j).getyCoordinate());
                    
                //设置店铺名称
                } else if ("shop_name".equals(fieldName)) {
                    list.add(shopInfoList.get(i).getName()+","+fieldCoordinateList.get(j).getxCoordinate()+","+
                            fieldCoordinateList.get(j).getyCoordinate());
                    
                //设置订单编号
                } else if ("order_number".equals(fieldName)) {
                    list.add(orderInfoList.get(i).getErpOrderNum()+","+fieldCoordinateList.get(j).getxCoordinate()+","+
                            fieldCoordinateList.get(j).getyCoordinate());
                    
                //设置商品名称
                } else if ("merchandise_news".equals(fieldName)) {
                    //获取每条订单的商品集合 
                    List<ItemCommonInfoOrderItemLink> tempList = listItemCommonInfoList.get(i);
                    String shopName = "";
                    for (int k = 0; k < tempList.size(); k++) {
                        shopName += tempList.get(k).getName();
                        if (k != tempList.size()-1) {
                            shopName += "和";
                        }
                    }
                    list.add(shopName+","+fieldCoordinateList.get(j).getxCoordinate()+","+
                            fieldCoordinateList.get(j).getyCoordinate());
                }
            }
//            System.out.println("根目录:"+projectPath);
//            projectPath = projectPath.replaceAll("('\')", "/");
//            System.out.println("转换后根目录:"+projectPath);
            String printTemplateImage = printTemplate.getTemplateImage();
//            System.out.println("数据库:"+printTemplateImage);
            //设置打印参数
            //打印的图片
            String imgSrc = projectPath+printTemplateImage;
//          "D:/Java/apache-tomcat-8.5.15-windows-x64/apache-tomcat-8.5.15/wtpwebapps/rongcheng-erp/images/abc.png";
            //打印的颜色
            String backgroundColor = "蓝色";
            String printColor = "黑色";
            //打印的字体
            String fontType = "宋体";
            String fontStyle = "加粗";
            Integer fontSize = 14;
            //是否竖打
            Boolean stoodPrint = true;
            //打印的区域
            Integer xCoordinate = printTemplate.getTemplateWidth();
            Integer yCoordinate = printTemplate.getTemplateHeight();
            
            //打印
            PrintTool.startPrint(list, imgSrc, backgroundColor, printColor, fontType, 
                    fontStyle, fontSize, stoodPrint, xCoordinate, yCoordinate);
            
            //修改打印状态
            //如果打印的是快递面单
            if (printTemplate.getTemplateType() == 7 || printTemplate.getTemplateType() == 8 || 
                    printTemplate.getTemplateType() == 9) {
                //设置打印时间
                orderInfoList.get(i).setExpressSheetPrint(gmtNow);
                //保存
                printDAO.updateOrderInfo(orderInfoList.get(i));
                
            //如果打印的是发货单
            } else if (printTemplate.getTemplateType() == 1 || printTemplate.getTemplateType() == 2) {
                //设置打印时间
                orderInfoList.get(i).setSaleBillPrint(gmtNow);
                //保存
                printDAO.updateOrderInfo(orderInfoList.get(i));
            }
            
        }
        
        return orderInfoList;
    }

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
    public Map<String, Object> listPrintAll(Boolean authorized, BigInteger ownerId, BigInteger operatorId, 
            BigInteger[] orderInfoId, BigInteger printTemplateId) {
        
        //判断主账号是否存在
        // To Be Continued
        
        //判断操作人是否存在
        // To Be Continued
        
        //判断是否授权
        // To Be Continued
        
        //创建返回参数map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        //记录时间
        Timestamp gmtNow = new Timestamp(System.currentTimeMillis()); 
        
        //1.获取订单 
        
        //创建查找参数
        Map<String, Object> mapOrderInfo = new HashMap<String, Object>();
        mapOrderInfo.put("authorized", authorized);
        mapOrderInfo.put("ownerId", ownerId);
        mapOrderInfo.put("orderInfoIds", orderInfoId);
        //查找订单 
        List<OrderInfo> orderInfoList = printDAO.listOrderInfoByIds(mapOrderInfo);
        /*for (int i = 0; i < orderInfoList.size(); i++) {
            System.out.println(orderInfoList.get(i));
        }*/

        //加入返回参数
        returnMap.put("orderInfoList", orderInfoList);

        //2.获取订单表中关联的ID
        
        //2.1.获取买家id
        BigInteger[] buyerInfoId = new BigInteger[orderInfoList.size()];
        
        //2.2.获取店铺id
        BigInteger[] shopId = new BigInteger[orderInfoList.size()];
        
        //2.3.获取快递id
        BigInteger[] carrierId = new BigInteger[orderInfoList.size()];
        
        for (int i = 0; i < orderInfoList.size(); i++) {
            
            //添加到买家id数组中
            buyerInfoId[i] = orderInfoList.get(i).getPlatformBuyerId();
            
            //添加到店铺id数组中
            shopId[i] = orderInfoList.get(i).getShopId();
            
            //添加到快递id数组中
            carrierId[i] = orderInfoList.get(i).getCarrierId();
        }
        
        //3.获取订单表中关联的表
        
        //3.1.创建买家查找参数
        Map<String, Object> mapBuyerInfo = new HashMap<String, Object>();
        mapBuyerInfo.put("authorized", authorized);
        mapBuyerInfo.put("ownerId", ownerId);
        mapBuyerInfo.put("buyerInfoIds", buyerInfoId);
        //查找买家
        List<BuyerInfo> buyerInfoList = printDAO.listBuyerInfoByIds(mapBuyerInfo);
        /*for (int i = 0; i < buyerInfoList.size(); i++) {
            System.out.println(buyerInfoList.get(i));
        }*/
        //加入返回参数
        returnMap.put("buyerInfoList", buyerInfoList);

        //3.2.创建店铺查找参数
        Map<String, Object> mapShopInfo = new HashMap<String, Object>();
        mapShopInfo.put("authorized", authorized);
        mapShopInfo.put("ownerId", ownerId);
        mapShopInfo.put("shopId", shopId);
        //查找店铺
        List<ShopInfo> shopInfoList = printDAO.listShopInfoById(mapShopInfo);
        /*for (int i = 0; i < shopInfoList.size(); i++) {
            System.out.println(shopInfoList.get(i));
        }*/
        //加入返回参数
        returnMap.put("shopInfoList", shopInfoList);
        
        //3.3.查找模版
        PrintTemplate printTemplate = printTemplateDAO.getPrintTemplateById(printTemplateId, authorized, ownerId);
        /*System.out.println(printTemplate);*/
        //加入返回参数
        returnMap.put("printTemplate", printTemplate);
        
        //4.查找商品信息
        
        //创建订单商品集合
        List<List<ItemCommonInfoOrderItemLink>> listItemCommonInfoList =
                new ArrayList<List<ItemCommonInfoOrderItemLink>>();
        for (int i = 0; i < orderInfoList.size(); i++) {
            //获取每条订单关联的商品
            List<ItemCommonInfoOrderItemLink> itemCommonInfoList =
                    printDAO.listItemCommonInfoByOrderInfoId(orderInfoList.get(i).getId(), authorized, ownerId);
            //添加到商品集合中
            listItemCommonInfoList.add(itemCommonInfoList);
        }
        //加入返回参数
        returnMap.put("listItemCommonInfoList", listItemCommonInfoList);
        
        //5. //获取字段坐标
        List<FieldCoordinate> fieldCoordinateList = 
                printDAO.listFieldCoordinateByTemplateId(printTemplateId, authorized, ownerId);
        //加入返回参数
        returnMap.put("fieldCoordinateList", fieldCoordinateList);
        
        //使用模版
        for (int i = 0; i < orderInfoList.size(); i++) {
            //修改打印状态
            //如果打印的是快递面单
            if (printTemplate.getTemplateType() == 7 || printTemplate.getTemplateType() == 8 || 
                    printTemplate.getTemplateType() == 9) {
                //设置打印时间
                orderInfoList.get(i).setExpressSheetPrint(gmtNow);
                //保存
                printDAO.updateOrderInfo(orderInfoList.get(i));
                
            //如果打印的是发货单
            } else if (printTemplate.getTemplateType() == 1 || printTemplate.getTemplateType() == 2) {
                //设置打印时间
                orderInfoList.get(i).setSaleBillPrint(gmtNow);
                //保存
                printDAO.updateOrderInfo(orderInfoList.get(i));
            }
            
        }
        
        return returnMap;
    }
}
