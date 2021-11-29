package managers;

import constants.ManagementSystemException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // Data permanence

    public boolean loadInitialData(){
        return useCaseManagerInterface.loadInitialData();
    }

    public boolean loadClinicData(int clinicID) {
        return useCaseManagerInterface.loadClinicData(clinicID);
    }

    public String setEmployees(int clinicId, LocalDate date, int employees) {
        return useCaseManagerInterface.setEmployees(clinicId, date, employees);
    }

    public String addTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        return useCaseManagerInterface.addTimePeriod(clinicId, dateTime);
    }

    public String removeTimePeriod(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        return useCaseManagerInterface.removeTimePeriod(clinicId, dateTime);
    }

    public int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) throws ManagementSystemException {
        return useCaseManagerInterface.addMultipleTimePeriods(clinicId, start, end, interval);
    }

    public String logAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        return useCaseManagerInterface.logAppointment(clinicId, appointmentId);
    }

    public String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand) throws ManagementSystemException {
        return useCaseManagerInterface.logWalkIn(clinicId, vaccinationID, clientHCN, dateTime, brand);
    }

    public StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime) throws ManagementSystemException {
        return useCaseManagerInterface.logByDateTime(clinicId, dateTime);
    }

    public StringBuilder logByDate(int clinicId, LocalDate date) throws ManagementSystemException {
        return useCaseManagerInterface.logByDate(clinicId, date);
    }


    public String bookAppointment(int clinicId, String healthCareNumber,
                                   LocalDateTime appointmentTime, String vaccineBrand, int appointmentId) throws ManagementSystemException {
        return useCaseManagerInterface.bookAppointment(clinicId, healthCareNumber,
                appointmentTime, vaccineBrand, appointmentId);
    }

    public String cancelAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        return useCaseManagerInterface.cancelAppointment(clinicId, appointmentId);
    }

    public String viewAppointment(int clinicId, int appointmentId) throws ManagementSystemException {
        return useCaseManagerInterface.viewAppointment(clinicId, appointmentId);
    }

    // Add a batch to the specified clinic given the parameters
    // Returns whether the batch was added
    public String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId) throws ManagementSystemException {
        return this.useCaseManagerInterface.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);
    }

    // Getters
    public List<Integer> getClinicIds() {
        return this.useCaseManagerInterface.getClinicIds();
    }

    public List<Integer> getBookableClinicIds() {return this.useCaseManagerInterface.getBookableClinicIds();}



    public String getSupplyByClinic(int clinicId) {return this.useCaseManagerInterface.getSupplyStringByClinic(clinicId);}
}