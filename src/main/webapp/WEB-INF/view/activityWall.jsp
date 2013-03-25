<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/activityWall_background.css" />
    <title>Welcome</title>
</head>

<body background-color:#ffffff>
     <span><font size="2" color="#888888">Hello, user_name</font></span><br><br>

	<center>
		<table class="QA">
			<body>
    			<td>
				    <center><label for="Ask_Question"><font size="5" color="white">Ask Question</label>
				    <a href="postQuestion"><center><button type="button" >Post Question</button></a>
				</td>
		
				<td>
    				<center><label for="Answer"><font size="5" color="white">Answer</font></label></H1>
				    <center><button type="button" onclick="alert('open list of questions link!')">Browse Open Questions</button>
				</td>

				<td>
				    <center><label for="Tags"><font size="5" color="white">Tag Cloud</font></label></H1>
				    <center><button type="button" onclick="alert('tag link!')">Tags</button>
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


              <center>
                    <button value="prev">prev</button>
                    <button value="next"/>next</button>
              </center>
		    </tbody>

	    </table>
	</div>
</body>
</html>