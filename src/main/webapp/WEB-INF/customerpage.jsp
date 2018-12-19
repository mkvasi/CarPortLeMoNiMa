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


    </head>
    <% List<Integer> listOfCustomerRequest = (List<Integer>) request.getAttribute("listOfCustomerRequest"); %>
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

                                        <form name="loginpage" action="FrontController" method="POST">
                                            <input type="hidden" name="command" value="loginpage" >
                                            <button type='submit' class="btn btn-primary">Login</button>
                                        </form>


                                        <form name="startpage" action="FrontController" method="POST">
                                            <input type="hidden" name="command" value="startpage" >
                                            <button type='submit' class="btn btn-primary">Beregn Carport</button>
                                        </form>

                                        <form name="customerrequest" action="FrontController" method="POST">
                                            <input type="hidden" name="command" value="customerrequest">
                                            <button type='submit' class="btn btn-primary">Mine forespørgsler</button>
                                        </form>

                                        <form name="logout" action="FrontController" method="POST">
                                            <input type="hidden" name="command" value="logout">
                                            <button type='submit' class="btn btn-primary">Log ud</button>
                                        </form>

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
        <%
            if (listOfCustomerRequest != null) {
        %>
        <h1>Tidligere forespørgsler</h1>



        

        <%                                for (Integer r_id : listOfCustomerRequest) {
                out.println("<form name=\"customerrequestdetails\" action=\"FrontController\" method=\"POST\">\n"
                        + "                <input type=\"hidden\" name=\"command\" value=\"customerrequestdetails\" >\n"
                        + "                <input type=\"hidden\" name=\"r_id\" value=\"" + r_id + "\" >\n"
                        + "                <button class=\"button1 button2\">" + r_id + "</button>\n"
                        + "            </form>");
            }

        %>          

        <%                            } else {
        %>
        <h1>Ingen tidligere forespørgsler!</h1>
        <%
            }
        %>
    </body>
</html>
