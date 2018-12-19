package FunctionLayer.exceptions;

/**
 *
 * @author Morten
 */
public class ConverterMapException extends Exception {

    /**
     * Constructs an instance of <code>CalculatorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ConverterMapException(String msg) {
        super(msg);
    }

    /**
     * Laver en instans af <code> ConverterMapException </code> som smider en predefineret besked videre.
     * 
     *
     * @param msg the detail message.
     */
    public ConverterMapException() {
        super("There was a technical error - please come back later");
    }

    /**
     * Laver en instans af <code> ConverterMapException </code> som smider en predefineret besked samt en throwable videre.
     * 
     *
     * @param msg the detail message.
     */
    public ConverterMapException(Throwable thrwbl) {
        super("There was a technical error - please come back later", thrwbl);
    }

}
