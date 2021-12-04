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

public class DatabaseModification implements DataModification {
    private final Connection connection;
    private final Statement statement;

    public DatabaseModification() throws SQLException {
        connection = DriverManager.getConnection(
                BookingConstants.DATABASE_CONNECTION_URL,
                BookingConstants.DATABASE_CONNECTION_USERNAME,
                BookingConstants.DATABASE_CONNECTION_PASSWORD);

        statement = connection.createStatement();
    }


    public void writeToAppointment(int appointmentID, int clinicID, String clientID, int periodID, int batchID,
                                   String brand) throws SQLException {
        String query = getQuery("appointment", appointmentID, clinicID, clientID, periodID, brand);
        connection.prepareStatement(query);
        new DatabaseAppointment(connection, statement).addAppointment(appointmentID, clinicID, clientID, periodID, batchID, brand);
    }


    public void writeToClient(String healthCardID, String name, boolean hasAppointment) throws SQLException {
        String query = getQuery("client", healthCardID, name, hasAppointment);
        connection.prepareStatement(query);
        new DatabaseClient(connection, statement).addClient(healthCardID, name, hasAppointment);
    }

    public void writeToTimePeriods(int periodID, int clinicID, int availableSlots, int bookedSlots, LocalDateTime datetime)
            throws SQLException {
        String query = getQuery("timePeriods", periodID, clinicID, availableSlots, datetime);
        connection.prepareStatement(query);
        new DatabaseTimePeriods(connection, statement).addTimePeriod(periodID, clinicID, availableSlots, bookedSlots, Timestamp.valueOf(datetime));
    }

    public void writeToVaccineBatch(int batchID, int clinicID, String brand,
                                    LocalDate expiryDate, int reserved, int quantity) throws SQLException {
        String query = getQuery("vaccineBatch", batchID, clinicID, brand, expiryDate, reserved, quantity);
        connection.prepareStatement(query);
        new DatabaseBatchAdding(connection, statement).addBatch(batchID, clinicID, brand, Date.valueOf(expiryDate), reserved, quantity);
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
