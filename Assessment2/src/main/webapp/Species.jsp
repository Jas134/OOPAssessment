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
<title>Species</title>
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
<br>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
		user="root" password="bit235" />
	<sql:query dataSource="${snapshot}" var="result">
SELECT title,content FROM species WHERE is_hidden= false
	</sql:query>
	<c:forEach items="${result.rows}" var="rows">
		<b> <a href="individualSpecies.jsp?title=${rows.title}"><c:out value="${rows.title}" /></a></b>
		<div><c:out value="${rows.content}" /></div>
		<br><br>
	</c:forEach>
<br>
<script src="functions.js"></script>
<script src="Search.js"></script>
</body>
</html>