/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.exceptions;

/**
 *
 * @author nr
 */
public class CalculatorException extends Exception {

    /**
     * Creates a new instance of <code>CalculatorException</code> without detail
     * message.
     */

    /**
     * Constructs an instance of <code>CalculatorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CalculatorException(String msg) {
        super(msg);
    }

    public CalculatorException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public CalculatorException(Throwable thrwbl) {
        super(thrwbl);
    }
    
    
}
