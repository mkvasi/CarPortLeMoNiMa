/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Carport;
import FunctionLayer.LineItem;
import FunctionLayer.Material;
import FunctionLayer.Roof;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author leage
 */
public class AllMaterialsCalculator {

    int toMilimeters = 1000;

    //Using TreeMap instead of hashmap, because the TreeMap is a NavigableMap, which can be useful in this case, 
    //where we are trying to get the best match for a board, instead of looping through each element in a hashMap or List 
//    public HashMap<String, Material> calculateAllMaterialTypes(Carport carport, HashMap<Integer, TreeMap<Double, Material>> boards) {
//        HashMap<String, Material> allMaterialsToUseForThisCarport = new HashMap<>();
//        HashMap<String, Material> materialListFromDB = carport.getMaterialsToUseForThisCarport();
//
//        if (!carport.getRoof().isPitchedRoof()) {
//            allMaterialsToUseForThisCarport.put("Spær", getBoardForRafter(carport, boards.get(10)));
//
//        } else {
//            allMaterialsToUseForThisCarport.put("Spær", materialListFromDB.get("FÆDIGSKÅRET (BYG-SELV SPÆR SKAL SAMLES) 8 STK.1.0"));
//
//        }
//        allMaterialsToUseForThisCarport.put("Rem", getBoardForRem(carport, boards.get(10)));
//        allMaterialsToUseForThisCarport.put("Understern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
//        allMaterialsToUseForThisCarport.put("Understern til for og bagende", getBoardForUndersternFrontAndBack(carport, boards.get(6)));
//        allMaterialsToUseForThisCarport.put("Overstern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
//        // allBoardsForThisCarport.put("Overstern til for og bagende", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
//
//        allMaterialsToUseForThisCarport.put("Universalbeslag højre", materialListFromDB.get("UNIVERSAL 190 MM HØJRE190.0"));
//        allMaterialsToUseForThisCarport.put("Universalbeslag venstre", materialListFromDB.get("UNIVERSAL 190 MM VENSTRE190.0"));
//
//        allMaterialsToUseForThisCarport.put("Stolper", materialListFromDB.get("97X97 MM FYR STOLPE IMPR.3000.0"));
//
//        return allMaterialsToUseForThisCarport;
//    }
    public HashMap<String, Material> calculateAllMaterialTypes(Carport carport, BillOfMaterial billOfMaterial, HashMap<Integer, TreeMap<Double, Material>> boards) {
        HashMap<String, Material> allMaterialsToUseForThisCarport = new HashMap<>();
        HashMap<String, Material> materialListFromDB = ();

        if (!carport.getRoof().isPitchedRoof()) {
            billOfMaterial.addMaterialToBOM(new LineItem(0, "Spær", getBoardForRafter(carport, boards.get(10))));
//            allMaterialsToUseForThisCarport.put("Spær", getBoardForRafter(carport, boards.get(10)));

        } else {
            allMaterialsToUseForThisCarport.put("Spær", materialListFromDB.get("FÆDIGSKÅRET (BYG-SELV SPÆR SKAL SAMLES) 8 STK.1.0"));

        }
        allMaterialsToUseForThisCarport.put("Rem", getBoardForRem(carport, boards.get(10)));
        allMaterialsToUseForThisCarport.put("Understern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
        allMaterialsToUseForThisCarport.put("Understern til for og bagende", getBoardForUndersternFrontAndBack(carport, boards.get(6)));
        allMaterialsToUseForThisCarport.put("Overstern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
        // allBoardsForThisCarport.put("Overstern til for og bagende", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));

        allMaterialsToUseForThisCarport.put("Universalbeslag højre", materialListFromDB.get("UNIVERSAL 190 MM HØJRE190.0"));
        allMaterialsToUseForThisCarport.put("Universalbeslag venstre", materialListFromDB.get("UNIVERSAL 190 MM VENSTRE190.0"));

        allMaterialsToUseForThisCarport.put("Stolper", materialListFromDB.get("97X97 MM FYR STOLPE IMPR.3000.0"));

        return allMaterialsToUseForThisCarport;
    }

//Finding the best board length to use for spær, or returning the type of board that we need two of to get the correct length
    public Material boardCalculator(double carportMeasure ,Carport carport, TreeMap<Double, Material> boards) {
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        
        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;

        } else {
            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
            boardMatch = boards.ceilingEntry(carportMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
    }

    Material getBoardForRafter(Carport carport, TreeMap<Double, Material> boards) {

        double carportMeasure = carport.getWidth() * toMilimeters;              // To get the dimension in milimeters, so it's comparable to the materials in the database.

        return boardCalculator(carportMeasure, carport, boards);
//        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
//        if (boardMatch != null) {
//            Material board = boardMatch.getValue();
//            return board;
//
//        } else {
//            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
//            boardMatch = boards.ceilingEntry(carportMeasure);
//            Material board = boardMatch.getValue();
//            return board;
//        }
    }

    Material getBoardForRem(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getLength() * toMilimeters;             // To get the dimension in milimeters, so it's comparable to the materials in the database.

        return boardCalculator(carportMeasure, carport, boards);
//        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
//        if (boardMatch != null) {
//            Material board = boardMatch.getValue();
//            return board;
//
//        } else {
//            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
//            boardMatch = boards.ceilingEntry(carportMeasure);
//            Material board = boardMatch.getValue();
//            return board;
//        }
    }

    private Material getBoardLengthForOversternAndUndersternSides(Carport carport, TreeMap<Double, Material> boards) {

        int extraSpace = 50;                                                    // 2.5 Cencimeter extra space for each side.
        double carportMeasure = (carport.getLength() * toMilimeters) + extraSpace;      // To get the dimension in milimeters, so it's comparable to the materials in the database.

        return boardCalculator(carportMeasure, carport, boards);
//        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
//        if (boardMatch != null) {
//            Material board = boardMatch.getValue();
//            return board;
//
//        } else {
//            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
//            boardMatch = boards.ceilingEntry(carportMeasure);
//            Material board = boardMatch.getValue();
//            return board;
//        }
    }

    private Material getBoardForUndersternFrontAndBack(Carport carport, TreeMap<Double, Material> boards) {

        int extraSpace = 50;                                                    // Extra space in each side for the boards.
        double carportMeasure = (carport.getWidth() * toMilimeters) + extraSpace;       // To get the dimension in milimeters, so it's comparable to the materials in the database.

        return boardCalculator(carportMeasure, carport, boards);
//        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
//        if (boardMatch != null) {
//            Material board = boardMatch.getValue();
//            return board;
//
//        } else {
//            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
//            boardMatch = boards.ceilingEntry(carportMeasure);
//            Material board = boardMatch.getValue();
//            return board;
//        }
    }
//    public static void main(String[] args) {
//        AllMaterialsCalculator b = new AllMaterialsCalculator();
//        Carport c = new Carport(8.0, 8.0, new Roof(false)); 
//        TreeMap<Double, Material> boards = new TreeMap<>();
//        boards.put(3000.0, new Material("3000", 3000.0));
//        boards.put(3600.0, new Material("3600", 3600.0));
//        boards.put(3900.0, new Material("3900", 3900.0));
//        boards.put(4200.0, new Material("4200", 4200.0));
//        boards.put(4500.0, new Material("3000", 3000.0));
//        boards.put(4800.0, new Material("3000", 3000.0));
// 
//        //b.getBoardForUndersternFrontAndBack(c, boards);
//        
//        System.out.println(b.getBoardForUndersternFrontAndBack(c, boards));
//    }

    // Total count of posts, for the carport. For each 3 meters, a new post will be added to each length (* 2)
    public int countPostsForCarport(Carport carport) {
        int meterPerPost = 3;
        double posts = ((Math.ceil(carport.getLength() / meterPerPost) * 2));
        return (int) posts;
    }

    //Using two carriage bolt pr. posts according to carport manual 
    public int countCarriageBoltandSquareSlices(Material post) {
        int amountOfCarriageBoltPerPost = 2;
        int carriageBolts = post.getQty() * amountOfCarriageBoltPerPost;
        return carriageBolts;
    }

    public int countAmountRaftersForRoofAndUniversalBracketsForRafter(Carport carport) {
        if (!carport.getRoof().isPitchedRoof()) {
            double spaceBetweenEachRafter = 0.55;
            double rafterWidth = 0.02;
            double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.

            double rafters = Math.ceil(carport.getLength() / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter

            return (int) rafters;
        } else {
            return 1;
        }
    }

    public int countUniversalBracketsScrews(Material universalBracketsLeft) {

        double amountOfUniversalBrackets = universalBracketsLeft.getQty() * 2; //We have to multiply to get the full amount of brackets

        double screws = 9 * amountOfUniversalBrackets;  // According to the carport instructions we need 3 screws pr. surface (universal brackets have 3 surfaces), 
        // which means 9 screws pr. universal brack

        double packages = Math.ceil(screws / 250) + 1;//Each package contains 250 screws according to the manual and one package get added to the hulbånd 

        return (int) packages;
    }
    //Skal have hjælp til denne metode, er der måske fejl i stykliste?

    public int calculateQtyOfRemForCarport(Carport carport, Material board) {
        int amountOfRem = 2;
        if (board.getLength() < carport.getLength()) {
            return 4;
        }
        return amountOfRem; // Amount of remme is set to 2 if the roof is flat
    }

    public void calculateRoofDimensions(Carport carport) {
        double extraSpaceFlatRoof = 0.30;

        double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 30 cm (* 2)
        double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 30 cm (* 2)
        carport.getRoof().setLength(carport.getLength() + extraLength);
        carport.getRoof().setWidth(carport.getWidth() + extraWidth);

    }

    // SKAL LAVES OM
    public int countEaves(Carport carport) {
        calculateRoofDimensions(carport);
        double eavesWidth = 1.0;
        double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
        // double eaveMetersForLength = Math.ceil(carport.getLength());              // Length of each eave piece.
        return (int) countEaves;
    }

    //add tiles pr. m2, based on measure from stykliste
    public int calculateTiles(Carport carport) {
        calculateRoofDimensions(carport);
        int tilesPrM2 = 11;
        double roof = Math.ceil((carport.getRoof().getLength() * (carport.getRoof().getWidth()))) * tilesPrM2;

        return (int) roof;
    }

    //stykslisten says 21 rygsten for 7,3 m, we calculate with 3 rygsten each meter
    public int calculateRygstensTiles(Carport carport) {
        int rygstenTilesPrMeterLength = 3;
        double roofTiles = Math.ceil((carport.getRoof().getLength())) * rygstenTilesPrMeterLength;

        return (int) roofTiles;
    }
}
