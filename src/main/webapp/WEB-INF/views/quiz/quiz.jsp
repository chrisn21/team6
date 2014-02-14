<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Quiz Time!</title>
	<jsp:include page="../includes.jsp"/>
	<script type="text/javascript" src="<c:url value="/resources/js/quizstyle.js"/>"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="callout callout-top">
	<h1>${quiz.name}</h1>
	<h5>${quiz.description}</h5>

<div class="grid clearfix">
<form id="quiz-form" method="POST">

	<c:forEach var="question" items="${questions}">
	<div class="question">
		<p>Q: <c:out value="${question.question}"/></p>
		<input name="absolutely-meaningless-name" type="text" />
	</div>
	</c:forEach>
	
	<input type="button" id='back' value="Back">
	<input type="button" id='next' value="Next">

</form>
</div>
</div>

<script>
$(function() {
	$('#quiz-form').submit(function(e) {
		var params = {answers:[]};
		$.each($('#quiz-form').serializeArray(), function(i, field) {
			params.answers.push(field.value);
		});
		e.preventDefault();
		$.ajax({
			type: 'POST',
			data: params,
			success: function() {
				console.log('yeah');
			},
			async: false
		});
	});
});
</script>
</body>
</html>
