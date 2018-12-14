<%-- 
    Document   : customerpage
    Created on : 06-12-2018, 08:52:53
    Author     : Morten
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <%
            if (listOfCustomerRequest != null) {
        %>
        <h1>Tidligere forespørgsler</h1>
        <table>
            <tr>
                <td>
                    <form name="customerrequestdetails" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="customerrequestdetails" >
                        <select name="r_id">
                            <%                                for (Integer r_id : listOfCustomerRequest) {
                                    out.println("<option value=\"" + r_id + "\">ForespørgselsID: " + r_id + "</option>");
                                }

                            %>
                        </select>
                        <br><br>
                        <input type="submit" value="Vis valgte forespørgsel">
                    </form>
                </td>
            </tr>
        </table>
        <%                            } else {
        %>
        <h1>Ingen tidligere forespørgsler!</h1>
        <%
            }
        %>
    </body>
</html>
