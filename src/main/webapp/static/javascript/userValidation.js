function validateUserName()
{
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
       if (xmlhttp.readyState==4 && xmlhttp.status==200)
       {
            document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
       }
    }
    var userName=document.getElementById("user").value;
    xmlhttp.open("GET","validateUserName?user="+userName,true);
    xmlhttp.send();
}