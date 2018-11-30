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
public class Roof {

    private int roofSlopeCelsius;
    private double heigth, width, length;
    private final double FLATWIDTH = 0.0;
    private final double FLATLENGTH = 50.0;
    private final double SLOPEWIDTH = 300.0;
    private final double SLOPELENGTH = 300.0;
    private final double TOMILIMETERS = 1000;
//    private double heigth, width, length; 


    
    

//    public Roof(boolean flatRoof) {
//        this.isPitchedRoof = flatRoof;
//    }
//
//    public Roof(boolean isPitchedRoof, double length, double width) {
//        this.isPitchedRoof = isPitchedRoof;
//        this.length = length;
//        this.width = width;
//       
//    }
//    
//
//    public boolean isPitchedRoof() {
//        return isPitchedRoof;
//    }
//
//    public void setIsPitchedRoof(boolean isPitchedRoof) {
//        this.isPitchedRoof = isPitchedRoof;
//    }
//
//    public double getLength() {
//        return length;
//    }
//
//    public void setLength(double length) {
//        this.length = length;
//    }
//
//    public double getWidth() {
//        return width;
//    }
//
//    public void setWidth(double width) {
//        this.width = width;
//    }
//
//    public double getSlope() {
//        return slope;
//    }
//
//    public void setSlope(double slope) {
//        this.slope = slope;
//    }

//    public Roof(int roofSlopeCelsius, double heigth, double width, double length) {
//        this.roofSlopeCelsius = roofSlopeCelsius;
//        this.heigth = heigth;
//        this.width = width;
//        this.length = length;
//    }

    public Roof(int roofSlopeCelsius) {
        this.roofSlopeCelsius = roofSlopeCelsius;
    }
    

   

    public int getCelsiusForSlope() {
        return roofSlopeCelsius;
    }

    public void setCelsiusForSlope(int roofSlopeCelsius) {
        this.roofSlopeCelsius = roofSlopeCelsius;
    }
    
//
//    public double getHeigth() {
//        return heigth;
//    }
//
//    public void setHeigth(double heigth) {
//        this.heigth = heigth;
//    }
//
//    public double getWidth() {
//        return width;
//    }
//
//    public void setWidth(double width) {
//        this.width = width;
//    }
//
//    public double getLength() {
//        return length;
//    }
//
//    public void setLength(double length) {
//        this.length = length;
//    }
//    
//    public void calculateRoofDimensionsFlat(Carport carport) {
//        double extraSpaceFlatRoof = 0.30;
//
//        double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 30 cm (* 2)
//        double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 30 cm (* 2)
//        carport.getRoof().setLength(carport.getLength() + extraLength);
//        carport.getRoof().setWidth(carport.getWidth() + extraWidth);
//
//    }
//    
//    public void calculateRoofDimensionsSlope(Carport carport) {
//        double extraSpaceFlatRoof = 0.30;
//
//        double extraLength = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each length it would be added 30 cm (* 2)
//        double extraWidth = extraSpaceFlatRoof * 2; // Total count of extra space for roof. For each wide it would be added 30 cm (* 2)
//        carport.getRoof().setLength(carport.getLength() + extraLength);
//        carport.getRoof().setWidth(carport.getWidth() + extraWidth);
//
//    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public void calculateRoofDimensions(Carport carport){
        if(roofSlopeCelsius == 0){
            width = (carport.getWidth() * TOMILIMETERS) + (FLATWIDTH * 2);
            length = (carport.getLength() * TOMILIMETERS) + (FLATLENGTH * 2);
        } else {
            width = (carport.getWidth() * TOMILIMETERS) + (SLOPEWIDTH * 2);
            length = (carport.getLength() * TOMILIMETERS) + (SLOPELENGTH * 2);
        }
    }
}

