<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Arena Lobby</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="callout">
<h1>Arena</h1>
<h5>Let's get ready to rumble...</h5>
</div>
<div class="grid">

<div class="col_6">
<h2>Available to fight</h2>
<table class="sortable">
<thead><tr>
	<th>Name</th>
	<th>Level</th>
	<th>Type</th>
	<th>Owner</th>
</tr></thead>
<tbody>
<c:forEach var="opponent" items="${opponents}">
	<tr>
	<td><c:out value="${opponent.character.characterName}"/></td>
	<td><c:out value="${opponent.character.level}"/></td>
	<td><c:out value="${opponent.character.character}"/></td>
	<td><c:out value="${opponent.username}"/></td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>

<div class="col_6">
<h2>Your battles</h2>
<table class="sortable">
<thead><tr>
	<th>Character 1 HP</th>
	<th>Character 2 HP</th>
	<th></th>
</tr></thead>
<tbody>
<c:forEach var="battle" items="${battles}">
	<tr>
	<td><c:out value="${battle.char1.health}"/></td>
	<td><c:out value="${battle.char2.health}"/></td>
	<td>
		<a href="<c:url value="/arena/${battle.id}"/>">Go!</a>
	</td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>

</div>

</body>
</html>