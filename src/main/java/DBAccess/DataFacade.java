/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Employee;
import FunctionLayer.entity.Material;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author nr
 */
public class DataFacade {

    public static TreeMap<Double, Material> getRoofFlatCladdingMaterialTreeMap(String roofFlatDescription) throws SystemException, MaterialException {
        return MaterialMapper.getRoofFlatCladdingMaterialTreeMap(roofFlatDescription);
    }

    public static Material getRoofSlopeCladdingMaterial(int roofSlopeId) throws MaterialException, SystemException {
        return MaterialMapper.getRoofSlopeCladdingMaterial(roofSlopeId);
    }

    public static List<Material> getDefaultList() throws MaterialException, SystemException {
        return MaterialMapper.getDefaultList();
    }

    public static Customer login(String email, String password) throws LoginUserException, SystemException {
        return UserMapper.login(email, password);
    }

    public static Employee employeelogin(String email, String password) {
        return employeelogin(email, password);
    }

    public static void createCustomer(Customer user) throws LoginUserException, SystemException {
        UserMapper.createCustomer(user);
    }

    public static List<String> getRoofFlatCladdingMaterialListJSP(int i) throws MaterialException, SystemException {
        return MaterialMapper.getRoofFlatCladdingMaterialListJSP(i);
    }

    public static List<Material> getRoofSlopeCladdingMaterialList(int i) throws MaterialException, SystemException {
        return MaterialMapper.getRoofSlopeCladdingMaterialList(i);
    }

}
