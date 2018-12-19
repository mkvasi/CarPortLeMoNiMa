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
 * Meningen med denne klasse er at skabe adgang mellem presentationlayer og functionlayer
 */
public class LogicFacade {
    
    /**
     * Meningen med denne metode er at danne et carport object ud fra de angivne parametre
     * @param length
     * @param width
     * @param roofSlopCelsius
     * @param shedLength
     * @param shedWidth
     * @return Carport
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
     * Meningen med denne metode er at danne en BillOfMaterial (stykliste) ud fra informationerne i de angivne parametre
     * @param carport
     * @param roofFlatDescription
     * @param roofSlopeId
     * @return BillOfMaterial
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
     * Meningen med denne metode er at hente alle metrialerne i databasen og returnere som et TreeMap baseret på det angivne parameter
     * @param roofFlatDescription
     * @return TreeMap
     * @throws MaterialException
     * @throws SystemException 
     */
    public static TreeMap<Double, Material> getAllMaterialForFlatRoofsAsList(String roofFlatDescription) throws MaterialException, SystemException {
        return DataFacade.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
    }

    /**
     * Meningen med denne metode er at hente det specifike materiale baseret på det angivne parameter
     * @param roofSlopeId
     * @return Material
     * @throws MaterialException
     * @throws SystemException 
     */
    public static Material getAllMaterialForSlopeRoofAsList(int roofSlopeId) throws MaterialException, SystemException {
        return DataFacade.getRoofSlopeCladdingMaterial(roofSlopeId);
    }

    /**
     * Meningen med denne metode er at beregne priserne på indholdet af BillOfMaterial (styklisten)
     * @param billOfMaterial
     * @return Price
     * @throws MaterialException 
     */
    public static Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException {
        Price price = new Price();
        price.calculateBuyPrice(billOfMaterial);
        price.calculateSellPrice(billOfMaterial);
        return price;
    }

    /**
     * Meningen med denne metode er at hente alle standard materialer i en liste
     * @param carport
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getAllDefaultMaterialsAsList(Carport carport) throws MaterialException, SystemException {
        return DataFacade.getDefaultList();
    }

    /**
     * Meningen med denne metode er at danne en HashMap ud fra en liste af standard materialer
     * @param carport
     * @return HashMap
     * @throws ConverterMapException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static HashMap<Integer, TreeMap<Double, Material>> getAllDefaultMaterialsAsHashMapOfTreeMaps(Carport carport) throws ConverterMapException, SystemException, MaterialException {
        ConverterListAndMap con = new ConverterListAndMap();
        return con.ListToHashMap(getAllDefaultMaterialsAsList(carport));
    }

    /**
     * Meningen med denn metode er at tjekke input fra en bruger, som prøver at logge ind
     * @param email
     * @param password
     * @return Customer
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static Customer login(String email, String password) throws LoginUserException, SystemException {
        return DataFacade.login(email, password);
    }

    /**
     * Meningen med denn metode er at tjekke input fra en medarbejder, som prøver at logge ind
     * @param email
     * @param password
     * @return Employee
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static Employee employeelogin(String email, String password) throws LoginUserException, SystemException {
        return DataFacade.employeelogin(email, password);
    }

    /**
     * Meningen med denn metode er at oprette en bruger i systemet ud fra de angivne informationer i det object som er med som parameter
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
     * Meningen med denne metode er at hente alle materialerne til fladt tag baseret på det angivne parameter
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<String> getCladdingFlatRoof() throws MaterialException, SystemException {
        List<String> eaveList = DataFacade.getRoofFlatCladdingMaterialListJSP(2);
        return eaveList;
    }

    /**
     * 
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getCladdingSlopeRoof() throws MaterialException, SystemException {
        List<Material> tileList = DataFacade.getRoofSlopeCladdingMaterialList(3);
        return tileList;
    }

    /**
     * Meningen med denne metode er at alle materialerne til tag med hældning baseret på det angivne parameter
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
     * Meningen med denne metode er at hente alle forespørgsler fra databasen baseret på informationer fra det angivne parameter
     * @param customer
     * @return List
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
     * @return RequestObject
     * @throws LoginUserException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException {
        return DataFacade.getRequestDetails(request_id);
    }

    /**
     * Meningen med denne metode er at hente alle detaljer på en forespørgsel
     * @param id
     * @return String
     * @throws LoginUserException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException {
        return DataFacade.getRoofDescriptionById(id);
    }

    /**
     * Meningen med denne metode er at sætte en forespørgsel som betalt i datbasen baseret på det angivne parameter
     * @param id
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void customerUpdatePayment(int id) throws LoginUserException, SystemException {
        DataFacade.customerUpdatePayment(id);
    }

}
