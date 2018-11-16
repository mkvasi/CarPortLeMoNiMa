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
public class Carport {

    private double length, width;
    private double heigth;
    private Roof roof;
    private List<Material> materialListCarport;

    public Carport(double length, double width, Roof roof) {
        this.length = length;
        this.width = width;
        this.heigth = 2.25;
        this.roof = roof;
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

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    public List<Material> getMaterialListCarport() {
        return materialListCarport;
    }

    public void setMaterialListCarport(List<Material> materialListCarport) {
        this.materialListCarport = materialListCarport;
    }

    public void addMaterialToCarport(Material material) {
        materialListCarport.add(material);
    }

}
