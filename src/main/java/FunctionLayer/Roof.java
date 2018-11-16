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
public class Roof {

    private boolean flatRoof;
    private double length, width;
    private List<Material> materialListRoof;

    public Roof(boolean flatRoof, double length, double width) {
        this.flatRoof = flatRoof;
        this.length = length;
        this.width = width;
    }

    public boolean isFlatRoof() {
        return flatRoof;
    }

    public void setFlatRoof(boolean flatRoof) {
        this.flatRoof = flatRoof;
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

    public List<Material> getMaterial() {
        return materialListRoof;
    }

    public void setMaterial(List<Material> materialListRoof) {
        this.materialListRoof = materialListRoof;
    }

    public void addMaterialToRoof(Material material) {
        materialListRoof.add(material);
    }
    
}

