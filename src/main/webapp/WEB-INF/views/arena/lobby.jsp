<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Arena Lobby</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="callout callout-top">
<h1>The Arena</h1>
<div class="grid clearfix">

<div class="col_6 visible">
<h2>Available to fight</h2>
<table class="sortable">
<thead><tr>
	<th>Name</th>
	<th>Level</th>
	<th>Type</th>
	<th>Owner</th>
	<th></th>
</tr></thead>
<tbody>
<c:forEach var="opponent" items="${opponents}">
	<tr>
	<td><c:out value="${opponent.character.characterName}"/></td>
	<td><c:out value="${opponent.character.level}"/></td>
	<td><c:out value="${opponent.character.character}"/></td>
	<td><c:out value="${opponent.username}"/></td>
	<td><button class="small red" onClick='createBattle("<c:out value="${opponent.id}"/>")'><i class="icon-bolt"></i> Challenge</button></td>
	</tr>
</c:forEach>
</tbody>
</table>
</div>

<div class="col_6 visible">
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
</div>

<script>
var createBattle = function(id) {
	$.post('/app/arena', {userId2: id}, function() {
		console.log('came back!');
	});
	console.log(id);
};
</script>
</body>
</html>