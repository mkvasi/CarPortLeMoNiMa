
package FunctionLayer;

import FunctionLayer.entity.LineItem;
import java.util.List;

/**
 * 
 * @author Morten
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
