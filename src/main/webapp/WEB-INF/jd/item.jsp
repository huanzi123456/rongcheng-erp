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
        <th>wareId</th>
        <th>title</th>
        <th>categoryId</th>
        <th>brandId</th>
        <th>wareStatus</th>
        <th>marketPrice</th>
        <td>costPrice</td>
        <td>jdPrice</td>
        <th>stockNum</th>
        <th>logo</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th>有效商品${page.totalItem }-${page.pageNo }-${page.pageSize }</th>
      </tr>
      <c:forEach var="p" items="${page.data }">
        <tr>
          <td>${p.wareId }</td>
          <td>${p.title }</td>
          <td>${p.categoryId }</td>
          <td>${p.brandId }</td>
          <td>${p.wareStatus }</td>
          <td>${p.marketPrice }</td>
          <td>${p.costPrice }</td>
          <td>${p.jdPrice }</td>
          <td>${p.stockNum }</td>
          <td><img src="http://img13.360buyimg.com/n1/${p.logo }"/></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>