package entities;

import entities.VaccineSupply;
import entities.Appointment;

/**
 * This is the BookableServiceLocation interface which supports methods for the BookableClinic class.
 */
public interface BookableServiceLocation {

    boolean addAppointment(Appointment ap);

    Appointment getAppointmentRecord(int id);

    boolean removeAppointment(Appointment ap);

    boolean removeAppointmentById(int id);

    void logPastVaccinations(Appointment appointment);

}
