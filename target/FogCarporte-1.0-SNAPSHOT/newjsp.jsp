<%-- 
    Document   : index
    Created on : 14-11-2018, 13:44:38
    Author     : Yeha
--%>

<%@page import="java.util.HashMap"%>
<%@page import="FunctionLayer.Carport"%>
<%@page import="FunctionLayer.Material"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <title>Fog - carport beregner</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- styles -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600,700" rel="stylesheet">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="bootstrap/css/style.css" rel="stylesheet">
        <link href="bootstrap/color/default.css" rel="stylesheet">



        <!-- fav and touch icons -->

        <link rel="shortcut icon" href="bootstrap/ico/favicon.ico">

        <!-- =======================================================
          Theme Name: Serenity
          Theme URL: https://bootstrapmade.com/serenity-bootstrap-corporate-template/
          Author: BootstrapMade.com
          Author URL: https://bootstrapmade.com
        ======================================================= -->
    </head>

    <body data-spy="scroll" data-target=".bs-docs-sidebar">
       
        <header>
            <!-- Navbar
            ================================================== -->
            <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container">
                        <!-- logo -->
                        <a class="brand logo" href="index.html">
                            <img src="bootstrap/img/foglogo.PNG" alt="" />
                        </a>
                        <!-- end logo -->
                        <!-- top menu -->
                   
                        <!-- end menu -->
                    </div>
                </div>
            </div>
        </header>
        <!-- Subhead
      ================================================== -->
        <section id="subintro">
            <div class="jumbotron subhead" id="overview">
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <div class="centered">
                                <h3>Beregn din carport nedenfor</h3>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


      
                     
   <div class="containerForCarport">
        <form action="FrontController" method="POST" name="billofmaterial" action="FrontController" method="POST">
                <input type="hidden" name="command" value="billofmaterial">
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Længde på carport</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01" >
    <option selected>Vælg venligst længde</option>
    <option value="2.4">2,4 m.</option>
    <option value="2.7">2,7 m</option>
    <option value="3.0">3,0 m</option>
  </select>
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Bredde på carport</label>
  </div>
   <select class="custom-select" id="inputGroupSelect01" >
    <option selected>Vælg venligst bredde</option>
    <option value="2.4">2,4 m.</option>
    <option value="2.7">2,7 m</option>
    <option value="3.0">3,0 m</option>
  </select>
  <div class="input-group-prepend">
    <label class="input-group-text" for="inputGroupSelect01">Vi du have tag med hældning?</label>
  </div>
  <select class="custom-select" id="inputGroupSelect01">
    <option selected>Vælg venligst nedenfor</option>
    <option value="Ja">Ja</option>
    <option value="Nej">Nej</option>
    
  </select>
       <div id="button">
                    <button type="submit" class="btn btn-primary">Se carport</button>
                    </div>
         </form>
</div>
        

                        

     
        <!-- Footer
       ================================================== -->
        <footer class="footer">


            <div class="container">
                <div class="row">
                    <div class="span6">
                        <p>
                            &copy; Serenity - All right reserved
                        </p>
                    </div>
                    <div class="span6">
                        <div class="credits">
                            <!--
                              All the links in the footer should remain intact.
                              You can delete the links only if you purchased the pro version.
                              Licensing information: https://bootstrapmade.com/license/
                              Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Serenity
                            -->
                            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
                        </div>
                    </div>
                </div>
            </div>

        </footer>




    </body>

</html>
