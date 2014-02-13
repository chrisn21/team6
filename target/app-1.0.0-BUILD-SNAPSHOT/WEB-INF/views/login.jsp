<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
</head>
<body>

<p>Your username is: ${username}</p>
<p>Your password is: ${password}</p>

<form method="POST">
	<input name="username" type="text" placeholder="Username">
	<input name="password" type="text" placeholder="Password">
	<input type="submit" value="Login">
</form>

</body>
</html>