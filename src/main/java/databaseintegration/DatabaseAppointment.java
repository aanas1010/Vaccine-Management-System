package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

/**
 * This is the driver relating to the database Appointment table
 */

public class DatabaseAppointment implements DatabaseAppointmentInterface{
    private final Connection connection;

    public DatabaseAppointment(Connection connection) {
        this.connection = connection;
    }

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
    public void addAppointment (int appointmentID, int clinicID, String clientID, int periodID, int batchID, String brand) throws
            SQLException {
        String query = "INSERT INTO appointment VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement state =  connection.prepareStatement(query);
        state.setInt(1, appointmentID);
        state.setInt(2, clinicID);
        state.setString(3, clientID);
        state.setInt(4, periodID);
        state.setInt(5, batchID);
        state.setString(6, brand);

        state.executeUpdate();
    }

    /** Load all appointments from a given clinic
     *
     * @param clinicID the ID of the clinic whose appointments we want
     * @return a JsonArray of the appointments
     * @throws SQLException if the data cannot be read
     */
    public JsonArray loadAppointments(int clinicID) throws SQLException {
        String query = "SELECT * FROM appointment WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet resultSet = state.executeQuery();
        return ResultSetToJSON.toJSON(resultSet);
    }

    /** Delete an appointment record from the appointment table
     *
     * @param clinicID the ID of the clinic whose appointment we want to delete
     * @param appointmentID the ID of the appointment that we want to delete
     * @throws SQLException if the appointment cannot be deleted
     */
    public void deleteAppointment (int clinicID, int appointmentID) throws SQLException {
        System.out.println("DELETING");
        connection.setAutoCommit(false);
        String query = "DELETE FROM appointment WHERE appointmentID = ? AND clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, appointmentID);
        state.setInt(2, clinicID);
        state.executeUpdate();
        connection.commit();
        System.out.println("Successfully deleted appointment");
    }
}
