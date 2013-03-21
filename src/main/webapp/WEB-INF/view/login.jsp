<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login Form Validation Demo</title>

</head>
    <center><body bgcolor="white">
        <form name="forgotpassord" action="validate" method="post">
           <br><br><br><br><br><br><br><br><br><br><br><br><br> <table
           style="border:1px solid #b4b28c;border-radius:20px; moz-border-radius: 20px; -webkit-box-shadow: 10px 10px 15px #afaf97;-moz-box-shadow: 10px 10px 15px #afaf97; box-shadow: 10px 10px 15px #afaf97; background:#C0C0C0;">
                <tbody>
                    <tr>
                    <td  style="height:100px;margin:auto;">
                        <label>&nbsp &nbsp &nbsp &nbsp User:</label> <input type='text' name='j_username' />
                    </td>
                     </tr>
                     <tr>
                    <td>
                         <label>Password:</label> <input type='password' name='j_password' />
                    </td>
                    </tr>
                    <td>
                        <br><br>&nbsp&nbsp &nbsp&nbsp  &nbsp&nbsp &nbsp&nbsp  &nbsp&nbsp &nbsp &nbsp&nbsp &nbsp&nbsp  &nbsp&nbsp  &nbsp&nbsp &nbsp&nbsp
                        &nbsp&nbsp &nbsp&nbsp &nbsp&nbsp &nbsp&nbsp&nbsp  &nbsp&nbsp  <input type="submit" value="Login">
                    </td>
                </tbody>
            </table>
        </form>
    </body>
</html>