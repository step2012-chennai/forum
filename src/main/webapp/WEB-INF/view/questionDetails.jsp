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
           <font size="5" color="black"><b><% out.print(request.getAttribute("question_user") + "</b> Asked :-");%></font>
                      <font size="6" color="#008000"><% out.print(request.getAttribute("question"));%></font>

           <font size="4" color="blue"><%out.print("<h4>No of Advice: "+request.getAttribute("noOfAnswer") + "</h4>");%></font>
        </div>
        <%@page import="com.forum.repository.Question,com.forum.repository.Advice, java.util.List"%>
                 <%int i=1;
                 for(Advice answer : (List<Advice>)request.getAttribute("answers")) { %>
                   <div class="answer" color="blue">
                        <% out.println(answer.getAdvice() + "<br/><br/>");%>
                        <div class="question-posted-time">
                                       <% out.println(answer.getUserName() + "&nbsp&nbsp|&nbsp&nbsp"+ answer.getTime()); %>
                        </div>
                   </div>
                 <% i++; } %>
                 </font>
    </div>
</body>
</html>