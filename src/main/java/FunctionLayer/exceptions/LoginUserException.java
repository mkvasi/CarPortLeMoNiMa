package FunctionLayer.exceptions;

/**
 * 
 * @author Morten
 */
public class LoginUserException extends Exception {

    /**
     * Constructs an instance of <code>SystemException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LoginUserException(String msg) {
        super(msg);
    }

    
    /**
     * Denne exception tager imod en String samt en Throwable, og smider videre op af.
     * 
     *
     * @param msg the detail message.
     */
    public LoginUserException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    
}
