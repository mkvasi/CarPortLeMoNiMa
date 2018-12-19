package FunctionLayer.entity;

import FunctionLayer.BillOfMaterial;

/**
 * Denne klasse indeholder variabler, konstruktør, gettere & settere for en pris.
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
     * Tager imod en BillOfMaterial for at udregne købsprisen for tilhørende lineItems.
     * @param billOfMaterial 
     */
    public void calculateBuyPrice(BillOfMaterial billOfMaterial){
        buyprice = 0.00;
        
        for (LineItem lineItem : billOfMaterial.getLineItems()) {
            buyprice += lineItem.getQty() * lineItem.getMaterial().getBuyprice();
        }
    }
    
    /**
     * Tager imod en BillOfMaterial for at udregne salgsprisen for tilhørende lineItems.
     * @param billOfMaterial 
     */
    public void calculateSellPrice(BillOfMaterial billOfMaterial){
        sellprice = 0.00;
        
        for (LineItem lineItem : billOfMaterial.getLineItems()) {
            sellprice += lineItem.getQty() * lineItem.getMaterial().getSellprice();
        }
    }
}
