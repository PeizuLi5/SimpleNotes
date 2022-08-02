<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Setting Page</title>
</head>

<%String user = (String) request.getAttribute("user");
  String password = (String) request.getAttribute("password");
  String question = (String) request.getAttribute("question");
  String answer = (String) request.getAttribute("answer");
  List<Timestamp> logins = (List<Timestamp>) request.getAttribute("logins");
  List<Timestamp> logouts = (List<Timestamp>) request.getAttribute("logouts");%>

<style>
	h1{
		font-size: 40px;
	}

	label{
		font-size: 25px;
	}
	
	td{
		width: 500px;
		heignt: 25px;
	}
	
	.textfield{
		width: 500px;
		height:30px;
		font-size: 25px;
		padding-left: 5px;
		padding-right: 5px;
	}
	
	.btn{
		cursor: pointer;
		height: 40px;
		font-size: 25px;
	}
	
	.sBtn{
		width:100px;
		margin-top: 10px;
	}
	
	.mBtn{
		width: 300px;
	}
</style>

<body>
	<h1 align="center">Setting</h1>
	<div align="center">
		<form action="changeQA" method="post">
			<input type="hidden" name="user" value="<%=user%>">
			<label>Question: </label>
			<input class="textfield" type="text" name="question" value="<%=question%>"> <br> <br>
			<label>Answer: </label>
			<input class="textfield" type="text" name="answer" value="<%=answer%>"> <br>
			<button class="btn sBtn">Save</button>
		</form>
		
		<br>
		
		<form action="changePassword" method="post">
			<input type="hidden" name="user" value="<%=user%>">
			<input type="hidden" name="oldPassword" value="<%=password %>">
			<label>Password: </label>
			<input class="textfield" type="text" name="password" value="<%=password%>"> <br>
			<button class="btn sBtn">Save</button>
		</form>
	</div> <br>
	
	<table border="1" align="center">
		<thead>
			<tr align="center">
				<td><b>Past Five Login History</b></td>
			</tr>
		</thead>
		<tbody>
			<%if(!logins.isEmpty()){
				for(Timestamp t : logins){%>
				<tr align="center">
					<td><%=t%></td>
				</tr>
			<%}} %>
		</tbody>
	</table> <br> <br>
	
	<table border="1" align="center">
		<thead>
			<tr align="center">
				<td><b>Past Five Logout History</b></td>
			</tr>
		</thead>
		<tbody>
			<%if(!logouts.isEmpty()){
				for(Timestamp t : logouts){%>
				<tr align="center">
					<td><%=t%></td>
				</tr>
			<%}} %>
		</tbody>
	</table> <br> <br>
	
	<div align="center">
		<form action="toMain" method="post">
			<input type="hidden" name="user" value="<%=user%>">
			<button class="btn mBtn">Main Page</button>
		</form>
	</div>
</body>
</html>