/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author leage
 */
public class Shed {
    
    private int id;
    private double heigth = 2.25, width, length;
    
    
//    boolean shedWanted;
//    double length, width; 
//    Roof roof;
//
//    
//    
//
//    public Shed(boolean wantShed, double length, double width) {
//        this.shedWanted = wantShed;
//        this.length = length;
//        this.width = width;
//    }
//
//    public boolean isShedWanted() {
//        return shedWanted;
//    }
//
//    public void setShedWanted(boolean shedWanted) {
//        this.shedWanted = shedWanted;
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

    public Shed(int id, int heigth, double width, double length) {
        this.id = id;
        this.width = width;
        this.length = length;
    }

    public Shed(int id, double width, double length) {
        this.id = id;
        this.width = width;
        this.length = length;
    }

    public Shed(double width, double length) {
         this.width = width;
        this.length = length;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
    
    
}
