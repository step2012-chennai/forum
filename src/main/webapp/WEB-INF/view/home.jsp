<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Welcome</title>
</head>
<body class="home" >
   	<%@include file="autoRefresh.jsp" %>

    <div id="loginAndRegistration">
        <ul>
            <li><a href="login">&nbsp Login &nbsp</li>        </a>
            <li> &nbsp &nbsp </li>
            <li><a href="home">&nbsp Registration &nbsp</li> </a>
        </ul>
    </div>

    <div id="logoSpace">
    </div>

    <div id="fiveRecentQuestions">

        <%@page import="com.forum.repository.Question, java.util.List"%>
                <% for (Question question : (List<Question>) request.getAttribute("questionList") ) { %>
                        <a href= "question_details?questionId=<%=question.getId()%>" >
                            <% out.println(question.getQuestion()); %>
                        </a>
                    <div class="question-posted-time">
                        <% out.println(question.getUserName()+ "&nbsp;&nbsp;|&nbsp;&nbsp;" + question.getTime());%>
                    </div>
                <%}%>
    </div>

    <div id="fiveRecentAdvices">

    </div>
</body>
</html>
