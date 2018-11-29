package DBAccess;

import FunctionLayer.Material;
import FunctionLayer.exceptions.MaterialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author leage
 */
public class DataMapper {

    public static List<Material> getMaterialList() throws MaterialException {
        try {
            Connection con = DBConnector.connection();
            String SQL = "SELECT * FROM `MATERIALS`";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            //Material material = null;
            //HashMap<Integer, Material> materialList = new HashMap();
            List<Material> materialList = new ArrayList();
            
            while (rs.next()) {

                int id = rs.getInt("id");
                double length = rs.getDouble("length");
                double height = rs.getDouble("heigth");
                double width = rs.getDouble("width");
                int measure_id = rs.getInt("measure_id");
                int type_id = rs.getInt("type_id");
                String description = rs.getString("description");
                double buyprice = rs.getDouble("buy_price");
                double sellprice = rs.getDouble("sell_price");
                //int _width = Integer.parseInt(String.valueOf(width));
                //int _height = Integer.parseInt(String.valueOf(height));
                //int name = type_id + _width + _height;

                //material = new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height);
                //materialList.put(name, new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height));
                materialList.add(new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height));

            }

            return materialList;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new MaterialException(ex.getMessage());
        }
    }

//    public static HashMap<String, Material> getMaterialList() throws MaterialException {
//        try {
//            Connection con = DBConnector.connection();
//            String SQL = "SELECT * FROM `MATERIALS`";
//            PreparedStatement ps = con.prepareStatement(SQL);
//
//            ResultSet rs = ps.executeQuery();
//
//            Material material = null;
//            HashMap<String, Material> materialList = new HashMap();
//            while (rs.next()) {
//
//                int id = rs.getInt("id");
//                double length = rs.getDouble("length");
//                double height = rs.getDouble("heigth");
//                double width = rs.getDouble("width");
//                int measure_id = rs.getInt("measure_id");
//                int type_id = rs.getInt("type_id");
//                String description = rs.getString("description");
//                double buyprice = rs.getDouble("buy_price");
//                double sellprice = rs.getDouble("sell_price");
//                String name = description + length;
//
//                material = new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height);
//                materialList.put(name, material);
//
//            }
//
//            return materialList;
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new MaterialException(ex.getMessage());
//        }
//    }
//    public static HashMap<Integer, Material> getMaterialList() throws MaterialException {
//        try {
//            Connection con = DBConnector.connection();
//            String SQL = "SELECT * FROM `MATERIALS`";
//            PreparedStatement ps = con.prepareStatement(SQL);
//
//            ResultSet rs = ps.executeQuery();
//
//            //Material material = null;
//            HashMap<Integer, Material> materialList = new HashMap();
//            while (rs.next()) {
//
//                int id = rs.getInt("id");
//                double length = rs.getDouble("length");
//                double height = rs.getDouble("heigth");
//                double width = rs.getDouble("width");
//                int measure_id = rs.getInt("measure_id");
//                int type_id = rs.getInt("type_id");
//                String description = rs.getString("description");
//                double buyprice = rs.getDouble("buy_price");
//                double sellprice = rs.getDouble("sell_price");
//                int _width = Integer.parseInt(String.valueOf(width));
//                int _height = Integer.parseInt(String.valueOf(height));
//                int name = type_id + _width + _height;
//
//                //material = new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height);
//                materialList.put(name, new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height));
//
//            }
//
//            return materialList;
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new MaterialException(ex.getMessage());
//        }
//    }
//    public static HashMap<Integer, Material> getMaterialList() throws MaterialException {
//        try {
//            Connection con = DBConnector.connection();
//            String SQL = "SELECT * FROM `MATERIALS`";
//            PreparedStatement ps = con.prepareStatement(SQL);
//
//            ResultSet rs = ps.executeQuery();
//
//            //Material material = null;
//            HashMap<Integer, Material> materialList = new HashMap();
//            while (rs.next()) {
//
//                int id = rs.getInt("id");
//                double length = rs.getDouble("length");
//                double height = rs.getDouble("heigth");
//                double width = rs.getDouble("width");
//                int measure_id = rs.getInt("measure_id");
//                int type_id = rs.getInt("type_id");
//                String description = rs.getString("description");
//                double buyprice = rs.getDouble("buy_price");
//                double sellprice = rs.getDouble("sell_price");
//                int _width = Integer.parseInt(String.valueOf(width));
//                int _height = Integer.parseInt(String.valueOf(height));
//                int name = type_id + _width + _height;
//
//                //material = new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height);
//                materialList.put(name, new Material(id, measure_id, type_id, description, buyprice, sellprice, length, width, height));
//
//            }
//
//            return materialList;
//
//        } catch (SQLException | ClassNotFoundException ex) {
//            throw new MaterialException(ex.getMessage());
//        }
//    }
//    public static List<Material> getAllBoardsForThisCarportWithOutLengthCalculation() throws MaterialException {
//        HashMap<Integer, TreeMap<Double, Material>> materialList = new HashMap<>();
//        try {
//            Connection con = DBConnector.connection();
//
//            materialList.put(6, getBoardWithType_id(con, 6, 200.0, 25));
//            materialList.put(7, getBoardWithType_id(con, 7, 97.0, 97.0));
//            materialList.put(8, getBoardWithType_id(con, 8, 73.0, 38.0));
//            materialList.put(9, getBoardWithType_id(con, 9, 150.0, 50.0));
//            materialList.put(10, getBoardWithType_id(con, 10, 195.0, 45));
//            materialList.put(11, getBoardWithType_id(con, 11, 1.0, 1.0));
//
//            return materialList;
//
//        } catch (SQLException | ClassNotFoundException ex) {
//
//            throw new MaterialException(ex.getMessage());
//
//        }
//
//    }
//
//    public static TreeMap<Double, Material> getBoardWithType_id(Connection con, int type_id, double width, double height) throws SQLException {
//        String SQL = " SELECT * FROM `MATERIALS` WHERE type_id = " + type_id + " AND width =" + width + " AND heigth =" + height + "";
//        PreparedStatement ps = con.prepareStatement(SQL);
//        ResultSet rs = ps.executeQuery();
//
//        TreeMap<Double, Material> boardsType = new TreeMap();
//        Material material = null;
//        while (rs.next()) {
//
//            int id = rs.getInt("id");
//            String description = rs.getString("description");
//            double length = rs.getDouble("length");
//            int measure_id = rs.getInt("measure_id");
//            double buyprice = rs.getDouble("buy_price");
//            double sellprice = rs.getDouble("sell_price");
//
//            material = new Material(id, type_id, measure_id, description, buyprice, sellprice, length, width, height);
//            boardsType.put(length, material);
//        }
//        return boardsType;
//    }
//    public static void main(String[] args) throws MaterialException {
//
//        HashMap<Integer, TreeMap<Double, Material>> hej = getAllBoardsForThisCarportWithOutLengthCalculation();
//        for (Map.Entry<Integer, TreeMap<Double, Material>> entry : hej.entrySet()) {
//            Integer key = entry.getKey();
//            TreeMap<Double, Material> value = entry.getValue();
//            System.out.println(key);
//            System.out.println(value);
//
//            for (Map.Entry<Double, Material> entry1 : value.entrySet()) {
//                Double key1 = entry1.getKey();
//                Material value1 = entry1.getValue();
//
//            }
//
//        }
//    }
}
