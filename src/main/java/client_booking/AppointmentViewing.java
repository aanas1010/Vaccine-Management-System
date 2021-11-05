package client_booking;

import entities.Appointment;
import entities.BookableClinic;
import entities.ServiceLocation;

//user case class for viewing a specific appointment
public class AppointmentViewing {

    /*
        clinic: the clinic where the appointment happened
        appointmentID: the id of an appointment

     ps. when it is in the log it has a prefix A for booked appointments
     and prefix V when walk-in appointment.
     */

    private final int appointmentID;
    private final ServiceLocation clinic;

    public AppointmentViewing(int appointmentID, ServiceLocation clinic)
    {
        this.clinic = clinic;
        this.appointmentID = appointmentID;
    }

    /*
    return a string of the details about the appointment

    produces athe following message if:
    appointment exists and hasn't passed - toString from appointment
    appointment was logged and passed    - toString from vaccinationLog
    appointment never existed            - null
     */
    public String appointmentDetails()
    {
        if(this.clinic.getAppointmentRecord(appointmentID) != null) //booked active_appointment
            return appointmentBooked_message(this.clinic.getAppointmentRecord(appointmentID));

        else
            if(this.clinic.getVaccineLog().getVaccinationRecord("A" + appointmentID) != null) //booked passed_appointment
                return appointmentPassed_message("A" + appointmentID);
            else if(clinic.getVaccineLog().getVaccinationRecord("V" + appointmentID) != null) //walk-in passed_appointment
                return appointmentPassed_message("V" + appointmentID);
            else
                return noAppointmentBooked_message();
    }

    // message methods

    //when appointment exists and active
    private String appointmentBooked_message(Appointment appointment) {return appointment.toString();}

    //when appointment has passed
    private String appointmentPassed_message(String appointmentID_str) {return this.clinic.getVaccineLog().getRecordString(appointmentID_str);}

    //when appointment never existed
    private String noAppointmentBooked_message() {return null;}
}