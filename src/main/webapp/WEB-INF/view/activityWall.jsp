<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Activity Wall</title>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css"/>
</head>

<body>
    <div id="wrapper">
    <div id="colorForHead">
	<%@include file="homeHeader.jsp" %>
	<%@include file="autoRefresh.jsp" %>

	<div class="recent-questions-panel">
        <div class="list-heading">
            Recent Questions
        </div>
        <%@page import="com.forum.domain.Question, java.util.List"%>
        <% for (Question question : (List<Question>) request.getAttribute("questionList") ) { %>
                <a href= "question_details?questionId=<%=question.getId()%>" >
                    <% out.println(question.getQuestion()); %>
                </a>
            <div class="question-posted-time">
                <% out.println(question.getUserName()+ "&nbsp;&nbsp;|&nbsp;&nbsp;" + question.getTime() + "&nbsp;&nbsp;&nbsp;&nbsp;");%>
                <%String str = question.getTags();%>
                <%String[] t1 = str.split(" ");%>
                <%for (String s : t1) { %>
                <a href="tagsearch?tag=<%=s%>"><lable><b> <%out.println(s);%> </b></lable></a>
                <%}%>
            </div>
        <%}%>
        <% Integer currentPageNumber = ((Integer)request.getAttribute("pageNumber")); %>
        <div class="navigation-panel">
            <input type="button" id="previous-button" value="Previous" onclick="javascript:window.location.href='activityWall?pageNumber=${param['pageNumber']-1}'" ${prevButton} ></input>
            <input type="button" id="next-button" value="Next" onclick="javascript:window.location.href='activityWall?pageNumber=<%= currentPageNumber %>'" ${nextButton} ></input>
        </div>
        </div>
        </div>
    </div>
</body>
</html>