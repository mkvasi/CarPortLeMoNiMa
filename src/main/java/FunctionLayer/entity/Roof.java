/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entity;

import FunctionLayer.entity.Carport;



/**
 *
 * @author nr
 */
public class Roof {

    private int roofSlopeCelsius;
    private double heigth, width, length;
    private int roofCladding;
    private final double FLATWIDTH = 0.0;
    private final double FLATLENGTH = 50.0;
    private final double SLOPEWIDTH = 300.0;
    private final double SLOPELENGTH = 300.0;
    private final double TOMILIMETERS = 1000;

    public Roof(int roofSlopeCelsius) {
        this.roofSlopeCelsius = roofSlopeCelsius;
    }

    public int getRoofSlopeCelsius() {
        return roofSlopeCelsius;
    }

    public void setRoofSlopeCelsius(int roofSlopeCelsius) {
        this.roofSlopeCelsius = roofSlopeCelsius;
    }

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

    public int getRoofCladding() {
        return roofCladding;
    }

    public void setRoofCladding(int roofCladding) {
        this.roofCladding = roofCladding;
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

