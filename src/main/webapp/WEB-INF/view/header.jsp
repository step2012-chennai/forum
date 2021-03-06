<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <script>
        function getSearchButtonStatus(){
            if(document.getElementById("basicSearch").value.length == 0){
                document.getElementById("search").disabled = true;
            }
            else{
                document.getElementById("search").disabled = false;
            }
        }
    </script>
</head>

<body>
<div id="wrapper">
        <div id="logoSpace"><a href="activityWall"><img src="./static/css/knowitall_header.jpg" width="170" height="100" border=2 /></a></div>
	    <center>
            <div id="userAndLogout">
            <ul>
                <li><a href="<c:url value="/j_spring_security_logout" />" >&nbsp  Logout &nbsp </a></li>
                <li>&nbsp&nbsp&nbsp&nbsp&nbsp</li>
                <li><font color="grey">Logged in as </font><b>${userName}</b></div></li>
            </ul>
        </div>
        </div>
		<br>
		<table class="activity-wall">
			<body onMouseOver="getSearchButtonStatus()">
    		    <td>
				    <a href="postQuestion" class="header-navigator-panel"><center><label id="question" for="Ask_Question" > Ask Question</label></a>
				</td>
				<td>
    				<a href="questions_advised" class="header-navigator-panel"><center><label id="answer" for="Answer"> My Answer</font></label></a>
				</td>
				<td>
				    <a href="tagcloud" class="header-navigator-panel"><center><label id="tags" for="Tags" class="Tag_Cloud" >Tag Cloud</label></a>
				</td>
			</body>
		</table>
		<br>

		<form name="search" action="search" method="get">
			<table border="0">
				<td><input type="text" id="basicSearch" name="basicSearch" value="${searchedQuestion}" onKeyUp="getSearchButtonStatus()"></input></td>
				<td><input id="search"  type="submit" value="search" ></input></td>
			</table>
		</form>
		</table>
    </center>
    </div>
</body>
</html>