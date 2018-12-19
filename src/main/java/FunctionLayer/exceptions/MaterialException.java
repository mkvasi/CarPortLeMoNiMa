package FunctionLayer.exceptions;

/**
 * 
 * @author Morten
 */
public class MaterialException extends Exception {

    public MaterialException(String msg) {
        super(msg);
    }

    public MaterialException() {
        super("There was an error. Please try again later.");
    }

}
