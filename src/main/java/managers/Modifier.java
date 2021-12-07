package managers;

import databaseintegration.DataModification;
import entities.*;

import java.sql.SQLException;

/**
 * This is the Use Case that stores data to a class that implements DataStoring
 */

public class Modifier {
    final DataModification dataModifying;

    public Modifier(DataModification dataModifying){this.dataModifying = dataModifying;}

    /**
     * Storing a vaccine batch in the database
     *
     * @param batch the vaccine batch being stored
     * @param clinicID the ID of the clinic where the vaccine batch is located
     */
    public void StoreBatch(VaccineBatch batch, int clinicID){
        try {

            this.dataModifying.writeToVaccineBatch(batch.getId(), clinicID,
                    batch.getBrand(), batch.getExpiry(), batch.getReserve(), batch.getAvailable());
        } catch (SQLException ex) {
            System.out.println("Cannot enter the batch");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Storing a time period in the database
     *
     * @param timePeriod the time period being stored
     * @param clinicID the ID of the clinic where the time period is located
     */
    public void StoreTimePeriod(TimePeriod timePeriod, int clinicID){
        try {
            this.dataModifying.writeToTimePeriods(timePeriod.getID(),
                    clinicID, timePeriod.getAvailableSlots(), timePeriod.getBookedSlots(), timePeriod.getDateTime());
        } catch (SQLException ex) {
            System.out.println("Cannot enter the time period");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Storing an appointment in the database
     *
     * @param appointment the appointment being stored
     * @param clinicID the ID of the clinic where the appointment is located
     */
    public void StoreAppointment(Appointment appointment, int clinicID){
        try {
            this.dataModifying.writeToAppointment(appointment.getAppointmentId(), clinicID,
                    appointment.getClient().getHealthCareNumber(),
                    appointment.getTimePeriod().getID(), appointment.getClientVaccineBatch().getId(),
                    appointment.getVaccineBrand());
        } catch (SQLException ex) {
            System.out.println("Cannot enter the appointment");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Deleting an appointment in the database
     *
     * @param appointment the appointment being stored
     * @param clinicID the ID of the clinic where the appointment is located
     */
    public void DeleteAppointment(Appointment appointment, int clinicID){
        try {
            this.dataModifying.deleteFromAppointments(clinicID, appointment.getAppointmentId());
        } catch (SQLException ex) {
            System.out.println("Cannot delete the appointment");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Updating the reserved amount in the database
     *
     * @param batch the batch being updated
     */
    public void UpdateReservedInBatch(VaccineBatch batch){
        try {
            this.dataModifying.updateReservedInBatch(batch.getId(), batch.getReserve());
        } catch (SQLException ex) {
            System.out.println("Cannot update the batch");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Updating the amount of booked and available slots for a time period in the database
     *
     * @param timePeriod the time period being updated
     * @param clinicID the ID of the clinic where the time period belongs to
     */
    public void UpdateBookedAvailableSlots(TimePeriod timePeriod, int clinicID){
        try {
            this.dataModifying.updateBookedAvailableSlots(clinicID, timePeriod.getID(),
                    timePeriod.getAvailableSlots(), timePeriod.getBookedSlots());
        } catch (SQLException ex) {
            System.out.println("Cannot update the slots");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Updating the client table
     *
     * @param healthCareID the ID of the client being updated
     */
    public void UpdateToNoAppointment(String healthCareID){
        try {
            this.dataModifying.updateToNoAppointment(healthCareID);
        } catch (SQLException ex) {
            System.out.println("Cannot update the client");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Updating the client table
     *
     * @param healthCareID the ID of the client being updated
     */
    public void UpdateToHasAppointment(String healthCareID){
        try {
            this.dataModifying.updateToHasAppointment(healthCareID);
        } catch (SQLException ex) {
            System.out.println("Cannot update the client");
            System.out.println(ex.getMessage());
        }
    }
}
