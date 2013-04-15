<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    <title>Post question</title>
</head>
<body class="postQuestion" background-color:#ffffff>
    <div id="wrapper">
    <script type="text/javascript" src="./static/tiny_mce/tiny_mce.js"></script>
     <script type="text/javascript" src="./static/javascript/tiny_mce_init.js"></script>
    <script type="text/javascript" src="./static/javascript/reset.js"></script>
    <script type="text/javascript" src="./static/javascript/textboxEvents.js"></script>
    <%@include file="homeHeader.jsp" %>

    <div class="post-questions-panel">
        <div class="list-heading">
            Post your question :
        </div>
    </div>

    <form id="postQuestion" method="post" action = "postedQuestion">
        <div style="padding-left:13em">
            <div style="color:#FF0000" >${error}
            </div>
            <div>
                    <textarea id="askAdviceTextarea" name="textareas" rows="8" cols="20" style="width: 60%"/>
                        ${askedQuestion}
                    </textarea>
            </div><br>
    	</div>
    <div style="padding-left:13em">
    <lable><b>Tag(s) (Optional) : </b></lable>
        <input type="text" size= 30 id="tag" name="createTag" title="use single word without spaces or use comma separated words to create multiple tags "
               value="${tag}"/>
            <form action="activityWall">
                <input type="submit" id="post" value="Post Question">
            </form>
        <input type="button" id="reset" onclick="clearTextbox()" value="Reset">
   </form>
     </div>
    </div>
</body>
</html>