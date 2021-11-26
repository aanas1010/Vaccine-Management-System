package databaseintegration;

import constants.BookingConstants;

import java.sql.*;
import java.util.List;

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

    public List<Integer> getClinicIDs() {
        String query = "SELECT clinicID FROM clinic";
        // The resultSet line below should probably be in the upper-level (driver-level) classes
        //ResultSet resultSet = statement.executeQuery(query);
        return null;
    }

    public ResultSet getClients() {
        return null;
    }

    public ResultSet getClinicInfo(int clinicID) {
        return null;
    }

    public ResultSet getVaccineBatches(int clinicID) {
        return null;
    }

    public ResultSet getTimePeriods(int clinicID) {
        return null;
    }

    public ResultSet getAppointments(int clinicID) {
        return null;
    }

}
