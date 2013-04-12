<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Post question</title>
</head>
<body class="postQuestion" background-color:#ffffff>
    <script type="text/javascript" src="./static/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript" src="./static/javascript/postQuestion.js"></script>
     <div class="user-name" > Logged in as<a href="activityWall"> ${userName}</a>  | <a href="#">Logout</a></div>
    <script type="text/javascript">
    	tinyMCE.init({
    		mode : "textareas",
    		theme : "advanced",
    		plugins : "spellchecker",
    		invalid_elements: "a",
    		theme_advanced_buttons1 : "bold,italic,underline,|,justifyleft,justifycenter,justifyright,justifyfull,|,bullist,numlist,spellchecker",
    	 	theme_advanced_toolbar_location : "top",
    	 	spellchecker_languages : "+English=en",
    	});
    </script> <br><br>

    <div style="padding-left:240px"><font size=5>Post your question :</font></center></div>
    <form id="postQuestion" method="post" action = "postedQuestion">
    	<div style="padding-left:240px">
        <div style="color:#FF0000" >${error} </div>
    		<div>
    			<textarea id="elm1" name="textareas" rows="12" cols="20" style="width: 80%"/>
    		        ${askedQuestion}
    			</textarea>
    		</div><br>

    		<div>
              <input type="text" size= 50 id="createTag" name="createTag" title="use single word without spaces or use comma separated words to create multiple tags " style = "color:#888;"
                value="create tag (optional)" onfocus="onFocus(this)" onblur="onBlur(this)" />
                &nbsp &nbsp &nbsp &nbsp&nbsp
                   <form action="activityWall">
                       <input type="submit" id="post" value="Post Question">
                   </form>
               <input type="button" id="reset" onclick="clearTextbox()" value="Reset">
    		</div>
    	</div>
   </form>

</body>
</html>