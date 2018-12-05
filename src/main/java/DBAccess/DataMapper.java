package DBAccess;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.Material;
import FunctionLayer.Customer;
import FunctionLayer.LineItem;
import FunctionLayer.Request;
import FunctionLayer.Shed;
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

    //private final double SHED_CLADDING_LENGTH = 3000.0;
    //private final int ROOF_FLAT_CLADDING_TYPE = 2;
    //private final int ROOF_SLOPE_CLADDING_TYPE = 3;
    private static final String INSERT_CUSTOMER_DEFAULT = "INSERT INTO `CUSTOMER` (firstname, lastname, email, zipcode, city, phonenumber, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_LOGIN_USER = "SELECT * FROM `CUSTOMER` WHERE email=? AND password=?";

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

        } catch (SQLException ex) {
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

        } catch (SQLException ex) {
            throw new MaterialException(ex.getMessage());
        }

    }

    public static TreeMap<Double, Material> getRoofFlatCladdingMaterialList(int input_type_id) throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT * FROM MATERIALS WHERE description = (SELECT description FROM MATERIALS WHERE id = ?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, input_type_id);

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

        } catch (SQLException ex) {
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

        } catch (SQLException ex) {
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
                int phone = rs.getInt("phonenumber");
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
        }
    }

    public static void createRequest(Customer customer, Request request, Carport carport, Shed shed) {
        try {
            Connection conn = DBConnector.connection();
            PreparedStatement shedPstmt = conn.prepareStatement("INSERT INTO `SHED` (`heigth`, `width`, `length`, `shedcladding`) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement carportPstmt = conn.prepareStatement("INSERT INTO `CARPORT` (`heigth`, `width`, `length`, `roofslopecelsius`, `roofcladding`, `shed_id`) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement requestPstmt = conn.prepareStatement("INSERT INTO `REQUEST` (`pricedefault`, `priceemployee`, `customer_id`, `carport_id`) VALUES (?,?,?,?)");
            PreparedStatement billOfMaterialPstmt = conn.prepareStatement("INSERT INTO `CARPORT_HAS_MATERIALS` (`carport_id`, `materials_id`) VALUES (?,?)");
            ResultSet rsShed = null;
            int shedId = 0;
            try {
                shedPstmt.setDouble(1, shed.getHeigth());
                shedPstmt.setDouble(2, shed.getWidth());
                shedPstmt.setDouble(3, shed.getLength());
                shedPstmt.setInt(4, shed.getShedCladding());
                //To create a transaction we need to not have automatic commit after each statement.
                conn.setAutoCommit(false);
                int resultShed = shedPstmt.executeUpdate();
                rsShed = shedPstmt.getGeneratedKeys();
                rsShed.next();
                shedId = rsShed.getInt(1);
                if (resultShed == 1) {
                    ResultSet rsCarport = null;
                    int carportId = 0;
                    carportPstmt.setDouble(1, carport.getHeigth());
                    carportPstmt.setDouble(2, carport.getWidth());
                    carportPstmt.setDouble(3, carport.getLength());
                    carportPstmt.setInt(4, carport.getRoof().getRoofSlopeCelsius());
                    carportPstmt.setInt(5, carport.getRoof().getRoofCladding());
                    carportPstmt.setInt(6, shedId);
                    int resultCarport = carportPstmt.executeUpdate();
                    rsCarport = carportPstmt.getGeneratedKeys();
                    rsCarport.next();
                    carportId = rsCarport.getInt(1);
                    if (resultCarport == 1) {
                        requestPstmt.setDouble(1, request.getPriceDefault());
                        requestPstmt.setDouble(2, request.getPriceEmployee());
                        requestPstmt.setInt(3, customer.getId());
                        requestPstmt.setInt(4, carportId);
                        requestPstmt.executeUpdate();
                        for (LineItem lineItem : carport.getBillOfmaterial().getLineItems()) {
                            billOfMaterialPstmt.setInt(1, carportId);
                            billOfMaterialPstmt.setInt(2, lineItem.getMaterial().getId());
                            billOfMaterialPstmt.executeUpdate();
                        }
                        conn.commit();
                    } else {
                        conn.rollback();
                    }
                } else {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); //This should go in the log file.
                // roll back the transaction
                if (conn != null) {
                    conn.rollback();
                }
            } finally {
                conn.setAutoCommit(true);
            }
            
        }catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
    public static List<Integer> getListCustomerRequestId(Customer customer) throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT id FROM `REQUEST` WHERE customer_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, customer.getId());

            ResultSet rs = ps.executeQuery();

            List<Integer> listCustomerRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listCustomerRequestId.add(id);
            }

            return listCustomerRequestId;

        } catch (SQLException ex) {
            throw new MaterialException(ex.getMessage());
        }
    }
    
    public static List<Integer> getListEmployeeOpenRequestId() throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT id FROM `REQUEST` WHERE employee_id IS NULL?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            List<Integer> listEmployeeOpenRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listEmployeeOpenRequestId.add(id);
            }

            return listEmployeeOpenRequestId;

        } catch (SQLException ex) {
            throw new MaterialException(ex.getMessage());
        }
    }
    
    public static List<Integer> getListEmployeeNotOpenRequestId() throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT id FROM `REQUEST` WHERE employee_id IS NOT NULL?";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            List<Integer> listEmployeeNotOpenRequestId = new ArrayList();

            while (rs.next()) {

                int id = rs.getInt("id");

                listEmployeeNotOpenRequestId.add(id);
            }

            return listEmployeeNotOpenRequestId;

        } catch (SQLException ex) {
            throw new MaterialException(ex.getMessage());
        }
    }
}