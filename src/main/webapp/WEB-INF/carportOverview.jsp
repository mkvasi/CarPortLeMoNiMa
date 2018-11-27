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

    <% Carport carport = (Carport) request.getAttribute("carport");%>
    <%if (carport.getShed() == null) {
            carport.setLength(0.0);
            carport.setWidth(0.0);
        }%>
    <% HashMap<String, Material> billofmaterial = (HashMap) request.getAttribute("billOfMaterial");  %>
    <% //double offerPrice = (Double) request.getAttribute("offerprice");%>
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
                                <h3>Din carport er nedenfor</h3>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <div id="carportinfo">
            <form action="FrontController" method="POST" name="billOfMaterial" action="FrontController" method="POST">
                <input type="hidden" name="command" value="billOfMaterial">


                <table>
                    <thead>
                        <tr>
                            <th>Carport bredde</th>
                            <td><%= (carport.getWidth())%> </td>
                        </tr>


                        <tr>
                            <th>Carport længde</th>
                            <td><%out.print(carport.getLength());%></td>
                        </tr>
                        <tr>
                            <th>Tag bredde</th>
                            <td><%= (carport.getRoof().getWidth())%> </td>
                        </tr>
                        <tr>
                            <th>Tag længde</th>
                            <td><%= (carport.getRoof().getLength())%></td>
                        </tr>
                        <tr>
                            <th>Redskabsrum bredde</th>
                            <td><%=(carport.getShed().getWidth())%></td>
                        </tr>
                        <tr>
                            <th>Redskabsrum længde</th>
                            <td><%= (carport.getShed().getLength())%></td>
                        </tr>
                        <!--<tr>
                              <th>Pris</th>
                              <td><//%=offerPrice%></td>
                              
                          </tr>-->

                    </thead>
                </table>
            </form>
        </div>




        <div id="middle">
            <table>
                <thead>
                    <tr>
                        <th>Anvendes som</th>
                        <th>Produkt</th>
                        <th>Længde</th>
                        <th>Antal</th>

                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Spær</td>
                        <td><%out.print(billofmaterial.get("Spær").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getQty());%></td>
                    </tr>
                    <tr>
                        <td>Rem</td>
                        <td><%out.print(billofmaterial.get("Rem").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getQty());%></td>
                    </tr>
                    <tr>
                        <td>Universalbeslag højre</td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getQty());%></td>

                    </tr>
                    <tr>
                        <td>Universalbeslag venstre</td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getQty());%></td>

                    </tr>
                    <tr>
                        <td>Stolper</td>
                        <td><%out.print(billofmaterial.get("Stolper").getDescription());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get("Stolper").getQty());%></td>

                    </tr>
                    <tr>
                        <td>Understern sider</td>
                        <td><%out.print(billofmaterial.get("Understern til siderne").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Understern til siderne").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Understern til siderne").getQty());%></td>

                    </tr>
                    <tr>
                        <td>Understern for/bagende</td>
                        <td><%out.print(billofmaterial.get("Understern til for og bagende").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Understern til for og bagende").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Understern til for og bagende").getQty());%></td>
                    </tr>

                    <tr>
                        <td>Overstern sider</td>
                        <td><%out.print(billofmaterial.get("Overstern til siderne").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Overstern til siderne").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Overstern til siderne").getQty());%></td>
                    </tr>


                </tbody>







            </table>
        </div>


        <div id="yderst">

            <SVG width="700" height=800>
            <rect width="100%" height="100%" fill="#E6E6E6"/>

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

            <rect y="50%" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="0" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="585" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="50%" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="0" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>

            <rect y="585" x="485" height="15" width="15"
                  style="stroke:#000000; fill: #3B0B0B"/>


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


            <%

                
                int pxmellemspar = 700 / billofmaterial.get("Spær").getQty();
                int nextspar = 700 / billofmaterial.get("Spær").getQty();
                
                for (int i = 0; i < billofmaterial.get("Spær").getQty(); i++) {   
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

            <text x="54" y="97" style="writing-mode: sideways-lr;">0,55 m</text>

            <line x1="60" y1="95" x2="60" y2="130" style="stroke:rgb(0,0,0);stroke-width:2" />

            <line x1="55" y1="95" x2="65" y2="95" style="stroke:rgb(0,0,0);stroke-width:2" />
            <line x1="55" y1="130" x2="65" y2="130" style="stroke:rgb(0,0,0);stroke-width:2" />



            </SVG>
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
