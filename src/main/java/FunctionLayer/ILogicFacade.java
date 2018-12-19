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
    
    Carport makeCarport(double length, double width, int roofSlopCelsius, double shedLength, double shedWidth);
    BillOfMaterial makeBillOfMaterial(Carport carport, String roofFlatDescription, int roofSlopeId) throws MaterialException, SystemException, CalculatorException, ConverterMapException;
    TreeMap<Double, Material> getAllMaterialForFlatRoofsAsList(String roofFlatDescription) throws MaterialException, SystemException;
    Material getAllMaterialForSlopeRoofAsList(int roofSlopeId) throws MaterialException, SystemException;
    Price makePrice(BillOfMaterial billOfMaterial) throws MaterialException;
    List<Material> getAllDefaultMaterialsAsList(Carport carport) throws MaterialException, SystemException;
    HashMap<Integer, TreeMap<Double, Material>> getAllDefaultMaterialsAsHashMapOfTreeMaps(Carport carport) throws ConverterMapException, SystemException, MaterialException;
    Customer login(String email, String password) throws LoginUserException, SystemException;
    Employee employeelogin(String email, String password) throws LoginUserException, SystemException;
    void createCustomer(String firstName, String lastName, String email, int zipcode, String city, int phone, String password) throws LoginUserException, SystemException;
    List<String> getCladdingFlatRoof() throws MaterialException, SystemException;
    List<Material> getCladdingSlopeRoof() throws MaterialException, SystemException;
    void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException;
    List<Integer> getRequestCustomerList(Customer customer) throws LoginUserException, SystemException, MaterialException;
    RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException;
    String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException;
    void customerUpdatePayment(int id) throws LoginUserException, SystemException;
    
}
