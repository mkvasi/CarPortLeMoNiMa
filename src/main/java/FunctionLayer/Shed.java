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
    boolean shedWanted;
    double length, width; 
    Roof roof;

    
    

    public Shed(boolean wantShed, double length, double width) {
        this.shedWanted = wantShed;
        this.length = length;
        this.width = width;
    }

    Shed(double shedLength, double shedWidth) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isShedWanted() {
        return shedWanted;
    }

    public void setShedWanted(boolean shedWanted) {
        this.shedWanted = shedWanted;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    
 
    
    
}
