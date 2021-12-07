package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

/**
 * This is the interface relating to the database Appointment table
 */

public interface DatabaseAppointmentInterface {

    /** Add data for a new appointment instance in the appointment table
     *
     * @param appointmentID the ID of the appointment
     * @param clinicID the ID of the clinic
     * @param clientID the HCN of the client
     * @param periodID the ID of the time period
     * @param batchID the ID of the batch
     * @param brand the brand of the batch
     * @throws SQLException if the data cannot be added
     */
    void addAppointment (int appointmentID, int clinicID, String clientID, int periodID, int batchID, String brand) throws SQLException;

    /** Load all appointments from a given clinic
     *
     * @param clinicID the ID of the clinic whose appointments we want
     * @return a JsonArray of the appointments
     * @throws SQLException if the data cannot be read
     */
    JsonArray loadAppointments(int clinicID) throws SQLException;

    /** Delete an appointment record from the appointment table
     *
     * @param clinicID the ID of the clinic whose appointment we want to delete
     * @param appointmentID the ID of the appointment that we want to delete
     * @throws SQLException if the appointment cannot be deleted
     */
    void deleteAppointment (int clinicID, int appointmentID) throws SQLException;
}
