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

<div id="commands">
<c:if test="${isActiveTurn}">
	<p>hey dude its your turn</p>
</c:if>
</div>

<div id="portraits">
	<img src="<c:url value="/resources/img/${char1.character}.png"/>"/>
	<img src="<c:url value="/resources/img/${char2.character}.png"/>"/>
</div>

<div id="log">
<c:forEach var="msg" items="${log}">
	<p><c:out value="${msg}"/></p>
</c:forEach>
</div>

</body>
</html>