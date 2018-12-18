package FunctionLayer.entity;

import FunctionLayer.entity.Material;

/**
 * The purpose of LineItem is to set QTY for used materials, and then use this in the BillOfMaterial entity
 * @author Morten
 * @version 1.0
 * @since 27-11-2018
 */

public class LineItem {
    
    private int qty;
    private String helpDescription;
    private Material material;

    public LineItem(int qty, String helpDescription, Material material) {
        this.qty = qty;
        this.helpDescription = helpDescription;
        this.material = material;
    }
    
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getHelpDescription() {
        return helpDescription;
    }

    public void setHelpDescription(String helpDescription) {
        this.helpDescription = helpDescription;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }   

}
