package FunctionLayer.entity;

import FunctionLayer.BillOfMaterial;

/**
 * 
 * @author Morten
 */
public class Price {

    private double buyprice;
    private double sellprice;

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
    
    /**
     * 
     * @param billOfMaterial 
     */
    public void calculateBuyPrice(BillOfMaterial billOfMaterial){
        buyprice = 0.00;
        
        for (LineItem lineItem : billOfMaterial.getLineItems()) {
            buyprice += lineItem.getQty() * lineItem.getMaterial().getBuyprice();
        }
    }
    
    /**
     * 
     * @param billOfMaterial 
     */
    public void calculateSellPrice(BillOfMaterial billOfMaterial){
        sellprice = 0.00;
        
        for (LineItem lineItem : billOfMaterial.getLineItems()) {
            sellprice += lineItem.getQty() * lineItem.getMaterial().getSellprice();
        }
    }
}
