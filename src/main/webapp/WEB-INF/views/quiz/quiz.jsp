<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Quiz Time!</title>
</head>
<body>

<h1>${quiz.name}</h1>
<p>${quiz.description}</p>

<c:forEach var="question" items="${questions}">
	<h3><c:out value="${question.question}"/></h3>
	<p><c:out value="${question.answer}"/></p>
</c:forEach>

</body>
</html>
