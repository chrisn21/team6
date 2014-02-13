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
<P>  The time on the server is ${serverTime}. </P>

<p>DO SOME QUIZZES <a href="quizzes">HERE</a></p>
<p><a href="leaderboard">Leader board</a></p>
<%if(request.getSession(true).getAttribute("loggedin") == null)
{%>
	<p>Login <a href="login">Here</a></p>
	<p>Sign-up <a href="signup">Here</a></p>
<%
}
else
{%>
	<p><a href="MyCharacter">My Character</a></p>
<%
}%>


<%if(request.getAttribute("userexists") != null) 
{%>
	<p><%= request.getAttribute("userexists") %></p>
<%
	request.setAttribute("userexists", null);
}
%>
</body>
</html>
