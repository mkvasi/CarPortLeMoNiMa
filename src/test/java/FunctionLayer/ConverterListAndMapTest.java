/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class ConverterListAndMapTest {
    ConverterListAndMap instance; 
    
    public ConverterListAndMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    instance = new ConverterListAndMap();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ListToHashMap method, of class ConverterListAndMap.
     */

    @Test
    public void testListToHashMapSmallList() {
        List<Material> listOfMaterials = new ArrayList();
        listOfMaterials.add(new Material(7));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        
        
        
        int  expResult = 2;
        int result = instance.ListToHashMap(listOfMaterials).size();
        assertEquals(expResult, result);

       
    }
   
    @Test
    public void testListToHashMapLargeList() {
        List<Material> listOfMaterials = new ArrayList();
        listOfMaterials.add(new Material(7));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(13));
        listOfMaterials.add(new Material(12));
        listOfMaterials.add(new Material(6));
        listOfMaterials.add(new Material(14));
        listOfMaterials.add(new Material(14));
        listOfMaterials.add(new Material(14));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        listOfMaterials.add(new Material(10));
        
        
        
        int  expResult = 6;
        int result = instance.ListToHashMap(listOfMaterials).size();
        assertEquals(expResult, result);
    }

    @Test
    public void testListToHashMapGetSpecificMaterialNumber14() {
        List<Material> listOfMaterials = new ArrayList();
        listOfMaterials.add(new Material(7, 3600.0));
        listOfMaterials.add(new Material(10, 3600.0));
        listOfMaterials.add(new Material(10, 4200.0));
        listOfMaterials.add(new Material(10, 3000.0 ));
        listOfMaterials.add(new Material(10, 4800.0));
        listOfMaterials.add(new Material(13, 10.0));
        listOfMaterials.add(new Material(12, 10.0));
        listOfMaterials.add(new Material(6, 3600.0 ));
        listOfMaterials.add(new Material(14, 3000.0));
        listOfMaterials.add(new Material(14, 3600.0));
        listOfMaterials.add(new Material(14, 4200.0));
       
        
        
        
        Material expResult = listOfMaterials.get(8);
        Material result = instance.ListToHashMap(listOfMaterials).get(14).get(3000.0);
        assertEquals(expResult, result);
    }

    @Test
    public void testListToHashMapGetSpecificMaterialNumber10() {
        List<Material> listOfMaterials = new ArrayList();
        listOfMaterials.add(new Material(7, 3600.0));
        listOfMaterials.add(new Material(10, 3600.0));
        listOfMaterials.add(new Material(10, 4200.0));
        listOfMaterials.add(new Material(10, 3000.0 ));
        listOfMaterials.add(new Material(10, 4800.0));
        listOfMaterials.add(new Material(13, 10.0));
        listOfMaterials.add(new Material(12, 10.0));
        listOfMaterials.add(new Material(6, 3600.0 ));
        listOfMaterials.add(new Material(14, 3000.0));
        listOfMaterials.add(new Material(14, 3600.0));
        listOfMaterials.add(new Material(14, 4200.0));
       
        
        
        
        Material expResult = listOfMaterials.get(1);
        Material result = instance.ListToHashMap(listOfMaterials).get(10).get(3600.0);
        assertEquals(expResult, result);
    }
    
}
