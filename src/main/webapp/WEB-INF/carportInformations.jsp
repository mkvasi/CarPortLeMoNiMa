<%-- 
    Document   : index
    Created on : 14-11-2018, 13:44:38
    Author     : Yeha
--%>

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
        <% List<Material> billofmaterial = (List) request.getAttribute("listDone");%>
        <% Carport carport = (Carport) request.getAttribute("carport");%>
        <% double offerPrice = (Double) request.getAttribute("offerprice");%>
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
                <form action="FrontController" method="POST" name="sendrequest" action="FrontController" method="POST">
                <input type="hidden" name="command" value="sendrequest">
                
                
            <table>
                <thead>
                    <tr>
                        <th>Carport bredde</th>
                        <td><%out.print(carport.getWidth());%></td>
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
                        <td>Ønsker ikke redskabsrum</td>
                    </tr>
                    <tr>
                        <th>Redskabsrum længde</th>
                        <td>Ønsker ikke redskabsrum</td>
                    </tr>

                </thead>
            </table>
                    <div id="button">
                    <button type="submit" class="btn btn-primary">Send forespørgelse</button>
                    </div>
        </div>
        <div id="pris">
            <table>
                <thead>
                    <tr>
                        <th>Pris</th>
                        <td><%=offerPrice%></td>
                    </tr>


                </thead>
            </table>

        </div>
                     



        <div id="middle">
            <table>
                <thead>
                    <tr>
                        <th>Træ & tagplader</th>
                        <th>Længde</th>
                        <th>Antal</th>
                        <th>Enhed</th>
                        <th>Beskrivelse</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%out.print(billofmaterial.get(0).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(0).getQty());%></td>
                        <td><%out.print(billofmaterial.get(0).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(0).getDescription());%></td>
                        
                    </tr>
                       <tr>
                        <td><%out.print(billofmaterial.get(1).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(1).getQty());%></td>
                        <td><%out.print(billofmaterial.get(1).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(1).getDescription());%></td>
                        
                    </tr>
                   <tr>
                        <td><%out.print(billofmaterial.get(2).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(2).getQty());%></td>
                        <td><%out.print(billofmaterial.get(2).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(2).getDescription());%></td>
                        
                    </tr>
                  <tr>
                        <td><%out.print(billofmaterial.get(3).getName());%></td>
                        <td><%out.print(billofmaterial.get(3).getLength());%></td>
                        <td><%out.print(billofmaterial.get(3).getQty());%></td>
                        <td><%out.print(billofmaterial.get(3).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(3).getDescription());%></td>
                        
                    </tr>
                   <tr>
                        <td><%out.print(billofmaterial.get(4).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(4).getQty());%></td>
                        <td><%out.print(billofmaterial.get(4).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(4).getDescription());%></td>
                        
                    </tr>
                   <tr>
                        <td><%out.print(billofmaterial.get(5).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(5).getQty());%></td>
                        <td><%out.print(billofmaterial.get(5).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(5).getDescription());%></td>
                        
                    </tr>
                                      <tr>
                        <td><%out.print(billofmaterial.get(6).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(6).getQty());%></td>
                        <td><%out.print(billofmaterial.get(6).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(6).getDescription());%></td>
                        
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get(7).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(7).getQty());%></td>
                        <td><%out.print(billofmaterial.get(7).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(7).getDescription());%></td>
                        
                    </tr>
               <tr>
                        <td><%out.print(billofmaterial.get(8).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(8).getQty());%></td>
                        <td><%out.print(billofmaterial.get(8).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(8).getDescription());%></td>
                        
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get(9).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(9).getQty());%></td>
                        <td><%out.print(billofmaterial.get(9).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(9).getDescription());%></td>
                        
                    </tr>  <tr>
                        <td><%out.print(billofmaterial.get(10).getName());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get(10).getQty());%></td>
                        <td><%out.print(billofmaterial.get(10).getMeasure());%></td>
                        <td><%out.print(billofmaterial.get(10).getDescription());%></td>
                        
                    </tr>
                </tbody>
            </table>
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
