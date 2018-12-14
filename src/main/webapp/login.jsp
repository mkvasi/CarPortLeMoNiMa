
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
                        <div class="navigation">
                            <nav>
                                <ul class="nav topnav">
                                    <li class="dropdown">
                                        <a href="http://localhost:8084/websitetest/login.jsp">Login</a>
                                    </li>
                                    <li class="dropdown active">
                                        <a href="http://localhost:8084/websitetest">Beregn carport</a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="http://localhost:8084/websitetest/WEB-INF/customerpage.jsp">Mine forespørgsler</a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="/FrontController?command=logout">Logud</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
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
                                <h3></h3>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <br>

        <h3 style="text-align: center;">Login eller opret bruger</h3>

        <hr>

        <br>

        <% String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<H2>Error!!</h2>");
                out.println(error);
            }
        %>


        <input type='checkbox' id='form-switch'>
        <form id='login-form' name="login" action="FrontController" method="POST">
            <input type="hidden" name="command" value="login">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type='submit' class="btn btn-primary">Login</button>
            <label for='form-switch'><span>Opret bruger</span></label>
        </form>
        <form id='register-form' name="register" action="FrontController" method="POST">
            <input type="hidden" name="command" value="register">
            <input type="text" name="firstname" placeholder="Firstname" required>
            <input type="text" name="lastname" placeholder="Lastname" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="number" name="zipcode" placeholder="Zipcode" required>
            <input type="text" name="city" placeholder="City" required>
            <input type="number" name="phone" placeholder="Phone" required>
            <input type="password" name="password1" placeholder="Password" required>
            <input type="password" name="password2" placeholder="RePassword" required>

            <button type='submit' class="btn btn-primary">Register</button>
            <label for='form-switch'>Allerede oprettet? Log ind her</label>
        </form>

        <!--        <form id='login-form' name="emplogin" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="emplogin">
                    <input type="email" name="email" placeholder="Username" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <button type='submit' class="btn btn-primary">Login</button>
                    <label for='form-switch'><span>Opret bruger</span></label>
                </form>
        -->

        <br>
        <br>


        <form id='login-form' name="emplogin" action="FrontController" method="POST">
            <input type="hidden" name="command" value="emplogin">
            <input type="email" name="email" placeholder="Employee email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type='submit' class="btn btn-primary">Login</button>
        </form>







        <!-- Footer
       ================================================== -->
        <footer class="footer">


            <div class="container">
                <div class="row">
                    <div class="span6">
                        <p>
                            &copy; LeMoNiMa - All right reserved
                        </p>
                    </div>
                </div>
            </div>

        </footer>


    </body>

</html>
