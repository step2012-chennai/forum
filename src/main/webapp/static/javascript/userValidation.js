function validateUserName()
{
    xmlhttp=new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
       if (xmlhttp.readyState==4 && xmlhttp.status==200)
       {
            document.getElementById("userName").innerHTML=xmlhttp.responseText;
            alert(document.getElementById("userName").value);
       }
    }
    xmlhttp.open("GET","validate",true);
    xmlhttp.send();
}