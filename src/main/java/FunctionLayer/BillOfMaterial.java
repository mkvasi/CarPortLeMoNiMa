
package FunctionLayer;

import FunctionLayer.entity.LineItem;
import java.util.List;

/**
 * Meningen med denne klasse er at danne en samlet billofmaterial (stykliste)
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
    
    /**
     * Meningen med denne metode er at tilføje et lineitem bestående af Qty, Hjælpetekst og Materiale til denne billof material (stykliste)
     * @param lineItem
     * @param lineItems
     * @return List
     */
    public List<LineItem> addMaterialToBOM(LineItem lineItem, List<LineItem> lineItems){
        lineItems.add(lineItem);
        return lineItems;
    }
    
}
