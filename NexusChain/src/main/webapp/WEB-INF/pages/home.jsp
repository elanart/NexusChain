<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 7/30/2024
  Time: 9:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
    <ul>
        <c:forEach items="${supplier}" var="s">
            <li>${s.name}</li>
        </c:forEach>
    </ul>
</body>
</html>
