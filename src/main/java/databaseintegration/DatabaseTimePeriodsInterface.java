package databaseintegration;

import javax.json.JsonArray;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * This is the interface relating to the database Time Period table
 */

public interface DatabaseTimePeriodsInterface {

    /** Load all time periods for a given clinic
     *
     * @param clinicID the ID of the clinic whose time periods we want to load
     * @return a JsonArray of the time periods for this clinic
     * @throws SQLException if the data could not be retrieved
     */
    JsonArray loadTimePeriods(int clinicID) throws SQLException;

    /** Add a time period to the time period table
     *
     * @param periodID the ID of the time period
     * @param clinicID the ID of the clinic
     * @param availableSlots the number of available slots for this time period
     * @param bookedSlots the number of reserved slots for this time period
     * @param datetime the timestamp of this time period
     * @throws SQLException if the data could not be added
     */
    void addTimePeriod (int periodID, int clinicID, int availableSlots, int bookedSlots, Timestamp datetime) throws SQLException;

    /** Update an existing time period in the time period table
     *
     * @param clinicID the ID of the clinic
     * @param periodID the ID of this time period
     * @param availableSlots the number of new available slots for this time period
     * @param bookedSlots the number of new reserved slots for this time period
     * @throws SQLException if the data could not be modified
     */
    void updateTimePeriods(int clinicID, int periodID, int availableSlots, int bookedSlots) throws SQLException;
}
