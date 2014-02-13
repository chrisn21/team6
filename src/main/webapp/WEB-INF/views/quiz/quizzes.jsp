<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Quizzes</title>
</head>
<body>

<h1>Here dem quiz categories!!</h1>

<c:forEach var="category" items="${categories}">
	<h2><c:out value="${category.name}"/></h2>
	<p><c:out value="${category.description}"/></p>
</c:forEach>

<ul>
	<c:forEach var="quiz" items="${quizzes}">
		<li><a href="quiz/${quiz.id}"><c:out value="${quiz.name}"/></a></li>
	</c:forEach>
</ul>

</body>
</html>
