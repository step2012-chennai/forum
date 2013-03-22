<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Post question</title>
</head>
<body class="postQuestion">
    <script type="text/javascript" src="./static/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript">
    	tinyMCE.init({
    		mode : "textareas",
    		theme : "advanced",
    		plugins : "spellchecker",
    		theme_advanced_buttons1 : "bold,italic,underline,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,|,bullist,numlist,spellchecker",
    	 	theme_advanced_toolbar_location : "top",
    	 	spellchecker_languages : "+English=en",
    	});
    </script>
    <form method="get" action = "postedQuestion">
    	<div>
    		<div>
    			<textarea id="elm1" name="textareas" rows="8" cols="20" style="width: 30%">
    			</textarea>
    		</div>
    		<input type="submit" value="Post Question" onclick="display()"/>
    		<input type="reset" value="Reset"/>

    	</div>
    </form>


</body>
</html>