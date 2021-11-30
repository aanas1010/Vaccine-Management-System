package databaseintegration;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.time.LocalDate;


/**
 * This is a mock class that will load example data into the program
 * using the DataRetrieval interface via dependency inversion
 */

public class ExampleRetrieval implements DataRetrieval{
    public JsonObject getClinicIDs() {
        JsonArrayBuilder clinicIDs = Json.createArrayBuilder();
        for(int i=0;i<10;i++) {
            clinicIDs.add(i);
        }

        return Json.createObjectBuilder().add("clinicIDs", clinicIDs).build();
    }

    public JsonObject getBookableClinicIDs() {
        JsonArrayBuilder clinicIDs = Json.createArrayBuilder();
        for(int i=1;i<10;i=i+2) {
            clinicIDs.add(i);
        }

        return Json.createObjectBuilder().add("bookableClinicIDs", clinicIDs).build();
    }

    public JsonObject getClients() {
        return Json.createObjectBuilder().add("clients",
                Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("healthCareID", "1111-111-111-AA")
                                .add("name", "Amy Ashcroft")
                                .add("hasAppointment", 1)
                        )
                        .add(Json.createObjectBuilder()
                                .add("healthCareID", "2222-222-222-BB")
                                .add("name", "Bart Black")
                                .add("hasAppointment", 1)
                        )
                        .add(Json.createObjectBuilder()
                                .add("healthCareID", "3333-333-333-CC")
                                .add("name", "Cameron Cooper")
                                .add("hasAppointment", 0)
                        )
                        .add(Json.createObjectBuilder()
                                .add("healthCareID", "4444-444-444-DD")
                                .add("name", "Denis Dick")
                                .add("hasAppointment", 0)
                        )
                        .add(Json.createObjectBuilder()
                                .add("healthCareID", "5555-555-555-EE")
                                .add("name", "Emily Edmonds")
                                .add("hasAppointment", 0)
                        )
        ).build();
    }

    public JsonObject getClinicInfo(int clinicID) {
        return Json.createObjectBuilder()
                .add("clinicID", clinicID)
                .add("location", "Some Location")
                .add("isBookable", clinicID % 2).build();
    }

    public JsonObject getVaccineBatches(int clinicID) {
        return Json.createObjectBuilder()
                .add("vaccineBatches", Json.createArrayBuilder()
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
                        )
                ).build();
    }

    public JsonObject getTimePeriods(int clinicID) {
        return Json.createObjectBuilder()
                .add("timePeriods", Json.createArrayBuilder()
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
                        )
                ).build();
    }

    public JsonObject getAppointments(int clinicID) {
        return Json.createObjectBuilder()
                .add("appointments", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("appointmentID", 1)
                                .add("clinicID", clinicID)
                                .add("clientID", "1111-111-111-AA")
                                .add("periodID", 1)
                                .add("brand", "Pfizer")
                        )
                        .add(Json.createObjectBuilder()
                                .add("appointmentID", 2)
                                .add("clinicID", clinicID)
                                .add("clientID", "2222-222-222-BB")
                                .add("periodID", 2)
                                .add("brand", "Moderna")
                        )
                ).build();
    }
}
