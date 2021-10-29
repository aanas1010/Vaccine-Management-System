package controllers;

import clinic_management.ClinicManagement;
import clinic_management.ClinicManagerInterface;

import java.time.LocalDate;
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