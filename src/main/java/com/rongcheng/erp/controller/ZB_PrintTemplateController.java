package com.rongcheng.erp.controller;


import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.PrintTemplate;
import com.rongcheng.erp.entity.UserInfo;
import com.rongcheng.erp.exception.NameException;
import com.rongcheng.erp.service.printTemplate.ZB_PrintTemplateService;
import com.rongcheng.erp.utils.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 单据模板表 控制层
 * @author 赵滨
 *
 */
@Controller
public class ZB_PrintTemplateController {
    //分页相关（每页多少条）
    @Value("#{config['rows']}")
    private int rows;

    @Resource
    private ZB_PrintTemplateService printTemplateService;

    @ExceptionHandler(NameException.class)
    @ResponseBody
    public JsonResult uploadStockException(NameException e){
        e.printStackTrace();
        return new JsonResult(JsonResult.ERROR, e);
    }

    /**
     * 跳转快递面单页面
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/expressTemplate.do")
    public String expressTemplate() {
        return "settings/expressTemplate";
    }
    
    /**
     * 加载快递面单页面的内容
     * @param page  加载的页数
     * @param templateType  模版类型
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/expressTemplate/loadExpressTemplate.do")
    public JsonResult loadExpressTemplate(int page, Integer[] templateType, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        Map<String, Object> map = printTemplateService.listPrintTemplateByPage(page, rows, ownerId, templateType);
        return new JsonResult(map);
    }

    /**
     * 查询单据模板图片 （无重复）
     * @param carrierInfoId 快递ID
     * @param templateType 模版类型[]
     * @param preset 是否使用预设模版
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/expressTemplate/loadPrintTemplateImage.do")
    public JsonResult loadPrintTemplateImage(
            BigInteger carrierInfoId, Integer[] templateType, Boolean preset, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //获取图片
        List<PrintTemplate> list =
                printTemplateService.listPrintTemplateByType(ownerId, carrierInfoId, templateType, preset);
        return new JsonResult(list);
    }
    
    /**
     * 删除单据模板
     * @param printTemplateId 单据模板id
     * @param session HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/expressTemplate/removeExpressTemplate.do")
    public JsonResult removeExpressTemplate(BigInteger printTemplateId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //删除模版
        int row = printTemplateService.removePrintTemplateById(printTemplateId, ownerId);
        return new JsonResult(row);
    }
    
    /**
     * 复制并新建单据模板 
     * @param printTemplateId 单据模板id
     * @param session HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/expressTemplate/copyAddExpressTemplate.do")
    public JsonResult copyAddExpressTemplate(BigInteger printTemplateId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //复制模版
        PrintTemplate printTemplate = printTemplateService.copyAddExpressTemplate(printTemplateId, ownerId);
        return new JsonResult(printTemplate);
    }

    /**
     * 跳转 添加修改 模版页面
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/addAmendExpressTemplate.do")
    public String addAmendExpressTemplate() {
        return "settings/addAmendExpressTemplate";
    }

    /**
     * 加载 添加修改 模版页面的内容
     * @param printTemplateId  模版id
     * @param preset 是否使用预设模版
     * @param session    HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendExpressTemplate/loadAddAmendExpressTemplate.do")
    public JsonResult loadAddAmendExpressTemplate(BigInteger printTemplateId, Boolean preset, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        Map<String, Object> map = printTemplateService.listPrintTemplateById(ownerId, printTemplateId, preset);
        return new JsonResult(map);
    }

    /**
     * 提交 添加修改 模版功能
     * @param request 请求
     * @param preset    是否预设模版
     * @param session    HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendExpressTemplate/commitAddAmendExpressTemplate.do")
    public JsonResult commitAddAmendExpressTemplate(HttpServletRequest request, Boolean preset, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();

        //获取集合map
        Map map = request.getParameterMap();
        //添加
        int row = printTemplateService.saveUpdatePrintTemplate(map, preset, ownerId);
        return new JsonResult(row);
    }

    /**
     * 跳转自定义单据页面
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/invoiceTemplate.do")
    public String invoiceTemplate() {
        return "settings/invoiceTemplate";
    }
    
    /**
     * 加载自定义单据页面的内容
     * @param page  加载的页数
     * @param templateType  模版类型
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/invoiceTemplate/loadInvoiceTemplate.do")
    public JsonResult loadInvoiceTemplate(int page, Integer[] templateType, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        Map<String, Object> map = printTemplateService.listPrintTemplateByPage(page, rows, ownerId, templateType);
        return new JsonResult(map);
    }

    /**
     * 查询单据模板图片 （无重复）
     * @param carrierInfoId 快递ID
     * @param templateType 模版类型[]
     * @param preset 是否使用预设模版
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/invoiceTemplate/loadPrintTemplateImage.do")
    public JsonResult loadPrintTemplateImg(
            BigInteger carrierInfoId, Integer[] templateType, Boolean preset, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //获取图片
        List<PrintTemplate> list =
                printTemplateService.listPrintTemplateByType(ownerId, carrierInfoId, templateType, preset);
        return new JsonResult(list);
    }

    /**
     * 删除自定义单据模板
     * @param printTemplateId 单据模板id
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/invoiceTemplate/removeInvoiceTemplate.do")
    public JsonResult removeInvoiceTemplate(BigInteger printTemplateId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //删除模版
        int row = printTemplateService.removePrintTemplateById(printTemplateId, ownerId);
        return new JsonResult(row);
    }
    
    /**
     * 复制并新建单据模板 
     * @param printTemplateId 单据模板id
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/invoiceTemplate/copyAddInvoiceTemplate.do")
    public JsonResult copyAddInvoiceTemplate(BigInteger printTemplateId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //复制模版
        PrintTemplate printTemplate = printTemplateService.copyAddExpressTemplate(printTemplateId, ownerId);
        return new JsonResult(printTemplate);
    }
    
    /**
     * 跳转 添加修改 自定义单据页面
     * @return jsp页面
     * @author 赵滨
     */
    @RequestMapping("/addAmendInvoiceTemplate.do")
    public String addAmendInvoiceTemplate() {
        return "settings/addAmendInvoiceTemplate";
    }

