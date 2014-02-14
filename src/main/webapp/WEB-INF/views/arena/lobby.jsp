<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Arena Lobby</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="callout"></div>
<ul>
	<c:forEach var="opponent" items="${opponents}">
		<li>
			<a href="<c:url value="arena/create?userId2=${opponent.id}"/>">
				<c:out value="${opponent.username}"/>
			</a>
		</li>
	</c:forEach>
</ul>

</body>
</html>