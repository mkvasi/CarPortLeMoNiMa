package DBAccess;

import FunctionLayer.Material;
import FunctionLayer.Customer;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leage
 */
public class DataMapper {

    private final double SHED_CLADDING_LENGTH = 3000.0;
    private final int ROOF_FLAT_CLADDING_TYPE = 2;
    private final int ROOF_SLOPE_CLADDING_TYPE = 3;

    private static final String INSERT_CUSTOMER_DEFAULT = "INSERT INTO `customer` (firstname, lastname, email, zipcode, city, phonenumber, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_LOGIN_USER = "SELECT id, role FROM `customer` WHERE email=? AND password=?";

    public static List<Material> getDefaultList() throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT * FROM `MATERIALS` WHERE defaultused = 1 ORDER BY type_id ASC";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            List<Material> materialList = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");
                String description = rs.getString("description");
                double height = rs.getDouble("heigth");
                double width = rs.getDouble("width");
                double length = rs.getDouble("length");
                double buyprice = rs.getDouble("buyprice");
                double sellprice = rs.getDouble("sellprice");
                boolean defaultUsed = rs.getBoolean("defaultused");
                int type_id = rs.getInt("type_id");
                int measure_id = rs.getInt("measure_id");

                materialList.add(new Material(id, description, height, width, length, buyprice, sellprice, defaultUsed, type_id, measure_id));
            }

            return materialList;

        } catch (SQLException ex) {
            throw new MaterialException(ex.getMessage());
        }

    }

    public static List<Material> getShedCladdingMaterialList(int input_type_id, double input_length) throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT * FROM `MATERIALS` WHERE type_id = ? AND length = ? ORDER BY description ASC";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, input_type_id);
            ps.setDouble(2, input_length);

            ResultSet rs = ps.executeQuery();

            List<Material> materialList = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");
                String description = rs.getString("description");
                double height = rs.getDouble("heigth");
                double width = rs.getDouble("width");
                double length = rs.getDouble("length");
                double buyprice = rs.getDouble("buyprice");
                double sellprice = rs.getDouble("sellprice");
                boolean defaultUsed = rs.getBoolean("defaultused");
                int type_id = rs.getInt("type_id");
                int measure_id = rs.getInt("measure_id");

                materialList.add(new Material(id, description, height, width, length, buyprice, sellprice, defaultUsed, type_id, measure_id));
            }

            return materialList;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new MaterialException(ex.getMessage());
        }

    }

    public static List<String> getRoofFlatCladdingMaterialListJSP(int input_type_id) throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT DISTINCT description FROM `MATERIALS` WHERE type_id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

            List<String> roofFlatMaterialListDefault = new ArrayList();

            while (rs.next()) {
                String description = rs.getString("description");

                roofFlatMaterialListDefault.add(description);
            }

            return roofFlatMaterialListDefault;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new MaterialException(ex.getMessage());
        }

    }

    //Ny transaction til at først finde description ud fra id og derefter køre nedenståend epå udlæst description
    public static TreeMap<Double, Material> getRoofFlatCladdingMaterialList(String input_description) throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            //Ny transaction til at først finde description ud fra id og derefter køre nedenståend epå udlæst description
            String SQL = "SELECT * FROM `MATERIALS` WHERE description = '?'";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, input_description);

            ResultSet rs = ps.executeQuery();

            TreeMap<Double, Material> listRoofFlat = new TreeMap();

            while (rs.next()) {

                int id = rs.getInt("id");
                String description = rs.getString("description");
                double height = rs.getDouble("heigth");
                double width = rs.getDouble("width");
                double length = rs.getDouble("length");
                double buyprice = rs.getDouble("buyprice");
                double sellprice = rs.getDouble("sellprice");
                boolean defaultUsed = rs.getBoolean("defaultused");
                int type_id = rs.getInt("type_id");
                int measure_id = rs.getInt("measure_id");

                listRoofFlat.put(length, new Material(id, description, height, width, length, buyprice, sellprice, defaultUsed, type_id, measure_id));
            }

            return listRoofFlat;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new MaterialException(ex.getMessage());
        }

    }

    public static List<Material> getRoofSlopeCladdingMaterialList(int input_type_id) throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT * FROM `MATERIALS` WHERE type_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

            List<Material> materialList = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");
                String description = rs.getString("description");
                double height = rs.getDouble("heigth");
                double width = rs.getDouble("width");
                double length = rs.getDouble("length");
                double buyprice = rs.getDouble("buyprice");
                double sellprice = rs.getDouble("sellprice");
                boolean defaultUsed = rs.getBoolean("defaultused");
                int type_id = rs.getInt("type_id");
                int measure_id = rs.getInt("measure_id");

                materialList.add(new Material(id, description, height, width, length, buyprice, sellprice, defaultUsed, type_id, measure_id));
            }

            return materialList;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new MaterialException(ex.getMessage());
        }

    }

    public static Customer login(String email, String password) throws LoginUserException {
        try {
            Connection con = DBConnector.connection();
            String SQL = GET_LOGIN_USER;
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                int zipcode = rs.getInt("zipcode");
                String city = rs.getString("city");
                int phone = rs.getInt("phone");
                String role = rs.getString("role");
                Customer customer = new Customer(id, firstname, lastname, email, zipcode, city, phone, password, role);
                return customer;
            } else {
                throw new LoginUserException("Could not validate user");
            }
        } catch (SQLException ex) {
            throw new LoginUserException(ex.getMessage());
        }
    }

    public static void createCustomer(Customer c) throws LoginUserException {
        try {
            Connection con = DBConnector.connection();
            String SQL = INSERT_CUSTOMER_DEFAULT;
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
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
            throw new LoginUserException(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
