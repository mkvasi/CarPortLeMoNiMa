
package FunctionLayer.exceptions;

/**
 * 
 * @author Morten
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

    /**
     * Laver en instans af <code> CalculatorException </code> som smider en predefineret besked videre.
     * 
     *
     * @param msg the detail message.
     */
    
    public CalculatorException() {
        super("There was a technical error - please come back later");
    }

    /**
     * Laver en instans af <code> CalculatorException </code> som smider en predfineret besked samt en throwable videre.
     * 
     *
     * @param msg the detail message.
     */
    
    public CalculatorException(Throwable thrwbl) {
        super("There was a technical error - please come back later", thrwbl);
    }

}
