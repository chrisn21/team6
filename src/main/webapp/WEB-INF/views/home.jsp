<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome to THE GAME</title>
	<jsp:include page="includes.jsp"/>
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
	


<jsp:include page="header.jsp"/>


<div class="callout callout-top clearfix">
	<div class="grid">
		<h2>Smart Pokemon Wars</h2>
		<h4>Smart Pokemon Trivia Games</h4>
		
		<div>
		<!-- Slideshow -->
<ul class="slideshow">
<li><img src="<c:url value="/resources/css/Media/pokemon_frontpage.png"/>" width="550" height="350" /></li>
<li><img src="<c:url value="/resources/css/Media/welcome_banner.jpg"/>" width="550" height="350" /></li>

</ul>
		</div>
		<div class="clear"></div><br />
		<a class="button red large" href="create_Pokemon.html"><i class="icon-wrench"></i> Create PokeAvatar</a> 
		<span style="white-space: nowrap;">
		<a class="button blue large" href="leadership.html"><i class="icon-group"></i></a>
		<a class="button blue large" href="contact.html"><i class="icon-envelope-alt"></i></a></span><br />
		<p>Pokemon Wars All Rights Reserved</p> 
	</div>
</div>

<div class="callout clearfix">
</div>

<!-- ===================================== START FOOTER ===================================== -->
<div class="clear"></div>
<div id="footer">
&copy; Copyright 2014 All Rights Reserved. Welcome to the World of Fun PokeWars 
<a href="elements.html">Pokemon Wars</a>  
<a href="login.html">Login</a> 
<a href="contact.html">Contact Us</a>
</div>

</body></html>