<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Welcome</title>
</head>

<body class="home" >
<div id="wrapper">
<sec:authorize access="isAnonymous()">
<div id="logoSpaceForHome"><div id="alignImage"><a><img src="./static/css/knowitall_header.jpg" width="150" height="90" border=1 ></img></a></div></div>
<div id="loginAndRegistration">
        <ul>
            <li><a id="homeLogin" href="login">&nbsp Login &nbsp</a></li>
            <li><a id="homeRegistration" href="registration">&nbsp Registration &nbsp</a></li>
        </ul>
    </div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<div id="logoSpaceForHome"><div id="alignImage"><a href="activityWall"><img src="./static/css/knowitall_header.jpg" width="170" height="100" border=2 ></img></a></div></div>
<div id="loginAndRegistration">
    <ul>
        <li><a href="<c:url value="/j_spring_security_logout" />" >&nbsp  Logout &nbsp </a></li>
        <li>&nbsp</li>
        <li><font color="grey">Logged in as </font><b>${userName}</b></center></div></li>
    </ul>
</div>
</sec:authorize>


    <%@include file="homeHeader.jsp" %>
    <%@include file="autoRefresh.jsp" %>

    <div id="headingSpaceForLogoSpace"></div>
        <div id="spaceForTags"></div>
    <div id="topFiveSeekers" style="height: 100px; width: 350px; float: right ">
        <H3>Top Five Seekers</H3>
        <%@page import="com.forum.repository.ShowLeaders, java.util.List, com.forum.domain.Leader, com.forum.domain.Question" %>
        <ul>
           <% List<Leader> seekers = (List<Leader>) request.getAttribute("seekerList");
           for (Leader seeker : seekers ){
                    %><li><i><% out.println(seeker.getUserName()); %></i></li><%
                }
           %>
        </ul>
        <div id="spaceForTags"></div>
        <div id="topFiveAdvisers" style="height: 100px; width: 350px; float: right" style=";">
                <H3>Top Five Advisors</H3>
                     <ul>
                       <% List<Leader> advisers = (List<Leader>) request.getAttribute("adviserList");
                                  for (Leader adviser : advisers ){
                                           %><li><i><% out.println(adviser.getUserName()); %></li></i><%
                                       }
                                  %>
                      </ul>
                    </ul>
            </div>
    </div>

    <div class="recent-questions-panel-for-home-page">
            <div class="list-heading">
        Recently Advised Question
             </div>
             <br>
            <% List<Question> questions=(List<Question>)     request.getAttribute("recentQuestion");  %>
            <%
                for (Question question : questions ){
                    %><a href= "question_details?questionId=<%=question.getId()%>" ><% out.println(question.getQuestion()); %></a>
                <div class="question-posted-time-for-home-page">
                    <% out.println(question.getUserName() + " | " + question.getTime() + "\n" + "\n"); %>&nbsp&nbsp&nbsp&nbsp
                     <%String str = question.getTags();%>
                      <a href="tagsearch?tag=<%=str%>"><lable><b> <%out.println(str);%> </b></lable></a>
                </div>
                <hr width="50%" align="left" id="horizon">
                <%}%>
    </div>
    </div>
</body>
</html>
