
package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaterialMapperIT {
    
  private static Connection testConnection;
    private static String USER = "connect";
    private static String USERPW = "connect";
    private static String DBNAME = "fog";
    private static String HOST = "188.166.86.13";

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    
    
    @Before
    public void setUp() throws SQLException {
             try {
            // awoid making a new connection for each test
            if ( testConnection == null ) {
                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
                Class.forName( "com.mysql.cj.jdbc.Driver" );

                testConnection = DriverManager.getConnection( url, USER, USERPW );
                // Make mappers use test 
                DBConnector.setConnection( testConnection );
            }
            // reset test database
            

        } catch ( ClassNotFoundException | SQLException ex ) {
            testConnection = null;
            System.out.println( "Could not open connection to database: " + ex.getMessage() );
}
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetDefaultList() throws Exception {
        int amountOfItemsOnList = 47;
        int result = MaterialMapper.getDefaultList().size();
        assertEquals(amountOfItemsOnList, result);
       
    }

    @Test
    public void testGetRoofFlatCladdingMaterialListJSP() throws Exception {
        System.out.println("getRoofFlatCladdingMaterialListJSP");
        int input_type_id = 2;
        int expResult = 2;
        int result = MaterialMapper.getRoofFlatCladdingMaterialListJSP(input_type_id).size();
        assertEquals(expResult, result);
       
    }
}