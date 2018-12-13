<%-- 
    Document   : customerpage
    Created on : 06-12-2018, 08:52:53
    Author     : Morten
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% List<Integer> listOfCustomerRequest = (List<Integer>) request.getAttribute("listOfCustomerRequest"); %>
    <body>
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
