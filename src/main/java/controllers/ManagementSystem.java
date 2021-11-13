package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Interface that the Command Line uses.
 * Includes methods for calling use cases
 */

public interface ManagementSystem {

    String setEmployees(int clinicId, LocalDate date, int employees);

    String addTimePeriod(int clinicId, LocalDateTime dateTime);

    String removeTimePeriod(int clinicId, LocalDateTime dateTime);

    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval);

    String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

    ArrayList<Integer> getClinicIds();

    ArrayList<Integer> getBookableClinicIds();

    String getSupplyByClinic(int clinicId);

    String logAppointment(int clinicId, int appointmentId);

    String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand);

    StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime);

    StringBuilder logByDate(int clinicId, LocalDate date);

    String bookAppointment(int clinicId, String healthCareNumber,
                                   LocalDateTime appointmentTime, String vaccineBrand, int appointmentId);

    String cancelAppointment(int clinicId, int appointmentId);

    String viewAppointment(int clinicId, int appointmentId);
}
