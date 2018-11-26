/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

import FunctionLayer.Carport;
import FunctionLayer.Material;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author leage
 */
public class BoardLengthCalculator {

    //Using TreeMap instead of hashmap, because the TreeMap is a NavigableMap, which can be useful in this case, 
    //where we are trying to get the best match for a board, instead of looping through each element in a hashMap or List 
    public HashMap<String, Material> calculateAllBoardTypes(Carport carport, HashMap<Integer, TreeMap<Double, Material>> boards) {

        HashMap<String, Material> allBoardsForThisCarport = new HashMap<>();

        allBoardsForThisCarport.put("Spær", getBoardForRafter(carport, boards.get(10)));
        allBoardsForThisCarport.put("Rem", getBoardForRem(carport, boards.get(10)));
        allBoardsForThisCarport.put("Understern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));
        allBoardsForThisCarport.put("Understern til for og bagende", getBoardForUndersternFrontAndBack(carport, boards.get(6)));
        allBoardsForThisCarport.put("Overstern til siderne", getBoardLengthForOversternAndUndersternSides(carport, boards.get(6)));

        return allBoardsForThisCarport;
    }

    Material getBoardForRafter(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getWidth() * 1000;

        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        Material board = boardMatch.getValue();

        return board;
    }

    Material getBoardForRem(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getLength() * 1000;

        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        Material board = boardMatch.getValue();
        return board;
    }

    private Material getBoardLengthForOversternAndUndersternSides(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = (carport.getLength() * 1000) + 50;

        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        Material board = boardMatch.getValue();
        return board;
    }

    private Material getBoardForUndersternFrontAndBack(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = (carport.getWidth() * 1000) + 50;

        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        Material board = boardMatch.getValue();
        return board;
    }
}
