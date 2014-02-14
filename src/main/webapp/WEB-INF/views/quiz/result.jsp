<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Quiz Results</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="callout callout-top"></div>
<p>Good work, ${userId}</p>
<p>You've gained ${expGained} experience points!</p>

</body>
</html>
