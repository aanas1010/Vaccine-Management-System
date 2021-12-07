package databaseintegration;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This is the interface for an adapter that stores data that can then be loaded later
 * This interface corresponds to classes that lie on the "Controllers" layer of clean architecture
 */

public interface DataModification {

    /** Write information for a new appointment
     *
     * @param appointmentID the ID of the appointment
     * @param clinicID the ID of the clinic
     * @param clientID the HCN of the client
     * @param periodID the ID of the time period
     * @param batchID the ID of the batch
     * @param brand the brand of the vaccine
     * @throws SQLException if the data could not be written
     */
    void writeToAppointment(int appointmentID, int clinicID, String clientID, int periodID, int batchID, String brand)
            throws SQLException;

    /** Write information for a new time period
     *
     * @param periodID the ID of the time period
     * @param clinicID the ID of the clinic
     * @param availableSlots the number of available slots for this time period
     * @param bookedSlots the number of reserved slots for this time period
     * @param datetime the datetime of this time period
     * @throws SQLException if the data could not be written
     */
    void writeToTimePeriods(int periodID, int clinicID, int availableSlots, int bookedSlots, LocalDateTime datetime) throws SQLException;

    /** Write information for a new vaccine batch
     *
     * @param batchID the ID of the batch
     * @param clinicID the ID of the clinic
     * @param brand the brand of this batch
     * @param expiryDate the expiry date for this batch
     * @param reserved the number of reserved doses
     * @param quantity the number of total doses
     * @throws SQLException if the data could not be written
     */
    void writeToVaccineBatch(int batchID, int clinicID, String brand,
                             LocalDate expiryDate, int reserved, int quantity) throws SQLException;

    /** Delete an appointment
     *
     * @param clinicID the ID of the clinic
     * @param appointmentID the ID of the appointment
     * @throws SQLException if the data could not be deleted
     */
    void deleteFromAppointments(int clinicID, int appointmentID) throws SQLException;

    /** Update the reserved quantity of a batch
     *
     * @param batchID the ID of the batch
     * @param reserved the number of reserved doses in this batch
     * @throws SQLException if the data could not be modified
     */
    void updateReservedInBatch(int batchID, int reserved) throws SQLException;

    /** Update the number of booked slots for a time period
     *
     * @param clinicID the ID of the clinic
     * @param periodID the ID of the time period
     * @param available the number of availabilities for this time period
     * @param booked the number of booked slots for this time period
     * @throws SQLException if the data could not be updated
     */
    void updateBookedAvailableSlots(int clinicID, int periodID, int available, int booked) throws SQLException;

    /** Update the 'hasAppointment' property of a client to false
     *
     * @param healthCareID the HCN of the client
     * @throws SQLException if the data could not be updated
     */
    void updateToNoAppointment(String healthCareID) throws SQLException;

    /** Update the 'hasAppointment' property of a client to true
     *
     * @param healthCareID the HCN of the client
     * @throws SQLException if the data could not be updated
     */
    void updateToHasAppointment(String healthCareID) throws SQLException;
}
