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
        <label>Name:</label> <input id="text" type="text" name="name" id="name"/>
        <br/><br/>
        <label>User Name:</label> <input id="text" type="text" id="user" name= "username" onChange="validateUserName()"/>  <label id="myDiv"></label>
        <br/><br/>
        <label>Date Of Birth:</label> <input id="text" name="dob" type="date" id="userName"/>
        <br/><br/>
        <label>Location:</label> <input id="text" name="location" type="text" id="userName"/>
        <br/><br/>
        <label>Gender: <input type="radio" name="gender" value="male">Male<input type="radio" name="gender" value="female">Female</label>
        <br/><br/>
        <label>Email:</label> <input id="text" type="text"/>
        <br/><br/>
        <label>Password:</label> <input id="text" type="password" />
        <br/><br/>
        <label>Confirm Password:</label> <input id="text" type="password"/>
        <br/><br/>
        <input type="submit" value="Sign in">
        <input type="reset" value = "reset">
    </form>
    </div>
    </body>
</html>