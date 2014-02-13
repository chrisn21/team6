<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Quiz Time!</title>
</head>
<body>

<h1>${quiz.name}</h1>
<p>${quiz.description}</p>
<span>created by ${creator.username}</span>

<form id="quiz-form" method="POST">

	<c:forEach var="question" items="${questions}">
		<p><c:out value="${question.question}"/></p>
		<input type="text" />
	</c:forEach>
	
	<input type="submit" value="Submit Answers">

</form>

</body>
</html>
