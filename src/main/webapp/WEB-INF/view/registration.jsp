<!DOCTYPE html>
<html>
    <head>
    <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
    </head>
    <body>
    <script type="text/javascript" src="./static/javascript/userValidation.js"></script>
    <div id="container">
       <div id="register">
           <form action="signIn" method="post">
           <div class="input"><label>Name:</label> <input type="text" name="name" id="name"/></div>
           <div class="input"><label>User Name:</label> <input type="text" id="user" name= "username" onChange="validateUserName()"/><br/>  <label id="myDiv"></label></div>
           <div class="input"><label>Date Of Birth:</label> <input name="dob" type="date" id="DOB"/></div>
           <label>Location:</label>

                 <div class="input">
                 <input type="text" id="address"></input>

                 <%@include file="location.jsp" %>

            <div class="input"><label>Gender:</label> <input type="radio" name="gender" value="male"/>Male<input type="radio" name="gender" value="female"/>Female  </div>
            <div class="input"><label>Email:</label> <input id="emailId" type="email"/> </div>
            <div class="input"><label>Password:</label> <input id="password" type="password" /> </div>

            <label>Confirm Password:</label> <input id="confirmPassword" type="password"  onChange="validatePassword()"/> <label id="myDiv1"></label><br/><br/>
            <div class="input">
            <div class="termsAndConditions">
                <%@include file="termsAndCondition.jsp" %>
            </div>
            <form action="register">
                <input type="submit" value="Register">
            </form>
                <input type="reset" value = "Clear">
            </div>
            </form>
        </div>
    </div>
    </body>
</html>