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
public class ConverterMapException extends Exception {

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
    
    public ConverterMapException(String msg) {
        super(msg);
    }

    public ConverterMapException() {
        super("There was a technical error - please come back later");
    }

    public ConverterMapException(Throwable thrwbl) {
        super("There was a technical error - please come back later", thrwbl);
    }

}
