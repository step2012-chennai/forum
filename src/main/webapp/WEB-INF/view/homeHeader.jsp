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

    <div id="logoSpace"><img src="./static/css/knowitall_header.jpg" width="160" height="60" border=2 >  </img></div>

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
				    <center><label for="Tags" class="header-navigator-panel" onclick="alert('tag link!')">Tag Cloud</label>
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
</body>
</html>