<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Searched Questions</title>
  </head>
  <body>
  <%@include file="header.jsp" %>
         <div class="recent-questions-panel">
                 <%@page import="com.forum.domain.Question, java.util.List"%>
                 <%List<Question> questions = (List<Question>) request.getAttribute("searchList");
                     %> <div class="list-heading">${message}</div><%
                     for (Question question : questions ) { %>
                         <a href= "question_details?questionId=<%=question.getId()%>" >
                             <% out.println(question.getQuestion()); %>
                         </a>
                     <div class="question-posted-time">
                     <% out.println(question.getUserName()+ "&nbsp;&nbsp;|&nbsp;&nbsp;" + question.getTime() + "&nbsp;&nbsp;&nbsp;&nbsp;");%>
                 <%String str = question.getTags();%>
                 <a href="tagsearch?tag=<%=str%>"><lable><b> <%out.println(str);%> </b></lable></a>
                 </div>
                 <%}%>
                 <% Integer currentPageNumber = ((Integer)request.getAttribute("pageNumber")); %>

                 <div class="navigation-panel">
                     <input type="button" id="previous-button" value="Previous" onclick="javascript:window.location.href='search?basicSearch=${param['basicSearch']}&pageNumber=${param['pageNumber']-1}'" ${prevButton} ></input>
                     <input type="button" id="next-button" value="Next" onclick="javascript:window.location.href='search?basicSearch=${param['basicSearch']}&pageNumber=<%= currentPageNumber %>'" ${nextButton} ></input>
                     </div>
                 </div>
             </div>

  </body>
</html>