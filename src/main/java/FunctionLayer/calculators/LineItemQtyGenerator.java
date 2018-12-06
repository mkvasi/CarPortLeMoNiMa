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
import FunctionLayer.exceptions.CalculatorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author leage
 */
public class LineItemQtyGenerator {

    double toMilimeters = 1000;
    LineItemTypeOfMaterialGenerator calc = new LineItemTypeOfMaterialGenerator();

    public BillOfMaterial makeBillOfMaterial(Carport carport, HashMap<Integer, TreeMap<Double, Material>> boards) throws CalculatorException {
        List<LineItem> lineItemList = new ArrayList();
        BillOfMaterial billOfMaterial = new BillOfMaterial();

        Material material;

        //Spær
        material = calc.getBoardForRafter(carport, boards.get(10));
        lineItemList.add(new LineItem(getQTYForRafter(carport, material), "Spær", material));

        //Rem
        material = calc.getBoardForRem(carport, boards.get(6));
        lineItemList.add(new LineItem(getQTYForRem(carport, material), "Rem", material));

        //Understern til siderne
        material = calc.getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
        lineItemList.add(new LineItem(getQTYForRem(carport, material), "Understern til siderne", material));

        //Understern til for og bagende
        material = calc.getBoardForUndersternFrontAndBack(carport, boards.get(6));
        lineItemList.add(new LineItem(getQTYForRafter(carport, material), "Understern til for og bagende", material));
        //Overstern til siderne
        material = calc.getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
        lineItemList.add(new LineItem(getQTYForRem(carport, material), "Overstern til siderne", material));

        //Overstern til for og bagende
        material = calc.getBoardLengthForOversternAndUndersternSides(carport, boards.get(6));
        lineItemList.add(new LineItem(1, "Overstern til for og bagende", material));

        //Universalbeslag højre
        material = boards.get(12).firstEntry().getValue();
        lineItemList.add(new LineItem(getUniversalBracketsQtyForOneSide(carport, material), "Universalbeslag højre", material));

        //Universalbeslag venstre
        material = boards.get(13).firstEntry().getValue();
        lineItemList.add(new LineItem(getUniversalBracketsQtyForOneSide(carport, material), "Universalbeslag venstre", material));

        //Stolper
        material = boards.get(7).firstEntry().getValue();
        lineItemList.add(new LineItem(getQtyOfPosts(carport), "Stolper", material));

        //Bræddebolt
        material = boards.get(14).firstEntry().getValue();
        lineItemList.add(new LineItem(getQtyOfBræddebolt(carport), "Bræddebolt", material));

        //Skruer
        material = boards.get(4).firstEntry().getValue();
        lineItemList.add(new LineItem(getQtyOfScrews(carport, material), "Skruer", material));

        billOfMaterial.setLineItems(lineItemList);
        return billOfMaterial;
    }

