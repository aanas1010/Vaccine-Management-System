package entities;

import entities.VaccineSupply;
import entities.VaccineBatch;
import entities.Appointment;
import entities.Clinic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the BookableServiceLocation interface which supports methods for the BookableClinic class.
 */
public interface BookableServiceLocation extends ServiceLocation {

    boolean addAppointment(Appointment ap);

    Appointment getAppointmentRecord(int id);

    boolean removeAppointment(Appointment ap);

    ArrayList<TimePeriod> getTimePeriods(LocalDate date);

    boolean removeAppointmentById(int id);

    void logPastVaccinations(Appointment appointment);

    void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand);

    int getServiceLocationId();

    ArrayList<VaccineBatch> getSupply();

    VaccineSupply getSupplyObj();

}
