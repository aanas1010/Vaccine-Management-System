package database_testing;

import databaseintegration.DatabaseClient;
import java.sql.*;

import org.junit.*;

import javax.json.JsonArray;

import static org.junit.Assert.*;

public class DatabaseClientTest {
    Connection connection;
    Statement statement;
    DatabaseClient clientDB;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5447070"; //URL of database to be connected
        connection = DriverManager.getConnection(url, "sql5447070", "AxQ1YdG6MP");
        statement = connection.createStatement();
        clientDB = new DatabaseClient(connection, statement);
    }

    @Test // Testing whether clinic IDs were loaded successfully
    public void TestLoadClients() throws SQLException {
        JsonArray results = clientDB.loadAllClients();
        assertNotNull(results);
    }

}
