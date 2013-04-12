<!DOCTYPE html>
<html>
    <head>
    <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="./static/css/main.css" />
    </head>
    <body>
     <script type="text/javascript" src="./static/javascript/userValidation.js"></script>
    <div id="register">
    <form action="signIn" method="post">
        <label>Name:</label> <input type="text" name="name" id="name"/>
        <br/><br/>
        <label>User Name:</label> <input type="text" id="user" name= "username" onChange="validateUserName()"/><br/>  <label id="myDiv"></label>
        <label>Date Of Birth:</label> <input name="dob" type="date" id="DOB"/>
        <br/><br/>
        <label>Location:</label> <input name="location" type="text" id="location"/>
        <br/><br/>
        <label>Gender:</label> <input type="radio" name="gender" value="male"/>Male<input type="radio" name="gender" value="female"/>Female
        <br/><br/>
        <label>Email:</label> <input id="emailId" type="text"/>
        <br/><br/>
        <label>Password:</label> <input id="password" type="password" />
        <br/><br/>
        <label>Confirm Password:</label> <input id="confirmPassword" type="password"/>
        <br/><br/>
        <input type="submit" value="Sign in">
        <input type="reset" value = "reset">
    </form>
    </div>
    </body>
</html>