<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="styles.css">
  <title>Searched Species</title>
</head>
<body>
<div class="topnav">
  <a href="index.jsp">Home</a>
  <a href="Species.jsp">Species</a>
  <a href="Categories.jsp">Categories</a>
  <a href="Login.jsp">Admin</a>
  <form onsubmit="return search()">
    <input type="search" name="q" id="searchh" value="" placeholder="Search.." required>
    <input type="submit" name="submission" value="search">
  </form>
</div>

<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
  url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
  user="root" password="bit235" />
<c:set var="title" scope="request" value="${param.title}" />
<sql:query dataSource="${snapshot}" var="result">
  SELECT title, created_date, category_id, content FROM species WHERE title LIKE ? AND is_hidden = false
  <sql:param value="%${title}%" />
</sql:query>
<c:if test="${empty result.rows}">
  <h2>Species not registered Yet..</h2>
  <h2>You can create one in the Admin console if You Like!</h2>
</c:if>
<c:forEach items="${result.rows}" var="rows">
  <h2><b><c:out value="${rows.title}" /></b></h2>
  <h3><b>Created:</b> <c:out value="${rows.created_date}" /></h3>
  <sql:query dataSource="${snapshot}" var="results">
    SELECT name FROM categories WHERE id= ?
    <sql:param value="${rows.category_id}" />
  </sql:query>
  <c:forEach items="${results.rows}" var="category">
    <h3><b>Category:</b> <c:out value="${category.name}" /></h3>
  </c:forEach>
  <c:out value="${rows.content}" />
</c:forEach>

<script src="functions.js"></script>
<script src="Search.js"></script>
</body>
</html>
