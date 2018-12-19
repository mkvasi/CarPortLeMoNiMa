package FunctionLayer.exceptions;

/**
 * 
 * @author Morten
 */
public class SystemException extends Exception {

    /**
     * Creates a new instance of <code>SystemException</code> without detail
     * message.
     */
    public SystemException() {
    }

    /**
     * Constructs an instance of <code>SystemException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(Throwable thrwbl) {
        super("There was a technical error - please contact IT-Support", thrwbl);
    }
    
    
}
