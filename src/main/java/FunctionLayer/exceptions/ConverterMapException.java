
package FunctionLayer.exceptions;

/**
 * 
 * @author Morten
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
