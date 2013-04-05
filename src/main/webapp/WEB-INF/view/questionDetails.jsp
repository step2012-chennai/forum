<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>question details</title>
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
		</form>

		</table>
    </center>
	<div class="recent-questions-panel">
        <div>
           <font size="5" color="#888888"><% out.print(request.getAttribute("question") );%></font>
        </div>
        <%@page import="com.forum.repository.Question, java.util.List"%>

          <%int i=1;
          for(String answer : (List<String>)request.getAttribute("answers")) { %>
                 <div class="answer"> <b>
                        <% out.print("Answer" + i + " :");%> &nbsp &nbsp</b>
                        <% out.println(answer + "<br/>");%>
                 </div>
          <% i++; } %>
    </div>
</body>
</html>