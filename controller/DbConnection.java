package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DbConnection {

    private static final String user = "root";
    private static final String password = "ionela0107";
    private static final String url = "jdbc:mysql://localhost:3306/train";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, password);
    }
}
