package client_booking;

import entities.*;

/**
 * This is the Use Case for viewing appointments.
 * Every time the use case is needed, a new AppointmentViewing instance is created
 * with the only parameters being the clinic and appointment ID
 */

public class AppointmentViewing {

    /*
        clinic: the clinic where the appointment happened
        appointmentID: the id of an appointment

     ps. when it is in the log it has a prefix A for booked appointments
     and prefix V when walk-in appointment.
     */

    private final int appointmentID;
    private final ServiceLocation clinic;

    // Constructor
    public AppointmentViewing(int appointmentID, ClinicDecorator clinic)
    {
        this.clinic = clinic;
        this.appointmentID = appointmentID;
    }

    /*
    Return a string of the details about the appointment

    produces the following message if:
    appointment exists and hasn't passed - toString from appointment
    appointment never existed            - null
     */
    public String appointmentDetails()
    {

        if(((BookableClinic)this.clinic).getAppointmentRecord(appointmentID) != null) { //booked active_appointment
            return getBookedAppointmentString(((BookableClinic) this.clinic).getAppointmentRecord(appointmentID));

        }
        return null;
    }

    // message methods

    //when appointment exists and active
    private String getBookedAppointmentString(Appointment appointment) {
        return appointment.toString();
    }
}
