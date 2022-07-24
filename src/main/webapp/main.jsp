<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>

<style>
	.userBtn{
		border: none;
		background-color: white;
		cursor: pointer;
		font-size: 20px;
		margin: 10px;
	}
	
	.settingBtn{
		color: blue;
	}
	
	.logoutBtn{
		color: red;
	}
	
	.userBtn:hover{
		text-decoration:underline;
	}
	
	.createBtn{
		height: 50px;
		width: 50px;
		border: none;
		border-radius: 50%;
		font-size: 40px;
		cursor: pointer;
	}
	
	.createBtn:hover{
		background-color: silver;
	}
	
	.users{
		display: inline-block;
	}
</style>

<body>
	<div align="right">
		<form class="users" action="setting" method="post">
			<button class="userBtn settingBtn">Setting</button>
		</form>
		<form class="users" action="back" method="post">
			<button class="userBtn logoutBtn">Logout</button>
		</form>
	</div>
	
	<div align="center">
		<h1>Welcome</h1>
			<form action="create" method="post">
				<button class="createBtn">+</button>
			</form>
	</div>
</body>
</html>