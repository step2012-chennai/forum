<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/activityWall_background.css" />
    <title>Welcome</title>
</head>

<body background-color:#fffff>

	<center>
		<table class="QA">
			<body>
    			<td>
				    <a href="postQuestion"><center><label for="Ask_Question" ><font size="5" color="white">Ask Question</label></a>
				</td>

				<td>
    				<center><label for="Answer" onclick="alert('open list of questions link!')"><font size="5" color="white">Answer</font></label></H1>
				</td>

				<td>
				    <center><label for="Tags" onclick="alert('tag link!')"><font size="5" color="white">Tag Cloud</font></label></H1>
				</td>
			</body>
		</table>
		<br>

		<form name="search" action="search">

				<table border="0">
				<td>  <input type="text" size="50" id="basicSearch" name="basicSearch"></input>
				 </td><td><input type="submit" value="search"></input>
				</td>
				</table>
		</forum>

		</table>
    </center>

	<br><br>
	<font size="5" style="padding-left:200px" color="#888888">Recent Questions</font>
	<br><br>

	<div class="questionList">
         <body>
           <%@page import="java.util.*,java.sql.*,com.forum.repository.ShowQuestions,com.forum.repository.Question,java.util.*,org.springframework.context.ApplicationContext,org.springframework.context.support.ClassPathXmlApplicationContext,org.springframework.jdbc.support.rowset.SqlRowSet"%>
              <% ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
                ShowQuestions showQuestions = (ShowQuestions) context.getBean("showQuestions");

                int pageNumber=request.getParameter("pageNumber")==null?1:Integer.parseInt(request.getParameter("pageNumber"));
                List<Question> questions=new ArrayList<Question>();
                questions=showQuestions.show(pageNumber,5);
                String previousButtonStatus=showQuestions.previousButtonStatus(pageNumber);
                String nextButtonStatus=showQuestions.nextButtonStatus(pageNumber,5);
                %>

                <table style="padding-left:275px" class="RecentQuestions" >
                    <td>
                        <%
                             for (Question question : questions) {
                             String url = "http://localhost:8080/app/questions/" + question.getId();
                                 %>

		                <a href=<%= url %>>
                                  <% out.println(question.getQuestion());  %> </a>
                                <% }
		                     %>
		            </td>
           </table>
        </body>
        <br><br><br><br><br>
    	 <div style="padding-left:420px">
    	 <a href="activityWall?pageNumber=<%=pageNumber-1%>" id="a" name="a">
    	 <input type="button" id="pre" name="pre" value="Previous" <%=previousButtonStatus%>></input></a>
                <a href="activityWall?pageNumber=<%=pageNumber+1%>"><input type="button" value="Next"  <%=nextButtonStatus%>></a>
         </div>
    	</div>
</body>
</html>
