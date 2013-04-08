<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
</head>

<body background-color:#fffff>
	<center>

        <div class="user-name" >${userName}    | <a href="#">Logout</a></div>
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
				<td><input type="text" size="50" id="basicSearch" name="basicSearch"></input></td>
				<td><input type="submit" value="search"></input></td>
				</table>
		</form>
		</table>
    </center>
</body>
</html>