<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Welcome</title>
</head>

<body class="home" >
    <div id="wrapper">
    <%@include file="homeHeader.jsp" %>
    <%@include file="autoRefresh.jsp" %>
    <div id="wrapper">

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
        Recently most advised Questions
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
</body>
</html>
