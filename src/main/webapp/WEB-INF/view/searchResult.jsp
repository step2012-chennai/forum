<html>
  <head>
    <title>Searched Questions</title>
  </head>
  <body>
  <%@include file="header.jsp" %>
         <div class="recent-questions-panel">
                 <div>
                     <font size="5" color="#888888">Recent Questions</font>
                 </div>
                 <%@page import="com.forum.repository.Question, java.util.List"%>
                 <% for (Question question : (List<Question>) request.getAttribute("searchList") ) { %>
                         <a href= "question_details?questionId=<%=question.getId()%>" >
                             <% out.println(question.getQuestion()); %>
                         </a>
                     <div class="question-posted-time">
                         <% out.println(question.getTime()); %>
                     </div>
                 <%}%>
                 <% Integer currentPageNumber = ((Integer)request.getAttribute("pageNumber")); %>

                 <div class="navigation-panel">
                     <a href="search?basicSearch=${param['basicSearch']}&pageNumber=${param['pageNumber']-1}" class="button-anchor">
                     <input id="previous-button" type="button" value="Previous" ${prevButton}></input></a>
                     <a rel="next" href="search?basicSearch=${param['basicSearch']}&pageNumber=<%= currentPageNumber %> " class="button-anchor" >
                     <input id="next-button" type="button" value="Next"  ${nextButton}></a>
                     <a href="activityWall">
                     <input id="redirect-to-activityWall" type="button" value="activityWall">
                     </input>
                     </a>
                     </div>
                 </div>
             </div>

  </body>
</html>