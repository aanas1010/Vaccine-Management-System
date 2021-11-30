package databaseintegration;

import constants.BookingConstants;

import javax.json.JsonObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the adapter for retrieving data from the database.
 * The methods here consist of getter methods that return a query from the database.
 */

public class DatabaseRetrieval implements DataRetrieval {
    private final Connection connection;
    private final Statement statement;

    public DatabaseRetrieval() throws SQLException {
        connection = DriverManager.getConnection(
                BookingConstants.DATABASE_CONNECTION_URL,
                BookingConstants.DATABASE_CONNECTION_USERNAME,
                BookingConstants.DATABASE_CONNECTION_PASSWORD);
        statement = connection.createStatement();
    }

    public JsonObject getClinicIDs() {
        String query = "SELECT clinicID FROM clinic";
        // The resultSet line below should probably be in the upper-level (driver-level) classes
        //ResultSet resultSet = statement.executeQuery(query);
        return null;
    }

    public JsonObject getBookableClinicIDs() {return null;}

    public JsonObject getClients() {
        return null;
    }

    public JsonObject getClinicInfo(int clinicID) {
        return null;
    }

    public JsonObject getVaccineBatches(int clinicID) {
        return null;
    }

    public JsonObject getTimePeriods(int clinicID) {
        return null;
    }

    public JsonObject getAppointments(int clinicID) {
        return null;
    }

}
