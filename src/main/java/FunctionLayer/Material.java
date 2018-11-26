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

    int id, qty, type_id, measure_id;
    private String description, name;
    private double buyprice, sellprice, length, width, height;

    public Material(int id, int qty, int type_id, int measure_id, String description, String name, double buyprice, double sellprice, double length, double width, double height) {
        this.id = id;
        this.qty = qty;
        this.type_id = type_id;
        this.measure_id = measure_id;
        this.description = description;
        this.name = name;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Material(int id, int type_id, int measure_id, String description, double buyprice, double sellprice, double length, double width, double height) {
        this.id = id;
        this.type_id = type_id;
        this.measure_id = measure_id;
        this.description = description;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Material(String description, double length) {
        this.description = description;
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", qty=" + qty + ", type_id=" + type_id + ", measure_id=" + measure_id + ", description=" + description + ", name=" + name + ", buyprice=" + buyprice + ", sellprice=" + sellprice + ", length=" + length + ", width=" + width + ", height=" + height + "\n";
    }

}
