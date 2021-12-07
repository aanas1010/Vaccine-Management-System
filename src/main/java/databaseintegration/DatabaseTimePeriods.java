package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

/**
 * This is the driver relating to the database Time Period table
 */

public class DatabaseTimePeriods implements DatabaseTimePeriodsInterface {
    private final Connection connection;

    public DatabaseTimePeriods(Connection connection) {
        this.connection = connection;
    }

    /** Add a time period to the time period table
     *
     * @param periodID the ID of the time period
     * @param clinicID the ID of the clinic
     * @param availableSlots the number of available slots for this time period
     * @param bookedSlots the number of reserved slots for this time period
     * @param datetime the timestamp of this time period
     * @throws SQLException if the data could not be added
     */
    public void addTimePeriod (int periodID, int clinicID, int availableSlots, int bookedSlots, Timestamp datetime)
            throws SQLException {
        String query = "INSERT INTO timePeriods VALUES (?, ?, ?, ?, ?)";
        PreparedStatement state =  connection.prepareStatement(query);
        state.setInt(1, periodID);
        state.setInt(2, clinicID);
        state.setInt(3, availableSlots);
        state.setInt(4, bookedSlots);
        state.setTimestamp(5, datetime);

        state.executeUpdate();
    }

    /** Load all time periods for a given clinic
     *
     * @param clinicID the ID of the clinic whose time periods we want to load
     * @return a JsonArray of the time periods for this clinic
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray loadTimePeriods(int clinicID) throws SQLException {
        String query = "SELECT * FROM timePeriods WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet resultSet = state.executeQuery();
        return ResultSetToJSON.toJSON(resultSet);
    }

    /** Update an existing time period in the time period table
     *
     * @param clinicID the ID of the clinic
     * @param periodID the ID of this time period
     * @param availableSlots the number of new available slots for this time period
     * @param bookedSlots the number of new reserved slots for this time period
     * @throws SQLException if the data could not be modified
     */
    public void updateTimePeriods(int clinicID, int periodID, int availableSlots, int bookedSlots) throws SQLException {
        connection.setAutoCommit(false);
        String query = "UPDATE timePeriods SET availableSlots = ?, bookedSlots = ? WHERE periodID = ? AND clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, availableSlots);
        state.setInt(2, bookedSlots);
        state.setInt(3, periodID);
        state.setInt(4, clinicID);
        state.executeUpdate();
        connection.commit();
    }
}
