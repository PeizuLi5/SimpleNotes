<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
</head>

<style>
	.textbox{
		cursor: text;
		height: 25px;
		width: 400px;
		margin-left: 5px;
		padding-left: 5px;
		font-size: 15px;
	}
	
	.label{
		margin-left: 33.3%;
		font-size: 23px;
	}

	.check{
		width: 20px;
		height: 20px;
		margin-top: 10px;
	}
	
	.box1{
		margin-left: 42%;
	}
	
	.box2{
		margin-left: 44%;
	}
	
	.clabel{
		font-size:23px;
		margin-top: 10px;
	}
	
	.btn{
		background-color: #6495ED;
		color: white;
		border: none;
		width: 300px;
		height: 40px;
		outline: none;
		border-radius: 5px;
		cursor: pointer;
		font-size: 30px;
	}
	
	.btn:hover{
		text-decoration:underline;
	}
</style>

<body>
	<h1 align="center">Reset Password</h1>
	<form action="reset" method="post">
		<label class="label" for="newPasswordText">New Password: </label>
		<input class="textbox" type="password" id="newPasswordText" name="newPassword" required /> <br>
		<input class="check box1" type="checkbox" onclick="displayPassword()">
		<label class="clabel">Show Password</label> <br> <br>
		<label class="label" for="reNewPasswordText">Re-enter Password: </label>
		<input class="textbox" type="password" id="reNewPasswordText" name="reNewpassword" required /> <br>
		<input class="check box2" type="checkbox" onclick="displayPassword2()">
		<label class="clabel">Show Password</label> <br> <br>
		<div align="center"><button class="btn">Reset Password</button></div>
	</form>
	
	<br>
	
	<div align="center">
		<form action="back" method="post">
			<button class="btn backBtn">Home</button>
		</form>
	</div>
	
	<script>
		function displayPassword(){
			var password = document.getElementById("newPasswordText");
			if(password.type == "password"){
				password.type = "text";
			}
			else{
				password.type = "password";
			}
		}
		function displayPassword2(){
			var password = document.getElementById("reNewPasswordText");
			if(password.type == "password"){
				password.type = "text";
			}
			else{
				password.type = "password";
			}
		}
	</script>
</body>
</html>