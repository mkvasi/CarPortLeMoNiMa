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
public class Material {

    private int id;
    private String description;
    private double heigth, width, length;
    private double buyprice, sellprice;
    private boolean defaultUsed;
    private int type_id, measure_id;

    public Material(int id, String description, double heigth, double width, double length, double buyprice, double sellprice, boolean defaultUsed, int type_id, int measure_id) {
        this.id = id;
        this.description = description;
        this.heigth = heigth;
        this.width = width;
        this.length = length;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.defaultUsed = defaultUsed;
        this.type_id = type_id;
        this.measure_id = measure_id;
    }
    //Til test
    public Material(double length) {
        this.length = length;
    }
    //Til test
    public Material(int type_id) {
        this.type_id = type_id;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public double getSellprice() {
        return sellprice;
    }

    public void setSellprice(double sellprice) {
        this.sellprice = sellprice;
    }

    public boolean isDefaultUsed() {
        return defaultUsed;
    }

    public void setDefaultUsed(boolean defaultUsed) {
        this.defaultUsed = defaultUsed;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getMeasure_id() {
        return measure_id;
    }

    public void setMeasure_id(int measure_id) {
        this.measure_id = measure_id;
    }

    
    
    
}
