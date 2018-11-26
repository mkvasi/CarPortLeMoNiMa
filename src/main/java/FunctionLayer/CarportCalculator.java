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
        
        //Material are set wheter the roof is pitched or not
        Material posts = materialList.get(0);
        posts.setQty(countPostsForCarport(carport));
        
        Material carriageBolts = materialList.get(1);
        carriageBolts.setQty(countCarriageBoltandSquareSlices(posts));
        
        Material squareSlices = materialList.get(2); 
        squareSlices.setQty(countCarriageBoltandSquareSlices(posts));
    
        Material rem = materialList.get(7);
        rem.setQty(countRemForCarport(carport));
        
       
        
        //Material based on wheter the roof is flat or pitched
        if(!carport.getRoof().isPitchedRoof()){
        Material rafter = materialList.get(3);
        rafter.setQty(countRaftersForRoofAndUniversalBracketsForRafter(carport));
        rafter.setLength(carport.getWidth());
        
        Material universalBracketsRight = materialList.get(4);
        universalBracketsRight.setQty(countRaftersForRoofAndUniversalBracketsForRafter(carport));
 
        Material universalBracketsLeft = materialList.get(5);
        universalBracketsLeft.setQty(countRaftersForRoofAndUniversalBracketsForRafter(carport));
        
        Material universalBracketsScrews = materialList.get(6);
        universalBracketsScrews.setQty(countUniversalBracketsScrews(universalBracketsLeft));
        
        Material plasticRoof = materialList.get(8);
        plasticRoof.setQty(countEaves(carport));
        
        }else{
        
            
        Material roofTiles = materialList.get(9);
        roofTiles.setQty(calculateTiles(carport));
        
        Material rygSten = materialList.get(10);
        rygSten.setQty(calculateRygstensTiles(carport));
        }
        
        
        return materialList;
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

//    //Usi
//    public int countSquareSliceForBrackets(Material brackets) {
//        int amountOfScrewsPerBracket = 9;
//        int screws = (brackets.getQty() * amountOfScrewsPerBracket); // Total amount of screws for the brackets. For each brackets, 8 screws are added
//        return screws;
//    }
        
    public int countRaftersForRoofAndUniversalBracketsForRafter(Carport carport) {
        double spaceBetweenEachRafter = 0.55;
        double rafterWidth = 0.02;
        double totalRafterDimension = spaceBetweenEachRafter + rafterWidth; // Total dimension for each rafter including both space and material.

        double rafters = Math.ceil(carport.getLength() / totalRafterDimension); // Total amount of rafter based on calculating length with total dimension per rafter
       
        return  (int)rafters;
    }

    public int countUniversalBracketsScrews(Material universalBracketsLeft) {
        
        double amountOfUniversalBrackets = universalBracketsLeft.getQty() * 2; //We have to multiply to get the full amount of brackets
        
        double screws = 9 * amountOfUniversalBrackets;  // According to the carport instructions we need 3 screws pr. surface (universal brackets have 3 surfaces), 
                                                        // which means 9 screws pr. universal brack
        
        double packages = Math.ceil(screws/250)+1;//Each package contains 250 screws according to the manual and one package get added to the hulbånd 
        
 
        return (int)packages;
    }
     //Skal have hjælp til denne metode, er der måske fejl i stykliste?
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

    // SKAL LAVES OM
    public int countEaves(Carport carport) {
        calculateRoofDimensions(carport);
        double eavesWidth = 1.0;
        double countEaves = Math.ceil(carport.getRoof().getWidth() / eavesWidth); // Total pieces of eaves with 1 meter width. 
       // double eaveMetersForLength = Math.ceil(carport.getLength());              // Length of each eave piece.
        return (int) countEaves;
    }
    //add tiles pr. m2, based on measure from stykliste
    public int calculateTiles(Carport carport){
        calculateRoofDimensions(carport);
        int tilesPrM2 = 11;
        double roof= Math.ceil((carport.getRoof().getLength()*(carport.getRoof().getWidth())))* tilesPrM2;
       
        return (int) roof;
    }
    //stykslisten says 21 rygsten for 7,3 m, we calculate with 3 rygsten each meter
    public int calculateRygstensTiles(Carport carport){
        int rygstenTilesPrMeterLength = 3;
        double roofTiles= Math.ceil((carport.getRoof().getLength()))* rygstenTilesPrMeterLength;
       
        return (int) roofTiles;
    }
    public int calculateUndersternsbrædderForSides(Carport carport){
        double length = carport.getLength()+0.05; 
        
        double numberOfBoards60 = length / 6;
        double numberOfBoardLeft = length % 6;
        double numberOfBoard54 = numberOfBoardLeft / 5.4;
        numberOfBoardLeft = length % 5.4;
        double numberOfBoard48 = numberOfBoardLeft / 4.8;
        numberOfBoardLeft = length % 4.8;
        double numberOfBoard42 = numberOfBoardLeft / 4.2;
        numberOfBoardLeft = length % 4.2;
        double numberOfBoard36 = numberOfBoardLeft / 3.6;
        numberOfBoardLeft = length % 3; 
        double numberOfBoards30 = numberOfBoardLeft / 3; 
        return 0;
        
    }
}