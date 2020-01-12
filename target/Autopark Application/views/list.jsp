<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: vlad
  Date: 08.01.2020
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Motor pool application</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Users</h2>
        </div>
        <%
            try {
                List<String> names = (List<String>) request.getAttribute("userNames");
                List<String> surnames = (List<String>) request.getAttribute("userSurnames");
                Iterator<String> nameIter = names.iterator();
                Iterator<String> surnameIter = surnames.iterator();

                if (names != null && !names.isEmpty() && surnames != null && !surnames.isEmpty()) {
                    out.println("<ul class=\"w3-ul\">");
                    while (nameIter.hasNext() && surnameIter.hasNext()) {
                        out.print("<li class=\"w3-hover-sand\">" + nameIter.next() + " " + surnameIter.next() + "</li>");
                    }
                    out.println("</ul>");

                } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                        +
                        "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                        "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                        "   <h5>There are no users yet!</h5>\n" +
                        "</div>");
            } catch (Exception e){
                out.println("<p><img src="+"images/error.png"+" alt="+"Error"+"></p>");
            }
        %>
    </div>
</div>

</body>
</html>
