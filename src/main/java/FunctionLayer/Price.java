package FunctionLayer;

/**
 * The purpose of Price is to calculate prices - both buy and sell price
 * @author Morten
 * @version 1.0
 * @since 27-11-2018
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
    
    public void calculateBuyPrice(BillOfMaterial billOfMaterial){
        buyprice = 0.00;
        
        for (LineItem materialQTY : billOfMaterial.getMaterialWithQty()) {
            buyprice += materialQTY.getQty() * materialQTY.getMaterial().getBuyprice();
        }
    }
    
    public void calculateSellPrice(BillOfMaterial billOfMaterial){
        sellprice = 0.00;
        
        for (LineItem materialQTY : billOfMaterial.getMaterialWithQty()) {
            sellprice += materialQTY.getQty() * materialQTY.getMaterial().getSellprice();
        }
    }
}
