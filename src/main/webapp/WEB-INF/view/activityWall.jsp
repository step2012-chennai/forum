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
    </center>

	<br><br>
	<font size="5" style="padding-left:200px" color="#888888">Recent Questions</font>
	<br><br>


<form name="list of question" action="ActivityWallControl">
	<div class="questionList">
         <body>
           <%@page import="java.util.*,java.sql.*,com.forum.repository.ShowQuestions,java.util.*,org.springframework.context.ApplicationContext,org.springframework.context.support.ClassPathXmlApplicationContext,org.springframework.jdbc.support.rowset.SqlRowSet"%>
              <% ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
                ShowQuestions showQuestions = (ShowQuestions) context.getBean("showQuestions");
                int pageNumber=request.getParameter("pageNumber")==null?1:Integer.parseInt(request.getParameter("pageNumber"));
                List<String> questions=new ArrayList<String>();
                questions=showQuestions.show(pageNumber,5);
                %>

                <table style="padding-left:200px" class="RecentQuestions" >
                    <td>
		                <a href="http://localhost:8080/app/questionDetails">
		                    <%
                                 for (String question : questions) {
                                     out.println(question);
                                 }
		                     %>
		               </a>
		            </td>
           </table>
        </body>
    	 <center><a href="activityWall?pageNumber=<%=pageNumber-1%>" id="a" name="a"><input type="button" id="pre" name="pre" value="Previous"></input></a>
                <a href="activityWall?pageNumber=<%=pageNumber+1%>"><input type="button" value="Next"></a>
         </center>
    	</div>
</body>
</html>