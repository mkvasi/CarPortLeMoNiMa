/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.HashMap;

/**
 *
 * @author leage
 */
public class BillOfMaterial {
    HashMap<String,Material> BOM;

    public BillOfMaterial(HashMap<String, Material> BOM) {
        this.BOM = BOM;
    }

    public HashMap<String, Material> getBOM() {
        return BOM;
    }

    public void setBOM(HashMap<String, Material> BOM) {
        this.BOM = BOM;
    }
    
    
}
