<%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 10.01.2020
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Motor pool application</h1>
</div>
<div>
    <p style="text-align: center"><button class="btn_st w3-btn w3-hover-green w3-round-large" onclick="location.href='/userRouteList'">All routes list</button>
        <button class="btn_st w3-btn w3-hover-green w3-round-large" onclick="location.href='/adminDriverList'">All drivers list</button>
        <button class="btn_st w3-btn w3-hover-green w3-round-large" onclick="location.href='/adminBusList'">All buses list</button>
        <button class="btn_st w3-btn w3-hover-green w3-round-large" onclick="location.href='/adminQuery'">Query page</button></p>
</div>
</body>
</html>
