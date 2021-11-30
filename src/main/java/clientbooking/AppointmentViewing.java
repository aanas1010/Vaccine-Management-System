package clientbooking;

import constants.ManagementSystemException;
import entities.*;

/**
 * This is the Use Case for viewing appointments.
 * Every time the use case is needed, a new AppointmentViewing instance is created
 * with the only parameters being the clinic and appointment ID
 */

public class AppointmentViewing {

    private final int appointmentID;
    private final ClinicDecorator clinic;

    /**
     * creates Use Case for viewing appointments.
     *
     * @param appointmentID the id of the appointment
     * @param clinic the clinic where the appointment suppose to happen
     */
    public AppointmentViewing(int appointmentID, ClinicDecorator clinic)
    {
        this.clinic = clinic;
        this.appointmentID = appointmentID;
    }

    /**
     * exhibit the appointment's appointment details.
     *
     * @return string of the appointment.
     * @throws ManagementSystemException thrown when appointmentID does not exist in appointment records.
     */
    public String appointmentDetails() throws ManagementSystemException {
        if(this.clinic.getAppointmentRecord(appointmentID) != null) { //booked active_appointment
            return this.clinic.getAppointmentRecord(appointmentID).toString();
        }
        throw new ManagementSystemException(ManagementSystemException.APPOINTMENT_DOES_NOT_EXIST);
    }
}
