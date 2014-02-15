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
	<h1>Battle</h1>
	<h5>
		<c:out value="${char1.characterName}"/> vs <c:out value="${char2.characterName}"/>
	</h5>
	<div class="grid clearfix">

<div id="portraits">
	<div class="col_6 visible">
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
	<div class="col_6 visible">
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

<div class="col_12 visible" id="log" style="color: black;">
<c:forEach var="msg" items="${log}">
	<p>&gt; <c:out value="${msg}"/></p>
</c:forEach>
</div>

<div class="col_12" id="commands">
<form method="POST">
	<input id="cmd" name="cmd" type="text" value="" style="display:none;"/>
	<c:choose>
	<c:when test="${char1.charged}">
		<button id="attack-button" class="large red"><i class="icon-bolt"></i> Attack</button>
	</c:when>
	<c:otherwise>
		<button id="attack-button" class="large disabled"><i class="icon-bolt"></i> Attack</button>
	</c:otherwise>
	</c:choose>
	<button id="charge-button" class="large orange"><i class="icon-star"></i> Charge</button>
	<button id="defend-button" class="large green"><i class="icon-ban-circle"></i> Defend</button>
</form>
</div>
<script>
$('#attack-button').click(function(e) {
	$('#cmd').val('attack');
});
$('#charge-button').click(function() {
	$('#cmd').val('charge');
});
$('#defend-button').click(function() {
	$('#cmd').val('defend');
});
</script>

</div>

</div>
</body>
</html>