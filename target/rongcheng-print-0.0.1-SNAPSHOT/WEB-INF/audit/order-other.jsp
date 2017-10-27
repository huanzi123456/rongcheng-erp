<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>待打印订单</title>  
    <link rel="stylesheet" href="/css/pintuer.css">
    <link rel="stylesheet" href="/css/admin.css">
    <link rel="stylesheet" href="/css/jquery-ui.min.css">
    <link rel="stylesheet" href="/jxb/css/order.css">
    <link rel="stylesheet" href="/css/print.css">
    <link rel="stylesheet" href="/css/logisticsRecord.css">
    <link rel="stylesheet" href="/css/font/iconfont.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/pintuer.js"></script>
    <script src="/util/jiml-utils.js"></script>
    <script src="/util/carrier-utils.js"></script>
    <script src="/util/region/js/jquery.citys.js"></script>
    <script src="/jxb/js/order.js"></script>
    <script src="/jxb/js/order-print.js"></script>
    <script src="/jxb/js/order_AdvancedSearch.js"></script>
    <script type="text/javascript">
    	var isOtherOrder = true;
    </script>
    <script src="../js/big_box.js"></script>
</head>
<body>
  <div class="panel admin-panel" style="margin-bottom: 20px;">
    <div class="panel-head inquire_btn_box">
      <strong class="strong_btn">订单查询</strong>
      <strong class="strong_btn">高级查询</strong>
    </div>
    <div class="inquire_content_box">
      <form id="selectiveForm">
        <c:if test="${orderStatus != null}">
          <!-- 订单状态-->
          <input id="orderStatus" name="orderStatus" value="${orderStatus }" class="jxb_none">
        </c:if>
        <c:if test="${orderStatusArray != null}">
          <!-- 订单状态-->
          <input name="orderStatusArray" value="7" class="jxb_none">
          <input name="orderStatusArray" value="8" class="jxb_none">
          <input name="orderStatusArray" value="9" class="jxb_none">
        </c:if>
        <c:if test="${refundClose != null}">
          <!-- 退款及关闭状态-->
          <input id="refundClose" name="refundClose" value="${refundClose }" class="jxb_none">
        </c:if>
        <c:if test="${expressSheetPrint != null}">
          <!-- 快递单打印状态-->
          <input id="expressSheetPrint" name="expressSheetPrint" value="${expressSheetPrint }" class="jxb_none">
        </c:if>
        <c:if test="${saleBillPrint != null}">
          <!-- 发货单打印状态-->
          <input id="saleBillPrint" name="saleBillPrint" value="${saleBillPrint }" class="jxb_none">
        </c:if>
        <c:if test="${onlineOrder != null}">
          <!-- 是否线上订单-->
          <input id="onlineOrder" name="onlineOrder" value="${onlineOrder }" class="jxb_none">
        </c:if>
        <c:if test="${orderCod != null}">
          <!-- 是否货到付款-->
          <input id="orderCod" name="orderCod" value="${orderCod }" class="jxb_none">
        </c:if>
        <c:if test="${orderHold != null}">
          <!-- 是否锁定-->
          <input id="orderHold" name="orderHold" value="${orderHold }" class="jxb_none">
        </c:if>
        <jsp:include page="public/search.jsp"></jsp:include>
      </form>
      <form id="advancedForm">
        <c:if test="${orderStatus != null}">
          <!-- 订单状态-->
          <input id="orderStatus" name="orderStatus" value="${orderStatus }" class="jxb_none">
        </c:if>
        <c:if test="${orderStatusArray != null}">
          <!-- 订单状态-->
          <input name="orderStatusArray" value="7" class="jxb_none">
          <input name="orderStatusArray" value="8" class="jxb_none">
          <input name="orderStatusArray" value="9" class="jxb_none">
        </c:if>
        <c:if test="${refundClose != null}">
          <!-- 退款及关闭状态-->
          <input id="refundClose" name="refundClose" value="${refundClose }" class="jxb_none">
        </c:if>
        <c:if test="${expressSheetPrint != null}">
          <!-- 快递单打印状态-->
          <input id="expressSheetPrint" name="expressSheetPrint" value="${expressSheetPrint }" class="jxb_none">
        </c:if>
        <c:if test="${saleBillPrint != null}">
          <!-- 发货单打印状态-->
          <input id="saleBillPrint" name="saleBillPrint" value="${saleBillPrint }" class="jxb_none">
        </c:if>
        <c:if test="${onlineOrder != null}">
          <!-- 是否线上订单-->
          <input id="onlineOrder" name="onlineOrder" value="${onlineOrder }" class="jxb_none">
        </c:if>
        <c:if test="${orderCod != null}">
          <!-- 是否货到付款-->
          <input id="orderCod" name="orderCod" value="${orderCod }" class="jxb_none">
        </c:if>
        <c:if test="${orderHold != null}">
          <!-- 是否锁定-->
          <input id="orderHold" name="orderHold" value="${orderHold }" class="jxb_none">
        </c:if>
        <jsp:include page="public/search-advanced.jsp"></jsp:include>
      </form>
    </div>
  </div>
  <div class="panel admin-panel">
    <div class="panel-head">
	    <strong class="icon-reorder">
  			<c:choose>
  				<c:when test="${param.state==1 }">已打印订单</c:when>
  				<c:when test="${param.state==2 }">待付款订单</c:when>
  				<c:when test="${param.state==3 }">退款中订单</c:when>
  				<c:when test="${param.state==4 }">线下订单</c:when>
  				<c:when test="${param.state==5 }">已发货订单</c:when>
  				<c:when test="${param.state==6 }">货到付款订单</c:when>
  				<c:when test="${param.state==7 }">已关闭的订单</c:when>
  				<c:when test="${param.state==8 }">锁定的订单</c:when>
  			</c:choose>
		  </strong>
    </div>
    <form id="checkbox_form">
      <input id="style" name="style" value="" style="display: none;"/>
      <div class="table table-hover text-center">
        <ul class="table-ul" id="reload">
          <li>系统单号</li>     
          <li>来源</li>  
          <li>买家信息</li>
          <li>收件信息</li> 
          <li>快递</li>  
          <li>物流状态</li>
          <li>留言备注</li>
          <li>快递单号</li>
          <li>更新物流记录</li>
          <li>操作</li>
        </ul>
      
        <ul class="table-ul" style="line-height: normal;">
          <li style="width: 100%;padding-top: 9px;">
            <div id="pagelist" style="height: 50px;">
            	<!-- 页码 -->
            </div>
          </li>
        </ul>
      </div>
    </form>
  </div>
</body>
<script src="/js/print.js"></script>
</html>