<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<title>My Character Profile</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="callout">
<h1>Your Character</h1>
<h5>It's all about <c:out value="${character.characterName}"/>!</h5>
</div>

<p><a href="leaderboard">Leader board</a></p>
<div id="characterprofile">
	<table><tr><td><h4>Character Stats:</h4></td></tr>
			<tr><td>Character Name: <c:out value="${character.characterName}"/></td></tr>
			<tr><td>Level: 			<c:out value="${character.level}"/></td></tr>
			<tr><td>Experience:		<c:out value="${character.experience}"/></td></tr>
			<tr><td>Health:			<c:out value="${character.health}"/></td></tr>
			<tr><td>Strength:		<c:out value="${character.str}"/></td></tr>
			<tr><td>Defense:		<c:out value="${character.def}"/></td></tr>
	</table>
</div>
</body>
</html>