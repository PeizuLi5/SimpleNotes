<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Notes</title>
</head>
<body>
	<h1 align="center">Create Note</h1>
	<div align="center">
		<form action="create">
			<label class="label" for="titleText">Title: </label>
			<input class="textbox" type="text" id="titleText" name="title"/> <br> <br>
			<label class="label" for="contentText">Content: </label> <br>
			<textarea rows="30" cols="100"></textarea>
			<button class="btn">Create</button>
		</form>
	</div>

</body>
</html>