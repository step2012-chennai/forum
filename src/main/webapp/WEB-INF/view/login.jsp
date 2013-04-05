<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login</title>
</head>
    <center>
    <body bgcolor="white">
       <script>
       window.history.forward(1);
       </script>
        <form id="loginForm" name="loginForm" action="j_spring_security_check" method = "post">
           <br><br><br><br><br><br><br><br><br><br> <table
           style="border:1px solid #b4b28c;border-radius:0px; moz-border-radius: 25px; background:#C0C0C0;">
                    <tr> <div style="color:#FF0000" > ${error} </div>
                        <td  style="height:100px;margin:auto;">
                            <label>Username:</label>
                            <input type='text' name='j_username' value=''/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Password:&nbsp</label>
                            <input type='password' name='j_password' />
                        </td>
                    </tr>
                    <td>
                        <br><br>&nbsp&nbsp &nbsp&nbsp  &nbsp&nbsp &nbsp&nbsp  &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp&nbsp  &nbsp&nbsp  &nbsp&nbsp &nbsp&nbsp
                        &nbsp&nbsp &nbsp&nbsp &nbsp&nbsp &nbsp&nbsp&nbsp  &nbsp&nbsp  <input name="submit" type="submit" value="Login">
                    </td>
            </table>
        </form>
    </body>
</html>