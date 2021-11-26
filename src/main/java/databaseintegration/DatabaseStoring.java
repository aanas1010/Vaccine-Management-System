package databaseintegration;

import constants.BookingConstants;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This is the adapter for writing data into the database.
 * The methods here consist of writing methods that write to
 * specific tables in a database, whose URL is specified in BookingConstants
 */

public class DatabaseStoring implements DataStoring {
    private final Connection connection;
    private final Statement statement;

    public DatabaseStoring() throws SQLException {
        connection = DriverManager.getConnection(
                BookingConstants.DATABASE_CONNECTION_URL,
                BookingConstants.DATABASE_CONNECTION_USERNAME,
                BookingConstants.DATABASE_CONNECTION_PASSWORD);
        statement = connection.createStatement();
    }


    public void writeToAppointment(int appointmentID, int clinicID, int clientID, int periodID, String brand) throws SQLException {
        // Create new DatabaseRetrieval instance
        // Do the thing with it
        String query = getQuery("appointment", appointmentID, clinicID, clientID, periodID, brand);
        PreparedStatement state =  connection.prepareStatement(query);
        // Then, reference the driver-level class for this table
        // doSomething(state, query);
    }


    public void writeToClient(int healthCardID, String name, boolean hasAppointment) throws SQLException {
        String query = getQuery("client", healthCardID, name, hasAppointment);
        PreparedStatement state =  connection.prepareStatement(query);
        // doSomething(state, query);
    }

    public void writeToClinic(int clinicID, String location, boolean isBookable) {
        /**
         * NOTE: SINCE WE DO NOT HAVE THE USE CASE TO ADD NEW CLINICS,
         * IS THIS REALLY NECESSARY? OR SHOULD THIS JUST BE A "READ-ONLY" TABLE?
        */
    }

    public void writeToTimePeriods(int periodID, int clinicID, int availableSlots, LocalDateTime datetime)
            throws SQLException {
        String query = getQuery("timePeriods", periodID, clinicID, availableSlots, datetime);
        PreparedStatement state =  connection.prepareStatement(query);
        // doSomething(state, query);
    }

    public void writeToVaccineBatch(int batchID, int clinicID, String brand,
                                    LocalDate expiryDate, int reserved, int quantity) throws SQLException {
        String query = getQuery("vaccineBatch", batchID, clinicID, brand, expiryDate, reserved, quantity);
        PreparedStatement state =  connection.prepareStatement(query);
        // doSomething(state, query);
    }



    private String getQuery(String table, Object ... o) {
        StringBuilder query = new StringBuilder("INSERT INTO " + table + " VALUES (");
        for(int i = 0;i<o.length;i++) {
            query.append(i);
            if(i < o.length - 1) {
                query.append(", ");
            }
        }
        query.append(")");
        return query.toString();
    }

}
