<html>
  <head>
    <title>Searched Questions</title>
  </head>
  <body>
  <%@include file="header.jsp" %>
         <div class="recent-questions-panel">
                 <div>
                     <font size="5" color="#888888">Search Result</font>
                 </div>
                 <%@page import="com.forum.repository.Question, java.util.List"%>
                 <% for (Question question : (List<Question>) request.getAttribute("searchList") ) { %>
                         <a href= "question_details?questionId=<%=question.getId()%>" >
                             <% out.println(question.getQuestion()); %>
                         </a>
                     <div class="question-posted-time">
                         <% out.println(question.getUserName()+ "&nbsp;&nbsp;|&nbsp;&nbsp;" + question.getTime());%>
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