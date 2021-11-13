package client_booking;

import Constants.ExceptionConstants;
import entities.*;

import java.util.concurrent.ExecutionException;

/**
 * This is the Use Case for viewing appointments.
 * Every time the use case is needed, a new AppointmentViewing instance is created
 * with the only parameters being the clinic and appointment ID
 */

public class AppointmentViewing {

    private final int appointmentID;
    private final ClinicDecorator clinic;

    // Constructor
    public AppointmentViewing(int appointmentID, ClinicDecorator clinic)
    {
        this.clinic = clinic;
        this.appointmentID = appointmentID;
    }

    public String appointmentDetails() throws Exception {
        if(this.clinic.getAppointmentRecord(appointmentID) != null) { //booked active_appointment
            return this.clinic.getAppointmentRecord(appointmentID).toString();

        }
        throw new Exception(ExceptionConstants.APPOINTMENT_DOES_NOT_EXIST);
    }
}
