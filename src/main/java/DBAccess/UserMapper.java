/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author nr
 */
public class UserMapper {

    private static final String INSERT_CUSTOMER_DEFAULT = "INSERT INTO `CUSTOMER` (`firstname`, `lastname`, `email`, `zipcode`, `city`, `phonenumber`, `password`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_LOGIN_USER = "SELECT * FROM `CUSTOMER` WHERE `email`=? AND `password`=?";
    private static final String GET_LOGIN_EMPLOYEE = "SELECT * FROM `EMPLOYEE` WHERE `email`=? AND `password`=?";

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
            //Logging
        }
    }

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
            //Logging
        }
    }

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
            //Logging
        }
    }

}
