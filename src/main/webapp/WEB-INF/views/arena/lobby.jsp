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
		<li><c:out value="${opponent.username}"/></li>
	</c:forEach>
</ul>

</body>
</html>