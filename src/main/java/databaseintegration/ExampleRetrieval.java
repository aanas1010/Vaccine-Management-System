package databaseintegration;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import java.time.LocalDate;


/**
 * This is a mock class that will load example data into the program
 * using the DataRetrieval interface via dependency inversion
 */

public class ExampleRetrieval implements DataRetrieval{

    /** Get the IDs of the clinics
     *
     * @return a JsonArray of the clinics
     */
    public JsonArray getClinicIDs() {
        JsonArrayBuilder clinicIDs = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("clinicID", 1)
                        .add("location", "420 Yonge St.")
                        .add("isBookable", true)
                ).add(Json.createObjectBuilder()
                        .add("clinicID", 2)
                        .add("location", "221b Baker St.")
                        .add("isBookable", true)
                ).add(Json.createObjectBuilder()
                        .add("clinicID", 3)
                        .add("location", "111 St. George st.")
                        .add("isBookable", false)
                );


        return clinicIDs.build();
    }

    /** Get the IDs of the bookable clinics
     *
     * @return a JsonArray of the bookable clinics
     */
    public JsonArray getBookableClinicIDs() {
        JsonArrayBuilder clinicIDs = Json.createArrayBuilder().add(1).add(2);
        return clinicIDs.build();
    }

    /** Get all clients
     *
     * @return a JsonArray of the clients
     */
    public JsonArray getClients() {
        return Json.createArrayBuilder()
            .add(Json.createObjectBuilder()
                    .add("healthCareID", "1111-111-111-AA")
                    .add("name", "Amy Ashcroft")
                    .add("hasAppointment", true)
            )
            .add(Json.createObjectBuilder()
                    .add("healthCareID", "2222-222-222-BB")
                    .add("name", "Bart Black")
                    .add("hasAppointment", true)
            )
            .add(Json.createObjectBuilder()
                    .add("healthCareID", "3333-333-333-CC")
                    .add("name", "Cameron Cooper")
                    .add("hasAppointment", false)
            )
            .add(Json.createObjectBuilder()
                    .add("healthCareID", "4444-444-444-DD")
                    .add("name", "Denis Dick")
                    .add("hasAppointment", false)
            )
            .add(Json.createObjectBuilder()
                    .add("healthCareID", "5555-555-555-EE")
                    .add("name", "Emily Edmonds")
                    .add("hasAppointment", false)
        ).build();
    }

    /** Get the info for a clinic
     *
     * @param clinicID the clinic whose ID we want
     * @return a JsonArray of clinics
     */
    public JsonArray getClinicInfo(int clinicID) {
        return Json.createArrayBuilder().add(
                Json.createObjectBuilder()
                .add("clinicID", clinicID)
                .add("location", "Some Location")
                .add("isBookable", true).build()
        ).build();
    }

    /** Get the batches for a clinic
     *
     * @param clinicID the ID of the clinic whose batches we want
     * @return a JsonArray of the batches
     */
    public JsonArray getVaccineBatches(int clinicID) {
        return Json.createArrayBuilder()
            .add(Json.createObjectBuilder()
                .add("batchID", 1)
                .add("clinicID", clinicID)
                .add("brand", "Pfizer")
                .add("expiryDate", LocalDate.now().plusDays(5).toString())
                .add("reserved", 1)
                .add("quantity", 100)
            )
            .add(Json.createObjectBuilder()
                    .add("batchID", 2)
                    .add("clinicID", clinicID)
                    .add("brand", "Moderna")
                    .add("expiryDate", LocalDate.now().plusDays(8).toString())
                    .add("reserved", 1)
                    .add("quantity", 500)
            )
            .add(Json.createObjectBuilder()
                    .add("batchID", 3)
                    .add("clinicID", clinicID)
                    .add("brand", "Johnson and Johnson")
                    .add("expiryDate", LocalDate.now().plusDays(10).toString())
                    .add("reserved", 0)
                    .add("quantity", 1000)
        ).build();
    }

    /** Get the time periods for a clinic
     *
     * @param clinicID the ID of the clinic whose batches we want
     * @return a JsonArray of the time periods
     */
    public JsonArray getTimePeriods(int clinicID) {
        return Json.createArrayBuilder()
            .add(Json.createObjectBuilder()
                .add("periodID", 1)
                .add("clinicID", clinicID)
                .add("availableSlots", 10)
                .add("bookedSlots", 1)
                .add("datetime", LocalDate.now().plusDays(1).
                        atTime(10, 0, 0).toString())
            )
            .add(Json.createObjectBuilder()
                .add("periodID", 2)
                .add("clinicID", clinicID)
                .add("availableSlots", 10)
                .add("bookedSlots", 1)
                .add("datetime", LocalDate.now().plusDays(1)
                        .atTime(11, 0, 0).toString())
            )
            .add(Json.createObjectBuilder()
                .add("periodID", 3)
                .add("clinicID", clinicID)
                .add("availableSlots", 10)
                .add("bookedSlots", 0)
                .add("datetime", LocalDate.now().plusDays(1)
                        .atTime(12, 0, 0).toString())
            )
            .add(Json.createObjectBuilder()
                .add("periodID", 4)
                .add("clinicID", clinicID)
                .add("availableSlots", 10)
                .add("bookedSlots", 0)
                .add("datetime", LocalDate.now().plusDays(1)
                        .atTime(13, 0, 0).toString())
            ).build();
    }

    /** Get the appointments for a bookable clinic
     *
     * @param clinicID the ID of a bookable clinic whose appoinments we want
     * @return a JsonArray of the appointments
     */
    public JsonArray getAppointments(int clinicID) {
        return Json.createArrayBuilder()
            .add(Json.createObjectBuilder()
                .add("appointmentID", 1)
                .add("clinicID", clinicID)
                .add("healthCareID", "1111-111-111-AA")
                .add("periodID", 1)
                .add("brand", "Pfizer")
                .add("batchID", 1)
            )
            .add(Json.createObjectBuilder()
                .add("appointmentID", 2)
                .add("clinicID", clinicID)
                .add("healthCareID", "2222-222-222-BB")
                .add("periodID", 2)
                .add("brand", "Moderna")
                .add("batchID", 2)
            ).build();
    }
}
