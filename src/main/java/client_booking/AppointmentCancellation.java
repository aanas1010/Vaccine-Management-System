package client_booking;

import entities.*;

/**
 * This is the Use Case for cancelling appointments.
 * Every time the use case is needed, a new AppointmentCancellation instance is created
 * with the only parameters being the clinic and appointment ID
 */

public class AppointmentCancellation {

    int appointmentId;
    ServiceLocation clinic;

    // Constructor
    public AppointmentCancellation(int appointmentId, ServiceLocation clinic){
        this.appointmentId = appointmentId;
        this.clinic = clinic;
    }

    // Delete the appointment. Return true if successful
    public boolean deleteAppointment(){
        Appointment appointment = ((BookableClinic)clinic).getAppointmentRecord(this.appointmentId);
        if (appointment.getClient().getHasAppointment()) {
            appointment.getClient().disapproveAppointment();
            ((BookableClinic)clinic).removeAppointmentById(this.appointmentId);
            appointment.getTimePeriod().addAvailableSlot();
            return true;
        }
        return false;
    }


}