/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.Customer;
import FunctionLayer.LogicFacade;
import FunctionLayer.exceptions.LoginUserException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nr
 */
  public class Register extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException {
        String zipcode = request.getParameter("zipcode");
        String phone = request.getParameter("phone");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (password1.equals(password2)) {
            Customer customer = null;
            try {
                customer = LogicFacade.createCustomer(Integer.parseInt(zipcode), Integer.parseInt(phone), firstName, lastName, city, email, password1, role);
            } catch (InstantiationException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user", customer);
            session.setAttribute("role", customer.getRole());
            return "index";
        } else {
            throw new LoginUserException("the two passwords did not match");
        }
    }
}  
