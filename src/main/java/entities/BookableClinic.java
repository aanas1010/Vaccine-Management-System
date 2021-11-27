package entities;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the main BookableClinic entity which extends Clinic
 * BookableClinics have the added functionality of allowing appointment booking
 */

public class BookableClinic extends ClinicDecorator{
    private final List<Appointment> appointments;

    /**
     * constructs a bookable clinic object
     *
     * @param decoratedClinic an object of the interface for service location.
     */
    public BookableClinic(ServiceLocation decoratedClinic)
    {
        super(decoratedClinic);
        this.appointments = new ArrayList<>();
    }

    /**
     * Try to add the appointment to the list and return whether the appointment was added.
     *
     * @param ap appointment that is getting added to the list.
     * @return true if appointment added successfully.
     */
    public boolean addAppointment(Appointment ap) {
        // If this appointment ID is already taken, do not add the appointment
        for(Appointment a: appointments) {
            if(a.getAppointmentId() == ap.getAppointmentId()) {
                return false;
            }
        }
        appointments.add(ap);
        return true;
    }

    /**
     * Return the Appointment record by ID.
     *
     * @param id of the appointment
     * @return appointment by id if found; null otherwise.
     */
    public Appointment getAppointmentRecord(int id) {
        for(Appointment record: appointments) {
            if(id == record.getAppointmentId()) {
                return record;
            }
        }
        return null;
    }

    /**
     * Try to remove an appointment from the list.
     *
     * @param ap appointment we are interested in removing.
     * @return true if the appointment removed successfully; false otherwise.
     */
    public boolean removeAppointment(Appointment ap) {
        if(appointments.contains(ap)) {
            ap.clientVaccineBatch.changeReserve(-1); // This adds 1 to the quantity
            appointments.remove(ap);
            return true;
        }else {
            return false;
        }
    }

    /**
     * Try to remove an appointment by ID from the list.
     *
     * @param id the id of the appointment we are interested in removing.
     * @return true if appointment removed successfully; false otherwise.
     */
    public boolean removeAppointmentById(int id) {
        return removeAppointment(getAppointmentRecord(id));
    }

    /**
     * logs an appointment that has already happened.
     *
     * @param appointment the appointment we want to log.
     */
    public void logPastVaccinations(Appointment appointment) {
        decoratedClinic.getVaccineLog().addToLog(appointment);
    }

    /**
     * getter.
     *
     * @return list of appointment ids
     */
    public List<Integer> getAppointmentIds() {
        List<Integer> appointmentIds = new ArrayList<>();
        for(Appointment ap : appointments) {
            appointmentIds.add(ap.getAppointmentId());
        }
        return appointmentIds;
    }

    /**
     * getters.
     *
     * @param timePeriod the date in which are interested in getting a list of appointments.
     * @return list of appointment at a given date.
     */
    public List<Appointment> getAppointmentByTimePeriod(TimePeriod timePeriod){
        List<Appointment> dateAppointments = new ArrayList<>();
        for (Appointment appointment: appointments){
            if (appointment.getTimePeriod().equals(timePeriod)){
                dateAppointments.add(appointment);
            }
        }
        return  dateAppointments;
    }
}