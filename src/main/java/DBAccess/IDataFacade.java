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
 *
 * @author Morten
 */
public interface IDataFacade {
    TreeMap<Double, Material> getRoofFlatCladdingMaterialTreeMap(String roofFlatDescription) throws SystemException, MaterialException;
    Material getRoofSlopeCladdingMaterial(int roofSlopeId) throws MaterialException, SystemException;
    List<Material> getDefaultList() throws MaterialException, SystemException;
    Customer login(String email, String password) throws LoginUserException, SystemException;
    Employee employeelogin(String email, String password) throws LoginUserException, SystemException;
    void createCustomer(Customer user) throws LoginUserException, SystemException;
    List<String> getRoofFlatCladdingMaterialListJSP(int i) throws MaterialException, SystemException;
    List<Material> getRoofSlopeCladdingMaterialList(int i) throws MaterialException, SystemException;
    void createRequest(Customer customer, Price price, Carport carport) throws LoginUserException, SystemException;
    List<Integer> getRequestCustomerList(Customer customer) throws LoginUserException, SystemException, MaterialException;
    RequestObject getRequestDetails(int request_id) throws LoginUserException, SystemException, MaterialException;
    String getRoofDescriptionById(int id) throws LoginUserException, SystemException, MaterialException;
    void customerUpdatePayment(int id) throws LoginUserException, SystemException;
}
