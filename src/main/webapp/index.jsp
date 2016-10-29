<html>
<head>
<meta charset="ISO-8859-1">
<title>REST-WS</title>
<script>
function getState()
{
	if (window.XMLHttpRequest)
    {
        // AJAX nutzen mit IE7+, Chrome, Firefox, Safari, Opera
        xmlhttp=new XMLHttpRequest();
    }
    else
    {
        // AJAX mit IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
        	var users=JSON.parse(xmlhttp.responseText);
        	var out="user list: ";
        	for(i=0;i<users.length;i++){
        		out+=users[i].name +'<br>'
        	}
            document.getElementById("sctastate").innerHTML=out;
        }
    }
    
    xmlhttp.open("GET","rest/admin/participants",true);
    xmlhttp.send();
}
</script>
</head>
<body onload="getState()">
	<h1>Administration</h1>
	<button onclick="setInterval(getState, 1000)">Push</button>
	<span id="sctastate">...</span>
</body>
</html>