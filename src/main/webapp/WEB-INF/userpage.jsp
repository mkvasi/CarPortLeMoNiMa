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
                                        <a href="http://localhost:8084/websitetest">Beregn carport</a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#">Min profil</a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#">Pages</a>
                                    </li>
                                    <li class="dropdown active">
                                        <a href="http://localhost:8084/websitetest/login.jsp">Login</a>
                                    </li>
                                    <li>
                                        <a href="contact.html">Kontakt</a>
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

        <h3>Telefon</h3>
        <p>Vores&nbsp;telefoner er åbne&nbsp;i tidsrummet<br />7:00 - 17:00 mandag til torsdag<br />7:00 - 16:00 fredag</p>
        <p>I weekender og på helligdage er telefonerne&nbsp;åbne&nbsp;i butikkernes åbningstider.<br />Se åbningstider under de respektive afdelinger.</p>
        <table border="0">
            <tbody>
                <tr>
                    <td><strong>Telefonnumre</strong></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td><strong>Omstilling</strong></td>
                    <td><strong>45 87 10 01&nbsp;</strong></td>
                </tr>
                <tr>
                    <td>Farum</td>
                    <td>44 95 01 64</td>
                </tr>
                <tr>
                    <td>Fredensborg</td>
                    <td>48 48 00 13</td>
                </tr>
                <tr>
                    <td>Helsinge</td>
                    <td>48 79 43 34</td>
                </tr>
                <tr>
                    <td>Herlev</td>
                    <td>44 94 40 66</td>
                </tr>
                <tr>
                    <td>Hørsholm</td>
                    <td>45 86 10 10</td>
                </tr>
                <tr>
                    <td>Kvistgård</td>
                    <td>49 12 50 50</td>
                </tr>
                <tr>
                    <td>Lyngby Trælast</td>
                    <td>45 87 10 01</td>
                </tr>
                <tr>
                    <td>Lyngby Bolig &amp; Designhus</td>
                    <td>45 26 63 00</td>
                </tr>
                <tr>
                    <td>Vordingborg</td>
                    <td>55 35 86 00</td>
                </tr>
                <tr>
                    <td>Værebro</td>
                    <td>47 17 99 44</td>
                </tr>
            </tbody>
        </table>
        <h3>Henvendelser vedr. ordrer</h3>
        <p>Ændringer eller spørgsmål&nbsp;til en afgivet ordre, skal rettes telefonisk direkte til den afdeling, der er anført på din ordrebekræftelse fra Fog<br />Vi kan desværre ikke garantere, at der reageres rettidigt på henvendelser på e-mail vedrørende ordrer.</p>
        <h3>Henvendelser på e-mail</h3>
        <p>Du kan maile til info@johannesfog.dk.<br />Vi foretrækker dog, at du benytter vores "Spørg Fog" funktion, hvis du har konkrete spørgsmål omkring produkter eller andre henvendelser. Så videresendes din henvendelse til en relevant person til besvarelse og det overvåges, at der bliver besvaret.<br />Henvendelser besvares på hverdage og kan forventes besvaret inden for 3 hverdage.</p>
        <p><a title="Spørg Fog" href="/byggecenter/sporg-fog/" target="_top">Klik her for at oprette en "Spørg Fog" henvendelse</a></p>




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