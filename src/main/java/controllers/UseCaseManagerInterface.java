package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Interface that a management system may use.
 * Includes methods for calling sub-use cases
 */

public interface UseCaseManagerInterface {
    ArrayList<Integer> getClinicIds();

    ArrayList<Integer> getBookableClinicIds();

    void addClinic(int clinicId, String location) throws Exception;

    void addBookableClinic(int clinicId, String location) throws Exception;

    void addClient(String name, String healthCardNumber) throws Exception;

    String setEmployees(int clinicId, LocalDate date, int employees);

    String addTimePeriod(int clinicId, LocalDateTime dateTime) throws Exception;

    String removeTimePeriod(int clinicId, LocalDateTime dateTime) throws Exception;

    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) throws Exception;

    String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId) throws Exception;

    String getSupplyStringByClinic(int clinicId);

    String logAppointment(int clinicId, int appointmentId) throws Exception;

    String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand) throws Exception;

    StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime) throws Exception;

    StringBuilder logByDate(int clinicId, LocalDate date) throws Exception;

    String bookAppointment(int clinicId, String healthCareNumber,
                            LocalDateTime appointmentTime, String vaccineBrand, int appointmentId) throws Exception;

    String cancelAppointment(int clinicId, int appointmentId) throws Exception;

    String viewAppointment(int clinicId, int appointmentId) throws Exception;

}
