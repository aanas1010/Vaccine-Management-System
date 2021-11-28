package managers;

import databaseintegration.DataRetrieval;
import databaseintegration.DataStoring;
import entities.*;

import javax.json.JsonObject;
import java.sql.ResultSet;
import java.util.List;

/**
 * This is the Use Case that retrieves data from a class that implements DataRetrieval
 */

public class Retriever {
    DataRetrieval dataRetrieval;

    public Retriever(DataRetrieval dataRetrieval) {
        this.dataRetrieval = dataRetrieval;
    }

    public JsonObject getClinicIDs() {
        return dataRetrieval.getClinicIDs();
    }

    public List<Client> getClients() {
        // Convert the Client JSON into client objects
        Object clients = dataRetrieval.getClients();
        return null;
    }

    public Clinic getClinicInfo(int clinicID) {
        // Convert the Clinic JSON into clinic object
        Object clinicInfo = dataRetrieval.getClinicInfo(clinicID);
        return null;
    }

    public List<VaccineBatch> getVaccineBatches(int clinicID) {
        // Convert the VaccineBatch JSON into vaccineBatch objects
        Object vaccineBatches = dataRetrieval.getVaccineBatches(clinicID);
        return null;
    }

    public List<TimePeriod> getTimePeriods(int clinicID) {
        // Convert the TimePeriod JSON into TimePeriod objects
        Object timePeriods = dataRetrieval.getTimePeriods(clinicID);
        return null;
    }

    public List<Appointment> getAppointments(int clinicID) {
        // Convert the Appointment JSON into Appointment Objects
        Object appointments = dataRetrieval.getAppointments(clinicID);
        return null;
    }
}
