<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Welcome</title>
</head>

<body background-color:#fffff>

	<center>
		<table class="activity-wall">
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

	    <table style="padding-left:275px">
	        <tbody>
	        <td>
                <%@page import="com.forum.repository.Question, java.util.List"%>
              <%
                for (Question question : (List<Question>) request.getAttribute("questionList") ) {
              %>

                    <a href= "question_details?questionId=<%=question.getId()%>" >
                    <% out.println(question.getQuestion());  %> </a><%
                }
              %>
              </td>
              </tbody>
         </table>
                <br><br>
                <% Integer currentPageNumber = ((Integer)request.getAttribute("pageNumber")); %>
                <div style="padding-left:275px">
                    <a href="activityWall?pageNumber=${param['pageNumber']-1}" class="button-anchor">
                    <input type="button" value="Previous" ${prevButton}></input></a>
                    <a rel="next" href="activityWall?pageNumber=<%= currentPageNumber %>" class="button-anchor" >
                    <input type="button" value="Next"  ${nextButton}></a>
                </div>
    </div>
</body>
</html>