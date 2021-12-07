package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

/**
 * This is the driver relating to the database Client table
 */

public class DatabaseClient implements DatabaseClientInterface{
    private final Connection connection;
    private final Statement statement;

    public DatabaseClient (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    /** Load all clients in the system
     *
     * @return JsonArray of the clients
     * @throws SQLException if the data could not be retrieved
     */
    public JsonArray loadAllClients() throws SQLException {
        String query = "SELECT * FROM client";
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }

    /** Update a client's 'hasAppointment' property to false
     *
     * @param healthCareID the HCN of the client that we want to update
     * @throws SQLException if the data could not be written
     */
    public void updateToNoAppointment(String healthCareID) throws SQLException {
        connection.setAutoCommit(false);
        String query = "UPDATE client SET hasAppointment = FALSE WHERE healthCareID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setString(1, healthCareID);
        state.executeUpdate();
        connection.commit();
        System.out.println("Successfully updated client");
    }

    /** Update a client's 'hasAppointment' property to true
     *
     * @param healthCareID the HCN of the client that we want to update
     * @throws SQLException if the data could not be written
     */
    public void updateToHasAppointment(String healthCareID) throws SQLException {
        connection.setAutoCommit(false);
        String query = "UPDATE client SET hasAppointment = TRUE WHERE healthCareID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setString(1, healthCareID);
        state.executeUpdate();
        connection.commit();
        System.out.println("Successfully updated client");
    }
}
