<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
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
	
	.btn{
		color: white;
		border: none;
		outline: none;
		height: 45px;
		width: 200px;
		border-radius: 5px;
		cursor: pointer;
		font-size: 30px;
	}
	
	.registerBtn{
		background-color: #3264c8;
	}
	
	.backBtn{
		background-color: black;
	}
	
	.btn:hover{
		text-decoration:underline;
	}
</style>

<body>
	<div align="center">
		<h1>Register</h1>
		<form action="register">
			<label class="label" for="usernameText">Username: </label>
			<input class="textbox" type="text" id="usernameText" name="username" required /> <br> <br>
			<label class="label" for="passwordText">Password: </label>
			<input class="textbox" type="password" id="passwordText" name="password" required /> <br> <br>
			<label class="label" for="securityQuestionText">Security Question: </label>
			<input class="textbox" type="text" id="securityQuestionText" name="securityQuestion" required/> <br> <br>
			<label class="label" for="securityAnswerText">Security Answer: </label>
			<input class="textbox" type="text" id="securityAnswerText" name="securityAnswer" required/> <br> <br>
			<button class="btn registerBtn">Register</button>
		</form>
		
		<br>
		
		<form action="back" method="post">
			<button class="btn backBtn">Back</button>
		</form>
	</div>
</body>
</html>