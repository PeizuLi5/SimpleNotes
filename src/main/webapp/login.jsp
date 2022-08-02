<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="passwordFunction.js"></script>
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
		margin-left: 33.3%;
		font-size: 23px;
	}
	
	.btn{
		border: none;
		outline: none;
		border-radius: 5px;
		cursor: pointer;
		font-size: 30px;
	}
	
	.loginBtn{
		background-color: white;
		color: black;
		margin-left: 47.5%;
	}
	
	.backBtn{
		background-color: black;
		color: white;
		height: 100%;
		width: 200px;
	}
	
	.passwordTB{
		margin-left: 10px;
	}
	
	.forgetPasswordBtn{
		color: blue;
		margin-right: 20%;
		background-color: white;
		font-size: 16px;
	}
	
	.check{
		width: 20px;
		height: 20px;
		margin-left: 39.5%;
	}
	
	.clabel{
		font-size:23px;
	}
	
	.btn:hover{
		text-decoration:underline;
	}
	
	.forgetLink{
		margin-left: 10px;
	}
</style>

<body>
	<div>
		<h1 align="center"> Login </h1>
		<form action="login" method="post">
			<label class="label" for="usernameText">Username: </label>
			<input class="textbox" type="text" id="usernameText" name="username" required /> 
			<br> <br>
			<label class="label" for="passwordText">Password:  </label>
			<input class="textbox passwordTB" type="password" id="passwordText" name="password" required /> 
			<a class="forgetLink" href="${pageContext.request.contextPath}/forget">Forget Password</a><br> <br>
			<input class="check" type="checkbox" onclick="displayPassword()">
			<label class="clabel">Show Password</label>
			<br> <br>
			<button class="btn loginBtn">Login</button>
		</form>
	</div>	
	
	<br>
	
	<div align="center">
		<form action="back" method="post">
			<button class="btn backBtn">Back</button>
		</form>
	</div>
</body>
</html>