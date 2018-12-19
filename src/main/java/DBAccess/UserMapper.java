
package DBAccess;

import FunctionLayer.entity.Customer;
import FunctionLayer.entity.Employee;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Meningen med denne klasse er at samle alle SQL til databasen baseret på User
 */
public class UserMapper {

    private static final String INSERT_CUSTOMER_DEFAULT = "INSERT INTO `CUSTOMER` (`firstname`, `lastname`, `email`, `zipcode`, `city`, `phonenumber`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_LOGIN_USER = "SELECT * FROM `CUSTOMER` WHERE `email`=? AND `password`=?";
    private static final String GET_LOGIN_EMPLOYEE = "SELECT * FROM `EMPLOYEE` WHERE `email`=? AND `password`=?";

    /**
     * Meningen med denn metode er at tjekke input fra en bruger, som prøver at logge ind
     * @param email
     * @param password
     * @return Customer
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static Customer login(String email, String password) throws LoginUserException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_LOGIN_USER);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int zipcode = rs.getInt("zipcode");
                String city = rs.getString("city");
                int phone = rs.getInt("phonenumber");
                String role = rs.getString("role");
                Customer customer = new Customer(id, firstname, lastname, email, zipcode, city, phone, password, role);
                return customer;
            } else {
                throw new LoginUserException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
    }

    /**
     * Meningen med denn metode er at oprette en bruger i systemet ud fra de angivne informationer i det object som er med som parameter
     * @param c
     * @throws LoginUserException
     * @throws SystemException 
     */
    public static void createCustomer(Customer c) throws LoginUserException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER_DEFAULT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getEmail());
            ps.setInt(4, c.getZipcode());
            ps.setString(5, c.getCity());
            ps.setInt(6, c.getPhone());
            ps.setString(7, c.getPassword());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            c.setId(id);
        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
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
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_LOGIN_EMPLOYEE);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                boolean admin = rs.getBoolean("admin");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String role = rs.getString("role");
                Employee employee = new Employee(id, admin, firstname, lastname, email, password, role);
                return employee;
            } else {
                throw new LoginUserException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
    }

}
