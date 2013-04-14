<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Welcome</title>
</head>
<body class="home" >
<sec:authorize access="isAnonymous()">
<div id="loginAndRegistration">
        <ul>
            <li><a href="login">&nbsp Login &nbsp</li>        </a>
            <li>&nbsp</li>
            <li><a href="home">&nbsp Registration &nbsp</li> </a>
        </ul>
    </div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<div id="loginAndRegistration">
    <ul>
        <li><a href="<c:url value="/j_spring_security_logout" />" >&nbsp  Logout &nbsp </a></div></li>
    </ul    >
</div>
</sec:authorize>


    <%@include file="homeHeader.jsp" %>

    <div id="headingSpaceForLogoSpace"></div>

    <div id="topFiveSeekers" style="height: 100px; width: 200px; float: right">
        <H3>Top Five Seekers</H3>
        <%@page import="com.forum.repository.ShowLeaders, java.util.List, com.forum.domain.Leader, com.forum.domain.Question" %>
        <ul>
           <% List<Leader> seekers = (List<Leader>) request.getAttribute("seekerList");
           for (Leader seeker : seekers ){
                    %><li><% out.println(seeker.getUserName()); %></li><%
                }
           %>
        </ul>
        <div id="spaceForTags"></div>
        <div id="topFiveAdvisers" style="height: 100px; width: 200px; float: right" style=";">
                <H3>Top Five Advisors</H3>
                     <ul>
                       <% List<Leader> advisers = (List<Leader>) request.getAttribute("adviserList");
                                  for (Leader adviser : advisers ){
                                           %><li><% out.println(adviser.getUserName()); %></li><%
                                       }
                                  %>
                      </ul>

                    </ul>
            </div>
    </div>

    <div class="recent-questions-panel">
            <div class="list-heading">
        Recently Advised Question
             </div>
             <br>
            <% List<Question> questions=(List<Question>)     request.getAttribute("recentQuestion");  %>
            <%
                for (Question question : questions ){
                    %><a href= "question_details?questionId=<%=question.getId()%>" ><% out.println(question.getQuestion()); %></a>

                <div class="question-posted-time-for-home-page"><br>
                    <% out.println(question.getUserName() + " | " + question.getTime() + "\n" + "\n"); %>
                </div>
                <hr width="50%" align="left">
                <%}%>
    </div>
</body>
</html>
