<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>京东code</title>
</head>
<body>
  code：${param.code }<br>
  state：${param.state }<br>
  accessToken：${accessToken }<br>
  <a href="/jd/order.do">部分订单</a>
</body>
</html>