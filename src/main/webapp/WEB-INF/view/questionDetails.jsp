<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>question details</title>
         <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
            <title>Welcome</title>
    </head>
<body background-color:#fffff>

	<%@include file="header.jsp" %>

	<div class="recent-questions-panel">
        <div>
           <font size="6" color="#888888"><% out.print(request.getAttribute("question") );%></font>
           <%out.print("<h4>No of Advice: "+request.getAttribute("noOfAnswer") + "</h4>");%>
        </div>
        <%@page import="com.forum.repository.Question, java.util.List"%>
           <font size="4">
          <%int i=1;
          for(String answer : (List<String>)request.getAttribute("answers")) { %>
                 <div class="answer"> <b>
                        <% out.print("Answer&nbsp" + i + " :");%> &nbsp &nbsp</b>
                        <% out.println(answer + "<br/>");%>
                 </div>
          <% i++; } %>
          </font>
    </div>
</body>
</html>