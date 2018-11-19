<%-- 
    Document   : register
    Created on : 17-11-2018, 22:20:02
    Author     : leage
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="bootstrap/css/css-orderform.css" rel="stylesheet">

    </head>
    <body>


       <div class="page-container">
            
            <form action="#" method="POST">
			<h1>Sign Up</h1>
                <input type="text" name="name" class="Name" placeholder="Name">
                <input type="text" name="tele" class="Tele" placeholder="Number Phone">
				<input type="text" name="email" class="Email" placeholder="Email">
				<input type="password" name="password" class="Address" placeholder="password">
                <button type="submit" value="Add" name="submit">Submit</button>
            </form>
        </div>
    </body>
</html>