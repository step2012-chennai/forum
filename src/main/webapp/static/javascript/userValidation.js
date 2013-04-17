function validateUserName()
{
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function() {
       if (xmlhttp.readyState==4 && xmlhttp.status==200){
            document.getElementById("sameUserErrorMessage").innerHTML=xmlhttp.responseText;
       }
    }
    var userName=document.getElementById("user").value;
    xmlhttp.open("GET","validateUserName?user="+userName,true);
    xmlhttp.send();
}

function validateDate()
{
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function() {
       if (xmlhttp.readyState==4 && xmlhttp.status==200){
            document.getElementById("dateErrorMessage").innerHTML=xmlhttp.responseText;
       }
    }
    var date=document.getElementById("DOB").value;
    xmlhttp.open("GET","validateDate?date="+date,true);
    xmlhttp.send();
}

function validatePassword(){
    xmlhttp=new XMLHttpRequest();
       xmlhttp.onreadystatechange=function() {
          if (xmlhttp.readyState==4 && xmlhttp.status==200){
                document.getElementById("passwordMismatchMessage").innerHTML=xmlhttp.responseText;
          }
       }
       var password=document.getElementById("password").value;
       var confirmPassword=document.getElementById("confirmPassword").value;
       xmlhttp.open("GET","validatePassword?password="+password+"&confirmPassword="+confirmPassword,true);
       xmlhttp.send();
}

function validateTermsAndCondition(){
     xmlhttp=new XMLHttpRequest();
        xmlhttp.onreadystatechange=function() {
           if (xmlhttp.readyState==4 && xmlhttp.status==200){
                document.getElementById("termsErrorMessage").innerHTML=xmlhttp.responseText;
           }
        }
        var check=document.getElementById("check").checked;
        xmlhttp.open("GET","validateTermsAndCondition?check="+check,true);
        xmlhttp.send();
}

function getRegisterButtonStatus(){
    if(document.getElementById("user").value== "" ||
            document.getElementById("name").value.length == 0 ||
            document.getElementById("DOB").value.length == 0 ||
            document.getElementById("genderId").value.length == 0 ||
            document.getElementById("emailId").value.length == 0 ||
            document.getElementById("password").value.length == 0 ||
            document.getElementById("address").value.length == 0 ||
            document.getElementById("sameUserErrorMessage").innerHTML != "" ||
            document.getElementById("dateErrorMessage").innerHTML != "" ||
            document.getElementById("confirmPassword").value.length == 0 ||
            document.getElementById("confirmPassword").value != document.getElementById("password").value){
        document.getElementById("registerId").disabled = true;
    }
    else{
        document.getElementById("registerId").disabled = false;
    }
}


function checkTermsAndCondition(){
    validateTermsAndCondition();
    getRegisterButtonStatus();
    if(document.getElementById("check").checked != true){
        return false;
    }
    return true;
}

