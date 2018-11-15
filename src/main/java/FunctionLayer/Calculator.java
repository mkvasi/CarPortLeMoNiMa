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
        return (int) posts;
    }

    public int countBracketsForCarport(Carport carport) {
        int amountOfBracketsPerPost = 4;
        int posts = countPostsForCarport(carport);
        int brackets = (posts * amountOfBracketsPerPost); // Total amount of brackets. For each post, 4 brackets are added 
        return brackets;
    }

    public int countScrewsForBrackets(Carport carport) {
        int amountOfScrewsPerBracket = 8;
        int brackets = countBracketsForCarport(carport);
        int screws = (brackets * amountOfScrewsPerBracket); // Total amount of screws. For each brackets, 8 screws are added
        
        return screws;
    }

    public int countRemForCarport(Carport carport) {   
        int amountOfRemFlatRoof = 2;
        int amountOfRemNotFlatRoof = 4;
        if (!carport.getRoof().isFlatRoof()) return amountOfRemNotFlatRoof; // Amount of remme is set to 4 if the roof is not flat
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
        return (int) rafters;
    }
}
