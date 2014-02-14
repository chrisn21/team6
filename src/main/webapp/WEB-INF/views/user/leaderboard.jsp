<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Leaderboard</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="callout">
<h1>Leaderboard</h1>
<h5>Can you make it to the top?</h5>
</div>

<div id="leaderboard">
	<table><tr><td> Username </td><td> Points </td></tr>
		<c:forEach var="user" items="${leaderboard}">
			<tr><td><c:out value="${user.username}"/></td>
			<td><c:out value="${user.points}"/></td></tr>
		</c:forEach>
	</table>
</div>
</body>
</html>