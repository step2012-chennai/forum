function validateUserName()
{
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function() {
       if (xmlhttp.readyState==4 && xmlhttp.status==200){
            document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
       }
    }
    var userName=document.getElementById("user").value;
    xmlhttp.open("GET","validateUserName?user="+userName,true);
    xmlhttp.send();
}

function validatePassword(){
    xmlhttp=new XMLHttpRequest();
       xmlhttp.onreadystatechange=function() {
          if (xmlhttp.readyState==4 && xmlhttp.status==200){
                document.getElementById("myDiv1").innerHTML=xmlhttp.responseText;
          }
       }
       var password=document.getElementById("password").value;
       var confirmPassword=document.getElementById("confirmPassword").value;
       xmlhttp.open("GET","validatePassword?password="+password+"&confirmPassword="+confirmPassword,true);
       xmlhttp.send();
}

function validateUserName()
{
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function() {
       if (xmlhttp.readyState==4 && xmlhttp.status==200){
            document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
       }
    }
    var userName=document.getElementById("user").value;
    xmlhttp.open("GET","validateUserName?user="+userName,true);
    xmlhttp.send();
}

function getRegisterButtonStatus(){
    if(document.getElementById("user").value.length == 0 ||
            document.getElementById("name").value.length == 0 ||
            document.getElementById("DOB").value.length == 0 ||
            document.getElementById("genderId").value.length == 0 ||
            document.getElementById("emailId").value.length == 0 ||
            document.getElementById("password").value.length == 0 ||
            document.getElementById("check").checked == false ||
            document.getElementById("myDiv").innerHTML != "correct" ||
            document.getElementById("confirmPassword").value.length == 0 ||
            document.getElementById("confirmPassword").value != document.getElementById("password").value){
        document.getElementById("registerId").disabled = true;
    }
    else{
        document.getElementById("registerId").disabled = false;
    }
}

