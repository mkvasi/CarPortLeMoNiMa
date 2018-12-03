package FunctionLayer;

//import FunctionLayer.calculators.OfferPriceCalculator;
import DBAccess.DataMapper;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import java.util.TreeMap;
import FunctionLayer.BillOfMaterial;
import FunctionLayer.calculators.LineItemQtyGenerator;
import FunctionLayer.exceptions.LoginUserException;
import java.util.List;

public class LogicFacade {

//CRUD - DATABASE
//BUSINESS LOGIC    
    public static Carport makeCarport(double length, double width, int roofSlopCelsius, double shedLength, double shedWidth) {
        if (shedLength > 0.0 && shedWidth > 0.0) {
            Carport carport = new Carport(length, width, new Roof(roofSlopCelsius), new Shed(width, length), new BillOfMaterial());
            carport.getRoof().calculateRoofDimensions(carport);
            return carport;
        } else {
            Carport carport = new Carport(length, width, new Roof(roofSlopCelsius), null, new BillOfMaterial());
            carport.getRoof().calculateRoofDimensions(carport);
            return carport;
        }
    }

    public static BillOfMaterial makeBillOfMaterial(Carport carport) throws MaterialException {

        LineItemQtyGenerator calc = new LineItemQtyGenerator();

        return calc.makeBillOfMaterial(carport, getAllDefaultMaterialsAsHashMapOfTreeMaps(carport));

    }

    public static Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateBuyPrice(billOfMaterial);
        price.calculateSellPrice(billOfMaterial);
        return price;
    }

    public static List<Material> getAllDefaultMaterialsAsList(Carport carport) throws MaterialException {
        return DataMapper.getDefaultList();

    }

    public static HashMap<Integer, TreeMap<Double, Material>> getAllDefaultMaterialsAsHashMapOfTreeMaps(Carport carport) throws MaterialException {

        ConverterListAndMap con = new ConverterListAndMap();
        return con.ListToHashMap(getAllDefaultMaterialsAsList(carport));
    }

    public static User login(String email, String password) throws LoginUserException {
        return DataMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws LoginUserException, InstantiationException, IllegalAccessException {
        User user = new User(email, password, email);
        DataMapper.createUser(user);
        return user;
    }

}
