package DBAccess;

import FunctionLayer.entity.Material;
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
 * @author Morten
 */
public class MaterialMapper {

    private static final String GET_DEFAULT_MATERIALS = "SELECT * FROM `MATERIALS` WHERE `defaultused` = 1 ORDER BY `type_id` ASC";
    private static final String GET_MATERIALS_BY_TYPEID_LENGTH = "SELECT * FROM `MATERIALS` WHERE `type_id` = ? AND `length` = ? ORDER BY `description` ASC";
    private static final String GET_DISTINCT_MATERIALDESCRIPTION_BY_TYPEID = "SELECT DISTINCT `description` FROM `MATERIALS` WHERE `type_id` = ?";
    private static final String GET_MATERIAL_BY_DESCRIPTION = "SELECT * FROM `MATERIALS` WHERE `description` = ?";
    private static final String GET_MATERIALS_BY_TYPEID = "SELECT * FROM `MATERIALS` WHERE `type_id` = ?";
    private static final String GET_MATERIALS_BY_ID = "SELECT * FROM `MATERIALS` WHERE `id` = ?";
    private static final String GET_MATERIALDESCRIPTION_BY_ID = "SELECT description FROM `MATERIALS` WHERE `id` = ?";

    /**
     * 
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getDefaultList() throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_DEFAULT_MATERIALS);

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

            if (materialList.isEmpty()) {
                throw new MaterialException();
            } else {
                return materialList;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }

    }

    /**
     * 
     * @param input_type_id
     * @param input_length
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getShedCladdingMaterialList(int input_type_id, double input_length) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIALS_BY_TYPEID_LENGTH);
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

            if (materialList.isEmpty()) {
                throw new MaterialException();
            } else {
                return materialList;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }

    }

    /**
     * 
     * @param input_type_id
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<String> getRoofFlatCladdingMaterialListJSP(int input_type_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_DISTINCT_MATERIALDESCRIPTION_BY_TYPEID);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

            List<String> roofFlatMaterialListDefault = new ArrayList();

            while (rs.next()) {
                String description = rs.getString("description");

                roofFlatMaterialListDefault.add(description);
            }

            if (roofFlatMaterialListDefault.isEmpty()) {
                throw new MaterialException();
            } else {
                return roofFlatMaterialListDefault;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }

    }

    /**
     * 
     * @param input_description
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static TreeMap<Double, Material> getRoofFlatCladdingMaterialTreeMap(String input_description) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIAL_BY_DESCRIPTION);
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

            if (listRoofFlat.isEmpty()) {
                throw new MaterialException();
            } else {
                return listRoofFlat;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }

    }

    /**
     * 
     * @param input_type_id
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static List<Material> getRoofSlopeCladdingMaterialList(int input_type_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIALS_BY_TYPEID);
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

            if (materialList.isEmpty()) {
                throw new MaterialException();
            } else {
                return materialList;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }

    }

    /**
     * 
     * @param input_type_id
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static Material getRoofSlopeCladdingMaterial(int input_type_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIALS_BY_ID);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

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

                return new Material(id, description, height, width, length, buyprice, sellprice, defaultUsed, type_id, measure_id);
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
        return null;

    }

    /**
     * 
     * @param input_type_id
     * @return
     * @throws MaterialException
     * @throws SystemException 
     */
    public static String getRoofFlatDescriptionById(int input_type_id) throws MaterialException, SystemException {
        try {
            Connection con = DBConnector.connection();
            PreparedStatement ps = con.prepareStatement(GET_MATERIALDESCRIPTION_BY_ID);
            ps.setInt(1, input_type_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String description = rs.getString("description");

                return description;
            }

        } catch (SQLException ex) {
            throw new SystemException(ex);
        }
        
        return null;

    }
}


