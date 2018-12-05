package FunctionLayer;

//import FunctionLayer.calculators.OfferPriceCalculator;
import DBAccess.MaterialMapper;
import DBAccess.UserMapper;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import java.util.TreeMap;
import FunctionLayer.BillOfMaterial;
import FunctionLayer.calculators.LineItemQtyGenerator;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;

public class LogicFacade {

//CRUD - DATABASE
//BUSINESS LOGIC    
    public static Carport makeCarport(double length, double width, int roofSlopCelsius, double shedLength, double shedWidth) {
        if (shedLength > 0.0 && shedWidth > 0.0) {
            Carport carport = new Carport(length, width, new Roof(roofSlopCelsius), new Shed(shedWidth, shedLength), new BillOfMaterial());
            carport.getRoof().calculateRoofDimensions(carport);
            return carport;
        } else {
            Carport carport = new Carport(length, width, new Roof(roofSlopCelsius), null, new BillOfMaterial());
            carport.getRoof().calculateRoofDimensions(carport);
            return carport;
        }
    }

    public static BillOfMaterial makeBillOfMaterial(Carport carport) throws MaterialException, SystemException, CalculatorException {

        LineItemQtyGenerator calc = new LineItemQtyGenerator();

        return calc.makeBillOfMaterial(carport, getAllDefaultMaterialsAsHashMapOfTreeMaps(carport));

    }

    public static Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateBuyPrice(billOfMaterial);
        price.calculateSellPrice(billOfMaterial);
        return price;
    }

    public static List<Material> getAllDefaultMaterialsAsList(Carport carport) throws MaterialException, SystemException {
        return MaterialMapper.getDefaultList();

    }

    public static HashMap<Integer, TreeMap<Double, Material>> getAllDefaultMaterialsAsHashMapOfTreeMaps(Carport carport) throws MaterialException, SystemException {

        ConverterListAndMap con = new ConverterListAndMap();
        return con.ListToHashMap(getAllDefaultMaterialsAsList(carport));
    }

    public static Customer login(String email, String password) throws LoginUserException, SystemException {
        return UserMapper.login(email, password);
    }

    public static Customer createCustomer(String firstName, String lastName, String email, int zipcode, String city, int phone, String password) throws LoginUserException, SystemException {
        Customer user = new Customer(firstName, lastName, email, zipcode, city, phone, password);
        UserMapper.createCustomer(user);
        return user;
    }

}
