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
import FunctionLayer.Shed;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author leage
 */
public class AllMaterialsCalculator {

    int toMilimeters = 1000;

    public BillOfMaterial calculateMaterials(Carport carport, BillOfMaterial billOfMaterial, HashMap<Integer, TreeMap<Double, Material>> boards) {
        
        //Beregninger til fladt tag!
        if (carport.getRoof().getCelsiusForSlope() == 0) {
            Material board;
            
            //Spær
            board = getBoardForRafter(carport, boards.get(10));
            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRaftersForRoofAndUniversalBracketsForRafter(carport), "Spær", board));

            //Rem
            board = getBoardForRem(carport, boards.get(10));
            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRem(carport, board), "Rem", board));

            //Understern til siderne
            board = getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRem(carport, board), "Understern til siderne", board));

            //Understern til for og bagende
            board = getBoardForUndersternFrontAndBack(carport, boards.get(6));
            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRaftersForRoofAndUniversalBracketsForRafter(carport), "Understern til for og bagende", board));

            //Overstern til siderne
            board = getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRem(carport, board), "Overstern til siderne", board));

            //Overstern til for og bagende
            board = getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRaftersForRoofAndUniversalBracketsForRafter(carport), "Overstern til for og bagende", board));
//
//            //Universalbeslag højre
//            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRaftersForRoofAndUniversalBracketsForRafter(carport), "Universalbeslag højre", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6))));
//
//            //Universalbeslag venstre
//            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfRaftersForRoofAndUniversalBracketsForRafter(carport), "Universalbeslag venstre", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6))));
//
//            //Stolper
//            billOfMaterial.addMaterialToBOM(new LineItem(calculateQtyOfPosts(carport), "Stolper", getBoardLengthForOversternAndUndersternSides(carport, boards.get(7))));
//
//            //Skruer
//            billOfMaterial.addMaterialToBOM(new LineItem(0, "Skruer", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6))));
        }
        return billOfMaterial;
    }

    //******************************************Board Calculation method***********************************************
    //Finding the best board length to use for spær, or returning the type of board that we need two of to get the correct length
    public Material boardCalculator(double carportMeasure, Carport carport, TreeMap<Double, Material> boards) {
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);

        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;
        } else {
            carportMeasure = carportMeasure / 2; // Results in having two boards to fill out the carport length
            boardMatch = boards.ceilingEntry(carportMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
    }

    //******************************************Get correct board methods***********************************************
    public Material getBoardForRafter(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getWidth() * toMilimeters; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    public Material getBoardForRem(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getLength() * toMilimeters; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    public Material getBoardLengthForOversternAndUndersternSides(Carport carport, TreeMap<Double, Material> boards) {
        int extraSpace = 50; // 2.5 Cencimeter extra space for each side.
        double carportMeasure = (carport.getLength() * toMilimeters) + extraSpace; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    public Material getBoardForUndersternFrontAndBack(Carport carport, TreeMap<Double, Material> boards) {
        int extraSpace = 50; // Extra space in each side for the boards.
        double carportMeasure = (carport.getWidth() * toMilimeters) + extraSpace; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }


    public int countAmountRaftersForRoofAndUniversalBracketsForRafter(Carport carport, TreeMap<Double, Material> boards) {
//        if (!carport.getRoof().isPitchedRoof()) {

        if (carport.getRoof().getCelsiusForSlope() == 0) {
            double spaceBetweenEachRafter = 0.55;
            double rafterWidth = boards.firstEntry().getValue().getWidth();
//            double rafterWidth = 0.02;
            double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.
            double rafters = Math.ceil(carport.getLength() / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter
            return (int) rafters;
        } else {
            return 1;
        }
    }
    
    //Skal have hjælp til denne metode, er der måske fejl i stykliste?
    public int calculateQtyOfRemForCarport(Carport carport, Material boards) {
        int amountOfRem = 2;
        if (boards.getLength() < carport.getLength()) {
            return 4;
        }
        return amountOfRem; //Amount of remme is set to 2 if the roof is flat
    }
    
        // Total count of posts, for the carport. For each 3 meters, a new post will be added to each length (* 2)
    public int calculateQtyOfPosts(Carport carport) {
        int meterPerPost = 3;
        double posts = ((Math.ceil(carport.getLength() / meterPerPost) * 2));
        return (int) posts;
    }
    

//    //Using two carriage bolt pr. posts according to carport manual 
//    public int countCarriageBoltandSquareSlices(Material post) {
//        int amountOfCarriageBoltPerPost = 2;
//        int carriageBolts = post.getQty() * amountOfCarriageBoltPerPost;
//        return carriageBolts;
//    }
//
//    public int countUniversalBracketsScrews(Material universalBracketsLeft) {
//        double amountOfUniversalBrackets = universalBracketsLeft.getQty() * 2; //We have to multiply to get the full amount of brackets
//        double screws = 9 * amountOfUniversalBrackets;  // According to the carport instructions we need 3 screws pr. surface (universal brackets have 3 surfaces), 
//        // which means 9 screws pr. universal brack
//        double packages = Math.ceil(screws / 250) + 1;//Each package contains 250 screws according to the manual and one package get added to the hulbånd 
//        return (int) packages;
//    }

    // SKAL LAVES OM
    public int countEaves(Carport carport) {
        carport.getRoof().calculateRoofDimensions(carport);
        double eavesWidth = 1.0;
        double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
        return (int) countEaves;
    }
    

    //stykslisten says 21 rygsten for 7,3 m, we calculate with 3 rygsten each meter
    public int calculateRygstensTiles(Carport carport) {
        int rygstenTilesPrMeterLength = 3;
        double roofTiles = Math.ceil((carport.getRoof().getLength())) * rygstenTilesPrMeterLength;
        return (int) roofTiles;
    }

    public Material returnMaterialForFarciaAndRainware(Carport carport, TreeMap<Double, Material> boards) {
        double boardLengthHypotenuse = calculateBoardLengthForFarciaAndRainware(carport); // Kalder metode for at få længden på vinskederne (hypotenusen)
        return boardCalculator(boardLengthHypotenuse, carport, boards);  // Kalder boardCalculator som finder det bræt som matcher længden bedst.
    }

    //METODER NEDENFOR BRUGES KUN TIL TAG MED HÆLDNING!
    //Beregner vindskedernes(Facia) længde, skal modtage boardtypen(25x150 mm trykimp. bræt), og carporten som parameter.
    //Kan også beregne længden på vandbrædderne(RainWare)(19x100mm tryk imp. bræt)
    public Double calculateBoardLengthForFarciaAndRainware(Carport carport) {
        double hosliggendeKatete = (carport.getRoof().getWidth() / 2);          // Tagbredden divideres med 2 for at finde midten af gavlen
        int roofAngle = carport.getRoof().getCelsiusForSlope();                 // roofAngle holder hældningen på taget
        double boardLengthHypotenuse = (hosliggendeKatete) / (Math.cos(Math.toRadians(roofAngle))); //  Hypotenusen isoleres i cosinus formel for retvinklet trekanter, for at finde længden på vindskederne

        return boardLengthHypotenuse;
    }

    public Double calculateHeightForRoof(Carport carport) {
        double boardLengthHypotenuse = calculateBoardLengthForFarciaAndRainware(carport); // Kalder metode for at få længden på vinskederne (hypotenusen)
        double height = (Math.sin(carport.getRoof().getWidth()) * boardLengthHypotenuse); // 

        return height;
    }
    
    // Den maksimale højde på taget, og den halve bredde på gavl, bruges til at regne antal brædder.
    public Double calculateBoardsForGable(Carport carport, Material boards) {
        double halfGable = (carport.getRoof().getWidth() / 2);                  // Finder bredden på en halv gavl
        double countBoards = (halfGable * toMilimeters  / boards.getWidth() * 2);// Retunere hvor mange brædder der skal bruges til gavlen. Der ganges med to fordi det både gælder for og bag
        
       return countBoards;
    }
    
    // Regner antal lægter ud som skal ligge på begge sidder af skråtaget
    public int calculateBattensForPitchedRoof(Carport carport, TreeMap<Double, Material> boards) {
        int spaceBetweenEachBatten = 300;
        double battenWidth = boards.firstEntry().getValue().getWidth();
        double totalMeasureBetweenEachBatten = spaceBetweenEachBatten + battenWidth;
        
        double hypotenuseLength = calculateBoardLengthForFarciaAndRainware(carport);
        double countBattens = (Math.ceil(hypotenuseLength * 1000 / totalMeasureBetweenEachBatten * 2)); // Længden af vindskeden bruges til at finde ud af antallet af lægter pr. side af skråtaget. For at dække begge sider ganges der med to. Math.ceil() bruges til at runde op. 
        
        return (int) countBattens;
    }
        
    //add tiles pr. m2, based on measure from stykliste
    public int calculateTiles(Carport carport) {
        carport.getRoof().calculateRoofDimensions(carport);
        int tilesPrM2 = 11;
        double roof = Math.ceil((carport.getRoof().getLength() * (carport.getRoof().getWidth()))) * tilesPrM2;

        return (int) roof;
    }
        

//    
//    public BillOfMaterial calculateMaterialsForRoofFlat(Carport carport, BillOfMaterial billOfMaterial, HashMap<Integer, TreeMap<Double, Material>> boards){
//        return billOfMaterial;
//    }
//    
//    public BillOfMaterial calculateMaterialsForRoofSlope(Carport carport, BillOfMaterial billOfMaterial, HashMap<Integer, TreeMap<Double, Material>> boards){
//        return billOfMaterial;
//    }
//    
//    public BillOfMaterial calculateMaterialsForShed(Carport carport, BillOfMaterial billOfMaterial, HashMap<Integer, TreeMap<Double, Material>> boards){
//        return billOfMaterial;
//    }
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
//    public BillOfMaterial calculateAllMaterialTypes(Carport carport, BillOfMaterial billOfMaterial, HashMap<Integer, TreeMap<Double, Material>> boards) {
////        HashMap<String, Material> allMaterialsToUseForThisCarport = new HashMap<>();
////        HashMap<String, Material> materialListFromDB = ();
//
////        if (!carport.getRoof().isPitchedRoof()) {
////            billOfMaterial.addMaterialToBOM(new LineItem(0, "Spær", getBoardForRafter(carport, boards.get(10))));
//////            allMaterialsToUseForThisCarport.put("Spær", getBoardForRafter(carport, boards.get(10)));
////
////        } else {
////            billOfMaterial.addMaterialToBOM(materialQTY);
////            allMaterialsToUseForThisCarport.put("Spær", materialListFromDB.get("FÆDIGSKÅRET (BYG-SELV SPÆR SKAL SAMLES) 8 STK.1.0"));
////
////        }
////        if (carport.getRoof().getCelsiusForSlope() == 0) {
////            billOfMaterial.addMaterialToBOM(new LineItem(0, "Spær", getBoardForRafter(carport, boards.get(10))));
//////            allMaterialsToUseForThisCarport.put("Spær", getBoardForRafter(carport, boards.get(10)));
////
////        } else {
////            billOfMaterial.addMaterialToBOM(new LineItem(0, "Spær", getBoardForRafter(carport, boards.get(10))));
////            //allMaterialsToUseForThisCarport.put("Spær", materialListFromDB.get("FÆDIGSKÅRET (BYG-SELV SPÆR SKAL SAMLES) 8 STK.1.0"));
////
////        }
//        billOfMaterial.addMaterialToBOM(new LineItem(0, "Rem", getBoardForRem(carport, boards.get(10))));
//        billOfMaterial.addMaterialToBOM(new LineItem(0, "Understern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6))));
//        billOfMaterial.addMaterialToBOM(new LineItem(0, "Understern til for og bagende", getBoardForUndersternFrontAndBack(carport, boards.get(6))));
//        billOfMaterial.addMaterialToBOM(new LineItem(0, "Overstern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6))));
////        allMaterialsToUseForThisCarport.put("Rem", getBoardForRem(carport, boards.get(10)));
////        allMaterialsToUseForThisCarport.put("Understern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
////        allMaterialsToUseForThisCarport.put("Understern til for og bagende", getBoardForUndersternFrontAndBack(carport, boards.get(6)));
////        allMaterialsToUseForThisCarport.put("Overstern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
//        // allBoardsForThisCarport.put("Overstern til for og bagende", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
//
//        allMaterialsToUseForThisCarport.put("Universalbeslag højre", materialListFromDB.get("UNIVERSAL 190 MM HØJRE190.0"));
//        allMaterialsToUseForThisCarport.put("Universalbeslag venstre", materialListFromDB.get("UNIVERSAL 190 MM VENSTRE190.0"));
//
//        allMaterialsToUseForThisCarport.put("Stolper", materialListFromDB.get("97X97 MM FYR STOLPE IMPR.3000.0"));
//
//        return billOfMaterial;
//    }
//Finding the best board length to use for spær, or returning the type of board that we need two of to get the correct length
//    public Material boardCalculator(double carportMeasure ,Carport carport, TreeMap<Double, Material> boards) {
//        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
//        
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
//    }
//    public Material getBoardForRafter(Carport carport, TreeMap<Double, Material> boards) {
//
//        double carportMeasure = carport.getWidth() * toMilimeters;              // To get the dimension in milimeters, so it's comparable to the materials in the database.
//
//        return boardCalculator(carportMeasure, carport, boards);
////        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
////        if (boardMatch != null) {
////            Material board = boardMatch.getValue();
////            return board;
////
////        } else {
////            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
////            boardMatch = boards.ceilingEntry(carportMeasure);
////            Material board = boardMatch.getValue();
////            return board;
////        }
//    }
//    Material getBoardForRem(Carport carport, TreeMap<Double, Material> boards) {
//        double carportMeasure = carport.getLength() * toMilimeters;             // To get the dimension in milimeters, so it's comparable to the materials in the database.
//
//        return boardCalculator(carportMeasure, carport, boards);
////        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
////        if (boardMatch != null) {
////            Material board = boardMatch.getValue();
////            return board;
////
////        } else {
////            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
////            boardMatch = boards.ceilingEntry(carportMeasure);
////            Material board = boardMatch.getValue();
////            return board;
////        }
//    }
//    private Material getBoardLengthForOversternAndUndersternSides(Carport carport, TreeMap<Double, Material> boards) {
//
//        int extraSpace = 50;                                                    // 2.5 Cencimeter extra space for each side.
//        double carportMeasure = (carport.getLength() * toMilimeters) + extraSpace;      // To get the dimension in milimeters, so it's comparable to the materials in the database.
//
//        return boardCalculator(carportMeasure, carport, boards);
////        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
////        if (boardMatch != null) {
////            Material board = boardMatch.getValue();
////            return board;
////
////        } else {
////            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
////            boardMatch = boards.ceilingEntry(carportMeasure);
////            Material board = boardMatch.getValue();
////            return board;
////        }
//    }
//    private Material getBoardForUndersternFrontAndBack(Carport carport, TreeMap<Double, Material> boards) {
//
//        int extraSpace = 50;                                                    // Extra space in each side for the boards.
//        double carportMeasure = (carport.getWidth() * toMilimeters) + extraSpace;       // To get the dimension in milimeters, so it's comparable to the materials in the database.
//
//        return boardCalculator(carportMeasure, carport, boards);
////        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
////        if (boardMatch != null) {
////            Material board = boardMatch.getValue();
////            return board;
////
////        } else {
////            carportMeasure = carportMeasure / 2;                                // Results in having two boards to fill out the carport length
////            boardMatch = boards.ceilingEntry(carportMeasure);
////            Material board = boardMatch.getValue();
////            return board;
////        }
//    }
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
////        if (!carport.getRoof().isPitchedRoof()) {
//        if (carport.getRoof().getCelsiusForSlope() == 0) {
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
//        if (board.getLength() < carport.getLength()) {
//            return 4;
//        }
//        return amountOfRem; // Amount of remme is set to 2 if the roof is flat
//    }
//
////    public void calculateRoofDimensions(Carport carport) {
////        double extraSpaceFlatRoof = 0.30;
////
////        double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 30 cm (* 2)
////        double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 30 cm (* 2)
////        carport.getRoof().setLength(carport.getLength() + extraLength);
////        carport.getRoof().setWidth(carport.getWidth() + extraWidth);
////
////    }
//    // SKAL LAVES OM
//    public int countEaves(Carport carport) {
//        //calculateRoofDimensions(carport);
//        double eavesWidth = 1.0;
//        double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
//        // double eaveMetersForLength = Math.ceil(carport.getLength());              // Length of each eave piece.
//        return (int) countEaves;
//    }
//
//    //add tiles pr. m2, based on measure from stykliste
//    public int calculateTiles(Carport carport) {
//        //calculateRoofDimensions(carport);
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
//
//    //ANVENDES KUN TIL TAG MED HÆLDNING!
//    //Beregner vindskedernes(Facia) længde, skal modtage boardtypen(25x150 mm trykimp. bræt), og carporten som parameter.
//    //Kan også beregne længden på vandbrædderne(RainWare)(19x100mm tryk imp. bræt)
//    //QTY er altid 2, men denne beregner kun længden på en vindskede/vandbræt
//    public Material calculateBoardForFaciaAndRainWare(Carport carport, TreeMap<Double, Material> boards) {
//        double hypotenuse = (carport.getWidth() / 2);
//        int roofAngle = carport.getRoof().getCelsiusForSlope();
//        double carportMeasure = (hypotenuse) / (Math.cos(Math.toRadians(roofAngle)));
//        return boardCalculator(carportMeasure, carport, boards);
//    }
    //STILL IN THE MAKING
//    public Material calculateBoardForGableDecor(Carport carport, TreeMap<Double, Material> boards){
//        double hypotenuse = (carport.getWidth() / 2); 
//        int roofAngle = carport.getRoof().getCelsiusForSlope();
//        double carportMeasure = (hypotenuse)/(Math.cos(Math.toRadians(roofAngle)));
//        return boardCalculator(carportMeasure, carport, boards); 
//    }
//    public Material calculateBoardForFaciaAndRainWare(Carport carport, TreeMap<Double, Material> boards){
//        carport.getWidth();
//    }
//    public static void main(String[] args) {
//        Carport carport  = new Carport(7.3, 3.6, new Roof(20), new Shed(0, 0));
//        AllMaterialsCalculator hej1 = new AllMaterialsCalculator();
//        double hej = hej1.calculateBoardForFaciaAndRainWare(carport);
//        System.out.println(hej);
//    }
}
