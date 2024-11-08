<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta charset="UTF-8">
<title>Categories Form</title>
</head>
<body>
<div class="topnav">
		<a href="index.jsp">Home</a> <a href="Species.jsp">Species</a> <a
			href="Categories.jsp">Categories</a> <a href="Login.jsp">Admin</a>
		<form onsubmit="return search()">
			<input type="search" name="query" id="searchh" value="" placeholder="Search.." required> 
			<input type="submit" name="submission" value="search">
		</form>
	</div>
<br>
	<center>
		<h4><a href="CategoriesServlet?action=list"">List All Species & Categories</a></h4>
	</center>
	<div align="center">
		<c:if test="${categories != null}">
			<form action="${pageContext.request.contextPath}/CategoriesServlet"
				method="post">
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="id" value="${categories.id}">
		</c:if>
		<c:if test="${categories == null}">
			<form
				action="${pageContext.request.contextPath}/CategoriesServlet?action=insert"
				method="post">
		</c:if>

		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${categories != null}"> Edit Category </c:if>
					<c:if test="${categories == null}"> Add New Category </c:if>
				</h2>
			</caption>

			<tr>
				<th>Category Name:</th>
				<td><input type="text" name="name" size="45" required
					value="<c:out value='${categories.name}' />"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
	<script src="functions.js"></script>
</body>
</html>