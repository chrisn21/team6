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

<div>
<c:forEach var="msg" items="${log}">
	<p><c:out value="${msg}"/></p>
</c:forEach>
</div>

</body>
</html>