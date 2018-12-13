<%-- 
    Document   : index
    Created on : 14-11-2018, 13:44:38
    Author     : Yeha
--%>

<%@page import="FunctionLayer.entity.Request"%>
<%@page import="FunctionLayer.entity.Employee"%>
<%@page import="FunctionLayer.entity.Customer"%>
<%@page import="FunctionLayer.entity.Price"%>
<%@page import="FunctionLayer.entity.LineItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="FunctionLayer.BillOfMaterial"%>
<%@page import="java.util.HashMap"%>
<%@page import="FunctionLayer.entity.Carport"%>
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

        <!-- =======================================================
          Theme Name: Serenity
          Theme URL: https://bootstrapmade.com/serenity-bootstrap-corporate-template/
          Author: BootstrapMade.com
          Author URL: https://bootstrapmade.com
        ======================================================= -->
    </head>

    <% Carport carport = (Carport) request.getSession().getAttribute("carport");%>

    <%  BillOfMaterial billofmaterial = (BillOfMaterial) request.getAttribute("billOfMaterial");  %>
    <% Price offerPrice = (Price) request.getSession().getAttribute("offerprice");%>
    <% Customer customer = (Customer) request.getSession().getAttribute("customer");%>
    <% Employee employee = (Employee) request.getSession().getAttribute("employee");%>
    <% Request requestClass = (Request) request.getAttribute("request");%>
    <% String requestDate = (String) request.getAttribute("date");%>
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
                                    <li class="dropdown active">
                                        <a href="index.html">Home</a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#">Features</a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#">Pages</a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="http://localhost:8084/websitetest/login.jsp">Login</a>
                                    </li>
                                    <li>
                                        <a href="contact.html">Contact</a>
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

        <h3 style="text-align: center;">Information</h3>

        <hr>


        <div id="carportinfo">
            <!-- <form action="FrontController" method="POST" name="billOfMaterial" action="FrontController" method="POST">
                  <input type="hidden" name="command" value="billOfMaterial"> -->


            <table>
                <thead>
                    <tr>
                        <th>Carport bredde</th>
                        <td><%= (carport.getWidth())%> m</td>
                    </tr>


                    <tr>
                        <th>Carport længde</th>
                        <td><%out.print(carport.getLength());%> m</td>
                    </tr>
                    <tr>
                        <th>Tag bredde</th>
                        <td><%= (carport.getRoof().getWidth())%> m</td>
                    </tr>
                    <tr>
                        <th>Tag længde</th>
                        <td><%= (carport.getRoof().getLength())%> m</td>
                    </tr>
                    <tr>
                        <th>Redskabsrum bredde</th>
                        <td>
                            <%
                                if (carport.getShed() == null) {
                                    out.println("Ønsker ikke redskabsrum");
                                } else {
                                    out.println(carport.getShed().getWidth() + " m");
                                }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <th>Redskabsrum længde</th>
                        <td>
                            <%
                                if (carport.getShed() == null) {
                                    out.println("Ønsker ikke redskabsrum");
                                } else {
                                    out.println(carport.getShed().getLength() + " m");
                                }
                            %>
                        </td>
                    </tr>
                    <!--<tr>
                          <th>Pris</th>
                          <td><//%=offerPrice%></td>
                          
                      </tr>-->

                </thead>
            </table>
            <!-- </form>-->
        </div>

        <% if (customer != null || employee != null) { %>           
        <div id="customerinfo">


            <table>
                <thead>
                    <tr>
                        <th>Navn</th>
                            <% if (customer != null || employee != null) {
                                    out.print("<td>" + customer.getFirstName() + " " + customer.getLastName() + "</td>");
                                }%>
                    </tr>

                    <tr>
                        <th>Kundenummer</th>
                            <% if (customer != null || employee != null) {
                                    out.print("<td>" + customer.getId() + "</td>");
                                }%>
                    </tr>
                    <tr>
                        <th>Postnummer</th>
                            <% if (customer != null || employee != null) {
                                    out.print("<td>" + customer.getZipcode() + "</td>");
                                }%>
                    </tr>

                    <tr>
                        <th>By</th>
                            <% if (customer != null || employee != null) {
                                    out.print("<td>" + customer.getCity() + "</td>");
                                }%>
                    </tr>
                    <tr>
                        <th>Telefon</th>
                            <% if (customer != null || employee != null) {
                                    out.print("<td>" + customer.getPhone() + "</td>");
                                }%>
                    </tr>
                    <tr>
                        <th>E-mail adresse</th>
                            <% if (customer != null || employee != null) {
                                    out.print("<td>" + customer.getEmail() + "</td>");
                                }%>
                    </tr>

                </thead>
            </table>

        </div>
        <%}%>

        <div id="dates">


            <table>
                <thead>
                    <tr>
                        <th>Dato forespørgsel</th>
                            <% if (requestClass != null) {
                                    out.print("<td>" + requestClass.getRequestDate() + "</td>");
                                } else {
                                    out.print("<td>" + requestDate + "</td>");
                                }%>
                    </tr>
                    <% if ((customer != null || employee != null)) {%>
                    <% if (employee != null) {%>
                    <tr>
                        <th>Dato tilbud</th>
                        <td><form id='login-form' name="emplogin" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="sendoffer">
                                <button type='submit' class="btn btn-primary">Send tilbud</button>
                            </form></td>
                    </tr>
                    <%} else if (requestClass != null) {
                            out.print("<td>" + requestClass.getOfferDate() + "</td>");
                        }%>
                    <% if (customer != null && requestClass != null) {%>
                    <tr>
                        <th>Dato betaling</th>
                        <td>            <form id='login-form' name="emplogin" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="acceptoffer">
                                <button type='submit' class="btn btn-primary">Accepter tilbud og betal</button>
                            </form></td>
                    </tr>
                    <%} else if (requestClass != null) {
                            out.print("<td>" + requestClass.getPaymentDate() + "</td>");
                        }%>
                    <%}%>

                </thead>
            </table>

        </div>

        <div id="pris">

            <table>
                <thead>
                    <tr>


                        <th>Pris</th>
                        <td><%=offerPrice.getSellprice()%></td>


                    </tr>                 

                </thead>
            </table>


        </div>
        <br>
        <br>
        <h3 style="text-align: center;">Tegninger</h3>

        <hr>


        <div id="yderst">

            <SVG width="700" height=800>
            <rect width="100%" height="100%" fill="#FFFFFF"/>

            <rect x="77" y="30" height="700" width="540" stroke-width="2"
                  style="stroke:#000000; fill: #EFF2FB"/>



            <SVG x="97" y="50" width="500" height=600>
            <rect width="100%" height="100%" fill="#E6E6E6"/>

            <rect x="" y="0" width="500" height="600" stroke-width="3"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="15" y="15" height="570" width="470" stroke-width="2"
                  style="stroke:#000000; fill: #EFF2FB"/>


            <!--
                        Det her er de 6 stolper.
                        
                        koden til venstre midter stolpe: 
                        []<rect y="50%" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>[]
                                  
                        koden til højre midter stolpe:
                        <rect y="50%" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>
                                  
                        Det er til at placere en stolpe i midten hvis carporten er lang nok.
            -->


            <rect y="0" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="585" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>



            <rect y="0" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="585" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <%
                if (carport.getLength() >= 6) {
                    out.println("<rect x=\"485\" y=\"50%\" height=\"15\" width=\"14\" stroke-width=\"2\"style=\"stroke:#000000; fill: #3B0B0B\"/>");
                    out.println("<rect y=\"50%\" height=\"15\" width=\"14\" stroke-width=\"2\"style=\"stroke:#000000; fill: #3B0B0B\"/>");
                }
            %>

            <!--  <rect y="50%" x="485" height="15" width="15"
                    style="stroke:#000000; fill: #3B0B0B"/>
              
              <rect y="50%" height="15" width="15"
                    style="stroke:#000000; fill: #3B0B0B"/>
            -->


            <%    if (carport.getShed() != null) {
                    if (carport.getShed().getWidth() == carport.getWidth()) {
                        out.println("<rect x=\"15\" y=\"150\" height=\"15\" width=\"470\" stroke-width=\"2\"\n"
                                + "				  style=\"stroke:#000000; fill: #F3E2A9\"/>\n"
                                + "				  \n"
                                + "			\n"
                                + "			<rect y=\"150\" x=\"485\" height=\"15\" width=\"15\"\n"
                                + "                  style=\"stroke:#000000; fill: #3B0B0B\"/>\n"
                                + "\n"
                                + "			<rect y=\"150\" height=\"15\" width=\"15\"\n"
                                + "                  style=\"stroke:#000000; fill: #3B0B0B\"/> \n"
                                + "				  \n"
                                + "			<rect y=\"0\" x=\"243\" height=\"15\" width=\"15\"\n"
                                + "                  style=\"stroke:#000000; fill: #3B0B0B\"/>\n"
                                + "				  \n"
                                + "			<rect y=\"150\" x=\"243\" height=\"15\" width=\"15\"\n"
                                + "                  style=\"stroke:#000000; fill: #3B0B0B\"/>");
                    } else {
                        out.println("<rect x=\"15\" y=\"150\" height=\"15\" width=\"235\" stroke-width=\"2\"\n"
                                + "				  style=\"stroke:#000000; fill: #F3E2A9\"/>\n"
                                + "				  \n"
                                + "			<rect x=\"243\" y=\"15\" height=\"150\" width=\"15\" stroke-width=\"2\"\n"
                                + "				  style=\"stroke:#000000; fill: #F3E2A9\"/>\n"
                                + "			\n"
                                + "\n"
                                + "			<rect y=\"150\" height=\"15\" width=\"15\"\n"
                                + "                  style=\"stroke:#000000; fill: #3B0B0B\"/> \n"
                                + "				  \n"
                                + "			<rect y=\"0\" x=\"243\" height=\"15\" width=\"15\"\n"
                                + "                  style=\"stroke:#000000; fill: #3B0B0B\"/>\n"
                                + "				  \n"
                                + "			<rect y=\"150\" x=\"243\" height=\"15\" width=\"15\"\n"
                                + "                  style=\"stroke:#000000; fill: #3B0B0B\"/>");
                    }
                }


            %>

            </SVG>

            <rect x="98" y="650" height="79" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>


            <rect x="582" y="650" height="79" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="98" y="30" height="20" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="582" y="30" height="20" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <!--
            Hver linje her er et spær. De har alle samme værdier undtagen y1 og y2.
            De stiger med 34 for hvert spær. Dvs. vi skal plusse y1 og y2 med 34 hver gang
            der sættes spær på, indtil vi rammer det rigtige antal spær. Men det kommer til
            at fucke up, for så er der ingen spær for enden hvis lille carport.
            Derfor skal vi tage det antal spær carporten skal bruge og dele dem op på 700px.
            dvs. 700 / antal spær = det antal px hvert spær stiger med.
            Så ved vi hvor mange px der skal være mellem hvert spær, som vi derefter indsætter
            på y1 og y2.
            -->


            <%  int qty = 0;
                for (LineItem lineItem : billofmaterial.getLineItems()) {
                    if (lineItem.getHelpDescription().equals("Spær")) {
                        qty = lineItem.getQty();
                    }

                }

                int pxmellemspar = 700 / qty;
                int nextspar = 700 / qty;

                for (int i = 0; i < qty; i++) {
                    out.println("<line x1=\"77\" y1=\"" + nextspar + "\" " + "x2=\"618\" y2=\"" + nextspar + "\" " + "style=\"stroke:rgb(0,0,0);stroke-width:3\" />");

                    nextspar += pxmellemspar;

                }


            %>

            <!--
            
            <line x1="77" y1="62" x2="618" y2="62" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="96" x2="618" y2="96" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="130" x2="618" y2="130" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="164" x2="618" y2="164" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="198" x2="618" y2="198" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="232" x2="618" y2="232" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="266" x2="618" y2="266" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="300" x2="618" y2="300" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="334" x2="618" y2="334" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="368" x2="618" y2="368" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="402" x2="618" y2="402" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="436" x2="618" y2="436" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="470" x2="618" y2="470" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="504" x2="618" y2="504" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="538" x2="618" y2="538" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="572" x2="618" y2="572" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="606" x2="618" y2="606" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="640" x2="618" y2="640" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="674" x2="618" y2="674" style="stroke:rgb(0,0,0);stroke-width:3" />
            <line x1="77" y1="708" x2="618" y2="708" style="stroke:rgb(0,0,0);stroke-width:3" />
            
            -->

            <!--
            Her erstatter vi "3 m" med den bredde carporten har ud til spærene
            -->

            <text x="48%" y="755" ><%= (carport.getWidth())%> m </text>


            <line x1="97" y1="760" x2="598" y2="760" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="97" y1="765" x2="97" y2="755" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="598" y1="765" x2="598" y2="755" style="stroke:rgb(0,0,0);stroke-width:2" />

            <!--
            Her erstatter vi "3,2 m" med den bredde brugeren indtaster
            -->

            <text x="48%" y="780" ><%= (carport.getRoof().getWidth())%> m</text>


            <line x1="77" y1="785" x2="618" y2="785" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="77" y1="780" x2="77" y2="790" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="618" y1="780" x2="618" y2="790" style="stroke:rgb(0,0,0);stroke-width:2" />

            <!--
            Her erstatter vi "5 m" med den længde brugeren indtaster
            -->

            <text x="640" y="400" style="writing-mode: sideways-lr;"><%= (carport.getRoof().getLength())%> m</text>


            <line x1="645" y1="30" x2="645" y2="730" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="650" y1="30" x2="640" y2="30" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="650" y1="730" x2="640" y2="730" style="stroke:rgb(0,0,0);stroke-width:2" />

            <!--
            Den her er altid 0,55 m
            -->

            <text x="54" y="<%= pxmellemspar * 1.33%>" style="writing-mode: sideways-lr;">0,55 m</text>

            <line x1="60" y1="<%= pxmellemspar%>" x2="60" y2="<%= pxmellemspar * 2%>" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="55" y1="<%= pxmellemspar%>" x2="65" y2="<%= pxmellemspar%>" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="55" y1="<%= pxmellemspar * 2%>" x2="65" y2="<%= pxmellemspar * 2%>" style="stroke:rgb(0,0,0);stroke-width:2" />



            </SVG>
        </div>




        <div id="svgfront">

            <SVG width="700" height=600>
            <rect width="100%" height="100%" fill="#FFFFFF"/>


            <rect x="120" y="299" height="300" width="16" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="550" y="299" height="300" width="16" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="115" y="299" height="28" width="13" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="558" y="299" height="28" width="13" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="55" y="272" height="25" width="570" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <text x="648" y="420" fill="" style="writing-mode: sideways-lr;">2,25 m</text>

            <line x1="655" y1="272" x2="655" y2="598" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="650" y1="272" x2="660" y2="272" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="650" y1="598" x2="660" y2="598" style="stroke:rgb(0,0,0);stroke-width:2" />


            <rect x="223" y="569" rx="5" ry="5" height="30" width="30" stroke-width="2"
                  style="stroke:#000000; fill: #424242"/> 

            <rect x="433" y="569" rx="5" ry="5" height="30" width="30" stroke-width="2"
                  style="stroke:#000000; fill: #424242"/> 

            <rect x="243" y="440" rx="10" ry="10" height="100" width="200" stroke-width="2"
                  style="stroke:#000000; fill: #6E6E6E"/>

            <rect x="218" y="488" rx="10" ry="10" height="100" width="250" stroke-width="2"
                  style="stroke:#000000; fill: #043D80"/>


            <rect x="230" y="500" rx="5" ry="5" height="20" width="40" stroke-width="2"
                  style="stroke:#000000; fill: #DF0101"/>	  

            <rect x="416" y="500" rx="5" ry="5" height="20" width="40" stroke-width="2"
                  style="stroke:#000000; fill: #DF0101"/>

            <rect x="308" y="545" rx="5" ry="5" height="25" width="65" stroke-width="2"
                  style="stroke:#000000; fill: #D8D8D8"/>

            <text x="328" y="562" fill="">FOG</text>


            <%
                if (carport.getRoof().getRoofSlopeCelsius() == 10) {
                    out.println("<polygon points=\"330,230 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>

            <%                if (carport.getRoof().getRoofSlopeCelsius() == 15) {
                    out.println("<polygon points=\"330,220 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>

            <%                if (carport.getRoof().getRoofSlopeCelsius() == 20) {
                    out.println("<polygon points=\"330,210 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>

            <%                if (carport.getRoof().getRoofSlopeCelsius() == 25) {
                    out.println("<polygon points=\"330,200 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>

            <%                if (carport.getRoof().getRoofSlopeCelsius() == 30) {
                    out.println("<polygon points=\"330,190 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>

            <%                if (carport.getRoof().getRoofSlopeCelsius() == 35) {
                    out.println("<polygon points=\"330,180 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>

            <%                if (carport.getRoof().getRoofSlopeCelsius() == 40) {
                    out.println("<polygon points=\"330,170 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>

            <%                 if (carport.getRoof().getRoofSlopeCelsius() == 45) {
                    out.println("<polygon points=\"330,160 625,272 55,272\" style=\"fill: #F3E2A9; stroke:#000000 ;stroke-width:2\" />");
                }

            %>




            </SVG>
        </div>

        <br>
        <br>
        <br>
        <br>
        <br>
        <br>

        <% if (employee != null) { %>

        <h3 style="text-align: center;">Stykliste</h3>

        <hr>





        <div id="stykliste">                                   
            <table>
                <thead>
                    <tr>
                        <th>Produkt</th>
                        <th>Anvendelses område</th>
                        <th>Antal</th>
                        <th>Længde</th>

                    </tr>
                </thead>
                <tbody>
                    <% for (LineItem lineItem : billofmaterial.getLineItems()) {%>
                    <tr>
                        <td><% out.print(lineItem.getMaterial().getDescription()); %></td>
                        <td><% out.print(lineItem.getHelpDescription()); %></td>
                        <td><% out.print(lineItem.getQty()); %></td>
                            <td><% out.print(lineItem.getMaterial().getLength());
                                }%></td>



                        <!-- create an html table row -->
                        <!-- create cells of row -->
                        <!-- close row -->
                        <!-- close the loop -->

                        <!-- close table --> 
                    </tr>
                </tbody>
            </table> 
        </div>
        <% }%>

        <% if (customer != null) { %>
        <div id="forspgknap">
            <form id='login-form' name="emplogin" action="FrontController" method="POST">
                <input type="hidden" name="command" value="sendrequest">
                <button type='submit' class="btn btn-primary">Send forespørgsel</button>
            </form>
        </div>
        <% }%>


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
