package DBAccess;


import FunctionLayer.Material;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


/**
 *
 * @author leage
 */
public class MaterialMapper {

    //private final double SHED_CLADDING_LENGTH = 3000.0;
    //private final int ROOF_FLAT_CLADDING_TYPE = 2;
    //private final int ROOF_SLOPE_CLADDING_TYPE = 3;
    private static final String GET_DEFAULT_MATERIALS = "SELECT * FROM `MATERIALS` WHERE defaultused = 1 ORDER BY type_id ASC";
    private static final String GET_MATERIALS_BY_TYPEID_LENGTH = "SELECT * FROM `MATERIALS` WHERE type_id = ? AND length = ? ORDER BY description ASC";
    private static final String GET_DISTINCT_MATERIALDESCRIPTION_BY_TYPEID = "SELECT DISTINCT description FROM `MATERIALS` WHERE type_id = ?;";
    private static final String GET_MATERIAL_BY_DESCRIPTION = "SELECT * FROM MATERIALS WHERE description = (SELECT description FROM MATERIALS WHERE id = ?)";
    private static final String GET_MATERIALS_BY_TYPEID = "SELECT * FROM `MATERIALS` WHERE type_id = ?";

    public static List<Material> getDefaultList() throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_DEFAULT_MATERIALS);

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

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
            
            if(materialList.isEmpty()){
                throw new MaterialException();
            } else {
                return materialList;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }

    }

    public static List<Material> getShedCladdingMaterialList(int input_type_id, double input_length) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIALS_BY_TYPEID_LENGTH);
            ps.setInt(1, input_type_id);
            ps.setDouble(2, input_length);

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

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
            
            if(materialList.isEmpty()){
                throw new MaterialException();
            } else{
                return materialList;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }

    }

    public static List<String> getRoofFlatCladdingMaterialListJSP(int input_type_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_DISTINCT_MATERIALDESCRIPTION_BY_TYPEID);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

            List<String> roofFlatMaterialListDefault = new ArrayList();

            while (rs.next()) {
                String description = rs.getString("description");

                roofFlatMaterialListDefault.add(description);
            }
            
            if(roofFlatMaterialListDefault.isEmpty()){
                throw new MaterialException();
            } else {
                return roofFlatMaterialListDefault;
            }  

        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }

    }

    public static TreeMap<Double, Material> getRoofFlatCladdingMaterialList(int input_type_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIAL_BY_DESCRIPTION);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

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
            
            if(listRoofFlat.isEmpty()){
                throw new MaterialException();
            } else {
                return listRoofFlat;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }

    }

    public static List<Material> getRoofSlopeCladdingMaterialList(int input_type_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIALS_BY_TYPEID);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

//            if (!rs.next()) {
//                throw new MaterialException();
//            }

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
            
            if(materialList.isEmpty()){
                throw new MaterialException();
            } else {
                return materialList;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
            //Logging
        }

    }
//    public static void main(String[] args) throws MaterialException, SystemException {
//        List hej = new ArrayList();
//        hej = getRoofFlatCladdingMaterialListJSP(2);
//       
//        System.out.println(hej.toString());
//    }
}
