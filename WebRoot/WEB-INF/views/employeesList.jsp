<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All Persons</title>
</head>
<body>
<h1>List Persons</h1>
<h3><a href="add.html">Add More Person</a></h3>

<c:if test="${!empty employees}">
	<table align="left" border="1">
		<tr>
			<th>Person ID</th>
			<th>Person Name</th>
			<th>Person Age</th>
			<th>Person Salary</th>
			<th>Person Address</th>
		</tr>

		<c:forEach items="${employees}" var="employee">
			<tr>
				<td><c:out value="${employee.id}"/></td>
				<td><c:out value="${employee.name}"/></td>
				<td><c:out value="${employee.age}"/></td>
				<td><c:out value="${employee.salary}"/></td>
				<td><c:out value="${employee.address}"/></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>