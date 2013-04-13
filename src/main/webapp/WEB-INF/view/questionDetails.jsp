<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
   <head>
      <title>question details</title>
      <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
   </head>
<body>
 <script type="text/javascript" src="./static/tiny_mce/tiny_mce.js"></script>
 <script type="text/javascript" src="./static/javascript/tiny_mce_init.js"></script>
 <script type="text/javascript" src="./static/javascript/reset.js"></script>
 <%@include file="header.jsp" %>

 <div class="recent-questions-panel">
    <div>
       <font size="5" color="black"><b><% out.print(request.getAttribute("question_user") + "</b> Asked :-");%></font>
       <font size="6" color="#008000"><% out.print(request.getAttribute("question"));%></font>
       <% String q_id =request.getParameter("questionId"); %>

       <div style="padding-left:5px"><font size=5>Post your Advice In the Text Box:</font></center></div>
            <form id="postAdvice" method="post" action ="postedAdvice?questionId=<%=q_id%>">
                <div style="padding-left:5px">
                <div class="error-color"> ${error} </div>
                <div>
                <textarea id="elm1" name="textareas" rows="5" cols="12" style="width: 60%"/>
                ${GivenAnswer}
                </textarea>   </center>
                </div><br>
                <div style="padding-left:495px">
                <input type="submit" id="post" value="Post">
                <input type="button" id="reset" onclick="clearTextbox()" value="Reset">
                </div>
                </div>
            </form>
           <font size="4" color="blue"><%out.print("<h4>No of Advice: "+request.getAttribute("noOfAnswer") + "</h4>");%></font>
       </div>

       <%@page import="com.forum.domain.Question,com.forum.domain.Advice, java.util.List"%>
       <font color="blue">
            <% int i=1;
            for(Advice answer : (List<Advice>)request.getAttribute("answers")) { %>
            <div class="answer" color="blue">
            <% out.println(answer.getAdvice()); %>
            <div class="question-posted-time">
            <% out.println(answer.getUserName() + "&nbsp&nbsp|&nbsp&nbsp"+ answer.getTime()); %>
            </div>
            </div>
            <% i++; } %>
       </font>
 </div>
</body>
</html>