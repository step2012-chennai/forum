<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>question details</title>
        <link rel="stylesheet" type="text/css" href="/forum/static/css/questionDetailsBackground.css" />
    </head>

    <body>
        <table border="0" class="QD" cellspacing="12">
            <tbody>
                <td>
                    <label><b> Question:- </b></label><br>
                     <%@page import="com.forum.repository.Question, java.util.List"%>
                     <% out.println(request.getAttribute("question"));  %>
                </td>
            </tbody>
        </table>
        </tbody>
        </table>
    </body>
</html>