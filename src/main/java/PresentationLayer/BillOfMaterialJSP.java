package PresentationLayer;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.LogicFacade;
import FunctionLayer.LoginUserException;
import FunctionLayer.Roof;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of BillOfMaterialJSP: !!!TYPE PURPOSE OF BillOfMaterialJSP HERE!!!
 * @author Morten
 * @version 1.0
 * @since 16-11-2018
 */

public class BillOfMaterialJSP extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) {
        //BillOfMaterial bom = new BillOfMaterial();
        //Carport carport = new Carport(5.0, 3.0, new Roof(true));
        
        System.out.println("TEST");
        //System.out.println(carport.getHeigth());
        
        //bom.setBomCarport(LogicFacade.updateBomCarport(carport));
        
        //request.setAttribute("bomCarport", bom.getBomCarport());
        
        return "index";
    }

}
