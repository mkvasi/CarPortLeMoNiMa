/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

import FunctionLayer.Carport;
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

    //Using TreeMap instead of hashmap, because the TreeMap is a NavigableMap, which can be useful in this case, 
    //where we are trying to get the best match for a board, instead of looping through each element in a hashMap or List 
    public HashMap<String, Material> calculateAllMaterialTypes(Carport carport, HashMap<Integer, TreeMap<Double, Material>> boards) {
        HashMap<String, Material> allMaterialsToUseForThisCarport = new HashMap<>();
        HashMap<String, Material> materialListFromDB = carport.getMaterialsToUseForThisCarport();

        if (!carport.getRoof().isPitchedRoof()) {
            allMaterialsToUseForThisCarport.put("Spær", getBoardForRafter(carport, boards.get(10)));

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
    Material getBoardForRafter(Carport carport, TreeMap<Double, Material> boards) {

        double carportMeasure = carport.getWidth() * 1000;

             
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;

        } else {
            carportMeasure = carportMeasure / 2;
            boardMatch = boards.ceilingEntry(carportMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
    }

    Material getBoardForRem(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getLength() * 1000;

        
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;

        } else {
            carportMeasure = carportMeasure / 2;
            boardMatch = boards.ceilingEntry(carportMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
    }

    private Material getBoardLengthForOversternAndUndersternSides(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = (carport.getLength() * 1000) + 50;

          
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;

        } else {
            carportMeasure = carportMeasure / 2;
            boardMatch = boards.ceilingEntry(carportMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
    }

    private Material getBoardForUndersternFrontAndBack(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = (carport.getWidth() * 1000) + 50;
             
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;

        } else {
            carportMeasure = carportMeasure / 2;
            boardMatch = boards.ceilingEntry(carportMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
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
}
