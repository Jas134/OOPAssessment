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
<title>Home Page</title>
</head>
<body>
	<div class="topnav">
		<a href="index.jsp">Home</a> <a href="Species.jsp">Species</a> <a
			href="Categories.jsp">Categories</a> <a href="Login.jsp">Admin</a>
		<form onsubmit="return search()">
			<input type="search" name="query" id="searchh" value="" placeholder="Search.." required> 
			<input type="submit" name="submission" value="search">
		</form>
  </form>
	</div>
	<br>
	<br>
	<center>
		<h4>
			<a
				href="${pageContext.request.contextPath}/SpeciesServlet?action=list">List
				All Species</a>
		</h4>
	</center>
	<div align="center">
		<c:if test="${species != null}">
			<form action="${pageContext.request.contextPath}/SpeciesServlet"
				method="post">
				<input type="hidden" name="action" value="update">
		</c:if>
		<c:if test="${species == null}">
			<form
				action="${pageContext.request.contextPath}/SpeciesServlet?action=insert"
				method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${species != null}"> Edit species </c:if>
					<c:if test="${species == null}"> Add New species </c:if>
				</h2>
			</caption>
			<c:if test="${species != null}">
				<input type="hidden" name="id"
					value="<c:out value='${species.getSpeciesID()}' />" />
			</c:if>
			<tr>
				<th>Species Title:</th>
				<td><input type="text" name="Title" size="45" required
					value="<c:out value='${species.getTitle()}' />" /></td>
			</tr>
			<tr>
				<th>Category:</th>
				<td><select name="CategoryID" id="categoryID">
						<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
							url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
							user="root" password="bit235" />
						<sql:query dataSource="${snapshot}" var="result">
            SELECT id, name FROM categories ORDER BY id
						</sql:query>
						<c:forEach items="${result.rows}" var="rows">
							<option value="${rows.id}">ID: <c:out value="${rows.id}" />, <c:out value="${rows.name}" /></option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<th>Created Date:</th>
				<td><input type="date" name="CreatedDate" required
					value="<c:out value='${species.getCreated_date()}' />" /></td>
			</tr>
			<tr>
				<th>Content:</th>
				<td><input type="text" name="Content" required minlength="105"
					value="<c:out value='${species.getContent()}' />" /></td>
			</tr>
			<tr>
				<th>is hidden?</th>
				<c:out value='${Species.isHidden()}' />
				<td><select name="is_hidden" required
					value="<c:out value='${species.isHidden()}' />">
						<option value="True">True</option>
						<option value="False">False</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
<script src="functions.js"></script>
<script src="Search.js"></script>
</body>
</html>