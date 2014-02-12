<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<script>
function err(type, message)
{
	var err = new Error();
	err.name = 'My API ' + type + ' Error';
	err.message = message;
	throw(err);
}

if(typeof this.output == 'undefined')
{
	delete this.output;
	err('Output', 'Missing input');
}

var result = new Date(input);
if (result == 'Invalid Date') 
{
	throw('Invalid Date was provided');
}
</script>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>
<p>Login <a href="login">HERE</a>.</p>
<p>DO SOME QUIZZES <a href="quizzes">HERE</a>.</p>

<P>  The time on the server is ${serverTime}. </P>
<P> ${userexists}</P>
<%request.setAttribute("userexists", ""); %>

<form action="Signup" method="POST">
First name: <input type="text" name="firstName"><br>
Last name: <input type="text" name="lastName"><br>
User name: <input type="text" name="username"><br>
Email: <input type="text" name="email"><br>
DOB: <input type="text" name="dob"><br>
<input type="submit" value="Submit">
</form>

</body>
</html>
