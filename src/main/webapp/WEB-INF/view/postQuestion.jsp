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
    <form method="post">
    	<div>
    		<div>
    			<textarea id="elm1" name="elm1" rows="8" cols="20" style="width: 30%">
    			</textarea>
    		</div>
    		<input type="button" value="Post Question" onclick="display()"/>
    		<input type="reset" value="Reset" />

    	</div>
    </form>

    <input type="button" onclick=alert(tinyMCE.activeEditor.save())>
     var text = $("#commentEditor").text();
     <%@ page import="java.util.*,java.sql.*" %>
    <%
         Connection connection = null;
               Statement sql;
               try {
                   connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum_user", "password");
               } catch (SQLException e) {
                   e.printStackTrace();
               }
               try {
                   sql = connection.createStatement();



                   sql.executeUpdate("insert into Student values(2,'tinyMCE.activeEditor.save()')");
                   ResultSet set = sql.executeQuery("select * from student");
                   while (set.next()) {
                       out.println(set.getString("id"));
                       out.println(set.getString("name"));
                   }
               } catch (SQLException e) {
                   e.printStackTrace();
               }
    %>
</body>
</html>