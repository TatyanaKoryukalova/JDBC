package jdbc.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection getConnection() throws SQLException {
        StringBuilder url = new StringBuilder();
        url.
                append("jdbc:mysql://").
                append("localhost:").
                append("3306/").
                append("db_example");
        return DriverManager.getConnection(url.toString(), "root", "00000000");
    }
}
