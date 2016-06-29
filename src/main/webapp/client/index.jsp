<!DOCTYPE html>
<html>
	<head>
		<title>SCTA RESTful-WS client prototype</title>	
	</head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>
		function init(){
			$("#data").show();
			if(!$("#data").text()){
				$("#login").show();
			}else{
				$("#login").hide();
			}
			
			$("#wait").hide();
			$("#document").hide();
			
		}
		
		function register(user){
			$("#data").text(user);
			$("#login").hide();
			scta_register(user);
		}
		
		
		function scta_register(user){
			var u=new Object();
			u.name=user;
			jQuery.ajax ({
			    url: "/webapp/rest/admin/post",
			    type: "POST",
			    data: JSON.stringify(u),
			    dataType: "json",
			    contentType: "application/json",
			    accepts: "text/plain",
			    success: function(data, status, xhr){
			        alert("done");
			    }
			}); 
		}
	</script>
<body onload="init()">

<div id="data"></div>
<div id="login">
	<label for="user">User name: <input id="user" name="user" /></label>
	<button onclick="register($('#user').val())">Register</button>
</div>
<div id="wait">waiting...</div>
<div id="document">document...</div>

</body>
</html>