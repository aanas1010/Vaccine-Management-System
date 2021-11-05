package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This is the ServiceLocation interface which supports methods for
 * Reading and writing vaccine and appointment details
 */
// Add comments
public interface ServiceLocation {

    void setShift(LocalDate date, int num);

    void addTimePeriod(TimePeriod timePeriod, LocalDate date);

    void removeTimePeriod(LocalDateTime dateTime);

    int getServiceLocationId();

    int getShiftForDate(LocalDate date);

    boolean shiftAvailable(LocalDate date);

    boolean containsShift(LocalDate date);

    boolean checkTimePeriod(LocalDateTime dateTime);


    ArrayList<TimePeriod> getTimePeriods(LocalDate date);

   VaccinationLog getVaccineLog();


    ArrayList<VaccineBatch> getSupply();

    VaccineSupply getSupplyObj();


    //option if we choose not to use casting for clinics:

    // // methods from the decoration classes

    // //BookableClinic

    // boolean addAppointment(Appointment ap);

    // Appointment getAppointmentRecord(int id);

    // boolean removeAppointment(Appointment ap);

    // boolean removeAppointmentById(int id);

    // void logPastVaccinations(Appointment appointmentRecord);

    // //WalkInClinic
    // void logPastVaccinations(String vaccinationId, Client client, LocalDateTime dateTime, String vaccineBrand);

}
