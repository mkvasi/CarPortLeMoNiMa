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
import FunctionLayer.exceptions.CalculatorException;
import java.util.ArrayList;
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
    Shed shedSmall;

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
        shedSmall = new Shed(2.4, 2.4);
        shedNull = null;

        bill = new BillOfMaterial();
        instance = new LineItemQtyGenerator();
        material3000 = new Material(0, "3000", 0, 0, 3000, 0, 0, true, 0, 0);
        material3600 = new Material(0, "3600", 0, 0, 3600, 0, 0, true, 0, 0);
        material4200 = new Material(0, "4200", 0, 0, 4200, 0, 0, true, 0, 0);
        material4500 = new Material(0, "4500", 0, 0, 4500, 0, 0, true, 0, 0);
        material4800 = new Material(0, "4800", 0, 0, 4800, 0, 0, true, 0, 0);
        material5100 = new Material(0, "5100", 0, 0, 5100, 0, 0, true, 0, 0);
        material5400 = new Material(0, "5400", 0, 0, 5400, 0, 0, true, 0, 0);
        material6000 = new Material(0, "6000", 0, 0, 6000, 0, 0, true, 0, 0);

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
    public void testMakeBillOfMaterial() throws CalculatorException {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        HashMap<Integer, TreeMap<Double, Material>> boards = new HashMap();
        boards.put(6, treemap);
        boards.put(7, treemap);
        boards.put(13, treemap);
        boards.put(14, treemap);
        boards.put(10, treemap);
        boards.put(12, treemap);
        boards.put(4, treemap);
        int expResult = 11;
        int result = instance.makeBillOfMaterial(carport, boards).getLineItems().size();

        assertEquals(expResult, result);

    }

    /**
     * Test of getQTYForRafter method, of class LineItemQtyGenerator.
     *
     * @throws FunctionLayer.exceptions.CalculatorException
     */
    @Test
    public void testGetQTYForRafterSmallCarport() throws CalculatorException {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material board = material3000;
        int expResult = 5;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQTYForRafterSmallCarportPitchedRoof25() throws CalculatorException {
        Carport carport = new Carport(2.4, 2.4, roof25, shedNull, bill);
        Material board = material4200;
        int expResult = 8;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQTYForRafterBigCarportPitchedRoof25() throws CalculatorException {
        Carport carport = new Carport(7.5, 7.8, roof25, shedNull, bill);
        Material board = material4200;
        int expResult = 8;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQTYForRafterBigCarport() throws CalculatorException {
        Carport carport = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material board = material4200;
        int expResult = 28;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQTYForRafterNotChooseAbleValueForUserSmallCarport() throws CalculatorException {
        Carport carport = new Carport(2.5, 3.8, roof0, shedNull, bill);
        Material board = material4200;
        int expResult = 5;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQTYForRafterNotChooseAbleValueForUserBigCarport() throws CalculatorException {
        Carport carport = new Carport(7.9, 7.2, roof0, shedNull, bill);
        Material board = material4200;
        int expResult = 28;
        int result = instance.getQTYForRafter(carport, board);
        assertEquals(expResult, result);

    }

    /**
     * Test of getQTYForRem method, of class LineItemQtyGenerator.
     */
    @Test
    public void testGetQTYForRemBigCarport() throws CalculatorException {
        Carport carport = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material board = material4200;
        int expResult = 4;
        int result = instance.getQTYForRem(carport, board);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQTYForRemSmallCarport() throws CalculatorException {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material board = material3000;
        int expResult = 2;
        int result = instance.getQTYForRem(carport, board);
        assertEquals(expResult, result);

    }


    /**
     * Test of getQtyOfPosts method, of class LineItemQtyGenerator.
     *
     * @throws FunctionLayer.exceptions.CalculatorException
     */
    @Test
    public void testGetQtyOfPostsBigCarport() throws CalculatorException {
        Carport carport = new Carport(7.5, 7.8, roof0, shedNull, bill);

        int expResult = 8;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQtyOfPostsSmallCarport() throws CalculatorException {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);

        int expResult = 4;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQtyOfPostsSmallCarportPitchedRoof25() throws CalculatorException {
        Carport carport = new Carport(2.4, 2.4, roof25, shedNull, bill);

        int expResult = 4;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetQtyOfPostsBigCarportPitchedRoof25() throws CalculatorException {
        Carport carport = new Carport(7.5, 2.4, roof25, shedNull, bill);
        int expResult = 8;
        int result = instance.getQtyOfPosts(carport);
        assertEquals(expResult, result);

    }

    @Test(expected = CalculatorException.class)
    public void testCalculatorExceptionAllObjectsNull() throws CalculatorException {
        Carport carport = null;
        Material board = null;

        instance.getQTYForRem(carport, board);
        instance.getQTYForRafter(carport, board);
        instance.getQtyOfPosts(carport);

    }

    @Test(expected = CalculatorException.class)
    public void testCalculatorExceptionCarportNull() throws CalculatorException {
        Carport carport = null;
        Material board = material3000;

        instance.getQTYForRem(carport, board);
        instance.getQTYForRafter(carport, board);

    }

    @Test(expected = CalculatorException.class)
    public void testCalculatorExceptionMaterialNull() throws CalculatorException {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material board = null;

        instance.getQTYForRem(carport, board);
        instance.getQTYForRafter(carport, board);

    }


    /**
     * Test of getUniversalBracketsQtyForOneSide method, of class LineItemQtyGenerator.
     */
 

    /**
     * Test of getQtyOfBræddebolt method, of class LineItemQtyGenerator.
     */
    @Test
    public void testGetQtyOfBræddeboltSmallCarport() throws Exception {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
  
        int expResult = 8;
        int result = instance.getQtyOfBræddebolt(carport);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testGetQtyOfBræddeboltBigCarport() throws Exception {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
  
        int expResult = 8;
        int result = instance.getQtyOfBræddebolt(carport);
        assertEquals(expResult, result);
    }
    
     @Test
    public void testGetQtyOfBræddeboltSmallCarportSmallShed() throws Exception {
        Carport carport = new Carport(2.4, 2.4, roof0, shedSmall, bill);
  
        int expResult = 20;
        int result = instance.getQtyOfBræddebolt(carport);
        assertEquals(expResult, result);
        
    }

   

}
