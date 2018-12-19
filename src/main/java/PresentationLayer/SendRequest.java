package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Price;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Morten
 */
public class SendRequest extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, MaterialException, SystemException, CalculatorException, ConverterMapException {
        Carport carport = (Carport) request.getSession().getAttribute("carport");
        Price price = (Price) request.getSession().getAttribute("offerprice");
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        
        LogicFacade.createRequest(customer, price, carport);
        
        return "requestSuccess";
    }

}
