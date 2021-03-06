<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 12.01.2020
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminBusList</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Motor pool application</h1>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Buses</h2>
        </div>
        <c:forEach items="${buses}" var="bus">
            <ul class="w3-ul">
                <li class="w3-hover-sand">
                    [${bus.id}] ${bus.name} [${bus.activity}]
                </li>
            </ul>
        </c:forEach>
    </div>
</div>
</body>
</html>
