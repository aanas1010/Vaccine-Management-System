package databaseintegration;

import constants.BookingConstants;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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

    public JsonArray getClinicIDs() throws SQLException {
        return new DatabaseClinic(connection, statement).loadAllClinics();
    }

    public JsonArray getBookableClinicIDs() throws SQLException {
        JsonArray clinics = new DatabaseClinic(connection, statement).loadAllClinics();

        JsonArrayBuilder clinicIDs = Json.createArrayBuilder();
        for (int i = 0; i < clinics.size(); i++) {
            JsonObject thisClinic = clinics.getJsonObject(i);
            clinicIDs.add(thisClinic.getInt("clinicID"));
        }

        return clinicIDs.build();

    }

    public JsonArray getClients() throws SQLException {
        return new DatabaseClient(connection, statement).loadAllClients();
    }

    public JsonArray getClinicInfo(int clinicID) throws SQLException {
        return new DatabaseClinic(connection, statement).loadAllClinics();
    }

    public JsonArray getVaccineBatches(int clinicID) throws SQLException {
        return new DatabaseBatchAdding(connection, statement).loadAllBatches();
    }

    public JsonArray getTimePeriods(int clinicID) throws SQLException {
        return new DatabaseTimePeriods(connection, statement).loadAllTimePeriods();
    }

    public JsonArray getAppointments(int clinicID) throws SQLException {
        return new DatabaseAppointment(connection, statement).loadAllAppointments();
    }

}
