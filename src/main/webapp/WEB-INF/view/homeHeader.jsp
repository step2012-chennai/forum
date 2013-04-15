<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

<body> <div id="wrapper">



<sec:authorize access="isAnonymous()">
<div id="logoSpaceForHome"><div id="alignImage"><a><img src="./static/css/knowitall_header.jpg" width="110" height="60" border=2 ></img></a></div></div>
<div id="loginAndRegistration">
        <ul>
            <li><a id="homeLogin" href="login">&nbsp Login &nbsp</a></li>
        <li>&nbsp</li>
            <li><a id="homeRegistration" href="registration">&nbsp Registration &nbsp</a></li>
        </ul>
    </div>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<div id="logoSpaceForHome"><div id="alignImage"><a href="activityWall"><img src="./static/css/knowitall_header.jpg" width="110" height="60" border=2 ></img></a></div></div>
<div id="loginAndRegistration">

    <ul>
        <li><a href="<c:url value="/j_spring_security_logout" />" >&nbsp  Logout &nbsp </a></li>
        <li>&nbsp</li>
        <li><font color="grey">Logged in as </font><b>${userName}</b></center></li>
    </ul>
</div>
</sec:authorize>

	    <center>

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