package com.rongcheng.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
/**
 * 订单信息表
 * @author 赵滨
 *
 */
public class OrderInfo implements Serializable{
    //版本号
    private static final long serialVersionUID = 868324195180940445L;

    //1.订单ID  非空  非负  长度20
    private BigInteger id;
    
    //2.erp订单编号  非空  长度12  char
    private String erpOrderNum;
    
    //3.平台（来源）订单ID  非空  非负  长度20
    private BigInteger platformOrderId;
    
    //4.来源（平台）编码  非空  非负  长度20
    private BigInteger platformId;
    
    //5.所属店铺ID  非空  非负  长度20
    private BigInteger shopId;
    
    //6.订单类型  非空  非负  长度1
    private Boolean onlineOrder;
    
    //7.平台买家编码  非空  非负  长度20
    private BigInteger platformBuyerId;
    
    //8.快递id  非负  长度20
    private BigInteger carrierId;
    
    //9.是否电子面单  非负  长度1  快递面单类型：电子面单和普通面单
    private Boolean electronicWaybill;
    
    //10.快递单号  长度10
    private BigInteger trackingNum;
    
    //11.买家留言  长度100
    private String buyerWord;
    
    //12.卖家备注  长度100
    private String sellerWord;
    
    //13.订单创建时间  非空  datetime  下单时间
    private Timestamp orderCreate;
    
    //14.订单付款时间  datetime
    private Timestamp orderPay;
    
    //15.订单下载时间  datetime
    private Timestamp orderDownload;
    
    //16.订单编辑时间  datetime  同时用于判断订单是否已编辑
    private Timestamp orderEdit;
    
    //17.订单回传时间  datetime
    private Timestamp orderUpload;
    
    //18.订单合并时间
    private Timestamp orderMerge;
    
    //19.订单拆分时间 
    private Timestamp orderSplit;
    
    //20.订单审核时间  datetime
    private Timestamp orderCheck;
    
    //21.快递单打印时间  datetime  同时用于判断快递单是否已打印
    private Timestamp expressSheetPrint;
    
    //22.发货单打印时间  datetime  同时用于判断发货单是否已打印
    private Timestamp saleBillPrint;
    
    //23.订单出库时间  datetime  对应天猫国际等
    private Timestamp orderOutbound;
    
    //24.发货查验订单时间 datetime
    private Timestamp orderInspect;
    
    //25.订单发货时间  datetime
    private Timestamp orderDeparture;
    
    //26.申请退款时间
    private Timestamp refundApply;
    
    //27.订单锁定时间
    private Timestamp orderLocked;
    
    //28.订单关闭时间
    private Timestamp orderClosed;
    
    //29.订单完成（签收）时间
    private Timestamp orderCompleted;
    
    //30.订单状态类型
    private Integer orderStatus;
    
    //31.退款标记
    private Integer refundClose;
    
    //32.订单锁定标记  非负  长度1
    private Boolean orderHold;
    
    //33.订单邮费  非负  长度8  小数2  运费
    private BigDecimal shippingFee;
    
    //34.是否运费（邮费）到付  非负  长度1  分成运费预付、运费到付
    private Boolean postagePrepaid;
    
    //35.是否货到付款  到付  非负  长度1  分货款预付和货款到付
    private Boolean orderCod;
    
    //36.是否特殊订单  非负  长度1  紧急订单，暂缓发货订单等
    private Boolean orderSpecial;
    
    //37.订单处理批次流水号  长度20
    private String lotCode;
    
    //38.发票类型
    private Integer invoiceType;
    
    //39.发票抬头  长度20
    private String invoiceTitle;
    
    //40.纳税人识别号
    private BigInteger taxpayerId;
    
    //41.发票明细  长度20
    private String invoiceDetail;
    
    //42.订单追踪反馈标志  非负  长度1  分未反馈、已反馈、反馈中、反馈失败、订单关闭等状态
    private Integer orderFeedbackCode;
    
    //43.自定义内容1  长度30
    private String reserved1;
    
    //44.自定义内容2  长度30
    private String reserved2;
    
    //45.备注  长度100
    private String note;
    
    //46.订单秘钥  长度20  用于分销管理
    private String orderSecretKey;
    
    //47.用户主账户ID  非空  非负  长度20  数据表所有者
    private BigInteger ownerId;
    
