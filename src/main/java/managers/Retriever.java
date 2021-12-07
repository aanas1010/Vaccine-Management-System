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
    final DataRetrieval dataRetrieval;

    public Retriever(DataRetrieval dataRetrieval) {
        this.dataRetrieval = dataRetrieval;
    }

    /**
     * Get a list of the clinic IDs from the DataRetrieval instance
     *
     * @return the list of valid clinic IDs
     * @throws SQLException if the query could not go through
     */
    public List<Integer> getClinicIDs() throws SQLException {
        JsonArray clinicsJson = dataRetrieval.getClinicIDs();
        List<Integer> clinicsList = new ArrayList<>();

        for (int i = 0; i < clinicsJson.size(); i++){
            clinicsList.add(clinicsJson.getJsonObject(i).getInt("clinicID"));
        }

        return clinicsList;
    }

    /**
     * Get a list of the bookable clinic IDs from the DataRetrieval instance
     *
     * @return the list of bookable clinic IDs
     * @throws SQLException if the query could not go through
     */
    public List<Integer> getBookableClinicIDs() throws SQLException {
        JsonArray bookableClinicsJson = dataRetrieval.getBookableClinicIDs();
        List<Integer> clinicsList = new ArrayList<>();

        for (int i = 0; i < bookableClinicsJson.size(); i++){
            clinicsList.add(bookableClinicsJson.getInt(i));
        }
        return clinicsList;

    }

    /**
     * Get a list of the clinic IDs from the DataRetrieval instance
     *
     * @return the list of users
     * @throws SQLException if the query could not go through
     */
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

    /**
     * Get a list of the clinic IDs from the DataRetrieval instance
     *
     * @param clinicID the ID of the clinic that we want
     * @return the instance of ServiceLocatino relating to the clinicID
     * @throws SQLException if the query could not go through
     */
    public ServiceLocation getClinicInfo(int clinicID) throws SQLException {
        // Convert the Clinic JSON into clinic object
        JsonArray clinicJson = dataRetrieval.getClinicInfo(clinicID);

        // Only get the clinicID's clinic
        ServiceLocation newClinic;
        for(int i=0;i<clinicJson.size();i++) {
            if(clinicJson.getJsonObject(i).getInt("clinicID") == clinicID) {
                newClinic = new Clinic.ClinicBuilder().clinicId(clinicID).location(clinicJson.getJsonObject(i)
                        .getString("location")).build();
                if(clinicJson.getJsonObject(i).getBoolean("isBookable")) {
                    newClinic = new BookableClinic(newClinic);
                }
                return newClinic;
            }
        }

        // Clinic could not be found (should be impossible since we only call this action
        // with an ID from getClinicIDs

        return null;

    }

    /**
     * Add the batches to a specified ServiceLocation instance
     *
     * @param clinic the instance of ServiceLocation that we want to add batches to
     * @throws SQLException if the query could not go through
     */
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

    /**
     * Add the time periods to a specified ServiceLocation instance
     *
     * @param clinic the instance of ServiceLocation that we want to add time periods to
     * @throws SQLException if the query could not go through
     */
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
                    thisTimePeriod.getInt("bookedSlots"),
                    thisTimePeriod.getInt("periodID")
            );

            clinic.addTimePeriod(newTimePeriod, dateObj);
        }
    }

    /**
     * Add the appointments to a specified BookableClinic instance
     *
     * @param clinic the instance of ServiceLocation that we want to add appointments to
     * @param clients the list of Users in the system to be linked with the appointments
     * @throws SQLException if the query could not go through
     */
    public void getAppointments(BookableClinic clinic, List<User> clients) throws SQLException {
        // Convert the Appointment JSON into Appointment Objects
        JsonArray appointmentJson = dataRetrieval.getAppointments(clinic.getServiceLocationId());
        for (int i = 0; i < appointmentJson.size(); i++) {
            JsonObject thisAppointment = appointmentJson.getJsonObject(i);
            TimePeriod thisTimePeriod = clinic.getTimePeriodByID(thisAppointment.getInt("periodID"));
            User thisClient = getClientByHCN(clients, thisAppointment.getString("healthCareID"));

            VaccineBatch thisBatch = getVaccineBatch(clinic, thisAppointment);

            if(thisBatch == null) {
                // If the appointment's batch cannot be found, do not add it
                System.out.println("Could not find batch #" +
                        thisAppointment.getInt("batchID") + " for appointment #" +
                        thisAppointment.getInt("appointmentID"));
                return;
            }

            Appointment newAppointment = new Appointment.AppointmentBuilder()
                    .client(thisClient)
                    .timePeriod(thisTimePeriod)
                    .vaccineBrand(thisAppointment.getString("brand"))
                    .appointmentID(thisAppointment.getInt("appointmentID"))
                    .clientVaccineBatch(thisBatch)
                .build();

            clinic.addAppointment(newAppointment);
        }
    }

    /**
     * Get the vaccineBatch that corresponds to the batchID of a specified appointment
     *
     * @param clinic the instance of BookableClinic that contains the batch that we want
     * @param thisAppointment the JsonObject of the appointment that contains the batchID
     * @return the instance of VaccineBatch with the id provided
     */
    private VaccineBatch getVaccineBatch(BookableClinic clinic, JsonObject thisAppointment) {
        VaccineBatch thisBatch = null;
        for(VaccineBatch batch : clinic.getSupply()) {
            if(batch.getId() == thisAppointment.getInt("batchID")) {
                thisBatch = batch;
            }
        }
        return thisBatch;
    }

    /**
     * Get the User from a list of User instance that has a specified health care number
     *
     * @param clients the list of Users in the system to be linked with the appointments
     * @param ID the health care number whose client we want
     * @return the User instance that corresponds to the ID
     */
    private User getClientByHCN(List<User> clients, String ID) {
        for(User client : clients) {
            if(client.getHealthCareNumber().equals(ID)) {
                return client;
            }
        }
        return null;
    }
}
