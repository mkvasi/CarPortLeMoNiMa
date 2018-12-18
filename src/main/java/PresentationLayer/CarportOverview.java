package PresentationLayer;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.entity.Carport;
import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Material;
import FunctionLayer.entity.Price;
import FunctionLayer.entity.Roof;
import FunctionLayer.entity.Shed;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarportOverview extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CalculatorException, MaterialException, SystemException, ConverterMapException {
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        int roofSlopeCelcius = Integer.parseInt(request.getParameter("roof"));
        int shedHalfOrFull = Integer.parseInt(request.getParameter("shedWidth"));

        double shedWidth = 0.0;
        if (shedHalfOrFull == 0) {
            shedWidth = width / 2;
        } else if (shedHalfOrFull == 1) {
            shedWidth = width;
        }
        double shedLength = Double.parseDouble(request.getParameter("shedLength"));
        String roofFlat = request.getParameter("roofflat");
        int roofSlope = Integer.parseInt(request.getParameter("roofslope"));

        Carport carport = LogicFacade.makeCarport(length, width, roofSlopeCelcius, shedLength, shedWidth);

        BillOfMaterial billOfMaterial = LogicFacade.makeBillOfMaterial(carport, roofFlat, roofSlope);
        carport.setBillOfmaterial(billOfMaterial);
        
        int materialRoofCladdingId = billOfMaterial.getLineItems().get(0).getMaterial().getId();
        carport.getRoof().setRoofCladding(materialRoofCladdingId);

        Price price = LogicFacade.makePrice(billOfMaterial);

        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String orderDate = simpleDateFormat.format(new Date());

        request.setAttribute("date", orderDate);
        request.getSession().setAttribute("offerprice", price);
        request.getSession().setAttribute("carport", carport);
        request.setAttribute("billOfMaterial", billOfMaterial);

        return "carportOverview";

    }
}
