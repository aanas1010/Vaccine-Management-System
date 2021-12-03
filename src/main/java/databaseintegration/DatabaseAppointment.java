package databaseintegration;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseAppointment {
    private final Connection connection;
    private final Statement statement;

    public DatabaseAppointment (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public void addAppointment (int appointmentID, int clinicID, String clientID, int periodID, int batchID) throws
            SQLException {
        String query = "INSERT INTO appointment VALUES (?, ?, ?, ?, ?)";
        PreparedStatement state =  connection.prepareStatement(query);
        state.setInt(1, appointmentID);
        state.setInt(2, clinicID);
        state.setString(3, clientID);
        state.setInt(4, periodID);
        state.setInt(5, batchID);

        state.executeUpdate();
    }

    public ArrayList<Object> loadAllBatches () throws SQLException {
        String query = "SELECT * FROM appointment";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Object> results = new ArrayList<>();
        while(resultSet.next()) {
            results.add(resultSet.getInt("appointmentID"));
            results.add(resultSet.getInt("clinicID"));
            results.add(resultSet.getString("clientID"));
            results.add(resultSet.getInt("periodID"));
            results.add(resultSet.getInt("batchID"));
        }
        return results;
    }
}
