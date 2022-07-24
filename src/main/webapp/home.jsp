<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Applications.DBUtils,oracle.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<style>
	h1{
		margin-top: 20px;
		font-size: 100px;
	}

	.btn{
		background-color: white;
		color: black;
		border: none;
		outline: none;
		height: 80px;
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

	<% 
	   String path = config.getServletContext().getRealPath("/").concat(System.getProperty("file.separator")).concat("createdb.txt");
	   OracleConnection ocon = (OracleConnection) DBUtils.getConnection();
	   DBUtils.createDB(ocon, path);
	%>

	<div align="center">
		<h1>Simple Notes</h1>
		
		<form action="toLogin" method="post">
			<button class="btn loginBtn">Login</button> <br> <br>
		</form>
		
		<form action="toRegister" method="post">
			<button class="btn registerBtn">Register</button>
		</form>
	</div>
</body>
</html>