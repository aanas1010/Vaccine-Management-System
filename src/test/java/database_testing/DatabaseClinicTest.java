package database_testing;

import databaseintegration.DatabaseClinic;
import java.sql.*;

import org.junit.*;

import javax.json.JsonArray;

import static org.junit.Assert.*;

public class DatabaseClinicTest {
    Connection connection;
    Statement statement;
    DatabaseClinic clinicDB;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5447070"; //URL of database to be connected
        connection = DriverManager.getConnection(url, "sql5447070", "AxQ1YdG6MP");
        statement = connection.createStatement();
        clinicDB = new DatabaseClinic(statement);
    }

    @Test // Testing whether clinic IDs were loaded successfully
    public void TestLoadClinicIDs() throws SQLException {
        JsonArray results = clinicDB.loadAllClinics();
        assertNotNull(results);
    }

}
