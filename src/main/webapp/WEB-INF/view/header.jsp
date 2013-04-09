<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
</head>

<body background-color:#fffff>
	<center>

        <div class="user-name" >Logged in as<a href="activityWall"> ${userName}</a>    | <a href="#">Logout</a></div>
		<br>
		<table class="activity-wall">
			<body>
    			<td>
				    <a href="postQuestion"><center><label for="Ask_Question" ><font size="5" color="white">Ask Question</label></a>
				</td>
				<td>
    				<a href="questions_advised"><center><label for="Answer"><font size="5" color="white"> My Answer</font></label></H1></a>
				</td>
				<td>
				    <center><label for="Tags" onclick="alert('tag link!')"><font size="5" color="white">Tag Cloud</font></label></H1>
				</td>
			</body>
		</table>
		<br>

		<form name="search" action="search">
		<%String question1 = (String)request.getAttribute("question");
		 if(question1 == null)question1="";
		 %>
				<table border="0">
				<td><input type="text" size="50" id="basicSearch" name="basicSearch"></input></td>
				<td><input id="search" type="submit" value="search"></input></td>
				</table>
		</form>
		</table>
    </center>
</body>
</html>