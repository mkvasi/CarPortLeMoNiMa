/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.List;

/**
 *
 * @author leage
 */
public class BillOfMaterial {
    
    private List<LineItem> lineItems;

    public BillOfMaterial() {
    }

    public BillOfMaterial(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        return "BillOfMaterial{" + "lineItems=" + lineItems + '}';
    }
    
 

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    
    public List<LineItem> addMaterialToBOM(LineItem lineItem, List<LineItem> lineItems){
        lineItems.add(lineItem);
        return lineItems;
    }
    
}