    //48.操作人  非空  非负  长度20
    private BigInteger operatorId;
    
    //49.是否授权  非空 非负  长度1
    private Boolean authorized;
    
    //50.记录创建时间  非空  datetime
    private Timestamp gmtCreate;
    
    //51.记录修改时间  datetime
    private Timestamp gmtModified;

    
    //无参数的构造器
    public OrderInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
  
    //有参数的构造器
    public OrderInfo(BigInteger id, String erpOrderNum, BigInteger platformOrderId, BigInteger platformId,
            BigInteger shopId, Boolean onlineOrder, BigInteger platformBuyerId, BigInteger carrierId,
            Boolean electronicWaybill, BigInteger trackingNum, String buyerWord, String sellerWord, Timestamp orderCreate,
            Timestamp orderPay, Timestamp orderDownload, Timestamp orderEdit, Timestamp orderUpload,
            Timestamp orderMerge, Timestamp orderSplit, Timestamp orderCheck, Timestamp expressSheetPrint,
            Timestamp saleBillPrint, Timestamp orderOutbound, Timestamp orderInspect, Timestamp orderDeparture,
            Timestamp refundApply, Timestamp orderLocked, Timestamp orderClosed, Timestamp orderCompleted,
            Integer orderStatus, Integer refundClose, Boolean orderHold, BigDecimal shippingFee, Boolean postagePrepaid,
            Boolean orderCod, Boolean orderSpecial, String lotCode, Integer invoiceType, String invoiceTitle,
            BigInteger taxpayerId, String invoiceDetail, Integer orderFeedbackCode, String reserved1, String reserved2,
            String note, String orderSecretKey, BigInteger ownerId, BigInteger operatorId, Boolean authorized,
            Timestamp gmtCreate, Timestamp gmtModified) {
        super();
        this.id = id;
        this.erpOrderNum = erpOrderNum;
        this.platformOrderId = platformOrderId;
        this.platformId = platformId;
        this.shopId = shopId;
        this.onlineOrder = onlineOrder;
        this.platformBuyerId = platformBuyerId;
        this.carrierId = carrierId;
        this.electronicWaybill = electronicWaybill;
        this.trackingNum = trackingNum;
        this.buyerWord = buyerWord;
        this.sellerWord = sellerWord;
        this.orderCreate = orderCreate;
        this.orderPay = orderPay;
        this.orderDownload = orderDownload;
        this.orderEdit = orderEdit;
        this.orderUpload = orderUpload;
        this.orderMerge = orderMerge;
        this.orderSplit = orderSplit;
        this.orderCheck = orderCheck;
        this.expressSheetPrint = expressSheetPrint;
        this.saleBillPrint = saleBillPrint;
        this.orderOutbound = orderOutbound;
        this.orderInspect = orderInspect;
        this.orderDeparture = orderDeparture;
        this.refundApply = refundApply;
        this.orderLocked = orderLocked;
        this.orderClosed = orderClosed;
        this.orderCompleted = orderCompleted;
        this.orderStatus = orderStatus;
        this.refundClose = refundClose;
        this.orderHold = orderHold;
        this.shippingFee = shippingFee;
        this.postagePrepaid = postagePrepaid;
        this.orderCod = orderCod;
        this.orderSpecial = orderSpecial;
        this.lotCode = lotCode;
        this.invoiceType = invoiceType;
        this.invoiceTitle = invoiceTitle;
        this.taxpayerId = taxpayerId;
        this.invoiceDetail = invoiceDetail;
        this.orderFeedbackCode = orderFeedbackCode;
        this.reserved1 = reserved1;
        this.reserved2 = reserved2;
        this.note = note;
        this.orderSecretKey = orderSecretKey;
        this.ownerId = ownerId;
        this.operatorId = operatorId;
        this.authorized = authorized;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    //创建的setter和getter方法
    public BigInteger getId() {
        return id;
    }


    public void setId(BigInteger id) {
        this.id = id;
    }


    public String getErpOrderNum() {
        return erpOrderNum;
    }


    public void setErpOrderNum(String erpOrderNum) {
        this.erpOrderNum = erpOrderNum;
    }


    public BigInteger getPlatformOrderId() {
        return platformOrderId;
    }


    public void setPlatformOrderId(BigInteger platformOrderId) {
        this.platformOrderId = platformOrderId;
    }


    public BigInteger getPlatformId() {
        return platformId;
    }


    public void setPlatformId(BigInteger platformId) {
        this.platformId = platformId;
    }


    public BigInteger getShopId() {
        return shopId;
    }


    public void setShopId(BigInteger shopId) {
        this.shopId = shopId;
    }


    public Boolean getOnlineOrder() {
        return onlineOrder;
    }


    public void setOnlineOrder(Boolean onlineOrder) {
        this.onlineOrder = onlineOrder;
    }


    public BigInteger getPlatformBuyerId() {
        return platformBuyerId;
    }


    public void setPlatformBuyerId(BigInteger platformBuyerId) {
        this.platformBuyerId = platformBuyerId;
    }


    public BigInteger getCarrierId() {
        return carrierId;
    }


    public void setCarrierId(BigInteger carrierId) {
        this.carrierId = carrierId;
    }


    public Boolean getElectronicWaybill() {
        return electronicWaybill;
    }


    public void setElectronicWaybill(Boolean electronicWaybill) {
        this.electronicWaybill = electronicWaybill;
    }


    public BigInteger getTrackingNum() {
        return trackingNum;
    }


    public void setTrackingNum(BigInteger trackingNum) {
        this.trackingNum = trackingNum;
    }


    public String getBuyerWord() {
        return buyerWord;
    }


    public void setBuyerWord(String buyerWord) {
        this.buyerWord = buyerWord;
    }


    public String getSellerWord() {
        return sellerWord;
    }


    public void setSellerWord(String sellerWord) {
        this.sellerWord = sellerWord;
    }


    public Timestamp getOrderCreate() {
        return orderCreate;
    }


    public void setOrderCreate(Timestamp orderCreate) {
        this.orderCreate = orderCreate;
    }


    public Timestamp getOrderPay() {
        return orderPay;
    }


    public void setOrderPay(Timestamp orderPay) {
        this.orderPay = orderPay;
    }


    public Timestamp getOrderDownload() {
        return orderDownload;
    }


    public void setOrderDownload(Timestamp orderDownload) {
        this.orderDownload = orderDownload;
    }


    public Timestamp getOrderEdit() {
        return orderEdit;
    }


    public void setOrderEdit(Timestamp orderEdit) {
        this.orderEdit = orderEdit;
    }


    public Timestamp getOrderUpload() {
        return orderUpload;
    }


    public void setOrderUpload(Timestamp orderUpload) {
        this.orderUpload = orderUpload;
    }


    public Timestamp getOrderMerge() {
        return orderMerge;
    }


    public void setOrderMerge(Timestamp orderMerge) {
        this.orderMerge = orderMerge;
    }


    public Timestamp getOrderSplit() {
        return orderSplit;
    }


    public void setOrderSplit(Timestamp orderSplit) {
        this.orderSplit = orderSplit;
    }


    public Timestamp getOrderCheck() {
        return orderCheck;
    }


    public void setOrderCheck(Timestamp orderCheck) {
        this.orderCheck = orderCheck;
    }


    public Timestamp getExpressSheetPrint() {
        return expressSheetPrint;
    }


    public void setExpressSheetPrint(Timestamp expressSheetPrint) {
        this.expressSheetPrint = expressSheetPrint;
    }


    public Timestamp getSaleBillPrint() {
        return saleBillPrint;
    }


    public void setSaleBillPrint(Timestamp saleBillPrint) {
        this.saleBillPrint = saleBillPrint;
    }


    public Timestamp getOrderOutbound() {
        return orderOutbound;
    }


    public void setOrderOutbound(Timestamp orderOutbound) {
        this.orderOutbound = orderOutbound;
    }


    public Timestamp getOrderInspect() {
        return orderInspect;
    }


    public void setOrderInspect(Timestamp orderInspect) {
        this.orderInspect = orderInspect;
    }


    public Timestamp getOrderDeparture() {
        return orderDeparture;
    }


    public void setOrderDeparture(Timestamp orderDeparture) {
        this.orderDeparture = orderDeparture;
    }


    public Timestamp getRefundApply() {
        return refundApply;
    }


    public void setRefundApply(Timestamp refundApply) {
        this.refundApply = refundApply;
    }


    public Timestamp getOrderLocked() {
        return orderLocked;
    }


    public void setOrderLocked(Timestamp orderLocked) {
        this.orderLocked = orderLocked;
    }


    public Timestamp getOrderClosed() {
        return orderClosed;
    }


    public void setOrderClosed(Timestamp orderClosed) {
        this.orderClosed = orderClosed;
    }


    public Timestamp getOrderCompleted() {
        return orderCompleted;
    }


    public void setOrderCompleted(Timestamp orderCompleted) {
        this.orderCompleted = orderCompleted;
    }


    public Integer getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }


