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
      
      request.setAttribute("carport", carport);
      return "billOfMaterial";
    }
    
}
