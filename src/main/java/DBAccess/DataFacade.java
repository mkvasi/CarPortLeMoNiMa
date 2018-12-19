
package DBAccess;

import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Employee;
import FunctionLayer.entity.Material;
import FunctionLayer.entity.Price;
import FunctionLayer.entity.RequestObject;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;
import java.util.TreeMap;

/**
 * Purpose of this class is to combine methods from functionlayer with correct mappers with access to database
 */

public class DataFacade {

    /**
     * Meningen med denne metode er at hente alle metrialerne i databasen og returnere som et TreeMap baseret på det angivne parameter 
     * @param roofFlatDescription
     * @return TreeMap
     * @throws SystemException
     * @throws MaterialException 
     */
    public static TreeMap<Double, Material> getRoofFlatCladdingMaterialTreeMap(String roofFlatDescription) throws SystemException, MaterialException {
        return MaterialMapper.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
    }

    /**
     * Meningen med denne metode er at hente det specifike materiale baseret på det angivne parameter
     * @param roofSlopeId
     * @return Material
     * @throws MaterialException
     * @throws SystemException 
     */
    public static Material getRoofSlopeCladdingMaterial(int roofSlopeId) throws MaterialException, SystemException {
        return MaterialMapper.getRoofSlopeCladdingMaterial(roofSlopeId);
    }

    /**
     * Meningen med denne metode er at hente alle standard materialer i en liste
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getDefaultList() throws MaterialException, SystemException {
        return MaterialMapper.getDefaultList();
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
        return UserMapper.login(email, password);
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
        return UserMapper.employeelogin(email, password);
    }

    /**
     * Meningen med denn metode er at oprette en bruger i systemet ud fra de angivne informationer i det object som er med som parameter
     * @param user
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void createCustomer(Customer user) throws LoginUserException, SystemException {
        UserMapper.createCustomer(user);
    }

    /**
     * Meningen med denne metode er at hente alle materialerne til fladt tag baseret på det angivne parameter
     * @param i
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<String> getRoofFlatCladdingMaterialListJSP(int i) throws MaterialException, SystemException {
        return MaterialMapper.getRoofFlatCladdingMaterialListJSP(i);
    }

    /**
     * Meningen med denne metode er at alle materialerne til tag med hældning baseret på det angivne parameter
     * @param i
     * @return List
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getRoofSlopeCladdingMaterialList(int i) throws MaterialException, SystemException {
        return MaterialMapper.getRoofSlopeCladdingMaterialList(i);
    }

    /**
     * Meningen med denne metode er at lave et request til databasen med information fra de angivne parametre
     * @param customer
     * @param price
     * @param carport
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException {
        RequestMapper.createRequest(customer, price, carport);
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
        return RequestMapper.getListCustomerRequestId(customer);
    }

    /**
     * Meningen med denne metode er at hente alle detaljer på en forespørgsel
     * @param request_id
     * @return RequestObject
     * @throws LoginUserException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException {
        return RequestMapper.getRequestDetailsByRequestId(request_id);
    }

    /**
     * Meningen med denne metode er at hente beskrivelsen for materialet baseret på det angivne parameter
     * @param id
     * @return String
     * @throws LoginUserException
     * @throws SystemException
     * @throws MaterialException 
     */
    public static String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException {
        return MaterialMapper.getRoofFlatDescriptionById(id);
    }

    /**
     * Meningen med denne metode er at sætte en forespørgsel som betalt i datbasen baseret på det angivne parameter
     * @param id
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void customerUpdatePayment(int id) throws LoginUserException, SystemException {
        RequestMapper.customerUpdatePayment(id);
    }
    
}