    /**
     * 加载 添加修改 自定义模版页面的内容
     * @param printTemplateId  模版id
     * @param preset 是否使用预设模版
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendInvoiceTemplate/loadAddAmendInvoiceTemplate.do")
    public JsonResult loadAddAmendInvoiceTemplate(BigInteger printTemplateId, Boolean preset, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        Map<String, Object> map = printTemplateService.listPrintTemplateById(ownerId, printTemplateId, preset);
        return new JsonResult(map);
    }
    
    /**
     * 提交 添加修改 自定义模版功能
     * @param request 请求
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendInvoiceTemplate/commitAddAmendInvoiceTemplate.do")
    public JsonResult commitAddAmendInvoiceTemplate(HttpServletRequest request, Boolean preset, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //获取集合map
        Map map = request.getParameterMap();
        //添加
        int row = printTemplateService.saveUpdatePrintTemplate(map, preset, ownerId);
        return new JsonResult(row);
    }
    
    /**
     * 加载打印页面的面单模版
     * @param carrierId 快递公司id
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/loadPrintTemplate.do")
    public JsonResult loadPrintTemplate(BigInteger carrierId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //查询
        List<PrintTemplate> list = 
                printTemplateService.listPrintTemplateByCarrierId(carrierId, ownerId);
        return new JsonResult(list);
    }
    
    /**
     * 加载打印页面的自定义面单模版
     * @param carrierId 快递公司id
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/loadInvoiceTemplate.do")
    public JsonResult loadInvoiceTemplate(BigInteger carrierId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        //查询
        List<PrintTemplate> list = 
                printTemplateService.listPrintTemplateByCarrierId(carrierId, ownerId);
        return new JsonResult(list);
    }
    
    /**
     * 添加或修改订单的快递单号
     * @param orderInfoId 订单id
     * @param trackingNum 快递单号
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/addOrModifyTracking.do")
    public JsonResult addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        OrderInfo orderInfo = 
                printTemplateService.addOrModifyTracking(orderInfoId, trackingNum, ownerId);
        return new JsonResult(orderInfo);
    }
    
    /**
     * 打印面单功能
     * 
     * @param orderInfoId 订单id[]
     * @param printTemplateId 模版id
     * @param session  HttpSession
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/printTemplate.do")
    public JsonResult printTemplate(BigInteger[] orderInfoId, BigInteger printTemplateId, HttpSession session) {
        BigInteger ownerId = ((UserInfo) (session.getAttribute("user"))).getOwnerId();
        Map<String, Object> returnMap = printTemplateService.listPrintAll(ownerId, orderInfoId, printTemplateId);
        return new JsonResult(returnMap);
    }
}
