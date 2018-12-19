
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

public class DataFacade implements IDataFacade {

    @Override
    public TreeMap<Double, Material> getRoofFlatCladdingMaterialTreeMap(String roofFlatDescription) throws SystemException, MaterialException {
        return MaterialMapper.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
    }

    @Override
    public Material getRoofSlopeCladdingMaterial(int roofSlopeId) throws MaterialException, SystemException {
        return MaterialMapper.getRoofSlopeCladdingMaterial(roofSlopeId);
    }

    @Override
    public List<Material> getDefaultList() throws MaterialException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer login(String email, String password) throws LoginUserException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee employeelogin(String email, String password) throws LoginUserException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createCustomer(Customer user) throws LoginUserException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getRoofFlatCladdingMaterialListJSP(int i) throws MaterialException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Material> getRoofSlopeCladdingMaterialList(int i) throws MaterialException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> getRequestCustomerList(Customer customer) throws LoginUserException, SystemException, MaterialException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void customerUpdatePayment(int id) throws LoginUserException, SystemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    /**
//     * Purpose of this method is to get all cladding material from database by the material mapper method
//     * @param roofFlatDescription
//     * @return 
//     * @throws SystemException
//     * @throws MaterialException 
//     */
//    public static TreeMap<Double, Material> getRoofFlatCladdingMaterialTreeMap(String roofFlatDescription) throws SystemException, MaterialException {
//        return MaterialMapper.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
//    }
//
//    /**
//     * Purpose of this method is to get all cladding material from database by the material mapper method
//     * @param roofSlopeId
//     * @return
//     * @throws MaterialException
//     * @throws SystemException 
//     */
//    public static Material getRoofSlopeCladdingMaterial(int roofSlopeId) throws MaterialException, SystemException {
//        return MaterialMapper.getRoofSlopeCladdingMaterial(roofSlopeId);
//    }
//
//    /**
//     * 
//     * @return
//     * @throws MaterialException
//     * @throws SystemException 
//     */
//    public static List<Material> getDefaultList() throws MaterialException, SystemException {
//        return MaterialMapper.getDefaultList();
//    }
//
//    /**
//     * 
//     * @param email
//     * @param password
//     * @return
//     * @throws LoginUserException
//     * @throws SystemException 
//     */
//    public static Customer login(String email, String password) throws LoginUserException, SystemException {
//        return UserMapper.login(email, password);
//    }
//
//    /**
//     * 
//     * @param email
//     * @param password
//     * @return
//     * @throws LoginUserException
//     * @throws SystemException 
//     */
//    public static Employee employeelogin(String email, String password) throws LoginUserException, SystemException {
//        return UserMapper.employeelogin(email, password);
//    }
//
//    /**
//     * 
//     * @param user
//     * @throws LoginUserException
//     * @throws SystemException 
//     */
//    public static void createCustomer(Customer user) throws LoginUserException, SystemException {
//        UserMapper.createCustomer(user);
//    }
//
//    /**
//     * 
//     * @param i
//     * @return
//     * @throws MaterialException
//     * @throws SystemException 
//     */
//    public static List<String> getRoofFlatCladdingMaterialListJSP(int i) throws MaterialException, SystemException {
//        return MaterialMapper.getRoofFlatCladdingMaterialListJSP(i);
//    }
//
//    /**
//     * 
//     * @param i
//     * @return
//     * @throws MaterialException
//     * @throws SystemException 
//     */
//    public static List<Material> getRoofSlopeCladdingMaterialList(int i) throws MaterialException, SystemException {
//        return MaterialMapper.getRoofSlopeCladdingMaterialList(i);
//    }
//    
//    /**
//     * 
//     * @param customer
//     * @param price
//     * @param carport
//     * @throws LoginUserException
//     * @throws SystemException 
//     */
//    public static void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException {
//        RequestMapper.createRequest(customer, price, carport);
//    }
//    
//    /**
//     * 
//     * @param customer
//     * @return
//     * @throws LoginUserException
//     * @throws SystemException
//     * @throws MaterialException 
//     */
//    public static List<Integer> getRequestCustomerList(Customer customer) throws LoginUserException, SystemException, MaterialException {
//        return RequestMapper.getListCustomerRequestId(customer);
//    }
//    
//    /**
//     * 
//     * @param request_id
//     * @return
//     * @throws LoginUserException
//     * @throws SystemException
//     * @throws MaterialException 
//     */
//    public static RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException {
//        return RequestMapper.getRequestDetailsByRequestId(request_id);
//    }
//    
//    /**
//     * 
//     * @param id
//     * @return
//     * @throws LoginUserException
//     * @throws SystemException
//     * @throws MaterialException 
//     */
//    public static String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException {
//        return MaterialMapper.getRoofFlatDescriptionById(id);
//    }
//    
//    /**
//     * 
//     * @param id
//     * @throws LoginUserException
//     * @throws SystemException 
//     */
//    public static void customerUpdatePayment(int id) throws LoginUserException, SystemException {
//        RequestMapper.customerUpdatePayment(id);
//    }
    
}
