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
<h1>Sign-Up</h1>
<h5>Let's get started!</h5>
</div>

		
<div class="container">

      <form class="form-signin">
        <h3 class="form-signin-heading">Please enter your information.</h3>
        <input type="text" name="firstName" class="input-block-level" placeholder="First Name">
		<input type="text" name="lastName" class="input-block-level" placeholder="Last Name">
		<input type="password" name="password" class="input-block-level" placeholder="Password">
		<input type="email" name="email" class="input-block-level" placeholder="email">
		<input type="text" name="dob" class="input-block-level" placeholder="DOB [MM/DD/YYYY]:">
        <input type="text" name="characterName" class="input-block-level" placeholder="Character Name:">
		<div><img src="pokemon/charizard.gif" alt="Smiley face" height="120" width="120"><input type="submit" name="character" value="Charizard"></div>
		<br />
        <button name="character" class="btn btn-large btn-primary" type="submit">Sign up</button>
      </form>

    </div> <!-- /container -->

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