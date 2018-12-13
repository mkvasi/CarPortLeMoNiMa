/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Customer;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nr
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, SystemException, MaterialException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = LogicFacade.login(email, password);

        HttpSession session = request.getSession();
        session.setAttribute("customer", customer);
        session.setAttribute("role", customer.getRole());
        
        List<Integer> listOfCustomerRequest = LogicFacade.getRequestCustomerList(customer);
        
        request.setAttribute("listOfCustomerRequest", listOfCustomerRequest);
        
        return customer.getRole() + "page";
    }
}
