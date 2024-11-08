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
	</div>
	<br>
	<br>
	<c:out value="Admin ${username} is Logged in" />

<center>
    <h2>List of Species</h2>
    <h4><a href="SpeciesForm.jsp">Add New Species</a></h4>
</center>

<table align="center">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Category</th>
        <th>Created Date</th>
        <th>Content</th>
        <th>Actions</th>
    </tr>
    <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
        user="root" password="bit235" />
    <sql:query dataSource="${snapshot}" var="result">
        SELECT id, title, category_id, created_date, content FROM species WHERE is_hidden = false
    </sql:query>
    <c:forEach items="${result.rows}" var="Species">
        <tr align="center">
            <td><c:out value="${Species.id}" /></td>
            <td><c:out value="${Species.title}" /></td>
            <td><sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
							url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
							user="root" password="bit235" />
						<sql:query dataSource="${snapshot}" var="result">
            SELECT name FROM categories WHERE id=?
            <sql:param value="${Species.category_id}"/>
						</sql:query>
						<c:forEach items="${result.rows}" var="rows">
						<c:out value="${rows.name}" />
						</c:forEach></td>
            <td><c:out value="${Species.created_date}" /></td>
            <td><textarea name="Content" rows="10" cols="50" readonly><c:out value="${Species.content}" /></textarea></td>
            <td>| 
                <a href="${pageContext.request.contextPath}/SpeciesServlet?action=edit&id=<c:out value='${Species.id}' />">Edit</a> | 
                <a   onclick="return confirm('Are you sure you want to delete this Species? This action cannot be undone.');"
                href="${pageContext.request.contextPath}/SpeciesServlet?action=delete&id=<c:out value='${Species.id}' />">Delete</a> |
            </td>
        </tr>
    </c:forEach>
</table>

<h2 align="center">List of <b>Hidden</b> Species</h2>
<table align="center">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Category</th>
        <th>Created Date</th>
        <th>Content</th>
        <th>Actions</th>
    </tr>
    <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
        user="root" password="bit235" />
    <sql:query dataSource="${snapshot}" var="result">
        SELECT id, title, category_id, created_date, content FROM species WHERE is_hidden = true ORDER BY id
    </sql:query>
    <c:forEach items="${result.rows}" var="Species">
        <tr align="center">
            <td><c:out value="${Species.id}" /></td>
            <td><c:out value="${Species.title}" /></td>
            <td><sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
							url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
							user="root" password="bit235" />
						<sql:query dataSource="${snapshot}" var="result">
            SELECT name FROM categories WHERE id=? ORDER BY id
            <sql:param value="${Species.category_id}"/>
						</sql:query>
						<c:forEach items="${result.rows}" var="rows">
						<c:out value="${rows.name}" />
						</c:forEach></td>
            <td rows="10"><c:out value="${Species.created_date}" /></td>
             <td><textarea name="Content" rows="10" cols="50" readonly><c:out value="${Species.content}" /></textarea></td>
            <td>| 
                <a href="${pageContext.request.contextPath}/SpeciesServlet?action=edit&id=<c:out value='${Species.id}' />">Edit</a> | 
                <a onclick="return confirm('Are you sure you want to delete this Species? This action cannot be undone.');"
                href="${pageContext.request.contextPath}/SpeciesServlet?action=delete&id=<c:out value='${Species.id}' />">Delete</a> |
            </td>
        </tr>
    </c:forEach>
</table>

<h2 align="center">List of <b>Categories</b></h2>
<h4 align="center"><a href="${pageContext.request.contextPath}/CategoriesServlet?action=new">Add New Category</a></h4>
<table align="center">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne"
        user="root" password="bit235" />
    <sql:query dataSource="${snapshot}" var="result">
        SELECT id, name FROM categories ORDER BY id
    </sql:query>
    <c:forEach items="${result.rows}" var="categories">
        <tr>
            <td><c:out value="${categories.id}" /></td>
            <td><c:out value="${categories.name}" /></td>
            <td>
            <c:if test="${categories.id != 1}">
                    | <a href="${pageContext.request.contextPath}/CategoriesServlet?action=edit&id=${categories.id}">Edit</a> |
                    <a onclick="return confirm('Are you sure you want to delete this Category? This action cannot be undone.');"
                    href="${pageContext.request.contextPath}/CategoriesServlet?action=delete&id=${categories.id}">Delete</a> |
                </c:if>
                <c:if test="${categories.id == 1}">
                     |   ---Default---   |
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
	
<script src="functions.js"></script>
<script src="Search.js"></script>
</body>
</html>