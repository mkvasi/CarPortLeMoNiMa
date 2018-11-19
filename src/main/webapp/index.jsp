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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="bootstrap/css/css-orderform.css" rel="stylesheet">

    </head>
    <body>


        <div class="page-container" >

            <form action="FrontController" method="POST" name="billofmaterial" action="FrontController" method="POST">
                <input type="hidden" name="command" value="billofmaterial">
                <h1>Indtast mål til ønsket carport nedenfor</h1>

                <input list="hosting-plan" type="label" placeholder="Vælg længden" name="length">

                <datalist id="hosting-plan" >
                    <option value="2.40" />
                    <option value="2.70"/>
                    <option value="3.00"/>
                    <option value="3.30" />
                    <option value="3.60"/>
                    <option value="3.90"/>
                    <option value="4.20" />
                    <option value="4.50"/>
                    <option value="4.80"/>
                    <option value="5.10"/>
                    <option value="5.40"/>
                    <option value="5.70"/>
                    <option value="6.00"/>
                    <option value="6.30"/>
                    <option value="6.60"/>
                    <option value="6.90"/>
                    <option value="7.20"/>
                    <option value="7.50"/>
                    <option value="7.80"/>
                </datalist>
                <input list="hosting-plan" type="label" placeholder="Vælg bredde" name="width" >

                <datalist id="hosting-plan" >
                    <option value="2.40" />
                    <option value="2.70"/>
                    <option value="3.00"/>
                    <option value="3.30" />
                    <option value="3.60"/>
                    <option value="3.90"/>
                    <option value="4.20" />
                    <option value="4.50"/>
                    <option value="4.80"/>
                    <option value="5.10"/>
                    <option value="5.40"/>
                    <option value="5.70"/>
                    <option value="6.00"/>
                    <option value="6.30"/>
                    <option value="6.60"/>
                    <option value="6.90"/>
                    <option value="7.20"/>
                    <option value="7.50"/>
                    <option value="7.80"/>
                </datalist>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label" for="gridCheck" name="roof">
                          Tag med hældning
                        </label>
                        <input  type="checkbox" id="gridCheck" name="roof" value="true">
                    </div>

         
                    <button type="submit" value="Add" name="submit">Se carport</button>
            </form>

        </div>
    </body>
</html>

<!--<form name="billofmaterial" action="FrontController" method="POST">
            <input type="hidden" name="command" value="billofmaterial">
            <input type="submit" value="Stykliste">
        </form>--> 