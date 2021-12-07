package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;

/**
 * This is the interface for an adapter that loads data from a ResultSet
 * This interface corresponds to classes that lie on the "Controllers" layer of clean architecture
 */

public interface DataRetrieval {
    /** Get the IDs of the clinics
     *
     * @return a JsonArray of the clinics
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray getClinicIDs() throws SQLException;

    /** Get the IDs of the bookable clinics
     *
     * @return a JsonArray of the bookable clinics
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray getBookableClinicIDs() throws SQLException;

    /** Get all clients
     *
     * @return a JsonArray of the clients
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray getClients() throws SQLException;

    /** Get the info for a clinic
     *
     * @param clinicID the clinic whose ID we want
     * @return a JsonArray of clinics
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray getClinicInfo(int clinicID) throws SQLException;

    /** Get the batches for a clinic
     *
     * @param clinicID the ID of the clinic whose batches we want
     * @return a JsonArray of the batches
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray getVaccineBatches(int clinicID) throws SQLException;

    /** Get the time periods for a clinic
     *
     * @param clinicID the ID of the clinic whose batches we want
     * @return a JsonArray of the time periods
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray getTimePeriods(int clinicID) throws SQLException;

    /** Get the appointments for a bookable clinic
     *
     * @param clinicID the ID of a bookable clinic whose appoinments we want
     * @return a JsonArray of the appointments
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray getAppointments(int clinicID) throws SQLException;
}
