package PresentationLayer;

//import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.LogicFacade;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.Roof;
import FunctionLayer.exceptions.MaterialException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of BillOfMaterial: !!!TYPE PURPOSE OF BillOfMaterial HERE!!!
 * @author Morten
 * @version 1.0
 * @since 16-11-2018
 */

public class BillOfMaterialJSP extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {

        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        boolean roofFlat = Boolean.parseBoolean(request.getParameter("roof"));
        
      
        Roof roof = new Roof(roofFlat); 
        
    
        Carport carport = new Carport(length, width, roof); 
        List listDone = null; 
        try {
            List list = LogicFacade.getMaterialList();
            listDone = LogicFacade.getMaterialListWithQty(list, carport); 
        } catch (MaterialException ex) {
            throw new IllegalArgumentException(); 
        }
      
     
        
        
        
        request.setAttribute("carport", carport);
        request.setAttribute("listDone", listDone);
        
        return "carportInformations";
    }

}
