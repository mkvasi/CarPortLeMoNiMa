/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.entity.Material;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nr
 */
public class Logout extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, MaterialException, SystemException {
        request.getSession().invalidate();
        
        
        List<String> CladdingFlatRoof = LogicFacade.getCladdingFlatRoof();
        List<Material> CladdingSlopeRoof = LogicFacade.getCladdingSlopeRoof();
        request.setAttribute("claddingflatroof", CladdingFlatRoof);
        request.setAttribute("claddingsloperoof", CladdingSlopeRoof);
        
        return "startpage";
    }   
}
