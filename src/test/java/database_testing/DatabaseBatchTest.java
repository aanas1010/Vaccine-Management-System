package database_testing;

import databaseintegration.DatabaseBatch;
import java.sql.*;

import org.junit.*;

import javax.json.JsonArray;

import static org.junit.Assert.*;

public class DatabaseBatchTest {
    Connection connection;
    Statement statement;
    DatabaseBatch batchDB;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5447070"; //URL of database to be connected
        connection = DriverManager.getConnection(url, "sql5447070", "AxQ1YdG6MP");
        statement = connection.createStatement();
        batchDB = new DatabaseBatch(connection);
    }

    @Test // Testing whether batches were loaded successfully
    public void TestLoadClinicIDs() throws SQLException {
        JsonArray results = batchDB.loadBatches(1);
        assertNotNull(results);
    }

}
