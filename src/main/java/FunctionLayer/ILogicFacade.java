package FunctionLayer;

import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Employee;
import FunctionLayer.entity.Material;
import FunctionLayer.entity.Price;
import FunctionLayer.entity.RequestObject;
import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.ConverterMapException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Morten
 */
public interface ILogicFacade {
    
//    public static Carport makeCarport(double length, double width, int roofSlopCelsius, double shedLength, double shedWidth);
//    public static BillOfMaterial makeBillOfMaterial(Carport carport, String roofFlatDescription, int roofSlopeId) throws MaterialException, SystemException, CalculatorException, ConverterMapException;
//    public static TreeMap<Double, Material> getAllMaterialForFlatRoofsAsList(String roofFlatDescription) throws MaterialException, SystemException;
//    public static Material getAllMaterialForSlopeRoofAsList(int roofSlopeId) throws MaterialException, SystemException;
//    public static Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException;
//    public static List<Material> getAllDefaultMaterialsAsList(Carport carport) throws MaterialException, SystemException;
//    public static HashMap<Integer, TreeMap<Double, Material>> getAllDefaultMaterialsAsHashMapOfTreeMaps(Carport carport) throws ConverterMapException, SystemException, MaterialException;
//    public static Customer login(String email, String password) throws LoginUserException, SystemException;
//    public static Employee employeelogin(String email, String password) throws LoginUserException, SystemException;
//    public static void createCustomer(String firstName, String lastName, String email, int zipcode, String city, int phone, String password) throws LoginUserException, SystemException;
//    public static List<String> getCladdingFlatRoof() throws MaterialException, SystemException;
//    public static List<Material> getCladdingSlopeRoof() throws MaterialException, SystemException;
//    public static void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException;
//    public static List<Integer> getRequestCustomerList(Customer customer) throws LoginUserException, SystemException, MaterialException;
//    public static RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException;
//    public static String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException;
//    public static void customerUpdatePayment(int id) throws LoginUserException, SystemException;
    
}