    public int getQTYForRafter(Carport carport, Material board) throws CalculatorException {

        try {
            if (carport.getRoof().getRoofSlopeCelsius() == 0) {
                double spaceBetweenEachRafter = 0.55;
                double rafterWidth = 0.02;
                double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.
                double rafters = Math.ceil(carport.getLength() / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter
                if (carport.getWidth() * toMilimeters > board.getLength()) {
                    return (int) rafters * 2;
                }
                return (int) rafters;
            } else {
                return 8;
            }
        } catch (NullPointerException ex) {
            throw new CalculatorException(ex);
        }
    }

    public int getQTYForRem(Carport carport, Material board) throws CalculatorException {
        try {
            int amountOfRem = 2;
            if (board.getLength() < carport.getLength() * toMilimeters) {
                return amountOfRem * 2;
            }
            return amountOfRem;
        } catch (NullPointerException ex) {
            throw new CalculatorException(ex);
        }
    }

    public int getQtyOfPosts(Carport carport) throws CalculatorException {
        int extraPostForShed = 6;
        try {
            int meterPerPost = 2;
            double posts = ((Math.ceil(carport.getLength() / meterPerPost) * 2));
            if(carport.getShed() == null){
            return (int) posts;
            }else{
            return (int) posts + extraPostForShed;     
            }
                
        } catch (Exception ex) {
            throw new CalculatorException(ex);
        }
    }

    public int countEaves(Carport carport) throws CalculatorException {
        //carport.getRoof().calculateRoofDimensions(carport);
        try {
            double eavesWidth = 1.0;
            double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
            return (int) countEaves;
        } catch (Exception ex) {
            throw new CalculatorException(ex);
        }
    }

    //stykslisten says 21 rygsten for 7,3 m, we calculate with 3 rygsten each meter
    public int calculateRygstensTiles(Carport carport) throws CalculatorException {
        try {
            int rygstenTilesPrMeterLength = 3;
            double roofTiles = Math.ceil((carport.getRoof().getLength())) * rygstenTilesPrMeterLength;
            return (int) roofTiles;
        } catch (Exception ex) {
            throw new CalculatorException(ex);
        }
    }

    public Material returnMaterialForFarciaAndRainware(Carport carport, TreeMap<Double, Material> boards) throws CalculatorException {
        try {
            double boardLengthHypotenuse = calculateBoardLengthForFarciaAndRainware(carport); // Kalder metode for at få længden på vinskederne (hypotenusen)
            return calc.boardCalculator(boardLengthHypotenuse, carport, boards);  // Kalder boardCalculator som finder det bræt som matcher længden bedst.
        } catch (Exception ex) {
            throw new CalculatorException(ex);
        }
    }

    //METODER NEDENFOR BRUGES KUN TIL TAG MED HÆLDNING!
    //Beregner vindskedernes(Facia) længde, skal modtage boardtypen(25x150 mm trykimp. bræt), og carporten som parameter.
    //Kan også beregne længden på vandbrædderne(RainWare)(19x100mm tryk imp. bræt)
    public Double calculateBoardLengthForFarciaAndRainware(Carport carport) throws CalculatorException {
        try {
            double hosliggendeKatete = (carport.getRoof().getWidth() / 2);          // Tagbredden divideres med 2 for at finde midten af gavlen
            int roofAngle = carport.getRoof().getRoofSlopeCelsius();                 // roofAngle holder hældningen på taget
            double boardLengthHypotenuse = (hosliggendeKatete) / (Math.cos(Math.toRadians(roofAngle))); //  Hypotenusen isoleres i cosinus formel for retvinklet trekanter, for at finde længden på vindskederne

            return boardLengthHypotenuse;
        } catch (Exception ex) {
            throw new CalculatorException(ex);
        }
    }

    public Double calculateHeightForRoof(Carport carport) throws CalculatorException {
        try {
            double boardLengthHypotenuse = calculateBoardLengthForFarciaAndRainware(carport); // Kalder metode for at få længden på vinskederne (hypotenusen)
            double height = (Math.sin(carport.getRoof().getWidth()) * boardLengthHypotenuse); // 

            return height;
        } catch (CalculatorException ex) {
            throw new CalculatorException(ex);
        }
    }

    // Den maksimale højde på taget, og den halve bredde på gavl, bruges til at regne antal brædder.
    public Double calculateBoardsForGable(Carport carport, Material boards) throws CalculatorException {
        try {
            double halfGable = (carport.getRoof().getWidth() / 2);                  // Finder bredden på en halv gavl
            double countBoards = (halfGable * toMilimeters / boards.getWidth() * 2);// Retunere hvor mange brædder der skal bruges til gavlen. Der ganges med to fordi det både gælder for og bag

            return countBoards;
        } catch (Exception ex) {
            throw new CalculatorException(ex);
        }
    }

    // Regner antal lægter ud som skal ligge på begge sidder af skråtaget
    public int calculateBattensForPitchedRoof(Carport carport, TreeMap<Double, Material> boards) throws CalculatorException {
        try {
            int spaceBetweenEachBatten = 300;
            double battenWidth = boards.firstEntry().getValue().getWidth();
            double totalMeasureBetweenEachBatten = spaceBetweenEachBatten + battenWidth;

            double hypotenuseLength = calculateBoardLengthForFarciaAndRainware(carport);
            double countBattens = (Math.ceil(hypotenuseLength * 1000 / totalMeasureBetweenEachBatten * 2)); // Længden af vindskeden bruges til at finde ud af antallet af lægter pr. side af skråtaget. For at dække begge sider ganges der med to. Math.ceil() bruges til at runde op. 

            return (int) countBattens;
        } catch (CalculatorException ex) {
            throw new CalculatorException(ex);
        }
    }

    //add tiles pr. m2, based on measure from stykliste
    public int calculateTiles(Carport carport) throws CalculatorException {
        try {
            carport.getRoof().calculateRoofDimensions(carport);
            int tilesPrM2 = 11;
            double roof = Math.ceil((carport.getRoof().getLength() * (carport.getRoof().getWidth()))) * tilesPrM2;

            return (int) roof;
        } catch (Exception ex) {
            throw new CalculatorException(ex);
        }
    }

    public int getUniversalBracketsQtyForOneSide(Carport carport, Material mat) throws CalculatorException {
        try {
            if (carport.getRoof().getRoofSlopeCelsius() == 0) {

                return getQTYForRafter(carport, mat);
            } else {
                return 8;

            }
        } catch (CalculatorException ex) {
            throw new CalculatorException(ex);
        }
    }

    public int getQtyOfBræddebolt(Carport carport) throws CalculatorException {
        //int extraBræddeBoltForShed = 
        try {
            if (carport.getRoof().getRoofSlopeCelsius() == 0) {
                return getQtyOfPosts(carport) * 2;
            } else {
                return 8;

            }
        } catch (CalculatorException ex) {
            throw new CalculatorException(ex);
        }
    }
    private int getQtyOfScrews(Carport carport, Material mat) throws CalculatorException {
        int screwsForEachUniversalBracket = 9;
        int screwsInEachPackage = 200;
        try {
            if (carport.getRoof().getRoofSlopeCelsius() == 0) {
                return (int) Math.ceil(((getUniversalBracketsQtyForOneSide(carport, mat)*2) * screwsForEachUniversalBracket))/screwsInEachPackage; 
            } else {
                return (int)Math.ceil((8 * screwsForEachUniversalBracket))/screwsInEachPackage;

            }
        } catch (CalculatorException ex) {
            throw new CalculatorException(ex);
        }
    }
}
