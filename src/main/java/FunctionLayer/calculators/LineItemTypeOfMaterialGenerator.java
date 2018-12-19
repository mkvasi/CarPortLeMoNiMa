package FunctionLayer.calculators;

import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Material;
import java.util.Map;
import java.util.TreeMap;

/**
 * Meningen med denne klasse er at finde de bedste og korrekte materialer til carportens stykliste
 */
public class LineItemTypeOfMaterialGenerator {

    //Bruges til at alle mål til milimeter
    int toMilimeters = 1000;

    /**
     * Meningen med denne metode er at finde materialet til brug for rem i carporten
     * @param carport
     * @param boards
     * @return Material
     */
    public Material getBoardForRem(Carport carport, TreeMap<Double, Material> boards) {
        double carportMeasure = carport.getLength() * toMilimeters; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    /**
     * Meningen med denne metode er at finde materialet til brug for understern for og bag i carporten
     * @param carport
     * @param boards
     * @return Material
     */
    public Material getBoardForUndersternFrontAndBack(Carport carport, TreeMap<Double, Material> boards) {
        int extraSpace = 50; // Extra space in each side for the boards.
        double carportMeasure = (carport.getWidth() * toMilimeters) + extraSpace; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    /**
     * Meningen med denne metode er at finde materialet til brug for understern og overstern i siderne i carporten
     * @param carport
     * @param boards
     * @return Material
     */
    public Material getBoardLengthForOversternAndUndersternSides(Carport carport, TreeMap<Double, Material> boards) {
        int extraSpace = 50; // 2.5 Cencimeter extra space for each side.
        double carportMeasure = (carport.getLength() * toMilimeters) + extraSpace; // To get the dimension in milimeters, so it's comparable to the materials in the database.
        return boardCalculator(carportMeasure, carport, boards);
    }

    /**
     * Meningen med denne metode er at finde materialet til brug for spær i carporten
     * @param carport
     * @param boards
     * @return Material
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
     * Meningen med denne metode er at finde materialet til brug for beklædning af fladt tag i carporten
     * @param carport
     * @param eaves
     * @return Material
     */
    public Material getCladdingForFlatRoof(Carport carport, TreeMap<Double, Material> eaves) {
        if (carport.getRoof().getRoofSlopeCelsius() == 0) {
            double carportMeasure = carport.getRoof().getLength();
            return boardCalculator(carportMeasure, carport, eaves);
        }
        return null;
    }

    /**
     * Meningen med denne metode er at finde den korrekte længde af materialet, ellers deles målet med 2 før en ny udregning af korrekte materiale
     * @param carportMeasure
     * @param carport
     * @param boards
     * @return Material
     */
    public Material boardCalculator(double carportMeasure, Carport carport, TreeMap<Double, Material> boards) {
        Map.Entry<Double, Material> boardMatch = boards.ceilingEntry(carportMeasure);
        if (boardMatch != null) {
            Material board = boardMatch.getValue();
            return board;
        } else {
            double newMeasure = carportMeasure / 2;
            boardMatch = boards.ceilingEntry(newMeasure);
            Material board = boardMatch.getValue();
            return board;
        }
    }

}
