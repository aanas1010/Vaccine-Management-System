package entities;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the main BookableClinic entity which extends Clinic
 * BookableClinics have the added functionality of allowing appointment booking
 */

public class BookableClinic extends ClinicDecorator{
    private final List<Appointment> appointments;

    // Basic constructor
    public BookableClinic(ServiceLocation decoratedClinic)
    {
        super(decoratedClinic);
        this.appointments = new ArrayList<>();
    }

    // Try to add the appointment to the list and return whether the appointment was added
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

    // Return the Appointment record by ID. Return null if it cannot be found
    public Appointment getAppointmentRecord(int id) {
        for(Appointment record: appointments) {
            if(id == record.getAppointmentId()) {
                return record;
            }
        }
        return null;
    }

    // Try to remove an appointment from the list. Return whether successful
    public boolean removeAppointment(Appointment ap) {
        if(appointments.contains(ap)) {
            ap.clientVaccineBatch.changeReserve(-1); // This adds 1 to the quantity
            appointments.remove(ap);
            return true;
        }else {
            return false;
        }
    }

    // Try to remove an appointment by ID from the list. Return whether successful
    public boolean removeAppointmentById(int id) {
        return removeAppointment(getAppointmentRecord(id));
    }

    // Log a past vaccination (WITH APPOINTMENT)
    public void logPastVaccinations(Appointment appointment) {
        decoratedClinic.getVaccineLog().addToLog(appointment);
    }

    public List<Integer> getAppointmentIds() {
        List<Integer> appointmentIds = new ArrayList<>();
        for(Appointment ap : appointments) {
            appointmentIds.add(ap.getAppointmentId());
        }
        return appointmentIds;
    }

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