package DBAccess;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.Material;
import FunctionLayer.Customer;
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

        } catch (SQLException | ClassNotFoundException ex) {
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
            String SQL = "SELECT * FROM materials WHERE description = (SELECT description FROM materials WHERE id = ?)";
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

    public static Customer login(String email, String password) throws LoginUserException, ClassNotFoundException {
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
        }
    }

    /*
    public void createOrder(ShoppingCart shoppingCart, String username, double balance) {
        try {
            Connection conn = new DBConnector().getConnection();
            PreparedStatement orderPstmt = conn.prepareStatement(INSERT_ORDER_CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement cupcakePstmt = conn.prepareStatement(INSERT_CUPCAKE_CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement orderDetailsPstmt = conn.prepareStatement(INSERT_ORDER_DETAILS_CREATE_ORDER);
            PreparedStatement updateBalancePstmt = conn.prepareStatement(UPDATE_BALANCE_CREATE_ORDER);
            ResultSet rs = null;
            int orderId = 0;
            try {
                orderPstmt.setString(1, username);
                orderPstmt.setDouble(2, shoppingCart.getTotalpriceForShoppingCart());
                orderPstmt.setString(3, orderDate);
                //To create a transaction we need to not have automatic commit after each statement.
                conn.setAutoCommit(false);
                int resultOrder = orderPstmt.executeUpdate();
                rs = orderPstmt.getGeneratedKeys();
                rs.next();
                orderId = rs.getInt(1);
                if (resultOrder == 1) {
                    ResultSet rsCupcake = null;
                    int cupcakeId = 0;
                    for (LineItem lineItem : shoppingCart.getArrLineItems()) {
                        cupcakePstmt.setString(1, lineItem.getCupcake().getName());
                        cupcakePstmt.setDouble(2, lineItem.getCupcake().getPrice());
                        cupcakePstmt.setString(3, lineItem.getCupcake().getTop());
                        cupcakePstmt.setString(4, lineItem.getCupcake().getBottom());
                        int resultCupcake = cupcakePstmt.executeUpdate();
                        rsCupcake = cupcakePstmt.getGeneratedKeys();
                        rsCupcake.next();
                        cupcakeId = rsCupcake.getInt(1);
                        if (resultCupcake == 1) {
                            orderDetailsPstmt.setInt(1, orderId);
                            orderDetailsPstmt.setInt(2, cupcakeId);
                            orderDetailsPstmt.setInt(3, lineItem.getQty());
                            orderDetailsPstmt.setDouble(4, lineItem.getTotalprice());
                            orderDetailsPstmt.executeUpdate();
                            updateBalancePstmt.setDouble(1, balance);
                            updateBalancePstmt.setString(2, username);
                            updateBalancePstmt.executeUpdate();
                            conn.commit();
                        } else {
                            conn.rollback();
                        }
                    }
                    conn.commit();
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
                if (orderPstmt != null) {
                    orderPstmt.close();
                }
                if (cupcakePstmt != null) {
                    cupcakePstmt.close();
                }
                if (orderDetailsPstmt != null) {
                    orderDetailsPstmt.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    */
    
    public static void createRequest(Request request, BillOfMaterial billOfMaterial, Carport carport, Shed shed){
        
    }
}
