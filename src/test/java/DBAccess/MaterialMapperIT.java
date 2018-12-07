/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author leage
 */
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
    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getDefaultList method, of class DataMapper.
//     * @throws java.lang.Exception
//     */
    @Test
    public void testGetDefaultList() throws Exception {
        int amountOfItemsOnList = 47;
        int result = MaterialMapper.getDefaultList().size();
        assertEquals(amountOfItemsOnList, result);
       
    }
////
////    /**
////     * Test of getShedCladdingMaterialList method, of class DataMapper.
////     */
//    @Test
//    public void testGetShedCladdingMaterialList6() throws Exception {
//        
//        int input_type_id = 6;
//        double input_length = 3600.0;
//        int expResult = 8;
//        int result = MaterialMapper.getShedCladdingMaterialList(input_type_id, input_length).size();
//        assertEquals(expResult, result);
//    
//    }
//    @Test
//    public void testGetShedCladdingMaterialList2() throws Exception {
//        
//        int input_type_id = 2;
//        double input_length = 1;
//        int expResult = 2;
//        int result = MaterialMapper.getShedCladdingMaterialList(input_type_id, input_length).size();
//        assertEquals(expResult, result);
//    
//    }
////
////    /**
////     * Test of getRoofFlatCladdingMaterialListJSP method, of class DataMapper.
////     */
////    @Test
////    public void testGetRoofFlatCladdingMaterialListJSP() throws Exception {
////        System.out.println("getRoofFlatCladdingMaterialListJSP");
////        int input_type_id = 0;
////        List<String> expResult = null;
////        List<String> result = DataMapper.getRoofFlatCladdingMaterialListJSP(input_type_id);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getRoofFlatCladdingMaterialList method, of class DataMapper.
////     */
////    @Test
////    public void testGetRoofFlatCladdingMaterialList() throws Exception {
////        System.out.println("getRoofFlatCladdingMaterialList");
////        int input_type_id = 0;
////        TreeMap<Double, Material> expResult = null;
////        TreeMap<Double, Material> result = DataMapper.getRoofFlatCladdingMaterialList(input_type_id);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of getRoofSlopeCladdingMaterialList method, of class DataMapper.
////     */
////    @Test
////    public void testGetRoofSlopeCladdingMaterialList() throws Exception {
////        System.out.println("getRoofSlopeCladdingMaterialList");
////        int input_type_id = 0;
////        List<Material> expResult = null;
////        List<Material> result = DataMapper.getRoofSlopeCladdingMaterialList(input_type_id);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of login method, of class DataMapper.
////     */
////    @Test
////    public void testLogin() throws Exception {
////        System.out.println("login");
////        String email = "";
////        String password = "";
////        Customer expResult = null;
////        Customer result = DataMapper.login(email, password);
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of createCustomer method, of class DataMapper.
////     */
////    @Test
////    public void testCreateCustomer() throws Exception {
////        System.out.println("createCustomer");
////        Customer c = null;
////        DataMapper.createCustomer(c);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////
////    /**
////     * Test of createRequest method, of class DataMapper.
////     */
////    @Test
////    public void testCreateRequest() {
////        System.out.println("createRequest");
////        Customer customer = null;
////        Request request = null;
////        Carport carport = null;
////        Shed shed = null;
////        DataMapper.createRequest(customer, request, carport, shed);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
////    
//
//    /**
//     * Test of getRoofFlatCladdingMaterialListJSP method, of class MaterialMapper.
//     */
    @Test
    public void testGetRoofFlatCladdingMaterialListJSP() throws Exception {
        System.out.println("getRoofFlatCladdingMaterialListJSP");
        int input_type_id = 2;
        int expResult = 2;
        int result = MaterialMapper.getRoofFlatCladdingMaterialListJSP(input_type_id).size();
        assertEquals(expResult, result);
       
    }
}
//
//    /**
//     * Test of getRoofFlatCladdingMaterialList method, of class MaterialMapper.
//     */
//    @Test
//    public void testGetRoofFlatCladdingMaterialList() throws Exception {
//        System.out.println("getRoofFlatCladdingMaterialList");
//        int input_type_id = 2;
//        TreeMap<Double, Material> expResult = null;
//        TreeMap<Double, Material> result = MaterialMapper.getRoofFlatCladdingMaterialList(input_type_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRoofSlopeCladdingMaterialList method, of class MaterialMapper.
//     */
//    @Test
//    public void testGetRoofSlopeCladdingMaterialList() throws Exception {
//        System.out.println("getRoofSlopeCladdingMaterialList");
//        int input_type_id = 0;
//        List<Material> expResult = null;
//        List<Material> result = MaterialMapper.getRoofSlopeCladdingMaterialList(input_type_id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//}