    public Boolean getOrderHold() {
        return orderHold;
    }


    public void setOrderHold(Boolean orderHold) {
        this.orderHold = orderHold;
    }


    public BigDecimal getShippingFee() {
        return shippingFee;
    }


    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }


    public Boolean getPostagePrepaid() {
        return postagePrepaid;
    }


    public void setPostagePrepaid(Boolean postagePrepaid) {
        this.postagePrepaid = postagePrepaid;
    }


    public Boolean getOrderCod() {
        return orderCod;
    }


    public void setOrderCod(Boolean orderCod) {
        this.orderCod = orderCod;
    }


    public Boolean getOrderSpecial() {
        return orderSpecial;
    }


    public void setOrderSpecial(Boolean orderSpecial) {
        this.orderSpecial = orderSpecial;
    }


    public String getLotCode() {
        return lotCode;
    }


    public void setLotCode(String lotCode) {
        this.lotCode = lotCode;
    }


    public Integer getInvoiceType() {
        return invoiceType;
    }


    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }


    public String getInvoiceTitle() {
        return invoiceTitle;
    }


    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }


    public BigInteger getTaxpayerId() {
        return taxpayerId;
    }


    public void setTaxpayerId(BigInteger taxpayerId) {
        this.taxpayerId = taxpayerId;
    }


    public String getInvoiceDetail() {
        return invoiceDetail;
    }


    public void setInvoiceDetail(String invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
    }


    public Integer getOrderFeedbackCode() {
        return orderFeedbackCode;
    }


    public void setOrderFeedbackCode(Integer orderFeedbackCode) {
        this.orderFeedbackCode = orderFeedbackCode;
    }


    public String getReserved1() {
        return reserved1;
    }


    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }


    public String getReserved2() {
        return reserved2;
    }


    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }


    public String getNote() {
        return note;
    }


    public void setNote(String note) {
        this.note = note;
    }


    public String getOrderSecretKey() {
        return orderSecretKey;
    }


    public void setOrderSecretKey(String orderSecretKey) {
        this.orderSecretKey = orderSecretKey;
    }


    public BigInteger getOwnerId() {
        return ownerId;
    }


    public void setOwnerId(BigInteger ownerId) {
        this.ownerId = ownerId;
    }


    public BigInteger getOperatorId() {
        return operatorId;
    }


    public void setOperatorId(BigInteger operatorId) {
        this.operatorId = operatorId;
    }


    public Boolean getAuthorized() {
        return authorized;
    }


    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }


    public Timestamp getGmtCreate() {
        return gmtCreate;
    }


    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


    public Timestamp getGmtModified() {
        return gmtModified;
    }


    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getRefundClose() {
        return refundClose;
    }

    public void setRefundClose(Integer refundClose) {
        this.refundClose = refundClose;
    }

    //重写hashCode
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((authorized == null) ? 0 : authorized.hashCode());
        result = prime * result + ((buyerWord == null) ? 0 : buyerWord.hashCode());
        result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
        result = prime * result + ((electronicWaybill == null) ? 0 : electronicWaybill.hashCode());
        result = prime * result + ((erpOrderNum == null) ? 0 : erpOrderNum.hashCode());
        result = prime * result + ((expressSheetPrint == null) ? 0 : expressSheetPrint.hashCode());
        result = prime * result + ((gmtCreate == null) ? 0 : gmtCreate.hashCode());
        result = prime * result + ((gmtModified == null) ? 0 : gmtModified.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((invoiceDetail == null) ? 0 : invoiceDetail.hashCode());
        result = prime * result + ((invoiceTitle == null) ? 0 : invoiceTitle.hashCode());
        result = prime * result + ((invoiceType == null) ? 0 : invoiceType.hashCode());
        result = prime * result + ((lotCode == null) ? 0 : lotCode.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((onlineOrder == null) ? 0 : onlineOrder.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((orderCheck == null) ? 0 : orderCheck.hashCode());
        result = prime * result + ((orderClosed == null) ? 0 : orderClosed.hashCode());
        result = prime * result + ((orderCod == null) ? 0 : orderCod.hashCode());
        result = prime * result + ((orderCompleted == null) ? 0 : orderCompleted.hashCode());
        result = prime * result + ((orderCreate == null) ? 0 : orderCreate.hashCode());
        result = prime * result + ((orderDeparture == null) ? 0 : orderDeparture.hashCode());
        result = prime * result + ((orderDownload == null) ? 0 : orderDownload.hashCode());
        result = prime * result + ((orderEdit == null) ? 0 : orderEdit.hashCode());
        result = prime * result + ((orderFeedbackCode == null) ? 0 : orderFeedbackCode.hashCode());
        result = prime * result + ((orderHold == null) ? 0 : orderHold.hashCode());
        result = prime * result + ((orderInspect == null) ? 0 : orderInspect.hashCode());
        result = prime * result + ((orderLocked == null) ? 0 : orderLocked.hashCode());
        result = prime * result + ((orderMerge == null) ? 0 : orderMerge.hashCode());
        result = prime * result + ((orderOutbound == null) ? 0 : orderOutbound.hashCode());
        result = prime * result + ((orderPay == null) ? 0 : orderPay.hashCode());
        result = prime * result + ((orderSecretKey == null) ? 0 : orderSecretKey.hashCode());
        result = prime * result + ((orderSpecial == null) ? 0 : orderSpecial.hashCode());
        result = prime * result + ((orderSplit == null) ? 0 : orderSplit.hashCode());
        result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
        result = prime * result + ((orderUpload == null) ? 0 : orderUpload.hashCode());
        result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
        result = prime * result + ((platformBuyerId == null) ? 0 : platformBuyerId.hashCode());
        result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
        result = prime * result + ((platformOrderId == null) ? 0 : platformOrderId.hashCode());
        result = prime * result + ((postagePrepaid == null) ? 0 : postagePrepaid.hashCode());
        result = prime * result + ((refundApply == null) ? 0 : refundApply.hashCode());
        result = prime * result + ((refundClose == null) ? 0 : refundClose.hashCode());
        result = prime * result + ((reserved1 == null) ? 0 : reserved1.hashCode());
        result = prime * result + ((reserved2 == null) ? 0 : reserved2.hashCode());
        result = prime * result + ((saleBillPrint == null) ? 0 : saleBillPrint.hashCode());
        result = prime * result + ((sellerWord == null) ? 0 : sellerWord.hashCode());
        result = prime * result + ((shippingFee == null) ? 0 : shippingFee.hashCode());
        result = prime * result + ((shopId == null) ? 0 : shopId.hashCode());
        result = prime * result + ((taxpayerId == null) ? 0 : taxpayerId.hashCode());
        result = prime * result + ((trackingNum == null) ? 0 : trackingNum.hashCode());
        return result;
    }

    //重写equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderInfo other = (OrderInfo) obj;
        if (authorized == null) {
            if (other.authorized != null)
                return false;
        } else if (!authorized.equals(other.authorized))
            return false;
        if (buyerWord == null) {
            if (other.buyerWord != null)
                return false;
        } else if (!buyerWord.equals(other.buyerWord))
            return false;
        if (carrierId == null) {
            if (other.carrierId != null)
                return false;
        } else if (!carrierId.equals(other.carrierId))
            return false;
        if (electronicWaybill == null) {
            if (other.electronicWaybill != null)
                return false;
        } else if (!electronicWaybill.equals(other.electronicWaybill))
            return false;
        if (erpOrderNum == null) {
            if (other.erpOrderNum != null)
                return false;
        } else if (!erpOrderNum.equals(other.erpOrderNum))
            return false;
        if (expressSheetPrint == null) {
            if (other.expressSheetPrint != null)
                return false;
        } else if (!expressSheetPrint.equals(other.expressSheetPrint))
            return false;
        if (gmtCreate == null) {
            if (other.gmtCreate != null)
                return false;
        } else if (!gmtCreate.equals(other.gmtCreate))
            return false;
        if (gmtModified == null) {
            if (other.gmtModified != null)
                return false;
        } else if (!gmtModified.equals(other.gmtModified))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (invoiceDetail == null) {
            if (other.invoiceDetail != null)
                return false;
        } else if (!invoiceDetail.equals(other.invoiceDetail))
            return false;
        if (invoiceTitle == null) {
            if (other.invoiceTitle != null)
                return false;
        } else if (!invoiceTitle.equals(other.invoiceTitle))
            return false;
        if (invoiceType == null) {
            if (other.invoiceType != null)
                return false;
        } else if (!invoiceType.equals(other.invoiceType))
            return false;
        if (lotCode == null) {
            if (other.lotCode != null)
                return false;
        } else if (!lotCode.equals(other.lotCode))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (onlineOrder == null) {
            if (other.onlineOrder != null)
                return false;
        } else if (!onlineOrder.equals(other.onlineOrder))
            return false;
        if (operatorId == null) {
            if (other.operatorId != null)
                return false;
        } else if (!operatorId.equals(other.operatorId))
            return false;
        if (orderCheck == null) {
            if (other.orderCheck != null)
                return false;
        } else if (!orderCheck.equals(other.orderCheck))
            return false;
        if (orderClosed == null) {
            if (other.orderClosed != null)
                return false;
        } else if (!orderClosed.equals(other.orderClosed))
            return false;
        if (orderCod == null) {
            if (other.orderCod != null)
                return false;
        } else if (!orderCod.equals(other.orderCod))
            return false;
        if (orderCompleted == null) {
            if (other.orderCompleted != null)
                return false;
        } else if (!orderCompleted.equals(other.orderCompleted))
            return false;
        if (orderCreate == null) {
            if (other.orderCreate != null)
                return false;
        } else if (!orderCreate.equals(other.orderCreate))
            return false;
        if (orderDeparture == null) {
            if (other.orderDeparture != null)
                return false;
        } else if (!orderDeparture.equals(other.orderDeparture))
            return false;
        if (orderDownload == null) {
            if (other.orderDownload != null)
                return false;
        } else if (!orderDownload.equals(other.orderDownload))
            return false;
        if (orderEdit == null) {
            if (other.orderEdit != null)
                return false;
        } else if (!orderEdit.equals(other.orderEdit))
            return false;
        if (orderFeedbackCode == null) {
            if (other.orderFeedbackCode != null)
                return false;
        } else if (!orderFeedbackCode.equals(other.orderFeedbackCode))
            return false;
        if (orderHold == null) {
            if (other.orderHold != null)
                return false;
        } else if (!orderHold.equals(other.orderHold))
            return false;
        if (orderInspect == null) {
            if (other.orderInspect != null)
                return false;
        } else if (!orderInspect.equals(other.orderInspect))
            return false;
        if (orderLocked == null) {
            if (other.orderLocked != null)
                return false;
        } else if (!orderLocked.equals(other.orderLocked))
            return false;
        if (orderMerge == null) {
            if (other.orderMerge != null)
                return false;
        } else if (!orderMerge.equals(other.orderMerge))
            return false;
        if (orderOutbound == null) {
            if (other.orderOutbound != null)
                return false;
        } else if (!orderOutbound.equals(other.orderOutbound))
            return false;
        if (orderPay == null) {
            if (other.orderPay != null)
                return false;
        } else if (!orderPay.equals(other.orderPay))
            return false;
        if (orderSecretKey == null) {
            if (other.orderSecretKey != null)
                return false;
        } else if (!orderSecretKey.equals(other.orderSecretKey))
            return false;
        if (orderSpecial == null) {
            if (other.orderSpecial != null)
                return false;
        } else if (!orderSpecial.equals(other.orderSpecial))
            return false;
        if (orderSplit == null) {
            if (other.orderSplit != null)
                return false;
        } else if (!orderSplit.equals(other.orderSplit))
            return false;
        if (orderStatus == null) {
            if (other.orderStatus != null)
                return false;
        } else if (!orderStatus.equals(other.orderStatus))
            return false;
        if (orderUpload == null) {
            if (other.orderUpload != null)
                return false;
        } else if (!orderUpload.equals(other.orderUpload))
            return false;
        if (ownerId == null) {
            if (other.ownerId != null)
                return false;
        } else if (!ownerId.equals(other.ownerId))
            return false;
        if (platformBuyerId == null) {
            if (other.platformBuyerId != null)
                return false;
        } else if (!platformBuyerId.equals(other.platformBuyerId))
            return false;
        if (platformId == null) {
            if (other.platformId != null)
                return false;
        } else if (!platformId.equals(other.platformId))
            return false;
        if (platformOrderId == null) {
            if (other.platformOrderId != null)
                return false;
        } else if (!platformOrderId.equals(other.platformOrderId))
            return false;
        if (postagePrepaid == null) {
            if (other.postagePrepaid != null)
                return false;
        } else if (!postagePrepaid.equals(other.postagePrepaid))
            return false;
        if (refundApply == null) {
            if (other.refundApply != null)
                return false;
        } else if (!refundApply.equals(other.refundApply))
            return false;
        if (refundClose == null) {
            if (other.refundClose != null)
                return false;
        } else if (!refundClose.equals(other.refundClose))
            return false;
        if (reserved1 == null) {
            if (other.reserved1 != null)
                return false;
        } else if (!reserved1.equals(other.reserved1))
            return false;
        if (reserved2 == null) {
            if (other.reserved2 != null)
                return false;
        } else if (!reserved2.equals(other.reserved2))
            return false;
        if (saleBillPrint == null) {
            if (other.saleBillPrint != null)
                return false;
        } else if (!saleBillPrint.equals(other.saleBillPrint))
            return false;
        if (sellerWord == null) {
            if (other.sellerWord != null)
                return false;
        } else if (!sellerWord.equals(other.sellerWord))
            return false;
        if (shippingFee == null) {
            if (other.shippingFee != null)
                return false;
        } else if (!shippingFee.equals(other.shippingFee))
            return false;
        if (shopId == null) {
            if (other.shopId != null)
                return false;
        } else if (!shopId.equals(other.shopId))
            return false;
        if (taxpayerId == null) {
            if (other.taxpayerId != null)
                return false;
        } else if (!taxpayerId.equals(other.taxpayerId))
            return false;
        if (trackingNum == null) {
            if (other.trackingNum != null)
                return false;
        } else if (!trackingNum.equals(other.trackingNum))
            return false;
        return true;
    }

    //重写toString
    @Override
    public String toString() {
        return "OrderInfo [id=" + id + ", erpOrderNum=" + erpOrderNum + ", platformOrderId=" + platformOrderId
                + ", platformId=" + platformId + ", shopId=" + shopId + ", onlineOrder=" + onlineOrder
                + ", platformBuyerId=" + platformBuyerId + ", carrierId=" + carrierId + ", electronicWaybill="
                + electronicWaybill + ", trackingNum=" + trackingNum + ", buyerWord=" + buyerWord + ", sellerWord="
                + sellerWord + ", orderCreate=" + orderCreate + ", orderPay=" + orderPay + ", orderDownload="
                + orderDownload + ", orderEdit=" + orderEdit + ", orderUpload=" + orderUpload + ", orderMerge="
                + orderMerge + ", orderSplit=" + orderSplit + ", orderCheck=" + orderCheck + ", expressSheetPrint="
                + expressSheetPrint + ", saleBillPrint=" + saleBillPrint + ", orderOutbound=" + orderOutbound
                + ", orderInspect=" + orderInspect + ", orderDeparture=" + orderDeparture + ", refundApply="
                + refundApply + ", orderLocked=" + orderLocked + ", orderClosed=" + orderClosed + ", orderCompleted="
                + orderCompleted + ", orderStatus=" + orderStatus + ", refundClose=" + refundClose + ", orderHold="
                + orderHold + ", shippingFee=" + shippingFee + ", postagePrepaid=" + postagePrepaid + ", orderCod="
                + orderCod + ", orderSpecial=" + orderSpecial + ", lotCode=" + lotCode + ", invoiceType=" + invoiceType
                + ", invoiceTitle=" + invoiceTitle + ", taxpayerId=" + taxpayerId + ", invoiceDetail=" + invoiceDetail
                + ", orderFeedbackCode=" + orderFeedbackCode + ", reserved1=" + reserved1 + ", reserved2=" + reserved2
                + ", note=" + note + ", orderSecretKey=" + orderSecretKey + ", ownerId=" + ownerId + ", operatorId="
                + operatorId + ", authorized=" + authorized + ", gmtCreate=" + gmtCreate + ", gmtModified="
                + gmtModified + "]";
    }
}
