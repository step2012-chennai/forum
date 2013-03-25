<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/activityWall_background.css" />
    <title>Welcome</title>
</head>

<body background-color:#ffffff>

	<center>
		<table class="QA">
			<body>
    			<td>
				    <a href="postQuestion"><center><label for="Ask_Question"><font size="5" color="white">Ask Question</label></a>
				</td>
		
				<td>
    				<center><label for="Answer" onclick="alert('open list of questions link!')"><font size="5" color="white">Answer</font></label></H1>
				</td>

				<td>
				    <center><label for="Tags" onclick="alert('tag link!')"><font size="5" color="white">Tag Cloud</font></label></H1>
				</td>
			</body>
		</table>			
    </center>

	<br><br>
	<font size="5" style="padding-left:200px" color="#888888">Recent Questions</font>
	<br><br>
	<div class="questionList">
	    <table style="padding-left:200px" class="RecentQuestions" >
		    <tbody>
		        <td>
		        <a href="http://localhost:8080/app/test">
		       </a>
              <center>
                    <button value="prev">prev</button>
                    <button value="next"/>next</button>
              </center>
		    </tbody>

	    </table>
	</div>
</body>
</html>