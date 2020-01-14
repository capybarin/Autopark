<%@ page import="java.util.List" %>
<%@ page import="com.autopark.app.entities.Work" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.autopark.app.entities.User" %>
<%@ page import="com.autopark.app.entities.Bus" %>
<%@ page import="com.autopark.app.entities.Route" %>
<%@ page import="com.autopark.app.database.DatabaseWorker" %><%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 13.01.2020
  Time: 14:44
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
<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Create query</h2>
        </div>
        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>Driver id:
                <input type="number" name="driver" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Route id:
                <input type="number" name="route" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Bus id:
                <input type="number" name="bus" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-light-blue">
    <h2>Current work list</h2>
</div>

<%
    try {
        List<Work> works = (List<Work>) request.getAttribute("workList");
        //List<User> users = (List<User>) request.getAttribute("userList");
        //List<Bus> buses = (List<Bus>) request.getAttribute("busList");
        //List<Route> routes = (List<Route>) request.getAttribute("routeList");
        DatabaseWorker databaseWorker = DatabaseWorker.getInstance();


        if(works != null && !works.isEmpty()){
            out.println("<ul class=\"w3-ul\">");
            for (Work work: works) {
                String tmp = null;
                if (work.getAccepted().equals("N"))
                    tmp = "No";
                if (work.getAccepted().equals("Y"))
                    tmp = "Yes";
                out.println("<li class=\"w3-hover-sand\"><br>Driver: " + databaseWorker.getDriverNameById(work.getId()) + "</br>"
                        + "<br>Route: " + databaseWorker.getRouteNameById(work.getRouteId()) + "</br>"
                        + "<br>Bus №" + databaseWorker.getBusNameById(work.getBusId()) + "</br>"
                        + "<br>Accepted: " + tmp + "</br></li>");
            }
            out.println("</ul>");
        }
    }catch (Exception e){
        out.println("<p><img src="+"images/error.png"+" alt="+"Error"+"></p>");
    }
%>

</body>
</html>
