<!DOCTYPE html>
<html>
    <head>
    <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
        <script type="text/javascript" src="./static/javascript/userValidation.js"></script>
    </head>
    <body onMouseOver="getRegisterButtonStatus()" onkeypress="getRegisterButtonStatus()" onchange="getRegisterButtonStatus()">
    <div id="container">
       <div id="register">
           <form method="post" action="register" onsubmit="return checkTermsAndCondition()" >
           <div class="input"><label>Name:</label> <input type="text" name="name" id="name"/></div>
           <div class="input"><label>User Name:</label> <input type="text" id="user" name= "username" onchange="validateUserName()"/><br/>  <label id="sameUserErrorMessage"></label></div>
           <div class="input"><label>Date Of Birth:</label> <input name="dob" type="date" id="DOB" onChange="validateDate()"/><br> <label id="dateErrorMessage"></label></div>
           <label>Location:</label>

                 <div class="input">
                 <input type="text" name="address" id="address"></input>

                 <%@include file="location.jsp" %>

            <div class="input"><label>Gender:</label> <input id="genderId" type="radio" name="gender" value="male"/>Male<input type="radio" name="gender" value="female"/>Female  </div>
            <div class="input"><label>Email:</label> <input id="emailId" name="email" type="email"/> </div>
            <div class="input"><label>Password:</label> <input id="password" type="password" /> </div>

            <label>Confirm Password:</label> <input id="confirmPassword" name="password" type="password"  onKeyUp="validatePassword()"/> <label id="passwordMismatchMessage"></label><br/><br/>
            <div class="input">
            <div class="termsAndConditions">
            <input type="checkbox" name="checkbox" id="check"></input>
                <lable>I accept</lable><%@include file="termsAndCondition.jsp" %>
            </div>
                 <label id="termsErrorMessage" text-color="red"></label> <br><br>
                <input id="registerId" type="submit" value="Register" onclick="checkTermsAndCondition()">
                <input type="reset" value = "Clear" onclick="window.location.reload(true)">
        <a style="left:padding=200px" href="home">home</a>
            </div>
            </form>
        </div>
    </div>
    </body>
</html>