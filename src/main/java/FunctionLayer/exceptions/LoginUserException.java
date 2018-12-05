package FunctionLayer.exceptions;

public class LoginUserException extends Exception {

    public LoginUserException(String msg) {
        super(msg);
    }

    public LoginUserException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    
}
