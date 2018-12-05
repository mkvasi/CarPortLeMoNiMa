package PresentationLayer;

import FunctionLayer.exceptions.CalculatorException;
import FunctionLayer.exceptions.LoginUserException;
import FunctionLayer.exceptions.MaterialException;
import FunctionLayer.exceptions.SystemException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("carportOverview", new CarportOverview());
        commands.put("billOfMaterial", new BillOfMaterial2());
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("logout", new Logout());
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginUserException, MaterialException, SystemException ,CalculatorException;
}
