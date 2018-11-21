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
public class OfferPriceCalculator {
    
    public double calculateOfferPrice(List<Material> materialList){
            double offerprice = 0.0;
        for (Material material  :  materialList) {
           offerprice += (material.getSellprice()) * (material.getQty()); 
        }
        return Math.ceil(offerprice);

}
}
