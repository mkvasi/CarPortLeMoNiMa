package FunctionLayer;

//import FunctionLayer.calculators.OfferPriceCalculator;
import FunctionLayer.entity.Shed;
import FunctionLayer.entity.Roof;
import FunctionLayer.entity.Price;
import FunctionLayer.entity.Material;
import FunctionLayer.entity.Employee;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Carport;
import DBAccess.DataFacade;
import DBAccess.MaterialMapper;
import DBAccess.RequestMapper;
import DBAccess.UserMapper;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import java.util.TreeMap;
import FunctionLayer.BillOfMaterial;
import FunctionLayer.calculators.LineItemQtyGenerator;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
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

    public static BillOfMaterial makeBillOfMaterial(Carport carport, String roofFlatDescription, int roofSlopeId) throws MaterialException, SystemException, CalculatorException, ConverterMapException {

        LineItemQtyGenerator calc = new LineItemQtyGenerator();
        if (carport.getRoof().getRoofSlopeCelsius() == 0) {
            return calc.makeBillOfMaterial(carport, getAllDefaultMaterialsAsHashMapOfTreeMaps(carport), getAllMaterialForFlatRoofsAsList(roofFlatDescription), null);
        } else {
            return calc.makeBillOfMaterial(carport, getAllDefaultMaterialsAsHashMapOfTreeMaps(carport), null, getAllMaterialForSlopeRoofAsList(roofSlopeId));
        }
    }

    //--------------------
    public static TreeMap<Double, Material> getAllMaterialForFlatRoofsAsList(String roofFlatDescription) throws MaterialException, SystemException {
        //return MaterialMapper.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
        return DataFacade.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
    }

    public static Material getAllMaterialForSlopeRoofAsList(int roofSlopeId) throws MaterialException, SystemException {
        return DataFacade.getRoofSlopeCladdingMaterial(roofSlopeId);
    }
    //--------------------

    public static Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateBuyPrice(billOfMaterial);
        price.calculateSellPrice(billOfMaterial);
        return price;
    }

    public static List<Material> getAllDefaultMaterialsAsList(Carport carport) throws MaterialException, SystemException {
        return DataFacade.getDefaultList();

    }

    public static HashMap<Integer, TreeMap<Double, Material>> getAllDefaultMaterialsAsHashMapOfTreeMaps(Carport carport) throws ConverterMapException, SystemException, MaterialException {

        ConverterListAndMap con = new ConverterListAndMap();
        return con.ListToHashMap(getAllDefaultMaterialsAsList(carport));
    }

    public static Customer login(String email, String password) throws LoginUserException, SystemException {
        return DataFacade.login(email, password);
    }

    public static Employee employeelogin(String email, String password) throws LoginUserException, SystemException {
        return DataFacade.employeelogin(email, password);
    }

    public static void createCustomer(String firstName, String lastName, String email, int zipcode, String city, int phone, String password) throws LoginUserException, SystemException {
        Customer user = new Customer(firstName, lastName, email, zipcode, city, phone, password);
        DataFacade.createCustomer(user);
    }

    public static List<String> getCladdingFlatRoof() throws MaterialException, SystemException {
        List<String> eaveList = DataFacade.getRoofFlatCladdingMaterialListJSP(2);
        return eaveList;
    }

    public static List<Material> getCladdingSlopeRoof() throws MaterialException, SystemException {
        List<Material> tileList = DataFacade.getRoofSlopeCladdingMaterialList(3);
        return tileList;
    }
    
    public static void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException {
        DataFacade.createRequest(customer, price, carport);
    }
    
    public static List<Integer> getRequestCustomerList(Customer customer) throws LoginUserException, SystemException, MaterialException {
        return DataFacade.getRequestCustomerList(customer);
    }
    
    public static FunctionLayer.entity.Request getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException {
        return DataFacade.getRequestDetails(request_id);
    }

}
