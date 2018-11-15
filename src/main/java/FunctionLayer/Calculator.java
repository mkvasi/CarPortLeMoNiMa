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
        double posts = (Math.ceil(carport.getLength() / 3) * 2); // Total count of posts, for the carport. For each 3 meters, a new post will be added to each length (* 2)
        return (int) posts;
    }

    public int countBracketsForCarport(Carport carport) {
        int posts = countPostsForCarport(carport);
        int brackets = (posts * 4);                             // Total amount of brackets. For each post, 4 brackets are added 
        return brackets;
    }

    public int countScrewsForBrackets(Carport carport) {
        int brackets = countBracketsForCarport(carport);
        int screws = (brackets * 8);
        
        return screws;
    }

    public int countRemForCarport(Carport carport) {
        int remme;
        
        if (carport.getRoof().isFlatRoof() == false) {
            remme = 4;
        } else {
            remme = 2;
        }
        return remme;
    }
    
    public void calculateRoofDimensions(Carport carport) {
        if (carport.getRoof().isFlatRoof()) {    
        double extraLength = 0.10 * 2;
        double extraWidth = 0.10 * 2;
        carport.getRoof().setLength(carport.getLength() + extraLength);
        carport.getRoof().setWidth(carport.getWidth() + extraWidth);
        }
    }
    
    public int countRaftersForRoof(Carport carport) {
        double carportLength = carport.getLength();
        double spaceBetweenEachRafter = 0.59;
        double rafterWidth = 0.02;
        double totalRafterDimension = spaceBetweenEachRafter + rafterWidth;
                
        double rafters = Math.ceil(carportLength / totalRafterDimension);
        return (int) rafters;
    }
}
