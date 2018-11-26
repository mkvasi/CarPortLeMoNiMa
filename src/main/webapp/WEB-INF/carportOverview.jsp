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
        <% HashMap<String , Material> billofmaterial = (HashMap) request.getAttribute("billOfMaterial");  %>
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
                  <!--<tr>
                        <th>Pris</th>
                        <td><//%=offerPrice%></td>
                        
                    </tr>-->

                </thead>
            </table>
                  
                        
                           

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
                        <td><%out.print(billofmaterial.get("Spær").getName());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getQty());%></td>
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Rem").getName());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getQty());%></td>
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getName());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getQty());%></td>
   
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getName());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getQty());%></td>
   
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Stolper").getName());%></td>
                        <td><%out.print(billofmaterial.get("Stolper").getDescription());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get("Stolper").getQty());%></td>
   
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Understern til siderne").getName());%></td>
                        <td><%out.print(billofmaterial.get("Understern til siderne").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Understern til siderne").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Understern til siderne").getQty());%></td>
   
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Understern til for og bagende").getName());%></td>
                        <td><%out.print(billofmaterial.get("Understern til for og bagende").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Understern til for og bagende").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Understern til for og bagende").getQty());%></td>
   
                    </tr>
                         <tr>
                        <td><%out.print(billofmaterial.get("Overstern til siderne").getName());%></td>
                        <td><%out.print(billofmaterial.get("Overstern til siderne").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Overstern til siderne").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Overstern til siderne").getQty());%></td>
   
                    </tr>
                </tbody>
        </div>
                        
           



            
            </table>

        </div>
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
