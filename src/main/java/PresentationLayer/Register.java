/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.entity.Customer;
import FunctionLayer.LogicFacade;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.SystemException;
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
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, SystemException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String zipcode = request.getParameter("zipcode");
        String city = request.getParameter("city");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (password1.equals(password2)) {
            LogicFacade.createCustomer(firstName, lastName, email, Integer.parseInt(zipcode), city, Integer.parseInt(phone), password2);
            return "index";
        } else {
            throw new LoginUserException("the two passwords did not match");
        }
    }
}  
