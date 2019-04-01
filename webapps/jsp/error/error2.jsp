<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>FOUR SEEDS Portal Site</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="text-align:left">
<div class="container">
message: ${message }<br>

<c:forEach items="${listmsg }" var="item">
	${item }<br>
</c:forEach>
</div>
</body>
</html>