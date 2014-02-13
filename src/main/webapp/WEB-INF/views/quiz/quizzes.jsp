<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Quizzes - Level up here!</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="callout"></div>
<div class="grid">
<div class="tab-content">
	<div class="col_12">
		<c:forEach var="category" items="${categories}">
			<article class="clearfix">
				<h4><c:out value="${category.name}"/></h4>
				<p><c:out value="${category.description}"/></p>
			</article>
		</c:forEach>
	</div>

	<div class="col_3">
		<c:forEach var="quiz" items="${quizzes}">
			<article class="post clearfix">
				<h4>
				<a href="quiz/${quiz.id}"><c:out value="${quiz.name}"/></a>
				</h4>
			</article>
		</c:forEach>
	</div>
</div>
</div>

</body>
</html>