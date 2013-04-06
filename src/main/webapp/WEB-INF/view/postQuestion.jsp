<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Post question</title>
</head>
<body class="postQuestion" background-color:#ffffff>
    <script type="text/javascript" src="/forum/static/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript" src="/forum/static/javascript/postQuestion.js"></script>

    <script type="text/javascript">
    	tinyMCE.init({
    		mode : "textareas",
    		theme : "advanced",
    		plugins : "spellchecker",
    		theme_advanced_buttons1 : "bold,italic,underline,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,|,bullist,numlist,spellchecker",
    		theme_advanced_blockformats : "p,pre,h1,h2,h3,h4,h5,h6,code",
    	 	theme_advanced_toolbar_location : "top",
    	 	spellchecker_languages : "+English=en",
    	});
    </script> <br><br>

    <div style="padding-left:415px"><font size=5>Post your question :</font></center></div>
    <form id="postQuestion" method="post" action = "postedQuestion">
    	<div style="padding-left:415px">
        <div style="color:#FF0000" >${error} </div>
    		<div>
    			<textarea id="elm1" name="textareas" rows="12" cols="20" style="width: 60%"/>
    		${askedQuestion}
    			</textarea>   </center>
    		</div><br>

    		<div style="padding-left:570px">
    		<form action="activityWall"><input type="submit" value="Post Question"></form>
    		<input type="button" onclick="clearTextbox()" value="Reset">
    		</div>
    	</div>
   </form>

</body>
</html>