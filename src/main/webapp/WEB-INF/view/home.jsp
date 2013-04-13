<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Welcome</title>
</head>
<body class="home" >
    <div id="loginAndRegistration">
        <ul>
            <li><a href="login">&nbsp Login &nbsp</li>        </a>
            <li> &nbsp &nbsp </li>
            <li><a href="home">&nbsp Registration &nbsp</li> </a>
        </ul>
    </div>

    <div id="logoSpace">
    </div>

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
    </div>

    <div id="topFiveAdvisers" style="height: 100px; width: 200px; float: right">
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
    <div id="recentlyAdvisedQuestion">
    <H1>Recently Advised Question</H1>
            <ul>
            <% List<Question> questions=(List<Question>)     request.getAttribute("recentQuestion");  %>
            <%
                for (Question question : questions ){
                    %><li><% out.println(question.getUserName()); %></li><%
                    %><li><% out.println(question.getQuestion()); %></li><%
                    %><li><% out.println(question.getTime()); %></li><%
                }
            %>
            </ul>

    </div>
</body>
</html>
