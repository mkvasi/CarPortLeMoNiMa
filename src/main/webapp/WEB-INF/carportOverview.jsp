<%-- 
    Document   : index
    Created on : 14-11-2018, 13:44:38
    Author     : Yeha
--%>

<%@page import="FunctionLayer.entity.RequestObject"%>
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


    </head>

    <% Carport carport = (Carport) request.getSession().getAttribute("carport");%>
    <%  BillOfMaterial billofmaterial = (BillOfMaterial) request.getAttribute("billOfMaterial");  %>
    <% Price offerPrice = (Price) request.getSession().getAttribute("offerprice");%>
    <% Customer customer = (Customer) request.getSession().getAttribute("customer");%>
    <% Employee employee = (Employee) request.getSession().getAttribute("employee");%>
    <% RequestObject requestClass = (RequestObject) request.getSession().getAttribute("request");%>
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

                            <!--
                            Menu knapper bliver lavet under hinanden
                            -->

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
      ==================================================
        Den blå streg der strækker sig hen over siden
        -->
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

        <!--
        En streg der går hen over siden liger under overskriften
        -->

        <hr>

        <!--
        tabellen med caportinfo
        -->

        <div id="carportinfo">

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


                </thead>
            </table>
            <!-- </form>-->
        </div>

        <!--
        Tabel med kundeinfo
        -->

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

        <!--
        Tabel til dato og knapper
        -->

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

                    <tr>
                        <th>Dato tilbud</th>
                            <% if (employee != null) {%>
                        <td><form id='login-form' name="emplogin" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="sendoffer">
                                <button type='submit' class="btn btn-primary">Send tilbud</button>
                            </form></td>

                        <%} else if (requestClass != null) {
                                out.print("<td>" + requestClass.getOfferDate() + "</td>");
                            }%>
                    </tr>
                    <tr>
                        <th>Dato betaling</th>
                            <% if (customer != null && (requestClass != null && requestClass.getPaymentDate() == "Tilbud ej betalt!")) {%>
                        <td>            <form id='login-form' name="emplogin" action="FrontController" method="POST">
                                <input type="hidden" name="command" value="customerrequestdetailspayment">
                                <button type='submit' class="btn btn-primary">Accepter tilbud og betal</button>
                            </form></td>

                        <%} else if (requestClass != null) {
                                out.print("<td>" + requestClass.getPaymentDate() + "</td>");
                            }%>
                        <%}%>
                    </tr>

                </thead>
            </table>

        </div>

        <!--
        Carportens pris i en lille tabel
        -->

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

        <!--
        Carporten set fra oven bliver lavet inde i denne div
        -->

        <div id="yderst">

            <!--
            Her laver vi en firkant på 700x800px som bliver den yderst kant på hele svg tegningen
            -->

            <SVG width="700" height=800>
            <rect width="100%" height="100%" fill="#FFFFFF"/>

            <rect x="77" y="30" height="700" width="540" stroke-width="2"
                  style="stroke:#000000; fill: #EFF2FB"/>

            <!--
            Der bliver lavet endnu en firkant, som er inde i den første firkant vi lavede.
            Det skaber carportens fundation.
            -->

            <SVG x="97" y="50" width="500" height=600>
            <rect width="100%" height="100%" fill="#E6E6E6"/>

            <rect x="" y="0" width="500" height="600" stroke-width="3"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="15" y="15" height="570" width="470" stroke-width="2"
                  style="stroke:#000000; fill: #EFF2FB"/>


            <rect y="0" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="585" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <!--
                    Her indsættes de små mørke firkanter der ligner stolper
            -->

            <rect y="0" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="585" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <!--
            Vi carporten er mere end 6m, bliver der indsat 2 ekstra stolper
            -->

            <%
                if (carport.getLength() >= 6) {
                    out.println("<rect x=\"485\" y=\"50%\" height=\"15\" width=\"14\" stroke-width=\"2\"style=\"stroke:#000000; fill: #3B0B0B\"/>");
                    out.println("<rect y=\"50%\" height=\"15\" width=\"14\" stroke-width=\"2\"style=\"stroke:#000000; fill: #3B0B0B\"/>");
                }
            %>


            <!--
            Her tegnes skuret. Der kommer ekstra mørke firkanter (stolper).
            Hvor der også bliver lavet aflange firkanter der ligner remme.
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


            <!--
            Her tegner vi de korte remme i fronten af carporten, så det ligner den stikker 1 meter ud.
            -->

            <rect x="98" y="650" height="79" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="582" y="650" height="79" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <!--
            Her tegner vi der 2 meget korte remme bagerst på carporten.
            -->

            <rect x="98" y="30" height="20" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="582" y="30" height="20" width="14" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>


            <!--
            Der bliver tjekket hvor mange spær der skal være, hvorefter den tegner det antal spær carporten har
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
            En streg bliver lavet langs carporten bredde, hvorefter den indsætter carportens bredde i meter som text
            -->

            <text x="48%" y="780" ><%= (carport.getRoof().getWidth())%> m</text>


            <line x1="77" y1="785" x2="618" y2="785" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="77" y1="780" x2="77" y2="790" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="618" y1="780" x2="618" y2="790" style="stroke:rgb(0,0,0);stroke-width:2" />

            <!--
