<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
</head>
<body>

<form action="login" method="POST">
	<input name="username" type="text" placeholder="Username">
	<input name="password" type="text" placeholder="Password">
	<input type="submit" value="Login">
</form>

</body>
</html>