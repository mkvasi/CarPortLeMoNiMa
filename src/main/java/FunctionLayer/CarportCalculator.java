/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;

/**
 *
 * @author nr
 */
public class CarportCalculator {
    

    public List getCarportBOM(List<Material> materialList, Carport carport) {
        
        
        Material posts = materialList.get(0);
        posts.setQty(countPostsForCarport(carport));
        
        Material brackets = materialList.get(1);
        brackets.setQty(countBracketsForCarport(posts));
        
        Material screws = materialList.get(2);
        screws.setQty(countScrewsForBrackets(brackets));
        
        Material rafter = materialList.get(3);
        rafter.setQty(countRaftersForRoof(carport));
     
        Material rafterScrews = materialList.get(4);
        rafterScrews.setQty(countScrewsForRafters(carport, rafter));
        
        Material rem = materialList.get(5);
        rem.setQty(countRemForCarport(carport));
        
        Material roof = materialList.get(6);
        roof.setQty(countEaves(carport));
        
        return materialList;
    }

    public int countPostsForCarport(Carport carport) {
        int meterPerPost = 3;
        double posts = ((Math.ceil(carport.getLength() / meterPerPost) * 2));// Total count of posts, for the carport. For each 3 meters, a new post will be added to each length (* 2)
        return (int) posts;
    }


    public int countBracketsForCarport(Material post) {
        int amountOfBracketsPerPost = 4;
        int brackets = post.getQty() * amountOfBracketsPerPost;
        return brackets;
    }


    public int countScrewsForBrackets(Material brackets) {
        int amountOfScrewsPerBracket = 8;
        int screws = (brackets.getQty() * amountOfScrewsPerBracket); // Total amount of screws for the brackets. For each brackets, 8 screws are added
        return screws;
    }
    public int countRaftersForRoof(Carport carport) {
        double carportLength = carport.getLength();
        double spaceBetweenEachRafter = 0.59;
        double rafterWidth = 0.02;
        double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.

        double rafters = Math.ceil(carportLength / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter
       
        return (int) rafters;
    }

    public int countScrewsForRafters(Carport carport, Material rafter) {
        
        int amountOfScrewsForOneMeterRaft = 4;
        double totalRafterMeters = Math.ceil(rafter.getQty() * carport.getWidth());
        double screws = totalRafterMeters * amountOfScrewsForOneMeterRaft; // Total amount of screws for the rafters, to hold the flat roof. For each rafter meter, 4 screws are added.
 
        return (int) screws;
    }

    public int countRemForCarport(Carport carport) {
        int amountOfRemFlatRoof = 2;
        int amountOfRemNotFlatRoof = 4;
        if (carport.getRoof().isPitchedRoof()) {
            return amountOfRemNotFlatRoof; // Amount of remme is set to 4 if the roof is not flat
        }
        return amountOfRemFlatRoof; // Amount of remme is set to 2 if the roof is flat
    }


    public void calculateRoofDimensions(Carport carport) {
        double extraSpaceFlatRoof = 0.10;
     
            double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 10 cm (* 2)
            double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 10 cm (* 2)
            carport.getRoof().setLength(carport.getLength() + extraLength);
            carport.getRoof().setWidth(carport.getWidth() + extraWidth);
        
        
    }

    
    public int countEaves(Carport carport) {
        calculateRoofDimensions(carport);
        double eavesWidth = 1.0;
        double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
       // double eaveMetersForLength = Math.ceil(carport.getLength());              // Length of each eave piece.
        return (int) countEaves;
    }

}
