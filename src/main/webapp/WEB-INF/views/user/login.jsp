<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
	<jsp:include page="../includes.jsp"/>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="callout"></div>
<p>Sign-up <a href="signup">Here</a></p>
<form action="verifyLogin" method="POST">
	<input name="username" type="text" placeholder="Username">
	<input name="password" type="password" size="20" placeholder="Password">
	<input type="submit" value="Login">
</form>

</body>
</html>