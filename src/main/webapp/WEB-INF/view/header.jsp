<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
</head>

<body>
	<center>

        <div class="user-name" >Logged in as<a href="activityWall"> ${userName}</a>    | <a href="#">Logout</a></div>
		<br>
		<table class="activity-wall">
			<body>
    			<td>
				    <a href="postQuestion" class="header-navigator-panel"><center><label for="Ask_Question" > Ask Question</label></a>
				</td>
				<td>
    				<a href="questions_advised" class="header-navigator-panel"><center><label for="Answer"> My Answer</font></label></a>
				</td>
				<td>
				    <center><label for="Tags" class="header-navigator-panel" onclick="alert('tag link!')">Tag Cloud</label>
				</td>
			</body>
		</table>
		<br>

		<form name="search" action="search">
		<%String question1 = (String)request.getAttribute("question");
		 if(question1 == null)question1="";
		 %>
				<table border="0">
				<td><input type="text" id="basicSearch" name="basicSearch" value="${searchedQuestion}"></input></td>
				<td><input id="search" type="submit" value="search"></input></td>
				</table>
		</form>
		</table>
    </center>
</body>
</html>