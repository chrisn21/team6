<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>
<p>Login <a href="login">HERE</a>.</p>
<p>Play games <a href="games">HERE</a>.</p>

<P>  The time on the server is ${serverTime}. </P>

<form action="Signup" method="POST">
First name: <input type="text" name="firstName"><br>
Last name: <input type="text" name="lastName"><br>
User name: <input type="text" name="username"><br>
<input type="submit" value="Submit">
</form>

</body>
</html>
