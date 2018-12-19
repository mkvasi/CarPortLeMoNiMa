package FunctionLayer.exceptions;

/**
 * 
 * @author Morten
 */
public class MaterialException extends Exception {
    
    /**
     * Constructs an instance of <code>SystemException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MaterialException(String msg) {
        super(msg);
    }

    /**
     * Denne exception smider en predefineret besked videre op af
     * 
     *
     * @param msg the detail message.
     */
    public MaterialException() {
        super("There was an error. Please try again later.");
    }

}
