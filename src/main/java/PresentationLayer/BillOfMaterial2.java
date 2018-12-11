/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.entity.Carport;
import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Material;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leage
 */
public class BillOfMaterial2 extends Command {

    public BillOfMaterial2() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, MaterialException {
       Carport carport = (Carport) request.getAttribute("carport"); 
      
      //HashMap carportWithDBMaterials = LogicFacade.getAllMaterialsFromDB(carport);
       //HashMap<String, Material> billOfMaterial = LogicFacade.getDoneCarportWithMaterialList(carport); 
       
     // billOfMaterial.setBOM((FunctionLayer.BillOfMaterial2) request.getAttribute("billOfMaterial"));
      
      //request.setAttribute("billOfMaterial", billOfMaterial);
      request.setAttribute("carport", carport);
      return "billOfMaterial";
    }
    
}
