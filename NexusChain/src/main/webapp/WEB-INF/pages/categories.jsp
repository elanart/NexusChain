<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: elana
  Date: 7/31/2024
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh Mục</title>
</head>
<body>
    <ul>
        <c:forEach items="${cates}" var="c">
            <li>
                ${c.name}
            </li>
        </c:forEach>
    </ul>
</body>
</html>
