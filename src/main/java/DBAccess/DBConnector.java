package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {

    //private static final String URL = "jdbc:mysql://localhost/fog?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    private static final String URL = "jdbc:mysql://46.101.210.61/fog";
    private static final String URL = "jdbc:mysql://188.166.86.13/fog";
    private static final String USERNAME = "connect";
    private static final String PASSWORD = "connect";

    private static Connection singletonCon;

    public static void setConnection(Connection con) {
        singletonCon = con;
    }

    public static Connection connection() throws SQLException {
        if (singletonCon == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                singletonCon = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            singletonCon = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        return singletonCon;
    }

}
