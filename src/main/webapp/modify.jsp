<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Note</title>
</head>

<%String user = (String) request.getAttribute("user");
  String title = (String) request.getAttribute("title");
  String content = (String) request.getAttribute("content");
  int Nid = (int) request.getAttribute("Nid");%>

<style>
	.label{
		font-size: 23px;
	}
	
	.textbox{
		cursor: text;
		height: 25px;
		width: 750px;
		margin-left: 5px;
		padding-left: 5px;
		font-size: 15px;
	}
	
	.conLabel{
		margin-left: 475px;
	}
	
	.area{
		cursor: text;
		padding-left: 5px;
		padding-top: 5px;
		font-size: 15px;
	}
	
	.btn{
		font-size: 25px;
		cursor: pointer;
		height: 50px;
		width: 150px;
		border-radius: 5px;
	}
	
	.btn:hover{
		background-color: gray;
	}
</style>

<body>
	<h1 align="center">Modify Note</h1>
	<form action="modify" method="post">
		<input type="hidden" name="user" value="<%=user.toString()%>">
		<input type="hidden" name="Nid" value="<%=Nid%>">
		<div align="center">
			<label class="label" for="titleText">Title: </label>
			<input class="textbox" type="text" id="titleText" name="title" value="<%=title%>"/>
		</div>
	    <br> <br>
		<label class="label conLabel" for="contentText">Content: </label> <br>
		<div align="center">
			<textarea class="area" id="contentText" name="content" rows="30" cols="80"><%=content %></textarea>
			<br> <br>
			<button class="btn">Save</button>
		</div>
	</form>
	
	<br>
	
	<form action="toNote" method="post">
		<input type="hidden" name="user" value="<%=user.toString()%>">
		<input type="hidden" name="Nid" value="<%=Nid%>">
		<input type="hidden" name="title" value="<%=title%>">
		<input type="hidden" name="content" value="<%=content%>">
		<div align="center">
			<button class="btn">Back</button>
		</div>
	</form>

</body>
</html>