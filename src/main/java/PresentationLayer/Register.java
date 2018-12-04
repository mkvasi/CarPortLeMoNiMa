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
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String zipcode = request.getParameter("zipcode");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String role = request.getParameter("role");
        if (password1.equals(password2)) {
            Customer customer = null;
            try {
                customer = LogicFacade.createCustomer(firstName, lastName, email, Integer.parseInt(zipcode), city, Integer.parseInt(phone), password2, role);
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
