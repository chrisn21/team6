<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome to THE GAME</title>
	<jsp:include page="../includes.jsp"/>
	<title>SmartPokemon Wars</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<meta name="description" />
<meta name="copyright" />
<link rel="stylesheet" type="text/css" href="css/kickstart.css" media="all" />                  
<link rel="stylesheet" type="text/css" href="style.css" media="all" />                          <!-- CUSTOM STYLES -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/kickstart.js"></script>                                  
</head>
<body topmargin="0" leftmargin="200" rightmargin="200" bottommargin="0" bgcolor="#000000">
<!-- ===================================== END HEADER ===================================== -->




<jsp:include page="../header.jsp"/>
<div class="callout callout-top">
<h1>Leaderboard</h1>
<h5>Can you make it to the top?</h5>
</div>

<div class="callout callout-top clearfix">
	<div class="grid">
		<div id="test_id">
		<h2 id="test_id">Pokemon Wars Leaderboards</h2>
		</div>
		

<div id="leaderboard">
<table>
	<tbody>
	<tr><td> Username </td><td> Points </td></tr>
		<c:forEach var="user" items="${leaderboard}">
			<tr><td><c:out value="${user.username}"/></td>
			<td><c:out value="${user.points}"/></td></tr>
		</c:forEach>
	</tbody>
</table>
</div>
</div>
		
		<div class="clear"></div><br /><br /><br/> <br/> 
		<span style="white-space: nowrap;">
		<p>Pokemon Wars All Rights Reserved</p> 

</div>

<!-- <div class="callout clearfix"> </div>  --> 

<!-- ===================================== START FOOTER ===================================== -->
<div class="clear"></div>
<div id="footer">
&copy; Copyright 2014 All Rights Reserved. Welcome to the World of Fun PokeWars 
<a href="elements.html">Pokemon Wars</a>  
<a href="login.html">Login</a> 
<a href="contact.html">Contact Us</a>
</div>

</body></html>		