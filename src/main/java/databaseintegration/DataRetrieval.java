package databaseintegration;

import javax.json.JsonObject;

/**
 * This is the interface for an adapter that loads data from a ResultSet
 * This interface corresponds to classes that lie on the "Controllers" layer of clean architecture
 */

public interface DataRetrieval {
    JsonObject getClinicIDs();

    JsonObject getBookableClinicIDs();

    JsonObject getClients();

    JsonObject getClinicInfo(int clinicID);

    JsonObject getVaccineBatches(int clinicID);

    JsonObject getTimePeriods(int clinicID);

    JsonObject getAppointments(int clinicID);
}
