package managers;

import databaseintegration.DataRetrieval;
import entities.*;

import javax.json.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the Use Case that retrieves data from a class that implements DataRetrieval
 */

public class Retriever {
    DataRetrieval dataRetrieval;

    public Retriever(DataRetrieval dataRetrieval) {
        this.dataRetrieval = dataRetrieval;
    }

    public List<Integer> getClinicIDs() {
        JsonArray clinicsJson = dataRetrieval.getClinicIDs().getJsonArray("clinicIDs");
        List<Integer> clinicsList = new ArrayList<>();

        for (int i = 0; i < clinicsJson.size(); i++){
            clinicsList.add(clinicsJson.getInt(i));
        }
        return clinicsList;

    }

    public List<Integer> getBookableClinicIDs() {
        JsonArray bookableClinicsJson = dataRetrieval.getBookableClinicIDs().getJsonArray("bookableClinicIDs");
        List<Integer> clinicsList = new ArrayList<>();

        for (int i = 0; i < bookableClinicsJson.size(); i++){
            clinicsList.add(bookableClinicsJson.getInt(i));
        }
        return clinicsList;

    }

    public List<User> getClients() {
        // Convert the Client JSON into client objects
        JsonArray clientsJson = dataRetrieval.getClients().getJsonArray("clients");
        List<User> clientsList = new ArrayList<>();
        for (int i = 0; i < clientsJson.size(); i++) {
            JsonObject thisClient = clientsJson.getJsonObject(i);
            User newClient = new Client(
                    thisClient.getString("name"),
                    thisClient.getString("healthCareID"));

            if(thisClient.getInt("hasAppointment") == 1) {
                newClient.approveAppointment();
            }

            clientsList.add(newClient);
        }

        return clientsList;
    }

    public ServiceLocation getClinicInfo(int clinicID) {
        // Convert the Clinic JSON into clinic object
        JsonObject clinicJson = dataRetrieval.getClinicInfo(clinicID);

        ServiceLocation newClinic = new Clinic.ClinicBuilder(clinicID, clinicJson.getString("location")).build();

        if(clinicJson.getInt("isBookable") == 1) {
            newClinic = new BookableClinic(newClinic);
        }

        return newClinic;
    }

    public void getVaccineBatches(ServiceLocation clinic) {
        // Convert the VaccineBatch JSON into vaccineBatch objects
        JsonArray batchJson = dataRetrieval.getVaccineBatches(clinic.getServiceLocationId()).getJsonArray("vaccineBatches");
        for (int i = 0; i < batchJson.size(); i++) {
            JsonObject thisBatch = batchJson.getJsonObject(i);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateObj = LocalDate.parse(thisBatch.getString("expiryDate").replace('T',' '), formatter);

            VaccineBatch newBatch = new VaccineBatch.BatchBuilder()
                    .brand(thisBatch.getString("brand"))
                    .quantity(thisBatch.getInt("quantity"))
                    .expiry(dateObj)
                    .id(thisBatch.getInt("batchID")).reserve(thisBatch.getInt("reserved")).build();


            clinic.addBatch(newBatch);
        }

    }

    public void getTimePeriods(ServiceLocation clinic) {
        // Convert the TimePeriod JSON into TimePeriod objects
        JsonArray timePeriodJson = dataRetrieval.getTimePeriods(clinic.getServiceLocationId()).getJsonArray("timePeriods");
        for (int i = 0; i < timePeriodJson.size(); i++) {
            JsonObject thisTimePeriod = timePeriodJson.getJsonObject(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeObj = LocalDateTime.parse(thisTimePeriod.getString("datetime").replace('T',' '), formatter);
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

    public void getAppointments(BookableClinic clinic, List<User> clients) {
        // Convert the Appointment JSON into Appointment Objects
        JsonArray appointmentJson = dataRetrieval.getAppointments(clinic.getServiceLocationId()).getJsonArray("appointments");
        for (int i = 0; i < appointmentJson.size(); i++) {
            JsonObject thisAppointment = appointmentJson.getJsonObject(i);

            TimePeriod thisTimePeriod = clinic.getTimePeriodByID(thisAppointment.getInt("periodID"));
            User thisClient = getClientByHCN(clients, thisAppointment.getString("clientID"));

            Appointment newAppointment = new Appointment.AppointmentBuilder(
                    thisClient,
                    thisTimePeriod,
                    thisAppointment.getString("brand"),
                    thisAppointment.getInt("appointmentID"),
                    //TODO: Need batchID parameter from the database
                    //thisAppointment.getInt("batchID")
                    null
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
