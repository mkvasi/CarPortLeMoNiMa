/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Customer;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.SystemException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nr
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, SystemException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Customer customer = LogicFacade.login(email, password);
        
        HttpSession session = request.getSession();
        session.setAttribute("user", customer);
        session.setAttribute("role", customer.getRole());
        if (customer.getRole().equals("employee")) {
            return (customer.getRole() + "page");
        } else {
            return "userpage";
        }
    }
}
