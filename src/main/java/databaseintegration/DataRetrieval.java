package databaseintegration;

import java.sql.ResultSet;
import java.util.List;

/**
 * This is the interface for an adapter that loads data from a ResultSet
 */

public interface DataRetrieval {
    List<Integer> getClinicIDs();

    ResultSet getClients();

    ResultSet getClinicInfo(int clinicID);

    ResultSet getVaccineBatches(int clinicID);

    ResultSet getTimePeriods(int clinicID);

    ResultSet getAppointments(int clinicID);
}
