package database_testing;

import databaseintegration.DatabaseAppointment;
import java.sql.*;

import org.junit.*;

import javax.json.JsonArray;

import static org.junit.Assert.*;

public class DatabaseAppointmentTest {
    Connection connection;
    Statement statement;
    DatabaseAppointment appointmentDB;

    @Before // Setting up before the tests
    public void setUp() throws Exception{
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5447070"; //URL of database to be connected
        connection = DriverManager.getConnection(url, "sql5447070", "AxQ1YdG6MP");
        statement = connection.createStatement();
        appointmentDB = new DatabaseAppointment(connection);
    }

    @Test // Testing whether appointments were loaded successfully
    public void TestLoadAppointments() throws SQLException {
        JsonArray results = appointmentDB.loadAppointments(1);
        assertNotNull(results);
    }

}
