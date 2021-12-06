package databaseintegration;

import javax.json.JsonArray;
import java.sql.*;

public class DatabaseAppointment implements DatabaseAppointmentInterface{
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

    public JsonArray loadAppointments(int clinicID) throws SQLException {
        String query = "SELECT * FROM appointment WHERE clinicID = ?";
        PreparedStatement state = connection.prepareStatement(query);
        state.setInt(1, clinicID);
        ResultSet resultSet = state.executeQuery();
        return ResultSetToJSON.toJSON(resultSet);
    }

    public void deleteAppointment (int clinicID, int appointmentID) throws SQLException {
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
