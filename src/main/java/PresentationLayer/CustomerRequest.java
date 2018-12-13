package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Customer;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, MaterialException, SystemException, CalculatorException, ConverterMapException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        
        List<Integer> listOfCustomerRequest = LogicFacade.getRequestCustomerList(customer);
        
        request.setAttribute("listOfCustomerRequest", listOfCustomerRequest);
        
        return customer.getRole() + "page";
    }

}
