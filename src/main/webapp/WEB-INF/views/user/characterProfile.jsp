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
<h1>Your Character</h1>
<h5>It's all about <c:out value="${character.characterName}"/>!</h5>
</div>

<div class="grid">

		
		<div id="commands">
		</div>

		<div id="log">
		</div>
					
			<div class="clear"></div><br />
				<div class="myprofile">	
				
					<div class="charimg">
						<img src="<c:url value="/resources/img/charizard.gif"/>" alt="Smiley face" height="120" width="120"><input type="submit" name="character" value="Charizard">
					</div>
					<div class="charstats">
					<table><tr><td><h4>Character Stats:</h4></td></tr>
						<tr><td>Character Name: <c:out value="${character.characterName}"/></td></tr>
						<tr><td>Level: 			<c:out value="${character.level}"/></td></tr>
						<tr><td>Experience:		<c:out value="${character.experience}"/></td></tr>
						<tr><td>Health:			<c:out value="${character.health}"/></td></tr>
						<tr><td>Strength:		<c:out value="${character.str}"/></td></tr>
						<tr><td>Defense:		<c:out value="${character.def}"/></td></tr>
						</table>
					</div>
				</div>	
			</div>
	</div>	



<div class="callout clearfix">
</div>


<!-- ===================================== START FOOTER ===================================== -->
<div class="clear"></div>
<div id="footer">
&copy; Copyright 2014 All Rights Reserved. Welcome to the World of Fun Pet Wars 
<a href="elements.html">Pokemon Wars</a> 
<a href="login.html">Login</a> 
<a href="contact.html">Contact Us</a>
</div>

</body></html>