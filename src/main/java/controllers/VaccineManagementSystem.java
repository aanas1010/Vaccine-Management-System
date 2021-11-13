package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Controller for a vaccine management system
 * that communicates with the use case interfaces
 */

public class VaccineManagementSystem implements ManagementSystem {
    private final UseCaseManagerInterface useCaseManagerInterface;

    // Constructor

    public VaccineManagementSystem(UseCaseManagerInterface useCaseManager) {
        this.useCaseManagerInterface = useCaseManager;
    }

    public String setEmployees(int clinicId, LocalDate date, int employees) {
        return useCaseManagerInterface.setEmployees(clinicId, date, employees);
    }

    public String addTimePeriod(int clinicId, LocalDateTime dateTime) throws Exception {
        return useCaseManagerInterface.addTimePeriod(clinicId, dateTime);
    }

    public String removeTimePeriod(int clinicId, LocalDateTime dateTime) throws Exception {
        return useCaseManagerInterface.removeTimePeriod(clinicId, dateTime);
    }

    public int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) throws Exception {
        return useCaseManagerInterface.addMultipleTimePeriods(clinicId, start, end, interval);
    }

    public String logAppointment(int clinicId, int appointmentId) throws Exception {
        return useCaseManagerInterface.logAppointment(clinicId, appointmentId);
    }

    public String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand) throws Exception {
        return useCaseManagerInterface.logWalkIn(clinicId, vaccinationID, clientHCN, dateTime, brand);
    }

    public StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime) throws Exception {
        return useCaseManagerInterface.logByDateTime(clinicId, dateTime);
    }

    public StringBuilder logByDate(int clinicId, LocalDate date) throws Exception {
        return useCaseManagerInterface.logByDate(clinicId, date);
    }


    public String bookAppointment(int clinicId, String healthCareNumber,
                                   LocalDateTime appointmentTime, String vaccineBrand, int appointmentId) throws Exception {
        return useCaseManagerInterface.bookAppointment(clinicId, healthCareNumber,
                appointmentTime, vaccineBrand, appointmentId);
    }

    public String cancelAppointment(int clinicId, int appointmentId) throws Exception {
        return useCaseManagerInterface.cancelAppointment(clinicId, appointmentId);
    }

    public String viewAppointment(int clinicId, int appointmentId) throws Exception {
        return useCaseManagerInterface.viewAppointment(clinicId, appointmentId);
    }

    // Add a batch to the specified clinic given the parameters
    // Returns whether the batch was added
    public String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId) throws Exception {
        return this.useCaseManagerInterface.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);
    }

    // Getters
    public ArrayList<Integer> getClinicIds() {
        return this.useCaseManagerInterface.getClinicIds();
    }

    public ArrayList<Integer> getBookableClinicIds() {return this.useCaseManagerInterface.getBookableClinicIds();}



    public String getSupplyByClinic(int clinicId) {return this.useCaseManagerInterface.getSupplyStringByClinic(clinicId);}
}