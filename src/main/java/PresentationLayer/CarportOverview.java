package PresentationLayer;

import FunctionLayer.Carport;
import FunctionLayer.LogicFacade;
import FunctionLayer.Material;
import FunctionLayer.Roof;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The purpose of BillOfMaterial: !!!TYPE PURPOSE OF BillOfMaterial HERE!!!
 *
 * @author Morten
 * @version 1.0
 * @since 16-11-2018
 */
public class CarportOverview extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws MaterialException {
        boolean roofFlat;
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        String _roof = request.getParameter("roof");
        if (_roof.equals("Ja")) {
            roofFlat = true;
        } else {
            roofFlat = false;
        }

        Roof roof = new Roof(roofFlat);
        Carport carport = new Carport(length, width, roof);
        LogicFacade.getAllMaterialsFromDB(carport);
        HashMap<String, Material> billOfMaterials = LogicFacade.getDoneCarportWithMaterialList(carport);

        request.setAttribute("billOfMaterial", billOfMaterials);
        //request.setAttribute("offerprice", offerPrice);
        request.setAttribute("carport", carport);

        return "carportOverview";
    }

}
