<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Answers</title>
</head>

<body>
<div id="wrapper">
	<%@include file="homeHeader.jsp" %>
	<div class="recent-questions-panel">
        <div class="list-heading">
           My Answers
        </div>
        <%@page import="com.forum.domain.Question, java.util.List"%>
        <% for (Question question : (List<Question>) request.getAttribute("questions") ) { %>
                <a href= "question_details?questionId=<%=question.getId()%>" >
                    <% out.println(question.getQuestion()); %>
                </a>
            <div class="question-posted-time">
                <% out.println(question.getUserName()+ "&nbsp&nbsp|&nbsp&nbsp" + question.getTime());%>
            </div>
        <%}%>
    </div>
    </div>
</body>
</html>