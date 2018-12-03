package DBAccess;

import FunctionLayer.Material;
import FunctionLayer.User;
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
public class DataMapper {


    
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

    public static User login(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void createUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
