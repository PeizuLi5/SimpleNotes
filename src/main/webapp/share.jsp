<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="deleteConfirmation.js"></script>
<title>Share Note</title>
</head>

<%String user = (String) request.getAttribute("user");
  int Nid = (int) request.getAttribute("Nid");
  List<String> share_to = (List<String>) request.getAttribute("share_to");%>

<style>
	h1{
		font-size: 40px;
	}

	.label{
		font-size: 30px;
	}

	.textbox{
		height: 30px;
		width: 800px;
		padding-left: 5px;
		padding-rignt: 5px;
		font-size: 20px;
	}

	td{
		font-size: 30px;
		font-weight: bold;
	}
	
	.btn{
		border: none;
		background-color: white;
		color: red;
		cursor: pointer;
		font-size: 25px;
	}
	
	.btn:hover{
		text-decoration:underline;
	}
	
	.users{
		padding-left: 5px;
		padding-rignt: 5px;
	}
	
	.funcBtn{
		width: 200px;
		height: 40px;
		cursor: pointer;
		font-size: 30px;
	}
</style>

<body>
	<h1 align="center">Share Note</h1>
	<div align="center">
		<form action="share" method="post">
			<input type="hidden" name="user" value="<%=user%>">
			<input type="hidden" name="Nid" value="<%=Nid%>">
			<label class="label" for="shareToText">Share to: </label>
			<input class="textbox" type="text" id="shareToText" name="shareTo" /> <br> <br>
			<button class="funcBtn">Share</button>
		</form>
	</div>
	
	<br>
	
	<table border="1" align="center">
		<thead>
			<tr>
				<td align="center" width="800">Already Shared</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<%if(share_to != null){
				for(String s : share_to){%>
				<tr>
					<td class="users"><%=s%></td>
					<td>
						<form onclick="isRemove()" action="deleteShare" method="post">
							<input type="hidden" name="user" value="<%=user%>"/>
							<input type="hidden" name="share_to" value="<%=s%>"/>
							<input type="hidden" name="Nid" value="<%=Nid%>">
							<button class="btn">Remove</button>
						</form>
					</td>
				</tr>
			<% } }%>
		</tbody>
	</table>
	
	<br>
	
	<div align="center">
		<form action="toMain" method="post">
			<input type="hidden" name="user" value="<%=user%>">
			<button class="funcBtn">Main</button>
		</form>
	</div>
</body>
</html>