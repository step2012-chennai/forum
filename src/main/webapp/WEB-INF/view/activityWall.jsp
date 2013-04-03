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
         <div style="padding-left:275px">
           <%@page import="com.forum.repository.Question, java.util.List"%>
              <%
                for (Question question : (List<Question>) request.getAttribute("questionList") ) {
                   String url = "http://localhost:8080/app/questions/" + question.getId();
                   %>

                    <a href= <%= url %> >
                    <% out.println(question.getQuestion());  %> </a><%
                }
              %>
            </div>
            </td>
            </table>
                </body>
                    <br><br><br>
                    <div style="padding-left:420px">
                        <a href="activityWall?pageNumber=${param['pageNumber']-1}" id="a" name="a">
                        <input type="button" id="pre" name="pre" value="Previous" ${prevButton}></input></a>
                        <a href="activityWall?pageNumber=${param['pageNumber']+1}">
                        <input type="button" value="Next"  ${nextButton}></a>
                    </div>
        </div>
</body>
</html>
