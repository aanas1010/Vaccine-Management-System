package managers;

import databaseintegration.DataRetrieval;
import entities.*;

import javax.json.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Use Case that retrieves data from a class that implements DataRetrieval
 */

public class Retriever {
    DataRetrieval dataRetrieval;

    public Retriever(DataRetrieval dataRetrieval) {
        this.dataRetrieval = dataRetrieval;
    }

    public List<Integer> getClinicIDs() throws SQLException {
        JsonArray clinicsJson = dataRetrieval.getClinicIDs();
        List<Integer> clinicsList = new ArrayList<>();

        for (int i = 0; i < clinicsJson.size(); i++){
            clinicsList.add(clinicsJson.getJsonObject(i).getInt("clinicID"));
        }

        return clinicsList;
    }

    public List<Integer> getBookableClinicIDs() throws SQLException {
        JsonArray bookableClinicsJson = dataRetrieval.getBookableClinicIDs();
        List<Integer> clinicsList = new ArrayList<>();

        for (int i = 0; i < bookableClinicsJson.size(); i++){
            clinicsList.add(bookableClinicsJson.getInt(i));
        }
        return clinicsList;

    }

    public List<User> getClients() throws SQLException {
        // Convert the Client JSON into client objects

        JsonArray clientsJson = dataRetrieval.getClients();
        List<User> clientsList = new ArrayList<>();
        for (int i = 0; i < clientsJson.size(); i++) {
            JsonObject thisClient = clientsJson.getJsonObject(i);
            User newClient = new Client(
                    thisClient.getString("name"),
                    thisClient.getString("healthCareID"));

            if(thisClient.getBoolean("hasAppointment")) {
                newClient.approveAppointment();
            }

            clientsList.add(newClient);
        }

        return clientsList;
    }

    public ServiceLocation getClinicInfo(int clinicID) throws SQLException {
        // Convert the Clinic JSON into clinic object
        JsonArray clinicJson = dataRetrieval.getClinicInfo(clinicID);

        // Only get the clinicID's clinic
        ServiceLocation newClinic;
        for(int i=0;i<clinicJson.size();i++) {
            if(clinicJson.getJsonObject(0).getInt("clinicID") == clinicID) {
                newClinic = new Clinic.ClinicBuilder(
                        clinicID,
                        clinicJson.getJsonObject(0)
                                .getString("location")).build();
                if(clinicJson.getJsonObject(0).getBoolean("isBookable")) {
                    newClinic = new BookableClinic(newClinic);
                }
                return newClinic;
            }
        }

        // Clinic could not be found (should be impossible since we only call this action
        // with an ID from getClinicIDs

        return null;

    }

    public void getVaccineBatches(ServiceLocation clinic) throws SQLException {
        // Convert the VaccineBatch JSON into vaccineBatch objects
        JsonArray batchJson = dataRetrieval.getVaccineBatches(clinic.getServiceLocationId());
        for (int i = 0; i < batchJson.size(); i++) {
            JsonObject thisBatch = batchJson.getJsonObject(i);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateObj = LocalDate.parse(
                    thisBatch.getString("expiryDate")
                            .replace('T',' '), formatter);

            VaccineBatch newBatch = new VaccineBatch.BatchBuilder()
                    .brand(thisBatch.getString("brand"))
                    .quantity(thisBatch.getInt("quantity"))
                    .expiry(dateObj)
                    .id(thisBatch.getInt("batchID")).reserve(thisBatch.getInt("reserved")).build();


            clinic.addBatch(newBatch);
        }

    }

    public void getTimePeriods(ServiceLocation clinic) throws SQLException {
        // Convert the TimePeriod JSON into TimePeriod objects
        JsonArray timePeriodJson = dataRetrieval.getTimePeriods(clinic.getServiceLocationId());
        for (int i = 0; i < timePeriodJson.size(); i++) {
            JsonObject thisTimePeriod = timePeriodJson.getJsonObject(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeObj = LocalDateTime.parse(
                    thisTimePeriod.getString("datetime")
                            .replace('T',' '), formatter);
            LocalDate dateObj = dateTimeObj.toLocalDate();

            TimePeriod newTimePeriod = new TimePeriod(
                    dateTimeObj,
                    thisTimePeriod.getInt("availableSlots"),
                    thisTimePeriod.getInt("periodID")
            );

            for(int j = 0; j < thisTimePeriod.getInt("bookedSlots"); j++) {
                newTimePeriod.findAndReserveSlot();
            }
            clinic.addTimePeriod(newTimePeriod, dateObj);
        }
    }

    public void getAppointments(BookableClinic clinic, List<User> clients) throws SQLException {
        // Convert the Appointment JSON into Appointment Objects
        JsonArray appointmentJson = dataRetrieval.getAppointments(clinic.getServiceLocationId());
        for (int i = 0; i < appointmentJson.size(); i++) {
            JsonObject thisAppointment = appointmentJson.getJsonObject(i);

            TimePeriod thisTimePeriod = clinic.getTimePeriodByID(thisAppointment.getInt("periodID"));
            User thisClient = getClientByHCN(clients, thisAppointment.getString("clientID"));

            VaccineBatch thisBatch = null;
            for(VaccineBatch batch : clinic.getSupply()) {
                if(batch.getId() == thisAppointment.getInt("batchID")) {
                    thisBatch = batch;
                }
            }

            if(thisBatch == null) {
                // If the appointment's batch cannot be found, do not add it
                System.out.println("Could not find batch #" +
                        thisAppointment.getInt("batchID") + " for appointment #" +
                        thisAppointment.getInt("appointmentID"));
                return;
            }

            Appointment newAppointment = new Appointment.AppointmentBuilder(
                    thisClient,
                    thisTimePeriod,
                    thisAppointment.getString("brand"),
                    thisAppointment.getInt("appointmentID"),
                    thisBatch
            ).build();

            clinic.addAppointment(newAppointment);
        }
    }

    private User getClientByHCN(List<User> clients, String ID) {
        for(User client : clients) {
            if(client.getHealthCareNumber().equals(ID)) {
                return client;
            }
        }
        return null;
    }
}
