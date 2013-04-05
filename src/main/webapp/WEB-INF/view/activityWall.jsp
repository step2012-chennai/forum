<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>

<body background-color:#fffff>

	<%@include file="header.jsp" %>

	<div class="recent-questions-panel">
        <div>
            <font size="5" color="#888888">Recent Questions</font>
        </div>
        <%@page import="com.forum.repository.Question, java.util.List"%>
        <% for (Question question : (List<Question>) request.getAttribute("questionList") ) { %>
                <a href= "question_details?questionId=<%=question.getId()%>" >
                    <% out.println(question.getQuestion()); %>
                </a>
            <div class="question-posted-time">
                <% out.println(question.getTime()); %>
            </div>
        <%}%>
        <% Integer currentPageNumber = ((Integer)request.getAttribute("pageNumber")); %>
        <div class="navigation-panel">
            <a href="activityWall?pageNumber=${param['pageNumber']-1}" class="button-anchor">
            <input id="previous-button" type="button" value="Previous" ${prevButton}></input></a>
            <a rel="next" href="activityWall?pageNumber=<%= currentPageNumber %>" class="button-anchor" >
            <input id="next-button" type="button" value="Next"  ${nextButton}></a>
            </div>
        </div>
    </div>
</body>
</html>