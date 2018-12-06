///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package DBAccess;
//
//import FunctionLayer.Customer;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author leage
// */
//public class UserMapperIT {
//      private static Connection testConnection;
//    private static String USER = "connect";
//    private static String USERPW = "connect";
//    private static String DBNAME = "fog";
//    private static String HOST = "188.166.86.13";
//    public UserMapperIT() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    @Before
//   
//         public void setUp() {
//        try {
//            // awoid making a new connection for each test
//            if ( testConnection == null ) {
//                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
//                Class.forName( "com.mysql.jdbc.Driver" );
//
//                testConnection = DriverManager.getConnection( url, USER, USERPW );
//                // Make mappers use test 
//                DBConnector.setConnection( testConnection );
//            }
//       
//
//        } catch ( ClassNotFoundException | SQLException ex ) {
//            testConnection = null;
//            System.out.println( "Could not open connection to database: " + ex.getMessage() );
//        }
//    }    
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//     
//    
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of createCustomer method, of class UserMapper.
//     */
//    @Test
//    public void testCreateCustomer() throws Exception {
//        System.out.println("createCustomer");
//        Customer c = null;
//        UserMapper.createCustomer(c);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//    /**
//     * Test of login method, of class UserMapper.
//     */
//    @Test
//    public void testLogin() throws Exception {
//        System.out.println("login");
//        String email = "";
//        String password = "";
//        Customer expResult = null;
//        Customer result = UserMapper.login(email, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//}
