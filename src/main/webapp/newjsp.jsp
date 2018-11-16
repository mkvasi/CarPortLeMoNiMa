<%-- 
    Document   : newjsp
    Created on : 16-11-2018, 13:58:40
    Author     : Morten
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="billofmaterial" action="FrontController" method="POST">
            <input type="hidden" name="command" value="billofmaterial">
            <input type="submit" value="Stykliste">
        </form>
    </body>
</html>
