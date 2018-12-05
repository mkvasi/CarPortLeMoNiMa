/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.Material;
import FunctionLayer.Roof;
import FunctionLayer.Shed;
import java.util.HashMap;
import java.util.TreeMap;
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
public class LineItemQtyGeneratorTest {

    
    LineItemQtyGenerator instance;
    TreeMap<Double, Material> treemap;
    Material material3000;
    Material material3600;
    Material material3900;
    Material material4200;
    Material material4500;
    Material material4800;
    Material material5100;
    Material material5400;
    Material material6000;
//Different roofs
    Roof roof0;
    Roof roof25;
    Roof roof45;
    Roof roofNull;
     

//Different sheds
    
    Shed shedNoLength;
    Shed shedNull;
   
//Different BOMs
    BillOfMaterial bill; 

  

    public LineItemQtyGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

      @Before
    public void setUp() {
        roof25 = new Roof(25); //In between min and max
        roof45 = new Roof(45);//Max slope
        roofNull = null;

        shedNoLength = new Shed(0.0, 0.0);
        shedNull = null;

        bill = new BillOfMaterial();
        instance = new LineItemQtyGenerator();
        material3000 = new Material(3000);
        material3600 = new Material(3600);
        material4200 = new Material(4200);
        material4500 = new Material(4500);
        material4800 = new Material(4800);
        material5100 = new Material(5100);
        material5400 = new Material(5400);
        material6000 = new Material(6000);
      
        treemap = new TreeMap();
        treemap.put(3000.0, material3000);
        treemap.put(3600.0, material3600);
        treemap.put(4200.0, material4200);
        treemap.put(4500.0, material4500);
        treemap.put(4800.0, material4800);
        treemap.put(5100.0, material5100);
        treemap.put(5400.0, material5400);
        treemap.put(6000.0, material6000);
        roof0 = new Roof(0);//Min slope
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of makeBillOfMaterial method, of class LineItemQtyGenerator.
     */
    @Test
    //Listens 
    public void testMakeBillOfMaterial() {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        HashMap<Integer, TreeMap<Double, Material>> boards = new HashMap();
        boards.put(6, treemap);
        boards.put(7, treemap);
        boards.put(13, treemap);
        boards.put(14, treemap);
        boards.put(10, treemap);
        boards.put(12, treemap);
        int expResult = 8;
        int result = instance.makeBillOfMaterial(carport, boards).getLineItems().size();
  
        assertEquals(expResult, result);
      
    }
        
     

    /**
     * Test of getQTYForRafter method, of class LineItemQtyGenerator.
     */
    @Test
    public void testGetQTYForRafterSmallCarport() {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material board = new Material(3000.0);
        int expResult = 5;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }
    @Test
    public void testGetQTYForRafterSmallCarportPitchedRoof25() {
        Carport carport = new Carport(2.4, 2.4, roof25, shedNull, bill);
        Material board = new Material(3000.0);
        int expResult = 8;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }
    @Test
    public void testGetQTYForRafterBigCarportPitchedRoof25() {
        Carport carport = new Carport(7.5, 7.8, roof25, shedNull, bill);
        Material board = new Material(4200.0);
        int expResult = 8;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQTYForRafterBigCarport() {
        Carport carport = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material board = new Material(4200.0);
        int expResult = 28;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }
    @Test
    public void testGetQTYForRafterNotChooseAbleValueForUserSmallCarport() {
        Carport carport = new Carport(2.5, 3.8, roof0, shedNull, bill);
        Material board = new Material(4200.0);
        int expResult = 5;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }
    @Test
    public void testGetQTYForRafterNotChooseAbleValueForUserBigCarport() {
        Carport carport = new Carport(7.9, 7.2, roof0, shedNull, bill);
        Material board = new Material(4200.0);
        int expResult = 28;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    /**
     * Test of getQTYForRem method, of class LineItemQtyGenerator.
     */
    @Test
    public void testGetQTYForRemBigCarport() {
        Carport carport = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material board = new Material(4200.0);
        int expResult = 4;
        int result = instance.getQTYForRem(carport, board);
        assertEquals(expResult, result);
      
    }
    @Test
    public void testGetQTYForRemSmallCarport() {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material board = new Material(3000.0);
        int expResult = 2;
        int result = instance.getQTYForRem(carport, board);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of getQtyOfPosts method, of class LineItemQtyGenerator.
     */
    @Test
    public void testGetQtyOfPostsBigCarport() {
        Carport carport = new Carport(7.5, 7.8, roof0, shedNull, bill);
    
        int expResult = 8;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);
 
    }
    @Test
    public void testGetQtyOfPostsSmallCarport() {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
    
        int expResult = 4;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);
 
    }
    @Test
    public void testGetQtyOfPostsSmallCarportPitchedRoof25() {
        Carport carport = new Carport(2.4, 2.4, roof25, shedNull, bill);
    
        int expResult = 4;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);
 
    }
    @Test
    public void testGetQtyOfPostsBigCarportPitchedRoof25() {
        Carport carport = new Carport(7.5, 2.4, roof25, shedNull, bill);
       int expResult = 8;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);
    }
    
 

//    /**
//     * Test of countEaves method, of class LineItemQtyGenerator.
//     */
//    @Test
//    public void testCountEaves() {
//        System.out.println("countEaves");
//        Carport carport = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        int expResult = 0;
//        int result = instance.countEaves(carport);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateRygstensTiles method, of class LineItemQtyGenerator.
//     */
//    @Test
//    public void testCalculateRygstensTiles() {
//        System.out.println("calculateRygstensTiles");
//        Carport carport = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        int expResult = 0;
//        int result = instance.calculateRygstensTiles(carport);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of returnMaterialForFarciaAndRainware method, of class
//     * LineItemQtyGenerator.
//     */
//    @Test
//    public void testReturnMaterialForFarciaAndRainware() {
//        System.out.println("returnMaterialForFarciaAndRainware");
//        Carport carport = null;
//        TreeMap<Double, Material> boards = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        Material expResult = null;
//        Material result = instance.returnMaterialForFarciaAndRainware(carport, boards);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateBoardLengthForFarciaAndRainware method, of class
//     * LineItemQtyGenerator.
//     */
//    @Test
//    public void testCalculateBoardLengthForFarciaAndRainware() {
//        System.out.println("calculateBoardLengthForFarciaAndRainware");
//        Carport carport = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        Double expResult = null;
//        Double result = instance.calculateBoardLengthForFarciaAndRainware(carport);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateHeightForRoof method, of class LineItemQtyGenerator.
//     */
//    @Test
//    public void testCalculateHeightForRoof() {
//        System.out.println("calculateHeightForRoof");
//        Carport carport = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        Double expResult = null;
//        Double result = instance.calculateHeightForRoof(carport);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateBoardsForGable method, of class LineItemQtyGenerator.
//     */
//    @Test
//    public void testCalculateBoardsForGable() {
//        System.out.println("calculateBoardsForGable");
//        Carport carport = null;
//        Material boards = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        Double expResult = null;
//        Double result = instance.calculateBoardsForGable(carport, boards);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateBattensForPitchedRoof method, of class
//     * LineItemQtyGenerator.
//     */
//    @Test
//    public void testCalculateBattensForPitchedRoof() {
//        System.out.println("calculateBattensForPitchedRoof");
//        Carport carport = null;
//        TreeMap<Double, Material> boards = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        int expResult = 0;
//        int result = instance.calculateBattensForPitchedRoof(carport, boards);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateTiles method, of class LineItemQtyGenerator.
//     */
//    @Test
//    public void testCalculateTiles() {
//        System.out.println("calculateTiles");
//        Carport carport = null;
//        LineItemQtyGenerator instance = new LineItemQtyGenerator();
//        int expResult = 0;
//        int result = instance.calculateTiles(carport);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
