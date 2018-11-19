package DBAccess;


import FunctionLayer.Material;
import FunctionLayer.exceptions.MaterialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author leage
 */
public class MaterialMapper {


    public static Material getMaterialById(int materialId) throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT * FROM `MATERIALS` WHERE id=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, materialId);
            ResultSet rs = ps.executeQuery();
            Material material = null;
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String measure = rs.getString("measure");
                String type = rs.getString("type");
                String description = rs.getString("description");
                double buyprice = rs.getDouble("buy_price");
                double sellprice = rs.getDouble("sell_price"); 
             
                 
      

                material = new Material(id, name, measure, type, description, buyprice, sellprice); 
                material.setId(materialId);
            }

            return material;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new MaterialException(ex.getMessage());
        }
    }

    public static List getMaterialList() throws MaterialException {
         try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT * FROM `MATERIALS`";
            PreparedStatement ps = con.prepareStatement(SQL);
            
            ResultSet rs = ps.executeQuery();
            
            Material material = null;
            List materialList = new ArrayList(); 
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String measure = rs.getString("measure");
                String type = rs.getString("type");
                String description = rs.getString("description");
                double buyprice = rs.getDouble("buy_price");
                double sellprice = rs.getDouble("sell_price"); 
             
                material = new Material(id, name, measure, type, description, buyprice, sellprice); 
                materialList.add(material); 
      

            }

            return materialList;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new MaterialException(ex.getMessage());
        }
    }

    

//    public static void createOrder(Order order, User user) throws OrderException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "INSERT INTO `order` (length, width, layers, user_id) VALUES (?, ?, ?, ?)";
//            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, order.getLength());
//            ps.setInt(2, order.getWidth());
//            ps.setInt(3, order.getLayers());
//            ps.setInt(4, user.getId());
//            ps.executeUpdate();
//            ResultSet ids = ps.getGeneratedKeys();
//            ids.next();
//            int id = ids.getInt(1);
//            order.setId(id);
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new OrderException(ex.getMessage());
//        }
//    }
//
//    public static List<Order> getUserOrders(User user) throws OrderException {
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT * FROM `order` WHERE user_id=?";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setInt(1, user.getId());
//            ResultSet rs = ps.executeQuery();
//            List<Order> orders = new ArrayList<>();
//            while (rs.next()) {
//                int orderId = rs.getInt("id");
//                int length = rs.getInt("length");
//                int width = rs.getInt("width");
//                int layers = rs.getInt("layers");
//
//                Order order = new Order(length, width, layers);
//                order.setId(orderId);
//                orders.add(order);
//            }
//            return orders;
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new OrderException(ex.getMessage());
//        }
//    }
//
//    public static List<Order> getAllOrders() throws OrderException {
//
//        try {
//            Connection con = Connector.connection();
//            String SQL = "SELECT * FROM `order`";
//            PreparedStatement pstmt = con.prepareStatement(SQL);
//            ResultSet rs = pstmt.executeQuery();
//            List<Order> orders = new ArrayList<>();
//            while (rs.next()) {
//                int orderId = rs.getInt("id");
//                int length = rs.getInt("length");
//                int width = rs.getInt("width");
//                int layers = rs.getInt("layers");
//
//                Order order = new Order(length, width, layers);
//                order.setId(orderId);
//                orders.add(order);
//            }
//            return orders;
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new OrderException(ex.getMessage());
//        }
//
//    }
//
//    public static Order shipOrder(Order order) throws OrderException {
//        try {
//            Connection con = Connector.connection();
//            String sql = "UPDATE `order` SET `shipped`=? WHERE `id`=?;";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, 1);
//            ps.setInt(2, order.getId());
//            ps.executeUpdate();
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new OrderException(ex.getMessage());
//        }
//        
//        return order;
//    }
//
//    public static void main(String[] args) throws MaterialException {
//        Material m = getMaterialById(1); 
//        
//  
//        List<Material> is = getMaterialList();
//   
//        System.out.println(""+m);
//        System.err.println(""+is);
//    }
}
