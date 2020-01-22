<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 13.01.2020
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quest list</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Motor pool application</h1>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Current work list</h2>
        </div>
        <c:forEach items="${workList}" var="work">
            <ul class="w3-ul">
                <li class="w3-hover-sand">
                    <h5>Work id: ${work.workId}</h5>
                    <h5>Route: ${work.routeName}</h5>
                    <h5>Bus №${work.busName}</h5>
                    <h5>Accepted: ${work.accepted}</h5>
                    <form method="post" class="w3-selection w3-light-grey w3-padding">
                        <button type="submit" name="id" class="w3-btn w3-green w3-round-large w3-margin-bottom" value=${work.workId}>AcceptBtn</button>
                    </form>
                </li>
            </ul>
        </c:forEach>
    </div>
</div>
</body>
</html>
