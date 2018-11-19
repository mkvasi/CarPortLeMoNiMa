package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String URL = "jdbc:mysql://46.101.210.61/fog";
    private static final String USERNAME = "connect";
    private static final String PASSWORD = "connect";

    private static Connection singletonCon;

    public static void setConnection(Connection con) {
        singletonCon = con;
    }

    public static Connection connection() throws ClassNotFoundException, SQLException {
        if (singletonCon == null) {
            Class.forName("com.mysql.jdbc.Driver");
            singletonCon = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        return singletonCon;
    }

}
