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

    BoardLengthCalculator calc = new BoardLengthCalculator();

    public HashMap<String, Material> getDoneCarportWithMaterialQty(Carport carport, HashMap<Integer, TreeMap<Double, Material>> boards) throws MaterialException {
        HashMap<String, Material> boardList = calc.calculateAllBoardTypes(carport, boards);
        HashMap<String, Material> materialListFromDB = carport.getMaterialsToUseForThisCarport();
        HashMap<String, Material> materialListCalculated = new HashMap();
        
        if (!carport.getRoof().isPitchedRoof()) {
            Material spær;
            spær = boardList.get("Spær");
            materialListCalculated.put("Spær", spær);
            spær.setQty(countAmountRaftersForRoofAndUniversalBracketsForRafter(carport));
            spær.setName("Spær");

            Material understernsider;
            understernsider = boardList.get("Understern til siderne");
            materialListCalculated.put("Understern til siderne", understernsider);
            understernsider.setQty(calculateQtyOfRemForCarport(carport));
            understernsider.setName("Understern til siderne");

            Material understernbagogfor;
            understernbagogfor = boardList.get("Understern til for og bagende");
            materialListCalculated.put("Understern til for og bagende", understernbagogfor);
            understernbagogfor.setQty(calculateQtyOfRemForCarport(carport));
            understernbagogfor.setName("Understern til for og bagende");

            Material overstern;
            overstern = boardList.get("Overstern til siderne");
            materialListCalculated.put("Overstern til siderne", overstern);
            overstern.setQty(calculateQtyOfRemForCarport(carport));
            overstern.setName("Overstern til siderne");
        } else {
            Material spær; 
            spær = materialListFromDB.get("FÆDIGSKÅRET (BYG-SELV SPÆR SKAL SAMLES) 8 STK.1.0");
            materialListCalculated.put("Spær", spær);
            spær.setQty(1);
            spær.setName("Spær");
        }

        Material rem = boardList.get("Rem");
        rem.setQty(calculateQtyOfRemForCarport(carport));
        rem.setName("Rem");
        materialListCalculated.put("Rem", rem);

        Material universalBracketsRight = materialListFromDB.get("UNIVERSAL 190 MM HØJRE190.0");
        materialListCalculated.put("Universalbeslag højre", universalBracketsRight);
        universalBracketsRight.setQty(countAmountRaftersForRoofAndUniversalBracketsForRafter(carport));
        universalBracketsRight.setName("Universalbeslag højre");

        Material universalBracketsLeft = materialListFromDB.get("UNIVERSAL 190 MM VENSTRE190.0");
        materialListCalculated.put("Universalbeslag venstre", universalBracketsLeft);
        universalBracketsLeft.setQty(countAmountRaftersForRoofAndUniversalBracketsForRafter(carport));
        universalBracketsLeft.setName("Universalbeslag");

        Material posts = materialListFromDB.get("97X97 MM FYR STOLPE IMPR.3000.0");
        materialListCalculated.put("Stolper", posts);
        posts.setQty(countPostsForCarport(carport));
        posts.setName("Stolper");

        return materialListCalculated;
    }

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
        double spaceBetweenEachRafter = 0.55;
        double rafterWidth = 0.02;
        double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.

        double rafters = Math.ceil(carport.getLength() / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter

        return (int) rafters;
    }

    public int countUniversalBracketsScrews(Material universalBracketsLeft) {

        double amountOfUniversalBrackets = universalBracketsLeft.getQty() * 2; //We have to multiply to get the full amount of brackets

        double screws = 9 * amountOfUniversalBrackets;  // According to the carport instructions we need 3 screws pr. surface (universal brackets have 3 surfaces), 
        // which means 9 screws pr. universal brack

        double packages = Math.ceil(screws / 250) + 1;//Each package contains 250 screws according to the manual and one package get added to the hulbånd 

        return (int) packages;
    }
    //Skal have hjælp til denne metode, er der måske fejl i stykliste?

    public int calculateQtyOfRemForCarport(Carport carport) {
        int amountOfRemFlatRoof = 2;
        return amountOfRemFlatRoof; // Amount of remme is set to 2 if the roof is flat
    }

    public void calculateRoofDimensions(Carport carport) {
        double extraSpaceFlatRoof = 0.10;

        double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 10 cm (* 2)
        double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 10 cm (* 2)
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
