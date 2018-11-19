/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.exceptions.LoginUserException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leage
 */
public class SendRequest extends Command {

    public SendRequest() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginUserException, NoSuchAlgorithmException {
      request.getAttribute("carport"); 
      
      
      return "sendRequest";
    }
    
}
