/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author nr
 */
public class Calculator {

    public int countPostsForCarport(Carport carport) {
        int meterPerPost = 3;
        double posts = (Math.ceil(carport.getLength() / meterPerPost) * 2); // Total count of posts, for the carport. For each 3 meters, a new post will be added to each length (* 2)
        carport.addMaterialToCarport(new Material(1, "Stolpe", "stk.", "Træ", "Stolpe til carport", 80, 130, (int) posts)); // !!! HARDCODED - SKAL FJERNES !!!
        return (int) posts;
    }

    public int countBracketsForCarport(Carport carport) {
        int amountOfBracketsPerPost = 4;
        int posts = countPostsForCarport(carport);
        int brackets = (posts * amountOfBracketsPerPost); // Total amount of brackets. For each post, 4 brackets are added 
        carport.addMaterialToCarport(new Material(2, "Beslag", "stk.", "Beslag", "Beslag til stolpe", 5, 12, brackets)); // !!! HARDCODED - SKAL FJERNES !!!
        return brackets;
    }

    public int countScrewsForBrackets(Carport carport) {
        int amountOfScrewsPerBracket = 8;
        int brackets = countBracketsForCarport(carport);
        int screws = (brackets * amountOfScrewsPerBracket); // Total amount of screws for the brackets. For each brackets, 8 screws are added
        carport.addMaterialToCarport(new Material(3, "Skrue til beslag", "stk.", "Skruer", "Skruer til beslag", 0.50, 0.80, screws)); // !!! HARDCODED - SKAL FJERNES !!!
        return screws;
    }

    public int countScrewsForRaftersFlatRoof(Carport carport) {
        int amountOfScrewsForOneMeterRaft = 4;
        double totalRafterMeters = Math.ceil(countRaftersForRoof(carport) * carport.getWidth()); // Total amount of rafter meters. All counted rafters are multiplied with the width of the carport.
        int screws = (int) totalRafterMeters * amountOfScrewsForOneMeterRaft; // Total amount of screws for the rafters, to hold the flat roof. For each rafter meter, 4 screws are added.
        carport.addMaterialToCarport(new Material(4, "Skrue til spær", "stk.", "Skruer", "Skruer til spær", 0.50, 0.80, screws)); // !!! HARDCODED - SKAL FJERNES !!!
        return screws;
    }

    public int countRemForCarport(Carport carport) {
        int amountOfRemFlatRoof = 2;
        int amountOfRemNotFlatRoof = 4;
        if (!carport.getRoof().isFlatRoof()) {
            carport.addMaterialToCarport(new Material(5, "Rem", "stk.", "Træ", "Remme til carport", 600, 900, amountOfRemFlatRoof)); // !!! HARDCODED - SKAL FJERNES !!!
            return amountOfRemNotFlatRoof; // Amount of remme is set to 4 if the roof is not flat
        }
        return amountOfRemFlatRoof; // Amount of remme is set to 2 if the roof is flat
    }

    public void calculateRoofDimensions(Carport carport) {
        double extraSpaceFlatRoof = 0.10;
        if (carport.getRoof().isFlatRoof()) {
            double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 10 cm (* 2)
            double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 10 cm (* 2)
            carport.getRoof().setLength(carport.getLength() + extraLength);
            carport.getRoof().setWidth(carport.getWidth() + extraWidth);
        }
    }

    public int countRaftersForRoof(Carport carport) {
        double carportLength = carport.getLength();
        double spaceBetweenEachRafter = 0.59;
        double rafterWidth = 0.02;
        double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.

        double rafters = Math.ceil(carportLength / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter
        carport.addMaterialToCarport(new Material(6, "Spær", "meter", "Træ", "Spær til at holde tagplader", 10, 25, (int) rafters));
        return (int) rafters;
    }
    
    public int countEaves(Carport carport) {
        double eavesWidth = 1.0;
        double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
        double eaveMetersForLength = Math.ceil(carport.getLength());              // Length of each eave piece.
        carport.addMaterialToCarport(new Material(7, "Tagplade blå 6 meter", "stk.", "Tagplader", "Tagplader til flat tag", 60, 180, (int) countEaves));
        return (int) countEaves;
    }
}
