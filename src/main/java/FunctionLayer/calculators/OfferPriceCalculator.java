/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculators;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.Material;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leage
 */
public class OfferPriceCalculator {

    public double calculateOfferPrice(BillOfMaterial bill) {
        HashMap<String, Material> billTotal = bill.getBOM(); 
        double offerprice = 0.0;
        for (Map.Entry<String, Material> entry : billTotal.entrySet()) {
            Material material = entry.getValue();
            offerprice += (material.getSellprice()) * (material.getQty());
        }

        return Math.ceil(offerprice);
    }
}



