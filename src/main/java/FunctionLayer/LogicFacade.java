package FunctionLayer;

import FunctionLayer.entity.Shed;
import FunctionLayer.entity.Roof;
import FunctionLayer.entity.Price;
import FunctionLayer.entity.Material;
import FunctionLayer.entity.Employee;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Carport;
import DBAccess.DataFacade;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import java.util.TreeMap;
import FunctionLayer.calculators.LineItemQtyGenerator;
import FunctionLayer.entity.RequestObject;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;

/**
 * 
 * @author Morten
 */

public class LogicFacade{
   
    /**
     * 
     * @param length
     * @param width
     * @param roofSlopCelsius
     * @param shedLength
     * @param shedWidth
     * @return 
     */
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

    /**
     * 
     * @param carport
     * @param roofFlatDescription
     * @param roofSlopeId
     * @return
     * @throws MaterialException
     * @throws SystemException
     * @throws CalculatorException
     * @throws ConverterMapException 
     */
    public static BillOfMaterial makeBillOfMaterial(Carport carport, String roofFlatDescription, int roofSlopeId) throws MaterialException, SystemException, CalculatorException, ConverterMapException {

        LineItemQtyGenerator calc = new LineItemQtyGenerator();
        if (carport.getRoof().getRoofSlopeCelsius() == 0) {
            return calc.makeBillOfMaterial(carport, getAllDefaultMaterialsAsHashMapOfTreeMaps(carport), getAllMaterialForFlatRoofsAsList(roofFlatDescription), null);
        } else {
            return calc.makeBillOfMaterial(carport, getAllDefaultMaterialsAsHashMapOfTreeMaps(carport), null, getAllMaterialForSlopeRoofAsList(roofSlopeId));
        }
    }

    /**
     * 
     * @param roofFlatDescription
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static TreeMap<Double, Material> getAllMaterialForFlatRoofsAsList(String roofFlatDescription) throws MaterialException, SystemException {
        //return MaterialMapper.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
        return DataFacade.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
    }

    /**
     * 
     * @param roofSlopeId
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static Material getAllMaterialForSlopeRoofAsList(int roofSlopeId) throws MaterialException, SystemException {
        return DataFacade.getRoofSlopeCladdingMaterial(roofSlopeId);
    }

    /**
     * 
     * @param billOfMaterial
     * @return
     * @throws MaterialException 
     */
    public static Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateBuyPrice(billOfMaterial);
        price.calculateSellPrice(billOfMaterial);
        return price;
    }

    /**
     * 
     * @param carport
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getAllDefaultMaterialsAsList(Carport carport) throws MaterialException, SystemException {
        return DataFacade.getDefaultList();

    }

    /**
     * 
     * @param carport
     * @return
     * @throws ConverterMapException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static HashMap<Integer, TreeMap<Double, Material>> getAllDefaultMaterialsAsHashMapOfTreeMaps(Carport carport) throws ConverterMapException, SystemException, MaterialException {

        ConverterListAndMap con = new ConverterListAndMap();
        return con.ListToHashMap(getAllDefaultMaterialsAsList(carport));
    }

    /**
     * 
     * @param email
     * @param password
     * @return
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static Customer login(String email, String password) throws LoginUserException, SystemException {
        return DataFacade.login(email, password);
    }

    /**
     * 
     * @param email
     * @param password
     * @return
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static Employee employeelogin(String email, String password) throws LoginUserException, SystemException {
        return DataFacade.employeelogin(email, password);
    }

    /**
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @param zipcode
     * @param city
     * @param phone
     * @param password
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void createCustomer(String firstName, String lastName, String email, int zipcode, String city, int phone, String password) throws LoginUserException, SystemException {
        Customer user = new Customer(firstName, lastName, email, zipcode, city, phone, password);
        DataFacade.createCustomer(user);
    }

    /**
     * 
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<String> getCladdingFlatRoof() throws MaterialException, SystemException {
        List<String> eaveList = DataFacade.getRoofFlatCladdingMaterialListJSP(2);
        return eaveList;
    }

    /**
     * 
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getCladdingSlopeRoof() throws MaterialException, SystemException {
        List<Material> tileList = DataFacade.getRoofSlopeCladdingMaterialList(3);
        return tileList;
    }
    
    /**
     * 
     * @param customer
     * @param price
     * @param carport
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException {
        DataFacade.createRequest(customer, price, carport);
    }
    
    /**
     * 
     * @param customer
     * @return
     * @throws LoginUserException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static List<Integer> getRequestCustomerList(Customer customer) throws LoginUserException, SystemException, MaterialException {
        return DataFacade.getRequestCustomerList(customer);
    }
    
    /**
     * 
     * @param request_id
     * @return
     * @throws LoginUserException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException {
        return DataFacade.getRequestDetails(request_id);
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws LoginUserException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException {
        return DataFacade.getRoofDescriptionById(id);
    }
    
    /**
     * 
     * @param id
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void customerUpdatePayment(int id) throws LoginUserException, SystemException {
        DataFacade.customerUpdatePayment(id);
    }
}
