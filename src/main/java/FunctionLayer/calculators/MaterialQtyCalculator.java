/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

import FunctionLayer.Carport;
import FunctionLayer.Material;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author nr
 */
public class MaterialQtyCalculator {

    AllMaterialsCalculator calc = new AllMaterialsCalculator();

    public HashMap<String, Material> getDoneCarportWithMaterialQty(Carport carport, HashMap<Integer, TreeMap<Double, Material>> boards) throws MaterialException {
        //HashMap<String, Material> allMaterialsForThisCarport = calc.calculateAllMaterialTypes(carport, boards);
        HashMap<String, Material> allMaterialsForThisCarportWithLength = calc.calculateAllMaterialTypes(carport, boards);
      
        //Calculate qty of spær, by getting spær from allMaterialsForThisCarport
        allMaterialsForThisCarportWithLength.get("Spær").setQty(countAmountRaftersForRoofAndUniversalBracketsForRafter(carport));
        allMaterialsForThisCarportWithLength.get("Rem").setQty(calculateQtyOfRemForCarport(carport, allMaterialsForThisCarportWithLength.get("Rem")));
        allMaterialsForThisCarportWithLength.get("Stolper").setQty(countPostsForCarport(carport));

        

        Material understernSides = allMaterialsForThisCarportWithLength.get("Understern til siderne");
        understernSides.setQty(calculateQtyOfRemForCarport(carport, understernSides));
        
        Material understernFront = allMaterialsForThisCarportWithLength.get("Understern til for og bagende" );
        understernFront.setQty(calculateQtyOfRemForCarport(carport, understernFront));
        
        Material oversternSides = allMaterialsForThisCarportWithLength.get("Overstern til siderne");
        oversternSides.setQty(calculateQtyOfRemForCarport(carport, oversternSides));

        allMaterialsForThisCarportWithLength.get("Universalbeslag højre").setQty(countAmountRaftersForRoofAndUniversalBracketsForRafter(carport));
        allMaterialsForThisCarportWithLength.get("Universalbeslag venstre").setQty(countAmountRaftersForRoofAndUniversalBracketsForRafter(carport));

       

        return allMaterialsForThisCarportWithLength;
    }

//    // Total count of posts, for the carport. For each 3 meters, a new post will be added to each length (* 2)
//    public int countPostsForCarport(Carport carport) {
//        int meterPerPost = 3;
//        double posts = ((Math.ceil(carport.getLength() / meterPerPost) * 2));
//        return (int) posts;
//    }
//
//    //Using two carriage bolt pr. posts according to carport manual 
//    public int countCarriageBoltandSquareSlices(Material post) {
//        int amountOfCarriageBoltPerPost = 2;
//        int carriageBolts = post.getQty() * amountOfCarriageBoltPerPost;
//        return carriageBolts;
//    }
//
//    public int countAmountRaftersForRoofAndUniversalBracketsForRafter(Carport carport) {
//        if (!carport.getRoof().isPitchedRoof()) {
//            double spaceBetweenEachRafter = 0.55;
//            double rafterWidth = 0.02;
//            double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.
//
//            double rafters = Math.ceil(carport.getLength() / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter
//
//            return (int) rafters;
//        } else {
//            return 1;
//        }
//    }
//
//    public int countUniversalBracketsScrews(Material universalBracketsLeft) {
//
//        double amountOfUniversalBrackets = universalBracketsLeft.getQty() * 2; //We have to multiply to get the full amount of brackets
//
//        double screws = 9 * amountOfUniversalBrackets;  // According to the carport instructions we need 3 screws pr. surface (universal brackets have 3 surfaces), 
//        // which means 9 screws pr. universal brack
//
//        double packages = Math.ceil(screws / 250) + 1;//Each package contains 250 screws according to the manual and one package get added to the hulbånd 
//
//        return (int) packages;
//    }
//    //Skal have hjælp til denne metode, er der måske fejl i stykliste?
//
//    public int calculateQtyOfRemForCarport(Carport carport, Material board) {
//        int amountOfRem = 2;
//        if(board.getLength() < carport.getLength()){
//        return 4;     
//        }
//        return amountOfRem; // Amount of remme is set to 2 if the roof is flat
//    }
//
//    public void calculateRoofDimensions(Carport carport) {
//        double extraSpaceFlatRoof = 0.30;
//
//        double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 30 cm (* 2)
//        double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 30 cm (* 2)
//        carport.getRoof().setLength(carport.getLength() + extraLength);
//        carport.getRoof().setWidth(carport.getWidth() + extraWidth);
//
//    }
//
//    // SKAL LAVES OM
//    public int countEaves(Carport carport) {
//        calculateRoofDimensions(carport);
//        double eavesWidth = 1.0;
//        double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
//        // double eaveMetersForLength = Math.ceil(carport.getLength());              // Length of each eave piece.
//        return (int) countEaves;
//    }
//
//    //add tiles pr. m2, based on measure from stykliste
//    public int calculateTiles(Carport carport) {
//        calculateRoofDimensions(carport);
//        int tilesPrM2 = 11;
//        double roof = Math.ceil((carport.getRoof().getLength() * (carport.getRoof().getWidth()))) * tilesPrM2;
//
//        return (int) roof;
//    }
//
//    //stykslisten says 21 rygsten for 7,3 m, we calculate with 3 rygsten each meter
//    public int calculateRygstensTiles(Carport carport) {
//        int rygstenTilesPrMeterLength = 3;
//        double roofTiles = Math.ceil((carport.getRoof().getLength())) * rygstenTilesPrMeterLength;
//
//        return (int) roofTiles;
//    }

}
