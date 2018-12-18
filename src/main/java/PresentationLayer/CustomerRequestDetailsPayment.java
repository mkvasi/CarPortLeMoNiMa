package PresentationLayer;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Price;
import FunctionLayer.entity.RequestObject;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerRequestDetailsPayment extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, MaterialException, SystemException, CalculatorException, ConverterMapException {
        
        RequestObject requestObject = (RequestObject) request.getSession().getAttribute("request");
        
        LogicFacade.customerUpdatePayment(requestObject.getId());
        
        requestObject = LogicFacade.getRequestDetails(requestObject.getId());
        
        double carportLength = requestObject.getCarport().getLength();
        double carportWidth = requestObject.getCarport().getWidth();
        int carportRoofSlopeCelsius = requestObject.getCarport().getRoof().getRoofSlopeCelsius();
        double shedLength = requestObject.getCarport().getShed().getLength();
        double shedWidth = requestObject.getCarport().getShed().getWidth();

        Carport carport = LogicFacade.makeCarport(carportLength, carportWidth, carportRoofSlopeCelsius, shedLength, shedWidth);
        
        int roofSlope = 0;
        String roofFlat = "0";
        
        if(requestObject.getCarport().getRoof().getRoofSlopeCelsius() == 0){
            roofFlat = LogicFacade.getRoofDescriptionById(requestObject.getCarport().getRoof().getRoofCladding());
        } else {
            roofSlope = requestObject.getCarport().getRoof().getRoofCladding();
        }

        BillOfMaterial billOfMaterial = LogicFacade.makeBillOfMaterial(carport, roofFlat, roofSlope);
        carport.setBillOfmaterial(billOfMaterial);
        

        Price price = LogicFacade.makePrice(billOfMaterial);

        request.getSession().setAttribute("offerprice", price);
        // request.setAttribute("price", price);
        request.getSession().setAttribute("request", requestObject);
        request.getSession().setAttribute("carport", carport);
        request.setAttribute("billOfMaterial", billOfMaterial);

        return "carportOverview";

    }

}
