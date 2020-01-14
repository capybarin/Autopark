<%@ page import="java.util.List" %>
<%@ page import="com.autopark.app.entities.Work" %>
<%@ page import="com.autopark.app.database.DatabaseWorker" %><%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 13.01.2020
  Time: 16:54
  To change this template use File | Settings | File Templates.


  Передвать ид при логине в database worker и с помощью него делать селект по заданиям
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
        <%
            try {
                List<Work> works = (List<Work>) request.getAttribute("workList");
                DatabaseWorker databaseWorker = DatabaseWorker.getInstance();

                if(works != null && !works.isEmpty()){
                    out.println("<ul class=\"w3-ul\">");
                    for (Work work: works) {
                        out.println("<li class=\"w3-hover-sand\">"
                                + "<br>Route: " + databaseWorker.getRouteNameById(work.getRouteId()) + "</br>"
                                + "<br>Bus №" + databaseWorker.getBusNameById(work.getBusId()) + "</br>"
                                + "<br>Accepted: " + work.getAccepted() + "o</br>" +
                                "<br><form method=\"post\"><button type=\"submit\" class=\"w3-btn w3-green w3-round-large w3-margin-bottom\">Accept</button></form></br></li>");
                    }
                    out.println("</ul>");
                }
            }catch (Exception e){
                out.println("<p><img src="+"images/error.png"+" alt="+"Error"+"></p>");
            }
        %>
    </div>
</div>
</body>
</html>
