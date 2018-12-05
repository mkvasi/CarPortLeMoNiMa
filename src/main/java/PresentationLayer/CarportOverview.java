package PresentationLayer;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.LogicFacade;
import FunctionLayer.Material;
import FunctionLayer.Price;
import FunctionLayer.Roof;
import FunctionLayer.Shed;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarportOverview extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CalculatorException, MaterialException, SystemException {
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        int roofSlopeCelcius = Integer.parseInt(request.getParameter("roof"));
        double shedLength = Double.parseDouble(request.getParameter("shedLength"));
        double shedWidth = Double.parseDouble(request.getParameter("shedWidth"));
        

        Carport carport = LogicFacade.makeCarport(length, width, roofSlopeCelcius, shedLength, shedWidth);
      

        BillOfMaterial billOfMaterial = LogicFacade.makeBillOfMaterial(carport);
        carport.setBillOfmaterial(billOfMaterial);
        
        Price price = LogicFacade.makePrice(billOfMaterial);

       // request.setAttribute("billOfMaterial", billOfMaterial);

        request.setAttribute("offerprice", price);
       // request.setAttribute("price", price);
        request.setAttribute("carport", carport);
        request.setAttribute("billOfMaterial", billOfMaterial);

        return "carportOverview";

    }
}
