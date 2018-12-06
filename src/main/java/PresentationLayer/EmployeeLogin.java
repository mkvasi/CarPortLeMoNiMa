/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.Employee;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.SystemException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nr
 */
public class EmployeeLogin extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, SystemException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Employee employee = LogicFacade.employeelogin(email, password);
        
        HttpSession session = request.getSession();
        session.setAttribute("user", employee);
        session.setAttribute("role", employee.getRole());
        if (employee.isAdmin()) {
            return ("userpage");
        } else {
            return "userpage";
        }
    }
}
