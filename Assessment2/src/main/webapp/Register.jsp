<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<link rel ="stylesheet" href="styles.css">
<meta charset="ISO-8859-1">
<title>Validate</title>
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
	<%-- JDBC driver name and database URL STEP 2: Register JDBC driver --%>
	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
		user="root" password="bit235" />
	<%-- Getting Request parameters --%>
	<c:set var="username" scope="session" value="${param.username}" />
	<c:set var="password" scope="session" value="${param.password}" />
	<%-- STEP 3: Open a connection
 STEP 4: Execute an Update--%>
	<sql:query dataSource="${snapshot}" var="result">
select count(*) as kount from admin_login where username = ?
<sql:param value="${username}" />
	</sql:query>
	<c:forEach items="${result.rows}" var="r">
		<c:choose>
			<c:when test="${r.kount > 0}">
				<c:out
					value="Username ${username} is already taken, Please try another one." />
			</c:when>
			<c:otherwise>
				<sql:update dataSource="${snapshot}" var="result">
INSERT INTO admin_login (username, password, created) VALUES (?, md5(?), CURRENT_TIMESTAMP)
  <sql:param value="${username}" />
					<sql:param value="${password}" />
				</sql:update>
				<c:out value="Admin ${username} has been created" />
			</c:otherwise>
		</c:choose>
	</c:forEach>
<script src="functions.js"></script>
<script src="Search.js"></script>
</body>
</html>