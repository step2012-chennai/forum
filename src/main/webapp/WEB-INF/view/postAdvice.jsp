<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Post Advice</title>
</head>
<body>
    <script type="text/javascript" src="./static/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript" src="./static/javascript/reset.js"></script>

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

     <div style="padding-left:240px"><font size=5>Post your Advice :</font></center></div>
        <% String q_id =request.getParameter("questionId"); %>
        <form id="postAdvice" method="post" action = "postedAdvice?questionId=<%= q_id %>">
        	<div style="padding-left:240px">
            <div style="color:#FF0000" >${error} </div>
        		<div>
        			<textarea id="elm1" name="textareas" rows="12" cols="20" style="width: 80%"/>
        		${GivenAnswer}
        			</textarea>   </center>
        		</div><br>

        		<div style="padding-left:635px">
        		<input type="submit" id="post" value="Post Advice">
        		<input type="button" id="reset" onclick="clearTextbox()" value="Reset">
        		</div>
        	</div>
       </form>
</body>
</html>