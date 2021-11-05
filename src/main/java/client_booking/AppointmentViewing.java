package client_booking;

import entities.Appointment;
import entities.BookableClinic;
import entities.ServiceLocation;

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
    public AppointmentViewing(int appointmentID, BookableServiceLocation clinic)
    {
        this.clinic = clinic;
        this.appointmentID = appointmentID;
    }

    /*
    Return a string of the details about the appointment

    produces the following message if:
    appointment exists and hasn't passed - toString from appointment
    appointment was logged and passed    - toString from vaccinationLog
    appointment never existed            - null
     */
    public String appointmentDetails()
    {
        if(((BookableClinic)this.clinic).getAppointmentRecord(appointmentID) != null) //booked active_appointment
            return appointmentBooked_message(((BookableClinic)this.clinic).getAppointmentRecord(appointmentID));

        else
            return noAppointmentBooked_message();
    }

    // message methods

    //when appointment exists and active
    private String getBookedAppointmentString(Appointment appointment) {
        return appointment.toString();
    }

    //when appointment has passed
    private String getPassedAppointmentString(String appointmentID_str) {
        return this.clinic.getVaccineLog().getRecordString(appointmentID_str);
    }

}