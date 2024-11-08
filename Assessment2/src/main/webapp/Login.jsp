<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<link rel ="stylesheet" href="styles.css">
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<div class="topnav">
<a href="index.jsp">Home</a>
<a href="Species.jsp">Species</a>
<a href="Categories.jsp">Categories</a>
<a href="Login.jsp">Admin</a>
<form onsubmit="return search()">
			<input type="search" name="query" id="searchh" value="" placeholder="Search.." required> 
			<input type="submit" name="submission" value="search">
		</form>
</div>
<br>
<form action="Validate.jsp" method="get">
<p>
<label for="username"><b>Username</b></label>
<br>
<input type="text" placeholder="Username" name="username" required>
</p>
<p>
 <label for="password"><b>Password</b></label>
 <br>
<input type="password" placeholder="Password" name="password" required>
</p>
<button type="submit" formaction="Register.jsp">Register</button>
<button type="submit">Login</button>
<br>
<br>
</form>
<script src="functions.js"></script>
<script src="Search.js"></script>
</body>
</html>