<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
function err(type, message)
{
	var err = new Error();
	err.name = 'My API ' + type + ' Error';
	err.message = message;
	throw(err);
}

function validateForm()
{
	var a=document.forms["acctSignup"]["firstName"].value;var b=document.forms["acctSignup"]["lastName"].value;
	var c=document.forms["acctSignup"]["username"].value;var d=document.forms["acctSignup"]["password"].value;
	var e=document.forms["acctSignup"]["email"].value;var f=document.forms["acctSignup"]["dob"].value;
	var g=document.forms["acctSignup"]["characterName"].value;var h=document.forms["acctSignup"]["character"].value;
	if (a,b,c,d,e,f,g,h == '')
	{
	  alert("Error: Cannot process sign-up request, all fields must be filled out.");
	  return false;
	}

    if(isDate(f))
   		return true;
    else
    {
    	alert("Error: Invalid Date Of Birth Provided");
    	return false;
    }	
    
}

function isDate(txtDate)
{
    var reg = /^(0[1-9]|1[012])([\/-])(0[1-9]|[12][0-9]|3[01])\2(\d{4})$/;
    return reg.test(txtDate);
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Sign-up</title>
</head>
<body>
	<form name="acctSignup" action="processSignup" onsubmit="return validateForm()" method="POST">
	First name: <input type="text" name="firstName"><br>
	Last name: <input type="text" name="lastName"><br>
	User name: <input type="text" name="username"><br>
	Password: <input type="password" size="20" name="password"><br>
	Email: <input type="text" name="email"><br>
	DOB [MM/DD/YYYY]: <input type="text" name="dob"><br>
	Character Name: <input type="text" name="characterName"><br>
		<div>
			<h4> Choose a character: </h4>
			  <select name="character" id="userID" size="3">
			   <option style="background-image:url(mushroom.png);">Joey</option>
			  </select>
		</div>
	<input type="submit" value="Submit">
</form>
</body>
</html>