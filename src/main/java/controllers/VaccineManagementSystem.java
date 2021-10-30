package controllers;

import clinic_management.ClinicManagement;
import clinic_management.ClinicManagerInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the Controller for a vaccine management system
 * that communicates with the use case interfaces
 */

public class VaccineManagementSystem implements ManagementSystem {
    private final ClinicManagerInterface clinicManagement;

    // Constructor
    public VaccineManagementSystem(int num) {
        this.clinicManagement = new ClinicManagement(num);
    }

    public boolean setEmployees(int clinicId, LocalDate date, int employees) {
        return clinicManagement.setEmployees(clinicId, date, employees);
    }

    public boolean addTimePeriod(int clinicId, LocalDateTime dateTime) {
        return clinicManagement.addTimePeriod(clinicId, dateTime);
    }

    public boolean removeTimePeriod(int clinicId, LocalDateTime dateTime){
        return clinicManagement.removeTimePeriod(clinicId, dateTime);
    }

    public int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) {
        return clinicManagement.addMultipleTimePeriods(clinicId, start, end, interval);
    }


    // Add a batch to the specified clinic given the parameters
    // Returns whether the batch was added
    public boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
        return this.clinicManagement.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);
    }

    // Getters
    public ArrayList<Integer> getClinicIds() {
        return this.clinicManagement.getClinicIds();
    }

    public String getSupplyByClinic(int clinicId) {return this.clinicManagement.getSupplyStringByClinic(clinicId);}
}