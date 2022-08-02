<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Applications.DBUtils" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<% 
	String username = (String) request.getAttribute("user");
	String question = (String) request.getAttribute("question");
%>

<body>
	<h1 align="center">Forget Password</h1>
	<div align="center">
		<form action="verify" method="post">
			<input type="hidden" name="user" value="<%=username.toString()%>"/>
			<label class="label" for="questionText">Security Question: <%=question.toString()%></label> <br> <br>
			<label class="label" for="answerText">Security Answer: </label>
			<input class="textbox" type="text" id="answerText" name="answer" required /> <br> <br>
		<button class="btn">Verify</button>
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