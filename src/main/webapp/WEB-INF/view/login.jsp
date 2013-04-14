<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Login</title>
    <link rel="stylesheet" type="text/css" href="./static/css/main.css"/>
</head>
    <center>
    <body bgcolor="white">
       <form id="loginForm" style="padding-top: 200px;" name="loginForm" action="j_spring_security_check" method = "post">
        <div style="color:#FF0000">${error}</div>
        <div style="border:1px solid #b4b28c; background:#C0C0C0; width:350px; height:200px">
             <div style="height:70px; padding-top:40px">
                 <label>Username:</label>
                 <input type='text' name='j_username' id="j_username" value="${username}"/>
             </div>
             <div style="padding-bottom:25px">
                 <label>Password:&nbsp</label>
                 <input type='password' name='j_password' />
             </div>
                <div style="padding-left:145px">
                <a href="registration">Sign Up</a>
                <input name="submit" type="submit" value="Login">
             </div>
        </div>
       </form>
    </body>
</html>