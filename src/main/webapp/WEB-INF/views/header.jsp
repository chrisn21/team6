<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar">
	<a class="hide-phone" id="logo" href="login">
		<i class="icon-signin"></i> 
		<span>Login</span>
	</a>
	<ul>
		<li><a href="<c:url value="/" />">Home</a></li>
		<li><a href="<c:url value="/quiz" />">Train</a></li>
		<li><a href="<c:url value="/arena" />">Battle</a></li>
		<li><a href="<c:url value="/leaderboard" />">Leaderboard</a></li>
	</ul>
</nav>