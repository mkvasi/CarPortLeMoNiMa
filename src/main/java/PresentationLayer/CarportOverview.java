package PresentationLayer;

import FunctionLayer.Carport;
import FunctionLayer.LogicFacade;
import FunctionLayer.Material;
import FunctionLayer.Roof;
import FunctionLayer.Shed;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarportOverview extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws MaterialException {
        double shedLength = 0.0;
        double shedWidth = 0.0;
        boolean roofFlat;
        boolean shedWanted;
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        String _roof = request.getParameter("roof");
        String _shed = request.getParameter("shed");
        if (_roof.equals("Ja")) {
            roofFlat = true;
        } else {
            roofFlat = false;
        }
        if (_shed.equals("Ja")) {
            shedWanted = true;
            shedLength = Double.parseDouble(request.getParameter("shedLength"));
            shedWidth = Double.parseDouble(request.getParameter("shedWidth"));
        } else {
            shedWanted = false;
            shedLength = 0.0;
            shedWidth = 0.0;
        }

        Roof roof = new Roof(roofFlat, length, width);
        Carport carport;

        carport = new Carport(length - 1, width - 0.3, roof, new Shed(shedWanted, shedLength, shedWidth));

//        LogicFacade.getAllMaterialsFromDB(carport);
        HashMap<String, Material> billOfMaterials = LogicFacade.getDoneCarportWithMaterialList(carport);

        request.setAttribute("billOfMaterial", billOfMaterials);
        //request.setAttribute("offerprice", offerPrice);
        request.setAttribute("carport", carport);

        return "carportOverview";
    }

}
