<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
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
		font-size: 23px;
	}
	
	.loginBtn{
		background-color: white;
		color: black;
		border: none;
		outline: none;
		height: 40px;
		width: 200px;
		border-radius: 5px;
		cursor: pointer;
		font-size: 30px;
	}
	
	.backBtn{
		background-color: black;
		color: white;
		border: none;
		outline: none;
		height: 40px;
		width: 200px;
		border-radius: 5px;
		cursor: pointer;
		font-size: 30px;
	}
	
	.btn:hover{
		text-decoration:underline;
	}
</style>

<body>
	<div align="center">
		<h1> Login </h1>
		<form action="login">
			<label class="label" for="usernameText">Username: </label>
			<input class="textbox" type="text" id="usernameText" name="username" required /> <br> <br>
			<label class="label" for="passwordText">Password: </label>
			<input class="textbox" type="password" id="passwordText" name="password" required /> <br> <br>
			<button class="btn loginBtn">Login</button>
		</form>
		
		<br>
		
		<form action="back" method="post">
			<button class="btn backBtn">Back</button>
		</form>
	</div>
</body>
</html>