<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome to THE GAME</title>
	<jsp:include page="includes.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="callout"></div>
<div class="grid">
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
</div>
</body>
</html>
