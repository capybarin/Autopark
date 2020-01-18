<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 09.01.2020
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Motor pool application</h1>
</div>
<div class="w3-container w3-padding">
    <c:if test="${userName != null}">
        <c:if test="${role == 'U'}">
            <div class="w3-panel w3-green w3-display-container w3-card-4 w3-round">
                <h1>Hi ${userName}!</h1>
                <button class="w3-btn w3-hover-red w3-round-large" onclick="location.href='views/userPage.jsp'"><h5>Press me to continue</h5></button>
            </div>
        </c:if>
        <c:if test="${role == 'A'}">
            <div class="w3-panel w3-green w3-display-container w3-card-4 w3-round">
                <h1>Hi ${userName}!</h1>
                <button class="w3-btn w3-hover-red w3-round-large" onclick="location.href='views/adminPage.jsp'"><h5>Press me to continue</h5></button>
            </div>
        </c:if>
    </c:if>

    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Logging in</h2>
        </div>
        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>Login:
                <input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Password:
                <input type="password" name="pass" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Log in</button>
        </form>
    </div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
