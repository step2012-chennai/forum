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