package databaseintegration;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseClient {
    private final Connection connection;
    private final Statement statement;

    public DatabaseClient (Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public void addBatch (String healthCareID, String name, boolean hasAppointment) throws SQLException {
        String query = "INSERT INTO client VALUES (?, ?, ?)";
        PreparedStatement state =  connection.prepareStatement(query);
        state.setString(1, healthCareID);
        state.setString(2, name);
        state.setBoolean(3, hasAppointment);

        state.executeUpdate();
    }

    public ArrayList<Object> loadAllBatches () throws SQLException {
        String query = "SELECT * FROM client";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Object> results = new ArrayList<>();
        while(resultSet.next()) {
            results.add(resultSet.getString("healthCareID"));
            results.add(resultSet.getString("name"));
            results.add(resultSet.getBoolean("hasAppointment"));
        }
        return results;
    }
}
