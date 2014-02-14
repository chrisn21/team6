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
	<h1>Battle</h1>
	<h5>
		<c:out value="${char1.characterName}"/> vs <c:out value="${char2.characterName}"/>
	</h5>
</div>
<div class="grid">

<div id="portraits">
	<div class="col_6">
		<table class="tight sortable">
			<tbody><tr>
				<th></th>
				<td><div class="img-holder">
					<img class="align-left" src="<c:url value="/resources/img/${char1.character}.gif"/>"/>
				</div></td>
			</tr><tr>
				<th>Level</th>
				<td><c:out value="${char1.level}"/></td>
			</tr><tr>
				<th>Type</th>
				<td><c:out value="${char1.character}"/></td>
			</tr><tr>
				<th>HP</th>
				<td><c:out value="${char1.health}"/></td>
			</tr><tr>
				<th>ATK</th>
				<td><c:out value="${char1.str}"/></td>
			</tr><tr>
				<th>DEF</th>
				<td><c:out value="${char1.def}"/></td>
			</tr></tbody>
		</table>
	</div>
	<div class="col_6">
		<table class="tight sortable">
			<tbody><tr>
				<th></th>
				<td><div class="img-holder">
					<img class="align-left" src="<c:url value="/resources/img/${char2.character}.gif"/>"/>
				</div></td>
			</tr><tr>
				<th>Level</th>
				<td><c:out value="${char2.level}"/></td>
			</tr><tr>
				<th>Type</th>
				<td><c:out value="${char2.character}"/></td>
			</tr><tr>
				<th>HP</th>
				<td><c:out value="${char2.health}"/></td>
			</tr><tr>
				<th>ATK</th>
				<td><c:out value="${char2.str}"/></td>
			</tr><tr>
				<th>DEF</th>
				<td><c:out value="${char2.def}"/></td>
			</tr></tbody>
		</table>
	</div>
</div>

<c:choose>
<c:when test="${isActiveTurn}">

<div class="col_12" id="commands">
<form method="POST">
	<input id="cmd" name="cmd" type="text" value="" style="display:none;"/>
	<button id="attack-button" class="large red"><i class="icon-bolt"></i> Attack</button>
	<button id="charge-button" class="large blue"><i class="icon-star"></i> Charge</button>
	<button id="defend-button" class="large green"><i class="icon-ban-circle"></i> Defend</button>
</form>
</div>
<script>
$('#attack-button').click(function() {
	console.log('attack');
	$('#cmd-text').value('attack');
});
$('#charge-button').click(function() {
	$('#cmd-text').value('charge');
});
$('#defend-button').click(function() {
	$('#cmd-text').value('defend');
});
</script>

</c:when>
<c:otherwise>

<div>
</div>

</c:otherwise>
</c:choose>

<div class="col_12 tab-content" id="log">
<c:forEach var="msg" items="${log}">
	<p>&gt; <c:out value="${msg}"/></p>
</c:forEach>
</div>

</div>
</body>
</html>