En streg bliver tegnet langs carporten, hvorefter den indsætter carporten længde i meter som text
            -->

            <text x="640" y="400" style="writing-mode: sideways-lr;"><%= (carport.getRoof().getLength())%> m</text>


            <line x1="645" y1="30" x2="645" y2="730" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="650" y1="30" x2="640" y2="30" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="650" y1="730" x2="640" y2="730" style="stroke:rgb(0,0,0);stroke-width:2" />

            <!--
Her bliver længden mellem spær målt. 0,55m er hardcoded, da det er altid er afstanden mellem spær.
            Stregen der bliver tegnet mellem spærene er dynamisk, og følger afstanden mellem spær.
            -->

            <text x="54" y="<%= pxmellemspar * 1.33%>" style="writing-mode: sideways-lr;">0,55 m</text>

            <line x1="60" y1="<%= pxmellemspar%>" x2="60" y2="<%= pxmellemspar * 2%>" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="55" y1="<%= pxmellemspar%>" x2="65" y2="<%= pxmellemspar%>" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="55" y1="<%= pxmellemspar * 2%>" x2="65" y2="<%= pxmellemspar * 2%>" style="stroke:rgb(0,0,0);stroke-width:2" />


            </SVG>
        </div>


        <!--
        carporten set fra fronten bliver tegnet i denne div
        -->

        <div id="svgfront">

            <!--
            En firkant bliver lavet hvor der bliver tegnet inden i
            -->

            <SVG width="700" height=600>
            <rect width="100%" height="100%" fill="#FFFFFF"/>



            <!--
            Der tegnes 2 stolper her
            -->
            <rect x="120" y="299" height="300" width="16" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="550" y="299" height="300" width="16" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <!--
            De to små aflange firkanter der ligner remme bliver tegnet
            -->

            <rect x="115" y="299" height="28" width="13" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <rect x="558" y="299" height="28" width="13" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <!--
            Carporten tag bliver tegnet her
            -->

            <rect x="55" y="272" height="25" width="570" stroke-width="2"
                  style="stroke:#000000; fill: #F3E2A9"/>

            <!--
            Højden på 2,25m er hardcoded, hvorefter vi også tegner stregen langs højden
            -->

            <text x="648" y="420" fill="" style="writing-mode: sideways-lr;">2,25 m</text>

            <line x1="655" y1="272" x2="655" y2="598" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="650" y1="272" x2="660" y2="272" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="650" y1="598" x2="660" y2="598" style="stroke:rgb(0,0,0);stroke-width:2" />


            <!--
            Her bliver bilen tegnet
            -->

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

            <!--
            En trekant bliver tegnet alt efter hvilken hældning det bliver valgt
            -->


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

        <% if (employee != null || (customer != null && requestClass != null && requestClass.getPaymentDate() != "Tilbud ej betalt!")) { %>

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


                    </tr>
                </tbody>
            </table> 
        </div>
        <% }%>

        <% if (customer != null && requestClass == null) { %> 
        <div id="forspgknap">
            <form id='login-form' name="emplogin" action="FrontController" method="POST">
                <input type="hidden" name="command" value="sendrequest">
                <button type='submit' class="btn btn-primary">Send forespørgsel</button>
            </form>
        </div>
        <%  }%> 


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
