<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>The Arena</title>
</head>
<body>

<ul>
	<c:forEach var="opponent" items="${opponents}">
		<li><c:out value="${opponent.username}"/></li>
	</c:forEach>
</ul>

</body>
</html>
