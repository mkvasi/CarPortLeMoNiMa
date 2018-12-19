
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.SystemException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Morten
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
            return "loginpage";
        } else {
            throw new LoginUserException("the two passwords did not match");
        }
    }
}  
