package com.rongcheng.erp.controller;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongcheng.erp.entity.OrderInfo;
import com.rongcheng.erp.entity.PrintTemplate;
import com.rongcheng.erp.service.printTemplate.ZB_PrintTemplateService;
import com.rongcheng.erp.utils.JsonResult;

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
     * @param authorized    是否授权
     * @param ownerId   主账号ID
     * @param operatorId    操作人ID
     * @param templateType  模版类型
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/expressTemplate/loadExpressTemplate.do")
    public JsonResult loadExpressTemplate(int page, Boolean authorized, BigInteger ownerId, BigInteger operatorId, 
            Integer[] templateType) {
        /*System.out.println(page+","+authorized+","+ownerId+","+operatorId+","+templateType+","+rows);*/
        Map<String, Object> map = printTemplateService.listPrintTemplateByPage(page, rows, authorized, ownerId, 
                operatorId, templateType);
        return new JsonResult(map);
    }

    /**
     * 查询单据模板图片 （无重复）
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @param templateType 模版类型[]
     * @param preset 是否使用预设模版
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/expressTemplate/loadPrintTemplateImage.do")
    public JsonResult loadPrintTemplateImage(Boolean authorized, BigInteger ownerId, BigInteger operatorId, 
            Integer[] templateType, Boolean preset) {
        //获取图片
        List<PrintTemplate> list = printTemplateService.listPrintTemplateByType(authorized, ownerId, operatorId, 
                templateType, preset);
        return new JsonResult(list);
    }
    
    /**
     * 删除单据模板
     * @param printTemplateId 单据模板id
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/expressTemplate/removeExpressTemplate.do")
    public JsonResult removeExpressTemplate(BigInteger printTemplateId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //删除模版
        int row = printTemplateService.removePrintTemplateById(printTemplateId, authorized, ownerId, operatorId);
        return new JsonResult(row);
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
    @ResponseBody
    @RequestMapping("/expressTemplate/copyAddExpressTemplate.do")
    public JsonResult copyAddExpressTemplate(BigInteger printTemplateId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //复制模版
        PrintTemplate printTemplate = printTemplateService.copyAddExpressTemplate(printTemplateId, authorized, ownerId, operatorId);
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
     * @param authorized    是否授权
     * @param ownerId   主账号ID
     * @param operatorId    操作人ID
     * @param printTemplateId  模版id
     * @param preset 是否使用预设模版
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendExpressTemplate/loadAddAmendExpressTemplate.do")
    public JsonResult loadAddAmendExpressTemplate(Boolean authorized, BigInteger ownerId, BigInteger operatorId,
                                             BigInteger printTemplateId, Boolean preset) {
        Map<String, Object> map = printTemplateService.listPrintTemplateById(authorized, ownerId, operatorId, printTemplateId, preset);
        return new JsonResult(map);
    }

    /**
     * 提交 添加修改 模版功能
     * @param request 请求
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendExpressTemplate/commitAddAmendExpressTemplate.do")
    public JsonResult commitAddAmendExpressTemplate(HttpServletRequest request, Boolean preset) {
        //获取集合map
        Map map = request.getParameterMap();

        //添加
        int row = printTemplateService.saveUpdatePrintTemplate(map, preset);
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
     * @param authorized    是否授权
     * @param ownerId   主账号ID
     * @param operatorId    操作人ID
     * @param templateType  模版类型
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/invoiceTemplate/loadInvoiceTemplate.do")
    public JsonResult loadInvoiceTemplate(int page, Boolean authorized, BigInteger ownerId, BigInteger operatorId, 
            Integer[] templateType) {
        /*System.out.println(page+","+authorized+","+ownerId+","+operatorId+","+templateType+","+rows);*/
        Map<String, Object> map = printTemplateService.listPrintTemplateByPage(page, rows, authorized, ownerId, 
                operatorId, templateType);
        return new JsonResult(map);
    }

    /**
     * 查询单据模板图片 （无重复）
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @param templateType 模版类型[]
     * @param preset 是否使用预设模版
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/invoiceTemplate/loadPrintTemplateImage.do")
    public JsonResult loadPrintTemplateImg(Boolean authorized, BigInteger ownerId, BigInteger operatorId, 
            Integer[] templateType, Boolean preset) {
        //获取图片
        List<PrintTemplate> list = printTemplateService.listPrintTemplateByType(authorized, ownerId, operatorId, 
                templateType, preset);
        return new JsonResult(list);
    }
    
    /**
     * 删除自定义单据模板
     * @param printTemplateId 单据模板id
     * @param authorized 是否授权
     * @param ownerId   主账号ID
     * @param operatorId 操作人ID
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/invoiceTemplate/removeInvoiceTemplate.do")
    public JsonResult removeInvoiceTemplate(BigInteger printTemplateId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //删除模版
        int row = printTemplateService.removePrintTemplateById(printTemplateId, authorized, ownerId, operatorId);
        return new JsonResult(row);
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
    @ResponseBody
    @RequestMapping("/invoiceTemplate/copyAddInvoiceTemplate.do")
    public JsonResult copyAddInvoiceTemplate(BigInteger printTemplateId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //复制模版
        PrintTemplate printTemplate = printTemplateService.copyAddExpressTemplate(printTemplateId, authorized, ownerId, operatorId);
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
     * @param authorized    是否授权
     * @param ownerId   主账号ID
     * @param operatorId    操作人ID
     * @param printTemplateId  模版id
     * @param preset 是否使用预设模版
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendInvoiceTemplate/loadAddAmendInvoiceTemplate.do")
    public JsonResult loadAddAmendInvoiceTemplate(Boolean authorized, BigInteger ownerId, BigInteger operatorId,
            BigInteger printTemplateId, Boolean preset) {
        Map<String, Object> map = printTemplateService.listPrintTemplateById(authorized, ownerId, operatorId, printTemplateId, preset);
        return new JsonResult(map);
    }
    
    /**
     * 提交 添加修改 自定义模版功能
     * @param request 请求
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/addAmendInvoiceTemplate/commitAddAmendInvoiceTemplate.do")
    public JsonResult commitAddAmendInvoiceTemplate(HttpServletRequest request, Boolean preset) {
        //获取集合map
        Map map = request.getParameterMap();
        //添加
        int row = printTemplateService.saveUpdatePrintTemplate(map, preset);
        return new JsonResult(row);
    }
    
    /**
     * 加载打印页面的面单模版
     * @param carrierId 快递公司id
     * @param authorized 是否授权
     * @param ownerId   主账号id
     * @param operatorId 操作人id
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/loadPrintTemplate.do")
    public JsonResult loadPrintTemplate(BigInteger carrierId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //查询
        List<PrintTemplate> list = 
                printTemplateService.listPrintTemplateByCarrierId(carrierId, authorized, ownerId, operatorId);
        return new JsonResult(list);
    }
    
    /**
     * 加载打印页面的自定义面单模版
     * @param carrierId 快递公司id
     * @param authorized 是否授权
     * @param ownerId   主账号id
     * @param operatorId 操作人id
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/loadInvoiceTemplate.do")
    public JsonResult loadInvoiceTemplate(BigInteger carrierId, Boolean authorized, BigInteger ownerId, 
            BigInteger operatorId) {
        //查询
        List<PrintTemplate> list = 
                printTemplateService.listPrintTemplateByCarrierId(carrierId, authorized, ownerId, operatorId);
        return new JsonResult(list);
    }
    
    /**
     * 添加或修改订单的快递单号
     * @param orderInfoId 订单id
     * @param trackingNum 快递单号
     * @param authorized 是否授权
     * @param ownerId   主账号id
     * @param operatorId 操作人id
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/addOrModifyTracking.do")
    public JsonResult addOrModifyTracking(BigInteger orderInfoId, BigInteger trackingNum, Boolean authorized, 
            BigInteger ownerId, BigInteger operatorId) {
        OrderInfo orderInfo = 
                printTemplateService.addOrModifyTracking(orderInfoId, trackingNum, authorized, ownerId, operatorId);
        return new JsonResult(orderInfo);
    }
    
    /**
     * 打印面单功能
     * 
     * @param request
     * @param orderInfoId 订单id[]
     * @param printTemplateId 模版id
     * @param authorized 是否授权
     * @param ownerId 主账号id
     * @param operatorId 操作人id
     * @return
     * @author 赵滨
     */
    @ResponseBody
    @RequestMapping("/printTemplate/printTemplate.do")
    public JsonResult printTemplate(HttpServletRequest request, BigInteger[] orderInfoId, BigInteger printTemplateId, 
            Boolean authorized, BigInteger ownerId, BigInteger operatorId) {
        
        //获取项目根目录
        String projectPath = request.getServletContext().getRealPath("/");
        
        /*System.out.println(projectPath);
        for (int i = 0; i < orderInfoId.length; i++) {
            System.out.println(orderInfoId[i]);
        }
        System.out.println(templateType+","+authorized+","+ownerId+","+operatorId);*/
        
        //打印
//        List<OrderInfo> list = printTemplateService.printTemplate(authorized, ownerId, 
//                operatorId, orderInfoId, printTemplateId, projectPath);
//        return new JsonResult(list);
        Map<String, Object> returnMap = printTemplateService.listPrintAll(authorized, ownerId, 
                operatorId, orderInfoId, printTemplateId);
        return new JsonResult(returnMap);
    }
    
}
