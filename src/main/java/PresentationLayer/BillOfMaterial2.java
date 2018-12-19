
package PresentationLayer;

import FunctionLayer.entity.Carport;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Morten
 */
public class BillOfMaterial2 extends Command {

    public BillOfMaterial2() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, MaterialException {
       Carport carport = (Carport) request.getAttribute("carport"); 
      
      request.setAttribute("carport", carport);
      return "billOfMaterial";
    }
    
}
