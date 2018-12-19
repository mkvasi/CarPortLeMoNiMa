<%@page import="FunctionLayer.entity.Material"%>
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

        <% List<String> claddingflatroof = (List<String>) request.getAttribute("claddingflatroof");%>
        <% List<Material> claddingsloperoof = (List<Material>) request.getAttribute("claddingsloperoof");%>


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

        <h3 style="text-align: center;">Beregn din carport nedenfor</h3>

        <hr>


        <div class="containerForCarport">
            <form action="FrontController" method="POST" name="carportOverview">
                <input type="hidden" name="command" value="carportOverview">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01" >Længde på carport</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="length" >

                    <option selected value="2.4">2,4 m.</option>
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

                    <option selected value="2.4">2,4 m.</option>
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
                    <label class="input-group-text" for="inputGroupSelect01">Hældning på taget?</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="roof">
                    <option selected value="0">Ingen hældning</option>
                    <option value="10">10°</option>
                    <option value="15">15°</option>
                    <option value="20">20°</option>
                    <option value="25">25°</option>
                    <option value="30">30°</option>
                    <option value="35">35°</option>
                    <option value="40">40°</option>
                    <option value="45">45°</option>

                </select>

                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Tagtype fladt tag</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="roofflat">
                    <option selected value="0">Vælg tagtype</option>

                    <%
                        if (claddingflatroof == null) {
                            out.println("<option value =\"" + 1 + "\">" + "No materials");
                        } else {
                            for (String str : claddingflatroof) {
                                out.println("<option value=\"" + str + "\">" + str + "</option>");
                            }
                        }
                    %>

                </select>

                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Tagtype skråtag</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="roofslope">
                    <option selected value="0">Vælg tagtype</option>

                    <%
                        if (claddingsloperoof == null) {
                            out.println("<option value =\"" + 1 + "\">" + "No materials");
                        } else {
                            for (Material m : claddingsloperoof) {
                                out.println("<option value=\"" + m.getId() + "\">" + m.getDescription() + "</option>");
                            }
                        }
                    %>

                </select>

                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Vælge længde</label>
                </div>
                <select class="custom-select" id="inputGroupSelect01" name="shedLength">
                    <option selected value="-1">Ønsker ikke redsskabsrum</option>
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
                    <option selected value="-1">Ønsker ikke redskabsrum</option>
                    <option value="0">Halv bredde</option>
                    <option value="1">Fuld bredde</option>

                </select>
                <div id="button">
                    <button type="submit" class="btn btn-primary">Se carport</button>
                </div>
            </form>
        </div>

        <% String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<H2>Error!!</h2>");
                out.println(error);
            }
        %>



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
