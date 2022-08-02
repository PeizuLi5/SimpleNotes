<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List,Applications.Note" %>
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
	
	.btn{
		cursor: pointer;
		border: none;
		background-color: white;
		font-size: 20px;
	}
	
	.btn:hover{
		text-decoration:underline;
	}
	
	td{
		font-size: 30px;
		font-weight: bold;
	}
</style>

<% String s = (String) request.getAttribute("user");%>

<body>
	<h1 align="center">
		Welcome! <%= s.toString().toUpperCase() %>
	</h1>
	<div align="right">
		<form class="users" action="setting" method="post">
			<input type="hidden" name="user" value="<%=s%>">
			<button class="userBtn settingBtn">Setting</button>
		</form>
		<form class="users" action="logout" method="post">
			<input type="hidden" name="user" value="<%=s.toString()%>">
			<button class="userBtn logoutBtn">Logout</button>
		</form>
	</div>
	
	<div align="center">
		<form action="toCreate" method="post">
			<input type="hidden" name="user" value="<%=s.toString()%>">
			<button class="createBtn">+</button>
		</form>
	</div>
	
	<br>
	
	<% List<Note> notes = (List<Note>) request.getAttribute("notes"); %>
	
		<table border ="1" align="center">
			<thead>
				<tr>
					<td align="center" width="800">Title</td>
				</tr>
			</thead>
			<tbody>
				<% if(notes != null) {
				for(Note n : notes) {%>
					<tr>
						<td align="center">
							<form action="toNote" method="post">
								<input type="hidden" name="Nid" value="<%=n.getNid()%>">
								<input type="hidden" name="title" value="<%=n.getTitle()%>">
								<input type="hidden" name="content" value="<%=n.getContent()%>">
								<input type="hidden" name="user" value="<%=s.toString()%>">
								<button class="btn"><%=n.getTitle()%></button>
							</form>
						</td>
					</tr>
				<%} }%>
			</tbody>
		</table>
</body>
</html>