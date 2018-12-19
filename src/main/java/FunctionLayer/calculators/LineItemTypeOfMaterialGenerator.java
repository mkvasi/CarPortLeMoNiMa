package FunctionLayer.calculators;

import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Material;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Morten
 */
public class LineItemTypeOfMaterialGenerator {

    //
    int toMilimeters = 1000;

    /**
     * 
     * @param carport
     * @param boards
     * @return 
     */
    public Material getBoardForRem(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getLength() * toMilimeters; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    /**
     * 
     * @param carport
     * @param boards
     * @return 
     */
    public Material getBoardForUndersternFrontAndBack(Carport carport, TreeMap<Double, Material> boards) {
        int extraSpace = 50; // Extra space in each side for the boards.
        double carportMeasure = (carport.getWidth() * toMilimeters) + extraSpace; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    /**
     * 
     * @param carport
     * @param boards
     * @return 
     */
    public Material getBoardLengthForOversternAndUndersternSides(Carport carport, TreeMap<Double, Material> boards) {
        int extraSpace = 50; // 2.5 Cencimeter extra space for each side.
        double carportMeasure = (carport.getLength() * toMilimeters) + extraSpace; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    /**
     * 
     * @param carport
     * @param boards
     * @return 
     */
    public Material getBoardForRafter(Carport carport, TreeMap<Double, Material> boards) {
        if (carport.getRoof().getRoofSlopeCelsius() == 0) {
            double carportMeasure = carport.getWidth() * toMilimeters; // To get the dimension in milimeters, so it's comparable to the materials in the database.
            return boardCalculator(carportMeasure, carport, boards);
        } else {
            return boards.get(1.0); //1.0 is the key to get done rafters(færdigskåret spær), which is used for pitched roof
        }
    }

    /**
     * 
     * @param carport
     * @param eaves
     * @return 
     */
    public Material getCladdingForFlatRoof(Carport carport, TreeMap<Double, Material> eaves) {
        if (carport.getRoof().getRoofSlopeCelsius() == 0) {
            double carportMeasure = carport.getRoof().getLength();
            return boardCalculator(carportMeasure, carport, eaves);
        }
        return null;
    }

    //Finding the best board length to use for spær, or returning the type of board that we need two of to get the correct length
    /**
     * 
     * @param carportMeasure
     * @param carport
     * @param boards
     * @return 
     */
    public Material boardCalculator(double carportMeasure, Carport carport, TreeMap<Double, Material> boards) {
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;
        } else {
            double newMeasure = carportMeasure / 2; // Results in having two boards to fill out the carport length
            boardMatch = boards.ceilingEntry(newMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
    }

}
