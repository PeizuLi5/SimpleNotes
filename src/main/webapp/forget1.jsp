<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Username Verification</title>
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
		background-color: #6495ED;
		color: white;
		border: none;
		outline: none;
		height: 45px;
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
	<h1 align="center">Forget Password</h1>
	<div align="center">
		<form action="checkUsername" method="post">
			<label class="label" for="usernameText">Username: </label>
			<input class="textbox" type="text" id="usernameText" name="username" required /> <br> <br>
		<button class="btn">Next</button>
	</form>
	</div>
	
	<br>
	
	<div align="center">
		<form action="back" method="post">
			<button class="btn backBtn">Home</button>
		</form>
	</div>
	
</body>
</html>