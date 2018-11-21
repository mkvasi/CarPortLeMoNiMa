//package FunctionLayerTest;
//
//
//import FunctionLayer.Carport;
//import FunctionLayer.CarportCalculator;
//import FunctionLayer.Material;
//import FunctionLayer.Roof;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.Test;
//import org.junit.Assert;
//import org.junit.Before;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author nr
// */
//public class CarportCalculatorTest {
//    
//    Carport carport;
//    CarportCalculator calc;
//    
//    Roof flatRoof = new Roof(false);
//    Roof pitchedRoof = new Roof(true);
//    Carport carport1 = new Carport(4, 2.5, flatRoof);
//    Carport carport2 = new Carport(5, 3.5, flatRoof);
//    Carport carport3 = new Carport(6, 3, flatRoof);
//    Carport carport4 = new Carport(6.5, 4.5, flatRoof);
//    Carport carport5 = new Carport(8.5, 4, flatRoof);
//    Carport carport6 = new Carport(7, 4.5, flatRoof);
//    Carport carportPitchedRoof = new Carport(5, 4, pitchedRoof);
//    
//    Material rafters = new Material(15, "Rafter", "stk.", "Tree", "Tree to hold eaves", 45, 90, 12);
//    Material brackets = new Material(16, "Bracket", "stk.", "Brackets", "Brackets for posts", 7, 20, 16);
//    Material fourPosts = new Material(17, "Post", "stk.", "Tree", "Posts for carport", 110, 350, 4);
//    Material sixPosts = new Material(17, "Post", "stk.", "Tree", "Posts for carport", 110, 350, 6);
//    
//    
//    
//    @Before public void setup(){
//        calc = new CarportCalculator();
//        Roof roof = new Roof(true);
//        carport = new Carport(5, 3, roof);
//    }
//    
//    @Test
//    public void testCountPostsForStandardCarport() {
//        int actualPosts = calc.countPostsForCarport(carport);
//        Assert.assertEquals(4,actualPosts);
//    }
//    
//    @Test
//    public void testCountPostsForCarport() {
//        int actualPosts = calc.countPostsForCarport(carport6);
//        Assert.assertEquals(6, actualPosts);
//    }
//    
//     @Test
//    public void testCountBracketsForCarport() {
//        int actualBrackets = calc.countBracketsForCarport(sixPosts);
//        Assert.assertEquals(24, actualBrackets);       
//    }
//    
//    @Test
//    public void testCountScrewsForBrackets() {
//        int actualScrews = calc.countScrewsForBrackets(brackets);
//        Assert.assertEquals(128, actualScrews);
//    }
//    @Test
//    public void testCountRaftersForRoof() {
//        int actualRafters = calc.countRaftersForRoof(carport2);
//        Assert.assertEquals(9, actualRafters);
//    }
//    
//    @Test
//    public void testScrewsForRaftersFlatRoof() {
//        int actualScrews = calc.countScrewsForRafters(carport3, rafters);
//        Assert.assertEquals(144, actualScrews);
//    }
//    
//    @Test
//    public void testRemForCarportWithPitchedRoof() {
//       int actualRems = calc.countRemForCarport(carportPitchedRoof);
//       Assert.assertEquals(4, actualRems);
//    }
//    
//    @Test
//    public void testRemForCarportWithFlatRoof() {
//       int actualRems = calc.countRemForCarport(carport2);
//       Assert.assertEquals(2, actualRems);
//    }
//    
//    @Test
//    public void testCalculateRoofDimensionsFlatRoof() {
//        calc.calculateRoofDimensions(carport1);
//        double actualRoofLength = carport1.getRoof().getLength();
//        double actualRoofWidth = carport1.getRoof().getWidth();
//        Assert.assertEquals(5.20, actualRoofWidth, actualRoofWidth);
//        Assert.assertEquals(2.70, actualRoofWidth, actualRoofWidth);
//
//    }
//}
