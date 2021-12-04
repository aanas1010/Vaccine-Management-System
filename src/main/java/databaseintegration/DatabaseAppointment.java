package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

public class DatabaseAppointment {
    private final Connection connection;
    private final Statement statement;

    public DatabaseAppointment (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

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

    public JsonArray loadAllAppointments() throws SQLException {
        //TODO need to only get the appointments from a specific clinic
        String query = "SELECT * FROM appointment";
        ResultSet resultSet = statement.executeQuery(query);
        return ResultSetToJSON.toJSON(resultSet);
    }

    public void deleteAppointment (int appointmentID) throws SQLException {
        String query = "DELETE FROM appointment WHERE appointmentID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, appointmentID);
        statement.executeUpdate(query);
    }
}
