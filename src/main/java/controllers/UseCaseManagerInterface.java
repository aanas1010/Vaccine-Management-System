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

    boolean AppointmentBooking(Client client, ServiceLocation clinic, TimePeriod timePeriod,
                               String vaccineBrand, int appointmentId);

    boolean AppointmentCancellation(int appointmentId, ServiceLocation clinic);

    String AppointmentViewing(Client client, ServiceLocation clinic, TimePeriod timePeriod,
                              String vaccineBrand, int appointmentId);
}
