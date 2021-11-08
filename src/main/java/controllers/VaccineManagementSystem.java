package controllers;

import entities.BookableClinic;
import entities.Client;
import entities.Clinic;
import entities.TimePeriod;

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
    public VaccineManagementSystem(int num, ArrayList<Clinic> listOfClinics) {
        this.useCaseManagerInterface = new UseCaseManager(num, listOfClinics);
    }

    public boolean setEmployees(int clinicId, LocalDate date, int employees) {
        return useCaseManagerInterface.setEmployees(clinicId, date, employees);
    }

    public boolean addTimePeriod(int clinicId, LocalDateTime dateTime) {
        return useCaseManagerInterface.addTimePeriod(clinicId, dateTime);
    }

    public boolean removeTimePeriod(int clinicId, LocalDateTime dateTime){
        return useCaseManagerInterface.removeTimePeriod(clinicId, dateTime);
    }

    public int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval) {
        return useCaseManagerInterface.addMultipleTimePeriods(clinicId, start, end, interval);
    }

    public boolean bookAppointment(int clinicId, String clientName, String healthCareNumber,
                                   LocalDateTime appointmentTime, String vaccineBrand, int appointmentId){
        return useCaseManagerInterface.bookAppointment(clinicId, clientName, healthCareNumber,
                appointmentTime, vaccineBrand, appointmentId);
    }

    public boolean cancelAppointment(int clinicId, int appointmentId) {
        return useCaseManagerInterface.cancelAppointment(clinicId, appointmentId);
    }

    public String viewAppointment(int clinicId, int appointmentId){
        return useCaseManagerInterface.viewAppointment(clinicId, appointmentId);
    }

    // Add a batch to the specified clinic given the parameters
    // Returns whether the batch was added
    public boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId){
        return this.useCaseManagerInterface.addBatch(clinicId, batchBrand, batchQuantity, batchExpiry, batchId);
    }

    // Getters
    public ArrayList<Integer> getClinicIds() {
        return this.useCaseManagerInterface.getClinicIds();
    }

    public ArrayList<Integer> getBookableClinicIds() {return this.useCaseManagerInterface.getBookableClinicIds();}



    public String getSupplyByClinic(int clinicId) {return this.useCaseManagerInterface.getSupplyStringByClinic(clinicId);}
}