package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Material;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Morten
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

        List<String> CladdingFlatRoof = LogicFacade.getCladdingFlatRoof();
        List<Material> CladdingSlopeRoof = LogicFacade.getCladdingSlopeRoof();
        request.setAttribute("claddingflatroof", CladdingFlatRoof);
        request.setAttribute("claddingsloperoof", CladdingSlopeRoof);

        return "startpage";
    }
}
