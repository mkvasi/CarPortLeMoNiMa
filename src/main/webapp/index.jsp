<%-- 
    Document   : newjsp
    Created on : 16-11-2018, 13:58:40
    Author     : Morten
--%>

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
            <form action="FrontController" method="POST" name="carportOverview" action="FrontController" method="POST">
                <input type="hidden" name="command" value="carportOverview">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01" >Længde på carport</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="length" >
                    <option selected>Vælg venligst længde</option>
                    <option value="2.4">2,4 m.</option>
                    <option value="2.7">2,7 m</option>
                    <option value="3.0">3,0 m</option>
                    <option value="3.3">3,3 m</option>
                    <option value="3.6">3,6 m</option>
                    <option value="3.9">3,9 m</option>
                    <option value="4.2">4,2 m</option>
                    <option value="4.5">4,5 m</option>
                    <option value="5.1">5,1 m</option>
                    <option value="5.4">5,4 m</option>
                    <option value="5.7">5,7 m</option>
                    <option value="6.0">6,0 m</option>
                    <option value="6.3">6,3 m</option>
                    <option value="6.6">6,6 m</option>
                    <option value="6.9">6,9 m</option>
                    <option value="7.2">7,2 m</option>
                    <option value="7.5">7,5 m</option>
                    <option value="7.8">7,8 m</option>
                </select>
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Bredde på carport</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="width">
                       <option selected>Vælg venligst bredde</option>
                    <option value="2.4">2,4 m.</option>
                    <option value="2.7">2,7 m</option>
                    <option value="3.0">3,0 m</option>
                    <option value="3.3">3,3 m</option>
                    <option value="3.6">3,6 m</option>
                    <option value="3.9">3,9 m</option>
                    <option value="4.2">4,2 m</option>
                    <option value="4.5">4,5 m</option>
                    <option value="5.1">5,1 m</option>
                    <option value="5.4">5,4 m</option>
                    <option value="5.7">5,7 m</option>
                    <option value="6.0">6,0 m</option>
                    <option value="6.3">6,3 m</option>
                    <option value="6.6">6,6 m</option>
                    <option value="6.9">6,9 m</option>
                    <option value="7.2">7,2 m</option>
                    <option value="7.5">7,5 m</option>
             
                </select>
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Vil du have tag med hældning?</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="roof">
                    <option selected value="Ja">Ja</option>
                    <option value="Nej">Nej</option>

                </select>
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Redskabsrum:</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="shed">
                    <option selected>Vælg venligst nedenfor</option>
                    <option value="Ja">Ja</option>
                    <option value="Nej">Nej</option>

                </select>
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Vælge længde</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="shedLength">
                    <option selected value="0.0">Ønsker ikke redsskabsrum</option>
                   <option value="2.4">2,4 m.</option>
                    <option value="2.7">2,7 m</option>
                    <option value="3.0">3,0 m</option>
                    <option value="3.3">3,3 m</option>
                    <option value="3.6">3,6 m</option>
                    <option value="3.9">3,9 m</option>
                    <option value="4.2">4,2 m</option>
                    <option value="4.5">4,5 m</option>
                    <option value="5.1">5,1 m</option>
                    <option value="5.4">5,4 m</option>
                    <option value="5.7">5,7 m</option>
                    <option value="6.0">6,0 m</option>
                    <option value="6.3">6,3 m</option>
                    <option value="6.6">6,6 m</option>
                    <option value="6.9">6,9 m</option>
                    <option value="7.2">7,2 m</option>
                    <option value="7.5">7,5 m</option>

                </select>
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Vælg bredde</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="shedWidth">
                    <option selected value="0.0">Ønsker ikke redskabsrum</option>
                    <option value="2.4">2,4 m.</option>
                    <option value="2.7">2,7 m</option>
                    <option value="3.0">3,0 m</option>
                    <option value="3.3">3,3 m</option>
                    <option value="3.6">3,6 m</option>
                    <option value="3.9">3,9 m</option>
                    <option value="4.2">4,2 m</option>
                    <option value="4.5">4,5 m</option>
                    <option value="5.1">5,1 m</option>
                    <option value="5.4">5,4 m</option>
                    <option value="5.7">5,7 m</option>
                    <option value="6.0">6,0 m</option>
                    <option value="6.3">6,3 m</option>
                    <option value="6.6">6,6 m</option>
                    <option value="6.9">6,9 m</option>
                    <option value="7.2">7,2 m</option>
                    <option value="7.5">7,5 m</option>

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
