package databaseintegration;

import java.sql.*;

public class DatabaseConnectivityManager {
    private Connection myConn;
    private Statement statement;

    public DatabaseConnectivityManager() throws SQLException {

        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5447070";
        myConn = DriverManager.getConnection(url, "sql5447070", "AxQ1YdG6MP");
        statement = myConn.createStatement();
    }
}
