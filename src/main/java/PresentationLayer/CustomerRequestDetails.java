package PresentationLayer;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Price;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerRequestDetails extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, MaterialException, SystemException, CalculatorException, ConverterMapException {
        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        int roofSlopeCelcius = Integer.parseInt(request.getParameter("roof"));
        double shedWidth = Double.parseDouble(request.getParameter("shedWidth"));
        double shedLength = Double.parseDouble(request.getParameter("shedLength"));
        String roofFlat = request.getParameter("roofflat");
        int roofSlope = Integer.parseInt(request.getParameter("roofslope"));

        Carport carport = LogicFacade.makeCarport(length, width, roofSlopeCelcius, shedLength, shedWidth);

        BillOfMaterial billOfMaterial = LogicFacade.makeBillOfMaterial(carport, roofFlat, roofSlope);
        carport.setBillOfmaterial(billOfMaterial);
        
        int materialRoofCladdingId = billOfMaterial.getLineItems().get(0).getMaterial().getId();
        carport.getRoof().setRoofCladding(materialRoofCladdingId);

        Price price = LogicFacade.makePrice(billOfMaterial);

        //String dateFormat = "dd-MM-yyyy";
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        //String orderDate = simpleDateFormat.format(new Date());

        // request.setAttribute("billOfMaterial", billOfMaterial);
        //request.setAttribute("date", orderDate);
        request.getSession().setAttribute("offerprice", price);
        // request.setAttribute("price", price);
        request.getSession().setAttribute("carport", carport);
        request.setAttribute("billOfMaterial", billOfMaterial);

        return "carportOverview";

    }

}
