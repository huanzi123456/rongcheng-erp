<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>京东code</title>
</head>
<body>
  <table style="border-style: solid;">
    <thead>
      <tr>
        <th>order_id</th>
        <th>vender_id</th>
        <th>pay_type</th>
        <th>order_source</th>
        <th>order_start_time</th>
        <th>consignee_info</th>
        <th>order_payment</th>
        <th>item</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th>等待出库订单${orderResult.orderTotal }</th>
      </tr>
      <c:forEach var="order" items="${orderList }">
        <tr>
          <td>${order.orderId }</td>
          <td>${order.venderId }</td>
          <td>${order.payType }</td>
          <td>${order.orderSource }</td>
          <td>${order.orderStartTime }</td>
          <td>${order.consigneeInfo.fullname }${order.consigneeInfo.telephone }${order.consigneeInfo.fullAddress }</td>
          <td>${order.orderPayment }</td>
          <td>${order.itemInfoList[0].skuId }--${order.itemInfoList[0].wareId }--${order.itemInfoList[0].productNo}</td>
        </tr>
      </c:forEach>
      <tr>
        <th>等待确认收货订单${orderResult2.orderTotal }</th>
      </tr>
      <c:forEach var="order" items="${orderList2 }">
        <tr>
          <td>${order.orderId }</td>
          <td>${order.venderId }</td>
          <td>${order.payType }</td>
          <td>${order.orderSource }</td>
          <td>${order.orderStartTime }</td>
          <td>${order.consigneeInfo.fullname }${order.consigneeInfo.telephone }${order.consigneeInfo.fullAddress }</td>
          <td>${order.orderPayment }</td>
          <td>${order.itemInfoList[0].skuId }--${order.itemInfoList[0].wareId }--${order.itemInfoList[0].productNo}</td>
        </tr>
      </c:forEach>
      <tr>
        <th>等待发货（只适用于海外购商家，等待境内发货 标签下的订单） ${orderResult3.orderTotal }</th>
      </tr>
      <c:forEach var="order" items="${orderList3 }">
        <tr>
          <td>${order.orderId }</td>
          <td>${order.venderId }</td>
          <td>${order.payType }</td>
          <td>${order.orderSource }</td>
          <td>${order.orderStartTime }</td>
          <td>${order.consigneeInfo.fullname }${order.consigneeInfo.telephone }${order.consigneeInfo.fullAddress }</td>
          <td>${order.orderPayment }</td>
          <td>${order.itemInfoList[0].skuId }--${order.itemInfoList[0].wareId }--${order.itemInfoList[0].productNo}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>