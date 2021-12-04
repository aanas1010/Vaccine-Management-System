package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

/**
 * This is the interface for an adapter that loads data from a ResultSet
 * This interface corresponds to classes that lie on the "Controllers" layer of clean architecture
 */

public interface DataRetrieval {
    JsonArray getClinicIDs() throws SQLException;

    JsonArray getBookableClinicIDs() throws SQLException;

    JsonArray getClients() throws SQLException;

    JsonArray getClinicInfo(int clinicID) throws SQLException;

    JsonArray getVaccineBatches(int clinicID) throws SQLException;

    JsonArray getTimePeriods(int clinicID) throws SQLException;

    JsonArray getAppointments(int clinicID) throws SQLException;
}
