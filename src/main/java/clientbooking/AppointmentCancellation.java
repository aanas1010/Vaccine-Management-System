package clientbooking;

import constants.ManagementSystemException;
import entities.*;
import managers.Modifier;

/**
 * This is the Use Case for cancelling appointments.
 * Every time the use case is needed, a new AppointmentCancellation instance is created
 * with the only parameters being the clinic and appointment ID
 */

public class AppointmentCancellation {

    final int appointmentId;
    final ClinicDecorator clinic;
    final Modifier modifier;

    /**
     * creates Use Case for canceling appointments.
     *
     * @param appointmentId the id of the appointment
     * @param clinic the clinic where the appointment suppose to happen
     */
    public AppointmentCancellation(int appointmentId, ClinicDecorator clinic){
        this.appointmentId = appointmentId;
        this.clinic = clinic;
        this.modifier = null;
    }

    /**
     * creates Use Case for canceling appointments.
     *
     * @param appointmentId the id of the appointment
     * @param clinic the clinic where the appointment suppose to happen
     * @param modifier the modifier object it is referred to
     */
    public AppointmentCancellation(int appointmentId, ClinicDecorator clinic, Modifier modifier){
        this.appointmentId = appointmentId;
        this.clinic = clinic;
        this.modifier = modifier;
    }

    /**
     * Delete the appointment.
     *
     * @return true if process successfully; false otherwise.
     * @throws ManagementSystemException if there is no appointment to delete.
     */
    public String deleteAppointment() throws ManagementSystemException {
        Appointment appointment = clinic.getAppointmentRecord(this.appointmentId);
        if (appointment != null) {
            appointment.getClient().disapproveAppointment();
            clinic.removeAppointmentById(this.appointmentId);
            appointment.getTimePeriod().addAvailableSlot();

            if(modifier != null) {
                this.modifier.UpdateBookedAvailableSlots(appointment.getTimePeriod(),
                        this.clinic.getServiceLocationId());
            }

            if(modifier != null) {
                this.modifier.UpdateReservedInBatch(appointment.getClientVaccineBatch(),
                        this.clinic.getServiceLocationId());
            }

            if(modifier != null) {
                this.modifier.DeleteAppointment(appointment, this.clinic.getServiceLocationId());
            }

            if(modifier != null) {
                this.modifier.UpdateToNoAppointment(appointment.getClient().getHealthCareNumber());
            }

            return appointment.toString();
        }
        throw new ManagementSystemException(ManagementSystemException.APPOINTMENT_DOES_NOT_EXIST);
    }


}