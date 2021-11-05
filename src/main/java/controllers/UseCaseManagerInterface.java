package controllers;

import entities.ServiceLocation;
import entities.Client;
import entities.TimePeriod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface UseCaseManagerInterface {
    ArrayList<Integer> getClinicIds();

    boolean setEmployees(int clinicId, LocalDate date, int employees);

    boolean addTimePeriod(int clinicId, LocalDateTime dateTime);

    boolean removeTimePeriod(int clinicId, LocalDateTime dateTime);

    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval);

    boolean addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

    String getSupplyStringByClinic(int clinicId);

    boolean bookAppointment(int clinicId, String clientName, String healthCareNumber,
                            LocalDateTime appointmentTime, String vaccineBrand, int appointmentId);

    boolean cancelAppointment(int clinicId, int appointmentId);

    String viewAppointment(int clinicId, int appointmentId);

}
