<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="deleteConfirmation.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Owned Note Important</title>
</head>

<style>
	h1{
		font-size: 40px;
	}
	
	.starBtn{
		border: none;
		background-color: white;
		cursor: pointer;
		font-size: 25px;
		color: orange;
	}
	
	
	.noteBtn{
		border: none;
		background-color: white;
		cursor: pointer;
		font-size: 20px;
		margin: 10px;
	}
	
	.mBtn{
		color: blue;
	}
	
	.dBtn{
		color: red;
	}
	
	.noteBtn:hover{
		text-decoration:underline;
	}
	
	.btns{
		display: inline-block;
	}
	
	.content{
		font-size: 25px;
		margin-left: 10%;
		margin-right: 10%;
	}
	
	.btn{
		font-size: 25px;
		border: none;
		height: 50px;
		width: 150px;
		border-radius: 5px;
		cursor: pointer;
	}
	
	.btn:hover{
		background-color: silver;
	}
</style>

<%
	String user = (String) request.getAttribute("user");
	String title = (String) request.getAttribute("title");
	String content = (String) request.getAttribute("content");
	int Nid = (int) request.getAttribute("Nid");
%>

<body>
	<h1 align="center"><%=title%></h1>
	<h2 align="center"><%=user%></h2>
	<h4 align="right">Last modify: <%= request.getAttribute("time") %></h4>
	<div align="right">
		<form class="btns" action="notImportant" method="post">
			<input type="hidden" name="user" value="<%=user.toString()%>">
			<input type="hidden" name="nid" value="<%=Nid%>">
			<input type="hidden" name="title" value="<%=title.toString()%>">
			<input type="hidden" name="content" value="<%=content.toString()%>">
			<input type="hidden" name="author" value="<%=user.toString()%>">
			<input type="hidden" name="isAuthor" value="true">
			<button class="fa fa-star starBtn"></button>
		</form>
		<form class="btns" action="toModify" method="post">
			<input type="hidden" name="user" value="<%=user.toString()%>">
			<input type="hidden" name="nid" value="<%=Nid%>">
			<input type="hidden" name="title" value="<%=title.toString()%>">
			<input type="hidden" name="content" value="<%=content.toString()%>">
			<button class="noteBtn mBtn">Modify</button>
		</form>
		<form class="btns" action="toShare" method="post">
			<input type="hidden" name="user" value="<%=user.toString()%>">
			<input type="hidden" name="nid" value="<%=Nid%>">
			<input type="hidden" name="title" value="<%=title.toString()%>">
			<input type="hidden" name="content" value="<%=content.toString()%>">
			<button class="noteBtn sBtn">Share</button>
		</form>
		<form onclick="isDelete()" class="btns" action="delete" method="post">
			<input type="hidden" name="user" value="<%=user.toString()%>">
			<input type="hidden" name="nid" value="<%=Nid%>">
			<button class="noteBtn dBtn">Delete</button>
		</form>
	</div>
	<div class="content" align="center">
		<%= content %>
	</div>
	<br>
	<div align="center">	
		<form action="toMain" method="post">
			<input type="hidden" name="user" value="<%=user.toString()%>">
			<button class="btn">Main Page</button>
		</form>
	</div>
	<br>
</body>
</html>