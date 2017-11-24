package com.rongcheng.erp.service.printTemplate;

import com.rongcheng.erp.dao.ZB_CarrierInfoDAO;
import com.rongcheng.erp.dao.ZB_FieldCoordinateDAO;
import com.rongcheng.erp.dao.ZB_PrintDAO;
import com.rongcheng.erp.dao.ZB_PrintTemplateDAO;
import com.rongcheng.erp.entity.*;
import com.rongcheng.erp.exception.NameException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

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

    //初始模板条数
    private static int initialTemplateIdCount;
    //最大模板ID
    private static BigInteger maxTemplateId;

    /**
     * 初始化静态变量
     * @author 赵滨
     */
    @PostConstruct
    private void init() {
        initialTemplateIdCount = printTemplateDAO.getInitialTemplateIdCount();
        maxTemplateId = printTemplateDAO.getMaxTemplateId();
    }

    /**
     * 加锁的增加模板ID
     * @param plus 增加数值
     * @return
     * @author 赵滨
     */
    private synchronized BigInteger increaseTemplateId(int plus) {
        BigInteger nowTemplateId = maxTemplateId;
        maxTemplateId = maxTemplateId.add(new BigInteger("" + plus));
        return nowTemplateId;
    }
    
    /**
     * 根据页数查询单据模板
     * @param page 加载的页数
     * @param rows 显示的条数
     * @param ownerId 主账号ID
     * @param templateType 模版类型
     * @return List<PrintTemplate> 单据模版的集合
     * @author 赵滨
     */
    public Map<String, Object> listPrintTemplateByPage(
            int page, int rows, BigInteger ownerId, Integer[] templateType) {
        //获取start参数
        int start = (page-1)*rows;
        //定义map
        Map<String, Object> map = new HashMap<>();

        //创建参数集合map
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("rows", rows);
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
            CarrierInfo carrierInfo = carrierInfoDAO.getCarrierInfoById(carrierId, new BigInteger("0"));
            //CarrierInfoList中添加carrierInfo
            carrierInfoList.add(carrierInfo);
        }
        map.put("carrierInfoList", carrierInfoList);

        //创建参数集合map
        params = new HashMap<>();
        params.put("ownerId", ownerId);
        params.put("templateType", templateType);
        //获取条数
        int row = printTemplateDAO.countPrintTemplate(params);
        map.put("maxPage", Math.ceil((double) row / (double) rows));

        return map;
    }

    /**
     * 查询单据模板图片 根据 模版类型（无重复）
     * @param ownerId   主账号ID
     * @param carrierInfoId 快递ID
     * @param templateType 模版类型[]
     * @param preset 是否使用预设模版
     * @return  List<PrintTemplate> 单据模版的集合
     * @author 赵滨
     */
    public List<PrintTemplate> listPrintTemplateByType(
            BigInteger ownerId, BigInteger carrierInfoId, Integer[] templateType, Boolean preset) {
        //创建参数集合map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("templateType", templateType);
        map.put("carrierInfoId", carrierInfoId);
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
     * @param ownerId   主账号ID
     * @return row 删除的行数
     * @author 赵滨
     */
    public int removePrintTemplateById(BigInteger templateId, BigInteger ownerId) {
        //查询单据模板是否存在
        PrintTemplate printTemplate = printTemplateDAO.getPrintTemplateById(templateId, ownerId);
        if (printTemplate != null) {
            //删除
            int row = printTemplateDAO.removePrintTemplateById(templateId, ownerId);
            fieldCoordinateDAO.removeFieldCoordinateByTemplateId(templateId, ownerId);
            return row;
        }
        return 0;
    }
    
    /**
     * 复制并新建单据模板
     * @param printTemplateId 单据模板id
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    public PrintTemplate copyAddExpressTemplate(BigInteger printTemplateId, BigInteger ownerId) {
        //记录创建时间
        Timestamp gmtCreate = new Timestamp(System.currentTimeMillis());

        //第一部分  复制模版
        //查询需要复制的模版
        PrintTemplate oldPrintTemplate = printTemplateDAO.getPrintTemplateById(printTemplateId, ownerId);
        String templateName = oldPrintTemplate.getTemplateName();

        //如果该模版本身就是副本  例如：某某某 - 副本(2)
        if (templateName.matches("^.+\\s-\\s副本\\(\\d+\\)$"))
            //去掉尾部  例如：某某某
            templateName = templateName.replaceAll("\\s-\\s副本\\(\\d+\\)$", "");

        //查询相同名称模版 的 副本
        List<PrintTemplate> printTemplateList =
                printTemplateDAO.listPrintTemplateByLikeTemplateName(templateName, ownerId);

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
            throw new NameException("复制后的模板名称过长，无法复制");
        }

        //把ID自增
        BigInteger id = increaseTemplateId(1).add(new BigInteger("1"));

        //定义新建模版
        PrintTemplate newPrintTemplate = new PrintTemplate(id, oldPrintTemplate.getCarrierId(),
                oldPrintTemplate.getCarrierName(), templateName,
                oldPrintTemplate.getTemplateImage(), oldPrintTemplate.getTemplateType(),
                oldPrintTemplate.getElectronicWaybill(), oldPrintTemplate.getTemplateWidth(),
                oldPrintTemplate.getTemplateHeight(), oldPrintTemplate.getPrintChoice(),
                oldPrintTemplate.getReserved1(), oldPrintTemplate.getNote(),
                ownerId, oldPrintTemplate.getOperatorId(), oldPrintTemplate.getAuthorized(),
                gmtCreate, null);

        //保存新建模版
        printTemplateDAO.savePrintTemplate(newPrintTemplate);

        //第二部分  复制坐标
        fieldCoordinateDAO.copyAddFieldCoordinate(id, ownerId, gmtCreate, printTemplateId);

        return newPrintTemplate;
    }

    /**
     * 保存 或 修改 单据模板
     * @param map 模版和坐标的集合
     * @param preset 是否预设模版
     * @param ownerId 主账号ID
     * @return state 状态值
     * @author 赵滨
     */
    public int saveUpdatePrintTemplate(Map map, Boolean preset, BigInteger ownerId) {
        //判断 只有不为空才能继续运行  否则直接报错
        if (map.size() != 0 || map != null || !map.isEmpty()) {
            //判断是 保存 还是 修改
            if (preset == true) {
                return savePrintTemplate(map, ownerId);
            } else {
                return updatePrintTemplate(map, ownerId);
            }
        }
        return 0;
    }

    /**
     * 保存单据模板
     * @param map 模版和坐标的集合
     * @param ownerId 主账号ID
     * @return state 状态值
     * @author 赵滨
     */
    public int savePrintTemplate(Map map, BigInteger ownerId) {
        //判断 只有不为空才能继续运行  否则直接报错
        if (map.size() != 0 || map != null || !map.isEmpty()) {
            //记录创建时间
            Timestamp gmtCreate = new Timestamp(System.currentTimeMillis()); 
            
            //定义参数
            BigInteger printTemplateId = null;            //模版id
            String templateName = null;            //模板名称
            Integer templateWidth = null;            //模板宽
            Integer templateHeight = null;            //模板高
            String printChoice = null;            //打印机选择

            //遍历    用于第一步 创建map前半部分参数
            for (Object key : map.keySet()) {
                //获取值
                for (String value : (String[]) map.get(key)) {
                    //匹配键
                    if ("data[printTemplateId]".equals(key))
                        printTemplateId = new BigInteger(value);//模版id

                    if ("data[templateName]".equals(key))
                        templateName = value;//模板名称

                    if ("data[templateWidth]".equals(key))
                        templateWidth = new Integer(value);//模板宽

                    if ("data[templateHeight]".equals(key))
                        templateHeight = new Integer(value);//模板高

                    if ("data[printChoice]".equals(key))
                        printChoice = value;//打印机选择
                }
            }
            
            //查找预设模版实体对象    用于第一步 创建map前半部分参数
            PrintTemplate printTemplateMode = 
                    printTemplateDAO.getPrintTemplateById(printTemplateId, new BigInteger("0"));

            BigInteger carrierId = printTemplateMode.getCarrierId();//快递公司ID
            String carrierName = printTemplateMode.getCarrierName();//快递公司名称
            String templateImage = printTemplateMode.getTemplateImage();//模板图片
            Boolean electronicWaybill = printTemplateMode.getElectronicWaybill();//是否电子面单
            Integer templateType = printTemplateMode.getTemplateType();//模板类型

            //把ID自增
            BigInteger id = increaseTemplateId(1).add(new BigInteger("1"));
            //创建实体对象    用于第一步 创建map前半部分参数
            PrintTemplate printTemplate = new PrintTemplate(id, carrierId, carrierName, templateName,
                    templateImage, templateType, electronicWaybill, templateWidth, templateHeight, printChoice, 
                    null, null, ownerId, null, null,
                    gmtCreate, null);
            //保存模版对象    第一部分结束
            int row = printTemplateDAO.savePrintTemplate(printTemplate);
            
            //遍历 用于第二步 创建map后半部分参数
            for (Object key : map.keySet()) {
                //获取值
                for (String value : (String[]) map.get(key)) {
                    //匹配键
                    if ("data[fieldCoordinate]".equals(((String)key).replaceAll("(\\d)+", ""))) {
                        //创建实体对象（字段坐标表）
                        FieldCoordinate fieldCoordinate = new FieldCoordinate();
                        fieldCoordinate.setTemplateId(id);//模板ID
                        fieldCoordinate.setCoordinateType(templateType);//坐标类型
                        String[] values = value.split("(\\s)*,(\\s)*");//分割参数
                        fieldCoordinate.setFieldName(values[0].toString());//字段英文命名
                        fieldCoordinate.setxCoordinate(
                                Integer.valueOf(values[1].replaceAll("[a-z]+", "")));//字段x坐标
                        fieldCoordinate.setyCoordinate(
                                Integer.valueOf(values[2].replaceAll("[a-z]+", "")));//字段y坐标

                        fieldCoordinate.setxLength(
                                Integer.valueOf(values[3].replaceAll("[a-z]+", "")));//字段x长度

                        fieldCoordinate.setyLength(
                                Integer.valueOf(values[4].replaceAll("[a-z]+", "")));//字段y长度
                        fieldCoordinate.setOwnerId(ownerId);//用户主账户ID
                        fieldCoordinate.setGmtCreate(gmtCreate);//记录创建时间
                        //保存字段坐标对象  第二部分结束
                        fieldCoordinateDAO.saveFieldCoordinate(fieldCoordinate);
                    }
                }
            }
            return row;
        }
        return 0;
    }
    
    /**
     * 根据id关联查询单据模板
     * @param ownerId    主账号ID
     * @param id    单据模版id
     * @return  Map<String, Object> 一个单据模版 和 不定个数的字段坐标
     * @author 赵滨
     */
    public Map<String, Object> listPrintTemplateById(BigInteger ownerId, BigInteger id, Boolean preset) {
        //创建返回值对象map
        Map<String, Object> map = new HashMap<String, Object>();
        
        //如果是预设模版
        if (preset == true) {
            //查询模版
            PrintTemplate PrintTemplate = printTemplateDAO.getPrintTemplateById(id, new BigInteger("0"));
            map.put("PrintTemplate", PrintTemplate);
            //查询字段坐标
            List<FieldCoordinate> fieldCoordinateList =
                    fieldCoordinateDAO.listFieldCoordinateByTemplateId(id, new BigInteger("0"));
            map.put("fieldCoordinateList", fieldCoordinateList);
        } else {
            //查询模版
            PrintTemplate PrintTemplate = printTemplateDAO.getPrintTemplateById(id, ownerId);
            map.put("PrintTemplate", PrintTemplate);
            //查询字段坐标
            List<FieldCoordinate> fieldCoordinateList = 
                    fieldCoordinateDAO.listFieldCoordinateByTemplateId(id, ownerId);
            map.put("fieldCoordinateList", fieldCoordinateList);
        }
        return map;
    }

    /**
     * 更新单据模板
     * @param map 模版和坐标的集合
     * @param ownerId    主账号ID
     * @return state 状态值
     * @author 赵滨
     */
    public int updatePrintTemplate(Map map, BigInteger ownerId) {
        //判断 只有不为空才能继续运行  否则直接报错
        if (map.size() != 0 || map != null || !map.isEmpty()) {
            //记录修改时间
            Timestamp gmtCreate = new Timestamp(System.currentTimeMillis()); 
            Timestamp gmtModified = gmtCreate;
            
            //定义参数
            BigInteger printTemplateId = null;//模版id
            String templateName = null;//模板名称
            Integer templateWidth = null;//模板宽
            Integer templateHeight = null;//模板高
            String printChoice = null;//打印机选择
            String fieldCoordinateIdData = null;//需要删除的id数组
            
            //遍历    用于第一步 创建map前半部分参数
            for (Object key : map.keySet()) {
                //获取值
                for (String value : (String[]) map.get(key)) {
                    //匹配键
                    if ("data[printTemplateId]".equals(key))
                        printTemplateId = new BigInteger(value);//模版id
                        
                   if ("data[templateName]".equals(key))
                        templateName = value;//模板名称
                        
                   if ("data[templateWidth]".equals(key))
                        templateWidth = new Integer(value);//模板宽
                        
                   if ("data[templateHeight]".equals(key))
                        templateHeight = new Integer(value);//模板高
                        
                   if ("data[printChoice]".equals(key))
                        printChoice = value;//打印机选择
                        
                   if ("data[fieldCoordinateIdData]".equals(key))
                        fieldCoordinateIdData = value;
                }
            }

            //创建实体对象    用于第一步 创建map前半部分参数
            PrintTemplate printTemplate = printTemplateDAO.getPrintTemplateById(printTemplateId, ownerId);

            int row = 0;
            //如果对象存在
            if (printTemplate != null) {
                //设置
                printTemplate.setTemplateName(templateName);//模板名称
                printTemplate.setTemplateWidth(templateWidth);//模板宽
                printTemplate.setTemplateHeight(templateHeight);//模板高
                printTemplate.setPrintChoice(printChoice);//打印机选择
                printTemplate.setGmtModified(gmtModified);//修改时间
                //更新对象  第一部分结束
                row = printTemplateDAO.updatePrintTemplate(printTemplate);
                
                //创建删除参数
                Map<String, Object> fieldCoordinateMap = new HashMap<>();
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
                        String[] values = value.split("(\\s)*,(\\s)*");//分割参数
                        
                        //如果不存在id，那么添加内容
                        if (values.length == 5) {
                            //创建实体对象（字段坐标表）
                            FieldCoordinate fieldCoordinate = new FieldCoordinate();
                            fieldCoordinate.setTemplateId(printTemplateId);//模板ID
                            fieldCoordinate.setCoordinateType(templateType);//坐标类型
                            fieldCoordinate.setFieldName(values[0].toString());//字段英文命名
                            //字段x坐标
                            fieldCoordinate.setxCoordinate(
                                    Integer.valueOf(values[1].replaceAll("[a-z]+", "")));
                            //字段y坐标
                            fieldCoordinate.setyCoordinate(
                                    Integer.valueOf(values[2].replaceAll("[a-z]+", "")));
                            //字段x长度
                            fieldCoordinate.setxLength(
                                    Integer.valueOf(values[3].replaceAll("[a-z]+", "")));
                            //字段y长度
                            fieldCoordinate.setyLength(
                                    Integer.valueOf(values[4].replaceAll("[a-z]+", "")));
                            fieldCoordinate.setOwnerId(ownerId);//用户主账户ID
                            fieldCoordinate.setGmtCreate(gmtCreate);//记录创建时间
                            //保存字段坐标对象
                            fieldCoordinateDAO.saveFieldCoordinate(fieldCoordinate);
                            
                        //如果存在id，那么修改内容
                        } else if (values.length == 6) {
                            BigInteger fieldCoordinateId = new BigInteger(values[5].toString());//获取id
                            //获取对象
                            FieldCoordinate fieldCoordinate = 
                                    fieldCoordinateDAO.getFieldCoordinateById(fieldCoordinateId, ownerId);
                            
                            fieldCoordinate.setFieldName(values[0].toString());//字段英文命名
                            //字段x坐标
                            fieldCoordinate.setxCoordinate(
                                    Integer.valueOf(values[1].replaceAll("[a-z]+", "")));
                            //字段y坐标
                            fieldCoordinate.setyCoordinate(
                                    Integer.valueOf(values[2].replaceAll("[a-z]+", "")));
                            //字段x长度
                            fieldCoordinate.setxLength(
                                    Integer.valueOf(values[3].replaceAll("[a-z]+", "")));
                            //字段y长度
                            fieldCoordinate.setyLength(
                                    Integer.valueOf(values[4].replaceAll("[a-z]+", "")));
                            fieldCoordinate.setGmtModified(gmtModified);//记录修改时间
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
     * @param ownerId   主账号id
     * @return List<PrintTemplate> 模版的集合
     * @author 赵滨
     */
    public List<PrintTemplate> listPrintTemplateByCarrierId(BigInteger carrierId, BigInteger ownerId) {
         //查询
         List<PrintTemplate> printTemplateList = printDAO.listPrintTemplateByCarrierId(carrierId, ownerId);
         return printTemplateList;
     }
    
    /**
     * 添加或修改订单的快递单号
     * @param orderInfoId 订单id
     * @param trackingNum 快递单号
     * @param ownerId   主账号id
     * @return OrderInfo 订单
     * @author 赵滨
     */
    public OrderInfo addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, BigInteger ownerId) {
        //查询订单
        OrderInfo orderInfo = printDAO.getOrderInfoById(orderInfoId, ownerId);
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
     * @param ownerId    主账号id
     * @param orderInfoId   订单ID[]
     * @param printTemplateId 模版id
     * @return Map<String, Object> 返回参数集合
     * @author 赵滨
     */
    public Map<String, Object> listPrintAll(BigInteger ownerId, BigInteger[] orderInfoId, BigInteger printTemplateId) {
        //创建返回参数map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        //记录时间
        Timestamp gmtNow = new Timestamp(System.currentTimeMillis()); 
        
        //1.获取订单 
        
        //创建查找参数
        Map<String, Object> mapOrderInfo = new HashMap<String, Object>();
        mapOrderInfo.put("ownerId", ownerId);
        mapOrderInfo.put("orderInfoIds", orderInfoId);
        //查找订单 
        List<OrderInfo> orderInfoList = printDAO.listOrderInfoByIds(mapOrderInfo);

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
        mapBuyerInfo.put("ownerId", ownerId);
        mapBuyerInfo.put("buyerInfoIds", buyerInfoId);
        //查找买家
        List<BuyerInfo> buyerInfoList = printDAO.listBuyerInfoByIds(mapBuyerInfo);

        //加入返回参数
        returnMap.put("buyerInfoList", buyerInfoList);

        //3.2.创建店铺查找参数
        Map<String, Object> mapShopInfo = new HashMap<String, Object>();
        mapShopInfo.put("ownerId", ownerId);
        mapShopInfo.put("shopId", shopId);
        //查找店铺
        List<ShopInfo> shopInfoList = printDAO.listShopInfoById(mapShopInfo);

        //加入返回参数
        returnMap.put("shopInfoList", shopInfoList);
        
        //3.3.查找模版
        PrintTemplate printTemplate = printTemplateDAO.getPrintTemplateById(printTemplateId, ownerId);

        //加入返回参数
        returnMap.put("printTemplate", printTemplate);
        
        //4.查找商品信息
        
        //创建订单商品集合
        List<List<ItemCommonInfoOrderItemLink>> listItemCommonInfoList =
                new ArrayList<List<ItemCommonInfoOrderItemLink>>();
        for (int i = 0; i < orderInfoList.size(); i++) {
            //获取每条订单关联的商品
            List<ItemCommonInfoOrderItemLink> itemCommonInfoList =
                    printDAO.listItemCommonInfoByOrderInfoId(orderInfoList.get(i).getId(), ownerId);
            //添加到商品集合中
            listItemCommonInfoList.add(itemCommonInfoList);
        }
        //加入返回参数
        returnMap.put("listItemCommonInfoList", listItemCommonInfoList);
        
        //5. //获取字段坐标
        List<FieldCoordinate> fieldCoordinateList = 
                printDAO.listFieldCoordinateByTemplateId(printTemplateId, ownerId);
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

    /**
     * 初始化模板
     * @param ownerId   主账号ID
     * @return
     * @author 赵滨
     */
    public Boolean initializationTemplate(BigInteger ownerId) {
        //创建时间
        Timestamp time = new Timestamp(System.currentTimeMillis());
        //传入初始条数，获取当前ID
        BigInteger id = increaseTemplateId(initialTemplateIdCount);

        //成功的行数
        int row = 0;

        //初始化模板
        row = printTemplateDAO.initializationPrintTemplate(id, ownerId, time);
        if (row <= 0) {
            return false;
        }
        //初始化字段坐标
        row = fieldCoordinateDAO.initializationFieldCoordinate(id, ownerId, time);
        if (row <= 0) {
            return false;
        }
        return true;
    }
}
