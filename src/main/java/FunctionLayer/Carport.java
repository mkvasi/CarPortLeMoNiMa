/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.HashMap;



public class Carport {

    private int id;
    private double heigth, width, length;
    private Roof roof;
    private Shed shed; 
    
    
////    private HashMap<String,Material> materialsToUseForThisCarport; 
//
//    public Shed getShed() {
//        return shed;
//    }
//
//    public void setShed(Shed shed) {
//        this.shed = shed;
//    }
//
//    public Carport(double length, double width, Roof roof, Shed shed) {
//        this.length = length;
//        this.width = width;
//        this.roof = roof;
//        this.shed = shed;
//    }
//
////    public Carport(double length, double width, double heigth, Roof roof, HashMap<String, Material> materialsToUseForThisCarport) {
////        this.length = length;
////        this.width = width;
////        this.heigth = heigth;
////        this.roof = roof;
////        this.materialsToUseForThisCarport = materialsToUseForThisCarport;
////    }
//
//    public Carport(double length, double width, double heigth, Roof roof, Shed shed) {
//        this.length = length;
//        this.width = width;
//        this.heigth = heigth;
//        this.roof = roof;
//        this.shed = shed;
//    }
//    
//      
//
//    public Carport(double length, double width, Roof roof) {
//        this.length = length;
//        this.width = width;
//        this.heigth = 2.25;
//        this.roof = roof;
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
//    public double getHeigth() {
//        return heigth;
//    }
//
//    public void setHeigth(double heigth) {
//        this.heigth = heigth;
//    }
//
//    public Roof getRoof() {
//        return roof;
//    }
//
//    public void setRoof(Roof roof) {
//        this.roof = roof;
//    }
//
////    public HashMap<String, Material> getMaterialsToUseForThisCarport() {
////        return materialsToUseForThisCarport;
////    }
////
////    public void setMaterialsToUseForThisCarport(HashMap<String, Material> materialsToUseForThisCarport) {
////        this.materialsToUseForThisCarport = materialsToUseForThisCarport;
////    }

    public Carport(int id, double heigth, double width, double length, Roof roof, Shed shed) {
        this.id = id;
        this.heigth = heigth;
        this.width = width;
        this.length = length;
        this.roof = roof;
        this.shed = shed;
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

    

   

 





}
