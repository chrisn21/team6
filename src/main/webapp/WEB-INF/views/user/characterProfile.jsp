<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>My Character Profile</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="callout"></div>
<p><a href="leaderboard">Leader board</a></p>
	<form action="adjustStats" method="GET">
		<input type="hidden" name="experience" value="1">
		<input type="submit" value="Adjust Character Stats">
	</form>
<P> ${characterstats} </P>
</body>
</html>