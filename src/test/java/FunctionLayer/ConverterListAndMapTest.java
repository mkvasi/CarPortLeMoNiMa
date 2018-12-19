
package FunctionLayer;

import FunctionLayer.entity.Shed;
import FunctionLayer.entity.Roof;
import FunctionLayer.entity.Material;
import FunctionLayer.exceptions.ConverterMapException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterListAndMapTest {
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
    List<Material> smallMaterialList; 
    BillOfMaterial bill;
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
        roof25 = new Roof(25); //In between min and max
        roof45 = new Roof(45);//Max slope
        roofNull = null;

        shedNoLength = new Shed(0.0, 0.0);
        shedNull = null;

        bill = new BillOfMaterial();
        instance = new ConverterListAndMap();
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
        smallMaterialList = new ArrayList();
        smallMaterialList.add(new Material(0, "3000", 0, 0, 3000, 0, 0, true, 7, 0));
        smallMaterialList.add(new Material(0, "3000", 0, 0, 3000, 0, 0, true, 10, 0));
        smallMaterialList.add(new Material(0, "3600", 0, 0, 3600, 0, 0, true, 10, 0));
        smallMaterialList.add(new Material(0, "4200", 0, 0, 4200, 0, 0, true, 10, 0));
        smallMaterialList.add(new Material(0, "3000", 0, 0, 3000, 0, 0, true, 12, 0));
        smallMaterialList.add(new Material(0, "3000", 0, 0, 3000, 0, 0, true, 13, 0));
        smallMaterialList.add(new Material(0, "3000", 0, 0, 3000, 0, 0, true, 6, 0));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testListToHashMapSmallList() throws ConverterMapException {
        int  expResult = 5;
        int result = instance.ListToHashMap(smallMaterialList).size();
        assertEquals(expResult, result);       
    }
   
    @Test
    public void testListToHashMapLargeList() throws ConverterMapException {
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
    public void testListToHashMapGetSpecificMaterialNumber14() throws ConverterMapException {
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
    public void testListToHashMapGetSpecificMaterialNumber10() throws ConverterMapException {
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
    
    @Test(expected = ConverterMapException.class)
    public void testConverterExceptionListNull() throws ConverterMapException {
        instance.ListToHashMap(null); 
    }
}
        


    
