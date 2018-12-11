/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.entity;

import FunctionLayer.BillOfMaterial;
import java.util.HashMap;
import java.util.List;



public class Carport {

    private int id;
    private double heigth, length, width;
    private Roof roof;
    private Shed shed; 
    private BillOfMaterial billOfmaterial;
    


    public Carport(double length, double width, Roof roof, Shed shed, BillOfMaterial billOfmaterial) {
        this.length = length;
        this.width = width;
        this.roof = roof;
        this.shed = shed;
        this.billOfmaterial = new BillOfMaterial();
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

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    public Shed getShed() {
        return shed;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }

    public BillOfMaterial getBillOfmaterial() {
        return billOfmaterial;
    }

    public void setBillOfmaterial(BillOfMaterial billOfmaterial) {
        this.billOfmaterial = billOfmaterial;
    }

    

   

 





}
