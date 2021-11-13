package controllers;

import client_booking.AddRecord;
import entities.ClinicDecorator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface UseCaseManagerInterface {
    ArrayList<Integer> getClinicIds();

    ArrayList<Integer> getBookableClinicIds();

    void addClinic(int clinicId, String location);

    void addBookableClinic(int clinicId, String location);

    void addClient(String name, String healthCardNumber);

    String setEmployees(int clinicId, LocalDate date, int employees);

    String addTimePeriod(int clinicId, LocalDateTime dateTime);

    String removeTimePeriod(int clinicId, LocalDateTime dateTime);

    int addMultipleTimePeriods(int clinicId, LocalDateTime start, LocalDateTime end, int interval);

    String addBatch(int clinicId, String batchBrand, int batchQuantity, LocalDate batchExpiry, int batchId);

    String getSupplyStringByClinic(int clinicId);

    String logAppointment(int clinicId, int appointmentId);

    String logWalkIn(int clinicId, String vaccinationID, String clientHCN, LocalDateTime dateTime, String brand);

    StringBuilder logByDateTime(int clinicId, LocalDateTime dateTime);

    StringBuilder logByDate(int clinicId, LocalDate date);

    String bookAppointment(int clinicId, String healthCareNumber,
                            LocalDateTime appointmentTime, String vaccineBrand, int appointmentId);

    String cancelAppointment(int clinicId, int appointmentId);

    String viewAppointment(int clinicId, int appointmentId);

}
